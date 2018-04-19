package Main;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Tutorial {
	private static final transient  Logger log 
	
	=  LoggerFactory.getLogger(Tutorial.class);
	
	
	public static void main(String[] args) {
		
		log.info("Apache Shiro Application started");
		
		/*
		 * 使用Shiro的IniSecurityManager实现来提取shiro.ini文件，该实现反应了shiro对工厂模式的支持。
		 * 
		 */
		Factory<org.apache.shiro.mgt.SecurityManager>  factory
		= new IniSecurityManagerFactory("classpath:shiro.ini");
		
		/*
		 * 解析ini文件并返回该配置的的SecurityManager实例
		 */
		org.apache.shiro.mgt.SecurityManager securityManager 
		= factory.getInstance();
		
		SecurityUtils.setSecurityManager(securityManager);
		
		/*
		 * 调取当前正在执行的Subject(当前正在执行的用户的特定的安全视图-正在和软件进行交互的事物)
		 * 可以把Subject看成shiro的User概念
		 */
		Subject subject = SecurityUtils.getSubject();
		
		/*
		 * 获得当前用户的会话，这个Session是Shiro的特定实例，它提供了大部分HttpSession经常使用的东西，
		 * 但它有一个巨大的优势-不需要HTTP环境
		 */
		Session session = subject.getSession();
		
		session.setAttribute("someKey", "aValue");
		
		/*
		 * 监测当前用户是否已经登陆
		 */
		if(!subject.isAuthenticated()){
			
			UsernamePasswordToken token 
			= new UsernamePasswordToken("lonestarr","vespa");
			
			token.setRememberMe(true);
			try {
				subject.login(token);
			} catch (UnknownAccountException e) {
				// TODO: handle exception
				log.info("账号尚未注册");
			} catch (IncorrectCredentialsException e ){
				log.info("密码不正确");
			} catch (LockedAccountException e){
				log.info("账户已停用");
			} 
			
		}
		/*
		 * 获得用户的登陆凭证
		 */
		log.info("User ["+ subject.getPrincipal()+"] logged in successfully");
		
		/*
		 * 检查它是否拥有某个角色
		 */
		if(subject.hasRole("schwartz")){
			log.info("May the Schwartz be with you!");
		}else{
			log.info("Sorry, the Schewart wasn't here");
		}
		
		/*
		 * 检查它是否有权现在特定类型的实体上进行操作
		 */
		if(subject.isPermitted("lightsaber:weild")){
			log.info("You may use the lightsaber ring , use it wisely");
		}else{
			log.info("Sorry ,lightsaber ring are for schwartz master only");
		}
		
		/*
		 * 检查用户是否有能力访问某一类型的特定实例的能力
		 */
		if (subject.isPermitted("winnebago:drive:eagle5")) {
			
			log.info(" you are permitted to drive the winnebago with license plate eagle5 ");
		} else {
			log.info("Sorry ,you are not allowed to drive the eagle5 winnebago！");
		}
		
		/*
		 * 注销登陆
		 */
		subject.logout();
		
		System.exit(0);
	}
}

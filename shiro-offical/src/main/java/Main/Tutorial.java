package Main;

import org.apache.shiro.SecurityUtils;
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
		
		log.info("My second Apache Shiro Application");
		
		Factory<org.apache.shiro.mgt.SecurityManager>  factory
		= new IniSecurityManagerFactory("classpath:shiro.ini");
		
		org.apache.shiro.mgt.SecurityManager securityManager 
		= factory.getInstance();
		
		
		SecurityUtils.setSecurityManager(securityManager);
		
		Subject subject = SecurityUtils.getSubject();
		
		Session session = subject.getSession();
		
		session.setAttribute("someKey", "aValue");
		
		if(!subject.isAuthenticated()){
			
			UsernamePasswordToken token 
			= new UsernamePasswordToken("lonestarr","vespa");
			
			token.setRememberMe(true);
			
			subject.login(token);
		}
		
		log.info("User ["+ subject.getPrincipal()+"] logged in successfully");
		
		
		if(subject.hasRole("schwartz")){
			log.info("May the Schwartz be with you!");
		}else{
			log.info("Sorry, the Schewart wasn't here");
		}
		
		if(subject.isPermitted("lightsaber:weild")){
			log.info("You may use the lightsaber ring , use it wisely");
		}else{
			log.info("Sorry ,lightsaber ring are for schwartz master only");
		}
		
		
		if (subject.isPermitted("winnebago:drive:eagle5")) {
			log.info(" you are permitted to drive the ");
		} else {

		}
		
		System.exit(0);
	}
}

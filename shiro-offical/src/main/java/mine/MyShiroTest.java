
package mine;

import java.util.Arrays;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.WildcardPermission;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

public class MyShiroTest {
	
	public static void main(String[] args) {
		
		Factory<org.apache.shiro.mgt.SecurityManager> factory 
		= new IniSecurityManagerFactory("classpath:myshiro.ini");
		
		org.apache.shiro.mgt.SecurityManager securityManager 
		= factory.getInstance();
		
		SecurityUtils.setSecurityManager(securityManager);
		
		Subject subject = SecurityUtils.getSubject();
		
		UsernamePasswordToken token = new UsernamePasswordToken();
		
		token.setUsername("admin");
		
		token.setPassword("adminpsd".toCharArray());
		
		try {
			
			subject.login(token);
			
		} catch (Exception e) {
			
			System.out.println("login failed");
			
			System.exit(0);
		}
		
		Permission p  = new WildcardPermission("role2do2");
		
		if(subject.isPermitted(p)){
			System.out.println(subject.getPrincipal() +"  has permission -- role2do2");
		}
		
	}
	
}

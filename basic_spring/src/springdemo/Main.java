package springdemo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("springdemo/helloMessage.xml");   
		
		  try {  
	            Person person = (Person) ctx.getBean("person");   
	            String s = person.personSayHello();  
	            System.out.println(s);  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        } finally {  
	            ((ClassPathXmlApplicationContext)ctx).close();  
	        }  
	          
	}
}

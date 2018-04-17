package springdemo;

public class Person {
	
	
	 	private IHelloMessage helloMessage;  
	 	
	  
	    public IHelloMessage getHelloMessage() {  
	        return helloMessage;  
	    }  
	  
	    public void setHelloMessage(IHelloMessage helloMessage) {  
	        this.helloMessage = helloMessage;  
	    }  
	      
	    
	    
	    public String personSayHello(){  
	        return this.helloMessage.sayHello();  
	    }  
}

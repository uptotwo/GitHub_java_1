package interceptor;

public class MainOutsie{
	public static void main(String[] args) {
		
		RiverController riverController = new RiverController();
		
		Interceptor interceptor = new SomeInterceptor();
		
		riverController.setInterceptor(interceptor);
		
		riverController.flow(new River());
		
	}
}

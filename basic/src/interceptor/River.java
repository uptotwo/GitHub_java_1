package interceptor;
/**
 * 
 * @author Euron
 */

class River {
	int volume;
	int numFish;
}

class PowerGenerator {
	
  double generate (int volume) {
    // 假设每一百立方米水发一度电
    return volume / 100;
  }
  
}

interface Interceptor {
  void intercept (River river);
}


class SomeInterceptor implements Interceptor{

	PowerGenerator generator = new PowerGenerator();
	
	@Override
	public void intercept(River river) {
	
		int waterUsed = 1000;
		river.volume  -= waterUsed;
		generator.generate(waterUsed);
		river.numFish = 0;
	}
	
}

class RiverController{
	Interceptor interceptor;
	void flow(River river){
		
		river.volume += 100000;
		river.numFish += 1000;
		interceptor.intercept(river);
		river.volume += 1000;
		
	}
	void setInterceptor(Interceptor interceptor){
		this.interceptor = interceptor;
	}
}

class Main{
	public static void main(String[] args) {
		
		RiverController riverController = new RiverController();
		Interceptor interceptor = new SomeInterceptor();
		
		//这一步通常叫做控制反转或者依赖注入，其实也没啥
		riverController.setInterceptor(interceptor);
		
		riverController.flow(new River());
	}
}





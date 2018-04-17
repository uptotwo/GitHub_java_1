package no_name_inner_class.main;


public class Outer {
	
	public static String s1 = "this is s1 in Outer";
    public static String s2 = "this is s2 in Outer";
    private static String s3 = "this is s3 in Outer";
    
    public void say(Inner inner) {
        System.out.println(inner.words());
    }
    
    private static String method2() {
        return "this is method2 in Outer";
    }
    
    
    public static void main(String[] args) {
    	//
    	
        Outer outer = new Outer();
        
        // 测试1，Inner为接口
        outer.say(new Inner() {
        	
            String s1 = "this is s1 in Inner";
            public String words() {
                // 外部类和匿名函数类中有同名变量s1
                return s1;
            }
        });
        
        // 测试2，Inner1为抽象类
        outer.say(new Inner1() {
            String s2 = "this is s2 in Inner1";

            public String words() {
                // 外部类和匿名函数类中有同名变量s2
                return Outer.s2;
            }
        });
      
        
        // 测试3，Inner2为普通类
        outer.say(new Inner2() {
            public String words() {
                // 访问外部类私有变量s3
                return s3;
            }
        });
        
        
        // 测试4，Inner2为普通类
        outer.say(new Inner2() {
            public String words() {
                // 访问外部类私有方法say()
                return method2();
            }
        });
    }
}

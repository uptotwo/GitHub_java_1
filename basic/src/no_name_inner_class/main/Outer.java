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
        
        // ����1��InnerΪ�ӿ�
        outer.say(new Inner() {
        	
            String s1 = "this is s1 in Inner";
            public String words() {
                // �ⲿ�����������������ͬ������s1
                return s1;
            }
        });
        
        // ����2��Inner1Ϊ������
        outer.say(new Inner1() {
            String s2 = "this is s2 in Inner1";

            public String words() {
                // �ⲿ�����������������ͬ������s2
                return Outer.s2;
            }
        });
      
        
        // ����3��Inner2Ϊ��ͨ��
        outer.say(new Inner2() {
            public String words() {
                // �����ⲿ��˽�б���s3
                return s3;
            }
        });
        
        
        // ����4��Inner2Ϊ��ͨ��
        outer.say(new Inner2() {
            public String words() {
                // �����ⲿ��˽�з���say()
                return method2();
            }
        });
    }
}

package simple_reference_variable;

public class Fruits {
	
	private Integer num;
	private String name;
	
	
	
	public Fruits() {
		super();
	}
	
	public Fruits(Integer num, String name) {
		super();
		this.num = num;
		this.name = name;
	}
	
	
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "Fruits [num=" + num + ", name=" + name + "]";
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 * 
	 * Object �����Ѿ�������equals���������÷���ֱ��ʹ�� == ����ʵ�֣������������� == ���ڱȽ�����Ԫ�صĵ�ַ�������������಻��д�÷�������ô��������ڱȽ�ʱ������Object�ж����
	 *  == �����бȽϡ�
	 *  �ܶೣ�õ��඼��д��equals����������String ,Integer,Double�ȣ��Ա��ڶԶ�������ݽ��бȽϣ������ڶ�����ʱ��һ�㶼������дequals������
	 *  
	 */
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
//		�ö��������null������false
		if( obj == null){
			return false;
		}
//		����ö����뱻�Ƚ϶���Ĵ洢λ����ͬ����������ͬһ�����󣩣�����true
		if( obj == this){
			return true;
		}
		
		if(obj.getClass() == this.getClass()){
			Fruits f = (Fruits)obj;
			return f.name.equals(this.name) && f.num.equals(this.num);
		}else{
			return false;
		}
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 * 
	 * ��Object���У�hash Code��д���� ��
	 *	 public native int hashCode();
	 * �������Object����hashCode˵����һ�����ط�����������ʵ�����뱾�ػ�����صģ���ʵ�ϴ˴�hashCode�ķ���ֵ��ʵ���Ĵ洢��ַ����
	 * 
	 * 
	 * һ���������д��equals������ͨ��Ҳ�б�Ҫ��дhashCode������Ŀ����Ϊ��ά��hashCode�����ĳ�Э�涨����Э��������ȵĶ��������
	 * ��ȵ�ɢ���롣hashCode�ĳ�Э�涨��Ҫ��һ�¼��㣺
	 * 
	 * 1.��javaӦ�ó���ִ���ڼ䣬��ͬһ�������϶�ε���hashCode����ʱ�����ص�ֵ����һ�£�ǰ����equals�Ƚ�ʱ���õ������Ϣ��û�б��޸ģ���
	 * ��ĳһӦ�ó����һ��ִ�е�ͬһӦ�õ���һ��ִ�У����������뱣��һ�¡�
	 * 2.�������equals��������������ʱ��ȵģ���ô�������������ϵ���hashCode��������������ͬ��ֵ��
	 * 3.����������Ǳ���ģ�
	 * 		�������equals��java.lang.Object������������������ȣ���ô����������������һ���������hashCode�����ض������ɲ�ͬ�������ᡣ
	 * 		���ǣ�Ϊ����ȵĶ������ɲ�ͬ����������������ɢ�б�����ܡ�
	 * 
	 * ʵ����,��Object�ඨ���hashCode����ȷʵ����Բ�ͬ�Ķ��󷵻ز�ͬ����������ͨ��ʱͨ�����ö�����ڲ���ַת����һ��������ʵ�ֵģ�����
	 * Java���Բ���Ҫ����ʵ�ּ��ɣ���
	 * 
	 * ע�⣺
	 * 1.��ȵĶ����������ͬ��ɢ����
	 * 2.ɢ������ͬ��һ���������
	 * 3.����ȵĶ���ɢ�����п������
	 * 
	 * 
	 * hashCode��������дʱͨ�������������ԭ��ʵ�֣�
	 * 1. ��ĳ�����㳣��ֵ��������17��������int�ͱ���result��
	 * 2. ���ڶ�����ÿһ���ؼ���f��ָequals�����п��ǵ���ÿһ���򣩲�������ԭ����:
	 * 		2.1 boolean�ͣ�����(f?0:1)
	 * 		2.2 byte,char,short�ͣ�����(int)
	 * 		2.3 long�ͣ�����(int)(f^(f>>>32))
	 * 		2.4 float �ͣ�����Float.floatToIntBits(afloat)
	 * 		2.5 double�ͣ�����Double.doubleToLongBits(adouble)�õ�һ��long,��ִ��long�Ĵ���
	 * 		2.6 �������ã��ݹ��������hashCode����
	 * 		2.7 �����򣬶�����ÿһ��Ԫ�ص�������hashCode����
	 * 3. ������ĵ���ɢ���뱣�浽int�ͱ���c��Ȼ��ִ�� result = 37*result+c
	 * 4. ����result
	 * 
	 * ���ӵ�hashCode��д����Demo���� HashCode_Override_Entity
	 * 
	 */
	
	@Override
	public int hashCode() {
		
		int result  = 17;
		result = 37*result + num.hashCode();
		result = 37*result + name.hashCode();
		// TODO Auto-generated method stub
		return result;
	}
	
	
	
	
	
	
	
	
	
}

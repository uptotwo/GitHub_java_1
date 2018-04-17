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
	 * Object 类中已经定义了equals方法，但该方法直接使用 == 进行实现（在引用类型中 == 用于比较两个元素的地址），因此如果子类不重写该方法，那么子类对象在比较时将调用Object中定义的
	 *  == 来进行比较。
	 *  很多常用的类都重写了equals方法，例如String ,Integer,Double等，以便于对对象的内容进行比较，所以在定义类时，一般都建议重写equals方法。
	 *  
	 */
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
//		该对象如果是null，返回false
		if( obj == null){
			return false;
		}
//		如果该对象与被比较对象的存储位置相同（即它们是同一个对象），返回true
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
	 * 在Object类中，hash Code的写法是 ：
	 *	 public native int hashCode();
	 * 这表明在Object类中hashCode说明是一个本地方法，即它的实现是与本地机器相关的（事实上此处hashCode的返回值是实例的存储地址）。
	 * 
	 * 
	 * 一个类如果重写了equals方法，通常也有必要重写hashCode方法，目的是为了维护hashCode方法的常协规定。该协定声明相等的对象必须有
	 * 相等的散列码。hashCode的常协规定主要有一下几点：
	 * 
	 * 1.在java应用程序执行期间，在同一个对象上多次调用hashCode方法时，返回的值必须一致（前提是equals比较时所用的相关信息并没有被修改）。
	 * 从某一应用程序的一次执行到同一应用的另一次执行，该整数无须保持一致。
	 * 2.如果根据equals方法，两个对象时相等的，那么在这两个对象上调用hashCode方法必须生成相同的值。
	 * 3.以下情况不是必须的：
	 * 		如果根据equals（java.lang.Object）方法，两个对象不相等，那么在两个对象中任意一个上面调用hashCode方法必定会生成不同的整数结。
	 * 		但是，为不相等的对象生成不同的整数结果可以提高散列表的性能。
	 * 
	 * 实际上,由Object类定义的hashCode方法确实会针对不同的对象返回不同的整数。（通常时通过将该对象的内部地址转换成一个整数来实现的，但是
	 * Java语言不需要这种实现技巧）。
	 * 
	 * 注意：
	 * 1.相等的对象必须由相同的散列码
	 * 2.散列码相同不一定对象相等
	 * 3.不相等的对象散列码有可能相等
	 * 
	 * 
	 * hashCode方法在重写时通常按照以下设计原则实现：
	 * 1. 把某个非零常数值，，例如17，保存在int型变量result中
	 * 2. 对于对象中每一个关键域f（指equals方法中考虑到的每一个域）参照以下原则处理:
	 * 		2.1 boolean型，计算(f?0:1)
	 * 		2.2 byte,char,short型，计算(int)
	 * 		2.3 long型，计算(int)(f^(f>>>32))
	 * 		2.4 float 型，计算Float.floatToIntBits(afloat)
	 * 		2.5 double型，计算Double.doubleToLongBits(adouble)得到一个long,在执行long的处理
	 * 		2.6 对象引用，递归调用它的hashCode方法
	 * 		2.7 数组域，对其中每一个元素调用它的hashCode方法
	 * 3. 将上面的到的散列码保存到int型变量c，然后执行 result = 37*result+c
	 * 4. 返回result
	 * 
	 * 复杂的hashCode重写方法Demo见类 HashCode_Override_Entity
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

package double_float_BigDecimal;

import java.math.BigDecimal;
/**
 * 为double型数据提供精确的四则运算
 */
public class ArithUtil {
	
	//默认除法运算精确度
	private static final int DEF_DIV_SCAlE = 10;
	//这个类不能被实例化
	private ArithUtil(){}
	
	/**
	 * 
	 * 提供精确的加法运算
	 * @param v1 被加数
	 * @param v2 加数
	 * @return 两个参数的和
	 * @author Euron
	 */
	
	public static double add(double v1,double v2){
		
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		
		return b1.add(b2).doubleValue();
		
	}
	
	public static double sub(double v1,double v2){
		
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		
		return b1.subtract(b2).doubleValue();
		
	}
	//
	public static double mul(double v1,double v2){
		
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		
		return b1.multiply(b2).doubleValue();
		
	}
	
	public static double div(double v1,double v2){
		
		
		return div(v1,v2,DEF_DIV_SCAlE);
		
		
	}
	
	public static double div(double v1,double v2,int scale){
//		如果制定的精确到的位数小于零，则抛出异常
		if(scale<0){
			throw new IllegalArgumentException("This scale must be a positive integer or zero");
		}

		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		
		return b1.divide(b2,scale,BigDecimal.ROUND_HALF_UP).doubleValue();
		
	}
}

package double_float_BigDecimal;

import java.math.BigDecimal;
/**
 * Ϊdouble�������ṩ��ȷ����������
 */
public class ArithUtil {
	
	//Ĭ�ϳ������㾫ȷ��
	private static final int DEF_DIV_SCAlE = 10;
	//����಻�ܱ�ʵ����
	private ArithUtil(){}
	
	/**
	 * 
	 * �ṩ��ȷ�ļӷ�����
	 * @param v1 ������
	 * @param v2 ����
	 * @return ���������ĺ�
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
//		����ƶ��ľ�ȷ����λ��С���㣬���׳��쳣
		if(scale<0){
			throw new IllegalArgumentException("This scale must be a positive integer or zero");
		}

		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		
		return b1.divide(b2,scale,BigDecimal.ROUND_HALF_UP).doubleValue();
		
	}
}

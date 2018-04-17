package simple_reference_variable;

import java.util.Arrays;

public class HashCode_Override_Entity {

		private short ashort;
		private char achar;
		private byte abyte;
		private boolean abool;
		private long along;
		private float afloat;
		private double adouble;
		private HashCode_Override_Entity aObject;
		private int[] ints;
		private HashCode_Override_Entity[] HashCode_Override_Entitys;
		
//		重写equals()方法
		@Override
		public boolean equals(Object o) {
		// TODO Auto-generated method stub
			
			if( o ==null )
				return false;
			if( this.getClass() != o.getClass())
				return false;
			
			HashCode_Override_Entity hoe = (HashCode_Override_Entity)o;
			
			return     hoe.ashort == ashort
					&& hoe.achar == achar
					&& hoe.abyte == abyte
					&& hoe.abool == abool
					&& hoe.along == along
					&& Float.floatToIntBits(hoe.afloat) == Float.floatToIntBits(afloat)
					&& Double.doubleToLongBits(hoe.adouble) == Double.doubleToLongBits(adouble)
					&& hoe.aObject.equals(aObject)
					&& equalsInt(hoe.ints)
					&& equalsHashCode_Override_Entitys(hoe.HashCode_Override_Entitys);
		}

		
		private boolean equalsInt(int[] aints) {
			// TODO Auto-generated method stub
			return  Arrays.equals(ints, aints);
		}

		private boolean equalsHashCode_Override_Entitys(
				HashCode_Override_Entity[] aHashCode_Override_Entitys) {
			// TODO Auto-generated method stub
			return Arrays.equals(HashCode_Override_Entitys, aHashCode_Override_Entitys);
		}

		
		
		
//		重写hashCode()方法
		@Override
		public int hashCode() {
		// TODO Auto-generated method stub
			int result = 17;
			result = 37*result + (int)ashort;
			result = 37*result + (int)achar;
			result = 37*result + (int)abyte;
			result = 37*result + (abool?0:1);
			result = 37*result + (int)(along ^ (along >>> 32));
			result = 37*result + (int)Float.floatToIntBits(afloat);
			long tolong = Double.doubleToLongBits(adouble);
			result = 37*result + (int)(tolong^(tolong>>>32));
			result = 37*result + aObject.hashCode();
			result = 37*result + intsHashCode(ints);
			result = 37*result + HashCode_Override_EntitysHashCode(HashCode_Override_Entitys);
			
		return result;
		}

		
		
		private int intsHashCode(int[] aints) {
			// TODO Auto-generated method stub
			int result = 17;
			for (int i = 0; i < aints.length; i++) {
				result = 37*result +aints[i];
			}
			return result;
		}
		

		private int HashCode_Override_EntitysHashCode(
				HashCode_Override_Entity[] ahashCode_Override_Entitys) {
			// TODO Auto-generated method stub
			int result = 17;
			for (int i = 0; i < ahashCode_Override_Entitys.length; i++) {
				result = 37*result + ahashCode_Override_Entitys[i].hashCode();
			}
			return result;
		}


		
		
}

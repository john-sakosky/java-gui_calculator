public class Enums{
	public enum Base {
		Binary,Octal,Decimal,Hex;
		
		private int size;
		
		static {
			Binary.size = 2;
			Octal.size = 8;
			Decimal.size = 10;
			Hex.size = 16;
		}
		
		public int rows(int columns){
			int rows = size/columns;
			if (size%columns != 0) rows++;
			return rows;
		}
		public int numarical(){
			return size;
		}
	}
	
}
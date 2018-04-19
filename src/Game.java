
public class Game {

	public int LetterToInt(String letter){
		int value=10;
		if (letter.length()==1) {
			value = (int)letter.charAt(0)-65;
		}
		return value;
	}	
		
	public static String IntToLetter(int value){
		String str="";
		switch(value){
			case 0:
				str= "A";
				break;
			case 1:
				str= "B";
				break;
			case 2:
				str= "C";
				break;
			case 3:
				str= "D";
				break;
			case 4:
				str= "E";
				break;
			case 5:
				str= "F";
			    break;			
			case 6:
				str= "G";
				break;
			case 7:
				str= "H";
				break;
			case 8:
				str= "I";
				break;
			case 9:
				str= "J";
				break;
		}
		return str;
	}

}
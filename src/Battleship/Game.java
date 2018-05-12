
import java.util.*;

public class Game {
	
	public static int stringToInt(String str){
		int length=str.length();
		int value=0;
		int tmp=0;
		for (int i=1;i<length;i++) {
			tmp = str.charAt(i)- '0';
			value = tmp + value*10;
		}
		return value;
	}	
		
	public static String intToString(int value){
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
	
	public static void printShipsMap(Player p1, Player p2, int x, int y){
		String coord;
		System.out.println("Map with your ships (\"O\" ship, \"X\" ship hit, \"!\" enemy's shot attempt)");
		System.out.print("  |");
		for(int k=0;k<x;k++) {
			System.out.print(" "+intToString(k)+" ");
		}
		System.out.println();
		System.out.print("--|");
		for(int l=0;l<x;l++) {
			System.out.print("---");
		}
		System.out.println();
		for(int i=1;i<=x;i++) { //colonnes
			if (i<10) {
			System.out.print(" "+i+"|");
			}
			else {
			System.out.print(i+"|");

			}
			for(int j=0;j<y;j++) { //lignes
				coord=intToString(j)+i;
				if (p1.getCarrier().getShotReceived().contains(coord)||
				p1.getBattleship().getShotReceived().contains(coord)||
				p1.getCruiser().getShotReceived().contains(coord)||
				p1.getSubmarine().getShotReceived().contains(coord)||
				p1.getDestroyer().getShotReceived().contains(coord)) {
					System.out.print(" X "); //touché
				}
				else {
					if (p1.getCarrier().getCoordShip().contains(coord)||
					p1.getBattleship().getCoordShip().contains(coord)||
					p1.getCruiser().getCoordShip().contains(coord)||
					p1.getSubmarine().getCoordShip().contains(coord)||
					p1.getDestroyer().getCoordShip().contains(coord)) {
						System.out.print(" O "); //pas touché
					}
					else {
						if (p2.getShotFired().contains(coord)) {
							System.out.print(" ! "); //tir de l'adversaire
						}
						else {
							System.out.print(" - "); //rien
						}
					}	
				}
			}
		System.out.println();
		}
	}

	public static void printShotMap(Player p1, Player p2, int x, int y) {
		String coord;
		System.out.println("Map with your shots (\"!\" missed, \"X\" hit)");
		System.out.print("  |");
		for(int k=0;k<x;k++) {
			System.out.print(" "+intToString(k)+" ");
		}
		System.out.println();
		System.out.print("--|");
		for(int l=0;l<x;l++) {
			System.out.print("---");
		}
		System.out.println();
		for(int i=1;i<=x;i++) { //colonnes
			if (i<10) {
			System.out.print(" "+i+"|");
			}
			else {
			System.out.print(i+"|");

			}
			for(int j=0;j<y;j++) {
				coord=intToString(j)+i; //lignes
				if (p1.getShotFired().contains(coord)&&(p2.getCarrier().getCoordShip().contains(coord)||
						p2.getBattleship().getCoordShip().contains(coord)||
						p2.getCruiser().getCoordShip().contains(coord)||
						p2.getSubmarine().getCoordShip().contains(coord)||
						p2.getDestroyer().getCoordShip().contains(coord))) {
					System.out.print(" X "); //touché
				}
				else {
					if (p1.getShotFired().contains(coord)) {
						System.out.print(" ! "); //pas touché
					}
					else {
						System.out.print(" - "); //rien
						}
					}	
				}
			System.out.println();
			}
		}
	
			
 	public static ArrayList<String> checkSpacesArray(int startCoordLine, int startCoordColl, int endCoordLine, int endCoordColl, int size, ArrayList<String> spaces){
		spaces.add(intToString(startCoordColl)+startCoordLine);
		spaces.add(intToString(endCoordColl)+endCoordLine);

		switch(size){
    	case 5:
	    if (endCoordLine==startCoordLine) {
	    	if (endCoordColl>startCoordColl) {
	    		startCoordColl++;
	    		spaces.add(intToString(startCoordColl++)+startCoordLine);
	    		spaces.add(intToString(startCoordColl++)+startCoordLine);
	    		spaces.add(intToString(startCoordColl++)+startCoordLine);
	    	}
	    	else {
	    		startCoordColl--;
	    		spaces.add(intToString(startCoordColl--)+startCoordLine);
	    		spaces.add(intToString(startCoordColl--)+startCoordLine);
	    		spaces.add(intToString(startCoordColl--)+startCoordLine);
	    	}
	    }
	    else {
	    	if (endCoordLine>startCoordLine) {
	    		startCoordLine++;
	    		spaces.add(intToString(startCoordColl)+startCoordLine++);
	    		spaces.add(intToString(startCoordColl)+startCoordLine++);
	    		spaces.add(intToString(startCoordColl)+startCoordLine++);
	    	}
	    	else {
	    		startCoordLine--;
	    		spaces.add(intToString(startCoordColl)+startCoordLine--);
	    		spaces.add(intToString(startCoordColl)+startCoordLine--);
	    		spaces.add(intToString(startCoordColl)+startCoordLine--);
	    	}
	    }
	    break;
	    	case 4:
		    if (endCoordLine==startCoordLine) {
		    	if (endCoordColl>startCoordColl) {
		    		startCoordColl++;
		    		spaces.add(intToString(startCoordColl++)+startCoordLine);
		    		spaces.add(intToString(startCoordColl++)+startCoordLine);
		    	}
		    	else {
		    		startCoordColl--;
		    		spaces.add(intToString(startCoordColl--)+startCoordLine);
		    		spaces.add(intToString(startCoordColl--)+startCoordLine);
		    	}
		    }
		    else {
		    	if (endCoordLine>startCoordLine) {
		    		startCoordLine++;
		    		spaces.add(intToString(startCoordColl)+startCoordLine++);
		    		spaces.add(intToString(startCoordColl)+startCoordLine++);
		    	}
		    	else {
		    		startCoordLine--;
		    		spaces.add(intToString(startCoordColl)+startCoordLine--);
		    		spaces.add(intToString(startCoordColl)+startCoordLine--);
		    	}
		    }
		    break;
		case 3:
		    if (endCoordLine==startCoordLine) {
		    	if (endCoordColl>startCoordColl) {
		    		startCoordColl++;
		    		spaces.add(intToString(startCoordColl++)+startCoordLine);
		    	}
		    	else {
		    		startCoordColl--;
		    		spaces.add(intToString(startCoordColl--)+startCoordLine);
		    	}
		    }
		    else {
		    	if (endCoordLine>startCoordLine) {
		    		startCoordLine++;
		    		spaces.add(intToString(startCoordColl)+startCoordLine++);
		    	}
		    	else {
		    		startCoordLine--;
		    		spaces.add(intToString(startCoordColl)+startCoordLine--);
		    	}
		    }
		    break;
	    }
	    return spaces;
	}
 	
 	public static boolean endGame(Player p1, Player p2) {
 		return p1.shipsAllDestroyed()||p2.shipsAllDestroyed();
 	}
 	
	public static void affiche(ArrayList<String> liste){
		for(int i=0;i<liste.size();i++)
			System.out.println(liste.get(i));
	}
	
	public static boolean checkStartCoord(String coord, int size,ArrayList<String> spaceOccupied){
		/*verifie si il y a de la place autour et sur la case donnee pour un bateau de la taille donnee.*/
		boolean end=false;
		for (int n=0;n<spaceOccupied.size();n++){
			if (spaceOccupied.get(n).equals(coord)){
				end=true;
			}
		}
		if (end) {
			return false;
		}
		else {
			ArrayList<String> spaces1 = new ArrayList<String>();
			ArrayList<String> spaces2 = new ArrayList<String>();
			ArrayList<String> spaces3 = new ArrayList<String>();
			ArrayList<String> spaces4 = new ArrayList<String>();
	
			int coordColl=(int)coord.charAt(0)-65;
		    int coordLine = stringToInt(coord);
		    
		    int coordColl1=coordColl-(size-1);
		    int coordLine1=coordLine-(size-1);
		    int coordColl2=coordColl+(size-1);
		    int coordLine2=coordLine+(size-1);
		    boolean notFind1=false;
		    boolean notFind2=false;
		    boolean notFind3=false;
		    boolean notFind4=false;
	
		    if (coordColl1>=0) {
		    	spaces1=Game.checkSpacesArray(coordLine,coordColl,coordLine,coordColl1,size,spaces1);
		    	notFind1=true;
		    }
		    if (coordColl2<=9){
		    	spaces2=Game.checkSpacesArray(coordLine,coordColl,coordLine,coordColl2,size,spaces2);
		    	notFind2=true;

		    }
		    if (coordLine1>=1){
		    	spaces3=Game.checkSpacesArray(coordLine,coordColl,coordLine1,coordColl,size,spaces3);	
		    	notFind3=true;
		    }
		    if (coordLine2<=10){
		    	spaces4=Game.checkSpacesArray(coordLine,coordColl,coordLine2,coordColl,size,spaces4);	
		    	notFind4=true;
		    }
		    
		    
		    int i=0;
	
		    while(i<spaceOccupied.size()&&(notFind1||notFind2||notFind3||notFind4)){
		    	for (int j=0;j<spaces1.size();j++){
		    		if (spaceOccupied.get(i).equals(spaces1.get(j))) {
		    			notFind1=false;
		    		}
		    	}
		    	for (int k=0;k<spaces2.size();k++){
		    		if (spaceOccupied.get(i).equals(spaces2.get(k))) {
		    			notFind2=false;
		    		}
		    	}
		    	for (int l=0;l<spaces3.size();l++){
		    		if (spaceOccupied.get(i).equals(spaces3.get(l))) {
		    			notFind3=false;
		    		}
		    	}
		    	for (int m=0;m<spaces4.size();m++){
		    		if (spaceOccupied.get(i).equals(spaces4.get(m))) {
		    			notFind4=false;
		    		}
		    	}
		    	i=i+1;
		    }    
		    return (notFind1||notFind2||notFind3||notFind4);
		}
	}
	
	public static boolean checkEndCoord(String startCoord, int endCoordColl, int endCoordLine, int size, ArrayList<String> spaceOccupied){
		/*verifie si la derniere coordonnées est valide : si aucun bateau ne se trouve entre la premiere coord et la derniere coord*/
	    int startCoordColl=(int)startCoord.charAt(0)-65;
	    int startCoordLine = startCoord.charAt(1) - '0';
	    if (startCoord.length()==3) {
	    	startCoordLine=(startCoord.charAt(1)- '0')*10+startCoord.charAt(2)- '0';
	    }
	    ArrayList<String> spaces = new ArrayList<String>();
	    spaces = Game.checkSpacesArray(startCoordLine,startCoordColl,endCoordLine,endCoordColl,size,spaces);
	    int j=0;
	    int i=0;
	    boolean notFind=true;
	    while(i<spaceOccupied.size()&&(notFind)){
	    	for (j=0;j<spaces.size();j++){
	    		if (spaceOccupied.get(i).equals(spaces.get(j))) {
	    			notFind=false;
	    		}
	    	}
	    i++;
	    }
	    return notFind;
	}    
			    
	public static boolean checkInputStartCoord(ArrayList<String> spaceOccupied, String coord1, int size) {			
		boolean check=false;
		if (coord1.length()==3||coord1.length()==2)	{
			int coordColl=(int)coord1.charAt(0)-65;
			int coordLine = stringToInt(coord1);
		    if ((coordColl<=9&&coordColl>=0&&coordLine>=1&&coordLine<=10)&&coord1!=""&&checkStartCoord(coord1,size,spaceOccupied)) {
		    	check=true;
		    }
		}
	return check;
	}
	
	public static boolean checkInputCoordShot(String coord, Player player) {
		boolean check=true;
		if (coord.length()==3||coord.length()==2)	{
			int coordColl=(int)coord.charAt(0)-65;
			int coordLine = stringToInt(coord);
		    if ((coordColl<=9&&coordColl>=0&&coordLine>=1&&coordLine<=10)&&coord!="") {
		    	for(String str : player.getShotFired()) {
		    		if(str.equals(coord)) {
		    			check=false;
		    		}
		    	}
		    }
		    else {
		    	check=false;
		    }
		}
		else {
			check=false;
		}
	return check;
	}
	
	public static Boolean isCarrierHere(String coord, Player p) {
		Boolean isHere=false;
		for(String str : p.getCarrier().getCoordShip())
			if(str.equals(coord)) {
				isHere=true;
			}
		return isHere;
	}
	
	public static Boolean isBattleshipHere(String coord, Player p) {
		Boolean isHere=false;
		for(String str : p.getBattleship().getCoordShip())
			if(str.equals(coord)) {
				isHere=true;
			}
		return isHere;
	}
	
	public static Boolean isCruiserHere(String coord, Player p) {
		Boolean isHere=false;
		for(String str : p.getCruiser().getCoordShip())
			if(str.equals(coord)) {
				isHere=true;
			}
		return isHere;
	}
	
	public static Boolean isSubmarineHere(String coord, Player p) {
		Boolean isHere=false;
		for(String str : p.getSubmarine().getCoordShip())
			if(str.equals(coord)) {
				isHere=true;
			}
		return isHere;
	}
	
	public static Boolean isDestroyerHere(String coord, Player p) {
		Boolean isHere=false;
		for(String str : p.getDestroyer().getCoordShip())
			if(str.equals(coord)) {
				isHere=true;
			}
		return isHere;
	}
	
	public static ArrayList<String> GenerateEndCoord(ArrayList<String> spaceOccupied, String coord1, int size){	
		int coordColl=(int)coord1.charAt(0)-65;
		int coordLine = stringToInt(coord1);
		int coordColl1=coordColl-(size-1);
		int coordLine1=coordLine-(size-1);
		int coordColl2=coordColl+(size-1);
		int coordLine2=coordLine+(size-1);
		String str1="",str2="",str3="",str4="";
		ArrayList<String> result = new ArrayList<String>();

		if (coordColl1>=0&&checkEndCoord(coord1, coordColl1,coordLine,size,spaceOccupied)) {
			str1=Game.intToString(coordColl1)+coordLine;
			result.add(str1);
		}
		if (coordColl2<=9&&checkEndCoord(coord1, coordColl2,coordLine,size,spaceOccupied)) {
			str2=Game.intToString(coordColl2)+coordLine;
			result.add(str2);
		}
		if (coordLine1>=1&&checkEndCoord(coord1, coordColl,coordLine1,size,spaceOccupied)) {
			str3=Game.intToString(coordColl)+coordLine1;
			result.add(str3);
		}
		if (coordLine2<=10&&checkEndCoord(coord1, coordColl,coordLine2,size,spaceOccupied)) {
			str4=Game.intToString(coordColl)+coordLine2;				
			result.add(str4);
		}
		return result;
	}
}
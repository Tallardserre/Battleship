
package allardserre.thibaut;

import java.util.ArrayList;

public class Tools {

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
	 	
 	public static boolean endGame(Player p1, Player p2) {
 		return p1.shipsAllDestroyed()||p2.shipsAllDestroyed();
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
		    	spaces1=checkSpacesArray(coordLine,coordColl,coordLine,coordColl1,size,spaces1);
		    	notFind1=true;
		    }
		    if (coordColl2<=9){
		    	spaces2=checkSpacesArray(coordLine,coordColl,coordLine,coordColl2,size,spaces2);
		    	notFind2=true;

		    }
		    if (coordLine1>=1){
		    	spaces3=checkSpacesArray(coordLine,coordColl,coordLine1,coordColl,size,spaces3);	
		    	notFind3=true;
		    }
		    if (coordLine2<=10){
		    	spaces4=checkSpacesArray(coordLine,coordColl,coordLine2,coordColl,size,spaces4);	
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
		/*verifie si la derniere coordonnées est valide : si aucun bateau ne se trouve entre la premiere coordonnée et la derniere coordonnée*/
	    int startCoordColl=(int)startCoord.charAt(0)-65;
	    int startCoordLine = startCoord.charAt(1) - '0';
	    if (startCoord.length()==3) {
	    	startCoordLine=(startCoord.charAt(1)- '0')*10+startCoord.charAt(2)- '0';
	    }
	    ArrayList<String> spaces = new ArrayList<String>();
	    spaces = checkSpacesArray(startCoordLine,startCoordColl,endCoordLine,endCoordColl,size,spaces);
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
	
	public static boolean checkCoord(String coord) {
		Boolean check=false;
		if (coord.length()==3||coord.length()==2)	{
			int coordColl=(int)coord.charAt(0)-65;
			int coordLine = stringToInt(coord);
		    if ((coordColl<=9&&coordColl>=0&&coordLine>=1&&coordLine<=10)&&coord!="") {
		    	check=true;
		    }
		}	    
	return check;
	}
	
	public static boolean checkInputCoordShot(String coord, ArrayList<String> shotFired) {
		boolean check=true;
		if (checkCoord(coord))	{
			for(String str : shotFired) {
		    	if(str.equals(coord)) {
		    		check=false;
		    	}
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
	
	public static ArrayList<String> generateEndCoord(ArrayList<String> spaceOccupied, String coord1, int size){	
		int coordColl=(int)coord1.charAt(0)-65;
		int coordLine = stringToInt(coord1);
		int coordColl1=coordColl-(size-1);
		int coordLine1=coordLine-(size-1);
		int coordColl2=coordColl+(size-1);
		int coordLine2=coordLine+(size-1);
		String str1="",str2="",str3="",str4="";
		ArrayList<String> result = new ArrayList<String>();

		if (coordColl1>=0&&checkEndCoord(coord1, coordColl1,coordLine,size,spaceOccupied)) {
			str1=intToString(coordColl1)+coordLine;
			result.add(str1);
		}
		if (coordColl2<=9&&checkEndCoord(coord1, coordColl2,coordLine,size,spaceOccupied)) {
			str2=intToString(coordColl2)+coordLine;
			result.add(str2);
		}
		if (coordLine1>=1&&checkEndCoord(coord1, coordColl,coordLine1,size,spaceOccupied)) {
			str3=intToString(coordColl)+coordLine1;
			result.add(str3);
		}
		if (coordLine2<=10&&checkEndCoord(coord1, coordColl,coordLine2,size,spaceOccupied)) {
			str4=intToString(coordColl)+coordLine2;		
			result.add(str4);
		}
		return result;
	}
	
	public static boolean nearCoord(String coord1, String coord2) {
		//si les deux coordonnées sont collées ou pas
		int coord1Coll=(int)coord1.charAt(0)-65;
		int coord1Line = stringToInt(coord1);		
		int coord2Coll=(int)coord2.charAt(0)-65;
		int coord2Line = stringToInt(coord2);
		Boolean check=false;
		if(coord1Line==coord2Line) {
			if(coord1Coll==coord2Coll+1||coord1Coll+1==coord2Coll) {
				check=true;
			}
		}
		if(coord1Coll==coord2Coll) {
			if(coord1Line==coord2Line+1||coord1Line+1==coord2Line) {
				check=true;
			}
		}
		return check;
	}
	
	public static ArrayList<String> generateShotArray(ArrayList<String> shotFired, String coordShot){	
		int coordColl=(int)coordShot.charAt(0)-65;
		int coordLine = stringToInt(coordShot);
		int coordColl1=coordColl-1;
		int coordLine1=coordLine-1;
		int coordColl2=coordColl+1;
		int coordLine2=coordLine+1;
		ArrayList<String> result = new ArrayList<String>();
		String coord1=intToString(coordColl1)+coordLine;
		String coord2=intToString(coordColl2)+coordLine;
		String coord3=intToString(coordColl)+coordLine1;
		String coord4=intToString(coordColl)+coordLine2;	

		if (coordColl1>=0&&checkInputCoordShot(coord1,shotFired)) {
			result.add(coord1);
		}
		if (coordColl2<=9&&checkInputCoordShot(coord2,shotFired)) {
			result.add(coord2);
		}
		if (coordLine1>=1&&checkInputCoordShot(coord3,shotFired)) {
			result.add(coord3);
		}
		if (coordLine2<=10&&checkInputCoordShot(coord4,shotFired)) {
			result.add(coord4);
		}
		return result;
	}
	
	public static ArrayList<String> generateSmartShotArray(ArrayList<String> shotFired, String coordShot, String coordLastShotHit){
		//coordLastShotHit avant dernier hit
		//coordShot dernier hit
		//genere des coordonnees de tir en se basant sur les deux derniers tirs touchés (pour savoir si horizontal ou vertical)
		ArrayList<String> generatedShotArray = new ArrayList<String>();
		generatedShotArray=generateShotArray(shotFired,coordShot);
		ArrayList<String> generatedShotArray2 = new ArrayList<String>();

		
		int coordShotColl=(int)coordShot.charAt(0)-65;
		int coordShotLine=stringToInt(coordShot);
		int coordLastShotHitColl=(int)coordLastShotHit.charAt(0)-65;
		
		if (coordShotColl==coordLastShotHitColl) {		
			for(String str:generatedShotArray) {
				int generatedCoordColl=(int)str.charAt(0)-65;
				if(generatedCoordColl==coordShotColl) {
					generatedShotArray2.add(str);
				}
			}
		}
		else {
			for(String str:generatedShotArray) {
				int generatedCoordLine=stringToInt(str);
				if(generatedCoordLine==coordShotLine) {
					generatedShotArray2.add(str);
				}
			}
		}
	return generatedShotArray2;
	}
}

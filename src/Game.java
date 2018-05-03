
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
	
	public static void printmap(Player p, int x, int y){
		String coord;
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
				if (p.getCarrier().getCoordShip().contains(coord)||
					p.getBattleship().getCoordShip().contains(coord)||
					p.getCruiser().getCoordShip().contains(coord)||
					p.getSubmarine().getCoordShip().contains(coord)||
					p.getDestroyer().getCoordShip().contains(coord)) {
					System.out.print(" O "); //pas touché
					//System.out.print(" X "); touché

				}
				else {
					System.out.print(" - ");
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

	public static void affiche(ArrayList<String> liste){
		for(int i=0;i<liste.size();i++)
			System.out.println(liste.get(i));
	}
	
	public boolean checkStartCoord(String coord, int size,ArrayList<String> spaceOccupied){
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
	
	public boolean checkEndCoord(String startCoord, int endCoordColl, int endCoordLine, int size, ArrayList<String> spaceOccupied){
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
			    
	public void addSpace(String startCoord, String endCoord, int size, ArrayList<String> spaceOccupied){
		/*Ajoute les coordonnees du bateau cree au tableau spaceOccupied */
		spaceOccupied.add(startCoord);
	    int startCoordColl=(int)startCoord.charAt(0)-65;
	    int startCoordLine = stringToInt(startCoord);
	    int endCoordColl=(int)endCoord.charAt(0)-65;
	    int endCoordLine = stringToInt(endCoord);
	    switch(size){
	    case 5:
		    if (endCoordLine==startCoordLine) {
		    	if (endCoordColl>startCoordColl) {
		    		startCoordColl++;
		    		spaceOccupied.add(Game.intToString(startCoordColl++)+startCoordLine);
		    		spaceOccupied.add(Game.intToString(startCoordColl++)+startCoordLine);
		    		spaceOccupied.add(Game.intToString(startCoordColl++)+startCoordLine);
		    	}
		    	else {
		    		startCoordColl--;
		    		spaceOccupied.add(Game.intToString(startCoordColl--)+startCoordLine);
		    		spaceOccupied.add(Game.intToString(startCoordColl--)+startCoordLine);
		    		spaceOccupied.add(Game.intToString(startCoordColl--)+startCoordLine);
		    	}
		    }
		    else {
		    	if (endCoordLine>startCoordLine) {
		    		startCoordLine++;
		    		spaceOccupied.add(Game.intToString(startCoordColl)+startCoordLine++);
		    		spaceOccupied.add(Game.intToString(startCoordColl)+startCoordLine++);
		    		spaceOccupied.add(Game.intToString(startCoordColl)+startCoordLine++);
		    	}
		    	else {
		    		startCoordLine--;
		    		spaceOccupied.add(Game.intToString(startCoordColl)+startCoordLine--);
		    		spaceOccupied.add(Game.intToString(startCoordColl)+startCoordLine--);
		    		spaceOccupied.add(Game.intToString(startCoordColl)+startCoordLine--);
		    	}
		    }
		    break;
	    case 4:
		    if (endCoordLine==startCoordLine) {
		    	if (endCoordColl>startCoordColl) {
		    		startCoordColl++;
		    		spaceOccupied.add(Game.intToString(startCoordColl++)+startCoordLine);
		    		spaceOccupied.add(Game.intToString(startCoordColl++)+startCoordLine);
		    	}
		    	else {
		    		startCoordColl--;
		    		spaceOccupied.add(Game.intToString(startCoordColl--)+startCoordLine);
		    		spaceOccupied.add(Game.intToString(startCoordColl--)+startCoordLine);
		    	}
		    }
		    else {
		    	if (endCoordLine>startCoordLine) {
		    		startCoordLine++;
		    		spaceOccupied.add(Game.intToString(startCoordColl)+startCoordLine++);
		    		spaceOccupied.add(Game.intToString(startCoordColl)+startCoordLine++);
		    	}
		    	else {
		    		startCoordLine--;
		    		spaceOccupied.add(Game.intToString(startCoordColl)+startCoordLine--);
		    		spaceOccupied.add(Game.intToString(startCoordColl)+startCoordLine--);
		    	}
		    }
		    break;
		case 3:
		    if (endCoordLine==startCoordLine) {
		    	if (endCoordColl>startCoordColl) {
		    		startCoordColl++;
		    		spaceOccupied.add(Game.intToString(startCoordColl++)+startCoordLine);
		    	}
		    	else {
		    		startCoordColl--;
		    		spaceOccupied.add(Game.intToString(startCoordColl--)+startCoordLine);
		    	}
		    }
		    else {
		    	if (endCoordLine>startCoordLine) {
		    		startCoordLine++;
		    		spaceOccupied.add(Game.intToString(startCoordColl)+startCoordLine++);
		    	}
		    	else {
		    		startCoordLine--;
		    		spaceOccupied.add(Game.intToString(startCoordColl)+startCoordLine--);
		    	}
		    }
		    break;
	    }
		spaceOccupied.add(endCoord);
	}
	
	public void initializePlayer(Player player){
		Scanner sc1 = new Scanner(System.in);
		Scanner sc2 = new Scanner(System.in);
		ArrayList<String> spaceOccupied = new ArrayList<String>();

		//Carrier:

		String str1="",str2="",str3="",str4="";
		boolean correct=false;
		int coordColl=11;
		int coordLine=11;
		String coord1="";
		String coord2="";
		int coordColl1;
		int coordLine1;
		int coordColl2;
		int coordLine2;
			
		
			while (!correct) {
				System.out.println("Give the first coordinates of your Carrier (size : 5)");
				coord1 = sc1.nextLine();
				if (coord1.length()!=3&&coord1.length()!=2)	{
					System.out.println("Bad coordinates!");
				}
				else{
				    coordColl=(int)coord1.charAt(0)-65;
				    coordLine = stringToInt(coord1);
				    if ((coordColl<=9&&coordColl>=0&&coordLine>=1&&coordLine<=10)&&coord1!="") {
				    	correct=true;
				    }
				    else {
				    	System.out.println("Bad coordinates!");
				    }
				}
			}
			coordColl1=coordColl-4;
			coordLine1=coordLine-4;
			coordColl2=coordColl+4;
			coordLine2=coordLine+4;
			if (coordColl1>=0) {
				str1=Game.intToString(coordColl1)+coordLine;
			}
			if (coordColl2<=9) {
				str2=Game.intToString(coordColl2)+coordLine;
			}
			if (coordLine1>=1) {
				str3=Game.intToString(coordColl)+coordLine1;
			}
			if (coordLine2<=10) {
				str4=Game.intToString(coordColl)+coordLine2;
			}

		correct=false;
		while(!correct){
			System.out.println("Give the last coordinates of your Carrier (choose between : "+str1+" "+str2+" "+str3+" "+str4+")");
			coord2 = sc2.nextLine();
			if ((coord2.equals(str1)||coord2.equals(str2)||coord2.equals(str3)||coord2.equals(str4))&&coord2!=""){
				correct=true;
			}	
		}
		
		
		player.setCarrier(new Ship(coord1,coord2,5,"Carrier"));
		spaceOccupied=checkSpacesArray(stringToInt(coord1),(int)coord1.charAt(0)-65,stringToInt(coord2),(int)coord2.charAt(0)-65,5,spaceOccupied);
		
		//Battleship:

		str1="";
		str2="";
		str3="";
		str4="";
		coord1="";
		correct=false;
		while (!correct) {
			System.out.println("Give the first coordinates of your Battleship (size : 4)");
			coord1 = sc1.nextLine();
			if (coord1.length()!=3&&coord1.length()!=2)	{
				System.out.println("Bad coordinates!");
			}
			else{
			    coordColl=(int)coord1.charAt(0)-65;
			    coordLine = stringToInt(coord1);

			    if ((coordColl<=9&&coordColl>=0&&coordLine>=1&&coordLine<=10)&&coord1!=""&&checkStartCoord(coord1,4,spaceOccupied)) {
			    	correct=true;
			    }
			    else {
			    	if (!checkStartCoord(coord1,4,spaceOccupied)){
			    		System.out.println("Not enough space available for your ship here");
			    	}	
			    	else {
				    	System.out.println("Bad coordinates!");
				    }
			    }
			}
		}
		coordColl1=coordColl-3;
		coordLine1=coordLine-3;
		coordColl2=coordColl+3;
		coordLine2=coordLine+3;
		if (coordColl1>=0&&checkEndCoord(coord1, coordColl1,coordLine,4,spaceOccupied)) {
			str1=Game.intToString(coordColl1)+coordLine;
		}
		if (coordColl2<=9&&checkEndCoord(coord1, coordColl2,coordLine,4,spaceOccupied)) {
			str2=Game.intToString(coordColl2)+coordLine;
		}
		if (coordLine1>=1&&checkEndCoord(coord1, coordColl,coordLine1,4,spaceOccupied)) {
			str3=Game.intToString(coordColl)+coordLine1;
		}
		if (coordLine2<=10&&checkEndCoord(coord1, coordColl,coordLine2,4,spaceOccupied)) {
			str4=Game.intToString(coordColl)+coordLine2;
		}
			
		correct=false;
		coord2="";
		while(!correct){
			System.out.println("Give the last coordinates of your Battleship (choose between : "+str1+" "+str2+" "+str3+" "+str4+")");
			coord2 = sc2.nextLine();
			if ((coord2.equals(str1)||coord2.equals(str2)||coord2.equals(str3)||coord2.equals(str4))&&coord2!=""){
				correct=true;
			}	
		}
		
		player.setBattleship(new Ship(coord1,coord2,4,"Battleship"));
		spaceOccupied=checkSpacesArray(stringToInt(coord1),(int)coord1.charAt(0)-65,stringToInt(coord2),(int)coord2.charAt(0)-65,4,spaceOccupied);


		//Cruiser:

		str1="";
		str2="";
		str3="";
		str4="";
		coord1="";
		correct=false;
		while (!correct) {
			System.out.println("Give the first coordinates of your Cruiser (size : 3)");
			coord1 = sc1.nextLine();
			if (coord1.length()!=3&&coord1.length()!=2)	{
				System.out.println("Bad coordinates!");
			}
			else{
			    coordColl=(int)coord1.charAt(0)-65;
			    coordLine = stringToInt(coord1);
			    if ((coordColl<=9&&coordColl>=0&&coordLine>=1&&coordLine<=10)&&coord1!=""&&checkStartCoord(coord1,3,spaceOccupied)) {
			    	correct=true;
			    }
			    else {
			    	if (!checkStartCoord(coord1,3,spaceOccupied)){
			    		System.out.println("Not enough space available for your ship here");
			    	}
				    else {
				    	System.out.println("Bad coordinates!");
				    }
			    }
			}
		}
		coordColl1=coordColl-2;
		coordLine1=coordLine-2;
		coordColl2=coordColl+2;
		coordLine2=coordLine+2;
		if (coordColl1>=0&&checkEndCoord(coord1, coordColl1,coordLine,3,spaceOccupied)) {
			str1=Game.intToString(coordColl1)+coordLine;
		}
		if (coordColl2<=9&&checkEndCoord(coord1, coordColl2,coordLine,3,spaceOccupied)) {
			str2=Game.intToString(coordColl2)+coordLine;
		}
		if (coordLine1>=1&&checkEndCoord(coord1, coordColl,coordLine1,3,spaceOccupied)) {
			str3=Game.intToString(coordColl)+coordLine1;
		}
		if (coordLine2<=10&&checkEndCoord(coord1, coordColl,coordLine2,3,spaceOccupied)) {
			str4=Game.intToString(coordColl)+coordLine2;
		}
			
		correct=false;
		coord2="";
		while(!correct){
			System.out.println("Give the last coordinates of your Cruiser (choose between : "+str1+" "+str2+" "+str3+" "+str4+")");
			coord2 = sc2.nextLine();
			if ((coord2.equals(str1)||coord2.equals(str2)||coord2.equals(str3)||coord2.equals(str4))&&coord2!=""){
				correct=true;
			}	
		}
		
		player.setCruiser(new Ship(coord1,coord2,3,"Cruiser"));
		spaceOccupied=checkSpacesArray(stringToInt(coord1),(int)coord1.charAt(0)-65,stringToInt(coord2),(int)coord2.charAt(0)-65,3,spaceOccupied);
		
		//Submarine:

		str1="";
		str2="";
		str3="";
		str4="";
		coord1="";
		correct=false;
		while (!correct) {
			System.out.println("Give the first coordinates of your Submarine (size : 3)");
			coord1 = sc1.nextLine();
			if (coord1.length()!=3&&coord1.length()!=2)	{
				System.out.println("Bad coordinates!");
			}
			else{
			    coordColl=(int)coord1.charAt(0)-65;
			    coordLine = stringToInt(coord1);
			    if ((coordColl<=9&&coordColl>=0&&coordLine>=1&&coordLine<=10)&&coord1!=""&&checkStartCoord(coord1,3,spaceOccupied)) {
			    	correct=true;
			    }
			    else {
			    	if (!checkStartCoord(coord1,3,spaceOccupied)){
			        	System.out.println("Not enough space available for your ship here");
			    	}
				    else {
				    	System.out.println("Bad coordinates!");
				    }
			    }
			}
		}
		coordColl1=coordColl-2;
		coordLine1=coordLine-2;
		coordColl2=coordColl+2;
		coordLine2=coordLine+2;
		if (coordColl1>=0&&checkEndCoord(coord1, coordColl1,coordLine,3,spaceOccupied)) {
			str1=Game.intToString(coordColl1)+coordLine;
		}
		if (coordColl2<=9&&checkEndCoord(coord1, coordColl2,coordLine,3,spaceOccupied)) {
			str2=Game.intToString(coordColl2)+coordLine;
		}
		if (coordLine1>=1&&checkEndCoord(coord1, coordColl,coordLine1,3,spaceOccupied)) {
			str3=Game.intToString(coordColl)+coordLine1;
		}
		if (coordLine2<=10&&checkEndCoord(coord1, coordColl,coordLine2,3,spaceOccupied)) {
			str4=Game.intToString(coordColl)+coordLine2;
		}
			
		correct=false;
		coord2="";
		while(!correct){
			System.out.println("Give the last coordinates of your Submarine (choose between : "+str1+" "+str2+" "+str3+" "+str4+")");
			coord2 = sc2.nextLine();
			if ((coord2.equals(str1)||coord2.equals(str2)||coord2.equals(str3)||coord2.equals(str4))&&coord2!=""){
				correct=true;
			}	
		}
		
		player.setSubmarine(new Ship(coord1,coord2,3,"Submarine"));
		spaceOccupied=checkSpacesArray(stringToInt(coord1),(int)coord1.charAt(0)-65,stringToInt(coord2),(int)coord2.charAt(0)-65,3,spaceOccupied);
				
		//Destroyer:

		str1="";
		str2="";
		str3="";
		str4="";
		coord1="";
		correct=false;
		while (!correct) {
			System.out.println("Give the first coordinates of your Destroyer (size : 2)");
			coord1 = sc1.nextLine();
			if (coord1.length()!=3&&coord1.length()!=2)	{
				System.out.println("Bad coordinates!");
			}
			else{
				coordColl=(int)coord1.charAt(0)-65;
			    coordLine = stringToInt(coord1);
			    if ((coordColl<=9&&coordColl>=0&&coordLine>=1&&coordLine<=10)&&coord1!=""&&checkStartCoord(coord1,2,spaceOccupied)) {
			    	correct=true;
			    }
			    else {
			    	if (!checkStartCoord(coord1,2,spaceOccupied)){
			    		System.out.println("Not enough space available for your ship here");
			    	}
				    else {
				    	System.out.println("Bad coordinates!");
				    }
			    }
			}
		}
		coordColl1=coordColl-1;
		coordLine1=coordLine-1;
		coordColl2=coordColl+1;
		coordLine2=coordLine+1;
		if (coordColl1>=0&&checkEndCoord(coord1, coordColl1,coordLine,2,spaceOccupied)) {
			str1=Game.intToString(coordColl1)+coordLine;
		}
		if (coordColl2<=9&&checkEndCoord(coord1, coordColl2,coordLine,2,spaceOccupied)) {
			str2=Game.intToString(coordColl2)+coordLine;
		}
		if (coordLine1>=1&&checkEndCoord(coord1, coordColl,coordLine1,2,spaceOccupied)) {
			str3=Game.intToString(coordColl)+coordLine1;
		}
		if (coordLine2<=10&&checkEndCoord(coord1, coordColl,coordLine2,2,spaceOccupied)) {
			str4=Game.intToString(coordColl)+coordLine2;
		}
			
		correct=false;
		coord2="";
		while(!correct){
			System.out.println("Give the last coordinates of your Destroyer (choose between : "+str1+" "+str2+" "+str3+" "+str4+")");
			coord2 = sc2.nextLine();
			if ((coord2.equals(str1)||coord2.equals(str2)||coord2.equals(str3)||coord2.equals(str4))&&coord2!=""){
				correct=true;
			}	
		}
		
		player.setDestroyer(new Ship(coord1,coord2,2,"Destroyer"));
		spaceOccupied=checkSpacesArray(stringToInt(coord1),(int)coord1.charAt(0)-65,stringToInt(coord2),(int)coord2.charAt(0)-65,2,spaceOccupied);
		System.out.println("All you ships are initialised!");
		printmap(player,10,10);

		
		sc1.close();
		sc2.close();
	}

	public void shotAttempt(Player p1, Player p2){
		System.out.println(p1.getName()+" enter a coordinates to shot :");
		
	}
}
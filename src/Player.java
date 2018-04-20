
import java.util.ArrayList;
import java.util.Scanner;

public class Player {
	private String name;
	private Ship Carrier;
	private Ship Battleship;
	private Ship Cruiser;
	private Ship Submarine;
	private Ship Destroyer;
	private ArrayList<String> spaceOccupied=new ArrayList<String>();
	
	public Player(String name){
		setName(name);
	}


	public Ship getCarrier() {
		return Carrier;
	}

	public void setCarrier(Ship carrier) {
		Carrier = carrier;
	}

	public Ship getBattleship() {
		return Battleship;
	}

	public void setBattleship(Ship battleship) {
		Battleship = battleship;
	}

	public Ship getCruiser() {
		return Cruiser;
	}

	public void setCruiser(Ship cruiser) {
		Cruiser = cruiser;
	}

	public Ship getSubmarine() {
		return Submarine;
	}

	public void setSubmarine(Ship submarine) {
		Submarine = submarine;
	}

	public Ship getDestroyer() {
		return Destroyer;
	}

	public void setDestroyer(Ship destroyer) {
		Destroyer = destroyer;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<String> getSpaceOccupied() {
		return spaceOccupied;
	}

	public void setSpaceOccupied(ArrayList<String> spaceOccupied) {
		this.spaceOccupied = spaceOccupied;
	}
	
	public boolean checkStartCoord(String coord, int size){//ERREUR
		boolean end=false;
		for (int n=0;n<this.spaceOccupied.size();n++){
			if (this.spaceOccupied.get(n).equals(coord)){
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
		    int coordLine = coord.charAt(1) - '0';
		    if (coord.length()==3) {
		    	coordLine=(coord.charAt(1)- '0')*10+coord.charAt(2)- '0';
		    }
		    
		    int coordColl1=coordColl-(size-1);
		    int coordLine1=coordLine-(size-1);
		    int coordColl2=coordColl+(size-1);
		    int coordLine2=coordLine+(size-1);
	
		    if (coordColl1>=0) {
		    	spaces1=checkSpacesArray(coordLine,coordColl,coordLine,coordColl1,size,spaces1);
		    	System.out.println("coordline:"+coordLine+"coordcoll:"+coordColl+"coordline:"+coordLine+"coordcoll:"+coordColl1+"");
		    }
		    if (coordColl2<=9){
		    	spaces2=checkSpacesArray(coordLine,coordColl,coordLine,coordColl2,size,spaces2);
		    	System.out.println("coordline:"+coordLine+"coordcoll:"+coordColl+"coordline:"+coordLine+"coordcoll:"+coordColl2+"");
	
		    }
		    if (coordLine1>=1){
		    	spaces3=checkSpacesArray(coordLine,coordColl,coordLine1,coordColl,size,spaces3);
		    	System.out.println("coordline:"+coordLine+"coordcoll:"+coordColl+"coordline:"+coordLine1+"coordcoll:"+coordColl+"");
	
		    }
		    if (coordLine2<=10){
		    	spaces4=checkSpacesArray(coordLine,coordColl,coordLine2,coordColl,size,spaces4);
		    	System.out.println("coordline:"+coordLine+"coordcoll:"+coordColl+"coordline:"+coordLine2+"coordcoll:"+coordColl+"");
	
		    }
		    boolean notFind1=true;
		    boolean notFind2=true;
		    boolean notFind3=true;
		    boolean notFind4=true;
		    int i=0;
		    int j=0;
		    int l=0;
		    int m=0;
		    int k=0;
	
		    while(i<this.spaceOccupied.size()&&(notFind1||notFind2||notFind3||notFind4)){
		    	for (j=0;j<spaces1.size();j++){
		    		if (this.spaceOccupied.get(i).equals(spaces1.get(j))) {
		    			notFind1=false;
		    		}
		    	}
		    	for (k=0;k<spaces2.size();k++){
		    		if (this.spaceOccupied.get(i).equals(spaces2.get(k))) {
		    			notFind2=false;
		    		}
		    	}
		    	for (l=0;l<spaces3.size();l++){
		    		if (this.spaceOccupied.get(i).equals(spaces3.get(l))) {
		    			notFind3=false;
		    		}
		    	}
		    	for (m=0;m<spaces4.size();m++){
		    		if (this.spaceOccupied.get(i).equals(spaces4.get(m))) {
		    			notFind4=false;
		    		}
		    	}
		    	i++;
		    }
		    return (notFind1||notFind2||notFind3||notFind4);
		}
	}
	
	public ArrayList<String> checkSpacesArray(int startCoordLine, int startCoordColl, int endCoordLine, int endCoordColl, int size, ArrayList<String> spaces){
		spaces.add(Game.IntToLetter(startCoordColl)+startCoordLine);//ERREUR
		spaces.add(Game.IntToLetter(endCoordColl)+endCoordLine);

		switch(size){
	    	case 4:
		    if (endCoordLine==startCoordLine) {
		    	if (endCoordColl>startCoordColl) {
		    		startCoordColl++;
		    		spaces.add(Game.IntToLetter(startCoordColl++)+startCoordLine);
		    		spaces.add(Game.IntToLetter(startCoordColl++)+startCoordLine);
		    	}
		    	else {
		    		startCoordColl--;
		    		spaces.add(Game.IntToLetter(startCoordColl--)+startCoordLine);
		    		spaces.add(Game.IntToLetter(startCoordColl--)+startCoordLine);
		    	}
		    }
		    else {
		    	if (endCoordLine>startCoordLine) {
		    		startCoordLine++;
		    		spaces.add(Game.IntToLetter(startCoordColl)+startCoordLine++);
		    		spaces.add(Game.IntToLetter(startCoordColl)+startCoordLine++);
		    	}
		    	else {
		    		startCoordLine--;
		    		spaces.add(Game.IntToLetter(startCoordColl)+startCoordLine--);
		    		spaces.add(Game.IntToLetter(startCoordColl)+startCoordLine--);
		    	}
		    }
		    break;
		case 3:
		    if (endCoordLine==startCoordLine) {
		    	if (endCoordColl>startCoordColl) {
		    		startCoordColl++;
		    		spaces.add(Game.IntToLetter(startCoordColl++)+startCoordLine);
		    	}
		    	else {
		    		startCoordColl--;
		    		spaces.add(Game.IntToLetter(startCoordColl--)+startCoordLine);
		    	}
		    }
		    else {
		    	if (endCoordLine>startCoordLine) {
		    		startCoordLine++;
		    		spaces.add(Game.IntToLetter(startCoordColl)+startCoordLine++);
		    	}
		    	else {
		    		startCoordLine--;
		    		spaces.add(Game.IntToLetter(startCoordColl)+startCoordLine--);
		    	}
		    }
		    break;
	    }
	    affiche(spaces);
	    return spaces;
	}
	
	public boolean checkEndCoord(String startCoord, int endCoordColl, int endCoordLine, int size){
	    int startCoordColl=(int)startCoord.charAt(0)-65;
	    int startCoordLine = startCoord.charAt(1) - '0';
	    ArrayList<String> spaces = new ArrayList<String>();
	    switch(size){
    	case 4:
	    if (endCoordLine==startCoordLine) {
	    	if (endCoordColl>startCoordColl) {
	    		startCoordColl++;
	    		spaces.add(Game.IntToLetter(startCoordColl++)+startCoordLine);
	    		spaces.add(Game.IntToLetter(startCoordColl++)+startCoordLine);
	    	}
	    	else {
	    		startCoordColl--;
	    		spaces.add(Game.IntToLetter(startCoordColl--)+startCoordLine);
	    		spaces.add(Game.IntToLetter(startCoordColl--)+startCoordLine);
	    	}
	    }
	    else {
	    	if (endCoordLine>startCoordLine) {
	    		startCoordLine++;
	    		spaces.add(Game.IntToLetter(startCoordColl)+startCoordLine++);
	    		spaces.add(Game.IntToLetter(startCoordColl)+startCoordLine++);
	    	}
	    	else {
	    		startCoordLine--;
	    		spaces.add(Game.IntToLetter(startCoordColl)+startCoordLine--);
	    		spaces.add(Game.IntToLetter(startCoordColl)+startCoordLine--);
	    	}
	    }
	    break;
		case 3:
		    if (endCoordLine==startCoordLine) {
		    	if (endCoordColl>startCoordColl) {
		    		startCoordColl++;
		    		spaces.add(Game.IntToLetter(startCoordColl++)+startCoordLine);
		    	}
		    	else {
		    		startCoordColl--;
		    		spaces.add(Game.IntToLetter(startCoordColl--)+startCoordLine);
		    	}
		    }
		    else {
		    	if (endCoordLine>startCoordLine) {
		    		startCoordLine++;
		    		spaces.add(Game.IntToLetter(startCoordColl)+startCoordLine++);
		    	}
		    	else {
		    		startCoordLine--;
		    		spaces.add(Game.IntToLetter(startCoordColl)+startCoordLine--);
		    	}
		    }
		    break;
	    }
	    int j=0;
	    int i=0;
	    boolean notFind=true;
	    while(i<this.spaceOccupied.size()&&(notFind)){
	    	for (j=0;j<spaces.size();j++){
	    		if (this.spaceOccupied.get(i).equals(spaces.get(j))) {
	    			notFind=false;
	    		}
	    	}
	    i++;
	    }
	    return notFind;
	}    
	    
	public void addSpace(String startCoord, String endCoord, int size){
		this.spaceOccupied.add(startCoord);
		this.spaceOccupied.add(endCoord);
	    int startCoordColl=(int)startCoord.charAt(0)-65;
	    int startCoordLine = startCoord.charAt(1) - '0';
	    if (startCoord.length()==3) {
	    	startCoordLine=(startCoord.charAt(1)- '0')*10+startCoord.charAt(2)- '0';
	    }
	    int endCoordColl=(int)endCoord.charAt(0)-65;
	    int endCoordLine = endCoord.charAt(1) - '0';
	    if (endCoord.length()==3) {
	    	endCoordLine=(endCoord.charAt(1)- '0')*10+endCoord.charAt(2)- '0';
	    }
	    switch(size){
	    case 5:
		    if (endCoordLine==startCoordLine) {
		    	if (endCoordColl>startCoordColl) {
		    		startCoordColl++;
		    		this.spaceOccupied.add(Game.IntToLetter(startCoordColl++)+startCoordLine);
		    		this.spaceOccupied.add(Game.IntToLetter(startCoordColl++)+startCoordLine);
		    		this.spaceOccupied.add(Game.IntToLetter(startCoordColl++)+startCoordLine);
		    	}
		    	else {
		    		startCoordColl--;
		    		this.spaceOccupied.add(Game.IntToLetter(startCoordColl--)+startCoordLine);
		    		this.spaceOccupied.add(Game.IntToLetter(startCoordColl--)+startCoordLine);
		    		this.spaceOccupied.add(Game.IntToLetter(startCoordColl--)+startCoordLine);
		    	}
		    }
		    else {
		    	if (endCoordLine>startCoordLine) {
		    		startCoordLine++;
		    		this.spaceOccupied.add(Game.IntToLetter(startCoordColl)+startCoordLine++);
		    		this.spaceOccupied.add(Game.IntToLetter(startCoordColl)+startCoordLine++);
		    		this.spaceOccupied.add(Game.IntToLetter(startCoordColl)+startCoordLine++);
		    	}
		    	else {
		    		startCoordLine--;
		    		this.spaceOccupied.add(Game.IntToLetter(startCoordColl)+startCoordLine--);
		    		this.spaceOccupied.add(Game.IntToLetter(startCoordColl)+startCoordLine--);
		    		this.spaceOccupied.add(Game.IntToLetter(startCoordColl)+startCoordLine--);
		    	}
		    }
		    break;
	    case 4:
		    if (endCoordLine==startCoordLine) {
		    	if (endCoordColl>startCoordColl) {
		    		startCoordColl++;
		    		this.spaceOccupied.add(Game.IntToLetter(startCoordColl++)+startCoordLine);
		    		this.spaceOccupied.add(Game.IntToLetter(startCoordColl++)+startCoordLine);
		    	}
		    	else {
		    		startCoordColl--;
		    		this.spaceOccupied.add(Game.IntToLetter(startCoordColl--)+startCoordLine);
		    		this.spaceOccupied.add(Game.IntToLetter(startCoordColl--)+startCoordLine);
		    	}
		    }
		    else {
		    	if (endCoordLine>startCoordLine) {
		    		startCoordLine++;
		    		this.spaceOccupied.add(Game.IntToLetter(startCoordColl)+startCoordLine++);
		    		this.spaceOccupied.add(Game.IntToLetter(startCoordColl)+startCoordLine++);
		    	}
		    	else {
		    		startCoordLine--;
		    		this.spaceOccupied.add(Game.IntToLetter(startCoordColl)+startCoordLine--);
		    		this.spaceOccupied.add(Game.IntToLetter(startCoordColl)+startCoordLine--);
		    	}
		    }
		    break;
		case 3:
		    if (endCoordLine==startCoordLine) {
		    	if (endCoordColl>startCoordColl) {
		    		startCoordColl++;
		    		this.spaceOccupied.add(Game.IntToLetter(startCoordColl++)+startCoordLine);
		    	}
		    	else {
		    		startCoordColl--;
		    		this.spaceOccupied.add(Game.IntToLetter(startCoordColl--)+startCoordLine);
		    	}
		    }
		    else {
		    	if (endCoordLine>startCoordLine) {
		    		startCoordLine++;
		    		this.spaceOccupied.add(Game.IntToLetter(startCoordColl)+startCoordLine++);
		    	}
		    	else {
		    		startCoordLine--;
		    		this.spaceOccupied.add(Game.IntToLetter(startCoordColl)+startCoordLine--);
		    	}
		    }
		    break;
	    }
	}
	
	public void affiche(ArrayList<String> liste){
		for(int i=0;i<liste.size();i++)
			System.out.println(liste.get(i));
	}
	
	public void initializePlayer(){
		Scanner sc1 = new Scanner(System.in);
		Scanner sc2 = new Scanner(System.in);
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
				    coordLine = coord1.charAt(1) - '0';
				    if (coord1.length()==3) {
				    	coordLine=(coord1.charAt(1)- '0')*10+coord1.charAt(2)- '0';
				    }
				    if ((coordColl<=9&&coordColl>=0&&coordLine>=1&&coordLine<=10)&&coord1!="") {
				    	correct=true;
				    }
				}
			}
			coordColl1=coordColl-4;
			coordLine1=coordLine-4;
			coordColl2=coordColl+4;
			coordLine2=coordLine+4;
			if (coordColl1>=0) {
				str1=Game.IntToLetter(coordColl1)+coordLine;
			}
			if (coordColl2<=9) {
				str2=Game.IntToLetter(coordColl2)+coordLine;
			}
			if (coordLine1>=1) {
				str3=Game.IntToLetter(coordColl)+coordLine1;
			}
			if (coordLine2<=10) {
				str4=Game.IntToLetter(coordColl)+coordLine2;
			}

		correct=false;
		while(!correct){
			System.out.println("Give the last coordinates of your Carrier (choose between : "+str1+" "+str2+" "+str3+" "+str4+")");
			coord2 = sc2.nextLine();
			if ((coord2.equals(str1)||coord2.equals(str2)||coord2.equals(str3)||coord2.equals(str4))&&coord2!=""){
				correct=true;
			}	
		}
		
		
		this.Carrier= new Ship(coord1,coord2);
		addSpace(coord1,coord2,5);
		affiche(this.spaceOccupied);
		

		
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
			    coordLine = coord1.charAt(1) - '0';
			    if (coord1.length()==3) {
			    	coordLine=(coord1.charAt(1)- '0')*10+coord1.charAt(2)- '0';
			    }
			    if ((coordColl<=9&&coordColl>=0&&coordLine>=1&&coordLine<=10)&&coord1!=""&&checkStartCoord(coord1,4)) {
			    	correct=true;
			    }
			    else if (!checkStartCoord(coord1,4)){
			    	System.out.println("Not enough space available for your ship here");
			    }
			}
		}
		coordColl1=coordColl-3;
		coordLine1=coordLine-3;
		coordColl2=coordColl+3;
		coordLine2=coordLine+3;
		if (coordColl1>=0&&checkEndCoord(coord1, coordColl1,coordLine,4)) {
			str1=Game.IntToLetter(coordColl1)+coordLine;
		}
		if (coordColl2<=9&&checkEndCoord(coord1, coordColl2,coordLine,4)) {
			str2=Game.IntToLetter(coordColl2)+coordLine;
		}
		if (coordLine1>=1&&checkEndCoord(coord1, coordColl,coordLine1,4)) {
			str3=Game.IntToLetter(coordColl)+coordLine1;
		}
		if (coordLine2<=10&&checkEndCoord(coord1, coordColl,coordLine2,4)) {
			str4=Game.IntToLetter(coordColl)+coordLine2;
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
		
		this.Battleship= new Ship(coord1,coord2);
		addSpace(coord1,coord2,4);
		affiche(this.spaceOccupied);


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
			    coordLine = coord1.charAt(1) - '0';
			    if (coord1.length()==3) {
			    	coordLine=(coord1.charAt(1)- '0')*10+coord1.charAt(2)- '0';
			    }
			    if ((coordColl<=9&&coordColl>=0&&coordLine>=1&&coordLine<=10)&&coord1!=""&&checkStartCoord(coord1,3)) {
			    	correct=true;
			    }
			    else if (!checkStartCoord(coord1,3)){
			    	System.out.println("Not enough space available for your ship here");
			    }
			}
		}
		coordColl1=coordColl-3;
		coordLine1=coordLine-2;
		coordColl2=coordColl+2;
		coordLine2=coordLine+2;
		if (coordColl1>=0&&checkEndCoord(coord1, coordColl1,coordLine,3)) {
			str1=Game.IntToLetter(coordColl1)+coordLine;
		}
		if (coordColl2<=9&&checkEndCoord(coord1, coordColl2,coordLine,3)) {
			str2=Game.IntToLetter(coordColl2)+coordLine;
		}
		if (coordLine1>=1&&checkEndCoord(coord1, coordColl,coordLine1,3)) {
			str3=Game.IntToLetter(coordColl)+coordLine1;
		}
		if (coordLine2<=10&&checkEndCoord(coord1, coordColl,coordLine2,3)) {
			str4=Game.IntToLetter(coordColl)+coordLine2;
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
		
		this.Cruiser= new Ship(coord1,coord2);
		addSpace(coord1,coord2,3);
		affiche(this.spaceOccupied);

		
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
			    coordLine = coord1.charAt(1) - '0';
			    if (coord1.length()==3) {
			    	coordLine=(coord1.charAt(1)- '0')*10+coord1.charAt(2)- '0';
			    }
			    if ((coordColl<=9&&coordColl>=0&&coordLine>=1&&coordLine<=10)&&coord1!=""&&checkStartCoord(coord1,3)) {
			    	correct=true;
			    }
			    else if (!checkStartCoord(coord1,3)){
			    	System.out.println("Not enough space available for your ship here");
			    }
			}
		}
		coordColl1=coordColl-2;
		coordLine1=coordLine-2;
		coordColl2=coordColl+2;
		coordLine2=coordLine+2;
		if (coordColl1>=0&&checkEndCoord(coord1, coordColl1,coordLine,3)) {
			str1=Game.IntToLetter(coordColl1)+coordLine;
		}
		if (coordColl2<=9&&checkEndCoord(coord1, coordColl2,coordLine,3)) {
			str2=Game.IntToLetter(coordColl2)+coordLine;
		}
		if (coordLine1>=1&&checkEndCoord(coord1, coordColl,coordLine1,3)) {
			str3=Game.IntToLetter(coordColl)+coordLine1;
		}
		if (coordLine2<=10&&checkEndCoord(coord1, coordColl,coordLine2,3)) {
			str4=Game.IntToLetter(coordColl)+coordLine2;
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
		
		this.Submarine= new Ship(coord1,coord2);
		addSpace(coord1,coord2,3);
		affiche(this.spaceOccupied);

				
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
			    coordLine = coord1.charAt(1) - '0';
			    if (coord1.length()==3) {
			    	coordLine=(coord1.charAt(1)- '0')*10+coord1.charAt(2)- '0';
			    }
			    if ((coordColl<=9&&coordColl>=0&&coordLine>=1&&coordLine<=10)&&coord1!=""&&checkStartCoord(coord1,2)) {
			    	correct=true;
			    }
			    else if (!checkStartCoord(coord1,2)){
			    	System.out.println("Not enough space available for your ship here");
			    }
			}
		}
		coordColl1=coordColl-1;
		coordLine1=coordLine-1;
		coordColl2=coordColl+1;
		coordLine2=coordLine+1;
		if (coordColl1>=0&&checkEndCoord(coord1, coordColl1,coordLine,2)) {
			str1=Game.IntToLetter(coordColl1)+coordLine;
		}
		if (coordColl2<=9&&checkEndCoord(coord1, coordColl2,coordLine,2)) {
			str2=Game.IntToLetter(coordColl2)+coordLine;
		}
		if (coordLine1>=1&&checkEndCoord(coord1, coordColl,coordLine1,2)) {
			str3=Game.IntToLetter(coordColl)+coordLine1;
		}
		if (coordLine2<=10&&checkEndCoord(coord1, coordColl,coordLine2,2)) {
			str4=Game.IntToLetter(coordColl)+coordLine2;
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
		
		this.Destroyer= new Ship(coord1,coord2);
		addSpace(coord1,coord2,2);
		affiche(this.spaceOccupied);

		
		sc1.close();
		sc2.close();
	}

}
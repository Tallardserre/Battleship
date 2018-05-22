
package allardserre.thibaut;

import java.util.ArrayList;
import java.util.Scanner;

public class Battleship {
	
	static Scanner sc1 = new Scanner(System.in);
	static Scanner sc2 = new Scanner(System.in);

	public static void main(String[] args) {
		Player p1 = new Player();
		Player p2 = new Player();
		String name="";
		int tour=0;
		//Selection du mode de jeu
		System.out.println("Choose one game mode");
		System.out.println("1 for : Player vs Player");
		System.out.println("2 for : Player vs AI");
		String gameMode="";
		int gameModeNumber=0;
		int levelAINumber=0;
		String levelAI="";
		int stop=0;
		while(stop==0) {
			gameMode= sc1.nextLine();
			if (!gameMode.equals("")&&gameMode.length()<2) {
				gameModeNumber=gameMode.charAt(0)-'0';
				if (gameModeNumber<1||gameModeNumber>2) {
					System.out.println("Invalid!");
				}
				else {
					stop=1;
				}
			}
			else {
				System.out.println("Invalid!");
			}
		}	
		switch(gameModeNumber) {
		case 1:
			//Joueur contre joueur
			System.out.println("Player 1, enter your name:");
			name = sc1.nextLine();
			p1.setName(name);
			System.out.println("Player 2, enter your name:");
			name = sc1.nextLine();
			p2.setName(name);
			break;
		
		case 2:
			//Joueur contre AI
			System.out.println("Enter your name:");
			name = sc1.nextLine();
			p1.setName(name);
			System.out.println();
			System.out.println("Select the level of your opponent");
			System.out.println("1 - Beginner");
			System.out.println("2 - Medium");
			System.out.println("3 - Hard");
			stop=0;
			while(stop==0) {
				levelAI= sc1.nextLine();
				if (!levelAI.equals("")&&levelAI.length()<2) {
					levelAINumber=levelAI.charAt(0)-'0';
					if (levelAINumber<1||levelAINumber>3) {
						System.out.println("Invalid!");
					}
					else {
						stop=1;
					}
				}
			}
			break;	
		}
		Boolean running=true;
		String replay="";
		int countingGame=1;
		//*************************GAME START *******************************
		while(running) {
			if(gameModeNumber==2) {
				p2=new AI("Opponent",levelAINumber);
				p1=setupPlayer(p1);
			}
			else {
				p1=setupPlayer(p1);
				p2=setupPlayer(p2);
			}
			System.out.println("Game start !");
			tour=1;
			while(!Tools.endGame(p1,p2)){
				//JOUEUR qui joue.
				System.out.println();
				System.out.println("*********************** Round "+tour+" ***********************");
				System.out.println();
				if(countingGame%2==0) {
					round(p1,p2,gameModeNumber);
					if(!Tools.endGame(p1,p2)) {
						round(p2,p1,gameModeNumber);
					}
				}
				else {
					round(p2,p1,gameModeNumber);
					if(!Tools.endGame(p1,p2)) {
						round(p1,p2,gameModeNumber);
					}
				}
				tour++;
			}
			if (p1.shipsAllDestroyed()) {
				printShotMap(p2,p1,10,10);
				System.out.println(p2.getName()+" won! in "+tour+" rounds.");
			}
			else {
				printShotMap(p1,p2,10,10);
				System.out.println(p1.getName()+" won! in "+tour+" rounds.");
			}
			System.out.println("Play again? Y/n");
			replay=sc1.nextLine();
			stop=0;
			while(stop==0) {
				if(replay.equals("n")||replay.equals("N")) {
					running=false;
					stop=1;
				}
				else if (replay.equals("y")||replay.equals("Y")){
					countingGame++;
					stop=1;
				}
			}
		}//*************************** END GAME *****************************
		sc1.close();	
		sc2.close();
	}
	
	public static void round(Player p1, Player p2, int gameModeNumber) {	
		String coordShot="";
		String hitShip="";
		Boolean isDestroyed=false;
		int stop=0;
		System.out.println(p1.getName()+" is playing!");
		if (p1 instanceof AI){
			if(gameModeNumber==2) {
				coordShot=((AI)p1).generateShotCoord();
			}
		}
		else {
			printShipsMap(p1,p2,10,10);
			printShotMap(p1,p2,10,10);
			while(stop==0) {
				System.out.println(p1.getName()+" enter where you want to shoot:");
				coordShot = sc2.nextLine();
				if (Tools.checkInputCoordShot(coordShot,p1.getShotFired())) {
					stop=1;
				}
				else {
					System.out.println("Bad coordinate!");
				}		
			}
		}
		System.out.println(p1.getName()+" shooted at "+coordShot);
		if (p2.shipHit(coordShot)) {
			if(Tools.isCarrierHere(coordShot,p2)) {
				p2.getCarrier().getShotReceived().add(coordShot);
				hitShip=p2.getCarrier().getShipCategory();
				isDestroyed=p2.getCarrier().isDestroyed();
			}
			if(Tools.isBattleshipHere(coordShot,p2)) {
				p2.getBattleship().getShotReceived().add(coordShot);
				hitShip=p2.getBattleship().getShipCategory();
				isDestroyed=p2.getBattleship().isDestroyed();
	
			}
			if(Tools.isCruiserHere(coordShot,p2)) {
				p2.getCruiser().getShotReceived().add(coordShot);
				hitShip=p2.getCruiser().getShipCategory();
				isDestroyed=p2.getCruiser().isDestroyed();
			}
			if(Tools.isSubmarineHere(coordShot,p2)) {
				p2.getSubmarine().getShotReceived().add(coordShot);
				hitShip=p2.getSubmarine().getShipCategory();
				isDestroyed=p2.getSubmarine().isDestroyed();
			}
			if(Tools.isDestroyerHere(coordShot,p2)) {
				p2.getDestroyer().getShotReceived().add(coordShot);
				hitShip=p2.getDestroyer().getShipCategory();
				isDestroyed=p2.getDestroyer().isDestroyed();
			}
			if(p1 instanceof AI) {
				ArrayList<String> shotArray = new ArrayList<String>();
				shotArray.add(coordShot);
				shotArray.add(Boolean.toString(isDestroyed));
				((AI) p1).getShotArray().add(shotArray);			
				((AI) p1).setLastHit(coordShot);
			}
			if(p2 instanceof AI) {
				ArrayList<String> shotArray = new ArrayList<String>();
				shotArray.add(coordShot);
				shotArray.add(Boolean.toString(isDestroyed));
				((AI) p2).getShotArray().add(shotArray);			
				((AI) p2).setLastHit(coordShot);
			}
			if (isDestroyed) {
				System.out.println("****** "+p2.getName()+"'s "+hitShip+" is destroyed! ******");
			}
			else {
				System.out.println("Hit!");
			}
		}
		else {
			System.out.println("Missed!");
		}
		System.out.println();
		p1.getShotFired().add(coordShot);
	}
	
	public static Player setupPlayer(Player p) {
		
		int stop=0;
		p.setCarrier(new Ship());
		p.setBattleship(new Ship());
		p.setCruiser(new Ship());
		p.setDestroyer(new Ship());
		p.setSubmarine(new Ship());
		p.setShotFired(new ArrayList<String>());
		ArrayList<String> spaceOccupied = new ArrayList<String>();
		String coord1="";
		String coord2="";
		boolean check;
		int size=5;
		String shipCategory="";
		System.out.println("Initialization of "+p.getName()+"'s ships");
		System.out.println();
		System.out.println("Columns between A-J, Lines between 1-10");
		for(int i=1;i<6;i++) {
			if (i!=1) {
				p.printInitShipsMap(10,10);
			}
			switch (i) {
			case 1:
				size=5;
				shipCategory="Carrier";
				break;
			case 2:
				size=4;
				shipCategory="Battleship";
				break;	
			case 3:
				size=3;
				shipCategory="Cruiser";
				break;
			case 4:
				size=3;
				shipCategory="Submarine";
				break;
			case 5:
				size=2;
				shipCategory="Destroyer";
				break;
			}
			stop=0;
			while (stop==0) {
				System.out.println("Give the first coordinate of your "+shipCategory+" (size: "+size+"):");
				coord1 = sc1.nextLine();
				check=Tools.checkInputStartCoord(spaceOccupied, coord1, size);
				if (check) {
					stop=1;
				}
				else {
					System.out.println("Bad coordinate!");
				}
			}
			System.out.print("Choose the last coordinate (you can only choose: ");
			for (String str : Tools.generateEndCoord(spaceOccupied, coord1, size)) {
				System.out.print(str+" ");
			}
			System.out.println("):");
			stop=0;
			while (stop==0) {
				coord2 = sc1.nextLine();
				if (coord2.length()==3||coord2.length()==2)	{
					for (String str : Tools.generateEndCoord(spaceOccupied, coord1, size)) {
						if (coord2.equals(str)) {
							stop=1;
						}
					}
					if (stop==0) {
						System.out.println("Bad coordinate!");
						System.out.print("Choose the end coord between : ");
						for (String str : Tools.generateEndCoord(spaceOccupied, coord1, size)) {
							System.out.print(str+" ");
						}
						System.out.println(":");
					}
				}
			}
			System.out.println(shipCategory+" generated!");
			spaceOccupied=Tools.checkSpacesArray(Tools.stringToInt(coord1),(int)coord1.charAt(0)-65,Tools.stringToInt(coord2),(int)coord2.charAt(0)-65,size,spaceOccupied);
			switch (i) {
				case 1:
					p.setCarrier(new Ship(coord1,coord2,size,shipCategory));
					break;
				case 2:
					p.setBattleship(new Ship(coord1,coord2,size,shipCategory));
					break;
				case 3:
					p.setCruiser(new Ship(coord1,coord2,size,shipCategory));
					break;
				case 4:
					p.setSubmarine(new Ship(coord1,coord2,size,shipCategory));
					break;
				case 5:
					p.setDestroyer(new Ship(coord1,coord2,size,shipCategory));
					break;
			}
		}
		System.out.println("All your ships are initialised!");
		return p;
	}
	
	
	public static void printShotMap(Player p1, Player p2, int x, int y) {
		String coord;
		System.out.println(" ");
		System.out.println(p1.getName()+"'s shots map (\"!\" missed, \"X\" hit)");
		System.out.print("  |");
		for(int k=0;k<x;k++) {
			System.out.print(" "+Tools.intToString(k)+" ");
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
				coord=Tools.intToString(j)+i; //lignes
				if (p1.getShotFired().contains(coord)&&(p2.getCarrier().getCoordShip().contains(coord)||
					p2.getBattleship().getCoordShip().contains(coord)||
					p2.getCruiser().getCoordShip().contains(coord)||
					p2.getSubmarine().getCoordShip().contains(coord)||
					p2.getDestroyer().getCoordShip().contains(coord))) {
					System.out.print(" X "); //touche
				}
				else {
					if (p1.getShotFired().contains(coord)) {
						System.out.print(" ! "); //pas touche
					}
					else {
						System.out.print(" - "); //rien
					}
				}	
			}
		System.out.println();
		}
	System.out.println();
	}

	public static void printShipsMap(Player p1, Player p2, int x, int y){
		String coord;
		System.out.println(" ");
		System.out.println(p1.getName()+"'s ships map (\"O\" ship, \"X\" ship hit, \"!\" enemy's shot attempt)");
		System.out.print("  |");
		for(int k=0;k<x;k++) {
			System.out.print(" "+Tools.intToString(k)+" ");
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
				coord=Tools.intToString(j)+i;
				if (p1.getCarrier().getShotReceived().contains(coord)||
				p1.getBattleship().getShotReceived().contains(coord)||
				p1.getCruiser().getShotReceived().contains(coord)||
				p1.getSubmarine().getShotReceived().contains(coord)||
				p1.getDestroyer().getShotReceived().contains(coord)) {
					System.out.print(" X "); //touche
				}
				else {
					if (p1.getCarrier().getCoordShip().contains(coord)||
					p1.getBattleship().getCoordShip().contains(coord)||
					p1.getCruiser().getCoordShip().contains(coord)||
					p1.getSubmarine().getCoordShip().contains(coord)||
					p1.getDestroyer().getCoordShip().contains(coord)) {
						System.out.print(" O "); //pas touche
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
		System.out.println();
	}
}

package Battleship;

import java.util.*;


public class Program {
	
	static Scanner sc1 = new Scanner(System.in);
	static Scanner sc2 = new Scanner(System.in);

	public static void main(String[] args) {
		//Selection du mode de jeu
		System.out.println("Choose one game mode");
		System.out.println("1 for : Player vs Player");
		System.out.println("2 for : Player vs AI");
		System.out.println("3 for : AI vs AI");
		String gameMode="";
		int gameModeNumber=0;
		String levelIA="";
		int stop=0;
		while(stop==0) {
			gameMode= sc1.nextLine();
			gameModeNumber=gameMode.charAt(0)-'0';
			if (gameModeNumber<1||gameModeNumber>3) {
				System.out.println("Invalid!");
			}
			else {
				stop=1;
			}
		}
		
		switch(gameModeNumber) {
		case 1:
			//Joueur contre joueur
			Player p1PVP=new Player();
			p1PVP=setupPlayer(p1PVP);
			Player p2PVP=new Player();
			p2PVP=setupPlayer(p2PVP);
			playPvP(p1PVP,p2PVP);
			break;
		
		case 2:
			//Joueur contre IA
			int levelIANumber=0;
			Player playerPVM=new Player();
			System.out.println("Select the level of your opponent");
			System.out.println("1 - Beginner");
			System.out.println("2 - Easy");
			System.out.println("3 - Normal");
			stop=0;
			while(stop==0) {
				levelIA= sc1.nextLine();
				levelIANumber=levelIA.charAt(0)-'0';
				if (levelIANumber<1||levelIANumber>3) {
					System.out.println("Invalid!");
				}
				else {
					stop=1;
				}
			}
			IA iaPVM=new IA("Opponent",levelIANumber);
			playerPVM=setupPlayer(playerPVM);
			playPvM(playerPVM,iaPVM);
			break;
		case 3:
			//IA contre IA
			System.out.println("Select the level of the IA number 1");
			System.out.println("1 - Beginner");
			System.out.println("2 - Easy");
			System.out.println("3 - Normal");
			int levelIA1Number=0;
			stop=0;
			while(stop==0) {
				levelIA= sc1.nextLine();
				levelIA1Number=levelIA.charAt(0)-'0';
				if (levelIA1Number<1||levelIA1Number>3) {
					System.out.println("Invalid!");
				}
				else {
					stop=1;
				}
			}
			IA ia1=new IA("IA number 1",levelIA1Number);
			
			System.out.println("Select the level of the IA number 2");
			System.out.println("1 - Beginner");
			System.out.println("2 - Easy");
			System.out.println("3 - Normal");
			int levelIA2Number=0;
			stop=0;
			while(stop==0) {
				levelIA= sc1.nextLine();
				levelIA2Number=levelIA.charAt(0)-'0';
				if (levelIA2Number<1||levelIA2Number>3) {
					System.out.println("Invalid!");
				}
				else {
					stop=1;
				}
			}
			IA ia2=new IA("IA number 2",levelIA2Number);
			playIAvIA(ia1,ia2);
		}
		sc1.close();	
		sc2.close();	
	}
	
	public static Player setupPlayer(Player p) {

	int stop=0;
	System.out.print("Enter your name here: ");
	String name = sc1.nextLine();
	p.setName(name);
	ArrayList<String> spaceOccupied = new ArrayList<String>();
	String coord1="";
	String coord2="";
	boolean check;
	int size=5;
	String shipCategory="";
	
	for(int i=1;i<6;i++) {
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
			check=Game.checkInputStartCoord(spaceOccupied, coord1, size);
			if (check) {
				stop=1;
			}
			else {
				System.out.println("Bad coordinate!");
			}
		}
		System.out.print("Choose the last coordinate (you can only choose: ");
		for (String str : Game.GenerateEndCoord(spaceOccupied, coord1, size)) {
			System.out.print(str+" ");
		}
		System.out.println("):");
		stop=0;
		while (stop==0) {
			coord2 = sc2.nextLine();
			if (coord2.length()==3||coord2.length()==2)	{
				for (String str : Game.GenerateEndCoord(spaceOccupied, coord1, size)) {
					if (coord2.equals(str)) {
						stop=1;
					}
				}
				if (stop==0) {
					System.out.println("Bad coordinate!");
					System.out.print("Choose the end coord between : ");
					for (String str : Game.GenerateEndCoord(spaceOccupied, coord1, size)) {
						System.out.print(str+" ");
					}
					System.out.println(":");
				}
			}
		}
		System.out.println(shipCategory+" generated!");
		spaceOccupied=Game.checkSpacesArray(Game.stringToInt(coord1),(int)coord1.charAt(0)-65,Game.stringToInt(coord2),(int)coord2.charAt(0)-65,size,spaceOccupied);
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
	System.out.println("All you ships are initialised!");

	return p;
	}
	
	public static void playPvP(Player p1, Player p2) {
		//GAME START
		String coordShot="";
		System.out.println("Game start !");
		String hitShip="";
		Boolean isDestroyed=false;
		int stop=0;
		int tour=1;
		while(!Game.endGame(p1,p2)){
			//JOUEUR 1 qui joue.
			stop=0;
			System.out.println();
			System.out.println("*********************** Tour "+tour+" ***********************");
			System.out.println();
			System.out.println(p1.getName()+" it's your turn");
			System.out.println();
			while(stop==0) {
				System.out.println(p1.getName()+" enter where you want to shoot:");
				coordShot = sc2.nextLine();
				if (Game.checkInputCoordShot(coordShot,p1)) {
					stop=1;
				}
				else {
					System.out.println("Bad coordinate!");
				}		
			}
			if (p2.shipHit(coordShot)) {
				if(Game.isCarrierHere(coordShot,p2)) {
					p2.getCarrier().getShotReceived().add(coordShot);
					hitShip=p2.getCarrier().getShipCategory();
					isDestroyed=p2.getCarrier().isDestroyed();
				}
				if(Game.isBattleshipHere(coordShot,p2)) {
					p2.getBattleship().getShotReceived().add(coordShot);
					hitShip=p2.getBattleship().getShipCategory();
					isDestroyed=p2.getBattleship().isDestroyed();
	
				}
				if(Game.isCruiserHere(coordShot,p2)) {
					p2.getCruiser().getShotReceived().add(coordShot);
					hitShip=p2.getCruiser().getShipCategory();
					isDestroyed=p2.getCruiser().isDestroyed();
				}
				if(Game.isSubmarineHere(coordShot,p2)) {
					p2.getSubmarine().getShotReceived().add(coordShot);
					hitShip=p2.getSubmarine().getShipCategory();
					isDestroyed=p2.getSubmarine().isDestroyed();
				}
				if(Game.isDestroyerHere(coordShot,p2)) {
					p2.getDestroyer().getShotReceived().add(coordShot);
					hitShip=p2.getDestroyer().getShipCategory();
					isDestroyed=p2.getDestroyer().isDestroyed();
				}
				if (isDestroyed) {
					System.out.println("Enemy "+hitShip+" is destroyed!");
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
			printShotMap(p1,p2,10,10);
			
			if (!Game.endGame(p1,p2)) {
				//Joueur 2 qui joue
				stop=0;
				System.out.println();
				System.out.println(p2.getName()+" it's your turn");
				System.out.println();
				while(stop==0) {
					System.out.println(p2.getName()+" enter where you want to shoot:");
					coordShot = sc2.nextLine();
					if (Game.checkInputCoordShot(coordShot,p1)) {
						stop=1;
					}
					else {
						System.out.println("Bad coordinate!");
					}		
				}
				if (p2.shipHit(coordShot)) {
					if(Game.isCarrierHere(coordShot,p1)) {
						p1.getCarrier().getShotReceived().add(coordShot);
						hitShip=p1.getCarrier().getShipCategory();
						isDestroyed=p1.getCarrier().isDestroyed();
					}
					if(Game.isBattleshipHere(coordShot,p1)) {
						p1.getBattleship().getShotReceived().add(coordShot);
						hitShip=p1.getBattleship().getShipCategory();
						isDestroyed=p1.getBattleship().isDestroyed();
		
					}
					if(Game.isCruiserHere(coordShot,p1)) {
						p1.getCruiser().getShotReceived().add(coordShot);
						hitShip=p1.getCruiser().getShipCategory();
						isDestroyed=p1.getCruiser().isDestroyed();
					}
					if(Game.isSubmarineHere(coordShot,p1)) {
						p1.getSubmarine().getShotReceived().add(coordShot);
						hitShip=p1.getSubmarine().getShipCategory();
						isDestroyed=p1.getSubmarine().isDestroyed();
					}
					if(Game.isDestroyerHere(coordShot,p1)) {
						p1.getDestroyer().getShotReceived().add(coordShot);
						hitShip=p1.getDestroyer().getShipCategory();
						isDestroyed=p1.getDestroyer().isDestroyed();
					}
					if (isDestroyed) {
						System.out.println("Enemy "+hitShip+" is destroyed!");
					}
					else {
						System.out.println("Hit!");
					}
				}
				else {
					System.out.println("Missed!");
				}
				System.out.println();
				p2.getShotFired().add(coordShot);
				printShotMap(p2,p1,10,10);
			}
		}		
	}
	
	public static void playPvM(Player p1, IA p2) {
		//GAME START
		String coordShot="";
		System.out.println("Game start !");
		String hitShip="";
		Boolean isDestroyed=false;
		int stop=0;
		int tour=1;
		int i=0;
		while(!Game.endGame(p1,p2)){
			//JOUEUR qui joue.
			stop=0;
			System.out.println();
			System.out.println("*********************** Tour "+tour+" ***********************");
			System.out.println();
			while(stop==0) {
				System.out.println(p1.getName()+" enter where you want to shoot:");
				coordShot = sc2.nextLine();
				if (Game.checkInputCoordShot(coordShot,p1)) {
					stop=1;
				}
				else {
					System.out.println("Bad coordinate!");
				}		
			}
			if (p2.shipHit(coordShot)) {
				if(Game.isCarrierHere(coordShot,p2)) {
					p2.getCarrier().getShotReceived().add(coordShot);
					hitShip=p2.getCarrier().getShipCategory();
					isDestroyed=p2.getCarrier().isDestroyed();
				}
				if(Game.isBattleshipHere(coordShot,p2)) {
					p2.getBattleship().getShotReceived().add(coordShot);
					hitShip=p2.getBattleship().getShipCategory();
					isDestroyed=p2.getBattleship().isDestroyed();
	
				}
				if(Game.isCruiserHere(coordShot,p2)) {
					p2.getCruiser().getShotReceived().add(coordShot);
					hitShip=p2.getCruiser().getShipCategory();
					isDestroyed=p2.getCruiser().isDestroyed();
				}
				if(Game.isSubmarineHere(coordShot,p2)) {
					p2.getSubmarine().getShotReceived().add(coordShot);
					hitShip=p2.getSubmarine().getShipCategory();
					isDestroyed=p2.getSubmarine().isDestroyed();
				}
				if(Game.isDestroyerHere(coordShot,p2)) {
					p2.getDestroyer().getShotReceived().add(coordShot);
					hitShip=p2.getDestroyer().getShipCategory();
					isDestroyed=p2.getDestroyer().isDestroyed();
				}
				if (isDestroyed) {
					System.out.println("Enemy "+hitShip+" is destroyed!");
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
			printShotMap(p1,p2,10,10);
			
			if (!Game.endGame(p1,p2)) {
				//IA qui joue.
				System.out.println();
				System.out.println("The opponent is playing!");
				coordShot=p2.generateShotCoord(p2.getLevel());
				System.out.println("Opponent shooted at "+coordShot);
				if (p1.shipHit(coordShot)) {
					if(Game.isCarrierHere(coordShot,p1)) {
						p1.getCarrier().getShotReceived().add(coordShot);
						hitShip=p1.getCarrier().getShipCategory();
						isDestroyed=p1.getCarrier().isDestroyed();
					}
					if(Game.isBattleshipHere(coordShot,p1)) {
						p1.getBattleship().getShotReceived().add(coordShot);
						hitShip=p1.getBattleship().getShipCategory();
						isDestroyed=p1.getBattleship().isDestroyed();
		
					}
					if(Game.isCruiserHere(coordShot,p1)) {
						p1.getCruiser().getShotReceived().add(coordShot);
						hitShip=p1.getCruiser().getShipCategory();
						isDestroyed=p1.getCruiser().isDestroyed();
					}
					if(Game.isSubmarineHere(coordShot,p1)) {
						p1.getSubmarine().getShotReceived().add(coordShot);
						hitShip=p1.getSubmarine().getShipCategory();
						isDestroyed=p1.getSubmarine().isDestroyed();
					}
					if(Game.isDestroyerHere(coordShot,p1)) {
						p1.getDestroyer().getShotReceived().add(coordShot);
						hitShip=p1.getDestroyer().getShipCategory();
						isDestroyed=p1.getDestroyer().isDestroyed();
					}
					if (isDestroyed) {
						System.out.println("Your "+hitShip+" is destroyed!");
					}
					else {
						System.out.println("Hit!");
					}
				}
				else {
					System.out.println("Missed!");
				}
				if(p2.getLevel()==3) {
					p2.getShotArray().get(i).add(coordShot);
					p2.getShotArray().get(i).add(Boolean.toString(p1.shipHit(coordShot)));
					p2.getShotArray().get(i).add("");
					p2.getShotArray().get(i).add(Boolean.toString(isDestroyed));
					i++;
				}
				System.out.println();
				p2.getShotFired().add(coordShot);
				printShipsMap(p1,p2,10,10);
				System.out.println();
				tour++;
			}
		}
		if (p1.shipsAllDestroyed()) {
			System.out.println(p2.getName()+" won!");
		}
		else {
			System.out.println(p1.getName()+" won!");
		}
	}
	
	public static int playIAvIA(IA p1, IA p2) {
		//GAME START
		String coordShot="";
		System.out.println("Game start !");
		String hitShip="";
		Boolean isDestroyed=false;
		int tour=1;
		int i=0;
		int j=0;
		
		while(!Game.endGame(p1,p2)){
			//IA numero 1 qui joue.
			System.out.println();
			System.out.println("*********************** Tour "+tour+" ***********************");
			System.out.println();
			System.out.println(p1.getName()+" is playing!");
			coordShot=p1.generateShotCoord(p1.getLevel());
			printShotMap(p1,p2,10,10);
			System.out.println(p1.getName()+" shooted at "+coordShot);
			if (p2.shipHit(coordShot)) {
				if(Game.isCarrierHere(coordShot,p2)) {
					p2.getCarrier().getShotReceived().add(coordShot);
					hitShip=p2.getCarrier().getShipCategory();
					isDestroyed=p2.getCarrier().isDestroyed();
				}
				if(Game.isBattleshipHere(coordShot,p2)) {
					p2.getBattleship().getShotReceived().add(coordShot);
					hitShip=p2.getBattleship().getShipCategory();
					isDestroyed=p2.getBattleship().isDestroyed();
	
				}
				if(Game.isCruiserHere(coordShot,p2)) {
					p2.getCruiser().getShotReceived().add(coordShot);
					hitShip=p2.getCruiser().getShipCategory();
					isDestroyed=p2.getCruiser().isDestroyed();
				}
				if(Game.isSubmarineHere(coordShot,p2)) {
					p2.getSubmarine().getShotReceived().add(coordShot);
					hitShip=p2.getSubmarine().getShipCategory();
					isDestroyed=p2.getSubmarine().isDestroyed();
				}
				if(Game.isDestroyerHere(coordShot,p2)) {
					p2.getDestroyer().getShotReceived().add(coordShot);
					hitShip=p2.getDestroyer().getShipCategory();
					isDestroyed=p2.getDestroyer().isDestroyed();
				}
				if (isDestroyed) {
					System.out.println("Your "+hitShip+" is destroyed!");
				}
				else {
					System.out.println("Hit!");
				}
			}
			else {
				System.out.println("Missed!");
			}
			if(p1.getLevel()==3) {
				p1.getShotArray().get(i).add(coordShot);
				p1.getShotArray().get(i).add(Boolean.toString(p2.shipHit(coordShot)));
				p1.getShotArray().get(i).add("");
				p1.getShotArray().get(i).add(Boolean.toString(isDestroyed));
				i++;
			}
			System.out.println();
			p1.getShotFired().add(coordShot);
			System.out.println();
			
			
			if (!Game.endGame(p1,p2)) {
				//IA numero 2 qui joue.
				System.out.println();
				System.out.println(p2.getName()+" is playing!");
				printShotMap(p2,p1,10,10);
				coordShot=p2.generateShotCoord(p2.getLevel());
				System.out.println(p2.getName()+" shooted at "+coordShot);
				if (p1.shipHit(coordShot)) {
					if(Game.isCarrierHere(coordShot,p1)) {
						p1.getCarrier().getShotReceived().add(coordShot);
						hitShip=p1.getCarrier().getShipCategory();
						isDestroyed=p1.getCarrier().isDestroyed();
					}
					if(Game.isBattleshipHere(coordShot,p1)) {
						p1.getBattleship().getShotReceived().add(coordShot);
						hitShip=p1.getBattleship().getShipCategory();
						isDestroyed=p1.getBattleship().isDestroyed();
		
					}
					if(Game.isCruiserHere(coordShot,p1)) {
						p1.getCruiser().getShotReceived().add(coordShot);
						hitShip=p1.getCruiser().getShipCategory();
						isDestroyed=p1.getCruiser().isDestroyed();
					}
					if(Game.isSubmarineHere(coordShot,p1)) {
						p1.getSubmarine().getShotReceived().add(coordShot);
						hitShip=p1.getSubmarine().getShipCategory();
						isDestroyed=p1.getSubmarine().isDestroyed();
					}
					if(Game.isDestroyerHere(coordShot,p1)) {
						p1.getDestroyer().getShotReceived().add(coordShot);
						hitShip=p1.getDestroyer().getShipCategory();
						isDestroyed=p1.getDestroyer().isDestroyed();
					}
					if (isDestroyed) {
						System.out.println("Your "+hitShip+" is destroyed!");
					}
					else {
						System.out.println("Hit!");
					}
				}
				else {
					System.out.println("Missed!");
				}
				if(p2.getLevel()==3) {
					p2.getShotArray().get(j).add(coordShot);
					p2.getShotArray().get(j).add(Boolean.toString(p1.shipHit(coordShot)));
					p2.getShotArray().get(j).add("");
					p2.getShotArray().get(j).add(Boolean.toString(isDestroyed));
					j++;
				}
				System.out.println();
				p2.getShotFired().add(coordShot);
				System.out.println();
				tour++;
			}
		}
		if (p1.shipsAllDestroyed()) {
			System.out.println(p2.getName()+" won!");
			return 2;
		}
		else {
			System.out.println(p1.getName()+" won!");
			return 1;
		}
	}
	
	public static void printShotMap(Player p1, Player p2, int x, int y) {
		String coord;
		System.out.println(p1.getName()+", map with your shots (\"!\" missed, \"X\" hit)");
		System.out.print("  |");
		for(int k=0;k<x;k++) {
			System.out.print(" "+Game.intToString(k)+" ");
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
				coord=Game.intToString(j)+i; //lignes
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

	public static void printShipsMap(Player p1, Player p2, int x, int y){
		String coord;
		System.out.println(p1.getName()+", map with your ships (\"O\" ship, \"X\" ship hit, \"!\" enemy's shot attempt)");
		System.out.print("  |");
		for(int k=0;k<x;k++) {
			System.out.print(" "+Game.intToString(k)+" ");
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
				coord=Game.intToString(j)+i;
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
		
}

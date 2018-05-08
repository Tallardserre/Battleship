
import java.util.*;


public class Program {

	public static void main(String[] args) {
		Scanner sc1 = new Scanner(System.in);
		Scanner sc2 = new Scanner(System.in);
		System.out.print("Enter your name here: ");
		String name = sc1.nextLine();
		Player p1=new Player(name);
		IA p2=new IA("Opponent");
		ArrayList<String> spaceOccupied = new ArrayList<String>();
		int stop=0;
		String coord1="";
		String coord2="";
		boolean check;
		int size=5;
		String shipCategory="";
		String coordShot="";
		
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
					p1.setCarrier(new Ship(coord1,coord2,size,shipCategory));
					break;
				case 2:
					p1.setBattleship(new Ship(coord1,coord2,size,shipCategory));
					break;
				case 3:
					p1.setCruiser(new Ship(coord1,coord2,size,shipCategory));
					break;
				case 4:
					p1.setSubmarine(new Ship(coord1,coord2,size,shipCategory));
					break;
				case 5:
					p1.setDestroyer(new Ship(coord1,coord2,size,shipCategory));
					break;
			}
		}
		System.out.println("All you ships are initialised!");
		Game.printShipsMap(p1,p2,10,10);
		
		//IA	
		Random rand = new Random();
		ArrayList<String> endCoordGenerate = new ArrayList<String>();
		int startCoordLine=0;
		int startCoordColl=0;
		String endCoord="";
		String startCoord="";
		for(int i=1;i<6;i++) {
			startCoordLine=rand.nextInt((10-1)+1)+1;
			startCoordColl=rand.nextInt((9-0)+1)+0;
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
				startCoord=Game.intToString(startCoordColl)+startCoordLine;
				if(Game.checkStartCoord(startCoord, size, spaceOccupied)){
					stop=1;
				}
				else {
					startCoordLine=rand.nextInt((10-1)+1)+1;
					startCoordColl=rand.nextInt((9-0)+1)+0;
				}
			}
			endCoordGenerate=Game.GenerateEndCoord(spaceOccupied,startCoord,size);
			int j=rand.nextInt((endCoordGenerate.size()-0))+0;
			endCoord=endCoordGenerate.get(j);
			switch (i) {
			case 1:
				p2.setCarrier(new Ship(startCoord,endCoord,size,shipCategory));
				spaceOccupied=Game.checkSpacesArray(Game.stringToInt(startCoord),(int)startCoord.charAt(0)-65,Game.stringToInt(endCoord),(int)endCoord.charAt(0)-65,size,spaceOccupied);
				break;
			case 2:
				p2.setBattleship(new Ship(startCoord,endCoord,size,shipCategory));
				spaceOccupied=Game.checkSpacesArray(Game.stringToInt(startCoord),(int)startCoord.charAt(0)-65,Game.stringToInt(endCoord),(int)endCoord.charAt(0)-65,size,spaceOccupied);
				break;
			case 3:
				p2.setCruiser(new Ship(startCoord,endCoord,size,shipCategory));
				spaceOccupied=Game.checkSpacesArray(Game.stringToInt(startCoord),(int)startCoord.charAt(0)-65,Game.stringToInt(endCoord),(int)endCoord.charAt(0)-65,size,spaceOccupied);
				break;
			case 4:
				p2.setSubmarine(new Ship(startCoord,endCoord,size,shipCategory));
				spaceOccupied=Game.checkSpacesArray(Game.stringToInt(startCoord),(int)startCoord.charAt(0)-65,Game.stringToInt(endCoord),(int)endCoord.charAt(0)-65,size,spaceOccupied);
				break;
			case 5:
				p2.setDestroyer(new Ship(startCoord,endCoord,size,shipCategory));
				spaceOccupied=Game.checkSpacesArray(Game.stringToInt(startCoord),(int)startCoord.charAt(0)-65,Game.stringToInt(endCoord),(int)endCoord.charAt(0)-65,size,spaceOccupied);
				break;
			}
		}
		
		//GAME START
		System.out.println("Game start !");
		while(!Game.endGame(p1,p2)) {
			String hitShip="";
			Boolean isDestroyed=false;
			stop=0;
			while(stop==0) {
				System.out.println(p1.getName()+" enter where you want to shoot: ");
				coordShot = sc1.nextLine();
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
			p1.getShotFired().add(coordShot);
			Game.printShotMap(p1,p2,10,10);
		}
		if (p1.shipsAllDestroyed()) {
			System.out.println(p2.getName()+" won!");
		}
		else {
			System.out.println(p1.getName()+" won!");
		}
		
		sc1.close();	
		sc2.close();		
	}
}

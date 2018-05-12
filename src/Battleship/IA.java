
package Battleship;

import java.util.ArrayList;
import java.util.Random;

public class IA extends Player{
	private int level;
	private ArrayList<ArrayList<String>> shotArray; 
		//case 0: coord
		//case 1: touche oui/non 
		//case 2: vertical 1/horizontal 2/inconnu 0
		//case 3: coulé oui/non

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public ArrayList<ArrayList<String>> getShotArray() {
		return shotArray;
	}

	public void setShotArray(ArrayList<ArrayList<String>> shotArray) {
		this.shotArray = shotArray;
	}
	
	public IA(String name, int level) {
		super(name);
		this.level = level;
		int size=0;
		int stop=0;
		ArrayList<String> spaceOccupied = new ArrayList<String>();
		String shipCategory="";
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
			int j=rand.nextInt((endCoordGenerate.size()));
			endCoord=endCoordGenerate.get(j);
			switch (i) {
			case 1:
				super.setCarrier(new Ship(startCoord,endCoord,size,shipCategory));
				spaceOccupied=Game.checkSpacesArray(Game.stringToInt(startCoord),(int)startCoord.charAt(0)-65,Game.stringToInt(endCoord),(int)endCoord.charAt(0)-65,size,spaceOccupied);
				break;
			case 2:
			super.setBattleship(new Ship(startCoord,endCoord,size,shipCategory));
				spaceOccupied=Game.checkSpacesArray(Game.stringToInt(startCoord),(int)startCoord.charAt(0)-65,Game.stringToInt(endCoord),(int)endCoord.charAt(0)-65,size,spaceOccupied);
				break;
			case 3:
				super.setCruiser(new Ship(startCoord,endCoord,size,shipCategory));
				spaceOccupied=Game.checkSpacesArray(Game.stringToInt(startCoord),(int)startCoord.charAt(0)-65,Game.stringToInt(endCoord),(int)endCoord.charAt(0)-65,size,spaceOccupied);
				break;
			case 4:
				super.setSubmarine(new Ship(startCoord,endCoord,size,shipCategory));
				spaceOccupied=Game.checkSpacesArray(Game.stringToInt(startCoord),(int)startCoord.charAt(0)-65,Game.stringToInt(endCoord),(int)endCoord.charAt(0)-65,size,spaceOccupied);
				break;
			case 5:
				super.setDestroyer(new Ship(startCoord,endCoord,size,shipCategory));
				spaceOccupied=Game.checkSpacesArray(Game.stringToInt(startCoord),(int)startCoord.charAt(0)-65,Game.stringToInt(endCoord),(int)endCoord.charAt(0)-65,size,spaceOccupied);
				break;
			}
		}
	}

	public String generateShotCoord(int level) {
		String coordShot="";
		switch(level) {
		case 1:
			coordShot=generateShotCoord1();
			break;
		case 2:
			coordShot=generateShotCoord2();
			break;
		case 3:
			coordShot=generateShotCoord3();
			break;
		}
		return coordShot;
	}
	
	public String generateShotCoord1() { 
		//generation de coordonnée de tir pour IA de niveau 1
		Random rand = new Random();
		int stop=0;
		int coordShotLine,coordShotColl;
		String coordShot="";
		while(stop==0) {
			coordShotLine=rand.nextInt((10-0));
			coordShotColl=rand.nextInt((10-1)+1)+1;
			coordShot=Game.intToString(coordShotLine)+coordShotColl;
			if (Game.checkCoord(coordShot)) { 
				//on verifie juste si la coordonnée est valide
				stop=1;
			}
		}
		return coordShot;
	}
	
	public String generateShotCoord2() { 
		//generation de coordonnée de tir pour IA de niveau 2
		Random rand = new Random();
		int stop=0;
		int coordShotLine,coordShotColl;
		String coordShot="";
		while(stop==0) {
			coordShotLine=rand.nextInt((10-0));
			coordShotColl=rand.nextInt((10-1)+1)+1;
			coordShot=Game.intToString(coordShotLine)+coordShotColl;
			if (Game.checkInputCoordShot(coordShot,this)) {
				// on verifie si la coordonnée est valide et si elle n'a jamais été utilisée
				stop=1;
			}
		}
		return coordShot;
	}
	
	public String generateShotCoord3() {
		Random rand = new Random();
		int stop=0;
		int coordShotLine,coordShotColl;
		String coordShot="";//GENERER LES TIRS!
		while(stop==0) {
			coordShotLine=rand.nextInt((10-0));
			coordShotColl=rand.nextInt((10-1)+1)+1;
			coordShot=Game.intToString(coordShotLine)+coordShotColl;
			if (Game.checkInputCoordShot(coordShot,this)) {
				// on verifie si la coordonnée est valide et si elle n'a jamais été utilisée
				stop=1;
			}
		}
		return coordShot;
	}


}
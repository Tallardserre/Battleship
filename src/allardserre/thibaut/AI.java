package allardserre.thibaut;

import java.util.ArrayList;
import java.util.Random;

public class AI extends Player{
	private int level;
	private ArrayList<ArrayList<String>> shotArray = new ArrayList<ArrayList<String>>(); 
		//case 0: coord
		//case 1: coulé oui/non
	private ArrayList<String> generateShot = new ArrayList<String>();
	private String lastHit;

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

	public ArrayList<String> getGenerateShot() {
		return generateShot;
	}

	public void setGenerateShot(ArrayList<String> generateShot) {
		this.generateShot = generateShot;
	}

	public String getLastHit() {
		return lastHit;
	}

	public void setLastHit(String lastHit) {
		this.lastHit = lastHit;
	}

	public AI(String name, int level) {
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
				startCoord=Tools.intToString(startCoordColl)+startCoordLine;
				if(Tools.checkStartCoord(startCoord, size, spaceOccupied)){
					stop=1;
				}
				else {
					startCoordLine=rand.nextInt((10-1)+1)+1;
					startCoordColl=rand.nextInt((9-0)+1)+0;
				}
			}
			endCoordGenerate=Tools.generateEndCoord(spaceOccupied,startCoord,size);
			int j=rand.nextInt((endCoordGenerate.size()));
			endCoord=endCoordGenerate.get(j);
			switch (i) {
			case 1:
				super.setCarrier(new Ship(startCoord,endCoord,size,shipCategory));
				spaceOccupied=Tools.checkSpacesArray(Tools.stringToInt(startCoord),(int)startCoord.charAt(0)-65,Tools.stringToInt(endCoord),(int)endCoord.charAt(0)-65,size,spaceOccupied);
				break;
			case 2:
			super.setBattleship(new Ship(startCoord,endCoord,size,shipCategory));
				spaceOccupied=Tools.checkSpacesArray(Tools.stringToInt(startCoord),(int)startCoord.charAt(0)-65,Tools.stringToInt(endCoord),(int)endCoord.charAt(0)-65,size,spaceOccupied);
				break;
			case 3:
				super.setCruiser(new Ship(startCoord,endCoord,size,shipCategory));
				spaceOccupied=Tools.checkSpacesArray(Tools.stringToInt(startCoord),(int)startCoord.charAt(0)-65,Tools.stringToInt(endCoord),(int)endCoord.charAt(0)-65,size,spaceOccupied);
				break;
			case 4:
				super.setSubmarine(new Ship(startCoord,endCoord,size,shipCategory));
				spaceOccupied=Tools.checkSpacesArray(Tools.stringToInt(startCoord),(int)startCoord.charAt(0)-65,Tools.stringToInt(endCoord),(int)endCoord.charAt(0)-65,size,spaceOccupied);
				break;
			case 5:
				super.setDestroyer(new Ship(startCoord,endCoord,size,shipCategory));
				spaceOccupied=Tools.checkSpacesArray(Tools.stringToInt(startCoord),(int)startCoord.charAt(0)-65,Tools.stringToInt(endCoord),(int)endCoord.charAt(0)-65,size,spaceOccupied);
				break;
			}
		}
	}

	public String generateShotCoord() {
		int level=this.getLevel();
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
		//generation de coordonnée de tir pour AI de niveau 1
		Random rand = new Random();
		int stop=0;
		int coordShotLine,coordShotColl;
		String coordShot="";
		while(stop==0) {
			coordShotLine=rand.nextInt((10-0));
			coordShotColl=rand.nextInt((10-1)+1)+1;
			coordShot=Tools.intToString(coordShotLine)+coordShotColl;
			if (Tools.checkCoord(coordShot)) { 
				//on verifie juste si la coordonnée est valide
				stop=1;
			}
		}
		return coordShot;
	}
	
	public String generateShotCoord2() { 
		//generation de coordonnée de tir pour AI de niveau 2
		Random rand = new Random();
		int stop=0;
		int coordShotLine,coordShotColl;
		String coordShot="";
		while(stop==0) {
			coordShotLine=rand.nextInt((10-0));
			coordShotColl=rand.nextInt((10-1)+1)+1;
			coordShot=Tools.intToString(coordShotLine)+coordShotColl;
			if (Tools.checkInputCoordShot(coordShot,this.getShotFired())) {
				// on verifie si la coordonnée est valide et si elle n'a jamais été utilisée
				stop=1;
			}
		}
		return coordShot;
	}
	
	public String generateShotCoord3() {
		Random rand = new Random();
		int stop=0;
		int i=0;
		boolean check=false;
		String coordShot="";//GENERER LES TIRS!		
		if(this.getShotArray().size()>0&&this.getShotArray().get(this.getShotArray().size()-1).get(1)=="false") {
			//si la liste de tir touché n'est pas vide		
			if(this.getGenerateShot().isEmpty()) {//si la liste de tir genere est vide
				if(this.getShotFired().get(this.getShotFired().size()-1).equals(this.getLastHit())) {//si le dernier tir est touché
					if(this.getShotArray().size()>1) {//si on a au moins 2 tir touché
						check=false;
						if(this.getShotArray().get(this.getShotArray().size()-1).get(1)=="false") {	//si le dernier tir n'a pas coulé le bateau
							i=this.getShotArray().size()-1;
							while(i>0&&!check) {
								if(Tools.nearCoord(this.getShotArray().get(i).get(0), lastHit)) {
									check=true;
								}
								i--;
							}
							if(this.getShotArray().size()<1||!check) {
								//si nb shot touché <1 OU deux derniers tirs non voisins
								this.setGenerateShot(Tools.generateShotArray(this.getShotFired(), this.getLastHit()));
								//generer nouvelle liste avec lasthit
							}
							else if(this.getShotArray().size()>1&&check) {
									this.setGenerateShot(Tools.generateSmartShotArray(this.getShotFired(), this.getLastHit(), this.getShotArray().get(i).get(0)));
									//generer nouvelle "smart" liste	
							}
							if(this.getGenerateShot().isEmpty()) {
								this.setGenerateShot(Tools.generateSmartShotArray(this.getShotFired(), this.getShotArray().get(i).get(0), this.getLastHit()));
							}
						}
					}
					else {
						this.setGenerateShot(Tools.generateShotArray(this.getShotFired(), this.getLastHit()));
					}
				}	
				else{//si le dernier tir n'est pas touché
					if(this.getShotArray().size()>1) {
						check=false;
						if(this.getShotArray().get(this.getShotArray().size()-1).get(1)=="false") {
							for(ArrayList<String> arrayStr:this.getShotArray()) {
								if(Tools.nearCoord(arrayStr.get(0), lastHit)) {
									check=true;
								}
							}
						}
						if(check){//si 2 derniers tirs touchés sont voisins	
							this.setGenerateShot(Tools.generateSmartShotArray(this.getShotFired(), this.getLastHit(), this.getShotArray().get(this.getShotArray().size()-2).get(0)));
							//generer nouvelle "smart" liste
							if(this.getGenerateShot().isEmpty()) {//si liste vide on inverse les deux derniers tirs dans la fonction
								i=this.getShotArray().size()-1;
								while(i>0&&this.getGenerateShot().isEmpty()){	
									this.setGenerateShot(
											Tools.generateSmartShotArray(this.getShotFired(), this.getShotArray().get(i-1).get(0), this.getLastHit()));
								i--;
								}
							} 
						}
					}
				}
			}
			else {//si liste de tir genere non vide		
				if(this.getLastHit().equals(this.getShotFired().get(this.getShotFired().size()-1))) {//si le dernier tir est touché
					if(this.getShotArray().size()<1||!Tools.nearCoord(this.getShotArray().get(this.getShotArray().size()-1).get(0), this.getShotArray().get(this.getShotArray().size()-2).get(0))) {
						//si nb shot<1 OU deux derniers tirs non voisins
						this.setGenerateShot(Tools.generateShotArray(this.getShotFired(), this.getLastHit()));
					}
					else {//si 2 derniers tirs voisins
						this.setGenerateShot(Tools.generateSmartShotArray(this.getShotFired(), this.getShotArray().get(this.getShotArray().size()-1).get(0), this.getShotArray().get(this.getShotArray().size()-2).get(0)));
						//generer nouvelle "smart" liste
						if(this.getGenerateShot().isEmpty()) {//si liste vide on inverse les deux derniers tirs dans la fonction
							i=this.getShotArray().size()-1;
							while(i>0&&this.getGenerateShot().isEmpty()&&Tools.nearCoord(this.getShotArray().get(i-1).get(0), this.getShotArray().get(i).get(0))){
								this.setGenerateShot(
										Tools.generateSmartShotArray(this.getShotFired(), this.getShotArray().get(i-1).get(0), this.getShotArray().get(i).get(0)));
							i--;
							}
						}
					}
				}
			}
		}
		if (this.getGenerateShot().isEmpty()) {//pas de coord de tir generées
			if(this.getShotArray().isEmpty()) {//aucun tir n'a touché de bateau
				while(stop==0) {//on genere des coordonnées qui concernent une case sur 2
					int coordShotLine=rand.nextInt((10-0));
					int coordShotColl=rand.nextInt((10-1)+1)+1;
					if(coordShotLine%2!=0||coordShotColl%2==0) {
						coordShot=Tools.intToString(coordShotLine)+coordShotColl;
						if (Tools.checkInputCoordShot(coordShot,this.getShotFired())) {
							stop=1;
						}
					}
					else if(coordShotLine%2==0||coordShotColl%2!=0) {
						coordShot=Tools.intToString(coordShotLine)+coordShotColl;
						if (Tools.checkInputCoordShot(coordShot,this.getShotFired())) {
							stop=1;
						}
					}
				}
			}
			else {//si on a deja un tir touché
				stop=0;
				while(stop==0) {//genere des coordonnées aleatoires
					int coordShotLine=rand.nextInt((10-0));
					int coordShotColl=rand.nextInt((10-1)+1)+1;
					coordShot=Tools.intToString(coordShotLine)+coordShotColl;
					if (Tools.checkInputCoordShot(coordShot,this.getShotFired())) {
						stop=1;
					}
				}
			}
		}
		if(coordShot.equals("")) {//si la liste de tir generées n'est pas vide
			i=0;
			while(i<0||i>this.getGenerateShot().size()) {// on choisit au hasard une des coordonnées 
				i=rand.nextInt(this.getGenerateShot().size()+1)-1;
			}
			coordShot=this.getGenerateShot().get(i);
			this.getGenerateShot().remove(i);
		}
		return coordShot;
	}
}

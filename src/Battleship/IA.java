
package Battleship;

import java.util.ArrayList;
import java.util.Random;

public class IA extends Player{
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
			if (Game.checkInputCoordShot(coordShot,this.getShotFired())) {
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
		String coordShot="";//GENERER LES TIRS!		
				
		if(this.getShotArray().size()>0&&this.getLastHit()==this.getShotArray().get(this.getShotArray().size()-1).get(0)) {
			//si la liste de tir touchés est pas vide et si le dernier tir touché correspond au dernier tir de la liste de tir touchés
			
			if(this.getShotArray().get(this.getShotArray().size()-1).get(1)=="false") {
				//si la le dernier tir de la liste de tir n'a pas coulé le bateau touché
				
				if(this.getGenerateShot().size()>0) {
					System.out.println("G");
					//si la liste de tir generee est pas vide
					if(super.getShotFired().get(super.getShotFired().size()-1)==this.getLastHit()) {
						System.out.println("F");
						//si le dernier tir est touché et correspond a un tir de la liste de tir 
						i=this.getShotArray().size()-1;
						if (Game.GenerateSmartShotArray(this.getShotFired(),this.getLastHit(),this.getShotArray().get(i).get(0)).isEmpty()) {
							System.out.println("D");
							while (Game.GenerateSmartShotArray(this.getShotFired(),this.getLastHit(),this.getShotArray().get(i).get(0)).isEmpty()) {
								i--;
								this.setGenerateShot(Game.GenerateSmartShotArray(this.getShotFired(),this.getLastHit(),this.getShotArray().get(i).get(0)));
							}
						}
						else {
							System.out.println("O");
							this.setGenerateShot(Game.GenerateSmartShotArray(this.getShotFired(),this.getLastHit(),this.getShotArray().get(i-1).get(0)));
						}//on genere des tirs autour du dernier tir touché
						i=-1;
						while(i<0) {
						i=rand.nextInt(this.getGenerateShot().size()+1)-1;
						}
						//on prend au hasard une coordonnée generée.
						Game.affiche(this.getGenerateShot());
						coordShot=this.getGenerateShot().get(i);
						this.getGenerateShot().remove(i);
					}
					else {
					i=rand.nextInt(this.getGenerateShot().size());
					//on prend au hasard une coordonnée generée.
					coordShot=this.getGenerateShot().get(i);
					Game.affiche(this.getGenerateShot());
					this.getGenerateShot().remove(i);
					}
				}
				else { //pas de liste de tir generée
					i=this.getShotArray().size()-1;
					stop=1;
					if (i>=1) {
						if (Game.GenerateSmartShotArray(this.getShotFired(),this.getLastHit(),this.getShotArray().get(i-1).get(0)).isEmpty()) {
							while(Game.GenerateSmartShotArray(this.getShotFired(),this.getShotArray().get(i).get(0),this.getShotArray().get(i-1).get(0)).isEmpty()) {
								i=i-1;
								if(Game.nearCoord(this.getShotArray().get(i).get(0),this.getShotArray().get(i-1).get(0))) {
									if (Game.GenerateSmartShotArray(this.getShotFired(),this.getShotArray().get(i).get(0),this.getShotArray().get(i-1).get(0)).isEmpty()) {
										this.setGenerateShot(Game.GenerateSmartShotArray(this.getShotFired(),this.getShotArray().get(i).get(0),this.getShotArray().get(i-1).get(0)));
									}
								}
								else {
									this.setGenerateShot(Game.GenerateSmartShotArray(this.getShotFired(),this.getShotArray().get(i).get(0),this.getShotArray().get(i-1).get(0)));

								}
							}
						}
						/*
						System.out.println("A");
						System.out.println("i="+i);
						if (Game.GenerateSmartShotArray(this.getShotFired(),this.getLastHit(),this.getShotArray().get(i-1).get(0)).isEmpty()) {
							System.out.println("B");
							//si toute les cases autour de la derniere touchées ont ete testées et que le bateau n'est toujours pas coulé
							while (Game.GenerateSmartShotArray(this.getShotFired(),this.getShotArray().get(i-1).get(0),this.getShotArray().get(i).get(0)).isEmpty()&&stop!=0) {
								System.out.println("i="+i);
								i--;
								if(!Game.nearCoord(this.getShotArray().get(i-1).get(0), this.getShotArray().get(i).get(0))||i<1) {
									System.out.println("STOP");
									stop=0;
								}
								else {
									System.out.println("10");
									this.setGenerateShot(Game.GenerateSmartShotArray(this.getShotFired(),this.getShotArray().get(i-1).get(0),this.getShotArray().get(i).get(0)));
									Game.affiche(this.getGenerateShot());
								}
							}							
						}
						else {
							System.out.println("2");
							if (Game.GenerateShotArray(this.getShotFired(),this.getLastHit()).isEmpty()){
								System.out.println("94");
								while (Game.GenerateShotArray(this.getShotFired(),this.getShotArray().get(i).get(0)).isEmpty()) {
									i--;
									this.setGenerateShot(Game.GenerateShotArray(this.getShotFired(),this.getShotArray().get(i).get(0)));
								}
							}
							else {
								System.out.println("89");
								this.setGenerateShot(Game.GenerateShotArray(this.getShotFired(),this.getShotArray().get(i).get(0)));
							}
						}
					*/}
					else {
						System.out.println("3");
						i=this.getShotArray().size()-1;
						if (Game.GenerateShotArray(this.getShotFired(),this.getLastHit()).isEmpty()){
							System.out.println("4");
							while (Game.GenerateShotArray(this.getShotFired(),this.getShotArray().get(i).get(0)).isEmpty()) {
								i--;
								this.setGenerateShot(Game.GenerateShotArray(this.getShotFired(),this.getShotArray().get(i).get(0)));
							}
						}
						else {
							System.out.println("5");
							this.setGenerateShot(Game.GenerateShotArray(this.getShotFired(),this.getLastHit()));
						}
					}
					//on genere des tirs autour du dernier tir touché
					i=-1;
					while(i<0) {
					i=rand.nextInt(this.getGenerateShot().size()+1)-1;
					}
					//on prend au hasard une coordonnée generée.
					Game.affiche(this.getGenerateShot());
					coordShot=this.getGenerateShot().get(i);
					this.getGenerateShot().remove(i);
				}
			}
			else {
				while(stop==0) {
					int coordShotLine=rand.nextInt((10-0));
					int coordShotColl=rand.nextInt((10-1)+1)+1;
					coordShot=Game.intToString(coordShotLine)+coordShotColl;
					if (Game.checkInputCoordShot(coordShot,this.getShotFired())) {
						// on verifie si la coordonnée est valide et si elle n'a jamais été utilisée
						stop=1;
					}
				}				
			}
		}
		else {
			while(stop==0) {
				int coordShotLine=rand.nextInt((10-0));
				int coordShotColl=rand.nextInt((10-1)+1)+1;
				coordShot=Game.intToString(coordShotLine)+coordShotColl;
				if (Game.checkInputCoordShot(coordShot,this.getShotFired())) {
					// on verifie si la coordonnée est valide et si elle n'a jamais été utilisée
					stop=1;
				}
			}
		}
		return coordShot;
	}

}
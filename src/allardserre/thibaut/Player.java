
package allardserre.thibaut;

import java.util.ArrayList;

public class Player {
	private String name;
	private Ship carrier;
	private Ship battleship;
	private Ship cruiser;
	private Ship submarine;
	private Ship destroyer;
	private ArrayList<String> shotFired = new ArrayList<String>();

	public Player(){
	}
	
	public Player(String name){
		setName(name);
	}

	public Ship getCarrier() {
		return carrier;
	}

	public void setCarrier(Ship carrier) {
		this.carrier = carrier;
	}

	public Ship getBattleship() {
		return battleship;
	}

	public void setBattleship(Ship battleship) {
		this.battleship = battleship;
	}

	public Ship getCruiser() {
		return cruiser;
	}

	public void setCruiser(Ship cruiser) {
		this.cruiser = cruiser;
	}

	public Ship getSubmarine() {
		return submarine;
	}

	public void setSubmarine(Ship submarine) {
		this.submarine = submarine;
	}

	public Ship getDestroyer() {
		return destroyer;
	}

	public void setDestroyer(Ship destroyer) {
		this.destroyer = destroyer;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<String> getShotFired() {
		return shotFired;
	}

	public void setShotFired(ArrayList<String> shotFired) {
		this.shotFired = shotFired;
	}
	
	public boolean shipHit(String coord){
		return this.carrier.isHit(coord)||this.battleship.isHit(coord)||this.submarine.isHit(coord)||this.cruiser.isHit(coord)||this.destroyer.isHit(coord);
	}
	
	public boolean shipsAllDestroyed() {
		return this.carrier.isDestroyed()&&this.battleship.isDestroyed()&&this.submarine.isDestroyed()&&this.cruiser.isDestroyed()&&this.destroyer.isDestroyed();
	}
	 		
	public void printInitShipsMap(int x, int y){
		String coord;
		System.out.println(" ");
		System.out.println(this.getName()+"'s ships map");
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
				if (this.getCarrier().getCoordShip().contains(coord)||
				this.getBattleship().getCoordShip().contains(coord)||
				this.getCruiser().getCoordShip().contains(coord)||
				this.getSubmarine().getCoordShip().contains(coord)||
				this.getDestroyer().getCoordShip().contains(coord)) {
					System.out.print(" O "); //pas touché
				}
				else {
					System.out.print(" - "); //rien
				}
			}
			System.out.println();
		}
		System.out.println();
	}
}
		



package battleship;

import java.util.ArrayList;

public class Player {
	private String name;
	private Ship Carrier;
	private Ship Battleship;
	private Ship Cruiser;
	private Ship Submarine;
	private Ship Destroyer;
	private ArrayList<String> shotFired = new ArrayList<String>();

	public Player(){
	}
	
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

	public ArrayList<String> getShotFired() {
		return shotFired;
	}

	public void setShotFired(ArrayList<String> shotFired) {
		this.shotFired = shotFired;
	}
	
	public boolean shipHit(String coord){
		return this.Carrier.isHit(coord)||this.Battleship.isHit(coord)||this.Submarine.isHit(coord)||this.Cruiser.isHit(coord)||this.Destroyer.isHit(coord);
	}
	
	public boolean shipsAllDestroyed() {
		return this.Carrier.isDestroyed()&&this.Battleship.isDestroyed()&&this.Submarine.isDestroyed()&&this.Cruiser.isDestroyed()&&this.Destroyer.isDestroyed();
	}
	
 	
}
		


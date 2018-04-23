
import java.util.*;


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
	
	
}
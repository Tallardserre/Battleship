
import java.util.*;

public class Ship {

	private String ShipCategory;
	private ArrayList<String> coordShip = new ArrayList<String>();
	private ArrayList<String> shotReceived = new ArrayList<String>();
	private int size;

	public String getShipCategory() {
		return ShipCategory;
	}

	public void setShipCategory(String shipCategory) {
		ShipCategory = shipCategory;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
	public ArrayList<String> getShotReceived() {
		return shotReceived;
	}

	public void setShotReceived(ArrayList<String> shotReceived) {
		this.shotReceived = shotReceived;
	}


	public ArrayList<String> getCoordShip() {
		return coordShip;
	}

	public void setCoordShip(ArrayList<String> coordShip) {
		this.coordShip = coordShip;
	}
	
	public Ship(String startCoord, String endCoord, int size, String category) {
		this.ShipCategory=category;
		int startCoordColl = (int) startCoord.charAt(0) - 65;
		int startCoordLine = Game.stringToInt(startCoord);
		int endCoordColl = (int) endCoord.charAt(0) - 65;
		int endCoordLine = Game.stringToInt(endCoord);
		

		this.coordShip = Game.checkSpacesArray(startCoordLine, startCoordColl, endCoordLine, endCoordColl, size,
				this.coordShip);
	}

	public boolean isDestroyed() {
		return this.coordShip.size()==this.shotReceived.size();
	}
	
	public boolean isHit(String missileCoord) {
		for (String coord : coordShip) {
			if (coord.equals(missileCoord)) {
				return true;
			}
		}
		return false;
	}
	
}
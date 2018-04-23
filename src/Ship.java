
import java.util.*;

public class Ship {

	private String ShipCategory;
	private ArrayList<String> coordShip = new ArrayList<String>();
	private ArrayList<String> shotReceived = new ArrayList<String>();

	public ArrayList<String> getCoordShip() {
		return coordShip;
	}

	public void setCoordShip(ArrayList<String> coordShip) {
		this.coordShip = coordShip;
	}

	public Ship(String startCoord, String endCoord, String category) {
		int size = 0;

		switch (category) {
		case "Carrier":
			size = 5;
			break;
		case "Battleship":
			size = 4;
			break;
		case "Cruiser":
			size = 3;
			break;
		case "Submarine":
			size = 3;
			break;
		case "Destroyer":
			size = 2;
			break;

		}
		int startCoordColl = (int) startCoord.charAt(0) - 65;
		int startCoordLine = Game.stringToInt(startCoord);
		int endCoordColl = (int) endCoord.charAt(0) - 65;
		int endCoordLine = Game.stringToInt(endCoord);
		

		this.coordShip = Game.checkSpacesArray(startCoordLine, startCoordColl, endCoordLine, endCoordColl, size,
				this.coordShip);
	}

	public boolean isHit(String missileCoord) {
		for (String coord : coordShip) {
			if (coord.equals(missileCoord)) {
				return true;
			}
		}
		return false;
	}
	
	public void saveHit(String missileCoord) {
		if (isHit(missileCoord)) {
			this.shotReceived.add(missileCoord);
		}
	}
	

	public boolean isDestroyed() {
		return this.coordShip.size()==this.shotReceived.size();
	}

	public String getShipCategory() {
		return ShipCategory;
	}

	public void setShipCategory(String shipCategory) {
		ShipCategory = shipCategory;
	}

}

import java.util.*;

public class Ship {

	private String ShipCategory;
	private ArrayList<String> coordShip = new ArrayList<String>();

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
		int startCoordLine = startCoord.charAt(1) - '0';
		if (startCoord.length() == 3) {
			startCoordLine = (startCoord.charAt(1) - '0') * 10 + startCoord.charAt(2) - '0';
		}
		int endCoordColl = (int) endCoord.charAt(0) - 65;
		int endCoordLine = endCoord.charAt(1) - '0';
		if (endCoord.length() == 3) {
			endCoordLine = (endCoord.charAt(1) - '0') * 10 + endCoord.charAt(2) - '0';
		}

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

	public boolean isDestroyed() {
		int i = 0;
		boolean destroyed = true;
		while (i < this.coordShip.size() || destroyed) {
			if (this.coordShip.get(i).contains("-hit")) {
				destroyed = false;
			}
		}
		return destroyed;
	}

	public String getShipCategory() {
		return ShipCategory;
	}

	public void setShipCategory(String shipCategory) {
		ShipCategory = shipCategory;
	}

}
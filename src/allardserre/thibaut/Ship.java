package allardserre.thibaut;

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
	
	public Ship() {
		
	}
	
	public Ship(String startCoord, String endCoord, int size, String category) {
		this.ShipCategory=category;
		int startCoordColl = (int) startCoord.charAt(0) - 65;
		int startCoordLine = Tools.stringToInt(startCoord);
		int endCoordColl = (int) endCoord.charAt(0) - 65;
		int endCoordLine = Tools.stringToInt(endCoord);
		

		this.coordShip = Tools.checkSpacesArray(startCoordLine, startCoordColl, endCoordLine, endCoordColl, size,
				this.coordShip);
	}

	public boolean isDestroyed() {
		//besoin d'ajouter d'autres verifs pour le mode IA vs IA en facile
		boolean find=false;
		String shot="";
		int i=0;
		ArrayList<String> checkDestroyed = new ArrayList<String>();
		for(String coord : this.coordShip) {
			i=0;
			find=false;
			while(i<this.shotReceived.size()&&!find) {
				shot=shotReceived.get(i);
				if (shot.equals(coord)) {
					checkDestroyed.add(coord);
					find=true;
				}
				i++;
			}
		}
		return this.coordShip.size()==checkDestroyed.size();
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
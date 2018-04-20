
public class Ship {

	private String startCoord;
	private String endCoord;
	private String ShipCategory;
	
	public Ship(String startCoord, String endCoord) {
		setStartCoord(startCoord);
		setEndCoord(endCoord);
	}
	
	/*public boolean isHit(String missileCoord) {
		int missileCoordColl =
		int missileCoordLine =
	}*/
	
	
	public String getStartCoord() {
		return startCoord;
	}
	public void setStartCoord(String startCoord) {
		this.startCoord = startCoord;
	}

	public String getEndCoord() {
		return endCoord;
	}
	public void setEndCoord(String endCoord) {
		this.endCoord = endCoord;
	}

	public String getShipCategory() {
		return ShipCategory;
	}

	public void setShipCategory(String shipCategory) {
		ShipCategory = shipCategory;
	}



}
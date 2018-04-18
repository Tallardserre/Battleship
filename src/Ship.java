
public class Ship {

	private String startCoord;
	private String endCoord;
	
	public Ship(String startCoord, String endCoord) {
		setStartCoord(startCoord);
		setEndCoord(endCoord);
	}
	
	public boolean isHit(String missileCoord) {
		//si le bateau est touche ou non
	}
	
	public boolean checkCoord(String lenght, String startCoord, String endCoord) {
		//verifie si les coordonnees correspondent a une taille de bateau valide
	}
	
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


}



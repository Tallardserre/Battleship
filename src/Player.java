
import java.util.ArrayList;

public class Player {
	private int number;
	private ArrayList<Ship> playerShips;

	public Player(int number){
		setNumber(number);
	}
	
	public void addShip(Ship s){
		playerShips.add(s);
	}
	
	public void removeShip(Ship s){
		playerShips.remove(s);
	}
	
	
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
}

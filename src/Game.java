import java.util.Scanner;
public class Game {

	public void generateShip(int playerNumber, int lenghtShip){
		Scanner sc1 = new Scanner(System.in);
		Scanner sc2 = new Scanner(System.in);
		String str1="",str2="",str3="",str4="";
		System.out.println("Give the first coordinates of your Carrier (size : 5)");
		String coord1 = sc1.nextLine();
		StringBuffer sbcol = new StringBuffer(coord1);
		StringBuffer sbline = new StringBuffer(coord1);
		sbcol.delete(0, 1);
		sbline.delete(1, 2);
		System.out.println("Give the last coordinates of your Carrier choose between "+str1+str2+str3+str4+":");
		String coord2 = sc2.nextLine();
		new Ship(coord1, coord2);//creation des bateaux pour les joueurs en demandant les coordonnees voulues pour le bateau.
	}
}

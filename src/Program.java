import java.util.Scanner;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc1 = new Scanner(System.in);
		Scanner sc2 = new Scanner(System.in);
		//BString str1="",str2="",str3="",str4="";
		System.out.println("Give the first coordinates of your Carrier (size : 5)");
		String coord1 = sc1.nextLine();
		//System.out.println("Give the last coordinates of your Carrier choose between "+str1+str2+str3+str4+":");
		//String coord2 = sc2.nextLine();
		//new Ship(coord1, coord2);//creation des bateaux pour les joueurs en demandant les coordonnees voulues pour le bateau.
		int coordColl=(int)coord1.charAt(0)-65;
		int coordLine = coord1.charAt(1) - '0';

		System.out.println(coordColl+" "+coordLine);


		sc1.close();
		sc2.close();
	}

}

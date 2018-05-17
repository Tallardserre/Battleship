package fr.battleship;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import allardserre.thibaut.AI;
import allardserre.thibaut.Tools;


public class TestIA {
	
	public static void main(String[] args) {
		AI p1,p2;
		int X1=0;
		int Y1=0;
		int X2=0;
		int Y2=0;
		int X3=0;
		int Y3=0;
		int levelIA1Number=0;
		int levelIA2Number=0;
		for(int j=0;j<300;j++) {
			if(j<100) {
				levelIA1Number=1;
				levelIA2Number=2;
			}
			else if (j<200) {
				levelIA1Number=1;
				levelIA2Number=3;
			}
			else {
				levelIA1Number=2;
				levelIA2Number=3;
			}
			p1=new AI("IA number 1",levelIA1Number);
			p2=new AI("IA number 2",levelIA2Number);
		
		
		
		//*************************GAME START *******************************
			String coordShot="";
			@SuppressWarnings("unused")
			String hitShip="";
			Boolean isDestroyed=false;
	
			while(!Tools.endGame(p1,p2)){
				//JOUEUR qui joue.
				coordShot=((AI) p1).generateShotCoord(((AI) p1).getLevel());
	
				if (p2.shipHit(coordShot)) {
					if(Tools.isCarrierHere(coordShot,p2)) {
						p2.getCarrier().getShotReceived().add(coordShot);
						hitShip=p2.getCarrier().getShipCategory();
						isDestroyed=p2.getCarrier().isDestroyed();
					}
					if(Tools.isBattleshipHere(coordShot,p2)) {
						p2.getBattleship().getShotReceived().add(coordShot);
						hitShip=p2.getBattleship().getShipCategory();
						isDestroyed=p2.getBattleship().isDestroyed();
		
					}
					if(Tools.isCruiserHere(coordShot,p2)) {
						p2.getCruiser().getShotReceived().add(coordShot);
						hitShip=p2.getCruiser().getShipCategory();
						isDestroyed=p2.getCruiser().isDestroyed();
					}
					if(Tools.isSubmarineHere(coordShot,p2)) {
						p2.getSubmarine().getShotReceived().add(coordShot);
						hitShip=p2.getSubmarine().getShipCategory();
						isDestroyed=p2.getSubmarine().isDestroyed();
					}
					if(Tools.isDestroyerHere(coordShot,p2)) {
						p2.getDestroyer().getShotReceived().add(coordShot);
						hitShip=p2.getDestroyer().getShipCategory();
						isDestroyed=p2.getDestroyer().isDestroyed();
					}
					if(p1.getLevel()==3) {
						ArrayList<String> shotArray = new ArrayList<String>();
						shotArray.add(coordShot);
						shotArray.add(Boolean.toString(isDestroyed));
						p1.getShotArray().add(shotArray);			
						p1.setLastHit(coordShot);
					}
					
				}
				p1.getShotFired().add(coordShot);
				
				if (!Tools.endGame(p1,p2)) {
	
					//IA qui joue.
					coordShot=((AI) p2).generateShotCoord(((AI) p2).getLevel());
					
					if (p1.shipHit(coordShot)) {
						if(Tools.isCarrierHere(coordShot,p1)) {
							p1.getCarrier().getShotReceived().add(coordShot);
							hitShip=p1.getCarrier().getShipCategory();
							isDestroyed=p1.getCarrier().isDestroyed();
						}
						if(Tools.isBattleshipHere(coordShot,p1)) {
							p1.getBattleship().getShotReceived().add(coordShot);
							hitShip=p1.getBattleship().getShipCategory();
							isDestroyed=p1.getBattleship().isDestroyed();
			
						}
						if(Tools.isCruiserHere(coordShot,p1)) {
							p1.getCruiser().getShotReceived().add(coordShot);
							hitShip=p1.getCruiser().getShipCategory();
							isDestroyed=p1.getCruiser().isDestroyed();
						}
						if(Tools.isSubmarineHere(coordShot,p1)) {
							p1.getSubmarine().getShotReceived().add(coordShot);
							hitShip=p1.getSubmarine().getShipCategory();
							isDestroyed=p1.getSubmarine().isDestroyed();
						}
						if(Tools.isDestroyerHere(coordShot,p1)) {
							p1.getDestroyer().getShotReceived().add(coordShot);
							hitShip=p1.getDestroyer().getShipCategory();
							isDestroyed=p1.getDestroyer().isDestroyed();
						}						
						if(p2.getLevel()==3) {
							ArrayList<String> shotArray = new ArrayList<String>();
							shotArray.add(coordShot);
							shotArray.add(Boolean.toString(isDestroyed));
							p2.getShotArray().add(shotArray);			
							p2.setLastHit(coordShot);
						}
						
					}
	
					p2.getShotFired().add(coordShot);
				}
			}
			if (p1.shipsAllDestroyed()) {
				if(j<100) {
					Y1=Y1+1;
				}
				else if (j<200) {
					Y2=Y2+1;
				}
				else {
					Y3=Y3+1;
				}
			}
			else {
				if(j<100) {
					X1=X1+1;
				}
				else if (j<200) {
					X2=X2+1;
	
				}
				else {
					X3=X3+1;
	
				}
			}
		}
		String str="AI NAME; score; AI NAME2; score2";
		String str2="AI Level Beginner; "+X1+"; AI Level Medium; "+Y1;
		String str3="AI Level Beginner; "+X2+"; AI Level Hard; "+Y2;
		String str4="AI Level Medium; "+X3+"; AI Level Hard; "+Y3;
		
		FileWriter writer = null;
		try {
			writer = new FileWriter("test.csv");
			writer.write(str);
			writer.write("\n");
			writer.write(str2);
			writer.write("\n");
			writer.write(str3);
			writer.write("\n");
			writer.write(str4);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				writer.flush();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println(str);
		System.out.println(str2);
		System.out.println(str3);
		System.out.println(str4);
	}
}

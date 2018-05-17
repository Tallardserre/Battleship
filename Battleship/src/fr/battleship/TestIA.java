package fr.battleship;

import java.util.ArrayList;

import allardserre.thibaut.AI;
import allardserre.thibaut.Tools;

public class TestIA {
	
	public static void main(String[] args) {
		AI p1,p2;
		String coordShot
		for(int i=0;i<300;i++){
			if(i<100){
				p1 = new AI("AI1",1);
				p2 = new AI("AI2",2);
			}
			else if (i>99&&i<200){
				p1 = new AI("AI1",1);
				p2 = new AI("AI2",3);
			}
			else {
				p1 = new AI("AI1",2);
				p2 = new AI("AI2",3);
			}
			
			while (!Tools.endGame(p1, p2)){
				coordShot=p1.generateShotCoord(p1.getLevel());
				p1.shoot(coordShot);
				if(p1.getLevel()==3){
					ArrayList<String> shotArray = new ArrayList<String>();
					shotArray.add(coordShot);
					shotArray.add(Boolean.toString(isDestroyed));
					((AI) p1).getShotArray().add(shotArray);			
					((AI) p1).setLastHit(coordShot);
				}
			}
		}
	}
	
}

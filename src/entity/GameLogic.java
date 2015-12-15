package entity;

import java.awt.event.KeyEvent;

import character.Character;
import character.Kurosaki;
import character.Pikachu;
import character.Playable;
import character.Reborn;
import input.InputUtility;
import render.IRenderable;
import render.Login;
import render.RenderableHolder;
import render.Resource;
import render.Setting;

public class GameLogic {
	public static Playable[] character;
	private Time t;
	static{
		character=new Playable[2];
	}
	
	public GameLogic(int player1Choose,int  player2Choose){	

		RenderableHolder.getInstance().getRenderableList().clear();
		//player[]
		character[0] = (Playable) Login.player[0].getCharacter()[player1Choose];
		RenderableHolder.getInstance().add(character[0]);
	
		character[1] = (Playable) Login.player[1].getCharacter()[player2Choose];
		RenderableHolder.getInstance().add(character[1]);
	
		StatusBar sb = new StatusBar((Character)character[0], (Character)character[1]);
		RenderableHolder.getInstance().add(sb);

		t = new Time();
		RenderableHolder.getInstance().add(t);
	}
	
	public void logicUpdate(){
		if(InputUtility.getKeyPressedTriggered(KeyEvent.VK_ESCAPE)){
			InputUtility.updateInputState();
			Time.isStop = !Time.isStop;
		}
		
		if(Time.isStop) return;
		
		if(InputUtility.getKeyPressed(Setting.key[1])){
			if(!((Character) character[0]).isLose() && Time.isPlay){
				((Character) character[0]).run(true);
//				Resource.run.play();
			}
		}else if(InputUtility.getKeyPressed(Setting.key[0])){
			if(!((Character) character[0]).isLose() && Time.isPlay){
				((Character) character[0]).run(false);
//				Resource.run.play();
			}
		}else if(InputUtility.getKeyPressed(Setting.key[3])){
			if(!((Character) character[0]).isLose() && Time.isPlay){
				((Character) character[0]).attack((Character)(character[1]));
				if((Character)character[0] instanceof Reborn) Resource.rebornGun.play();
				else Resource.fencing.play();
			}
		}else if(InputUtility.getKeyPressed(Setting.key[4])){
			if(!((Character) character[0]).isLose() && Time.isPlay){
				((Character) character[0]).shoot(((Character) character[1]));
				if((Character)character[0] instanceof Kurosaki) Resource.fencing.play();
				else if((Character)character[0] instanceof Reborn) Resource.rebornBomb.play();
				else if((Character)character[0] instanceof Pikachu) Resource.pikaShoot.play();
			}
		}else if(InputUtility.getKeyPressed(Setting.key[5])){
			if(!((Character) character[0]).isLose() && Time.isPlay){
				character[0].superAttack();
				if((Character)character[0] instanceof Pikachu) Resource.pikachu2.play();
			}
		}
		
		if(InputUtility.getKeyPressed(Setting.key[2])){
			if(!((Character) character[0]).isLose() && Time.isPlay){
				((Character) character[0]).jump();
				if((Character)character[0] instanceof Pikachu)Resource.pikachu1.play();
				else Resource.jump.play();
			}
		}
		
		
		if(InputUtility.getKeyPressed(Setting.key[7])){
			if(!((Character) character[1]).isLose()&& Time.isPlay){
				((Character) character[1]).run(true);
//				Resource.run.play();
			}
		}else if(InputUtility.getKeyPressed(Setting.key[6])){
			if(!((Character) character[1]).isLose()&& Time.isPlay){
				((Character) character[1]).run(false);
//				Resource.run.play();
			}
		}else if(InputUtility.getKeyPressed(Setting.key[9])){
			if(!((Character) character[1]).isLose()&& Time.isPlay){
				((Character)character[1]).attack((Character) character[0]);
				if((Character)character[1] instanceof Reborn) Resource.rebornGun.play();
				else Resource.fencing.play();
			}
		}else if(InputUtility.getKeyPressed(Setting.key[10])){
			if(!((Character) character[1]).isLose()&& Time.isPlay){
				((Character) character[1]).shoot(((Character) character[0]));
				if((Character)character[1] instanceof Kurosaki) Resource.fencing.play();
				else if((Character)character[1] instanceof Reborn) Resource.rebornBomb.play();
				else if((Character)character[1] instanceof Pikachu) Resource.pikaShoot.play();
			}
		}else if(InputUtility.getKeyPressed(Setting.key[11])){
			if(!((Character) character[1]).isLose()&& Time.isPlay){
				character[1].superAttack();
			}
		}
		
		if(InputUtility.getKeyPressed(Setting.key[8])){
			if(!((Character) character[1]).isLose()&& Time.isPlay){
				((Character) character[1]).jump();
				if((Character)character[1] instanceof Pikachu)Resource.pikachu1.play();
				else Resource.jump.play();
			}
		}
		//isPlay = false;
		synchronized (RenderableHolder.getInstance()) {
			for(IRenderable entity : RenderableHolder.getInstance().getRenderableList()){
				if(entity.isVisible()){
					if(!Time.isPlay){
						if(entity instanceof Time)entity.update();
						if(entity instanceof Character && ((Character)entity).isLose())entity.update();
					}else{
						entity.update();
					}
				}
			}
		}
		
		
	}
	
	public static int getWinner(){
		if (((Character) character[0]).isLose() && ((Character) character[1]).isLose()) return 0;
		if(((Character) character[0]).isLose()) return 2;
		if(((Character) character[1]).isLose()) return 1;
		
		if(((Character) character[0]).getHealthPoint()>((Character) character[1]).getHealthPoint()) return 1;
		if(((Character) character[1]).getHealthPoint()>((Character) character[0]).getHealthPoint()) return 2;
		return 0;
	}
}

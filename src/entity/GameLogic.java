package entity;

import java.awt.event.KeyEvent;

import character.Character;
import character.Kurosaki;
import character.Pikachu;
import character.Playable;
import character.Reborn;
import input.InputUtility;
import render.IRenderable;
import render.RenderableHolder;
import render.Resource;

public class GameLogic {
	protected Player player1;
	protected Player player2;
	protected Playable character1;
	protected Playable character2;
	
	public GameLogic(){	
		player1 = new Player(KeyEvent.VK_LEFT,KeyEvent.VK_RIGHT,KeyEvent.VK_UP);
		character1 = (Playable) player1.getCharacter()[5];
		RenderableHolder.getInstance().add(character1);
	
		player2 = new Player(KeyEvent.VK_A,KeyEvent.VK_D,KeyEvent.VK_W);
		character2 = (Playable) player2.getCharacter()[3];
		RenderableHolder.getInstance().add(character2);
	
		StatusBar sb = new StatusBar((Character)character1, (Character)character2);
		RenderableHolder.getInstance().add(sb);

	}
	
	public void logicUpdate(){
		if(InputUtility.getKeyPressed(KeyEvent.VK_RIGHT)){
			if(!((Character) character1).isLose()){
				((Character) character1).run(true);
//				Resource.run.play();
			}
			else return;
		}else if(InputUtility.getKeyPressed(KeyEvent.VK_LEFT)){
			if(!((Character) character1).isLose()){
				((Character) character1).run(false);
//				Resource.run.play();
			}
			else return;
		}else if(InputUtility.getKeyPressed(KeyEvent.VK_DOWN)){
			if(!((Character) character1).isLose()){
				((Character) character1).attack((Character)(character2));
				if((Character)character1 instanceof Reborn) Resource.rebornGun.play();
				else Resource.fencing.play();
			}
			else return;
		}else if(InputUtility.getKeyPressed(KeyEvent.VK_ENTER)){
			if(!((Character) character1).isLose()){
				((Character) character1).shoot(((Character) character2));
				if((Character)character1 instanceof Kurosaki) Resource.fencing.play();
				else if((Character)character1 instanceof Reborn) Resource.rebornBomb.play();
				else if((Character)character1 instanceof Pikachu) Resource.pikaShoot.play();
			}
				else return;
		}else if(InputUtility.getKeyPressed(KeyEvent.VK_BACK_SLASH)){
			if(!character1.isLose()){
				character1.superAttack();
				if((Character)character1 instanceof Pikachu) Resource.pikachu2.play();
			}
				else return;
		}
		
		if(InputUtility.getKeyPressed(KeyEvent.VK_UP)){
			if(!((Character) character1).isLose()){
				((Character) character1).jump();
				if((Character)character1 instanceof Pikachu)Resource.pikachu1.play();
				else Resource.jump.play();
			}
			else return;
		}
		
		
		if(InputUtility.getKeyPressed(KeyEvent.VK_D)){
			if(!((Character) character2).isLose()){
				((Character) character2).run(true);
//				Resource.run.play();
			}
			else return;
		}else if(InputUtility.getKeyPressed(KeyEvent.VK_A)){
			if(!((Character) character2).isLose()){
				((Character) character2).run(false);
//				Resource.run.play();
			}
			else return;
		}else if(InputUtility.getKeyPressed(KeyEvent.VK_F)){
			if(!((Character) character2).isLose()){
				((Character)character2).attack((Character) character1);
				if((Character)character2 instanceof Reborn) Resource.rebornGun.play();
				else Resource.fencing.play();
			}
			else return;
		}else if(InputUtility.getKeyPressed(KeyEvent.VK_X)){
			if(!((Character) character2).isLose()){
				((Character) character2).shoot(((Character) character1));
				if((Character)character2 instanceof Kurosaki) Resource.fencing.play();
				else if((Character)character2 instanceof Reborn) Resource.rebornBomb.play();
				else if((Character)character2 instanceof Pikachu) Resource.pikaShoot.play();
			}
				else return;
		}else if(InputUtility.getKeyPressed(KeyEvent.VK_Z)){
			if(!character2.isLose())
				character2.superAttack();
				else return;
		}
		
		if(InputUtility.getKeyPressed(KeyEvent.VK_W)){
			if(!((Character) character2).isLose()){
				((Character) character2).jump();
				if((Character)character2 instanceof Pikachu)Resource.pikachu1.play();
				else Resource.jump.play();
			}
			else return;
		}
		
		synchronized (RenderableHolder.getInstance().getRenderableList()) {
			for(IRenderable entity : RenderableHolder.getInstance().getRenderableList()){
				if(entity.isVisible()){
					entity.update();
				}
			}
		}
		
	}
}

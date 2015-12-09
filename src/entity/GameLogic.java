package entity;

import java.awt.event.KeyEvent;

import character.Character;
import character.Playable;
import input.InputUtility;
import render.GameScreen;
import render.IRenderable;
import render.RenderableHolder;

public class GameLogic {
	protected Player player1;
	protected Player player2;
	protected Playable character1;
	protected Playable character2;
	
	public GameLogic(){
//		Background bg = new Background();
//		RenderableHolder.getInstance().add(bg);
		
		player1 = new Player(KeyEvent.VK_LEFT,KeyEvent.VK_RIGHT,KeyEvent.VK_UP);
		character1 = (Playable) player1.getCharacter()[0];
		RenderableHolder.getInstance().add(character1);
//		
		player2 = new Player(KeyEvent.VK_A,KeyEvent.VK_D,KeyEvent.VK_W);
		character2 = (Playable) player2.getCharacter()[2];
		RenderableHolder.getInstance().add(character2);
//		
		StatusBar sb = new StatusBar((Character)character1, (Character)character2);
		RenderableHolder.getInstance().add(sb);

	}
	
	public void logicUpdate(){
		if(InputUtility.getKeyPressed(KeyEvent.VK_RIGHT)){
			if(!((Character) character1).isLose())
			 ((Character) character1).run(true);
			else return;
		}else if(InputUtility.getKeyPressed(KeyEvent.VK_LEFT)){
			if(!((Character) character1).isLose())
			((Character) character1).run(false);
			else return;
		}else if(InputUtility.getKeyPressed(KeyEvent.VK_DOWN)){
			if(!((Character) character1).isLose())
			((Character) character1).attack((Character)(character2));
			else return;
		}else if(InputUtility.getKeyPressed(KeyEvent.VK_ENTER)){
			if(!((Character) character1).isLose())
				((Character) character1).shoot(((Character) character2));
				else return;
		}
		
		if(InputUtility.getKeyPressed(KeyEvent.VK_UP)){
			if(!((Character) character1).isLose())
			((Character) character1).jump();
			else return;
		}
		
		
		if(InputUtility.getKeyPressed(KeyEvent.VK_D)){
			if(!((Character) character2).isLose())
			((Character) character2).run(true);
			else return;
		}else if(InputUtility.getKeyPressed(KeyEvent.VK_A)){
			if(!((Character) character2).isLose())
			((Character) character2).run(false);
			else return;
		}else if(InputUtility.getKeyPressed(KeyEvent.VK_F)){
			if(!((Character) character2).isLose())
			((Character)character2).attack((Character) character1);
			else return;
		}else if(InputUtility.getKeyPressed(KeyEvent.VK_X)){
			if(!((Character) character2).isLose())
				((Character) character2).shoot(((Character) character1));
				else return;
		}
		
		if(InputUtility.getKeyPressed(KeyEvent.VK_W)){
			if(!((Character) character2).isLose())
			((Character) character2).jump();
			else return;
		}
		
		for(IRenderable entity : RenderableHolder.getInstance().getRenderableList()){
			if(entity.isVisible()){
				entity.update();
			}
		}
		
	}
}

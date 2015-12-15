package entity;

import java.awt.event.KeyEvent;

import render.IRenderable;
import character.Kurosaki;
import character.Luffy;
import character.Naruto;
import character.Natsu;
import character.Pikachu;
import character.Reborn;
import logic.Name;

public class Player {
	private int level;
	private String name;
	private IRenderable[] character = new IRenderable[6];
	private int player;
	
	public Player(int player,String name){
		this.player=player;	
		this.name=name;
		level = Name.findName(name);
		
		Pikachu pikachu = new Pikachu(player,0, 0, 0, this);
		character[0] = pikachu;
		Luffy luffy = new Luffy(player,0, 0, 0, this);
		character[1] = luffy;
		Naruto naruto = new Naruto(player,0, 0, 0, this);
		character[2] = naruto;
		Reborn reborn = new Reborn(player,0, 0, 0, this);
		character[3] = reborn;
		Natsu natsu = new Natsu(player,0, 0, 0, this);
		character[4] = natsu;
		Kurosaki kuro = new Kurosaki(player,0, 0, 0, this);
		character[5] = kuro;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public int getLevel() {
		return level;
	}

	public String getName() {
		return name;
	}

	public IRenderable[] getCharacter() {
		return character;
	}
	
}

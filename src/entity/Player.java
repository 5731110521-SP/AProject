package entity;

import java.awt.event.KeyEvent;

import render.IRenderable;
import character.Kurosaki;
import character.Luffy;
import character.Naruto;
import character.Natsu;
import character.Pikachu;
import character.Reborn;

public class Player {
	

	private int level;
	private String name;
	private int left,right,up;
	private IRenderable[] character = new IRenderable[6];
	private int player;
	
	public Player(int player,int l,int r, int u){
		left = l;
		right = r;
		up = u;
		this.player=player;
		
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
	
	public IRenderable[] getCharacter() {
		return character;
	}
	
	public int getLeft() {
		return left;
	}

	public void setLeft(int left) {
		this.left = left;
	}

	public int getRight() {
		return right;
	}

	public void setRight(int right) {
		this.right = right;
	}

	public int getUp() {
		return up;
	}

	public void setUp(int up) {
		this.up = up;
	}

}

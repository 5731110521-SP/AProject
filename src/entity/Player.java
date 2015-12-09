package entity;

import java.awt.event.KeyEvent;

import render.IRenderable;
import character.Luffy;
import character.Naruto;
import character.Pikachu;

public class Player {
	

	private int level;
	private String name;
	private int left,right,up;
	private IRenderable[] character = new IRenderable[3];
	
	public Player(int l,int r, int u){
		left = l;
		right = r;
		up = u;
		Pikachu p = new Pikachu(0, 0, 0, this);
		character[0] = p;
		Luffy lf = new Luffy(0, 0, 0, this);
		character[1] = lf;
		Naruto na = new Naruto(0, 0, 0, this);
		character[2] = na;
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

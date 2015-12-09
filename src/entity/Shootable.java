package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import character.Character;
import character.Luffy;
import character.Naruto;
import character.Pikachu;
import render.GameScreen;
import render.IRenderable;
import render.Resource;

public class Shootable implements IRenderable{
	private int power;
	private Character shooter;
	private character.Character enemy;
	private BufferedImage shootOject;
	private int x,y,xp;
	private int width,height;
	private boolean isDestroy,isRight;

	public Shootable(character.Character ch) {
		shooter = ch;
		xp=0;
		isDestroy = false;
		enemy = ch.getEnemy();
		isRight=ch.isRight();
		power = 10;
		if(ch instanceof Pikachu){
			shootOject=null;
			width=20;
			height=20;
		}else if(ch instanceof Luffy){
			shootOject = Resource.luffy.getSubimage(214, 631, 50, 18);
			width=shootOject.getWidth();
			height=shootOject.getHeight();
		}else if(ch instanceof Naruto){
			shootOject = Resource.narutoBall.getSubimage(0, 0, 24, 26);
			width=shootOject.getWidth();
			height=shootOject.getHeight();
		}
		
		if(!isRight && shootOject!=null) transform();
		
		x = shooter.getX();
		y = shooter.getY()+shooter.getHeight()/2-height/2;
		
	}
	
	@Override
	public void draw(Graphics2D g) {
		if(shootOject==null){
			g.setColor(Color.YELLOW);
			g.fillOval(x, y, width, height);
		}else{
			g.drawImage(shootOject, x, y, null);
		}
		xp=0;
	}
	
	public void transform() {
		AffineTransform at = new AffineTransform();
		if(!isRight){
			at = AffineTransform.getScaleInstance(-1, 1);
			at.translate(-shootOject.getWidth(null), 0);
			AffineTransformOp op = new AffineTransformOp(at, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
			shootOject = op.filter(shootOject, null);
			if(width>25) xp=width-25;
		}
	}

	@Override
	public boolean isVisible() {
		return !isDestroy;
	}

	@Override
	public int getZ() {
		return Integer.MAX_VALUE;
	}
	
	public boolean collideWith(Character ch){
		if(Math.abs((x+width/2.0)-(ch.getX()-ch.getXp()+ch.getWidth()/2.0)) <= width/2.0+ch.getWidth()/2.0 
				&& Math.abs((y+height/2.0)-(ch.getY()-ch.getYp()+ch.getHeight()/2.0)) <= height/2.0+ch.getHeight()/2.0){
			return true;
		}
		return false;
	}
	
	@Override
	public void update() {
		if(isRight){
			x += width;
		}else{
			x-=width;
		}
		if(collideWith(enemy)){
			enemy.attacked(power);
			isDestroy = true;
		}else if(x >= GameScreen.width){
			isDestroy =  true;
		}
	}

	@Override
	public boolean getFlashing() {
		return false;
	}

}

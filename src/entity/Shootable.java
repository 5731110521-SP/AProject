package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import character.Character;
import character.Kurosaki;
import character.Luffy;
import character.Naruto;
import character.Natsu;
import character.Pikachu;
import character.Reborn;
import render.GameScreen;
import render.IRenderable;
import render.Resource;

public class Shootable implements IRenderable {
	private int power;
	private Character shooter;
	private character.Character enemy;
	private BufferedImage shootOject;
	private int x, y;
	private int width, height;
	private int speed;
	private boolean isDestroy, isRight, isVisible;

	public Shootable(character.Character ch) {
		shooter = ch;
		// xp=0;
		isDestroy = false;
		isVisible = true;
		enemy = ch.getEnemy();
		isRight = ch.isRight();
		power = 10;
		if (ch instanceof Pikachu) {
			shootOject = null;
			width = 20;
			height = 20;
			speed = width;

		} else if (ch instanceof Luffy) {
			shootOject = Resource.luffy.getSubimage(214, 631, 50, 18);
			width = shootOject.getWidth();
			height = shootOject.getHeight();
			speed = width;
		} else if (ch instanceof Naruto) {
			shootOject = Resource.narutoBall.getSubimage(0, 0, 24, 26);
			width = shootOject.getWidth();
			height = shootOject.getHeight();
			speed = width;
		} else if (ch instanceof Reborn) {
			shootOject = Resource.reborn.getSubimage(685, 1827, 67, 29);
			width = shootOject.getWidth();
			height = shootOject.getHeight();
			speed = width;
		} else if (ch instanceof Natsu) {
			shootOject = Resource.natsu.getSubimage(271, 615, 30, 25);
			width = shootOject.getWidth();
			height = shootOject.getHeight();
			speed = width;
		} else if (ch instanceof Kurosaki) {
			shootOject = Resource.bleach.getSubimage(671, 1058, 81, 78);
			width = shootOject.getWidth();
			height = shootOject.getHeight();
			speed = width;
		}

		x = shooter.getX() - shooter.getXp() + (shooter.getCharacter().getWidth() / 2);
		y = shooter.getY() + shooter.getHeight() / 2 - height / 2;

		if (!isRight && shootOject != null)
			transform();

	}

	@Override
	public void draw(Graphics2D g) {
		if (shootOject == null) {
			g.setColor(Color.YELLOW);
			g.fillOval(x, y, width, height);
		} else {
			g.drawImage(shootOject, x, y, null);
		}
		// xp=0;
	}

	public void transform() {
		AffineTransform at = new AffineTransform();
		if (!isRight) {
			at = AffineTransform.getScaleInstance(-1, 1);
			at.translate(-shootOject.getWidth(null), 0);
			AffineTransformOp op = new AffineTransformOp(at, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
			shootOject = op.filter(shootOject, null);
			x = x - width;
			// if(width>25) xp=width-25;
			// if(shootOject.getWidth()>width) xp = shootOject.getWidth()-53;
		}
	}

	@Override
	public boolean isVisible() {
		return isVisible;
	}

	@Override
	public int getZ() {
		return Integer.MAX_VALUE;
	}

	public boolean collideWith(Character ch) {
		// if(Math.abs((x+width/2.0)-(ch.getX()-ch.getXp()+ch.getWidth()/2.0))
		// <= width/2.0+ch.getWidth()/2.0
		// && Math.abs((y+height/2.0)-(ch.getY()-ch.getYp()+ch.getHeight()/2.0))
		// <= height/2.0+ch.getHeight()/2.0){
		// return true;
		// }
		if (Math.abs(
				(x + (width / 2.0)) - (ch.getX() - ch.getXp() + (ch.getCharacter().getWidth() / 2.0))) <= width / 2.0
						+ ch.getCharacter().getWidth() / 2.0
				&& Math.abs((y + height / 2.0) - (ch.getY() + ch.getCharacter().getHeight() / 2.0)) <= height / 2.0
						+ ch.getCharacter().getHeight() / 2.0) {
			return true;
		}
		// if(x>=(ch.getX()-ch.getXp()) && x<=(ch.getX()+ch.getWidth()) &&
		// y>=(ch.getY()-ch.getYp()) && y<=(ch.getY()+ch.getHeight())){
		// return true;
		// }
		return false;
	}

	@Override
	public void update() {
		if (isRight) {
			x += speed;
		} else {
			x -= speed;
		}
		if (isDestroy) {
			isVisible = false;
			return;
		}
		if (collideWith(enemy)) {
			enemy.attacked(power);
			isDestroy = true;
			shooter.setPowerCount(shooter.getPowerCount() + 1);
		} else if (x >= GameScreen.width) {
			isDestroy = true;
		}
	}

	@Override
	public boolean getFlashing() {
		return false;
	}

}

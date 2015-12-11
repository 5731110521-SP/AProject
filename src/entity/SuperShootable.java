package entity;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import character.Character;
import character.Pikachu;
import render.GameScreen;
import render.IRenderable;
import render.RenderableHolder;
import render.Resource;

public class SuperShootable implements IRenderable {
	private int power;
	private Character shooter;
	private character.Character enemy;
	private BufferedImage[] shootPic;
	private BufferedImage image;
	private int x, y, width, height;
	private int count;
	private int speed;
	private boolean isDestroy, isRight, isVisible,isDoubleAttack;

	public SuperShootable(Character ch) {
		power = 20;
		shooter = ch;
		enemy = ch.getEnemy();
		isVisible = true;
		isDestroy = false;
		isRight = true;
		count = 0;
		isDoubleAttack=false;
		
		if (shooter instanceof Pikachu) {
			shootPic = new BufferedImage[3];
			shootPic[0] = Resource.superAttack[0].getSubimage(652, 12, 33, 221);
			shootPic[1] = Resource.superAttack[0].getSubimage(736, 16, 45, 216);
			shootPic[2] = Resource.superAttack[0].getSubimage(714, 249, 64, 211);
			 width=33;
			 height=221;
			 power=10;
		}
		
		x = enemy.getX() - enemy.getXp() + (enemy.getWidth() / 2);
		y = enemy.getY() + enemy.getHeight()/2 +height / 2;

		transform();

	}

	@Override
	public void draw(Graphics2D g) {
		g.drawImage(image, x, y, null);
		x += image.getWidth() / 2;
		if (shooter instanceof Pikachu) {
			y += image.getHeight() + 100 -(enemy.getHeight()/2 -height / 2);
		}
	}

	@Override
	public boolean isVisible() {
		return isVisible;
	}

	@Override
	public int getZ() {
		return 0;
	}
	
	public boolean collideWith(Character ch) {
		if (Math.abs(
				(x + (width / 2.0)) - (ch.getX() - ch.getXp() + (ch.getCharacter().getWidth() / 2.0))) <= width / 2.0
						+ ch.getCharacter().getWidth() / 2.0
				&& Math.abs((y + height / 2.0) - (ch.getY() + ch.getCharacter().getHeight() / 2.0)) <= height / 2.0
						+ ch.getCharacter().getHeight() / 2.0) {
			return true;
		}
		return false;
	}

	@Override
	public void update() {
		if(isDestroy) isVisible=false;
		if (shooter instanceof Pikachu) {
			if(count<=1){
				image = shootPic[count];
			}else if (count <= 3) {
				image = shootPic[count/2];
			}else {
				image = shootPic[2];
				isDestroy = true;
			}
			y -= image.getHeight() + 80 -(enemy.getHeight()/2 -height / 2);
			x -= image.getWidth() / 2;
			count++;
		}
		
		if (collideWith(enemy)) {
			if(!isDoubleAttack)enemy.attacked(power);
			isDoubleAttack = true;
		}
		
	}

	public void transform() {
		AffineTransform at = new AffineTransform();
		if (!isRight) {
			at = AffineTransform.getScaleInstance(-1, 1);
			at.translate(-image.getWidth(null), 0);
			AffineTransformOp op = new AffineTransformOp(at, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
			image = op.filter(image, null);
			x = x - width;
			// if(width>25) xp=width-25;
			// if(shootOject.getWidth()>width) xp = shootOject.getWidth()-53;
		}
	}

	@Override
	public boolean getFlashing() {
		return false;
	}

}

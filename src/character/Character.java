package character;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import com.sun.org.apache.xml.internal.security.utils.HelperNodeList;

import entity.Player;
import entity.Shootable;
import input.InputUtility;
import render.IRenderable;
import render.RenderableHolder;
import render.Resource;

public abstract class Character implements Playable {

	BufferedImage character;
	public int indexC;
	protected int attackPower;
	protected int defencePower;
	protected int healthPoint;
	protected int powerCount;
	protected boolean lose;
	protected int x, y, xp, yp, width, height;
	protected boolean isAttacked, isVisible;
	protected boolean isRun, isRight, isJump, isAttack, isDoubleAttack, isShoot,isSuperAttack;
	protected boolean flashing;
	protected int flashCounter, flashDurationCounter, counter, countShoot;
	protected Player player;
	protected Character enemy;
	protected int jumpMax = 10;
	protected int count = 1;
	protected int[] countPic = new int[6];

	public Character(int ap, int dp, int hp) {
		attackPower = ap;
		defencePower = dp;
		healthPoint = hp;
		xp=0;
		yp=0;
		countShoot = 0;
		lose = false;
		isAttacked = false;
		isRun = false;
		isJump = false;
		isRight = true;
		isShoot = false;
		flashing = false;
		isVisible = true;
		for (int a : countPic)
			a = 0;
	}

	public void draw(Graphics2D g) {
		yp = character.getHeight() - height;
		g.drawImage(character, x - xp, y - yp, character.getWidth(), character.getHeight(), null);
		xp = 0;
		yp = 0;
	}

	public void transform() {
		if(lose) return;
		AffineTransform at = new AffineTransform();
		if (!isRight) {
			at = AffineTransform.getScaleInstance(-1, 1);
			at.translate(-character.getWidth(null), 0);
			AffineTransformOp op = new AffineTransformOp(at, AffineTransformOp.TYPE_BICUBIC);
			character = op.filter(character, null);
//			if (width > 53)
//				xp = width - 53;
			if(character.getWidth()>width) xp = character.getWidth()-53;
		}
	}

	public void run(boolean isRight) {
		if (isAttack || isShoot || isSuperAttack){
			return;
		}
		isRun = true;
		this.isRight = isRight;
		if (isRight)
			x += 20;
		else
			x -= 20;
		
		if(x<0) x=0;
		else if(x>640-character.getWidth()) x=640-character.getWidth();
	}

	public void jump() {
		if (isJump || isAttack || isShoot || isSuperAttack){
//			System.out.println("re");
			return;
		}
//		System.out.println("in");
		isJump = true;

		new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized (character) {
					while (true) {
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						// System.out.println("while");
						if (count <= jumpMax) {
							y -= 20;
							// System.out.println(count);
							count++;
						} else if (count > jumpMax && count <= jumpMax * 2) {
							y += 20;
							count++;
						} else {
							count = 1;
							// System.out.println("break");
						}

						picJumpUpdate();
						
						if(!isJump) {
							count=1;
							break;
						}
					}
				}
			}
		}).start();
	}

	public void attackUpdate() {
		if ((isAttack) && collideWith(enemy) && !isDoubleAttack) {
			enemy.setAttacked(true);
			enemy.attacked(attackPower);
			isDoubleAttack = true;
			powerCount++;
		}

		if (isAttacked && flashing && flashDurationCounter % 2 == 0) {
			flashing = false;
			flashDurationCounter++;
		} else if (isAttacked && !flashing && flashDurationCounter % 2 == 1) {
			flashing = true;
			flashDurationCounter++;
		}
		if (isAttacked && flashDurationCounter == flashCounter) {
			flashing = false;
			isAttacked = false;
			flashDurationCounter = 0;
		}
	}

	public void hitByEnemy() {
		flashCounter = 5;
		flashDurationCounter = 0;
	}

	public boolean isLose() {
		if (healthPoint <= 0)
			return true;
		else
			return false;
	}

	public void shoot(Character c) {
		if (isAttack || isShoot || isSuperAttack){
			return;
		}
		this.enemy = c;
		if (countShoot >= 5) {
			RenderableHolder.getInstance().add(new Shootable(this));
			countShoot = 0;
			isShoot = true;
		}

	}

	public void superAttack(){
		if (isAttack || isShoot || isSuperAttack){
			return;
		}
		if(powerCount>=4){
			isSuperAttack=true;
			powerCount=0;
		}
	}

	public void attack(Character c) {
		if (isAttack || isShoot || isSuperAttack){
			return;
		}
		isAttack = true;
		// System.out.println(isAttack);
		this.enemy = c;
	}

	public void attacked(int amount) {
		healthPoint -= amount;
		if (healthPoint <= 0)
			healthPoint = 0;
		lose = isLose();
		isAttacked = true;
		flashing = true;
		hitByEnemy();
	}

	public boolean collideWith(Character ch) {
//		if (Math.abs((x - xp + width / 2.0) - (ch.x - ch.xp + ch.width / 2.0)) <= width / 2.0 + ch.width / 2.0 && Math
//				.abs((y - yp + height / 2.0) - (ch.y - ch.yp + ch.height / 2.0)) <= height / 2.0 + ch.height / 2.0) {
//			return true;
//		}
//		if (Math.abs((x - xp + getCharacter().getWidth() / 2.0) - (ch.x - ch.xp + ch.getCharacter().getWidth() / 2.0)) <= getCharacter().getWidth() / 2.0 + ch.getCharacter().getWidth() / 2.0 && Math
//				.abs((y - yp + getCharacter().getHeight() / 2.0) - (ch.y - ch.yp + ch.getCharacter().getHeight() / 2.0)) <= getCharacter().getHeight() / 2.0 + ch.getCharacter().getHeight() / 2.0) {
//			return true;
//		}
//		return false;
//		Rectangle p = new Rectangle(x, y, width/2, height/2);
//		Rectangle m = new Rectangle(ch.x, ch.y, ch.width/2, ch.height/2);
		Rectangle p = new Rectangle(x-xp, y-yp, character.getWidth(), character.getHeight());
		Rectangle m = new Rectangle(ch.x-ch.xp, ch.y-ch.yp, ch.getCharacter().getWidth(), ch.getCharacter().getHeight());
		return p.intersects(m);
	}

	public BufferedImage getCharacter() {
		return character;
	}

	public void update() {
		if (!InputUtility.getKeyPressed(player.getLeft()) && !InputUtility.getKeyPressed(player.getRight())) {
			isRun = false;
		}
		countShoot++;
		
		if(!isJump && !isAttack)picRunUpdate();
		picAttackUpdate();
		picShootUpdate();
		picSuperAttack();
		
		stand();
		picLoseUpdate();
		
		attackUpdate();
		
		transform();
	}

	public abstract void picRunUpdate();

	public abstract void picJumpUpdate();

	public abstract void stand();

	public abstract void picAttackUpdate();

	public abstract void picShootUpdate();

	public abstract void picLoseUpdate();

	public abstract void picSuperAttack();

	public boolean isRight() {
		return isRight;
	}

	public Character getEnemy() {
		return enemy;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getXp() {
		return xp;
	}

	public int getYp() {
		return yp;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setLose(boolean lose) {
		this.lose = lose;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	public boolean isVisible() {
		return isVisible;
	}

	public void setAttacked(boolean isAttacked) {
		this.isAttacked = isAttacked;
	}

	public int getAttackPower() {
		return attackPower;
	}

	public void setAttackPower(int attackPower) {
		this.attackPower = attackPower;
	}

	public int getDefencePower() {
		return defencePower;
	}

	public void setDefencePower(int defencePower) {
		this.defencePower = defencePower;
	}

	public int getHealthPoint() {
		return healthPoint;
	}

	public void setHealthPoint(int healthPoint) {
		this.healthPoint = healthPoint;
	}

	public boolean getFlashing() {
		return flashing;
	}

	public int getPowerCount() {
		return powerCount;
	}

	public void setPowerCount(int powerCount) {
		this.powerCount = powerCount;
	}
	
	public boolean isSuperAttack() {
		return isSuperAttack;
	}

}
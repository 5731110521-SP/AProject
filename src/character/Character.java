package character;

import java.awt.Graphics2D;
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
	protected int maxPower;
	protected boolean lose;
	protected int x, y, width, height;
	protected int xp = 0, yp = 0;
	protected boolean isAttacked, isVisible;
	protected boolean flashing = false;
	protected int flashCounter, flashDurationCounter, counter, countShoot;
	protected boolean isRun, isRight, isJump, isAttack, isDoubleAttack, isShoot;
	protected Player player;
	protected Character enemy;
	protected int jumpMax = 10;
	protected int count = 1;
	protected int[] countPic = new int[6];
	
	public int getPowerCount() {
		return powerCount;
	}

	public Character(int ap, int dp, int hp) {
		attackPower = ap;
		defencePower = dp;
		healthPoint = hp;
		lose = false;
		isAttacked = false;
		isVisible = true;
	}

	public void transform() {
		AffineTransform at = new AffineTransform();
		if (!isRight) {
			at = AffineTransform.getScaleInstance(-1, 1);
			at.translate(-character.getWidth(null), 0);
			AffineTransformOp op = new AffineTransformOp(at, AffineTransformOp.TYPE_BICUBIC);
			character = op.filter(character, null);
			if (width > 53)
				xp = width - 53;
		}
	}

	public void draw(Graphics2D g) {
		yp=character.getHeight()-height;
		g.drawImage(character, x - xp, y - yp, character.getWidth(), character.getHeight(), null);
		xp = 0;
		yp = 0;
	}

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

	public int getMaxPower() {
		return maxPower;
	}

	public void setMaxPower(int maxPower) {
		this.maxPower = maxPower;
	}

	public boolean getFlashing() {
		return flashing;
	}

	public boolean collideWith(Character ch) {
		if (Math.abs((x - xp + width / 2.0) - (ch.x - ch.xp + ch.width / 2.0)) <= width / 2.0 + ch.width / 2.0 && Math
				.abs((y - yp + height / 2.0) - (ch.y - ch.yp + ch.height / 2.0)) <= height / 2.0 + ch.height / 2.0) {
			return true;
		}
		return false;
	}

	public void run(boolean isRight) {
		if (isAttack)
			return;
		isRun = true;
		this.isRight = isRight;
		if (isRight)
			x += 20;
		else
			x -= 20;
	}

	public void jump() {
		// System.out.println(isJump);
		if (isJump) {
			// System.out.println("return");
			return;
		}
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
							isJump = false;
							break;
						}
						
						picJumpUpdate();
						
					}
				}
			}
		}).start();
	}

	public void attackUpdate() {
		if (isAttack && collideWith(enemy) && !isDoubleAttack) {
			enemy.setAttacked(true);
			enemy.attacked(attackPower);
			isDoubleAttack = true;
			powerCount++;
		}

		if (isShoot && collideWith(enemy) && !isDoubleAttack) {
			enemy.setAttacked(true);
			enemy.attacked(maxPower);
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

	public void attack(Character c) {
		isAttack = true;
		// System.out.println(isAttack);
		this.enemy = c;
	}

	public void attacked(int amount) {
		healthPoint -= amount;
		if (healthPoint <= 0)
			healthPoint = 0;
		lose=isLose();
		isAttacked = true;
		flashing = true;
		hitByEnemy();
	}

	public boolean isLose() {
		if (healthPoint <= 0)
			return true;
		else
			return false;
	}

	public void hitByEnemy() {
		flashCounter = 5;
		flashDurationCounter = 0;
	}

	public void shoot(Character c) {
		this.enemy = c;
		if (countShoot >= 10) {
			RenderableHolder.getInstance().add(new Shootable(this));
			countShoot = 0;
			isShoot = true;
		}
		
	}
	
	public void update() {
		if (!InputUtility.getKeyPressed(player.getLeft()) && !InputUtility.getKeyPressed(player.getRight())) {
			isRun = false;
		}

		picRunUpdate();
		picAttackUpdate();
		stand();

		picLoseUpdate();
		picShootUpdate();
		countShoot++;

		transform();
		attackUpdate();

	}
	
	public abstract void picRunUpdate();
	
	public abstract void picJumpUpdate();

	public abstract void stand();

	public abstract void picAttackUpdate();

	public abstract void picShootUpdate();

	public abstract void picLoseUpdate();
	
	public abstract void picSuperAttack();

}
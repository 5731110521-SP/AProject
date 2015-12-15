package character;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import com.sun.org.apache.xml.internal.security.utils.HelperNodeList;

import entity.GameLogic;
import entity.Player;
import entity.Shootable;
import entity.Time;
import input.InputUtility;
import render.GameScreen;
import render.IRenderable;
import render.Login;
import render.RenderableHolder;
import render.Resource;
import render.Setting;

public abstract class Character implements Playable {
	BufferedImage character;
	public int indexC, playeri;
	protected int attackPower, healthPoint, powerCount;
	protected int x, y, xp, yp, width, height;
	protected boolean isRun, isRight, isJump, isDoubleJump, isTripleJump, isAttack, isDoubleAttack, isShoot,
			isSuperAttack, lose, isAttacked, isVisible, flashing;
	protected int flashCounter, flashDurationCounter, countShoot;
	protected Player player;
	protected Character enemy;
	protected int jumpMax = 4;
	protected int[] count = new int[2];
	protected int[] countPic = new int[6];

	public Character(int player, int ap, int hp) {
		attackPower = ap;
		healthPoint = hp;
		xp = 0;
		yp = 0;
		countShoot = 0;
		lose = false;
		isAttacked = false;
		isAttack = false;
		isDoubleAttack = false;
		isRun = false;
		isJump = false;
		count[0] = 1;
		count[1] = 1;
		isDoubleJump = false;
		isTripleJump = false;
		isRight = true;
		isShoot = false;
		flashing = false;
		isVisible = true;
		playeri = player;
		isSuperAttack = false;
		if (player == 1) {
			x = 100;
		} else {
			x = 500;
			isRight = false;
		}
		for (int a : countPic)
			a = 0;
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

	public void setY(int y) {
		this.y = y - height;
	}

	public int getY() {
		return y;
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

	public int getHealthPoint() {
		return healthPoint;
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

	public boolean isJump() {
		return isJump;
	}

	public BufferedImage getCharacter() {
		return character;
	}

	public void transform() {
		if (lose)
			return;
		AffineTransform at = new AffineTransform();
		if (!isRight) {
			at = AffineTransform.getScaleInstance(-1, 1);
			at.translate(-character.getWidth(null), 0);
			AffineTransformOp op = new AffineTransformOp(at, AffineTransformOp.TYPE_BICUBIC);
			character = op.filter(character, null);
			if (character.getWidth() > width)
				xp = character.getWidth() - width;
		}
	}

	public boolean collideWith(Character ch) {
		Rectangle p = new Rectangle(x - (int)(xp* 1.5), y - (int)(yp* 1.5), (int) (character.getWidth() * 1.5), (int) (character.getHeight() * 1.5));
		Rectangle m = new Rectangle(ch.x - (int)(ch.getXp()* 1.5), ch.y - (int)(ch.getYp()* 1.5), (int)(ch.getCharacter().getWidth()* 1.5),
				(int)(ch.getCharacter().getHeight()* 1.5));
		return p.intersects(m);
	}

	public void draw(Graphics2D g) {
		yp = character.getHeight() - height;
		g.drawImage(character, x - (int)(xp* 1.5), y - (int)(yp* 1.5), (int) (character.getWidth() * 1.5), (int) (character.getHeight() * 1.5),
				null);
		xp = 0;
		yp = 0;
	}

	public void update() {
		if (playeri == 1) {
			if (!InputUtility.getKeyPressed(Setting.key[0]) && !InputUtility.getKeyPressed(Setting.key[1])) {
				isRun = false;
			}
		} else {
			if (!InputUtility.getKeyPressed(Setting.key[6]) && !InputUtility.getKeyPressed(Setting.key[7])) {
				isRun = false;
			}
		}
		countShoot++;

		if (!isJump && !isAttack)
			picRunUpdate();
		picAttackUpdate();
		picShootUpdate();
		picSuperAttack();

		stand();
		picLoseUpdate();

		transform();
		attackUpdate();
	}

	public void run(boolean isRight) {
		if (isAttack || isShoot || isSuperAttack)
			return;
		isRun = true;
		this.isRight = isRight;
		if (isRight)
			x += 20;
		else
			x -= 20;

		if (x < 0)
			x = 0;
		else if (x > 640 - character.getWidth())
			x = 640 - character.getWidth();
	}

	public void jump() {
		if (isAttack || isShoot || isSuperAttack) {
			// System.out.println("re");
			return;
		}

		if (isJump) {
			if (!isDoubleJump && !isTripleJump && count[0] > jumpMax - 1 && count[0] <= jumpMax * 2 - 2) {
				// sys
				isDoubleJump = true;
				isTripleJump = true;
			}
			return;
		}

		isJump = true;
		new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized (GameLogic.character[playeri - 1]) {
					if (GameLogic.character[playeri - 1] instanceof Pikachu)
						Resource.pikachu1.play();
					else
						Resource.jump.play();

					while (true) {
						if (isDoubleJump) {
							if (GameLogic.character[playeri - 1] instanceof Pikachu)
								Resource.pikachu1.play();
							else
								Resource.jump.play();

							while (true) {

								try {
									GameLogic.character[playeri - 1].wait();
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
								if (Time.isPlay) {
									if (count[1] <= jumpMax) {
										y -= 25;
										count[1]++;
									} else if (count[1] > jumpMax && count[1] <= jumpMax * 2) {
										y += 25;
										count[1]++;
									} else {
										count[1] = 1;
									}

									picJumpUpdate(1);
									transform();
								}
								if (!isDoubleJump) {
									count[1] = 1;
									break;
								}
							}
						}

						try {
							GameLogic.character[playeri - 1].wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						if (Time.isPlay) {

							if (count[0] <= jumpMax) {
								y -= 25;
								count[0]++;
							} else if (count[0] > jumpMax && count[0] <= jumpMax * 2) {
								y += 25;
								count[0]++;
							} else {
								count[0] = 1;
							}

							picJumpUpdate(0);
							transform();
						}

						if (!isJump) {
							count[0] = 1;
							isTripleJump = false;
							break;
						}
					}

				}
			}

		}).start();
	}

	public void attack(Character c) {
		if (isAttack || isShoot || isSuperAttack) {
			return;
		}
		if (this instanceof Reborn)
			Resource.rebornGun.play();
		else
			Resource.fencing.play();

		isAttack = true;
		this.enemy = c;
	}

	public void attackUpdate() {
		if ((isAttack) && collideWith(enemy) && !isDoubleAttack) {
			enemy.setAttacked(true);
			if (isSuperAttack)
				enemy.attacked(attackPower * 4);
			else
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

	public void attacked(int amount) {
		healthPoint -= amount;
		if (healthPoint <= 0)
			healthPoint = 0;
		lose = isLose();
		isAttacked = true;
		flashing = true;
		hitByEnemy();
	}

	public void hitByEnemy() {
		flashCounter = 5;
		flashDurationCounter = 0;
	}

	public boolean isLose() {
		if (healthPoint <= 0) {
			Time.isHasWinner = true;
			return true;
		} else
			return false;
	}

	public void shoot(Character c) {
		if (isAttack || isShoot || isSuperAttack) {
			return;
		}
		this.enemy = c;
		if (countShoot >= 15) {
			if (this instanceof Kurosaki)
				Resource.fencing.play();
			else if (this instanceof Reborn)
				Resource.rebornBomb.play();
			else if (this instanceof Pikachu)
				Resource.pikaShoot.play();
			else
				Resource.attack.play();
			RenderableHolder.getInstance().add(new Shootable(this));
			countShoot = 0;
			isShoot = true;
		}

	}

	public void superAttack() {
		if (isAttack || isShoot || isSuperAttack) {
			return;
		}
		if (powerCount >= 4) {
			Time.isPlay = false;
			isSuperAttack = true;
			powerCount = 0;
			if (!(this instanceof Pikachu))
				Resource.attack.play();

			new Thread(new Runnable() {

				@Override
				public void run() {

					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					synchronized (Login.player[playeri - 1]) {
						Login.player[playeri - 1].notifyAll();
					}

				}
			}).start();
		}
	}

	public abstract void picRunUpdate();

	public abstract void picJumpUpdate(int i);

	public abstract void stand();

	public abstract void picAttackUpdate();

	public abstract void picShootUpdate();

	public abstract void picLoseUpdate();

	public abstract void picSuperAttack();

}
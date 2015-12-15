package character;

import input.InputUtility;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import javax.swing.SwingUtilities;

import entity.Player;
import entity.Shootable;
import entity.SuperShootable;
import entity.Time;
import render.IRenderable;
import render.Login;
import render.RenderableHolder;
import render.Resource;

public class Pikachu extends Character implements IRenderable {

	public Pikachu(int p, int ap, int hp, Player player) {
		super(p, 10, 100);
		indexC = 0;
		width = 25;
		height = 30;
		y = y - height;
		this.player = player;
		character = Resource.pikachu.getSubimage(102, 4, 25, 28);
		transform();
	}

	@Override
	public void stand() {
		if (isRun || isJump || isAttack || isShoot || isSuperAttack)	return;
		character = Resource.natsu.getSubimage(471, 224, 42, 55);;
		for (int a : countPic)
			a = 0;
	}
	
	public void picRunUpdate() {
		if (!isRun)	return;
		isAttack = false;
		character = Resource.pikachu.getSubimage(39 + countPic[0] * 25, 147, 25, 28);
		width = 25;
		height = 28;
		countPic[0]++;
		if (countPic[0] == 6)
			countPic[0] = 0;
	}

	@Override
	public void picJumpUpdate(int i) {
		if (countPic[1] > 3) {
			countPic[1] = 0;
		}
		character = Resource.pikachu.getSubimage(64 + countPic[1] * 26, 110, 26, 32);
		width = 26;
		height = 32;
		if (count[i] == 1)
			countPic[1] = 1;
		else if (count[i] == jumpMax + 1)
			countPic[1] = 2;
		else if (count[i] == jumpMax * 2)
			countPic[1] = 3;
		else if (countPic[1] >= 3) {
			countPic[1] = 0;
			if (i == 0) {
				isJump = false;
			} else isDoubleJump = false;
		}
	}

	public void picAttackUpdate() {
		if (!isAttack)	return;
		character = Resource.pikachu.getSubimage(1 + countPic[2] * 28, 181, 28, 25);
		width = 28;
		height = 25;

		if (isRight)	x += 20;
		else	x -= 20;

		if (x < 0)	x = 0;
		else if (x > 640 - character.getWidth())
			x = 640 - character.getWidth();

		countPic[2]++;
		if (countPic[2] >= 7) {
			character = Resource.pikachu.getSubimage(102, 4, 25, 28);
			width = 25;
			height = 28;
			countPic[2] = 0;
			isAttack = false;
			isDoubleAttack = false;
		}
	}

	public void picShootUpdate() {
		if (!isShoot)	return;
		isShoot = false;
	}

	@Override
	public void picSuperAttack() {
		if (!isSuperAttack)
			return;
		countPic[4]++;
		if (countPic[4] >= 3) {
			countPic[4]=0;
			Time.isAlreadyStop = false;
			isSuperAttack = false;
		}
	}

	public void superAttack() {
		if (isAttack || isShoot || isJump) return;
		if (powerCount >= 4) {
			Time.isPlay = false;
			isSuperAttack = true;
			powerCount = 0;
			RenderableHolder.getInstance().add(new SuperShootable(this));

			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						isSuperAttack = true;
						Time.isPlay = false;
						Thread.sleep(1200);
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
	
	public void picLoseUpdate() {
		if (!lose)	return;
		character = Resource.pikachu.getSubimage(102, 4, 25, 28);
		AffineTransform at = AffineTransform.getRotateInstance(Math.toRadians(-90), width / 2, height / 2);
		AffineTransformOp op = new AffineTransformOp(at, AffineTransformOp.TYPE_BICUBIC);
		character = op.filter(character, null);
	}
	
	@Override
	public int getZ() {
		return 0;
	}

}

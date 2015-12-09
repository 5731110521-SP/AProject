package character;

import input.InputUtility;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import entity.Player;
import entity.Shootable;
import render.IRenderable;
import render.RenderableHolder;
import render.Resource;

public class Luffy extends Character implements IRenderable {

	public Luffy(int ap, int dp, int hp, int mp, Player player) {
		super(10, dp, 100, mp);
		indexC = 1;
		width = 53;
		height = 51;
		x = 100;
		y = 373 - height;
		this.player = player;
		isAttack = false;
		isRun = false;
		isJump = false;
		isRight = true;
		isShoot = false;
		countShoot = 0;
		character = Resource.luffy.getSubimage(278, 40, 60, 58);
		for (int a : countPic)
			a = 0;

	}

	@Override
	public int getZ() {
		return 0;
	}
	
	public void picRunUpdate() {
		if (isJump || isAttack)
			return;
		character = Resource.luffy.getSubimage(380 + countPic[0] * 55, 43, 55, 50);
		width = 55;
		height = 50;
		countPic[0]++;
		if (countPic[0] == 8)
			countPic[0] = 0;

	}

	public void stand() {
		if (isRun || isJump || isAttack)
			return;
		character = Resource.luffy.getSubimage(278, 40, 53, 51);
		width = 53;
		height = 51;
		for (int a : countPic)
			a = 0;
	}

	public void picAttackUpdate() {
		if (!isAttack)
			return;
		character = Resource.luffy.getSubimage(39 + countPic[2] * 40, 228, 45, 55);
		width = 45;
		height = 51;
		if (countPic[2] == 0) {
			countPic[2] = 1;
		} else if (countPic[2] == 1) {
			countPic[2]++;
		} else if (countPic[2] > 1 && countPic[2] < 6) {
			character = Resource.luffy.getSubimage(132 + (countPic[2] - 2) * 109, 229, 109, 53);
			countPic[2]++;
			width = 109;
			height = 51;
		} else if (countPic[2] >= 6) {
			character = Resource.luffy.getSubimage(568, 229, 40, 55);
			countPic[2] = 0;
			width = 40;
			height = 51;
			isAttack = false;
			isDoubleAttack = false;
		}

	}

	public void picShootUpdate() {
		if (!isShoot)
			return;
		character = Resource.luffy.getSubimage(154, 618, 57, 53);
		width=57;
		height=53;
		countPic[5]++;
		if(countPic[5] == 1)
			character = Resource.luffy.getSubimage(278, 621, 56, 53);
		else if(countPic[5] ==2)
			character = Resource.luffy.getSubimage(400, 619, 55, 53);
		else if(countPic[5] == 3){
			character = Resource.luffy.getSubimage(532, 622, 49, 48);
			isShoot = false;
			countPic[5] = 0;
		}
	}

	public void picLoseUpdate() {
		if (!lose)
			return;
		if (countPic[3] < 3)
			character = Resource.luffy.getSubimage(261 + (countPic[3] * 56), 890, 56, 43);
		countPic[3]++;
		if (countPic[3] >= 3)
			character = Resource.luffy.getSubimage(261 + (2 * 56), 890, 56, 43);

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

}

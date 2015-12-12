package character;

import input.InputUtility;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import javax.swing.SwingUtilities;

import entity.Player;
import entity.Shootable;
import entity.SuperShootable;
import render.IRenderable;
import render.RenderableHolder;
import render.Resource;

public class Pikachu extends Character implements IRenderable {

	public Pikachu(int p,int ap, int dp, int hp, Player player) {
		super(p,10, dp, 100);
		indexC = 0;
		width = 25;
		height = 30;
		y = 373 - height;
		this.player = player;
		character = Resource.pikachu.getSubimage(102, 4, 25, 28);
	}

	public void picRunUpdate() {
		if (!isRun)
			return;
		isAttack = false;
		character = Resource.pikachu.getSubimage(39 + countPic[0] * 25, 147, 25, 28);
		width = 25;
		height = 28;
		countPic[0]++;
		if (countPic[0] == 6)
			countPic[0] = 0;
	}

	@Override
	public void picJumpUpdate() {
		if (countPic[1] > 3) {
			countPic[1] = 0;
		}
		character = Resource.pikachu.getSubimage(64 + countPic[1] * 26, 110, 26, 32);
		width = 26;
		height = 32;
		if (count == 1)
			countPic[1] = 1;
		else if (count == jumpMax + 1)
			countPic[1] = 2;
		else if (count == jumpMax * 2)
			countPic[1] = 3;
		else if (countPic[1] >= 3) {
			countPic[1] = 0;
			isJump = false;

		}
	}

	public void stand() {
		if (isRun || isJump || isAttack || isShoot || isSuperAttack)
			return;
		character = Resource.pikachu.getSubimage(102, 4, 25, 28);
		width = 25;
		height = 28;
		countPic[0] = 0;
		for (int a : countPic)
			a = 0;
	}

	public void picAttackUpdate() {
		if (!isAttack)
			return;
		character = Resource.pikachu.getSubimage(1 + countPic[2] * 28, 181, 28, 25);
		width = 28;
		height = 25;
		
		if (isRight)
			x += 20;
		else
			x -= 20;
		
		if(x<0) x=0;
		else if(x>640-character.getWidth()) x=640-character.getWidth();
		
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
		if (!isShoot)
			return;
		isShoot = false;
	}

	public void picLoseUpdate() {
		if (!lose)
			return;
		character = Resource.pikachu.getSubimage(102, 4, 25, 28);
		AffineTransform at = AffineTransform.getRotateInstance(Math.toRadians(-90), width / 2, height / 2);
		AffineTransformOp op = new AffineTransformOp(at, AffineTransformOp.TYPE_BICUBIC);
		character = op.filter(character, null);

	}

	@Override
	public void picSuperAttack() {
		if (!isSuperAttack){
			return;
		}
		count++;
		if(count==10){
			count=1;
			isSuperAttack = false;
		}
	}

	public void superAttack() {
		if (isAttack || isShoot || isJump){
			return;
		}
		if(isSuperAttack){
			RenderableHolder.getInstance().add(new SuperShootable(this));
		}
		if (powerCount >= 4) {
			isSuperAttack = true;
			powerCount = 0;
			RenderableHolder.getInstance().add(new SuperShootable(this));
		}
	}

	@Override
	public int getZ() {
		return 0;
	}

}

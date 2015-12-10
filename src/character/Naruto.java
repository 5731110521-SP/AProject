package character;

import input.InputUtility;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import entity.Player;
import entity.Shootable;
import render.IRenderable;
import render.RenderableHolder;
import render.Resource;

public class Naruto extends Character
					implements IRenderable{
	
	public Naruto(int ap, int dp, int hp,Player player) {
		super(20, 0, 100);
		indexC = 2;
		width = 53;
		height = 61;
		x=100;
		y=373-height;
		this.player = player;
		isAttack = false;
		isRun = false;
		isJump = false;
		isRight = true;
		countShoot = 5;
		character = Resource.naruto.getSubimage(0, 0, 53, 61);
	}

	@Override
	public void picRunUpdate() {
		if (isJump || isAttack)
			return;
		isAttack = false;
		if(countPic[0] == 0)
			character = Resource.naruto.getSubimage(0, 75, 50, 55);
		else if(countPic[0] == 1)
			character = Resource.naruto.getSubimage(55,78, 65, 51);
		else if(countPic[0] == 2)
			character = Resource.naruto.getSubimage(130,78, 54, 52);
		else if(countPic[0] == 3)
			character = Resource.naruto.getSubimage(193, 76,49, 52);
		else if(countPic[0] == 4)
			character = Resource.naruto.getSubimage(254, 76, 61,47);
		else if(countPic[0] == 5)
			character = Resource.naruto.getSubimage(320, 72, 56,56);
		countPic[0]++;
		if(countPic[0] == 6)countPic[0] = 0;
	}

	@Override
	public void picJumpUpdate() {
		if(countPic[1] < 3){
			if(countPic[1] == 0){
				character = Resource.naruto.getSubimage(1, 146, 42, 44);
				width=42;
				height=44;
			}else if(countPic[1] == 1)
				character = Resource.naruto.getSubimage(110, 135, 41, 68);
			else if(countPic[1] == 2){
				character = Resource.naruto.getSubimage(163, 135, 51, 66);
				width=51;
				height=66;
			}
			if(countPic[1] == 3) {
				isJump=false;
				countPic[1] = 0;
			}
		}
		if(count==1) countPic[1]=0;
		else if(count==jumpMax+1) countPic[1]=1;
		else if(count==jumpMax*2) countPic[1]=2;
		else if(countPic[1]>=2){
			countPic[1] = 0;
			isJump=false;
		}
	}

	@Override
	public void stand() {
		if (isRun || isJump || isAttack)
			return;
		character = Resource.naruto.getSubimage(0, 0, 53, 61);
		for (int a : countPic)
			a = 0;
	}

	@Override
	public void picAttackUpdate() {
		if (!isAttack)
			return;
		if(countPic[2] == 0)
			character = Resource.naruto.getSubimage(10, 375, 45, 61);
		else if(countPic[2] == 1)
			character = Resource.naruto.getSubimage(65, 377, 57, 55);
		else if(countPic[2] == 2)
			character = Resource.naruto.getSubimage(138,381, 59, 54);
		else if(countPic[2] == 3)
			character = Resource.naruto.getSubimage(209, 383, 58, 52);
		else if(countPic[2] == 4)
			character = Resource.naruto.getSubimage(274, 384, 51,53);
		countPic[2]++;
		if(countPic[2]>=5) {
			character = Resource.naruto.getSubimage(0, 0, 53, 61);
			countPic[2] = 0;
			isAttack = false;
			isDoubleAttack = false;
		}
	}

	@Override
	public void picShootUpdate() {
		if (!isShoot)
			return;
		if(countPic[5] == 0){
			character = Resource.naruto.getSubimage(20, 543, 66, 59);
			width=66;
		}else if(countPic[5] == 1){
			character = Resource.naruto.getSubimage(104, 544, 66, 58);
		}else if(countPic[5] ==2){
			character = Resource.naruto.getSubimage(188, 540, 67, 56);
		}else if(countPic[5] == 3){
			character = Resource.naruto.getSubimage(263, 533, 66, 58);
		}else if(countPic[5] == 4){
			character = Resource.naruto.getSubimage(339, 465, 55, 54);
		}else if(countPic[5] == 5){
			character = Resource.naruto.getSubimage(410, 463, 67, 51);
			isShoot = false;
			countPic[5] = -1;
			width=53;
		}
		countPic[5]++;
	}

	@Override
	public void picLoseUpdate() {
		if (!lose)
			return;
		if(countPic[3] == 0){
			character = Resource.naruto.getSubimage(4, 221, 52, 56);
			countPic[3]++;
		}else if(countPic[3] == 1){
			character = Resource.naruto.getSubimage(73, 217, 44, 55);
			countPic[3]++;
		}else if(countPic [3] == 2){
			character = Resource.naruto.getSubimage(127, 220, 52, 50);
			countPic[3]++;
		}
		else if(countPic[3] == 3){
			character = Resource.naruto.getSubimage(192, 234, 64, 39);
			height=39;
			yp=61-39;
		}
	}

	@Override
	public int getZ() {
		return 0;
	}

	@Override
	public void picSuperAttack() {
		// TODO Auto-generated method stub
		
	}

}

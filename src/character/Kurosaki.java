package character;

import entity.Player;
import render.IRenderable;
import render.Resource;

public class Kurosaki extends Character implements IRenderable{

	public Kurosaki(int ap, int dp, int hp,Player player) {
		super(10, dp, 100);
		indexC = 5;
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
		character = Resource.bleach.getSubimage(5, 27, 44, 46);
		for (int a : countPic)
			a = 0;
	}

	@Override
	public int getZ() {
		return 0;
	}

	@Override
	public void picRunUpdate() {
		if (isJump || isAttack)
			return;
		isAttack = false;
		if(countPic[0] == 0)
			character = Resource.bleach.getSubimage(6, 108, 49, 37);
		else if(countPic[0] == 1)
			character = Resource.bleach.getSubimage(130, 108, 50, 39);
		else if(countPic[0] == 2)
			character = Resource.bleach.getSubimage(263, 107, 47, 39);
		else if(countPic[0] == 3)
			character = Resource.bleach.getSubimage(329, 109, 48, 36);
		else if(countPic[0] == 4)
			character = Resource.bleach.getSubimage(395, 109, 47, 37);
		else if(countPic[0] == 5)
			character = Resource.bleach.getSubimage(457, 109,47, 37);
		countPic[0]++;
		if(countPic[0] == 6)countPic[0] = 0;
	}

	@Override
	public void picJumpUpdate() {
		if(countPic[1] < 3){
			if(countPic[1] == 0) {
				character = Resource.bleach.getSubimage(63, 176,37, 43);
				width = 37;
				height = 43;
			}else if(countPic[1] == 1) {
				character = Resource.bleach.getSubimage(162, 177,40, 36);
				width = 40;
				height = 36;
			}else if(countPic[1] == 2) {
				character = Resource.bleach.getSubimage(261, 196, 44, 50);
				width = 44;
				height = 50;
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
		character = Resource.bleach.getSubimage(5, 27, 44, 46);
		for (int a : countPic)
			a = 0;
	}

	@Override
	public void picAttackUpdate() {
		if (!isAttack)
			return;
		if(countPic[2] == 0)
			character = Resource.bleach.getSubimage(7, 641, 41, 40);
		else if(countPic[2] == 1)
			character = Resource.bleach.getSubimage(57, 643, 44, 38);
		else if(countPic[2] == 2)
			character = Resource.bleach.getSubimage(116, 645, 40, 36);
		else if(countPic[2] == 3)
			character = Resource.bleach.getSubimage(166, 645, 60, 37);
		countPic[2]++;
		if(countPic[2]>=4){
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
			character = Resource.bleach.getSubimage(5, 1192, 42, 44);
			width = 70;
			height =50;
		}
//		else if(countPic[5] == 1)	
//			character = Resource.bleach.getSubimage(59, 1192, 42,44);
//		else if(countPic[5] == 1)
//			character = Resource.bleach.getSubimage(114, 1192, 43,45);
		else if(countPic[5] == 1)
			character = Resource.bleach.getSubimage(222, 1183, 68,52);
		else if(countPic[5] == 2)
			character = Resource.bleach.getSubimage(301, 1172, 47,64);
		else if(countPic[5] == 3)
			character = Resource.bleach.getSubimage(421, 1192, 51,44);
//		else if(countPic[5] == 6)
//			character = Resource.bleach.getSubimage(484, 1188, 54,47);

		countPic[5]++;
		if(countPic[5] >= 4){
			isShoot = false;
			countPic[5] = 0;
		}
	}

	@Override
	public void picLoseUpdate() {
		if (!lose)
			return;
		if(countPic[3] == 0) 			
			character = Resource.bleach.getSubimage(297, 352,47,32);
		else if(countPic[3] == 1)
			character = Resource.bleach.getSubimage(356, 353,43,34);
		else if(countPic[3] >= 2)
			character = Resource.bleach.getSubimage(443, 383,57,17);
		countPic[3]++;
	}

	@Override
	public void picSuperAttack() {
		// TODO Auto-generated method stub
		
	}

}

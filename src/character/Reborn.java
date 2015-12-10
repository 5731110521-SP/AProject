package character;

import com.sun.org.apache.regexp.internal.recompile;

import entity.Player;
import render.IRenderable;
import render.Resource;

public class Reborn extends Character implements IRenderable{

	public Reborn(int ap, int dp, int hp,Player player) {
		super(10, 5, 100);
		indexC = 3;
		width = 37;
		height = 39;
		x = 100;
		y = 373 - height;
		this.player = player;
		character = Resource.reborn.getSubimage(7, 492, 37, 39);
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
		if(countPic[0] < 4 ) character = Resource.reborn.getSubimage(1+(countPic[0]*45), 489, 45, 43);;
		countPic[0]++;
		if(countPic[0] >= 4) countPic[0] = 0;
	}

	@Override
	public void picJumpUpdate() {
		if(countPic[1] < 3){
			if(countPic[1] == 0) {
				character = Resource.reborn.getSubimage(17, 577,33, 43);
			}else if(countPic[1] == 1) {
				character = Resource.reborn.getSubimage(102, 553,43, 46);
			}else if(countPic[1] == 2) {
				character = Resource.reborn.getSubimage(198, 585, 38, 41);
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
		character = Resource.reborn.getSubimage(7, 492, 37, 39);
		for (int a : countPic)
			a = 0;
	}

	@Override
	public void picAttackUpdate() {
		if (!isAttack)
			return;
		if(countPic[2] == 0) {
			character = Resource.reborn.getSubimage(20, 1097, 37, 42);
		}else if(countPic[2] == 1) character = Resource.reborn.getSubimage(61, 1097, 37, 42);
		else if(countPic[2] == 2) character = Resource.reborn.getSubimage(102, 1097, 37, 42);
		else if(countPic[2] == 3) {
			character = Resource.reborn.getSubimage(147, 1097, 42, 42);
		}else if(countPic[2] == 4) {
			character = Resource.reborn.getSubimage(191, 1098,146, 42);
		}else if(countPic[2] == 5) {
			character = Resource.reborn.getSubimage(343, 1098, 167, 42);
		}else if(countPic[2] == 6) {
			character = Resource.reborn.getSubimage(520, 1097, 163, 43);
		}else if(countPic[2] == 7) {
			character = Resource.reborn.getSubimage(690, 1097, 160, 44);
		}else if(countPic[2] == 8) {
			character = Resource.reborn.getSubimage(864, 1097, 147, 43);
		}else if(countPic[2] == 9) {
			character = Resource.reborn.getSubimage(1016, 1098, 42, 41);
		}else if(countPic[2] == 10) {
			character = Resource.reborn.getSubimage(1074, 1097, 38, 42);
		}else if(countPic[2] == 11) {
			character = Resource.reborn.getSubimage(1120, 1098, 37, 42);
		}
		countPic[2]++;
		if(countPic[2]>=12){
			countPic[2] = 0;
			isAttack = false;
			isDoubleAttack = false;
		}
	}

	@Override
	public void picShootUpdate() {
		if (!isShoot)
			return;
//		if(countPic[5] == 0) {
//			character = Resource.reborn.getSubimage(16, 1821, 37, 43);
//			width = 37;
//			height = 43;
//		}else if(countPic[5] == 1){
//			character = Resource.reborn.getSubimage(56, 1821, 37, 43);
//			width = 37;
//			height = 43;
//		}else if(countPic[5] == 2){
//			character = Resource.reborn.getSubimage(101, 1821, 40, 42);
//			width = 40;
//			height = 42;
//		}else 
			if(countPic[5] == 0){
			character = Resource.reborn.getSubimage(149, 1821, 61, 43);
		}
//			else if(countPic[5] == 4){
//			character = Resource.reborn.getSubimage(231, 1825, 59, 43);
//			width = 59;
//			height = 43;
//		}
			else if(countPic[5] == 1){
			character = Resource.reborn.getSubimage(305, 1824, 80, 40);
		}else if(countPic[5] == 2){
			character = Resource.reborn.getSubimage(393, 1816, 82, 49);
		}
//		else if(countPic[5] == 7){
//			character = Resource.reborn.getSubimage(482, 1810, 91, 54);
//			width = 91;
//			height = 54;
//		}else if(countPic[5] == 8){
//			character = Resource.reborn.getSubimage(584, 1806, 78, 57);
//			width = 78;
//			height = 57;
//		}
		countPic[5]++;
		if(countPic[5] >= 3){
			isShoot = false;
			countPic[5] = 0;
		}
			
	}

	@Override
	public void picLoseUpdate() {
		if (!lose)
			return;
		if(countPic[3] == 0) {
			character = Resource.reborn.getSubimage(495, 727, 38, 38);
			countPic[3]++;
		}
		else if(countPic[3] == 1) character = Resource.reborn.getSubimage(542, 727, 38, 38);
	}

	@Override
	public void picSuperAttack() {
		// TODO Auto-generated method stub
		
	}

}

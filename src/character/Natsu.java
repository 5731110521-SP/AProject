package character;

import entity.Player;
import render.IRenderable;
import render.Resource;

public class Natsu extends Character implements IRenderable{

	public Natsu(int p,int ap, int dp, int hp,Player player) {
		super(p,20, 10, 100);
		indexC = 4;
		width = 42;
		height = 55;
		y=y-height;
		this.player = player;
		character = Resource.natsu.getSubimage(471, 224, 42, 55);
	}

	@Override
	public int getZ() {
		return 0;
	}

	@Override
	public void picRunUpdate() {
		if (!isRun)
			return;
		isAttack = false;
		if(countPic[0] == 0)
			character = Resource.natsu.getSubimage(558, 229, 44, 49);
		else if(countPic[0] == 1)
			character = Resource.natsu.getSubimage(603, 229, 44, 49);
		else if(countPic[0] == 2)
			character = Resource.natsu.getSubimage(645, 228, 47, 50);
		else if(countPic[0] == 3)
			character = Resource.natsu.getSubimage(694, 228, 44, 50);
		else if(countPic[0] == 4)
			character = Resource.natsu.getSubimage(737, 229, 44, 50);
		else if(countPic[0] == 5)
			character = Resource.natsu.getSubimage(778, 228, 47, 49);
		countPic[0]++;
		if(countPic[0] == 6) countPic[0] = 0;
	}

	@Override
	public void picJumpUpdate() {
		if(countPic[1] < 3){
			if(countPic[1] == 0){
				character = Resource.natsu.getSubimage(61, 297, 39, 57);
//				width=39;
//				height=57;
			}else if(countPic[1] == 1){
				character = Resource.natsu.getSubimage(104, 308, 52, 47);
//				width=52;
//				height=47;
			}else if(countPic[1] == 2){
				character = Resource.natsu.getSubimage(163, 297, 51, 58);
//				width=51;
//				height=58;
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
		if (isRun || isJump || isAttack || isShoot || isSuperAttack)
			return;
		character = Resource.natsu.getSubimage(471, 224, 42, 55);
//		width = 42;
//		height = 55;
		for (int a : countPic)
			a = 0;
	}

	@Override
	public void picAttackUpdate() {
		if (!isAttack || isSuperAttack)
			return;
		if(countPic[2] == 0)
			character = Resource.natsu.getSubimage(7, 449, 61, 58);
		else if(countPic[2] == 1)
			character = Resource.natsu.getSubimage(188, 455, 65, 52);
		else if(countPic[2] == 2)
			character = Resource.natsu.getSubimage(260, 448, 97, 60);
		else if(countPic[2] == 3)
			character = Resource.natsu.getSubimage(361, 446, 96, 61);
		else if(countPic[2] ==4)
			character = Resource.natsu.getSubimage(462, 445, 57, 61);
		else if(countPic[2] == 5)
			character = Resource.natsu.getSubimage(524, 462, 51, 44);
		else if(countPic[2] ==6)
			character = Resource.natsu.getSubimage(580, 462, 38, 45);
		countPic[2]++;
		if(countPic[2]>=7) {
			character = Resource.natsu.getSubimage(471, 224, 42, 55);
			countPic[2] = 0;
			isAttack = false;
			isDoubleAttack = false;
		}
	}

	@Override
	public void picShootUpdate() {
		if (!isShoot)
			return;
		if(countPic[5] == 0)
			character = Resource.natsu.getSubimage(8, 608, 54, 51);
		else if(countPic[5] == 1)
			character = Resource.natsu.getSubimage(66, 610, 54, 49);
		else if(countPic[5] == 2)
			character = Resource.natsu.getSubimage(123, 607, 39, 52);
		else if(countPic[5] == 3)
			character = Resource.natsu.getSubimage(168, 610, 44, 50);
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
			character = Resource.natsu.getSubimage(16, 875, 42, 50);
		else if(countPic[3] == 1)
			character = Resource.natsu.getSubimage(59, 873, 46, 53);
		else if(countPic[3] == 2)
			character = Resource.natsu.getSubimage(114, 896, 53, 27);
		else if(countPic[3] == 3)
			character = Resource.natsu.getSubimage(180, 890, 60, 29);
		else if(countPic[3] >= 4)
			character = Resource.natsu.getSubimage(254, 908, 60, 19);
		countPic[3]++;
	}

	@Override
	public void picSuperAttack() {
		if(!isSuperAttack) return;
		if(countPic[4] == 0)
			character = Resource.natsu.getSubimage(9, 715, 60, 57);
		else if(countPic[4] == 1)
			character = Resource.natsu.getSubimage(68, 713, 55, 59);
		else if(countPic[4] == 2)
			character = Resource.natsu.getSubimage(124, 712, 55, 60);
		else if(countPic[4] == 3)
			character = Resource.natsu.getSubimage(183, 708, 71, 63);
		else if(countPic[4] == 4)
			character = Resource.natsu.getSubimage(259, 703, 72, 69);
		else if(countPic[4] == 5)
			character = Resource.natsu.getSubimage(334, 697, 80, 76);
		else if(countPic[4] == 6)
			character = Resource.natsu.getSubimage(414, 678, 58, 93);
		else if(countPic[4] == 7)
			character = Resource.natsu.getSubimage(476, 676, 52, 95);
		countPic[4]++;
		if(countPic[4] >= 8){
			isAttack=false;
			isSuperAttack = false;
			countPic[4] = 0;
		}
		if(countPic[4]>=5 && countPic[4]<8) isAttack=true;
	}

}

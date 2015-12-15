package character;

import com.sun.org.apache.regexp.internal.recompile;

import entity.Player;
import entity.SuperShootable;
import entity.Time;
import render.IRenderable;
import render.Login;
import render.RenderableHolder;
import render.Resource;

public class Reborn extends Character implements IRenderable {

	public Reborn(int p, Player player) {
		super(p, 3, 100);
		indexC = 3;
		width = 37;
		height = 39;
		y = y - height;
		this.player = player;
		character = Resource.reborn.getSubimage(7, 492, 37, 39);
		transform();
	}

	@Override
	public void stand() {
		if (isRun || isJump || isAttack || isShoot || isSuperAttack)
			return;
		character = Resource.reborn.getSubimage(7, 492, 37, 39);
		for (int a : countPic)
			a = 0;
	}

	@Override
	public void picRunUpdate() {
		if (!isRun)
			return;
		isAttack = false;
		if (countPic[0] < 4)
			character = Resource.reborn.getSubimage(1 + (countPic[0] * 45), 489, 45, 43);
		;
		countPic[0]++;
		if (countPic[0] >= 4)
			countPic[0] = 0;
	}

	@Override
	public void picJumpUpdate(int i) {
		if (countPic[1] < 3) {
			if (countPic[1] == 0) {
				character = Resource.reborn.getSubimage(17, 577, 33, 43);
			} else if (countPic[1] == 1) {
				character = Resource.reborn.getSubimage(102, 553, 43, 46);
			} else if (countPic[1] == 2) {
				character = Resource.reborn.getSubimage(198, 585, 38, 41);
			}
			if (countPic[1] == 3) {
				if (i == 0) {
					isJump = false;
				} else {
					isDoubleJump = false;
				}
				countPic[1] = 0;
			}
		}
		if (count[i] == 1)
			countPic[1] = 0;
		else if (count[i] == jumpMax + 1)
			countPic[1] = 1;
		else if (count[i] == jumpMax * 2)
			countPic[1] = 2;
		else if (countPic[1] >= 2) {
			countPic[1] = 0;
			if (i == 0) {
				isJump = false;
			} else {
				isDoubleJump = false;
			}
		}
	}

	public void superAttack() {
		if (isAttack || isShoot || isJump)
			return;
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

	@Override
	public void picAttackUpdate() {
		if (!isAttack || isSuperAttack)
			return;
		if (countPic[2] == 0) {
			character = Resource.reborn.getSubimage(20, 1097, 37, 42);
		} else if (countPic[2] == 1)
			character = Resource.reborn.getSubimage(61, 1097, 37, 42);
		else if (countPic[2] == 2)
			character = Resource.reborn.getSubimage(102, 1097, 37, 42);
		else if (countPic[2] == 3) {
			character = Resource.reborn.getSubimage(147, 1097, 42, 42);
		} else if (countPic[2] == 4) {
			character = Resource.reborn.getSubimage(191, 1098, 146, 42);
		} else if (countPic[2] == 5) {
			character = Resource.reborn.getSubimage(343, 1098, 167, 42);
		} else if (countPic[2] == 6) {
			character = Resource.reborn.getSubimage(520, 1097, 163, 43);
		} else if (countPic[2] == 7) {
			character = Resource.reborn.getSubimage(690, 1097, 160, 44);
		} else if (countPic[2] == 8) {
			character = Resource.reborn.getSubimage(864, 1097, 147, 43);
		} else if (countPic[2] == 9) {
			character = Resource.reborn.getSubimage(1016, 1098, 42, 41);
		} else if (countPic[2] == 10) {
			character = Resource.reborn.getSubimage(1074, 1097, 38, 42);
		} else if (countPic[2] == 11) {
			character = Resource.reborn.getSubimage(1120, 1098, 37, 42);
		}
		countPic[2]++;
		if (countPic[2] >= 12) {
			countPic[2] = 0;
			isAttack = false;
			isDoubleAttack = false;
		}
	}

	@Override
	public void picShootUpdate() {
		if (!isShoot)
			return;
		if (countPic[5] == 0) {
			character = Resource.reborn.getSubimage(149, 1821, 61, 43);
		} else if (countPic[5] == 1) {
			character = Resource.reborn.getSubimage(305, 1824, 80, 40);
		} else if (countPic[5] == 2) {
			character = Resource.reborn.getSubimage(393, 1816, 82, 49);
		}
		countPic[5]++;
		if (countPic[5] >= 3) {
			isShoot = false;
			countPic[5] = 0;
		}
	}

	@Override
	public void picSuperAttack() {
		if (!isSuperAttack)
			return;
		if (countPic[4] == 0)
			character = Resource.reborn.getSubimage(13, 1184, 36, 40);
		else if (countPic[4] == 1)
			character = Resource.reborn.getSubimage(56, 1181, 37, 44);
		else if (countPic[4] == 2)
			character = Resource.reborn.getSubimage(97, 1183, 38, 42);
		else if (countPic[4] == 3)
			character = Resource.reborn.getSubimage(138, 1184, 38, 40);
		else if (countPic[4] == 4)
			character = Resource.reborn.getSubimage(180, 1184, 40, 41);
		else if (countPic[4] == 5)
			character = Resource.reborn.getSubimage(224, 1183, 38, 41);
		else if (countPic[4] == 6)
			character = Resource.reborn.getSubimage(269, 1184, 38, 41);
		else if (countPic[4] == 7)
			character = Resource.reborn.getSubimage(312, 1184, 38, 42);
		else if (countPic[4] == 8)
			character = Resource.reborn.getSubimage(353, 1185, 40, 39);
		else if (countPic[4] == 9)
			character = Resource.rebornBomb1.getSubimage(0, 0, 113, 59);
		else if (countPic[4] == 10)
			character = Resource.rebornBomb2.getSubimage(0, 0, 113, 59);
		else if (countPic[4] == 11)
			character = Resource.rebornBomb3.getSubimage(0, 0, 113, 59);
		countPic[4]++;
		if (countPic[4] >= 12) {
			countPic[4] = 0;
			isSuperAttack = false;
			Time.isAlreadyStop = false;
		}
	}

	@Override
	public void picLoseUpdate() {
		if (!lose)
			return;
		if (countPic[3] == 0) {
			character = Resource.reborn.getSubimage(495, 727, 38, 38);
			countPic[3]++;
		} else if (countPic[3] == 1)
			character = Resource.reborn.getSubimage(542, 727, 38, 38);
	}

	@Override
	public int getZ() {
		return 0;
	}
}

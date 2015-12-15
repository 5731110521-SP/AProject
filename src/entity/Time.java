package entity;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import org.w3c.dom.NamedNodeMap;

import character.Character;
import render.GameScreen;
import render.IRenderable;
import render.Login;
import render.Resource;

public class Time implements IRenderable {
	public static boolean isPlay, isEnd, isHasWinner, isAlreadyStop, isStop;
	private boolean isUpdate;
	private double startTime;
	private int timeEnd, time, state;
	private String winner;
	private BufferedImage image;
	private static int playeri;

	static {
		isPlay = false;
		isEnd = false;

	}

	public Time() {
		state = 0;
		startTime = System.nanoTime() / 1000000000;
		timeEnd = 120;
		winner = "";
		time = timeEnd;
		isPlay = false;
		isEnd = false;
		isHasWinner = false;
		isAlreadyStop = false;
		isStop = false;
		image = Resource.ss[0];
	}

	public void getWinnerAn() {
		int i = GameLogic.getWinner();
		if (i == 1) {
			winner = "The winner is " + Login.player[0].getName();
		} else if (i == 2) {
			winner = "The winner is " + Login.player[1].getName();
		} else {
			winner = "Draw";
		}
	}

	@Override
	public void draw(Graphics2D g) {
		if (isStop) {
			g.drawImage(Resource.center, 0, 0, null);
			g.setFont(new Font("Tahoma", Font.BOLD, 40));
			FontMetrics fm = g.getFontMetrics();
			Rectangle2D r2 = fm.getStringBounds("PAUSE", g);
			g.drawString("PAUSE", GameScreen.width / 2 - (int) r2.getWidth() / 2, GameScreen.height / 2);
			g.drawImage(Resource.timePoint, (int) (165 + 295 - ((time * 1.0) / timeEnd) * 295), 5, null);
			return;
		}

		if (state == timeEnd + 16) {
			g.drawImage(image, 0, 0, null);
			return;
		}

		if (state >= 1 && state <= 4 || state >= timeEnd + 5 && state <= timeEnd + 15) {
			g.drawImage(Resource.center, 0, 0, null);
		}

		g.setFont(new Font("Tahoma", Font.BOLD, 40));
		g.setColor(Color.BLACK);
		FontMetrics fm = g.getFontMetrics();
		Rectangle2D r2 = fm.getStringBounds("3", g);
		if (state == 1) {
			g.drawString("3", GameScreen.width / 2 - (int) r2.getWidth() / 2, GameScreen.height / 2);
		} else if (state == 2) {
			g.drawString("2", GameScreen.width / 2 - (int) r2.getWidth() / 2, GameScreen.height / 2);
		} else if (state == 3) {
			g.drawString("1", GameScreen.width / 2 - (int) r2.getWidth() / 2, GameScreen.height / 2);
		} else if (state == 4) {
			r2 = fm.getStringBounds("Start", g);
			g.drawString("Start", GameScreen.width / 2 - (int) r2.getWidth() / 2, GameScreen.height / 2);
		} else if (state == timeEnd + 5) {
			r2 = fm.getStringBounds("Time out", g);
			g.drawString("Time out", GameScreen.width / 2 - (int) r2.getWidth() / 2, GameScreen.height / 2);
		} else if ((state >= timeEnd + 6 && state <= timeEnd + 8) || (state >= timeEnd + 12 && state <= timeEnd + 14)) {
			r2 = fm.getStringBounds(winner, g);
			g.drawString(winner, GameScreen.width / 2 - (int) r2.getWidth() / 2, GameScreen.height / 2);
		} else if (state == timeEnd + 11) {
			r2 = fm.getStringBounds("KO", g);
			g.drawString("KO", GameScreen.width / 2 - (int) r2.getWidth() / 2, GameScreen.height / 2);
		}
		g.drawImage(Resource.timePoint, (int) (165 + 295 - ((time * 1.0) / timeEnd) * 295), 5, null);
	}

	@Override
	public void update() {
		if (isStop)
			return;

		if (((Character) GameLogic.character[0]).isSuperAttack() && !isPlay && !isAlreadyStop) {
			playeri = 0;
			image = Resource.ss[((Character) GameLogic.character[0]).indexC];
			stateSuperAttack();
			return;

		} else if (((Character) GameLogic.character[1]).isSuperAttack() && !isPlay && !isAlreadyStop) {
			playeri = 1;
			image = Resource.ss[((Character) GameLogic.character[1]).indexC];
			AffineTransform at = new AffineTransform();
			at = AffineTransform.getScaleInstance(-1, 1);
			at.translate(-image.getWidth(null), 0);
			AffineTransformOp op = new AffineTransformOp(at, AffineTransformOp.TYPE_BICUBIC);
			image = op.filter(image, null);
			stateSuperAttack();
			return;
		}

		double lastTime = System.nanoTime() / 1000000000;
		if (lastTime - startTime >= 1 && !isEnd) {
			state++;
			startTime = lastTime;
			if (state <= timeEnd + 4 && state >= 5 && !isHasWinner)
				time--;
			if (state <= 3 && state >= 1)
				Resource.count.play();
			if (state == timeEnd + 11) {
				Resource.bgsound[GameScreen.indexBgm].stop();
				Resource.ko.play();
			}
			if (state == timeEnd + 4) {
				Resource.bgsound[GameScreen.indexBgm].stop();
				Resource.timeout.play();
			}
		}
		if (isHasWinner) {
			isPlay = false;
			if (state < timeEnd + 4)
				state = timeEnd + 10;
			else if (state == timeEnd + 11)
				getWinnerAn();
			else if (state == timeEnd + 15)
				isEnd = true;
		} else if (state < 4) {
			isPlay = false;
		} else if (state < timeEnd + 4) {
			isPlay = true;
		} else if (state == timeEnd + 4) {
			isPlay = false;
			getWinnerAn();
		} else if (state == timeEnd + 9) {
			isEnd = true;
		}

	}

	public void stateSuperAttack() {

		isAlreadyStop = true;
		if (!isUpdate) {
			int prevState = state;
			state = timeEnd + 16;
			isUpdate = true;
			new Thread(new Runnable() {

				@Override
				public void run() {
					synchronized (Login.player[playeri]) {
						try {
							Login.player[playeri].wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					isPlay = true;
					isUpdate = false;
					state = prevState;
				}
			}).start();
		}

	}

	@Override
	public boolean getFlashing() {
		return false;
	}

	@Override
	public boolean isVisible() {
		return true;
	}

	@Override
	public int getZ() {
		return Integer.MAX_VALUE;
	}
}

package character;

import entity.Player;
import render.IRenderable;

public interface Playable extends IRenderable {
	public boolean isLose();
	public void shoot(Character c);
	public void superAttack();
	public void attack(Character c);
}

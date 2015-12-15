package character;

import entity.Player;
import render.IRenderable;

public interface Playable extends IRenderable {
	public void attack(Character c);
	public void shoot(Character c);
	public void superAttack();
	public boolean isLose();
}

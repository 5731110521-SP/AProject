package input;

public class InputUtility {
	private static boolean[] keyPressed = new boolean[256];
	public static int mouseX,mouseY;
	public static boolean mouseOnScreen = true;
	private static boolean isLeftDown = false;
	private static boolean isLeftClickedLastTick = false;
	
	public static boolean getKeyPressed(int key){
		if(key<0 || key>=256) return false;
		else return keyPressed[key];
	}
	
	public static void setKeyPressed(int key,boolean pressed){
		if(key<0 || key>=256) return;
		else InputUtility.keyPressed[key] = pressed;
	}
	
	public static void mouseLeftDown(){
		isLeftDown = true;
		isLeftClickedLastTick = true;
	}
	
	public static void mouseLeftRelease(){
		isLeftDown = false;
	}
	
	public static boolean isLeftClickTriggered(){
		return isLeftClickedLastTick;
	}
	
	public static void updateInputState(){
		isLeftClickedLastTick = false;
	}
	
}

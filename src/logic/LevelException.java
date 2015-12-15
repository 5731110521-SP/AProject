package logic;

import javax.swing.JOptionPane;

public class LevelException extends Exception{
	public LevelException(int level) {
		if(level==-1){
			JOptionPane.showMessageDialog(null,"This character is already choosed","Choose error",JOptionPane.ERROR_MESSAGE);
		}else{
			JOptionPane.showMessageDialog(null,"Your Level is not enough.\nLevel Required for this character is "+level,"Level error",JOptionPane.ERROR_MESSAGE);
		}
	}
}

package logic;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class MyException extends Exception{
	public MyException(JPanel p,JLabel l) {
		if(l.getForeground()==Color.WHITE){
			l.setForeground(Color.BLACK);
		}else{
			l.setForeground(Color.WHITE);
		}
		p.validate();
	}
}

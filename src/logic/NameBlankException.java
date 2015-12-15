package logic;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class NameBlankException extends Exception {
	public NameBlankException() {
		JOptionPane.showMessageDialog(null, "Please fill your name.", "Blank", JOptionPane.ERROR_MESSAGE);
	}

	public NameBlankException(int i) {
		JOptionPane.showMessageDialog(null, "This name already login by Player " + i, "Blank",
				JOptionPane.ERROR_MESSAGE);
	}
}

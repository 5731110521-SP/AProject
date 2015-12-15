package logic;

import javax.swing.JOptionPane;

public class SettingException extends Exception {
	public SettingException() {
		JOptionPane.showMessageDialog(null, "This key already set.", "ERROR", JOptionPane.ERROR_MESSAGE);
	}
}

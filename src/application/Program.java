package application;

import java.awt.EventQueue;

import view.gui;

public class Program {
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gui window = new gui();
					window.frame.setVisible(true);
					window.readJTable();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


}

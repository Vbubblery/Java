package main;
import java.awt.EventQueue;

import view.MainFrame;

/**
 * Launch the application.
 */
public class MainHospital {
	public static void main(String[] args) {
//		Multithreading
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//Laugh main thread
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}

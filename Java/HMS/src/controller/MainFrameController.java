package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.MainFrame;
import view.SelectionButtonFrame;

public class MainFrameController implements ActionListener {
	// Judge which mode choosed before
	public static String WhichButton;

	private MainFrame v;

	// Constructor
	public MainFrameController(MainFrame v) {
		this.v = v;
	}

	// Button click Listener
	@Override
	public void actionPerformed(ActionEvent e) {
		// Click "PATIENT" button.
		if (e.getSource() == v.getPATIENT_Button()) {
			try {
				// Serialization new frame.
				SelectionButtonFrame frame = new SelectionButtonFrame();
				frame.setVisible(true);
				WhichButton = "patient";
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		// Click "TREATMENT" button.
		else {
			try {
				// Serialization new frame.
				SelectionButtonFrame frame = new SelectionButtonFrame();
				frame.setVisible(true);
				WhichButton = "treatment";
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		v.setVisible(false);
	}
}

package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.AddFrame;
import view.DisplayFrame;
import view.SelectionButtonFrame;

public class SelectionButtonFrameController implements ActionListener {
	private SelectionButtonFrame v;

	// Constructor
	public SelectionButtonFrameController(SelectionButtonFrame v) {
		this.v = v;
	}

	// Button click Listener
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == v.getAdd()) {
			// Serialization new frame.
			AddFrame frame = new AddFrame();
			frame.setVisible(true);
		} else if (e.getSource() == v.getDisplay()) {
			// Serialization new frame.
			DisplayFrame frame = new DisplayFrame();
			frame.setVisible(true);
		} else if (e.getSource() == v.getExit()) {
			// Exit the program.
			System.exit(0);
		}
	}

}

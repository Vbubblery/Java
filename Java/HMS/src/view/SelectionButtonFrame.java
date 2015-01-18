package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import controller.SelectionButtonFrameController;

public class SelectionButtonFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JButton Add;
	JButton Display;
	JButton Exit;
	/**
	 * Create the frame.
	 */
	public SelectionButtonFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 150);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		SelectionButtonFrameController c = new SelectionButtonFrameController(this);
		
		Add = new JButton("Add");
		Add.addActionListener(c);
		Add.setBounds(10, 43, 93, 23);
		contentPane.add(Add);
		
		Display = new JButton("Display");
		Display.setBounds(149, 43, 93, 23);
		contentPane.add(Display);
		Display.addActionListener(c);
		
		Exit = new JButton("Exit");
		Exit.setBounds(294, 43, 93, 23);
		contentPane.add(Exit);
		Exit.addActionListener(c);
	}
	public JButton getAdd() {
		return Add;
	}
	public void setAdd(JButton add) {
		Add = add;
	}
	public JButton getDisplay() {
		return Display;
	}
	public void setDisplay(JButton display) {
		Display = display;
	}
	public JButton getExit() {
		return Exit;
	}
	public void setExit(JButton exit) {
		Exit = exit;
	}
}

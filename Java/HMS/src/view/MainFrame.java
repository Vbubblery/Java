package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import controller.MainFrameController;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JButton PATIENT_Button;
	JButton TREATMENT_Button;

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 150);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		MainFrameController c = new MainFrameController(this);
		TREATMENT_Button = new JButton("TREATMENT");
		TREATMENT_Button.setBounds(242, 26, 122, 51);
		TREATMENT_Button.addActionListener(c);
		PATIENT_Button = new JButton("PATIENT");
		PATIENT_Button.setBounds(46, 26, 104, 51);
		PATIENT_Button.addActionListener(c);
		contentPane.setLayout(null);
		contentPane.add(TREATMENT_Button);
		contentPane.add(PATIENT_Button);
	}

	public JButton getPATIENT_Button() {
		return PATIENT_Button;
	}

	public void setPATIENT_Button(JButton pATIENT_Button) {
		PATIENT_Button = pATIENT_Button;
	}

	public JButton getTREATMENT_Button() {
		return TREATMENT_Button;
	}

	public void setTREATMENT_Button(JButton tREATMENT_Button) {
		TREATMENT_Button = tREATMENT_Button;
	}

}

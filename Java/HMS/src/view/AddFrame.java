package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import controller.AddFrameController;
import controller.MainFrameController;

public class AddFrame extends JFrame {
	// Declaring variables
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblPatientregistrationnumber;
	private JLabel lblGender;
	private JLabel lblDateofbirth;
	private JLabel lblAddress;
	private JLabel lblContactno;

	private JTextField PatientName_Text;
	private JTextField PatientRegistrationNumber_Text;
	private JTextField Gender_Text;
	private JTextField DateofBirth_Text;
	private JTextField Address_Text;
	private JTextField ContactNo_Text;

	JLabel lblTreatmentnumber;
	JLabel lblTreatmentname;
	JLabel lblDoctorincharge;
	JLabel lblRoomno;

	private JTextField lblTreatmentnumber_Text;
	private JTextField lblTreatmentname_Text;
	private JTextField lblDoctorincharge_Text;
	private JTextField lblRoomno_Text;

	JButton btnSubmit;
	JButton btnCheck;

	JLabel alert;

	/**
	 * Create the frame.
	 */
	public class WhichBtn extends MainFrameController {

		public WhichBtn(MainFrame v) {
			super(v);
			// TODO Auto-generated constructor stub
		}
	}

	public AddFrame() {
		// Close Operation
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Frame position and size.
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		// No layout
		contentPane.setLayout(null);
		// Link to controller
		AddFrameController c = new AddFrameController(this);
		if (WhichBtn.WhichButton == "patient") {
			JLabel PatientName = new JLabel("Patient-Name");
			PatientName.setBounds(26, 32, 92, 15);
			contentPane.add(PatientName);

			lblPatientregistrationnumber = new JLabel(
					"Patient-Registration-Number");
			lblPatientregistrationnumber.setBounds(26, 57, 177, 15);
			contentPane.add(lblPatientregistrationnumber);

			lblGender = new JLabel("Gender");
			lblGender.setBounds(26, 82, 54, 15);
			contentPane.add(lblGender);

			lblDateofbirth = new JLabel("Date-of-Birth");
			lblDateofbirth.setBounds(26, 107, 92, 15);
			contentPane.add(lblDateofbirth);

			lblAddress = new JLabel("Address");
			lblAddress.setBounds(26, 132, 92, 15);
			contentPane.add(lblAddress);

			lblContactno = new JLabel("Contact-No");
			lblContactno.setBounds(26, 157, 71, 15);
			contentPane.add(lblContactno);

			PatientName_Text = new JTextField();
			PatientName_Text.setBounds(226, 29, 100, 21);
			contentPane.add(PatientName_Text);
			PatientName_Text.setColumns(10);

			PatientRegistrationNumber_Text = new JTextField();
			PatientRegistrationNumber_Text.setBounds(226, 54, 100, 21);
			contentPane.add(PatientRegistrationNumber_Text);
			PatientRegistrationNumber_Text.setColumns(10);

			Gender_Text = new JTextField();
			Gender_Text.setBounds(226, 79, 100, 21);
			contentPane.add(Gender_Text);
			Gender_Text.setColumns(10);

			DateofBirth_Text = new JTextField();
			DateofBirth_Text.setBounds(226, 104, 100, 21);
			contentPane.add(DateofBirth_Text);
			DateofBirth_Text.setColumns(10);

			Address_Text = new JTextField();
			Address_Text.setBounds(226, 129, 100, 21);
			contentPane.add(Address_Text);
			Address_Text.setColumns(10);

			ContactNo_Text = new JTextField();
			ContactNo_Text.setBounds(226, 154, 100, 21);
			contentPane.add(ContactNo_Text);
			ContactNo_Text.setColumns(10);

		} else {

			lblTreatmentnumber = new JLabel("Treatment-Number");
			lblTreatmentnumber.setBounds(35, 40, 128, 15);
			contentPane.add(lblTreatmentnumber);

			lblTreatmentname = new JLabel("Treatment-Name");
			lblTreatmentname.setBounds(35, 65, 117, 15);
			contentPane.add(lblTreatmentname);

			lblDoctorincharge = new JLabel("Doctor-in-charge");
			lblDoctorincharge.setBounds(35, 90, 117, 15);
			contentPane.add(lblDoctorincharge);

			lblRoomno = new JLabel("Room-No");
			lblRoomno.setBounds(35, 115, 117, 15);
			contentPane.add(lblRoomno);

			lblTreatmentnumber_Text = new JTextField();
			lblTreatmentnumber_Text.setBounds(212, 37, 100, 21);
			contentPane.add(lblTreatmentnumber_Text);
			lblTreatmentnumber_Text.setColumns(10);

			lblTreatmentname_Text = new JTextField();
			lblTreatmentname_Text.setBounds(212, 62, 100, 21);
			contentPane.add(lblTreatmentname_Text);
			lblTreatmentname_Text.setColumns(10);

			lblDoctorincharge_Text = new JTextField();
			lblDoctorincharge_Text.setBounds(212, 87, 100, 21);
			contentPane.add(lblDoctorincharge_Text);
			lblDoctorincharge_Text.setColumns(10);

			lblRoomno_Text = new JTextField();
			lblRoomno_Text.setBounds(212, 112, 100, 21);
			contentPane.add(lblRoomno_Text);
			lblRoomno_Text.setColumns(10);
		}

		btnSubmit = new JButton("Submit");
		btnSubmit.setEnabled(false);
		btnSubmit.addActionListener(c);
		btnSubmit.setBounds(230, 228, 93, 23);
		contentPane.add(btnSubmit);

		btnCheck = new JButton("Check");
		btnCheck.addActionListener(c);
		btnCheck.setBounds(82, 228, 93, 23);
		contentPane.add(btnCheck);

		alert = new JLabel();
		alert.setBounds(65, 203, 339, 15);
		contentPane.add(alert);

		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	public JLabel getAlert() {
		return alert;
	}

	public void setAlert(JLabel alert) {
		this.alert = alert;
	}

	public JTextField getPatientName_Text() {
		return PatientName_Text;
	}

	public void setPatientName_Text(JTextField patientName_Text) {
		PatientName_Text = patientName_Text;
	}

	public JTextField getPatientRegistrationNumber_Text() {
		return PatientRegistrationNumber_Text;
	}

	public void setPatientRegistrationNumber_Text(
			JTextField patientRegistrationNumber_Text) {
		PatientRegistrationNumber_Text = patientRegistrationNumber_Text;
	}

	public JTextField getGender_Text() {
		return Gender_Text;
	}

	public void setGender_Text(JTextField gender_Text) {
		Gender_Text = gender_Text;
	}

	public JTextField getDateofBirth_Text() {
		return DateofBirth_Text;
	}

	public void setDateofBirth_Text(JTextField dateofBirth_Text) {
		DateofBirth_Text = dateofBirth_Text;
	}

	public JTextField getAddress_Text() {
		return Address_Text;
	}

	public void setAddress_Text(JTextField address_Text) {
		Address_Text = address_Text;
	}

	public JTextField getContactNo_Text() {
		return ContactNo_Text;
	}

	public void setContactNo_Text(JTextField contactNo_Text) {
		ContactNo_Text = contactNo_Text;
	}

	public JTextField getLblTreatmentnumber_Text() {
		return lblTreatmentnumber_Text;
	}

	public void setLblTreatmentnumber_Text(JTextField lblTreatmentnumber_Text) {
		this.lblTreatmentnumber_Text = lblTreatmentnumber_Text;
	}

	public JTextField getLblTreatmentname_Text() {
		return lblTreatmentname_Text;
	}

	public void setLblTreatmentname_Text(JTextField lblTreatmentname_Text) {
		this.lblTreatmentname_Text = lblTreatmentname_Text;
	}

	public JTextField getLblDoctorincharge_Text() {
		return lblDoctorincharge_Text;
	}

	public void setLblDoctorincharge_Text(JTextField lblDoctorincharge_Text) {
		this.lblDoctorincharge_Text = lblDoctorincharge_Text;
	}

	public JTextField getLblRoomno_Text() {
		return lblRoomno_Text;
	}

	public void setLblRoomno_Text(JTextField lblRoomno_Text) {
		this.lblRoomno_Text = lblRoomno_Text;
	}

	public JButton getBtnSubmit() {
		return btnSubmit;
	}

	public void setBtnSubmit(JButton btnSubmit) {
		this.btnSubmit = btnSubmit;
	}

	public JButton getBtnCheck() {
		return btnCheck;
	}

	public void setBtnCheck(JButton btnCheck) {
		this.btnCheck = btnCheck;
	}
}

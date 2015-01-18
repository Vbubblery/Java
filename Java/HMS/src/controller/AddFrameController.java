package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.AryCollection;
import model.Patient;
import model.Treatment;

import view.AddFrame;
import view.MainFrame;

public class AddFrameController extends AryCollection implements ActionListener {
	// Declaring variables
	private AddFrame v;
	Patient p;
	Treatment t;

	// Constructor
	public AddFrameController(AddFrame v) {
		this.v = v;
	}

	// Button click Listener
	@Override
	public void actionPerformed(ActionEvent e) {
		// Click "Check" button.
		if (e.getSource() == v.getBtnCheck()) {
			if (WhichBtn.WhichButton == "patient") {
				// Add data into a new patient object
				try {
					p = new Patient();
					p.setAddress(v.getAddress_Text().getText());
					p.setContactNumber(Integer.parseInt(v.getContactNo_Text()
							.getText()));
					p.setDateofBirth(v.getDateofBirth_Text().getText());
					p.setGender(v.getGender_Text().getText());
					p.setPatientName(v.getPatientName_Text().getText());
					p.setPatientRegistrationNumber(Integer.parseInt(v
							.getPatientRegistrationNumber_Text().getText()));
					v.getBtnSubmit().setEnabled(true);
				} catch (Exception e1) {
					// when the data is error
					v.getAlert().setText("Error: " + e1.getMessage());
					v.getBtnSubmit().setEnabled(false);
				}
			}
			// Add data into a new treatment object
			else {
				try {
					t = new Treatment();
					t.setDoctorincharge(v.getLblDoctorincharge_Text().getText());
					t.setRoomNumber(Integer.parseInt(v.getLblRoomno_Text()
							.getText()));
					t.setTreatmentName(v.getLblTreatmentname_Text().getText());
					t.setTreatmentNumber(Integer.parseInt(v
							.getLblTreatmentnumber_Text().getText()));
					v.getBtnSubmit().setEnabled(true);
				} catch (Exception e1) {
					v.getAlert().setText("Error: " + e1.getMessage());
					v.getBtnSubmit().setEnabled(false);
				}
			}
		} else {
			if (WhichBtn.WhichButton == "patient") {
				AddNewPatient(p);
			} else {
				AddNewTreatment(t);
			}
			// Close the window after submit.
			v.dispose();
		}
	}

	public class WhichBtn extends MainFrameController {

		public WhichBtn(MainFrame v) {
			super(v);
			// TODO Auto-generated constructor stub
		}
	}

}

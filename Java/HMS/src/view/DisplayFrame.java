package view;

import java.awt.BorderLayout;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.JTable;

import controller.MainFrameController;

import main.ArrayList;
import main.AryCollection;
import model.Patient;
import model.Treatment;
import javax.swing.JLabel;

public class DisplayFrame extends JFrame {
	// Declaring variables
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private static int size;

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public DisplayFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		table = new JTable();
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		if (WhichBtn.WhichButton == "patient") {
			String[] Names = { "Patient-Name", "Patient-Registration-Number",
					"Gender", "Date-of-Birth", "Address", "Contact-No" };
			dtm.setColumnIdentifiers(Names);
			//Table title
			dtm.addRow(Names);
			ArrayList<Patient> Patients = AryColl.patient;
			size = Patients.GetSize();
			//Traversing add into the table
			for (int i = 0; i < Patients.GetSize(); i++) {
				Vector v = new Vector();
				v.add(Patients.get(i).getPatientName().toString());
				v.add(Patients.get(i).getPatientRegistrationNumber());
				v.add(Patients.get(i).getGender().toString());
				v.add(Patients.get(i).getDateofBirth().toString());
				v.add(Patients.get(i).getAddress().toString());
				v.add(Patients.get(i).getContactNumber());
				dtm.addRow(v);
			}
		} else {
			String[] Names = { "Treatment-Number", "Treatment-Name",
					"Doctor-in-charge", "Room-No" };
			dtm.setColumnIdentifiers(Names);
			dtm.addRow(Names);
			ArrayList<Treatment> treatments = AryColl.treatment;
			size = treatments.GetSize();
			for (int i = 0; i < treatments.GetSize(); i++) {
				Vector v = new Vector();
				v.add(treatments.get(i).getTreatmentNumber());
				v.add(treatments.get(i).getTreatmentName().toString());
				v.add(treatments.get(i).getDoctorincharge().toString());
				v.add(treatments.get(i).getRoomNumber());
				dtm.addRow(v);
			}
		}
		//set the width of 2ed col.
		TableColumn firsetColumn = table.getColumnModel().getColumn(1);
		firsetColumn.setPreferredWidth(200);
		firsetColumn.setMaxWidth(200);
		firsetColumn.setMinWidth(200);
		table.setEnabled(false);
		table.setBounds(0, 0, 684, 225);
		contentPane.add(table.getTableHeader(), BorderLayout.PAGE_START);
		contentPane.setLayout(null);
		contentPane.add(table);
		JLabel lblTheNumberOf = new JLabel("The Number of "
				+ WhichBtn.WhichButton + "  " + size);
		lblTheNumberOf.setBounds(10, 235, 156, 15);
		contentPane.add(lblTheNumberOf);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public class AryColl extends AryCollection {

	}

	public class WhichBtn extends MainFrameController {

		public WhichBtn(MainFrame v) {
			super(v);
			// TODO Auto-generated constructor stub
		}
	}
}

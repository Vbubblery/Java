package main;

import model.Patient;
import model.Treatment;

public class AryCollection {
	//// Declaring variables (Will be used in "display" and "add")
	public static ArrayList<Patient> patient = new ArrayList<Patient>();
	public static ArrayList<Treatment> treatment = new ArrayList<Treatment>();

	public void AddNewPatient(Patient p) {
		patient.add(p);
	}

	public void AddNewTreatment(Treatment t) {
		treatment.add(t);
	}

}

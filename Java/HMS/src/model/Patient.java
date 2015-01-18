/**
 * 
 */
package model;

public class Patient {
	public String PatientName;
	public int PatientRegistrationNumber;
	public String Gender;
	public String DateofBirth;
	public String Address;
	public int ContactNumber;

	public String getPatientName() {
		return PatientName;
	}

	public void setPatientName(String patientName) {
		PatientName = patientName;
	}

	public int getPatientRegistrationNumber() {
		return PatientRegistrationNumber;
	}

	public void setPatientRegistrationNumber(int patientRegistrationNumber) {
		PatientRegistrationNumber = patientRegistrationNumber;
	}

	public String getGender() {
		return Gender;
	}

	public void setGender(String gender) {
		Gender = gender;
	}

	public String getDateofBirth() {
		return DateofBirth;
	}

	public void setDateofBirth(String dateofBirth) {
		DateofBirth = dateofBirth;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public int getContactNumber() {
		return ContactNumber;
	}

	public void setContactNumber(int contactNumber) {
		ContactNumber = contactNumber;
	}

}

package projekt;

import java.util.Date;


//na razie tutaj
//cos dorzuce
public class Appointment {

	private Date date;
	private String doctorsFirstName;
	private String doctorsLastName;
	private String doctorsSpecialization;
	
	public Appointment(Date date, String doctorsFirstName, 
			String doctorsLastName, String doctorsSpecialization) {
		this.date = date;
		this.doctorsFirstName = doctorsFirstName;
		this.doctorsLastName = doctorsLastName;
		this.doctorsSpecialization = doctorsSpecialization;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getDoctorsFirstName() {
		return doctorsFirstName;
	}
	
	public void setDoctorsFirstName(String doctorsFirstName) {
		this.doctorsFirstName = doctorsFirstName;
	}
	
	public String getDoctorsLastName() {
		return doctorsLastName;
	}
	
	public void setDoctorsLastName(String doctorsLastName) {
		this.doctorsLastName = doctorsLastName;
	}
	
	public String getDoctorsSpecialization() {
		return doctorsSpecialization;
	}
	
	public void setDoctorsSpecialization(String doctorsSpecialization) {
		this.doctorsSpecialization = doctorsSpecialization;
	}
}

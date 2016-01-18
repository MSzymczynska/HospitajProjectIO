package projekt;

import java.util.Date;

public class Interview {

	private Date date;
	private String doctorsFirstName;
	private String doctorsLastName;
	private String information;

	public Interview(Date date, String doctorsFirstName, 
			String doctorsLastName, String information) {
		this.date = date;
		this.doctorsFirstName = doctorsFirstName;
		this.doctorsLastName = doctorsLastName;
		this.information = information;
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

	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}
}

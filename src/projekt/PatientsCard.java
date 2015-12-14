package data;
import java.util.ArrayList;
import java.util.Date;

public class PatientsCard {

	private ArrayList<Appointment> appointments;
	private ArrayList<Interview> interviews;
	private ArrayList<String> patientsConditions;

	public PatientsCard() {
		appointments = new ArrayList<Appointment>();
		interviews = new ArrayList<Interview>();
		patientsConditions = new ArrayList<String>();
	}

	public void addPatientsAppointment(Date date, String doctorsFirstName, 
			String doctorsLastName, String doctorsSpecialization) {
		Appointment appointment = new Appointment(date, doctorsFirstName, 
				doctorsLastName, doctorsSpecialization);
		appointments.add(appointment);
	}

	public void addPatientsInterview(Date date, String doctorsFirstName, 
			String doctorsLastName, String information) {
		Interview interview = new Interview(date, doctorsFirstName, 
				doctorsLastName, information);
		interviews.add(interview);
	}

	public void addPatientsCondition(String condition) {
		patientsConditions.add(condition);
	}

	public ArrayList<Appointment> getAppointments() {
		return appointments;
	}

	public ArrayList<Interview> getInterviews() {
		return interviews;
	}

	public ArrayList<String> getPatientsConditions() {
		return patientsConditions;
	}
}

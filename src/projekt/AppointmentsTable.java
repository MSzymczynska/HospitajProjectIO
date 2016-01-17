package projekt;
import java.util.List;

import javax.swing.table.AbstractTableModel;


public class AppointmentsTable extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2060646979656777096L;
	private List<Appointment> list;
	private String[] columnNames = { "Data wizyty", "Imię doktora", "Nazwisko doktora", "Specjalizacja" };

	public AppointmentsTable(List<Appointment> list) {

		this.list = list;
	}

	@Override
	public int getColumnCount() {
		return 4;
	}

	@Override
	public int getRowCount() {
		return list.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object output = null;
		Appointment appointment = null;
		appointment = list.get(rowIndex);
		switch (columnIndex) {
		case 0:
			output = appointment.getDate();
			break;
		case 1:
			output = appointment.getDoctorsFirstName();
			break;
		case 2:
			output = appointment.getDoctorsLastName();
			break;
		case 3:
			output = appointment.getDoctorsSpecialization();
			break;
		}
		return output;
	}

	public String[] getColumnNames() {
		return columnNames;
	}

	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}

	public List<Appointment> getList() {
		return list;
	}
}

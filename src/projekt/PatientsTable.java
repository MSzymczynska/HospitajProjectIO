package projekt;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import java.util.List;

public class PatientsTable  extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Patient> list;
	private String[] columnNames = { "imie", "nazwisko", "id" };

	public PatientsTable(List<Patient> list) {

		this.list = list;
	}

	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public int getRowCount() {
		return list.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object output = null;
		Patient patient = null;
		patient = list.get(rowIndex);
		switch (columnIndex) {
		case 0:
			output = patient.getFirstName();
			break;
		case 1:
			output = patient.getLastName();
			break;
		case 2:
			output = patient.getIndex();
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

	public List<Patient> getList() {
		return list;
	}
}


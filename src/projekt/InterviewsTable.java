package projekt;
import java.util.List;

import javax.swing.table.AbstractTableModel;


public class InterviewsTable extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Interview> list;
	private String[] columnNames = { "Data", "Imiê doktora", "Nazwisko doktora", "Opis" };

	public InterviewsTable(List<Interview> list) {

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
		Interview interview = null;
		interview = list.get(rowIndex);
		switch (columnIndex) {
		case 0:
			output = interview.getDate();
			break;
		case 1:
			output = interview.getDoctorsFirstName();
			break;
		case 2:
			output = interview.getDoctorsLastName();
			break;
		case 3:
			output = interview.getInformation();
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

	public List<Interview> getList() {
		return list;
	}
}

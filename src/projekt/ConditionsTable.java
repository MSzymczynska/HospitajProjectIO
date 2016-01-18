package projekt;
import java.util.List;

import javax.swing.table.AbstractTableModel;


public class ConditionsTable extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4526018300843114763L;
	private List<String> list;
	private String[] columnNames = {"Aktualny stan pacjenta" };

	public ConditionsTable(List<String> list) {

		this.list = list;
	}

	@Override
	public int getColumnCount() {
		return 1;
	}

	@Override
	public int getRowCount() {
		return list.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object output = null;
		String condition = null;
		condition = list.get(rowIndex);
		switch (columnIndex) {
		case 0:
			output = condition;
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

	public List<String> getList() {
		return list;
	}
}

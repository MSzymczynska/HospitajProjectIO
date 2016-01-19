package projekt;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class MealsTable  extends AbstractTableModel {


	private static final long serialVersionUID = 4526018300843114763L;
	private List<String> list;
	private String[] columnNames = {"Zalecenia zywieniowe: " };

	public MealsTable(List<String> list) {

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
		String meals = null;
		meals = list.get(rowIndex);
		switch (columnIndex) {
		case 0:
			output = meals;
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

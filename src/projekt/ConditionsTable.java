package projekt;
import java.util.List;

import javax.swing.table.AbstractTableModel;


public class ConditionsTable extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4526018300843114763L;
	private List<Condition> list;
	private String[] columnNames = { "Data", "Stan pacjenta" };

	public ConditionsTable(List<Condition> list) {

		this.list = list;
	}

	@Override
	public int getColumnCount() {
		return 2;
	}

	@Override
	public int getRowCount() {
		return list.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object output = null;
		Condition condition = null;
		condition = list.get(rowIndex);
		switch (columnIndex) {
		case 0:
			output = condition.getDate();
			break;
		case 1:
			output = condition.getCondition();
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

	public List<Condition> getList() {
		return list;
	}
}

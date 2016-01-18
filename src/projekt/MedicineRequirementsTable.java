package projekt;
import java.util.List;

import javax.swing.table.AbstractTableModel;


public class MedicineRequirementsTable extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<MedicineRequirement> list;
	private String[] columnNames = { "Nazwa leku", "Iloœæ", "Sposób aplikowania leku" };

	public MedicineRequirementsTable(List<MedicineRequirement> list) {

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
		MedicineRequirement medicineRequirement = null;
		medicineRequirement = list.get(rowIndex);
		switch (columnIndex) {
		case 0:
			output = medicineRequirement.getMedicine();
			break;
		case 1:
			output = medicineRequirement.getAmount();
			break;
		case 2:
			output = medicineRequirement.getApplication();
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

	public List<MedicineRequirement> getList() {
		return list;
	}
}

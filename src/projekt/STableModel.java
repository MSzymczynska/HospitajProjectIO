/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Maciej
 */
public class STableModel extends AbstractTableModel {

	List wordsList;
	String headerList[] = new String[] { "Pid", "Nazwa", "Producent", "Iloœæ", "Termin wa¿noœci" };

	public STableModel(List list) {
		wordsList = list;
	}

	@Override
	public int getColumnCount() {
		return 5;
	}

	@Override
	public int getRowCount() {
		return wordsList.size();
	}

	// this method is called to set the value of each cell
	@Override
	public Object getValueAt(int row, int column) {
		ProductQuantity entity = null;
		entity = (ProductQuantity) wordsList.get(row);

		switch (column) {

		case 0:
			return entity.product.id;
		case 1:
			return entity.product.name;
		case 2:
			return entity.product.producer;
		case 3:
			return entity.quantity;
		case 4:
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			return sdf.format(entity.product.expirationDate);

		default:

			return "";
		}
		// This method will be used to display the name of columns

	}

	@Override
	public String getColumnName(int col) {
		return headerList[col];
	}

	void removeRow(int selectedRow) {
		wordsList.remove(selectedRow);
		this.fireTableDataChanged();
	}

}

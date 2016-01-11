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
public class OFOTableModel extends AbstractTableModel {

    List wordsList;
    String headerList[] = new String[]{"Oid", "Nazwa", "Producent", "Iloœæ", "Kiedy", "Sk¹d", "Dok¹d"};

    public OFOTableModel(List list) {
        wordsList = list;
    }

    @Override
    public int getColumnCount() {
        return 7;
    }

    @Override
    public int getRowCount() {
        return wordsList.size();
    }

// this method is called to set the value of each cell
    @Override
    public Object getValueAt(int row, int column) {
        ProductOrder entity = null;
		entity = (ProductOrder) wordsList.get(row);

        switch (column) {

            case 0:
                return entity.id;
            case 1:
                return entity.productQuantity.product.name;
            case 2:
                return entity.productQuantity.product.producer;
            case 3:
                return entity.productQuantity.quantity;
            case 4:
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                return sdf.format(entity.date);
            case 5:
                return entity.from;
            case 6:
                return entity.to;

            default:

                return "";
        }
        //This method will be used to display the name of columns

    }

    @Override
    public String getColumnName(int col) {
        return headerList[col];
    }
    
    public void removeRow(int selectedRow) {
        wordsList.remove(selectedRow);
        this.fireTableDataChanged();
    }

}

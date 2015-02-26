package MyTableModel;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;
// Notas:
// IMPORTANTE -> ONCE THE MODEL IS CREATED THE NUMBER OF COLUNS CAN NOT CHANGE!!!
// long values like set long values

public class MyTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;

	// Atributes -------------------------------------------------------
	private String[] columnNames = null;

	private ArrayList<Object[]> data = new ArrayList<Object[]>();

	private boolean[] isEditable = null;

	// constructor ----------------------------------------------------

	public MyTableModel(String[] ColumNames) {
		this.columnNames = ColumNames;
		isEditable = new boolean[columnNames.length];// creating the boolean array in java is initialized by false
	}

	// temp seting data -----------------------------------------------------

	public void setData(ArrayList<Object[]> data) {
		this.data = data; // iguala o array data para ter a matriz da jtable (tenho de ter cuidado ao adicionar pois nao deteta erros xD)
		// celulas que sao editaveis
		for (int i = 0; i < columnNames.length; i++) {
			isEditable[i] = false;// false by default
		}
	}

	// Adding/Delete Row Methods --------------------------------------------
	public void addRow(Object[] dataRow) {
		if (dataRow.length != columnNames.length)
			return;
		data.add(dataRow);
		fireTableRowsInserted(data.size() - 1, data.size() - 1);
	}

	public void RemoveRow_PosArray(int row) {
		if (row < 0 || row >= data.size())
			return;
		data.remove(row);
		fireTableRowsDeleted(row, row);
	}

	// -----------------------------------------------------------------
	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	public final Object[] longValues = { "Artigo Artigo Artigo", new Integer(1000), "Kg", // to a pensar fazer fora e enviar quando faço setData() com os maiores valores de cada coluna data
			new Integer(20), };

	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

	public int getColumnCount() {
		return columnNames.length;// only gives error if a null column array is send to the constructor
	}

	public int getRowCount() {
		return data.size();// there is no way to give error because the array list is always present
	}

	public String getColumnName(int col) {
		if (col < 0 || col >= columnNames.length)
			return "";
		return columnNames[col];
	}

	public Object getValueAt(int row, int col) {
		if (row < 0 || row >= data.size())
			return "";// as default returns empty string (it is safer that returns null or even ArrayOutOfBonds)
		if (col < 0 || col >= data.get(0).length)
			return "";// does not have the problem of null array because if the arraylist has 1 or more lines there is an instance of an array in the lines xD
		return data.get(row)[col] == null ? "" : data.get(row)[col];// The comboBox in the table can give null if empty so this will prevent wall the possible error that can occur whit null
	}

	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}

	public boolean isCellEditable(int row, int col) {// to choose the table cells that are editable xD
		if (col < 0 || col >= isEditable.length)
			return false;
		return isEditable[col];// i am only interested in the col value need to be this way because overrides the one in
	}

	public void setColumCellEditor(int col, boolean value) {
		if (col < 0 || col >= isEditable.length)
			return;
		isEditable[col] = value;
	}

	public void setValueAt(Object value, int row, int col) {
		if (row < 0 || row >= data.size())
			return;
		if (col < 0 || col >= data.get(0).length)
			return;
		data.get(row)[col] = value;
		fireTableCellUpdated(row, col);
	}

	public void deleteData() {// Deletes all the table lines
		int rows = getRowCount();
		if (rows == 0) {
			return;
		}

		data.clear();// removes all the the table lines

		for (int i = 0; i < columnNames.length; i++) {
			isEditable[i] = false;// false by default
		}

		fireTableRowsDeleted(0, rows - 1);
	}
}

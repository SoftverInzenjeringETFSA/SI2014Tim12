package ba.unsa.etf.si.tim12.ui.components;

import java.util.Vector;

import javax.swing.table.DefaultTableModel;

@SuppressWarnings({"rawtypes", "unchecked"})
public class UneditableTableModel extends DefaultTableModel {

	public UneditableTableModel() {
		super();
	}

	public UneditableTableModel(int rowCount, int columnCount) {
		super(rowCount, columnCount);
		
	}

	public UneditableTableModel(Object[] columnNames, int rowCount) {
		super(columnNames, rowCount);
		
	}

	public UneditableTableModel(Object[][] data, Object[] columnNames) {
		super(data, columnNames);
		
	}

	
	public UneditableTableModel(Vector columnNames, int rowCount) {
		super(columnNames, rowCount);
		
	}

	public UneditableTableModel(Vector data, Vector columnNames) {
		super(data, columnNames);
		
	}

	private static final long serialVersionUID = 339618601197135153L;

	@Override
	public boolean isCellEditable(int row, int column) {
		
		return false;
	}
	
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }
}

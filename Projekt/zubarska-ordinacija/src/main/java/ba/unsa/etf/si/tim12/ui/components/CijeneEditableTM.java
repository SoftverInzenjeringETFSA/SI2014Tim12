package ba.unsa.etf.si.tim12.ui.components;

import java.util.Vector;

import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class CijeneEditableTM extends DefaultTableModel {

	public CijeneEditableTM() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CijeneEditableTM(int arg0, int arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public CijeneEditableTM(Object[] arg0, int arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public CijeneEditableTM(Object[][] arg0, Object[] arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public CijeneEditableTM(Vector arg0, int arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public CijeneEditableTM(Vector arg0, Vector arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isCellEditable(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return arg1 == 1;
	}
	
}

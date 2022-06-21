package igu.util;

import javax.swing.table.DefaultTableModel;

public class ReadonlyTableModel extends DefaultTableModel {
	
	private static final long serialVersionUID = 1L;

	public ReadonlyTableModel() {
		super();
	}
	
	public ReadonlyTableModel(Object[][] objects, String[] strings) {
		super(objects, strings);
	}

	@Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}

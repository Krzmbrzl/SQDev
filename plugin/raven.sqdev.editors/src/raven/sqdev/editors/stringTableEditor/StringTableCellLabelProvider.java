package raven.sqdev.editors.stringTableEditor;

import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.ViewerCell;

public class StringTableCellLabelProvider extends CellLabelProvider {
	
	/**
	 * The <code>TableViewer</code> this provider works for
	 */
	private TableViewer viewer;
	
	public StringTableCellLabelProvider(TableViewer viewer) {
		this.viewer = viewer;
	}
	
	@Override
	public void update(ViewerCell cell) {
		int columnIndex = cell.getColumnIndex();
		
		Object data = viewer.getTable().getColumn(columnIndex).getData();
		
		if (!(data instanceof Language) || data == null) {
			// this is the "add" column
			return;
		}
		
		Object key = cell.getViewerRow().getItem().getData();
		
		// remove any remaining images
		cell.setImage(null);
		
		if (key instanceof StringTableKey) {
			String text = ((StringTableKey) key).getString((Language) data);
			
			if (text != null) {
				cell.setText(text);
			} else {
				cell.setText("Not specified");
			}
		} else {
			if (key.equals(StringTableEditor.ADD_ROW) || key.equals(StringTableEditor.EMPTY_ROW)) {
				// no text for an "add" row nor for an empty row
				return;
			}
			
			setErrorText(cell, "Row data is not appropriate");
		}
	}
	
	/**
	 * Sets the text of the given cell to an error message
	 * 
	 * @param cell
	 *            The cell whose text should be changed
	 * @param msg
	 *            The message that should be printed (may be null)
	 */
	private void setErrorText(ViewerCell cell, String msg) {
		cell.setText("Failed" + ((msg != null) ? " " + msg : ""));
	}
	
	
}

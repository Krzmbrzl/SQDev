package raven.sqdev.editors.stringTableEditor;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.ICellEditorListener;
import org.eclipse.jface.viewers.ICellEditorValidator;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

public class StringTableCellEditingSupport extends EditingSupport {
	
	/**
	 * The currently active cell
	 */
	private ViewerCell activeCell;
	/**
	 * Indicates whether current input is valid
	 */
	private boolean isValid = true;
	
	public StringTableCellEditingSupport(TableViewer viewer) {
		super(viewer);
	}
	
	@Override
	protected CellEditor getCellEditor(Object element) {
		TextCellEditor editor = new TextCellEditor(getViewer().getTable(), SWT.SINGLE);
		
		Point mousePos = PlatformUI.getWorkbench().getDisplay().getCursorLocation();
		Point relMousePos = PlatformUI.getWorkbench().getDisplay().getFocusControl()
				.toControl(mousePos);
		
		ViewerCell cell = getViewer().getCell(relMousePos);
		
		if (cell.getColumnIndex() == 0) {
			// add validator to key cell
			editor.setValidator(new ICellEditorValidator() {
				
				@Override
				public String isValid(Object value) {
					String str = (String) value;
					
					isValid = false;
					
					if (str.contains(" ")) {
						return "A key may not contain a blank!";
					}
					
					if (str.isEmpty()) {
						return "The key may not be empty!";
					}
					
					if (!Character.isLetter(str.charAt(0))) {
						return "A key must start with a letter!";
					}
					
					isValid = true;
					return null;
				}
			});
			
			editor.addListener(new ICellEditorListener() {
				
				private Shell errorShell;
				private Label errorLabel;
				
				@Override
				public void editorValueChanged(boolean oldValidState, boolean newValidState) {
					if (!newValidState) {
						if (errorShell == null || errorLabel == null || errorShell.isDisposed()
								|| errorLabel.isDisposed()) {
							errorShell = new Shell(getViewer().getTable().getShell(),
									SWT.ON_TOP | SWT.TOOL);
							
							errorShell.setLayout(new FillLayout());
							
							errorLabel = new Label(errorShell, SWT.NONE);
						}
						
						errorLabel.setText(editor.getErrorMessage());
						errorLabel.setBackground(PlatformUI.getWorkbench().getDisplay()
								.getSystemColor(SWT.COLOR_RED));
						
						
						FontData data = new FontData();
						data.setStyle(SWT.BOLD);
						data.setHeight(8);
						errorLabel.setFont(new Font(PlatformUI.getWorkbench().getDisplay(), data));
						
						errorShell.setLocation(getTooltipLocation(cell, mousePos));
						errorShell.pack();
						errorShell.setVisible(true);
					} else {
						if (errorShell != null && !errorShell.isDisposed()) {
							errorShell.setVisible(false);
						}
					}
				}
				
				/**
				 * Gets the tooltip location for the given cell
				 * 
				 * @param cell
				 *            The cell the tooltip should be applied to
				 * @param mousePos
				 *            The absolute mouse position for the given cell
				 * @return The location the tooltip should be put on
				 */
				private Point getTooltipLocation(ViewerCell cell, Point mousePos) {
					// TODO: make work properly
					
					/*
					 * Point relPos =
					 * PlatformUI.getWorkbench().getDisplay().getFocusControl()
					 * .toControl(mousePos);
					 * 
					 * int counter = 1; while (leftBorder == -1) { Point
					 * testPoint = new Point(relPos.x - counter, relPos.y);
					 * 
					 * ViewerCell testCell = getViewer().getCell(testPoint);
					 * 
					 * if (testCell == null || !testCell.equals(cell)) {
					 * leftBorder = mousePos.x + counter - 1; } else {
					 * counter++; } }
					 * 
					 * counter = 1; while (downBorder == -1) { Point testPoint =
					 * new Point(relPos.x, relPos.y - counter);
					 * 
					 * if (getViewer().getCell(testPoint) == null ||
					 * !getViewer().getCell(testPoint).equals(cell)) {
					 * downBorder = mousePos.y + counter - 1; } else {
					 * counter++; } }
					 */
					
					
					// create the respective tooltip location
					return new Point(mousePos.x - 10, mousePos.y + 10);
				}
				
				@Override
				public void cancelEditor() {
					if (errorShell != null) {
						errorShell.dispose();
					}
				}
				
				@Override
				public void applyEditorValue() {
					if (errorShell != null) {
						errorShell.dispose();
					}
				}
			});
		}
		
		return editor;
	}
	
	@Override
	protected boolean canEdit(Object element) {
		Point cursorLocation = PlatformUI.getWorkbench().getDisplay().getCursorLocation();
		Point relativeCursorLocation = PlatformUI.getWorkbench().getDisplay().getFocusControl()
				.toControl(cursorLocation);
		
		activeCell = getViewer().getCell(relativeCursorLocation);
		
		return (element instanceof StringTableKey) && activeCell != null;
	}
	
	@Override
	protected Object getValue(Object element) {
		if (activeCell == null) {
			return "";
		} else {
			return activeCell.getText();
		}
	}
	
	@Override
	protected void setValue(Object element, Object val) {
		StringTableKey keyObj = (StringTableKey) element;
		String value = (String) val;
		
		Object data = getViewer().getTable().getColumn(activeCell.getColumnIndex()).getData();
		
		if (data == null || value == null) {
			// TODO: log
			return;
		}
		
		if (isValid) {
			if (data instanceof Language) {
				// update respective key object
				keyObj.setString((Language) data, value);
			} else {
				if (data instanceof String) {
					if (((String) data).equals(StringTableEditor.KEY_COLUMN) && !value.isEmpty()) {
						keyObj.setKey(value);
					} else {
						return;
					}
				}
			}
			
			// update cellText
			activeCell.setText(value);
		}
	}
	
	@Override
	public TableViewer getViewer() {
		return (TableViewer) super.getViewer();
	}
	
}

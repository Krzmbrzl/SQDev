package raven.sqdev.editors.stringTableEditor;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class StringTableContentProvider implements IStructuredContentProvider {
	
	public StringTableContentProvider() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public Object[] getElements(Object inputElement) {
		if (inputElement instanceof StringTableKey[]) {
			return (StringTableKey[]) inputElement;
		}
		
		if (inputElement instanceof StringTableKey) {
			return new StringTableKey[] { (StringTableKey) inputElement };
		}
		
		if (inputElement instanceof Object[]) {
			return (Object[]) inputElement;
		} else {
			return new Object[] { inputElement };
		}
	}
	
}

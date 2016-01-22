package raven.sqdev.editors.sqfeditor;

import raven.sqdev.editors.BasicTextEditor;

public class SQF_Editor extends BasicTextEditor {
	
	public SQF_Editor() {
		super();
		this.setSourceViewerConfiguration(new SQFConfiguration(this.getColorManager(), this));
		this.setDocumentProvider(new SQFDocumentProvider());
	}
}

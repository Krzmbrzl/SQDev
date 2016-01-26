package raven.sqdev.editors.sqfeditor;

import raven.sqdev.editors.BasicCodeEditor;

public class SQF_Editor extends BasicCodeEditor {
	
	public SQF_Editor() {
		super();
		this.setSourceViewerConfiguration(new SQFConfiguration(this.getColorManager(), this));
		this.setDocumentProvider(new SQFDocumentProvider());
	}
}

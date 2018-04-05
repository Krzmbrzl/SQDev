package raven.sqdev.ui.commands;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import raven.sqdev.editors.BasicCodeEditor;
import raven.sqdev.interfaces.IParseResult;
import ui.TreeUI;

public class ShowSyntaxTreeHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IEditorPart active = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();

		if (active != null && active instanceof BasicCodeEditor) {
			BasicCodeEditor activeEditor = (BasicCodeEditor) active;

			IParseResult parseResult = activeEditor.getParseResult();

			Shell shell = new Shell(PlatformUI.getWorkbench().getDisplay());
			shell.setLayout(new FillLayout());
			shell.setText("Syntax Tree");

			new TreeUI(shell, SWT.NONE, parseResult.getTree(), parseResult.getTokenBuffer());

			shell.open();
		}
		return null;
	}

}

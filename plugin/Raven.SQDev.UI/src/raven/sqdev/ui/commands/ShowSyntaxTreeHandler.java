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
import raven.sqdev.misc.SQDevInfobox;
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

			if (parseResult == null) {
				// there is nothing to display

				SQDevInfobox info = new SQDevInfobox("There is no syntax tree to display here.\n"
						+ "Note that the syntax tree can only be shown when having an SQF editor opened. "
						+ "If you do happen do have one opened, try to let its content be parsed and try again.",
						SWT.ICON_INFORMATION);

				info.open(false);

				return null;
			}

			new TreeUI(shell, SWT.NONE, parseResult.getTree(), parseResult.getTokenBuffer());

			shell.open();
		}
		return null;
	}

}

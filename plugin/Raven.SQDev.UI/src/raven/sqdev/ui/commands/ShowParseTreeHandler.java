package raven.sqdev.ui.commands;

import java.util.List;

import org.antlr.v4.runtime.tree.ParseTree;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import raven.sqdev.editors.BasicCodeEditor;
import raven.sqdev.interfaces.IParseResult;
import raven.sqdev.ui.util.TreeViewer;

public class ShowParseTreeHandler extends AbstractHandler {
	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IEditorPart active = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
				.getActiveEditor();
		
		if (active != null && active instanceof BasicCodeEditor) {
			BasicCodeEditor activeEditor = (BasicCodeEditor) active;
			
			IParseResult tree = activeEditor.getParseResult();
			List<String> ruleNames = activeEditor.getParseRuleNames();
			
			if (tree != null && ruleNames != null) {
				// TODO: use new TreeDisplayer
				// TreeViewer viewer = new TreeViewer(ruleNames, tree);
				// viewer.open();
			}
		}
		return null;
	}
	
}

package raven.sqdev.ui.commands;

import java.awt.Desktop;
import java.io.IOException;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import raven.sqdev.util.SQDevInfobox;
import raven.sqdev.util.Util;

public class CreatePluginInfo extends AbstractHandler {
	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		openBugReportPanel();
		
		return null;
	}
	
	public void openBugReportPanel() {
		Shell shell = new Shell(Display.getCurrent().getActiveShell());
		
		shell.setLayout(new GridLayout());
		
		shell.setText("Create plugin information");
		
		Label description = new Label(shell, SWT.NONE);
		description.setText("This will create a zip file with some standard information about "
				+ "your system, the SQDev plugin and the running eclipse version "
				+ "as well as it's log file.\nIt will be named \"" + Util.PLUGIN_INFO_FILE_NAME
				+ "\".\n");
		
		Button button = new Button(shell, SWT.PUSH);
		button.setText("Create and open");
		button.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseUp(MouseEvent e) {
				try {
					Desktop.getDesktop().open(Util.getPluginInfoZipAsFile().getParentFile());
				} catch (IOException e1) {
					e1.printStackTrace();
					
					SQDevInfobox info = new SQDevInfobox(
							"Failed at creating or opeing error info file", e1);
					info.open(false);
				}
			}
		});
		
		button.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false));
		
		
		shell.pack();
		
		shell.open();
	}
	
}

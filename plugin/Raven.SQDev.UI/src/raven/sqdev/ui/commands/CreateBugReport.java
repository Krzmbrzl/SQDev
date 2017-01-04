package raven.sqdev.ui.commands;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import raven.sqdev.util.Util;

public class CreateBugReport extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		openBugReportPanel();
		
		return null;
	}
	
	public void openBugReportPanel() {
		Shell shell = new Shell(Display.getCurrent().getActiveShell());
		
		shell.setLayout(new GridLayout());
		
		Label description = new Label(shell, SWT.NONE);
		description.setText("Here is a collection of system information that are needed in order to solve your problem.");
		
		Text systemInfo = new Text(shell, SWT.BORDER | SWT.MULTI);
		systemInfo.setText(Util.getStandardInformation());
		
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		
		clipboard.setContents(new Transferable() {
			
			@Override
			public boolean isDataFlavorSupported(DataFlavor flavor) {
				return flavor.equals(DataFlavor.javaFileListFlavor);
			}
			
			@Override
			public DataFlavor[] getTransferDataFlavors() {
				return new DataFlavor[] {DataFlavor.javaFileListFlavor};
			}
			
			@Override
			public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
				ArrayList<File> list = new ArrayList<File>();
				list.add(Util.getErrorInfoZipAsFile());
				
				return list;
			}
		}, new ClipboardOwner() {
			
			@Override
			public void lostOwnership(Clipboard clipboard, Transferable contents) {
				System.out.println("Lost ownership");
			}
		});
		
		shell.pack();
		
		shell.open();
	}
	
	public static void main (String[] args) throws IOException {
		Transferable test1 = Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null);
		System.out.println(test1);
		
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new Transferable() {
			
			@Override
			public boolean isDataFlavorSupported(DataFlavor flavor) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public DataFlavor[] getTransferDataFlavors() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
				// TODO Auto-generated method stub
				return null;
			}
		}, null);
		
		Transferable test2 = Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null);
		System.out.println(test2);
	}

}

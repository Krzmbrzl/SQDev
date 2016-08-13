package raven.sqdev.ui.views;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import raven.sqdev.util.FileUtil;
import raven.sqdev.util.SQDevInfobox;

public class RPTViewer extends ViewPart {
	
	/**
	 * The TabFolder for displaying the RPTs
	 */
	private CTabFolder tabFolder;
	
	public RPTViewer() {
		super();
	}
	
	@Override
	public void createPartControl(Composite parent) {
		tabFolder = new CTabFolder(parent, SWT.TOP);
		
		if (!System.getProperty("os.name").toLowerCase().contains("windows")) {
			SQDevInfobox info = new SQDevInfobox("RPTViewer currently only supports Windows !",
					SWT.ERROR);
			info.open(false);
		} else {
			displayRPTs();
			
			configureWatchService();
		}
	}
	
	@Override
	public void setFocus() {
		tabFolder.setFocus();
	}
	
	/**
	 * Gets a list of all paths to the RPTs
	 */
	private List<IPath> getRPTFiles() {
		List<IPath> rptPaths = new ArrayList<IPath>(0);
		
		IPath root = new Path(System.getProperty("user.home") + "/AppData/Local/Arma 3");
		
		if (!root.toFile().exists()) {
			// No RPT files available
			return rptPaths;
		}
		
		for (File currentFile : root.toFile().listFiles()) {
			IPath currentPath = new Path(currentFile.getPath());
			
			if (currentPath.getFileExtension() != null
					&& currentPath.getFileExtension().equals("rpt")) {
				rptPaths.add(currentPath);
			}
		}
		
		return rptPaths;
	}
	
	/**
	 * Display the RPTs to the user
	 */
	private void displayRPTs() {
		List<IPath> rpts = getRPTFiles();
		
		rpts.sort(new Comparator<IPath>() {
			
			@Override
			public int compare(IPath path1, IPath path2) {
				// sort so that newest is the first element
				long mod1 = path1.toFile().lastModified();
				long mod2 = path2.toFile().lastModified();
				
				if (mod1 == mod2) {
					return 0;
				}
				
				if (mod1 > mod2) {
					return -1;
				} else {
					return 1;
				}
			}
		});
		
		for (IPath currentRPT : rpts) {
			CTabItem item = new CTabItem(tabFolder, SWT.NONE);
			
			String rptContent;
			try {
				rptContent = FileUtil.readAll(new FileInputStream(currentRPT.toFile()));
				
				if (rptContent.length() > 1000000) {
					// don't display more than a million characters
					rptContent = "...\n\n" + rptContent.substring(rptContent.length() - 1000000);
				}
			} catch (IOException e) {
				rptContent = "[Error]: " + e.getMessage();
				
				e.printStackTrace();
			}
			
			ScrolledComposite scrollComp = new ScrolledComposite(tabFolder,
					SWT.H_SCROLL | SWT.V_SCROLL);
			
			Composite subComp = new Composite(scrollComp, SWT.NONE);
			subComp.setLayout(new FillLayout());
			
			StyledText rptText = new StyledText(subComp, SWT.READ_ONLY);
			rptText.setText(rptContent);
			
			scrollComp.setContent(subComp);
			scrollComp.setExpandHorizontal(true);
			scrollComp.setExpandVertical(true);
			scrollComp.setMinSize(rptText.computeSize(SWT.DEFAULT, SWT.DEFAULT));
			
			// TODO: colorize
			
			item.setControl(scrollComp);
			item.setText(new Date(currentRPT.toFile().lastModified()).toString());
		}
	}
	
	/**
	 * Sets up the Watch service that will monitor the file system for changes
	 */
	private void configureWatchService() {
		
	}
	
}

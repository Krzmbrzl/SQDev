package raven.sqdev.misc;

import java.io.IOException;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import raven.sqdev.exceptions.SQDevCoreException;
import raven.sqdev.interfaces.IProposalInformationCategory;

/**
 * An information category being able to display a styled text that may contain
 * code that is encapsulated in between the respective tags
 * 
 * @author Raven
 * 		
 */
public class StyledProposalInformationCategory implements IProposalInformationCategory {
	/**
	 * The name of this category
	 */
	private String name;
	/**
	 * The (styled) content of this category
	 */
	private String content;
	/**
	 * A list of (styled) contents of this category
	 */
	private List<String> contentList;
	
	
	/**
	 * Creates an instance of this infoCategory
	 * 
	 * @param name
	 *            The name of the category
	 * @param content
	 *            The content corresponding to this category. May contain styled
	 *            elements using the tags specified in {@linkplain SQDev}
	 */
	public StyledProposalInformationCategory(String name, String content) {
		if (name == null || content == null) {
			throw new NullPointerException(
					"The specified  " + ((name == null) ? "name" : "content") + "may not be null!");
		}
		
		this.name = name;
		this.content = content;
	}
	
	/**
	 * Creates an instance of this infoCategory
	 * 
	 * @param name
	 *            The name of the category
	 * @param contentList
	 *            A list of contents corresponding to this category. May contain
	 *            styled elements using the tags specified in {@linkplain SQDev}
	 *            .<br>
	 *            Each element will be prefixed with the category's name plus
	 *            it's index
	 */
	public StyledProposalInformationCategory(String name, List<String> contentList) {
		if (name == null || contentList == null) {
			throw new NullPointerException("The specified  "
					+ ((name == null) ? "name" : "contentList") + "may not be null!");
		}
		
		this.name = name;
		
		if (contentList.size() == 1) {
			this.content = contentList.get(0);
		} else {
			this.contentList = contentList;
		}
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public Control getControl(Composite parent) {
		Composite base = new Composite(parent, SWT.NULL);
		base.setBackground(parent.getBackground());
		base.setForeground(parent.getForeground());
		
		if (parent.getLayout() instanceof GridLayout) {
			base.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
			
			GridLayout layout;
			if (parent.getLayout() == null) {
				layout = new GridLayout();
			} else {
				layout = (GridLayout) parent.getLayout();
			}
			
			layout.marginHeight = 0;
			layout.marginWidth = 0;
			
			parent.setLayout(layout);
		}
		
		try {
			if (content != null) {
				SQDev.createStyledComposite(base, content);
			} else {
				// process contentList
				for (int i = 1; i <= contentList.size(); i++) {
					String prefix = SQDev.BOLD.getOpener() + getName().trim() + " " + i + ": "
							+ SQDev.BOLD.getCloser();
							
					String currentContent = contentList.get(i - 1);
					
					// create the respective composite prefixed by name and
					// index
					SQDev.createStyledComposite(base, prefix + currentContent);
				}
			}
		} catch (IOException e) {
			// rethrow
			throw new SQDevCoreException("Failed at creating styled composite", e);
		}
		
		return base;
	}
	
}

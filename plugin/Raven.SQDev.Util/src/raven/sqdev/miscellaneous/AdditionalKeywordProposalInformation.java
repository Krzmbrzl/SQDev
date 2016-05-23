package raven.sqdev.miscellaneous;

import java.net.URL;
import java.util.ArrayList;

import org.eclipse.jface.action.Action;

import raven.sqdev.actions.WikiAction;
import raven.sqdev.infoCollection.base.Keyword;
import raven.sqdev.infoCollection.base.SQFCommand;
import raven.sqdev.infoCollection.base.SQFElement;
import raven.sqdev.interfaces.IProposalInformationCategory;
import raven.sqdev.misc.AbstractAdditionalProposalInformation;
import raven.sqdev.misc.SQDev;
import raven.sqdev.misc.StyledProposalInformationCategory;

/**
 * An additional proposal information based on a <code>Keyword</code>
 * 
 * @author Raven
 * 
 */
public class AdditionalKeywordProposalInformation extends AbstractAdditionalProposalInformation {
	
	/**
	 * The <code>Keyword</code> this info will correspond to
	 */
	private Keyword keyword;
	/**
	 * The action for taking the user to the wiki
	 */
	private Action wikiAction;
	
	public AdditionalKeywordProposalInformation(Keyword keyword) {
		if (keyword == null) {
			throw new NullPointerException();
		}
		
		this.keyword = keyword;
		
		// directly computes the categories
		doComputeCategories();
	}
	
	@Override
	protected ArrayList<IProposalInformationCategory> computeCategories(
			ArrayList<IProposalInformationCategory> categories) {
		// Description
		if (keyword.hasDescription()) {
			categories.add(
					new StyledProposalInformationCategory("Description", keyword.getDescription()));
		}
		
		if (keyword instanceof SQFElement) {
			SQFElement element = (SQFElement) keyword;
			
			computeWikiAction(element.getWikiPage());
			
			if (keyword instanceof SQFCommand) {
				SQFCommand command = (SQFCommand) keyword;
				
				// create overview page
				String overviewContent = "";
				
				// description
				if (command.getDescription().length() > 100) {
					overviewContent += command.getDescription().substring(0, 99) + "...\n\n";
				} else {
					overviewContent += command.getDescription() + "\n\n";
				}
				
				// syntax
				overviewContent += SQDev.BOLD.getOpener() + "Possible syntax: "
						+ SQDev.BOLD.getCloser()
						+ command.getRawSytaxes().get(command.getRawSytaxes().size() - 1) + "\n\n";
				
				// locality
				if (command.isArgumentLocalityDefined()) {
					overviewContent += SQDev.BOLD.getOpener() + "Argument locality: "
							+ SQDev.BOLD.getCloser() + command.getArgumentLocality() + "\n\n";
				}
				if (command.isEffectLocalityDefined()) {
					// remove one newLine
					overviewContent = overviewContent.substring(0, overviewContent.length() - 1);
					overviewContent += SQDev.BOLD.getOpener() + "Effect locality: "
							+ SQDev.BOLD.getCloser() + command.getEffectLocality() + "\n\n";
				}
				
				// return value
				overviewContent += SQDev.BOLD.getOpener() + "Return Value: "
						+ SQDev.BOLD.getCloser() + command.getReturnType();
				
				if (!overviewContent.isEmpty()) {
					categories.add(0,
							new StyledProposalInformationCategory("Overview", overviewContent));
				}
				
				
				if (command.hasExample()) {
					categories.add(new StyledProposalInformationCategory("Example",
							command.getExamples()));
				}
				
				if (command.hasNote()) {
					categories
							.add(new StyledProposalInformationCategory("Note", command.getNotes()));
				}
				
				if (command.hasRawSyntax()) {
					categories.add(new StyledProposalInformationCategory("Raw Syntax",
							command.getRawSytaxes()));
				}
				
				if (command.hasSyntax()) {
					categories.add(new StyledProposalInformationCategory("Syntax",
							command.getStringSyntaxes()));
				}
			}
		}
		
		return categories;
	}
	
	/**
	 * Gets the <code>Keyword</code> this info corresponds to
	 */
	public Keyword getKeyword() {
		return keyword;
	}
	
	/**
	 * Computes the <code>WikiPage</code> that can be used in a respective
	 * toolbar
	 * 
	 * @param wiki
	 *            The <code>URL</code> to the wikiPage
	 */
	private void computeWikiAction(URL wiki) {
		if (wiki == null) {
			return;
		}
		
		wikiAction = new WikiAction(wiki);
	}
	
	/**
	 * Gets the <code>Action</code> that should be added to the toolbar
	 */
	public Action getToolbarAction() {
		return wikiAction;
	}
	
}

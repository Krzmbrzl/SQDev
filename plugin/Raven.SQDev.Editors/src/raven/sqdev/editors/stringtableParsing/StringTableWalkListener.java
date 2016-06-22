package raven.sqdev.editors.stringtableParsing;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.eclipse.core.runtime.Assert;

import raven.sqdev.editors.stringTableEditor.Language;
import raven.sqdev.editors.stringTableEditor.StringTableContainer;
import raven.sqdev.editors.stringTableEditor.StringTableKey;
import raven.sqdev.editors.stringTableEditor.StringTablePackage;
import raven.sqdev.editors.stringtableParsing.StringTableParser.ContainerStartTagContext;
import raven.sqdev.editors.stringtableParsing.StringTableParser.KeyStartTagContext;
import raven.sqdev.editors.stringtableParsing.StringTableParser.LanguageContext;
import raven.sqdev.editors.stringtableParsing.StringTableParser.LanguageOpenTagContext;
import raven.sqdev.editors.stringtableParsing.StringTableParser.PackageStartTagContext;

/**
 * The listener that will extract the neccessary information from the XML file
 * 
 * @author Raven
 *
 */
public class StringTableWalkListener extends StringTableBaseListener {
	/**
	 * The list of all extracted packages
	 */
	private ArrayList<StringTablePackage> packageList;
	/**
	 * The currently processed package
	 */
	private StringTablePackage currentPackage;
	/**
	 * The currently processed container
	 */
	private StringTableContainer currentContainer;
	/**
	 * The currently processed key
	 */
	private StringTableKey currentKey;
	/**
	 * The currently processed language
	 */
	private Language currentLanguage;
	/**
	 * The tolen stream associated with the parse tree to walk
	 */
	private CommonTokenStream stream;
	
	
	/**
	 * Creates a new instance of this walker
	 * 
	 * @param stream
	 *            The <code>CommonTokenStream</code> associated with the parse
	 *            tree to walk
	 */
	public StringTableWalkListener(CommonTokenStream stream) {
		Assert.isNotNull(stream, "Token stream may not be null!");
		
		packageList = new ArrayList<StringTablePackage>();
		this.stream = stream;
	}
	
	
	@Override
	public void enterPackageStartTag(PackageStartTagContext ctx) {
		String name = ctx.children.get(ctx.children.size() - 2).getText();
		name = name.substring(1, name.length() - 1);
		
		currentPackage = new StringTablePackage(name);
		
		packageList.add(currentPackage);
	}
	
	@Override
	public void enterContainerStartTag(ContainerStartTagContext ctx) {
		String name = ctx.children.get(ctx.children.size() - 2).getText();
		name = name.substring(1, name.length() - 1);
		
		currentContainer = new StringTableContainer(name);
		
		addContainer(currentContainer);
	}
	
	@Override
	public void enterKeyStartTag(KeyStartTagContext ctx) {
		String name = ctx.children.get(ctx.children.size() - 2).getText();
		
		if (name.length() <= 2) {
			return;
		}
		
		name = name.substring(1, name.length() - 1);
		
		currentKey = new StringTableKey(name);
		
		addKey(currentKey);
	}
	
	@Override
	public void enterLanguageOpenTag(LanguageOpenTagContext ctx) {
		String name = ctx.children.get(ctx.children.size() - 2).getText();
		
		try {
			currentLanguage = Language.valueOf(name.toUpperCase());
		} catch (IllegalArgumentException e) {
			// TODO: create error marker
			currentLanguage = null;
		}
	}
	
	@Override
	public void exitLanguage(LanguageContext ctx) {
		if (currentLanguage != null) {
			StringBuilder builder = new StringBuilder();
			
			for (int i = 1; i < ctx.children.size() - 1; i++) {
				TerminalNode child = (TerminalNode) ctx.children.get(i);
				
				Token token = child.getSymbol();
				int index = token.getTokenIndex();
				
				List<Token> hiddenTokens = stream.getHiddenTokensToLeft(index);
				
				if (hiddenTokens != null) {
					for (Token currentToken : hiddenTokens) {
						builder.append(currentToken.getText());
					}
				}
				
				builder.append(child.getText());
			}
			
			currentKey.setString(currentLanguage, builder.toString().trim());
		}
	}
	
	/**
	 * Adds the given container to the current package. If the current package
	 * is not yet defined it will create it as the default package
	 * 
	 * @param container
	 *            The container to add
	 */
	private void addContainer(StringTableContainer container) {
		if (currentPackage == null) {
			currentPackage = new StringTablePackage("Default package");
			packageList.add(currentPackage);
		}
		
		currentPackage.addContainer(container);
	}
	
	/**
	 * Adds the given key to the current container. If the current container
	 * and/or the current package is not yet defined default ones will be
	 * created
	 * 
	 * @param key
	 *            The key to add
	 */
	private void addKey(StringTableKey key) {
		if (currentPackage == null) {
			currentPackage = new StringTablePackage("Default package");
		}
		
		if (currentContainer == null) {
			currentContainer = new StringTableContainer("Default container");
			currentPackage.addContainer(currentContainer);
			;
		}
		
		currentContainer.addKey(currentKey);
	}
	
	/**
	 * Gets the package list that has been created during parsing
	 */
	public ArrayList<StringTablePackage> getPackageList() {
		return packageList;
	}
}

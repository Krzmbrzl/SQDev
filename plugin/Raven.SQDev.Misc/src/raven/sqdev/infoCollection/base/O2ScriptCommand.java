package raven.sqdev.infoCollection.base;

import java.util.List;

import raven.sqdev.exceptions.BadSyntaxException;
import raven.sqdev.syntax.Syntax;

public class O2ScriptCommand extends SQFCommand {

	private static final long serialVersionUID = 7237571646032787905L;

	public O2ScriptCommand() {
	}

	public O2ScriptCommand(String command) {
		super(command);
	}

	public O2ScriptCommand(String command, String description) {
		super(command, description);
	}
	
	@Override
	protected Syntax createSyntax(String syntaxString, String keyword) throws BadSyntaxException {
		// TODO: create actual syntax
		return Syntax.parseSyntax(keyword, keyword);
	}
	
	@Override
	protected boolean doMapReturnValues(List<Syntax> syntaxes, String[] returnValues) {
		// TODO: remove this method
		return true;
	}
	
	@Override
	protected boolean shouldHaveLocality() {
		// prevent the serach for locality-specs for O2Script-commands
		return false;
	}
}

package raven.sqdev.parser.misc;

import java.util.Collection;

import dataStructures.AbstractSQFTokenFactory;


public class SQFTokenFactory extends AbstractSQFTokenFactory {

	private Collection<String> binaryKeywords;
	private Collection<String> unaryKeywords;

	public SQFTokenFactory(Collection<String> binaryKeywords, Collection<String> unaryKeywords) {
		assert (binaryKeywords != null);
		assert (unaryKeywords != null);

		this.binaryKeywords = binaryKeywords;
		this.unaryKeywords = unaryKeywords;
	}

	@Override
	protected void doInitialize() {
		for (String currentOperator : unaryKeywords) {
			lookupTable.put(currentOperator.toLowerCase(), UNARY);
		}

		for (String currentOperator : binaryKeywords) {
			currentOperator = currentOperator.toLowerCase();

			if (hasSpecialPrecedence(currentOperator)) {
				lookupTable.put(currentOperator, specialOperators.get(currentOperator));
			} else {
				lookupTable.put(currentOperator, BINARY);
			}
		}

		lookupTable.put("=", specialOperators.get("="));
	}

}

package raven.sqdev.editors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.rules.EndOfLineRule;
import org.eclipse.jface.text.rules.IPredicateRule;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.RuleBasedPartitionScanner;
import org.eclipse.jface.text.rules.Token;

/**
 * A partionScanner for the basic partitions. <br>
 * It's default rules are MULTILINE_COMMENT_RULE, SINGLELINE_COMMENT_RULE,
 * DOUBLE_QUOTE_STRING_RULE and SINGLE_QUOTE_STRING_RULE
 * 
 * @author Raven
 * 
 */
public class BasicPartitionScanner extends RuleBasedPartitionScanner {
	
	public static final String BASIC_PARTITIONING = "__basic_partitioning";
	
	public static final String BASIC_COMMENT = "__basic_Comment";
	public static final String BASIC_STRING = "__basic_String";
	
	/**
	 * The rule for a multiline comment
	 */
	public static final IPredicateRule MULTILINE_COMMENT_RULE = new MultiLineRule("/*", "*/",
			new Token(BASIC_COMMENT));
	/**
	 * The rule for a singleline comment
	 */
	public static final IPredicateRule SINGLELINE_COMMENT_RULE = new EndOfLineRule("//",
			new Token(BASIC_COMMENT));
	/**
	 * The rule for a string encapsulated by double quotation marks
	 */
	public static final IPredicateRule DOUBLE_QUOTE_STRING_RULE = new MultiLineRule("\"", "\"",
			new Token(BASIC_STRING), '\\');
	/**
	 * The rule for a string encapsulated by single quotation marks
	 */
	public static final IPredicateRule SINGLE_QUOTE_STRING_RULE = new MultiLineRule("'", "'",
			new Token(BASIC_STRING));
	
	/**
	 * An array containing all currently used rules
	 */
	protected IPredicateRule[] rules;
	
	
	public BasicPartitionScanner() {
		IPredicateRule[] rules = {
				// rule for multiLine comments
				MULTILINE_COMMENT_RULE,
				// rule for singleLine comments
				SINGLELINE_COMMENT_RULE,
				// rule for strings in double quotes
				DOUBLE_QUOTE_STRING_RULE,
				// rule for strings in single quotes
				SINGLE_QUOTE_STRING_RULE };
		
		setRules(rules);
	}
	
	/**
	 * Set the partition rules for this partitioner
	 * 
	 * @param rules
	 *            The new set of rules
	 */
	public void setRules(IPredicateRule[] rules) {
		Assert.isNotNull(rules);
		
		this.rules = rules;
		
		setPredicateRules(rules);
	}
	
	/**
	 * Set the partition rules for this partitioner
	 * 
	 * @param rules
	 *            The new set of rules
	 */
	public void setRules(List<IPredicateRule> rules) {
		setRules(rules.toArray(new IPredicateRule[rules.size()]));
	}
	
	/**
	 * Get all the rules that this partitioner uses
	 * 
	 * @return An array of the used rules
	 */
	public IPredicateRule[] getRules() {
		if (rules == null) {
			// set an empty array of rules
			setRules(new IPredicateRule[0]);
		}
		
		return rules;
	}
	
	/**
	 * Gets the rules of this partitionScanner as an ArrayList
	 * 
	 * @return The list of rules
	 */
	protected ArrayList<IPredicateRule> getRuleList() {
		ArrayList<IPredicateRule> rules = new ArrayList<IPredicateRule>();
		rules.addAll(Arrays.asList(this.rules));
		
		return rules;
	}
	
	/**
	 * Removes the specified rule from the rule list of this partitioner
	 * 
	 * @param rule
	 *            The rule to remove
	 */
	public void removeRule(IPredicateRule rule) {
		ArrayList<IPredicateRule> rules = getRuleList();
		
		// remove the specified rule
		rules.remove(rule);
		
		// set rules again
		setRules(rules);
	}
	
	/**
	 * Adds a rule to this partitionScanner if it's not already added
	 * 
	 * @param rule
	 *            The rule to add
	 */
	public void addRule(IPredicateRule rule) {
		ArrayList<IPredicateRule> rules = getRuleList();
		
		if (!rules.contains(rule)) {
			rules.add(rule);
		}
		
		setRules(rules);
	}
	
	/**
	 * Gets the configured content types
	 */
	public String[] getConfiguredContentTypes() {
		ArrayList<String> types = new ArrayList<String>();
		IPredicateRule[] rules = getRules();
		
		for (int i = 0; i < rules.length; i++) {
			String data = (String) rules[i].getSuccessToken().getData();
			
			if (!types.contains(data)) {
				types.add(data);
			}
		}
		
		types.add(IDocument.DEFAULT_CONTENT_TYPE);
		
		return types.toArray(new String[types.size()]);
	}
}

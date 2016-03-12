package raven.sqdev.editors.sqfeditor;

import org.eclipse.jface.text.rules.EndOfLineRule;
import org.eclipse.jface.text.rules.IPredicateRule;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.RuleBasedPartitionScanner;
import org.eclipse.jface.text.rules.Token;

/**
 * The partitionScanner for SQF partitions
 * 
 * @author Raven
 *		
 */
public class SQFPartitionScanner extends RuleBasedPartitionScanner {
	
	public static final String SQF_PARTITIONING = "__sqf_partitioning";
	
	public static final String SQF_COMMENT = "__sqf_Comment";
	public static final String SQF_STRING = "__sqf_String";
	
	public SQFPartitionScanner() {
		IPredicateRule[] rules = {
				// rule for multiLine comments
				new MultiLineRule("/*", "*/", new Token(SQF_COMMENT)),
				// rule for singleLine comments
				new EndOfLineRule("//", new Token(SQF_COMMENT)),
				// rule for strings in double qoutes
				new SQFStringPartitionRule(new Token(SQF_STRING)),
				// rule for strings in single quotes
				new MultiLineRule("'", "'", new Token(SQF_STRING)) };
				
		this.setPredicateRules(rules);
	}
	
}

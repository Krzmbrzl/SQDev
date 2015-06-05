package raven.parser.antlr.internal; 

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import raven.services.SQFGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalSQFParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_COMPARATOR", "RULE_NUMBER", "RULE_STRING", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "';'", "'='", "'!'", "'not'", "'+'", "'-'", "'_x'", "'select'", "'call'", "'('", "')'", "'_this'", "'['", "','", "']'", "'if'", "'then'", "'{'", "'}'", "'else'", "'exitWith'", "'while'", "'do'", "'for'", "'from'", "'to'", "'step'", "'forEach'", "'switch'", "'case'", "':'", "'default'", "'spawn'", "'*'", "'/'", "'mod'", "'^'", "'true'", "'false'", "'isServer'", "'isPlayer'"
    };
    public static final int T__50=50;
    public static final int T__19=19;
    public static final int T__15=15;
    public static final int T__16=16;
    public static final int T__17=17;
    public static final int T__18=18;
    public static final int T__11=11;
    public static final int T__12=12;
    public static final int T__13=13;
    public static final int T__14=14;
    public static final int T__51=51;
    public static final int RULE_ID=4;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int T__28=28;
    public static final int T__29=29;
    public static final int T__22=22;
    public static final int RULE_ML_COMMENT=8;
    public static final int T__23=23;
    public static final int T__24=24;
    public static final int T__25=25;
    public static final int T__20=20;
    public static final int T__21=21;
    public static final int RULE_STRING=7;
    public static final int RULE_SL_COMMENT=9;
    public static final int T__37=37;
    public static final int T__38=38;
    public static final int T__39=39;
    public static final int T__33=33;
    public static final int T__34=34;
    public static final int T__35=35;
    public static final int RULE_COMPARATOR=5;
    public static final int T__36=36;
    public static final int EOF=-1;
    public static final int T__30=30;
    public static final int T__31=31;
    public static final int T__32=32;
    public static final int RULE_WS=10;
    public static final int RULE_NUMBER=6;
    public static final int T__48=48;
    public static final int T__49=49;
    public static final int T__44=44;
    public static final int T__45=45;
    public static final int T__46=46;
    public static final int T__47=47;
    public static final int T__40=40;
    public static final int T__41=41;
    public static final int T__42=42;
    public static final int T__43=43;

    // delegates
    // delegators


        public InternalSQFParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalSQFParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalSQFParser.tokenNames; }
    public String getGrammarFileName() { return "../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g"; }



     	private SQFGrammarAccess grammarAccess;
     	
        public InternalSQFParser(TokenStream input, SQFGrammarAccess grammarAccess) {
            this(input);
            this.grammarAccess = grammarAccess;
            registerRules(grammarAccess.getGrammar());
        }
        
        @Override
        protected String getFirstRuleName() {
        	return "Model";	
       	}
       	
       	@Override
       	protected SQFGrammarAccess getGrammarAccess() {
       		return grammarAccess;
       	}



    // $ANTLR start "entryRuleModel"
    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:67:1: entryRuleModel returns [EObject current=null] : iv_ruleModel= ruleModel EOF ;
    public final EObject entryRuleModel() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleModel = null;


        try {
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:68:2: (iv_ruleModel= ruleModel EOF )
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:69:2: iv_ruleModel= ruleModel EOF
            {
             newCompositeNode(grammarAccess.getModelRule()); 
            pushFollow(FOLLOW_ruleModel_in_entryRuleModel75);
            iv_ruleModel=ruleModel();

            state._fsp--;

             current =iv_ruleModel; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleModel85); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleModel"


    // $ANTLR start "ruleModel"
    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:76:1: ruleModel returns [EObject current=null] : ( (lv_elements_0_0= ruleCode ) )* ;
    public final EObject ruleModel() throws RecognitionException {
        EObject current = null;

        EObject lv_elements_0_0 = null;


         enterRule(); 
            
        try {
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:79:28: ( ( (lv_elements_0_0= ruleCode ) )* )
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:80:1: ( (lv_elements_0_0= ruleCode ) )*
            {
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:80:1: ( (lv_elements_0_0= ruleCode ) )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==RULE_ID||LA1_0==23||LA1_0==26||LA1_0==28||LA1_0==32||LA1_0==34||LA1_0==39) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:81:1: (lv_elements_0_0= ruleCode )
            	    {
            	    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:81:1: (lv_elements_0_0= ruleCode )
            	    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:82:3: lv_elements_0_0= ruleCode
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getModelAccess().getElementsCodeParserRuleCall_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleCode_in_ruleModel130);
            	    lv_elements_0_0=ruleCode();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getModelRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"elements",
            	            		lv_elements_0_0, 
            	            		"Code");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleModel"


    // $ANTLR start "entryRuleCode"
    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:106:1: entryRuleCode returns [EObject current=null] : iv_ruleCode= ruleCode EOF ;
    public final EObject entryRuleCode() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCode = null;


        try {
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:107:2: (iv_ruleCode= ruleCode EOF )
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:108:2: iv_ruleCode= ruleCode EOF
            {
             newCompositeNode(grammarAccess.getCodeRule()); 
            pushFollow(FOLLOW_ruleCode_in_entryRuleCode166);
            iv_ruleCode=ruleCode();

            state._fsp--;

             current =iv_ruleCode; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleCode176); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleCode"


    // $ANTLR start "ruleCode"
    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:115:1: ruleCode returns [EObject current=null] : ( ( ( (lv_dec_0_0= ruleDeclaration ) ) otherlv_1= ';' ) | ( ( (lv_control_2_0= ruleControlStructure ) ) otherlv_3= ';' ) | ( ( (lv_method_4_0= ruleMethod ) ) otherlv_5= ';' ) ) ;
    public final EObject ruleCode() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        EObject lv_dec_0_0 = null;

        EObject lv_control_2_0 = null;

        EObject lv_method_4_0 = null;


         enterRule(); 
            
        try {
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:118:28: ( ( ( ( (lv_dec_0_0= ruleDeclaration ) ) otherlv_1= ';' ) | ( ( (lv_control_2_0= ruleControlStructure ) ) otherlv_3= ';' ) | ( ( (lv_method_4_0= ruleMethod ) ) otherlv_5= ';' ) ) )
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:119:1: ( ( ( (lv_dec_0_0= ruleDeclaration ) ) otherlv_1= ';' ) | ( ( (lv_control_2_0= ruleControlStructure ) ) otherlv_3= ';' ) | ( ( (lv_method_4_0= ruleMethod ) ) otherlv_5= ';' ) )
            {
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:119:1: ( ( ( (lv_dec_0_0= ruleDeclaration ) ) otherlv_1= ';' ) | ( ( (lv_control_2_0= ruleControlStructure ) ) otherlv_3= ';' ) | ( ( (lv_method_4_0= ruleMethod ) ) otherlv_5= ';' ) )
            int alt2=3;
            switch ( input.LA(1) ) {
            case RULE_ID:
                {
                alt2=1;
                }
                break;
            case 26:
            case 28:
            case 32:
            case 34:
            case 39:
                {
                alt2=2;
                }
                break;
            case 23:
                {
                alt2=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }

            switch (alt2) {
                case 1 :
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:119:2: ( ( (lv_dec_0_0= ruleDeclaration ) ) otherlv_1= ';' )
                    {
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:119:2: ( ( (lv_dec_0_0= ruleDeclaration ) ) otherlv_1= ';' )
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:119:3: ( (lv_dec_0_0= ruleDeclaration ) ) otherlv_1= ';'
                    {
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:119:3: ( (lv_dec_0_0= ruleDeclaration ) )
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:120:1: (lv_dec_0_0= ruleDeclaration )
                    {
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:120:1: (lv_dec_0_0= ruleDeclaration )
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:121:3: lv_dec_0_0= ruleDeclaration
                    {
                     
                    	        newCompositeNode(grammarAccess.getCodeAccess().getDecDeclarationParserRuleCall_0_0_0()); 
                    	    
                    pushFollow(FOLLOW_ruleDeclaration_in_ruleCode223);
                    lv_dec_0_0=ruleDeclaration();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getCodeRule());
                    	        }
                           		set(
                           			current, 
                           			"dec",
                            		lv_dec_0_0, 
                            		"Declaration");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    otherlv_1=(Token)match(input,11,FOLLOW_11_in_ruleCode235); 

                        	newLeafNode(otherlv_1, grammarAccess.getCodeAccess().getSemicolonKeyword_0_1());
                        

                    }


                    }
                    break;
                case 2 :
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:142:6: ( ( (lv_control_2_0= ruleControlStructure ) ) otherlv_3= ';' )
                    {
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:142:6: ( ( (lv_control_2_0= ruleControlStructure ) ) otherlv_3= ';' )
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:142:7: ( (lv_control_2_0= ruleControlStructure ) ) otherlv_3= ';'
                    {
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:142:7: ( (lv_control_2_0= ruleControlStructure ) )
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:143:1: (lv_control_2_0= ruleControlStructure )
                    {
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:143:1: (lv_control_2_0= ruleControlStructure )
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:144:3: lv_control_2_0= ruleControlStructure
                    {
                     
                    	        newCompositeNode(grammarAccess.getCodeAccess().getControlControlStructureParserRuleCall_1_0_0()); 
                    	    
                    pushFollow(FOLLOW_ruleControlStructure_in_ruleCode264);
                    lv_control_2_0=ruleControlStructure();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getCodeRule());
                    	        }
                           		set(
                           			current, 
                           			"control",
                            		lv_control_2_0, 
                            		"ControlStructure");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    otherlv_3=(Token)match(input,11,FOLLOW_11_in_ruleCode276); 

                        	newLeafNode(otherlv_3, grammarAccess.getCodeAccess().getSemicolonKeyword_1_1());
                        

                    }


                    }
                    break;
                case 3 :
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:165:6: ( ( (lv_method_4_0= ruleMethod ) ) otherlv_5= ';' )
                    {
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:165:6: ( ( (lv_method_4_0= ruleMethod ) ) otherlv_5= ';' )
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:165:7: ( (lv_method_4_0= ruleMethod ) ) otherlv_5= ';'
                    {
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:165:7: ( (lv_method_4_0= ruleMethod ) )
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:166:1: (lv_method_4_0= ruleMethod )
                    {
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:166:1: (lv_method_4_0= ruleMethod )
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:167:3: lv_method_4_0= ruleMethod
                    {
                     
                    	        newCompositeNode(grammarAccess.getCodeAccess().getMethodMethodParserRuleCall_2_0_0()); 
                    	    
                    pushFollow(FOLLOW_ruleMethod_in_ruleCode305);
                    lv_method_4_0=ruleMethod();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getCodeRule());
                    	        }
                           		set(
                           			current, 
                           			"method",
                            		lv_method_4_0, 
                            		"Method");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    otherlv_5=(Token)match(input,11,FOLLOW_11_in_ruleCode317); 

                        	newLeafNode(otherlv_5, grammarAccess.getCodeAccess().getSemicolonKeyword_2_1());
                        

                    }


                    }
                    break;

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleCode"


    // $ANTLR start "entryRuleDeclaration"
    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:195:1: entryRuleDeclaration returns [EObject current=null] : iv_ruleDeclaration= ruleDeclaration EOF ;
    public final EObject entryRuleDeclaration() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDeclaration = null;


        try {
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:196:2: (iv_ruleDeclaration= ruleDeclaration EOF )
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:197:2: iv_ruleDeclaration= ruleDeclaration EOF
            {
             newCompositeNode(grammarAccess.getDeclarationRule()); 
            pushFollow(FOLLOW_ruleDeclaration_in_entryRuleDeclaration354);
            iv_ruleDeclaration=ruleDeclaration();

            state._fsp--;

             current =iv_ruleDeclaration; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleDeclaration364); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleDeclaration"


    // $ANTLR start "ruleDeclaration"
    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:204:1: ruleDeclaration returns [EObject current=null] : ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= '=' ( (lv_brCon_2_0= ruleBracketContent ) ) ) ;
    public final EObject ruleDeclaration() throws RecognitionException {
        EObject current = null;

        Token lv_name_0_0=null;
        Token otherlv_1=null;
        EObject lv_brCon_2_0 = null;


         enterRule(); 
            
        try {
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:207:28: ( ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= '=' ( (lv_brCon_2_0= ruleBracketContent ) ) ) )
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:208:1: ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= '=' ( (lv_brCon_2_0= ruleBracketContent ) ) )
            {
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:208:1: ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= '=' ( (lv_brCon_2_0= ruleBracketContent ) ) )
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:208:2: ( (lv_name_0_0= RULE_ID ) ) otherlv_1= '=' ( (lv_brCon_2_0= ruleBracketContent ) )
            {
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:208:2: ( (lv_name_0_0= RULE_ID ) )
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:209:1: (lv_name_0_0= RULE_ID )
            {
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:209:1: (lv_name_0_0= RULE_ID )
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:210:3: lv_name_0_0= RULE_ID
            {
            lv_name_0_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleDeclaration406); 

            			newLeafNode(lv_name_0_0, grammarAccess.getDeclarationAccess().getNameIDTerminalRuleCall_0_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getDeclarationRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"name",
                    		lv_name_0_0, 
                    		"ID");
            	    

            }


            }

            otherlv_1=(Token)match(input,12,FOLLOW_12_in_ruleDeclaration423); 

                	newLeafNode(otherlv_1, grammarAccess.getDeclarationAccess().getEqualsSignKeyword_1());
                
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:230:1: ( (lv_brCon_2_0= ruleBracketContent ) )
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:231:1: (lv_brCon_2_0= ruleBracketContent )
            {
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:231:1: (lv_brCon_2_0= ruleBracketContent )
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:232:3: lv_brCon_2_0= ruleBracketContent
            {
             
            	        newCompositeNode(grammarAccess.getDeclarationAccess().getBrConBracketContentParserRuleCall_2_0()); 
            	    
            pushFollow(FOLLOW_ruleBracketContent_in_ruleDeclaration444);
            lv_brCon_2_0=ruleBracketContent();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getDeclarationRule());
            	        }
                   		set(
                   			current, 
                   			"brCon",
                    		lv_brCon_2_0, 
                    		"BracketContent");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleDeclaration"


    // $ANTLR start "entryRuleBracketContent"
    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:256:1: entryRuleBracketContent returns [EObject current=null] : iv_ruleBracketContent= ruleBracketContent EOF ;
    public final EObject entryRuleBracketContent() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleBracketContent = null;


        try {
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:257:2: (iv_ruleBracketContent= ruleBracketContent EOF )
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:258:2: iv_ruleBracketContent= ruleBracketContent EOF
            {
             newCompositeNode(grammarAccess.getBracketContentRule()); 
            pushFollow(FOLLOW_ruleBracketContent_in_entryRuleBracketContent480);
            iv_ruleBracketContent=ruleBracketContent();

            state._fsp--;

             current =iv_ruleBracketContent; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleBracketContent490); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleBracketContent"


    // $ANTLR start "ruleBracketContent"
    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:265:1: ruleBracketContent returns [EObject current=null] : ( ( (lv_decCon_0_0= ruleDecContent ) ) ( ( (lv_comp_1_0= RULE_COMPARATOR ) ) ( (lv_content_2_0= ruleDecContent ) ) )* ) ;
    public final EObject ruleBracketContent() throws RecognitionException {
        EObject current = null;

        Token lv_comp_1_0=null;
        EObject lv_decCon_0_0 = null;

        EObject lv_content_2_0 = null;


         enterRule(); 
            
        try {
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:268:28: ( ( ( (lv_decCon_0_0= ruleDecContent ) ) ( ( (lv_comp_1_0= RULE_COMPARATOR ) ) ( (lv_content_2_0= ruleDecContent ) ) )* ) )
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:269:1: ( ( (lv_decCon_0_0= ruleDecContent ) ) ( ( (lv_comp_1_0= RULE_COMPARATOR ) ) ( (lv_content_2_0= ruleDecContent ) ) )* )
            {
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:269:1: ( ( (lv_decCon_0_0= ruleDecContent ) ) ( ( (lv_comp_1_0= RULE_COMPARATOR ) ) ( (lv_content_2_0= ruleDecContent ) ) )* )
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:269:2: ( (lv_decCon_0_0= ruleDecContent ) ) ( ( (lv_comp_1_0= RULE_COMPARATOR ) ) ( (lv_content_2_0= ruleDecContent ) ) )*
            {
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:269:2: ( (lv_decCon_0_0= ruleDecContent ) )
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:270:1: (lv_decCon_0_0= ruleDecContent )
            {
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:270:1: (lv_decCon_0_0= ruleDecContent )
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:271:3: lv_decCon_0_0= ruleDecContent
            {
             
            	        newCompositeNode(grammarAccess.getBracketContentAccess().getDecConDecContentParserRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_ruleDecContent_in_ruleBracketContent536);
            lv_decCon_0_0=ruleDecContent();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getBracketContentRule());
            	        }
                   		set(
                   			current, 
                   			"decCon",
                    		lv_decCon_0_0, 
                    		"DecContent");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:287:2: ( ( (lv_comp_1_0= RULE_COMPARATOR ) ) ( (lv_content_2_0= ruleDecContent ) ) )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==RULE_COMPARATOR) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:287:3: ( (lv_comp_1_0= RULE_COMPARATOR ) ) ( (lv_content_2_0= ruleDecContent ) )
            	    {
            	    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:287:3: ( (lv_comp_1_0= RULE_COMPARATOR ) )
            	    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:288:1: (lv_comp_1_0= RULE_COMPARATOR )
            	    {
            	    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:288:1: (lv_comp_1_0= RULE_COMPARATOR )
            	    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:289:3: lv_comp_1_0= RULE_COMPARATOR
            	    {
            	    lv_comp_1_0=(Token)match(input,RULE_COMPARATOR,FOLLOW_RULE_COMPARATOR_in_ruleBracketContent554); 

            	    			newLeafNode(lv_comp_1_0, grammarAccess.getBracketContentAccess().getCompCOMPARATORTerminalRuleCall_1_0_0()); 
            	    		

            	    	        if (current==null) {
            	    	            current = createModelElement(grammarAccess.getBracketContentRule());
            	    	        }
            	           		addWithLastConsumed(
            	           			current, 
            	           			"comp",
            	            		lv_comp_1_0, 
            	            		"COMPARATOR");
            	    	    

            	    }


            	    }

            	    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:305:2: ( (lv_content_2_0= ruleDecContent ) )
            	    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:306:1: (lv_content_2_0= ruleDecContent )
            	    {
            	    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:306:1: (lv_content_2_0= ruleDecContent )
            	    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:307:3: lv_content_2_0= ruleDecContent
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getBracketContentAccess().getContentDecContentParserRuleCall_1_1_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleDecContent_in_ruleBracketContent580);
            	    lv_content_2_0=ruleDecContent();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getBracketContentRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"content",
            	            		lv_content_2_0, 
            	            		"DecContent");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleBracketContent"


    // $ANTLR start "entryRuleDecContent"
    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:331:1: entryRuleDecContent returns [EObject current=null] : iv_ruleDecContent= ruleDecContent EOF ;
    public final EObject entryRuleDecContent() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDecContent = null;


        try {
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:332:2: (iv_ruleDecContent= ruleDecContent EOF )
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:333:2: iv_ruleDecContent= ruleDecContent EOF
            {
             newCompositeNode(grammarAccess.getDecContentRule()); 
            pushFollow(FOLLOW_ruleDecContent_in_entryRuleDecContent618);
            iv_ruleDecContent=ruleDecContent();

            state._fsp--;

             current =iv_ruleDecContent; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleDecContent628); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleDecContent"


    // $ANTLR start "ruleDecContent"
    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:340:1: ruleDecContent returns [EObject current=null] : ( ( ( (lv_neg_0_1= '!' | lv_neg_0_2= 'not' ) ) )? ( (lv_singleContent_1_0= ruleVarContent ) ) ( ( (lv_op_2_0= ruleOPERATOR ) ) ( (lv_nextCon_3_0= ruleVarContent ) ) )* ) ;
    public final EObject ruleDecContent() throws RecognitionException {
        EObject current = null;

        Token lv_neg_0_1=null;
        Token lv_neg_0_2=null;
        EObject lv_singleContent_1_0 = null;

        AntlrDatatypeRuleToken lv_op_2_0 = null;

        EObject lv_nextCon_3_0 = null;


         enterRule(); 
            
        try {
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:343:28: ( ( ( ( (lv_neg_0_1= '!' | lv_neg_0_2= 'not' ) ) )? ( (lv_singleContent_1_0= ruleVarContent ) ) ( ( (lv_op_2_0= ruleOPERATOR ) ) ( (lv_nextCon_3_0= ruleVarContent ) ) )* ) )
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:344:1: ( ( ( (lv_neg_0_1= '!' | lv_neg_0_2= 'not' ) ) )? ( (lv_singleContent_1_0= ruleVarContent ) ) ( ( (lv_op_2_0= ruleOPERATOR ) ) ( (lv_nextCon_3_0= ruleVarContent ) ) )* )
            {
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:344:1: ( ( ( (lv_neg_0_1= '!' | lv_neg_0_2= 'not' ) ) )? ( (lv_singleContent_1_0= ruleVarContent ) ) ( ( (lv_op_2_0= ruleOPERATOR ) ) ( (lv_nextCon_3_0= ruleVarContent ) ) )* )
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:344:2: ( ( (lv_neg_0_1= '!' | lv_neg_0_2= 'not' ) ) )? ( (lv_singleContent_1_0= ruleVarContent ) ) ( ( (lv_op_2_0= ruleOPERATOR ) ) ( (lv_nextCon_3_0= ruleVarContent ) ) )*
            {
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:344:2: ( ( (lv_neg_0_1= '!' | lv_neg_0_2= 'not' ) ) )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( ((LA5_0>=13 && LA5_0<=14)) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:345:1: ( (lv_neg_0_1= '!' | lv_neg_0_2= 'not' ) )
                    {
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:345:1: ( (lv_neg_0_1= '!' | lv_neg_0_2= 'not' ) )
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:346:1: (lv_neg_0_1= '!' | lv_neg_0_2= 'not' )
                    {
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:346:1: (lv_neg_0_1= '!' | lv_neg_0_2= 'not' )
                    int alt4=2;
                    int LA4_0 = input.LA(1);

                    if ( (LA4_0==13) ) {
                        alt4=1;
                    }
                    else if ( (LA4_0==14) ) {
                        alt4=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 4, 0, input);

                        throw nvae;
                    }
                    switch (alt4) {
                        case 1 :
                            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:347:3: lv_neg_0_1= '!'
                            {
                            lv_neg_0_1=(Token)match(input,13,FOLLOW_13_in_ruleDecContent673); 

                                    newLeafNode(lv_neg_0_1, grammarAccess.getDecContentAccess().getNegExclamationMarkKeyword_0_0_0());
                                

                            	        if (current==null) {
                            	            current = createModelElement(grammarAccess.getDecContentRule());
                            	        }
                                   		setWithLastConsumed(current, "neg", lv_neg_0_1, null);
                            	    

                            }
                            break;
                        case 2 :
                            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:359:8: lv_neg_0_2= 'not'
                            {
                            lv_neg_0_2=(Token)match(input,14,FOLLOW_14_in_ruleDecContent702); 

                                    newLeafNode(lv_neg_0_2, grammarAccess.getDecContentAccess().getNegNotKeyword_0_0_1());
                                

                            	        if (current==null) {
                            	            current = createModelElement(grammarAccess.getDecContentRule());
                            	        }
                                   		setWithLastConsumed(current, "neg", lv_neg_0_2, null);
                            	    

                            }
                            break;

                    }


                    }


                    }
                    break;

            }

            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:374:3: ( (lv_singleContent_1_0= ruleVarContent ) )
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:375:1: (lv_singleContent_1_0= ruleVarContent )
            {
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:375:1: (lv_singleContent_1_0= ruleVarContent )
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:376:3: lv_singleContent_1_0= ruleVarContent
            {
             
            	        newCompositeNode(grammarAccess.getDecContentAccess().getSingleContentVarContentParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleVarContent_in_ruleDecContent740);
            lv_singleContent_1_0=ruleVarContent();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getDecContentRule());
            	        }
                   		set(
                   			current, 
                   			"singleContent",
                    		lv_singleContent_1_0, 
                    		"VarContent");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:392:2: ( ( (lv_op_2_0= ruleOPERATOR ) ) ( (lv_nextCon_3_0= ruleVarContent ) ) )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( ((LA6_0>=15 && LA6_0<=16)||(LA6_0>=44 && LA6_0<=47)) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:392:3: ( (lv_op_2_0= ruleOPERATOR ) ) ( (lv_nextCon_3_0= ruleVarContent ) )
            	    {
            	    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:392:3: ( (lv_op_2_0= ruleOPERATOR ) )
            	    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:393:1: (lv_op_2_0= ruleOPERATOR )
            	    {
            	    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:393:1: (lv_op_2_0= ruleOPERATOR )
            	    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:394:3: lv_op_2_0= ruleOPERATOR
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getDecContentAccess().getOpOPERATORParserRuleCall_2_0_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleOPERATOR_in_ruleDecContent762);
            	    lv_op_2_0=ruleOPERATOR();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getDecContentRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"op",
            	            		lv_op_2_0, 
            	            		"OPERATOR");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }

            	    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:410:2: ( (lv_nextCon_3_0= ruleVarContent ) )
            	    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:411:1: (lv_nextCon_3_0= ruleVarContent )
            	    {
            	    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:411:1: (lv_nextCon_3_0= ruleVarContent )
            	    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:412:3: lv_nextCon_3_0= ruleVarContent
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getDecContentAccess().getNextConVarContentParserRuleCall_2_1_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleVarContent_in_ruleDecContent783);
            	    lv_nextCon_3_0=ruleVarContent();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getDecContentRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"nextCon",
            	            		lv_nextCon_3_0, 
            	            		"VarContent");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop6;
                }
            } while (true);


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleDecContent"


    // $ANTLR start "entryRuleVarContent"
    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:436:1: entryRuleVarContent returns [EObject current=null] : iv_ruleVarContent= ruleVarContent EOF ;
    public final EObject entryRuleVarContent() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleVarContent = null;


        try {
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:437:2: (iv_ruleVarContent= ruleVarContent EOF )
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:438:2: iv_ruleVarContent= ruleVarContent EOF
            {
             newCompositeNode(grammarAccess.getVarContentRule()); 
            pushFollow(FOLLOW_ruleVarContent_in_entryRuleVarContent821);
            iv_ruleVarContent=ruleVarContent();

            state._fsp--;

             current =iv_ruleVarContent; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleVarContent831); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleVarContent"


    // $ANTLR start "ruleVarContent"
    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:445:1: ruleVarContent returns [EObject current=null] : ( ( ( ( (lv_unOP_0_1= '+' | lv_unOP_0_2= '-' ) ) )? ( (lv_num_1_0= RULE_NUMBER ) ) ) | ( (lv_string_2_0= RULE_STRING ) ) | ( ( ( (lv_unOP_3_1= '+' | lv_unOP_3_2= '-' ) ) )? ( ( (otherlv_4= RULE_ID ) ) | ( (lv_forEachVar_5_0= '_x' ) ) ) ( ( (lv_sel_6_0= 'select' ) ) ( (lv_index_7_0= RULE_NUMBER ) ) )? ) | ( (otherlv_8= '+' )? ( (lv_arrayContent_9_0= ruleArrayLiteral ) ) ( ( (lv_execute_10_0= 'call' ) ) ( (lv_methodName_11_0= ruleMethodName ) ) )? ) | ( ( ( (lv_unOP_12_1= '+' | lv_unOP_12_2= '-' ) ) )? ( (lv_embraced_13_0= '(' ) ) ( (lv_embrCon_14_0= ruleBracketContent ) ) otherlv_15= ')' ) | ( (lv_bool_16_0= ruleBoolean ) ) | ( ( (lv_param_17_0= '_this' ) ) (otherlv_18= 'select' ( (lv_index_19_0= RULE_NUMBER ) ) )? ) ) ;
    public final EObject ruleVarContent() throws RecognitionException {
        EObject current = null;

        Token lv_unOP_0_1=null;
        Token lv_unOP_0_2=null;
        Token lv_num_1_0=null;
        Token lv_string_2_0=null;
        Token lv_unOP_3_1=null;
        Token lv_unOP_3_2=null;
        Token otherlv_4=null;
        Token lv_forEachVar_5_0=null;
        Token lv_sel_6_0=null;
        Token lv_index_7_0=null;
        Token otherlv_8=null;
        Token lv_execute_10_0=null;
        Token lv_unOP_12_1=null;
        Token lv_unOP_12_2=null;
        Token lv_embraced_13_0=null;
        Token otherlv_15=null;
        Token lv_param_17_0=null;
        Token otherlv_18=null;
        Token lv_index_19_0=null;
        EObject lv_arrayContent_9_0 = null;

        EObject lv_methodName_11_0 = null;

        EObject lv_embrCon_14_0 = null;

        EObject lv_bool_16_0 = null;


         enterRule(); 
            
        try {
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:448:28: ( ( ( ( ( (lv_unOP_0_1= '+' | lv_unOP_0_2= '-' ) ) )? ( (lv_num_1_0= RULE_NUMBER ) ) ) | ( (lv_string_2_0= RULE_STRING ) ) | ( ( ( (lv_unOP_3_1= '+' | lv_unOP_3_2= '-' ) ) )? ( ( (otherlv_4= RULE_ID ) ) | ( (lv_forEachVar_5_0= '_x' ) ) ) ( ( (lv_sel_6_0= 'select' ) ) ( (lv_index_7_0= RULE_NUMBER ) ) )? ) | ( (otherlv_8= '+' )? ( (lv_arrayContent_9_0= ruleArrayLiteral ) ) ( ( (lv_execute_10_0= 'call' ) ) ( (lv_methodName_11_0= ruleMethodName ) ) )? ) | ( ( ( (lv_unOP_12_1= '+' | lv_unOP_12_2= '-' ) ) )? ( (lv_embraced_13_0= '(' ) ) ( (lv_embrCon_14_0= ruleBracketContent ) ) otherlv_15= ')' ) | ( (lv_bool_16_0= ruleBoolean ) ) | ( ( (lv_param_17_0= '_this' ) ) (otherlv_18= 'select' ( (lv_index_19_0= RULE_NUMBER ) ) )? ) ) )
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:449:1: ( ( ( ( (lv_unOP_0_1= '+' | lv_unOP_0_2= '-' ) ) )? ( (lv_num_1_0= RULE_NUMBER ) ) ) | ( (lv_string_2_0= RULE_STRING ) ) | ( ( ( (lv_unOP_3_1= '+' | lv_unOP_3_2= '-' ) ) )? ( ( (otherlv_4= RULE_ID ) ) | ( (lv_forEachVar_5_0= '_x' ) ) ) ( ( (lv_sel_6_0= 'select' ) ) ( (lv_index_7_0= RULE_NUMBER ) ) )? ) | ( (otherlv_8= '+' )? ( (lv_arrayContent_9_0= ruleArrayLiteral ) ) ( ( (lv_execute_10_0= 'call' ) ) ( (lv_methodName_11_0= ruleMethodName ) ) )? ) | ( ( ( (lv_unOP_12_1= '+' | lv_unOP_12_2= '-' ) ) )? ( (lv_embraced_13_0= '(' ) ) ( (lv_embrCon_14_0= ruleBracketContent ) ) otherlv_15= ')' ) | ( (lv_bool_16_0= ruleBoolean ) ) | ( ( (lv_param_17_0= '_this' ) ) (otherlv_18= 'select' ( (lv_index_19_0= RULE_NUMBER ) ) )? ) )
            {
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:449:1: ( ( ( ( (lv_unOP_0_1= '+' | lv_unOP_0_2= '-' ) ) )? ( (lv_num_1_0= RULE_NUMBER ) ) ) | ( (lv_string_2_0= RULE_STRING ) ) | ( ( ( (lv_unOP_3_1= '+' | lv_unOP_3_2= '-' ) ) )? ( ( (otherlv_4= RULE_ID ) ) | ( (lv_forEachVar_5_0= '_x' ) ) ) ( ( (lv_sel_6_0= 'select' ) ) ( (lv_index_7_0= RULE_NUMBER ) ) )? ) | ( (otherlv_8= '+' )? ( (lv_arrayContent_9_0= ruleArrayLiteral ) ) ( ( (lv_execute_10_0= 'call' ) ) ( (lv_methodName_11_0= ruleMethodName ) ) )? ) | ( ( ( (lv_unOP_12_1= '+' | lv_unOP_12_2= '-' ) ) )? ( (lv_embraced_13_0= '(' ) ) ( (lv_embrCon_14_0= ruleBracketContent ) ) otherlv_15= ')' ) | ( (lv_bool_16_0= ruleBoolean ) ) | ( ( (lv_param_17_0= '_this' ) ) (otherlv_18= 'select' ( (lv_index_19_0= RULE_NUMBER ) ) )? ) )
            int alt18=7;
            alt18 = dfa18.predict(input);
            switch (alt18) {
                case 1 :
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:449:2: ( ( ( (lv_unOP_0_1= '+' | lv_unOP_0_2= '-' ) ) )? ( (lv_num_1_0= RULE_NUMBER ) ) )
                    {
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:449:2: ( ( ( (lv_unOP_0_1= '+' | lv_unOP_0_2= '-' ) ) )? ( (lv_num_1_0= RULE_NUMBER ) ) )
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:449:3: ( ( (lv_unOP_0_1= '+' | lv_unOP_0_2= '-' ) ) )? ( (lv_num_1_0= RULE_NUMBER ) )
                    {
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:449:3: ( ( (lv_unOP_0_1= '+' | lv_unOP_0_2= '-' ) ) )?
                    int alt8=2;
                    int LA8_0 = input.LA(1);

                    if ( ((LA8_0>=15 && LA8_0<=16)) ) {
                        alt8=1;
                    }
                    switch (alt8) {
                        case 1 :
                            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:450:1: ( (lv_unOP_0_1= '+' | lv_unOP_0_2= '-' ) )
                            {
                            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:450:1: ( (lv_unOP_0_1= '+' | lv_unOP_0_2= '-' ) )
                            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:451:1: (lv_unOP_0_1= '+' | lv_unOP_0_2= '-' )
                            {
                            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:451:1: (lv_unOP_0_1= '+' | lv_unOP_0_2= '-' )
                            int alt7=2;
                            int LA7_0 = input.LA(1);

                            if ( (LA7_0==15) ) {
                                alt7=1;
                            }
                            else if ( (LA7_0==16) ) {
                                alt7=2;
                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("", 7, 0, input);

                                throw nvae;
                            }
                            switch (alt7) {
                                case 1 :
                                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:452:3: lv_unOP_0_1= '+'
                                    {
                                    lv_unOP_0_1=(Token)match(input,15,FOLLOW_15_in_ruleVarContent877); 

                                            newLeafNode(lv_unOP_0_1, grammarAccess.getVarContentAccess().getUnOPPlusSignKeyword_0_0_0_0());
                                        

                                    	        if (current==null) {
                                    	            current = createModelElement(grammarAccess.getVarContentRule());
                                    	        }
                                           		setWithLastConsumed(current, "unOP", lv_unOP_0_1, null);
                                    	    

                                    }
                                    break;
                                case 2 :
                                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:464:8: lv_unOP_0_2= '-'
                                    {
                                    lv_unOP_0_2=(Token)match(input,16,FOLLOW_16_in_ruleVarContent906); 

                                            newLeafNode(lv_unOP_0_2, grammarAccess.getVarContentAccess().getUnOPHyphenMinusKeyword_0_0_0_1());
                                        

                                    	        if (current==null) {
                                    	            current = createModelElement(grammarAccess.getVarContentRule());
                                    	        }
                                           		setWithLastConsumed(current, "unOP", lv_unOP_0_2, null);
                                    	    

                                    }
                                    break;

                            }


                            }


                            }
                            break;

                    }

                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:479:3: ( (lv_num_1_0= RULE_NUMBER ) )
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:480:1: (lv_num_1_0= RULE_NUMBER )
                    {
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:480:1: (lv_num_1_0= RULE_NUMBER )
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:481:3: lv_num_1_0= RULE_NUMBER
                    {
                    lv_num_1_0=(Token)match(input,RULE_NUMBER,FOLLOW_RULE_NUMBER_in_ruleVarContent940); 

                    			newLeafNode(lv_num_1_0, grammarAccess.getVarContentAccess().getNumNUMBERTerminalRuleCall_0_1_0()); 
                    		

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getVarContentRule());
                    	        }
                           		setWithLastConsumed(
                           			current, 
                           			"num",
                            		lv_num_1_0, 
                            		"NUMBER");
                    	    

                    }


                    }


                    }


                    }
                    break;
                case 2 :
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:498:6: ( (lv_string_2_0= RULE_STRING ) )
                    {
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:498:6: ( (lv_string_2_0= RULE_STRING ) )
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:499:1: (lv_string_2_0= RULE_STRING )
                    {
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:499:1: (lv_string_2_0= RULE_STRING )
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:500:3: lv_string_2_0= RULE_STRING
                    {
                    lv_string_2_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleVarContent969); 

                    			newLeafNode(lv_string_2_0, grammarAccess.getVarContentAccess().getStringSTRINGTerminalRuleCall_1_0()); 
                    		

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getVarContentRule());
                    	        }
                           		setWithLastConsumed(
                           			current, 
                           			"string",
                            		lv_string_2_0, 
                            		"STRING");
                    	    

                    }


                    }


                    }
                    break;
                case 3 :
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:517:6: ( ( ( (lv_unOP_3_1= '+' | lv_unOP_3_2= '-' ) ) )? ( ( (otherlv_4= RULE_ID ) ) | ( (lv_forEachVar_5_0= '_x' ) ) ) ( ( (lv_sel_6_0= 'select' ) ) ( (lv_index_7_0= RULE_NUMBER ) ) )? )
                    {
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:517:6: ( ( ( (lv_unOP_3_1= '+' | lv_unOP_3_2= '-' ) ) )? ( ( (otherlv_4= RULE_ID ) ) | ( (lv_forEachVar_5_0= '_x' ) ) ) ( ( (lv_sel_6_0= 'select' ) ) ( (lv_index_7_0= RULE_NUMBER ) ) )? )
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:517:7: ( ( (lv_unOP_3_1= '+' | lv_unOP_3_2= '-' ) ) )? ( ( (otherlv_4= RULE_ID ) ) | ( (lv_forEachVar_5_0= '_x' ) ) ) ( ( (lv_sel_6_0= 'select' ) ) ( (lv_index_7_0= RULE_NUMBER ) ) )?
                    {
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:517:7: ( ( (lv_unOP_3_1= '+' | lv_unOP_3_2= '-' ) ) )?
                    int alt10=2;
                    int LA10_0 = input.LA(1);

                    if ( ((LA10_0>=15 && LA10_0<=16)) ) {
                        alt10=1;
                    }
                    switch (alt10) {
                        case 1 :
                            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:518:1: ( (lv_unOP_3_1= '+' | lv_unOP_3_2= '-' ) )
                            {
                            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:518:1: ( (lv_unOP_3_1= '+' | lv_unOP_3_2= '-' ) )
                            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:519:1: (lv_unOP_3_1= '+' | lv_unOP_3_2= '-' )
                            {
                            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:519:1: (lv_unOP_3_1= '+' | lv_unOP_3_2= '-' )
                            int alt9=2;
                            int LA9_0 = input.LA(1);

                            if ( (LA9_0==15) ) {
                                alt9=1;
                            }
                            else if ( (LA9_0==16) ) {
                                alt9=2;
                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("", 9, 0, input);

                                throw nvae;
                            }
                            switch (alt9) {
                                case 1 :
                                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:520:3: lv_unOP_3_1= '+'
                                    {
                                    lv_unOP_3_1=(Token)match(input,15,FOLLOW_15_in_ruleVarContent1001); 

                                            newLeafNode(lv_unOP_3_1, grammarAccess.getVarContentAccess().getUnOPPlusSignKeyword_2_0_0_0());
                                        

                                    	        if (current==null) {
                                    	            current = createModelElement(grammarAccess.getVarContentRule());
                                    	        }
                                           		setWithLastConsumed(current, "unOP", lv_unOP_3_1, null);
                                    	    

                                    }
                                    break;
                                case 2 :
                                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:532:8: lv_unOP_3_2= '-'
                                    {
                                    lv_unOP_3_2=(Token)match(input,16,FOLLOW_16_in_ruleVarContent1030); 

                                            newLeafNode(lv_unOP_3_2, grammarAccess.getVarContentAccess().getUnOPHyphenMinusKeyword_2_0_0_1());
                                        

                                    	        if (current==null) {
                                    	            current = createModelElement(grammarAccess.getVarContentRule());
                                    	        }
                                           		setWithLastConsumed(current, "unOP", lv_unOP_3_2, null);
                                    	    

                                    }
                                    break;

                            }


                            }


                            }
                            break;

                    }

                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:547:3: ( ( (otherlv_4= RULE_ID ) ) | ( (lv_forEachVar_5_0= '_x' ) ) )
                    int alt11=2;
                    int LA11_0 = input.LA(1);

                    if ( (LA11_0==RULE_ID) ) {
                        alt11=1;
                    }
                    else if ( (LA11_0==17) ) {
                        alt11=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 11, 0, input);

                        throw nvae;
                    }
                    switch (alt11) {
                        case 1 :
                            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:547:4: ( (otherlv_4= RULE_ID ) )
                            {
                            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:547:4: ( (otherlv_4= RULE_ID ) )
                            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:548:1: (otherlv_4= RULE_ID )
                            {
                            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:548:1: (otherlv_4= RULE_ID )
                            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:549:3: otherlv_4= RULE_ID
                            {

                            			if (current==null) {
                            	            current = createModelElement(grammarAccess.getVarContentRule());
                            	        }
                                    
                            otherlv_4=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleVarContent1068); 

                            		newLeafNode(otherlv_4, grammarAccess.getVarContentAccess().getReferenceDeclarationCrossReference_2_1_0_0()); 
                            	

                            }


                            }


                            }
                            break;
                        case 2 :
                            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:561:6: ( (lv_forEachVar_5_0= '_x' ) )
                            {
                            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:561:6: ( (lv_forEachVar_5_0= '_x' ) )
                            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:562:1: (lv_forEachVar_5_0= '_x' )
                            {
                            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:562:1: (lv_forEachVar_5_0= '_x' )
                            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:563:3: lv_forEachVar_5_0= '_x'
                            {
                            lv_forEachVar_5_0=(Token)match(input,17,FOLLOW_17_in_ruleVarContent1092); 

                                    newLeafNode(lv_forEachVar_5_0, grammarAccess.getVarContentAccess().getForEachVar_xKeyword_2_1_1_0());
                                

                            	        if (current==null) {
                            	            current = createModelElement(grammarAccess.getVarContentRule());
                            	        }
                                   		setWithLastConsumed(current, "forEachVar", lv_forEachVar_5_0, "_x");
                            	    

                            }


                            }


                            }
                            break;

                    }

                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:576:3: ( ( (lv_sel_6_0= 'select' ) ) ( (lv_index_7_0= RULE_NUMBER ) ) )?
                    int alt12=2;
                    int LA12_0 = input.LA(1);

                    if ( (LA12_0==18) ) {
                        alt12=1;
                    }
                    switch (alt12) {
                        case 1 :
                            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:576:4: ( (lv_sel_6_0= 'select' ) ) ( (lv_index_7_0= RULE_NUMBER ) )
                            {
                            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:576:4: ( (lv_sel_6_0= 'select' ) )
                            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:577:1: (lv_sel_6_0= 'select' )
                            {
                            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:577:1: (lv_sel_6_0= 'select' )
                            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:578:3: lv_sel_6_0= 'select'
                            {
                            lv_sel_6_0=(Token)match(input,18,FOLLOW_18_in_ruleVarContent1125); 

                                    newLeafNode(lv_sel_6_0, grammarAccess.getVarContentAccess().getSelSelectKeyword_2_2_0_0());
                                

                            	        if (current==null) {
                            	            current = createModelElement(grammarAccess.getVarContentRule());
                            	        }
                                   		setWithLastConsumed(current, "sel", lv_sel_6_0, "select");
                            	    

                            }


                            }

                            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:591:2: ( (lv_index_7_0= RULE_NUMBER ) )
                            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:592:1: (lv_index_7_0= RULE_NUMBER )
                            {
                            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:592:1: (lv_index_7_0= RULE_NUMBER )
                            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:593:3: lv_index_7_0= RULE_NUMBER
                            {
                            lv_index_7_0=(Token)match(input,RULE_NUMBER,FOLLOW_RULE_NUMBER_in_ruleVarContent1155); 

                            			newLeafNode(lv_index_7_0, grammarAccess.getVarContentAccess().getIndexNUMBERTerminalRuleCall_2_2_1_0()); 
                            		

                            	        if (current==null) {
                            	            current = createModelElement(grammarAccess.getVarContentRule());
                            	        }
                                   		setWithLastConsumed(
                                   			current, 
                                   			"index",
                                    		lv_index_7_0, 
                                    		"NUMBER");
                            	    

                            }


                            }


                            }
                            break;

                    }


                    }


                    }
                    break;
                case 4 :
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:610:6: ( (otherlv_8= '+' )? ( (lv_arrayContent_9_0= ruleArrayLiteral ) ) ( ( (lv_execute_10_0= 'call' ) ) ( (lv_methodName_11_0= ruleMethodName ) ) )? )
                    {
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:610:6: ( (otherlv_8= '+' )? ( (lv_arrayContent_9_0= ruleArrayLiteral ) ) ( ( (lv_execute_10_0= 'call' ) ) ( (lv_methodName_11_0= ruleMethodName ) ) )? )
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:610:7: (otherlv_8= '+' )? ( (lv_arrayContent_9_0= ruleArrayLiteral ) ) ( ( (lv_execute_10_0= 'call' ) ) ( (lv_methodName_11_0= ruleMethodName ) ) )?
                    {
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:610:7: (otherlv_8= '+' )?
                    int alt13=2;
                    int LA13_0 = input.LA(1);

                    if ( (LA13_0==15) ) {
                        alt13=1;
                    }
                    switch (alt13) {
                        case 1 :
                            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:610:9: otherlv_8= '+'
                            {
                            otherlv_8=(Token)match(input,15,FOLLOW_15_in_ruleVarContent1183); 

                                	newLeafNode(otherlv_8, grammarAccess.getVarContentAccess().getPlusSignKeyword_3_0());
                                

                            }
                            break;

                    }

                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:614:3: ( (lv_arrayContent_9_0= ruleArrayLiteral ) )
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:615:1: (lv_arrayContent_9_0= ruleArrayLiteral )
                    {
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:615:1: (lv_arrayContent_9_0= ruleArrayLiteral )
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:616:3: lv_arrayContent_9_0= ruleArrayLiteral
                    {
                     
                    	        newCompositeNode(grammarAccess.getVarContentAccess().getArrayContentArrayLiteralParserRuleCall_3_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleArrayLiteral_in_ruleVarContent1206);
                    lv_arrayContent_9_0=ruleArrayLiteral();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getVarContentRule());
                    	        }
                           		set(
                           			current, 
                           			"arrayContent",
                            		lv_arrayContent_9_0, 
                            		"ArrayLiteral");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:632:2: ( ( (lv_execute_10_0= 'call' ) ) ( (lv_methodName_11_0= ruleMethodName ) ) )?
                    int alt14=2;
                    int LA14_0 = input.LA(1);

                    if ( (LA14_0==19) ) {
                        alt14=1;
                    }
                    switch (alt14) {
                        case 1 :
                            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:632:3: ( (lv_execute_10_0= 'call' ) ) ( (lv_methodName_11_0= ruleMethodName ) )
                            {
                            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:632:3: ( (lv_execute_10_0= 'call' ) )
                            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:633:1: (lv_execute_10_0= 'call' )
                            {
                            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:633:1: (lv_execute_10_0= 'call' )
                            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:634:3: lv_execute_10_0= 'call'
                            {
                            lv_execute_10_0=(Token)match(input,19,FOLLOW_19_in_ruleVarContent1225); 

                                    newLeafNode(lv_execute_10_0, grammarAccess.getVarContentAccess().getExecuteCallKeyword_3_2_0_0());
                                

                            	        if (current==null) {
                            	            current = createModelElement(grammarAccess.getVarContentRule());
                            	        }
                                   		setWithLastConsumed(current, "execute", lv_execute_10_0, "call");
                            	    

                            }


                            }

                            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:647:2: ( (lv_methodName_11_0= ruleMethodName ) )
                            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:648:1: (lv_methodName_11_0= ruleMethodName )
                            {
                            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:648:1: (lv_methodName_11_0= ruleMethodName )
                            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:649:3: lv_methodName_11_0= ruleMethodName
                            {
                             
                            	        newCompositeNode(grammarAccess.getVarContentAccess().getMethodNameMethodNameParserRuleCall_3_2_1_0()); 
                            	    
                            pushFollow(FOLLOW_ruleMethodName_in_ruleVarContent1259);
                            lv_methodName_11_0=ruleMethodName();

                            state._fsp--;


                            	        if (current==null) {
                            	            current = createModelElementForParent(grammarAccess.getVarContentRule());
                            	        }
                                   		set(
                                   			current, 
                                   			"methodName",
                                    		lv_methodName_11_0, 
                                    		"MethodName");
                            	        afterParserOrEnumRuleCall();
                            	    

                            }


                            }


                            }
                            break;

                    }


                    }


                    }
                    break;
                case 5 :
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:666:6: ( ( ( (lv_unOP_12_1= '+' | lv_unOP_12_2= '-' ) ) )? ( (lv_embraced_13_0= '(' ) ) ( (lv_embrCon_14_0= ruleBracketContent ) ) otherlv_15= ')' )
                    {
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:666:6: ( ( ( (lv_unOP_12_1= '+' | lv_unOP_12_2= '-' ) ) )? ( (lv_embraced_13_0= '(' ) ) ( (lv_embrCon_14_0= ruleBracketContent ) ) otherlv_15= ')' )
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:666:7: ( ( (lv_unOP_12_1= '+' | lv_unOP_12_2= '-' ) ) )? ( (lv_embraced_13_0= '(' ) ) ( (lv_embrCon_14_0= ruleBracketContent ) ) otherlv_15= ')'
                    {
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:666:7: ( ( (lv_unOP_12_1= '+' | lv_unOP_12_2= '-' ) ) )?
                    int alt16=2;
                    int LA16_0 = input.LA(1);

                    if ( ((LA16_0>=15 && LA16_0<=16)) ) {
                        alt16=1;
                    }
                    switch (alt16) {
                        case 1 :
                            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:667:1: ( (lv_unOP_12_1= '+' | lv_unOP_12_2= '-' ) )
                            {
                            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:667:1: ( (lv_unOP_12_1= '+' | lv_unOP_12_2= '-' ) )
                            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:668:1: (lv_unOP_12_1= '+' | lv_unOP_12_2= '-' )
                            {
                            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:668:1: (lv_unOP_12_1= '+' | lv_unOP_12_2= '-' )
                            int alt15=2;
                            int LA15_0 = input.LA(1);

                            if ( (LA15_0==15) ) {
                                alt15=1;
                            }
                            else if ( (LA15_0==16) ) {
                                alt15=2;
                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("", 15, 0, input);

                                throw nvae;
                            }
                            switch (alt15) {
                                case 1 :
                                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:669:3: lv_unOP_12_1= '+'
                                    {
                                    lv_unOP_12_1=(Token)match(input,15,FOLLOW_15_in_ruleVarContent1289); 

                                            newLeafNode(lv_unOP_12_1, grammarAccess.getVarContentAccess().getUnOPPlusSignKeyword_4_0_0_0());
                                        

                                    	        if (current==null) {
                                    	            current = createModelElement(grammarAccess.getVarContentRule());
                                    	        }
                                           		setWithLastConsumed(current, "unOP", lv_unOP_12_1, null);
                                    	    

                                    }
                                    break;
                                case 2 :
                                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:681:8: lv_unOP_12_2= '-'
                                    {
                                    lv_unOP_12_2=(Token)match(input,16,FOLLOW_16_in_ruleVarContent1318); 

                                            newLeafNode(lv_unOP_12_2, grammarAccess.getVarContentAccess().getUnOPHyphenMinusKeyword_4_0_0_1());
                                        

                                    	        if (current==null) {
                                    	            current = createModelElement(grammarAccess.getVarContentRule());
                                    	        }
                                           		setWithLastConsumed(current, "unOP", lv_unOP_12_2, null);
                                    	    

                                    }
                                    break;

                            }


                            }


                            }
                            break;

                    }

                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:696:3: ( (lv_embraced_13_0= '(' ) )
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:697:1: (lv_embraced_13_0= '(' )
                    {
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:697:1: (lv_embraced_13_0= '(' )
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:698:3: lv_embraced_13_0= '('
                    {
                    lv_embraced_13_0=(Token)match(input,20,FOLLOW_20_in_ruleVarContent1353); 

                            newLeafNode(lv_embraced_13_0, grammarAccess.getVarContentAccess().getEmbracedLeftParenthesisKeyword_4_1_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getVarContentRule());
                    	        }
                           		setWithLastConsumed(current, "embraced", lv_embraced_13_0, "(");
                    	    

                    }


                    }

                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:711:2: ( (lv_embrCon_14_0= ruleBracketContent ) )
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:712:1: (lv_embrCon_14_0= ruleBracketContent )
                    {
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:712:1: (lv_embrCon_14_0= ruleBracketContent )
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:713:3: lv_embrCon_14_0= ruleBracketContent
                    {
                     
                    	        newCompositeNode(grammarAccess.getVarContentAccess().getEmbrConBracketContentParserRuleCall_4_2_0()); 
                    	    
                    pushFollow(FOLLOW_ruleBracketContent_in_ruleVarContent1387);
                    lv_embrCon_14_0=ruleBracketContent();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getVarContentRule());
                    	        }
                           		set(
                           			current, 
                           			"embrCon",
                            		lv_embrCon_14_0, 
                            		"BracketContent");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    otherlv_15=(Token)match(input,21,FOLLOW_21_in_ruleVarContent1399); 

                        	newLeafNode(otherlv_15, grammarAccess.getVarContentAccess().getRightParenthesisKeyword_4_3());
                        

                    }


                    }
                    break;
                case 6 :
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:734:6: ( (lv_bool_16_0= ruleBoolean ) )
                    {
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:734:6: ( (lv_bool_16_0= ruleBoolean ) )
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:735:1: (lv_bool_16_0= ruleBoolean )
                    {
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:735:1: (lv_bool_16_0= ruleBoolean )
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:736:3: lv_bool_16_0= ruleBoolean
                    {
                     
                    	        newCompositeNode(grammarAccess.getVarContentAccess().getBoolBooleanParserRuleCall_5_0()); 
                    	    
                    pushFollow(FOLLOW_ruleBoolean_in_ruleVarContent1427);
                    lv_bool_16_0=ruleBoolean();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getVarContentRule());
                    	        }
                           		set(
                           			current, 
                           			"bool",
                            		lv_bool_16_0, 
                            		"Boolean");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;
                case 7 :
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:753:6: ( ( (lv_param_17_0= '_this' ) ) (otherlv_18= 'select' ( (lv_index_19_0= RULE_NUMBER ) ) )? )
                    {
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:753:6: ( ( (lv_param_17_0= '_this' ) ) (otherlv_18= 'select' ( (lv_index_19_0= RULE_NUMBER ) ) )? )
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:753:7: ( (lv_param_17_0= '_this' ) ) (otherlv_18= 'select' ( (lv_index_19_0= RULE_NUMBER ) ) )?
                    {
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:753:7: ( (lv_param_17_0= '_this' ) )
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:754:1: (lv_param_17_0= '_this' )
                    {
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:754:1: (lv_param_17_0= '_this' )
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:755:3: lv_param_17_0= '_this'
                    {
                    lv_param_17_0=(Token)match(input,22,FOLLOW_22_in_ruleVarContent1452); 

                            newLeafNode(lv_param_17_0, grammarAccess.getVarContentAccess().getParam_thisKeyword_6_0_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getVarContentRule());
                    	        }
                           		setWithLastConsumed(current, "param", lv_param_17_0, "_this");
                    	    

                    }


                    }

                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:768:2: (otherlv_18= 'select' ( (lv_index_19_0= RULE_NUMBER ) ) )?
                    int alt17=2;
                    int LA17_0 = input.LA(1);

                    if ( (LA17_0==18) ) {
                        alt17=1;
                    }
                    switch (alt17) {
                        case 1 :
                            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:768:4: otherlv_18= 'select' ( (lv_index_19_0= RULE_NUMBER ) )
                            {
                            otherlv_18=(Token)match(input,18,FOLLOW_18_in_ruleVarContent1478); 

                                	newLeafNode(otherlv_18, grammarAccess.getVarContentAccess().getSelectKeyword_6_1_0());
                                
                            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:772:1: ( (lv_index_19_0= RULE_NUMBER ) )
                            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:773:1: (lv_index_19_0= RULE_NUMBER )
                            {
                            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:773:1: (lv_index_19_0= RULE_NUMBER )
                            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:774:3: lv_index_19_0= RULE_NUMBER
                            {
                            lv_index_19_0=(Token)match(input,RULE_NUMBER,FOLLOW_RULE_NUMBER_in_ruleVarContent1495); 

                            			newLeafNode(lv_index_19_0, grammarAccess.getVarContentAccess().getIndexNUMBERTerminalRuleCall_6_1_1_0()); 
                            		

                            	        if (current==null) {
                            	            current = createModelElement(grammarAccess.getVarContentRule());
                            	        }
                                   		setWithLastConsumed(
                                   			current, 
                                   			"index",
                                    		lv_index_19_0, 
                                    		"NUMBER");
                            	    

                            }


                            }


                            }
                            break;

                    }


                    }


                    }
                    break;

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleVarContent"


    // $ANTLR start "entryRuleArrayLiteral"
    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:798:1: entryRuleArrayLiteral returns [EObject current=null] : iv_ruleArrayLiteral= ruleArrayLiteral EOF ;
    public final EObject entryRuleArrayLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleArrayLiteral = null;


        try {
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:799:2: (iv_ruleArrayLiteral= ruleArrayLiteral EOF )
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:800:2: iv_ruleArrayLiteral= ruleArrayLiteral EOF
            {
             newCompositeNode(grammarAccess.getArrayLiteralRule()); 
            pushFollow(FOLLOW_ruleArrayLiteral_in_entryRuleArrayLiteral1539);
            iv_ruleArrayLiteral=ruleArrayLiteral();

            state._fsp--;

             current =iv_ruleArrayLiteral; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleArrayLiteral1549); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleArrayLiteral"


    // $ANTLR start "ruleArrayLiteral"
    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:807:1: ruleArrayLiteral returns [EObject current=null] : ( ( (lv_con_0_0= '[' ) ) ( ( (lv_content_1_0= ruleVarContent ) ) (otherlv_2= ',' ( (lv_nextContent_3_0= ruleVarContent ) ) )* )? otherlv_4= ']' ) ;
    public final EObject ruleArrayLiteral() throws RecognitionException {
        EObject current = null;

        Token lv_con_0_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_content_1_0 = null;

        EObject lv_nextContent_3_0 = null;


         enterRule(); 
            
        try {
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:810:28: ( ( ( (lv_con_0_0= '[' ) ) ( ( (lv_content_1_0= ruleVarContent ) ) (otherlv_2= ',' ( (lv_nextContent_3_0= ruleVarContent ) ) )* )? otherlv_4= ']' ) )
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:811:1: ( ( (lv_con_0_0= '[' ) ) ( ( (lv_content_1_0= ruleVarContent ) ) (otherlv_2= ',' ( (lv_nextContent_3_0= ruleVarContent ) ) )* )? otherlv_4= ']' )
            {
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:811:1: ( ( (lv_con_0_0= '[' ) ) ( ( (lv_content_1_0= ruleVarContent ) ) (otherlv_2= ',' ( (lv_nextContent_3_0= ruleVarContent ) ) )* )? otherlv_4= ']' )
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:811:2: ( (lv_con_0_0= '[' ) ) ( ( (lv_content_1_0= ruleVarContent ) ) (otherlv_2= ',' ( (lv_nextContent_3_0= ruleVarContent ) ) )* )? otherlv_4= ']'
            {
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:811:2: ( (lv_con_0_0= '[' ) )
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:812:1: (lv_con_0_0= '[' )
            {
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:812:1: (lv_con_0_0= '[' )
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:813:3: lv_con_0_0= '['
            {
            lv_con_0_0=(Token)match(input,23,FOLLOW_23_in_ruleArrayLiteral1592); 

                    newLeafNode(lv_con_0_0, grammarAccess.getArrayLiteralAccess().getConLeftSquareBracketKeyword_0_0());
                

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getArrayLiteralRule());
            	        }
                   		setWithLastConsumed(current, "con", lv_con_0_0, "[");
            	    

            }


            }

            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:826:2: ( ( (lv_content_1_0= ruleVarContent ) ) (otherlv_2= ',' ( (lv_nextContent_3_0= ruleVarContent ) ) )* )?
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==RULE_ID||(LA20_0>=RULE_NUMBER && LA20_0<=RULE_STRING)||(LA20_0>=15 && LA20_0<=17)||LA20_0==20||(LA20_0>=22 && LA20_0<=23)||(LA20_0>=48 && LA20_0<=51)) ) {
                alt20=1;
            }
            switch (alt20) {
                case 1 :
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:826:3: ( (lv_content_1_0= ruleVarContent ) ) (otherlv_2= ',' ( (lv_nextContent_3_0= ruleVarContent ) ) )*
                    {
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:826:3: ( (lv_content_1_0= ruleVarContent ) )
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:827:1: (lv_content_1_0= ruleVarContent )
                    {
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:827:1: (lv_content_1_0= ruleVarContent )
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:828:3: lv_content_1_0= ruleVarContent
                    {
                     
                    	        newCompositeNode(grammarAccess.getArrayLiteralAccess().getContentVarContentParserRuleCall_1_0_0()); 
                    	    
                    pushFollow(FOLLOW_ruleVarContent_in_ruleArrayLiteral1627);
                    lv_content_1_0=ruleVarContent();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getArrayLiteralRule());
                    	        }
                           		set(
                           			current, 
                           			"content",
                            		lv_content_1_0, 
                            		"VarContent");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:844:2: (otherlv_2= ',' ( (lv_nextContent_3_0= ruleVarContent ) ) )*
                    loop19:
                    do {
                        int alt19=2;
                        int LA19_0 = input.LA(1);

                        if ( (LA19_0==24) ) {
                            alt19=1;
                        }


                        switch (alt19) {
                    	case 1 :
                    	    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:844:4: otherlv_2= ',' ( (lv_nextContent_3_0= ruleVarContent ) )
                    	    {
                    	    otherlv_2=(Token)match(input,24,FOLLOW_24_in_ruleArrayLiteral1640); 

                    	        	newLeafNode(otherlv_2, grammarAccess.getArrayLiteralAccess().getCommaKeyword_1_1_0());
                    	        
                    	    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:848:1: ( (lv_nextContent_3_0= ruleVarContent ) )
                    	    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:849:1: (lv_nextContent_3_0= ruleVarContent )
                    	    {
                    	    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:849:1: (lv_nextContent_3_0= ruleVarContent )
                    	    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:850:3: lv_nextContent_3_0= ruleVarContent
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getArrayLiteralAccess().getNextContentVarContentParserRuleCall_1_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleVarContent_in_ruleArrayLiteral1661);
                    	    lv_nextContent_3_0=ruleVarContent();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getArrayLiteralRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"nextContent",
                    	            		lv_nextContent_3_0, 
                    	            		"VarContent");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop19;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_4=(Token)match(input,25,FOLLOW_25_in_ruleArrayLiteral1677); 

                	newLeafNode(otherlv_4, grammarAccess.getArrayLiteralAccess().getRightSquareBracketKeyword_2());
                

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleArrayLiteral"


    // $ANTLR start "entryRuleControlStructure"
    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:878:1: entryRuleControlStructure returns [EObject current=null] : iv_ruleControlStructure= ruleControlStructure EOF ;
    public final EObject entryRuleControlStructure() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleControlStructure = null;


        try {
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:879:2: (iv_ruleControlStructure= ruleControlStructure EOF )
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:880:2: iv_ruleControlStructure= ruleControlStructure EOF
            {
             newCompositeNode(grammarAccess.getControlStructureRule()); 
            pushFollow(FOLLOW_ruleControlStructure_in_entryRuleControlStructure1713);
            iv_ruleControlStructure=ruleControlStructure();

            state._fsp--;

             current =iv_ruleControlStructure; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleControlStructure1723); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleControlStructure"


    // $ANTLR start "ruleControlStructure"
    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:887:1: ruleControlStructure returns [EObject current=null] : ( ( (lv_ifStat_0_0= ruleifType ) ) | ( (lv_whileStat_1_0= ruleWhileType ) ) | ( (lv_forStat_2_0= ruleForType ) ) | ( (lv_forEachStat_3_0= ruleForeachType ) ) | ( (lv_switchStat_4_0= ruleSwitchType ) ) ) ;
    public final EObject ruleControlStructure() throws RecognitionException {
        EObject current = null;

        EObject lv_ifStat_0_0 = null;

        EObject lv_whileStat_1_0 = null;

        EObject lv_forStat_2_0 = null;

        EObject lv_forEachStat_3_0 = null;

        EObject lv_switchStat_4_0 = null;


         enterRule(); 
            
        try {
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:890:28: ( ( ( (lv_ifStat_0_0= ruleifType ) ) | ( (lv_whileStat_1_0= ruleWhileType ) ) | ( (lv_forStat_2_0= ruleForType ) ) | ( (lv_forEachStat_3_0= ruleForeachType ) ) | ( (lv_switchStat_4_0= ruleSwitchType ) ) ) )
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:891:1: ( ( (lv_ifStat_0_0= ruleifType ) ) | ( (lv_whileStat_1_0= ruleWhileType ) ) | ( (lv_forStat_2_0= ruleForType ) ) | ( (lv_forEachStat_3_0= ruleForeachType ) ) | ( (lv_switchStat_4_0= ruleSwitchType ) ) )
            {
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:891:1: ( ( (lv_ifStat_0_0= ruleifType ) ) | ( (lv_whileStat_1_0= ruleWhileType ) ) | ( (lv_forStat_2_0= ruleForType ) ) | ( (lv_forEachStat_3_0= ruleForeachType ) ) | ( (lv_switchStat_4_0= ruleSwitchType ) ) )
            int alt21=5;
            switch ( input.LA(1) ) {
            case 26:
                {
                alt21=1;
                }
                break;
            case 32:
                {
                alt21=2;
                }
                break;
            case 34:
                {
                alt21=3;
                }
                break;
            case 28:
                {
                alt21=4;
                }
                break;
            case 39:
                {
                alt21=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 21, 0, input);

                throw nvae;
            }

            switch (alt21) {
                case 1 :
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:891:2: ( (lv_ifStat_0_0= ruleifType ) )
                    {
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:891:2: ( (lv_ifStat_0_0= ruleifType ) )
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:892:1: (lv_ifStat_0_0= ruleifType )
                    {
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:892:1: (lv_ifStat_0_0= ruleifType )
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:893:3: lv_ifStat_0_0= ruleifType
                    {
                     
                    	        newCompositeNode(grammarAccess.getControlStructureAccess().getIfStatIfTypeParserRuleCall_0_0()); 
                    	    
                    pushFollow(FOLLOW_ruleifType_in_ruleControlStructure1769);
                    lv_ifStat_0_0=ruleifType();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getControlStructureRule());
                    	        }
                           		set(
                           			current, 
                           			"ifStat",
                            		lv_ifStat_0_0, 
                            		"ifType");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;
                case 2 :
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:910:6: ( (lv_whileStat_1_0= ruleWhileType ) )
                    {
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:910:6: ( (lv_whileStat_1_0= ruleWhileType ) )
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:911:1: (lv_whileStat_1_0= ruleWhileType )
                    {
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:911:1: (lv_whileStat_1_0= ruleWhileType )
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:912:3: lv_whileStat_1_0= ruleWhileType
                    {
                     
                    	        newCompositeNode(grammarAccess.getControlStructureAccess().getWhileStatWhileTypeParserRuleCall_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleWhileType_in_ruleControlStructure1796);
                    lv_whileStat_1_0=ruleWhileType();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getControlStructureRule());
                    	        }
                           		set(
                           			current, 
                           			"whileStat",
                            		lv_whileStat_1_0, 
                            		"WhileType");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;
                case 3 :
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:929:6: ( (lv_forStat_2_0= ruleForType ) )
                    {
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:929:6: ( (lv_forStat_2_0= ruleForType ) )
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:930:1: (lv_forStat_2_0= ruleForType )
                    {
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:930:1: (lv_forStat_2_0= ruleForType )
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:931:3: lv_forStat_2_0= ruleForType
                    {
                     
                    	        newCompositeNode(grammarAccess.getControlStructureAccess().getForStatForTypeParserRuleCall_2_0()); 
                    	    
                    pushFollow(FOLLOW_ruleForType_in_ruleControlStructure1823);
                    lv_forStat_2_0=ruleForType();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getControlStructureRule());
                    	        }
                           		set(
                           			current, 
                           			"forStat",
                            		lv_forStat_2_0, 
                            		"ForType");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;
                case 4 :
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:948:6: ( (lv_forEachStat_3_0= ruleForeachType ) )
                    {
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:948:6: ( (lv_forEachStat_3_0= ruleForeachType ) )
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:949:1: (lv_forEachStat_3_0= ruleForeachType )
                    {
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:949:1: (lv_forEachStat_3_0= ruleForeachType )
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:950:3: lv_forEachStat_3_0= ruleForeachType
                    {
                     
                    	        newCompositeNode(grammarAccess.getControlStructureAccess().getForEachStatForeachTypeParserRuleCall_3_0()); 
                    	    
                    pushFollow(FOLLOW_ruleForeachType_in_ruleControlStructure1850);
                    lv_forEachStat_3_0=ruleForeachType();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getControlStructureRule());
                    	        }
                           		set(
                           			current, 
                           			"forEachStat",
                            		lv_forEachStat_3_0, 
                            		"ForeachType");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;
                case 5 :
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:967:6: ( (lv_switchStat_4_0= ruleSwitchType ) )
                    {
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:967:6: ( (lv_switchStat_4_0= ruleSwitchType ) )
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:968:1: (lv_switchStat_4_0= ruleSwitchType )
                    {
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:968:1: (lv_switchStat_4_0= ruleSwitchType )
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:969:3: lv_switchStat_4_0= ruleSwitchType
                    {
                     
                    	        newCompositeNode(grammarAccess.getControlStructureAccess().getSwitchStatSwitchTypeParserRuleCall_4_0()); 
                    	    
                    pushFollow(FOLLOW_ruleSwitchType_in_ruleControlStructure1877);
                    lv_switchStat_4_0=ruleSwitchType();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getControlStructureRule());
                    	        }
                           		set(
                           			current, 
                           			"switchStat",
                            		lv_switchStat_4_0, 
                            		"SwitchType");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleControlStructure"


    // $ANTLR start "entryRuleifType"
    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:993:1: entryRuleifType returns [EObject current=null] : iv_ruleifType= ruleifType EOF ;
    public final EObject entryRuleifType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleifType = null;


        try {
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:994:2: (iv_ruleifType= ruleifType EOF )
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:995:2: iv_ruleifType= ruleifType EOF
            {
             newCompositeNode(grammarAccess.getIfTypeRule()); 
            pushFollow(FOLLOW_ruleifType_in_entryRuleifType1913);
            iv_ruleifType=ruleifType();

            state._fsp--;

             current =iv_ruleifType; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleifType1923); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleifType"


    // $ANTLR start "ruleifType"
    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1002:1: ruleifType returns [EObject current=null] : (otherlv_0= 'if' otherlv_1= '(' ( (lv_condition_2_0= ruleBooleanContent ) ) otherlv_3= ')' ( (otherlv_4= 'then' ( (otherlv_5= '{' ( (lv_thenCode_6_0= ruleCode ) )* otherlv_7= '}' (otherlv_8= 'else' otherlv_9= '{' ( (lv_elseCode_10_0= ruleCode ) )* otherlv_11= '}' )? ) | (otherlv_12= '[' otherlv_13= '{' ( (lv_thenCode_14_0= ruleCode ) )* otherlv_15= '}' otherlv_16= ',' otherlv_17= '{' ( (lv_elseCode_18_0= ruleCode ) )* otherlv_19= '}' otherlv_20= ']' ) ) ) | (otherlv_21= 'exitWith' otherlv_22= '{' ( (lv_exitCode_23_0= ruleCode ) )* otherlv_24= '}' ) ) ) ;
    public final EObject ruleifType() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_8=null;
        Token otherlv_9=null;
        Token otherlv_11=null;
        Token otherlv_12=null;
        Token otherlv_13=null;
        Token otherlv_15=null;
        Token otherlv_16=null;
        Token otherlv_17=null;
        Token otherlv_19=null;
        Token otherlv_20=null;
        Token otherlv_21=null;
        Token otherlv_22=null;
        Token otherlv_24=null;
        EObject lv_condition_2_0 = null;

        EObject lv_thenCode_6_0 = null;

        EObject lv_elseCode_10_0 = null;

        EObject lv_thenCode_14_0 = null;

        EObject lv_elseCode_18_0 = null;

        EObject lv_exitCode_23_0 = null;


         enterRule(); 
            
        try {
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1005:28: ( (otherlv_0= 'if' otherlv_1= '(' ( (lv_condition_2_0= ruleBooleanContent ) ) otherlv_3= ')' ( (otherlv_4= 'then' ( (otherlv_5= '{' ( (lv_thenCode_6_0= ruleCode ) )* otherlv_7= '}' (otherlv_8= 'else' otherlv_9= '{' ( (lv_elseCode_10_0= ruleCode ) )* otherlv_11= '}' )? ) | (otherlv_12= '[' otherlv_13= '{' ( (lv_thenCode_14_0= ruleCode ) )* otherlv_15= '}' otherlv_16= ',' otherlv_17= '{' ( (lv_elseCode_18_0= ruleCode ) )* otherlv_19= '}' otherlv_20= ']' ) ) ) | (otherlv_21= 'exitWith' otherlv_22= '{' ( (lv_exitCode_23_0= ruleCode ) )* otherlv_24= '}' ) ) ) )
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1006:1: (otherlv_0= 'if' otherlv_1= '(' ( (lv_condition_2_0= ruleBooleanContent ) ) otherlv_3= ')' ( (otherlv_4= 'then' ( (otherlv_5= '{' ( (lv_thenCode_6_0= ruleCode ) )* otherlv_7= '}' (otherlv_8= 'else' otherlv_9= '{' ( (lv_elseCode_10_0= ruleCode ) )* otherlv_11= '}' )? ) | (otherlv_12= '[' otherlv_13= '{' ( (lv_thenCode_14_0= ruleCode ) )* otherlv_15= '}' otherlv_16= ',' otherlv_17= '{' ( (lv_elseCode_18_0= ruleCode ) )* otherlv_19= '}' otherlv_20= ']' ) ) ) | (otherlv_21= 'exitWith' otherlv_22= '{' ( (lv_exitCode_23_0= ruleCode ) )* otherlv_24= '}' ) ) )
            {
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1006:1: (otherlv_0= 'if' otherlv_1= '(' ( (lv_condition_2_0= ruleBooleanContent ) ) otherlv_3= ')' ( (otherlv_4= 'then' ( (otherlv_5= '{' ( (lv_thenCode_6_0= ruleCode ) )* otherlv_7= '}' (otherlv_8= 'else' otherlv_9= '{' ( (lv_elseCode_10_0= ruleCode ) )* otherlv_11= '}' )? ) | (otherlv_12= '[' otherlv_13= '{' ( (lv_thenCode_14_0= ruleCode ) )* otherlv_15= '}' otherlv_16= ',' otherlv_17= '{' ( (lv_elseCode_18_0= ruleCode ) )* otherlv_19= '}' otherlv_20= ']' ) ) ) | (otherlv_21= 'exitWith' otherlv_22= '{' ( (lv_exitCode_23_0= ruleCode ) )* otherlv_24= '}' ) ) )
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1006:3: otherlv_0= 'if' otherlv_1= '(' ( (lv_condition_2_0= ruleBooleanContent ) ) otherlv_3= ')' ( (otherlv_4= 'then' ( (otherlv_5= '{' ( (lv_thenCode_6_0= ruleCode ) )* otherlv_7= '}' (otherlv_8= 'else' otherlv_9= '{' ( (lv_elseCode_10_0= ruleCode ) )* otherlv_11= '}' )? ) | (otherlv_12= '[' otherlv_13= '{' ( (lv_thenCode_14_0= ruleCode ) )* otherlv_15= '}' otherlv_16= ',' otherlv_17= '{' ( (lv_elseCode_18_0= ruleCode ) )* otherlv_19= '}' otherlv_20= ']' ) ) ) | (otherlv_21= 'exitWith' otherlv_22= '{' ( (lv_exitCode_23_0= ruleCode ) )* otherlv_24= '}' ) )
            {
            otherlv_0=(Token)match(input,26,FOLLOW_26_in_ruleifType1960); 

                	newLeafNode(otherlv_0, grammarAccess.getIfTypeAccess().getIfKeyword_0());
                
            otherlv_1=(Token)match(input,20,FOLLOW_20_in_ruleifType1972); 

                	newLeafNode(otherlv_1, grammarAccess.getIfTypeAccess().getLeftParenthesisKeyword_1());
                
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1014:1: ( (lv_condition_2_0= ruleBooleanContent ) )
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1015:1: (lv_condition_2_0= ruleBooleanContent )
            {
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1015:1: (lv_condition_2_0= ruleBooleanContent )
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1016:3: lv_condition_2_0= ruleBooleanContent
            {
             
            	        newCompositeNode(grammarAccess.getIfTypeAccess().getConditionBooleanContentParserRuleCall_2_0()); 
            	    
            pushFollow(FOLLOW_ruleBooleanContent_in_ruleifType1993);
            lv_condition_2_0=ruleBooleanContent();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getIfTypeRule());
            	        }
                   		set(
                   			current, 
                   			"condition",
                    		lv_condition_2_0, 
                    		"BooleanContent");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_3=(Token)match(input,21,FOLLOW_21_in_ruleifType2005); 

                	newLeafNode(otherlv_3, grammarAccess.getIfTypeAccess().getRightParenthesisKeyword_3());
                
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1036:1: ( (otherlv_4= 'then' ( (otherlv_5= '{' ( (lv_thenCode_6_0= ruleCode ) )* otherlv_7= '}' (otherlv_8= 'else' otherlv_9= '{' ( (lv_elseCode_10_0= ruleCode ) )* otherlv_11= '}' )? ) | (otherlv_12= '[' otherlv_13= '{' ( (lv_thenCode_14_0= ruleCode ) )* otherlv_15= '}' otherlv_16= ',' otherlv_17= '{' ( (lv_elseCode_18_0= ruleCode ) )* otherlv_19= '}' otherlv_20= ']' ) ) ) | (otherlv_21= 'exitWith' otherlv_22= '{' ( (lv_exitCode_23_0= ruleCode ) )* otherlv_24= '}' ) )
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( (LA29_0==27) ) {
                alt29=1;
            }
            else if ( (LA29_0==31) ) {
                alt29=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 29, 0, input);

                throw nvae;
            }
            switch (alt29) {
                case 1 :
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1036:2: (otherlv_4= 'then' ( (otherlv_5= '{' ( (lv_thenCode_6_0= ruleCode ) )* otherlv_7= '}' (otherlv_8= 'else' otherlv_9= '{' ( (lv_elseCode_10_0= ruleCode ) )* otherlv_11= '}' )? ) | (otherlv_12= '[' otherlv_13= '{' ( (lv_thenCode_14_0= ruleCode ) )* otherlv_15= '}' otherlv_16= ',' otherlv_17= '{' ( (lv_elseCode_18_0= ruleCode ) )* otherlv_19= '}' otherlv_20= ']' ) ) )
                    {
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1036:2: (otherlv_4= 'then' ( (otherlv_5= '{' ( (lv_thenCode_6_0= ruleCode ) )* otherlv_7= '}' (otherlv_8= 'else' otherlv_9= '{' ( (lv_elseCode_10_0= ruleCode ) )* otherlv_11= '}' )? ) | (otherlv_12= '[' otherlv_13= '{' ( (lv_thenCode_14_0= ruleCode ) )* otherlv_15= '}' otherlv_16= ',' otherlv_17= '{' ( (lv_elseCode_18_0= ruleCode ) )* otherlv_19= '}' otherlv_20= ']' ) ) )
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1036:4: otherlv_4= 'then' ( (otherlv_5= '{' ( (lv_thenCode_6_0= ruleCode ) )* otherlv_7= '}' (otherlv_8= 'else' otherlv_9= '{' ( (lv_elseCode_10_0= ruleCode ) )* otherlv_11= '}' )? ) | (otherlv_12= '[' otherlv_13= '{' ( (lv_thenCode_14_0= ruleCode ) )* otherlv_15= '}' otherlv_16= ',' otherlv_17= '{' ( (lv_elseCode_18_0= ruleCode ) )* otherlv_19= '}' otherlv_20= ']' ) )
                    {
                    otherlv_4=(Token)match(input,27,FOLLOW_27_in_ruleifType2019); 

                        	newLeafNode(otherlv_4, grammarAccess.getIfTypeAccess().getThenKeyword_4_0_0());
                        
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1040:1: ( (otherlv_5= '{' ( (lv_thenCode_6_0= ruleCode ) )* otherlv_7= '}' (otherlv_8= 'else' otherlv_9= '{' ( (lv_elseCode_10_0= ruleCode ) )* otherlv_11= '}' )? ) | (otherlv_12= '[' otherlv_13= '{' ( (lv_thenCode_14_0= ruleCode ) )* otherlv_15= '}' otherlv_16= ',' otherlv_17= '{' ( (lv_elseCode_18_0= ruleCode ) )* otherlv_19= '}' otherlv_20= ']' ) )
                    int alt27=2;
                    int LA27_0 = input.LA(1);

                    if ( (LA27_0==28) ) {
                        alt27=1;
                    }
                    else if ( (LA27_0==23) ) {
                        alt27=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 27, 0, input);

                        throw nvae;
                    }
                    switch (alt27) {
                        case 1 :
                            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1040:2: (otherlv_5= '{' ( (lv_thenCode_6_0= ruleCode ) )* otherlv_7= '}' (otherlv_8= 'else' otherlv_9= '{' ( (lv_elseCode_10_0= ruleCode ) )* otherlv_11= '}' )? )
                            {
                            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1040:2: (otherlv_5= '{' ( (lv_thenCode_6_0= ruleCode ) )* otherlv_7= '}' (otherlv_8= 'else' otherlv_9= '{' ( (lv_elseCode_10_0= ruleCode ) )* otherlv_11= '}' )? )
                            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1040:4: otherlv_5= '{' ( (lv_thenCode_6_0= ruleCode ) )* otherlv_7= '}' (otherlv_8= 'else' otherlv_9= '{' ( (lv_elseCode_10_0= ruleCode ) )* otherlv_11= '}' )?
                            {
                            otherlv_5=(Token)match(input,28,FOLLOW_28_in_ruleifType2033); 

                                	newLeafNode(otherlv_5, grammarAccess.getIfTypeAccess().getLeftCurlyBracketKeyword_4_0_1_0_0());
                                
                            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1044:1: ( (lv_thenCode_6_0= ruleCode ) )*
                            loop22:
                            do {
                                int alt22=2;
                                int LA22_0 = input.LA(1);

                                if ( (LA22_0==RULE_ID||LA22_0==23||LA22_0==26||LA22_0==28||LA22_0==32||LA22_0==34||LA22_0==39) ) {
                                    alt22=1;
                                }


                                switch (alt22) {
                            	case 1 :
                            	    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1045:1: (lv_thenCode_6_0= ruleCode )
                            	    {
                            	    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1045:1: (lv_thenCode_6_0= ruleCode )
                            	    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1046:3: lv_thenCode_6_0= ruleCode
                            	    {
                            	     
                            	    	        newCompositeNode(grammarAccess.getIfTypeAccess().getThenCodeCodeParserRuleCall_4_0_1_0_1_0()); 
                            	    	    
                            	    pushFollow(FOLLOW_ruleCode_in_ruleifType2054);
                            	    lv_thenCode_6_0=ruleCode();

                            	    state._fsp--;


                            	    	        if (current==null) {
                            	    	            current = createModelElementForParent(grammarAccess.getIfTypeRule());
                            	    	        }
                            	           		add(
                            	           			current, 
                            	           			"thenCode",
                            	            		lv_thenCode_6_0, 
                            	            		"Code");
                            	    	        afterParserOrEnumRuleCall();
                            	    	    

                            	    }


                            	    }
                            	    break;

                            	default :
                            	    break loop22;
                                }
                            } while (true);

                            otherlv_7=(Token)match(input,29,FOLLOW_29_in_ruleifType2067); 

                                	newLeafNode(otherlv_7, grammarAccess.getIfTypeAccess().getRightCurlyBracketKeyword_4_0_1_0_2());
                                
                            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1066:1: (otherlv_8= 'else' otherlv_9= '{' ( (lv_elseCode_10_0= ruleCode ) )* otherlv_11= '}' )?
                            int alt24=2;
                            int LA24_0 = input.LA(1);

                            if ( (LA24_0==30) ) {
                                alt24=1;
                            }
                            switch (alt24) {
                                case 1 :
                                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1066:3: otherlv_8= 'else' otherlv_9= '{' ( (lv_elseCode_10_0= ruleCode ) )* otherlv_11= '}'
                                    {
                                    otherlv_8=(Token)match(input,30,FOLLOW_30_in_ruleifType2080); 

                                        	newLeafNode(otherlv_8, grammarAccess.getIfTypeAccess().getElseKeyword_4_0_1_0_3_0());
                                        
                                    otherlv_9=(Token)match(input,28,FOLLOW_28_in_ruleifType2092); 

                                        	newLeafNode(otherlv_9, grammarAccess.getIfTypeAccess().getLeftCurlyBracketKeyword_4_0_1_0_3_1());
                                        
                                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1074:1: ( (lv_elseCode_10_0= ruleCode ) )*
                                    loop23:
                                    do {
                                        int alt23=2;
                                        int LA23_0 = input.LA(1);

                                        if ( (LA23_0==RULE_ID||LA23_0==23||LA23_0==26||LA23_0==28||LA23_0==32||LA23_0==34||LA23_0==39) ) {
                                            alt23=1;
                                        }


                                        switch (alt23) {
                                    	case 1 :
                                    	    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1075:1: (lv_elseCode_10_0= ruleCode )
                                    	    {
                                    	    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1075:1: (lv_elseCode_10_0= ruleCode )
                                    	    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1076:3: lv_elseCode_10_0= ruleCode
                                    	    {
                                    	     
                                    	    	        newCompositeNode(grammarAccess.getIfTypeAccess().getElseCodeCodeParserRuleCall_4_0_1_0_3_2_0()); 
                                    	    	    
                                    	    pushFollow(FOLLOW_ruleCode_in_ruleifType2113);
                                    	    lv_elseCode_10_0=ruleCode();

                                    	    state._fsp--;


                                    	    	        if (current==null) {
                                    	    	            current = createModelElementForParent(grammarAccess.getIfTypeRule());
                                    	    	        }
                                    	           		add(
                                    	           			current, 
                                    	           			"elseCode",
                                    	            		lv_elseCode_10_0, 
                                    	            		"Code");
                                    	    	        afterParserOrEnumRuleCall();
                                    	    	    

                                    	    }


                                    	    }
                                    	    break;

                                    	default :
                                    	    break loop23;
                                        }
                                    } while (true);

                                    otherlv_11=(Token)match(input,29,FOLLOW_29_in_ruleifType2126); 

                                        	newLeafNode(otherlv_11, grammarAccess.getIfTypeAccess().getRightCurlyBracketKeyword_4_0_1_0_3_3());
                                        

                                    }
                                    break;

                            }


                            }


                            }
                            break;
                        case 2 :
                            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1097:6: (otherlv_12= '[' otherlv_13= '{' ( (lv_thenCode_14_0= ruleCode ) )* otherlv_15= '}' otherlv_16= ',' otherlv_17= '{' ( (lv_elseCode_18_0= ruleCode ) )* otherlv_19= '}' otherlv_20= ']' )
                            {
                            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1097:6: (otherlv_12= '[' otherlv_13= '{' ( (lv_thenCode_14_0= ruleCode ) )* otherlv_15= '}' otherlv_16= ',' otherlv_17= '{' ( (lv_elseCode_18_0= ruleCode ) )* otherlv_19= '}' otherlv_20= ']' )
                            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1097:8: otherlv_12= '[' otherlv_13= '{' ( (lv_thenCode_14_0= ruleCode ) )* otherlv_15= '}' otherlv_16= ',' otherlv_17= '{' ( (lv_elseCode_18_0= ruleCode ) )* otherlv_19= '}' otherlv_20= ']'
                            {
                            otherlv_12=(Token)match(input,23,FOLLOW_23_in_ruleifType2148); 

                                	newLeafNode(otherlv_12, grammarAccess.getIfTypeAccess().getLeftSquareBracketKeyword_4_0_1_1_0());
                                
                            otherlv_13=(Token)match(input,28,FOLLOW_28_in_ruleifType2160); 

                                	newLeafNode(otherlv_13, grammarAccess.getIfTypeAccess().getLeftCurlyBracketKeyword_4_0_1_1_1());
                                
                            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1105:1: ( (lv_thenCode_14_0= ruleCode ) )*
                            loop25:
                            do {
                                int alt25=2;
                                int LA25_0 = input.LA(1);

                                if ( (LA25_0==RULE_ID||LA25_0==23||LA25_0==26||LA25_0==28||LA25_0==32||LA25_0==34||LA25_0==39) ) {
                                    alt25=1;
                                }


                                switch (alt25) {
                            	case 1 :
                            	    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1106:1: (lv_thenCode_14_0= ruleCode )
                            	    {
                            	    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1106:1: (lv_thenCode_14_0= ruleCode )
                            	    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1107:3: lv_thenCode_14_0= ruleCode
                            	    {
                            	     
                            	    	        newCompositeNode(grammarAccess.getIfTypeAccess().getThenCodeCodeParserRuleCall_4_0_1_1_2_0()); 
                            	    	    
                            	    pushFollow(FOLLOW_ruleCode_in_ruleifType2181);
                            	    lv_thenCode_14_0=ruleCode();

                            	    state._fsp--;


                            	    	        if (current==null) {
                            	    	            current = createModelElementForParent(grammarAccess.getIfTypeRule());
                            	    	        }
                            	           		add(
                            	           			current, 
                            	           			"thenCode",
                            	            		lv_thenCode_14_0, 
                            	            		"Code");
                            	    	        afterParserOrEnumRuleCall();
                            	    	    

                            	    }


                            	    }
                            	    break;

                            	default :
                            	    break loop25;
                                }
                            } while (true);

                            otherlv_15=(Token)match(input,29,FOLLOW_29_in_ruleifType2194); 

                                	newLeafNode(otherlv_15, grammarAccess.getIfTypeAccess().getRightCurlyBracketKeyword_4_0_1_1_3());
                                
                            otherlv_16=(Token)match(input,24,FOLLOW_24_in_ruleifType2206); 

                                	newLeafNode(otherlv_16, grammarAccess.getIfTypeAccess().getCommaKeyword_4_0_1_1_4());
                                
                            otherlv_17=(Token)match(input,28,FOLLOW_28_in_ruleifType2218); 

                                	newLeafNode(otherlv_17, grammarAccess.getIfTypeAccess().getLeftCurlyBracketKeyword_4_0_1_1_5());
                                
                            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1135:1: ( (lv_elseCode_18_0= ruleCode ) )*
                            loop26:
                            do {
                                int alt26=2;
                                int LA26_0 = input.LA(1);

                                if ( (LA26_0==RULE_ID||LA26_0==23||LA26_0==26||LA26_0==28||LA26_0==32||LA26_0==34||LA26_0==39) ) {
                                    alt26=1;
                                }


                                switch (alt26) {
                            	case 1 :
                            	    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1136:1: (lv_elseCode_18_0= ruleCode )
                            	    {
                            	    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1136:1: (lv_elseCode_18_0= ruleCode )
                            	    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1137:3: lv_elseCode_18_0= ruleCode
                            	    {
                            	     
                            	    	        newCompositeNode(grammarAccess.getIfTypeAccess().getElseCodeCodeParserRuleCall_4_0_1_1_6_0()); 
                            	    	    
                            	    pushFollow(FOLLOW_ruleCode_in_ruleifType2239);
                            	    lv_elseCode_18_0=ruleCode();

                            	    state._fsp--;


                            	    	        if (current==null) {
                            	    	            current = createModelElementForParent(grammarAccess.getIfTypeRule());
                            	    	        }
                            	           		add(
                            	           			current, 
                            	           			"elseCode",
                            	            		lv_elseCode_18_0, 
                            	            		"Code");
                            	    	        afterParserOrEnumRuleCall();
                            	    	    

                            	    }


                            	    }
                            	    break;

                            	default :
                            	    break loop26;
                                }
                            } while (true);

                            otherlv_19=(Token)match(input,29,FOLLOW_29_in_ruleifType2252); 

                                	newLeafNode(otherlv_19, grammarAccess.getIfTypeAccess().getRightCurlyBracketKeyword_4_0_1_1_7());
                                
                            otherlv_20=(Token)match(input,25,FOLLOW_25_in_ruleifType2264); 

                                	newLeafNode(otherlv_20, grammarAccess.getIfTypeAccess().getRightSquareBracketKeyword_4_0_1_1_8());
                                

                            }


                            }
                            break;

                    }


                    }


                    }
                    break;
                case 2 :
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1162:6: (otherlv_21= 'exitWith' otherlv_22= '{' ( (lv_exitCode_23_0= ruleCode ) )* otherlv_24= '}' )
                    {
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1162:6: (otherlv_21= 'exitWith' otherlv_22= '{' ( (lv_exitCode_23_0= ruleCode ) )* otherlv_24= '}' )
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1162:8: otherlv_21= 'exitWith' otherlv_22= '{' ( (lv_exitCode_23_0= ruleCode ) )* otherlv_24= '}'
                    {
                    otherlv_21=(Token)match(input,31,FOLLOW_31_in_ruleifType2286); 

                        	newLeafNode(otherlv_21, grammarAccess.getIfTypeAccess().getExitWithKeyword_4_1_0());
                        
                    otherlv_22=(Token)match(input,28,FOLLOW_28_in_ruleifType2298); 

                        	newLeafNode(otherlv_22, grammarAccess.getIfTypeAccess().getLeftCurlyBracketKeyword_4_1_1());
                        
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1170:1: ( (lv_exitCode_23_0= ruleCode ) )*
                    loop28:
                    do {
                        int alt28=2;
                        int LA28_0 = input.LA(1);

                        if ( (LA28_0==RULE_ID||LA28_0==23||LA28_0==26||LA28_0==28||LA28_0==32||LA28_0==34||LA28_0==39) ) {
                            alt28=1;
                        }


                        switch (alt28) {
                    	case 1 :
                    	    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1171:1: (lv_exitCode_23_0= ruleCode )
                    	    {
                    	    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1171:1: (lv_exitCode_23_0= ruleCode )
                    	    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1172:3: lv_exitCode_23_0= ruleCode
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getIfTypeAccess().getExitCodeCodeParserRuleCall_4_1_2_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleCode_in_ruleifType2319);
                    	    lv_exitCode_23_0=ruleCode();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getIfTypeRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"exitCode",
                    	            		lv_exitCode_23_0, 
                    	            		"Code");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop28;
                        }
                    } while (true);

                    otherlv_24=(Token)match(input,29,FOLLOW_29_in_ruleifType2332); 

                        	newLeafNode(otherlv_24, grammarAccess.getIfTypeAccess().getRightCurlyBracketKeyword_4_1_3());
                        

                    }


                    }
                    break;

            }


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleifType"


    // $ANTLR start "entryRuleWhileType"
    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1200:1: entryRuleWhileType returns [EObject current=null] : iv_ruleWhileType= ruleWhileType EOF ;
    public final EObject entryRuleWhileType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleWhileType = null;


        try {
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1201:2: (iv_ruleWhileType= ruleWhileType EOF )
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1202:2: iv_ruleWhileType= ruleWhileType EOF
            {
             newCompositeNode(grammarAccess.getWhileTypeRule()); 
            pushFollow(FOLLOW_ruleWhileType_in_entryRuleWhileType2370);
            iv_ruleWhileType=ruleWhileType();

            state._fsp--;

             current =iv_ruleWhileType; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleWhileType2380); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleWhileType"


    // $ANTLR start "ruleWhileType"
    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1209:1: ruleWhileType returns [EObject current=null] : (otherlv_0= 'while' otherlv_1= '{' ( (lv_condition_2_0= ruleBooleanContent ) ) otherlv_3= '}' otherlv_4= 'do' otherlv_5= '{' ( (lv_loopCode_6_0= ruleCode ) )* otherlv_7= '}' ) ;
    public final EObject ruleWhileType() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        EObject lv_condition_2_0 = null;

        EObject lv_loopCode_6_0 = null;


         enterRule(); 
            
        try {
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1212:28: ( (otherlv_0= 'while' otherlv_1= '{' ( (lv_condition_2_0= ruleBooleanContent ) ) otherlv_3= '}' otherlv_4= 'do' otherlv_5= '{' ( (lv_loopCode_6_0= ruleCode ) )* otherlv_7= '}' ) )
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1213:1: (otherlv_0= 'while' otherlv_1= '{' ( (lv_condition_2_0= ruleBooleanContent ) ) otherlv_3= '}' otherlv_4= 'do' otherlv_5= '{' ( (lv_loopCode_6_0= ruleCode ) )* otherlv_7= '}' )
            {
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1213:1: (otherlv_0= 'while' otherlv_1= '{' ( (lv_condition_2_0= ruleBooleanContent ) ) otherlv_3= '}' otherlv_4= 'do' otherlv_5= '{' ( (lv_loopCode_6_0= ruleCode ) )* otherlv_7= '}' )
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1213:3: otherlv_0= 'while' otherlv_1= '{' ( (lv_condition_2_0= ruleBooleanContent ) ) otherlv_3= '}' otherlv_4= 'do' otherlv_5= '{' ( (lv_loopCode_6_0= ruleCode ) )* otherlv_7= '}'
            {
            otherlv_0=(Token)match(input,32,FOLLOW_32_in_ruleWhileType2417); 

                	newLeafNode(otherlv_0, grammarAccess.getWhileTypeAccess().getWhileKeyword_0());
                
            otherlv_1=(Token)match(input,28,FOLLOW_28_in_ruleWhileType2429); 

                	newLeafNode(otherlv_1, grammarAccess.getWhileTypeAccess().getLeftCurlyBracketKeyword_1());
                
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1221:1: ( (lv_condition_2_0= ruleBooleanContent ) )
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1222:1: (lv_condition_2_0= ruleBooleanContent )
            {
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1222:1: (lv_condition_2_0= ruleBooleanContent )
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1223:3: lv_condition_2_0= ruleBooleanContent
            {
             
            	        newCompositeNode(grammarAccess.getWhileTypeAccess().getConditionBooleanContentParserRuleCall_2_0()); 
            	    
            pushFollow(FOLLOW_ruleBooleanContent_in_ruleWhileType2450);
            lv_condition_2_0=ruleBooleanContent();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getWhileTypeRule());
            	        }
                   		set(
                   			current, 
                   			"condition",
                    		lv_condition_2_0, 
                    		"BooleanContent");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_3=(Token)match(input,29,FOLLOW_29_in_ruleWhileType2462); 

                	newLeafNode(otherlv_3, grammarAccess.getWhileTypeAccess().getRightCurlyBracketKeyword_3());
                
            otherlv_4=(Token)match(input,33,FOLLOW_33_in_ruleWhileType2474); 

                	newLeafNode(otherlv_4, grammarAccess.getWhileTypeAccess().getDoKeyword_4());
                
            otherlv_5=(Token)match(input,28,FOLLOW_28_in_ruleWhileType2486); 

                	newLeafNode(otherlv_5, grammarAccess.getWhileTypeAccess().getLeftCurlyBracketKeyword_5());
                
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1251:1: ( (lv_loopCode_6_0= ruleCode ) )*
            loop30:
            do {
                int alt30=2;
                int LA30_0 = input.LA(1);

                if ( (LA30_0==RULE_ID||LA30_0==23||LA30_0==26||LA30_0==28||LA30_0==32||LA30_0==34||LA30_0==39) ) {
                    alt30=1;
                }


                switch (alt30) {
            	case 1 :
            	    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1252:1: (lv_loopCode_6_0= ruleCode )
            	    {
            	    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1252:1: (lv_loopCode_6_0= ruleCode )
            	    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1253:3: lv_loopCode_6_0= ruleCode
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getWhileTypeAccess().getLoopCodeCodeParserRuleCall_6_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleCode_in_ruleWhileType2507);
            	    lv_loopCode_6_0=ruleCode();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getWhileTypeRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"loopCode",
            	            		lv_loopCode_6_0, 
            	            		"Code");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    break loop30;
                }
            } while (true);

            otherlv_7=(Token)match(input,29,FOLLOW_29_in_ruleWhileType2520); 

                	newLeafNode(otherlv_7, grammarAccess.getWhileTypeAccess().getRightCurlyBracketKeyword_7());
                

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleWhileType"


    // $ANTLR start "entryRuleForType"
    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1281:1: entryRuleForType returns [EObject current=null] : iv_ruleForType= ruleForType EOF ;
    public final EObject entryRuleForType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleForType = null;


        try {
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1282:2: (iv_ruleForType= ruleForType EOF )
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1283:2: iv_ruleForType= ruleForType EOF
            {
             newCompositeNode(grammarAccess.getForTypeRule()); 
            pushFollow(FOLLOW_ruleForType_in_entryRuleForType2556);
            iv_ruleForType=ruleForType();

            state._fsp--;

             current =iv_ruleForType; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleForType2566); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleForType"


    // $ANTLR start "ruleForType"
    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1290:1: ruleForType returns [EObject current=null] : (otherlv_0= 'for' ( (otherlv_1= '[' otherlv_2= '{' ( (lv_begin_3_0= ruleDeclaration ) ) otherlv_4= '}' otherlv_5= ',' otherlv_6= '{' ( (lv_condition_7_0= ruleBooleanContent ) ) otherlv_8= '}' otherlv_9= ',' otherlv_10= '{' ( (lv_end_11_0= ruleDeclaration ) ) otherlv_12= '}' otherlv_13= ']' ) | (this_forVarDeclaration_14= ruleforVarDeclaration otherlv_15= 'from' ( (lv_from_16_0= RULE_NUMBER ) ) otherlv_17= 'to' ( (lv_to_18_0= RULE_NUMBER ) ) (otherlv_19= 'step' ( (lv_step_20_0= RULE_NUMBER ) ) )? ) ) otherlv_21= 'do' otherlv_22= '{' ( (lv_loopCode_23_0= ruleCode ) )* otherlv_24= '}' ) ;
    public final EObject ruleForType() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        Token otherlv_9=null;
        Token otherlv_10=null;
        Token otherlv_12=null;
        Token otherlv_13=null;
        Token otherlv_15=null;
        Token lv_from_16_0=null;
        Token otherlv_17=null;
        Token lv_to_18_0=null;
        Token otherlv_19=null;
        Token lv_step_20_0=null;
        Token otherlv_21=null;
        Token otherlv_22=null;
        Token otherlv_24=null;
        EObject lv_begin_3_0 = null;

        EObject lv_condition_7_0 = null;

        EObject lv_end_11_0 = null;

        EObject this_forVarDeclaration_14 = null;

        EObject lv_loopCode_23_0 = null;


         enterRule(); 
            
        try {
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1293:28: ( (otherlv_0= 'for' ( (otherlv_1= '[' otherlv_2= '{' ( (lv_begin_3_0= ruleDeclaration ) ) otherlv_4= '}' otherlv_5= ',' otherlv_6= '{' ( (lv_condition_7_0= ruleBooleanContent ) ) otherlv_8= '}' otherlv_9= ',' otherlv_10= '{' ( (lv_end_11_0= ruleDeclaration ) ) otherlv_12= '}' otherlv_13= ']' ) | (this_forVarDeclaration_14= ruleforVarDeclaration otherlv_15= 'from' ( (lv_from_16_0= RULE_NUMBER ) ) otherlv_17= 'to' ( (lv_to_18_0= RULE_NUMBER ) ) (otherlv_19= 'step' ( (lv_step_20_0= RULE_NUMBER ) ) )? ) ) otherlv_21= 'do' otherlv_22= '{' ( (lv_loopCode_23_0= ruleCode ) )* otherlv_24= '}' ) )
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1294:1: (otherlv_0= 'for' ( (otherlv_1= '[' otherlv_2= '{' ( (lv_begin_3_0= ruleDeclaration ) ) otherlv_4= '}' otherlv_5= ',' otherlv_6= '{' ( (lv_condition_7_0= ruleBooleanContent ) ) otherlv_8= '}' otherlv_9= ',' otherlv_10= '{' ( (lv_end_11_0= ruleDeclaration ) ) otherlv_12= '}' otherlv_13= ']' ) | (this_forVarDeclaration_14= ruleforVarDeclaration otherlv_15= 'from' ( (lv_from_16_0= RULE_NUMBER ) ) otherlv_17= 'to' ( (lv_to_18_0= RULE_NUMBER ) ) (otherlv_19= 'step' ( (lv_step_20_0= RULE_NUMBER ) ) )? ) ) otherlv_21= 'do' otherlv_22= '{' ( (lv_loopCode_23_0= ruleCode ) )* otherlv_24= '}' )
            {
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1294:1: (otherlv_0= 'for' ( (otherlv_1= '[' otherlv_2= '{' ( (lv_begin_3_0= ruleDeclaration ) ) otherlv_4= '}' otherlv_5= ',' otherlv_6= '{' ( (lv_condition_7_0= ruleBooleanContent ) ) otherlv_8= '}' otherlv_9= ',' otherlv_10= '{' ( (lv_end_11_0= ruleDeclaration ) ) otherlv_12= '}' otherlv_13= ']' ) | (this_forVarDeclaration_14= ruleforVarDeclaration otherlv_15= 'from' ( (lv_from_16_0= RULE_NUMBER ) ) otherlv_17= 'to' ( (lv_to_18_0= RULE_NUMBER ) ) (otherlv_19= 'step' ( (lv_step_20_0= RULE_NUMBER ) ) )? ) ) otherlv_21= 'do' otherlv_22= '{' ( (lv_loopCode_23_0= ruleCode ) )* otherlv_24= '}' )
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1294:3: otherlv_0= 'for' ( (otherlv_1= '[' otherlv_2= '{' ( (lv_begin_3_0= ruleDeclaration ) ) otherlv_4= '}' otherlv_5= ',' otherlv_6= '{' ( (lv_condition_7_0= ruleBooleanContent ) ) otherlv_8= '}' otherlv_9= ',' otherlv_10= '{' ( (lv_end_11_0= ruleDeclaration ) ) otherlv_12= '}' otherlv_13= ']' ) | (this_forVarDeclaration_14= ruleforVarDeclaration otherlv_15= 'from' ( (lv_from_16_0= RULE_NUMBER ) ) otherlv_17= 'to' ( (lv_to_18_0= RULE_NUMBER ) ) (otherlv_19= 'step' ( (lv_step_20_0= RULE_NUMBER ) ) )? ) ) otherlv_21= 'do' otherlv_22= '{' ( (lv_loopCode_23_0= ruleCode ) )* otherlv_24= '}'
            {
            otherlv_0=(Token)match(input,34,FOLLOW_34_in_ruleForType2603); 

                	newLeafNode(otherlv_0, grammarAccess.getForTypeAccess().getForKeyword_0());
                
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1298:1: ( (otherlv_1= '[' otherlv_2= '{' ( (lv_begin_3_0= ruleDeclaration ) ) otherlv_4= '}' otherlv_5= ',' otherlv_6= '{' ( (lv_condition_7_0= ruleBooleanContent ) ) otherlv_8= '}' otherlv_9= ',' otherlv_10= '{' ( (lv_end_11_0= ruleDeclaration ) ) otherlv_12= '}' otherlv_13= ']' ) | (this_forVarDeclaration_14= ruleforVarDeclaration otherlv_15= 'from' ( (lv_from_16_0= RULE_NUMBER ) ) otherlv_17= 'to' ( (lv_to_18_0= RULE_NUMBER ) ) (otherlv_19= 'step' ( (lv_step_20_0= RULE_NUMBER ) ) )? ) )
            int alt32=2;
            int LA32_0 = input.LA(1);

            if ( (LA32_0==23) ) {
                alt32=1;
            }
            else if ( (LA32_0==RULE_STRING) ) {
                alt32=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 32, 0, input);

                throw nvae;
            }
            switch (alt32) {
                case 1 :
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1298:2: (otherlv_1= '[' otherlv_2= '{' ( (lv_begin_3_0= ruleDeclaration ) ) otherlv_4= '}' otherlv_5= ',' otherlv_6= '{' ( (lv_condition_7_0= ruleBooleanContent ) ) otherlv_8= '}' otherlv_9= ',' otherlv_10= '{' ( (lv_end_11_0= ruleDeclaration ) ) otherlv_12= '}' otherlv_13= ']' )
                    {
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1298:2: (otherlv_1= '[' otherlv_2= '{' ( (lv_begin_3_0= ruleDeclaration ) ) otherlv_4= '}' otherlv_5= ',' otherlv_6= '{' ( (lv_condition_7_0= ruleBooleanContent ) ) otherlv_8= '}' otherlv_9= ',' otherlv_10= '{' ( (lv_end_11_0= ruleDeclaration ) ) otherlv_12= '}' otherlv_13= ']' )
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1298:4: otherlv_1= '[' otherlv_2= '{' ( (lv_begin_3_0= ruleDeclaration ) ) otherlv_4= '}' otherlv_5= ',' otherlv_6= '{' ( (lv_condition_7_0= ruleBooleanContent ) ) otherlv_8= '}' otherlv_9= ',' otherlv_10= '{' ( (lv_end_11_0= ruleDeclaration ) ) otherlv_12= '}' otherlv_13= ']'
                    {
                    otherlv_1=(Token)match(input,23,FOLLOW_23_in_ruleForType2617); 

                        	newLeafNode(otherlv_1, grammarAccess.getForTypeAccess().getLeftSquareBracketKeyword_1_0_0());
                        
                    otherlv_2=(Token)match(input,28,FOLLOW_28_in_ruleForType2629); 

                        	newLeafNode(otherlv_2, grammarAccess.getForTypeAccess().getLeftCurlyBracketKeyword_1_0_1());
                        
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1306:1: ( (lv_begin_3_0= ruleDeclaration ) )
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1307:1: (lv_begin_3_0= ruleDeclaration )
                    {
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1307:1: (lv_begin_3_0= ruleDeclaration )
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1308:3: lv_begin_3_0= ruleDeclaration
                    {
                     
                    	        newCompositeNode(grammarAccess.getForTypeAccess().getBeginDeclarationParserRuleCall_1_0_2_0()); 
                    	    
                    pushFollow(FOLLOW_ruleDeclaration_in_ruleForType2650);
                    lv_begin_3_0=ruleDeclaration();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getForTypeRule());
                    	        }
                           		set(
                           			current, 
                           			"begin",
                            		lv_begin_3_0, 
                            		"Declaration");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    otherlv_4=(Token)match(input,29,FOLLOW_29_in_ruleForType2662); 

                        	newLeafNode(otherlv_4, grammarAccess.getForTypeAccess().getRightCurlyBracketKeyword_1_0_3());
                        
                    otherlv_5=(Token)match(input,24,FOLLOW_24_in_ruleForType2674); 

                        	newLeafNode(otherlv_5, grammarAccess.getForTypeAccess().getCommaKeyword_1_0_4());
                        
                    otherlv_6=(Token)match(input,28,FOLLOW_28_in_ruleForType2686); 

                        	newLeafNode(otherlv_6, grammarAccess.getForTypeAccess().getLeftCurlyBracketKeyword_1_0_5());
                        
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1336:1: ( (lv_condition_7_0= ruleBooleanContent ) )
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1337:1: (lv_condition_7_0= ruleBooleanContent )
                    {
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1337:1: (lv_condition_7_0= ruleBooleanContent )
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1338:3: lv_condition_7_0= ruleBooleanContent
                    {
                     
                    	        newCompositeNode(grammarAccess.getForTypeAccess().getConditionBooleanContentParserRuleCall_1_0_6_0()); 
                    	    
                    pushFollow(FOLLOW_ruleBooleanContent_in_ruleForType2707);
                    lv_condition_7_0=ruleBooleanContent();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getForTypeRule());
                    	        }
                           		set(
                           			current, 
                           			"condition",
                            		lv_condition_7_0, 
                            		"BooleanContent");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    otherlv_8=(Token)match(input,29,FOLLOW_29_in_ruleForType2719); 

                        	newLeafNode(otherlv_8, grammarAccess.getForTypeAccess().getRightCurlyBracketKeyword_1_0_7());
                        
                    otherlv_9=(Token)match(input,24,FOLLOW_24_in_ruleForType2731); 

                        	newLeafNode(otherlv_9, grammarAccess.getForTypeAccess().getCommaKeyword_1_0_8());
                        
                    otherlv_10=(Token)match(input,28,FOLLOW_28_in_ruleForType2743); 

                        	newLeafNode(otherlv_10, grammarAccess.getForTypeAccess().getLeftCurlyBracketKeyword_1_0_9());
                        
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1366:1: ( (lv_end_11_0= ruleDeclaration ) )
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1367:1: (lv_end_11_0= ruleDeclaration )
                    {
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1367:1: (lv_end_11_0= ruleDeclaration )
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1368:3: lv_end_11_0= ruleDeclaration
                    {
                     
                    	        newCompositeNode(grammarAccess.getForTypeAccess().getEndDeclarationParserRuleCall_1_0_10_0()); 
                    	    
                    pushFollow(FOLLOW_ruleDeclaration_in_ruleForType2764);
                    lv_end_11_0=ruleDeclaration();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getForTypeRule());
                    	        }
                           		set(
                           			current, 
                           			"end",
                            		lv_end_11_0, 
                            		"Declaration");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    otherlv_12=(Token)match(input,29,FOLLOW_29_in_ruleForType2776); 

                        	newLeafNode(otherlv_12, grammarAccess.getForTypeAccess().getRightCurlyBracketKeyword_1_0_11());
                        
                    otherlv_13=(Token)match(input,25,FOLLOW_25_in_ruleForType2788); 

                        	newLeafNode(otherlv_13, grammarAccess.getForTypeAccess().getRightSquareBracketKeyword_1_0_12());
                        

                    }


                    }
                    break;
                case 2 :
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1393:6: (this_forVarDeclaration_14= ruleforVarDeclaration otherlv_15= 'from' ( (lv_from_16_0= RULE_NUMBER ) ) otherlv_17= 'to' ( (lv_to_18_0= RULE_NUMBER ) ) (otherlv_19= 'step' ( (lv_step_20_0= RULE_NUMBER ) ) )? )
                    {
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1393:6: (this_forVarDeclaration_14= ruleforVarDeclaration otherlv_15= 'from' ( (lv_from_16_0= RULE_NUMBER ) ) otherlv_17= 'to' ( (lv_to_18_0= RULE_NUMBER ) ) (otherlv_19= 'step' ( (lv_step_20_0= RULE_NUMBER ) ) )? )
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1394:5: this_forVarDeclaration_14= ruleforVarDeclaration otherlv_15= 'from' ( (lv_from_16_0= RULE_NUMBER ) ) otherlv_17= 'to' ( (lv_to_18_0= RULE_NUMBER ) ) (otherlv_19= 'step' ( (lv_step_20_0= RULE_NUMBER ) ) )?
                    {
                     
                            newCompositeNode(grammarAccess.getForTypeAccess().getForVarDeclarationParserRuleCall_1_1_0()); 
                        
                    pushFollow(FOLLOW_ruleforVarDeclaration_in_ruleForType2818);
                    this_forVarDeclaration_14=ruleforVarDeclaration();

                    state._fsp--;

                     
                            current = this_forVarDeclaration_14; 
                            afterParserOrEnumRuleCall();
                        
                    otherlv_15=(Token)match(input,35,FOLLOW_35_in_ruleForType2829); 

                        	newLeafNode(otherlv_15, grammarAccess.getForTypeAccess().getFromKeyword_1_1_1());
                        
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1406:1: ( (lv_from_16_0= RULE_NUMBER ) )
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1407:1: (lv_from_16_0= RULE_NUMBER )
                    {
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1407:1: (lv_from_16_0= RULE_NUMBER )
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1408:3: lv_from_16_0= RULE_NUMBER
                    {
                    lv_from_16_0=(Token)match(input,RULE_NUMBER,FOLLOW_RULE_NUMBER_in_ruleForType2846); 

                    			newLeafNode(lv_from_16_0, grammarAccess.getForTypeAccess().getFromNUMBERTerminalRuleCall_1_1_2_0()); 
                    		

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getForTypeRule());
                    	        }
                           		setWithLastConsumed(
                           			current, 
                           			"from",
                            		lv_from_16_0, 
                            		"NUMBER");
                    	    

                    }


                    }

                    otherlv_17=(Token)match(input,36,FOLLOW_36_in_ruleForType2863); 

                        	newLeafNode(otherlv_17, grammarAccess.getForTypeAccess().getToKeyword_1_1_3());
                        
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1428:1: ( (lv_to_18_0= RULE_NUMBER ) )
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1429:1: (lv_to_18_0= RULE_NUMBER )
                    {
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1429:1: (lv_to_18_0= RULE_NUMBER )
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1430:3: lv_to_18_0= RULE_NUMBER
                    {
                    lv_to_18_0=(Token)match(input,RULE_NUMBER,FOLLOW_RULE_NUMBER_in_ruleForType2880); 

                    			newLeafNode(lv_to_18_0, grammarAccess.getForTypeAccess().getToNUMBERTerminalRuleCall_1_1_4_0()); 
                    		

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getForTypeRule());
                    	        }
                           		setWithLastConsumed(
                           			current, 
                           			"to",
                            		lv_to_18_0, 
                            		"NUMBER");
                    	    

                    }


                    }

                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1446:2: (otherlv_19= 'step' ( (lv_step_20_0= RULE_NUMBER ) ) )?
                    int alt31=2;
                    int LA31_0 = input.LA(1);

                    if ( (LA31_0==37) ) {
                        alt31=1;
                    }
                    switch (alt31) {
                        case 1 :
                            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1446:4: otherlv_19= 'step' ( (lv_step_20_0= RULE_NUMBER ) )
                            {
                            otherlv_19=(Token)match(input,37,FOLLOW_37_in_ruleForType2898); 

                                	newLeafNode(otherlv_19, grammarAccess.getForTypeAccess().getStepKeyword_1_1_5_0());
                                
                            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1450:1: ( (lv_step_20_0= RULE_NUMBER ) )
                            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1451:1: (lv_step_20_0= RULE_NUMBER )
                            {
                            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1451:1: (lv_step_20_0= RULE_NUMBER )
                            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1452:3: lv_step_20_0= RULE_NUMBER
                            {
                            lv_step_20_0=(Token)match(input,RULE_NUMBER,FOLLOW_RULE_NUMBER_in_ruleForType2915); 

                            			newLeafNode(lv_step_20_0, grammarAccess.getForTypeAccess().getStepNUMBERTerminalRuleCall_1_1_5_1_0()); 
                            		

                            	        if (current==null) {
                            	            current = createModelElement(grammarAccess.getForTypeRule());
                            	        }
                                   		setWithLastConsumed(
                                   			current, 
                                   			"step",
                                    		lv_step_20_0, 
                                    		"NUMBER");
                            	    

                            }


                            }


                            }
                            break;

                    }


                    }


                    }
                    break;

            }

            otherlv_21=(Token)match(input,33,FOLLOW_33_in_ruleForType2936); 

                	newLeafNode(otherlv_21, grammarAccess.getForTypeAccess().getDoKeyword_2());
                
            otherlv_22=(Token)match(input,28,FOLLOW_28_in_ruleForType2948); 

                	newLeafNode(otherlv_22, grammarAccess.getForTypeAccess().getLeftCurlyBracketKeyword_3());
                
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1476:1: ( (lv_loopCode_23_0= ruleCode ) )*
            loop33:
            do {
                int alt33=2;
                int LA33_0 = input.LA(1);

                if ( (LA33_0==RULE_ID||LA33_0==23||LA33_0==26||LA33_0==28||LA33_0==32||LA33_0==34||LA33_0==39) ) {
                    alt33=1;
                }


                switch (alt33) {
            	case 1 :
            	    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1477:1: (lv_loopCode_23_0= ruleCode )
            	    {
            	    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1477:1: (lv_loopCode_23_0= ruleCode )
            	    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1478:3: lv_loopCode_23_0= ruleCode
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getForTypeAccess().getLoopCodeCodeParserRuleCall_4_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleCode_in_ruleForType2969);
            	    lv_loopCode_23_0=ruleCode();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getForTypeRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"loopCode",
            	            		lv_loopCode_23_0, 
            	            		"Code");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    break loop33;
                }
            } while (true);

            otherlv_24=(Token)match(input,29,FOLLOW_29_in_ruleForType2982); 

                	newLeafNode(otherlv_24, grammarAccess.getForTypeAccess().getRightCurlyBracketKeyword_5());
                

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleForType"


    // $ANTLR start "entryRuleforVarDeclaration"
    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1506:1: entryRuleforVarDeclaration returns [EObject current=null] : iv_ruleforVarDeclaration= ruleforVarDeclaration EOF ;
    public final EObject entryRuleforVarDeclaration() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleforVarDeclaration = null;


        try {
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1507:2: (iv_ruleforVarDeclaration= ruleforVarDeclaration EOF )
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1508:2: iv_ruleforVarDeclaration= ruleforVarDeclaration EOF
            {
             newCompositeNode(grammarAccess.getForVarDeclarationRule()); 
            pushFollow(FOLLOW_ruleforVarDeclaration_in_entryRuleforVarDeclaration3018);
            iv_ruleforVarDeclaration=ruleforVarDeclaration();

            state._fsp--;

             current =iv_ruleforVarDeclaration; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleforVarDeclaration3028); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleforVarDeclaration"


    // $ANTLR start "ruleforVarDeclaration"
    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1515:1: ruleforVarDeclaration returns [EObject current=null] : ( (lv_name_0_0= RULE_STRING ) ) ;
    public final EObject ruleforVarDeclaration() throws RecognitionException {
        EObject current = null;

        Token lv_name_0_0=null;

         enterRule(); 
            
        try {
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1518:28: ( ( (lv_name_0_0= RULE_STRING ) ) )
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1519:1: ( (lv_name_0_0= RULE_STRING ) )
            {
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1519:1: ( (lv_name_0_0= RULE_STRING ) )
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1520:1: (lv_name_0_0= RULE_STRING )
            {
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1520:1: (lv_name_0_0= RULE_STRING )
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1521:3: lv_name_0_0= RULE_STRING
            {
            lv_name_0_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleforVarDeclaration3069); 

            			newLeafNode(lv_name_0_0, grammarAccess.getForVarDeclarationAccess().getNameSTRINGTerminalRuleCall_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getForVarDeclarationRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"name",
                    		lv_name_0_0, 
                    		"STRING");
            	    

            }


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleforVarDeclaration"


    // $ANTLR start "entryRuleForeachType"
    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1545:1: entryRuleForeachType returns [EObject current=null] : iv_ruleForeachType= ruleForeachType EOF ;
    public final EObject entryRuleForeachType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleForeachType = null;


        try {
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1546:2: (iv_ruleForeachType= ruleForeachType EOF )
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1547:2: iv_ruleForeachType= ruleForeachType EOF
            {
             newCompositeNode(grammarAccess.getForeachTypeRule()); 
            pushFollow(FOLLOW_ruleForeachType_in_entryRuleForeachType3109);
            iv_ruleForeachType=ruleForeachType();

            state._fsp--;

             current =iv_ruleForeachType; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleForeachType3119); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleForeachType"


    // $ANTLR start "ruleForeachType"
    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1554:1: ruleForeachType returns [EObject current=null] : (otherlv_0= '{' ( (lv_code_1_0= ruleCode ) )* otherlv_2= '}' otherlv_3= 'forEach' ( ( (otherlv_4= RULE_ID ) ) | ( (lv_arrayLiteral_5_0= ruleArrayLiteral ) ) ) ) ;
    public final EObject ruleForeachType() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        EObject lv_code_1_0 = null;

        EObject lv_arrayLiteral_5_0 = null;


         enterRule(); 
            
        try {
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1557:28: ( (otherlv_0= '{' ( (lv_code_1_0= ruleCode ) )* otherlv_2= '}' otherlv_3= 'forEach' ( ( (otherlv_4= RULE_ID ) ) | ( (lv_arrayLiteral_5_0= ruleArrayLiteral ) ) ) ) )
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1558:1: (otherlv_0= '{' ( (lv_code_1_0= ruleCode ) )* otherlv_2= '}' otherlv_3= 'forEach' ( ( (otherlv_4= RULE_ID ) ) | ( (lv_arrayLiteral_5_0= ruleArrayLiteral ) ) ) )
            {
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1558:1: (otherlv_0= '{' ( (lv_code_1_0= ruleCode ) )* otherlv_2= '}' otherlv_3= 'forEach' ( ( (otherlv_4= RULE_ID ) ) | ( (lv_arrayLiteral_5_0= ruleArrayLiteral ) ) ) )
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1558:3: otherlv_0= '{' ( (lv_code_1_0= ruleCode ) )* otherlv_2= '}' otherlv_3= 'forEach' ( ( (otherlv_4= RULE_ID ) ) | ( (lv_arrayLiteral_5_0= ruleArrayLiteral ) ) )
            {
            otherlv_0=(Token)match(input,28,FOLLOW_28_in_ruleForeachType3156); 

                	newLeafNode(otherlv_0, grammarAccess.getForeachTypeAccess().getLeftCurlyBracketKeyword_0());
                
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1562:1: ( (lv_code_1_0= ruleCode ) )*
            loop34:
            do {
                int alt34=2;
                int LA34_0 = input.LA(1);

                if ( (LA34_0==RULE_ID||LA34_0==23||LA34_0==26||LA34_0==28||LA34_0==32||LA34_0==34||LA34_0==39) ) {
                    alt34=1;
                }


                switch (alt34) {
            	case 1 :
            	    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1563:1: (lv_code_1_0= ruleCode )
            	    {
            	    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1563:1: (lv_code_1_0= ruleCode )
            	    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1564:3: lv_code_1_0= ruleCode
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getForeachTypeAccess().getCodeCodeParserRuleCall_1_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleCode_in_ruleForeachType3177);
            	    lv_code_1_0=ruleCode();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getForeachTypeRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"code",
            	            		lv_code_1_0, 
            	            		"Code");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    break loop34;
                }
            } while (true);

            otherlv_2=(Token)match(input,29,FOLLOW_29_in_ruleForeachType3190); 

                	newLeafNode(otherlv_2, grammarAccess.getForeachTypeAccess().getRightCurlyBracketKeyword_2());
                
            otherlv_3=(Token)match(input,38,FOLLOW_38_in_ruleForeachType3202); 

                	newLeafNode(otherlv_3, grammarAccess.getForeachTypeAccess().getForEachKeyword_3());
                
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1588:1: ( ( (otherlv_4= RULE_ID ) ) | ( (lv_arrayLiteral_5_0= ruleArrayLiteral ) ) )
            int alt35=2;
            int LA35_0 = input.LA(1);

            if ( (LA35_0==RULE_ID) ) {
                alt35=1;
            }
            else if ( (LA35_0==23) ) {
                alt35=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 35, 0, input);

                throw nvae;
            }
            switch (alt35) {
                case 1 :
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1588:2: ( (otherlv_4= RULE_ID ) )
                    {
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1588:2: ( (otherlv_4= RULE_ID ) )
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1589:1: (otherlv_4= RULE_ID )
                    {
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1589:1: (otherlv_4= RULE_ID )
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1590:3: otherlv_4= RULE_ID
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getForeachTypeRule());
                    	        }
                            
                    otherlv_4=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleForeachType3223); 

                    		newLeafNode(otherlv_4, grammarAccess.getForeachTypeAccess().getArrayDeclarationCrossReference_4_0_0()); 
                    	

                    }


                    }


                    }
                    break;
                case 2 :
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1602:6: ( (lv_arrayLiteral_5_0= ruleArrayLiteral ) )
                    {
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1602:6: ( (lv_arrayLiteral_5_0= ruleArrayLiteral ) )
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1603:1: (lv_arrayLiteral_5_0= ruleArrayLiteral )
                    {
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1603:1: (lv_arrayLiteral_5_0= ruleArrayLiteral )
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1604:3: lv_arrayLiteral_5_0= ruleArrayLiteral
                    {
                     
                    	        newCompositeNode(grammarAccess.getForeachTypeAccess().getArrayLiteralArrayLiteralParserRuleCall_4_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleArrayLiteral_in_ruleForeachType3250);
                    lv_arrayLiteral_5_0=ruleArrayLiteral();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getForeachTypeRule());
                    	        }
                           		set(
                           			current, 
                           			"arrayLiteral",
                            		lv_arrayLiteral_5_0, 
                            		"ArrayLiteral");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleForeachType"


    // $ANTLR start "entryRuleSwitchType"
    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1628:1: entryRuleSwitchType returns [EObject current=null] : iv_ruleSwitchType= ruleSwitchType EOF ;
    public final EObject entryRuleSwitchType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSwitchType = null;


        try {
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1629:2: (iv_ruleSwitchType= ruleSwitchType EOF )
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1630:2: iv_ruleSwitchType= ruleSwitchType EOF
            {
             newCompositeNode(grammarAccess.getSwitchTypeRule()); 
            pushFollow(FOLLOW_ruleSwitchType_in_entryRuleSwitchType3287);
            iv_ruleSwitchType=ruleSwitchType();

            state._fsp--;

             current =iv_ruleSwitchType; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleSwitchType3297); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleSwitchType"


    // $ANTLR start "ruleSwitchType"
    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1637:1: ruleSwitchType returns [EObject current=null] : (otherlv_0= 'switch' otherlv_1= '(' ( (otherlv_2= RULE_ID ) ) otherlv_3= ')' otherlv_4= 'do' otherlv_5= '{' (otherlv_6= 'case' ( (lv_value_7_0= ruleANYTHING ) ) otherlv_8= ':' otherlv_9= '{' ( (lv_caseCode_10_0= ruleCode ) )* otherlv_11= '}' otherlv_12= ';' )* (otherlv_13= 'default' otherlv_14= '{' ( (lv_defaultCode_15_0= ruleCode ) )* otherlv_16= '}' otherlv_17= ';' )? otherlv_18= '}' ) ;
    public final EObject ruleSwitchType() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        Token otherlv_9=null;
        Token otherlv_11=null;
        Token otherlv_12=null;
        Token otherlv_13=null;
        Token otherlv_14=null;
        Token otherlv_16=null;
        Token otherlv_17=null;
        Token otherlv_18=null;
        EObject lv_value_7_0 = null;

        EObject lv_caseCode_10_0 = null;

        EObject lv_defaultCode_15_0 = null;


         enterRule(); 
            
        try {
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1640:28: ( (otherlv_0= 'switch' otherlv_1= '(' ( (otherlv_2= RULE_ID ) ) otherlv_3= ')' otherlv_4= 'do' otherlv_5= '{' (otherlv_6= 'case' ( (lv_value_7_0= ruleANYTHING ) ) otherlv_8= ':' otherlv_9= '{' ( (lv_caseCode_10_0= ruleCode ) )* otherlv_11= '}' otherlv_12= ';' )* (otherlv_13= 'default' otherlv_14= '{' ( (lv_defaultCode_15_0= ruleCode ) )* otherlv_16= '}' otherlv_17= ';' )? otherlv_18= '}' ) )
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1641:1: (otherlv_0= 'switch' otherlv_1= '(' ( (otherlv_2= RULE_ID ) ) otherlv_3= ')' otherlv_4= 'do' otherlv_5= '{' (otherlv_6= 'case' ( (lv_value_7_0= ruleANYTHING ) ) otherlv_8= ':' otherlv_9= '{' ( (lv_caseCode_10_0= ruleCode ) )* otherlv_11= '}' otherlv_12= ';' )* (otherlv_13= 'default' otherlv_14= '{' ( (lv_defaultCode_15_0= ruleCode ) )* otherlv_16= '}' otherlv_17= ';' )? otherlv_18= '}' )
            {
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1641:1: (otherlv_0= 'switch' otherlv_1= '(' ( (otherlv_2= RULE_ID ) ) otherlv_3= ')' otherlv_4= 'do' otherlv_5= '{' (otherlv_6= 'case' ( (lv_value_7_0= ruleANYTHING ) ) otherlv_8= ':' otherlv_9= '{' ( (lv_caseCode_10_0= ruleCode ) )* otherlv_11= '}' otherlv_12= ';' )* (otherlv_13= 'default' otherlv_14= '{' ( (lv_defaultCode_15_0= ruleCode ) )* otherlv_16= '}' otherlv_17= ';' )? otherlv_18= '}' )
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1641:3: otherlv_0= 'switch' otherlv_1= '(' ( (otherlv_2= RULE_ID ) ) otherlv_3= ')' otherlv_4= 'do' otherlv_5= '{' (otherlv_6= 'case' ( (lv_value_7_0= ruleANYTHING ) ) otherlv_8= ':' otherlv_9= '{' ( (lv_caseCode_10_0= ruleCode ) )* otherlv_11= '}' otherlv_12= ';' )* (otherlv_13= 'default' otherlv_14= '{' ( (lv_defaultCode_15_0= ruleCode ) )* otherlv_16= '}' otherlv_17= ';' )? otherlv_18= '}'
            {
            otherlv_0=(Token)match(input,39,FOLLOW_39_in_ruleSwitchType3334); 

                	newLeafNode(otherlv_0, grammarAccess.getSwitchTypeAccess().getSwitchKeyword_0());
                
            otherlv_1=(Token)match(input,20,FOLLOW_20_in_ruleSwitchType3346); 

                	newLeafNode(otherlv_1, grammarAccess.getSwitchTypeAccess().getLeftParenthesisKeyword_1());
                
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1649:1: ( (otherlv_2= RULE_ID ) )
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1650:1: (otherlv_2= RULE_ID )
            {
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1650:1: (otherlv_2= RULE_ID )
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1651:3: otherlv_2= RULE_ID
            {

            			if (current==null) {
            	            current = createModelElement(grammarAccess.getSwitchTypeRule());
            	        }
                    
            otherlv_2=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleSwitchType3366); 

            		newLeafNode(otherlv_2, grammarAccess.getSwitchTypeAccess().getVarDeclarationCrossReference_2_0()); 
            	

            }


            }

            otherlv_3=(Token)match(input,21,FOLLOW_21_in_ruleSwitchType3378); 

                	newLeafNode(otherlv_3, grammarAccess.getSwitchTypeAccess().getRightParenthesisKeyword_3());
                
            otherlv_4=(Token)match(input,33,FOLLOW_33_in_ruleSwitchType3390); 

                	newLeafNode(otherlv_4, grammarAccess.getSwitchTypeAccess().getDoKeyword_4());
                
            otherlv_5=(Token)match(input,28,FOLLOW_28_in_ruleSwitchType3402); 

                	newLeafNode(otherlv_5, grammarAccess.getSwitchTypeAccess().getLeftCurlyBracketKeyword_5());
                
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1674:1: (otherlv_6= 'case' ( (lv_value_7_0= ruleANYTHING ) ) otherlv_8= ':' otherlv_9= '{' ( (lv_caseCode_10_0= ruleCode ) )* otherlv_11= '}' otherlv_12= ';' )*
            loop37:
            do {
                int alt37=2;
                int LA37_0 = input.LA(1);

                if ( (LA37_0==40) ) {
                    alt37=1;
                }


                switch (alt37) {
            	case 1 :
            	    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1674:3: otherlv_6= 'case' ( (lv_value_7_0= ruleANYTHING ) ) otherlv_8= ':' otherlv_9= '{' ( (lv_caseCode_10_0= ruleCode ) )* otherlv_11= '}' otherlv_12= ';'
            	    {
            	    otherlv_6=(Token)match(input,40,FOLLOW_40_in_ruleSwitchType3415); 

            	        	newLeafNode(otherlv_6, grammarAccess.getSwitchTypeAccess().getCaseKeyword_6_0());
            	        
            	    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1678:1: ( (lv_value_7_0= ruleANYTHING ) )
            	    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1679:1: (lv_value_7_0= ruleANYTHING )
            	    {
            	    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1679:1: (lv_value_7_0= ruleANYTHING )
            	    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1680:3: lv_value_7_0= ruleANYTHING
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getSwitchTypeAccess().getValueANYTHINGParserRuleCall_6_1_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleANYTHING_in_ruleSwitchType3436);
            	    lv_value_7_0=ruleANYTHING();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getSwitchTypeRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"value",
            	            		lv_value_7_0, 
            	            		"ANYTHING");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }

            	    otherlv_8=(Token)match(input,41,FOLLOW_41_in_ruleSwitchType3448); 

            	        	newLeafNode(otherlv_8, grammarAccess.getSwitchTypeAccess().getColonKeyword_6_2());
            	        
            	    otherlv_9=(Token)match(input,28,FOLLOW_28_in_ruleSwitchType3460); 

            	        	newLeafNode(otherlv_9, grammarAccess.getSwitchTypeAccess().getLeftCurlyBracketKeyword_6_3());
            	        
            	    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1704:1: ( (lv_caseCode_10_0= ruleCode ) )*
            	    loop36:
            	    do {
            	        int alt36=2;
            	        int LA36_0 = input.LA(1);

            	        if ( (LA36_0==RULE_ID||LA36_0==23||LA36_0==26||LA36_0==28||LA36_0==32||LA36_0==34||LA36_0==39) ) {
            	            alt36=1;
            	        }


            	        switch (alt36) {
            	    	case 1 :
            	    	    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1705:1: (lv_caseCode_10_0= ruleCode )
            	    	    {
            	    	    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1705:1: (lv_caseCode_10_0= ruleCode )
            	    	    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1706:3: lv_caseCode_10_0= ruleCode
            	    	    {
            	    	     
            	    	    	        newCompositeNode(grammarAccess.getSwitchTypeAccess().getCaseCodeCodeParserRuleCall_6_4_0()); 
            	    	    	    
            	    	    pushFollow(FOLLOW_ruleCode_in_ruleSwitchType3481);
            	    	    lv_caseCode_10_0=ruleCode();

            	    	    state._fsp--;


            	    	    	        if (current==null) {
            	    	    	            current = createModelElementForParent(grammarAccess.getSwitchTypeRule());
            	    	    	        }
            	    	           		add(
            	    	           			current, 
            	    	           			"caseCode",
            	    	            		lv_caseCode_10_0, 
            	    	            		"Code");
            	    	    	        afterParserOrEnumRuleCall();
            	    	    	    

            	    	    }


            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop36;
            	        }
            	    } while (true);

            	    otherlv_11=(Token)match(input,29,FOLLOW_29_in_ruleSwitchType3494); 

            	        	newLeafNode(otherlv_11, grammarAccess.getSwitchTypeAccess().getRightCurlyBracketKeyword_6_5());
            	        
            	    otherlv_12=(Token)match(input,11,FOLLOW_11_in_ruleSwitchType3506); 

            	        	newLeafNode(otherlv_12, grammarAccess.getSwitchTypeAccess().getSemicolonKeyword_6_6());
            	        

            	    }
            	    break;

            	default :
            	    break loop37;
                }
            } while (true);

            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1730:3: (otherlv_13= 'default' otherlv_14= '{' ( (lv_defaultCode_15_0= ruleCode ) )* otherlv_16= '}' otherlv_17= ';' )?
            int alt39=2;
            int LA39_0 = input.LA(1);

            if ( (LA39_0==42) ) {
                alt39=1;
            }
            switch (alt39) {
                case 1 :
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1730:5: otherlv_13= 'default' otherlv_14= '{' ( (lv_defaultCode_15_0= ruleCode ) )* otherlv_16= '}' otherlv_17= ';'
                    {
                    otherlv_13=(Token)match(input,42,FOLLOW_42_in_ruleSwitchType3521); 

                        	newLeafNode(otherlv_13, grammarAccess.getSwitchTypeAccess().getDefaultKeyword_7_0());
                        
                    otherlv_14=(Token)match(input,28,FOLLOW_28_in_ruleSwitchType3533); 

                        	newLeafNode(otherlv_14, grammarAccess.getSwitchTypeAccess().getLeftCurlyBracketKeyword_7_1());
                        
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1738:1: ( (lv_defaultCode_15_0= ruleCode ) )*
                    loop38:
                    do {
                        int alt38=2;
                        int LA38_0 = input.LA(1);

                        if ( (LA38_0==RULE_ID||LA38_0==23||LA38_0==26||LA38_0==28||LA38_0==32||LA38_0==34||LA38_0==39) ) {
                            alt38=1;
                        }


                        switch (alt38) {
                    	case 1 :
                    	    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1739:1: (lv_defaultCode_15_0= ruleCode )
                    	    {
                    	    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1739:1: (lv_defaultCode_15_0= ruleCode )
                    	    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1740:3: lv_defaultCode_15_0= ruleCode
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getSwitchTypeAccess().getDefaultCodeCodeParserRuleCall_7_2_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleCode_in_ruleSwitchType3554);
                    	    lv_defaultCode_15_0=ruleCode();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getSwitchTypeRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"defaultCode",
                    	            		lv_defaultCode_15_0, 
                    	            		"Code");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop38;
                        }
                    } while (true);

                    otherlv_16=(Token)match(input,29,FOLLOW_29_in_ruleSwitchType3567); 

                        	newLeafNode(otherlv_16, grammarAccess.getSwitchTypeAccess().getRightCurlyBracketKeyword_7_3());
                        
                    otherlv_17=(Token)match(input,11,FOLLOW_11_in_ruleSwitchType3579); 

                        	newLeafNode(otherlv_17, grammarAccess.getSwitchTypeAccess().getSemicolonKeyword_7_4());
                        

                    }
                    break;

            }

            otherlv_18=(Token)match(input,29,FOLLOW_29_in_ruleSwitchType3593); 

                	newLeafNode(otherlv_18, grammarAccess.getSwitchTypeAccess().getRightCurlyBracketKeyword_8());
                

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleSwitchType"


    // $ANTLR start "entryRuleMethod"
    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1776:1: entryRuleMethod returns [EObject current=null] : iv_ruleMethod= ruleMethod EOF ;
    public final EObject entryRuleMethod() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMethod = null;


        try {
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1777:2: (iv_ruleMethod= ruleMethod EOF )
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1778:2: iv_ruleMethod= ruleMethod EOF
            {
             newCompositeNode(grammarAccess.getMethodRule()); 
            pushFollow(FOLLOW_ruleMethod_in_entryRuleMethod3629);
            iv_ruleMethod=ruleMethod();

            state._fsp--;

             current =iv_ruleMethod; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleMethod3639); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleMethod"


    // $ANTLR start "ruleMethod"
    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1785:1: ruleMethod returns [EObject current=null] : ( ( (lv_params_0_0= ruleArrayLiteral ) ) ( (lv_execute_1_0= 'spawn' ) ) ( (lv_methodName_2_0= ruleMethodName ) ) ) ;
    public final EObject ruleMethod() throws RecognitionException {
        EObject current = null;

        Token lv_execute_1_0=null;
        EObject lv_params_0_0 = null;

        EObject lv_methodName_2_0 = null;


         enterRule(); 
            
        try {
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1788:28: ( ( ( (lv_params_0_0= ruleArrayLiteral ) ) ( (lv_execute_1_0= 'spawn' ) ) ( (lv_methodName_2_0= ruleMethodName ) ) ) )
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1789:1: ( ( (lv_params_0_0= ruleArrayLiteral ) ) ( (lv_execute_1_0= 'spawn' ) ) ( (lv_methodName_2_0= ruleMethodName ) ) )
            {
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1789:1: ( ( (lv_params_0_0= ruleArrayLiteral ) ) ( (lv_execute_1_0= 'spawn' ) ) ( (lv_methodName_2_0= ruleMethodName ) ) )
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1789:2: ( (lv_params_0_0= ruleArrayLiteral ) ) ( (lv_execute_1_0= 'spawn' ) ) ( (lv_methodName_2_0= ruleMethodName ) )
            {
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1789:2: ( (lv_params_0_0= ruleArrayLiteral ) )
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1790:1: (lv_params_0_0= ruleArrayLiteral )
            {
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1790:1: (lv_params_0_0= ruleArrayLiteral )
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1791:3: lv_params_0_0= ruleArrayLiteral
            {
             
            	        newCompositeNode(grammarAccess.getMethodAccess().getParamsArrayLiteralParserRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_ruleArrayLiteral_in_ruleMethod3685);
            lv_params_0_0=ruleArrayLiteral();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getMethodRule());
            	        }
                   		set(
                   			current, 
                   			"params",
                    		lv_params_0_0, 
                    		"ArrayLiteral");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1807:2: ( (lv_execute_1_0= 'spawn' ) )
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1808:1: (lv_execute_1_0= 'spawn' )
            {
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1808:1: (lv_execute_1_0= 'spawn' )
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1809:3: lv_execute_1_0= 'spawn'
            {
            lv_execute_1_0=(Token)match(input,43,FOLLOW_43_in_ruleMethod3703); 

                    newLeafNode(lv_execute_1_0, grammarAccess.getMethodAccess().getExecuteSpawnKeyword_1_0());
                

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getMethodRule());
            	        }
                   		setWithLastConsumed(current, "execute", lv_execute_1_0, "spawn");
            	    

            }


            }

            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1822:2: ( (lv_methodName_2_0= ruleMethodName ) )
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1823:1: (lv_methodName_2_0= ruleMethodName )
            {
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1823:1: (lv_methodName_2_0= ruleMethodName )
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1824:3: lv_methodName_2_0= ruleMethodName
            {
             
            	        newCompositeNode(grammarAccess.getMethodAccess().getMethodNameMethodNameParserRuleCall_2_0()); 
            	    
            pushFollow(FOLLOW_ruleMethodName_in_ruleMethod3737);
            lv_methodName_2_0=ruleMethodName();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getMethodRule());
            	        }
                   		set(
                   			current, 
                   			"methodName",
                    		lv_methodName_2_0, 
                    		"MethodName");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleMethod"


    // $ANTLR start "entryRuleOPERATOR"
    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1850:1: entryRuleOPERATOR returns [String current=null] : iv_ruleOPERATOR= ruleOPERATOR EOF ;
    public final String entryRuleOPERATOR() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleOPERATOR = null;


        try {
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1851:2: (iv_ruleOPERATOR= ruleOPERATOR EOF )
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1852:2: iv_ruleOPERATOR= ruleOPERATOR EOF
            {
             newCompositeNode(grammarAccess.getOPERATORRule()); 
            pushFollow(FOLLOW_ruleOPERATOR_in_entryRuleOPERATOR3776);
            iv_ruleOPERATOR=ruleOPERATOR();

            state._fsp--;

             current =iv_ruleOPERATOR.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOPERATOR3787); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleOPERATOR"


    // $ANTLR start "ruleOPERATOR"
    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1859:1: ruleOPERATOR returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= '+' | kw= '-' | kw= '*' | kw= '/' | kw= 'mod' | kw= '^' ) ;
    public final AntlrDatatypeRuleToken ruleOPERATOR() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule(); 
            
        try {
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1862:28: ( (kw= '+' | kw= '-' | kw= '*' | kw= '/' | kw= 'mod' | kw= '^' ) )
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1863:1: (kw= '+' | kw= '-' | kw= '*' | kw= '/' | kw= 'mod' | kw= '^' )
            {
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1863:1: (kw= '+' | kw= '-' | kw= '*' | kw= '/' | kw= 'mod' | kw= '^' )
            int alt40=6;
            switch ( input.LA(1) ) {
            case 15:
                {
                alt40=1;
                }
                break;
            case 16:
                {
                alt40=2;
                }
                break;
            case 44:
                {
                alt40=3;
                }
                break;
            case 45:
                {
                alt40=4;
                }
                break;
            case 46:
                {
                alt40=5;
                }
                break;
            case 47:
                {
                alt40=6;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 40, 0, input);

                throw nvae;
            }

            switch (alt40) {
                case 1 :
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1864:2: kw= '+'
                    {
                    kw=(Token)match(input,15,FOLLOW_15_in_ruleOPERATOR3825); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getOPERATORAccess().getPlusSignKeyword_0()); 
                        

                    }
                    break;
                case 2 :
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1871:2: kw= '-'
                    {
                    kw=(Token)match(input,16,FOLLOW_16_in_ruleOPERATOR3844); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getOPERATORAccess().getHyphenMinusKeyword_1()); 
                        

                    }
                    break;
                case 3 :
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1878:2: kw= '*'
                    {
                    kw=(Token)match(input,44,FOLLOW_44_in_ruleOPERATOR3863); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getOPERATORAccess().getAsteriskKeyword_2()); 
                        

                    }
                    break;
                case 4 :
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1885:2: kw= '/'
                    {
                    kw=(Token)match(input,45,FOLLOW_45_in_ruleOPERATOR3882); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getOPERATORAccess().getSolidusKeyword_3()); 
                        

                    }
                    break;
                case 5 :
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1892:2: kw= 'mod'
                    {
                    kw=(Token)match(input,46,FOLLOW_46_in_ruleOPERATOR3901); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getOPERATORAccess().getModKeyword_4()); 
                        

                    }
                    break;
                case 6 :
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1899:2: kw= '^'
                    {
                    kw=(Token)match(input,47,FOLLOW_47_in_ruleOPERATOR3920); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getOPERATORAccess().getCircumflexAccentKeyword_5()); 
                        

                    }
                    break;

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleOPERATOR"


    // $ANTLR start "entryRuleANYTHING"
    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1914:1: entryRuleANYTHING returns [EObject current=null] : iv_ruleANYTHING= ruleANYTHING EOF ;
    public final EObject entryRuleANYTHING() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleANYTHING = null;


        try {
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1915:2: (iv_ruleANYTHING= ruleANYTHING EOF )
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1916:2: iv_ruleANYTHING= ruleANYTHING EOF
            {
             newCompositeNode(grammarAccess.getANYTHINGRule()); 
            pushFollow(FOLLOW_ruleANYTHING_in_entryRuleANYTHING3962);
            iv_ruleANYTHING=ruleANYTHING();

            state._fsp--;

             current =iv_ruleANYTHING; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleANYTHING3972); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleANYTHING"


    // $ANTLR start "ruleANYTHING"
    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1923:1: ruleANYTHING returns [EObject current=null] : ( ( (lv_bool_0_0= ruleBoolean ) ) | ( (lv_num_1_0= RULE_NUMBER ) ) | ( (lv_string_2_0= RULE_STRING ) ) | ( (otherlv_3= RULE_ID ) ) ) ;
    public final EObject ruleANYTHING() throws RecognitionException {
        EObject current = null;

        Token lv_num_1_0=null;
        Token lv_string_2_0=null;
        Token otherlv_3=null;
        EObject lv_bool_0_0 = null;


         enterRule(); 
            
        try {
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1926:28: ( ( ( (lv_bool_0_0= ruleBoolean ) ) | ( (lv_num_1_0= RULE_NUMBER ) ) | ( (lv_string_2_0= RULE_STRING ) ) | ( (otherlv_3= RULE_ID ) ) ) )
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1927:1: ( ( (lv_bool_0_0= ruleBoolean ) ) | ( (lv_num_1_0= RULE_NUMBER ) ) | ( (lv_string_2_0= RULE_STRING ) ) | ( (otherlv_3= RULE_ID ) ) )
            {
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1927:1: ( ( (lv_bool_0_0= ruleBoolean ) ) | ( (lv_num_1_0= RULE_NUMBER ) ) | ( (lv_string_2_0= RULE_STRING ) ) | ( (otherlv_3= RULE_ID ) ) )
            int alt41=4;
            switch ( input.LA(1) ) {
            case 48:
            case 49:
            case 50:
            case 51:
                {
                alt41=1;
                }
                break;
            case RULE_NUMBER:
                {
                alt41=2;
                }
                break;
            case RULE_STRING:
                {
                alt41=3;
                }
                break;
            case RULE_ID:
                {
                alt41=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 41, 0, input);

                throw nvae;
            }

            switch (alt41) {
                case 1 :
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1927:2: ( (lv_bool_0_0= ruleBoolean ) )
                    {
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1927:2: ( (lv_bool_0_0= ruleBoolean ) )
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1928:1: (lv_bool_0_0= ruleBoolean )
                    {
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1928:1: (lv_bool_0_0= ruleBoolean )
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1929:3: lv_bool_0_0= ruleBoolean
                    {
                     
                    	        newCompositeNode(grammarAccess.getANYTHINGAccess().getBoolBooleanParserRuleCall_0_0()); 
                    	    
                    pushFollow(FOLLOW_ruleBoolean_in_ruleANYTHING4018);
                    lv_bool_0_0=ruleBoolean();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getANYTHINGRule());
                    	        }
                           		set(
                           			current, 
                           			"bool",
                            		lv_bool_0_0, 
                            		"Boolean");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;
                case 2 :
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1946:6: ( (lv_num_1_0= RULE_NUMBER ) )
                    {
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1946:6: ( (lv_num_1_0= RULE_NUMBER ) )
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1947:1: (lv_num_1_0= RULE_NUMBER )
                    {
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1947:1: (lv_num_1_0= RULE_NUMBER )
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1948:3: lv_num_1_0= RULE_NUMBER
                    {
                    lv_num_1_0=(Token)match(input,RULE_NUMBER,FOLLOW_RULE_NUMBER_in_ruleANYTHING4041); 

                    			newLeafNode(lv_num_1_0, grammarAccess.getANYTHINGAccess().getNumNUMBERTerminalRuleCall_1_0()); 
                    		

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getANYTHINGRule());
                    	        }
                           		setWithLastConsumed(
                           			current, 
                           			"num",
                            		lv_num_1_0, 
                            		"NUMBER");
                    	    

                    }


                    }


                    }
                    break;
                case 3 :
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1965:6: ( (lv_string_2_0= RULE_STRING ) )
                    {
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1965:6: ( (lv_string_2_0= RULE_STRING ) )
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1966:1: (lv_string_2_0= RULE_STRING )
                    {
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1966:1: (lv_string_2_0= RULE_STRING )
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1967:3: lv_string_2_0= RULE_STRING
                    {
                    lv_string_2_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleANYTHING4069); 

                    			newLeafNode(lv_string_2_0, grammarAccess.getANYTHINGAccess().getStringSTRINGTerminalRuleCall_2_0()); 
                    		

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getANYTHINGRule());
                    	        }
                           		setWithLastConsumed(
                           			current, 
                           			"string",
                            		lv_string_2_0, 
                            		"STRING");
                    	    

                    }


                    }


                    }
                    break;
                case 4 :
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1984:6: ( (otherlv_3= RULE_ID ) )
                    {
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1984:6: ( (otherlv_3= RULE_ID ) )
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1985:1: (otherlv_3= RULE_ID )
                    {
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1985:1: (otherlv_3= RULE_ID )
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:1986:3: otherlv_3= RULE_ID
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getANYTHINGRule());
                    	        }
                            
                    otherlv_3=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleANYTHING4100); 

                    		newLeafNode(otherlv_3, grammarAccess.getANYTHINGAccess().getReferenceDeclarationCrossReference_3_0()); 
                    	

                    }


                    }


                    }
                    break;

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleANYTHING"


    // $ANTLR start "entryRuleBoolean"
    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:2005:1: entryRuleBoolean returns [EObject current=null] : iv_ruleBoolean= ruleBoolean EOF ;
    public final EObject entryRuleBoolean() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleBoolean = null;


        try {
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:2006:2: (iv_ruleBoolean= ruleBoolean EOF )
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:2007:2: iv_ruleBoolean= ruleBoolean EOF
            {
             newCompositeNode(grammarAccess.getBooleanRule()); 
            pushFollow(FOLLOW_ruleBoolean_in_entryRuleBoolean4136);
            iv_ruleBoolean=ruleBoolean();

            state._fsp--;

             current =iv_ruleBoolean; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleBoolean4146); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleBoolean"


    // $ANTLR start "ruleBoolean"
    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:2014:1: ruleBoolean returns [EObject current=null] : ( ( ( (lv_bool_0_1= 'true' | lv_bool_0_2= 'false' ) ) ) | ( (lv_command_1_0= ruleBoolCommand ) ) ) ;
    public final EObject ruleBoolean() throws RecognitionException {
        EObject current = null;

        Token lv_bool_0_1=null;
        Token lv_bool_0_2=null;
        AntlrDatatypeRuleToken lv_command_1_0 = null;


         enterRule(); 
            
        try {
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:2017:28: ( ( ( ( (lv_bool_0_1= 'true' | lv_bool_0_2= 'false' ) ) ) | ( (lv_command_1_0= ruleBoolCommand ) ) ) )
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:2018:1: ( ( ( (lv_bool_0_1= 'true' | lv_bool_0_2= 'false' ) ) ) | ( (lv_command_1_0= ruleBoolCommand ) ) )
            {
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:2018:1: ( ( ( (lv_bool_0_1= 'true' | lv_bool_0_2= 'false' ) ) ) | ( (lv_command_1_0= ruleBoolCommand ) ) )
            int alt43=2;
            int LA43_0 = input.LA(1);

            if ( ((LA43_0>=48 && LA43_0<=49)) ) {
                alt43=1;
            }
            else if ( ((LA43_0>=50 && LA43_0<=51)) ) {
                alt43=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 43, 0, input);

                throw nvae;
            }
            switch (alt43) {
                case 1 :
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:2018:2: ( ( (lv_bool_0_1= 'true' | lv_bool_0_2= 'false' ) ) )
                    {
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:2018:2: ( ( (lv_bool_0_1= 'true' | lv_bool_0_2= 'false' ) ) )
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:2019:1: ( (lv_bool_0_1= 'true' | lv_bool_0_2= 'false' ) )
                    {
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:2019:1: ( (lv_bool_0_1= 'true' | lv_bool_0_2= 'false' ) )
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:2020:1: (lv_bool_0_1= 'true' | lv_bool_0_2= 'false' )
                    {
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:2020:1: (lv_bool_0_1= 'true' | lv_bool_0_2= 'false' )
                    int alt42=2;
                    int LA42_0 = input.LA(1);

                    if ( (LA42_0==48) ) {
                        alt42=1;
                    }
                    else if ( (LA42_0==49) ) {
                        alt42=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 42, 0, input);

                        throw nvae;
                    }
                    switch (alt42) {
                        case 1 :
                            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:2021:3: lv_bool_0_1= 'true'
                            {
                            lv_bool_0_1=(Token)match(input,48,FOLLOW_48_in_ruleBoolean4191); 

                                    newLeafNode(lv_bool_0_1, grammarAccess.getBooleanAccess().getBoolTrueKeyword_0_0_0());
                                

                            	        if (current==null) {
                            	            current = createModelElement(grammarAccess.getBooleanRule());
                            	        }
                                   		setWithLastConsumed(current, "bool", lv_bool_0_1, null);
                            	    

                            }
                            break;
                        case 2 :
                            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:2033:8: lv_bool_0_2= 'false'
                            {
                            lv_bool_0_2=(Token)match(input,49,FOLLOW_49_in_ruleBoolean4220); 

                                    newLeafNode(lv_bool_0_2, grammarAccess.getBooleanAccess().getBoolFalseKeyword_0_0_1());
                                

                            	        if (current==null) {
                            	            current = createModelElement(grammarAccess.getBooleanRule());
                            	        }
                                   		setWithLastConsumed(current, "bool", lv_bool_0_2, null);
                            	    

                            }
                            break;

                    }


                    }


                    }


                    }
                    break;
                case 2 :
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:2049:6: ( (lv_command_1_0= ruleBoolCommand ) )
                    {
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:2049:6: ( (lv_command_1_0= ruleBoolCommand ) )
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:2050:1: (lv_command_1_0= ruleBoolCommand )
                    {
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:2050:1: (lv_command_1_0= ruleBoolCommand )
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:2051:3: lv_command_1_0= ruleBoolCommand
                    {
                     
                    	        newCompositeNode(grammarAccess.getBooleanAccess().getCommandBoolCommandParserRuleCall_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleBoolCommand_in_ruleBoolean4263);
                    lv_command_1_0=ruleBoolCommand();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getBooleanRule());
                    	        }
                           		set(
                           			current, 
                           			"command",
                            		lv_command_1_0, 
                            		"BoolCommand");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleBoolean"


    // $ANTLR start "entryRuleBooleanContent"
    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:2075:1: entryRuleBooleanContent returns [EObject current=null] : iv_ruleBooleanContent= ruleBooleanContent EOF ;
    public final EObject entryRuleBooleanContent() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleBooleanContent = null;


        try {
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:2076:2: (iv_ruleBooleanContent= ruleBooleanContent EOF )
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:2077:2: iv_ruleBooleanContent= ruleBooleanContent EOF
            {
             newCompositeNode(grammarAccess.getBooleanContentRule()); 
            pushFollow(FOLLOW_ruleBooleanContent_in_entryRuleBooleanContent4299);
            iv_ruleBooleanContent=ruleBooleanContent();

            state._fsp--;

             current =iv_ruleBooleanContent; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleBooleanContent4309); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleBooleanContent"


    // $ANTLR start "ruleBooleanContent"
    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:2084:1: ruleBooleanContent returns [EObject current=null] : ( (lv_boolCon_0_0= ruleBracketContent ) ) ;
    public final EObject ruleBooleanContent() throws RecognitionException {
        EObject current = null;

        EObject lv_boolCon_0_0 = null;


         enterRule(); 
            
        try {
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:2087:28: ( ( (lv_boolCon_0_0= ruleBracketContent ) ) )
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:2088:1: ( (lv_boolCon_0_0= ruleBracketContent ) )
            {
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:2088:1: ( (lv_boolCon_0_0= ruleBracketContent ) )
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:2089:1: (lv_boolCon_0_0= ruleBracketContent )
            {
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:2089:1: (lv_boolCon_0_0= ruleBracketContent )
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:2090:3: lv_boolCon_0_0= ruleBracketContent
            {
             
            	        newCompositeNode(grammarAccess.getBooleanContentAccess().getBoolConBracketContentParserRuleCall_0()); 
            	    
            pushFollow(FOLLOW_ruleBracketContent_in_ruleBooleanContent4354);
            lv_boolCon_0_0=ruleBracketContent();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getBooleanContentRule());
            	        }
                   		set(
                   			current, 
                   			"boolCon",
                    		lv_boolCon_0_0, 
                    		"BracketContent");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleBooleanContent"


    // $ANTLR start "entryRuleMethodName"
    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:2114:1: entryRuleMethodName returns [EObject current=null] : iv_ruleMethodName= ruleMethodName EOF ;
    public final EObject entryRuleMethodName() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMethodName = null;


        try {
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:2115:2: (iv_ruleMethodName= ruleMethodName EOF )
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:2116:2: iv_ruleMethodName= ruleMethodName EOF
            {
             newCompositeNode(grammarAccess.getMethodNameRule()); 
            pushFollow(FOLLOW_ruleMethodName_in_entryRuleMethodName4389);
            iv_ruleMethodName=ruleMethodName();

            state._fsp--;

             current =iv_ruleMethodName; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleMethodName4399); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleMethodName"


    // $ANTLR start "ruleMethodName"
    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:2123:1: ruleMethodName returns [EObject current=null] : ( (otherlv_0= RULE_ID ) ) ;
    public final EObject ruleMethodName() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;

         enterRule(); 
            
        try {
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:2126:28: ( ( (otherlv_0= RULE_ID ) ) )
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:2127:1: ( (otherlv_0= RULE_ID ) )
            {
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:2127:1: ( (otherlv_0= RULE_ID ) )
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:2128:1: (otherlv_0= RULE_ID )
            {
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:2128:1: (otherlv_0= RULE_ID )
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:2129:3: otherlv_0= RULE_ID
            {

            			if (current==null) {
            	            current = createModelElement(grammarAccess.getMethodNameRule());
            	        }
                    
            otherlv_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleMethodName4443); 

            		newLeafNode(otherlv_0, grammarAccess.getMethodNameAccess().getRefDeclarationCrossReference_0()); 
            	

            }


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleMethodName"


    // $ANTLR start "entryRuleBoolCommand"
    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:2148:1: entryRuleBoolCommand returns [String current=null] : iv_ruleBoolCommand= ruleBoolCommand EOF ;
    public final String entryRuleBoolCommand() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleBoolCommand = null;


        try {
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:2149:2: (iv_ruleBoolCommand= ruleBoolCommand EOF )
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:2150:2: iv_ruleBoolCommand= ruleBoolCommand EOF
            {
             newCompositeNode(grammarAccess.getBoolCommandRule()); 
            pushFollow(FOLLOW_ruleBoolCommand_in_entryRuleBoolCommand4479);
            iv_ruleBoolCommand=ruleBoolCommand();

            state._fsp--;

             current =iv_ruleBoolCommand.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleBoolCommand4490); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleBoolCommand"


    // $ANTLR start "ruleBoolCommand"
    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:2157:1: ruleBoolCommand returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= 'isServer' | kw= 'isPlayer' ) ;
    public final AntlrDatatypeRuleToken ruleBoolCommand() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule(); 
            
        try {
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:2160:28: ( (kw= 'isServer' | kw= 'isPlayer' ) )
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:2161:1: (kw= 'isServer' | kw= 'isPlayer' )
            {
            // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:2161:1: (kw= 'isServer' | kw= 'isPlayer' )
            int alt44=2;
            int LA44_0 = input.LA(1);

            if ( (LA44_0==50) ) {
                alt44=1;
            }
            else if ( (LA44_0==51) ) {
                alt44=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 44, 0, input);

                throw nvae;
            }
            switch (alt44) {
                case 1 :
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:2162:2: kw= 'isServer'
                    {
                    kw=(Token)match(input,50,FOLLOW_50_in_ruleBoolCommand4528); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getBoolCommandAccess().getIsServerKeyword_0()); 
                        

                    }
                    break;
                case 2 :
                    // ../raven.sqf/src-gen/raven/parser/antlr/internal/InternalSQF.g:2169:2: kw= 'isPlayer'
                    {
                    kw=(Token)match(input,51,FOLLOW_51_in_ruleBoolCommand4547); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getBoolCommandAccess().getIsPlayerKeyword_1()); 
                        

                    }
                    break;

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleBoolCommand"

    // Delegated rules


    protected DFA18 dfa18 = new DFA18(this);
    static final String DFA18_eotS =
        "\12\uffff";
    static final String DFA18_eofS =
        "\12\uffff";
    static final String DFA18_minS =
        "\3\4\7\uffff";
    static final String DFA18_maxS =
        "\1\63\1\27\1\24\7\uffff";
    static final String DFA18_acceptS =
        "\3\uffff\1\1\1\2\1\3\1\4\1\5\1\6\1\7";
    static final String DFA18_specialS =
        "\12\uffff}>";
    static final String[] DFA18_transitionS = {
            "\1\5\1\uffff\1\3\1\4\7\uffff\1\1\1\2\1\5\2\uffff\1\7\1\uffff"+
            "\1\11\1\6\30\uffff\4\10",
            "\1\5\1\uffff\1\3\12\uffff\1\5\2\uffff\1\7\2\uffff\1\6",
            "\1\5\1\uffff\1\3\12\uffff\1\5\2\uffff\1\7",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA18_eot = DFA.unpackEncodedString(DFA18_eotS);
    static final short[] DFA18_eof = DFA.unpackEncodedString(DFA18_eofS);
    static final char[] DFA18_min = DFA.unpackEncodedStringToUnsignedChars(DFA18_minS);
    static final char[] DFA18_max = DFA.unpackEncodedStringToUnsignedChars(DFA18_maxS);
    static final short[] DFA18_accept = DFA.unpackEncodedString(DFA18_acceptS);
    static final short[] DFA18_special = DFA.unpackEncodedString(DFA18_specialS);
    static final short[][] DFA18_transition;

    static {
        int numStates = DFA18_transitionS.length;
        DFA18_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA18_transition[i] = DFA.unpackEncodedString(DFA18_transitionS[i]);
        }
    }

    class DFA18 extends DFA {

        public DFA18(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 18;
            this.eot = DFA18_eot;
            this.eof = DFA18_eof;
            this.min = DFA18_min;
            this.max = DFA18_max;
            this.accept = DFA18_accept;
            this.special = DFA18_special;
            this.transition = DFA18_transition;
        }
        public String getDescription() {
            return "449:1: ( ( ( ( (lv_unOP_0_1= '+' | lv_unOP_0_2= '-' ) ) )? ( (lv_num_1_0= RULE_NUMBER ) ) ) | ( (lv_string_2_0= RULE_STRING ) ) | ( ( ( (lv_unOP_3_1= '+' | lv_unOP_3_2= '-' ) ) )? ( ( (otherlv_4= RULE_ID ) ) | ( (lv_forEachVar_5_0= '_x' ) ) ) ( ( (lv_sel_6_0= 'select' ) ) ( (lv_index_7_0= RULE_NUMBER ) ) )? ) | ( (otherlv_8= '+' )? ( (lv_arrayContent_9_0= ruleArrayLiteral ) ) ( ( (lv_execute_10_0= 'call' ) ) ( (lv_methodName_11_0= ruleMethodName ) ) )? ) | ( ( ( (lv_unOP_12_1= '+' | lv_unOP_12_2= '-' ) ) )? ( (lv_embraced_13_0= '(' ) ) ( (lv_embrCon_14_0= ruleBracketContent ) ) otherlv_15= ')' ) | ( (lv_bool_16_0= ruleBoolean ) ) | ( ( (lv_param_17_0= '_this' ) ) (otherlv_18= 'select' ( (lv_index_19_0= RULE_NUMBER ) ) )? ) )";
        }
    }
 

    public static final BitSet FOLLOW_ruleModel_in_entryRuleModel75 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleModel85 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCode_in_ruleModel130 = new BitSet(new long[]{0x0000008514800012L});
    public static final BitSet FOLLOW_ruleCode_in_entryRuleCode166 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleCode176 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDeclaration_in_ruleCode223 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_ruleCode235 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleControlStructure_in_ruleCode264 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_ruleCode276 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleMethod_in_ruleCode305 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_ruleCode317 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDeclaration_in_entryRuleDeclaration354 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleDeclaration364 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleDeclaration406 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleDeclaration423 = new BitSet(new long[]{0x000F008514DBE0D0L});
    public static final BitSet FOLLOW_ruleBracketContent_in_ruleDeclaration444 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBracketContent_in_entryRuleBracketContent480 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleBracketContent490 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDecContent_in_ruleBracketContent536 = new BitSet(new long[]{0x0000000000000022L});
    public static final BitSet FOLLOW_RULE_COMPARATOR_in_ruleBracketContent554 = new BitSet(new long[]{0x000F008514DBE0D0L});
    public static final BitSet FOLLOW_ruleDecContent_in_ruleBracketContent580 = new BitSet(new long[]{0x0000000000000022L});
    public static final BitSet FOLLOW_ruleDecContent_in_entryRuleDecContent618 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleDecContent628 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_ruleDecContent673 = new BitSet(new long[]{0x000F008514DBE0D0L});
    public static final BitSet FOLLOW_14_in_ruleDecContent702 = new BitSet(new long[]{0x000F008514DBE0D0L});
    public static final BitSet FOLLOW_ruleVarContent_in_ruleDecContent740 = new BitSet(new long[]{0x0000F00000018002L});
    public static final BitSet FOLLOW_ruleOPERATOR_in_ruleDecContent762 = new BitSet(new long[]{0x000F008514DBE0D0L});
    public static final BitSet FOLLOW_ruleVarContent_in_ruleDecContent783 = new BitSet(new long[]{0x0000F00000018002L});
    public static final BitSet FOLLOW_ruleVarContent_in_entryRuleVarContent821 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleVarContent831 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_ruleVarContent877 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_16_in_ruleVarContent906 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_RULE_NUMBER_in_ruleVarContent940 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleVarContent969 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_ruleVarContent1001 = new BitSet(new long[]{0x0000000000020010L});
    public static final BitSet FOLLOW_16_in_ruleVarContent1030 = new BitSet(new long[]{0x0000000000020010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleVarContent1068 = new BitSet(new long[]{0x0000000000040002L});
    public static final BitSet FOLLOW_17_in_ruleVarContent1092 = new BitSet(new long[]{0x0000000000040002L});
    public static final BitSet FOLLOW_18_in_ruleVarContent1125 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_RULE_NUMBER_in_ruleVarContent1155 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_ruleVarContent1183 = new BitSet(new long[]{0x0000008514880010L});
    public static final BitSet FOLLOW_ruleArrayLiteral_in_ruleVarContent1206 = new BitSet(new long[]{0x0000000000080002L});
    public static final BitSet FOLLOW_19_in_ruleVarContent1225 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleMethodName_in_ruleVarContent1259 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_ruleVarContent1289 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_16_in_ruleVarContent1318 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_ruleVarContent1353 = new BitSet(new long[]{0x000F008514DBE0D0L});
    public static final BitSet FOLLOW_ruleBracketContent_in_ruleVarContent1387 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_21_in_ruleVarContent1399 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBoolean_in_ruleVarContent1427 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_22_in_ruleVarContent1452 = new BitSet(new long[]{0x0000000000040002L});
    public static final BitSet FOLLOW_18_in_ruleVarContent1478 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_RULE_NUMBER_in_ruleVarContent1495 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleArrayLiteral_in_entryRuleArrayLiteral1539 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleArrayLiteral1549 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_23_in_ruleArrayLiteral1592 = new BitSet(new long[]{0x000F008516DBE0D0L});
    public static final BitSet FOLLOW_ruleVarContent_in_ruleArrayLiteral1627 = new BitSet(new long[]{0x0000000003000000L});
    public static final BitSet FOLLOW_24_in_ruleArrayLiteral1640 = new BitSet(new long[]{0x000F008514DBE0D0L});
    public static final BitSet FOLLOW_ruleVarContent_in_ruleArrayLiteral1661 = new BitSet(new long[]{0x0000000003000000L});
    public static final BitSet FOLLOW_25_in_ruleArrayLiteral1677 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleControlStructure_in_entryRuleControlStructure1713 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleControlStructure1723 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleifType_in_ruleControlStructure1769 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleWhileType_in_ruleControlStructure1796 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleForType_in_ruleControlStructure1823 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleForeachType_in_ruleControlStructure1850 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSwitchType_in_ruleControlStructure1877 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleifType_in_entryRuleifType1913 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleifType1923 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_26_in_ruleifType1960 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_ruleifType1972 = new BitSet(new long[]{0x000F008514DBE0D0L});
    public static final BitSet FOLLOW_ruleBooleanContent_in_ruleifType1993 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_21_in_ruleifType2005 = new BitSet(new long[]{0x0000000088000000L});
    public static final BitSet FOLLOW_27_in_ruleifType2019 = new BitSet(new long[]{0x0000000010800000L});
    public static final BitSet FOLLOW_28_in_ruleifType2033 = new BitSet(new long[]{0x0000008534800010L});
    public static final BitSet FOLLOW_ruleCode_in_ruleifType2054 = new BitSet(new long[]{0x0000008534800010L});
    public static final BitSet FOLLOW_29_in_ruleifType2067 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_30_in_ruleifType2080 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_28_in_ruleifType2092 = new BitSet(new long[]{0x0000008534800010L});
    public static final BitSet FOLLOW_ruleCode_in_ruleifType2113 = new BitSet(new long[]{0x0000008534800010L});
    public static final BitSet FOLLOW_29_in_ruleifType2126 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_23_in_ruleifType2148 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_28_in_ruleifType2160 = new BitSet(new long[]{0x0000008534800010L});
    public static final BitSet FOLLOW_ruleCode_in_ruleifType2181 = new BitSet(new long[]{0x0000008534800010L});
    public static final BitSet FOLLOW_29_in_ruleifType2194 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_24_in_ruleifType2206 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_28_in_ruleifType2218 = new BitSet(new long[]{0x0000008534800010L});
    public static final BitSet FOLLOW_ruleCode_in_ruleifType2239 = new BitSet(new long[]{0x0000008534800010L});
    public static final BitSet FOLLOW_29_in_ruleifType2252 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_25_in_ruleifType2264 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_31_in_ruleifType2286 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_28_in_ruleifType2298 = new BitSet(new long[]{0x0000008534800010L});
    public static final BitSet FOLLOW_ruleCode_in_ruleifType2319 = new BitSet(new long[]{0x0000008534800010L});
    public static final BitSet FOLLOW_29_in_ruleifType2332 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleWhileType_in_entryRuleWhileType2370 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleWhileType2380 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_32_in_ruleWhileType2417 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_28_in_ruleWhileType2429 = new BitSet(new long[]{0x000F008514DBE0D0L});
    public static final BitSet FOLLOW_ruleBooleanContent_in_ruleWhileType2450 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_29_in_ruleWhileType2462 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_33_in_ruleWhileType2474 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_28_in_ruleWhileType2486 = new BitSet(new long[]{0x0000008534800010L});
    public static final BitSet FOLLOW_ruleCode_in_ruleWhileType2507 = new BitSet(new long[]{0x0000008534800010L});
    public static final BitSet FOLLOW_29_in_ruleWhileType2520 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleForType_in_entryRuleForType2556 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleForType2566 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_34_in_ruleForType2603 = new BitSet(new long[]{0x0000000000800080L});
    public static final BitSet FOLLOW_23_in_ruleForType2617 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_28_in_ruleForType2629 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleDeclaration_in_ruleForType2650 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_29_in_ruleForType2662 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_24_in_ruleForType2674 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_28_in_ruleForType2686 = new BitSet(new long[]{0x000F008514DBE0D0L});
    public static final BitSet FOLLOW_ruleBooleanContent_in_ruleForType2707 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_29_in_ruleForType2719 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_24_in_ruleForType2731 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_28_in_ruleForType2743 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleDeclaration_in_ruleForType2764 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_29_in_ruleForType2776 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_25_in_ruleForType2788 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_ruleforVarDeclaration_in_ruleForType2818 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_35_in_ruleForType2829 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_RULE_NUMBER_in_ruleForType2846 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_36_in_ruleForType2863 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_RULE_NUMBER_in_ruleForType2880 = new BitSet(new long[]{0x0000002200000000L});
    public static final BitSet FOLLOW_37_in_ruleForType2898 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_RULE_NUMBER_in_ruleForType2915 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_33_in_ruleForType2936 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_28_in_ruleForType2948 = new BitSet(new long[]{0x0000008534800010L});
    public static final BitSet FOLLOW_ruleCode_in_ruleForType2969 = new BitSet(new long[]{0x0000008534800010L});
    public static final BitSet FOLLOW_29_in_ruleForType2982 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleforVarDeclaration_in_entryRuleforVarDeclaration3018 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleforVarDeclaration3028 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleforVarDeclaration3069 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleForeachType_in_entryRuleForeachType3109 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleForeachType3119 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_28_in_ruleForeachType3156 = new BitSet(new long[]{0x0000008534800010L});
    public static final BitSet FOLLOW_ruleCode_in_ruleForeachType3177 = new BitSet(new long[]{0x0000008534800010L});
    public static final BitSet FOLLOW_29_in_ruleForeachType3190 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_38_in_ruleForeachType3202 = new BitSet(new long[]{0x0000008514800010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleForeachType3223 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleArrayLiteral_in_ruleForeachType3250 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSwitchType_in_entryRuleSwitchType3287 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSwitchType3297 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_39_in_ruleSwitchType3334 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_ruleSwitchType3346 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleSwitchType3366 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_21_in_ruleSwitchType3378 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_33_in_ruleSwitchType3390 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_28_in_ruleSwitchType3402 = new BitSet(new long[]{0x0000050020000000L});
    public static final BitSet FOLLOW_40_in_ruleSwitchType3415 = new BitSet(new long[]{0x000F0000000000D0L});
    public static final BitSet FOLLOW_ruleANYTHING_in_ruleSwitchType3436 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_41_in_ruleSwitchType3448 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_28_in_ruleSwitchType3460 = new BitSet(new long[]{0x0000008534800010L});
    public static final BitSet FOLLOW_ruleCode_in_ruleSwitchType3481 = new BitSet(new long[]{0x0000008534800010L});
    public static final BitSet FOLLOW_29_in_ruleSwitchType3494 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_ruleSwitchType3506 = new BitSet(new long[]{0x0000050020000000L});
    public static final BitSet FOLLOW_42_in_ruleSwitchType3521 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_28_in_ruleSwitchType3533 = new BitSet(new long[]{0x0000008534800010L});
    public static final BitSet FOLLOW_ruleCode_in_ruleSwitchType3554 = new BitSet(new long[]{0x0000008534800010L});
    public static final BitSet FOLLOW_29_in_ruleSwitchType3567 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_ruleSwitchType3579 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_29_in_ruleSwitchType3593 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleMethod_in_entryRuleMethod3629 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleMethod3639 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleArrayLiteral_in_ruleMethod3685 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_43_in_ruleMethod3703 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleMethodName_in_ruleMethod3737 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOPERATOR_in_entryRuleOPERATOR3776 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOPERATOR3787 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_ruleOPERATOR3825 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_ruleOPERATOR3844 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_44_in_ruleOPERATOR3863 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_45_in_ruleOPERATOR3882 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_46_in_ruleOPERATOR3901 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_47_in_ruleOPERATOR3920 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleANYTHING_in_entryRuleANYTHING3962 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleANYTHING3972 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBoolean_in_ruleANYTHING4018 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_NUMBER_in_ruleANYTHING4041 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleANYTHING4069 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleANYTHING4100 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBoolean_in_entryRuleBoolean4136 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleBoolean4146 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_48_in_ruleBoolean4191 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_49_in_ruleBoolean4220 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBoolCommand_in_ruleBoolean4263 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBooleanContent_in_entryRuleBooleanContent4299 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleBooleanContent4309 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBracketContent_in_ruleBooleanContent4354 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleMethodName_in_entryRuleMethodName4389 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleMethodName4399 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleMethodName4443 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBoolCommand_in_entryRuleBoolCommand4479 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleBoolCommand4490 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_50_in_ruleBoolCommand4528 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_51_in_ruleBoolCommand4547 = new BitSet(new long[]{0x0000000000000002L});

}
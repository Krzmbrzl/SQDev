package raven.ui.contentassist.antlr.internal; 

import java.io.InputStream;
import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.ui.editor.contentassist.antlr.internal.AbstractInternalContentAssistParser;
import org.eclipse.xtext.ui.editor.contentassist.antlr.internal.DFA;
import raven.services.SQFGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalSQFParser extends AbstractInternalContentAssistParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_COMPARATOR", "RULE_NUMBER", "RULE_STRING", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "'!'", "'not'", "'+'", "'-'", "'*'", "'/'", "'mod'", "'^'", "'true'", "'false'", "'isServer'", "'isPlayer'", "';'", "'='", "')'", "'select'", "']'", "','", "'if'", "'('", "'then'", "'{'", "'}'", "'else'", "'['", "'exitWith'", "'while'", "'do'", "'for'", "'from'", "'to'", "'step'", "'forEach'", "'switch'", "'case'", "':'", "'default'", "'_x'", "'call'", "'_this'", "'spawn'"
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
    public String getGrammarFileName() { return "../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g"; }


     
     	private SQFGrammarAccess grammarAccess;
     	
        public void setGrammarAccess(SQFGrammarAccess grammarAccess) {
        	this.grammarAccess = grammarAccess;
        }
        
        @Override
        protected Grammar getGrammar() {
        	return grammarAccess.getGrammar();
        }
        
        @Override
        protected String getValueForTokenName(String tokenName) {
        	return tokenName;
        }




    // $ANTLR start "entryRuleModel"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:60:1: entryRuleModel : ruleModel EOF ;
    public final void entryRuleModel() throws RecognitionException {
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:61:1: ( ruleModel EOF )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:62:1: ruleModel EOF
            {
             before(grammarAccess.getModelRule()); 
            pushFollow(FOLLOW_ruleModel_in_entryRuleModel61);
            ruleModel();

            state._fsp--;

             after(grammarAccess.getModelRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleModel68); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleModel"


    // $ANTLR start "ruleModel"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:69:1: ruleModel : ( ( rule__Model__ElementsAssignment )* ) ;
    public final void ruleModel() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:73:2: ( ( ( rule__Model__ElementsAssignment )* ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:74:1: ( ( rule__Model__ElementsAssignment )* )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:74:1: ( ( rule__Model__ElementsAssignment )* )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:75:1: ( rule__Model__ElementsAssignment )*
            {
             before(grammarAccess.getModelAccess().getElementsAssignment()); 
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:76:1: ( rule__Model__ElementsAssignment )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==RULE_ID||LA1_0==29||LA1_0==32||LA1_0==35||LA1_0==37||LA1_0==39||LA1_0==44) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:76:2: rule__Model__ElementsAssignment
            	    {
            	    pushFollow(FOLLOW_rule__Model__ElementsAssignment_in_ruleModel94);
            	    rule__Model__ElementsAssignment();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);

             after(grammarAccess.getModelAccess().getElementsAssignment()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleModel"


    // $ANTLR start "entryRuleCode"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:88:1: entryRuleCode : ruleCode EOF ;
    public final void entryRuleCode() throws RecognitionException {
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:89:1: ( ruleCode EOF )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:90:1: ruleCode EOF
            {
             before(grammarAccess.getCodeRule()); 
            pushFollow(FOLLOW_ruleCode_in_entryRuleCode122);
            ruleCode();

            state._fsp--;

             after(grammarAccess.getCodeRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleCode129); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleCode"


    // $ANTLR start "ruleCode"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:97:1: ruleCode : ( ( rule__Code__Alternatives ) ) ;
    public final void ruleCode() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:101:2: ( ( ( rule__Code__Alternatives ) ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:102:1: ( ( rule__Code__Alternatives ) )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:102:1: ( ( rule__Code__Alternatives ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:103:1: ( rule__Code__Alternatives )
            {
             before(grammarAccess.getCodeAccess().getAlternatives()); 
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:104:1: ( rule__Code__Alternatives )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:104:2: rule__Code__Alternatives
            {
            pushFollow(FOLLOW_rule__Code__Alternatives_in_ruleCode155);
            rule__Code__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getCodeAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleCode"


    // $ANTLR start "entryRuleDeclaration"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:116:1: entryRuleDeclaration : ruleDeclaration EOF ;
    public final void entryRuleDeclaration() throws RecognitionException {
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:117:1: ( ruleDeclaration EOF )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:118:1: ruleDeclaration EOF
            {
             before(grammarAccess.getDeclarationRule()); 
            pushFollow(FOLLOW_ruleDeclaration_in_entryRuleDeclaration182);
            ruleDeclaration();

            state._fsp--;

             after(grammarAccess.getDeclarationRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleDeclaration189); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleDeclaration"


    // $ANTLR start "ruleDeclaration"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:125:1: ruleDeclaration : ( ( rule__Declaration__Group__0 ) ) ;
    public final void ruleDeclaration() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:129:2: ( ( ( rule__Declaration__Group__0 ) ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:130:1: ( ( rule__Declaration__Group__0 ) )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:130:1: ( ( rule__Declaration__Group__0 ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:131:1: ( rule__Declaration__Group__0 )
            {
             before(grammarAccess.getDeclarationAccess().getGroup()); 
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:132:1: ( rule__Declaration__Group__0 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:132:2: rule__Declaration__Group__0
            {
            pushFollow(FOLLOW_rule__Declaration__Group__0_in_ruleDeclaration215);
            rule__Declaration__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getDeclarationAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleDeclaration"


    // $ANTLR start "entryRuleBracketContent"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:144:1: entryRuleBracketContent : ruleBracketContent EOF ;
    public final void entryRuleBracketContent() throws RecognitionException {
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:145:1: ( ruleBracketContent EOF )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:146:1: ruleBracketContent EOF
            {
             before(grammarAccess.getBracketContentRule()); 
            pushFollow(FOLLOW_ruleBracketContent_in_entryRuleBracketContent242);
            ruleBracketContent();

            state._fsp--;

             after(grammarAccess.getBracketContentRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleBracketContent249); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleBracketContent"


    // $ANTLR start "ruleBracketContent"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:153:1: ruleBracketContent : ( ( rule__BracketContent__Group__0 ) ) ;
    public final void ruleBracketContent() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:157:2: ( ( ( rule__BracketContent__Group__0 ) ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:158:1: ( ( rule__BracketContent__Group__0 ) )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:158:1: ( ( rule__BracketContent__Group__0 ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:159:1: ( rule__BracketContent__Group__0 )
            {
             before(grammarAccess.getBracketContentAccess().getGroup()); 
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:160:1: ( rule__BracketContent__Group__0 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:160:2: rule__BracketContent__Group__0
            {
            pushFollow(FOLLOW_rule__BracketContent__Group__0_in_ruleBracketContent275);
            rule__BracketContent__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getBracketContentAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleBracketContent"


    // $ANTLR start "entryRuleDecContent"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:172:1: entryRuleDecContent : ruleDecContent EOF ;
    public final void entryRuleDecContent() throws RecognitionException {
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:173:1: ( ruleDecContent EOF )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:174:1: ruleDecContent EOF
            {
             before(grammarAccess.getDecContentRule()); 
            pushFollow(FOLLOW_ruleDecContent_in_entryRuleDecContent302);
            ruleDecContent();

            state._fsp--;

             after(grammarAccess.getDecContentRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleDecContent309); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleDecContent"


    // $ANTLR start "ruleDecContent"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:181:1: ruleDecContent : ( ( rule__DecContent__Group__0 ) ) ;
    public final void ruleDecContent() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:185:2: ( ( ( rule__DecContent__Group__0 ) ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:186:1: ( ( rule__DecContent__Group__0 ) )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:186:1: ( ( rule__DecContent__Group__0 ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:187:1: ( rule__DecContent__Group__0 )
            {
             before(grammarAccess.getDecContentAccess().getGroup()); 
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:188:1: ( rule__DecContent__Group__0 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:188:2: rule__DecContent__Group__0
            {
            pushFollow(FOLLOW_rule__DecContent__Group__0_in_ruleDecContent335);
            rule__DecContent__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getDecContentAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleDecContent"


    // $ANTLR start "entryRuleVarContent"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:200:1: entryRuleVarContent : ruleVarContent EOF ;
    public final void entryRuleVarContent() throws RecognitionException {
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:201:1: ( ruleVarContent EOF )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:202:1: ruleVarContent EOF
            {
             before(grammarAccess.getVarContentRule()); 
            pushFollow(FOLLOW_ruleVarContent_in_entryRuleVarContent362);
            ruleVarContent();

            state._fsp--;

             after(grammarAccess.getVarContentRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleVarContent369); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleVarContent"


    // $ANTLR start "ruleVarContent"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:209:1: ruleVarContent : ( ( rule__VarContent__Alternatives ) ) ;
    public final void ruleVarContent() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:213:2: ( ( ( rule__VarContent__Alternatives ) ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:214:1: ( ( rule__VarContent__Alternatives ) )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:214:1: ( ( rule__VarContent__Alternatives ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:215:1: ( rule__VarContent__Alternatives )
            {
             before(grammarAccess.getVarContentAccess().getAlternatives()); 
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:216:1: ( rule__VarContent__Alternatives )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:216:2: rule__VarContent__Alternatives
            {
            pushFollow(FOLLOW_rule__VarContent__Alternatives_in_ruleVarContent395);
            rule__VarContent__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getVarContentAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleVarContent"


    // $ANTLR start "entryRuleArrayLiteral"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:228:1: entryRuleArrayLiteral : ruleArrayLiteral EOF ;
    public final void entryRuleArrayLiteral() throws RecognitionException {
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:229:1: ( ruleArrayLiteral EOF )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:230:1: ruleArrayLiteral EOF
            {
             before(grammarAccess.getArrayLiteralRule()); 
            pushFollow(FOLLOW_ruleArrayLiteral_in_entryRuleArrayLiteral422);
            ruleArrayLiteral();

            state._fsp--;

             after(grammarAccess.getArrayLiteralRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleArrayLiteral429); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleArrayLiteral"


    // $ANTLR start "ruleArrayLiteral"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:237:1: ruleArrayLiteral : ( ( rule__ArrayLiteral__Group__0 ) ) ;
    public final void ruleArrayLiteral() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:241:2: ( ( ( rule__ArrayLiteral__Group__0 ) ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:242:1: ( ( rule__ArrayLiteral__Group__0 ) )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:242:1: ( ( rule__ArrayLiteral__Group__0 ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:243:1: ( rule__ArrayLiteral__Group__0 )
            {
             before(grammarAccess.getArrayLiteralAccess().getGroup()); 
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:244:1: ( rule__ArrayLiteral__Group__0 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:244:2: rule__ArrayLiteral__Group__0
            {
            pushFollow(FOLLOW_rule__ArrayLiteral__Group__0_in_ruleArrayLiteral455);
            rule__ArrayLiteral__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getArrayLiteralAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleArrayLiteral"


    // $ANTLR start "entryRuleControlStructure"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:256:1: entryRuleControlStructure : ruleControlStructure EOF ;
    public final void entryRuleControlStructure() throws RecognitionException {
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:257:1: ( ruleControlStructure EOF )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:258:1: ruleControlStructure EOF
            {
             before(grammarAccess.getControlStructureRule()); 
            pushFollow(FOLLOW_ruleControlStructure_in_entryRuleControlStructure482);
            ruleControlStructure();

            state._fsp--;

             after(grammarAccess.getControlStructureRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleControlStructure489); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleControlStructure"


    // $ANTLR start "ruleControlStructure"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:265:1: ruleControlStructure : ( ( rule__ControlStructure__Alternatives ) ) ;
    public final void ruleControlStructure() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:269:2: ( ( ( rule__ControlStructure__Alternatives ) ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:270:1: ( ( rule__ControlStructure__Alternatives ) )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:270:1: ( ( rule__ControlStructure__Alternatives ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:271:1: ( rule__ControlStructure__Alternatives )
            {
             before(grammarAccess.getControlStructureAccess().getAlternatives()); 
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:272:1: ( rule__ControlStructure__Alternatives )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:272:2: rule__ControlStructure__Alternatives
            {
            pushFollow(FOLLOW_rule__ControlStructure__Alternatives_in_ruleControlStructure515);
            rule__ControlStructure__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getControlStructureAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleControlStructure"


    // $ANTLR start "entryRuleifType"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:284:1: entryRuleifType : ruleifType EOF ;
    public final void entryRuleifType() throws RecognitionException {
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:285:1: ( ruleifType EOF )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:286:1: ruleifType EOF
            {
             before(grammarAccess.getIfTypeRule()); 
            pushFollow(FOLLOW_ruleifType_in_entryRuleifType542);
            ruleifType();

            state._fsp--;

             after(grammarAccess.getIfTypeRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleifType549); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleifType"


    // $ANTLR start "ruleifType"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:293:1: ruleifType : ( ( rule__IfType__Group__0 ) ) ;
    public final void ruleifType() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:297:2: ( ( ( rule__IfType__Group__0 ) ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:298:1: ( ( rule__IfType__Group__0 ) )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:298:1: ( ( rule__IfType__Group__0 ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:299:1: ( rule__IfType__Group__0 )
            {
             before(grammarAccess.getIfTypeAccess().getGroup()); 
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:300:1: ( rule__IfType__Group__0 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:300:2: rule__IfType__Group__0
            {
            pushFollow(FOLLOW_rule__IfType__Group__0_in_ruleifType575);
            rule__IfType__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getIfTypeAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleifType"


    // $ANTLR start "entryRuleWhileType"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:312:1: entryRuleWhileType : ruleWhileType EOF ;
    public final void entryRuleWhileType() throws RecognitionException {
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:313:1: ( ruleWhileType EOF )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:314:1: ruleWhileType EOF
            {
             before(grammarAccess.getWhileTypeRule()); 
            pushFollow(FOLLOW_ruleWhileType_in_entryRuleWhileType602);
            ruleWhileType();

            state._fsp--;

             after(grammarAccess.getWhileTypeRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleWhileType609); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleWhileType"


    // $ANTLR start "ruleWhileType"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:321:1: ruleWhileType : ( ( rule__WhileType__Group__0 ) ) ;
    public final void ruleWhileType() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:325:2: ( ( ( rule__WhileType__Group__0 ) ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:326:1: ( ( rule__WhileType__Group__0 ) )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:326:1: ( ( rule__WhileType__Group__0 ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:327:1: ( rule__WhileType__Group__0 )
            {
             before(grammarAccess.getWhileTypeAccess().getGroup()); 
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:328:1: ( rule__WhileType__Group__0 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:328:2: rule__WhileType__Group__0
            {
            pushFollow(FOLLOW_rule__WhileType__Group__0_in_ruleWhileType635);
            rule__WhileType__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getWhileTypeAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleWhileType"


    // $ANTLR start "entryRuleForType"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:340:1: entryRuleForType : ruleForType EOF ;
    public final void entryRuleForType() throws RecognitionException {
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:341:1: ( ruleForType EOF )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:342:1: ruleForType EOF
            {
             before(grammarAccess.getForTypeRule()); 
            pushFollow(FOLLOW_ruleForType_in_entryRuleForType662);
            ruleForType();

            state._fsp--;

             after(grammarAccess.getForTypeRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleForType669); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleForType"


    // $ANTLR start "ruleForType"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:349:1: ruleForType : ( ( rule__ForType__Group__0 ) ) ;
    public final void ruleForType() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:353:2: ( ( ( rule__ForType__Group__0 ) ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:354:1: ( ( rule__ForType__Group__0 ) )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:354:1: ( ( rule__ForType__Group__0 ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:355:1: ( rule__ForType__Group__0 )
            {
             before(grammarAccess.getForTypeAccess().getGroup()); 
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:356:1: ( rule__ForType__Group__0 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:356:2: rule__ForType__Group__0
            {
            pushFollow(FOLLOW_rule__ForType__Group__0_in_ruleForType695);
            rule__ForType__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getForTypeAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleForType"


    // $ANTLR start "entryRuleforVarDeclaration"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:368:1: entryRuleforVarDeclaration : ruleforVarDeclaration EOF ;
    public final void entryRuleforVarDeclaration() throws RecognitionException {
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:369:1: ( ruleforVarDeclaration EOF )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:370:1: ruleforVarDeclaration EOF
            {
             before(grammarAccess.getForVarDeclarationRule()); 
            pushFollow(FOLLOW_ruleforVarDeclaration_in_entryRuleforVarDeclaration722);
            ruleforVarDeclaration();

            state._fsp--;

             after(grammarAccess.getForVarDeclarationRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleforVarDeclaration729); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleforVarDeclaration"


    // $ANTLR start "ruleforVarDeclaration"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:377:1: ruleforVarDeclaration : ( ( rule__ForVarDeclaration__NameAssignment ) ) ;
    public final void ruleforVarDeclaration() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:381:2: ( ( ( rule__ForVarDeclaration__NameAssignment ) ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:382:1: ( ( rule__ForVarDeclaration__NameAssignment ) )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:382:1: ( ( rule__ForVarDeclaration__NameAssignment ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:383:1: ( rule__ForVarDeclaration__NameAssignment )
            {
             before(grammarAccess.getForVarDeclarationAccess().getNameAssignment()); 
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:384:1: ( rule__ForVarDeclaration__NameAssignment )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:384:2: rule__ForVarDeclaration__NameAssignment
            {
            pushFollow(FOLLOW_rule__ForVarDeclaration__NameAssignment_in_ruleforVarDeclaration755);
            rule__ForVarDeclaration__NameAssignment();

            state._fsp--;


            }

             after(grammarAccess.getForVarDeclarationAccess().getNameAssignment()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleforVarDeclaration"


    // $ANTLR start "entryRuleForeachType"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:396:1: entryRuleForeachType : ruleForeachType EOF ;
    public final void entryRuleForeachType() throws RecognitionException {
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:397:1: ( ruleForeachType EOF )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:398:1: ruleForeachType EOF
            {
             before(grammarAccess.getForeachTypeRule()); 
            pushFollow(FOLLOW_ruleForeachType_in_entryRuleForeachType782);
            ruleForeachType();

            state._fsp--;

             after(grammarAccess.getForeachTypeRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleForeachType789); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleForeachType"


    // $ANTLR start "ruleForeachType"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:405:1: ruleForeachType : ( ( rule__ForeachType__Group__0 ) ) ;
    public final void ruleForeachType() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:409:2: ( ( ( rule__ForeachType__Group__0 ) ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:410:1: ( ( rule__ForeachType__Group__0 ) )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:410:1: ( ( rule__ForeachType__Group__0 ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:411:1: ( rule__ForeachType__Group__0 )
            {
             before(grammarAccess.getForeachTypeAccess().getGroup()); 
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:412:1: ( rule__ForeachType__Group__0 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:412:2: rule__ForeachType__Group__0
            {
            pushFollow(FOLLOW_rule__ForeachType__Group__0_in_ruleForeachType815);
            rule__ForeachType__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getForeachTypeAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleForeachType"


    // $ANTLR start "entryRuleSwitchType"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:424:1: entryRuleSwitchType : ruleSwitchType EOF ;
    public final void entryRuleSwitchType() throws RecognitionException {
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:425:1: ( ruleSwitchType EOF )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:426:1: ruleSwitchType EOF
            {
             before(grammarAccess.getSwitchTypeRule()); 
            pushFollow(FOLLOW_ruleSwitchType_in_entryRuleSwitchType842);
            ruleSwitchType();

            state._fsp--;

             after(grammarAccess.getSwitchTypeRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleSwitchType849); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleSwitchType"


    // $ANTLR start "ruleSwitchType"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:433:1: ruleSwitchType : ( ( rule__SwitchType__Group__0 ) ) ;
    public final void ruleSwitchType() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:437:2: ( ( ( rule__SwitchType__Group__0 ) ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:438:1: ( ( rule__SwitchType__Group__0 ) )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:438:1: ( ( rule__SwitchType__Group__0 ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:439:1: ( rule__SwitchType__Group__0 )
            {
             before(grammarAccess.getSwitchTypeAccess().getGroup()); 
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:440:1: ( rule__SwitchType__Group__0 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:440:2: rule__SwitchType__Group__0
            {
            pushFollow(FOLLOW_rule__SwitchType__Group__0_in_ruleSwitchType875);
            rule__SwitchType__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getSwitchTypeAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleSwitchType"


    // $ANTLR start "entryRuleMethod"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:452:1: entryRuleMethod : ruleMethod EOF ;
    public final void entryRuleMethod() throws RecognitionException {
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:453:1: ( ruleMethod EOF )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:454:1: ruleMethod EOF
            {
             before(grammarAccess.getMethodRule()); 
            pushFollow(FOLLOW_ruleMethod_in_entryRuleMethod902);
            ruleMethod();

            state._fsp--;

             after(grammarAccess.getMethodRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleMethod909); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleMethod"


    // $ANTLR start "ruleMethod"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:461:1: ruleMethod : ( ( rule__Method__Group__0 ) ) ;
    public final void ruleMethod() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:465:2: ( ( ( rule__Method__Group__0 ) ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:466:1: ( ( rule__Method__Group__0 ) )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:466:1: ( ( rule__Method__Group__0 ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:467:1: ( rule__Method__Group__0 )
            {
             before(grammarAccess.getMethodAccess().getGroup()); 
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:468:1: ( rule__Method__Group__0 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:468:2: rule__Method__Group__0
            {
            pushFollow(FOLLOW_rule__Method__Group__0_in_ruleMethod935);
            rule__Method__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getMethodAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleMethod"


    // $ANTLR start "entryRuleOPERATOR"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:482:1: entryRuleOPERATOR : ruleOPERATOR EOF ;
    public final void entryRuleOPERATOR() throws RecognitionException {
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:483:1: ( ruleOPERATOR EOF )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:484:1: ruleOPERATOR EOF
            {
             before(grammarAccess.getOPERATORRule()); 
            pushFollow(FOLLOW_ruleOPERATOR_in_entryRuleOPERATOR964);
            ruleOPERATOR();

            state._fsp--;

             after(grammarAccess.getOPERATORRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOPERATOR971); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleOPERATOR"


    // $ANTLR start "ruleOPERATOR"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:491:1: ruleOPERATOR : ( ( rule__OPERATOR__Alternatives ) ) ;
    public final void ruleOPERATOR() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:495:2: ( ( ( rule__OPERATOR__Alternatives ) ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:496:1: ( ( rule__OPERATOR__Alternatives ) )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:496:1: ( ( rule__OPERATOR__Alternatives ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:497:1: ( rule__OPERATOR__Alternatives )
            {
             before(grammarAccess.getOPERATORAccess().getAlternatives()); 
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:498:1: ( rule__OPERATOR__Alternatives )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:498:2: rule__OPERATOR__Alternatives
            {
            pushFollow(FOLLOW_rule__OPERATOR__Alternatives_in_ruleOPERATOR997);
            rule__OPERATOR__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getOPERATORAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleOPERATOR"


    // $ANTLR start "entryRuleANYTHING"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:512:1: entryRuleANYTHING : ruleANYTHING EOF ;
    public final void entryRuleANYTHING() throws RecognitionException {
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:513:1: ( ruleANYTHING EOF )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:514:1: ruleANYTHING EOF
            {
             before(grammarAccess.getANYTHINGRule()); 
            pushFollow(FOLLOW_ruleANYTHING_in_entryRuleANYTHING1026);
            ruleANYTHING();

            state._fsp--;

             after(grammarAccess.getANYTHINGRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleANYTHING1033); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleANYTHING"


    // $ANTLR start "ruleANYTHING"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:521:1: ruleANYTHING : ( ( rule__ANYTHING__Alternatives ) ) ;
    public final void ruleANYTHING() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:525:2: ( ( ( rule__ANYTHING__Alternatives ) ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:526:1: ( ( rule__ANYTHING__Alternatives ) )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:526:1: ( ( rule__ANYTHING__Alternatives ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:527:1: ( rule__ANYTHING__Alternatives )
            {
             before(grammarAccess.getANYTHINGAccess().getAlternatives()); 
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:528:1: ( rule__ANYTHING__Alternatives )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:528:2: rule__ANYTHING__Alternatives
            {
            pushFollow(FOLLOW_rule__ANYTHING__Alternatives_in_ruleANYTHING1059);
            rule__ANYTHING__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getANYTHINGAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleANYTHING"


    // $ANTLR start "entryRuleBoolean"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:540:1: entryRuleBoolean : ruleBoolean EOF ;
    public final void entryRuleBoolean() throws RecognitionException {
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:541:1: ( ruleBoolean EOF )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:542:1: ruleBoolean EOF
            {
             before(grammarAccess.getBooleanRule()); 
            pushFollow(FOLLOW_ruleBoolean_in_entryRuleBoolean1086);
            ruleBoolean();

            state._fsp--;

             after(grammarAccess.getBooleanRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleBoolean1093); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleBoolean"


    // $ANTLR start "ruleBoolean"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:549:1: ruleBoolean : ( ( rule__Boolean__Alternatives ) ) ;
    public final void ruleBoolean() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:553:2: ( ( ( rule__Boolean__Alternatives ) ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:554:1: ( ( rule__Boolean__Alternatives ) )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:554:1: ( ( rule__Boolean__Alternatives ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:555:1: ( rule__Boolean__Alternatives )
            {
             before(grammarAccess.getBooleanAccess().getAlternatives()); 
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:556:1: ( rule__Boolean__Alternatives )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:556:2: rule__Boolean__Alternatives
            {
            pushFollow(FOLLOW_rule__Boolean__Alternatives_in_ruleBoolean1119);
            rule__Boolean__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getBooleanAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleBoolean"


    // $ANTLR start "entryRuleBooleanContent"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:568:1: entryRuleBooleanContent : ruleBooleanContent EOF ;
    public final void entryRuleBooleanContent() throws RecognitionException {
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:569:1: ( ruleBooleanContent EOF )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:570:1: ruleBooleanContent EOF
            {
             before(grammarAccess.getBooleanContentRule()); 
            pushFollow(FOLLOW_ruleBooleanContent_in_entryRuleBooleanContent1146);
            ruleBooleanContent();

            state._fsp--;

             after(grammarAccess.getBooleanContentRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleBooleanContent1153); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleBooleanContent"


    // $ANTLR start "ruleBooleanContent"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:577:1: ruleBooleanContent : ( ( rule__BooleanContent__BoolConAssignment ) ) ;
    public final void ruleBooleanContent() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:581:2: ( ( ( rule__BooleanContent__BoolConAssignment ) ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:582:1: ( ( rule__BooleanContent__BoolConAssignment ) )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:582:1: ( ( rule__BooleanContent__BoolConAssignment ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:583:1: ( rule__BooleanContent__BoolConAssignment )
            {
             before(grammarAccess.getBooleanContentAccess().getBoolConAssignment()); 
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:584:1: ( rule__BooleanContent__BoolConAssignment )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:584:2: rule__BooleanContent__BoolConAssignment
            {
            pushFollow(FOLLOW_rule__BooleanContent__BoolConAssignment_in_ruleBooleanContent1179);
            rule__BooleanContent__BoolConAssignment();

            state._fsp--;


            }

             after(grammarAccess.getBooleanContentAccess().getBoolConAssignment()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleBooleanContent"


    // $ANTLR start "entryRuleMethodName"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:596:1: entryRuleMethodName : ruleMethodName EOF ;
    public final void entryRuleMethodName() throws RecognitionException {
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:597:1: ( ruleMethodName EOF )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:598:1: ruleMethodName EOF
            {
             before(grammarAccess.getMethodNameRule()); 
            pushFollow(FOLLOW_ruleMethodName_in_entryRuleMethodName1206);
            ruleMethodName();

            state._fsp--;

             after(grammarAccess.getMethodNameRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleMethodName1213); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleMethodName"


    // $ANTLR start "ruleMethodName"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:605:1: ruleMethodName : ( ( rule__MethodName__RefAssignment ) ) ;
    public final void ruleMethodName() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:609:2: ( ( ( rule__MethodName__RefAssignment ) ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:610:1: ( ( rule__MethodName__RefAssignment ) )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:610:1: ( ( rule__MethodName__RefAssignment ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:611:1: ( rule__MethodName__RefAssignment )
            {
             before(grammarAccess.getMethodNameAccess().getRefAssignment()); 
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:612:1: ( rule__MethodName__RefAssignment )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:612:2: rule__MethodName__RefAssignment
            {
            pushFollow(FOLLOW_rule__MethodName__RefAssignment_in_ruleMethodName1239);
            rule__MethodName__RefAssignment();

            state._fsp--;


            }

             after(grammarAccess.getMethodNameAccess().getRefAssignment()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleMethodName"


    // $ANTLR start "entryRuleBoolCommand"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:624:1: entryRuleBoolCommand : ruleBoolCommand EOF ;
    public final void entryRuleBoolCommand() throws RecognitionException {
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:625:1: ( ruleBoolCommand EOF )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:626:1: ruleBoolCommand EOF
            {
             before(grammarAccess.getBoolCommandRule()); 
            pushFollow(FOLLOW_ruleBoolCommand_in_entryRuleBoolCommand1266);
            ruleBoolCommand();

            state._fsp--;

             after(grammarAccess.getBoolCommandRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleBoolCommand1273); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleBoolCommand"


    // $ANTLR start "ruleBoolCommand"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:633:1: ruleBoolCommand : ( ( rule__BoolCommand__Alternatives ) ) ;
    public final void ruleBoolCommand() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:637:2: ( ( ( rule__BoolCommand__Alternatives ) ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:638:1: ( ( rule__BoolCommand__Alternatives ) )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:638:1: ( ( rule__BoolCommand__Alternatives ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:639:1: ( rule__BoolCommand__Alternatives )
            {
             before(grammarAccess.getBoolCommandAccess().getAlternatives()); 
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:640:1: ( rule__BoolCommand__Alternatives )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:640:2: rule__BoolCommand__Alternatives
            {
            pushFollow(FOLLOW_rule__BoolCommand__Alternatives_in_ruleBoolCommand1299);
            rule__BoolCommand__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getBoolCommandAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleBoolCommand"


    // $ANTLR start "rule__Code__Alternatives"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:652:1: rule__Code__Alternatives : ( ( ( rule__Code__Group_0__0 ) ) | ( ( rule__Code__Group_1__0 ) ) | ( ( rule__Code__Group_2__0 ) ) );
    public final void rule__Code__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:656:1: ( ( ( rule__Code__Group_0__0 ) ) | ( ( rule__Code__Group_1__0 ) ) | ( ( rule__Code__Group_2__0 ) ) )
            int alt2=3;
            switch ( input.LA(1) ) {
            case RULE_ID:
                {
                alt2=1;
                }
                break;
            case 29:
            case 32:
            case 37:
            case 39:
            case 44:
                {
                alt2=2;
                }
                break;
            case 35:
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
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:657:1: ( ( rule__Code__Group_0__0 ) )
                    {
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:657:1: ( ( rule__Code__Group_0__0 ) )
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:658:1: ( rule__Code__Group_0__0 )
                    {
                     before(grammarAccess.getCodeAccess().getGroup_0()); 
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:659:1: ( rule__Code__Group_0__0 )
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:659:2: rule__Code__Group_0__0
                    {
                    pushFollow(FOLLOW_rule__Code__Group_0__0_in_rule__Code__Alternatives1335);
                    rule__Code__Group_0__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getCodeAccess().getGroup_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:663:6: ( ( rule__Code__Group_1__0 ) )
                    {
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:663:6: ( ( rule__Code__Group_1__0 ) )
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:664:1: ( rule__Code__Group_1__0 )
                    {
                     before(grammarAccess.getCodeAccess().getGroup_1()); 
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:665:1: ( rule__Code__Group_1__0 )
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:665:2: rule__Code__Group_1__0
                    {
                    pushFollow(FOLLOW_rule__Code__Group_1__0_in_rule__Code__Alternatives1353);
                    rule__Code__Group_1__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getCodeAccess().getGroup_1()); 

                    }


                    }
                    break;
                case 3 :
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:669:6: ( ( rule__Code__Group_2__0 ) )
                    {
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:669:6: ( ( rule__Code__Group_2__0 ) )
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:670:1: ( rule__Code__Group_2__0 )
                    {
                     before(grammarAccess.getCodeAccess().getGroup_2()); 
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:671:1: ( rule__Code__Group_2__0 )
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:671:2: rule__Code__Group_2__0
                    {
                    pushFollow(FOLLOW_rule__Code__Group_2__0_in_rule__Code__Alternatives1371);
                    rule__Code__Group_2__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getCodeAccess().getGroup_2()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Code__Alternatives"


    // $ANTLR start "rule__DecContent__NegAlternatives_0_0"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:680:1: rule__DecContent__NegAlternatives_0_0 : ( ( '!' ) | ( 'not' ) );
    public final void rule__DecContent__NegAlternatives_0_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:684:1: ( ( '!' ) | ( 'not' ) )
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==11) ) {
                alt3=1;
            }
            else if ( (LA3_0==12) ) {
                alt3=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;
            }
            switch (alt3) {
                case 1 :
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:685:1: ( '!' )
                    {
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:685:1: ( '!' )
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:686:1: '!'
                    {
                     before(grammarAccess.getDecContentAccess().getNegExclamationMarkKeyword_0_0_0()); 
                    match(input,11,FOLLOW_11_in_rule__DecContent__NegAlternatives_0_01405); 
                     after(grammarAccess.getDecContentAccess().getNegExclamationMarkKeyword_0_0_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:693:6: ( 'not' )
                    {
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:693:6: ( 'not' )
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:694:1: 'not'
                    {
                     before(grammarAccess.getDecContentAccess().getNegNotKeyword_0_0_1()); 
                    match(input,12,FOLLOW_12_in_rule__DecContent__NegAlternatives_0_01425); 
                     after(grammarAccess.getDecContentAccess().getNegNotKeyword_0_0_1()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__DecContent__NegAlternatives_0_0"


    // $ANTLR start "rule__VarContent__Alternatives"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:706:1: rule__VarContent__Alternatives : ( ( ( rule__VarContent__Group_0__0 ) ) | ( ( rule__VarContent__StringAssignment_1 ) ) | ( ( rule__VarContent__Group_2__0 ) ) | ( ( rule__VarContent__Group_3__0 ) ) | ( ( rule__VarContent__Group_4__0 ) ) | ( ( rule__VarContent__BoolAssignment_5 ) ) | ( ( rule__VarContent__Group_6__0 ) ) );
    public final void rule__VarContent__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:710:1: ( ( ( rule__VarContent__Group_0__0 ) ) | ( ( rule__VarContent__StringAssignment_1 ) ) | ( ( rule__VarContent__Group_2__0 ) ) | ( ( rule__VarContent__Group_3__0 ) ) | ( ( rule__VarContent__Group_4__0 ) ) | ( ( rule__VarContent__BoolAssignment_5 ) ) | ( ( rule__VarContent__Group_6__0 ) ) )
            int alt4=7;
            alt4 = dfa4.predict(input);
            switch (alt4) {
                case 1 :
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:711:1: ( ( rule__VarContent__Group_0__0 ) )
                    {
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:711:1: ( ( rule__VarContent__Group_0__0 ) )
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:712:1: ( rule__VarContent__Group_0__0 )
                    {
                     before(grammarAccess.getVarContentAccess().getGroup_0()); 
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:713:1: ( rule__VarContent__Group_0__0 )
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:713:2: rule__VarContent__Group_0__0
                    {
                    pushFollow(FOLLOW_rule__VarContent__Group_0__0_in_rule__VarContent__Alternatives1459);
                    rule__VarContent__Group_0__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getVarContentAccess().getGroup_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:717:6: ( ( rule__VarContent__StringAssignment_1 ) )
                    {
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:717:6: ( ( rule__VarContent__StringAssignment_1 ) )
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:718:1: ( rule__VarContent__StringAssignment_1 )
                    {
                     before(grammarAccess.getVarContentAccess().getStringAssignment_1()); 
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:719:1: ( rule__VarContent__StringAssignment_1 )
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:719:2: rule__VarContent__StringAssignment_1
                    {
                    pushFollow(FOLLOW_rule__VarContent__StringAssignment_1_in_rule__VarContent__Alternatives1477);
                    rule__VarContent__StringAssignment_1();

                    state._fsp--;


                    }

                     after(grammarAccess.getVarContentAccess().getStringAssignment_1()); 

                    }


                    }
                    break;
                case 3 :
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:723:6: ( ( rule__VarContent__Group_2__0 ) )
                    {
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:723:6: ( ( rule__VarContent__Group_2__0 ) )
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:724:1: ( rule__VarContent__Group_2__0 )
                    {
                     before(grammarAccess.getVarContentAccess().getGroup_2()); 
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:725:1: ( rule__VarContent__Group_2__0 )
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:725:2: rule__VarContent__Group_2__0
                    {
                    pushFollow(FOLLOW_rule__VarContent__Group_2__0_in_rule__VarContent__Alternatives1495);
                    rule__VarContent__Group_2__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getVarContentAccess().getGroup_2()); 

                    }


                    }
                    break;
                case 4 :
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:729:6: ( ( rule__VarContent__Group_3__0 ) )
                    {
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:729:6: ( ( rule__VarContent__Group_3__0 ) )
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:730:1: ( rule__VarContent__Group_3__0 )
                    {
                     before(grammarAccess.getVarContentAccess().getGroup_3()); 
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:731:1: ( rule__VarContent__Group_3__0 )
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:731:2: rule__VarContent__Group_3__0
                    {
                    pushFollow(FOLLOW_rule__VarContent__Group_3__0_in_rule__VarContent__Alternatives1513);
                    rule__VarContent__Group_3__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getVarContentAccess().getGroup_3()); 

                    }


                    }
                    break;
                case 5 :
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:735:6: ( ( rule__VarContent__Group_4__0 ) )
                    {
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:735:6: ( ( rule__VarContent__Group_4__0 ) )
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:736:1: ( rule__VarContent__Group_4__0 )
                    {
                     before(grammarAccess.getVarContentAccess().getGroup_4()); 
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:737:1: ( rule__VarContent__Group_4__0 )
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:737:2: rule__VarContent__Group_4__0
                    {
                    pushFollow(FOLLOW_rule__VarContent__Group_4__0_in_rule__VarContent__Alternatives1531);
                    rule__VarContent__Group_4__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getVarContentAccess().getGroup_4()); 

                    }


                    }
                    break;
                case 6 :
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:741:6: ( ( rule__VarContent__BoolAssignment_5 ) )
                    {
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:741:6: ( ( rule__VarContent__BoolAssignment_5 ) )
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:742:1: ( rule__VarContent__BoolAssignment_5 )
                    {
                     before(grammarAccess.getVarContentAccess().getBoolAssignment_5()); 
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:743:1: ( rule__VarContent__BoolAssignment_5 )
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:743:2: rule__VarContent__BoolAssignment_5
                    {
                    pushFollow(FOLLOW_rule__VarContent__BoolAssignment_5_in_rule__VarContent__Alternatives1549);
                    rule__VarContent__BoolAssignment_5();

                    state._fsp--;


                    }

                     after(grammarAccess.getVarContentAccess().getBoolAssignment_5()); 

                    }


                    }
                    break;
                case 7 :
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:747:6: ( ( rule__VarContent__Group_6__0 ) )
                    {
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:747:6: ( ( rule__VarContent__Group_6__0 ) )
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:748:1: ( rule__VarContent__Group_6__0 )
                    {
                     before(grammarAccess.getVarContentAccess().getGroup_6()); 
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:749:1: ( rule__VarContent__Group_6__0 )
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:749:2: rule__VarContent__Group_6__0
                    {
                    pushFollow(FOLLOW_rule__VarContent__Group_6__0_in_rule__VarContent__Alternatives1567);
                    rule__VarContent__Group_6__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getVarContentAccess().getGroup_6()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarContent__Alternatives"


    // $ANTLR start "rule__VarContent__UnOPAlternatives_0_0_0"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:758:1: rule__VarContent__UnOPAlternatives_0_0_0 : ( ( '+' ) | ( '-' ) );
    public final void rule__VarContent__UnOPAlternatives_0_0_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:762:1: ( ( '+' ) | ( '-' ) )
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==13) ) {
                alt5=1;
            }
            else if ( (LA5_0==14) ) {
                alt5=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;
            }
            switch (alt5) {
                case 1 :
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:763:1: ( '+' )
                    {
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:763:1: ( '+' )
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:764:1: '+'
                    {
                     before(grammarAccess.getVarContentAccess().getUnOPPlusSignKeyword_0_0_0_0()); 
                    match(input,13,FOLLOW_13_in_rule__VarContent__UnOPAlternatives_0_0_01601); 
                     after(grammarAccess.getVarContentAccess().getUnOPPlusSignKeyword_0_0_0_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:771:6: ( '-' )
                    {
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:771:6: ( '-' )
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:772:1: '-'
                    {
                     before(grammarAccess.getVarContentAccess().getUnOPHyphenMinusKeyword_0_0_0_1()); 
                    match(input,14,FOLLOW_14_in_rule__VarContent__UnOPAlternatives_0_0_01621); 
                     after(grammarAccess.getVarContentAccess().getUnOPHyphenMinusKeyword_0_0_0_1()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarContent__UnOPAlternatives_0_0_0"


    // $ANTLR start "rule__VarContent__UnOPAlternatives_2_0_0"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:784:1: rule__VarContent__UnOPAlternatives_2_0_0 : ( ( '+' ) | ( '-' ) );
    public final void rule__VarContent__UnOPAlternatives_2_0_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:788:1: ( ( '+' ) | ( '-' ) )
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==13) ) {
                alt6=1;
            }
            else if ( (LA6_0==14) ) {
                alt6=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;
            }
            switch (alt6) {
                case 1 :
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:789:1: ( '+' )
                    {
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:789:1: ( '+' )
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:790:1: '+'
                    {
                     before(grammarAccess.getVarContentAccess().getUnOPPlusSignKeyword_2_0_0_0()); 
                    match(input,13,FOLLOW_13_in_rule__VarContent__UnOPAlternatives_2_0_01656); 
                     after(grammarAccess.getVarContentAccess().getUnOPPlusSignKeyword_2_0_0_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:797:6: ( '-' )
                    {
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:797:6: ( '-' )
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:798:1: '-'
                    {
                     before(grammarAccess.getVarContentAccess().getUnOPHyphenMinusKeyword_2_0_0_1()); 
                    match(input,14,FOLLOW_14_in_rule__VarContent__UnOPAlternatives_2_0_01676); 
                     after(grammarAccess.getVarContentAccess().getUnOPHyphenMinusKeyword_2_0_0_1()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarContent__UnOPAlternatives_2_0_0"


    // $ANTLR start "rule__VarContent__Alternatives_2_1"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:810:1: rule__VarContent__Alternatives_2_1 : ( ( ( rule__VarContent__ReferenceAssignment_2_1_0 ) ) | ( ( rule__VarContent__ForEachVarAssignment_2_1_1 ) ) );
    public final void rule__VarContent__Alternatives_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:814:1: ( ( ( rule__VarContent__ReferenceAssignment_2_1_0 ) ) | ( ( rule__VarContent__ForEachVarAssignment_2_1_1 ) ) )
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==RULE_ID) ) {
                alt7=1;
            }
            else if ( (LA7_0==48) ) {
                alt7=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;
            }
            switch (alt7) {
                case 1 :
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:815:1: ( ( rule__VarContent__ReferenceAssignment_2_1_0 ) )
                    {
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:815:1: ( ( rule__VarContent__ReferenceAssignment_2_1_0 ) )
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:816:1: ( rule__VarContent__ReferenceAssignment_2_1_0 )
                    {
                     before(grammarAccess.getVarContentAccess().getReferenceAssignment_2_1_0()); 
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:817:1: ( rule__VarContent__ReferenceAssignment_2_1_0 )
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:817:2: rule__VarContent__ReferenceAssignment_2_1_0
                    {
                    pushFollow(FOLLOW_rule__VarContent__ReferenceAssignment_2_1_0_in_rule__VarContent__Alternatives_2_11710);
                    rule__VarContent__ReferenceAssignment_2_1_0();

                    state._fsp--;


                    }

                     after(grammarAccess.getVarContentAccess().getReferenceAssignment_2_1_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:821:6: ( ( rule__VarContent__ForEachVarAssignment_2_1_1 ) )
                    {
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:821:6: ( ( rule__VarContent__ForEachVarAssignment_2_1_1 ) )
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:822:1: ( rule__VarContent__ForEachVarAssignment_2_1_1 )
                    {
                     before(grammarAccess.getVarContentAccess().getForEachVarAssignment_2_1_1()); 
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:823:1: ( rule__VarContent__ForEachVarAssignment_2_1_1 )
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:823:2: rule__VarContent__ForEachVarAssignment_2_1_1
                    {
                    pushFollow(FOLLOW_rule__VarContent__ForEachVarAssignment_2_1_1_in_rule__VarContent__Alternatives_2_11728);
                    rule__VarContent__ForEachVarAssignment_2_1_1();

                    state._fsp--;


                    }

                     after(grammarAccess.getVarContentAccess().getForEachVarAssignment_2_1_1()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarContent__Alternatives_2_1"


    // $ANTLR start "rule__VarContent__UnOPAlternatives_4_0_0"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:832:1: rule__VarContent__UnOPAlternatives_4_0_0 : ( ( '+' ) | ( '-' ) );
    public final void rule__VarContent__UnOPAlternatives_4_0_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:836:1: ( ( '+' ) | ( '-' ) )
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==13) ) {
                alt8=1;
            }
            else if ( (LA8_0==14) ) {
                alt8=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;
            }
            switch (alt8) {
                case 1 :
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:837:1: ( '+' )
                    {
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:837:1: ( '+' )
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:838:1: '+'
                    {
                     before(grammarAccess.getVarContentAccess().getUnOPPlusSignKeyword_4_0_0_0()); 
                    match(input,13,FOLLOW_13_in_rule__VarContent__UnOPAlternatives_4_0_01762); 
                     after(grammarAccess.getVarContentAccess().getUnOPPlusSignKeyword_4_0_0_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:845:6: ( '-' )
                    {
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:845:6: ( '-' )
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:846:1: '-'
                    {
                     before(grammarAccess.getVarContentAccess().getUnOPHyphenMinusKeyword_4_0_0_1()); 
                    match(input,14,FOLLOW_14_in_rule__VarContent__UnOPAlternatives_4_0_01782); 
                     after(grammarAccess.getVarContentAccess().getUnOPHyphenMinusKeyword_4_0_0_1()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarContent__UnOPAlternatives_4_0_0"


    // $ANTLR start "rule__ControlStructure__Alternatives"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:858:1: rule__ControlStructure__Alternatives : ( ( ( rule__ControlStructure__IfStatAssignment_0 ) ) | ( ( rule__ControlStructure__WhileStatAssignment_1 ) ) | ( ( rule__ControlStructure__ForStatAssignment_2 ) ) | ( ( rule__ControlStructure__ForEachStatAssignment_3 ) ) | ( ( rule__ControlStructure__SwitchStatAssignment_4 ) ) );
    public final void rule__ControlStructure__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:862:1: ( ( ( rule__ControlStructure__IfStatAssignment_0 ) ) | ( ( rule__ControlStructure__WhileStatAssignment_1 ) ) | ( ( rule__ControlStructure__ForStatAssignment_2 ) ) | ( ( rule__ControlStructure__ForEachStatAssignment_3 ) ) | ( ( rule__ControlStructure__SwitchStatAssignment_4 ) ) )
            int alt9=5;
            switch ( input.LA(1) ) {
            case 29:
                {
                alt9=1;
                }
                break;
            case 37:
                {
                alt9=2;
                }
                break;
            case 39:
                {
                alt9=3;
                }
                break;
            case 32:
                {
                alt9=4;
                }
                break;
            case 44:
                {
                alt9=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 9, 0, input);

                throw nvae;
            }

            switch (alt9) {
                case 1 :
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:863:1: ( ( rule__ControlStructure__IfStatAssignment_0 ) )
                    {
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:863:1: ( ( rule__ControlStructure__IfStatAssignment_0 ) )
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:864:1: ( rule__ControlStructure__IfStatAssignment_0 )
                    {
                     before(grammarAccess.getControlStructureAccess().getIfStatAssignment_0()); 
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:865:1: ( rule__ControlStructure__IfStatAssignment_0 )
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:865:2: rule__ControlStructure__IfStatAssignment_0
                    {
                    pushFollow(FOLLOW_rule__ControlStructure__IfStatAssignment_0_in_rule__ControlStructure__Alternatives1816);
                    rule__ControlStructure__IfStatAssignment_0();

                    state._fsp--;


                    }

                     after(grammarAccess.getControlStructureAccess().getIfStatAssignment_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:869:6: ( ( rule__ControlStructure__WhileStatAssignment_1 ) )
                    {
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:869:6: ( ( rule__ControlStructure__WhileStatAssignment_1 ) )
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:870:1: ( rule__ControlStructure__WhileStatAssignment_1 )
                    {
                     before(grammarAccess.getControlStructureAccess().getWhileStatAssignment_1()); 
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:871:1: ( rule__ControlStructure__WhileStatAssignment_1 )
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:871:2: rule__ControlStructure__WhileStatAssignment_1
                    {
                    pushFollow(FOLLOW_rule__ControlStructure__WhileStatAssignment_1_in_rule__ControlStructure__Alternatives1834);
                    rule__ControlStructure__WhileStatAssignment_1();

                    state._fsp--;


                    }

                     after(grammarAccess.getControlStructureAccess().getWhileStatAssignment_1()); 

                    }


                    }
                    break;
                case 3 :
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:875:6: ( ( rule__ControlStructure__ForStatAssignment_2 ) )
                    {
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:875:6: ( ( rule__ControlStructure__ForStatAssignment_2 ) )
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:876:1: ( rule__ControlStructure__ForStatAssignment_2 )
                    {
                     before(grammarAccess.getControlStructureAccess().getForStatAssignment_2()); 
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:877:1: ( rule__ControlStructure__ForStatAssignment_2 )
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:877:2: rule__ControlStructure__ForStatAssignment_2
                    {
                    pushFollow(FOLLOW_rule__ControlStructure__ForStatAssignment_2_in_rule__ControlStructure__Alternatives1852);
                    rule__ControlStructure__ForStatAssignment_2();

                    state._fsp--;


                    }

                     after(grammarAccess.getControlStructureAccess().getForStatAssignment_2()); 

                    }


                    }
                    break;
                case 4 :
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:881:6: ( ( rule__ControlStructure__ForEachStatAssignment_3 ) )
                    {
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:881:6: ( ( rule__ControlStructure__ForEachStatAssignment_3 ) )
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:882:1: ( rule__ControlStructure__ForEachStatAssignment_3 )
                    {
                     before(grammarAccess.getControlStructureAccess().getForEachStatAssignment_3()); 
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:883:1: ( rule__ControlStructure__ForEachStatAssignment_3 )
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:883:2: rule__ControlStructure__ForEachStatAssignment_3
                    {
                    pushFollow(FOLLOW_rule__ControlStructure__ForEachStatAssignment_3_in_rule__ControlStructure__Alternatives1870);
                    rule__ControlStructure__ForEachStatAssignment_3();

                    state._fsp--;


                    }

                     after(grammarAccess.getControlStructureAccess().getForEachStatAssignment_3()); 

                    }


                    }
                    break;
                case 5 :
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:887:6: ( ( rule__ControlStructure__SwitchStatAssignment_4 ) )
                    {
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:887:6: ( ( rule__ControlStructure__SwitchStatAssignment_4 ) )
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:888:1: ( rule__ControlStructure__SwitchStatAssignment_4 )
                    {
                     before(grammarAccess.getControlStructureAccess().getSwitchStatAssignment_4()); 
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:889:1: ( rule__ControlStructure__SwitchStatAssignment_4 )
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:889:2: rule__ControlStructure__SwitchStatAssignment_4
                    {
                    pushFollow(FOLLOW_rule__ControlStructure__SwitchStatAssignment_4_in_rule__ControlStructure__Alternatives1888);
                    rule__ControlStructure__SwitchStatAssignment_4();

                    state._fsp--;


                    }

                     after(grammarAccess.getControlStructureAccess().getSwitchStatAssignment_4()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ControlStructure__Alternatives"


    // $ANTLR start "rule__IfType__Alternatives_4"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:898:1: rule__IfType__Alternatives_4 : ( ( ( rule__IfType__Group_4_0__0 ) ) | ( ( rule__IfType__Group_4_1__0 ) ) );
    public final void rule__IfType__Alternatives_4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:902:1: ( ( ( rule__IfType__Group_4_0__0 ) ) | ( ( rule__IfType__Group_4_1__0 ) ) )
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==31) ) {
                alt10=1;
            }
            else if ( (LA10_0==36) ) {
                alt10=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 10, 0, input);

                throw nvae;
            }
            switch (alt10) {
                case 1 :
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:903:1: ( ( rule__IfType__Group_4_0__0 ) )
                    {
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:903:1: ( ( rule__IfType__Group_4_0__0 ) )
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:904:1: ( rule__IfType__Group_4_0__0 )
                    {
                     before(grammarAccess.getIfTypeAccess().getGroup_4_0()); 
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:905:1: ( rule__IfType__Group_4_0__0 )
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:905:2: rule__IfType__Group_4_0__0
                    {
                    pushFollow(FOLLOW_rule__IfType__Group_4_0__0_in_rule__IfType__Alternatives_41921);
                    rule__IfType__Group_4_0__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getIfTypeAccess().getGroup_4_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:909:6: ( ( rule__IfType__Group_4_1__0 ) )
                    {
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:909:6: ( ( rule__IfType__Group_4_1__0 ) )
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:910:1: ( rule__IfType__Group_4_1__0 )
                    {
                     before(grammarAccess.getIfTypeAccess().getGroup_4_1()); 
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:911:1: ( rule__IfType__Group_4_1__0 )
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:911:2: rule__IfType__Group_4_1__0
                    {
                    pushFollow(FOLLOW_rule__IfType__Group_4_1__0_in_rule__IfType__Alternatives_41939);
                    rule__IfType__Group_4_1__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getIfTypeAccess().getGroup_4_1()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfType__Alternatives_4"


    // $ANTLR start "rule__IfType__Alternatives_4_0_1"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:920:1: rule__IfType__Alternatives_4_0_1 : ( ( ( rule__IfType__Group_4_0_1_0__0 ) ) | ( ( rule__IfType__Group_4_0_1_1__0 ) ) );
    public final void rule__IfType__Alternatives_4_0_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:924:1: ( ( ( rule__IfType__Group_4_0_1_0__0 ) ) | ( ( rule__IfType__Group_4_0_1_1__0 ) ) )
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==32) ) {
                alt11=1;
            }
            else if ( (LA11_0==35) ) {
                alt11=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 11, 0, input);

                throw nvae;
            }
            switch (alt11) {
                case 1 :
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:925:1: ( ( rule__IfType__Group_4_0_1_0__0 ) )
                    {
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:925:1: ( ( rule__IfType__Group_4_0_1_0__0 ) )
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:926:1: ( rule__IfType__Group_4_0_1_0__0 )
                    {
                     before(grammarAccess.getIfTypeAccess().getGroup_4_0_1_0()); 
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:927:1: ( rule__IfType__Group_4_0_1_0__0 )
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:927:2: rule__IfType__Group_4_0_1_0__0
                    {
                    pushFollow(FOLLOW_rule__IfType__Group_4_0_1_0__0_in_rule__IfType__Alternatives_4_0_11972);
                    rule__IfType__Group_4_0_1_0__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getIfTypeAccess().getGroup_4_0_1_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:931:6: ( ( rule__IfType__Group_4_0_1_1__0 ) )
                    {
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:931:6: ( ( rule__IfType__Group_4_0_1_1__0 ) )
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:932:1: ( rule__IfType__Group_4_0_1_1__0 )
                    {
                     before(grammarAccess.getIfTypeAccess().getGroup_4_0_1_1()); 
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:933:1: ( rule__IfType__Group_4_0_1_1__0 )
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:933:2: rule__IfType__Group_4_0_1_1__0
                    {
                    pushFollow(FOLLOW_rule__IfType__Group_4_0_1_1__0_in_rule__IfType__Alternatives_4_0_11990);
                    rule__IfType__Group_4_0_1_1__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getIfTypeAccess().getGroup_4_0_1_1()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfType__Alternatives_4_0_1"


    // $ANTLR start "rule__ForType__Alternatives_1"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:942:1: rule__ForType__Alternatives_1 : ( ( ( rule__ForType__Group_1_0__0 ) ) | ( ( rule__ForType__Group_1_1__0 ) ) );
    public final void rule__ForType__Alternatives_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:946:1: ( ( ( rule__ForType__Group_1_0__0 ) ) | ( ( rule__ForType__Group_1_1__0 ) ) )
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==35) ) {
                alt12=1;
            }
            else if ( (LA12_0==RULE_STRING) ) {
                alt12=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 12, 0, input);

                throw nvae;
            }
            switch (alt12) {
                case 1 :
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:947:1: ( ( rule__ForType__Group_1_0__0 ) )
                    {
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:947:1: ( ( rule__ForType__Group_1_0__0 ) )
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:948:1: ( rule__ForType__Group_1_0__0 )
                    {
                     before(grammarAccess.getForTypeAccess().getGroup_1_0()); 
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:949:1: ( rule__ForType__Group_1_0__0 )
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:949:2: rule__ForType__Group_1_0__0
                    {
                    pushFollow(FOLLOW_rule__ForType__Group_1_0__0_in_rule__ForType__Alternatives_12023);
                    rule__ForType__Group_1_0__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getForTypeAccess().getGroup_1_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:953:6: ( ( rule__ForType__Group_1_1__0 ) )
                    {
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:953:6: ( ( rule__ForType__Group_1_1__0 ) )
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:954:1: ( rule__ForType__Group_1_1__0 )
                    {
                     before(grammarAccess.getForTypeAccess().getGroup_1_1()); 
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:955:1: ( rule__ForType__Group_1_1__0 )
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:955:2: rule__ForType__Group_1_1__0
                    {
                    pushFollow(FOLLOW_rule__ForType__Group_1_1__0_in_rule__ForType__Alternatives_12041);
                    rule__ForType__Group_1_1__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getForTypeAccess().getGroup_1_1()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ForType__Alternatives_1"


    // $ANTLR start "rule__ForeachType__Alternatives_4"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:964:1: rule__ForeachType__Alternatives_4 : ( ( ( rule__ForeachType__ArrayAssignment_4_0 ) ) | ( ( rule__ForeachType__ArrayLiteralAssignment_4_1 ) ) );
    public final void rule__ForeachType__Alternatives_4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:968:1: ( ( ( rule__ForeachType__ArrayAssignment_4_0 ) ) | ( ( rule__ForeachType__ArrayLiteralAssignment_4_1 ) ) )
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==RULE_ID) ) {
                alt13=1;
            }
            else if ( (LA13_0==35) ) {
                alt13=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 13, 0, input);

                throw nvae;
            }
            switch (alt13) {
                case 1 :
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:969:1: ( ( rule__ForeachType__ArrayAssignment_4_0 ) )
                    {
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:969:1: ( ( rule__ForeachType__ArrayAssignment_4_0 ) )
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:970:1: ( rule__ForeachType__ArrayAssignment_4_0 )
                    {
                     before(grammarAccess.getForeachTypeAccess().getArrayAssignment_4_0()); 
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:971:1: ( rule__ForeachType__ArrayAssignment_4_0 )
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:971:2: rule__ForeachType__ArrayAssignment_4_0
                    {
                    pushFollow(FOLLOW_rule__ForeachType__ArrayAssignment_4_0_in_rule__ForeachType__Alternatives_42074);
                    rule__ForeachType__ArrayAssignment_4_0();

                    state._fsp--;


                    }

                     after(grammarAccess.getForeachTypeAccess().getArrayAssignment_4_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:975:6: ( ( rule__ForeachType__ArrayLiteralAssignment_4_1 ) )
                    {
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:975:6: ( ( rule__ForeachType__ArrayLiteralAssignment_4_1 ) )
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:976:1: ( rule__ForeachType__ArrayLiteralAssignment_4_1 )
                    {
                     before(grammarAccess.getForeachTypeAccess().getArrayLiteralAssignment_4_1()); 
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:977:1: ( rule__ForeachType__ArrayLiteralAssignment_4_1 )
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:977:2: rule__ForeachType__ArrayLiteralAssignment_4_1
                    {
                    pushFollow(FOLLOW_rule__ForeachType__ArrayLiteralAssignment_4_1_in_rule__ForeachType__Alternatives_42092);
                    rule__ForeachType__ArrayLiteralAssignment_4_1();

                    state._fsp--;


                    }

                     after(grammarAccess.getForeachTypeAccess().getArrayLiteralAssignment_4_1()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ForeachType__Alternatives_4"


    // $ANTLR start "rule__OPERATOR__Alternatives"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:987:1: rule__OPERATOR__Alternatives : ( ( '+' ) | ( '-' ) | ( '*' ) | ( '/' ) | ( 'mod' ) | ( '^' ) );
    public final void rule__OPERATOR__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:991:1: ( ( '+' ) | ( '-' ) | ( '*' ) | ( '/' ) | ( 'mod' ) | ( '^' ) )
            int alt14=6;
            switch ( input.LA(1) ) {
            case 13:
                {
                alt14=1;
                }
                break;
            case 14:
                {
                alt14=2;
                }
                break;
            case 15:
                {
                alt14=3;
                }
                break;
            case 16:
                {
                alt14=4;
                }
                break;
            case 17:
                {
                alt14=5;
                }
                break;
            case 18:
                {
                alt14=6;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 14, 0, input);

                throw nvae;
            }

            switch (alt14) {
                case 1 :
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:992:1: ( '+' )
                    {
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:992:1: ( '+' )
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:993:1: '+'
                    {
                     before(grammarAccess.getOPERATORAccess().getPlusSignKeyword_0()); 
                    match(input,13,FOLLOW_13_in_rule__OPERATOR__Alternatives2127); 
                     after(grammarAccess.getOPERATORAccess().getPlusSignKeyword_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1000:6: ( '-' )
                    {
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1000:6: ( '-' )
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1001:1: '-'
                    {
                     before(grammarAccess.getOPERATORAccess().getHyphenMinusKeyword_1()); 
                    match(input,14,FOLLOW_14_in_rule__OPERATOR__Alternatives2147); 
                     after(grammarAccess.getOPERATORAccess().getHyphenMinusKeyword_1()); 

                    }


                    }
                    break;
                case 3 :
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1008:6: ( '*' )
                    {
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1008:6: ( '*' )
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1009:1: '*'
                    {
                     before(grammarAccess.getOPERATORAccess().getAsteriskKeyword_2()); 
                    match(input,15,FOLLOW_15_in_rule__OPERATOR__Alternatives2167); 
                     after(grammarAccess.getOPERATORAccess().getAsteriskKeyword_2()); 

                    }


                    }
                    break;
                case 4 :
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1016:6: ( '/' )
                    {
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1016:6: ( '/' )
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1017:1: '/'
                    {
                     before(grammarAccess.getOPERATORAccess().getSolidusKeyword_3()); 
                    match(input,16,FOLLOW_16_in_rule__OPERATOR__Alternatives2187); 
                     after(grammarAccess.getOPERATORAccess().getSolidusKeyword_3()); 

                    }


                    }
                    break;
                case 5 :
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1024:6: ( 'mod' )
                    {
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1024:6: ( 'mod' )
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1025:1: 'mod'
                    {
                     before(grammarAccess.getOPERATORAccess().getModKeyword_4()); 
                    match(input,17,FOLLOW_17_in_rule__OPERATOR__Alternatives2207); 
                     after(grammarAccess.getOPERATORAccess().getModKeyword_4()); 

                    }


                    }
                    break;
                case 6 :
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1032:6: ( '^' )
                    {
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1032:6: ( '^' )
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1033:1: '^'
                    {
                     before(grammarAccess.getOPERATORAccess().getCircumflexAccentKeyword_5()); 
                    match(input,18,FOLLOW_18_in_rule__OPERATOR__Alternatives2227); 
                     after(grammarAccess.getOPERATORAccess().getCircumflexAccentKeyword_5()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OPERATOR__Alternatives"


    // $ANTLR start "rule__ANYTHING__Alternatives"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1045:1: rule__ANYTHING__Alternatives : ( ( ( rule__ANYTHING__BoolAssignment_0 ) ) | ( ( rule__ANYTHING__NumAssignment_1 ) ) | ( ( rule__ANYTHING__StringAssignment_2 ) ) | ( ( rule__ANYTHING__ReferenceAssignment_3 ) ) );
    public final void rule__ANYTHING__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1049:1: ( ( ( rule__ANYTHING__BoolAssignment_0 ) ) | ( ( rule__ANYTHING__NumAssignment_1 ) ) | ( ( rule__ANYTHING__StringAssignment_2 ) ) | ( ( rule__ANYTHING__ReferenceAssignment_3 ) ) )
            int alt15=4;
            switch ( input.LA(1) ) {
            case 19:
            case 20:
            case 21:
            case 22:
                {
                alt15=1;
                }
                break;
            case RULE_NUMBER:
                {
                alt15=2;
                }
                break;
            case RULE_STRING:
                {
                alt15=3;
                }
                break;
            case RULE_ID:
                {
                alt15=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 15, 0, input);

                throw nvae;
            }

            switch (alt15) {
                case 1 :
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1050:1: ( ( rule__ANYTHING__BoolAssignment_0 ) )
                    {
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1050:1: ( ( rule__ANYTHING__BoolAssignment_0 ) )
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1051:1: ( rule__ANYTHING__BoolAssignment_0 )
                    {
                     before(grammarAccess.getANYTHINGAccess().getBoolAssignment_0()); 
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1052:1: ( rule__ANYTHING__BoolAssignment_0 )
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1052:2: rule__ANYTHING__BoolAssignment_0
                    {
                    pushFollow(FOLLOW_rule__ANYTHING__BoolAssignment_0_in_rule__ANYTHING__Alternatives2261);
                    rule__ANYTHING__BoolAssignment_0();

                    state._fsp--;


                    }

                     after(grammarAccess.getANYTHINGAccess().getBoolAssignment_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1056:6: ( ( rule__ANYTHING__NumAssignment_1 ) )
                    {
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1056:6: ( ( rule__ANYTHING__NumAssignment_1 ) )
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1057:1: ( rule__ANYTHING__NumAssignment_1 )
                    {
                     before(grammarAccess.getANYTHINGAccess().getNumAssignment_1()); 
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1058:1: ( rule__ANYTHING__NumAssignment_1 )
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1058:2: rule__ANYTHING__NumAssignment_1
                    {
                    pushFollow(FOLLOW_rule__ANYTHING__NumAssignment_1_in_rule__ANYTHING__Alternatives2279);
                    rule__ANYTHING__NumAssignment_1();

                    state._fsp--;


                    }

                     after(grammarAccess.getANYTHINGAccess().getNumAssignment_1()); 

                    }


                    }
                    break;
                case 3 :
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1062:6: ( ( rule__ANYTHING__StringAssignment_2 ) )
                    {
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1062:6: ( ( rule__ANYTHING__StringAssignment_2 ) )
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1063:1: ( rule__ANYTHING__StringAssignment_2 )
                    {
                     before(grammarAccess.getANYTHINGAccess().getStringAssignment_2()); 
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1064:1: ( rule__ANYTHING__StringAssignment_2 )
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1064:2: rule__ANYTHING__StringAssignment_2
                    {
                    pushFollow(FOLLOW_rule__ANYTHING__StringAssignment_2_in_rule__ANYTHING__Alternatives2297);
                    rule__ANYTHING__StringAssignment_2();

                    state._fsp--;


                    }

                     after(grammarAccess.getANYTHINGAccess().getStringAssignment_2()); 

                    }


                    }
                    break;
                case 4 :
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1068:6: ( ( rule__ANYTHING__ReferenceAssignment_3 ) )
                    {
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1068:6: ( ( rule__ANYTHING__ReferenceAssignment_3 ) )
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1069:1: ( rule__ANYTHING__ReferenceAssignment_3 )
                    {
                     before(grammarAccess.getANYTHINGAccess().getReferenceAssignment_3()); 
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1070:1: ( rule__ANYTHING__ReferenceAssignment_3 )
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1070:2: rule__ANYTHING__ReferenceAssignment_3
                    {
                    pushFollow(FOLLOW_rule__ANYTHING__ReferenceAssignment_3_in_rule__ANYTHING__Alternatives2315);
                    rule__ANYTHING__ReferenceAssignment_3();

                    state._fsp--;


                    }

                     after(grammarAccess.getANYTHINGAccess().getReferenceAssignment_3()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ANYTHING__Alternatives"


    // $ANTLR start "rule__Boolean__Alternatives"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1079:1: rule__Boolean__Alternatives : ( ( ( rule__Boolean__BoolAssignment_0 ) ) | ( ( rule__Boolean__CommandAssignment_1 ) ) );
    public final void rule__Boolean__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1083:1: ( ( ( rule__Boolean__BoolAssignment_0 ) ) | ( ( rule__Boolean__CommandAssignment_1 ) ) )
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( ((LA16_0>=19 && LA16_0<=20)) ) {
                alt16=1;
            }
            else if ( ((LA16_0>=21 && LA16_0<=22)) ) {
                alt16=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 16, 0, input);

                throw nvae;
            }
            switch (alt16) {
                case 1 :
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1084:1: ( ( rule__Boolean__BoolAssignment_0 ) )
                    {
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1084:1: ( ( rule__Boolean__BoolAssignment_0 ) )
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1085:1: ( rule__Boolean__BoolAssignment_0 )
                    {
                     before(grammarAccess.getBooleanAccess().getBoolAssignment_0()); 
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1086:1: ( rule__Boolean__BoolAssignment_0 )
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1086:2: rule__Boolean__BoolAssignment_0
                    {
                    pushFollow(FOLLOW_rule__Boolean__BoolAssignment_0_in_rule__Boolean__Alternatives2348);
                    rule__Boolean__BoolAssignment_0();

                    state._fsp--;


                    }

                     after(grammarAccess.getBooleanAccess().getBoolAssignment_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1090:6: ( ( rule__Boolean__CommandAssignment_1 ) )
                    {
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1090:6: ( ( rule__Boolean__CommandAssignment_1 ) )
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1091:1: ( rule__Boolean__CommandAssignment_1 )
                    {
                     before(grammarAccess.getBooleanAccess().getCommandAssignment_1()); 
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1092:1: ( rule__Boolean__CommandAssignment_1 )
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1092:2: rule__Boolean__CommandAssignment_1
                    {
                    pushFollow(FOLLOW_rule__Boolean__CommandAssignment_1_in_rule__Boolean__Alternatives2366);
                    rule__Boolean__CommandAssignment_1();

                    state._fsp--;


                    }

                     after(grammarAccess.getBooleanAccess().getCommandAssignment_1()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Boolean__Alternatives"


    // $ANTLR start "rule__Boolean__BoolAlternatives_0_0"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1101:1: rule__Boolean__BoolAlternatives_0_0 : ( ( 'true' ) | ( 'false' ) );
    public final void rule__Boolean__BoolAlternatives_0_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1105:1: ( ( 'true' ) | ( 'false' ) )
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==19) ) {
                alt17=1;
            }
            else if ( (LA17_0==20) ) {
                alt17=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 17, 0, input);

                throw nvae;
            }
            switch (alt17) {
                case 1 :
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1106:1: ( 'true' )
                    {
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1106:1: ( 'true' )
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1107:1: 'true'
                    {
                     before(grammarAccess.getBooleanAccess().getBoolTrueKeyword_0_0_0()); 
                    match(input,19,FOLLOW_19_in_rule__Boolean__BoolAlternatives_0_02400); 
                     after(grammarAccess.getBooleanAccess().getBoolTrueKeyword_0_0_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1114:6: ( 'false' )
                    {
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1114:6: ( 'false' )
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1115:1: 'false'
                    {
                     before(grammarAccess.getBooleanAccess().getBoolFalseKeyword_0_0_1()); 
                    match(input,20,FOLLOW_20_in_rule__Boolean__BoolAlternatives_0_02420); 
                     after(grammarAccess.getBooleanAccess().getBoolFalseKeyword_0_0_1()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Boolean__BoolAlternatives_0_0"


    // $ANTLR start "rule__BoolCommand__Alternatives"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1127:1: rule__BoolCommand__Alternatives : ( ( 'isServer' ) | ( 'isPlayer' ) );
    public final void rule__BoolCommand__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1131:1: ( ( 'isServer' ) | ( 'isPlayer' ) )
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==21) ) {
                alt18=1;
            }
            else if ( (LA18_0==22) ) {
                alt18=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 18, 0, input);

                throw nvae;
            }
            switch (alt18) {
                case 1 :
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1132:1: ( 'isServer' )
                    {
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1132:1: ( 'isServer' )
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1133:1: 'isServer'
                    {
                     before(grammarAccess.getBoolCommandAccess().getIsServerKeyword_0()); 
                    match(input,21,FOLLOW_21_in_rule__BoolCommand__Alternatives2455); 
                     after(grammarAccess.getBoolCommandAccess().getIsServerKeyword_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1140:6: ( 'isPlayer' )
                    {
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1140:6: ( 'isPlayer' )
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1141:1: 'isPlayer'
                    {
                     before(grammarAccess.getBoolCommandAccess().getIsPlayerKeyword_1()); 
                    match(input,22,FOLLOW_22_in_rule__BoolCommand__Alternatives2475); 
                     after(grammarAccess.getBoolCommandAccess().getIsPlayerKeyword_1()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__BoolCommand__Alternatives"


    // $ANTLR start "rule__Code__Group_0__0"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1155:1: rule__Code__Group_0__0 : rule__Code__Group_0__0__Impl rule__Code__Group_0__1 ;
    public final void rule__Code__Group_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1159:1: ( rule__Code__Group_0__0__Impl rule__Code__Group_0__1 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1160:2: rule__Code__Group_0__0__Impl rule__Code__Group_0__1
            {
            pushFollow(FOLLOW_rule__Code__Group_0__0__Impl_in_rule__Code__Group_0__02507);
            rule__Code__Group_0__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Code__Group_0__1_in_rule__Code__Group_0__02510);
            rule__Code__Group_0__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Code__Group_0__0"


    // $ANTLR start "rule__Code__Group_0__0__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1167:1: rule__Code__Group_0__0__Impl : ( ( rule__Code__DecAssignment_0_0 ) ) ;
    public final void rule__Code__Group_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1171:1: ( ( ( rule__Code__DecAssignment_0_0 ) ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1172:1: ( ( rule__Code__DecAssignment_0_0 ) )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1172:1: ( ( rule__Code__DecAssignment_0_0 ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1173:1: ( rule__Code__DecAssignment_0_0 )
            {
             before(grammarAccess.getCodeAccess().getDecAssignment_0_0()); 
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1174:1: ( rule__Code__DecAssignment_0_0 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1174:2: rule__Code__DecAssignment_0_0
            {
            pushFollow(FOLLOW_rule__Code__DecAssignment_0_0_in_rule__Code__Group_0__0__Impl2537);
            rule__Code__DecAssignment_0_0();

            state._fsp--;


            }

             after(grammarAccess.getCodeAccess().getDecAssignment_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Code__Group_0__0__Impl"


    // $ANTLR start "rule__Code__Group_0__1"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1184:1: rule__Code__Group_0__1 : rule__Code__Group_0__1__Impl ;
    public final void rule__Code__Group_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1188:1: ( rule__Code__Group_0__1__Impl )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1189:2: rule__Code__Group_0__1__Impl
            {
            pushFollow(FOLLOW_rule__Code__Group_0__1__Impl_in_rule__Code__Group_0__12567);
            rule__Code__Group_0__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Code__Group_0__1"


    // $ANTLR start "rule__Code__Group_0__1__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1195:1: rule__Code__Group_0__1__Impl : ( ';' ) ;
    public final void rule__Code__Group_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1199:1: ( ( ';' ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1200:1: ( ';' )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1200:1: ( ';' )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1201:1: ';'
            {
             before(grammarAccess.getCodeAccess().getSemicolonKeyword_0_1()); 
            match(input,23,FOLLOW_23_in_rule__Code__Group_0__1__Impl2595); 
             after(grammarAccess.getCodeAccess().getSemicolonKeyword_0_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Code__Group_0__1__Impl"


    // $ANTLR start "rule__Code__Group_1__0"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1218:1: rule__Code__Group_1__0 : rule__Code__Group_1__0__Impl rule__Code__Group_1__1 ;
    public final void rule__Code__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1222:1: ( rule__Code__Group_1__0__Impl rule__Code__Group_1__1 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1223:2: rule__Code__Group_1__0__Impl rule__Code__Group_1__1
            {
            pushFollow(FOLLOW_rule__Code__Group_1__0__Impl_in_rule__Code__Group_1__02630);
            rule__Code__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Code__Group_1__1_in_rule__Code__Group_1__02633);
            rule__Code__Group_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Code__Group_1__0"


    // $ANTLR start "rule__Code__Group_1__0__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1230:1: rule__Code__Group_1__0__Impl : ( ( rule__Code__ControlAssignment_1_0 ) ) ;
    public final void rule__Code__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1234:1: ( ( ( rule__Code__ControlAssignment_1_0 ) ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1235:1: ( ( rule__Code__ControlAssignment_1_0 ) )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1235:1: ( ( rule__Code__ControlAssignment_1_0 ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1236:1: ( rule__Code__ControlAssignment_1_0 )
            {
             before(grammarAccess.getCodeAccess().getControlAssignment_1_0()); 
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1237:1: ( rule__Code__ControlAssignment_1_0 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1237:2: rule__Code__ControlAssignment_1_0
            {
            pushFollow(FOLLOW_rule__Code__ControlAssignment_1_0_in_rule__Code__Group_1__0__Impl2660);
            rule__Code__ControlAssignment_1_0();

            state._fsp--;


            }

             after(grammarAccess.getCodeAccess().getControlAssignment_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Code__Group_1__0__Impl"


    // $ANTLR start "rule__Code__Group_1__1"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1247:1: rule__Code__Group_1__1 : rule__Code__Group_1__1__Impl ;
    public final void rule__Code__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1251:1: ( rule__Code__Group_1__1__Impl )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1252:2: rule__Code__Group_1__1__Impl
            {
            pushFollow(FOLLOW_rule__Code__Group_1__1__Impl_in_rule__Code__Group_1__12690);
            rule__Code__Group_1__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Code__Group_1__1"


    // $ANTLR start "rule__Code__Group_1__1__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1258:1: rule__Code__Group_1__1__Impl : ( ';' ) ;
    public final void rule__Code__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1262:1: ( ( ';' ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1263:1: ( ';' )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1263:1: ( ';' )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1264:1: ';'
            {
             before(grammarAccess.getCodeAccess().getSemicolonKeyword_1_1()); 
            match(input,23,FOLLOW_23_in_rule__Code__Group_1__1__Impl2718); 
             after(grammarAccess.getCodeAccess().getSemicolonKeyword_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Code__Group_1__1__Impl"


    // $ANTLR start "rule__Code__Group_2__0"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1281:1: rule__Code__Group_2__0 : rule__Code__Group_2__0__Impl rule__Code__Group_2__1 ;
    public final void rule__Code__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1285:1: ( rule__Code__Group_2__0__Impl rule__Code__Group_2__1 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1286:2: rule__Code__Group_2__0__Impl rule__Code__Group_2__1
            {
            pushFollow(FOLLOW_rule__Code__Group_2__0__Impl_in_rule__Code__Group_2__02753);
            rule__Code__Group_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Code__Group_2__1_in_rule__Code__Group_2__02756);
            rule__Code__Group_2__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Code__Group_2__0"


    // $ANTLR start "rule__Code__Group_2__0__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1293:1: rule__Code__Group_2__0__Impl : ( ( rule__Code__MethodAssignment_2_0 ) ) ;
    public final void rule__Code__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1297:1: ( ( ( rule__Code__MethodAssignment_2_0 ) ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1298:1: ( ( rule__Code__MethodAssignment_2_0 ) )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1298:1: ( ( rule__Code__MethodAssignment_2_0 ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1299:1: ( rule__Code__MethodAssignment_2_0 )
            {
             before(grammarAccess.getCodeAccess().getMethodAssignment_2_0()); 
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1300:1: ( rule__Code__MethodAssignment_2_0 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1300:2: rule__Code__MethodAssignment_2_0
            {
            pushFollow(FOLLOW_rule__Code__MethodAssignment_2_0_in_rule__Code__Group_2__0__Impl2783);
            rule__Code__MethodAssignment_2_0();

            state._fsp--;


            }

             after(grammarAccess.getCodeAccess().getMethodAssignment_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Code__Group_2__0__Impl"


    // $ANTLR start "rule__Code__Group_2__1"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1310:1: rule__Code__Group_2__1 : rule__Code__Group_2__1__Impl ;
    public final void rule__Code__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1314:1: ( rule__Code__Group_2__1__Impl )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1315:2: rule__Code__Group_2__1__Impl
            {
            pushFollow(FOLLOW_rule__Code__Group_2__1__Impl_in_rule__Code__Group_2__12813);
            rule__Code__Group_2__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Code__Group_2__1"


    // $ANTLR start "rule__Code__Group_2__1__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1321:1: rule__Code__Group_2__1__Impl : ( ';' ) ;
    public final void rule__Code__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1325:1: ( ( ';' ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1326:1: ( ';' )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1326:1: ( ';' )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1327:1: ';'
            {
             before(grammarAccess.getCodeAccess().getSemicolonKeyword_2_1()); 
            match(input,23,FOLLOW_23_in_rule__Code__Group_2__1__Impl2841); 
             after(grammarAccess.getCodeAccess().getSemicolonKeyword_2_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Code__Group_2__1__Impl"


    // $ANTLR start "rule__Declaration__Group__0"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1344:1: rule__Declaration__Group__0 : rule__Declaration__Group__0__Impl rule__Declaration__Group__1 ;
    public final void rule__Declaration__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1348:1: ( rule__Declaration__Group__0__Impl rule__Declaration__Group__1 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1349:2: rule__Declaration__Group__0__Impl rule__Declaration__Group__1
            {
            pushFollow(FOLLOW_rule__Declaration__Group__0__Impl_in_rule__Declaration__Group__02876);
            rule__Declaration__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Declaration__Group__1_in_rule__Declaration__Group__02879);
            rule__Declaration__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Declaration__Group__0"


    // $ANTLR start "rule__Declaration__Group__0__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1356:1: rule__Declaration__Group__0__Impl : ( ( rule__Declaration__NameAssignment_0 ) ) ;
    public final void rule__Declaration__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1360:1: ( ( ( rule__Declaration__NameAssignment_0 ) ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1361:1: ( ( rule__Declaration__NameAssignment_0 ) )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1361:1: ( ( rule__Declaration__NameAssignment_0 ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1362:1: ( rule__Declaration__NameAssignment_0 )
            {
             before(grammarAccess.getDeclarationAccess().getNameAssignment_0()); 
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1363:1: ( rule__Declaration__NameAssignment_0 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1363:2: rule__Declaration__NameAssignment_0
            {
            pushFollow(FOLLOW_rule__Declaration__NameAssignment_0_in_rule__Declaration__Group__0__Impl2906);
            rule__Declaration__NameAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getDeclarationAccess().getNameAssignment_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Declaration__Group__0__Impl"


    // $ANTLR start "rule__Declaration__Group__1"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1373:1: rule__Declaration__Group__1 : rule__Declaration__Group__1__Impl rule__Declaration__Group__2 ;
    public final void rule__Declaration__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1377:1: ( rule__Declaration__Group__1__Impl rule__Declaration__Group__2 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1378:2: rule__Declaration__Group__1__Impl rule__Declaration__Group__2
            {
            pushFollow(FOLLOW_rule__Declaration__Group__1__Impl_in_rule__Declaration__Group__12936);
            rule__Declaration__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Declaration__Group__2_in_rule__Declaration__Group__12939);
            rule__Declaration__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Declaration__Group__1"


    // $ANTLR start "rule__Declaration__Group__1__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1385:1: rule__Declaration__Group__1__Impl : ( '=' ) ;
    public final void rule__Declaration__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1389:1: ( ( '=' ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1390:1: ( '=' )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1390:1: ( '=' )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1391:1: '='
            {
             before(grammarAccess.getDeclarationAccess().getEqualsSignKeyword_1()); 
            match(input,24,FOLLOW_24_in_rule__Declaration__Group__1__Impl2967); 
             after(grammarAccess.getDeclarationAccess().getEqualsSignKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Declaration__Group__1__Impl"


    // $ANTLR start "rule__Declaration__Group__2"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1404:1: rule__Declaration__Group__2 : rule__Declaration__Group__2__Impl ;
    public final void rule__Declaration__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1408:1: ( rule__Declaration__Group__2__Impl )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1409:2: rule__Declaration__Group__2__Impl
            {
            pushFollow(FOLLOW_rule__Declaration__Group__2__Impl_in_rule__Declaration__Group__22998);
            rule__Declaration__Group__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Declaration__Group__2"


    // $ANTLR start "rule__Declaration__Group__2__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1415:1: rule__Declaration__Group__2__Impl : ( ( rule__Declaration__BrConAssignment_2 ) ) ;
    public final void rule__Declaration__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1419:1: ( ( ( rule__Declaration__BrConAssignment_2 ) ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1420:1: ( ( rule__Declaration__BrConAssignment_2 ) )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1420:1: ( ( rule__Declaration__BrConAssignment_2 ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1421:1: ( rule__Declaration__BrConAssignment_2 )
            {
             before(grammarAccess.getDeclarationAccess().getBrConAssignment_2()); 
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1422:1: ( rule__Declaration__BrConAssignment_2 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1422:2: rule__Declaration__BrConAssignment_2
            {
            pushFollow(FOLLOW_rule__Declaration__BrConAssignment_2_in_rule__Declaration__Group__2__Impl3025);
            rule__Declaration__BrConAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getDeclarationAccess().getBrConAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Declaration__Group__2__Impl"


    // $ANTLR start "rule__BracketContent__Group__0"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1438:1: rule__BracketContent__Group__0 : rule__BracketContent__Group__0__Impl rule__BracketContent__Group__1 ;
    public final void rule__BracketContent__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1442:1: ( rule__BracketContent__Group__0__Impl rule__BracketContent__Group__1 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1443:2: rule__BracketContent__Group__0__Impl rule__BracketContent__Group__1
            {
            pushFollow(FOLLOW_rule__BracketContent__Group__0__Impl_in_rule__BracketContent__Group__03061);
            rule__BracketContent__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__BracketContent__Group__1_in_rule__BracketContent__Group__03064);
            rule__BracketContent__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__BracketContent__Group__0"


    // $ANTLR start "rule__BracketContent__Group__0__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1450:1: rule__BracketContent__Group__0__Impl : ( ( rule__BracketContent__DecConAssignment_0 ) ) ;
    public final void rule__BracketContent__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1454:1: ( ( ( rule__BracketContent__DecConAssignment_0 ) ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1455:1: ( ( rule__BracketContent__DecConAssignment_0 ) )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1455:1: ( ( rule__BracketContent__DecConAssignment_0 ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1456:1: ( rule__BracketContent__DecConAssignment_0 )
            {
             before(grammarAccess.getBracketContentAccess().getDecConAssignment_0()); 
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1457:1: ( rule__BracketContent__DecConAssignment_0 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1457:2: rule__BracketContent__DecConAssignment_0
            {
            pushFollow(FOLLOW_rule__BracketContent__DecConAssignment_0_in_rule__BracketContent__Group__0__Impl3091);
            rule__BracketContent__DecConAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getBracketContentAccess().getDecConAssignment_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__BracketContent__Group__0__Impl"


    // $ANTLR start "rule__BracketContent__Group__1"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1467:1: rule__BracketContent__Group__1 : rule__BracketContent__Group__1__Impl ;
    public final void rule__BracketContent__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1471:1: ( rule__BracketContent__Group__1__Impl )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1472:2: rule__BracketContent__Group__1__Impl
            {
            pushFollow(FOLLOW_rule__BracketContent__Group__1__Impl_in_rule__BracketContent__Group__13121);
            rule__BracketContent__Group__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__BracketContent__Group__1"


    // $ANTLR start "rule__BracketContent__Group__1__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1478:1: rule__BracketContent__Group__1__Impl : ( ( rule__BracketContent__Group_1__0 )* ) ;
    public final void rule__BracketContent__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1482:1: ( ( ( rule__BracketContent__Group_1__0 )* ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1483:1: ( ( rule__BracketContent__Group_1__0 )* )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1483:1: ( ( rule__BracketContent__Group_1__0 )* )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1484:1: ( rule__BracketContent__Group_1__0 )*
            {
             before(grammarAccess.getBracketContentAccess().getGroup_1()); 
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1485:1: ( rule__BracketContent__Group_1__0 )*
            loop19:
            do {
                int alt19=2;
                int LA19_0 = input.LA(1);

                if ( (LA19_0==RULE_COMPARATOR) ) {
                    alt19=1;
                }


                switch (alt19) {
            	case 1 :
            	    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1485:2: rule__BracketContent__Group_1__0
            	    {
            	    pushFollow(FOLLOW_rule__BracketContent__Group_1__0_in_rule__BracketContent__Group__1__Impl3148);
            	    rule__BracketContent__Group_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop19;
                }
            } while (true);

             after(grammarAccess.getBracketContentAccess().getGroup_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__BracketContent__Group__1__Impl"


    // $ANTLR start "rule__BracketContent__Group_1__0"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1499:1: rule__BracketContent__Group_1__0 : rule__BracketContent__Group_1__0__Impl rule__BracketContent__Group_1__1 ;
    public final void rule__BracketContent__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1503:1: ( rule__BracketContent__Group_1__0__Impl rule__BracketContent__Group_1__1 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1504:2: rule__BracketContent__Group_1__0__Impl rule__BracketContent__Group_1__1
            {
            pushFollow(FOLLOW_rule__BracketContent__Group_1__0__Impl_in_rule__BracketContent__Group_1__03183);
            rule__BracketContent__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__BracketContent__Group_1__1_in_rule__BracketContent__Group_1__03186);
            rule__BracketContent__Group_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__BracketContent__Group_1__0"


    // $ANTLR start "rule__BracketContent__Group_1__0__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1511:1: rule__BracketContent__Group_1__0__Impl : ( ( rule__BracketContent__CompAssignment_1_0 ) ) ;
    public final void rule__BracketContent__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1515:1: ( ( ( rule__BracketContent__CompAssignment_1_0 ) ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1516:1: ( ( rule__BracketContent__CompAssignment_1_0 ) )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1516:1: ( ( rule__BracketContent__CompAssignment_1_0 ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1517:1: ( rule__BracketContent__CompAssignment_1_0 )
            {
             before(grammarAccess.getBracketContentAccess().getCompAssignment_1_0()); 
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1518:1: ( rule__BracketContent__CompAssignment_1_0 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1518:2: rule__BracketContent__CompAssignment_1_0
            {
            pushFollow(FOLLOW_rule__BracketContent__CompAssignment_1_0_in_rule__BracketContent__Group_1__0__Impl3213);
            rule__BracketContent__CompAssignment_1_0();

            state._fsp--;


            }

             after(grammarAccess.getBracketContentAccess().getCompAssignment_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__BracketContent__Group_1__0__Impl"


    // $ANTLR start "rule__BracketContent__Group_1__1"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1528:1: rule__BracketContent__Group_1__1 : rule__BracketContent__Group_1__1__Impl ;
    public final void rule__BracketContent__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1532:1: ( rule__BracketContent__Group_1__1__Impl )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1533:2: rule__BracketContent__Group_1__1__Impl
            {
            pushFollow(FOLLOW_rule__BracketContent__Group_1__1__Impl_in_rule__BracketContent__Group_1__13243);
            rule__BracketContent__Group_1__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__BracketContent__Group_1__1"


    // $ANTLR start "rule__BracketContent__Group_1__1__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1539:1: rule__BracketContent__Group_1__1__Impl : ( ( rule__BracketContent__ContentAssignment_1_1 ) ) ;
    public final void rule__BracketContent__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1543:1: ( ( ( rule__BracketContent__ContentAssignment_1_1 ) ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1544:1: ( ( rule__BracketContent__ContentAssignment_1_1 ) )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1544:1: ( ( rule__BracketContent__ContentAssignment_1_1 ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1545:1: ( rule__BracketContent__ContentAssignment_1_1 )
            {
             before(grammarAccess.getBracketContentAccess().getContentAssignment_1_1()); 
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1546:1: ( rule__BracketContent__ContentAssignment_1_1 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1546:2: rule__BracketContent__ContentAssignment_1_1
            {
            pushFollow(FOLLOW_rule__BracketContent__ContentAssignment_1_1_in_rule__BracketContent__Group_1__1__Impl3270);
            rule__BracketContent__ContentAssignment_1_1();

            state._fsp--;


            }

             after(grammarAccess.getBracketContentAccess().getContentAssignment_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__BracketContent__Group_1__1__Impl"


    // $ANTLR start "rule__DecContent__Group__0"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1560:1: rule__DecContent__Group__0 : rule__DecContent__Group__0__Impl rule__DecContent__Group__1 ;
    public final void rule__DecContent__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1564:1: ( rule__DecContent__Group__0__Impl rule__DecContent__Group__1 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1565:2: rule__DecContent__Group__0__Impl rule__DecContent__Group__1
            {
            pushFollow(FOLLOW_rule__DecContent__Group__0__Impl_in_rule__DecContent__Group__03304);
            rule__DecContent__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__DecContent__Group__1_in_rule__DecContent__Group__03307);
            rule__DecContent__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__DecContent__Group__0"


    // $ANTLR start "rule__DecContent__Group__0__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1572:1: rule__DecContent__Group__0__Impl : ( ( rule__DecContent__NegAssignment_0 )? ) ;
    public final void rule__DecContent__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1576:1: ( ( ( rule__DecContent__NegAssignment_0 )? ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1577:1: ( ( rule__DecContent__NegAssignment_0 )? )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1577:1: ( ( rule__DecContent__NegAssignment_0 )? )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1578:1: ( rule__DecContent__NegAssignment_0 )?
            {
             before(grammarAccess.getDecContentAccess().getNegAssignment_0()); 
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1579:1: ( rule__DecContent__NegAssignment_0 )?
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( ((LA20_0>=11 && LA20_0<=12)) ) {
                alt20=1;
            }
            switch (alt20) {
                case 1 :
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1579:2: rule__DecContent__NegAssignment_0
                    {
                    pushFollow(FOLLOW_rule__DecContent__NegAssignment_0_in_rule__DecContent__Group__0__Impl3334);
                    rule__DecContent__NegAssignment_0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getDecContentAccess().getNegAssignment_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__DecContent__Group__0__Impl"


    // $ANTLR start "rule__DecContent__Group__1"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1589:1: rule__DecContent__Group__1 : rule__DecContent__Group__1__Impl rule__DecContent__Group__2 ;
    public final void rule__DecContent__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1593:1: ( rule__DecContent__Group__1__Impl rule__DecContent__Group__2 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1594:2: rule__DecContent__Group__1__Impl rule__DecContent__Group__2
            {
            pushFollow(FOLLOW_rule__DecContent__Group__1__Impl_in_rule__DecContent__Group__13365);
            rule__DecContent__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__DecContent__Group__2_in_rule__DecContent__Group__13368);
            rule__DecContent__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__DecContent__Group__1"


    // $ANTLR start "rule__DecContent__Group__1__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1601:1: rule__DecContent__Group__1__Impl : ( ( rule__DecContent__SingleContentAssignment_1 ) ) ;
    public final void rule__DecContent__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1605:1: ( ( ( rule__DecContent__SingleContentAssignment_1 ) ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1606:1: ( ( rule__DecContent__SingleContentAssignment_1 ) )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1606:1: ( ( rule__DecContent__SingleContentAssignment_1 ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1607:1: ( rule__DecContent__SingleContentAssignment_1 )
            {
             before(grammarAccess.getDecContentAccess().getSingleContentAssignment_1()); 
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1608:1: ( rule__DecContent__SingleContentAssignment_1 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1608:2: rule__DecContent__SingleContentAssignment_1
            {
            pushFollow(FOLLOW_rule__DecContent__SingleContentAssignment_1_in_rule__DecContent__Group__1__Impl3395);
            rule__DecContent__SingleContentAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getDecContentAccess().getSingleContentAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__DecContent__Group__1__Impl"


    // $ANTLR start "rule__DecContent__Group__2"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1618:1: rule__DecContent__Group__2 : rule__DecContent__Group__2__Impl ;
    public final void rule__DecContent__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1622:1: ( rule__DecContent__Group__2__Impl )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1623:2: rule__DecContent__Group__2__Impl
            {
            pushFollow(FOLLOW_rule__DecContent__Group__2__Impl_in_rule__DecContent__Group__23425);
            rule__DecContent__Group__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__DecContent__Group__2"


    // $ANTLR start "rule__DecContent__Group__2__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1629:1: rule__DecContent__Group__2__Impl : ( ( rule__DecContent__Group_2__0 )* ) ;
    public final void rule__DecContent__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1633:1: ( ( ( rule__DecContent__Group_2__0 )* ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1634:1: ( ( rule__DecContent__Group_2__0 )* )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1634:1: ( ( rule__DecContent__Group_2__0 )* )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1635:1: ( rule__DecContent__Group_2__0 )*
            {
             before(grammarAccess.getDecContentAccess().getGroup_2()); 
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1636:1: ( rule__DecContent__Group_2__0 )*
            loop21:
            do {
                int alt21=2;
                int LA21_0 = input.LA(1);

                if ( ((LA21_0>=13 && LA21_0<=18)) ) {
                    alt21=1;
                }


                switch (alt21) {
            	case 1 :
            	    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1636:2: rule__DecContent__Group_2__0
            	    {
            	    pushFollow(FOLLOW_rule__DecContent__Group_2__0_in_rule__DecContent__Group__2__Impl3452);
            	    rule__DecContent__Group_2__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop21;
                }
            } while (true);

             after(grammarAccess.getDecContentAccess().getGroup_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__DecContent__Group__2__Impl"


    // $ANTLR start "rule__DecContent__Group_2__0"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1652:1: rule__DecContent__Group_2__0 : rule__DecContent__Group_2__0__Impl rule__DecContent__Group_2__1 ;
    public final void rule__DecContent__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1656:1: ( rule__DecContent__Group_2__0__Impl rule__DecContent__Group_2__1 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1657:2: rule__DecContent__Group_2__0__Impl rule__DecContent__Group_2__1
            {
            pushFollow(FOLLOW_rule__DecContent__Group_2__0__Impl_in_rule__DecContent__Group_2__03489);
            rule__DecContent__Group_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__DecContent__Group_2__1_in_rule__DecContent__Group_2__03492);
            rule__DecContent__Group_2__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__DecContent__Group_2__0"


    // $ANTLR start "rule__DecContent__Group_2__0__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1664:1: rule__DecContent__Group_2__0__Impl : ( ( rule__DecContent__OpAssignment_2_0 ) ) ;
    public final void rule__DecContent__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1668:1: ( ( ( rule__DecContent__OpAssignment_2_0 ) ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1669:1: ( ( rule__DecContent__OpAssignment_2_0 ) )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1669:1: ( ( rule__DecContent__OpAssignment_2_0 ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1670:1: ( rule__DecContent__OpAssignment_2_0 )
            {
             before(grammarAccess.getDecContentAccess().getOpAssignment_2_0()); 
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1671:1: ( rule__DecContent__OpAssignment_2_0 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1671:2: rule__DecContent__OpAssignment_2_0
            {
            pushFollow(FOLLOW_rule__DecContent__OpAssignment_2_0_in_rule__DecContent__Group_2__0__Impl3519);
            rule__DecContent__OpAssignment_2_0();

            state._fsp--;


            }

             after(grammarAccess.getDecContentAccess().getOpAssignment_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__DecContent__Group_2__0__Impl"


    // $ANTLR start "rule__DecContent__Group_2__1"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1681:1: rule__DecContent__Group_2__1 : rule__DecContent__Group_2__1__Impl ;
    public final void rule__DecContent__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1685:1: ( rule__DecContent__Group_2__1__Impl )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1686:2: rule__DecContent__Group_2__1__Impl
            {
            pushFollow(FOLLOW_rule__DecContent__Group_2__1__Impl_in_rule__DecContent__Group_2__13549);
            rule__DecContent__Group_2__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__DecContent__Group_2__1"


    // $ANTLR start "rule__DecContent__Group_2__1__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1692:1: rule__DecContent__Group_2__1__Impl : ( ( rule__DecContent__NextConAssignment_2_1 ) ) ;
    public final void rule__DecContent__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1696:1: ( ( ( rule__DecContent__NextConAssignment_2_1 ) ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1697:1: ( ( rule__DecContent__NextConAssignment_2_1 ) )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1697:1: ( ( rule__DecContent__NextConAssignment_2_1 ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1698:1: ( rule__DecContent__NextConAssignment_2_1 )
            {
             before(grammarAccess.getDecContentAccess().getNextConAssignment_2_1()); 
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1699:1: ( rule__DecContent__NextConAssignment_2_1 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1699:2: rule__DecContent__NextConAssignment_2_1
            {
            pushFollow(FOLLOW_rule__DecContent__NextConAssignment_2_1_in_rule__DecContent__Group_2__1__Impl3576);
            rule__DecContent__NextConAssignment_2_1();

            state._fsp--;


            }

             after(grammarAccess.getDecContentAccess().getNextConAssignment_2_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__DecContent__Group_2__1__Impl"


    // $ANTLR start "rule__VarContent__Group_0__0"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1713:1: rule__VarContent__Group_0__0 : rule__VarContent__Group_0__0__Impl rule__VarContent__Group_0__1 ;
    public final void rule__VarContent__Group_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1717:1: ( rule__VarContent__Group_0__0__Impl rule__VarContent__Group_0__1 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1718:2: rule__VarContent__Group_0__0__Impl rule__VarContent__Group_0__1
            {
            pushFollow(FOLLOW_rule__VarContent__Group_0__0__Impl_in_rule__VarContent__Group_0__03610);
            rule__VarContent__Group_0__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__VarContent__Group_0__1_in_rule__VarContent__Group_0__03613);
            rule__VarContent__Group_0__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarContent__Group_0__0"


    // $ANTLR start "rule__VarContent__Group_0__0__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1725:1: rule__VarContent__Group_0__0__Impl : ( ( rule__VarContent__UnOPAssignment_0_0 )? ) ;
    public final void rule__VarContent__Group_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1729:1: ( ( ( rule__VarContent__UnOPAssignment_0_0 )? ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1730:1: ( ( rule__VarContent__UnOPAssignment_0_0 )? )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1730:1: ( ( rule__VarContent__UnOPAssignment_0_0 )? )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1731:1: ( rule__VarContent__UnOPAssignment_0_0 )?
            {
             before(grammarAccess.getVarContentAccess().getUnOPAssignment_0_0()); 
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1732:1: ( rule__VarContent__UnOPAssignment_0_0 )?
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( ((LA22_0>=13 && LA22_0<=14)) ) {
                alt22=1;
            }
            switch (alt22) {
                case 1 :
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1732:2: rule__VarContent__UnOPAssignment_0_0
                    {
                    pushFollow(FOLLOW_rule__VarContent__UnOPAssignment_0_0_in_rule__VarContent__Group_0__0__Impl3640);
                    rule__VarContent__UnOPAssignment_0_0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getVarContentAccess().getUnOPAssignment_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarContent__Group_0__0__Impl"


    // $ANTLR start "rule__VarContent__Group_0__1"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1742:1: rule__VarContent__Group_0__1 : rule__VarContent__Group_0__1__Impl ;
    public final void rule__VarContent__Group_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1746:1: ( rule__VarContent__Group_0__1__Impl )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1747:2: rule__VarContent__Group_0__1__Impl
            {
            pushFollow(FOLLOW_rule__VarContent__Group_0__1__Impl_in_rule__VarContent__Group_0__13671);
            rule__VarContent__Group_0__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarContent__Group_0__1"


    // $ANTLR start "rule__VarContent__Group_0__1__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1753:1: rule__VarContent__Group_0__1__Impl : ( ( rule__VarContent__NumAssignment_0_1 ) ) ;
    public final void rule__VarContent__Group_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1757:1: ( ( ( rule__VarContent__NumAssignment_0_1 ) ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1758:1: ( ( rule__VarContent__NumAssignment_0_1 ) )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1758:1: ( ( rule__VarContent__NumAssignment_0_1 ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1759:1: ( rule__VarContent__NumAssignment_0_1 )
            {
             before(grammarAccess.getVarContentAccess().getNumAssignment_0_1()); 
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1760:1: ( rule__VarContent__NumAssignment_0_1 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1760:2: rule__VarContent__NumAssignment_0_1
            {
            pushFollow(FOLLOW_rule__VarContent__NumAssignment_0_1_in_rule__VarContent__Group_0__1__Impl3698);
            rule__VarContent__NumAssignment_0_1();

            state._fsp--;


            }

             after(grammarAccess.getVarContentAccess().getNumAssignment_0_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarContent__Group_0__1__Impl"


    // $ANTLR start "rule__VarContent__Group_2__0"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1774:1: rule__VarContent__Group_2__0 : rule__VarContent__Group_2__0__Impl rule__VarContent__Group_2__1 ;
    public final void rule__VarContent__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1778:1: ( rule__VarContent__Group_2__0__Impl rule__VarContent__Group_2__1 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1779:2: rule__VarContent__Group_2__0__Impl rule__VarContent__Group_2__1
            {
            pushFollow(FOLLOW_rule__VarContent__Group_2__0__Impl_in_rule__VarContent__Group_2__03732);
            rule__VarContent__Group_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__VarContent__Group_2__1_in_rule__VarContent__Group_2__03735);
            rule__VarContent__Group_2__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarContent__Group_2__0"


    // $ANTLR start "rule__VarContent__Group_2__0__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1786:1: rule__VarContent__Group_2__0__Impl : ( ( rule__VarContent__UnOPAssignment_2_0 )? ) ;
    public final void rule__VarContent__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1790:1: ( ( ( rule__VarContent__UnOPAssignment_2_0 )? ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1791:1: ( ( rule__VarContent__UnOPAssignment_2_0 )? )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1791:1: ( ( rule__VarContent__UnOPAssignment_2_0 )? )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1792:1: ( rule__VarContent__UnOPAssignment_2_0 )?
            {
             before(grammarAccess.getVarContentAccess().getUnOPAssignment_2_0()); 
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1793:1: ( rule__VarContent__UnOPAssignment_2_0 )?
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( ((LA23_0>=13 && LA23_0<=14)) ) {
                alt23=1;
            }
            switch (alt23) {
                case 1 :
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1793:2: rule__VarContent__UnOPAssignment_2_0
                    {
                    pushFollow(FOLLOW_rule__VarContent__UnOPAssignment_2_0_in_rule__VarContent__Group_2__0__Impl3762);
                    rule__VarContent__UnOPAssignment_2_0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getVarContentAccess().getUnOPAssignment_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarContent__Group_2__0__Impl"


    // $ANTLR start "rule__VarContent__Group_2__1"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1803:1: rule__VarContent__Group_2__1 : rule__VarContent__Group_2__1__Impl rule__VarContent__Group_2__2 ;
    public final void rule__VarContent__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1807:1: ( rule__VarContent__Group_2__1__Impl rule__VarContent__Group_2__2 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1808:2: rule__VarContent__Group_2__1__Impl rule__VarContent__Group_2__2
            {
            pushFollow(FOLLOW_rule__VarContent__Group_2__1__Impl_in_rule__VarContent__Group_2__13793);
            rule__VarContent__Group_2__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__VarContent__Group_2__2_in_rule__VarContent__Group_2__13796);
            rule__VarContent__Group_2__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarContent__Group_2__1"


    // $ANTLR start "rule__VarContent__Group_2__1__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1815:1: rule__VarContent__Group_2__1__Impl : ( ( rule__VarContent__Alternatives_2_1 ) ) ;
    public final void rule__VarContent__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1819:1: ( ( ( rule__VarContent__Alternatives_2_1 ) ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1820:1: ( ( rule__VarContent__Alternatives_2_1 ) )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1820:1: ( ( rule__VarContent__Alternatives_2_1 ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1821:1: ( rule__VarContent__Alternatives_2_1 )
            {
             before(grammarAccess.getVarContentAccess().getAlternatives_2_1()); 
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1822:1: ( rule__VarContent__Alternatives_2_1 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1822:2: rule__VarContent__Alternatives_2_1
            {
            pushFollow(FOLLOW_rule__VarContent__Alternatives_2_1_in_rule__VarContent__Group_2__1__Impl3823);
            rule__VarContent__Alternatives_2_1();

            state._fsp--;


            }

             after(grammarAccess.getVarContentAccess().getAlternatives_2_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarContent__Group_2__1__Impl"


    // $ANTLR start "rule__VarContent__Group_2__2"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1832:1: rule__VarContent__Group_2__2 : rule__VarContent__Group_2__2__Impl ;
    public final void rule__VarContent__Group_2__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1836:1: ( rule__VarContent__Group_2__2__Impl )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1837:2: rule__VarContent__Group_2__2__Impl
            {
            pushFollow(FOLLOW_rule__VarContent__Group_2__2__Impl_in_rule__VarContent__Group_2__23853);
            rule__VarContent__Group_2__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarContent__Group_2__2"


    // $ANTLR start "rule__VarContent__Group_2__2__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1843:1: rule__VarContent__Group_2__2__Impl : ( ( rule__VarContent__Group_2_2__0 )? ) ;
    public final void rule__VarContent__Group_2__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1847:1: ( ( ( rule__VarContent__Group_2_2__0 )? ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1848:1: ( ( rule__VarContent__Group_2_2__0 )? )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1848:1: ( ( rule__VarContent__Group_2_2__0 )? )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1849:1: ( rule__VarContent__Group_2_2__0 )?
            {
             before(grammarAccess.getVarContentAccess().getGroup_2_2()); 
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1850:1: ( rule__VarContent__Group_2_2__0 )?
            int alt24=2;
            int LA24_0 = input.LA(1);

            if ( (LA24_0==26) ) {
                alt24=1;
            }
            switch (alt24) {
                case 1 :
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1850:2: rule__VarContent__Group_2_2__0
                    {
                    pushFollow(FOLLOW_rule__VarContent__Group_2_2__0_in_rule__VarContent__Group_2__2__Impl3880);
                    rule__VarContent__Group_2_2__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getVarContentAccess().getGroup_2_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarContent__Group_2__2__Impl"


    // $ANTLR start "rule__VarContent__Group_2_2__0"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1866:1: rule__VarContent__Group_2_2__0 : rule__VarContent__Group_2_2__0__Impl rule__VarContent__Group_2_2__1 ;
    public final void rule__VarContent__Group_2_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1870:1: ( rule__VarContent__Group_2_2__0__Impl rule__VarContent__Group_2_2__1 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1871:2: rule__VarContent__Group_2_2__0__Impl rule__VarContent__Group_2_2__1
            {
            pushFollow(FOLLOW_rule__VarContent__Group_2_2__0__Impl_in_rule__VarContent__Group_2_2__03917);
            rule__VarContent__Group_2_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__VarContent__Group_2_2__1_in_rule__VarContent__Group_2_2__03920);
            rule__VarContent__Group_2_2__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarContent__Group_2_2__0"


    // $ANTLR start "rule__VarContent__Group_2_2__0__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1878:1: rule__VarContent__Group_2_2__0__Impl : ( ( rule__VarContent__SelAssignment_2_2_0 ) ) ;
    public final void rule__VarContent__Group_2_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1882:1: ( ( ( rule__VarContent__SelAssignment_2_2_0 ) ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1883:1: ( ( rule__VarContent__SelAssignment_2_2_0 ) )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1883:1: ( ( rule__VarContent__SelAssignment_2_2_0 ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1884:1: ( rule__VarContent__SelAssignment_2_2_0 )
            {
             before(grammarAccess.getVarContentAccess().getSelAssignment_2_2_0()); 
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1885:1: ( rule__VarContent__SelAssignment_2_2_0 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1885:2: rule__VarContent__SelAssignment_2_2_0
            {
            pushFollow(FOLLOW_rule__VarContent__SelAssignment_2_2_0_in_rule__VarContent__Group_2_2__0__Impl3947);
            rule__VarContent__SelAssignment_2_2_0();

            state._fsp--;


            }

             after(grammarAccess.getVarContentAccess().getSelAssignment_2_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarContent__Group_2_2__0__Impl"


    // $ANTLR start "rule__VarContent__Group_2_2__1"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1895:1: rule__VarContent__Group_2_2__1 : rule__VarContent__Group_2_2__1__Impl ;
    public final void rule__VarContent__Group_2_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1899:1: ( rule__VarContent__Group_2_2__1__Impl )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1900:2: rule__VarContent__Group_2_2__1__Impl
            {
            pushFollow(FOLLOW_rule__VarContent__Group_2_2__1__Impl_in_rule__VarContent__Group_2_2__13977);
            rule__VarContent__Group_2_2__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarContent__Group_2_2__1"


    // $ANTLR start "rule__VarContent__Group_2_2__1__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1906:1: rule__VarContent__Group_2_2__1__Impl : ( ( rule__VarContent__IndexAssignment_2_2_1 ) ) ;
    public final void rule__VarContent__Group_2_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1910:1: ( ( ( rule__VarContent__IndexAssignment_2_2_1 ) ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1911:1: ( ( rule__VarContent__IndexAssignment_2_2_1 ) )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1911:1: ( ( rule__VarContent__IndexAssignment_2_2_1 ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1912:1: ( rule__VarContent__IndexAssignment_2_2_1 )
            {
             before(grammarAccess.getVarContentAccess().getIndexAssignment_2_2_1()); 
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1913:1: ( rule__VarContent__IndexAssignment_2_2_1 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1913:2: rule__VarContent__IndexAssignment_2_2_1
            {
            pushFollow(FOLLOW_rule__VarContent__IndexAssignment_2_2_1_in_rule__VarContent__Group_2_2__1__Impl4004);
            rule__VarContent__IndexAssignment_2_2_1();

            state._fsp--;


            }

             after(grammarAccess.getVarContentAccess().getIndexAssignment_2_2_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarContent__Group_2_2__1__Impl"


    // $ANTLR start "rule__VarContent__Group_3__0"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1927:1: rule__VarContent__Group_3__0 : rule__VarContent__Group_3__0__Impl rule__VarContent__Group_3__1 ;
    public final void rule__VarContent__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1931:1: ( rule__VarContent__Group_3__0__Impl rule__VarContent__Group_3__1 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1932:2: rule__VarContent__Group_3__0__Impl rule__VarContent__Group_3__1
            {
            pushFollow(FOLLOW_rule__VarContent__Group_3__0__Impl_in_rule__VarContent__Group_3__04038);
            rule__VarContent__Group_3__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__VarContent__Group_3__1_in_rule__VarContent__Group_3__04041);
            rule__VarContent__Group_3__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarContent__Group_3__0"


    // $ANTLR start "rule__VarContent__Group_3__0__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1939:1: rule__VarContent__Group_3__0__Impl : ( ( '+' )? ) ;
    public final void rule__VarContent__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1943:1: ( ( ( '+' )? ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1944:1: ( ( '+' )? )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1944:1: ( ( '+' )? )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1945:1: ( '+' )?
            {
             before(grammarAccess.getVarContentAccess().getPlusSignKeyword_3_0()); 
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1946:1: ( '+' )?
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( (LA25_0==13) ) {
                alt25=1;
            }
            switch (alt25) {
                case 1 :
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1947:2: '+'
                    {
                    match(input,13,FOLLOW_13_in_rule__VarContent__Group_3__0__Impl4070); 

                    }
                    break;

            }

             after(grammarAccess.getVarContentAccess().getPlusSignKeyword_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarContent__Group_3__0__Impl"


    // $ANTLR start "rule__VarContent__Group_3__1"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1958:1: rule__VarContent__Group_3__1 : rule__VarContent__Group_3__1__Impl rule__VarContent__Group_3__2 ;
    public final void rule__VarContent__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1962:1: ( rule__VarContent__Group_3__1__Impl rule__VarContent__Group_3__2 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1963:2: rule__VarContent__Group_3__1__Impl rule__VarContent__Group_3__2
            {
            pushFollow(FOLLOW_rule__VarContent__Group_3__1__Impl_in_rule__VarContent__Group_3__14103);
            rule__VarContent__Group_3__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__VarContent__Group_3__2_in_rule__VarContent__Group_3__14106);
            rule__VarContent__Group_3__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarContent__Group_3__1"


    // $ANTLR start "rule__VarContent__Group_3__1__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1970:1: rule__VarContent__Group_3__1__Impl : ( ( rule__VarContent__ArrayContentAssignment_3_1 ) ) ;
    public final void rule__VarContent__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1974:1: ( ( ( rule__VarContent__ArrayContentAssignment_3_1 ) ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1975:1: ( ( rule__VarContent__ArrayContentAssignment_3_1 ) )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1975:1: ( ( rule__VarContent__ArrayContentAssignment_3_1 ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1976:1: ( rule__VarContent__ArrayContentAssignment_3_1 )
            {
             before(grammarAccess.getVarContentAccess().getArrayContentAssignment_3_1()); 
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1977:1: ( rule__VarContent__ArrayContentAssignment_3_1 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1977:2: rule__VarContent__ArrayContentAssignment_3_1
            {
            pushFollow(FOLLOW_rule__VarContent__ArrayContentAssignment_3_1_in_rule__VarContent__Group_3__1__Impl4133);
            rule__VarContent__ArrayContentAssignment_3_1();

            state._fsp--;


            }

             after(grammarAccess.getVarContentAccess().getArrayContentAssignment_3_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarContent__Group_3__1__Impl"


    // $ANTLR start "rule__VarContent__Group_3__2"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1987:1: rule__VarContent__Group_3__2 : rule__VarContent__Group_3__2__Impl ;
    public final void rule__VarContent__Group_3__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1991:1: ( rule__VarContent__Group_3__2__Impl )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1992:2: rule__VarContent__Group_3__2__Impl
            {
            pushFollow(FOLLOW_rule__VarContent__Group_3__2__Impl_in_rule__VarContent__Group_3__24163);
            rule__VarContent__Group_3__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarContent__Group_3__2"


    // $ANTLR start "rule__VarContent__Group_3__2__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:1998:1: rule__VarContent__Group_3__2__Impl : ( ( rule__VarContent__Group_3_2__0 )? ) ;
    public final void rule__VarContent__Group_3__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2002:1: ( ( ( rule__VarContent__Group_3_2__0 )? ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2003:1: ( ( rule__VarContent__Group_3_2__0 )? )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2003:1: ( ( rule__VarContent__Group_3_2__0 )? )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2004:1: ( rule__VarContent__Group_3_2__0 )?
            {
             before(grammarAccess.getVarContentAccess().getGroup_3_2()); 
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2005:1: ( rule__VarContent__Group_3_2__0 )?
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( (LA26_0==49) ) {
                alt26=1;
            }
            switch (alt26) {
                case 1 :
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2005:2: rule__VarContent__Group_3_2__0
                    {
                    pushFollow(FOLLOW_rule__VarContent__Group_3_2__0_in_rule__VarContent__Group_3__2__Impl4190);
                    rule__VarContent__Group_3_2__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getVarContentAccess().getGroup_3_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarContent__Group_3__2__Impl"


    // $ANTLR start "rule__VarContent__Group_3_2__0"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2021:1: rule__VarContent__Group_3_2__0 : rule__VarContent__Group_3_2__0__Impl rule__VarContent__Group_3_2__1 ;
    public final void rule__VarContent__Group_3_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2025:1: ( rule__VarContent__Group_3_2__0__Impl rule__VarContent__Group_3_2__1 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2026:2: rule__VarContent__Group_3_2__0__Impl rule__VarContent__Group_3_2__1
            {
            pushFollow(FOLLOW_rule__VarContent__Group_3_2__0__Impl_in_rule__VarContent__Group_3_2__04227);
            rule__VarContent__Group_3_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__VarContent__Group_3_2__1_in_rule__VarContent__Group_3_2__04230);
            rule__VarContent__Group_3_2__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarContent__Group_3_2__0"


    // $ANTLR start "rule__VarContent__Group_3_2__0__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2033:1: rule__VarContent__Group_3_2__0__Impl : ( ( rule__VarContent__ExecuteAssignment_3_2_0 ) ) ;
    public final void rule__VarContent__Group_3_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2037:1: ( ( ( rule__VarContent__ExecuteAssignment_3_2_0 ) ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2038:1: ( ( rule__VarContent__ExecuteAssignment_3_2_0 ) )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2038:1: ( ( rule__VarContent__ExecuteAssignment_3_2_0 ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2039:1: ( rule__VarContent__ExecuteAssignment_3_2_0 )
            {
             before(grammarAccess.getVarContentAccess().getExecuteAssignment_3_2_0()); 
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2040:1: ( rule__VarContent__ExecuteAssignment_3_2_0 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2040:2: rule__VarContent__ExecuteAssignment_3_2_0
            {
            pushFollow(FOLLOW_rule__VarContent__ExecuteAssignment_3_2_0_in_rule__VarContent__Group_3_2__0__Impl4257);
            rule__VarContent__ExecuteAssignment_3_2_0();

            state._fsp--;


            }

             after(grammarAccess.getVarContentAccess().getExecuteAssignment_3_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarContent__Group_3_2__0__Impl"


    // $ANTLR start "rule__VarContent__Group_3_2__1"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2050:1: rule__VarContent__Group_3_2__1 : rule__VarContent__Group_3_2__1__Impl ;
    public final void rule__VarContent__Group_3_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2054:1: ( rule__VarContent__Group_3_2__1__Impl )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2055:2: rule__VarContent__Group_3_2__1__Impl
            {
            pushFollow(FOLLOW_rule__VarContent__Group_3_2__1__Impl_in_rule__VarContent__Group_3_2__14287);
            rule__VarContent__Group_3_2__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarContent__Group_3_2__1"


    // $ANTLR start "rule__VarContent__Group_3_2__1__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2061:1: rule__VarContent__Group_3_2__1__Impl : ( ( rule__VarContent__MethodNameAssignment_3_2_1 ) ) ;
    public final void rule__VarContent__Group_3_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2065:1: ( ( ( rule__VarContent__MethodNameAssignment_3_2_1 ) ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2066:1: ( ( rule__VarContent__MethodNameAssignment_3_2_1 ) )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2066:1: ( ( rule__VarContent__MethodNameAssignment_3_2_1 ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2067:1: ( rule__VarContent__MethodNameAssignment_3_2_1 )
            {
             before(grammarAccess.getVarContentAccess().getMethodNameAssignment_3_2_1()); 
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2068:1: ( rule__VarContent__MethodNameAssignment_3_2_1 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2068:2: rule__VarContent__MethodNameAssignment_3_2_1
            {
            pushFollow(FOLLOW_rule__VarContent__MethodNameAssignment_3_2_1_in_rule__VarContent__Group_3_2__1__Impl4314);
            rule__VarContent__MethodNameAssignment_3_2_1();

            state._fsp--;


            }

             after(grammarAccess.getVarContentAccess().getMethodNameAssignment_3_2_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarContent__Group_3_2__1__Impl"


    // $ANTLR start "rule__VarContent__Group_4__0"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2082:1: rule__VarContent__Group_4__0 : rule__VarContent__Group_4__0__Impl rule__VarContent__Group_4__1 ;
    public final void rule__VarContent__Group_4__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2086:1: ( rule__VarContent__Group_4__0__Impl rule__VarContent__Group_4__1 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2087:2: rule__VarContent__Group_4__0__Impl rule__VarContent__Group_4__1
            {
            pushFollow(FOLLOW_rule__VarContent__Group_4__0__Impl_in_rule__VarContent__Group_4__04348);
            rule__VarContent__Group_4__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__VarContent__Group_4__1_in_rule__VarContent__Group_4__04351);
            rule__VarContent__Group_4__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarContent__Group_4__0"


    // $ANTLR start "rule__VarContent__Group_4__0__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2094:1: rule__VarContent__Group_4__0__Impl : ( ( rule__VarContent__UnOPAssignment_4_0 )? ) ;
    public final void rule__VarContent__Group_4__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2098:1: ( ( ( rule__VarContent__UnOPAssignment_4_0 )? ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2099:1: ( ( rule__VarContent__UnOPAssignment_4_0 )? )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2099:1: ( ( rule__VarContent__UnOPAssignment_4_0 )? )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2100:1: ( rule__VarContent__UnOPAssignment_4_0 )?
            {
             before(grammarAccess.getVarContentAccess().getUnOPAssignment_4_0()); 
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2101:1: ( rule__VarContent__UnOPAssignment_4_0 )?
            int alt27=2;
            int LA27_0 = input.LA(1);

            if ( ((LA27_0>=13 && LA27_0<=14)) ) {
                alt27=1;
            }
            switch (alt27) {
                case 1 :
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2101:2: rule__VarContent__UnOPAssignment_4_0
                    {
                    pushFollow(FOLLOW_rule__VarContent__UnOPAssignment_4_0_in_rule__VarContent__Group_4__0__Impl4378);
                    rule__VarContent__UnOPAssignment_4_0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getVarContentAccess().getUnOPAssignment_4_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarContent__Group_4__0__Impl"


    // $ANTLR start "rule__VarContent__Group_4__1"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2111:1: rule__VarContent__Group_4__1 : rule__VarContent__Group_4__1__Impl rule__VarContent__Group_4__2 ;
    public final void rule__VarContent__Group_4__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2115:1: ( rule__VarContent__Group_4__1__Impl rule__VarContent__Group_4__2 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2116:2: rule__VarContent__Group_4__1__Impl rule__VarContent__Group_4__2
            {
            pushFollow(FOLLOW_rule__VarContent__Group_4__1__Impl_in_rule__VarContent__Group_4__14409);
            rule__VarContent__Group_4__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__VarContent__Group_4__2_in_rule__VarContent__Group_4__14412);
            rule__VarContent__Group_4__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarContent__Group_4__1"


    // $ANTLR start "rule__VarContent__Group_4__1__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2123:1: rule__VarContent__Group_4__1__Impl : ( ( rule__VarContent__EmbracedAssignment_4_1 ) ) ;
    public final void rule__VarContent__Group_4__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2127:1: ( ( ( rule__VarContent__EmbracedAssignment_4_1 ) ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2128:1: ( ( rule__VarContent__EmbracedAssignment_4_1 ) )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2128:1: ( ( rule__VarContent__EmbracedAssignment_4_1 ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2129:1: ( rule__VarContent__EmbracedAssignment_4_1 )
            {
             before(grammarAccess.getVarContentAccess().getEmbracedAssignment_4_1()); 
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2130:1: ( rule__VarContent__EmbracedAssignment_4_1 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2130:2: rule__VarContent__EmbracedAssignment_4_1
            {
            pushFollow(FOLLOW_rule__VarContent__EmbracedAssignment_4_1_in_rule__VarContent__Group_4__1__Impl4439);
            rule__VarContent__EmbracedAssignment_4_1();

            state._fsp--;


            }

             after(grammarAccess.getVarContentAccess().getEmbracedAssignment_4_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarContent__Group_4__1__Impl"


    // $ANTLR start "rule__VarContent__Group_4__2"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2140:1: rule__VarContent__Group_4__2 : rule__VarContent__Group_4__2__Impl rule__VarContent__Group_4__3 ;
    public final void rule__VarContent__Group_4__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2144:1: ( rule__VarContent__Group_4__2__Impl rule__VarContent__Group_4__3 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2145:2: rule__VarContent__Group_4__2__Impl rule__VarContent__Group_4__3
            {
            pushFollow(FOLLOW_rule__VarContent__Group_4__2__Impl_in_rule__VarContent__Group_4__24469);
            rule__VarContent__Group_4__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__VarContent__Group_4__3_in_rule__VarContent__Group_4__24472);
            rule__VarContent__Group_4__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarContent__Group_4__2"


    // $ANTLR start "rule__VarContent__Group_4__2__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2152:1: rule__VarContent__Group_4__2__Impl : ( ( rule__VarContent__EmbrConAssignment_4_2 ) ) ;
    public final void rule__VarContent__Group_4__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2156:1: ( ( ( rule__VarContent__EmbrConAssignment_4_2 ) ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2157:1: ( ( rule__VarContent__EmbrConAssignment_4_2 ) )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2157:1: ( ( rule__VarContent__EmbrConAssignment_4_2 ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2158:1: ( rule__VarContent__EmbrConAssignment_4_2 )
            {
             before(grammarAccess.getVarContentAccess().getEmbrConAssignment_4_2()); 
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2159:1: ( rule__VarContent__EmbrConAssignment_4_2 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2159:2: rule__VarContent__EmbrConAssignment_4_2
            {
            pushFollow(FOLLOW_rule__VarContent__EmbrConAssignment_4_2_in_rule__VarContent__Group_4__2__Impl4499);
            rule__VarContent__EmbrConAssignment_4_2();

            state._fsp--;


            }

             after(grammarAccess.getVarContentAccess().getEmbrConAssignment_4_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarContent__Group_4__2__Impl"


    // $ANTLR start "rule__VarContent__Group_4__3"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2169:1: rule__VarContent__Group_4__3 : rule__VarContent__Group_4__3__Impl ;
    public final void rule__VarContent__Group_4__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2173:1: ( rule__VarContent__Group_4__3__Impl )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2174:2: rule__VarContent__Group_4__3__Impl
            {
            pushFollow(FOLLOW_rule__VarContent__Group_4__3__Impl_in_rule__VarContent__Group_4__34529);
            rule__VarContent__Group_4__3__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarContent__Group_4__3"


    // $ANTLR start "rule__VarContent__Group_4__3__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2180:1: rule__VarContent__Group_4__3__Impl : ( ')' ) ;
    public final void rule__VarContent__Group_4__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2184:1: ( ( ')' ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2185:1: ( ')' )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2185:1: ( ')' )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2186:1: ')'
            {
             before(grammarAccess.getVarContentAccess().getRightParenthesisKeyword_4_3()); 
            match(input,25,FOLLOW_25_in_rule__VarContent__Group_4__3__Impl4557); 
             after(grammarAccess.getVarContentAccess().getRightParenthesisKeyword_4_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarContent__Group_4__3__Impl"


    // $ANTLR start "rule__VarContent__Group_6__0"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2207:1: rule__VarContent__Group_6__0 : rule__VarContent__Group_6__0__Impl rule__VarContent__Group_6__1 ;
    public final void rule__VarContent__Group_6__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2211:1: ( rule__VarContent__Group_6__0__Impl rule__VarContent__Group_6__1 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2212:2: rule__VarContent__Group_6__0__Impl rule__VarContent__Group_6__1
            {
            pushFollow(FOLLOW_rule__VarContent__Group_6__0__Impl_in_rule__VarContent__Group_6__04596);
            rule__VarContent__Group_6__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__VarContent__Group_6__1_in_rule__VarContent__Group_6__04599);
            rule__VarContent__Group_6__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarContent__Group_6__0"


    // $ANTLR start "rule__VarContent__Group_6__0__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2219:1: rule__VarContent__Group_6__0__Impl : ( ( rule__VarContent__ParamAssignment_6_0 ) ) ;
    public final void rule__VarContent__Group_6__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2223:1: ( ( ( rule__VarContent__ParamAssignment_6_0 ) ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2224:1: ( ( rule__VarContent__ParamAssignment_6_0 ) )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2224:1: ( ( rule__VarContent__ParamAssignment_6_0 ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2225:1: ( rule__VarContent__ParamAssignment_6_0 )
            {
             before(grammarAccess.getVarContentAccess().getParamAssignment_6_0()); 
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2226:1: ( rule__VarContent__ParamAssignment_6_0 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2226:2: rule__VarContent__ParamAssignment_6_0
            {
            pushFollow(FOLLOW_rule__VarContent__ParamAssignment_6_0_in_rule__VarContent__Group_6__0__Impl4626);
            rule__VarContent__ParamAssignment_6_0();

            state._fsp--;


            }

             after(grammarAccess.getVarContentAccess().getParamAssignment_6_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarContent__Group_6__0__Impl"


    // $ANTLR start "rule__VarContent__Group_6__1"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2236:1: rule__VarContent__Group_6__1 : rule__VarContent__Group_6__1__Impl ;
    public final void rule__VarContent__Group_6__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2240:1: ( rule__VarContent__Group_6__1__Impl )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2241:2: rule__VarContent__Group_6__1__Impl
            {
            pushFollow(FOLLOW_rule__VarContent__Group_6__1__Impl_in_rule__VarContent__Group_6__14656);
            rule__VarContent__Group_6__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarContent__Group_6__1"


    // $ANTLR start "rule__VarContent__Group_6__1__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2247:1: rule__VarContent__Group_6__1__Impl : ( ( rule__VarContent__Group_6_1__0 )? ) ;
    public final void rule__VarContent__Group_6__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2251:1: ( ( ( rule__VarContent__Group_6_1__0 )? ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2252:1: ( ( rule__VarContent__Group_6_1__0 )? )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2252:1: ( ( rule__VarContent__Group_6_1__0 )? )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2253:1: ( rule__VarContent__Group_6_1__0 )?
            {
             before(grammarAccess.getVarContentAccess().getGroup_6_1()); 
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2254:1: ( rule__VarContent__Group_6_1__0 )?
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( (LA28_0==26) ) {
                alt28=1;
            }
            switch (alt28) {
                case 1 :
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2254:2: rule__VarContent__Group_6_1__0
                    {
                    pushFollow(FOLLOW_rule__VarContent__Group_6_1__0_in_rule__VarContent__Group_6__1__Impl4683);
                    rule__VarContent__Group_6_1__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getVarContentAccess().getGroup_6_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarContent__Group_6__1__Impl"


    // $ANTLR start "rule__VarContent__Group_6_1__0"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2268:1: rule__VarContent__Group_6_1__0 : rule__VarContent__Group_6_1__0__Impl rule__VarContent__Group_6_1__1 ;
    public final void rule__VarContent__Group_6_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2272:1: ( rule__VarContent__Group_6_1__0__Impl rule__VarContent__Group_6_1__1 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2273:2: rule__VarContent__Group_6_1__0__Impl rule__VarContent__Group_6_1__1
            {
            pushFollow(FOLLOW_rule__VarContent__Group_6_1__0__Impl_in_rule__VarContent__Group_6_1__04718);
            rule__VarContent__Group_6_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__VarContent__Group_6_1__1_in_rule__VarContent__Group_6_1__04721);
            rule__VarContent__Group_6_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarContent__Group_6_1__0"


    // $ANTLR start "rule__VarContent__Group_6_1__0__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2280:1: rule__VarContent__Group_6_1__0__Impl : ( 'select' ) ;
    public final void rule__VarContent__Group_6_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2284:1: ( ( 'select' ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2285:1: ( 'select' )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2285:1: ( 'select' )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2286:1: 'select'
            {
             before(grammarAccess.getVarContentAccess().getSelectKeyword_6_1_0()); 
            match(input,26,FOLLOW_26_in_rule__VarContent__Group_6_1__0__Impl4749); 
             after(grammarAccess.getVarContentAccess().getSelectKeyword_6_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarContent__Group_6_1__0__Impl"


    // $ANTLR start "rule__VarContent__Group_6_1__1"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2299:1: rule__VarContent__Group_6_1__1 : rule__VarContent__Group_6_1__1__Impl ;
    public final void rule__VarContent__Group_6_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2303:1: ( rule__VarContent__Group_6_1__1__Impl )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2304:2: rule__VarContent__Group_6_1__1__Impl
            {
            pushFollow(FOLLOW_rule__VarContent__Group_6_1__1__Impl_in_rule__VarContent__Group_6_1__14780);
            rule__VarContent__Group_6_1__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarContent__Group_6_1__1"


    // $ANTLR start "rule__VarContent__Group_6_1__1__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2310:1: rule__VarContent__Group_6_1__1__Impl : ( ( rule__VarContent__IndexAssignment_6_1_1 ) ) ;
    public final void rule__VarContent__Group_6_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2314:1: ( ( ( rule__VarContent__IndexAssignment_6_1_1 ) ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2315:1: ( ( rule__VarContent__IndexAssignment_6_1_1 ) )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2315:1: ( ( rule__VarContent__IndexAssignment_6_1_1 ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2316:1: ( rule__VarContent__IndexAssignment_6_1_1 )
            {
             before(grammarAccess.getVarContentAccess().getIndexAssignment_6_1_1()); 
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2317:1: ( rule__VarContent__IndexAssignment_6_1_1 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2317:2: rule__VarContent__IndexAssignment_6_1_1
            {
            pushFollow(FOLLOW_rule__VarContent__IndexAssignment_6_1_1_in_rule__VarContent__Group_6_1__1__Impl4807);
            rule__VarContent__IndexAssignment_6_1_1();

            state._fsp--;


            }

             after(grammarAccess.getVarContentAccess().getIndexAssignment_6_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarContent__Group_6_1__1__Impl"


    // $ANTLR start "rule__ArrayLiteral__Group__0"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2331:1: rule__ArrayLiteral__Group__0 : rule__ArrayLiteral__Group__0__Impl rule__ArrayLiteral__Group__1 ;
    public final void rule__ArrayLiteral__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2335:1: ( rule__ArrayLiteral__Group__0__Impl rule__ArrayLiteral__Group__1 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2336:2: rule__ArrayLiteral__Group__0__Impl rule__ArrayLiteral__Group__1
            {
            pushFollow(FOLLOW_rule__ArrayLiteral__Group__0__Impl_in_rule__ArrayLiteral__Group__04841);
            rule__ArrayLiteral__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ArrayLiteral__Group__1_in_rule__ArrayLiteral__Group__04844);
            rule__ArrayLiteral__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ArrayLiteral__Group__0"


    // $ANTLR start "rule__ArrayLiteral__Group__0__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2343:1: rule__ArrayLiteral__Group__0__Impl : ( ( rule__ArrayLiteral__ConAssignment_0 ) ) ;
    public final void rule__ArrayLiteral__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2347:1: ( ( ( rule__ArrayLiteral__ConAssignment_0 ) ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2348:1: ( ( rule__ArrayLiteral__ConAssignment_0 ) )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2348:1: ( ( rule__ArrayLiteral__ConAssignment_0 ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2349:1: ( rule__ArrayLiteral__ConAssignment_0 )
            {
             before(grammarAccess.getArrayLiteralAccess().getConAssignment_0()); 
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2350:1: ( rule__ArrayLiteral__ConAssignment_0 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2350:2: rule__ArrayLiteral__ConAssignment_0
            {
            pushFollow(FOLLOW_rule__ArrayLiteral__ConAssignment_0_in_rule__ArrayLiteral__Group__0__Impl4871);
            rule__ArrayLiteral__ConAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getArrayLiteralAccess().getConAssignment_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ArrayLiteral__Group__0__Impl"


    // $ANTLR start "rule__ArrayLiteral__Group__1"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2360:1: rule__ArrayLiteral__Group__1 : rule__ArrayLiteral__Group__1__Impl rule__ArrayLiteral__Group__2 ;
    public final void rule__ArrayLiteral__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2364:1: ( rule__ArrayLiteral__Group__1__Impl rule__ArrayLiteral__Group__2 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2365:2: rule__ArrayLiteral__Group__1__Impl rule__ArrayLiteral__Group__2
            {
            pushFollow(FOLLOW_rule__ArrayLiteral__Group__1__Impl_in_rule__ArrayLiteral__Group__14901);
            rule__ArrayLiteral__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ArrayLiteral__Group__2_in_rule__ArrayLiteral__Group__14904);
            rule__ArrayLiteral__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ArrayLiteral__Group__1"


    // $ANTLR start "rule__ArrayLiteral__Group__1__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2372:1: rule__ArrayLiteral__Group__1__Impl : ( ( rule__ArrayLiteral__Group_1__0 )? ) ;
    public final void rule__ArrayLiteral__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2376:1: ( ( ( rule__ArrayLiteral__Group_1__0 )? ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2377:1: ( ( rule__ArrayLiteral__Group_1__0 )? )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2377:1: ( ( rule__ArrayLiteral__Group_1__0 )? )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2378:1: ( rule__ArrayLiteral__Group_1__0 )?
            {
             before(grammarAccess.getArrayLiteralAccess().getGroup_1()); 
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2379:1: ( rule__ArrayLiteral__Group_1__0 )?
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( (LA29_0==RULE_ID||(LA29_0>=RULE_NUMBER && LA29_0<=RULE_STRING)||(LA29_0>=13 && LA29_0<=14)||(LA29_0>=19 && LA29_0<=22)||LA29_0==30||LA29_0==35||LA29_0==48||LA29_0==50) ) {
                alt29=1;
            }
            switch (alt29) {
                case 1 :
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2379:2: rule__ArrayLiteral__Group_1__0
                    {
                    pushFollow(FOLLOW_rule__ArrayLiteral__Group_1__0_in_rule__ArrayLiteral__Group__1__Impl4931);
                    rule__ArrayLiteral__Group_1__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getArrayLiteralAccess().getGroup_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ArrayLiteral__Group__1__Impl"


    // $ANTLR start "rule__ArrayLiteral__Group__2"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2389:1: rule__ArrayLiteral__Group__2 : rule__ArrayLiteral__Group__2__Impl ;
    public final void rule__ArrayLiteral__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2393:1: ( rule__ArrayLiteral__Group__2__Impl )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2394:2: rule__ArrayLiteral__Group__2__Impl
            {
            pushFollow(FOLLOW_rule__ArrayLiteral__Group__2__Impl_in_rule__ArrayLiteral__Group__24962);
            rule__ArrayLiteral__Group__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ArrayLiteral__Group__2"


    // $ANTLR start "rule__ArrayLiteral__Group__2__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2400:1: rule__ArrayLiteral__Group__2__Impl : ( ']' ) ;
    public final void rule__ArrayLiteral__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2404:1: ( ( ']' ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2405:1: ( ']' )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2405:1: ( ']' )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2406:1: ']'
            {
             before(grammarAccess.getArrayLiteralAccess().getRightSquareBracketKeyword_2()); 
            match(input,27,FOLLOW_27_in_rule__ArrayLiteral__Group__2__Impl4990); 
             after(grammarAccess.getArrayLiteralAccess().getRightSquareBracketKeyword_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ArrayLiteral__Group__2__Impl"


    // $ANTLR start "rule__ArrayLiteral__Group_1__0"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2425:1: rule__ArrayLiteral__Group_1__0 : rule__ArrayLiteral__Group_1__0__Impl rule__ArrayLiteral__Group_1__1 ;
    public final void rule__ArrayLiteral__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2429:1: ( rule__ArrayLiteral__Group_1__0__Impl rule__ArrayLiteral__Group_1__1 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2430:2: rule__ArrayLiteral__Group_1__0__Impl rule__ArrayLiteral__Group_1__1
            {
            pushFollow(FOLLOW_rule__ArrayLiteral__Group_1__0__Impl_in_rule__ArrayLiteral__Group_1__05027);
            rule__ArrayLiteral__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ArrayLiteral__Group_1__1_in_rule__ArrayLiteral__Group_1__05030);
            rule__ArrayLiteral__Group_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ArrayLiteral__Group_1__0"


    // $ANTLR start "rule__ArrayLiteral__Group_1__0__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2437:1: rule__ArrayLiteral__Group_1__0__Impl : ( ( rule__ArrayLiteral__ContentAssignment_1_0 ) ) ;
    public final void rule__ArrayLiteral__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2441:1: ( ( ( rule__ArrayLiteral__ContentAssignment_1_0 ) ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2442:1: ( ( rule__ArrayLiteral__ContentAssignment_1_0 ) )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2442:1: ( ( rule__ArrayLiteral__ContentAssignment_1_0 ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2443:1: ( rule__ArrayLiteral__ContentAssignment_1_0 )
            {
             before(grammarAccess.getArrayLiteralAccess().getContentAssignment_1_0()); 
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2444:1: ( rule__ArrayLiteral__ContentAssignment_1_0 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2444:2: rule__ArrayLiteral__ContentAssignment_1_0
            {
            pushFollow(FOLLOW_rule__ArrayLiteral__ContentAssignment_1_0_in_rule__ArrayLiteral__Group_1__0__Impl5057);
            rule__ArrayLiteral__ContentAssignment_1_0();

            state._fsp--;


            }

             after(grammarAccess.getArrayLiteralAccess().getContentAssignment_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ArrayLiteral__Group_1__0__Impl"


    // $ANTLR start "rule__ArrayLiteral__Group_1__1"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2454:1: rule__ArrayLiteral__Group_1__1 : rule__ArrayLiteral__Group_1__1__Impl ;
    public final void rule__ArrayLiteral__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2458:1: ( rule__ArrayLiteral__Group_1__1__Impl )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2459:2: rule__ArrayLiteral__Group_1__1__Impl
            {
            pushFollow(FOLLOW_rule__ArrayLiteral__Group_1__1__Impl_in_rule__ArrayLiteral__Group_1__15087);
            rule__ArrayLiteral__Group_1__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ArrayLiteral__Group_1__1"


    // $ANTLR start "rule__ArrayLiteral__Group_1__1__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2465:1: rule__ArrayLiteral__Group_1__1__Impl : ( ( rule__ArrayLiteral__Group_1_1__0 )* ) ;
    public final void rule__ArrayLiteral__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2469:1: ( ( ( rule__ArrayLiteral__Group_1_1__0 )* ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2470:1: ( ( rule__ArrayLiteral__Group_1_1__0 )* )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2470:1: ( ( rule__ArrayLiteral__Group_1_1__0 )* )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2471:1: ( rule__ArrayLiteral__Group_1_1__0 )*
            {
             before(grammarAccess.getArrayLiteralAccess().getGroup_1_1()); 
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2472:1: ( rule__ArrayLiteral__Group_1_1__0 )*
            loop30:
            do {
                int alt30=2;
                int LA30_0 = input.LA(1);

                if ( (LA30_0==28) ) {
                    alt30=1;
                }


                switch (alt30) {
            	case 1 :
            	    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2472:2: rule__ArrayLiteral__Group_1_1__0
            	    {
            	    pushFollow(FOLLOW_rule__ArrayLiteral__Group_1_1__0_in_rule__ArrayLiteral__Group_1__1__Impl5114);
            	    rule__ArrayLiteral__Group_1_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop30;
                }
            } while (true);

             after(grammarAccess.getArrayLiteralAccess().getGroup_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ArrayLiteral__Group_1__1__Impl"


    // $ANTLR start "rule__ArrayLiteral__Group_1_1__0"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2486:1: rule__ArrayLiteral__Group_1_1__0 : rule__ArrayLiteral__Group_1_1__0__Impl rule__ArrayLiteral__Group_1_1__1 ;
    public final void rule__ArrayLiteral__Group_1_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2490:1: ( rule__ArrayLiteral__Group_1_1__0__Impl rule__ArrayLiteral__Group_1_1__1 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2491:2: rule__ArrayLiteral__Group_1_1__0__Impl rule__ArrayLiteral__Group_1_1__1
            {
            pushFollow(FOLLOW_rule__ArrayLiteral__Group_1_1__0__Impl_in_rule__ArrayLiteral__Group_1_1__05149);
            rule__ArrayLiteral__Group_1_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ArrayLiteral__Group_1_1__1_in_rule__ArrayLiteral__Group_1_1__05152);
            rule__ArrayLiteral__Group_1_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ArrayLiteral__Group_1_1__0"


    // $ANTLR start "rule__ArrayLiteral__Group_1_1__0__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2498:1: rule__ArrayLiteral__Group_1_1__0__Impl : ( ',' ) ;
    public final void rule__ArrayLiteral__Group_1_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2502:1: ( ( ',' ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2503:1: ( ',' )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2503:1: ( ',' )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2504:1: ','
            {
             before(grammarAccess.getArrayLiteralAccess().getCommaKeyword_1_1_0()); 
            match(input,28,FOLLOW_28_in_rule__ArrayLiteral__Group_1_1__0__Impl5180); 
             after(grammarAccess.getArrayLiteralAccess().getCommaKeyword_1_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ArrayLiteral__Group_1_1__0__Impl"


    // $ANTLR start "rule__ArrayLiteral__Group_1_1__1"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2517:1: rule__ArrayLiteral__Group_1_1__1 : rule__ArrayLiteral__Group_1_1__1__Impl ;
    public final void rule__ArrayLiteral__Group_1_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2521:1: ( rule__ArrayLiteral__Group_1_1__1__Impl )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2522:2: rule__ArrayLiteral__Group_1_1__1__Impl
            {
            pushFollow(FOLLOW_rule__ArrayLiteral__Group_1_1__1__Impl_in_rule__ArrayLiteral__Group_1_1__15211);
            rule__ArrayLiteral__Group_1_1__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ArrayLiteral__Group_1_1__1"


    // $ANTLR start "rule__ArrayLiteral__Group_1_1__1__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2528:1: rule__ArrayLiteral__Group_1_1__1__Impl : ( ( rule__ArrayLiteral__NextContentAssignment_1_1_1 ) ) ;
    public final void rule__ArrayLiteral__Group_1_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2532:1: ( ( ( rule__ArrayLiteral__NextContentAssignment_1_1_1 ) ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2533:1: ( ( rule__ArrayLiteral__NextContentAssignment_1_1_1 ) )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2533:1: ( ( rule__ArrayLiteral__NextContentAssignment_1_1_1 ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2534:1: ( rule__ArrayLiteral__NextContentAssignment_1_1_1 )
            {
             before(grammarAccess.getArrayLiteralAccess().getNextContentAssignment_1_1_1()); 
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2535:1: ( rule__ArrayLiteral__NextContentAssignment_1_1_1 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2535:2: rule__ArrayLiteral__NextContentAssignment_1_1_1
            {
            pushFollow(FOLLOW_rule__ArrayLiteral__NextContentAssignment_1_1_1_in_rule__ArrayLiteral__Group_1_1__1__Impl5238);
            rule__ArrayLiteral__NextContentAssignment_1_1_1();

            state._fsp--;


            }

             after(grammarAccess.getArrayLiteralAccess().getNextContentAssignment_1_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ArrayLiteral__Group_1_1__1__Impl"


    // $ANTLR start "rule__IfType__Group__0"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2549:1: rule__IfType__Group__0 : rule__IfType__Group__0__Impl rule__IfType__Group__1 ;
    public final void rule__IfType__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2553:1: ( rule__IfType__Group__0__Impl rule__IfType__Group__1 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2554:2: rule__IfType__Group__0__Impl rule__IfType__Group__1
            {
            pushFollow(FOLLOW_rule__IfType__Group__0__Impl_in_rule__IfType__Group__05272);
            rule__IfType__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__IfType__Group__1_in_rule__IfType__Group__05275);
            rule__IfType__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfType__Group__0"


    // $ANTLR start "rule__IfType__Group__0__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2561:1: rule__IfType__Group__0__Impl : ( 'if' ) ;
    public final void rule__IfType__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2565:1: ( ( 'if' ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2566:1: ( 'if' )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2566:1: ( 'if' )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2567:1: 'if'
            {
             before(grammarAccess.getIfTypeAccess().getIfKeyword_0()); 
            match(input,29,FOLLOW_29_in_rule__IfType__Group__0__Impl5303); 
             after(grammarAccess.getIfTypeAccess().getIfKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfType__Group__0__Impl"


    // $ANTLR start "rule__IfType__Group__1"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2580:1: rule__IfType__Group__1 : rule__IfType__Group__1__Impl rule__IfType__Group__2 ;
    public final void rule__IfType__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2584:1: ( rule__IfType__Group__1__Impl rule__IfType__Group__2 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2585:2: rule__IfType__Group__1__Impl rule__IfType__Group__2
            {
            pushFollow(FOLLOW_rule__IfType__Group__1__Impl_in_rule__IfType__Group__15334);
            rule__IfType__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__IfType__Group__2_in_rule__IfType__Group__15337);
            rule__IfType__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfType__Group__1"


    // $ANTLR start "rule__IfType__Group__1__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2592:1: rule__IfType__Group__1__Impl : ( '(' ) ;
    public final void rule__IfType__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2596:1: ( ( '(' ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2597:1: ( '(' )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2597:1: ( '(' )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2598:1: '('
            {
             before(grammarAccess.getIfTypeAccess().getLeftParenthesisKeyword_1()); 
            match(input,30,FOLLOW_30_in_rule__IfType__Group__1__Impl5365); 
             after(grammarAccess.getIfTypeAccess().getLeftParenthesisKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfType__Group__1__Impl"


    // $ANTLR start "rule__IfType__Group__2"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2611:1: rule__IfType__Group__2 : rule__IfType__Group__2__Impl rule__IfType__Group__3 ;
    public final void rule__IfType__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2615:1: ( rule__IfType__Group__2__Impl rule__IfType__Group__3 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2616:2: rule__IfType__Group__2__Impl rule__IfType__Group__3
            {
            pushFollow(FOLLOW_rule__IfType__Group__2__Impl_in_rule__IfType__Group__25396);
            rule__IfType__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__IfType__Group__3_in_rule__IfType__Group__25399);
            rule__IfType__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfType__Group__2"


    // $ANTLR start "rule__IfType__Group__2__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2623:1: rule__IfType__Group__2__Impl : ( ( rule__IfType__ConditionAssignment_2 ) ) ;
    public final void rule__IfType__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2627:1: ( ( ( rule__IfType__ConditionAssignment_2 ) ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2628:1: ( ( rule__IfType__ConditionAssignment_2 ) )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2628:1: ( ( rule__IfType__ConditionAssignment_2 ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2629:1: ( rule__IfType__ConditionAssignment_2 )
            {
             before(grammarAccess.getIfTypeAccess().getConditionAssignment_2()); 
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2630:1: ( rule__IfType__ConditionAssignment_2 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2630:2: rule__IfType__ConditionAssignment_2
            {
            pushFollow(FOLLOW_rule__IfType__ConditionAssignment_2_in_rule__IfType__Group__2__Impl5426);
            rule__IfType__ConditionAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getIfTypeAccess().getConditionAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfType__Group__2__Impl"


    // $ANTLR start "rule__IfType__Group__3"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2640:1: rule__IfType__Group__3 : rule__IfType__Group__3__Impl rule__IfType__Group__4 ;
    public final void rule__IfType__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2644:1: ( rule__IfType__Group__3__Impl rule__IfType__Group__4 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2645:2: rule__IfType__Group__3__Impl rule__IfType__Group__4
            {
            pushFollow(FOLLOW_rule__IfType__Group__3__Impl_in_rule__IfType__Group__35456);
            rule__IfType__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__IfType__Group__4_in_rule__IfType__Group__35459);
            rule__IfType__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfType__Group__3"


    // $ANTLR start "rule__IfType__Group__3__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2652:1: rule__IfType__Group__3__Impl : ( ')' ) ;
    public final void rule__IfType__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2656:1: ( ( ')' ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2657:1: ( ')' )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2657:1: ( ')' )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2658:1: ')'
            {
             before(grammarAccess.getIfTypeAccess().getRightParenthesisKeyword_3()); 
            match(input,25,FOLLOW_25_in_rule__IfType__Group__3__Impl5487); 
             after(grammarAccess.getIfTypeAccess().getRightParenthesisKeyword_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfType__Group__3__Impl"


    // $ANTLR start "rule__IfType__Group__4"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2671:1: rule__IfType__Group__4 : rule__IfType__Group__4__Impl ;
    public final void rule__IfType__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2675:1: ( rule__IfType__Group__4__Impl )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2676:2: rule__IfType__Group__4__Impl
            {
            pushFollow(FOLLOW_rule__IfType__Group__4__Impl_in_rule__IfType__Group__45518);
            rule__IfType__Group__4__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfType__Group__4"


    // $ANTLR start "rule__IfType__Group__4__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2682:1: rule__IfType__Group__4__Impl : ( ( rule__IfType__Alternatives_4 ) ) ;
    public final void rule__IfType__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2686:1: ( ( ( rule__IfType__Alternatives_4 ) ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2687:1: ( ( rule__IfType__Alternatives_4 ) )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2687:1: ( ( rule__IfType__Alternatives_4 ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2688:1: ( rule__IfType__Alternatives_4 )
            {
             before(grammarAccess.getIfTypeAccess().getAlternatives_4()); 
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2689:1: ( rule__IfType__Alternatives_4 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2689:2: rule__IfType__Alternatives_4
            {
            pushFollow(FOLLOW_rule__IfType__Alternatives_4_in_rule__IfType__Group__4__Impl5545);
            rule__IfType__Alternatives_4();

            state._fsp--;


            }

             after(grammarAccess.getIfTypeAccess().getAlternatives_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfType__Group__4__Impl"


    // $ANTLR start "rule__IfType__Group_4_0__0"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2709:1: rule__IfType__Group_4_0__0 : rule__IfType__Group_4_0__0__Impl rule__IfType__Group_4_0__1 ;
    public final void rule__IfType__Group_4_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2713:1: ( rule__IfType__Group_4_0__0__Impl rule__IfType__Group_4_0__1 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2714:2: rule__IfType__Group_4_0__0__Impl rule__IfType__Group_4_0__1
            {
            pushFollow(FOLLOW_rule__IfType__Group_4_0__0__Impl_in_rule__IfType__Group_4_0__05585);
            rule__IfType__Group_4_0__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__IfType__Group_4_0__1_in_rule__IfType__Group_4_0__05588);
            rule__IfType__Group_4_0__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfType__Group_4_0__0"


    // $ANTLR start "rule__IfType__Group_4_0__0__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2721:1: rule__IfType__Group_4_0__0__Impl : ( 'then' ) ;
    public final void rule__IfType__Group_4_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2725:1: ( ( 'then' ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2726:1: ( 'then' )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2726:1: ( 'then' )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2727:1: 'then'
            {
             before(grammarAccess.getIfTypeAccess().getThenKeyword_4_0_0()); 
            match(input,31,FOLLOW_31_in_rule__IfType__Group_4_0__0__Impl5616); 
             after(grammarAccess.getIfTypeAccess().getThenKeyword_4_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfType__Group_4_0__0__Impl"


    // $ANTLR start "rule__IfType__Group_4_0__1"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2740:1: rule__IfType__Group_4_0__1 : rule__IfType__Group_4_0__1__Impl ;
    public final void rule__IfType__Group_4_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2744:1: ( rule__IfType__Group_4_0__1__Impl )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2745:2: rule__IfType__Group_4_0__1__Impl
            {
            pushFollow(FOLLOW_rule__IfType__Group_4_0__1__Impl_in_rule__IfType__Group_4_0__15647);
            rule__IfType__Group_4_0__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfType__Group_4_0__1"


    // $ANTLR start "rule__IfType__Group_4_0__1__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2751:1: rule__IfType__Group_4_0__1__Impl : ( ( rule__IfType__Alternatives_4_0_1 ) ) ;
    public final void rule__IfType__Group_4_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2755:1: ( ( ( rule__IfType__Alternatives_4_0_1 ) ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2756:1: ( ( rule__IfType__Alternatives_4_0_1 ) )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2756:1: ( ( rule__IfType__Alternatives_4_0_1 ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2757:1: ( rule__IfType__Alternatives_4_0_1 )
            {
             before(grammarAccess.getIfTypeAccess().getAlternatives_4_0_1()); 
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2758:1: ( rule__IfType__Alternatives_4_0_1 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2758:2: rule__IfType__Alternatives_4_0_1
            {
            pushFollow(FOLLOW_rule__IfType__Alternatives_4_0_1_in_rule__IfType__Group_4_0__1__Impl5674);
            rule__IfType__Alternatives_4_0_1();

            state._fsp--;


            }

             after(grammarAccess.getIfTypeAccess().getAlternatives_4_0_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfType__Group_4_0__1__Impl"


    // $ANTLR start "rule__IfType__Group_4_0_1_0__0"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2772:1: rule__IfType__Group_4_0_1_0__0 : rule__IfType__Group_4_0_1_0__0__Impl rule__IfType__Group_4_0_1_0__1 ;
    public final void rule__IfType__Group_4_0_1_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2776:1: ( rule__IfType__Group_4_0_1_0__0__Impl rule__IfType__Group_4_0_1_0__1 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2777:2: rule__IfType__Group_4_0_1_0__0__Impl rule__IfType__Group_4_0_1_0__1
            {
            pushFollow(FOLLOW_rule__IfType__Group_4_0_1_0__0__Impl_in_rule__IfType__Group_4_0_1_0__05708);
            rule__IfType__Group_4_0_1_0__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__IfType__Group_4_0_1_0__1_in_rule__IfType__Group_4_0_1_0__05711);
            rule__IfType__Group_4_0_1_0__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfType__Group_4_0_1_0__0"


    // $ANTLR start "rule__IfType__Group_4_0_1_0__0__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2784:1: rule__IfType__Group_4_0_1_0__0__Impl : ( '{' ) ;
    public final void rule__IfType__Group_4_0_1_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2788:1: ( ( '{' ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2789:1: ( '{' )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2789:1: ( '{' )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2790:1: '{'
            {
             before(grammarAccess.getIfTypeAccess().getLeftCurlyBracketKeyword_4_0_1_0_0()); 
            match(input,32,FOLLOW_32_in_rule__IfType__Group_4_0_1_0__0__Impl5739); 
             after(grammarAccess.getIfTypeAccess().getLeftCurlyBracketKeyword_4_0_1_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfType__Group_4_0_1_0__0__Impl"


    // $ANTLR start "rule__IfType__Group_4_0_1_0__1"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2803:1: rule__IfType__Group_4_0_1_0__1 : rule__IfType__Group_4_0_1_0__1__Impl rule__IfType__Group_4_0_1_0__2 ;
    public final void rule__IfType__Group_4_0_1_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2807:1: ( rule__IfType__Group_4_0_1_0__1__Impl rule__IfType__Group_4_0_1_0__2 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2808:2: rule__IfType__Group_4_0_1_0__1__Impl rule__IfType__Group_4_0_1_0__2
            {
            pushFollow(FOLLOW_rule__IfType__Group_4_0_1_0__1__Impl_in_rule__IfType__Group_4_0_1_0__15770);
            rule__IfType__Group_4_0_1_0__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__IfType__Group_4_0_1_0__2_in_rule__IfType__Group_4_0_1_0__15773);
            rule__IfType__Group_4_0_1_0__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfType__Group_4_0_1_0__1"


    // $ANTLR start "rule__IfType__Group_4_0_1_0__1__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2815:1: rule__IfType__Group_4_0_1_0__1__Impl : ( ( rule__IfType__ThenCodeAssignment_4_0_1_0_1 )* ) ;
    public final void rule__IfType__Group_4_0_1_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2819:1: ( ( ( rule__IfType__ThenCodeAssignment_4_0_1_0_1 )* ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2820:1: ( ( rule__IfType__ThenCodeAssignment_4_0_1_0_1 )* )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2820:1: ( ( rule__IfType__ThenCodeAssignment_4_0_1_0_1 )* )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2821:1: ( rule__IfType__ThenCodeAssignment_4_0_1_0_1 )*
            {
             before(grammarAccess.getIfTypeAccess().getThenCodeAssignment_4_0_1_0_1()); 
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2822:1: ( rule__IfType__ThenCodeAssignment_4_0_1_0_1 )*
            loop31:
            do {
                int alt31=2;
                int LA31_0 = input.LA(1);

                if ( (LA31_0==RULE_ID||LA31_0==29||LA31_0==32||LA31_0==35||LA31_0==37||LA31_0==39||LA31_0==44) ) {
                    alt31=1;
                }


                switch (alt31) {
            	case 1 :
            	    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2822:2: rule__IfType__ThenCodeAssignment_4_0_1_0_1
            	    {
            	    pushFollow(FOLLOW_rule__IfType__ThenCodeAssignment_4_0_1_0_1_in_rule__IfType__Group_4_0_1_0__1__Impl5800);
            	    rule__IfType__ThenCodeAssignment_4_0_1_0_1();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop31;
                }
            } while (true);

             after(grammarAccess.getIfTypeAccess().getThenCodeAssignment_4_0_1_0_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfType__Group_4_0_1_0__1__Impl"


    // $ANTLR start "rule__IfType__Group_4_0_1_0__2"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2832:1: rule__IfType__Group_4_0_1_0__2 : rule__IfType__Group_4_0_1_0__2__Impl rule__IfType__Group_4_0_1_0__3 ;
    public final void rule__IfType__Group_4_0_1_0__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2836:1: ( rule__IfType__Group_4_0_1_0__2__Impl rule__IfType__Group_4_0_1_0__3 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2837:2: rule__IfType__Group_4_0_1_0__2__Impl rule__IfType__Group_4_0_1_0__3
            {
            pushFollow(FOLLOW_rule__IfType__Group_4_0_1_0__2__Impl_in_rule__IfType__Group_4_0_1_0__25831);
            rule__IfType__Group_4_0_1_0__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__IfType__Group_4_0_1_0__3_in_rule__IfType__Group_4_0_1_0__25834);
            rule__IfType__Group_4_0_1_0__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfType__Group_4_0_1_0__2"


    // $ANTLR start "rule__IfType__Group_4_0_1_0__2__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2844:1: rule__IfType__Group_4_0_1_0__2__Impl : ( '}' ) ;
    public final void rule__IfType__Group_4_0_1_0__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2848:1: ( ( '}' ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2849:1: ( '}' )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2849:1: ( '}' )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2850:1: '}'
            {
             before(grammarAccess.getIfTypeAccess().getRightCurlyBracketKeyword_4_0_1_0_2()); 
            match(input,33,FOLLOW_33_in_rule__IfType__Group_4_0_1_0__2__Impl5862); 
             after(grammarAccess.getIfTypeAccess().getRightCurlyBracketKeyword_4_0_1_0_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfType__Group_4_0_1_0__2__Impl"


    // $ANTLR start "rule__IfType__Group_4_0_1_0__3"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2863:1: rule__IfType__Group_4_0_1_0__3 : rule__IfType__Group_4_0_1_0__3__Impl ;
    public final void rule__IfType__Group_4_0_1_0__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2867:1: ( rule__IfType__Group_4_0_1_0__3__Impl )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2868:2: rule__IfType__Group_4_0_1_0__3__Impl
            {
            pushFollow(FOLLOW_rule__IfType__Group_4_0_1_0__3__Impl_in_rule__IfType__Group_4_0_1_0__35893);
            rule__IfType__Group_4_0_1_0__3__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfType__Group_4_0_1_0__3"


    // $ANTLR start "rule__IfType__Group_4_0_1_0__3__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2874:1: rule__IfType__Group_4_0_1_0__3__Impl : ( ( rule__IfType__Group_4_0_1_0_3__0 )? ) ;
    public final void rule__IfType__Group_4_0_1_0__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2878:1: ( ( ( rule__IfType__Group_4_0_1_0_3__0 )? ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2879:1: ( ( rule__IfType__Group_4_0_1_0_3__0 )? )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2879:1: ( ( rule__IfType__Group_4_0_1_0_3__0 )? )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2880:1: ( rule__IfType__Group_4_0_1_0_3__0 )?
            {
             before(grammarAccess.getIfTypeAccess().getGroup_4_0_1_0_3()); 
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2881:1: ( rule__IfType__Group_4_0_1_0_3__0 )?
            int alt32=2;
            int LA32_0 = input.LA(1);

            if ( (LA32_0==34) ) {
                alt32=1;
            }
            switch (alt32) {
                case 1 :
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2881:2: rule__IfType__Group_4_0_1_0_3__0
                    {
                    pushFollow(FOLLOW_rule__IfType__Group_4_0_1_0_3__0_in_rule__IfType__Group_4_0_1_0__3__Impl5920);
                    rule__IfType__Group_4_0_1_0_3__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getIfTypeAccess().getGroup_4_0_1_0_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfType__Group_4_0_1_0__3__Impl"


    // $ANTLR start "rule__IfType__Group_4_0_1_0_3__0"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2899:1: rule__IfType__Group_4_0_1_0_3__0 : rule__IfType__Group_4_0_1_0_3__0__Impl rule__IfType__Group_4_0_1_0_3__1 ;
    public final void rule__IfType__Group_4_0_1_0_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2903:1: ( rule__IfType__Group_4_0_1_0_3__0__Impl rule__IfType__Group_4_0_1_0_3__1 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2904:2: rule__IfType__Group_4_0_1_0_3__0__Impl rule__IfType__Group_4_0_1_0_3__1
            {
            pushFollow(FOLLOW_rule__IfType__Group_4_0_1_0_3__0__Impl_in_rule__IfType__Group_4_0_1_0_3__05959);
            rule__IfType__Group_4_0_1_0_3__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__IfType__Group_4_0_1_0_3__1_in_rule__IfType__Group_4_0_1_0_3__05962);
            rule__IfType__Group_4_0_1_0_3__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfType__Group_4_0_1_0_3__0"


    // $ANTLR start "rule__IfType__Group_4_0_1_0_3__0__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2911:1: rule__IfType__Group_4_0_1_0_3__0__Impl : ( 'else' ) ;
    public final void rule__IfType__Group_4_0_1_0_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2915:1: ( ( 'else' ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2916:1: ( 'else' )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2916:1: ( 'else' )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2917:1: 'else'
            {
             before(grammarAccess.getIfTypeAccess().getElseKeyword_4_0_1_0_3_0()); 
            match(input,34,FOLLOW_34_in_rule__IfType__Group_4_0_1_0_3__0__Impl5990); 
             after(grammarAccess.getIfTypeAccess().getElseKeyword_4_0_1_0_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfType__Group_4_0_1_0_3__0__Impl"


    // $ANTLR start "rule__IfType__Group_4_0_1_0_3__1"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2930:1: rule__IfType__Group_4_0_1_0_3__1 : rule__IfType__Group_4_0_1_0_3__1__Impl rule__IfType__Group_4_0_1_0_3__2 ;
    public final void rule__IfType__Group_4_0_1_0_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2934:1: ( rule__IfType__Group_4_0_1_0_3__1__Impl rule__IfType__Group_4_0_1_0_3__2 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2935:2: rule__IfType__Group_4_0_1_0_3__1__Impl rule__IfType__Group_4_0_1_0_3__2
            {
            pushFollow(FOLLOW_rule__IfType__Group_4_0_1_0_3__1__Impl_in_rule__IfType__Group_4_0_1_0_3__16021);
            rule__IfType__Group_4_0_1_0_3__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__IfType__Group_4_0_1_0_3__2_in_rule__IfType__Group_4_0_1_0_3__16024);
            rule__IfType__Group_4_0_1_0_3__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfType__Group_4_0_1_0_3__1"


    // $ANTLR start "rule__IfType__Group_4_0_1_0_3__1__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2942:1: rule__IfType__Group_4_0_1_0_3__1__Impl : ( '{' ) ;
    public final void rule__IfType__Group_4_0_1_0_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2946:1: ( ( '{' ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2947:1: ( '{' )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2947:1: ( '{' )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2948:1: '{'
            {
             before(grammarAccess.getIfTypeAccess().getLeftCurlyBracketKeyword_4_0_1_0_3_1()); 
            match(input,32,FOLLOW_32_in_rule__IfType__Group_4_0_1_0_3__1__Impl6052); 
             after(grammarAccess.getIfTypeAccess().getLeftCurlyBracketKeyword_4_0_1_0_3_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfType__Group_4_0_1_0_3__1__Impl"


    // $ANTLR start "rule__IfType__Group_4_0_1_0_3__2"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2961:1: rule__IfType__Group_4_0_1_0_3__2 : rule__IfType__Group_4_0_1_0_3__2__Impl rule__IfType__Group_4_0_1_0_3__3 ;
    public final void rule__IfType__Group_4_0_1_0_3__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2965:1: ( rule__IfType__Group_4_0_1_0_3__2__Impl rule__IfType__Group_4_0_1_0_3__3 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2966:2: rule__IfType__Group_4_0_1_0_3__2__Impl rule__IfType__Group_4_0_1_0_3__3
            {
            pushFollow(FOLLOW_rule__IfType__Group_4_0_1_0_3__2__Impl_in_rule__IfType__Group_4_0_1_0_3__26083);
            rule__IfType__Group_4_0_1_0_3__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__IfType__Group_4_0_1_0_3__3_in_rule__IfType__Group_4_0_1_0_3__26086);
            rule__IfType__Group_4_0_1_0_3__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfType__Group_4_0_1_0_3__2"


    // $ANTLR start "rule__IfType__Group_4_0_1_0_3__2__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2973:1: rule__IfType__Group_4_0_1_0_3__2__Impl : ( ( rule__IfType__ElseCodeAssignment_4_0_1_0_3_2 )* ) ;
    public final void rule__IfType__Group_4_0_1_0_3__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2977:1: ( ( ( rule__IfType__ElseCodeAssignment_4_0_1_0_3_2 )* ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2978:1: ( ( rule__IfType__ElseCodeAssignment_4_0_1_0_3_2 )* )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2978:1: ( ( rule__IfType__ElseCodeAssignment_4_0_1_0_3_2 )* )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2979:1: ( rule__IfType__ElseCodeAssignment_4_0_1_0_3_2 )*
            {
             before(grammarAccess.getIfTypeAccess().getElseCodeAssignment_4_0_1_0_3_2()); 
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2980:1: ( rule__IfType__ElseCodeAssignment_4_0_1_0_3_2 )*
            loop33:
            do {
                int alt33=2;
                int LA33_0 = input.LA(1);

                if ( (LA33_0==RULE_ID||LA33_0==29||LA33_0==32||LA33_0==35||LA33_0==37||LA33_0==39||LA33_0==44) ) {
                    alt33=1;
                }


                switch (alt33) {
            	case 1 :
            	    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2980:2: rule__IfType__ElseCodeAssignment_4_0_1_0_3_2
            	    {
            	    pushFollow(FOLLOW_rule__IfType__ElseCodeAssignment_4_0_1_0_3_2_in_rule__IfType__Group_4_0_1_0_3__2__Impl6113);
            	    rule__IfType__ElseCodeAssignment_4_0_1_0_3_2();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop33;
                }
            } while (true);

             after(grammarAccess.getIfTypeAccess().getElseCodeAssignment_4_0_1_0_3_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfType__Group_4_0_1_0_3__2__Impl"


    // $ANTLR start "rule__IfType__Group_4_0_1_0_3__3"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2990:1: rule__IfType__Group_4_0_1_0_3__3 : rule__IfType__Group_4_0_1_0_3__3__Impl ;
    public final void rule__IfType__Group_4_0_1_0_3__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2994:1: ( rule__IfType__Group_4_0_1_0_3__3__Impl )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:2995:2: rule__IfType__Group_4_0_1_0_3__3__Impl
            {
            pushFollow(FOLLOW_rule__IfType__Group_4_0_1_0_3__3__Impl_in_rule__IfType__Group_4_0_1_0_3__36144);
            rule__IfType__Group_4_0_1_0_3__3__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfType__Group_4_0_1_0_3__3"


    // $ANTLR start "rule__IfType__Group_4_0_1_0_3__3__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3001:1: rule__IfType__Group_4_0_1_0_3__3__Impl : ( '}' ) ;
    public final void rule__IfType__Group_4_0_1_0_3__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3005:1: ( ( '}' ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3006:1: ( '}' )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3006:1: ( '}' )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3007:1: '}'
            {
             before(grammarAccess.getIfTypeAccess().getRightCurlyBracketKeyword_4_0_1_0_3_3()); 
            match(input,33,FOLLOW_33_in_rule__IfType__Group_4_0_1_0_3__3__Impl6172); 
             after(grammarAccess.getIfTypeAccess().getRightCurlyBracketKeyword_4_0_1_0_3_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfType__Group_4_0_1_0_3__3__Impl"


    // $ANTLR start "rule__IfType__Group_4_0_1_1__0"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3028:1: rule__IfType__Group_4_0_1_1__0 : rule__IfType__Group_4_0_1_1__0__Impl rule__IfType__Group_4_0_1_1__1 ;
    public final void rule__IfType__Group_4_0_1_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3032:1: ( rule__IfType__Group_4_0_1_1__0__Impl rule__IfType__Group_4_0_1_1__1 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3033:2: rule__IfType__Group_4_0_1_1__0__Impl rule__IfType__Group_4_0_1_1__1
            {
            pushFollow(FOLLOW_rule__IfType__Group_4_0_1_1__0__Impl_in_rule__IfType__Group_4_0_1_1__06211);
            rule__IfType__Group_4_0_1_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__IfType__Group_4_0_1_1__1_in_rule__IfType__Group_4_0_1_1__06214);
            rule__IfType__Group_4_0_1_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfType__Group_4_0_1_1__0"


    // $ANTLR start "rule__IfType__Group_4_0_1_1__0__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3040:1: rule__IfType__Group_4_0_1_1__0__Impl : ( '[' ) ;
    public final void rule__IfType__Group_4_0_1_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3044:1: ( ( '[' ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3045:1: ( '[' )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3045:1: ( '[' )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3046:1: '['
            {
             before(grammarAccess.getIfTypeAccess().getLeftSquareBracketKeyword_4_0_1_1_0()); 
            match(input,35,FOLLOW_35_in_rule__IfType__Group_4_0_1_1__0__Impl6242); 
             after(grammarAccess.getIfTypeAccess().getLeftSquareBracketKeyword_4_0_1_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfType__Group_4_0_1_1__0__Impl"


    // $ANTLR start "rule__IfType__Group_4_0_1_1__1"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3059:1: rule__IfType__Group_4_0_1_1__1 : rule__IfType__Group_4_0_1_1__1__Impl rule__IfType__Group_4_0_1_1__2 ;
    public final void rule__IfType__Group_4_0_1_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3063:1: ( rule__IfType__Group_4_0_1_1__1__Impl rule__IfType__Group_4_0_1_1__2 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3064:2: rule__IfType__Group_4_0_1_1__1__Impl rule__IfType__Group_4_0_1_1__2
            {
            pushFollow(FOLLOW_rule__IfType__Group_4_0_1_1__1__Impl_in_rule__IfType__Group_4_0_1_1__16273);
            rule__IfType__Group_4_0_1_1__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__IfType__Group_4_0_1_1__2_in_rule__IfType__Group_4_0_1_1__16276);
            rule__IfType__Group_4_0_1_1__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfType__Group_4_0_1_1__1"


    // $ANTLR start "rule__IfType__Group_4_0_1_1__1__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3071:1: rule__IfType__Group_4_0_1_1__1__Impl : ( '{' ) ;
    public final void rule__IfType__Group_4_0_1_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3075:1: ( ( '{' ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3076:1: ( '{' )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3076:1: ( '{' )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3077:1: '{'
            {
             before(grammarAccess.getIfTypeAccess().getLeftCurlyBracketKeyword_4_0_1_1_1()); 
            match(input,32,FOLLOW_32_in_rule__IfType__Group_4_0_1_1__1__Impl6304); 
             after(grammarAccess.getIfTypeAccess().getLeftCurlyBracketKeyword_4_0_1_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfType__Group_4_0_1_1__1__Impl"


    // $ANTLR start "rule__IfType__Group_4_0_1_1__2"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3090:1: rule__IfType__Group_4_0_1_1__2 : rule__IfType__Group_4_0_1_1__2__Impl rule__IfType__Group_4_0_1_1__3 ;
    public final void rule__IfType__Group_4_0_1_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3094:1: ( rule__IfType__Group_4_0_1_1__2__Impl rule__IfType__Group_4_0_1_1__3 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3095:2: rule__IfType__Group_4_0_1_1__2__Impl rule__IfType__Group_4_0_1_1__3
            {
            pushFollow(FOLLOW_rule__IfType__Group_4_0_1_1__2__Impl_in_rule__IfType__Group_4_0_1_1__26335);
            rule__IfType__Group_4_0_1_1__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__IfType__Group_4_0_1_1__3_in_rule__IfType__Group_4_0_1_1__26338);
            rule__IfType__Group_4_0_1_1__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfType__Group_4_0_1_1__2"


    // $ANTLR start "rule__IfType__Group_4_0_1_1__2__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3102:1: rule__IfType__Group_4_0_1_1__2__Impl : ( ( rule__IfType__ThenCodeAssignment_4_0_1_1_2 )* ) ;
    public final void rule__IfType__Group_4_0_1_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3106:1: ( ( ( rule__IfType__ThenCodeAssignment_4_0_1_1_2 )* ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3107:1: ( ( rule__IfType__ThenCodeAssignment_4_0_1_1_2 )* )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3107:1: ( ( rule__IfType__ThenCodeAssignment_4_0_1_1_2 )* )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3108:1: ( rule__IfType__ThenCodeAssignment_4_0_1_1_2 )*
            {
             before(grammarAccess.getIfTypeAccess().getThenCodeAssignment_4_0_1_1_2()); 
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3109:1: ( rule__IfType__ThenCodeAssignment_4_0_1_1_2 )*
            loop34:
            do {
                int alt34=2;
                int LA34_0 = input.LA(1);

                if ( (LA34_0==RULE_ID||LA34_0==29||LA34_0==32||LA34_0==35||LA34_0==37||LA34_0==39||LA34_0==44) ) {
                    alt34=1;
                }


                switch (alt34) {
            	case 1 :
            	    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3109:2: rule__IfType__ThenCodeAssignment_4_0_1_1_2
            	    {
            	    pushFollow(FOLLOW_rule__IfType__ThenCodeAssignment_4_0_1_1_2_in_rule__IfType__Group_4_0_1_1__2__Impl6365);
            	    rule__IfType__ThenCodeAssignment_4_0_1_1_2();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop34;
                }
            } while (true);

             after(grammarAccess.getIfTypeAccess().getThenCodeAssignment_4_0_1_1_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfType__Group_4_0_1_1__2__Impl"


    // $ANTLR start "rule__IfType__Group_4_0_1_1__3"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3119:1: rule__IfType__Group_4_0_1_1__3 : rule__IfType__Group_4_0_1_1__3__Impl rule__IfType__Group_4_0_1_1__4 ;
    public final void rule__IfType__Group_4_0_1_1__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3123:1: ( rule__IfType__Group_4_0_1_1__3__Impl rule__IfType__Group_4_0_1_1__4 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3124:2: rule__IfType__Group_4_0_1_1__3__Impl rule__IfType__Group_4_0_1_1__4
            {
            pushFollow(FOLLOW_rule__IfType__Group_4_0_1_1__3__Impl_in_rule__IfType__Group_4_0_1_1__36396);
            rule__IfType__Group_4_0_1_1__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__IfType__Group_4_0_1_1__4_in_rule__IfType__Group_4_0_1_1__36399);
            rule__IfType__Group_4_0_1_1__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfType__Group_4_0_1_1__3"


    // $ANTLR start "rule__IfType__Group_4_0_1_1__3__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3131:1: rule__IfType__Group_4_0_1_1__3__Impl : ( '}' ) ;
    public final void rule__IfType__Group_4_0_1_1__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3135:1: ( ( '}' ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3136:1: ( '}' )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3136:1: ( '}' )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3137:1: '}'
            {
             before(grammarAccess.getIfTypeAccess().getRightCurlyBracketKeyword_4_0_1_1_3()); 
            match(input,33,FOLLOW_33_in_rule__IfType__Group_4_0_1_1__3__Impl6427); 
             after(grammarAccess.getIfTypeAccess().getRightCurlyBracketKeyword_4_0_1_1_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfType__Group_4_0_1_1__3__Impl"


    // $ANTLR start "rule__IfType__Group_4_0_1_1__4"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3150:1: rule__IfType__Group_4_0_1_1__4 : rule__IfType__Group_4_0_1_1__4__Impl rule__IfType__Group_4_0_1_1__5 ;
    public final void rule__IfType__Group_4_0_1_1__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3154:1: ( rule__IfType__Group_4_0_1_1__4__Impl rule__IfType__Group_4_0_1_1__5 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3155:2: rule__IfType__Group_4_0_1_1__4__Impl rule__IfType__Group_4_0_1_1__5
            {
            pushFollow(FOLLOW_rule__IfType__Group_4_0_1_1__4__Impl_in_rule__IfType__Group_4_0_1_1__46458);
            rule__IfType__Group_4_0_1_1__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__IfType__Group_4_0_1_1__5_in_rule__IfType__Group_4_0_1_1__46461);
            rule__IfType__Group_4_0_1_1__5();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfType__Group_4_0_1_1__4"


    // $ANTLR start "rule__IfType__Group_4_0_1_1__4__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3162:1: rule__IfType__Group_4_0_1_1__4__Impl : ( ',' ) ;
    public final void rule__IfType__Group_4_0_1_1__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3166:1: ( ( ',' ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3167:1: ( ',' )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3167:1: ( ',' )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3168:1: ','
            {
             before(grammarAccess.getIfTypeAccess().getCommaKeyword_4_0_1_1_4()); 
            match(input,28,FOLLOW_28_in_rule__IfType__Group_4_0_1_1__4__Impl6489); 
             after(grammarAccess.getIfTypeAccess().getCommaKeyword_4_0_1_1_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfType__Group_4_0_1_1__4__Impl"


    // $ANTLR start "rule__IfType__Group_4_0_1_1__5"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3181:1: rule__IfType__Group_4_0_1_1__5 : rule__IfType__Group_4_0_1_1__5__Impl rule__IfType__Group_4_0_1_1__6 ;
    public final void rule__IfType__Group_4_0_1_1__5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3185:1: ( rule__IfType__Group_4_0_1_1__5__Impl rule__IfType__Group_4_0_1_1__6 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3186:2: rule__IfType__Group_4_0_1_1__5__Impl rule__IfType__Group_4_0_1_1__6
            {
            pushFollow(FOLLOW_rule__IfType__Group_4_0_1_1__5__Impl_in_rule__IfType__Group_4_0_1_1__56520);
            rule__IfType__Group_4_0_1_1__5__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__IfType__Group_4_0_1_1__6_in_rule__IfType__Group_4_0_1_1__56523);
            rule__IfType__Group_4_0_1_1__6();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfType__Group_4_0_1_1__5"


    // $ANTLR start "rule__IfType__Group_4_0_1_1__5__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3193:1: rule__IfType__Group_4_0_1_1__5__Impl : ( '{' ) ;
    public final void rule__IfType__Group_4_0_1_1__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3197:1: ( ( '{' ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3198:1: ( '{' )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3198:1: ( '{' )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3199:1: '{'
            {
             before(grammarAccess.getIfTypeAccess().getLeftCurlyBracketKeyword_4_0_1_1_5()); 
            match(input,32,FOLLOW_32_in_rule__IfType__Group_4_0_1_1__5__Impl6551); 
             after(grammarAccess.getIfTypeAccess().getLeftCurlyBracketKeyword_4_0_1_1_5()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfType__Group_4_0_1_1__5__Impl"


    // $ANTLR start "rule__IfType__Group_4_0_1_1__6"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3212:1: rule__IfType__Group_4_0_1_1__6 : rule__IfType__Group_4_0_1_1__6__Impl rule__IfType__Group_4_0_1_1__7 ;
    public final void rule__IfType__Group_4_0_1_1__6() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3216:1: ( rule__IfType__Group_4_0_1_1__6__Impl rule__IfType__Group_4_0_1_1__7 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3217:2: rule__IfType__Group_4_0_1_1__6__Impl rule__IfType__Group_4_0_1_1__7
            {
            pushFollow(FOLLOW_rule__IfType__Group_4_0_1_1__6__Impl_in_rule__IfType__Group_4_0_1_1__66582);
            rule__IfType__Group_4_0_1_1__6__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__IfType__Group_4_0_1_1__7_in_rule__IfType__Group_4_0_1_1__66585);
            rule__IfType__Group_4_0_1_1__7();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfType__Group_4_0_1_1__6"


    // $ANTLR start "rule__IfType__Group_4_0_1_1__6__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3224:1: rule__IfType__Group_4_0_1_1__6__Impl : ( ( rule__IfType__ElseCodeAssignment_4_0_1_1_6 )* ) ;
    public final void rule__IfType__Group_4_0_1_1__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3228:1: ( ( ( rule__IfType__ElseCodeAssignment_4_0_1_1_6 )* ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3229:1: ( ( rule__IfType__ElseCodeAssignment_4_0_1_1_6 )* )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3229:1: ( ( rule__IfType__ElseCodeAssignment_4_0_1_1_6 )* )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3230:1: ( rule__IfType__ElseCodeAssignment_4_0_1_1_6 )*
            {
             before(grammarAccess.getIfTypeAccess().getElseCodeAssignment_4_0_1_1_6()); 
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3231:1: ( rule__IfType__ElseCodeAssignment_4_0_1_1_6 )*
            loop35:
            do {
                int alt35=2;
                int LA35_0 = input.LA(1);

                if ( (LA35_0==RULE_ID||LA35_0==29||LA35_0==32||LA35_0==35||LA35_0==37||LA35_0==39||LA35_0==44) ) {
                    alt35=1;
                }


                switch (alt35) {
            	case 1 :
            	    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3231:2: rule__IfType__ElseCodeAssignment_4_0_1_1_6
            	    {
            	    pushFollow(FOLLOW_rule__IfType__ElseCodeAssignment_4_0_1_1_6_in_rule__IfType__Group_4_0_1_1__6__Impl6612);
            	    rule__IfType__ElseCodeAssignment_4_0_1_1_6();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop35;
                }
            } while (true);

             after(grammarAccess.getIfTypeAccess().getElseCodeAssignment_4_0_1_1_6()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfType__Group_4_0_1_1__6__Impl"


    // $ANTLR start "rule__IfType__Group_4_0_1_1__7"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3241:1: rule__IfType__Group_4_0_1_1__7 : rule__IfType__Group_4_0_1_1__7__Impl rule__IfType__Group_4_0_1_1__8 ;
    public final void rule__IfType__Group_4_0_1_1__7() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3245:1: ( rule__IfType__Group_4_0_1_1__7__Impl rule__IfType__Group_4_0_1_1__8 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3246:2: rule__IfType__Group_4_0_1_1__7__Impl rule__IfType__Group_4_0_1_1__8
            {
            pushFollow(FOLLOW_rule__IfType__Group_4_0_1_1__7__Impl_in_rule__IfType__Group_4_0_1_1__76643);
            rule__IfType__Group_4_0_1_1__7__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__IfType__Group_4_0_1_1__8_in_rule__IfType__Group_4_0_1_1__76646);
            rule__IfType__Group_4_0_1_1__8();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfType__Group_4_0_1_1__7"


    // $ANTLR start "rule__IfType__Group_4_0_1_1__7__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3253:1: rule__IfType__Group_4_0_1_1__7__Impl : ( '}' ) ;
    public final void rule__IfType__Group_4_0_1_1__7__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3257:1: ( ( '}' ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3258:1: ( '}' )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3258:1: ( '}' )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3259:1: '}'
            {
             before(grammarAccess.getIfTypeAccess().getRightCurlyBracketKeyword_4_0_1_1_7()); 
            match(input,33,FOLLOW_33_in_rule__IfType__Group_4_0_1_1__7__Impl6674); 
             after(grammarAccess.getIfTypeAccess().getRightCurlyBracketKeyword_4_0_1_1_7()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfType__Group_4_0_1_1__7__Impl"


    // $ANTLR start "rule__IfType__Group_4_0_1_1__8"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3272:1: rule__IfType__Group_4_0_1_1__8 : rule__IfType__Group_4_0_1_1__8__Impl ;
    public final void rule__IfType__Group_4_0_1_1__8() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3276:1: ( rule__IfType__Group_4_0_1_1__8__Impl )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3277:2: rule__IfType__Group_4_0_1_1__8__Impl
            {
            pushFollow(FOLLOW_rule__IfType__Group_4_0_1_1__8__Impl_in_rule__IfType__Group_4_0_1_1__86705);
            rule__IfType__Group_4_0_1_1__8__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfType__Group_4_0_1_1__8"


    // $ANTLR start "rule__IfType__Group_4_0_1_1__8__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3283:1: rule__IfType__Group_4_0_1_1__8__Impl : ( ']' ) ;
    public final void rule__IfType__Group_4_0_1_1__8__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3287:1: ( ( ']' ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3288:1: ( ']' )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3288:1: ( ']' )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3289:1: ']'
            {
             before(grammarAccess.getIfTypeAccess().getRightSquareBracketKeyword_4_0_1_1_8()); 
            match(input,27,FOLLOW_27_in_rule__IfType__Group_4_0_1_1__8__Impl6733); 
             after(grammarAccess.getIfTypeAccess().getRightSquareBracketKeyword_4_0_1_1_8()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfType__Group_4_0_1_1__8__Impl"


    // $ANTLR start "rule__IfType__Group_4_1__0"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3320:1: rule__IfType__Group_4_1__0 : rule__IfType__Group_4_1__0__Impl rule__IfType__Group_4_1__1 ;
    public final void rule__IfType__Group_4_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3324:1: ( rule__IfType__Group_4_1__0__Impl rule__IfType__Group_4_1__1 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3325:2: rule__IfType__Group_4_1__0__Impl rule__IfType__Group_4_1__1
            {
            pushFollow(FOLLOW_rule__IfType__Group_4_1__0__Impl_in_rule__IfType__Group_4_1__06782);
            rule__IfType__Group_4_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__IfType__Group_4_1__1_in_rule__IfType__Group_4_1__06785);
            rule__IfType__Group_4_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfType__Group_4_1__0"


    // $ANTLR start "rule__IfType__Group_4_1__0__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3332:1: rule__IfType__Group_4_1__0__Impl : ( 'exitWith' ) ;
    public final void rule__IfType__Group_4_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3336:1: ( ( 'exitWith' ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3337:1: ( 'exitWith' )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3337:1: ( 'exitWith' )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3338:1: 'exitWith'
            {
             before(grammarAccess.getIfTypeAccess().getExitWithKeyword_4_1_0()); 
            match(input,36,FOLLOW_36_in_rule__IfType__Group_4_1__0__Impl6813); 
             after(grammarAccess.getIfTypeAccess().getExitWithKeyword_4_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfType__Group_4_1__0__Impl"


    // $ANTLR start "rule__IfType__Group_4_1__1"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3351:1: rule__IfType__Group_4_1__1 : rule__IfType__Group_4_1__1__Impl rule__IfType__Group_4_1__2 ;
    public final void rule__IfType__Group_4_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3355:1: ( rule__IfType__Group_4_1__1__Impl rule__IfType__Group_4_1__2 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3356:2: rule__IfType__Group_4_1__1__Impl rule__IfType__Group_4_1__2
            {
            pushFollow(FOLLOW_rule__IfType__Group_4_1__1__Impl_in_rule__IfType__Group_4_1__16844);
            rule__IfType__Group_4_1__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__IfType__Group_4_1__2_in_rule__IfType__Group_4_1__16847);
            rule__IfType__Group_4_1__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfType__Group_4_1__1"


    // $ANTLR start "rule__IfType__Group_4_1__1__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3363:1: rule__IfType__Group_4_1__1__Impl : ( '{' ) ;
    public final void rule__IfType__Group_4_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3367:1: ( ( '{' ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3368:1: ( '{' )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3368:1: ( '{' )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3369:1: '{'
            {
             before(grammarAccess.getIfTypeAccess().getLeftCurlyBracketKeyword_4_1_1()); 
            match(input,32,FOLLOW_32_in_rule__IfType__Group_4_1__1__Impl6875); 
             after(grammarAccess.getIfTypeAccess().getLeftCurlyBracketKeyword_4_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfType__Group_4_1__1__Impl"


    // $ANTLR start "rule__IfType__Group_4_1__2"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3382:1: rule__IfType__Group_4_1__2 : rule__IfType__Group_4_1__2__Impl rule__IfType__Group_4_1__3 ;
    public final void rule__IfType__Group_4_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3386:1: ( rule__IfType__Group_4_1__2__Impl rule__IfType__Group_4_1__3 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3387:2: rule__IfType__Group_4_1__2__Impl rule__IfType__Group_4_1__3
            {
            pushFollow(FOLLOW_rule__IfType__Group_4_1__2__Impl_in_rule__IfType__Group_4_1__26906);
            rule__IfType__Group_4_1__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__IfType__Group_4_1__3_in_rule__IfType__Group_4_1__26909);
            rule__IfType__Group_4_1__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfType__Group_4_1__2"


    // $ANTLR start "rule__IfType__Group_4_1__2__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3394:1: rule__IfType__Group_4_1__2__Impl : ( ( rule__IfType__ExitCodeAssignment_4_1_2 )* ) ;
    public final void rule__IfType__Group_4_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3398:1: ( ( ( rule__IfType__ExitCodeAssignment_4_1_2 )* ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3399:1: ( ( rule__IfType__ExitCodeAssignment_4_1_2 )* )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3399:1: ( ( rule__IfType__ExitCodeAssignment_4_1_2 )* )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3400:1: ( rule__IfType__ExitCodeAssignment_4_1_2 )*
            {
             before(grammarAccess.getIfTypeAccess().getExitCodeAssignment_4_1_2()); 
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3401:1: ( rule__IfType__ExitCodeAssignment_4_1_2 )*
            loop36:
            do {
                int alt36=2;
                int LA36_0 = input.LA(1);

                if ( (LA36_0==RULE_ID||LA36_0==29||LA36_0==32||LA36_0==35||LA36_0==37||LA36_0==39||LA36_0==44) ) {
                    alt36=1;
                }


                switch (alt36) {
            	case 1 :
            	    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3401:2: rule__IfType__ExitCodeAssignment_4_1_2
            	    {
            	    pushFollow(FOLLOW_rule__IfType__ExitCodeAssignment_4_1_2_in_rule__IfType__Group_4_1__2__Impl6936);
            	    rule__IfType__ExitCodeAssignment_4_1_2();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop36;
                }
            } while (true);

             after(grammarAccess.getIfTypeAccess().getExitCodeAssignment_4_1_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfType__Group_4_1__2__Impl"


    // $ANTLR start "rule__IfType__Group_4_1__3"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3411:1: rule__IfType__Group_4_1__3 : rule__IfType__Group_4_1__3__Impl ;
    public final void rule__IfType__Group_4_1__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3415:1: ( rule__IfType__Group_4_1__3__Impl )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3416:2: rule__IfType__Group_4_1__3__Impl
            {
            pushFollow(FOLLOW_rule__IfType__Group_4_1__3__Impl_in_rule__IfType__Group_4_1__36967);
            rule__IfType__Group_4_1__3__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfType__Group_4_1__3"


    // $ANTLR start "rule__IfType__Group_4_1__3__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3422:1: rule__IfType__Group_4_1__3__Impl : ( '}' ) ;
    public final void rule__IfType__Group_4_1__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3426:1: ( ( '}' ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3427:1: ( '}' )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3427:1: ( '}' )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3428:1: '}'
            {
             before(grammarAccess.getIfTypeAccess().getRightCurlyBracketKeyword_4_1_3()); 
            match(input,33,FOLLOW_33_in_rule__IfType__Group_4_1__3__Impl6995); 
             after(grammarAccess.getIfTypeAccess().getRightCurlyBracketKeyword_4_1_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfType__Group_4_1__3__Impl"


    // $ANTLR start "rule__WhileType__Group__0"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3449:1: rule__WhileType__Group__0 : rule__WhileType__Group__0__Impl rule__WhileType__Group__1 ;
    public final void rule__WhileType__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3453:1: ( rule__WhileType__Group__0__Impl rule__WhileType__Group__1 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3454:2: rule__WhileType__Group__0__Impl rule__WhileType__Group__1
            {
            pushFollow(FOLLOW_rule__WhileType__Group__0__Impl_in_rule__WhileType__Group__07034);
            rule__WhileType__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__WhileType__Group__1_in_rule__WhileType__Group__07037);
            rule__WhileType__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__WhileType__Group__0"


    // $ANTLR start "rule__WhileType__Group__0__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3461:1: rule__WhileType__Group__0__Impl : ( 'while' ) ;
    public final void rule__WhileType__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3465:1: ( ( 'while' ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3466:1: ( 'while' )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3466:1: ( 'while' )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3467:1: 'while'
            {
             before(grammarAccess.getWhileTypeAccess().getWhileKeyword_0()); 
            match(input,37,FOLLOW_37_in_rule__WhileType__Group__0__Impl7065); 
             after(grammarAccess.getWhileTypeAccess().getWhileKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__WhileType__Group__0__Impl"


    // $ANTLR start "rule__WhileType__Group__1"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3480:1: rule__WhileType__Group__1 : rule__WhileType__Group__1__Impl rule__WhileType__Group__2 ;
    public final void rule__WhileType__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3484:1: ( rule__WhileType__Group__1__Impl rule__WhileType__Group__2 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3485:2: rule__WhileType__Group__1__Impl rule__WhileType__Group__2
            {
            pushFollow(FOLLOW_rule__WhileType__Group__1__Impl_in_rule__WhileType__Group__17096);
            rule__WhileType__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__WhileType__Group__2_in_rule__WhileType__Group__17099);
            rule__WhileType__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__WhileType__Group__1"


    // $ANTLR start "rule__WhileType__Group__1__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3492:1: rule__WhileType__Group__1__Impl : ( '{' ) ;
    public final void rule__WhileType__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3496:1: ( ( '{' ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3497:1: ( '{' )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3497:1: ( '{' )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3498:1: '{'
            {
             before(grammarAccess.getWhileTypeAccess().getLeftCurlyBracketKeyword_1()); 
            match(input,32,FOLLOW_32_in_rule__WhileType__Group__1__Impl7127); 
             after(grammarAccess.getWhileTypeAccess().getLeftCurlyBracketKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__WhileType__Group__1__Impl"


    // $ANTLR start "rule__WhileType__Group__2"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3511:1: rule__WhileType__Group__2 : rule__WhileType__Group__2__Impl rule__WhileType__Group__3 ;
    public final void rule__WhileType__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3515:1: ( rule__WhileType__Group__2__Impl rule__WhileType__Group__3 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3516:2: rule__WhileType__Group__2__Impl rule__WhileType__Group__3
            {
            pushFollow(FOLLOW_rule__WhileType__Group__2__Impl_in_rule__WhileType__Group__27158);
            rule__WhileType__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__WhileType__Group__3_in_rule__WhileType__Group__27161);
            rule__WhileType__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__WhileType__Group__2"


    // $ANTLR start "rule__WhileType__Group__2__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3523:1: rule__WhileType__Group__2__Impl : ( ( rule__WhileType__ConditionAssignment_2 ) ) ;
    public final void rule__WhileType__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3527:1: ( ( ( rule__WhileType__ConditionAssignment_2 ) ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3528:1: ( ( rule__WhileType__ConditionAssignment_2 ) )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3528:1: ( ( rule__WhileType__ConditionAssignment_2 ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3529:1: ( rule__WhileType__ConditionAssignment_2 )
            {
             before(grammarAccess.getWhileTypeAccess().getConditionAssignment_2()); 
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3530:1: ( rule__WhileType__ConditionAssignment_2 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3530:2: rule__WhileType__ConditionAssignment_2
            {
            pushFollow(FOLLOW_rule__WhileType__ConditionAssignment_2_in_rule__WhileType__Group__2__Impl7188);
            rule__WhileType__ConditionAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getWhileTypeAccess().getConditionAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__WhileType__Group__2__Impl"


    // $ANTLR start "rule__WhileType__Group__3"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3540:1: rule__WhileType__Group__3 : rule__WhileType__Group__3__Impl rule__WhileType__Group__4 ;
    public final void rule__WhileType__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3544:1: ( rule__WhileType__Group__3__Impl rule__WhileType__Group__4 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3545:2: rule__WhileType__Group__3__Impl rule__WhileType__Group__4
            {
            pushFollow(FOLLOW_rule__WhileType__Group__3__Impl_in_rule__WhileType__Group__37218);
            rule__WhileType__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__WhileType__Group__4_in_rule__WhileType__Group__37221);
            rule__WhileType__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__WhileType__Group__3"


    // $ANTLR start "rule__WhileType__Group__3__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3552:1: rule__WhileType__Group__3__Impl : ( '}' ) ;
    public final void rule__WhileType__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3556:1: ( ( '}' ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3557:1: ( '}' )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3557:1: ( '}' )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3558:1: '}'
            {
             before(grammarAccess.getWhileTypeAccess().getRightCurlyBracketKeyword_3()); 
            match(input,33,FOLLOW_33_in_rule__WhileType__Group__3__Impl7249); 
             after(grammarAccess.getWhileTypeAccess().getRightCurlyBracketKeyword_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__WhileType__Group__3__Impl"


    // $ANTLR start "rule__WhileType__Group__4"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3571:1: rule__WhileType__Group__4 : rule__WhileType__Group__4__Impl rule__WhileType__Group__5 ;
    public final void rule__WhileType__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3575:1: ( rule__WhileType__Group__4__Impl rule__WhileType__Group__5 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3576:2: rule__WhileType__Group__4__Impl rule__WhileType__Group__5
            {
            pushFollow(FOLLOW_rule__WhileType__Group__4__Impl_in_rule__WhileType__Group__47280);
            rule__WhileType__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__WhileType__Group__5_in_rule__WhileType__Group__47283);
            rule__WhileType__Group__5();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__WhileType__Group__4"


    // $ANTLR start "rule__WhileType__Group__4__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3583:1: rule__WhileType__Group__4__Impl : ( 'do' ) ;
    public final void rule__WhileType__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3587:1: ( ( 'do' ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3588:1: ( 'do' )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3588:1: ( 'do' )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3589:1: 'do'
            {
             before(grammarAccess.getWhileTypeAccess().getDoKeyword_4()); 
            match(input,38,FOLLOW_38_in_rule__WhileType__Group__4__Impl7311); 
             after(grammarAccess.getWhileTypeAccess().getDoKeyword_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__WhileType__Group__4__Impl"


    // $ANTLR start "rule__WhileType__Group__5"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3602:1: rule__WhileType__Group__5 : rule__WhileType__Group__5__Impl rule__WhileType__Group__6 ;
    public final void rule__WhileType__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3606:1: ( rule__WhileType__Group__5__Impl rule__WhileType__Group__6 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3607:2: rule__WhileType__Group__5__Impl rule__WhileType__Group__6
            {
            pushFollow(FOLLOW_rule__WhileType__Group__5__Impl_in_rule__WhileType__Group__57342);
            rule__WhileType__Group__5__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__WhileType__Group__6_in_rule__WhileType__Group__57345);
            rule__WhileType__Group__6();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__WhileType__Group__5"


    // $ANTLR start "rule__WhileType__Group__5__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3614:1: rule__WhileType__Group__5__Impl : ( '{' ) ;
    public final void rule__WhileType__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3618:1: ( ( '{' ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3619:1: ( '{' )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3619:1: ( '{' )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3620:1: '{'
            {
             before(grammarAccess.getWhileTypeAccess().getLeftCurlyBracketKeyword_5()); 
            match(input,32,FOLLOW_32_in_rule__WhileType__Group__5__Impl7373); 
             after(grammarAccess.getWhileTypeAccess().getLeftCurlyBracketKeyword_5()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__WhileType__Group__5__Impl"


    // $ANTLR start "rule__WhileType__Group__6"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3633:1: rule__WhileType__Group__6 : rule__WhileType__Group__6__Impl rule__WhileType__Group__7 ;
    public final void rule__WhileType__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3637:1: ( rule__WhileType__Group__6__Impl rule__WhileType__Group__7 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3638:2: rule__WhileType__Group__6__Impl rule__WhileType__Group__7
            {
            pushFollow(FOLLOW_rule__WhileType__Group__6__Impl_in_rule__WhileType__Group__67404);
            rule__WhileType__Group__6__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__WhileType__Group__7_in_rule__WhileType__Group__67407);
            rule__WhileType__Group__7();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__WhileType__Group__6"


    // $ANTLR start "rule__WhileType__Group__6__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3645:1: rule__WhileType__Group__6__Impl : ( ( rule__WhileType__LoopCodeAssignment_6 )* ) ;
    public final void rule__WhileType__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3649:1: ( ( ( rule__WhileType__LoopCodeAssignment_6 )* ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3650:1: ( ( rule__WhileType__LoopCodeAssignment_6 )* )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3650:1: ( ( rule__WhileType__LoopCodeAssignment_6 )* )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3651:1: ( rule__WhileType__LoopCodeAssignment_6 )*
            {
             before(grammarAccess.getWhileTypeAccess().getLoopCodeAssignment_6()); 
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3652:1: ( rule__WhileType__LoopCodeAssignment_6 )*
            loop37:
            do {
                int alt37=2;
                int LA37_0 = input.LA(1);

                if ( (LA37_0==RULE_ID||LA37_0==29||LA37_0==32||LA37_0==35||LA37_0==37||LA37_0==39||LA37_0==44) ) {
                    alt37=1;
                }


                switch (alt37) {
            	case 1 :
            	    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3652:2: rule__WhileType__LoopCodeAssignment_6
            	    {
            	    pushFollow(FOLLOW_rule__WhileType__LoopCodeAssignment_6_in_rule__WhileType__Group__6__Impl7434);
            	    rule__WhileType__LoopCodeAssignment_6();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop37;
                }
            } while (true);

             after(grammarAccess.getWhileTypeAccess().getLoopCodeAssignment_6()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__WhileType__Group__6__Impl"


    // $ANTLR start "rule__WhileType__Group__7"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3662:1: rule__WhileType__Group__7 : rule__WhileType__Group__7__Impl ;
    public final void rule__WhileType__Group__7() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3666:1: ( rule__WhileType__Group__7__Impl )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3667:2: rule__WhileType__Group__7__Impl
            {
            pushFollow(FOLLOW_rule__WhileType__Group__7__Impl_in_rule__WhileType__Group__77465);
            rule__WhileType__Group__7__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__WhileType__Group__7"


    // $ANTLR start "rule__WhileType__Group__7__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3673:1: rule__WhileType__Group__7__Impl : ( '}' ) ;
    public final void rule__WhileType__Group__7__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3677:1: ( ( '}' ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3678:1: ( '}' )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3678:1: ( '}' )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3679:1: '}'
            {
             before(grammarAccess.getWhileTypeAccess().getRightCurlyBracketKeyword_7()); 
            match(input,33,FOLLOW_33_in_rule__WhileType__Group__7__Impl7493); 
             after(grammarAccess.getWhileTypeAccess().getRightCurlyBracketKeyword_7()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__WhileType__Group__7__Impl"


    // $ANTLR start "rule__ForType__Group__0"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3708:1: rule__ForType__Group__0 : rule__ForType__Group__0__Impl rule__ForType__Group__1 ;
    public final void rule__ForType__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3712:1: ( rule__ForType__Group__0__Impl rule__ForType__Group__1 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3713:2: rule__ForType__Group__0__Impl rule__ForType__Group__1
            {
            pushFollow(FOLLOW_rule__ForType__Group__0__Impl_in_rule__ForType__Group__07540);
            rule__ForType__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ForType__Group__1_in_rule__ForType__Group__07543);
            rule__ForType__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ForType__Group__0"


    // $ANTLR start "rule__ForType__Group__0__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3720:1: rule__ForType__Group__0__Impl : ( 'for' ) ;
    public final void rule__ForType__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3724:1: ( ( 'for' ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3725:1: ( 'for' )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3725:1: ( 'for' )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3726:1: 'for'
            {
             before(grammarAccess.getForTypeAccess().getForKeyword_0()); 
            match(input,39,FOLLOW_39_in_rule__ForType__Group__0__Impl7571); 
             after(grammarAccess.getForTypeAccess().getForKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ForType__Group__0__Impl"


    // $ANTLR start "rule__ForType__Group__1"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3739:1: rule__ForType__Group__1 : rule__ForType__Group__1__Impl rule__ForType__Group__2 ;
    public final void rule__ForType__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3743:1: ( rule__ForType__Group__1__Impl rule__ForType__Group__2 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3744:2: rule__ForType__Group__1__Impl rule__ForType__Group__2
            {
            pushFollow(FOLLOW_rule__ForType__Group__1__Impl_in_rule__ForType__Group__17602);
            rule__ForType__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ForType__Group__2_in_rule__ForType__Group__17605);
            rule__ForType__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ForType__Group__1"


    // $ANTLR start "rule__ForType__Group__1__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3751:1: rule__ForType__Group__1__Impl : ( ( rule__ForType__Alternatives_1 ) ) ;
    public final void rule__ForType__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3755:1: ( ( ( rule__ForType__Alternatives_1 ) ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3756:1: ( ( rule__ForType__Alternatives_1 ) )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3756:1: ( ( rule__ForType__Alternatives_1 ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3757:1: ( rule__ForType__Alternatives_1 )
            {
             before(grammarAccess.getForTypeAccess().getAlternatives_1()); 
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3758:1: ( rule__ForType__Alternatives_1 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3758:2: rule__ForType__Alternatives_1
            {
            pushFollow(FOLLOW_rule__ForType__Alternatives_1_in_rule__ForType__Group__1__Impl7632);
            rule__ForType__Alternatives_1();

            state._fsp--;


            }

             after(grammarAccess.getForTypeAccess().getAlternatives_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ForType__Group__1__Impl"


    // $ANTLR start "rule__ForType__Group__2"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3768:1: rule__ForType__Group__2 : rule__ForType__Group__2__Impl rule__ForType__Group__3 ;
    public final void rule__ForType__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3772:1: ( rule__ForType__Group__2__Impl rule__ForType__Group__3 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3773:2: rule__ForType__Group__2__Impl rule__ForType__Group__3
            {
            pushFollow(FOLLOW_rule__ForType__Group__2__Impl_in_rule__ForType__Group__27662);
            rule__ForType__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ForType__Group__3_in_rule__ForType__Group__27665);
            rule__ForType__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ForType__Group__2"


    // $ANTLR start "rule__ForType__Group__2__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3780:1: rule__ForType__Group__2__Impl : ( 'do' ) ;
    public final void rule__ForType__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3784:1: ( ( 'do' ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3785:1: ( 'do' )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3785:1: ( 'do' )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3786:1: 'do'
            {
             before(grammarAccess.getForTypeAccess().getDoKeyword_2()); 
            match(input,38,FOLLOW_38_in_rule__ForType__Group__2__Impl7693); 
             after(grammarAccess.getForTypeAccess().getDoKeyword_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ForType__Group__2__Impl"


    // $ANTLR start "rule__ForType__Group__3"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3799:1: rule__ForType__Group__3 : rule__ForType__Group__3__Impl rule__ForType__Group__4 ;
    public final void rule__ForType__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3803:1: ( rule__ForType__Group__3__Impl rule__ForType__Group__4 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3804:2: rule__ForType__Group__3__Impl rule__ForType__Group__4
            {
            pushFollow(FOLLOW_rule__ForType__Group__3__Impl_in_rule__ForType__Group__37724);
            rule__ForType__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ForType__Group__4_in_rule__ForType__Group__37727);
            rule__ForType__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ForType__Group__3"


    // $ANTLR start "rule__ForType__Group__3__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3811:1: rule__ForType__Group__3__Impl : ( '{' ) ;
    public final void rule__ForType__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3815:1: ( ( '{' ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3816:1: ( '{' )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3816:1: ( '{' )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3817:1: '{'
            {
             before(grammarAccess.getForTypeAccess().getLeftCurlyBracketKeyword_3()); 
            match(input,32,FOLLOW_32_in_rule__ForType__Group__3__Impl7755); 
             after(grammarAccess.getForTypeAccess().getLeftCurlyBracketKeyword_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ForType__Group__3__Impl"


    // $ANTLR start "rule__ForType__Group__4"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3830:1: rule__ForType__Group__4 : rule__ForType__Group__4__Impl rule__ForType__Group__5 ;
    public final void rule__ForType__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3834:1: ( rule__ForType__Group__4__Impl rule__ForType__Group__5 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3835:2: rule__ForType__Group__4__Impl rule__ForType__Group__5
            {
            pushFollow(FOLLOW_rule__ForType__Group__4__Impl_in_rule__ForType__Group__47786);
            rule__ForType__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ForType__Group__5_in_rule__ForType__Group__47789);
            rule__ForType__Group__5();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ForType__Group__4"


    // $ANTLR start "rule__ForType__Group__4__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3842:1: rule__ForType__Group__4__Impl : ( ( rule__ForType__LoopCodeAssignment_4 )* ) ;
    public final void rule__ForType__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3846:1: ( ( ( rule__ForType__LoopCodeAssignment_4 )* ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3847:1: ( ( rule__ForType__LoopCodeAssignment_4 )* )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3847:1: ( ( rule__ForType__LoopCodeAssignment_4 )* )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3848:1: ( rule__ForType__LoopCodeAssignment_4 )*
            {
             before(grammarAccess.getForTypeAccess().getLoopCodeAssignment_4()); 
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3849:1: ( rule__ForType__LoopCodeAssignment_4 )*
            loop38:
            do {
                int alt38=2;
                int LA38_0 = input.LA(1);

                if ( (LA38_0==RULE_ID||LA38_0==29||LA38_0==32||LA38_0==35||LA38_0==37||LA38_0==39||LA38_0==44) ) {
                    alt38=1;
                }


                switch (alt38) {
            	case 1 :
            	    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3849:2: rule__ForType__LoopCodeAssignment_4
            	    {
            	    pushFollow(FOLLOW_rule__ForType__LoopCodeAssignment_4_in_rule__ForType__Group__4__Impl7816);
            	    rule__ForType__LoopCodeAssignment_4();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop38;
                }
            } while (true);

             after(grammarAccess.getForTypeAccess().getLoopCodeAssignment_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ForType__Group__4__Impl"


    // $ANTLR start "rule__ForType__Group__5"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3859:1: rule__ForType__Group__5 : rule__ForType__Group__5__Impl ;
    public final void rule__ForType__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3863:1: ( rule__ForType__Group__5__Impl )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3864:2: rule__ForType__Group__5__Impl
            {
            pushFollow(FOLLOW_rule__ForType__Group__5__Impl_in_rule__ForType__Group__57847);
            rule__ForType__Group__5__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ForType__Group__5"


    // $ANTLR start "rule__ForType__Group__5__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3870:1: rule__ForType__Group__5__Impl : ( '}' ) ;
    public final void rule__ForType__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3874:1: ( ( '}' ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3875:1: ( '}' )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3875:1: ( '}' )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3876:1: '}'
            {
             before(grammarAccess.getForTypeAccess().getRightCurlyBracketKeyword_5()); 
            match(input,33,FOLLOW_33_in_rule__ForType__Group__5__Impl7875); 
             after(grammarAccess.getForTypeAccess().getRightCurlyBracketKeyword_5()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ForType__Group__5__Impl"


    // $ANTLR start "rule__ForType__Group_1_0__0"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3901:1: rule__ForType__Group_1_0__0 : rule__ForType__Group_1_0__0__Impl rule__ForType__Group_1_0__1 ;
    public final void rule__ForType__Group_1_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3905:1: ( rule__ForType__Group_1_0__0__Impl rule__ForType__Group_1_0__1 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3906:2: rule__ForType__Group_1_0__0__Impl rule__ForType__Group_1_0__1
            {
            pushFollow(FOLLOW_rule__ForType__Group_1_0__0__Impl_in_rule__ForType__Group_1_0__07918);
            rule__ForType__Group_1_0__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ForType__Group_1_0__1_in_rule__ForType__Group_1_0__07921);
            rule__ForType__Group_1_0__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ForType__Group_1_0__0"


    // $ANTLR start "rule__ForType__Group_1_0__0__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3913:1: rule__ForType__Group_1_0__0__Impl : ( '[' ) ;
    public final void rule__ForType__Group_1_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3917:1: ( ( '[' ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3918:1: ( '[' )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3918:1: ( '[' )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3919:1: '['
            {
             before(grammarAccess.getForTypeAccess().getLeftSquareBracketKeyword_1_0_0()); 
            match(input,35,FOLLOW_35_in_rule__ForType__Group_1_0__0__Impl7949); 
             after(grammarAccess.getForTypeAccess().getLeftSquareBracketKeyword_1_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ForType__Group_1_0__0__Impl"


    // $ANTLR start "rule__ForType__Group_1_0__1"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3932:1: rule__ForType__Group_1_0__1 : rule__ForType__Group_1_0__1__Impl rule__ForType__Group_1_0__2 ;
    public final void rule__ForType__Group_1_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3936:1: ( rule__ForType__Group_1_0__1__Impl rule__ForType__Group_1_0__2 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3937:2: rule__ForType__Group_1_0__1__Impl rule__ForType__Group_1_0__2
            {
            pushFollow(FOLLOW_rule__ForType__Group_1_0__1__Impl_in_rule__ForType__Group_1_0__17980);
            rule__ForType__Group_1_0__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ForType__Group_1_0__2_in_rule__ForType__Group_1_0__17983);
            rule__ForType__Group_1_0__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ForType__Group_1_0__1"


    // $ANTLR start "rule__ForType__Group_1_0__1__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3944:1: rule__ForType__Group_1_0__1__Impl : ( '{' ) ;
    public final void rule__ForType__Group_1_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3948:1: ( ( '{' ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3949:1: ( '{' )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3949:1: ( '{' )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3950:1: '{'
            {
             before(grammarAccess.getForTypeAccess().getLeftCurlyBracketKeyword_1_0_1()); 
            match(input,32,FOLLOW_32_in_rule__ForType__Group_1_0__1__Impl8011); 
             after(grammarAccess.getForTypeAccess().getLeftCurlyBracketKeyword_1_0_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ForType__Group_1_0__1__Impl"


    // $ANTLR start "rule__ForType__Group_1_0__2"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3963:1: rule__ForType__Group_1_0__2 : rule__ForType__Group_1_0__2__Impl rule__ForType__Group_1_0__3 ;
    public final void rule__ForType__Group_1_0__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3967:1: ( rule__ForType__Group_1_0__2__Impl rule__ForType__Group_1_0__3 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3968:2: rule__ForType__Group_1_0__2__Impl rule__ForType__Group_1_0__3
            {
            pushFollow(FOLLOW_rule__ForType__Group_1_0__2__Impl_in_rule__ForType__Group_1_0__28042);
            rule__ForType__Group_1_0__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ForType__Group_1_0__3_in_rule__ForType__Group_1_0__28045);
            rule__ForType__Group_1_0__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ForType__Group_1_0__2"


    // $ANTLR start "rule__ForType__Group_1_0__2__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3975:1: rule__ForType__Group_1_0__2__Impl : ( ( rule__ForType__BeginAssignment_1_0_2 ) ) ;
    public final void rule__ForType__Group_1_0__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3979:1: ( ( ( rule__ForType__BeginAssignment_1_0_2 ) ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3980:1: ( ( rule__ForType__BeginAssignment_1_0_2 ) )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3980:1: ( ( rule__ForType__BeginAssignment_1_0_2 ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3981:1: ( rule__ForType__BeginAssignment_1_0_2 )
            {
             before(grammarAccess.getForTypeAccess().getBeginAssignment_1_0_2()); 
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3982:1: ( rule__ForType__BeginAssignment_1_0_2 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3982:2: rule__ForType__BeginAssignment_1_0_2
            {
            pushFollow(FOLLOW_rule__ForType__BeginAssignment_1_0_2_in_rule__ForType__Group_1_0__2__Impl8072);
            rule__ForType__BeginAssignment_1_0_2();

            state._fsp--;


            }

             after(grammarAccess.getForTypeAccess().getBeginAssignment_1_0_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ForType__Group_1_0__2__Impl"


    // $ANTLR start "rule__ForType__Group_1_0__3"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3992:1: rule__ForType__Group_1_0__3 : rule__ForType__Group_1_0__3__Impl rule__ForType__Group_1_0__4 ;
    public final void rule__ForType__Group_1_0__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3996:1: ( rule__ForType__Group_1_0__3__Impl rule__ForType__Group_1_0__4 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:3997:2: rule__ForType__Group_1_0__3__Impl rule__ForType__Group_1_0__4
            {
            pushFollow(FOLLOW_rule__ForType__Group_1_0__3__Impl_in_rule__ForType__Group_1_0__38102);
            rule__ForType__Group_1_0__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ForType__Group_1_0__4_in_rule__ForType__Group_1_0__38105);
            rule__ForType__Group_1_0__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ForType__Group_1_0__3"


    // $ANTLR start "rule__ForType__Group_1_0__3__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4004:1: rule__ForType__Group_1_0__3__Impl : ( '}' ) ;
    public final void rule__ForType__Group_1_0__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4008:1: ( ( '}' ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4009:1: ( '}' )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4009:1: ( '}' )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4010:1: '}'
            {
             before(grammarAccess.getForTypeAccess().getRightCurlyBracketKeyword_1_0_3()); 
            match(input,33,FOLLOW_33_in_rule__ForType__Group_1_0__3__Impl8133); 
             after(grammarAccess.getForTypeAccess().getRightCurlyBracketKeyword_1_0_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ForType__Group_1_0__3__Impl"


    // $ANTLR start "rule__ForType__Group_1_0__4"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4023:1: rule__ForType__Group_1_0__4 : rule__ForType__Group_1_0__4__Impl rule__ForType__Group_1_0__5 ;
    public final void rule__ForType__Group_1_0__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4027:1: ( rule__ForType__Group_1_0__4__Impl rule__ForType__Group_1_0__5 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4028:2: rule__ForType__Group_1_0__4__Impl rule__ForType__Group_1_0__5
            {
            pushFollow(FOLLOW_rule__ForType__Group_1_0__4__Impl_in_rule__ForType__Group_1_0__48164);
            rule__ForType__Group_1_0__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ForType__Group_1_0__5_in_rule__ForType__Group_1_0__48167);
            rule__ForType__Group_1_0__5();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ForType__Group_1_0__4"


    // $ANTLR start "rule__ForType__Group_1_0__4__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4035:1: rule__ForType__Group_1_0__4__Impl : ( ',' ) ;
    public final void rule__ForType__Group_1_0__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4039:1: ( ( ',' ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4040:1: ( ',' )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4040:1: ( ',' )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4041:1: ','
            {
             before(grammarAccess.getForTypeAccess().getCommaKeyword_1_0_4()); 
            match(input,28,FOLLOW_28_in_rule__ForType__Group_1_0__4__Impl8195); 
             after(grammarAccess.getForTypeAccess().getCommaKeyword_1_0_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ForType__Group_1_0__4__Impl"


    // $ANTLR start "rule__ForType__Group_1_0__5"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4054:1: rule__ForType__Group_1_0__5 : rule__ForType__Group_1_0__5__Impl rule__ForType__Group_1_0__6 ;
    public final void rule__ForType__Group_1_0__5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4058:1: ( rule__ForType__Group_1_0__5__Impl rule__ForType__Group_1_0__6 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4059:2: rule__ForType__Group_1_0__5__Impl rule__ForType__Group_1_0__6
            {
            pushFollow(FOLLOW_rule__ForType__Group_1_0__5__Impl_in_rule__ForType__Group_1_0__58226);
            rule__ForType__Group_1_0__5__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ForType__Group_1_0__6_in_rule__ForType__Group_1_0__58229);
            rule__ForType__Group_1_0__6();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ForType__Group_1_0__5"


    // $ANTLR start "rule__ForType__Group_1_0__5__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4066:1: rule__ForType__Group_1_0__5__Impl : ( '{' ) ;
    public final void rule__ForType__Group_1_0__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4070:1: ( ( '{' ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4071:1: ( '{' )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4071:1: ( '{' )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4072:1: '{'
            {
             before(grammarAccess.getForTypeAccess().getLeftCurlyBracketKeyword_1_0_5()); 
            match(input,32,FOLLOW_32_in_rule__ForType__Group_1_0__5__Impl8257); 
             after(grammarAccess.getForTypeAccess().getLeftCurlyBracketKeyword_1_0_5()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ForType__Group_1_0__5__Impl"


    // $ANTLR start "rule__ForType__Group_1_0__6"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4085:1: rule__ForType__Group_1_0__6 : rule__ForType__Group_1_0__6__Impl rule__ForType__Group_1_0__7 ;
    public final void rule__ForType__Group_1_0__6() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4089:1: ( rule__ForType__Group_1_0__6__Impl rule__ForType__Group_1_0__7 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4090:2: rule__ForType__Group_1_0__6__Impl rule__ForType__Group_1_0__7
            {
            pushFollow(FOLLOW_rule__ForType__Group_1_0__6__Impl_in_rule__ForType__Group_1_0__68288);
            rule__ForType__Group_1_0__6__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ForType__Group_1_0__7_in_rule__ForType__Group_1_0__68291);
            rule__ForType__Group_1_0__7();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ForType__Group_1_0__6"


    // $ANTLR start "rule__ForType__Group_1_0__6__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4097:1: rule__ForType__Group_1_0__6__Impl : ( ( rule__ForType__ConditionAssignment_1_0_6 ) ) ;
    public final void rule__ForType__Group_1_0__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4101:1: ( ( ( rule__ForType__ConditionAssignment_1_0_6 ) ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4102:1: ( ( rule__ForType__ConditionAssignment_1_0_6 ) )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4102:1: ( ( rule__ForType__ConditionAssignment_1_0_6 ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4103:1: ( rule__ForType__ConditionAssignment_1_0_6 )
            {
             before(grammarAccess.getForTypeAccess().getConditionAssignment_1_0_6()); 
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4104:1: ( rule__ForType__ConditionAssignment_1_0_6 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4104:2: rule__ForType__ConditionAssignment_1_0_6
            {
            pushFollow(FOLLOW_rule__ForType__ConditionAssignment_1_0_6_in_rule__ForType__Group_1_0__6__Impl8318);
            rule__ForType__ConditionAssignment_1_0_6();

            state._fsp--;


            }

             after(grammarAccess.getForTypeAccess().getConditionAssignment_1_0_6()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ForType__Group_1_0__6__Impl"


    // $ANTLR start "rule__ForType__Group_1_0__7"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4114:1: rule__ForType__Group_1_0__7 : rule__ForType__Group_1_0__7__Impl rule__ForType__Group_1_0__8 ;
    public final void rule__ForType__Group_1_0__7() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4118:1: ( rule__ForType__Group_1_0__7__Impl rule__ForType__Group_1_0__8 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4119:2: rule__ForType__Group_1_0__7__Impl rule__ForType__Group_1_0__8
            {
            pushFollow(FOLLOW_rule__ForType__Group_1_0__7__Impl_in_rule__ForType__Group_1_0__78348);
            rule__ForType__Group_1_0__7__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ForType__Group_1_0__8_in_rule__ForType__Group_1_0__78351);
            rule__ForType__Group_1_0__8();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ForType__Group_1_0__7"


    // $ANTLR start "rule__ForType__Group_1_0__7__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4126:1: rule__ForType__Group_1_0__7__Impl : ( '}' ) ;
    public final void rule__ForType__Group_1_0__7__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4130:1: ( ( '}' ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4131:1: ( '}' )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4131:1: ( '}' )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4132:1: '}'
            {
             before(grammarAccess.getForTypeAccess().getRightCurlyBracketKeyword_1_0_7()); 
            match(input,33,FOLLOW_33_in_rule__ForType__Group_1_0__7__Impl8379); 
             after(grammarAccess.getForTypeAccess().getRightCurlyBracketKeyword_1_0_7()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ForType__Group_1_0__7__Impl"


    // $ANTLR start "rule__ForType__Group_1_0__8"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4145:1: rule__ForType__Group_1_0__8 : rule__ForType__Group_1_0__8__Impl rule__ForType__Group_1_0__9 ;
    public final void rule__ForType__Group_1_0__8() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4149:1: ( rule__ForType__Group_1_0__8__Impl rule__ForType__Group_1_0__9 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4150:2: rule__ForType__Group_1_0__8__Impl rule__ForType__Group_1_0__9
            {
            pushFollow(FOLLOW_rule__ForType__Group_1_0__8__Impl_in_rule__ForType__Group_1_0__88410);
            rule__ForType__Group_1_0__8__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ForType__Group_1_0__9_in_rule__ForType__Group_1_0__88413);
            rule__ForType__Group_1_0__9();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ForType__Group_1_0__8"


    // $ANTLR start "rule__ForType__Group_1_0__8__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4157:1: rule__ForType__Group_1_0__8__Impl : ( ',' ) ;
    public final void rule__ForType__Group_1_0__8__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4161:1: ( ( ',' ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4162:1: ( ',' )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4162:1: ( ',' )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4163:1: ','
            {
             before(grammarAccess.getForTypeAccess().getCommaKeyword_1_0_8()); 
            match(input,28,FOLLOW_28_in_rule__ForType__Group_1_0__8__Impl8441); 
             after(grammarAccess.getForTypeAccess().getCommaKeyword_1_0_8()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ForType__Group_1_0__8__Impl"


    // $ANTLR start "rule__ForType__Group_1_0__9"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4176:1: rule__ForType__Group_1_0__9 : rule__ForType__Group_1_0__9__Impl rule__ForType__Group_1_0__10 ;
    public final void rule__ForType__Group_1_0__9() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4180:1: ( rule__ForType__Group_1_0__9__Impl rule__ForType__Group_1_0__10 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4181:2: rule__ForType__Group_1_0__9__Impl rule__ForType__Group_1_0__10
            {
            pushFollow(FOLLOW_rule__ForType__Group_1_0__9__Impl_in_rule__ForType__Group_1_0__98472);
            rule__ForType__Group_1_0__9__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ForType__Group_1_0__10_in_rule__ForType__Group_1_0__98475);
            rule__ForType__Group_1_0__10();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ForType__Group_1_0__9"


    // $ANTLR start "rule__ForType__Group_1_0__9__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4188:1: rule__ForType__Group_1_0__9__Impl : ( '{' ) ;
    public final void rule__ForType__Group_1_0__9__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4192:1: ( ( '{' ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4193:1: ( '{' )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4193:1: ( '{' )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4194:1: '{'
            {
             before(grammarAccess.getForTypeAccess().getLeftCurlyBracketKeyword_1_0_9()); 
            match(input,32,FOLLOW_32_in_rule__ForType__Group_1_0__9__Impl8503); 
             after(grammarAccess.getForTypeAccess().getLeftCurlyBracketKeyword_1_0_9()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ForType__Group_1_0__9__Impl"


    // $ANTLR start "rule__ForType__Group_1_0__10"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4207:1: rule__ForType__Group_1_0__10 : rule__ForType__Group_1_0__10__Impl rule__ForType__Group_1_0__11 ;
    public final void rule__ForType__Group_1_0__10() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4211:1: ( rule__ForType__Group_1_0__10__Impl rule__ForType__Group_1_0__11 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4212:2: rule__ForType__Group_1_0__10__Impl rule__ForType__Group_1_0__11
            {
            pushFollow(FOLLOW_rule__ForType__Group_1_0__10__Impl_in_rule__ForType__Group_1_0__108534);
            rule__ForType__Group_1_0__10__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ForType__Group_1_0__11_in_rule__ForType__Group_1_0__108537);
            rule__ForType__Group_1_0__11();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ForType__Group_1_0__10"


    // $ANTLR start "rule__ForType__Group_1_0__10__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4219:1: rule__ForType__Group_1_0__10__Impl : ( ( rule__ForType__EndAssignment_1_0_10 ) ) ;
    public final void rule__ForType__Group_1_0__10__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4223:1: ( ( ( rule__ForType__EndAssignment_1_0_10 ) ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4224:1: ( ( rule__ForType__EndAssignment_1_0_10 ) )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4224:1: ( ( rule__ForType__EndAssignment_1_0_10 ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4225:1: ( rule__ForType__EndAssignment_1_0_10 )
            {
             before(grammarAccess.getForTypeAccess().getEndAssignment_1_0_10()); 
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4226:1: ( rule__ForType__EndAssignment_1_0_10 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4226:2: rule__ForType__EndAssignment_1_0_10
            {
            pushFollow(FOLLOW_rule__ForType__EndAssignment_1_0_10_in_rule__ForType__Group_1_0__10__Impl8564);
            rule__ForType__EndAssignment_1_0_10();

            state._fsp--;


            }

             after(grammarAccess.getForTypeAccess().getEndAssignment_1_0_10()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ForType__Group_1_0__10__Impl"


    // $ANTLR start "rule__ForType__Group_1_0__11"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4236:1: rule__ForType__Group_1_0__11 : rule__ForType__Group_1_0__11__Impl rule__ForType__Group_1_0__12 ;
    public final void rule__ForType__Group_1_0__11() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4240:1: ( rule__ForType__Group_1_0__11__Impl rule__ForType__Group_1_0__12 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4241:2: rule__ForType__Group_1_0__11__Impl rule__ForType__Group_1_0__12
            {
            pushFollow(FOLLOW_rule__ForType__Group_1_0__11__Impl_in_rule__ForType__Group_1_0__118594);
            rule__ForType__Group_1_0__11__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ForType__Group_1_0__12_in_rule__ForType__Group_1_0__118597);
            rule__ForType__Group_1_0__12();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ForType__Group_1_0__11"


    // $ANTLR start "rule__ForType__Group_1_0__11__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4248:1: rule__ForType__Group_1_0__11__Impl : ( '}' ) ;
    public final void rule__ForType__Group_1_0__11__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4252:1: ( ( '}' ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4253:1: ( '}' )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4253:1: ( '}' )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4254:1: '}'
            {
             before(grammarAccess.getForTypeAccess().getRightCurlyBracketKeyword_1_0_11()); 
            match(input,33,FOLLOW_33_in_rule__ForType__Group_1_0__11__Impl8625); 
             after(grammarAccess.getForTypeAccess().getRightCurlyBracketKeyword_1_0_11()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ForType__Group_1_0__11__Impl"


    // $ANTLR start "rule__ForType__Group_1_0__12"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4267:1: rule__ForType__Group_1_0__12 : rule__ForType__Group_1_0__12__Impl ;
    public final void rule__ForType__Group_1_0__12() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4271:1: ( rule__ForType__Group_1_0__12__Impl )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4272:2: rule__ForType__Group_1_0__12__Impl
            {
            pushFollow(FOLLOW_rule__ForType__Group_1_0__12__Impl_in_rule__ForType__Group_1_0__128656);
            rule__ForType__Group_1_0__12__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ForType__Group_1_0__12"


    // $ANTLR start "rule__ForType__Group_1_0__12__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4278:1: rule__ForType__Group_1_0__12__Impl : ( ']' ) ;
    public final void rule__ForType__Group_1_0__12__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4282:1: ( ( ']' ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4283:1: ( ']' )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4283:1: ( ']' )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4284:1: ']'
            {
             before(grammarAccess.getForTypeAccess().getRightSquareBracketKeyword_1_0_12()); 
            match(input,27,FOLLOW_27_in_rule__ForType__Group_1_0__12__Impl8684); 
             after(grammarAccess.getForTypeAccess().getRightSquareBracketKeyword_1_0_12()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ForType__Group_1_0__12__Impl"


    // $ANTLR start "rule__ForType__Group_1_1__0"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4323:1: rule__ForType__Group_1_1__0 : rule__ForType__Group_1_1__0__Impl rule__ForType__Group_1_1__1 ;
    public final void rule__ForType__Group_1_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4327:1: ( rule__ForType__Group_1_1__0__Impl rule__ForType__Group_1_1__1 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4328:2: rule__ForType__Group_1_1__0__Impl rule__ForType__Group_1_1__1
            {
            pushFollow(FOLLOW_rule__ForType__Group_1_1__0__Impl_in_rule__ForType__Group_1_1__08741);
            rule__ForType__Group_1_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ForType__Group_1_1__1_in_rule__ForType__Group_1_1__08744);
            rule__ForType__Group_1_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ForType__Group_1_1__0"


    // $ANTLR start "rule__ForType__Group_1_1__0__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4335:1: rule__ForType__Group_1_1__0__Impl : ( ruleforVarDeclaration ) ;
    public final void rule__ForType__Group_1_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4339:1: ( ( ruleforVarDeclaration ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4340:1: ( ruleforVarDeclaration )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4340:1: ( ruleforVarDeclaration )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4341:1: ruleforVarDeclaration
            {
             before(grammarAccess.getForTypeAccess().getForVarDeclarationParserRuleCall_1_1_0()); 
            pushFollow(FOLLOW_ruleforVarDeclaration_in_rule__ForType__Group_1_1__0__Impl8771);
            ruleforVarDeclaration();

            state._fsp--;

             after(grammarAccess.getForTypeAccess().getForVarDeclarationParserRuleCall_1_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ForType__Group_1_1__0__Impl"


    // $ANTLR start "rule__ForType__Group_1_1__1"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4352:1: rule__ForType__Group_1_1__1 : rule__ForType__Group_1_1__1__Impl rule__ForType__Group_1_1__2 ;
    public final void rule__ForType__Group_1_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4356:1: ( rule__ForType__Group_1_1__1__Impl rule__ForType__Group_1_1__2 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4357:2: rule__ForType__Group_1_1__1__Impl rule__ForType__Group_1_1__2
            {
            pushFollow(FOLLOW_rule__ForType__Group_1_1__1__Impl_in_rule__ForType__Group_1_1__18800);
            rule__ForType__Group_1_1__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ForType__Group_1_1__2_in_rule__ForType__Group_1_1__18803);
            rule__ForType__Group_1_1__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ForType__Group_1_1__1"


    // $ANTLR start "rule__ForType__Group_1_1__1__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4364:1: rule__ForType__Group_1_1__1__Impl : ( 'from' ) ;
    public final void rule__ForType__Group_1_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4368:1: ( ( 'from' ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4369:1: ( 'from' )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4369:1: ( 'from' )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4370:1: 'from'
            {
             before(grammarAccess.getForTypeAccess().getFromKeyword_1_1_1()); 
            match(input,40,FOLLOW_40_in_rule__ForType__Group_1_1__1__Impl8831); 
             after(grammarAccess.getForTypeAccess().getFromKeyword_1_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ForType__Group_1_1__1__Impl"


    // $ANTLR start "rule__ForType__Group_1_1__2"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4383:1: rule__ForType__Group_1_1__2 : rule__ForType__Group_1_1__2__Impl rule__ForType__Group_1_1__3 ;
    public final void rule__ForType__Group_1_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4387:1: ( rule__ForType__Group_1_1__2__Impl rule__ForType__Group_1_1__3 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4388:2: rule__ForType__Group_1_1__2__Impl rule__ForType__Group_1_1__3
            {
            pushFollow(FOLLOW_rule__ForType__Group_1_1__2__Impl_in_rule__ForType__Group_1_1__28862);
            rule__ForType__Group_1_1__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ForType__Group_1_1__3_in_rule__ForType__Group_1_1__28865);
            rule__ForType__Group_1_1__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ForType__Group_1_1__2"


    // $ANTLR start "rule__ForType__Group_1_1__2__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4395:1: rule__ForType__Group_1_1__2__Impl : ( ( rule__ForType__FromAssignment_1_1_2 ) ) ;
    public final void rule__ForType__Group_1_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4399:1: ( ( ( rule__ForType__FromAssignment_1_1_2 ) ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4400:1: ( ( rule__ForType__FromAssignment_1_1_2 ) )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4400:1: ( ( rule__ForType__FromAssignment_1_1_2 ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4401:1: ( rule__ForType__FromAssignment_1_1_2 )
            {
             before(grammarAccess.getForTypeAccess().getFromAssignment_1_1_2()); 
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4402:1: ( rule__ForType__FromAssignment_1_1_2 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4402:2: rule__ForType__FromAssignment_1_1_2
            {
            pushFollow(FOLLOW_rule__ForType__FromAssignment_1_1_2_in_rule__ForType__Group_1_1__2__Impl8892);
            rule__ForType__FromAssignment_1_1_2();

            state._fsp--;


            }

             after(grammarAccess.getForTypeAccess().getFromAssignment_1_1_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ForType__Group_1_1__2__Impl"


    // $ANTLR start "rule__ForType__Group_1_1__3"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4412:1: rule__ForType__Group_1_1__3 : rule__ForType__Group_1_1__3__Impl rule__ForType__Group_1_1__4 ;
    public final void rule__ForType__Group_1_1__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4416:1: ( rule__ForType__Group_1_1__3__Impl rule__ForType__Group_1_1__4 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4417:2: rule__ForType__Group_1_1__3__Impl rule__ForType__Group_1_1__4
            {
            pushFollow(FOLLOW_rule__ForType__Group_1_1__3__Impl_in_rule__ForType__Group_1_1__38922);
            rule__ForType__Group_1_1__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ForType__Group_1_1__4_in_rule__ForType__Group_1_1__38925);
            rule__ForType__Group_1_1__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ForType__Group_1_1__3"


    // $ANTLR start "rule__ForType__Group_1_1__3__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4424:1: rule__ForType__Group_1_1__3__Impl : ( 'to' ) ;
    public final void rule__ForType__Group_1_1__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4428:1: ( ( 'to' ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4429:1: ( 'to' )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4429:1: ( 'to' )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4430:1: 'to'
            {
             before(grammarAccess.getForTypeAccess().getToKeyword_1_1_3()); 
            match(input,41,FOLLOW_41_in_rule__ForType__Group_1_1__3__Impl8953); 
             after(grammarAccess.getForTypeAccess().getToKeyword_1_1_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ForType__Group_1_1__3__Impl"


    // $ANTLR start "rule__ForType__Group_1_1__4"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4443:1: rule__ForType__Group_1_1__4 : rule__ForType__Group_1_1__4__Impl rule__ForType__Group_1_1__5 ;
    public final void rule__ForType__Group_1_1__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4447:1: ( rule__ForType__Group_1_1__4__Impl rule__ForType__Group_1_1__5 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4448:2: rule__ForType__Group_1_1__4__Impl rule__ForType__Group_1_1__5
            {
            pushFollow(FOLLOW_rule__ForType__Group_1_1__4__Impl_in_rule__ForType__Group_1_1__48984);
            rule__ForType__Group_1_1__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ForType__Group_1_1__5_in_rule__ForType__Group_1_1__48987);
            rule__ForType__Group_1_1__5();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ForType__Group_1_1__4"


    // $ANTLR start "rule__ForType__Group_1_1__4__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4455:1: rule__ForType__Group_1_1__4__Impl : ( ( rule__ForType__ToAssignment_1_1_4 ) ) ;
    public final void rule__ForType__Group_1_1__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4459:1: ( ( ( rule__ForType__ToAssignment_1_1_4 ) ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4460:1: ( ( rule__ForType__ToAssignment_1_1_4 ) )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4460:1: ( ( rule__ForType__ToAssignment_1_1_4 ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4461:1: ( rule__ForType__ToAssignment_1_1_4 )
            {
             before(grammarAccess.getForTypeAccess().getToAssignment_1_1_4()); 
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4462:1: ( rule__ForType__ToAssignment_1_1_4 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4462:2: rule__ForType__ToAssignment_1_1_4
            {
            pushFollow(FOLLOW_rule__ForType__ToAssignment_1_1_4_in_rule__ForType__Group_1_1__4__Impl9014);
            rule__ForType__ToAssignment_1_1_4();

            state._fsp--;


            }

             after(grammarAccess.getForTypeAccess().getToAssignment_1_1_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ForType__Group_1_1__4__Impl"


    // $ANTLR start "rule__ForType__Group_1_1__5"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4472:1: rule__ForType__Group_1_1__5 : rule__ForType__Group_1_1__5__Impl ;
    public final void rule__ForType__Group_1_1__5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4476:1: ( rule__ForType__Group_1_1__5__Impl )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4477:2: rule__ForType__Group_1_1__5__Impl
            {
            pushFollow(FOLLOW_rule__ForType__Group_1_1__5__Impl_in_rule__ForType__Group_1_1__59044);
            rule__ForType__Group_1_1__5__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ForType__Group_1_1__5"


    // $ANTLR start "rule__ForType__Group_1_1__5__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4483:1: rule__ForType__Group_1_1__5__Impl : ( ( rule__ForType__Group_1_1_5__0 )? ) ;
    public final void rule__ForType__Group_1_1__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4487:1: ( ( ( rule__ForType__Group_1_1_5__0 )? ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4488:1: ( ( rule__ForType__Group_1_1_5__0 )? )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4488:1: ( ( rule__ForType__Group_1_1_5__0 )? )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4489:1: ( rule__ForType__Group_1_1_5__0 )?
            {
             before(grammarAccess.getForTypeAccess().getGroup_1_1_5()); 
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4490:1: ( rule__ForType__Group_1_1_5__0 )?
            int alt39=2;
            int LA39_0 = input.LA(1);

            if ( (LA39_0==42) ) {
                alt39=1;
            }
            switch (alt39) {
                case 1 :
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4490:2: rule__ForType__Group_1_1_5__0
                    {
                    pushFollow(FOLLOW_rule__ForType__Group_1_1_5__0_in_rule__ForType__Group_1_1__5__Impl9071);
                    rule__ForType__Group_1_1_5__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getForTypeAccess().getGroup_1_1_5()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ForType__Group_1_1__5__Impl"


    // $ANTLR start "rule__ForType__Group_1_1_5__0"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4512:1: rule__ForType__Group_1_1_5__0 : rule__ForType__Group_1_1_5__0__Impl rule__ForType__Group_1_1_5__1 ;
    public final void rule__ForType__Group_1_1_5__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4516:1: ( rule__ForType__Group_1_1_5__0__Impl rule__ForType__Group_1_1_5__1 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4517:2: rule__ForType__Group_1_1_5__0__Impl rule__ForType__Group_1_1_5__1
            {
            pushFollow(FOLLOW_rule__ForType__Group_1_1_5__0__Impl_in_rule__ForType__Group_1_1_5__09114);
            rule__ForType__Group_1_1_5__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ForType__Group_1_1_5__1_in_rule__ForType__Group_1_1_5__09117);
            rule__ForType__Group_1_1_5__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ForType__Group_1_1_5__0"


    // $ANTLR start "rule__ForType__Group_1_1_5__0__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4524:1: rule__ForType__Group_1_1_5__0__Impl : ( 'step' ) ;
    public final void rule__ForType__Group_1_1_5__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4528:1: ( ( 'step' ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4529:1: ( 'step' )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4529:1: ( 'step' )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4530:1: 'step'
            {
             before(grammarAccess.getForTypeAccess().getStepKeyword_1_1_5_0()); 
            match(input,42,FOLLOW_42_in_rule__ForType__Group_1_1_5__0__Impl9145); 
             after(grammarAccess.getForTypeAccess().getStepKeyword_1_1_5_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ForType__Group_1_1_5__0__Impl"


    // $ANTLR start "rule__ForType__Group_1_1_5__1"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4543:1: rule__ForType__Group_1_1_5__1 : rule__ForType__Group_1_1_5__1__Impl ;
    public final void rule__ForType__Group_1_1_5__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4547:1: ( rule__ForType__Group_1_1_5__1__Impl )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4548:2: rule__ForType__Group_1_1_5__1__Impl
            {
            pushFollow(FOLLOW_rule__ForType__Group_1_1_5__1__Impl_in_rule__ForType__Group_1_1_5__19176);
            rule__ForType__Group_1_1_5__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ForType__Group_1_1_5__1"


    // $ANTLR start "rule__ForType__Group_1_1_5__1__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4554:1: rule__ForType__Group_1_1_5__1__Impl : ( ( rule__ForType__StepAssignment_1_1_5_1 ) ) ;
    public final void rule__ForType__Group_1_1_5__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4558:1: ( ( ( rule__ForType__StepAssignment_1_1_5_1 ) ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4559:1: ( ( rule__ForType__StepAssignment_1_1_5_1 ) )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4559:1: ( ( rule__ForType__StepAssignment_1_1_5_1 ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4560:1: ( rule__ForType__StepAssignment_1_1_5_1 )
            {
             before(grammarAccess.getForTypeAccess().getStepAssignment_1_1_5_1()); 
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4561:1: ( rule__ForType__StepAssignment_1_1_5_1 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4561:2: rule__ForType__StepAssignment_1_1_5_1
            {
            pushFollow(FOLLOW_rule__ForType__StepAssignment_1_1_5_1_in_rule__ForType__Group_1_1_5__1__Impl9203);
            rule__ForType__StepAssignment_1_1_5_1();

            state._fsp--;


            }

             after(grammarAccess.getForTypeAccess().getStepAssignment_1_1_5_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ForType__Group_1_1_5__1__Impl"


    // $ANTLR start "rule__ForeachType__Group__0"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4575:1: rule__ForeachType__Group__0 : rule__ForeachType__Group__0__Impl rule__ForeachType__Group__1 ;
    public final void rule__ForeachType__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4579:1: ( rule__ForeachType__Group__0__Impl rule__ForeachType__Group__1 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4580:2: rule__ForeachType__Group__0__Impl rule__ForeachType__Group__1
            {
            pushFollow(FOLLOW_rule__ForeachType__Group__0__Impl_in_rule__ForeachType__Group__09237);
            rule__ForeachType__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ForeachType__Group__1_in_rule__ForeachType__Group__09240);
            rule__ForeachType__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ForeachType__Group__0"


    // $ANTLR start "rule__ForeachType__Group__0__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4587:1: rule__ForeachType__Group__0__Impl : ( '{' ) ;
    public final void rule__ForeachType__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4591:1: ( ( '{' ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4592:1: ( '{' )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4592:1: ( '{' )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4593:1: '{'
            {
             before(grammarAccess.getForeachTypeAccess().getLeftCurlyBracketKeyword_0()); 
            match(input,32,FOLLOW_32_in_rule__ForeachType__Group__0__Impl9268); 
             after(grammarAccess.getForeachTypeAccess().getLeftCurlyBracketKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ForeachType__Group__0__Impl"


    // $ANTLR start "rule__ForeachType__Group__1"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4606:1: rule__ForeachType__Group__1 : rule__ForeachType__Group__1__Impl rule__ForeachType__Group__2 ;
    public final void rule__ForeachType__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4610:1: ( rule__ForeachType__Group__1__Impl rule__ForeachType__Group__2 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4611:2: rule__ForeachType__Group__1__Impl rule__ForeachType__Group__2
            {
            pushFollow(FOLLOW_rule__ForeachType__Group__1__Impl_in_rule__ForeachType__Group__19299);
            rule__ForeachType__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ForeachType__Group__2_in_rule__ForeachType__Group__19302);
            rule__ForeachType__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ForeachType__Group__1"


    // $ANTLR start "rule__ForeachType__Group__1__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4618:1: rule__ForeachType__Group__1__Impl : ( ( rule__ForeachType__CodeAssignment_1 )* ) ;
    public final void rule__ForeachType__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4622:1: ( ( ( rule__ForeachType__CodeAssignment_1 )* ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4623:1: ( ( rule__ForeachType__CodeAssignment_1 )* )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4623:1: ( ( rule__ForeachType__CodeAssignment_1 )* )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4624:1: ( rule__ForeachType__CodeAssignment_1 )*
            {
             before(grammarAccess.getForeachTypeAccess().getCodeAssignment_1()); 
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4625:1: ( rule__ForeachType__CodeAssignment_1 )*
            loop40:
            do {
                int alt40=2;
                int LA40_0 = input.LA(1);

                if ( (LA40_0==RULE_ID||LA40_0==29||LA40_0==32||LA40_0==35||LA40_0==37||LA40_0==39||LA40_0==44) ) {
                    alt40=1;
                }


                switch (alt40) {
            	case 1 :
            	    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4625:2: rule__ForeachType__CodeAssignment_1
            	    {
            	    pushFollow(FOLLOW_rule__ForeachType__CodeAssignment_1_in_rule__ForeachType__Group__1__Impl9329);
            	    rule__ForeachType__CodeAssignment_1();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop40;
                }
            } while (true);

             after(grammarAccess.getForeachTypeAccess().getCodeAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ForeachType__Group__1__Impl"


    // $ANTLR start "rule__ForeachType__Group__2"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4635:1: rule__ForeachType__Group__2 : rule__ForeachType__Group__2__Impl rule__ForeachType__Group__3 ;
    public final void rule__ForeachType__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4639:1: ( rule__ForeachType__Group__2__Impl rule__ForeachType__Group__3 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4640:2: rule__ForeachType__Group__2__Impl rule__ForeachType__Group__3
            {
            pushFollow(FOLLOW_rule__ForeachType__Group__2__Impl_in_rule__ForeachType__Group__29360);
            rule__ForeachType__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ForeachType__Group__3_in_rule__ForeachType__Group__29363);
            rule__ForeachType__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ForeachType__Group__2"


    // $ANTLR start "rule__ForeachType__Group__2__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4647:1: rule__ForeachType__Group__2__Impl : ( '}' ) ;
    public final void rule__ForeachType__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4651:1: ( ( '}' ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4652:1: ( '}' )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4652:1: ( '}' )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4653:1: '}'
            {
             before(grammarAccess.getForeachTypeAccess().getRightCurlyBracketKeyword_2()); 
            match(input,33,FOLLOW_33_in_rule__ForeachType__Group__2__Impl9391); 
             after(grammarAccess.getForeachTypeAccess().getRightCurlyBracketKeyword_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ForeachType__Group__2__Impl"


    // $ANTLR start "rule__ForeachType__Group__3"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4666:1: rule__ForeachType__Group__3 : rule__ForeachType__Group__3__Impl rule__ForeachType__Group__4 ;
    public final void rule__ForeachType__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4670:1: ( rule__ForeachType__Group__3__Impl rule__ForeachType__Group__4 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4671:2: rule__ForeachType__Group__3__Impl rule__ForeachType__Group__4
            {
            pushFollow(FOLLOW_rule__ForeachType__Group__3__Impl_in_rule__ForeachType__Group__39422);
            rule__ForeachType__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ForeachType__Group__4_in_rule__ForeachType__Group__39425);
            rule__ForeachType__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ForeachType__Group__3"


    // $ANTLR start "rule__ForeachType__Group__3__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4678:1: rule__ForeachType__Group__3__Impl : ( 'forEach' ) ;
    public final void rule__ForeachType__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4682:1: ( ( 'forEach' ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4683:1: ( 'forEach' )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4683:1: ( 'forEach' )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4684:1: 'forEach'
            {
             before(grammarAccess.getForeachTypeAccess().getForEachKeyword_3()); 
            match(input,43,FOLLOW_43_in_rule__ForeachType__Group__3__Impl9453); 
             after(grammarAccess.getForeachTypeAccess().getForEachKeyword_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ForeachType__Group__3__Impl"


    // $ANTLR start "rule__ForeachType__Group__4"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4697:1: rule__ForeachType__Group__4 : rule__ForeachType__Group__4__Impl ;
    public final void rule__ForeachType__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4701:1: ( rule__ForeachType__Group__4__Impl )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4702:2: rule__ForeachType__Group__4__Impl
            {
            pushFollow(FOLLOW_rule__ForeachType__Group__4__Impl_in_rule__ForeachType__Group__49484);
            rule__ForeachType__Group__4__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ForeachType__Group__4"


    // $ANTLR start "rule__ForeachType__Group__4__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4708:1: rule__ForeachType__Group__4__Impl : ( ( rule__ForeachType__Alternatives_4 ) ) ;
    public final void rule__ForeachType__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4712:1: ( ( ( rule__ForeachType__Alternatives_4 ) ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4713:1: ( ( rule__ForeachType__Alternatives_4 ) )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4713:1: ( ( rule__ForeachType__Alternatives_4 ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4714:1: ( rule__ForeachType__Alternatives_4 )
            {
             before(grammarAccess.getForeachTypeAccess().getAlternatives_4()); 
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4715:1: ( rule__ForeachType__Alternatives_4 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4715:2: rule__ForeachType__Alternatives_4
            {
            pushFollow(FOLLOW_rule__ForeachType__Alternatives_4_in_rule__ForeachType__Group__4__Impl9511);
            rule__ForeachType__Alternatives_4();

            state._fsp--;


            }

             after(grammarAccess.getForeachTypeAccess().getAlternatives_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ForeachType__Group__4__Impl"


    // $ANTLR start "rule__SwitchType__Group__0"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4735:1: rule__SwitchType__Group__0 : rule__SwitchType__Group__0__Impl rule__SwitchType__Group__1 ;
    public final void rule__SwitchType__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4739:1: ( rule__SwitchType__Group__0__Impl rule__SwitchType__Group__1 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4740:2: rule__SwitchType__Group__0__Impl rule__SwitchType__Group__1
            {
            pushFollow(FOLLOW_rule__SwitchType__Group__0__Impl_in_rule__SwitchType__Group__09551);
            rule__SwitchType__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__SwitchType__Group__1_in_rule__SwitchType__Group__09554);
            rule__SwitchType__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SwitchType__Group__0"


    // $ANTLR start "rule__SwitchType__Group__0__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4747:1: rule__SwitchType__Group__0__Impl : ( 'switch' ) ;
    public final void rule__SwitchType__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4751:1: ( ( 'switch' ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4752:1: ( 'switch' )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4752:1: ( 'switch' )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4753:1: 'switch'
            {
             before(grammarAccess.getSwitchTypeAccess().getSwitchKeyword_0()); 
            match(input,44,FOLLOW_44_in_rule__SwitchType__Group__0__Impl9582); 
             after(grammarAccess.getSwitchTypeAccess().getSwitchKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SwitchType__Group__0__Impl"


    // $ANTLR start "rule__SwitchType__Group__1"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4766:1: rule__SwitchType__Group__1 : rule__SwitchType__Group__1__Impl rule__SwitchType__Group__2 ;
    public final void rule__SwitchType__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4770:1: ( rule__SwitchType__Group__1__Impl rule__SwitchType__Group__2 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4771:2: rule__SwitchType__Group__1__Impl rule__SwitchType__Group__2
            {
            pushFollow(FOLLOW_rule__SwitchType__Group__1__Impl_in_rule__SwitchType__Group__19613);
            rule__SwitchType__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__SwitchType__Group__2_in_rule__SwitchType__Group__19616);
            rule__SwitchType__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SwitchType__Group__1"


    // $ANTLR start "rule__SwitchType__Group__1__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4778:1: rule__SwitchType__Group__1__Impl : ( '(' ) ;
    public final void rule__SwitchType__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4782:1: ( ( '(' ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4783:1: ( '(' )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4783:1: ( '(' )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4784:1: '('
            {
             before(grammarAccess.getSwitchTypeAccess().getLeftParenthesisKeyword_1()); 
            match(input,30,FOLLOW_30_in_rule__SwitchType__Group__1__Impl9644); 
             after(grammarAccess.getSwitchTypeAccess().getLeftParenthesisKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SwitchType__Group__1__Impl"


    // $ANTLR start "rule__SwitchType__Group__2"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4797:1: rule__SwitchType__Group__2 : rule__SwitchType__Group__2__Impl rule__SwitchType__Group__3 ;
    public final void rule__SwitchType__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4801:1: ( rule__SwitchType__Group__2__Impl rule__SwitchType__Group__3 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4802:2: rule__SwitchType__Group__2__Impl rule__SwitchType__Group__3
            {
            pushFollow(FOLLOW_rule__SwitchType__Group__2__Impl_in_rule__SwitchType__Group__29675);
            rule__SwitchType__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__SwitchType__Group__3_in_rule__SwitchType__Group__29678);
            rule__SwitchType__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SwitchType__Group__2"


    // $ANTLR start "rule__SwitchType__Group__2__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4809:1: rule__SwitchType__Group__2__Impl : ( ( rule__SwitchType__VarAssignment_2 ) ) ;
    public final void rule__SwitchType__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4813:1: ( ( ( rule__SwitchType__VarAssignment_2 ) ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4814:1: ( ( rule__SwitchType__VarAssignment_2 ) )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4814:1: ( ( rule__SwitchType__VarAssignment_2 ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4815:1: ( rule__SwitchType__VarAssignment_2 )
            {
             before(grammarAccess.getSwitchTypeAccess().getVarAssignment_2()); 
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4816:1: ( rule__SwitchType__VarAssignment_2 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4816:2: rule__SwitchType__VarAssignment_2
            {
            pushFollow(FOLLOW_rule__SwitchType__VarAssignment_2_in_rule__SwitchType__Group__2__Impl9705);
            rule__SwitchType__VarAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getSwitchTypeAccess().getVarAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SwitchType__Group__2__Impl"


    // $ANTLR start "rule__SwitchType__Group__3"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4826:1: rule__SwitchType__Group__3 : rule__SwitchType__Group__3__Impl rule__SwitchType__Group__4 ;
    public final void rule__SwitchType__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4830:1: ( rule__SwitchType__Group__3__Impl rule__SwitchType__Group__4 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4831:2: rule__SwitchType__Group__3__Impl rule__SwitchType__Group__4
            {
            pushFollow(FOLLOW_rule__SwitchType__Group__3__Impl_in_rule__SwitchType__Group__39735);
            rule__SwitchType__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__SwitchType__Group__4_in_rule__SwitchType__Group__39738);
            rule__SwitchType__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SwitchType__Group__3"


    // $ANTLR start "rule__SwitchType__Group__3__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4838:1: rule__SwitchType__Group__3__Impl : ( ')' ) ;
    public final void rule__SwitchType__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4842:1: ( ( ')' ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4843:1: ( ')' )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4843:1: ( ')' )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4844:1: ')'
            {
             before(grammarAccess.getSwitchTypeAccess().getRightParenthesisKeyword_3()); 
            match(input,25,FOLLOW_25_in_rule__SwitchType__Group__3__Impl9766); 
             after(grammarAccess.getSwitchTypeAccess().getRightParenthesisKeyword_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SwitchType__Group__3__Impl"


    // $ANTLR start "rule__SwitchType__Group__4"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4857:1: rule__SwitchType__Group__4 : rule__SwitchType__Group__4__Impl rule__SwitchType__Group__5 ;
    public final void rule__SwitchType__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4861:1: ( rule__SwitchType__Group__4__Impl rule__SwitchType__Group__5 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4862:2: rule__SwitchType__Group__4__Impl rule__SwitchType__Group__5
            {
            pushFollow(FOLLOW_rule__SwitchType__Group__4__Impl_in_rule__SwitchType__Group__49797);
            rule__SwitchType__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__SwitchType__Group__5_in_rule__SwitchType__Group__49800);
            rule__SwitchType__Group__5();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SwitchType__Group__4"


    // $ANTLR start "rule__SwitchType__Group__4__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4869:1: rule__SwitchType__Group__4__Impl : ( 'do' ) ;
    public final void rule__SwitchType__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4873:1: ( ( 'do' ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4874:1: ( 'do' )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4874:1: ( 'do' )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4875:1: 'do'
            {
             before(grammarAccess.getSwitchTypeAccess().getDoKeyword_4()); 
            match(input,38,FOLLOW_38_in_rule__SwitchType__Group__4__Impl9828); 
             after(grammarAccess.getSwitchTypeAccess().getDoKeyword_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SwitchType__Group__4__Impl"


    // $ANTLR start "rule__SwitchType__Group__5"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4888:1: rule__SwitchType__Group__5 : rule__SwitchType__Group__5__Impl rule__SwitchType__Group__6 ;
    public final void rule__SwitchType__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4892:1: ( rule__SwitchType__Group__5__Impl rule__SwitchType__Group__6 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4893:2: rule__SwitchType__Group__5__Impl rule__SwitchType__Group__6
            {
            pushFollow(FOLLOW_rule__SwitchType__Group__5__Impl_in_rule__SwitchType__Group__59859);
            rule__SwitchType__Group__5__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__SwitchType__Group__6_in_rule__SwitchType__Group__59862);
            rule__SwitchType__Group__6();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SwitchType__Group__5"


    // $ANTLR start "rule__SwitchType__Group__5__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4900:1: rule__SwitchType__Group__5__Impl : ( '{' ) ;
    public final void rule__SwitchType__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4904:1: ( ( '{' ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4905:1: ( '{' )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4905:1: ( '{' )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4906:1: '{'
            {
             before(grammarAccess.getSwitchTypeAccess().getLeftCurlyBracketKeyword_5()); 
            match(input,32,FOLLOW_32_in_rule__SwitchType__Group__5__Impl9890); 
             after(grammarAccess.getSwitchTypeAccess().getLeftCurlyBracketKeyword_5()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SwitchType__Group__5__Impl"


    // $ANTLR start "rule__SwitchType__Group__6"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4919:1: rule__SwitchType__Group__6 : rule__SwitchType__Group__6__Impl rule__SwitchType__Group__7 ;
    public final void rule__SwitchType__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4923:1: ( rule__SwitchType__Group__6__Impl rule__SwitchType__Group__7 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4924:2: rule__SwitchType__Group__6__Impl rule__SwitchType__Group__7
            {
            pushFollow(FOLLOW_rule__SwitchType__Group__6__Impl_in_rule__SwitchType__Group__69921);
            rule__SwitchType__Group__6__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__SwitchType__Group__7_in_rule__SwitchType__Group__69924);
            rule__SwitchType__Group__7();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SwitchType__Group__6"


    // $ANTLR start "rule__SwitchType__Group__6__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4931:1: rule__SwitchType__Group__6__Impl : ( ( rule__SwitchType__Group_6__0 )* ) ;
    public final void rule__SwitchType__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4935:1: ( ( ( rule__SwitchType__Group_6__0 )* ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4936:1: ( ( rule__SwitchType__Group_6__0 )* )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4936:1: ( ( rule__SwitchType__Group_6__0 )* )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4937:1: ( rule__SwitchType__Group_6__0 )*
            {
             before(grammarAccess.getSwitchTypeAccess().getGroup_6()); 
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4938:1: ( rule__SwitchType__Group_6__0 )*
            loop41:
            do {
                int alt41=2;
                int LA41_0 = input.LA(1);

                if ( (LA41_0==45) ) {
                    alt41=1;
                }


                switch (alt41) {
            	case 1 :
            	    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4938:2: rule__SwitchType__Group_6__0
            	    {
            	    pushFollow(FOLLOW_rule__SwitchType__Group_6__0_in_rule__SwitchType__Group__6__Impl9951);
            	    rule__SwitchType__Group_6__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop41;
                }
            } while (true);

             after(grammarAccess.getSwitchTypeAccess().getGroup_6()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SwitchType__Group__6__Impl"


    // $ANTLR start "rule__SwitchType__Group__7"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4948:1: rule__SwitchType__Group__7 : rule__SwitchType__Group__7__Impl rule__SwitchType__Group__8 ;
    public final void rule__SwitchType__Group__7() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4952:1: ( rule__SwitchType__Group__7__Impl rule__SwitchType__Group__8 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4953:2: rule__SwitchType__Group__7__Impl rule__SwitchType__Group__8
            {
            pushFollow(FOLLOW_rule__SwitchType__Group__7__Impl_in_rule__SwitchType__Group__79982);
            rule__SwitchType__Group__7__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__SwitchType__Group__8_in_rule__SwitchType__Group__79985);
            rule__SwitchType__Group__8();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SwitchType__Group__7"


    // $ANTLR start "rule__SwitchType__Group__7__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4960:1: rule__SwitchType__Group__7__Impl : ( ( rule__SwitchType__Group_7__0 )? ) ;
    public final void rule__SwitchType__Group__7__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4964:1: ( ( ( rule__SwitchType__Group_7__0 )? ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4965:1: ( ( rule__SwitchType__Group_7__0 )? )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4965:1: ( ( rule__SwitchType__Group_7__0 )? )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4966:1: ( rule__SwitchType__Group_7__0 )?
            {
             before(grammarAccess.getSwitchTypeAccess().getGroup_7()); 
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4967:1: ( rule__SwitchType__Group_7__0 )?
            int alt42=2;
            int LA42_0 = input.LA(1);

            if ( (LA42_0==47) ) {
                alt42=1;
            }
            switch (alt42) {
                case 1 :
                    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4967:2: rule__SwitchType__Group_7__0
                    {
                    pushFollow(FOLLOW_rule__SwitchType__Group_7__0_in_rule__SwitchType__Group__7__Impl10012);
                    rule__SwitchType__Group_7__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getSwitchTypeAccess().getGroup_7()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SwitchType__Group__7__Impl"


    // $ANTLR start "rule__SwitchType__Group__8"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4977:1: rule__SwitchType__Group__8 : rule__SwitchType__Group__8__Impl ;
    public final void rule__SwitchType__Group__8() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4981:1: ( rule__SwitchType__Group__8__Impl )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4982:2: rule__SwitchType__Group__8__Impl
            {
            pushFollow(FOLLOW_rule__SwitchType__Group__8__Impl_in_rule__SwitchType__Group__810043);
            rule__SwitchType__Group__8__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SwitchType__Group__8"


    // $ANTLR start "rule__SwitchType__Group__8__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4988:1: rule__SwitchType__Group__8__Impl : ( '}' ) ;
    public final void rule__SwitchType__Group__8__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4992:1: ( ( '}' ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4993:1: ( '}' )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4993:1: ( '}' )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:4994:1: '}'
            {
             before(grammarAccess.getSwitchTypeAccess().getRightCurlyBracketKeyword_8()); 
            match(input,33,FOLLOW_33_in_rule__SwitchType__Group__8__Impl10071); 
             after(grammarAccess.getSwitchTypeAccess().getRightCurlyBracketKeyword_8()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SwitchType__Group__8__Impl"


    // $ANTLR start "rule__SwitchType__Group_6__0"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5025:1: rule__SwitchType__Group_6__0 : rule__SwitchType__Group_6__0__Impl rule__SwitchType__Group_6__1 ;
    public final void rule__SwitchType__Group_6__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5029:1: ( rule__SwitchType__Group_6__0__Impl rule__SwitchType__Group_6__1 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5030:2: rule__SwitchType__Group_6__0__Impl rule__SwitchType__Group_6__1
            {
            pushFollow(FOLLOW_rule__SwitchType__Group_6__0__Impl_in_rule__SwitchType__Group_6__010120);
            rule__SwitchType__Group_6__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__SwitchType__Group_6__1_in_rule__SwitchType__Group_6__010123);
            rule__SwitchType__Group_6__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SwitchType__Group_6__0"


    // $ANTLR start "rule__SwitchType__Group_6__0__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5037:1: rule__SwitchType__Group_6__0__Impl : ( 'case' ) ;
    public final void rule__SwitchType__Group_6__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5041:1: ( ( 'case' ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5042:1: ( 'case' )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5042:1: ( 'case' )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5043:1: 'case'
            {
             before(grammarAccess.getSwitchTypeAccess().getCaseKeyword_6_0()); 
            match(input,45,FOLLOW_45_in_rule__SwitchType__Group_6__0__Impl10151); 
             after(grammarAccess.getSwitchTypeAccess().getCaseKeyword_6_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SwitchType__Group_6__0__Impl"


    // $ANTLR start "rule__SwitchType__Group_6__1"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5056:1: rule__SwitchType__Group_6__1 : rule__SwitchType__Group_6__1__Impl rule__SwitchType__Group_6__2 ;
    public final void rule__SwitchType__Group_6__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5060:1: ( rule__SwitchType__Group_6__1__Impl rule__SwitchType__Group_6__2 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5061:2: rule__SwitchType__Group_6__1__Impl rule__SwitchType__Group_6__2
            {
            pushFollow(FOLLOW_rule__SwitchType__Group_6__1__Impl_in_rule__SwitchType__Group_6__110182);
            rule__SwitchType__Group_6__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__SwitchType__Group_6__2_in_rule__SwitchType__Group_6__110185);
            rule__SwitchType__Group_6__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SwitchType__Group_6__1"


    // $ANTLR start "rule__SwitchType__Group_6__1__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5068:1: rule__SwitchType__Group_6__1__Impl : ( ( rule__SwitchType__ValueAssignment_6_1 ) ) ;
    public final void rule__SwitchType__Group_6__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5072:1: ( ( ( rule__SwitchType__ValueAssignment_6_1 ) ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5073:1: ( ( rule__SwitchType__ValueAssignment_6_1 ) )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5073:1: ( ( rule__SwitchType__ValueAssignment_6_1 ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5074:1: ( rule__SwitchType__ValueAssignment_6_1 )
            {
             before(grammarAccess.getSwitchTypeAccess().getValueAssignment_6_1()); 
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5075:1: ( rule__SwitchType__ValueAssignment_6_1 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5075:2: rule__SwitchType__ValueAssignment_6_1
            {
            pushFollow(FOLLOW_rule__SwitchType__ValueAssignment_6_1_in_rule__SwitchType__Group_6__1__Impl10212);
            rule__SwitchType__ValueAssignment_6_1();

            state._fsp--;


            }

             after(grammarAccess.getSwitchTypeAccess().getValueAssignment_6_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SwitchType__Group_6__1__Impl"


    // $ANTLR start "rule__SwitchType__Group_6__2"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5085:1: rule__SwitchType__Group_6__2 : rule__SwitchType__Group_6__2__Impl rule__SwitchType__Group_6__3 ;
    public final void rule__SwitchType__Group_6__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5089:1: ( rule__SwitchType__Group_6__2__Impl rule__SwitchType__Group_6__3 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5090:2: rule__SwitchType__Group_6__2__Impl rule__SwitchType__Group_6__3
            {
            pushFollow(FOLLOW_rule__SwitchType__Group_6__2__Impl_in_rule__SwitchType__Group_6__210242);
            rule__SwitchType__Group_6__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__SwitchType__Group_6__3_in_rule__SwitchType__Group_6__210245);
            rule__SwitchType__Group_6__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SwitchType__Group_6__2"


    // $ANTLR start "rule__SwitchType__Group_6__2__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5097:1: rule__SwitchType__Group_6__2__Impl : ( ':' ) ;
    public final void rule__SwitchType__Group_6__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5101:1: ( ( ':' ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5102:1: ( ':' )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5102:1: ( ':' )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5103:1: ':'
            {
             before(grammarAccess.getSwitchTypeAccess().getColonKeyword_6_2()); 
            match(input,46,FOLLOW_46_in_rule__SwitchType__Group_6__2__Impl10273); 
             after(grammarAccess.getSwitchTypeAccess().getColonKeyword_6_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SwitchType__Group_6__2__Impl"


    // $ANTLR start "rule__SwitchType__Group_6__3"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5116:1: rule__SwitchType__Group_6__3 : rule__SwitchType__Group_6__3__Impl rule__SwitchType__Group_6__4 ;
    public final void rule__SwitchType__Group_6__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5120:1: ( rule__SwitchType__Group_6__3__Impl rule__SwitchType__Group_6__4 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5121:2: rule__SwitchType__Group_6__3__Impl rule__SwitchType__Group_6__4
            {
            pushFollow(FOLLOW_rule__SwitchType__Group_6__3__Impl_in_rule__SwitchType__Group_6__310304);
            rule__SwitchType__Group_6__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__SwitchType__Group_6__4_in_rule__SwitchType__Group_6__310307);
            rule__SwitchType__Group_6__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SwitchType__Group_6__3"


    // $ANTLR start "rule__SwitchType__Group_6__3__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5128:1: rule__SwitchType__Group_6__3__Impl : ( '{' ) ;
    public final void rule__SwitchType__Group_6__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5132:1: ( ( '{' ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5133:1: ( '{' )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5133:1: ( '{' )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5134:1: '{'
            {
             before(grammarAccess.getSwitchTypeAccess().getLeftCurlyBracketKeyword_6_3()); 
            match(input,32,FOLLOW_32_in_rule__SwitchType__Group_6__3__Impl10335); 
             after(grammarAccess.getSwitchTypeAccess().getLeftCurlyBracketKeyword_6_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SwitchType__Group_6__3__Impl"


    // $ANTLR start "rule__SwitchType__Group_6__4"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5147:1: rule__SwitchType__Group_6__4 : rule__SwitchType__Group_6__4__Impl rule__SwitchType__Group_6__5 ;
    public final void rule__SwitchType__Group_6__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5151:1: ( rule__SwitchType__Group_6__4__Impl rule__SwitchType__Group_6__5 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5152:2: rule__SwitchType__Group_6__4__Impl rule__SwitchType__Group_6__5
            {
            pushFollow(FOLLOW_rule__SwitchType__Group_6__4__Impl_in_rule__SwitchType__Group_6__410366);
            rule__SwitchType__Group_6__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__SwitchType__Group_6__5_in_rule__SwitchType__Group_6__410369);
            rule__SwitchType__Group_6__5();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SwitchType__Group_6__4"


    // $ANTLR start "rule__SwitchType__Group_6__4__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5159:1: rule__SwitchType__Group_6__4__Impl : ( ( rule__SwitchType__CaseCodeAssignment_6_4 )* ) ;
    public final void rule__SwitchType__Group_6__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5163:1: ( ( ( rule__SwitchType__CaseCodeAssignment_6_4 )* ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5164:1: ( ( rule__SwitchType__CaseCodeAssignment_6_4 )* )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5164:1: ( ( rule__SwitchType__CaseCodeAssignment_6_4 )* )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5165:1: ( rule__SwitchType__CaseCodeAssignment_6_4 )*
            {
             before(grammarAccess.getSwitchTypeAccess().getCaseCodeAssignment_6_4()); 
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5166:1: ( rule__SwitchType__CaseCodeAssignment_6_4 )*
            loop43:
            do {
                int alt43=2;
                int LA43_0 = input.LA(1);

                if ( (LA43_0==RULE_ID||LA43_0==29||LA43_0==32||LA43_0==35||LA43_0==37||LA43_0==39||LA43_0==44) ) {
                    alt43=1;
                }


                switch (alt43) {
            	case 1 :
            	    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5166:2: rule__SwitchType__CaseCodeAssignment_6_4
            	    {
            	    pushFollow(FOLLOW_rule__SwitchType__CaseCodeAssignment_6_4_in_rule__SwitchType__Group_6__4__Impl10396);
            	    rule__SwitchType__CaseCodeAssignment_6_4();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop43;
                }
            } while (true);

             after(grammarAccess.getSwitchTypeAccess().getCaseCodeAssignment_6_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SwitchType__Group_6__4__Impl"


    // $ANTLR start "rule__SwitchType__Group_6__5"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5176:1: rule__SwitchType__Group_6__5 : rule__SwitchType__Group_6__5__Impl rule__SwitchType__Group_6__6 ;
    public final void rule__SwitchType__Group_6__5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5180:1: ( rule__SwitchType__Group_6__5__Impl rule__SwitchType__Group_6__6 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5181:2: rule__SwitchType__Group_6__5__Impl rule__SwitchType__Group_6__6
            {
            pushFollow(FOLLOW_rule__SwitchType__Group_6__5__Impl_in_rule__SwitchType__Group_6__510427);
            rule__SwitchType__Group_6__5__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__SwitchType__Group_6__6_in_rule__SwitchType__Group_6__510430);
            rule__SwitchType__Group_6__6();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SwitchType__Group_6__5"


    // $ANTLR start "rule__SwitchType__Group_6__5__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5188:1: rule__SwitchType__Group_6__5__Impl : ( '}' ) ;
    public final void rule__SwitchType__Group_6__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5192:1: ( ( '}' ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5193:1: ( '}' )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5193:1: ( '}' )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5194:1: '}'
            {
             before(grammarAccess.getSwitchTypeAccess().getRightCurlyBracketKeyword_6_5()); 
            match(input,33,FOLLOW_33_in_rule__SwitchType__Group_6__5__Impl10458); 
             after(grammarAccess.getSwitchTypeAccess().getRightCurlyBracketKeyword_6_5()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SwitchType__Group_6__5__Impl"


    // $ANTLR start "rule__SwitchType__Group_6__6"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5207:1: rule__SwitchType__Group_6__6 : rule__SwitchType__Group_6__6__Impl ;
    public final void rule__SwitchType__Group_6__6() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5211:1: ( rule__SwitchType__Group_6__6__Impl )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5212:2: rule__SwitchType__Group_6__6__Impl
            {
            pushFollow(FOLLOW_rule__SwitchType__Group_6__6__Impl_in_rule__SwitchType__Group_6__610489);
            rule__SwitchType__Group_6__6__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SwitchType__Group_6__6"


    // $ANTLR start "rule__SwitchType__Group_6__6__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5218:1: rule__SwitchType__Group_6__6__Impl : ( ';' ) ;
    public final void rule__SwitchType__Group_6__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5222:1: ( ( ';' ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5223:1: ( ';' )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5223:1: ( ';' )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5224:1: ';'
            {
             before(grammarAccess.getSwitchTypeAccess().getSemicolonKeyword_6_6()); 
            match(input,23,FOLLOW_23_in_rule__SwitchType__Group_6__6__Impl10517); 
             after(grammarAccess.getSwitchTypeAccess().getSemicolonKeyword_6_6()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SwitchType__Group_6__6__Impl"


    // $ANTLR start "rule__SwitchType__Group_7__0"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5251:1: rule__SwitchType__Group_7__0 : rule__SwitchType__Group_7__0__Impl rule__SwitchType__Group_7__1 ;
    public final void rule__SwitchType__Group_7__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5255:1: ( rule__SwitchType__Group_7__0__Impl rule__SwitchType__Group_7__1 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5256:2: rule__SwitchType__Group_7__0__Impl rule__SwitchType__Group_7__1
            {
            pushFollow(FOLLOW_rule__SwitchType__Group_7__0__Impl_in_rule__SwitchType__Group_7__010562);
            rule__SwitchType__Group_7__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__SwitchType__Group_7__1_in_rule__SwitchType__Group_7__010565);
            rule__SwitchType__Group_7__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SwitchType__Group_7__0"


    // $ANTLR start "rule__SwitchType__Group_7__0__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5263:1: rule__SwitchType__Group_7__0__Impl : ( 'default' ) ;
    public final void rule__SwitchType__Group_7__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5267:1: ( ( 'default' ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5268:1: ( 'default' )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5268:1: ( 'default' )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5269:1: 'default'
            {
             before(grammarAccess.getSwitchTypeAccess().getDefaultKeyword_7_0()); 
            match(input,47,FOLLOW_47_in_rule__SwitchType__Group_7__0__Impl10593); 
             after(grammarAccess.getSwitchTypeAccess().getDefaultKeyword_7_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SwitchType__Group_7__0__Impl"


    // $ANTLR start "rule__SwitchType__Group_7__1"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5282:1: rule__SwitchType__Group_7__1 : rule__SwitchType__Group_7__1__Impl rule__SwitchType__Group_7__2 ;
    public final void rule__SwitchType__Group_7__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5286:1: ( rule__SwitchType__Group_7__1__Impl rule__SwitchType__Group_7__2 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5287:2: rule__SwitchType__Group_7__1__Impl rule__SwitchType__Group_7__2
            {
            pushFollow(FOLLOW_rule__SwitchType__Group_7__1__Impl_in_rule__SwitchType__Group_7__110624);
            rule__SwitchType__Group_7__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__SwitchType__Group_7__2_in_rule__SwitchType__Group_7__110627);
            rule__SwitchType__Group_7__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SwitchType__Group_7__1"


    // $ANTLR start "rule__SwitchType__Group_7__1__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5294:1: rule__SwitchType__Group_7__1__Impl : ( '{' ) ;
    public final void rule__SwitchType__Group_7__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5298:1: ( ( '{' ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5299:1: ( '{' )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5299:1: ( '{' )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5300:1: '{'
            {
             before(grammarAccess.getSwitchTypeAccess().getLeftCurlyBracketKeyword_7_1()); 
            match(input,32,FOLLOW_32_in_rule__SwitchType__Group_7__1__Impl10655); 
             after(grammarAccess.getSwitchTypeAccess().getLeftCurlyBracketKeyword_7_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SwitchType__Group_7__1__Impl"


    // $ANTLR start "rule__SwitchType__Group_7__2"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5313:1: rule__SwitchType__Group_7__2 : rule__SwitchType__Group_7__2__Impl rule__SwitchType__Group_7__3 ;
    public final void rule__SwitchType__Group_7__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5317:1: ( rule__SwitchType__Group_7__2__Impl rule__SwitchType__Group_7__3 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5318:2: rule__SwitchType__Group_7__2__Impl rule__SwitchType__Group_7__3
            {
            pushFollow(FOLLOW_rule__SwitchType__Group_7__2__Impl_in_rule__SwitchType__Group_7__210686);
            rule__SwitchType__Group_7__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__SwitchType__Group_7__3_in_rule__SwitchType__Group_7__210689);
            rule__SwitchType__Group_7__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SwitchType__Group_7__2"


    // $ANTLR start "rule__SwitchType__Group_7__2__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5325:1: rule__SwitchType__Group_7__2__Impl : ( ( rule__SwitchType__DefaultCodeAssignment_7_2 )* ) ;
    public final void rule__SwitchType__Group_7__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5329:1: ( ( ( rule__SwitchType__DefaultCodeAssignment_7_2 )* ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5330:1: ( ( rule__SwitchType__DefaultCodeAssignment_7_2 )* )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5330:1: ( ( rule__SwitchType__DefaultCodeAssignment_7_2 )* )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5331:1: ( rule__SwitchType__DefaultCodeAssignment_7_2 )*
            {
             before(grammarAccess.getSwitchTypeAccess().getDefaultCodeAssignment_7_2()); 
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5332:1: ( rule__SwitchType__DefaultCodeAssignment_7_2 )*
            loop44:
            do {
                int alt44=2;
                int LA44_0 = input.LA(1);

                if ( (LA44_0==RULE_ID||LA44_0==29||LA44_0==32||LA44_0==35||LA44_0==37||LA44_0==39||LA44_0==44) ) {
                    alt44=1;
                }


                switch (alt44) {
            	case 1 :
            	    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5332:2: rule__SwitchType__DefaultCodeAssignment_7_2
            	    {
            	    pushFollow(FOLLOW_rule__SwitchType__DefaultCodeAssignment_7_2_in_rule__SwitchType__Group_7__2__Impl10716);
            	    rule__SwitchType__DefaultCodeAssignment_7_2();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop44;
                }
            } while (true);

             after(grammarAccess.getSwitchTypeAccess().getDefaultCodeAssignment_7_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SwitchType__Group_7__2__Impl"


    // $ANTLR start "rule__SwitchType__Group_7__3"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5342:1: rule__SwitchType__Group_7__3 : rule__SwitchType__Group_7__3__Impl rule__SwitchType__Group_7__4 ;
    public final void rule__SwitchType__Group_7__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5346:1: ( rule__SwitchType__Group_7__3__Impl rule__SwitchType__Group_7__4 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5347:2: rule__SwitchType__Group_7__3__Impl rule__SwitchType__Group_7__4
            {
            pushFollow(FOLLOW_rule__SwitchType__Group_7__3__Impl_in_rule__SwitchType__Group_7__310747);
            rule__SwitchType__Group_7__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__SwitchType__Group_7__4_in_rule__SwitchType__Group_7__310750);
            rule__SwitchType__Group_7__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SwitchType__Group_7__3"


    // $ANTLR start "rule__SwitchType__Group_7__3__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5354:1: rule__SwitchType__Group_7__3__Impl : ( '}' ) ;
    public final void rule__SwitchType__Group_7__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5358:1: ( ( '}' ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5359:1: ( '}' )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5359:1: ( '}' )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5360:1: '}'
            {
             before(grammarAccess.getSwitchTypeAccess().getRightCurlyBracketKeyword_7_3()); 
            match(input,33,FOLLOW_33_in_rule__SwitchType__Group_7__3__Impl10778); 
             after(grammarAccess.getSwitchTypeAccess().getRightCurlyBracketKeyword_7_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SwitchType__Group_7__3__Impl"


    // $ANTLR start "rule__SwitchType__Group_7__4"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5373:1: rule__SwitchType__Group_7__4 : rule__SwitchType__Group_7__4__Impl ;
    public final void rule__SwitchType__Group_7__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5377:1: ( rule__SwitchType__Group_7__4__Impl )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5378:2: rule__SwitchType__Group_7__4__Impl
            {
            pushFollow(FOLLOW_rule__SwitchType__Group_7__4__Impl_in_rule__SwitchType__Group_7__410809);
            rule__SwitchType__Group_7__4__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SwitchType__Group_7__4"


    // $ANTLR start "rule__SwitchType__Group_7__4__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5384:1: rule__SwitchType__Group_7__4__Impl : ( ';' ) ;
    public final void rule__SwitchType__Group_7__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5388:1: ( ( ';' ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5389:1: ( ';' )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5389:1: ( ';' )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5390:1: ';'
            {
             before(grammarAccess.getSwitchTypeAccess().getSemicolonKeyword_7_4()); 
            match(input,23,FOLLOW_23_in_rule__SwitchType__Group_7__4__Impl10837); 
             after(grammarAccess.getSwitchTypeAccess().getSemicolonKeyword_7_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SwitchType__Group_7__4__Impl"


    // $ANTLR start "rule__Method__Group__0"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5413:1: rule__Method__Group__0 : rule__Method__Group__0__Impl rule__Method__Group__1 ;
    public final void rule__Method__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5417:1: ( rule__Method__Group__0__Impl rule__Method__Group__1 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5418:2: rule__Method__Group__0__Impl rule__Method__Group__1
            {
            pushFollow(FOLLOW_rule__Method__Group__0__Impl_in_rule__Method__Group__010878);
            rule__Method__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Method__Group__1_in_rule__Method__Group__010881);
            rule__Method__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Method__Group__0"


    // $ANTLR start "rule__Method__Group__0__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5425:1: rule__Method__Group__0__Impl : ( ( rule__Method__ParamsAssignment_0 ) ) ;
    public final void rule__Method__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5429:1: ( ( ( rule__Method__ParamsAssignment_0 ) ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5430:1: ( ( rule__Method__ParamsAssignment_0 ) )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5430:1: ( ( rule__Method__ParamsAssignment_0 ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5431:1: ( rule__Method__ParamsAssignment_0 )
            {
             before(grammarAccess.getMethodAccess().getParamsAssignment_0()); 
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5432:1: ( rule__Method__ParamsAssignment_0 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5432:2: rule__Method__ParamsAssignment_0
            {
            pushFollow(FOLLOW_rule__Method__ParamsAssignment_0_in_rule__Method__Group__0__Impl10908);
            rule__Method__ParamsAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getMethodAccess().getParamsAssignment_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Method__Group__0__Impl"


    // $ANTLR start "rule__Method__Group__1"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5442:1: rule__Method__Group__1 : rule__Method__Group__1__Impl rule__Method__Group__2 ;
    public final void rule__Method__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5446:1: ( rule__Method__Group__1__Impl rule__Method__Group__2 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5447:2: rule__Method__Group__1__Impl rule__Method__Group__2
            {
            pushFollow(FOLLOW_rule__Method__Group__1__Impl_in_rule__Method__Group__110938);
            rule__Method__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Method__Group__2_in_rule__Method__Group__110941);
            rule__Method__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Method__Group__1"


    // $ANTLR start "rule__Method__Group__1__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5454:1: rule__Method__Group__1__Impl : ( ( rule__Method__ExecuteAssignment_1 ) ) ;
    public final void rule__Method__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5458:1: ( ( ( rule__Method__ExecuteAssignment_1 ) ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5459:1: ( ( rule__Method__ExecuteAssignment_1 ) )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5459:1: ( ( rule__Method__ExecuteAssignment_1 ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5460:1: ( rule__Method__ExecuteAssignment_1 )
            {
             before(grammarAccess.getMethodAccess().getExecuteAssignment_1()); 
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5461:1: ( rule__Method__ExecuteAssignment_1 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5461:2: rule__Method__ExecuteAssignment_1
            {
            pushFollow(FOLLOW_rule__Method__ExecuteAssignment_1_in_rule__Method__Group__1__Impl10968);
            rule__Method__ExecuteAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getMethodAccess().getExecuteAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Method__Group__1__Impl"


    // $ANTLR start "rule__Method__Group__2"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5471:1: rule__Method__Group__2 : rule__Method__Group__2__Impl ;
    public final void rule__Method__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5475:1: ( rule__Method__Group__2__Impl )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5476:2: rule__Method__Group__2__Impl
            {
            pushFollow(FOLLOW_rule__Method__Group__2__Impl_in_rule__Method__Group__210998);
            rule__Method__Group__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Method__Group__2"


    // $ANTLR start "rule__Method__Group__2__Impl"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5482:1: rule__Method__Group__2__Impl : ( ( rule__Method__MethodNameAssignment_2 ) ) ;
    public final void rule__Method__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5486:1: ( ( ( rule__Method__MethodNameAssignment_2 ) ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5487:1: ( ( rule__Method__MethodNameAssignment_2 ) )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5487:1: ( ( rule__Method__MethodNameAssignment_2 ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5488:1: ( rule__Method__MethodNameAssignment_2 )
            {
             before(grammarAccess.getMethodAccess().getMethodNameAssignment_2()); 
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5489:1: ( rule__Method__MethodNameAssignment_2 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5489:2: rule__Method__MethodNameAssignment_2
            {
            pushFollow(FOLLOW_rule__Method__MethodNameAssignment_2_in_rule__Method__Group__2__Impl11025);
            rule__Method__MethodNameAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getMethodAccess().getMethodNameAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Method__Group__2__Impl"


    // $ANTLR start "rule__Model__ElementsAssignment"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5506:1: rule__Model__ElementsAssignment : ( ruleCode ) ;
    public final void rule__Model__ElementsAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5510:1: ( ( ruleCode ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5511:1: ( ruleCode )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5511:1: ( ruleCode )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5512:1: ruleCode
            {
             before(grammarAccess.getModelAccess().getElementsCodeParserRuleCall_0()); 
            pushFollow(FOLLOW_ruleCode_in_rule__Model__ElementsAssignment11066);
            ruleCode();

            state._fsp--;

             after(grammarAccess.getModelAccess().getElementsCodeParserRuleCall_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Model__ElementsAssignment"


    // $ANTLR start "rule__Code__DecAssignment_0_0"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5521:1: rule__Code__DecAssignment_0_0 : ( ruleDeclaration ) ;
    public final void rule__Code__DecAssignment_0_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5525:1: ( ( ruleDeclaration ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5526:1: ( ruleDeclaration )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5526:1: ( ruleDeclaration )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5527:1: ruleDeclaration
            {
             before(grammarAccess.getCodeAccess().getDecDeclarationParserRuleCall_0_0_0()); 
            pushFollow(FOLLOW_ruleDeclaration_in_rule__Code__DecAssignment_0_011097);
            ruleDeclaration();

            state._fsp--;

             after(grammarAccess.getCodeAccess().getDecDeclarationParserRuleCall_0_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Code__DecAssignment_0_0"


    // $ANTLR start "rule__Code__ControlAssignment_1_0"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5536:1: rule__Code__ControlAssignment_1_0 : ( ruleControlStructure ) ;
    public final void rule__Code__ControlAssignment_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5540:1: ( ( ruleControlStructure ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5541:1: ( ruleControlStructure )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5541:1: ( ruleControlStructure )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5542:1: ruleControlStructure
            {
             before(grammarAccess.getCodeAccess().getControlControlStructureParserRuleCall_1_0_0()); 
            pushFollow(FOLLOW_ruleControlStructure_in_rule__Code__ControlAssignment_1_011128);
            ruleControlStructure();

            state._fsp--;

             after(grammarAccess.getCodeAccess().getControlControlStructureParserRuleCall_1_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Code__ControlAssignment_1_0"


    // $ANTLR start "rule__Code__MethodAssignment_2_0"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5551:1: rule__Code__MethodAssignment_2_0 : ( ruleMethod ) ;
    public final void rule__Code__MethodAssignment_2_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5555:1: ( ( ruleMethod ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5556:1: ( ruleMethod )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5556:1: ( ruleMethod )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5557:1: ruleMethod
            {
             before(grammarAccess.getCodeAccess().getMethodMethodParserRuleCall_2_0_0()); 
            pushFollow(FOLLOW_ruleMethod_in_rule__Code__MethodAssignment_2_011159);
            ruleMethod();

            state._fsp--;

             after(grammarAccess.getCodeAccess().getMethodMethodParserRuleCall_2_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Code__MethodAssignment_2_0"


    // $ANTLR start "rule__Declaration__NameAssignment_0"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5566:1: rule__Declaration__NameAssignment_0 : ( RULE_ID ) ;
    public final void rule__Declaration__NameAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5570:1: ( ( RULE_ID ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5571:1: ( RULE_ID )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5571:1: ( RULE_ID )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5572:1: RULE_ID
            {
             before(grammarAccess.getDeclarationAccess().getNameIDTerminalRuleCall_0_0()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__Declaration__NameAssignment_011190); 
             after(grammarAccess.getDeclarationAccess().getNameIDTerminalRuleCall_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Declaration__NameAssignment_0"


    // $ANTLR start "rule__Declaration__BrConAssignment_2"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5581:1: rule__Declaration__BrConAssignment_2 : ( ruleBracketContent ) ;
    public final void rule__Declaration__BrConAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5585:1: ( ( ruleBracketContent ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5586:1: ( ruleBracketContent )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5586:1: ( ruleBracketContent )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5587:1: ruleBracketContent
            {
             before(grammarAccess.getDeclarationAccess().getBrConBracketContentParserRuleCall_2_0()); 
            pushFollow(FOLLOW_ruleBracketContent_in_rule__Declaration__BrConAssignment_211221);
            ruleBracketContent();

            state._fsp--;

             after(grammarAccess.getDeclarationAccess().getBrConBracketContentParserRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Declaration__BrConAssignment_2"


    // $ANTLR start "rule__BracketContent__DecConAssignment_0"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5596:1: rule__BracketContent__DecConAssignment_0 : ( ruleDecContent ) ;
    public final void rule__BracketContent__DecConAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5600:1: ( ( ruleDecContent ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5601:1: ( ruleDecContent )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5601:1: ( ruleDecContent )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5602:1: ruleDecContent
            {
             before(grammarAccess.getBracketContentAccess().getDecConDecContentParserRuleCall_0_0()); 
            pushFollow(FOLLOW_ruleDecContent_in_rule__BracketContent__DecConAssignment_011252);
            ruleDecContent();

            state._fsp--;

             after(grammarAccess.getBracketContentAccess().getDecConDecContentParserRuleCall_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__BracketContent__DecConAssignment_0"


    // $ANTLR start "rule__BracketContent__CompAssignment_1_0"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5611:1: rule__BracketContent__CompAssignment_1_0 : ( RULE_COMPARATOR ) ;
    public final void rule__BracketContent__CompAssignment_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5615:1: ( ( RULE_COMPARATOR ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5616:1: ( RULE_COMPARATOR )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5616:1: ( RULE_COMPARATOR )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5617:1: RULE_COMPARATOR
            {
             before(grammarAccess.getBracketContentAccess().getCompCOMPARATORTerminalRuleCall_1_0_0()); 
            match(input,RULE_COMPARATOR,FOLLOW_RULE_COMPARATOR_in_rule__BracketContent__CompAssignment_1_011283); 
             after(grammarAccess.getBracketContentAccess().getCompCOMPARATORTerminalRuleCall_1_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__BracketContent__CompAssignment_1_0"


    // $ANTLR start "rule__BracketContent__ContentAssignment_1_1"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5626:1: rule__BracketContent__ContentAssignment_1_1 : ( ruleDecContent ) ;
    public final void rule__BracketContent__ContentAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5630:1: ( ( ruleDecContent ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5631:1: ( ruleDecContent )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5631:1: ( ruleDecContent )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5632:1: ruleDecContent
            {
             before(grammarAccess.getBracketContentAccess().getContentDecContentParserRuleCall_1_1_0()); 
            pushFollow(FOLLOW_ruleDecContent_in_rule__BracketContent__ContentAssignment_1_111314);
            ruleDecContent();

            state._fsp--;

             after(grammarAccess.getBracketContentAccess().getContentDecContentParserRuleCall_1_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__BracketContent__ContentAssignment_1_1"


    // $ANTLR start "rule__DecContent__NegAssignment_0"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5641:1: rule__DecContent__NegAssignment_0 : ( ( rule__DecContent__NegAlternatives_0_0 ) ) ;
    public final void rule__DecContent__NegAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5645:1: ( ( ( rule__DecContent__NegAlternatives_0_0 ) ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5646:1: ( ( rule__DecContent__NegAlternatives_0_0 ) )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5646:1: ( ( rule__DecContent__NegAlternatives_0_0 ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5647:1: ( rule__DecContent__NegAlternatives_0_0 )
            {
             before(grammarAccess.getDecContentAccess().getNegAlternatives_0_0()); 
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5648:1: ( rule__DecContent__NegAlternatives_0_0 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5648:2: rule__DecContent__NegAlternatives_0_0
            {
            pushFollow(FOLLOW_rule__DecContent__NegAlternatives_0_0_in_rule__DecContent__NegAssignment_011345);
            rule__DecContent__NegAlternatives_0_0();

            state._fsp--;


            }

             after(grammarAccess.getDecContentAccess().getNegAlternatives_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__DecContent__NegAssignment_0"


    // $ANTLR start "rule__DecContent__SingleContentAssignment_1"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5657:1: rule__DecContent__SingleContentAssignment_1 : ( ruleVarContent ) ;
    public final void rule__DecContent__SingleContentAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5661:1: ( ( ruleVarContent ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5662:1: ( ruleVarContent )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5662:1: ( ruleVarContent )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5663:1: ruleVarContent
            {
             before(grammarAccess.getDecContentAccess().getSingleContentVarContentParserRuleCall_1_0()); 
            pushFollow(FOLLOW_ruleVarContent_in_rule__DecContent__SingleContentAssignment_111378);
            ruleVarContent();

            state._fsp--;

             after(grammarAccess.getDecContentAccess().getSingleContentVarContentParserRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__DecContent__SingleContentAssignment_1"


    // $ANTLR start "rule__DecContent__OpAssignment_2_0"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5672:1: rule__DecContent__OpAssignment_2_0 : ( ruleOPERATOR ) ;
    public final void rule__DecContent__OpAssignment_2_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5676:1: ( ( ruleOPERATOR ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5677:1: ( ruleOPERATOR )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5677:1: ( ruleOPERATOR )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5678:1: ruleOPERATOR
            {
             before(grammarAccess.getDecContentAccess().getOpOPERATORParserRuleCall_2_0_0()); 
            pushFollow(FOLLOW_ruleOPERATOR_in_rule__DecContent__OpAssignment_2_011409);
            ruleOPERATOR();

            state._fsp--;

             after(grammarAccess.getDecContentAccess().getOpOPERATORParserRuleCall_2_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__DecContent__OpAssignment_2_0"


    // $ANTLR start "rule__DecContent__NextConAssignment_2_1"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5687:1: rule__DecContent__NextConAssignment_2_1 : ( ruleVarContent ) ;
    public final void rule__DecContent__NextConAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5691:1: ( ( ruleVarContent ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5692:1: ( ruleVarContent )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5692:1: ( ruleVarContent )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5693:1: ruleVarContent
            {
             before(grammarAccess.getDecContentAccess().getNextConVarContentParserRuleCall_2_1_0()); 
            pushFollow(FOLLOW_ruleVarContent_in_rule__DecContent__NextConAssignment_2_111440);
            ruleVarContent();

            state._fsp--;

             after(grammarAccess.getDecContentAccess().getNextConVarContentParserRuleCall_2_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__DecContent__NextConAssignment_2_1"


    // $ANTLR start "rule__VarContent__UnOPAssignment_0_0"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5702:1: rule__VarContent__UnOPAssignment_0_0 : ( ( rule__VarContent__UnOPAlternatives_0_0_0 ) ) ;
    public final void rule__VarContent__UnOPAssignment_0_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5706:1: ( ( ( rule__VarContent__UnOPAlternatives_0_0_0 ) ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5707:1: ( ( rule__VarContent__UnOPAlternatives_0_0_0 ) )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5707:1: ( ( rule__VarContent__UnOPAlternatives_0_0_0 ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5708:1: ( rule__VarContent__UnOPAlternatives_0_0_0 )
            {
             before(grammarAccess.getVarContentAccess().getUnOPAlternatives_0_0_0()); 
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5709:1: ( rule__VarContent__UnOPAlternatives_0_0_0 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5709:2: rule__VarContent__UnOPAlternatives_0_0_0
            {
            pushFollow(FOLLOW_rule__VarContent__UnOPAlternatives_0_0_0_in_rule__VarContent__UnOPAssignment_0_011471);
            rule__VarContent__UnOPAlternatives_0_0_0();

            state._fsp--;


            }

             after(grammarAccess.getVarContentAccess().getUnOPAlternatives_0_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarContent__UnOPAssignment_0_0"


    // $ANTLR start "rule__VarContent__NumAssignment_0_1"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5718:1: rule__VarContent__NumAssignment_0_1 : ( RULE_NUMBER ) ;
    public final void rule__VarContent__NumAssignment_0_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5722:1: ( ( RULE_NUMBER ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5723:1: ( RULE_NUMBER )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5723:1: ( RULE_NUMBER )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5724:1: RULE_NUMBER
            {
             before(grammarAccess.getVarContentAccess().getNumNUMBERTerminalRuleCall_0_1_0()); 
            match(input,RULE_NUMBER,FOLLOW_RULE_NUMBER_in_rule__VarContent__NumAssignment_0_111504); 
             after(grammarAccess.getVarContentAccess().getNumNUMBERTerminalRuleCall_0_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarContent__NumAssignment_0_1"


    // $ANTLR start "rule__VarContent__StringAssignment_1"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5733:1: rule__VarContent__StringAssignment_1 : ( RULE_STRING ) ;
    public final void rule__VarContent__StringAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5737:1: ( ( RULE_STRING ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5738:1: ( RULE_STRING )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5738:1: ( RULE_STRING )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5739:1: RULE_STRING
            {
             before(grammarAccess.getVarContentAccess().getStringSTRINGTerminalRuleCall_1_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__VarContent__StringAssignment_111535); 
             after(grammarAccess.getVarContentAccess().getStringSTRINGTerminalRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarContent__StringAssignment_1"


    // $ANTLR start "rule__VarContent__UnOPAssignment_2_0"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5748:1: rule__VarContent__UnOPAssignment_2_0 : ( ( rule__VarContent__UnOPAlternatives_2_0_0 ) ) ;
    public final void rule__VarContent__UnOPAssignment_2_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5752:1: ( ( ( rule__VarContent__UnOPAlternatives_2_0_0 ) ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5753:1: ( ( rule__VarContent__UnOPAlternatives_2_0_0 ) )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5753:1: ( ( rule__VarContent__UnOPAlternatives_2_0_0 ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5754:1: ( rule__VarContent__UnOPAlternatives_2_0_0 )
            {
             before(grammarAccess.getVarContentAccess().getUnOPAlternatives_2_0_0()); 
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5755:1: ( rule__VarContent__UnOPAlternatives_2_0_0 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5755:2: rule__VarContent__UnOPAlternatives_2_0_0
            {
            pushFollow(FOLLOW_rule__VarContent__UnOPAlternatives_2_0_0_in_rule__VarContent__UnOPAssignment_2_011566);
            rule__VarContent__UnOPAlternatives_2_0_0();

            state._fsp--;


            }

             after(grammarAccess.getVarContentAccess().getUnOPAlternatives_2_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarContent__UnOPAssignment_2_0"


    // $ANTLR start "rule__VarContent__ReferenceAssignment_2_1_0"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5764:1: rule__VarContent__ReferenceAssignment_2_1_0 : ( ( RULE_ID ) ) ;
    public final void rule__VarContent__ReferenceAssignment_2_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5768:1: ( ( ( RULE_ID ) ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5769:1: ( ( RULE_ID ) )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5769:1: ( ( RULE_ID ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5770:1: ( RULE_ID )
            {
             before(grammarAccess.getVarContentAccess().getReferenceDeclarationCrossReference_2_1_0_0()); 
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5771:1: ( RULE_ID )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5772:1: RULE_ID
            {
             before(grammarAccess.getVarContentAccess().getReferenceDeclarationIDTerminalRuleCall_2_1_0_0_1()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__VarContent__ReferenceAssignment_2_1_011603); 
             after(grammarAccess.getVarContentAccess().getReferenceDeclarationIDTerminalRuleCall_2_1_0_0_1()); 

            }

             after(grammarAccess.getVarContentAccess().getReferenceDeclarationCrossReference_2_1_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarContent__ReferenceAssignment_2_1_0"


    // $ANTLR start "rule__VarContent__ForEachVarAssignment_2_1_1"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5783:1: rule__VarContent__ForEachVarAssignment_2_1_1 : ( ( '_x' ) ) ;
    public final void rule__VarContent__ForEachVarAssignment_2_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5787:1: ( ( ( '_x' ) ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5788:1: ( ( '_x' ) )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5788:1: ( ( '_x' ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5789:1: ( '_x' )
            {
             before(grammarAccess.getVarContentAccess().getForEachVar_xKeyword_2_1_1_0()); 
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5790:1: ( '_x' )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5791:1: '_x'
            {
             before(grammarAccess.getVarContentAccess().getForEachVar_xKeyword_2_1_1_0()); 
            match(input,48,FOLLOW_48_in_rule__VarContent__ForEachVarAssignment_2_1_111643); 
             after(grammarAccess.getVarContentAccess().getForEachVar_xKeyword_2_1_1_0()); 

            }

             after(grammarAccess.getVarContentAccess().getForEachVar_xKeyword_2_1_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarContent__ForEachVarAssignment_2_1_1"


    // $ANTLR start "rule__VarContent__SelAssignment_2_2_0"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5806:1: rule__VarContent__SelAssignment_2_2_0 : ( ( 'select' ) ) ;
    public final void rule__VarContent__SelAssignment_2_2_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5810:1: ( ( ( 'select' ) ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5811:1: ( ( 'select' ) )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5811:1: ( ( 'select' ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5812:1: ( 'select' )
            {
             before(grammarAccess.getVarContentAccess().getSelSelectKeyword_2_2_0_0()); 
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5813:1: ( 'select' )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5814:1: 'select'
            {
             before(grammarAccess.getVarContentAccess().getSelSelectKeyword_2_2_0_0()); 
            match(input,26,FOLLOW_26_in_rule__VarContent__SelAssignment_2_2_011687); 
             after(grammarAccess.getVarContentAccess().getSelSelectKeyword_2_2_0_0()); 

            }

             after(grammarAccess.getVarContentAccess().getSelSelectKeyword_2_2_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarContent__SelAssignment_2_2_0"


    // $ANTLR start "rule__VarContent__IndexAssignment_2_2_1"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5829:1: rule__VarContent__IndexAssignment_2_2_1 : ( RULE_NUMBER ) ;
    public final void rule__VarContent__IndexAssignment_2_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5833:1: ( ( RULE_NUMBER ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5834:1: ( RULE_NUMBER )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5834:1: ( RULE_NUMBER )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5835:1: RULE_NUMBER
            {
             before(grammarAccess.getVarContentAccess().getIndexNUMBERTerminalRuleCall_2_2_1_0()); 
            match(input,RULE_NUMBER,FOLLOW_RULE_NUMBER_in_rule__VarContent__IndexAssignment_2_2_111726); 
             after(grammarAccess.getVarContentAccess().getIndexNUMBERTerminalRuleCall_2_2_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarContent__IndexAssignment_2_2_1"


    // $ANTLR start "rule__VarContent__ArrayContentAssignment_3_1"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5844:1: rule__VarContent__ArrayContentAssignment_3_1 : ( ruleArrayLiteral ) ;
    public final void rule__VarContent__ArrayContentAssignment_3_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5848:1: ( ( ruleArrayLiteral ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5849:1: ( ruleArrayLiteral )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5849:1: ( ruleArrayLiteral )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5850:1: ruleArrayLiteral
            {
             before(grammarAccess.getVarContentAccess().getArrayContentArrayLiteralParserRuleCall_3_1_0()); 
            pushFollow(FOLLOW_ruleArrayLiteral_in_rule__VarContent__ArrayContentAssignment_3_111757);
            ruleArrayLiteral();

            state._fsp--;

             after(grammarAccess.getVarContentAccess().getArrayContentArrayLiteralParserRuleCall_3_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarContent__ArrayContentAssignment_3_1"


    // $ANTLR start "rule__VarContent__ExecuteAssignment_3_2_0"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5859:1: rule__VarContent__ExecuteAssignment_3_2_0 : ( ( 'call' ) ) ;
    public final void rule__VarContent__ExecuteAssignment_3_2_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5863:1: ( ( ( 'call' ) ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5864:1: ( ( 'call' ) )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5864:1: ( ( 'call' ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5865:1: ( 'call' )
            {
             before(grammarAccess.getVarContentAccess().getExecuteCallKeyword_3_2_0_0()); 
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5866:1: ( 'call' )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5867:1: 'call'
            {
             before(grammarAccess.getVarContentAccess().getExecuteCallKeyword_3_2_0_0()); 
            match(input,49,FOLLOW_49_in_rule__VarContent__ExecuteAssignment_3_2_011793); 
             after(grammarAccess.getVarContentAccess().getExecuteCallKeyword_3_2_0_0()); 

            }

             after(grammarAccess.getVarContentAccess().getExecuteCallKeyword_3_2_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarContent__ExecuteAssignment_3_2_0"


    // $ANTLR start "rule__VarContent__MethodNameAssignment_3_2_1"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5882:1: rule__VarContent__MethodNameAssignment_3_2_1 : ( ruleMethodName ) ;
    public final void rule__VarContent__MethodNameAssignment_3_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5886:1: ( ( ruleMethodName ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5887:1: ( ruleMethodName )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5887:1: ( ruleMethodName )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5888:1: ruleMethodName
            {
             before(grammarAccess.getVarContentAccess().getMethodNameMethodNameParserRuleCall_3_2_1_0()); 
            pushFollow(FOLLOW_ruleMethodName_in_rule__VarContent__MethodNameAssignment_3_2_111832);
            ruleMethodName();

            state._fsp--;

             after(grammarAccess.getVarContentAccess().getMethodNameMethodNameParserRuleCall_3_2_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarContent__MethodNameAssignment_3_2_1"


    // $ANTLR start "rule__VarContent__UnOPAssignment_4_0"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5897:1: rule__VarContent__UnOPAssignment_4_0 : ( ( rule__VarContent__UnOPAlternatives_4_0_0 ) ) ;
    public final void rule__VarContent__UnOPAssignment_4_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5901:1: ( ( ( rule__VarContent__UnOPAlternatives_4_0_0 ) ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5902:1: ( ( rule__VarContent__UnOPAlternatives_4_0_0 ) )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5902:1: ( ( rule__VarContent__UnOPAlternatives_4_0_0 ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5903:1: ( rule__VarContent__UnOPAlternatives_4_0_0 )
            {
             before(grammarAccess.getVarContentAccess().getUnOPAlternatives_4_0_0()); 
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5904:1: ( rule__VarContent__UnOPAlternatives_4_0_0 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5904:2: rule__VarContent__UnOPAlternatives_4_0_0
            {
            pushFollow(FOLLOW_rule__VarContent__UnOPAlternatives_4_0_0_in_rule__VarContent__UnOPAssignment_4_011863);
            rule__VarContent__UnOPAlternatives_4_0_0();

            state._fsp--;


            }

             after(grammarAccess.getVarContentAccess().getUnOPAlternatives_4_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarContent__UnOPAssignment_4_0"


    // $ANTLR start "rule__VarContent__EmbracedAssignment_4_1"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5913:1: rule__VarContent__EmbracedAssignment_4_1 : ( ( '(' ) ) ;
    public final void rule__VarContent__EmbracedAssignment_4_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5917:1: ( ( ( '(' ) ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5918:1: ( ( '(' ) )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5918:1: ( ( '(' ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5919:1: ( '(' )
            {
             before(grammarAccess.getVarContentAccess().getEmbracedLeftParenthesisKeyword_4_1_0()); 
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5920:1: ( '(' )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5921:1: '('
            {
             before(grammarAccess.getVarContentAccess().getEmbracedLeftParenthesisKeyword_4_1_0()); 
            match(input,30,FOLLOW_30_in_rule__VarContent__EmbracedAssignment_4_111901); 
             after(grammarAccess.getVarContentAccess().getEmbracedLeftParenthesisKeyword_4_1_0()); 

            }

             after(grammarAccess.getVarContentAccess().getEmbracedLeftParenthesisKeyword_4_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarContent__EmbracedAssignment_4_1"


    // $ANTLR start "rule__VarContent__EmbrConAssignment_4_2"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5936:1: rule__VarContent__EmbrConAssignment_4_2 : ( ruleBracketContent ) ;
    public final void rule__VarContent__EmbrConAssignment_4_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5940:1: ( ( ruleBracketContent ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5941:1: ( ruleBracketContent )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5941:1: ( ruleBracketContent )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5942:1: ruleBracketContent
            {
             before(grammarAccess.getVarContentAccess().getEmbrConBracketContentParserRuleCall_4_2_0()); 
            pushFollow(FOLLOW_ruleBracketContent_in_rule__VarContent__EmbrConAssignment_4_211940);
            ruleBracketContent();

            state._fsp--;

             after(grammarAccess.getVarContentAccess().getEmbrConBracketContentParserRuleCall_4_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarContent__EmbrConAssignment_4_2"


    // $ANTLR start "rule__VarContent__BoolAssignment_5"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5951:1: rule__VarContent__BoolAssignment_5 : ( ruleBoolean ) ;
    public final void rule__VarContent__BoolAssignment_5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5955:1: ( ( ruleBoolean ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5956:1: ( ruleBoolean )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5956:1: ( ruleBoolean )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5957:1: ruleBoolean
            {
             before(grammarAccess.getVarContentAccess().getBoolBooleanParserRuleCall_5_0()); 
            pushFollow(FOLLOW_ruleBoolean_in_rule__VarContent__BoolAssignment_511971);
            ruleBoolean();

            state._fsp--;

             after(grammarAccess.getVarContentAccess().getBoolBooleanParserRuleCall_5_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarContent__BoolAssignment_5"


    // $ANTLR start "rule__VarContent__ParamAssignment_6_0"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5966:1: rule__VarContent__ParamAssignment_6_0 : ( ( '_this' ) ) ;
    public final void rule__VarContent__ParamAssignment_6_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5970:1: ( ( ( '_this' ) ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5971:1: ( ( '_this' ) )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5971:1: ( ( '_this' ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5972:1: ( '_this' )
            {
             before(grammarAccess.getVarContentAccess().getParam_thisKeyword_6_0_0()); 
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5973:1: ( '_this' )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5974:1: '_this'
            {
             before(grammarAccess.getVarContentAccess().getParam_thisKeyword_6_0_0()); 
            match(input,50,FOLLOW_50_in_rule__VarContent__ParamAssignment_6_012007); 
             after(grammarAccess.getVarContentAccess().getParam_thisKeyword_6_0_0()); 

            }

             after(grammarAccess.getVarContentAccess().getParam_thisKeyword_6_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarContent__ParamAssignment_6_0"


    // $ANTLR start "rule__VarContent__IndexAssignment_6_1_1"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5989:1: rule__VarContent__IndexAssignment_6_1_1 : ( RULE_NUMBER ) ;
    public final void rule__VarContent__IndexAssignment_6_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5993:1: ( ( RULE_NUMBER ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5994:1: ( RULE_NUMBER )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5994:1: ( RULE_NUMBER )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:5995:1: RULE_NUMBER
            {
             before(grammarAccess.getVarContentAccess().getIndexNUMBERTerminalRuleCall_6_1_1_0()); 
            match(input,RULE_NUMBER,FOLLOW_RULE_NUMBER_in_rule__VarContent__IndexAssignment_6_1_112046); 
             after(grammarAccess.getVarContentAccess().getIndexNUMBERTerminalRuleCall_6_1_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarContent__IndexAssignment_6_1_1"


    // $ANTLR start "rule__ArrayLiteral__ConAssignment_0"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6004:1: rule__ArrayLiteral__ConAssignment_0 : ( ( '[' ) ) ;
    public final void rule__ArrayLiteral__ConAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6008:1: ( ( ( '[' ) ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6009:1: ( ( '[' ) )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6009:1: ( ( '[' ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6010:1: ( '[' )
            {
             before(grammarAccess.getArrayLiteralAccess().getConLeftSquareBracketKeyword_0_0()); 
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6011:1: ( '[' )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6012:1: '['
            {
             before(grammarAccess.getArrayLiteralAccess().getConLeftSquareBracketKeyword_0_0()); 
            match(input,35,FOLLOW_35_in_rule__ArrayLiteral__ConAssignment_012082); 
             after(grammarAccess.getArrayLiteralAccess().getConLeftSquareBracketKeyword_0_0()); 

            }

             after(grammarAccess.getArrayLiteralAccess().getConLeftSquareBracketKeyword_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ArrayLiteral__ConAssignment_0"


    // $ANTLR start "rule__ArrayLiteral__ContentAssignment_1_0"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6027:1: rule__ArrayLiteral__ContentAssignment_1_0 : ( ruleVarContent ) ;
    public final void rule__ArrayLiteral__ContentAssignment_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6031:1: ( ( ruleVarContent ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6032:1: ( ruleVarContent )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6032:1: ( ruleVarContent )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6033:1: ruleVarContent
            {
             before(grammarAccess.getArrayLiteralAccess().getContentVarContentParserRuleCall_1_0_0()); 
            pushFollow(FOLLOW_ruleVarContent_in_rule__ArrayLiteral__ContentAssignment_1_012121);
            ruleVarContent();

            state._fsp--;

             after(grammarAccess.getArrayLiteralAccess().getContentVarContentParserRuleCall_1_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ArrayLiteral__ContentAssignment_1_0"


    // $ANTLR start "rule__ArrayLiteral__NextContentAssignment_1_1_1"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6042:1: rule__ArrayLiteral__NextContentAssignment_1_1_1 : ( ruleVarContent ) ;
    public final void rule__ArrayLiteral__NextContentAssignment_1_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6046:1: ( ( ruleVarContent ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6047:1: ( ruleVarContent )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6047:1: ( ruleVarContent )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6048:1: ruleVarContent
            {
             before(grammarAccess.getArrayLiteralAccess().getNextContentVarContentParserRuleCall_1_1_1_0()); 
            pushFollow(FOLLOW_ruleVarContent_in_rule__ArrayLiteral__NextContentAssignment_1_1_112152);
            ruleVarContent();

            state._fsp--;

             after(grammarAccess.getArrayLiteralAccess().getNextContentVarContentParserRuleCall_1_1_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ArrayLiteral__NextContentAssignment_1_1_1"


    // $ANTLR start "rule__ControlStructure__IfStatAssignment_0"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6057:1: rule__ControlStructure__IfStatAssignment_0 : ( ruleifType ) ;
    public final void rule__ControlStructure__IfStatAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6061:1: ( ( ruleifType ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6062:1: ( ruleifType )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6062:1: ( ruleifType )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6063:1: ruleifType
            {
             before(grammarAccess.getControlStructureAccess().getIfStatIfTypeParserRuleCall_0_0()); 
            pushFollow(FOLLOW_ruleifType_in_rule__ControlStructure__IfStatAssignment_012183);
            ruleifType();

            state._fsp--;

             after(grammarAccess.getControlStructureAccess().getIfStatIfTypeParserRuleCall_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ControlStructure__IfStatAssignment_0"


    // $ANTLR start "rule__ControlStructure__WhileStatAssignment_1"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6072:1: rule__ControlStructure__WhileStatAssignment_1 : ( ruleWhileType ) ;
    public final void rule__ControlStructure__WhileStatAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6076:1: ( ( ruleWhileType ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6077:1: ( ruleWhileType )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6077:1: ( ruleWhileType )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6078:1: ruleWhileType
            {
             before(grammarAccess.getControlStructureAccess().getWhileStatWhileTypeParserRuleCall_1_0()); 
            pushFollow(FOLLOW_ruleWhileType_in_rule__ControlStructure__WhileStatAssignment_112214);
            ruleWhileType();

            state._fsp--;

             after(grammarAccess.getControlStructureAccess().getWhileStatWhileTypeParserRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ControlStructure__WhileStatAssignment_1"


    // $ANTLR start "rule__ControlStructure__ForStatAssignment_2"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6087:1: rule__ControlStructure__ForStatAssignment_2 : ( ruleForType ) ;
    public final void rule__ControlStructure__ForStatAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6091:1: ( ( ruleForType ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6092:1: ( ruleForType )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6092:1: ( ruleForType )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6093:1: ruleForType
            {
             before(grammarAccess.getControlStructureAccess().getForStatForTypeParserRuleCall_2_0()); 
            pushFollow(FOLLOW_ruleForType_in_rule__ControlStructure__ForStatAssignment_212245);
            ruleForType();

            state._fsp--;

             after(grammarAccess.getControlStructureAccess().getForStatForTypeParserRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ControlStructure__ForStatAssignment_2"


    // $ANTLR start "rule__ControlStructure__ForEachStatAssignment_3"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6102:1: rule__ControlStructure__ForEachStatAssignment_3 : ( ruleForeachType ) ;
    public final void rule__ControlStructure__ForEachStatAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6106:1: ( ( ruleForeachType ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6107:1: ( ruleForeachType )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6107:1: ( ruleForeachType )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6108:1: ruleForeachType
            {
             before(grammarAccess.getControlStructureAccess().getForEachStatForeachTypeParserRuleCall_3_0()); 
            pushFollow(FOLLOW_ruleForeachType_in_rule__ControlStructure__ForEachStatAssignment_312276);
            ruleForeachType();

            state._fsp--;

             after(grammarAccess.getControlStructureAccess().getForEachStatForeachTypeParserRuleCall_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ControlStructure__ForEachStatAssignment_3"


    // $ANTLR start "rule__ControlStructure__SwitchStatAssignment_4"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6117:1: rule__ControlStructure__SwitchStatAssignment_4 : ( ruleSwitchType ) ;
    public final void rule__ControlStructure__SwitchStatAssignment_4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6121:1: ( ( ruleSwitchType ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6122:1: ( ruleSwitchType )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6122:1: ( ruleSwitchType )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6123:1: ruleSwitchType
            {
             before(grammarAccess.getControlStructureAccess().getSwitchStatSwitchTypeParserRuleCall_4_0()); 
            pushFollow(FOLLOW_ruleSwitchType_in_rule__ControlStructure__SwitchStatAssignment_412307);
            ruleSwitchType();

            state._fsp--;

             after(grammarAccess.getControlStructureAccess().getSwitchStatSwitchTypeParserRuleCall_4_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ControlStructure__SwitchStatAssignment_4"


    // $ANTLR start "rule__IfType__ConditionAssignment_2"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6132:1: rule__IfType__ConditionAssignment_2 : ( ruleBooleanContent ) ;
    public final void rule__IfType__ConditionAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6136:1: ( ( ruleBooleanContent ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6137:1: ( ruleBooleanContent )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6137:1: ( ruleBooleanContent )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6138:1: ruleBooleanContent
            {
             before(grammarAccess.getIfTypeAccess().getConditionBooleanContentParserRuleCall_2_0()); 
            pushFollow(FOLLOW_ruleBooleanContent_in_rule__IfType__ConditionAssignment_212338);
            ruleBooleanContent();

            state._fsp--;

             after(grammarAccess.getIfTypeAccess().getConditionBooleanContentParserRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfType__ConditionAssignment_2"


    // $ANTLR start "rule__IfType__ThenCodeAssignment_4_0_1_0_1"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6147:1: rule__IfType__ThenCodeAssignment_4_0_1_0_1 : ( ruleCode ) ;
    public final void rule__IfType__ThenCodeAssignment_4_0_1_0_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6151:1: ( ( ruleCode ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6152:1: ( ruleCode )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6152:1: ( ruleCode )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6153:1: ruleCode
            {
             before(grammarAccess.getIfTypeAccess().getThenCodeCodeParserRuleCall_4_0_1_0_1_0()); 
            pushFollow(FOLLOW_ruleCode_in_rule__IfType__ThenCodeAssignment_4_0_1_0_112369);
            ruleCode();

            state._fsp--;

             after(grammarAccess.getIfTypeAccess().getThenCodeCodeParserRuleCall_4_0_1_0_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfType__ThenCodeAssignment_4_0_1_0_1"


    // $ANTLR start "rule__IfType__ElseCodeAssignment_4_0_1_0_3_2"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6162:1: rule__IfType__ElseCodeAssignment_4_0_1_0_3_2 : ( ruleCode ) ;
    public final void rule__IfType__ElseCodeAssignment_4_0_1_0_3_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6166:1: ( ( ruleCode ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6167:1: ( ruleCode )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6167:1: ( ruleCode )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6168:1: ruleCode
            {
             before(grammarAccess.getIfTypeAccess().getElseCodeCodeParserRuleCall_4_0_1_0_3_2_0()); 
            pushFollow(FOLLOW_ruleCode_in_rule__IfType__ElseCodeAssignment_4_0_1_0_3_212400);
            ruleCode();

            state._fsp--;

             after(grammarAccess.getIfTypeAccess().getElseCodeCodeParserRuleCall_4_0_1_0_3_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfType__ElseCodeAssignment_4_0_1_0_3_2"


    // $ANTLR start "rule__IfType__ThenCodeAssignment_4_0_1_1_2"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6177:1: rule__IfType__ThenCodeAssignment_4_0_1_1_2 : ( ruleCode ) ;
    public final void rule__IfType__ThenCodeAssignment_4_0_1_1_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6181:1: ( ( ruleCode ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6182:1: ( ruleCode )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6182:1: ( ruleCode )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6183:1: ruleCode
            {
             before(grammarAccess.getIfTypeAccess().getThenCodeCodeParserRuleCall_4_0_1_1_2_0()); 
            pushFollow(FOLLOW_ruleCode_in_rule__IfType__ThenCodeAssignment_4_0_1_1_212431);
            ruleCode();

            state._fsp--;

             after(grammarAccess.getIfTypeAccess().getThenCodeCodeParserRuleCall_4_0_1_1_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfType__ThenCodeAssignment_4_0_1_1_2"


    // $ANTLR start "rule__IfType__ElseCodeAssignment_4_0_1_1_6"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6192:1: rule__IfType__ElseCodeAssignment_4_0_1_1_6 : ( ruleCode ) ;
    public final void rule__IfType__ElseCodeAssignment_4_0_1_1_6() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6196:1: ( ( ruleCode ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6197:1: ( ruleCode )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6197:1: ( ruleCode )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6198:1: ruleCode
            {
             before(grammarAccess.getIfTypeAccess().getElseCodeCodeParserRuleCall_4_0_1_1_6_0()); 
            pushFollow(FOLLOW_ruleCode_in_rule__IfType__ElseCodeAssignment_4_0_1_1_612462);
            ruleCode();

            state._fsp--;

             after(grammarAccess.getIfTypeAccess().getElseCodeCodeParserRuleCall_4_0_1_1_6_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfType__ElseCodeAssignment_4_0_1_1_6"


    // $ANTLR start "rule__IfType__ExitCodeAssignment_4_1_2"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6207:1: rule__IfType__ExitCodeAssignment_4_1_2 : ( ruleCode ) ;
    public final void rule__IfType__ExitCodeAssignment_4_1_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6211:1: ( ( ruleCode ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6212:1: ( ruleCode )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6212:1: ( ruleCode )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6213:1: ruleCode
            {
             before(grammarAccess.getIfTypeAccess().getExitCodeCodeParserRuleCall_4_1_2_0()); 
            pushFollow(FOLLOW_ruleCode_in_rule__IfType__ExitCodeAssignment_4_1_212493);
            ruleCode();

            state._fsp--;

             after(grammarAccess.getIfTypeAccess().getExitCodeCodeParserRuleCall_4_1_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfType__ExitCodeAssignment_4_1_2"


    // $ANTLR start "rule__WhileType__ConditionAssignment_2"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6222:1: rule__WhileType__ConditionAssignment_2 : ( ruleBooleanContent ) ;
    public final void rule__WhileType__ConditionAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6226:1: ( ( ruleBooleanContent ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6227:1: ( ruleBooleanContent )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6227:1: ( ruleBooleanContent )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6228:1: ruleBooleanContent
            {
             before(grammarAccess.getWhileTypeAccess().getConditionBooleanContentParserRuleCall_2_0()); 
            pushFollow(FOLLOW_ruleBooleanContent_in_rule__WhileType__ConditionAssignment_212524);
            ruleBooleanContent();

            state._fsp--;

             after(grammarAccess.getWhileTypeAccess().getConditionBooleanContentParserRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__WhileType__ConditionAssignment_2"


    // $ANTLR start "rule__WhileType__LoopCodeAssignment_6"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6237:1: rule__WhileType__LoopCodeAssignment_6 : ( ruleCode ) ;
    public final void rule__WhileType__LoopCodeAssignment_6() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6241:1: ( ( ruleCode ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6242:1: ( ruleCode )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6242:1: ( ruleCode )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6243:1: ruleCode
            {
             before(grammarAccess.getWhileTypeAccess().getLoopCodeCodeParserRuleCall_6_0()); 
            pushFollow(FOLLOW_ruleCode_in_rule__WhileType__LoopCodeAssignment_612555);
            ruleCode();

            state._fsp--;

             after(grammarAccess.getWhileTypeAccess().getLoopCodeCodeParserRuleCall_6_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__WhileType__LoopCodeAssignment_6"


    // $ANTLR start "rule__ForType__BeginAssignment_1_0_2"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6252:1: rule__ForType__BeginAssignment_1_0_2 : ( ruleDeclaration ) ;
    public final void rule__ForType__BeginAssignment_1_0_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6256:1: ( ( ruleDeclaration ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6257:1: ( ruleDeclaration )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6257:1: ( ruleDeclaration )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6258:1: ruleDeclaration
            {
             before(grammarAccess.getForTypeAccess().getBeginDeclarationParserRuleCall_1_0_2_0()); 
            pushFollow(FOLLOW_ruleDeclaration_in_rule__ForType__BeginAssignment_1_0_212586);
            ruleDeclaration();

            state._fsp--;

             after(grammarAccess.getForTypeAccess().getBeginDeclarationParserRuleCall_1_0_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ForType__BeginAssignment_1_0_2"


    // $ANTLR start "rule__ForType__ConditionAssignment_1_0_6"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6267:1: rule__ForType__ConditionAssignment_1_0_6 : ( ruleBooleanContent ) ;
    public final void rule__ForType__ConditionAssignment_1_0_6() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6271:1: ( ( ruleBooleanContent ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6272:1: ( ruleBooleanContent )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6272:1: ( ruleBooleanContent )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6273:1: ruleBooleanContent
            {
             before(grammarAccess.getForTypeAccess().getConditionBooleanContentParserRuleCall_1_0_6_0()); 
            pushFollow(FOLLOW_ruleBooleanContent_in_rule__ForType__ConditionAssignment_1_0_612617);
            ruleBooleanContent();

            state._fsp--;

             after(grammarAccess.getForTypeAccess().getConditionBooleanContentParserRuleCall_1_0_6_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ForType__ConditionAssignment_1_0_6"


    // $ANTLR start "rule__ForType__EndAssignment_1_0_10"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6282:1: rule__ForType__EndAssignment_1_0_10 : ( ruleDeclaration ) ;
    public final void rule__ForType__EndAssignment_1_0_10() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6286:1: ( ( ruleDeclaration ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6287:1: ( ruleDeclaration )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6287:1: ( ruleDeclaration )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6288:1: ruleDeclaration
            {
             before(grammarAccess.getForTypeAccess().getEndDeclarationParserRuleCall_1_0_10_0()); 
            pushFollow(FOLLOW_ruleDeclaration_in_rule__ForType__EndAssignment_1_0_1012648);
            ruleDeclaration();

            state._fsp--;

             after(grammarAccess.getForTypeAccess().getEndDeclarationParserRuleCall_1_0_10_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ForType__EndAssignment_1_0_10"


    // $ANTLR start "rule__ForType__FromAssignment_1_1_2"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6297:1: rule__ForType__FromAssignment_1_1_2 : ( RULE_NUMBER ) ;
    public final void rule__ForType__FromAssignment_1_1_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6301:1: ( ( RULE_NUMBER ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6302:1: ( RULE_NUMBER )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6302:1: ( RULE_NUMBER )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6303:1: RULE_NUMBER
            {
             before(grammarAccess.getForTypeAccess().getFromNUMBERTerminalRuleCall_1_1_2_0()); 
            match(input,RULE_NUMBER,FOLLOW_RULE_NUMBER_in_rule__ForType__FromAssignment_1_1_212679); 
             after(grammarAccess.getForTypeAccess().getFromNUMBERTerminalRuleCall_1_1_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ForType__FromAssignment_1_1_2"


    // $ANTLR start "rule__ForType__ToAssignment_1_1_4"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6312:1: rule__ForType__ToAssignment_1_1_4 : ( RULE_NUMBER ) ;
    public final void rule__ForType__ToAssignment_1_1_4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6316:1: ( ( RULE_NUMBER ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6317:1: ( RULE_NUMBER )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6317:1: ( RULE_NUMBER )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6318:1: RULE_NUMBER
            {
             before(grammarAccess.getForTypeAccess().getToNUMBERTerminalRuleCall_1_1_4_0()); 
            match(input,RULE_NUMBER,FOLLOW_RULE_NUMBER_in_rule__ForType__ToAssignment_1_1_412710); 
             after(grammarAccess.getForTypeAccess().getToNUMBERTerminalRuleCall_1_1_4_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ForType__ToAssignment_1_1_4"


    // $ANTLR start "rule__ForType__StepAssignment_1_1_5_1"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6327:1: rule__ForType__StepAssignment_1_1_5_1 : ( RULE_NUMBER ) ;
    public final void rule__ForType__StepAssignment_1_1_5_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6331:1: ( ( RULE_NUMBER ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6332:1: ( RULE_NUMBER )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6332:1: ( RULE_NUMBER )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6333:1: RULE_NUMBER
            {
             before(grammarAccess.getForTypeAccess().getStepNUMBERTerminalRuleCall_1_1_5_1_0()); 
            match(input,RULE_NUMBER,FOLLOW_RULE_NUMBER_in_rule__ForType__StepAssignment_1_1_5_112741); 
             after(grammarAccess.getForTypeAccess().getStepNUMBERTerminalRuleCall_1_1_5_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ForType__StepAssignment_1_1_5_1"


    // $ANTLR start "rule__ForType__LoopCodeAssignment_4"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6342:1: rule__ForType__LoopCodeAssignment_4 : ( ruleCode ) ;
    public final void rule__ForType__LoopCodeAssignment_4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6346:1: ( ( ruleCode ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6347:1: ( ruleCode )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6347:1: ( ruleCode )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6348:1: ruleCode
            {
             before(grammarAccess.getForTypeAccess().getLoopCodeCodeParserRuleCall_4_0()); 
            pushFollow(FOLLOW_ruleCode_in_rule__ForType__LoopCodeAssignment_412772);
            ruleCode();

            state._fsp--;

             after(grammarAccess.getForTypeAccess().getLoopCodeCodeParserRuleCall_4_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ForType__LoopCodeAssignment_4"


    // $ANTLR start "rule__ForVarDeclaration__NameAssignment"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6357:1: rule__ForVarDeclaration__NameAssignment : ( RULE_STRING ) ;
    public final void rule__ForVarDeclaration__NameAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6361:1: ( ( RULE_STRING ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6362:1: ( RULE_STRING )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6362:1: ( RULE_STRING )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6363:1: RULE_STRING
            {
             before(grammarAccess.getForVarDeclarationAccess().getNameSTRINGTerminalRuleCall_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__ForVarDeclaration__NameAssignment12803); 
             after(grammarAccess.getForVarDeclarationAccess().getNameSTRINGTerminalRuleCall_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ForVarDeclaration__NameAssignment"


    // $ANTLR start "rule__ForeachType__CodeAssignment_1"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6372:1: rule__ForeachType__CodeAssignment_1 : ( ruleCode ) ;
    public final void rule__ForeachType__CodeAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6376:1: ( ( ruleCode ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6377:1: ( ruleCode )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6377:1: ( ruleCode )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6378:1: ruleCode
            {
             before(grammarAccess.getForeachTypeAccess().getCodeCodeParserRuleCall_1_0()); 
            pushFollow(FOLLOW_ruleCode_in_rule__ForeachType__CodeAssignment_112834);
            ruleCode();

            state._fsp--;

             after(grammarAccess.getForeachTypeAccess().getCodeCodeParserRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ForeachType__CodeAssignment_1"


    // $ANTLR start "rule__ForeachType__ArrayAssignment_4_0"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6387:1: rule__ForeachType__ArrayAssignment_4_0 : ( ( RULE_ID ) ) ;
    public final void rule__ForeachType__ArrayAssignment_4_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6391:1: ( ( ( RULE_ID ) ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6392:1: ( ( RULE_ID ) )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6392:1: ( ( RULE_ID ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6393:1: ( RULE_ID )
            {
             before(grammarAccess.getForeachTypeAccess().getArrayDeclarationCrossReference_4_0_0()); 
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6394:1: ( RULE_ID )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6395:1: RULE_ID
            {
             before(grammarAccess.getForeachTypeAccess().getArrayDeclarationIDTerminalRuleCall_4_0_0_1()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__ForeachType__ArrayAssignment_4_012869); 
             after(grammarAccess.getForeachTypeAccess().getArrayDeclarationIDTerminalRuleCall_4_0_0_1()); 

            }

             after(grammarAccess.getForeachTypeAccess().getArrayDeclarationCrossReference_4_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ForeachType__ArrayAssignment_4_0"


    // $ANTLR start "rule__ForeachType__ArrayLiteralAssignment_4_1"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6406:1: rule__ForeachType__ArrayLiteralAssignment_4_1 : ( ruleArrayLiteral ) ;
    public final void rule__ForeachType__ArrayLiteralAssignment_4_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6410:1: ( ( ruleArrayLiteral ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6411:1: ( ruleArrayLiteral )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6411:1: ( ruleArrayLiteral )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6412:1: ruleArrayLiteral
            {
             before(grammarAccess.getForeachTypeAccess().getArrayLiteralArrayLiteralParserRuleCall_4_1_0()); 
            pushFollow(FOLLOW_ruleArrayLiteral_in_rule__ForeachType__ArrayLiteralAssignment_4_112904);
            ruleArrayLiteral();

            state._fsp--;

             after(grammarAccess.getForeachTypeAccess().getArrayLiteralArrayLiteralParserRuleCall_4_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ForeachType__ArrayLiteralAssignment_4_1"


    // $ANTLR start "rule__SwitchType__VarAssignment_2"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6421:1: rule__SwitchType__VarAssignment_2 : ( ( RULE_ID ) ) ;
    public final void rule__SwitchType__VarAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6425:1: ( ( ( RULE_ID ) ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6426:1: ( ( RULE_ID ) )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6426:1: ( ( RULE_ID ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6427:1: ( RULE_ID )
            {
             before(grammarAccess.getSwitchTypeAccess().getVarDeclarationCrossReference_2_0()); 
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6428:1: ( RULE_ID )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6429:1: RULE_ID
            {
             before(grammarAccess.getSwitchTypeAccess().getVarDeclarationIDTerminalRuleCall_2_0_1()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__SwitchType__VarAssignment_212939); 
             after(grammarAccess.getSwitchTypeAccess().getVarDeclarationIDTerminalRuleCall_2_0_1()); 

            }

             after(grammarAccess.getSwitchTypeAccess().getVarDeclarationCrossReference_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SwitchType__VarAssignment_2"


    // $ANTLR start "rule__SwitchType__ValueAssignment_6_1"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6440:1: rule__SwitchType__ValueAssignment_6_1 : ( ruleANYTHING ) ;
    public final void rule__SwitchType__ValueAssignment_6_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6444:1: ( ( ruleANYTHING ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6445:1: ( ruleANYTHING )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6445:1: ( ruleANYTHING )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6446:1: ruleANYTHING
            {
             before(grammarAccess.getSwitchTypeAccess().getValueANYTHINGParserRuleCall_6_1_0()); 
            pushFollow(FOLLOW_ruleANYTHING_in_rule__SwitchType__ValueAssignment_6_112974);
            ruleANYTHING();

            state._fsp--;

             after(grammarAccess.getSwitchTypeAccess().getValueANYTHINGParserRuleCall_6_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SwitchType__ValueAssignment_6_1"


    // $ANTLR start "rule__SwitchType__CaseCodeAssignment_6_4"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6455:1: rule__SwitchType__CaseCodeAssignment_6_4 : ( ruleCode ) ;
    public final void rule__SwitchType__CaseCodeAssignment_6_4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6459:1: ( ( ruleCode ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6460:1: ( ruleCode )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6460:1: ( ruleCode )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6461:1: ruleCode
            {
             before(grammarAccess.getSwitchTypeAccess().getCaseCodeCodeParserRuleCall_6_4_0()); 
            pushFollow(FOLLOW_ruleCode_in_rule__SwitchType__CaseCodeAssignment_6_413005);
            ruleCode();

            state._fsp--;

             after(grammarAccess.getSwitchTypeAccess().getCaseCodeCodeParserRuleCall_6_4_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SwitchType__CaseCodeAssignment_6_4"


    // $ANTLR start "rule__SwitchType__DefaultCodeAssignment_7_2"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6470:1: rule__SwitchType__DefaultCodeAssignment_7_2 : ( ruleCode ) ;
    public final void rule__SwitchType__DefaultCodeAssignment_7_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6474:1: ( ( ruleCode ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6475:1: ( ruleCode )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6475:1: ( ruleCode )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6476:1: ruleCode
            {
             before(grammarAccess.getSwitchTypeAccess().getDefaultCodeCodeParserRuleCall_7_2_0()); 
            pushFollow(FOLLOW_ruleCode_in_rule__SwitchType__DefaultCodeAssignment_7_213036);
            ruleCode();

            state._fsp--;

             after(grammarAccess.getSwitchTypeAccess().getDefaultCodeCodeParserRuleCall_7_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SwitchType__DefaultCodeAssignment_7_2"


    // $ANTLR start "rule__Method__ParamsAssignment_0"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6485:1: rule__Method__ParamsAssignment_0 : ( ruleArrayLiteral ) ;
    public final void rule__Method__ParamsAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6489:1: ( ( ruleArrayLiteral ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6490:1: ( ruleArrayLiteral )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6490:1: ( ruleArrayLiteral )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6491:1: ruleArrayLiteral
            {
             before(grammarAccess.getMethodAccess().getParamsArrayLiteralParserRuleCall_0_0()); 
            pushFollow(FOLLOW_ruleArrayLiteral_in_rule__Method__ParamsAssignment_013067);
            ruleArrayLiteral();

            state._fsp--;

             after(grammarAccess.getMethodAccess().getParamsArrayLiteralParserRuleCall_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Method__ParamsAssignment_0"


    // $ANTLR start "rule__Method__ExecuteAssignment_1"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6500:1: rule__Method__ExecuteAssignment_1 : ( ( 'spawn' ) ) ;
    public final void rule__Method__ExecuteAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6504:1: ( ( ( 'spawn' ) ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6505:1: ( ( 'spawn' ) )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6505:1: ( ( 'spawn' ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6506:1: ( 'spawn' )
            {
             before(grammarAccess.getMethodAccess().getExecuteSpawnKeyword_1_0()); 
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6507:1: ( 'spawn' )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6508:1: 'spawn'
            {
             before(grammarAccess.getMethodAccess().getExecuteSpawnKeyword_1_0()); 
            match(input,51,FOLLOW_51_in_rule__Method__ExecuteAssignment_113103); 
             after(grammarAccess.getMethodAccess().getExecuteSpawnKeyword_1_0()); 

            }

             after(grammarAccess.getMethodAccess().getExecuteSpawnKeyword_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Method__ExecuteAssignment_1"


    // $ANTLR start "rule__Method__MethodNameAssignment_2"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6523:1: rule__Method__MethodNameAssignment_2 : ( ruleMethodName ) ;
    public final void rule__Method__MethodNameAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6527:1: ( ( ruleMethodName ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6528:1: ( ruleMethodName )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6528:1: ( ruleMethodName )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6529:1: ruleMethodName
            {
             before(grammarAccess.getMethodAccess().getMethodNameMethodNameParserRuleCall_2_0()); 
            pushFollow(FOLLOW_ruleMethodName_in_rule__Method__MethodNameAssignment_213142);
            ruleMethodName();

            state._fsp--;

             after(grammarAccess.getMethodAccess().getMethodNameMethodNameParserRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Method__MethodNameAssignment_2"


    // $ANTLR start "rule__ANYTHING__BoolAssignment_0"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6541:1: rule__ANYTHING__BoolAssignment_0 : ( ruleBoolean ) ;
    public final void rule__ANYTHING__BoolAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6545:1: ( ( ruleBoolean ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6546:1: ( ruleBoolean )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6546:1: ( ruleBoolean )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6547:1: ruleBoolean
            {
             before(grammarAccess.getANYTHINGAccess().getBoolBooleanParserRuleCall_0_0()); 
            pushFollow(FOLLOW_ruleBoolean_in_rule__ANYTHING__BoolAssignment_013176);
            ruleBoolean();

            state._fsp--;

             after(grammarAccess.getANYTHINGAccess().getBoolBooleanParserRuleCall_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ANYTHING__BoolAssignment_0"


    // $ANTLR start "rule__ANYTHING__NumAssignment_1"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6556:1: rule__ANYTHING__NumAssignment_1 : ( RULE_NUMBER ) ;
    public final void rule__ANYTHING__NumAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6560:1: ( ( RULE_NUMBER ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6561:1: ( RULE_NUMBER )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6561:1: ( RULE_NUMBER )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6562:1: RULE_NUMBER
            {
             before(grammarAccess.getANYTHINGAccess().getNumNUMBERTerminalRuleCall_1_0()); 
            match(input,RULE_NUMBER,FOLLOW_RULE_NUMBER_in_rule__ANYTHING__NumAssignment_113207); 
             after(grammarAccess.getANYTHINGAccess().getNumNUMBERTerminalRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ANYTHING__NumAssignment_1"


    // $ANTLR start "rule__ANYTHING__StringAssignment_2"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6571:1: rule__ANYTHING__StringAssignment_2 : ( RULE_STRING ) ;
    public final void rule__ANYTHING__StringAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6575:1: ( ( RULE_STRING ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6576:1: ( RULE_STRING )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6576:1: ( RULE_STRING )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6577:1: RULE_STRING
            {
             before(grammarAccess.getANYTHINGAccess().getStringSTRINGTerminalRuleCall_2_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__ANYTHING__StringAssignment_213238); 
             after(grammarAccess.getANYTHINGAccess().getStringSTRINGTerminalRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ANYTHING__StringAssignment_2"


    // $ANTLR start "rule__ANYTHING__ReferenceAssignment_3"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6586:1: rule__ANYTHING__ReferenceAssignment_3 : ( ( RULE_ID ) ) ;
    public final void rule__ANYTHING__ReferenceAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6590:1: ( ( ( RULE_ID ) ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6591:1: ( ( RULE_ID ) )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6591:1: ( ( RULE_ID ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6592:1: ( RULE_ID )
            {
             before(grammarAccess.getANYTHINGAccess().getReferenceDeclarationCrossReference_3_0()); 
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6593:1: ( RULE_ID )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6594:1: RULE_ID
            {
             before(grammarAccess.getANYTHINGAccess().getReferenceDeclarationIDTerminalRuleCall_3_0_1()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__ANYTHING__ReferenceAssignment_313273); 
             after(grammarAccess.getANYTHINGAccess().getReferenceDeclarationIDTerminalRuleCall_3_0_1()); 

            }

             after(grammarAccess.getANYTHINGAccess().getReferenceDeclarationCrossReference_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ANYTHING__ReferenceAssignment_3"


    // $ANTLR start "rule__Boolean__BoolAssignment_0"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6605:1: rule__Boolean__BoolAssignment_0 : ( ( rule__Boolean__BoolAlternatives_0_0 ) ) ;
    public final void rule__Boolean__BoolAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6609:1: ( ( ( rule__Boolean__BoolAlternatives_0_0 ) ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6610:1: ( ( rule__Boolean__BoolAlternatives_0_0 ) )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6610:1: ( ( rule__Boolean__BoolAlternatives_0_0 ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6611:1: ( rule__Boolean__BoolAlternatives_0_0 )
            {
             before(grammarAccess.getBooleanAccess().getBoolAlternatives_0_0()); 
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6612:1: ( rule__Boolean__BoolAlternatives_0_0 )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6612:2: rule__Boolean__BoolAlternatives_0_0
            {
            pushFollow(FOLLOW_rule__Boolean__BoolAlternatives_0_0_in_rule__Boolean__BoolAssignment_013308);
            rule__Boolean__BoolAlternatives_0_0();

            state._fsp--;


            }

             after(grammarAccess.getBooleanAccess().getBoolAlternatives_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Boolean__BoolAssignment_0"


    // $ANTLR start "rule__Boolean__CommandAssignment_1"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6621:1: rule__Boolean__CommandAssignment_1 : ( ruleBoolCommand ) ;
    public final void rule__Boolean__CommandAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6625:1: ( ( ruleBoolCommand ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6626:1: ( ruleBoolCommand )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6626:1: ( ruleBoolCommand )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6627:1: ruleBoolCommand
            {
             before(grammarAccess.getBooleanAccess().getCommandBoolCommandParserRuleCall_1_0()); 
            pushFollow(FOLLOW_ruleBoolCommand_in_rule__Boolean__CommandAssignment_113341);
            ruleBoolCommand();

            state._fsp--;

             after(grammarAccess.getBooleanAccess().getCommandBoolCommandParserRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Boolean__CommandAssignment_1"


    // $ANTLR start "rule__BooleanContent__BoolConAssignment"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6636:1: rule__BooleanContent__BoolConAssignment : ( ruleBracketContent ) ;
    public final void rule__BooleanContent__BoolConAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6640:1: ( ( ruleBracketContent ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6641:1: ( ruleBracketContent )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6641:1: ( ruleBracketContent )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6642:1: ruleBracketContent
            {
             before(grammarAccess.getBooleanContentAccess().getBoolConBracketContentParserRuleCall_0()); 
            pushFollow(FOLLOW_ruleBracketContent_in_rule__BooleanContent__BoolConAssignment13372);
            ruleBracketContent();

            state._fsp--;

             after(grammarAccess.getBooleanContentAccess().getBoolConBracketContentParserRuleCall_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__BooleanContent__BoolConAssignment"


    // $ANTLR start "rule__MethodName__RefAssignment"
    // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6651:1: rule__MethodName__RefAssignment : ( ( RULE_ID ) ) ;
    public final void rule__MethodName__RefAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6655:1: ( ( ( RULE_ID ) ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6656:1: ( ( RULE_ID ) )
            {
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6656:1: ( ( RULE_ID ) )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6657:1: ( RULE_ID )
            {
             before(grammarAccess.getMethodNameAccess().getRefDeclarationCrossReference_0()); 
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6658:1: ( RULE_ID )
            // ../raven.sqf.ui/src-gen/raven/ui/contentassist/antlr/internal/InternalSQF.g:6659:1: RULE_ID
            {
             before(grammarAccess.getMethodNameAccess().getRefDeclarationIDTerminalRuleCall_0_1()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__MethodName__RefAssignment13407); 
             after(grammarAccess.getMethodNameAccess().getRefDeclarationIDTerminalRuleCall_0_1()); 

            }

             after(grammarAccess.getMethodNameAccess().getRefDeclarationCrossReference_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MethodName__RefAssignment"

    // Delegated rules


    protected DFA4 dfa4 = new DFA4(this);
    static final String DFA4_eotS =
        "\12\uffff";
    static final String DFA4_eofS =
        "\12\uffff";
    static final String DFA4_minS =
        "\3\4\7\uffff";
    static final String DFA4_maxS =
        "\1\62\2\60\7\uffff";
    static final String DFA4_acceptS =
        "\3\uffff\1\1\1\2\1\3\1\4\1\5\1\6\1\7";
    static final String DFA4_specialS =
        "\12\uffff}>";
    static final String[] DFA4_transitionS = {
            "\1\5\1\uffff\1\3\1\4\5\uffff\1\1\1\2\4\uffff\4\10\7\uffff\1"+
            "\7\4\uffff\1\6\14\uffff\1\5\1\uffff\1\11",
            "\1\5\1\uffff\1\3\27\uffff\1\7\4\uffff\1\6\14\uffff\1\5",
            "\1\5\1\uffff\1\3\27\uffff\1\7\21\uffff\1\5",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA4_eot = DFA.unpackEncodedString(DFA4_eotS);
    static final short[] DFA4_eof = DFA.unpackEncodedString(DFA4_eofS);
    static final char[] DFA4_min = DFA.unpackEncodedStringToUnsignedChars(DFA4_minS);
    static final char[] DFA4_max = DFA.unpackEncodedStringToUnsignedChars(DFA4_maxS);
    static final short[] DFA4_accept = DFA.unpackEncodedString(DFA4_acceptS);
    static final short[] DFA4_special = DFA.unpackEncodedString(DFA4_specialS);
    static final short[][] DFA4_transition;

    static {
        int numStates = DFA4_transitionS.length;
        DFA4_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA4_transition[i] = DFA.unpackEncodedString(DFA4_transitionS[i]);
        }
    }

    class DFA4 extends DFA {

        public DFA4(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 4;
            this.eot = DFA4_eot;
            this.eof = DFA4_eof;
            this.min = DFA4_min;
            this.max = DFA4_max;
            this.accept = DFA4_accept;
            this.special = DFA4_special;
            this.transition = DFA4_transition;
        }
        public String getDescription() {
            return "706:1: rule__VarContent__Alternatives : ( ( ( rule__VarContent__Group_0__0 ) ) | ( ( rule__VarContent__StringAssignment_1 ) ) | ( ( rule__VarContent__Group_2__0 ) ) | ( ( rule__VarContent__Group_3__0 ) ) | ( ( rule__VarContent__Group_4__0 ) ) | ( ( rule__VarContent__BoolAssignment_5 ) ) | ( ( rule__VarContent__Group_6__0 ) ) );";
        }
    }
 

    public static final BitSet FOLLOW_ruleModel_in_entryRuleModel61 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleModel68 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Model__ElementsAssignment_in_ruleModel94 = new BitSet(new long[]{0x000010A920000012L});
    public static final BitSet FOLLOW_ruleCode_in_entryRuleCode122 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleCode129 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Code__Alternatives_in_ruleCode155 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDeclaration_in_entryRuleDeclaration182 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleDeclaration189 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Declaration__Group__0_in_ruleDeclaration215 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBracketContent_in_entryRuleBracketContent242 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleBracketContent249 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__BracketContent__Group__0_in_ruleBracketContent275 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDecContent_in_entryRuleDecContent302 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleDecContent309 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__DecContent__Group__0_in_ruleDecContent335 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleVarContent_in_entryRuleVarContent362 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleVarContent369 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__VarContent__Alternatives_in_ruleVarContent395 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleArrayLiteral_in_entryRuleArrayLiteral422 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleArrayLiteral429 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ArrayLiteral__Group__0_in_ruleArrayLiteral455 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleControlStructure_in_entryRuleControlStructure482 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleControlStructure489 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ControlStructure__Alternatives_in_ruleControlStructure515 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleifType_in_entryRuleifType542 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleifType549 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__IfType__Group__0_in_ruleifType575 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleWhileType_in_entryRuleWhileType602 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleWhileType609 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__WhileType__Group__0_in_ruleWhileType635 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleForType_in_entryRuleForType662 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleForType669 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ForType__Group__0_in_ruleForType695 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleforVarDeclaration_in_entryRuleforVarDeclaration722 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleforVarDeclaration729 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ForVarDeclaration__NameAssignment_in_ruleforVarDeclaration755 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleForeachType_in_entryRuleForeachType782 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleForeachType789 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ForeachType__Group__0_in_ruleForeachType815 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSwitchType_in_entryRuleSwitchType842 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSwitchType849 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SwitchType__Group__0_in_ruleSwitchType875 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleMethod_in_entryRuleMethod902 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleMethod909 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Method__Group__0_in_ruleMethod935 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOPERATOR_in_entryRuleOPERATOR964 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOPERATOR971 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OPERATOR__Alternatives_in_ruleOPERATOR997 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleANYTHING_in_entryRuleANYTHING1026 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleANYTHING1033 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ANYTHING__Alternatives_in_ruleANYTHING1059 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBoolean_in_entryRuleBoolean1086 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleBoolean1093 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Boolean__Alternatives_in_ruleBoolean1119 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBooleanContent_in_entryRuleBooleanContent1146 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleBooleanContent1153 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__BooleanContent__BoolConAssignment_in_ruleBooleanContent1179 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleMethodName_in_entryRuleMethodName1206 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleMethodName1213 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__MethodName__RefAssignment_in_ruleMethodName1239 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBoolCommand_in_entryRuleBoolCommand1266 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleBoolCommand1273 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__BoolCommand__Alternatives_in_ruleBoolCommand1299 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Code__Group_0__0_in_rule__Code__Alternatives1335 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Code__Group_1__0_in_rule__Code__Alternatives1353 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Code__Group_2__0_in_rule__Code__Alternatives1371 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_11_in_rule__DecContent__NegAlternatives_0_01405 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_rule__DecContent__NegAlternatives_0_01425 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__VarContent__Group_0__0_in_rule__VarContent__Alternatives1459 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__VarContent__StringAssignment_1_in_rule__VarContent__Alternatives1477 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__VarContent__Group_2__0_in_rule__VarContent__Alternatives1495 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__VarContent__Group_3__0_in_rule__VarContent__Alternatives1513 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__VarContent__Group_4__0_in_rule__VarContent__Alternatives1531 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__VarContent__BoolAssignment_5_in_rule__VarContent__Alternatives1549 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__VarContent__Group_6__0_in_rule__VarContent__Alternatives1567 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_rule__VarContent__UnOPAlternatives_0_0_01601 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_14_in_rule__VarContent__UnOPAlternatives_0_0_01621 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_rule__VarContent__UnOPAlternatives_2_0_01656 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_14_in_rule__VarContent__UnOPAlternatives_2_0_01676 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__VarContent__ReferenceAssignment_2_1_0_in_rule__VarContent__Alternatives_2_11710 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__VarContent__ForEachVarAssignment_2_1_1_in_rule__VarContent__Alternatives_2_11728 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_rule__VarContent__UnOPAlternatives_4_0_01762 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_14_in_rule__VarContent__UnOPAlternatives_4_0_01782 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ControlStructure__IfStatAssignment_0_in_rule__ControlStructure__Alternatives1816 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ControlStructure__WhileStatAssignment_1_in_rule__ControlStructure__Alternatives1834 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ControlStructure__ForStatAssignment_2_in_rule__ControlStructure__Alternatives1852 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ControlStructure__ForEachStatAssignment_3_in_rule__ControlStructure__Alternatives1870 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ControlStructure__SwitchStatAssignment_4_in_rule__ControlStructure__Alternatives1888 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__IfType__Group_4_0__0_in_rule__IfType__Alternatives_41921 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__IfType__Group_4_1__0_in_rule__IfType__Alternatives_41939 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__IfType__Group_4_0_1_0__0_in_rule__IfType__Alternatives_4_0_11972 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__IfType__Group_4_0_1_1__0_in_rule__IfType__Alternatives_4_0_11990 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ForType__Group_1_0__0_in_rule__ForType__Alternatives_12023 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ForType__Group_1_1__0_in_rule__ForType__Alternatives_12041 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ForeachType__ArrayAssignment_4_0_in_rule__ForeachType__Alternatives_42074 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ForeachType__ArrayLiteralAssignment_4_1_in_rule__ForeachType__Alternatives_42092 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_rule__OPERATOR__Alternatives2127 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_14_in_rule__OPERATOR__Alternatives2147 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_rule__OPERATOR__Alternatives2167 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_rule__OPERATOR__Alternatives2187 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_17_in_rule__OPERATOR__Alternatives2207 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_rule__OPERATOR__Alternatives2227 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ANYTHING__BoolAssignment_0_in_rule__ANYTHING__Alternatives2261 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ANYTHING__NumAssignment_1_in_rule__ANYTHING__Alternatives2279 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ANYTHING__StringAssignment_2_in_rule__ANYTHING__Alternatives2297 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ANYTHING__ReferenceAssignment_3_in_rule__ANYTHING__Alternatives2315 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Boolean__BoolAssignment_0_in_rule__Boolean__Alternatives2348 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Boolean__CommandAssignment_1_in_rule__Boolean__Alternatives2366 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_rule__Boolean__BoolAlternatives_0_02400 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_20_in_rule__Boolean__BoolAlternatives_0_02420 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_21_in_rule__BoolCommand__Alternatives2455 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_22_in_rule__BoolCommand__Alternatives2475 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Code__Group_0__0__Impl_in_rule__Code__Group_0__02507 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_rule__Code__Group_0__1_in_rule__Code__Group_0__02510 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Code__DecAssignment_0_0_in_rule__Code__Group_0__0__Impl2537 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Code__Group_0__1__Impl_in_rule__Code__Group_0__12567 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_23_in_rule__Code__Group_0__1__Impl2595 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Code__Group_1__0__Impl_in_rule__Code__Group_1__02630 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_rule__Code__Group_1__1_in_rule__Code__Group_1__02633 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Code__ControlAssignment_1_0_in_rule__Code__Group_1__0__Impl2660 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Code__Group_1__1__Impl_in_rule__Code__Group_1__12690 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_23_in_rule__Code__Group_1__1__Impl2718 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Code__Group_2__0__Impl_in_rule__Code__Group_2__02753 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_rule__Code__Group_2__1_in_rule__Code__Group_2__02756 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Code__MethodAssignment_2_0_in_rule__Code__Group_2__0__Impl2783 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Code__Group_2__1__Impl_in_rule__Code__Group_2__12813 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_23_in_rule__Code__Group_2__1__Impl2841 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Declaration__Group__0__Impl_in_rule__Declaration__Group__02876 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_rule__Declaration__Group__1_in_rule__Declaration__Group__02879 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Declaration__NameAssignment_0_in_rule__Declaration__Group__0__Impl2906 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Declaration__Group__1__Impl_in_rule__Declaration__Group__12936 = new BitSet(new long[]{0x000510A9607878D0L});
    public static final BitSet FOLLOW_rule__Declaration__Group__2_in_rule__Declaration__Group__12939 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_24_in_rule__Declaration__Group__1__Impl2967 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Declaration__Group__2__Impl_in_rule__Declaration__Group__22998 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Declaration__BrConAssignment_2_in_rule__Declaration__Group__2__Impl3025 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__BracketContent__Group__0__Impl_in_rule__BracketContent__Group__03061 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rule__BracketContent__Group__1_in_rule__BracketContent__Group__03064 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__BracketContent__DecConAssignment_0_in_rule__BracketContent__Group__0__Impl3091 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__BracketContent__Group__1__Impl_in_rule__BracketContent__Group__13121 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__BracketContent__Group_1__0_in_rule__BracketContent__Group__1__Impl3148 = new BitSet(new long[]{0x0000000000000022L});
    public static final BitSet FOLLOW_rule__BracketContent__Group_1__0__Impl_in_rule__BracketContent__Group_1__03183 = new BitSet(new long[]{0x000510A9607878D0L});
    public static final BitSet FOLLOW_rule__BracketContent__Group_1__1_in_rule__BracketContent__Group_1__03186 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__BracketContent__CompAssignment_1_0_in_rule__BracketContent__Group_1__0__Impl3213 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__BracketContent__Group_1__1__Impl_in_rule__BracketContent__Group_1__13243 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__BracketContent__ContentAssignment_1_1_in_rule__BracketContent__Group_1__1__Impl3270 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__DecContent__Group__0__Impl_in_rule__DecContent__Group__03304 = new BitSet(new long[]{0x000510A9607878D0L});
    public static final BitSet FOLLOW_rule__DecContent__Group__1_in_rule__DecContent__Group__03307 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__DecContent__NegAssignment_0_in_rule__DecContent__Group__0__Impl3334 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__DecContent__Group__1__Impl_in_rule__DecContent__Group__13365 = new BitSet(new long[]{0x000000000007E000L});
    public static final BitSet FOLLOW_rule__DecContent__Group__2_in_rule__DecContent__Group__13368 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__DecContent__SingleContentAssignment_1_in_rule__DecContent__Group__1__Impl3395 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__DecContent__Group__2__Impl_in_rule__DecContent__Group__23425 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__DecContent__Group_2__0_in_rule__DecContent__Group__2__Impl3452 = new BitSet(new long[]{0x000000000007E002L});
    public static final BitSet FOLLOW_rule__DecContent__Group_2__0__Impl_in_rule__DecContent__Group_2__03489 = new BitSet(new long[]{0x000510A9607878D0L});
    public static final BitSet FOLLOW_rule__DecContent__Group_2__1_in_rule__DecContent__Group_2__03492 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__DecContent__OpAssignment_2_0_in_rule__DecContent__Group_2__0__Impl3519 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__DecContent__Group_2__1__Impl_in_rule__DecContent__Group_2__13549 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__DecContent__NextConAssignment_2_1_in_rule__DecContent__Group_2__1__Impl3576 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__VarContent__Group_0__0__Impl_in_rule__VarContent__Group_0__03610 = new BitSet(new long[]{0x0000000000006040L});
    public static final BitSet FOLLOW_rule__VarContent__Group_0__1_in_rule__VarContent__Group_0__03613 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__VarContent__UnOPAssignment_0_0_in_rule__VarContent__Group_0__0__Impl3640 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__VarContent__Group_0__1__Impl_in_rule__VarContent__Group_0__13671 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__VarContent__NumAssignment_0_1_in_rule__VarContent__Group_0__1__Impl3698 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__VarContent__Group_2__0__Impl_in_rule__VarContent__Group_2__03732 = new BitSet(new long[]{0x0001000000006010L});
    public static final BitSet FOLLOW_rule__VarContent__Group_2__1_in_rule__VarContent__Group_2__03735 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__VarContent__UnOPAssignment_2_0_in_rule__VarContent__Group_2__0__Impl3762 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__VarContent__Group_2__1__Impl_in_rule__VarContent__Group_2__13793 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_rule__VarContent__Group_2__2_in_rule__VarContent__Group_2__13796 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__VarContent__Alternatives_2_1_in_rule__VarContent__Group_2__1__Impl3823 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__VarContent__Group_2__2__Impl_in_rule__VarContent__Group_2__23853 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__VarContent__Group_2_2__0_in_rule__VarContent__Group_2__2__Impl3880 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__VarContent__Group_2_2__0__Impl_in_rule__VarContent__Group_2_2__03917 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_rule__VarContent__Group_2_2__1_in_rule__VarContent__Group_2_2__03920 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__VarContent__SelAssignment_2_2_0_in_rule__VarContent__Group_2_2__0__Impl3947 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__VarContent__Group_2_2__1__Impl_in_rule__VarContent__Group_2_2__13977 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__VarContent__IndexAssignment_2_2_1_in_rule__VarContent__Group_2_2__1__Impl4004 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__VarContent__Group_3__0__Impl_in_rule__VarContent__Group_3__04038 = new BitSet(new long[]{0x000010A920002010L});
    public static final BitSet FOLLOW_rule__VarContent__Group_3__1_in_rule__VarContent__Group_3__04041 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_rule__VarContent__Group_3__0__Impl4070 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__VarContent__Group_3__1__Impl_in_rule__VarContent__Group_3__14103 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_rule__VarContent__Group_3__2_in_rule__VarContent__Group_3__14106 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__VarContent__ArrayContentAssignment_3_1_in_rule__VarContent__Group_3__1__Impl4133 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__VarContent__Group_3__2__Impl_in_rule__VarContent__Group_3__24163 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__VarContent__Group_3_2__0_in_rule__VarContent__Group_3__2__Impl4190 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__VarContent__Group_3_2__0__Impl_in_rule__VarContent__Group_3_2__04227 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__VarContent__Group_3_2__1_in_rule__VarContent__Group_3_2__04230 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__VarContent__ExecuteAssignment_3_2_0_in_rule__VarContent__Group_3_2__0__Impl4257 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__VarContent__Group_3_2__1__Impl_in_rule__VarContent__Group_3_2__14287 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__VarContent__MethodNameAssignment_3_2_1_in_rule__VarContent__Group_3_2__1__Impl4314 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__VarContent__Group_4__0__Impl_in_rule__VarContent__Group_4__04348 = new BitSet(new long[]{0x0000000040006000L});
    public static final BitSet FOLLOW_rule__VarContent__Group_4__1_in_rule__VarContent__Group_4__04351 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__VarContent__UnOPAssignment_4_0_in_rule__VarContent__Group_4__0__Impl4378 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__VarContent__Group_4__1__Impl_in_rule__VarContent__Group_4__14409 = new BitSet(new long[]{0x000510A9607878D0L});
    public static final BitSet FOLLOW_rule__VarContent__Group_4__2_in_rule__VarContent__Group_4__14412 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__VarContent__EmbracedAssignment_4_1_in_rule__VarContent__Group_4__1__Impl4439 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__VarContent__Group_4__2__Impl_in_rule__VarContent__Group_4__24469 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_rule__VarContent__Group_4__3_in_rule__VarContent__Group_4__24472 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__VarContent__EmbrConAssignment_4_2_in_rule__VarContent__Group_4__2__Impl4499 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__VarContent__Group_4__3__Impl_in_rule__VarContent__Group_4__34529 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_25_in_rule__VarContent__Group_4__3__Impl4557 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__VarContent__Group_6__0__Impl_in_rule__VarContent__Group_6__04596 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_rule__VarContent__Group_6__1_in_rule__VarContent__Group_6__04599 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__VarContent__ParamAssignment_6_0_in_rule__VarContent__Group_6__0__Impl4626 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__VarContent__Group_6__1__Impl_in_rule__VarContent__Group_6__14656 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__VarContent__Group_6_1__0_in_rule__VarContent__Group_6__1__Impl4683 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__VarContent__Group_6_1__0__Impl_in_rule__VarContent__Group_6_1__04718 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_rule__VarContent__Group_6_1__1_in_rule__VarContent__Group_6_1__04721 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_26_in_rule__VarContent__Group_6_1__0__Impl4749 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__VarContent__Group_6_1__1__Impl_in_rule__VarContent__Group_6_1__14780 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__VarContent__IndexAssignment_6_1_1_in_rule__VarContent__Group_6_1__1__Impl4807 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ArrayLiteral__Group__0__Impl_in_rule__ArrayLiteral__Group__04841 = new BitSet(new long[]{0x000510A9687878D0L});
    public static final BitSet FOLLOW_rule__ArrayLiteral__Group__1_in_rule__ArrayLiteral__Group__04844 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ArrayLiteral__ConAssignment_0_in_rule__ArrayLiteral__Group__0__Impl4871 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ArrayLiteral__Group__1__Impl_in_rule__ArrayLiteral__Group__14901 = new BitSet(new long[]{0x000510A9687878D0L});
    public static final BitSet FOLLOW_rule__ArrayLiteral__Group__2_in_rule__ArrayLiteral__Group__14904 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ArrayLiteral__Group_1__0_in_rule__ArrayLiteral__Group__1__Impl4931 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ArrayLiteral__Group__2__Impl_in_rule__ArrayLiteral__Group__24962 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_27_in_rule__ArrayLiteral__Group__2__Impl4990 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ArrayLiteral__Group_1__0__Impl_in_rule__ArrayLiteral__Group_1__05027 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_rule__ArrayLiteral__Group_1__1_in_rule__ArrayLiteral__Group_1__05030 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ArrayLiteral__ContentAssignment_1_0_in_rule__ArrayLiteral__Group_1__0__Impl5057 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ArrayLiteral__Group_1__1__Impl_in_rule__ArrayLiteral__Group_1__15087 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ArrayLiteral__Group_1_1__0_in_rule__ArrayLiteral__Group_1__1__Impl5114 = new BitSet(new long[]{0x0000000010000002L});
    public static final BitSet FOLLOW_rule__ArrayLiteral__Group_1_1__0__Impl_in_rule__ArrayLiteral__Group_1_1__05149 = new BitSet(new long[]{0x000510A9607878D0L});
    public static final BitSet FOLLOW_rule__ArrayLiteral__Group_1_1__1_in_rule__ArrayLiteral__Group_1_1__05152 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_28_in_rule__ArrayLiteral__Group_1_1__0__Impl5180 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ArrayLiteral__Group_1_1__1__Impl_in_rule__ArrayLiteral__Group_1_1__15211 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ArrayLiteral__NextContentAssignment_1_1_1_in_rule__ArrayLiteral__Group_1_1__1__Impl5238 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__IfType__Group__0__Impl_in_rule__IfType__Group__05272 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_rule__IfType__Group__1_in_rule__IfType__Group__05275 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_29_in_rule__IfType__Group__0__Impl5303 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__IfType__Group__1__Impl_in_rule__IfType__Group__15334 = new BitSet(new long[]{0x000510A9607878D0L});
    public static final BitSet FOLLOW_rule__IfType__Group__2_in_rule__IfType__Group__15337 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_30_in_rule__IfType__Group__1__Impl5365 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__IfType__Group__2__Impl_in_rule__IfType__Group__25396 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_rule__IfType__Group__3_in_rule__IfType__Group__25399 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__IfType__ConditionAssignment_2_in_rule__IfType__Group__2__Impl5426 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__IfType__Group__3__Impl_in_rule__IfType__Group__35456 = new BitSet(new long[]{0x0000001080000000L});
    public static final BitSet FOLLOW_rule__IfType__Group__4_in_rule__IfType__Group__35459 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_25_in_rule__IfType__Group__3__Impl5487 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__IfType__Group__4__Impl_in_rule__IfType__Group__45518 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__IfType__Alternatives_4_in_rule__IfType__Group__4__Impl5545 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__IfType__Group_4_0__0__Impl_in_rule__IfType__Group_4_0__05585 = new BitSet(new long[]{0x0000000900000000L});
    public static final BitSet FOLLOW_rule__IfType__Group_4_0__1_in_rule__IfType__Group_4_0__05588 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_31_in_rule__IfType__Group_4_0__0__Impl5616 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__IfType__Group_4_0__1__Impl_in_rule__IfType__Group_4_0__15647 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__IfType__Alternatives_4_0_1_in_rule__IfType__Group_4_0__1__Impl5674 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__IfType__Group_4_0_1_0__0__Impl_in_rule__IfType__Group_4_0_1_0__05708 = new BitSet(new long[]{0x000010AB20000010L});
    public static final BitSet FOLLOW_rule__IfType__Group_4_0_1_0__1_in_rule__IfType__Group_4_0_1_0__05711 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_32_in_rule__IfType__Group_4_0_1_0__0__Impl5739 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__IfType__Group_4_0_1_0__1__Impl_in_rule__IfType__Group_4_0_1_0__15770 = new BitSet(new long[]{0x000010AB20000010L});
    public static final BitSet FOLLOW_rule__IfType__Group_4_0_1_0__2_in_rule__IfType__Group_4_0_1_0__15773 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__IfType__ThenCodeAssignment_4_0_1_0_1_in_rule__IfType__Group_4_0_1_0__1__Impl5800 = new BitSet(new long[]{0x000010A920000012L});
    public static final BitSet FOLLOW_rule__IfType__Group_4_0_1_0__2__Impl_in_rule__IfType__Group_4_0_1_0__25831 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_rule__IfType__Group_4_0_1_0__3_in_rule__IfType__Group_4_0_1_0__25834 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_33_in_rule__IfType__Group_4_0_1_0__2__Impl5862 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__IfType__Group_4_0_1_0__3__Impl_in_rule__IfType__Group_4_0_1_0__35893 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__IfType__Group_4_0_1_0_3__0_in_rule__IfType__Group_4_0_1_0__3__Impl5920 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__IfType__Group_4_0_1_0_3__0__Impl_in_rule__IfType__Group_4_0_1_0_3__05959 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_rule__IfType__Group_4_0_1_0_3__1_in_rule__IfType__Group_4_0_1_0_3__05962 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_34_in_rule__IfType__Group_4_0_1_0_3__0__Impl5990 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__IfType__Group_4_0_1_0_3__1__Impl_in_rule__IfType__Group_4_0_1_0_3__16021 = new BitSet(new long[]{0x000010AB20000010L});
    public static final BitSet FOLLOW_rule__IfType__Group_4_0_1_0_3__2_in_rule__IfType__Group_4_0_1_0_3__16024 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_32_in_rule__IfType__Group_4_0_1_0_3__1__Impl6052 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__IfType__Group_4_0_1_0_3__2__Impl_in_rule__IfType__Group_4_0_1_0_3__26083 = new BitSet(new long[]{0x000010AB20000010L});
    public static final BitSet FOLLOW_rule__IfType__Group_4_0_1_0_3__3_in_rule__IfType__Group_4_0_1_0_3__26086 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__IfType__ElseCodeAssignment_4_0_1_0_3_2_in_rule__IfType__Group_4_0_1_0_3__2__Impl6113 = new BitSet(new long[]{0x000010A920000012L});
    public static final BitSet FOLLOW_rule__IfType__Group_4_0_1_0_3__3__Impl_in_rule__IfType__Group_4_0_1_0_3__36144 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_33_in_rule__IfType__Group_4_0_1_0_3__3__Impl6172 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__IfType__Group_4_0_1_1__0__Impl_in_rule__IfType__Group_4_0_1_1__06211 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_rule__IfType__Group_4_0_1_1__1_in_rule__IfType__Group_4_0_1_1__06214 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_35_in_rule__IfType__Group_4_0_1_1__0__Impl6242 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__IfType__Group_4_0_1_1__1__Impl_in_rule__IfType__Group_4_0_1_1__16273 = new BitSet(new long[]{0x000010AB20000010L});
    public static final BitSet FOLLOW_rule__IfType__Group_4_0_1_1__2_in_rule__IfType__Group_4_0_1_1__16276 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_32_in_rule__IfType__Group_4_0_1_1__1__Impl6304 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__IfType__Group_4_0_1_1__2__Impl_in_rule__IfType__Group_4_0_1_1__26335 = new BitSet(new long[]{0x000010AB20000010L});
    public static final BitSet FOLLOW_rule__IfType__Group_4_0_1_1__3_in_rule__IfType__Group_4_0_1_1__26338 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__IfType__ThenCodeAssignment_4_0_1_1_2_in_rule__IfType__Group_4_0_1_1__2__Impl6365 = new BitSet(new long[]{0x000010A920000012L});
    public static final BitSet FOLLOW_rule__IfType__Group_4_0_1_1__3__Impl_in_rule__IfType__Group_4_0_1_1__36396 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_rule__IfType__Group_4_0_1_1__4_in_rule__IfType__Group_4_0_1_1__36399 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_33_in_rule__IfType__Group_4_0_1_1__3__Impl6427 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__IfType__Group_4_0_1_1__4__Impl_in_rule__IfType__Group_4_0_1_1__46458 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_rule__IfType__Group_4_0_1_1__5_in_rule__IfType__Group_4_0_1_1__46461 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_28_in_rule__IfType__Group_4_0_1_1__4__Impl6489 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__IfType__Group_4_0_1_1__5__Impl_in_rule__IfType__Group_4_0_1_1__56520 = new BitSet(new long[]{0x000010AB20000010L});
    public static final BitSet FOLLOW_rule__IfType__Group_4_0_1_1__6_in_rule__IfType__Group_4_0_1_1__56523 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_32_in_rule__IfType__Group_4_0_1_1__5__Impl6551 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__IfType__Group_4_0_1_1__6__Impl_in_rule__IfType__Group_4_0_1_1__66582 = new BitSet(new long[]{0x000010AB20000010L});
    public static final BitSet FOLLOW_rule__IfType__Group_4_0_1_1__7_in_rule__IfType__Group_4_0_1_1__66585 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__IfType__ElseCodeAssignment_4_0_1_1_6_in_rule__IfType__Group_4_0_1_1__6__Impl6612 = new BitSet(new long[]{0x000010A920000012L});
    public static final BitSet FOLLOW_rule__IfType__Group_4_0_1_1__7__Impl_in_rule__IfType__Group_4_0_1_1__76643 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_rule__IfType__Group_4_0_1_1__8_in_rule__IfType__Group_4_0_1_1__76646 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_33_in_rule__IfType__Group_4_0_1_1__7__Impl6674 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__IfType__Group_4_0_1_1__8__Impl_in_rule__IfType__Group_4_0_1_1__86705 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_27_in_rule__IfType__Group_4_0_1_1__8__Impl6733 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__IfType__Group_4_1__0__Impl_in_rule__IfType__Group_4_1__06782 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_rule__IfType__Group_4_1__1_in_rule__IfType__Group_4_1__06785 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_36_in_rule__IfType__Group_4_1__0__Impl6813 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__IfType__Group_4_1__1__Impl_in_rule__IfType__Group_4_1__16844 = new BitSet(new long[]{0x000010AB20000010L});
    public static final BitSet FOLLOW_rule__IfType__Group_4_1__2_in_rule__IfType__Group_4_1__16847 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_32_in_rule__IfType__Group_4_1__1__Impl6875 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__IfType__Group_4_1__2__Impl_in_rule__IfType__Group_4_1__26906 = new BitSet(new long[]{0x000010AB20000010L});
    public static final BitSet FOLLOW_rule__IfType__Group_4_1__3_in_rule__IfType__Group_4_1__26909 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__IfType__ExitCodeAssignment_4_1_2_in_rule__IfType__Group_4_1__2__Impl6936 = new BitSet(new long[]{0x000010A920000012L});
    public static final BitSet FOLLOW_rule__IfType__Group_4_1__3__Impl_in_rule__IfType__Group_4_1__36967 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_33_in_rule__IfType__Group_4_1__3__Impl6995 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__WhileType__Group__0__Impl_in_rule__WhileType__Group__07034 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_rule__WhileType__Group__1_in_rule__WhileType__Group__07037 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_37_in_rule__WhileType__Group__0__Impl7065 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__WhileType__Group__1__Impl_in_rule__WhileType__Group__17096 = new BitSet(new long[]{0x000510A9607878D0L});
    public static final BitSet FOLLOW_rule__WhileType__Group__2_in_rule__WhileType__Group__17099 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_32_in_rule__WhileType__Group__1__Impl7127 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__WhileType__Group__2__Impl_in_rule__WhileType__Group__27158 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_rule__WhileType__Group__3_in_rule__WhileType__Group__27161 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__WhileType__ConditionAssignment_2_in_rule__WhileType__Group__2__Impl7188 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__WhileType__Group__3__Impl_in_rule__WhileType__Group__37218 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_rule__WhileType__Group__4_in_rule__WhileType__Group__37221 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_33_in_rule__WhileType__Group__3__Impl7249 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__WhileType__Group__4__Impl_in_rule__WhileType__Group__47280 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_rule__WhileType__Group__5_in_rule__WhileType__Group__47283 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_38_in_rule__WhileType__Group__4__Impl7311 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__WhileType__Group__5__Impl_in_rule__WhileType__Group__57342 = new BitSet(new long[]{0x000010AB20000010L});
    public static final BitSet FOLLOW_rule__WhileType__Group__6_in_rule__WhileType__Group__57345 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_32_in_rule__WhileType__Group__5__Impl7373 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__WhileType__Group__6__Impl_in_rule__WhileType__Group__67404 = new BitSet(new long[]{0x000010AB20000010L});
    public static final BitSet FOLLOW_rule__WhileType__Group__7_in_rule__WhileType__Group__67407 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__WhileType__LoopCodeAssignment_6_in_rule__WhileType__Group__6__Impl7434 = new BitSet(new long[]{0x000010A920000012L});
    public static final BitSet FOLLOW_rule__WhileType__Group__7__Impl_in_rule__WhileType__Group__77465 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_33_in_rule__WhileType__Group__7__Impl7493 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ForType__Group__0__Impl_in_rule__ForType__Group__07540 = new BitSet(new long[]{0x0000000800000080L});
    public static final BitSet FOLLOW_rule__ForType__Group__1_in_rule__ForType__Group__07543 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_39_in_rule__ForType__Group__0__Impl7571 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ForType__Group__1__Impl_in_rule__ForType__Group__17602 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_rule__ForType__Group__2_in_rule__ForType__Group__17605 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ForType__Alternatives_1_in_rule__ForType__Group__1__Impl7632 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ForType__Group__2__Impl_in_rule__ForType__Group__27662 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_rule__ForType__Group__3_in_rule__ForType__Group__27665 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_38_in_rule__ForType__Group__2__Impl7693 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ForType__Group__3__Impl_in_rule__ForType__Group__37724 = new BitSet(new long[]{0x000010AB20000010L});
    public static final BitSet FOLLOW_rule__ForType__Group__4_in_rule__ForType__Group__37727 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_32_in_rule__ForType__Group__3__Impl7755 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ForType__Group__4__Impl_in_rule__ForType__Group__47786 = new BitSet(new long[]{0x000010AB20000010L});
    public static final BitSet FOLLOW_rule__ForType__Group__5_in_rule__ForType__Group__47789 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ForType__LoopCodeAssignment_4_in_rule__ForType__Group__4__Impl7816 = new BitSet(new long[]{0x000010A920000012L});
    public static final BitSet FOLLOW_rule__ForType__Group__5__Impl_in_rule__ForType__Group__57847 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_33_in_rule__ForType__Group__5__Impl7875 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ForType__Group_1_0__0__Impl_in_rule__ForType__Group_1_0__07918 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_rule__ForType__Group_1_0__1_in_rule__ForType__Group_1_0__07921 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_35_in_rule__ForType__Group_1_0__0__Impl7949 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ForType__Group_1_0__1__Impl_in_rule__ForType__Group_1_0__17980 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__ForType__Group_1_0__2_in_rule__ForType__Group_1_0__17983 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_32_in_rule__ForType__Group_1_0__1__Impl8011 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ForType__Group_1_0__2__Impl_in_rule__ForType__Group_1_0__28042 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_rule__ForType__Group_1_0__3_in_rule__ForType__Group_1_0__28045 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ForType__BeginAssignment_1_0_2_in_rule__ForType__Group_1_0__2__Impl8072 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ForType__Group_1_0__3__Impl_in_rule__ForType__Group_1_0__38102 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_rule__ForType__Group_1_0__4_in_rule__ForType__Group_1_0__38105 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_33_in_rule__ForType__Group_1_0__3__Impl8133 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ForType__Group_1_0__4__Impl_in_rule__ForType__Group_1_0__48164 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_rule__ForType__Group_1_0__5_in_rule__ForType__Group_1_0__48167 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_28_in_rule__ForType__Group_1_0__4__Impl8195 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ForType__Group_1_0__5__Impl_in_rule__ForType__Group_1_0__58226 = new BitSet(new long[]{0x000510A9607878D0L});
    public static final BitSet FOLLOW_rule__ForType__Group_1_0__6_in_rule__ForType__Group_1_0__58229 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_32_in_rule__ForType__Group_1_0__5__Impl8257 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ForType__Group_1_0__6__Impl_in_rule__ForType__Group_1_0__68288 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_rule__ForType__Group_1_0__7_in_rule__ForType__Group_1_0__68291 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ForType__ConditionAssignment_1_0_6_in_rule__ForType__Group_1_0__6__Impl8318 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ForType__Group_1_0__7__Impl_in_rule__ForType__Group_1_0__78348 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_rule__ForType__Group_1_0__8_in_rule__ForType__Group_1_0__78351 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_33_in_rule__ForType__Group_1_0__7__Impl8379 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ForType__Group_1_0__8__Impl_in_rule__ForType__Group_1_0__88410 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_rule__ForType__Group_1_0__9_in_rule__ForType__Group_1_0__88413 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_28_in_rule__ForType__Group_1_0__8__Impl8441 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ForType__Group_1_0__9__Impl_in_rule__ForType__Group_1_0__98472 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__ForType__Group_1_0__10_in_rule__ForType__Group_1_0__98475 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_32_in_rule__ForType__Group_1_0__9__Impl8503 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ForType__Group_1_0__10__Impl_in_rule__ForType__Group_1_0__108534 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_rule__ForType__Group_1_0__11_in_rule__ForType__Group_1_0__108537 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ForType__EndAssignment_1_0_10_in_rule__ForType__Group_1_0__10__Impl8564 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ForType__Group_1_0__11__Impl_in_rule__ForType__Group_1_0__118594 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_rule__ForType__Group_1_0__12_in_rule__ForType__Group_1_0__118597 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_33_in_rule__ForType__Group_1_0__11__Impl8625 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ForType__Group_1_0__12__Impl_in_rule__ForType__Group_1_0__128656 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_27_in_rule__ForType__Group_1_0__12__Impl8684 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ForType__Group_1_1__0__Impl_in_rule__ForType__Group_1_1__08741 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_rule__ForType__Group_1_1__1_in_rule__ForType__Group_1_1__08744 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleforVarDeclaration_in_rule__ForType__Group_1_1__0__Impl8771 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ForType__Group_1_1__1__Impl_in_rule__ForType__Group_1_1__18800 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_rule__ForType__Group_1_1__2_in_rule__ForType__Group_1_1__18803 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_40_in_rule__ForType__Group_1_1__1__Impl8831 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ForType__Group_1_1__2__Impl_in_rule__ForType__Group_1_1__28862 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_rule__ForType__Group_1_1__3_in_rule__ForType__Group_1_1__28865 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ForType__FromAssignment_1_1_2_in_rule__ForType__Group_1_1__2__Impl8892 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ForType__Group_1_1__3__Impl_in_rule__ForType__Group_1_1__38922 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_rule__ForType__Group_1_1__4_in_rule__ForType__Group_1_1__38925 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_41_in_rule__ForType__Group_1_1__3__Impl8953 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ForType__Group_1_1__4__Impl_in_rule__ForType__Group_1_1__48984 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_rule__ForType__Group_1_1__5_in_rule__ForType__Group_1_1__48987 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ForType__ToAssignment_1_1_4_in_rule__ForType__Group_1_1__4__Impl9014 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ForType__Group_1_1__5__Impl_in_rule__ForType__Group_1_1__59044 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ForType__Group_1_1_5__0_in_rule__ForType__Group_1_1__5__Impl9071 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ForType__Group_1_1_5__0__Impl_in_rule__ForType__Group_1_1_5__09114 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_rule__ForType__Group_1_1_5__1_in_rule__ForType__Group_1_1_5__09117 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_42_in_rule__ForType__Group_1_1_5__0__Impl9145 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ForType__Group_1_1_5__1__Impl_in_rule__ForType__Group_1_1_5__19176 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ForType__StepAssignment_1_1_5_1_in_rule__ForType__Group_1_1_5__1__Impl9203 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ForeachType__Group__0__Impl_in_rule__ForeachType__Group__09237 = new BitSet(new long[]{0x000010AB20000010L});
    public static final BitSet FOLLOW_rule__ForeachType__Group__1_in_rule__ForeachType__Group__09240 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_32_in_rule__ForeachType__Group__0__Impl9268 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ForeachType__Group__1__Impl_in_rule__ForeachType__Group__19299 = new BitSet(new long[]{0x000010AB20000010L});
    public static final BitSet FOLLOW_rule__ForeachType__Group__2_in_rule__ForeachType__Group__19302 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ForeachType__CodeAssignment_1_in_rule__ForeachType__Group__1__Impl9329 = new BitSet(new long[]{0x000010A920000012L});
    public static final BitSet FOLLOW_rule__ForeachType__Group__2__Impl_in_rule__ForeachType__Group__29360 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_rule__ForeachType__Group__3_in_rule__ForeachType__Group__29363 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_33_in_rule__ForeachType__Group__2__Impl9391 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ForeachType__Group__3__Impl_in_rule__ForeachType__Group__39422 = new BitSet(new long[]{0x000010A920000010L});
    public static final BitSet FOLLOW_rule__ForeachType__Group__4_in_rule__ForeachType__Group__39425 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_43_in_rule__ForeachType__Group__3__Impl9453 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ForeachType__Group__4__Impl_in_rule__ForeachType__Group__49484 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ForeachType__Alternatives_4_in_rule__ForeachType__Group__4__Impl9511 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SwitchType__Group__0__Impl_in_rule__SwitchType__Group__09551 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_rule__SwitchType__Group__1_in_rule__SwitchType__Group__09554 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_44_in_rule__SwitchType__Group__0__Impl9582 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SwitchType__Group__1__Impl_in_rule__SwitchType__Group__19613 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__SwitchType__Group__2_in_rule__SwitchType__Group__19616 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_30_in_rule__SwitchType__Group__1__Impl9644 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SwitchType__Group__2__Impl_in_rule__SwitchType__Group__29675 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_rule__SwitchType__Group__3_in_rule__SwitchType__Group__29678 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SwitchType__VarAssignment_2_in_rule__SwitchType__Group__2__Impl9705 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SwitchType__Group__3__Impl_in_rule__SwitchType__Group__39735 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_rule__SwitchType__Group__4_in_rule__SwitchType__Group__39738 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_25_in_rule__SwitchType__Group__3__Impl9766 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SwitchType__Group__4__Impl_in_rule__SwitchType__Group__49797 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_rule__SwitchType__Group__5_in_rule__SwitchType__Group__49800 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_38_in_rule__SwitchType__Group__4__Impl9828 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SwitchType__Group__5__Impl_in_rule__SwitchType__Group__59859 = new BitSet(new long[]{0x0000A00200000000L});
    public static final BitSet FOLLOW_rule__SwitchType__Group__6_in_rule__SwitchType__Group__59862 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_32_in_rule__SwitchType__Group__5__Impl9890 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SwitchType__Group__6__Impl_in_rule__SwitchType__Group__69921 = new BitSet(new long[]{0x0000A00200000000L});
    public static final BitSet FOLLOW_rule__SwitchType__Group__7_in_rule__SwitchType__Group__69924 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SwitchType__Group_6__0_in_rule__SwitchType__Group__6__Impl9951 = new BitSet(new long[]{0x0000200000000002L});
    public static final BitSet FOLLOW_rule__SwitchType__Group__7__Impl_in_rule__SwitchType__Group__79982 = new BitSet(new long[]{0x0000A00200000000L});
    public static final BitSet FOLLOW_rule__SwitchType__Group__8_in_rule__SwitchType__Group__79985 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SwitchType__Group_7__0_in_rule__SwitchType__Group__7__Impl10012 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SwitchType__Group__8__Impl_in_rule__SwitchType__Group__810043 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_33_in_rule__SwitchType__Group__8__Impl10071 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SwitchType__Group_6__0__Impl_in_rule__SwitchType__Group_6__010120 = new BitSet(new long[]{0x00000000007800D0L});
    public static final BitSet FOLLOW_rule__SwitchType__Group_6__1_in_rule__SwitchType__Group_6__010123 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_45_in_rule__SwitchType__Group_6__0__Impl10151 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SwitchType__Group_6__1__Impl_in_rule__SwitchType__Group_6__110182 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_rule__SwitchType__Group_6__2_in_rule__SwitchType__Group_6__110185 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SwitchType__ValueAssignment_6_1_in_rule__SwitchType__Group_6__1__Impl10212 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SwitchType__Group_6__2__Impl_in_rule__SwitchType__Group_6__210242 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_rule__SwitchType__Group_6__3_in_rule__SwitchType__Group_6__210245 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_46_in_rule__SwitchType__Group_6__2__Impl10273 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SwitchType__Group_6__3__Impl_in_rule__SwitchType__Group_6__310304 = new BitSet(new long[]{0x000010AB20000010L});
    public static final BitSet FOLLOW_rule__SwitchType__Group_6__4_in_rule__SwitchType__Group_6__310307 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_32_in_rule__SwitchType__Group_6__3__Impl10335 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SwitchType__Group_6__4__Impl_in_rule__SwitchType__Group_6__410366 = new BitSet(new long[]{0x000010AB20000010L});
    public static final BitSet FOLLOW_rule__SwitchType__Group_6__5_in_rule__SwitchType__Group_6__410369 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SwitchType__CaseCodeAssignment_6_4_in_rule__SwitchType__Group_6__4__Impl10396 = new BitSet(new long[]{0x000010A920000012L});
    public static final BitSet FOLLOW_rule__SwitchType__Group_6__5__Impl_in_rule__SwitchType__Group_6__510427 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_rule__SwitchType__Group_6__6_in_rule__SwitchType__Group_6__510430 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_33_in_rule__SwitchType__Group_6__5__Impl10458 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SwitchType__Group_6__6__Impl_in_rule__SwitchType__Group_6__610489 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_23_in_rule__SwitchType__Group_6__6__Impl10517 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SwitchType__Group_7__0__Impl_in_rule__SwitchType__Group_7__010562 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_rule__SwitchType__Group_7__1_in_rule__SwitchType__Group_7__010565 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_47_in_rule__SwitchType__Group_7__0__Impl10593 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SwitchType__Group_7__1__Impl_in_rule__SwitchType__Group_7__110624 = new BitSet(new long[]{0x000010AB20000010L});
    public static final BitSet FOLLOW_rule__SwitchType__Group_7__2_in_rule__SwitchType__Group_7__110627 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_32_in_rule__SwitchType__Group_7__1__Impl10655 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SwitchType__Group_7__2__Impl_in_rule__SwitchType__Group_7__210686 = new BitSet(new long[]{0x000010AB20000010L});
    public static final BitSet FOLLOW_rule__SwitchType__Group_7__3_in_rule__SwitchType__Group_7__210689 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SwitchType__DefaultCodeAssignment_7_2_in_rule__SwitchType__Group_7__2__Impl10716 = new BitSet(new long[]{0x000010A920000012L});
    public static final BitSet FOLLOW_rule__SwitchType__Group_7__3__Impl_in_rule__SwitchType__Group_7__310747 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_rule__SwitchType__Group_7__4_in_rule__SwitchType__Group_7__310750 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_33_in_rule__SwitchType__Group_7__3__Impl10778 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SwitchType__Group_7__4__Impl_in_rule__SwitchType__Group_7__410809 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_23_in_rule__SwitchType__Group_7__4__Impl10837 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Method__Group__0__Impl_in_rule__Method__Group__010878 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_rule__Method__Group__1_in_rule__Method__Group__010881 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Method__ParamsAssignment_0_in_rule__Method__Group__0__Impl10908 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Method__Group__1__Impl_in_rule__Method__Group__110938 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__Method__Group__2_in_rule__Method__Group__110941 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Method__ExecuteAssignment_1_in_rule__Method__Group__1__Impl10968 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Method__Group__2__Impl_in_rule__Method__Group__210998 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Method__MethodNameAssignment_2_in_rule__Method__Group__2__Impl11025 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCode_in_rule__Model__ElementsAssignment11066 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDeclaration_in_rule__Code__DecAssignment_0_011097 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleControlStructure_in_rule__Code__ControlAssignment_1_011128 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleMethod_in_rule__Code__MethodAssignment_2_011159 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__Declaration__NameAssignment_011190 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBracketContent_in_rule__Declaration__BrConAssignment_211221 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDecContent_in_rule__BracketContent__DecConAssignment_011252 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_COMPARATOR_in_rule__BracketContent__CompAssignment_1_011283 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDecContent_in_rule__BracketContent__ContentAssignment_1_111314 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__DecContent__NegAlternatives_0_0_in_rule__DecContent__NegAssignment_011345 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleVarContent_in_rule__DecContent__SingleContentAssignment_111378 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOPERATOR_in_rule__DecContent__OpAssignment_2_011409 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleVarContent_in_rule__DecContent__NextConAssignment_2_111440 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__VarContent__UnOPAlternatives_0_0_0_in_rule__VarContent__UnOPAssignment_0_011471 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_NUMBER_in_rule__VarContent__NumAssignment_0_111504 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__VarContent__StringAssignment_111535 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__VarContent__UnOPAlternatives_2_0_0_in_rule__VarContent__UnOPAssignment_2_011566 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__VarContent__ReferenceAssignment_2_1_011603 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_48_in_rule__VarContent__ForEachVarAssignment_2_1_111643 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_26_in_rule__VarContent__SelAssignment_2_2_011687 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_NUMBER_in_rule__VarContent__IndexAssignment_2_2_111726 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleArrayLiteral_in_rule__VarContent__ArrayContentAssignment_3_111757 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_49_in_rule__VarContent__ExecuteAssignment_3_2_011793 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleMethodName_in_rule__VarContent__MethodNameAssignment_3_2_111832 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__VarContent__UnOPAlternatives_4_0_0_in_rule__VarContent__UnOPAssignment_4_011863 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_30_in_rule__VarContent__EmbracedAssignment_4_111901 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBracketContent_in_rule__VarContent__EmbrConAssignment_4_211940 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBoolean_in_rule__VarContent__BoolAssignment_511971 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_50_in_rule__VarContent__ParamAssignment_6_012007 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_NUMBER_in_rule__VarContent__IndexAssignment_6_1_112046 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_35_in_rule__ArrayLiteral__ConAssignment_012082 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleVarContent_in_rule__ArrayLiteral__ContentAssignment_1_012121 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleVarContent_in_rule__ArrayLiteral__NextContentAssignment_1_1_112152 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleifType_in_rule__ControlStructure__IfStatAssignment_012183 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleWhileType_in_rule__ControlStructure__WhileStatAssignment_112214 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleForType_in_rule__ControlStructure__ForStatAssignment_212245 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleForeachType_in_rule__ControlStructure__ForEachStatAssignment_312276 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSwitchType_in_rule__ControlStructure__SwitchStatAssignment_412307 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBooleanContent_in_rule__IfType__ConditionAssignment_212338 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCode_in_rule__IfType__ThenCodeAssignment_4_0_1_0_112369 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCode_in_rule__IfType__ElseCodeAssignment_4_0_1_0_3_212400 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCode_in_rule__IfType__ThenCodeAssignment_4_0_1_1_212431 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCode_in_rule__IfType__ElseCodeAssignment_4_0_1_1_612462 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCode_in_rule__IfType__ExitCodeAssignment_4_1_212493 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBooleanContent_in_rule__WhileType__ConditionAssignment_212524 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCode_in_rule__WhileType__LoopCodeAssignment_612555 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDeclaration_in_rule__ForType__BeginAssignment_1_0_212586 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBooleanContent_in_rule__ForType__ConditionAssignment_1_0_612617 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDeclaration_in_rule__ForType__EndAssignment_1_0_1012648 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_NUMBER_in_rule__ForType__FromAssignment_1_1_212679 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_NUMBER_in_rule__ForType__ToAssignment_1_1_412710 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_NUMBER_in_rule__ForType__StepAssignment_1_1_5_112741 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCode_in_rule__ForType__LoopCodeAssignment_412772 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__ForVarDeclaration__NameAssignment12803 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCode_in_rule__ForeachType__CodeAssignment_112834 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__ForeachType__ArrayAssignment_4_012869 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleArrayLiteral_in_rule__ForeachType__ArrayLiteralAssignment_4_112904 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__SwitchType__VarAssignment_212939 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleANYTHING_in_rule__SwitchType__ValueAssignment_6_112974 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCode_in_rule__SwitchType__CaseCodeAssignment_6_413005 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCode_in_rule__SwitchType__DefaultCodeAssignment_7_213036 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleArrayLiteral_in_rule__Method__ParamsAssignment_013067 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_51_in_rule__Method__ExecuteAssignment_113103 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleMethodName_in_rule__Method__MethodNameAssignment_213142 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBoolean_in_rule__ANYTHING__BoolAssignment_013176 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_NUMBER_in_rule__ANYTHING__NumAssignment_113207 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__ANYTHING__StringAssignment_213238 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__ANYTHING__ReferenceAssignment_313273 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Boolean__BoolAlternatives_0_0_in_rule__Boolean__BoolAssignment_013308 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBoolCommand_in_rule__Boolean__CommandAssignment_113341 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBracketContent_in_rule__BooleanContent__BoolConAssignment13372 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__MethodName__RefAssignment13407 = new BitSet(new long[]{0x0000000000000002L});

}
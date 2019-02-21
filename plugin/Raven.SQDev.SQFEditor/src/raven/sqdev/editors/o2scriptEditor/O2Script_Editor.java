package raven.sqdev.editors.o2scriptEditor;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

import org.eclipse.ui.IEditorInput;

import raven.sqdev.constants.SQDevPreferenceConstants;
import raven.sqdev.editors.BasicSourceViewerConfiguration;
import raven.sqdev.editors.KeywordScanner;
import raven.sqdev.editors.sqfeditor.SQF_Editor;
import raven.sqdev.infoCollection.base.KeywordList;
import raven.sqdev.interfaces.IParseResult;

/**
 * An editor for O2Script scripts
 * 
 * @author Raven
 *
 */
public class O2Script_Editor extends SQF_Editor {

	public O2Script_Editor() {
		super();
	}

	@Override
	protected void setKeywords(BasicSourceViewerConfiguration configuration) {
		commandProvider = new O2ScriptKeywordProvider();
		KeywordScanner keywordScanner = configuration
				.createKeywordScanner(SQDevPreferenceConstants.SQDEV_EDITOR_KEYWORDHIGHLIGHTING_COLOR_KEY, false);
		keywordScanner.setKeywordProvider(commandProvider);
	}

	@Override
	protected void doPreprocessorParsing(InputStream input) {
		// disable preprocessing
	}

	@Override
	protected IParseResult doParse(InputStream input) {
		// disable parsing
		return null;
	}

	@Override
	protected void setupFunctionSupport(IEditorInput input) {
		// prevent function-set-up from SQF
	}

	public static void main(String[] args) throws IOException {
		String content = new String(Files.readAllBytes(new File(
				"/home/robert/Documents/Git/SQDev/plugin//Raven.SQDev.Misc/resources/o2script/O2ScriptKeywords.txt")
						.toPath()));

		KeywordList list = new KeywordList();
		if (!list.recreateFrom(content)) {
			System.err.println("Error!");
		}
		
		System.out.println(list.getFailures());
	}

}

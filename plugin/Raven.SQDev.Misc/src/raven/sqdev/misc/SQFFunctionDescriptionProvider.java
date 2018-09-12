package raven.sqdev.misc;

import java.io.IOException;
import java.io.InputStream;

import dataStructures.CharacterInputStream;
import dataStructures.ESQFOperatorType;
import dataStructures.ESQFTokentype;
import dataStructures.ICharacterBuffer;
import dataStructures.ITokenFactory;
import dataStructures.SQFToken;
import lexer.SQFLexer;
import raven.config.ConfigFunction;
import raven.sqdev.interfaces.IStreamProvider;

/**
 * A class that is able to get the description-comment out of a SQF-function
 * 
 * @author Raven
 *
 */
public class SQFFunctionDescriptionProvider {

	class DescriptionTokenFactory implements ITokenFactory<SQFToken> {
		ICharacterBuffer buffer;
		public String description;

		@Override
		public void setBuffer(ICharacterBuffer buffer) {
			this.buffer = buffer;
		}

		@Override
		public SQFToken produce(Object t, int start, int end, ICharacterBuffer buffer) {
			ESQFTokentype type = (ESQFTokentype) t;

			if (type == ESQFTokentype.COMMENT) {
				description = buffer.getText(start, end - start);
			}

			return new SQFToken(type, start, end, 0, ESQFOperatorType.NULAR, buffer);
		}

		@Override
		public SQFToken produce(Object type, int start, int end) {
			return produce(type, start, end, buffer);
		}
	}


	/**
	 * The {@linkplain StreamProvider} to use
	 */
	protected IStreamProvider provider;


	/**
	 * Creates a new instance of this class
	 * 
	 * @param provider
	 *            The {@linkplain IStreamProvider} that is responsible for returning
	 *            an {@linkplain InputStream} for the requested path
	 */
	public SQFFunctionDescriptionProvider(IStreamProvider provider) {
		this.provider = provider;
	}

	/**
	 * Gets the description of the SQF-function at the given path. The description
	 * is the first encountered comment in the SQF-file.
	 * 
	 * @param function
	 *            The {@linkplain ConfigFunction} to get the description for
	 * @return The found comment serving as a description or <code>null</code> if
	 *         there is none
	 * @throws IOException
	 */
	public String getDescription(ConfigFunction function) throws IOException {
		return getDescription(function.path);
	}

	/**
	 * Gets the description of the SQF-function at the given path. The description
	 * is the first encountered comment in the SQF-file. The path has to be relative
	 * to the mission folder or the game root.
	 * 
	 * @param path
	 *            The path of the respective function
	 * @return The found comment serving as a description or <code>null</code> if
	 *         there is none
	 * @throws IOException
	 */
	public String getDescription(String path) throws IOException {
		InputStream in = provider.getStreamFor(path);

		if (in == null) {
			return null;
		}

		DescriptionTokenFactory factory = new DescriptionTokenFactory();

		SQFLexer lexer = new SQFLexer();
		lexer.setTokenFactory(factory);
		lexer.lex(new CharacterInputStream(in) {
			@Override
			public boolean hasNext() throws IOException {
				// stop lexing as soon as the description has been found
				return factory.description == null && super.hasNext();
			}
		});

		return factory.description;
	}

}

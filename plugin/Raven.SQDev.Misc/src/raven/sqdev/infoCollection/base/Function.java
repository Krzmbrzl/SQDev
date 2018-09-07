package raven.sqdev.infoCollection.base;

import java.util.Arrays;

import org.eclipse.core.runtime.Path;

import raven.config.ConfigFunction;

public class Function extends Keyword {

	/**
	 * The path to the source file of this function (relative to mission/game root)
	 */
	protected Path path;
	/**
	 * The attributes set in CfgFunctions for this function
	 */
	protected String[] attributes;


	/**
	 * Creates a new instance of this class
	 * 
	 * @param name
	 *            The name of the function
	 */
	public Function(String name) {
		this(name, null, null, null);
	}

	/**
	 * Creates a new instance of this class
	 * 
	 * @param name
	 *            The name if the function
	 * @param description
	 *            The description of this function or <code>null</code> if there is
	 *            none
	 */
	public Function(String name, String description) {
		this(name, description, null, null);
	}

	/**
	 * Creates a new instance of this class
	 * 
	 * @param name
	 *            The name if the function
	 * @param description
	 *            The description of this function or <code>null</code> if there is
	 *            none
	 * @param path
	 *            The path to the source file of this function (relative to
	 *            mission/game root) or <code>null</code> if there is none
	 * @param attributes
	 *            The attributes set for this function in CfgFunctions or
	 *            <code>null</code> if there aren't any
	 */
	public Function(String name, String description, String path, String[] attributes) {
		super(name, description);

		this.path = path == null ? null : new Path(path);
		this.attributes = attributes;
	}

	@Override
	public String getSaveableFormat() {
		throw new IllegalAccessError("Not yet saveable!");
	}

	/**
	 * Creates a {@linkplain Function} out of the given {@linkplain ConfigFunction}
	 * 
	 * @param func
	 *            The {@linkplain ConfigFunction} to transform
	 * @return The created function
	 */
	public static Function from(ConfigFunction func) {
		return new Function(func.name, null, func.path, func.attributes);
	}

	/**
	 * Checks whether this function has a path to its source file attached to it
	 */
	public boolean hasSourcePath() {
		return path != null;
	}

	/**
	 * Gets the {@linkplain Path} to this function's source file or
	 * <code>null</code> if there is none
	 */
	public Path getSourcePath() {
		return path;
	}

	/**
	 * Checks whether this function has any attributes attached to it that are
	 * specified in the corresponding CfgFunctions
	 */
	public boolean hasAttributes() {
		return attributes != null && attributes.length > 0;
	}

	/**
	 * Gets the attributes attached to this function that are specified in the
	 * corresponding CfgFunctions
	 */
	public String[] getAttributes() {
		return Arrays.copyOf(attributes, attributes.length);
	}

}

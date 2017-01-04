package raven.sqdev.misc;

import raven.sqdev.interfaces.IReplaceTester;

/**
 * This enum contains all available data types in the SQF scripting language
 * 
 * @author Raven
 *
 */
public enum EDataType implements IReplaceTester {
	ANY_VALUE {
		@Override
		public String[] getStringRepresentations() {
			return new String[] { "Any_Value", "Any Value", "Any", "AnyValue" };
		}

		@Override
		public boolean canBeUsedAs(EDataType type) {
			return true;
		}
	},
	ANYTHING {
		@Override
		public String[] getStringRepresentations() {
			return new String[] { "Anything" };
		}

		@Override
		public boolean canBeUsedAs(EDataType type) {
			return true;
		}
	},
	ARRAY {
		@Override
		public String[] getStringRepresentations() {
			return new String[] { "Array" };
		}

		@Override
		public boolean canBeUsedAs(EDataType type) {
			return type.equals(POSITION);
		}
	},
	BOOLEAN {
		@Override
		public String[] getStringRepresentations() {
			return new String[] { "Boolean", "Bool" };
		}
	},
	CODE {
		@Override
		public String[] getStringRepresentations() {
			return new String[] { "Code" };
		}
	},
	CONFIG {
		@Override
		public String[] getStringRepresentations() {
			return new String[] { "Config" };
		}
	},
	CONTROL {
		@Override
		public String[] getStringRepresentations() {
			return new String[] { "Control" };
		}
	},
	DISPLAY {
		@Override
		public String[] getStringRepresentations() {
			return new String[] { "Display" };
		}
	},
	DIARY_RECORD {
		@Override
		public String[] getStringRepresentations() {
			return new String[] { "Diary_Record", "Diary Record", "Diary", "DiaryRecord" };
		}
	},
	EDITOR_OBJECT {
		@Override
		public String[] getStringRepresentations() {
			return new String[] { "Ediotor_Object", "Editor Object", "Editor", "EditorObject" };
		}
	},
	FOR_TYPE {
		@Override
		public String[] getStringRepresentations() {
			return new String[] { "For_Type", "For Type", "For", "ForType" };
		}
	},
	GROUP {
		@Override
		public String[] getStringRepresentations() {
			return new String[] { "Group" };
		}
	},
	IF_TYPE {
		@Override
		public String[] getStringRepresentations() {
			return new String[] { "If_Type", "If Type", "If", "IfType" };
		}
	},
	LOCATION {
		@Override
		public String[] getStringRepresentations() {
			return new String[] { "Location" };
		}
	},
	NAMESPACE {
		@Override
		public String[] getStringRepresentations() {
			return new String[] { "Namespace" };
		}
	},
	NOTHING {
		@Override
		public String[] getStringRepresentations() {
			return new String[] { "Nothing" };
		}
	},
	NUMBER {
		@Override
		public String[] getStringRepresentations() {
			return new String[] { "Number", "Scalar" };
		}
	},
	OBJECT {
		@Override
		public String[] getStringRepresentations() {
			return new String[] { "Object" };
		}
	},
	ORIENT {
		@Override
		public String[] getStringRepresentations() {
			return new String[] { "Orient" };
		}
	},
	POSITION {
		@Override
		public String[] getStringRepresentations() {
			return new String[] { "Position" };
		}

		@Override
		public boolean canBeUsedAs(EDataType type) {
			return type.equals(ARRAY);
		}
	},
	SCRIPT_HANDLE {
		@Override
		public String[] getStringRepresentations() {
			return new String[] { "Script_Handle", "Script Handle", "Script", "ScriptHandle" };
		}
	},
	SIDE {
		@Override
		public String[] getStringRepresentations() {
			return new String[] { "Side" };
		}
	},
	STRING {
		@Override
		public String[] getStringRepresentations() {
			return new String[] { "String" };
		}
	},
	STRUCTURED_TEXT {
		@Override
		public String[] getStringRepresentations() {
			return new String[] { "Structured_Text", "Structured Text", "Structured", "Text", "StructuredText" };
		}
	},
	SWITCH_TYPE {
		@Override
		public String[] getStringRepresentations() {
			return new String[] { "Switch_Type", "Switch Type", "Switch", "SwitchType" };
		}
	},
	TARGET {
		@Override
		public String[] getStringRepresentations() {
			return new String[] { "Target" };
		}
	},
	TASK {
		@Override
		public String[] getStringRepresentations() {
			return new String[] { "Task" };
		}
	},
	TEAM_MEMBER {
		@Override
		public String[] getStringRepresentations() {
			return new String[] { "Team_Member", "Team Member", "Team", "TeamMember" };
		}
	},
	TRANS {
		@Override
		public String[] getStringRepresentations() {
			return new String[] { "Trans" };
		}
	},
	VECTOR {
		@Override
		public String[] getStringRepresentations() {
			return new String[] { "Vector" };
		}
	},
	VOID {
		@Override
		public String[] getStringRepresentations() {
			return new String[] { "Void" };
		}
	},
	WHILE_TYPE {
		@Override
		public String[] getStringRepresentations() {
			return new String[] { "While_Type", "While Type", "While", "WhileType" };
		}
	};

	/**
	 * Gets all possible String represnetations of this data type
	 */
	public abstract String[] getStringRepresentations();

	/**
	 * Checks whether this data type can be used instead of the given data type
	 * 
	 * @param type
	 *            The data type to test this one against
	 */
	public boolean canBeUsedAs(EDataType type) {
		return type.equals(this) || type.equals(ANYTHING) || type.equals(ANY_VALUE);
	}

	/**
	 * Resolves the given String into a <code>EDataType</code> by matching it
	 * against all possible String representations of all available data types.
	 * The search is case-insensitive
	 * 
	 * @param str
	 *            The String to convert into a data type (Leading and trailing
	 *            Whitespace is ignored)
	 * @return The corresponding <code>EDataType</code> or <code>null</code> if
	 *         none could be found
	 */
	public static EDataType resolve(String str) {
		str = str.toLowerCase().trim();

		for (EDataType currentType : values()) {
			for (String currentRepresentation : currentType.getStringRepresentations()) {
				if (currentRepresentation.toLowerCase().equals(str)) {
					return currentType;
				}
			}
		}

		return null;
	}

	@Override
	public boolean canBeReplacedBy(Object obj) {
		if (obj == null || !(obj instanceof EDataType)) {
			return false;
		}

		return canBeUsedAs((EDataType) obj);
	}

	@Override
	public String toString() {
		return getStringRepresentations()[0];
	}
}

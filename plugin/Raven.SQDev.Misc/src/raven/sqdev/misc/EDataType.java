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
			if (super.canBeUsedAs(type)) {
				return true;
			}
			
			return type.equals(POSITION) || type.equals(POSITION2D)
					|| type.equals(EDataType.POSITION3D) || type.equals(POSITION_CONFIG)
					|| type.equals(POSITION_AGL) || type.equals(POSITION_AGLS)
					|| type.equals(POSITION_ASL) || type.equals(POSITION_ASLW)
					|| type.equals(POSITION_ATL) || type.equals(POSITION_RELATIVE)
					|| type.equals(EDataType.POSITION_WORLD) || type.equals(WAYPOINT);
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
	EDEN_ENTITY {
		@Override
		public String[] getStringRepresentations() {
			return new String[] { "EdenEntity", "Eden", "3denEntity", "3den" };
		}
	},
	EDITOR_OBJECT {
		@Override
		public String[] getStringRepresentations() {
			return new String[] { "Ediotor_Object", "Editor Object", "Editor", "EditorObject" };
		}
	},
	EXCEPTION {
		@Override
		public String[] getStringRepresentations() {
			return new String[] { "Exception", "ExceptionType" };
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
	NETOBJECT {
		@Override
		public String[] getStringRepresentations() {
			return new String[] { "NetObject", "Net" };
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
		
		@Override
		public boolean canBeUsedAs(EDataType type) {
			if (super.canBeUsedAs(type)) {
				return true;
			}
			
			return type.equals(NETOBJECT);
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
			if (super.canBeUsedAs(type)) {
				return true;
			}
			
			return type.equals(ARRAY) || type.equals(POSITION) || type.equals(POSITION2D)
					|| type.equals(EDataType.POSITION3D) || type.equals(POSITION_CONFIG)
					|| type.equals(POSITION_AGL) || type.equals(POSITION_AGLS)
					|| type.equals(POSITION_ASL) || type.equals(POSITION_ASLW)
					|| type.equals(POSITION_ATL) || type.equals(POSITION_RELATIVE)
					|| type.equals(EDataType.POSITION_WORLD);
		}
	},
	POSITION2D {
		@Override
		public String[] getStringRepresentations() {
			return new String[] { "Position2D", "2D" };
		}
		
		@Override
		public boolean canBeUsedAs(EDataType type) {
			if (super.canBeUsedAs(type)) {
				return true;
			}
			
			return type.equals(ARRAY) || type.equals(POSITION) || type.equals(EDataType.POSITION3D)
					|| type.equals(POSITION_CONFIG) || type.equals(POSITION_AGL)
					|| type.equals(POSITION_AGLS) || type.equals(POSITION_ASL)
					|| type.equals(POSITION_ASLW) || type.equals(POSITION_ATL)
					|| type.equals(POSITION_RELATIVE) || type.equals(EDataType.POSITION_WORLD);
		}
	},
	POSITION3D {
		@Override
		public String[] getStringRepresentations() {
			return new String[] { "Position3D", "3D" };
		}
		
		@Override
		public boolean canBeUsedAs(EDataType type) {
			if (super.canBeUsedAs(type)) {
				return true;
			}
			
			return type.equals(ARRAY) || type.equals(POSITION) || type.equals(EDataType.POSITION3D)
					|| type.equals(POSITION_CONFIG) || type.equals(POSITION_AGL)
					|| type.equals(POSITION_AGLS) || type.equals(POSITION_ASL)
					|| type.equals(POSITION_ASLW) || type.equals(POSITION_ATL)
					|| type.equals(POSITION_RELATIVE) || type.equals(EDataType.POSITION_WORLD);
		}
	},
	POSITION_CONFIG {
		@Override
		public String[] getStringRepresentations() {
			return new String[] { "PositionConfig" };
		}
		
		@Override
		public boolean canBeUsedAs(EDataType type) {
			if (super.canBeUsedAs(type)) {
				return true;
			}
			
			return type.equals(ARRAY) || type.equals(POSITION) || type.equals(EDataType.POSITION3D)
					|| type.equals(POSITION_AGL) || type.equals(POSITION_AGLS)
					|| type.equals(POSITION_ASL) || type.equals(POSITION_ASLW)
					|| type.equals(POSITION_ATL) || type.equals(POSITION_RELATIVE)
					|| type.equals(EDataType.POSITION_WORLD);
		}
	},
	POSITION_AGL {
		@Override
		public String[] getStringRepresentations() {
			return new String[] { "PositionAGL", "AGL" };
		}
		
		@Override
		public boolean canBeUsedAs(EDataType type) {
			if (super.canBeUsedAs(type)) {
				return true;
			}
			
			return type.equals(ARRAY) || type.equals(POSITION) || type.equals(EDataType.POSITION3D)
					|| type.equals(POSITION_CONFIG) || type.equals(POSITION_AGL)
					|| type.equals(POSITION_AGLS) || type.equals(POSITION_ASL)
					|| type.equals(POSITION_ASLW) || type.equals(POSITION_ATL)
					|| type.equals(POSITION_RELATIVE) || type.equals(EDataType.POSITION_WORLD);
		}
	},
	POSITION_AGLS {
		@Override
		public String[] getStringRepresentations() {
			return new String[] { "PositionAGLS", "AGLS" };
		}
		
		@Override
		public boolean canBeUsedAs(EDataType type) {
			if (super.canBeUsedAs(type)) {
				return true;
			}
			
			return type.equals(ARRAY) || type.equals(POSITION) || type.equals(EDataType.POSITION3D)
					|| type.equals(POSITION_CONFIG) || type.equals(POSITION_AGL)
					|| type.equals(POSITION_AGLS) || type.equals(POSITION_ASL)
					|| type.equals(POSITION_ASLW) || type.equals(POSITION_ATL)
					|| type.equals(POSITION_RELATIVE) || type.equals(EDataType.POSITION_WORLD);
		}
	},
	POSITION_ASL {
		@Override
		public String[] getStringRepresentations() {
			return new String[] { "PositionASL", "ASL" };
		}
		
		@Override
		public boolean canBeUsedAs(EDataType type) {
			if (super.canBeUsedAs(type)) {
				return true;
			}
			
			return type.equals(ARRAY) || type.equals(POSITION) || type.equals(EDataType.POSITION3D)
					|| type.equals(POSITION_CONFIG) || type.equals(POSITION_AGL)
					|| type.equals(POSITION_AGLS) || type.equals(POSITION_ASL)
					|| type.equals(POSITION_ASLW) || type.equals(POSITION_ATL)
					|| type.equals(POSITION_RELATIVE) || type.equals(EDataType.POSITION_WORLD);
		}
	},
	POSITION_ASLW {
		@Override
		public String[] getStringRepresentations() {
			return new String[] { "PositionASLW", "ASLW" };
		}
		
		@Override
		public boolean canBeUsedAs(EDataType type) {
			if (super.canBeUsedAs(type)) {
				return true;
			}
			
			return type.equals(ARRAY) || type.equals(POSITION) || type.equals(EDataType.POSITION3D)
					|| type.equals(POSITION_CONFIG) || type.equals(POSITION_AGL)
					|| type.equals(POSITION_AGLS) || type.equals(POSITION_ASL)
					|| type.equals(POSITION_ASLW) || type.equals(POSITION_ATL)
					|| type.equals(POSITION_RELATIVE) || type.equals(EDataType.POSITION_WORLD);
		}
	},
	POSITION_ATL {
		@Override
		public String[] getStringRepresentations() {
			return new String[] { "PositionATL", "ATL" };
		}
		
		@Override
		public boolean canBeUsedAs(EDataType type) {
			if (super.canBeUsedAs(type)) {
				return true;
			}
			
			return type.equals(ARRAY) || type.equals(POSITION) || type.equals(EDataType.POSITION3D)
					|| type.equals(POSITION_CONFIG) || type.equals(POSITION_AGL)
					|| type.equals(POSITION_AGLS) || type.equals(POSITION_ASL)
					|| type.equals(POSITION_ASLW) || type.equals(POSITION_ATL)
					|| type.equals(POSITION_RELATIVE) || type.equals(EDataType.POSITION_WORLD);
		}
	},
	POSITION_WORLD {
		@Override
		public String[] getStringRepresentations() {
			return new String[] { "PositionWorld", "World" };
		}
		
		@Override
		public boolean canBeUsedAs(EDataType type) {
			if (super.canBeUsedAs(type)) {
				return true;
			}
			
			return type.equals(ARRAY) || type.equals(POSITION) || type.equals(EDataType.POSITION3D)
					|| type.equals(POSITION_CONFIG) || type.equals(POSITION_AGL)
					|| type.equals(POSITION_AGLS) || type.equals(POSITION_ASL)
					|| type.equals(POSITION_ASLW) || type.equals(POSITION_ATL)
					|| type.equals(POSITION_RELATIVE) || type.equals(EDataType.POSITION_WORLD);
		}
	},
	POSITION_RELATIVE {
		@Override
		public String[] getStringRepresentations() {
			return new String[] { "PositionRelative", "Relative" };
		}
		
		@Override
		public boolean canBeUsedAs(EDataType type) {
			if (super.canBeUsedAs(type)) {
				return true;
			}
			
			return type.equals(ARRAY) || type.equals(POSITION) || type.equals(EDataType.POSITION3D)
					|| type.equals(POSITION_CONFIG) || type.equals(POSITION_AGL)
					|| type.equals(POSITION_AGLS) || type.equals(POSITION_ASL)
					|| type.equals(POSITION_ASLW) || type.equals(POSITION_ATL)
					|| type.equals(POSITION_RELATIVE) || type.equals(EDataType.POSITION_WORLD);
		}
	},
	SCRIPT_HANDLE {
		@Override
		public String[] getStringRepresentations() {
			return new String[] { "Script_Handle", "Script Handle", "Script", "ScriptHandle",
					"Script_" };
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
			return new String[] { "Structured_Text", "Structured Text", "Structured", "Text",
					"StructuredText" };
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
	UNKNOWN {
		@Override
		public String[] getStringRepresentations() {
			return new String[] { "Unknown" };
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
			return new String[] { "Void", "nil" };
		}
	},
	WAYPOINT {
		@Override
		public String[] getStringRepresentations() {
			return new String[] { "Waypoint" };
		}
		
	},
	WHILE_TYPE {
		@Override
		public String[] getStringRepresentations() {
			return new String[] { "While_Type", "While Type", "While", "WhileType" };
		}
	},
	WITH_TYPE {
		@Override
		public String[] getStringRepresentations() {
			return new String[] { "With_Type", "With Type", "With", "WithType" };
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
		
		if (obj.equals(this)) {
			return true;
		}
		
		return canBeUsedAs((EDataType) obj);
	}
	
	@Override
	public String toString() {
		return getStringRepresentations()[0];
	}
}

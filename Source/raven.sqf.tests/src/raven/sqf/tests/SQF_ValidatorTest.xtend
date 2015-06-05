package raven.sqf.tests

import org.junit.runner.RunWith
import org.eclipse.xtext.junit4.XtextRunner
import raven.SQFInjectorProvider
import org.eclipse.xtext.junit4.InjectWith
import org.junit.Test
import javax.inject.Inject
import org.eclipse.xtext.junit4.util.ParseHelper
import raven.sQF.Model
import org.eclipse.xtext.junit4.validation.ValidationTestHelper
import raven.sQF.SQFPackage
import raven.validation.SQFValidator

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(SQFInjectorProvider))

class SQF_ValidatorTest {
	@Inject extension ParseHelper<Model>
	@Inject extension ValidationTestHelper
	
	@Test
	def void checkUnaryOperatorAssignedToNumbersOnlyFirstTest() {
		'''
			test = "Test";
			test2 = -test;
		'''.parse.assertError(SQFPackage.eINSTANCE.decContent,
			SQFValidator.UNARYOPERATOR_TO_NUMBERS_ONLY,
			"Unary operator can only be applied to numbers")
			
		'''
			test = ["Miau",3];
			test2 = -test;
		'''.parse.assertError(SQFPackage.eINSTANCE.decContent,
			SQFValidator.UNARYOPERATOR_TO_ARRAY,
			"The unary operator '-' is not defined for type array")
		
		'''
			test = -4;
			test2 = -test + +test;
		'''.parse.assertNoErrors
	}
	
	@Test
	def void checkAllDeclarationsAreFromTheSameTypeTest() {
		'''
			var1 = "Mama";
			var2 = 3;
			var3 = var1 + var2;
		'''.parse.assertError(SQFPackage.eINSTANCE.decContent,
			SQFValidator.DECLARATIONS_ARE_SAME_TYPE,
			"Mismatch in type number -> expected string")
		
		'''
			var1 = 3;
			var2 = "Mama";
			var3 = var1 + var2;
		'''.parse.assertError(SQFPackage.eINSTANCE.decContent,
			SQFValidator.DECLARATIONS_ARE_SAME_TYPE,
			"Mismatch in type string -> expected number")
			
		'''
			var1 = [3];
			var2 = "Mama";
			var3 = var1 + var2;
		'''.parse.assertError(SQFPackage.eINSTANCE.decContent,
			SQFValidator.DECLARATIONS_ARE_SAME_TYPE,
			"Mismatch in type string -> expected array")
			
		'''
			var1 = 3;
			var2 = [4];
			var3 = var1 + var2;
		'''.parse.assertError(SQFPackage.eINSTANCE.decContent,
			SQFValidator.DECLARATIONS_ARE_SAME_TYPE,
			"Mismatch in type array -> expected number")
			
		'''
			var1 = (4*3);
			var2 = var1 + ("Test" + "Miau");
		'''.parse.assertError(SQFPackage.eINSTANCE.decContent,
			SQFValidator.DECLARATIONS_ARE_SAME_TYPE,
			"Mismatch in type string -> expected number")
	}
	
	@Test
	def void checkArraysAreOnlyAddedOrSubstractedTest() {
		'''
			var1 = [3] * [4];
		'''.parse.assertError(SQFPackage.eINSTANCE.decContent,
			SQFValidator.ARRAYS_ONLY_ADDED_OR_SUBSTRACTED,
			"The operator '*' is undefined for type array")
			
		'''
			var1 = [3] / [4];
		'''.parse.assertError(SQFPackage.eINSTANCE.decContent,
			SQFValidator.ARRAYS_ONLY_ADDED_OR_SUBSTRACTED,
			"The operator '/' is undefined for type array")
			
		'''
			a = [3];
			b = a + a;
			c = a - a;
		'''.parse.assertNoErrors
	}
	
	@Test
	def void checkStringsOnlyUsePlusOperatorTest() {
		'''
			test = "Miau" * "Miau";
		'''.parse.assertError(SQFPackage.eINSTANCE.decContent,
			SQFValidator.STRINGS_ONLY_PLUS_OPERATOR,
			"The operator '*' is not defined for type string")
			
		'''
			test = "Miau" - "Miau";
		'''.parse.assertError(SQFPackage.eINSTANCE.decContent,
			SQFValidator.STRINGS_ONLY_PLUS_OPERATOR,
			"The operator '-' is not defined for type string")
			
		'''
			test = "Miau" / "Miau";
		'''.parse.assertError(SQFPackage.eINSTANCE.decContent,
			SQFValidator.STRINGS_ONLY_PLUS_OPERATOR,
			"The operator '/' is not defined for type string")
	}
	
	@Test
	def void checkSelectIsUsedOnArraysOnlyTest() {
		'''
			test = "Miau";
			test2 = test select 0;
		'''.parse.assertError(SQFPackage.eINSTANCE.varContent,
			SQFValidator.SELECT_ONLY_FOR_ARRAYS,
			"The command 'select' is not applicable for type string")
			
		'''
			test = 3;
			test2 = test select 0;
		'''.parse.assertError(SQFPackage.eINSTANCE.varContent,
			SQFValidator.SELECT_ONLY_FOR_ARRAYS,
			"The command 'select' is not applicable for type number")
	}
	
/*	@Test
	def void checkDeclarationIsNotReferenceToItselfTest() {
		'''
			test = 3;
			test = test;
		'''.parse.assertWarning(SQFPackage.eINSTANCE.declaration,
			SQFValidator.SELFREFERENCE,
			"The declaration 'test' is a reference to itself")
	}*/
	
	@Test
	def void checkNoCycleInHierarchyTest() {
		'''
			test = test1;
			test1 = test;
		'''.parse.assertError(SQFPackage.eINSTANCE.declaration,
			SQFValidator.CYCLE_IN_HIERARCHY,
			"Cycle in hierarchy!")
			
		'''
			test = 3;
			test2 = test + 1;
			test3 = test2;
		'''.parse.assertNoErrors
	}
	
	@Test
	def void checkComparisonDoneOnlyBetweenSameTypes() {
		'''
			test1 = 2 < "Mama";
		'''.parse.assertError(SQFPackage.eINSTANCE.bracketContent,
			SQFValidator.COMPARISON_ONLY_WITH_SAME_TYPE,
			"Can't compare type number and string with comparator '<'")
			
		'''
			test2 = 3 < 4 == "Test";
		'''.parse.assertError(SQFPackage.eINSTANCE.bracketContent,
			SQFValidator.COMPARISON_ONLY_WITH_SAME_TYPE,
			"Can't compare type number and string with comparator '=='")
			
		val model= '''
			test1 = "Test";
			test2 = 3 == test1;
			test3 = test1 == 3;
		'''.parse
		
			model.assertError(SQFPackage.eINSTANCE.bracketContent,
			SQFValidator.COMPARISON_ONLY_WITH_SAME_TYPE,
			"Can't compare type number and string with comparator '=='")
			
			model.assertError(SQFPackage.eINSTANCE.bracketContent,
			SQFValidator.COMPARISON_ONLY_WITH_SAME_TYPE,
			"Can't compare type string and number with comparator '=='")
			
		'''
			test1 = "Mama";
			
			if(test1 == 3) then {
				
			};
		'''.parse.assertError(SQFPackage.eINSTANCE.bracketContent,
			SQFValidator.COMPARISON_ONLY_WITH_SAME_TYPE,
			"Can't compare type string and number with comparator '=='")
		
		
	}
	
	@Test
	def void checkBoolContentIsAlwaysBooleanTest() {
		'''
			if("Miau") then {};
		'''.parse.assertError(SQFPackage.eINSTANCE.booleanContent,
			SQFValidator.ONLY_BOOLEAN_ALLOWED,
			"Mismatch in type string -> expected boolean")
			
		'''
			if(7 + 4) exitWith{};
		'''.parse.assertError(SQFPackage.eINSTANCE.booleanContent,
			SQFValidator.ONLY_BOOLEAN_ALLOWED,
			"Mismatch in type number -> expected boolean")
	}
	
	@Test
	def void checkNegationOperatorOnlyUsedForBooleanExpressionsTest() {
		'''
			test = !"Mama";
		'''.parse.assertError(SQFPackage.eINSTANCE.decContent,
			SQFValidator.NEGATION_OPERATOR_ONLY_BOOLEAN,
			"The operator '!' is not defined for type string")
			
		'''
			test = !("Mama" isEqualTo "Test");
		'''.parse.assertNoErrors
		
		'''
			test1 = "Mama";
			test2 = "Test";
			test3 = "Mäh";
			
			test = !(test1 isEqualTo (test2 + test3));
		'''.parse.assertNoErrors
	}
	
	@Test
	def void checkForEachOnlyUsesArrayTest() {
		'''
			var = 3;
			{
				
			} forEach var;
		'''.parse.assertError(SQFPackage.eINSTANCE.foreachType,
			SQFValidator.FOREACH_ONLY_ARRAY,
			"ForEach can only process arrays")
			
		'''
			var = [3];
			{
				
			} forEach var;
		'''.parse.assertNoErrors
		
		'''
			{
				
			} forEach ["amam",3,"mama"];
		'''.parse.assertNoErrors
	}
}
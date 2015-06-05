package raven.sqf.tests;

import javax.inject.Inject;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.junit4.InjectWith;
import org.eclipse.xtext.junit4.XtextRunner;
import org.eclipse.xtext.junit4.util.ParseHelper;
import org.eclipse.xtext.junit4.validation.ValidationTestHelper;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.junit.Test;
import org.junit.runner.RunWith;
import raven.SQFInjectorProvider;
import raven.sQF.Model;
import raven.sQF.SQFPackage;
import raven.validation.SQFValidator;

@RunWith(XtextRunner.class)
@InjectWith(SQFInjectorProvider.class)
@SuppressWarnings("all")
public class SQF_ValidatorTest {
  @Inject
  @Extension
  private ParseHelper<Model> _parseHelper;
  
  @Inject
  @Extension
  private ValidationTestHelper _validationTestHelper;
  
  @Test
  public void checkUnaryOperatorAssignedToNumbersOnlyFirstTest() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("test = \"Test\";");
      _builder.newLine();
      _builder.append("test2 = -test;");
      _builder.newLine();
      Model _parse = this._parseHelper.parse(_builder);
      EClass _decContent = SQFPackage.eINSTANCE.getDecContent();
      this._validationTestHelper.assertError(_parse, _decContent, 
        SQFValidator.UNARYOPERATOR_TO_NUMBERS_ONLY, 
        "Unary operator can only be applied to numbers");
      StringConcatenation _builder_1 = new StringConcatenation();
      _builder_1.append("test = [\"Miau\",3];");
      _builder_1.newLine();
      _builder_1.append("test2 = -test;");
      _builder_1.newLine();
      Model _parse_1 = this._parseHelper.parse(_builder_1);
      EClass _decContent_1 = SQFPackage.eINSTANCE.getDecContent();
      this._validationTestHelper.assertError(_parse_1, _decContent_1, 
        SQFValidator.UNARYOPERATOR_TO_ARRAY, 
        "The unary operator \'-\' is not defined for type array");
      StringConcatenation _builder_2 = new StringConcatenation();
      _builder_2.append("test = -4;");
      _builder_2.newLine();
      _builder_2.append("test2 = -test + +test;");
      _builder_2.newLine();
      Model _parse_2 = this._parseHelper.parse(_builder_2);
      this._validationTestHelper.assertNoErrors(_parse_2);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void checkAllDeclarationsAreFromTheSameTypeTest() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("var1 = \"Mama\";");
      _builder.newLine();
      _builder.append("var2 = 3;");
      _builder.newLine();
      _builder.append("var3 = var1 + var2;");
      _builder.newLine();
      Model _parse = this._parseHelper.parse(_builder);
      EClass _decContent = SQFPackage.eINSTANCE.getDecContent();
      this._validationTestHelper.assertError(_parse, _decContent, 
        SQFValidator.DECLARATIONS_ARE_SAME_TYPE, 
        "Mismatch in type number -> expected string");
      StringConcatenation _builder_1 = new StringConcatenation();
      _builder_1.append("var1 = 3;");
      _builder_1.newLine();
      _builder_1.append("var2 = \"Mama\";");
      _builder_1.newLine();
      _builder_1.append("var3 = var1 + var2;");
      _builder_1.newLine();
      Model _parse_1 = this._parseHelper.parse(_builder_1);
      EClass _decContent_1 = SQFPackage.eINSTANCE.getDecContent();
      this._validationTestHelper.assertError(_parse_1, _decContent_1, 
        SQFValidator.DECLARATIONS_ARE_SAME_TYPE, 
        "Mismatch in type string -> expected number");
      StringConcatenation _builder_2 = new StringConcatenation();
      _builder_2.append("var1 = [3];");
      _builder_2.newLine();
      _builder_2.append("var2 = \"Mama\";");
      _builder_2.newLine();
      _builder_2.append("var3 = var1 + var2;");
      _builder_2.newLine();
      Model _parse_2 = this._parseHelper.parse(_builder_2);
      EClass _decContent_2 = SQFPackage.eINSTANCE.getDecContent();
      this._validationTestHelper.assertError(_parse_2, _decContent_2, 
        SQFValidator.DECLARATIONS_ARE_SAME_TYPE, 
        "Mismatch in type string -> expected array");
      StringConcatenation _builder_3 = new StringConcatenation();
      _builder_3.append("var1 = 3;");
      _builder_3.newLine();
      _builder_3.append("var2 = [4];");
      _builder_3.newLine();
      _builder_3.append("var3 = var1 + var2;");
      _builder_3.newLine();
      Model _parse_3 = this._parseHelper.parse(_builder_3);
      EClass _decContent_3 = SQFPackage.eINSTANCE.getDecContent();
      this._validationTestHelper.assertError(_parse_3, _decContent_3, 
        SQFValidator.DECLARATIONS_ARE_SAME_TYPE, 
        "Mismatch in type array -> expected number");
      StringConcatenation _builder_4 = new StringConcatenation();
      _builder_4.append("var1 = (4*3);");
      _builder_4.newLine();
      _builder_4.append("var2 = var1 + (\"Test\" + \"Miau\");");
      _builder_4.newLine();
      Model _parse_4 = this._parseHelper.parse(_builder_4);
      EClass _decContent_4 = SQFPackage.eINSTANCE.getDecContent();
      this._validationTestHelper.assertError(_parse_4, _decContent_4, 
        SQFValidator.DECLARATIONS_ARE_SAME_TYPE, 
        "Mismatch in type string -> expected number");
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void checkArraysAreOnlyAddedOrSubstractedTest() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("var1 = [3] * [4];");
      _builder.newLine();
      Model _parse = this._parseHelper.parse(_builder);
      EClass _decContent = SQFPackage.eINSTANCE.getDecContent();
      this._validationTestHelper.assertError(_parse, _decContent, 
        SQFValidator.ARRAYS_ONLY_ADDED_OR_SUBSTRACTED, 
        "The operator \'*\' is undefined for type array");
      StringConcatenation _builder_1 = new StringConcatenation();
      _builder_1.append("var1 = [3] / [4];");
      _builder_1.newLine();
      Model _parse_1 = this._parseHelper.parse(_builder_1);
      EClass _decContent_1 = SQFPackage.eINSTANCE.getDecContent();
      this._validationTestHelper.assertError(_parse_1, _decContent_1, 
        SQFValidator.ARRAYS_ONLY_ADDED_OR_SUBSTRACTED, 
        "The operator \'/\' is undefined for type array");
      StringConcatenation _builder_2 = new StringConcatenation();
      _builder_2.append("a = [3];");
      _builder_2.newLine();
      _builder_2.append("b = a + a;");
      _builder_2.newLine();
      _builder_2.append("c = a - a;");
      _builder_2.newLine();
      Model _parse_2 = this._parseHelper.parse(_builder_2);
      this._validationTestHelper.assertNoErrors(_parse_2);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void checkStringsOnlyUsePlusOperatorTest() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("test = \"Miau\" * \"Miau\";");
      _builder.newLine();
      Model _parse = this._parseHelper.parse(_builder);
      EClass _decContent = SQFPackage.eINSTANCE.getDecContent();
      this._validationTestHelper.assertError(_parse, _decContent, 
        SQFValidator.STRINGS_ONLY_PLUS_OPERATOR, 
        "The operator \'*\' is not defined for type string");
      StringConcatenation _builder_1 = new StringConcatenation();
      _builder_1.append("test = \"Miau\" - \"Miau\";");
      _builder_1.newLine();
      Model _parse_1 = this._parseHelper.parse(_builder_1);
      EClass _decContent_1 = SQFPackage.eINSTANCE.getDecContent();
      this._validationTestHelper.assertError(_parse_1, _decContent_1, 
        SQFValidator.STRINGS_ONLY_PLUS_OPERATOR, 
        "The operator \'-\' is not defined for type string");
      StringConcatenation _builder_2 = new StringConcatenation();
      _builder_2.append("test = \"Miau\" / \"Miau\";");
      _builder_2.newLine();
      Model _parse_2 = this._parseHelper.parse(_builder_2);
      EClass _decContent_2 = SQFPackage.eINSTANCE.getDecContent();
      this._validationTestHelper.assertError(_parse_2, _decContent_2, 
        SQFValidator.STRINGS_ONLY_PLUS_OPERATOR, 
        "The operator \'/\' is not defined for type string");
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void checkSelectIsUsedOnArraysOnlyTest() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("test = \"Miau\";");
      _builder.newLine();
      _builder.append("test2 = test select 0;");
      _builder.newLine();
      Model _parse = this._parseHelper.parse(_builder);
      EClass _varContent = SQFPackage.eINSTANCE.getVarContent();
      this._validationTestHelper.assertError(_parse, _varContent, 
        SQFValidator.SELECT_ONLY_FOR_ARRAYS, 
        "The command \'select\' is not applicable for type string");
      StringConcatenation _builder_1 = new StringConcatenation();
      _builder_1.append("test = 3;");
      _builder_1.newLine();
      _builder_1.append("test2 = test select 0;");
      _builder_1.newLine();
      Model _parse_1 = this._parseHelper.parse(_builder_1);
      EClass _varContent_1 = SQFPackage.eINSTANCE.getVarContent();
      this._validationTestHelper.assertError(_parse_1, _varContent_1, 
        SQFValidator.SELECT_ONLY_FOR_ARRAYS, 
        "The command \'select\' is not applicable for type number");
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  /**
   * @Test
   * def void checkDeclarationIsNotReferenceToItselfTest() {
   * '''
   * test = 3;
   * test = test;
   * '''.parse.assertWarning(SQFPackage.eINSTANCE.declaration,
   * SQFValidator.SELFREFERENCE,
   * "The declaration 'test' is a reference to itself")
   * }
   */
  @Test
  public void checkNoCycleInHierarchyTest() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("test = test1;");
      _builder.newLine();
      _builder.append("test1 = test;");
      _builder.newLine();
      Model _parse = this._parseHelper.parse(_builder);
      EClass _declaration = SQFPackage.eINSTANCE.getDeclaration();
      this._validationTestHelper.assertError(_parse, _declaration, 
        SQFValidator.CYCLE_IN_HIERARCHY, 
        "Cycle in hierarchy!");
      StringConcatenation _builder_1 = new StringConcatenation();
      _builder_1.append("test = 3;");
      _builder_1.newLine();
      _builder_1.append("test2 = test + 1;");
      _builder_1.newLine();
      _builder_1.append("test3 = test2;");
      _builder_1.newLine();
      Model _parse_1 = this._parseHelper.parse(_builder_1);
      this._validationTestHelper.assertNoErrors(_parse_1);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void checkComparisonDoneOnlyBetweenSameTypes() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("test1 = 2 < \"Mama\";");
      _builder.newLine();
      Model _parse = this._parseHelper.parse(_builder);
      EClass _bracketContent = SQFPackage.eINSTANCE.getBracketContent();
      this._validationTestHelper.assertError(_parse, _bracketContent, 
        SQFValidator.COMPARISON_ONLY_WITH_SAME_TYPE, 
        "Can\'t compare type number and string with comparator \'<\'");
      StringConcatenation _builder_1 = new StringConcatenation();
      _builder_1.append("test2 = 3 < 4 == \"Test\";");
      _builder_1.newLine();
      Model _parse_1 = this._parseHelper.parse(_builder_1);
      EClass _bracketContent_1 = SQFPackage.eINSTANCE.getBracketContent();
      this._validationTestHelper.assertError(_parse_1, _bracketContent_1, 
        SQFValidator.COMPARISON_ONLY_WITH_SAME_TYPE, 
        "Can\'t compare type number and string with comparator \'==\'");
      StringConcatenation _builder_2 = new StringConcatenation();
      _builder_2.append("test1 = \"Test\";");
      _builder_2.newLine();
      _builder_2.append("test2 = 3 == test1;");
      _builder_2.newLine();
      _builder_2.append("test3 = test1 == 3;");
      _builder_2.newLine();
      final Model model = this._parseHelper.parse(_builder_2);
      EClass _bracketContent_2 = SQFPackage.eINSTANCE.getBracketContent();
      this._validationTestHelper.assertError(model, _bracketContent_2, 
        SQFValidator.COMPARISON_ONLY_WITH_SAME_TYPE, 
        "Can\'t compare type number and string with comparator \'==\'");
      EClass _bracketContent_3 = SQFPackage.eINSTANCE.getBracketContent();
      this._validationTestHelper.assertError(model, _bracketContent_3, 
        SQFValidator.COMPARISON_ONLY_WITH_SAME_TYPE, 
        "Can\'t compare type string and number with comparator \'==\'");
      StringConcatenation _builder_3 = new StringConcatenation();
      _builder_3.append("test1 = \"Mama\";");
      _builder_3.newLine();
      _builder_3.newLine();
      _builder_3.append("if(test1 == 3) then {");
      _builder_3.newLine();
      _builder_3.append("\t");
      _builder_3.newLine();
      _builder_3.append("};");
      _builder_3.newLine();
      Model _parse_2 = this._parseHelper.parse(_builder_3);
      EClass _bracketContent_4 = SQFPackage.eINSTANCE.getBracketContent();
      this._validationTestHelper.assertError(_parse_2, _bracketContent_4, 
        SQFValidator.COMPARISON_ONLY_WITH_SAME_TYPE, 
        "Can\'t compare type string and number with comparator \'==\'");
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void checkBoolContentIsAlwaysBooleanTest() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("if(\"Miau\") then {};");
      _builder.newLine();
      Model _parse = this._parseHelper.parse(_builder);
      EClass _booleanContent = SQFPackage.eINSTANCE.getBooleanContent();
      this._validationTestHelper.assertError(_parse, _booleanContent, 
        SQFValidator.ONLY_BOOLEAN_ALLOWED, 
        "Mismatch in type string -> expected boolean");
      StringConcatenation _builder_1 = new StringConcatenation();
      _builder_1.append("if(7 + 4) exitWith{};");
      _builder_1.newLine();
      Model _parse_1 = this._parseHelper.parse(_builder_1);
      EClass _booleanContent_1 = SQFPackage.eINSTANCE.getBooleanContent();
      this._validationTestHelper.assertError(_parse_1, _booleanContent_1, 
        SQFValidator.ONLY_BOOLEAN_ALLOWED, 
        "Mismatch in type number -> expected boolean");
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void checkNegationOperatorOnlyUsedForBooleanExpressionsTest() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("test = !\"Mama\";");
      _builder.newLine();
      Model _parse = this._parseHelper.parse(_builder);
      EClass _decContent = SQFPackage.eINSTANCE.getDecContent();
      this._validationTestHelper.assertError(_parse, _decContent, 
        SQFValidator.NEGATION_OPERATOR_ONLY_BOOLEAN, 
        "The operator \'!\' is not defined for type string");
      StringConcatenation _builder_1 = new StringConcatenation();
      _builder_1.append("test = !(\"Mama\" isEqualTo \"Test\");");
      _builder_1.newLine();
      Model _parse_1 = this._parseHelper.parse(_builder_1);
      this._validationTestHelper.assertNoErrors(_parse_1);
      StringConcatenation _builder_2 = new StringConcatenation();
      _builder_2.append("test1 = \"Mama\";");
      _builder_2.newLine();
      _builder_2.append("test2 = \"Test\";");
      _builder_2.newLine();
      _builder_2.append("test3 = \"Mäh\";");
      _builder_2.newLine();
      _builder_2.newLine();
      _builder_2.append("test = !(test1 isEqualTo (test2 + test3));");
      _builder_2.newLine();
      Model _parse_2 = this._parseHelper.parse(_builder_2);
      this._validationTestHelper.assertNoErrors(_parse_2);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void checkForEachOnlyUsesArrayTest() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("var = 3;");
      _builder.newLine();
      _builder.append("{");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("} forEach var;");
      _builder.newLine();
      Model _parse = this._parseHelper.parse(_builder);
      EClass _foreachType = SQFPackage.eINSTANCE.getForeachType();
      this._validationTestHelper.assertError(_parse, _foreachType, 
        SQFValidator.FOREACH_ONLY_ARRAY, 
        "ForEach can only process arrays");
      StringConcatenation _builder_1 = new StringConcatenation();
      _builder_1.append("var = [3];");
      _builder_1.newLine();
      _builder_1.append("{");
      _builder_1.newLine();
      _builder_1.append("\t");
      _builder_1.newLine();
      _builder_1.append("} forEach var;");
      _builder_1.newLine();
      Model _parse_1 = this._parseHelper.parse(_builder_1);
      this._validationTestHelper.assertNoErrors(_parse_1);
      StringConcatenation _builder_2 = new StringConcatenation();
      _builder_2.append("{");
      _builder_2.newLine();
      _builder_2.append("\t");
      _builder_2.newLine();
      _builder_2.append("} forEach [\"amam\",3,\"mama\"];");
      _builder_2.newLine();
      Model _parse_2 = this._parseHelper.parse(_builder_2);
      this._validationTestHelper.assertNoErrors(_parse_2);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}

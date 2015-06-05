/**
 */
package raven.sQF.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

import raven.sQF.ANYTHING;
import raven.sQF.ArrayLiteral;
import raven.sQF.BooleanContent;
import raven.sQF.BracketContent;
import raven.sQF.Code;
import raven.sQF.ControlStructure;
import raven.sQF.DecContent;
import raven.sQF.Declaration;
import raven.sQF.ForType;
import raven.sQF.ForeachType;
import raven.sQF.Method;
import raven.sQF.MethodName;
import raven.sQF.Model;
import raven.sQF.SQFPackage;
import raven.sQF.SwitchType;
import raven.sQF.VarContent;
import raven.sQF.WhileType;
import raven.sQF.forVarDeclaration;
import raven.sQF.ifType;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see raven.sQF.SQFPackage
 * @generated
 */
public class SQFSwitch<T> extends Switch<T>
{
  /**
   * The cached model package
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected static SQFPackage modelPackage;

  /**
   * Creates an instance of the switch.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SQFSwitch()
  {
    if (modelPackage == null)
    {
      modelPackage = SQFPackage.eINSTANCE;
    }
  }

  /**
   * Checks whether this is a switch for the given package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @parameter ePackage the package in question.
   * @return whether this is a switch for the given package.
   * @generated
   */
  @Override
  protected boolean isSwitchFor(EPackage ePackage)
  {
    return ePackage == modelPackage;
  }

  /**
   * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the first non-null result returned by a <code>caseXXX</code> call.
   * @generated
   */
  @Override
  protected T doSwitch(int classifierID, EObject theEObject)
  {
    switch (classifierID)
    {
      case SQFPackage.MODEL:
      {
        Model model = (Model)theEObject;
        T result = caseModel(model);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SQFPackage.CODE:
      {
        Code code = (Code)theEObject;
        T result = caseCode(code);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SQFPackage.DECLARATION:
      {
        Declaration declaration = (Declaration)theEObject;
        T result = caseDeclaration(declaration);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SQFPackage.BRACKET_CONTENT:
      {
        BracketContent bracketContent = (BracketContent)theEObject;
        T result = caseBracketContent(bracketContent);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SQFPackage.DEC_CONTENT:
      {
        DecContent decContent = (DecContent)theEObject;
        T result = caseDecContent(decContent);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SQFPackage.VAR_CONTENT:
      {
        VarContent varContent = (VarContent)theEObject;
        T result = caseVarContent(varContent);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SQFPackage.ARRAY_LITERAL:
      {
        ArrayLiteral arrayLiteral = (ArrayLiteral)theEObject;
        T result = caseArrayLiteral(arrayLiteral);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SQFPackage.CONTROL_STRUCTURE:
      {
        ControlStructure controlStructure = (ControlStructure)theEObject;
        T result = caseControlStructure(controlStructure);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SQFPackage.IF_TYPE:
      {
        ifType ifType = (ifType)theEObject;
        T result = caseifType(ifType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SQFPackage.WHILE_TYPE:
      {
        WhileType whileType = (WhileType)theEObject;
        T result = caseWhileType(whileType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SQFPackage.FOR_TYPE:
      {
        ForType forType = (ForType)theEObject;
        T result = caseForType(forType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SQFPackage.FOR_VAR_DECLARATION:
      {
        forVarDeclaration forVarDeclaration = (forVarDeclaration)theEObject;
        T result = caseforVarDeclaration(forVarDeclaration);
        if (result == null) result = caseForType(forVarDeclaration);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SQFPackage.FOREACH_TYPE:
      {
        ForeachType foreachType = (ForeachType)theEObject;
        T result = caseForeachType(foreachType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SQFPackage.SWITCH_TYPE:
      {
        SwitchType switchType = (SwitchType)theEObject;
        T result = caseSwitchType(switchType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SQFPackage.METHOD:
      {
        Method method = (Method)theEObject;
        T result = caseMethod(method);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SQFPackage.OBJECT:
      {
        raven.sQF.Object object = (raven.sQF.Object)theEObject;
        T result = caseObject(object);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SQFPackage.ANYTHING:
      {
        ANYTHING anything = (ANYTHING)theEObject;
        T result = caseANYTHING(anything);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SQFPackage.BOOLEAN:
      {
        raven.sQF.Boolean boolean_ = (raven.sQF.Boolean)theEObject;
        T result = caseBoolean(boolean_);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SQFPackage.BOOLEAN_CONTENT:
      {
        BooleanContent booleanContent = (BooleanContent)theEObject;
        T result = caseBooleanContent(booleanContent);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SQFPackage.METHOD_NAME:
      {
        MethodName methodName = (MethodName)theEObject;
        T result = caseMethodName(methodName);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      default: return defaultCase(theEObject);
    }
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Model</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Model</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseModel(Model object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Code</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Code</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseCode(Code object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Declaration</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Declaration</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseDeclaration(Declaration object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Bracket Content</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Bracket Content</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseBracketContent(BracketContent object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Dec Content</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Dec Content</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseDecContent(DecContent object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Var Content</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Var Content</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseVarContent(VarContent object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Array Literal</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Array Literal</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseArrayLiteral(ArrayLiteral object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Control Structure</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Control Structure</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseControlStructure(ControlStructure object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>if Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>if Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseifType(ifType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>While Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>While Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseWhileType(WhileType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>For Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>For Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseForType(ForType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>for Var Declaration</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>for Var Declaration</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseforVarDeclaration(forVarDeclaration object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Foreach Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Foreach Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseForeachType(ForeachType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Switch Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Switch Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseSwitchType(SwitchType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Method</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Method</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseMethod(Method object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Object</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Object</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseObject(raven.sQF.Object object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>ANYTHING</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>ANYTHING</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseANYTHING(ANYTHING object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Boolean</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Boolean</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseBoolean(raven.sQF.Boolean object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Boolean Content</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Boolean Content</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseBooleanContent(BooleanContent object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Method Name</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Method Name</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseMethodName(MethodName object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch, but this is the last case anyway.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject)
   * @generated
   */
  @Override
  public T defaultCase(EObject object)
  {
    return null;
  }

} //SQFSwitch

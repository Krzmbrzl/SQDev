/**
 */
package raven.sQF;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see raven.sQF.SQFPackage
 * @generated
 */
public interface SQFFactory extends EFactory
{
  /**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  SQFFactory eINSTANCE = raven.sQF.impl.SQFFactoryImpl.init();

  /**
   * Returns a new object of class '<em>Model</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Model</em>'.
   * @generated
   */
  Model createModel();

  /**
   * Returns a new object of class '<em>Code</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Code</em>'.
   * @generated
   */
  Code createCode();

  /**
   * Returns a new object of class '<em>Declaration</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Declaration</em>'.
   * @generated
   */
  Declaration createDeclaration();

  /**
   * Returns a new object of class '<em>Bracket Content</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Bracket Content</em>'.
   * @generated
   */
  BracketContent createBracketContent();

  /**
   * Returns a new object of class '<em>Dec Content</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Dec Content</em>'.
   * @generated
   */
  DecContent createDecContent();

  /**
   * Returns a new object of class '<em>Var Content</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Var Content</em>'.
   * @generated
   */
  VarContent createVarContent();

  /**
   * Returns a new object of class '<em>Array Literal</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Array Literal</em>'.
   * @generated
   */
  ArrayLiteral createArrayLiteral();

  /**
   * Returns a new object of class '<em>Control Structure</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Control Structure</em>'.
   * @generated
   */
  ControlStructure createControlStructure();

  /**
   * Returns a new object of class '<em>if Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>if Type</em>'.
   * @generated
   */
  ifType createifType();

  /**
   * Returns a new object of class '<em>While Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>While Type</em>'.
   * @generated
   */
  WhileType createWhileType();

  /**
   * Returns a new object of class '<em>For Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>For Type</em>'.
   * @generated
   */
  ForType createForType();

  /**
   * Returns a new object of class '<em>for Var Declaration</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>for Var Declaration</em>'.
   * @generated
   */
  forVarDeclaration createforVarDeclaration();

  /**
   * Returns a new object of class '<em>Foreach Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Foreach Type</em>'.
   * @generated
   */
  ForeachType createForeachType();

  /**
   * Returns a new object of class '<em>Switch Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Switch Type</em>'.
   * @generated
   */
  SwitchType createSwitchType();

  /**
   * Returns a new object of class '<em>Method</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Method</em>'.
   * @generated
   */
  Method createMethod();

  /**
   * Returns a new object of class '<em>Object</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Object</em>'.
   * @generated
   */
  Object createObject();

  /**
   * Returns a new object of class '<em>ANYTHING</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>ANYTHING</em>'.
   * @generated
   */
  ANYTHING createANYTHING();

  /**
   * Returns a new object of class '<em>Boolean</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Boolean</em>'.
   * @generated
   */
  Boolean createBoolean();

  /**
   * Returns a new object of class '<em>Boolean Content</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Boolean Content</em>'.
   * @generated
   */
  BooleanContent createBooleanContent();

  /**
   * Returns a new object of class '<em>Method Name</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Method Name</em>'.
   * @generated
   */
  MethodName createMethodName();

  /**
   * Returns the package supported by this factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the package supported by this factory.
   * @generated
   */
  SQFPackage getSQFPackage();

} //SQFFactory

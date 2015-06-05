/**
 */
package raven.sQF;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see raven.sQF.SQFFactory
 * @model kind="package"
 * @generated
 */
public interface SQFPackage extends EPackage
{
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNAME = "sQF";

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_URI = "http://www.SQF.raven";

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_PREFIX = "sQF";

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  SQFPackage eINSTANCE = raven.sQF.impl.SQFPackageImpl.init();

  /**
   * The meta object id for the '{@link raven.sQF.impl.ModelImpl <em>Model</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see raven.sQF.impl.ModelImpl
   * @see raven.sQF.impl.SQFPackageImpl#getModel()
   * @generated
   */
  int MODEL = 0;

  /**
   * The feature id for the '<em><b>Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODEL__ELEMENTS = 0;

  /**
   * The number of structural features of the '<em>Model</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODEL_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link raven.sQF.impl.CodeImpl <em>Code</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see raven.sQF.impl.CodeImpl
   * @see raven.sQF.impl.SQFPackageImpl#getCode()
   * @generated
   */
  int CODE = 1;

  /**
   * The feature id for the '<em><b>Dec</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CODE__DEC = 0;

  /**
   * The feature id for the '<em><b>Control</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CODE__CONTROL = 1;

  /**
   * The feature id for the '<em><b>Method</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CODE__METHOD = 2;

  /**
   * The number of structural features of the '<em>Code</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CODE_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link raven.sQF.impl.DeclarationImpl <em>Declaration</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see raven.sQF.impl.DeclarationImpl
   * @see raven.sQF.impl.SQFPackageImpl#getDeclaration()
   * @generated
   */
  int DECLARATION = 2;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DECLARATION__NAME = 0;

  /**
   * The feature id for the '<em><b>Br Con</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DECLARATION__BR_CON = 1;

  /**
   * The feature id for the '<em><b>Declaration</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DECLARATION__DECLARATION = 2;

  /**
   * The feature id for the '<em><b>Loop Declaration</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DECLARATION__LOOP_DECLARATION = 3;

  /**
   * The number of structural features of the '<em>Declaration</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DECLARATION_FEATURE_COUNT = 4;

  /**
   * The meta object id for the '{@link raven.sQF.impl.BracketContentImpl <em>Bracket Content</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see raven.sQF.impl.BracketContentImpl
   * @see raven.sQF.impl.SQFPackageImpl#getBracketContent()
   * @generated
   */
  int BRACKET_CONTENT = 3;

  /**
   * The feature id for the '<em><b>Dec Con</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BRACKET_CONTENT__DEC_CON = 0;

  /**
   * The feature id for the '<em><b>Comp</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BRACKET_CONTENT__COMP = 1;

  /**
   * The feature id for the '<em><b>Content</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BRACKET_CONTENT__CONTENT = 2;

  /**
   * The number of structural features of the '<em>Bracket Content</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BRACKET_CONTENT_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link raven.sQF.impl.DecContentImpl <em>Dec Content</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see raven.sQF.impl.DecContentImpl
   * @see raven.sQF.impl.SQFPackageImpl#getDecContent()
   * @generated
   */
  int DEC_CONTENT = 4;

  /**
   * The feature id for the '<em><b>Neg</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DEC_CONTENT__NEG = 0;

  /**
   * The feature id for the '<em><b>Single Content</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DEC_CONTENT__SINGLE_CONTENT = 1;

  /**
   * The feature id for the '<em><b>Op</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DEC_CONTENT__OP = 2;

  /**
   * The feature id for the '<em><b>Next Con</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DEC_CONTENT__NEXT_CON = 3;

  /**
   * The number of structural features of the '<em>Dec Content</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DEC_CONTENT_FEATURE_COUNT = 4;

  /**
   * The meta object id for the '{@link raven.sQF.impl.VarContentImpl <em>Var Content</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see raven.sQF.impl.VarContentImpl
   * @see raven.sQF.impl.SQFPackageImpl#getVarContent()
   * @generated
   */
  int VAR_CONTENT = 5;

  /**
   * The feature id for the '<em><b>Un OP</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VAR_CONTENT__UN_OP = 0;

  /**
   * The feature id for the '<em><b>Num</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VAR_CONTENT__NUM = 1;

  /**
   * The feature id for the '<em><b>String</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VAR_CONTENT__STRING = 2;

  /**
   * The feature id for the '<em><b>Reference</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VAR_CONTENT__REFERENCE = 3;

  /**
   * The feature id for the '<em><b>For Each Var</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VAR_CONTENT__FOR_EACH_VAR = 4;

  /**
   * The feature id for the '<em><b>Sel</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VAR_CONTENT__SEL = 5;

  /**
   * The feature id for the '<em><b>Index</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VAR_CONTENT__INDEX = 6;

  /**
   * The feature id for the '<em><b>Array Content</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VAR_CONTENT__ARRAY_CONTENT = 7;

  /**
   * The feature id for the '<em><b>Execute</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VAR_CONTENT__EXECUTE = 8;

  /**
   * The feature id for the '<em><b>Method Name</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VAR_CONTENT__METHOD_NAME = 9;

  /**
   * The feature id for the '<em><b>Embraced</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VAR_CONTENT__EMBRACED = 10;

  /**
   * The feature id for the '<em><b>Embr Con</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VAR_CONTENT__EMBR_CON = 11;

  /**
   * The feature id for the '<em><b>Bool</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VAR_CONTENT__BOOL = 12;

  /**
   * The feature id for the '<em><b>Param</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VAR_CONTENT__PARAM = 13;

  /**
   * The number of structural features of the '<em>Var Content</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VAR_CONTENT_FEATURE_COUNT = 14;

  /**
   * The meta object id for the '{@link raven.sQF.impl.ArrayLiteralImpl <em>Array Literal</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see raven.sQF.impl.ArrayLiteralImpl
   * @see raven.sQF.impl.SQFPackageImpl#getArrayLiteral()
   * @generated
   */
  int ARRAY_LITERAL = 6;

  /**
   * The feature id for the '<em><b>Con</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ARRAY_LITERAL__CON = 0;

  /**
   * The feature id for the '<em><b>Content</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ARRAY_LITERAL__CONTENT = 1;

  /**
   * The feature id for the '<em><b>Next Content</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ARRAY_LITERAL__NEXT_CONTENT = 2;

  /**
   * The number of structural features of the '<em>Array Literal</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ARRAY_LITERAL_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link raven.sQF.impl.ControlStructureImpl <em>Control Structure</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see raven.sQF.impl.ControlStructureImpl
   * @see raven.sQF.impl.SQFPackageImpl#getControlStructure()
   * @generated
   */
  int CONTROL_STRUCTURE = 7;

  /**
   * The feature id for the '<em><b>If Stat</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONTROL_STRUCTURE__IF_STAT = 0;

  /**
   * The feature id for the '<em><b>While Stat</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONTROL_STRUCTURE__WHILE_STAT = 1;

  /**
   * The feature id for the '<em><b>For Stat</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONTROL_STRUCTURE__FOR_STAT = 2;

  /**
   * The feature id for the '<em><b>For Each Stat</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONTROL_STRUCTURE__FOR_EACH_STAT = 3;

  /**
   * The feature id for the '<em><b>Switch Stat</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONTROL_STRUCTURE__SWITCH_STAT = 4;

  /**
   * The number of structural features of the '<em>Control Structure</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONTROL_STRUCTURE_FEATURE_COUNT = 5;

  /**
   * The meta object id for the '{@link raven.sQF.impl.ifTypeImpl <em>if Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see raven.sQF.impl.ifTypeImpl
   * @see raven.sQF.impl.SQFPackageImpl#getifType()
   * @generated
   */
  int IF_TYPE = 8;

  /**
   * The feature id for the '<em><b>Condition</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IF_TYPE__CONDITION = 0;

  /**
   * The feature id for the '<em><b>Then Code</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IF_TYPE__THEN_CODE = 1;

  /**
   * The feature id for the '<em><b>Else Code</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IF_TYPE__ELSE_CODE = 2;

  /**
   * The feature id for the '<em><b>Exit Code</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IF_TYPE__EXIT_CODE = 3;

  /**
   * The number of structural features of the '<em>if Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IF_TYPE_FEATURE_COUNT = 4;

  /**
   * The meta object id for the '{@link raven.sQF.impl.WhileTypeImpl <em>While Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see raven.sQF.impl.WhileTypeImpl
   * @see raven.sQF.impl.SQFPackageImpl#getWhileType()
   * @generated
   */
  int WHILE_TYPE = 9;

  /**
   * The feature id for the '<em><b>Condition</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WHILE_TYPE__CONDITION = 0;

  /**
   * The feature id for the '<em><b>Loop Code</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WHILE_TYPE__LOOP_CODE = 1;

  /**
   * The number of structural features of the '<em>While Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WHILE_TYPE_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link raven.sQF.impl.ForTypeImpl <em>For Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see raven.sQF.impl.ForTypeImpl
   * @see raven.sQF.impl.SQFPackageImpl#getForType()
   * @generated
   */
  int FOR_TYPE = 10;

  /**
   * The feature id for the '<em><b>Begin</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FOR_TYPE__BEGIN = 0;

  /**
   * The feature id for the '<em><b>Condition</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FOR_TYPE__CONDITION = 1;

  /**
   * The feature id for the '<em><b>End</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FOR_TYPE__END = 2;

  /**
   * The feature id for the '<em><b>Loop Code</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FOR_TYPE__LOOP_CODE = 3;

  /**
   * The number of structural features of the '<em>For Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FOR_TYPE_FEATURE_COUNT = 4;

  /**
   * The meta object id for the '{@link raven.sQF.impl.forVarDeclarationImpl <em>for Var Declaration</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see raven.sQF.impl.forVarDeclarationImpl
   * @see raven.sQF.impl.SQFPackageImpl#getforVarDeclaration()
   * @generated
   */
  int FOR_VAR_DECLARATION = 11;

  /**
   * The feature id for the '<em><b>Begin</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FOR_VAR_DECLARATION__BEGIN = FOR_TYPE__BEGIN;

  /**
   * The feature id for the '<em><b>Condition</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FOR_VAR_DECLARATION__CONDITION = FOR_TYPE__CONDITION;

  /**
   * The feature id for the '<em><b>End</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FOR_VAR_DECLARATION__END = FOR_TYPE__END;

  /**
   * The feature id for the '<em><b>Loop Code</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FOR_VAR_DECLARATION__LOOP_CODE = FOR_TYPE__LOOP_CODE;

  /**
   * The feature id for the '<em><b>From</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FOR_VAR_DECLARATION__FROM = FOR_TYPE_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>To</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FOR_VAR_DECLARATION__TO = FOR_TYPE_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Step</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FOR_VAR_DECLARATION__STEP = FOR_TYPE_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FOR_VAR_DECLARATION__NAME = FOR_TYPE_FEATURE_COUNT + 3;

  /**
   * The number of structural features of the '<em>for Var Declaration</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FOR_VAR_DECLARATION_FEATURE_COUNT = FOR_TYPE_FEATURE_COUNT + 4;

  /**
   * The meta object id for the '{@link raven.sQF.impl.ForeachTypeImpl <em>Foreach Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see raven.sQF.impl.ForeachTypeImpl
   * @see raven.sQF.impl.SQFPackageImpl#getForeachType()
   * @generated
   */
  int FOREACH_TYPE = 12;

  /**
   * The feature id for the '<em><b>Code</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FOREACH_TYPE__CODE = 0;

  /**
   * The feature id for the '<em><b>Array</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FOREACH_TYPE__ARRAY = 1;

  /**
   * The feature id for the '<em><b>Array Literal</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FOREACH_TYPE__ARRAY_LITERAL = 2;

  /**
   * The number of structural features of the '<em>Foreach Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FOREACH_TYPE_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link raven.sQF.impl.SwitchTypeImpl <em>Switch Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see raven.sQF.impl.SwitchTypeImpl
   * @see raven.sQF.impl.SQFPackageImpl#getSwitchType()
   * @generated
   */
  int SWITCH_TYPE = 13;

  /**
   * The feature id for the '<em><b>Var</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SWITCH_TYPE__VAR = 0;

  /**
   * The feature id for the '<em><b>Value</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SWITCH_TYPE__VALUE = 1;

  /**
   * The feature id for the '<em><b>Case Code</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SWITCH_TYPE__CASE_CODE = 2;

  /**
   * The feature id for the '<em><b>Default Code</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SWITCH_TYPE__DEFAULT_CODE = 3;

  /**
   * The number of structural features of the '<em>Switch Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SWITCH_TYPE_FEATURE_COUNT = 4;

  /**
   * The meta object id for the '{@link raven.sQF.impl.MethodImpl <em>Method</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see raven.sQF.impl.MethodImpl
   * @see raven.sQF.impl.SQFPackageImpl#getMethod()
   * @generated
   */
  int METHOD = 14;

  /**
   * The feature id for the '<em><b>Params</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int METHOD__PARAMS = 0;

  /**
   * The feature id for the '<em><b>Execute</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int METHOD__EXECUTE = 1;

  /**
   * The feature id for the '<em><b>Method Name</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int METHOD__METHOD_NAME = 2;

  /**
   * The number of structural features of the '<em>Method</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int METHOD_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link raven.sQF.impl.ObjectImpl <em>Object</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see raven.sQF.impl.ObjectImpl
   * @see raven.sQF.impl.SQFPackageImpl#getObject()
   * @generated
   */
  int OBJECT = 15;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OBJECT__NAME = 0;

  /**
   * The number of structural features of the '<em>Object</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OBJECT_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link raven.sQF.impl.ANYTHINGImpl <em>ANYTHING</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see raven.sQF.impl.ANYTHINGImpl
   * @see raven.sQF.impl.SQFPackageImpl#getANYTHING()
   * @generated
   */
  int ANYTHING = 16;

  /**
   * The feature id for the '<em><b>Bool</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ANYTHING__BOOL = 0;

  /**
   * The feature id for the '<em><b>Num</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ANYTHING__NUM = 1;

  /**
   * The feature id for the '<em><b>String</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ANYTHING__STRING = 2;

  /**
   * The feature id for the '<em><b>Reference</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ANYTHING__REFERENCE = 3;

  /**
   * The number of structural features of the '<em>ANYTHING</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ANYTHING_FEATURE_COUNT = 4;

  /**
   * The meta object id for the '{@link raven.sQF.impl.BooleanImpl <em>Boolean</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see raven.sQF.impl.BooleanImpl
   * @see raven.sQF.impl.SQFPackageImpl#getBoolean()
   * @generated
   */
  int BOOLEAN = 17;

  /**
   * The feature id for the '<em><b>Bool</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BOOLEAN__BOOL = 0;

  /**
   * The feature id for the '<em><b>Command</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BOOLEAN__COMMAND = 1;

  /**
   * The number of structural features of the '<em>Boolean</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BOOLEAN_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link raven.sQF.impl.BooleanContentImpl <em>Boolean Content</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see raven.sQF.impl.BooleanContentImpl
   * @see raven.sQF.impl.SQFPackageImpl#getBooleanContent()
   * @generated
   */
  int BOOLEAN_CONTENT = 18;

  /**
   * The feature id for the '<em><b>Bool Con</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BOOLEAN_CONTENT__BOOL_CON = 0;

  /**
   * The number of structural features of the '<em>Boolean Content</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BOOLEAN_CONTENT_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link raven.sQF.impl.MethodNameImpl <em>Method Name</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see raven.sQF.impl.MethodNameImpl
   * @see raven.sQF.impl.SQFPackageImpl#getMethodName()
   * @generated
   */
  int METHOD_NAME = 19;

  /**
   * The feature id for the '<em><b>Ref</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int METHOD_NAME__REF = 0;

  /**
   * The number of structural features of the '<em>Method Name</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int METHOD_NAME_FEATURE_COUNT = 1;


  /**
   * Returns the meta object for class '{@link raven.sQF.Model <em>Model</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Model</em>'.
   * @see raven.sQF.Model
   * @generated
   */
  EClass getModel();

  /**
   * Returns the meta object for the containment reference list '{@link raven.sQF.Model#getElements <em>Elements</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Elements</em>'.
   * @see raven.sQF.Model#getElements()
   * @see #getModel()
   * @generated
   */
  EReference getModel_Elements();

  /**
   * Returns the meta object for class '{@link raven.sQF.Code <em>Code</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Code</em>'.
   * @see raven.sQF.Code
   * @generated
   */
  EClass getCode();

  /**
   * Returns the meta object for the containment reference '{@link raven.sQF.Code#getDec <em>Dec</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Dec</em>'.
   * @see raven.sQF.Code#getDec()
   * @see #getCode()
   * @generated
   */
  EReference getCode_Dec();

  /**
   * Returns the meta object for the containment reference '{@link raven.sQF.Code#getControl <em>Control</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Control</em>'.
   * @see raven.sQF.Code#getControl()
   * @see #getCode()
   * @generated
   */
  EReference getCode_Control();

  /**
   * Returns the meta object for the containment reference '{@link raven.sQF.Code#getMethod <em>Method</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Method</em>'.
   * @see raven.sQF.Code#getMethod()
   * @see #getCode()
   * @generated
   */
  EReference getCode_Method();

  /**
   * Returns the meta object for class '{@link raven.sQF.Declaration <em>Declaration</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Declaration</em>'.
   * @see raven.sQF.Declaration
   * @generated
   */
  EClass getDeclaration();

  /**
   * Returns the meta object for the attribute '{@link raven.sQF.Declaration#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see raven.sQF.Declaration#getName()
   * @see #getDeclaration()
   * @generated
   */
  EAttribute getDeclaration_Name();

  /**
   * Returns the meta object for the containment reference '{@link raven.sQF.Declaration#getBrCon <em>Br Con</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Br Con</em>'.
   * @see raven.sQF.Declaration#getBrCon()
   * @see #getDeclaration()
   * @generated
   */
  EReference getDeclaration_BrCon();

  /**
   * Returns the meta object for the containment reference '{@link raven.sQF.Declaration#getDeclaration <em>Declaration</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Declaration</em>'.
   * @see raven.sQF.Declaration#getDeclaration()
   * @see #getDeclaration()
   * @generated
   */
  EReference getDeclaration_Declaration();

  /**
   * Returns the meta object for the containment reference '{@link raven.sQF.Declaration#getLoopDeclaration <em>Loop Declaration</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Loop Declaration</em>'.
   * @see raven.sQF.Declaration#getLoopDeclaration()
   * @see #getDeclaration()
   * @generated
   */
  EReference getDeclaration_LoopDeclaration();

  /**
   * Returns the meta object for class '{@link raven.sQF.BracketContent <em>Bracket Content</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Bracket Content</em>'.
   * @see raven.sQF.BracketContent
   * @generated
   */
  EClass getBracketContent();

  /**
   * Returns the meta object for the containment reference '{@link raven.sQF.BracketContent#getDecCon <em>Dec Con</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Dec Con</em>'.
   * @see raven.sQF.BracketContent#getDecCon()
   * @see #getBracketContent()
   * @generated
   */
  EReference getBracketContent_DecCon();

  /**
   * Returns the meta object for the attribute list '{@link raven.sQF.BracketContent#getComp <em>Comp</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Comp</em>'.
   * @see raven.sQF.BracketContent#getComp()
   * @see #getBracketContent()
   * @generated
   */
  EAttribute getBracketContent_Comp();

  /**
   * Returns the meta object for the containment reference list '{@link raven.sQF.BracketContent#getContent <em>Content</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Content</em>'.
   * @see raven.sQF.BracketContent#getContent()
   * @see #getBracketContent()
   * @generated
   */
  EReference getBracketContent_Content();

  /**
   * Returns the meta object for class '{@link raven.sQF.DecContent <em>Dec Content</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Dec Content</em>'.
   * @see raven.sQF.DecContent
   * @generated
   */
  EClass getDecContent();

  /**
   * Returns the meta object for the attribute '{@link raven.sQF.DecContent#getNeg <em>Neg</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Neg</em>'.
   * @see raven.sQF.DecContent#getNeg()
   * @see #getDecContent()
   * @generated
   */
  EAttribute getDecContent_Neg();

  /**
   * Returns the meta object for the containment reference '{@link raven.sQF.DecContent#getSingleContent <em>Single Content</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Single Content</em>'.
   * @see raven.sQF.DecContent#getSingleContent()
   * @see #getDecContent()
   * @generated
   */
  EReference getDecContent_SingleContent();

  /**
   * Returns the meta object for the attribute list '{@link raven.sQF.DecContent#getOp <em>Op</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Op</em>'.
   * @see raven.sQF.DecContent#getOp()
   * @see #getDecContent()
   * @generated
   */
  EAttribute getDecContent_Op();

  /**
   * Returns the meta object for the containment reference list '{@link raven.sQF.DecContent#getNextCon <em>Next Con</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Next Con</em>'.
   * @see raven.sQF.DecContent#getNextCon()
   * @see #getDecContent()
   * @generated
   */
  EReference getDecContent_NextCon();

  /**
   * Returns the meta object for class '{@link raven.sQF.VarContent <em>Var Content</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Var Content</em>'.
   * @see raven.sQF.VarContent
   * @generated
   */
  EClass getVarContent();

  /**
   * Returns the meta object for the attribute '{@link raven.sQF.VarContent#getUnOP <em>Un OP</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Un OP</em>'.
   * @see raven.sQF.VarContent#getUnOP()
   * @see #getVarContent()
   * @generated
   */
  EAttribute getVarContent_UnOP();

  /**
   * Returns the meta object for the attribute '{@link raven.sQF.VarContent#getNum <em>Num</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Num</em>'.
   * @see raven.sQF.VarContent#getNum()
   * @see #getVarContent()
   * @generated
   */
  EAttribute getVarContent_Num();

  /**
   * Returns the meta object for the attribute '{@link raven.sQF.VarContent#getString <em>String</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>String</em>'.
   * @see raven.sQF.VarContent#getString()
   * @see #getVarContent()
   * @generated
   */
  EAttribute getVarContent_String();

  /**
   * Returns the meta object for the reference '{@link raven.sQF.VarContent#getReference <em>Reference</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Reference</em>'.
   * @see raven.sQF.VarContent#getReference()
   * @see #getVarContent()
   * @generated
   */
  EReference getVarContent_Reference();

  /**
   * Returns the meta object for the attribute '{@link raven.sQF.VarContent#getForEachVar <em>For Each Var</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>For Each Var</em>'.
   * @see raven.sQF.VarContent#getForEachVar()
   * @see #getVarContent()
   * @generated
   */
  EAttribute getVarContent_ForEachVar();

  /**
   * Returns the meta object for the attribute '{@link raven.sQF.VarContent#getSel <em>Sel</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Sel</em>'.
   * @see raven.sQF.VarContent#getSel()
   * @see #getVarContent()
   * @generated
   */
  EAttribute getVarContent_Sel();

  /**
   * Returns the meta object for the attribute '{@link raven.sQF.VarContent#getIndex <em>Index</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Index</em>'.
   * @see raven.sQF.VarContent#getIndex()
   * @see #getVarContent()
   * @generated
   */
  EAttribute getVarContent_Index();

  /**
   * Returns the meta object for the containment reference '{@link raven.sQF.VarContent#getArrayContent <em>Array Content</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Array Content</em>'.
   * @see raven.sQF.VarContent#getArrayContent()
   * @see #getVarContent()
   * @generated
   */
  EReference getVarContent_ArrayContent();

  /**
   * Returns the meta object for the attribute '{@link raven.sQF.VarContent#getExecute <em>Execute</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Execute</em>'.
   * @see raven.sQF.VarContent#getExecute()
   * @see #getVarContent()
   * @generated
   */
  EAttribute getVarContent_Execute();

  /**
   * Returns the meta object for the containment reference '{@link raven.sQF.VarContent#getMethodName <em>Method Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Method Name</em>'.
   * @see raven.sQF.VarContent#getMethodName()
   * @see #getVarContent()
   * @generated
   */
  EReference getVarContent_MethodName();

  /**
   * Returns the meta object for the attribute '{@link raven.sQF.VarContent#getEmbraced <em>Embraced</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Embraced</em>'.
   * @see raven.sQF.VarContent#getEmbraced()
   * @see #getVarContent()
   * @generated
   */
  EAttribute getVarContent_Embraced();

  /**
   * Returns the meta object for the containment reference '{@link raven.sQF.VarContent#getEmbrCon <em>Embr Con</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Embr Con</em>'.
   * @see raven.sQF.VarContent#getEmbrCon()
   * @see #getVarContent()
   * @generated
   */
  EReference getVarContent_EmbrCon();

  /**
   * Returns the meta object for the containment reference '{@link raven.sQF.VarContent#getBool <em>Bool</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Bool</em>'.
   * @see raven.sQF.VarContent#getBool()
   * @see #getVarContent()
   * @generated
   */
  EReference getVarContent_Bool();

  /**
   * Returns the meta object for the attribute '{@link raven.sQF.VarContent#getParam <em>Param</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Param</em>'.
   * @see raven.sQF.VarContent#getParam()
   * @see #getVarContent()
   * @generated
   */
  EAttribute getVarContent_Param();

  /**
   * Returns the meta object for class '{@link raven.sQF.ArrayLiteral <em>Array Literal</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Array Literal</em>'.
   * @see raven.sQF.ArrayLiteral
   * @generated
   */
  EClass getArrayLiteral();

  /**
   * Returns the meta object for the attribute '{@link raven.sQF.ArrayLiteral#getCon <em>Con</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Con</em>'.
   * @see raven.sQF.ArrayLiteral#getCon()
   * @see #getArrayLiteral()
   * @generated
   */
  EAttribute getArrayLiteral_Con();

  /**
   * Returns the meta object for the containment reference '{@link raven.sQF.ArrayLiteral#getContent <em>Content</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Content</em>'.
   * @see raven.sQF.ArrayLiteral#getContent()
   * @see #getArrayLiteral()
   * @generated
   */
  EReference getArrayLiteral_Content();

  /**
   * Returns the meta object for the containment reference list '{@link raven.sQF.ArrayLiteral#getNextContent <em>Next Content</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Next Content</em>'.
   * @see raven.sQF.ArrayLiteral#getNextContent()
   * @see #getArrayLiteral()
   * @generated
   */
  EReference getArrayLiteral_NextContent();

  /**
   * Returns the meta object for class '{@link raven.sQF.ControlStructure <em>Control Structure</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Control Structure</em>'.
   * @see raven.sQF.ControlStructure
   * @generated
   */
  EClass getControlStructure();

  /**
   * Returns the meta object for the containment reference '{@link raven.sQF.ControlStructure#getIfStat <em>If Stat</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>If Stat</em>'.
   * @see raven.sQF.ControlStructure#getIfStat()
   * @see #getControlStructure()
   * @generated
   */
  EReference getControlStructure_IfStat();

  /**
   * Returns the meta object for the containment reference '{@link raven.sQF.ControlStructure#getWhileStat <em>While Stat</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>While Stat</em>'.
   * @see raven.sQF.ControlStructure#getWhileStat()
   * @see #getControlStructure()
   * @generated
   */
  EReference getControlStructure_WhileStat();

  /**
   * Returns the meta object for the containment reference '{@link raven.sQF.ControlStructure#getForStat <em>For Stat</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>For Stat</em>'.
   * @see raven.sQF.ControlStructure#getForStat()
   * @see #getControlStructure()
   * @generated
   */
  EReference getControlStructure_ForStat();

  /**
   * Returns the meta object for the containment reference '{@link raven.sQF.ControlStructure#getForEachStat <em>For Each Stat</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>For Each Stat</em>'.
   * @see raven.sQF.ControlStructure#getForEachStat()
   * @see #getControlStructure()
   * @generated
   */
  EReference getControlStructure_ForEachStat();

  /**
   * Returns the meta object for the containment reference '{@link raven.sQF.ControlStructure#getSwitchStat <em>Switch Stat</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Switch Stat</em>'.
   * @see raven.sQF.ControlStructure#getSwitchStat()
   * @see #getControlStructure()
   * @generated
   */
  EReference getControlStructure_SwitchStat();

  /**
   * Returns the meta object for class '{@link raven.sQF.ifType <em>if Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>if Type</em>'.
   * @see raven.sQF.ifType
   * @generated
   */
  EClass getifType();

  /**
   * Returns the meta object for the containment reference '{@link raven.sQF.ifType#getCondition <em>Condition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Condition</em>'.
   * @see raven.sQF.ifType#getCondition()
   * @see #getifType()
   * @generated
   */
  EReference getifType_Condition();

  /**
   * Returns the meta object for the containment reference list '{@link raven.sQF.ifType#getThenCode <em>Then Code</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Then Code</em>'.
   * @see raven.sQF.ifType#getThenCode()
   * @see #getifType()
   * @generated
   */
  EReference getifType_ThenCode();

  /**
   * Returns the meta object for the containment reference list '{@link raven.sQF.ifType#getElseCode <em>Else Code</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Else Code</em>'.
   * @see raven.sQF.ifType#getElseCode()
   * @see #getifType()
   * @generated
   */
  EReference getifType_ElseCode();

  /**
   * Returns the meta object for the containment reference list '{@link raven.sQF.ifType#getExitCode <em>Exit Code</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Exit Code</em>'.
   * @see raven.sQF.ifType#getExitCode()
   * @see #getifType()
   * @generated
   */
  EReference getifType_ExitCode();

  /**
   * Returns the meta object for class '{@link raven.sQF.WhileType <em>While Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>While Type</em>'.
   * @see raven.sQF.WhileType
   * @generated
   */
  EClass getWhileType();

  /**
   * Returns the meta object for the containment reference '{@link raven.sQF.WhileType#getCondition <em>Condition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Condition</em>'.
   * @see raven.sQF.WhileType#getCondition()
   * @see #getWhileType()
   * @generated
   */
  EReference getWhileType_Condition();

  /**
   * Returns the meta object for the containment reference list '{@link raven.sQF.WhileType#getLoopCode <em>Loop Code</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Loop Code</em>'.
   * @see raven.sQF.WhileType#getLoopCode()
   * @see #getWhileType()
   * @generated
   */
  EReference getWhileType_LoopCode();

  /**
   * Returns the meta object for class '{@link raven.sQF.ForType <em>For Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>For Type</em>'.
   * @see raven.sQF.ForType
   * @generated
   */
  EClass getForType();

  /**
   * Returns the meta object for the containment reference '{@link raven.sQF.ForType#getBegin <em>Begin</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Begin</em>'.
   * @see raven.sQF.ForType#getBegin()
   * @see #getForType()
   * @generated
   */
  EReference getForType_Begin();

  /**
   * Returns the meta object for the containment reference '{@link raven.sQF.ForType#getCondition <em>Condition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Condition</em>'.
   * @see raven.sQF.ForType#getCondition()
   * @see #getForType()
   * @generated
   */
  EReference getForType_Condition();

  /**
   * Returns the meta object for the containment reference '{@link raven.sQF.ForType#getEnd <em>End</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>End</em>'.
   * @see raven.sQF.ForType#getEnd()
   * @see #getForType()
   * @generated
   */
  EReference getForType_End();

  /**
   * Returns the meta object for the containment reference list '{@link raven.sQF.ForType#getLoopCode <em>Loop Code</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Loop Code</em>'.
   * @see raven.sQF.ForType#getLoopCode()
   * @see #getForType()
   * @generated
   */
  EReference getForType_LoopCode();

  /**
   * Returns the meta object for class '{@link raven.sQF.forVarDeclaration <em>for Var Declaration</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>for Var Declaration</em>'.
   * @see raven.sQF.forVarDeclaration
   * @generated
   */
  EClass getforVarDeclaration();

  /**
   * Returns the meta object for the attribute '{@link raven.sQF.forVarDeclaration#getFrom <em>From</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>From</em>'.
   * @see raven.sQF.forVarDeclaration#getFrom()
   * @see #getforVarDeclaration()
   * @generated
   */
  EAttribute getforVarDeclaration_From();

  /**
   * Returns the meta object for the attribute '{@link raven.sQF.forVarDeclaration#getTo <em>To</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>To</em>'.
   * @see raven.sQF.forVarDeclaration#getTo()
   * @see #getforVarDeclaration()
   * @generated
   */
  EAttribute getforVarDeclaration_To();

  /**
   * Returns the meta object for the attribute '{@link raven.sQF.forVarDeclaration#getStep <em>Step</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Step</em>'.
   * @see raven.sQF.forVarDeclaration#getStep()
   * @see #getforVarDeclaration()
   * @generated
   */
  EAttribute getforVarDeclaration_Step();

  /**
   * Returns the meta object for the attribute '{@link raven.sQF.forVarDeclaration#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see raven.sQF.forVarDeclaration#getName()
   * @see #getforVarDeclaration()
   * @generated
   */
  EAttribute getforVarDeclaration_Name();

  /**
   * Returns the meta object for class '{@link raven.sQF.ForeachType <em>Foreach Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Foreach Type</em>'.
   * @see raven.sQF.ForeachType
   * @generated
   */
  EClass getForeachType();

  /**
   * Returns the meta object for the containment reference list '{@link raven.sQF.ForeachType#getCode <em>Code</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Code</em>'.
   * @see raven.sQF.ForeachType#getCode()
   * @see #getForeachType()
   * @generated
   */
  EReference getForeachType_Code();

  /**
   * Returns the meta object for the reference '{@link raven.sQF.ForeachType#getArray <em>Array</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Array</em>'.
   * @see raven.sQF.ForeachType#getArray()
   * @see #getForeachType()
   * @generated
   */
  EReference getForeachType_Array();

  /**
   * Returns the meta object for the containment reference '{@link raven.sQF.ForeachType#getArrayLiteral <em>Array Literal</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Array Literal</em>'.
   * @see raven.sQF.ForeachType#getArrayLiteral()
   * @see #getForeachType()
   * @generated
   */
  EReference getForeachType_ArrayLiteral();

  /**
   * Returns the meta object for class '{@link raven.sQF.SwitchType <em>Switch Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Switch Type</em>'.
   * @see raven.sQF.SwitchType
   * @generated
   */
  EClass getSwitchType();

  /**
   * Returns the meta object for the reference '{@link raven.sQF.SwitchType#getVar <em>Var</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Var</em>'.
   * @see raven.sQF.SwitchType#getVar()
   * @see #getSwitchType()
   * @generated
   */
  EReference getSwitchType_Var();

  /**
   * Returns the meta object for the containment reference list '{@link raven.sQF.SwitchType#getValue <em>Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Value</em>'.
   * @see raven.sQF.SwitchType#getValue()
   * @see #getSwitchType()
   * @generated
   */
  EReference getSwitchType_Value();

  /**
   * Returns the meta object for the containment reference list '{@link raven.sQF.SwitchType#getCaseCode <em>Case Code</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Case Code</em>'.
   * @see raven.sQF.SwitchType#getCaseCode()
   * @see #getSwitchType()
   * @generated
   */
  EReference getSwitchType_CaseCode();

  /**
   * Returns the meta object for the containment reference list '{@link raven.sQF.SwitchType#getDefaultCode <em>Default Code</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Default Code</em>'.
   * @see raven.sQF.SwitchType#getDefaultCode()
   * @see #getSwitchType()
   * @generated
   */
  EReference getSwitchType_DefaultCode();

  /**
   * Returns the meta object for class '{@link raven.sQF.Method <em>Method</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Method</em>'.
   * @see raven.sQF.Method
   * @generated
   */
  EClass getMethod();

  /**
   * Returns the meta object for the containment reference '{@link raven.sQF.Method#getParams <em>Params</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Params</em>'.
   * @see raven.sQF.Method#getParams()
   * @see #getMethod()
   * @generated
   */
  EReference getMethod_Params();

  /**
   * Returns the meta object for the attribute '{@link raven.sQF.Method#getExecute <em>Execute</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Execute</em>'.
   * @see raven.sQF.Method#getExecute()
   * @see #getMethod()
   * @generated
   */
  EAttribute getMethod_Execute();

  /**
   * Returns the meta object for the containment reference '{@link raven.sQF.Method#getMethodName <em>Method Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Method Name</em>'.
   * @see raven.sQF.Method#getMethodName()
   * @see #getMethod()
   * @generated
   */
  EReference getMethod_MethodName();

  /**
   * Returns the meta object for class '{@link raven.sQF.Object <em>Object</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Object</em>'.
   * @see raven.sQF.Object
   * @generated
   */
  EClass getObject();

  /**
   * Returns the meta object for the attribute '{@link raven.sQF.Object#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see raven.sQF.Object#getName()
   * @see #getObject()
   * @generated
   */
  EAttribute getObject_Name();

  /**
   * Returns the meta object for class '{@link raven.sQF.ANYTHING <em>ANYTHING</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>ANYTHING</em>'.
   * @see raven.sQF.ANYTHING
   * @generated
   */
  EClass getANYTHING();

  /**
   * Returns the meta object for the containment reference '{@link raven.sQF.ANYTHING#getBool <em>Bool</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Bool</em>'.
   * @see raven.sQF.ANYTHING#getBool()
   * @see #getANYTHING()
   * @generated
   */
  EReference getANYTHING_Bool();

  /**
   * Returns the meta object for the attribute '{@link raven.sQF.ANYTHING#getNum <em>Num</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Num</em>'.
   * @see raven.sQF.ANYTHING#getNum()
   * @see #getANYTHING()
   * @generated
   */
  EAttribute getANYTHING_Num();

  /**
   * Returns the meta object for the attribute '{@link raven.sQF.ANYTHING#getString <em>String</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>String</em>'.
   * @see raven.sQF.ANYTHING#getString()
   * @see #getANYTHING()
   * @generated
   */
  EAttribute getANYTHING_String();

  /**
   * Returns the meta object for the reference '{@link raven.sQF.ANYTHING#getReference <em>Reference</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Reference</em>'.
   * @see raven.sQF.ANYTHING#getReference()
   * @see #getANYTHING()
   * @generated
   */
  EReference getANYTHING_Reference();

  /**
   * Returns the meta object for class '{@link raven.sQF.Boolean <em>Boolean</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Boolean</em>'.
   * @see raven.sQF.Boolean
   * @generated
   */
  EClass getBoolean();

  /**
   * Returns the meta object for the attribute '{@link raven.sQF.Boolean#getBool <em>Bool</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Bool</em>'.
   * @see raven.sQF.Boolean#getBool()
   * @see #getBoolean()
   * @generated
   */
  EAttribute getBoolean_Bool();

  /**
   * Returns the meta object for the attribute '{@link raven.sQF.Boolean#getCommand <em>Command</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Command</em>'.
   * @see raven.sQF.Boolean#getCommand()
   * @see #getBoolean()
   * @generated
   */
  EAttribute getBoolean_Command();

  /**
   * Returns the meta object for class '{@link raven.sQF.BooleanContent <em>Boolean Content</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Boolean Content</em>'.
   * @see raven.sQF.BooleanContent
   * @generated
   */
  EClass getBooleanContent();

  /**
   * Returns the meta object for the containment reference '{@link raven.sQF.BooleanContent#getBoolCon <em>Bool Con</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Bool Con</em>'.
   * @see raven.sQF.BooleanContent#getBoolCon()
   * @see #getBooleanContent()
   * @generated
   */
  EReference getBooleanContent_BoolCon();

  /**
   * Returns the meta object for class '{@link raven.sQF.MethodName <em>Method Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Method Name</em>'.
   * @see raven.sQF.MethodName
   * @generated
   */
  EClass getMethodName();

  /**
   * Returns the meta object for the reference '{@link raven.sQF.MethodName#getRef <em>Ref</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Ref</em>'.
   * @see raven.sQF.MethodName#getRef()
   * @see #getMethodName()
   * @generated
   */
  EReference getMethodName_Ref();

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
  SQFFactory getSQFFactory();

  /**
   * <!-- begin-user-doc -->
   * Defines literals for the meta objects that represent
   * <ul>
   *   <li>each class,</li>
   *   <li>each feature of each class,</li>
   *   <li>each enum,</li>
   *   <li>and each data type</li>
   * </ul>
   * <!-- end-user-doc -->
   * @generated
   */
  interface Literals
  {
    /**
     * The meta object literal for the '{@link raven.sQF.impl.ModelImpl <em>Model</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see raven.sQF.impl.ModelImpl
     * @see raven.sQF.impl.SQFPackageImpl#getModel()
     * @generated
     */
    EClass MODEL = eINSTANCE.getModel();

    /**
     * The meta object literal for the '<em><b>Elements</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference MODEL__ELEMENTS = eINSTANCE.getModel_Elements();

    /**
     * The meta object literal for the '{@link raven.sQF.impl.CodeImpl <em>Code</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see raven.sQF.impl.CodeImpl
     * @see raven.sQF.impl.SQFPackageImpl#getCode()
     * @generated
     */
    EClass CODE = eINSTANCE.getCode();

    /**
     * The meta object literal for the '<em><b>Dec</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CODE__DEC = eINSTANCE.getCode_Dec();

    /**
     * The meta object literal for the '<em><b>Control</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CODE__CONTROL = eINSTANCE.getCode_Control();

    /**
     * The meta object literal for the '<em><b>Method</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CODE__METHOD = eINSTANCE.getCode_Method();

    /**
     * The meta object literal for the '{@link raven.sQF.impl.DeclarationImpl <em>Declaration</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see raven.sQF.impl.DeclarationImpl
     * @see raven.sQF.impl.SQFPackageImpl#getDeclaration()
     * @generated
     */
    EClass DECLARATION = eINSTANCE.getDeclaration();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute DECLARATION__NAME = eINSTANCE.getDeclaration_Name();

    /**
     * The meta object literal for the '<em><b>Br Con</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference DECLARATION__BR_CON = eINSTANCE.getDeclaration_BrCon();

    /**
     * The meta object literal for the '<em><b>Declaration</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference DECLARATION__DECLARATION = eINSTANCE.getDeclaration_Declaration();

    /**
     * The meta object literal for the '<em><b>Loop Declaration</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference DECLARATION__LOOP_DECLARATION = eINSTANCE.getDeclaration_LoopDeclaration();

    /**
     * The meta object literal for the '{@link raven.sQF.impl.BracketContentImpl <em>Bracket Content</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see raven.sQF.impl.BracketContentImpl
     * @see raven.sQF.impl.SQFPackageImpl#getBracketContent()
     * @generated
     */
    EClass BRACKET_CONTENT = eINSTANCE.getBracketContent();

    /**
     * The meta object literal for the '<em><b>Dec Con</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference BRACKET_CONTENT__DEC_CON = eINSTANCE.getBracketContent_DecCon();

    /**
     * The meta object literal for the '<em><b>Comp</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute BRACKET_CONTENT__COMP = eINSTANCE.getBracketContent_Comp();

    /**
     * The meta object literal for the '<em><b>Content</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference BRACKET_CONTENT__CONTENT = eINSTANCE.getBracketContent_Content();

    /**
     * The meta object literal for the '{@link raven.sQF.impl.DecContentImpl <em>Dec Content</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see raven.sQF.impl.DecContentImpl
     * @see raven.sQF.impl.SQFPackageImpl#getDecContent()
     * @generated
     */
    EClass DEC_CONTENT = eINSTANCE.getDecContent();

    /**
     * The meta object literal for the '<em><b>Neg</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute DEC_CONTENT__NEG = eINSTANCE.getDecContent_Neg();

    /**
     * The meta object literal for the '<em><b>Single Content</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference DEC_CONTENT__SINGLE_CONTENT = eINSTANCE.getDecContent_SingleContent();

    /**
     * The meta object literal for the '<em><b>Op</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute DEC_CONTENT__OP = eINSTANCE.getDecContent_Op();

    /**
     * The meta object literal for the '<em><b>Next Con</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference DEC_CONTENT__NEXT_CON = eINSTANCE.getDecContent_NextCon();

    /**
     * The meta object literal for the '{@link raven.sQF.impl.VarContentImpl <em>Var Content</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see raven.sQF.impl.VarContentImpl
     * @see raven.sQF.impl.SQFPackageImpl#getVarContent()
     * @generated
     */
    EClass VAR_CONTENT = eINSTANCE.getVarContent();

    /**
     * The meta object literal for the '<em><b>Un OP</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute VAR_CONTENT__UN_OP = eINSTANCE.getVarContent_UnOP();

    /**
     * The meta object literal for the '<em><b>Num</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute VAR_CONTENT__NUM = eINSTANCE.getVarContent_Num();

    /**
     * The meta object literal for the '<em><b>String</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute VAR_CONTENT__STRING = eINSTANCE.getVarContent_String();

    /**
     * The meta object literal for the '<em><b>Reference</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference VAR_CONTENT__REFERENCE = eINSTANCE.getVarContent_Reference();

    /**
     * The meta object literal for the '<em><b>For Each Var</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute VAR_CONTENT__FOR_EACH_VAR = eINSTANCE.getVarContent_ForEachVar();

    /**
     * The meta object literal for the '<em><b>Sel</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute VAR_CONTENT__SEL = eINSTANCE.getVarContent_Sel();

    /**
     * The meta object literal for the '<em><b>Index</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute VAR_CONTENT__INDEX = eINSTANCE.getVarContent_Index();

    /**
     * The meta object literal for the '<em><b>Array Content</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference VAR_CONTENT__ARRAY_CONTENT = eINSTANCE.getVarContent_ArrayContent();

    /**
     * The meta object literal for the '<em><b>Execute</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute VAR_CONTENT__EXECUTE = eINSTANCE.getVarContent_Execute();

    /**
     * The meta object literal for the '<em><b>Method Name</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference VAR_CONTENT__METHOD_NAME = eINSTANCE.getVarContent_MethodName();

    /**
     * The meta object literal for the '<em><b>Embraced</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute VAR_CONTENT__EMBRACED = eINSTANCE.getVarContent_Embraced();

    /**
     * The meta object literal for the '<em><b>Embr Con</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference VAR_CONTENT__EMBR_CON = eINSTANCE.getVarContent_EmbrCon();

    /**
     * The meta object literal for the '<em><b>Bool</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference VAR_CONTENT__BOOL = eINSTANCE.getVarContent_Bool();

    /**
     * The meta object literal for the '<em><b>Param</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute VAR_CONTENT__PARAM = eINSTANCE.getVarContent_Param();

    /**
     * The meta object literal for the '{@link raven.sQF.impl.ArrayLiteralImpl <em>Array Literal</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see raven.sQF.impl.ArrayLiteralImpl
     * @see raven.sQF.impl.SQFPackageImpl#getArrayLiteral()
     * @generated
     */
    EClass ARRAY_LITERAL = eINSTANCE.getArrayLiteral();

    /**
     * The meta object literal for the '<em><b>Con</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ARRAY_LITERAL__CON = eINSTANCE.getArrayLiteral_Con();

    /**
     * The meta object literal for the '<em><b>Content</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ARRAY_LITERAL__CONTENT = eINSTANCE.getArrayLiteral_Content();

    /**
     * The meta object literal for the '<em><b>Next Content</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ARRAY_LITERAL__NEXT_CONTENT = eINSTANCE.getArrayLiteral_NextContent();

    /**
     * The meta object literal for the '{@link raven.sQF.impl.ControlStructureImpl <em>Control Structure</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see raven.sQF.impl.ControlStructureImpl
     * @see raven.sQF.impl.SQFPackageImpl#getControlStructure()
     * @generated
     */
    EClass CONTROL_STRUCTURE = eINSTANCE.getControlStructure();

    /**
     * The meta object literal for the '<em><b>If Stat</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CONTROL_STRUCTURE__IF_STAT = eINSTANCE.getControlStructure_IfStat();

    /**
     * The meta object literal for the '<em><b>While Stat</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CONTROL_STRUCTURE__WHILE_STAT = eINSTANCE.getControlStructure_WhileStat();

    /**
     * The meta object literal for the '<em><b>For Stat</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CONTROL_STRUCTURE__FOR_STAT = eINSTANCE.getControlStructure_ForStat();

    /**
     * The meta object literal for the '<em><b>For Each Stat</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CONTROL_STRUCTURE__FOR_EACH_STAT = eINSTANCE.getControlStructure_ForEachStat();

    /**
     * The meta object literal for the '<em><b>Switch Stat</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CONTROL_STRUCTURE__SWITCH_STAT = eINSTANCE.getControlStructure_SwitchStat();

    /**
     * The meta object literal for the '{@link raven.sQF.impl.ifTypeImpl <em>if Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see raven.sQF.impl.ifTypeImpl
     * @see raven.sQF.impl.SQFPackageImpl#getifType()
     * @generated
     */
    EClass IF_TYPE = eINSTANCE.getifType();

    /**
     * The meta object literal for the '<em><b>Condition</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference IF_TYPE__CONDITION = eINSTANCE.getifType_Condition();

    /**
     * The meta object literal for the '<em><b>Then Code</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference IF_TYPE__THEN_CODE = eINSTANCE.getifType_ThenCode();

    /**
     * The meta object literal for the '<em><b>Else Code</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference IF_TYPE__ELSE_CODE = eINSTANCE.getifType_ElseCode();

    /**
     * The meta object literal for the '<em><b>Exit Code</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference IF_TYPE__EXIT_CODE = eINSTANCE.getifType_ExitCode();

    /**
     * The meta object literal for the '{@link raven.sQF.impl.WhileTypeImpl <em>While Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see raven.sQF.impl.WhileTypeImpl
     * @see raven.sQF.impl.SQFPackageImpl#getWhileType()
     * @generated
     */
    EClass WHILE_TYPE = eINSTANCE.getWhileType();

    /**
     * The meta object literal for the '<em><b>Condition</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference WHILE_TYPE__CONDITION = eINSTANCE.getWhileType_Condition();

    /**
     * The meta object literal for the '<em><b>Loop Code</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference WHILE_TYPE__LOOP_CODE = eINSTANCE.getWhileType_LoopCode();

    /**
     * The meta object literal for the '{@link raven.sQF.impl.ForTypeImpl <em>For Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see raven.sQF.impl.ForTypeImpl
     * @see raven.sQF.impl.SQFPackageImpl#getForType()
     * @generated
     */
    EClass FOR_TYPE = eINSTANCE.getForType();

    /**
     * The meta object literal for the '<em><b>Begin</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FOR_TYPE__BEGIN = eINSTANCE.getForType_Begin();

    /**
     * The meta object literal for the '<em><b>Condition</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FOR_TYPE__CONDITION = eINSTANCE.getForType_Condition();

    /**
     * The meta object literal for the '<em><b>End</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FOR_TYPE__END = eINSTANCE.getForType_End();

    /**
     * The meta object literal for the '<em><b>Loop Code</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FOR_TYPE__LOOP_CODE = eINSTANCE.getForType_LoopCode();

    /**
     * The meta object literal for the '{@link raven.sQF.impl.forVarDeclarationImpl <em>for Var Declaration</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see raven.sQF.impl.forVarDeclarationImpl
     * @see raven.sQF.impl.SQFPackageImpl#getforVarDeclaration()
     * @generated
     */
    EClass FOR_VAR_DECLARATION = eINSTANCE.getforVarDeclaration();

    /**
     * The meta object literal for the '<em><b>From</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute FOR_VAR_DECLARATION__FROM = eINSTANCE.getforVarDeclaration_From();

    /**
     * The meta object literal for the '<em><b>To</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute FOR_VAR_DECLARATION__TO = eINSTANCE.getforVarDeclaration_To();

    /**
     * The meta object literal for the '<em><b>Step</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute FOR_VAR_DECLARATION__STEP = eINSTANCE.getforVarDeclaration_Step();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute FOR_VAR_DECLARATION__NAME = eINSTANCE.getforVarDeclaration_Name();

    /**
     * The meta object literal for the '{@link raven.sQF.impl.ForeachTypeImpl <em>Foreach Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see raven.sQF.impl.ForeachTypeImpl
     * @see raven.sQF.impl.SQFPackageImpl#getForeachType()
     * @generated
     */
    EClass FOREACH_TYPE = eINSTANCE.getForeachType();

    /**
     * The meta object literal for the '<em><b>Code</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FOREACH_TYPE__CODE = eINSTANCE.getForeachType_Code();

    /**
     * The meta object literal for the '<em><b>Array</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FOREACH_TYPE__ARRAY = eINSTANCE.getForeachType_Array();

    /**
     * The meta object literal for the '<em><b>Array Literal</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FOREACH_TYPE__ARRAY_LITERAL = eINSTANCE.getForeachType_ArrayLiteral();

    /**
     * The meta object literal for the '{@link raven.sQF.impl.SwitchTypeImpl <em>Switch Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see raven.sQF.impl.SwitchTypeImpl
     * @see raven.sQF.impl.SQFPackageImpl#getSwitchType()
     * @generated
     */
    EClass SWITCH_TYPE = eINSTANCE.getSwitchType();

    /**
     * The meta object literal for the '<em><b>Var</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference SWITCH_TYPE__VAR = eINSTANCE.getSwitchType_Var();

    /**
     * The meta object literal for the '<em><b>Value</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference SWITCH_TYPE__VALUE = eINSTANCE.getSwitchType_Value();

    /**
     * The meta object literal for the '<em><b>Case Code</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference SWITCH_TYPE__CASE_CODE = eINSTANCE.getSwitchType_CaseCode();

    /**
     * The meta object literal for the '<em><b>Default Code</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference SWITCH_TYPE__DEFAULT_CODE = eINSTANCE.getSwitchType_DefaultCode();

    /**
     * The meta object literal for the '{@link raven.sQF.impl.MethodImpl <em>Method</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see raven.sQF.impl.MethodImpl
     * @see raven.sQF.impl.SQFPackageImpl#getMethod()
     * @generated
     */
    EClass METHOD = eINSTANCE.getMethod();

    /**
     * The meta object literal for the '<em><b>Params</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference METHOD__PARAMS = eINSTANCE.getMethod_Params();

    /**
     * The meta object literal for the '<em><b>Execute</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute METHOD__EXECUTE = eINSTANCE.getMethod_Execute();

    /**
     * The meta object literal for the '<em><b>Method Name</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference METHOD__METHOD_NAME = eINSTANCE.getMethod_MethodName();

    /**
     * The meta object literal for the '{@link raven.sQF.impl.ObjectImpl <em>Object</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see raven.sQF.impl.ObjectImpl
     * @see raven.sQF.impl.SQFPackageImpl#getObject()
     * @generated
     */
    EClass OBJECT = eINSTANCE.getObject();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute OBJECT__NAME = eINSTANCE.getObject_Name();

    /**
     * The meta object literal for the '{@link raven.sQF.impl.ANYTHINGImpl <em>ANYTHING</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see raven.sQF.impl.ANYTHINGImpl
     * @see raven.sQF.impl.SQFPackageImpl#getANYTHING()
     * @generated
     */
    EClass ANYTHING = eINSTANCE.getANYTHING();

    /**
     * The meta object literal for the '<em><b>Bool</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ANYTHING__BOOL = eINSTANCE.getANYTHING_Bool();

    /**
     * The meta object literal for the '<em><b>Num</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ANYTHING__NUM = eINSTANCE.getANYTHING_Num();

    /**
     * The meta object literal for the '<em><b>String</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ANYTHING__STRING = eINSTANCE.getANYTHING_String();

    /**
     * The meta object literal for the '<em><b>Reference</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ANYTHING__REFERENCE = eINSTANCE.getANYTHING_Reference();

    /**
     * The meta object literal for the '{@link raven.sQF.impl.BooleanImpl <em>Boolean</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see raven.sQF.impl.BooleanImpl
     * @see raven.sQF.impl.SQFPackageImpl#getBoolean()
     * @generated
     */
    EClass BOOLEAN = eINSTANCE.getBoolean();

    /**
     * The meta object literal for the '<em><b>Bool</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute BOOLEAN__BOOL = eINSTANCE.getBoolean_Bool();

    /**
     * The meta object literal for the '<em><b>Command</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute BOOLEAN__COMMAND = eINSTANCE.getBoolean_Command();

    /**
     * The meta object literal for the '{@link raven.sQF.impl.BooleanContentImpl <em>Boolean Content</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see raven.sQF.impl.BooleanContentImpl
     * @see raven.sQF.impl.SQFPackageImpl#getBooleanContent()
     * @generated
     */
    EClass BOOLEAN_CONTENT = eINSTANCE.getBooleanContent();

    /**
     * The meta object literal for the '<em><b>Bool Con</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference BOOLEAN_CONTENT__BOOL_CON = eINSTANCE.getBooleanContent_BoolCon();

    /**
     * The meta object literal for the '{@link raven.sQF.impl.MethodNameImpl <em>Method Name</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see raven.sQF.impl.MethodNameImpl
     * @see raven.sQF.impl.SQFPackageImpl#getMethodName()
     * @generated
     */
    EClass METHOD_NAME = eINSTANCE.getMethodName();

    /**
     * The meta object literal for the '<em><b>Ref</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference METHOD_NAME__REF = eINSTANCE.getMethodName_Ref();

  }

} //SQFPackage

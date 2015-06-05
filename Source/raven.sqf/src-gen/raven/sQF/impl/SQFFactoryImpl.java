/**
 */
package raven.sQF.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

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
import raven.sQF.SQFFactory;
import raven.sQF.SQFPackage;
import raven.sQF.SwitchType;
import raven.sQF.VarContent;
import raven.sQF.WhileType;
import raven.sQF.forVarDeclaration;
import raven.sQF.ifType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class SQFFactoryImpl extends EFactoryImpl implements SQFFactory
{
  /**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static SQFFactory init()
  {
    try
    {
      SQFFactory theSQFFactory = (SQFFactory)EPackage.Registry.INSTANCE.getEFactory(SQFPackage.eNS_URI);
      if (theSQFFactory != null)
      {
        return theSQFFactory;
      }
    }
    catch (Exception exception)
    {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new SQFFactoryImpl();
  }

  /**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SQFFactoryImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EObject create(EClass eClass)
  {
    switch (eClass.getClassifierID())
    {
      case SQFPackage.MODEL: return createModel();
      case SQFPackage.CODE: return createCode();
      case SQFPackage.DECLARATION: return createDeclaration();
      case SQFPackage.BRACKET_CONTENT: return createBracketContent();
      case SQFPackage.DEC_CONTENT: return createDecContent();
      case SQFPackage.VAR_CONTENT: return createVarContent();
      case SQFPackage.ARRAY_LITERAL: return createArrayLiteral();
      case SQFPackage.CONTROL_STRUCTURE: return createControlStructure();
      case SQFPackage.IF_TYPE: return createifType();
      case SQFPackage.WHILE_TYPE: return createWhileType();
      case SQFPackage.FOR_TYPE: return createForType();
      case SQFPackage.FOR_VAR_DECLARATION: return createforVarDeclaration();
      case SQFPackage.FOREACH_TYPE: return createForeachType();
      case SQFPackage.SWITCH_TYPE: return createSwitchType();
      case SQFPackage.METHOD: return createMethod();
      case SQFPackage.OBJECT: return createObject();
      case SQFPackage.ANYTHING: return createANYTHING();
      case SQFPackage.BOOLEAN: return createBoolean();
      case SQFPackage.BOOLEAN_CONTENT: return createBooleanContent();
      case SQFPackage.METHOD_NAME: return createMethodName();
      default:
        throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Model createModel()
  {
    ModelImpl model = new ModelImpl();
    return model;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Code createCode()
  {
    CodeImpl code = new CodeImpl();
    return code;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Declaration createDeclaration()
  {
    DeclarationImpl declaration = new DeclarationImpl();
    return declaration;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public BracketContent createBracketContent()
  {
    BracketContentImpl bracketContent = new BracketContentImpl();
    return bracketContent;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DecContent createDecContent()
  {
    DecContentImpl decContent = new DecContentImpl();
    return decContent;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public VarContent createVarContent()
  {
    VarContentImpl varContent = new VarContentImpl();
    return varContent;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ArrayLiteral createArrayLiteral()
  {
    ArrayLiteralImpl arrayLiteral = new ArrayLiteralImpl();
    return arrayLiteral;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ControlStructure createControlStructure()
  {
    ControlStructureImpl controlStructure = new ControlStructureImpl();
    return controlStructure;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ifType createifType()
  {
    ifTypeImpl ifType = new ifTypeImpl();
    return ifType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public WhileType createWhileType()
  {
    WhileTypeImpl whileType = new WhileTypeImpl();
    return whileType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ForType createForType()
  {
    ForTypeImpl forType = new ForTypeImpl();
    return forType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public forVarDeclaration createforVarDeclaration()
  {
    forVarDeclarationImpl forVarDeclaration = new forVarDeclarationImpl();
    return forVarDeclaration;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ForeachType createForeachType()
  {
    ForeachTypeImpl foreachType = new ForeachTypeImpl();
    return foreachType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SwitchType createSwitchType()
  {
    SwitchTypeImpl switchType = new SwitchTypeImpl();
    return switchType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Method createMethod()
  {
    MethodImpl method = new MethodImpl();
    return method;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public raven.sQF.Object createObject()
  {
    ObjectImpl object = new ObjectImpl();
    return object;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ANYTHING createANYTHING()
  {
    ANYTHINGImpl anything = new ANYTHINGImpl();
    return anything;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public raven.sQF.Boolean createBoolean()
  {
    BooleanImpl boolean_ = new BooleanImpl();
    return boolean_;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public BooleanContent createBooleanContent()
  {
    BooleanContentImpl booleanContent = new BooleanContentImpl();
    return booleanContent;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public MethodName createMethodName()
  {
    MethodNameImpl methodName = new MethodNameImpl();
    return methodName;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SQFPackage getSQFPackage()
  {
    return (SQFPackage)getEPackage();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
  @Deprecated
  public static SQFPackage getPackage()
  {
    return SQFPackage.eINSTANCE;
  }

} //SQFFactoryImpl

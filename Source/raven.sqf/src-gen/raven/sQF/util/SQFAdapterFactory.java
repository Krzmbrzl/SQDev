/**
 */
package raven.sQF.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

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
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see raven.sQF.SQFPackage
 * @generated
 */
public class SQFAdapterFactory extends AdapterFactoryImpl
{
  /**
   * The cached model package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected static SQFPackage modelPackage;

  /**
   * Creates an instance of the adapter factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SQFAdapterFactory()
  {
    if (modelPackage == null)
    {
      modelPackage = SQFPackage.eINSTANCE;
    }
  }

  /**
   * Returns whether this factory is applicable for the type of the object.
   * <!-- begin-user-doc -->
   * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
   * <!-- end-user-doc -->
   * @return whether this factory is applicable for the type of the object.
   * @generated
   */
  @Override
  public boolean isFactoryForType(Object object)
  {
    if (object == modelPackage)
    {
      return true;
    }
    if (object instanceof EObject)
    {
      return ((EObject)object).eClass().getEPackage() == modelPackage;
    }
    return false;
  }

  /**
   * The switch that delegates to the <code>createXXX</code> methods.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected SQFSwitch<Adapter> modelSwitch =
    new SQFSwitch<Adapter>()
    {
      @Override
      public Adapter caseModel(Model object)
      {
        return createModelAdapter();
      }
      @Override
      public Adapter caseCode(Code object)
      {
        return createCodeAdapter();
      }
      @Override
      public Adapter caseDeclaration(Declaration object)
      {
        return createDeclarationAdapter();
      }
      @Override
      public Adapter caseBracketContent(BracketContent object)
      {
        return createBracketContentAdapter();
      }
      @Override
      public Adapter caseDecContent(DecContent object)
      {
        return createDecContentAdapter();
      }
      @Override
      public Adapter caseVarContent(VarContent object)
      {
        return createVarContentAdapter();
      }
      @Override
      public Adapter caseArrayLiteral(ArrayLiteral object)
      {
        return createArrayLiteralAdapter();
      }
      @Override
      public Adapter caseControlStructure(ControlStructure object)
      {
        return createControlStructureAdapter();
      }
      @Override
      public Adapter caseifType(ifType object)
      {
        return createifTypeAdapter();
      }
      @Override
      public Adapter caseWhileType(WhileType object)
      {
        return createWhileTypeAdapter();
      }
      @Override
      public Adapter caseForType(ForType object)
      {
        return createForTypeAdapter();
      }
      @Override
      public Adapter caseforVarDeclaration(forVarDeclaration object)
      {
        return createforVarDeclarationAdapter();
      }
      @Override
      public Adapter caseForeachType(ForeachType object)
      {
        return createForeachTypeAdapter();
      }
      @Override
      public Adapter caseSwitchType(SwitchType object)
      {
        return createSwitchTypeAdapter();
      }
      @Override
      public Adapter caseMethod(Method object)
      {
        return createMethodAdapter();
      }
      @Override
      public Adapter caseObject(raven.sQF.Object object)
      {
        return createObjectAdapter();
      }
      @Override
      public Adapter caseANYTHING(ANYTHING object)
      {
        return createANYTHINGAdapter();
      }
      @Override
      public Adapter caseBoolean(raven.sQF.Boolean object)
      {
        return createBooleanAdapter();
      }
      @Override
      public Adapter caseBooleanContent(BooleanContent object)
      {
        return createBooleanContentAdapter();
      }
      @Override
      public Adapter caseMethodName(MethodName object)
      {
        return createMethodNameAdapter();
      }
      @Override
      public Adapter defaultCase(EObject object)
      {
        return createEObjectAdapter();
      }
    };

  /**
   * Creates an adapter for the <code>target</code>.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param target the object to adapt.
   * @return the adapter for the <code>target</code>.
   * @generated
   */
  @Override
  public Adapter createAdapter(Notifier target)
  {
    return modelSwitch.doSwitch((EObject)target);
  }


  /**
   * Creates a new adapter for an object of class '{@link raven.sQF.Model <em>Model</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see raven.sQF.Model
   * @generated
   */
  public Adapter createModelAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link raven.sQF.Code <em>Code</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see raven.sQF.Code
   * @generated
   */
  public Adapter createCodeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link raven.sQF.Declaration <em>Declaration</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see raven.sQF.Declaration
   * @generated
   */
  public Adapter createDeclarationAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link raven.sQF.BracketContent <em>Bracket Content</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see raven.sQF.BracketContent
   * @generated
   */
  public Adapter createBracketContentAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link raven.sQF.DecContent <em>Dec Content</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see raven.sQF.DecContent
   * @generated
   */
  public Adapter createDecContentAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link raven.sQF.VarContent <em>Var Content</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see raven.sQF.VarContent
   * @generated
   */
  public Adapter createVarContentAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link raven.sQF.ArrayLiteral <em>Array Literal</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see raven.sQF.ArrayLiteral
   * @generated
   */
  public Adapter createArrayLiteralAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link raven.sQF.ControlStructure <em>Control Structure</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see raven.sQF.ControlStructure
   * @generated
   */
  public Adapter createControlStructureAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link raven.sQF.ifType <em>if Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see raven.sQF.ifType
   * @generated
   */
  public Adapter createifTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link raven.sQF.WhileType <em>While Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see raven.sQF.WhileType
   * @generated
   */
  public Adapter createWhileTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link raven.sQF.ForType <em>For Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see raven.sQF.ForType
   * @generated
   */
  public Adapter createForTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link raven.sQF.forVarDeclaration <em>for Var Declaration</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see raven.sQF.forVarDeclaration
   * @generated
   */
  public Adapter createforVarDeclarationAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link raven.sQF.ForeachType <em>Foreach Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see raven.sQF.ForeachType
   * @generated
   */
  public Adapter createForeachTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link raven.sQF.SwitchType <em>Switch Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see raven.sQF.SwitchType
   * @generated
   */
  public Adapter createSwitchTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link raven.sQF.Method <em>Method</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see raven.sQF.Method
   * @generated
   */
  public Adapter createMethodAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link raven.sQF.Object <em>Object</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see raven.sQF.Object
   * @generated
   */
  public Adapter createObjectAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link raven.sQF.ANYTHING <em>ANYTHING</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see raven.sQF.ANYTHING
   * @generated
   */
  public Adapter createANYTHINGAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link raven.sQF.Boolean <em>Boolean</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see raven.sQF.Boolean
   * @generated
   */
  public Adapter createBooleanAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link raven.sQF.BooleanContent <em>Boolean Content</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see raven.sQF.BooleanContent
   * @generated
   */
  public Adapter createBooleanContentAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link raven.sQF.MethodName <em>Method Name</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see raven.sQF.MethodName
   * @generated
   */
  public Adapter createMethodNameAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for the default case.
   * <!-- begin-user-doc -->
   * This default implementation returns null.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @generated
   */
  public Adapter createEObjectAdapter()
  {
    return null;
  }

} //SQFAdapterFactory

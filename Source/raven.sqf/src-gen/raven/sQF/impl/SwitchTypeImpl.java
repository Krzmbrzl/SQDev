/**
 */
package raven.sQF.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import raven.sQF.ANYTHING;
import raven.sQF.Code;
import raven.sQF.Declaration;
import raven.sQF.SQFPackage;
import raven.sQF.SwitchType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Switch Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link raven.sQF.impl.SwitchTypeImpl#getVar <em>Var</em>}</li>
 *   <li>{@link raven.sQF.impl.SwitchTypeImpl#getValue <em>Value</em>}</li>
 *   <li>{@link raven.sQF.impl.SwitchTypeImpl#getCaseCode <em>Case Code</em>}</li>
 *   <li>{@link raven.sQF.impl.SwitchTypeImpl#getDefaultCode <em>Default Code</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SwitchTypeImpl extends MinimalEObjectImpl.Container implements SwitchType
{
  /**
   * The cached value of the '{@link #getVar() <em>Var</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getVar()
   * @generated
   * @ordered
   */
  protected Declaration var;

  /**
   * The cached value of the '{@link #getValue() <em>Value</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getValue()
   * @generated
   * @ordered
   */
  protected EList<ANYTHING> value;

  /**
   * The cached value of the '{@link #getCaseCode() <em>Case Code</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCaseCode()
   * @generated
   * @ordered
   */
  protected EList<Code> caseCode;

  /**
   * The cached value of the '{@link #getDefaultCode() <em>Default Code</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDefaultCode()
   * @generated
   * @ordered
   */
  protected EList<Code> defaultCode;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected SwitchTypeImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass()
  {
    return SQFPackage.Literals.SWITCH_TYPE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Declaration getVar()
  {
    if (var != null && var.eIsProxy())
    {
      InternalEObject oldVar = (InternalEObject)var;
      var = (Declaration)eResolveProxy(oldVar);
      if (var != oldVar)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, SQFPackage.SWITCH_TYPE__VAR, oldVar, var));
      }
    }
    return var;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Declaration basicGetVar()
  {
    return var;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setVar(Declaration newVar)
  {
    Declaration oldVar = var;
    var = newVar;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SQFPackage.SWITCH_TYPE__VAR, oldVar, var));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<ANYTHING> getValue()
  {
    if (value == null)
    {
      value = new EObjectContainmentEList<ANYTHING>(ANYTHING.class, this, SQFPackage.SWITCH_TYPE__VALUE);
    }
    return value;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Code> getCaseCode()
  {
    if (caseCode == null)
    {
      caseCode = new EObjectContainmentEList<Code>(Code.class, this, SQFPackage.SWITCH_TYPE__CASE_CODE);
    }
    return caseCode;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Code> getDefaultCode()
  {
    if (defaultCode == null)
    {
      defaultCode = new EObjectContainmentEList<Code>(Code.class, this, SQFPackage.SWITCH_TYPE__DEFAULT_CODE);
    }
    return defaultCode;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case SQFPackage.SWITCH_TYPE__VALUE:
        return ((InternalEList<?>)getValue()).basicRemove(otherEnd, msgs);
      case SQFPackage.SWITCH_TYPE__CASE_CODE:
        return ((InternalEList<?>)getCaseCode()).basicRemove(otherEnd, msgs);
      case SQFPackage.SWITCH_TYPE__DEFAULT_CODE:
        return ((InternalEList<?>)getDefaultCode()).basicRemove(otherEnd, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case SQFPackage.SWITCH_TYPE__VAR:
        if (resolve) return getVar();
        return basicGetVar();
      case SQFPackage.SWITCH_TYPE__VALUE:
        return getValue();
      case SQFPackage.SWITCH_TYPE__CASE_CODE:
        return getCaseCode();
      case SQFPackage.SWITCH_TYPE__DEFAULT_CODE:
        return getDefaultCode();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case SQFPackage.SWITCH_TYPE__VAR:
        setVar((Declaration)newValue);
        return;
      case SQFPackage.SWITCH_TYPE__VALUE:
        getValue().clear();
        getValue().addAll((Collection<? extends ANYTHING>)newValue);
        return;
      case SQFPackage.SWITCH_TYPE__CASE_CODE:
        getCaseCode().clear();
        getCaseCode().addAll((Collection<? extends Code>)newValue);
        return;
      case SQFPackage.SWITCH_TYPE__DEFAULT_CODE:
        getDefaultCode().clear();
        getDefaultCode().addAll((Collection<? extends Code>)newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eUnset(int featureID)
  {
    switch (featureID)
    {
      case SQFPackage.SWITCH_TYPE__VAR:
        setVar((Declaration)null);
        return;
      case SQFPackage.SWITCH_TYPE__VALUE:
        getValue().clear();
        return;
      case SQFPackage.SWITCH_TYPE__CASE_CODE:
        getCaseCode().clear();
        return;
      case SQFPackage.SWITCH_TYPE__DEFAULT_CODE:
        getDefaultCode().clear();
        return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
    {
      case SQFPackage.SWITCH_TYPE__VAR:
        return var != null;
      case SQFPackage.SWITCH_TYPE__VALUE:
        return value != null && !value.isEmpty();
      case SQFPackage.SWITCH_TYPE__CASE_CODE:
        return caseCode != null && !caseCode.isEmpty();
      case SQFPackage.SWITCH_TYPE__DEFAULT_CODE:
        return defaultCode != null && !defaultCode.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //SwitchTypeImpl

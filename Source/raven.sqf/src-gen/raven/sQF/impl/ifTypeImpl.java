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

import raven.sQF.BooleanContent;
import raven.sQF.Code;
import raven.sQF.SQFPackage;
import raven.sQF.ifType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>if Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link raven.sQF.impl.ifTypeImpl#getCondition <em>Condition</em>}</li>
 *   <li>{@link raven.sQF.impl.ifTypeImpl#getThenCode <em>Then Code</em>}</li>
 *   <li>{@link raven.sQF.impl.ifTypeImpl#getElseCode <em>Else Code</em>}</li>
 *   <li>{@link raven.sQF.impl.ifTypeImpl#getExitCode <em>Exit Code</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ifTypeImpl extends MinimalEObjectImpl.Container implements ifType
{
  /**
   * The cached value of the '{@link #getCondition() <em>Condition</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCondition()
   * @generated
   * @ordered
   */
  protected BooleanContent condition;

  /**
   * The cached value of the '{@link #getThenCode() <em>Then Code</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getThenCode()
   * @generated
   * @ordered
   */
  protected EList<Code> thenCode;

  /**
   * The cached value of the '{@link #getElseCode() <em>Else Code</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getElseCode()
   * @generated
   * @ordered
   */
  protected EList<Code> elseCode;

  /**
   * The cached value of the '{@link #getExitCode() <em>Exit Code</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getExitCode()
   * @generated
   * @ordered
   */
  protected EList<Code> exitCode;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ifTypeImpl()
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
    return SQFPackage.Literals.IF_TYPE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public BooleanContent getCondition()
  {
    return condition;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetCondition(BooleanContent newCondition, NotificationChain msgs)
  {
    BooleanContent oldCondition = condition;
    condition = newCondition;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQFPackage.IF_TYPE__CONDITION, oldCondition, newCondition);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setCondition(BooleanContent newCondition)
  {
    if (newCondition != condition)
    {
      NotificationChain msgs = null;
      if (condition != null)
        msgs = ((InternalEObject)condition).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQFPackage.IF_TYPE__CONDITION, null, msgs);
      if (newCondition != null)
        msgs = ((InternalEObject)newCondition).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SQFPackage.IF_TYPE__CONDITION, null, msgs);
      msgs = basicSetCondition(newCondition, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SQFPackage.IF_TYPE__CONDITION, newCondition, newCondition));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Code> getThenCode()
  {
    if (thenCode == null)
    {
      thenCode = new EObjectContainmentEList<Code>(Code.class, this, SQFPackage.IF_TYPE__THEN_CODE);
    }
    return thenCode;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Code> getElseCode()
  {
    if (elseCode == null)
    {
      elseCode = new EObjectContainmentEList<Code>(Code.class, this, SQFPackage.IF_TYPE__ELSE_CODE);
    }
    return elseCode;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Code> getExitCode()
  {
    if (exitCode == null)
    {
      exitCode = new EObjectContainmentEList<Code>(Code.class, this, SQFPackage.IF_TYPE__EXIT_CODE);
    }
    return exitCode;
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
      case SQFPackage.IF_TYPE__CONDITION:
        return basicSetCondition(null, msgs);
      case SQFPackage.IF_TYPE__THEN_CODE:
        return ((InternalEList<?>)getThenCode()).basicRemove(otherEnd, msgs);
      case SQFPackage.IF_TYPE__ELSE_CODE:
        return ((InternalEList<?>)getElseCode()).basicRemove(otherEnd, msgs);
      case SQFPackage.IF_TYPE__EXIT_CODE:
        return ((InternalEList<?>)getExitCode()).basicRemove(otherEnd, msgs);
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
      case SQFPackage.IF_TYPE__CONDITION:
        return getCondition();
      case SQFPackage.IF_TYPE__THEN_CODE:
        return getThenCode();
      case SQFPackage.IF_TYPE__ELSE_CODE:
        return getElseCode();
      case SQFPackage.IF_TYPE__EXIT_CODE:
        return getExitCode();
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
      case SQFPackage.IF_TYPE__CONDITION:
        setCondition((BooleanContent)newValue);
        return;
      case SQFPackage.IF_TYPE__THEN_CODE:
        getThenCode().clear();
        getThenCode().addAll((Collection<? extends Code>)newValue);
        return;
      case SQFPackage.IF_TYPE__ELSE_CODE:
        getElseCode().clear();
        getElseCode().addAll((Collection<? extends Code>)newValue);
        return;
      case SQFPackage.IF_TYPE__EXIT_CODE:
        getExitCode().clear();
        getExitCode().addAll((Collection<? extends Code>)newValue);
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
      case SQFPackage.IF_TYPE__CONDITION:
        setCondition((BooleanContent)null);
        return;
      case SQFPackage.IF_TYPE__THEN_CODE:
        getThenCode().clear();
        return;
      case SQFPackage.IF_TYPE__ELSE_CODE:
        getElseCode().clear();
        return;
      case SQFPackage.IF_TYPE__EXIT_CODE:
        getExitCode().clear();
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
      case SQFPackage.IF_TYPE__CONDITION:
        return condition != null;
      case SQFPackage.IF_TYPE__THEN_CODE:
        return thenCode != null && !thenCode.isEmpty();
      case SQFPackage.IF_TYPE__ELSE_CODE:
        return elseCode != null && !elseCode.isEmpty();
      case SQFPackage.IF_TYPE__EXIT_CODE:
        return exitCode != null && !exitCode.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //ifTypeImpl

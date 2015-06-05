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
import raven.sQF.WhileType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>While Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link raven.sQF.impl.WhileTypeImpl#getCondition <em>Condition</em>}</li>
 *   <li>{@link raven.sQF.impl.WhileTypeImpl#getLoopCode <em>Loop Code</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class WhileTypeImpl extends MinimalEObjectImpl.Container implements WhileType
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
   * The cached value of the '{@link #getLoopCode() <em>Loop Code</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLoopCode()
   * @generated
   * @ordered
   */
  protected EList<Code> loopCode;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected WhileTypeImpl()
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
    return SQFPackage.Literals.WHILE_TYPE;
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQFPackage.WHILE_TYPE__CONDITION, oldCondition, newCondition);
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
        msgs = ((InternalEObject)condition).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQFPackage.WHILE_TYPE__CONDITION, null, msgs);
      if (newCondition != null)
        msgs = ((InternalEObject)newCondition).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SQFPackage.WHILE_TYPE__CONDITION, null, msgs);
      msgs = basicSetCondition(newCondition, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SQFPackage.WHILE_TYPE__CONDITION, newCondition, newCondition));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Code> getLoopCode()
  {
    if (loopCode == null)
    {
      loopCode = new EObjectContainmentEList<Code>(Code.class, this, SQFPackage.WHILE_TYPE__LOOP_CODE);
    }
    return loopCode;
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
      case SQFPackage.WHILE_TYPE__CONDITION:
        return basicSetCondition(null, msgs);
      case SQFPackage.WHILE_TYPE__LOOP_CODE:
        return ((InternalEList<?>)getLoopCode()).basicRemove(otherEnd, msgs);
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
      case SQFPackage.WHILE_TYPE__CONDITION:
        return getCondition();
      case SQFPackage.WHILE_TYPE__LOOP_CODE:
        return getLoopCode();
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
      case SQFPackage.WHILE_TYPE__CONDITION:
        setCondition((BooleanContent)newValue);
        return;
      case SQFPackage.WHILE_TYPE__LOOP_CODE:
        getLoopCode().clear();
        getLoopCode().addAll((Collection<? extends Code>)newValue);
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
      case SQFPackage.WHILE_TYPE__CONDITION:
        setCondition((BooleanContent)null);
        return;
      case SQFPackage.WHILE_TYPE__LOOP_CODE:
        getLoopCode().clear();
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
      case SQFPackage.WHILE_TYPE__CONDITION:
        return condition != null;
      case SQFPackage.WHILE_TYPE__LOOP_CODE:
        return loopCode != null && !loopCode.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //WhileTypeImpl

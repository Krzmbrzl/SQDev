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
import raven.sQF.Declaration;
import raven.sQF.ForType;
import raven.sQF.SQFPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>For Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link raven.sQF.impl.ForTypeImpl#getBegin <em>Begin</em>}</li>
 *   <li>{@link raven.sQF.impl.ForTypeImpl#getCondition <em>Condition</em>}</li>
 *   <li>{@link raven.sQF.impl.ForTypeImpl#getEnd <em>End</em>}</li>
 *   <li>{@link raven.sQF.impl.ForTypeImpl#getLoopCode <em>Loop Code</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ForTypeImpl extends MinimalEObjectImpl.Container implements ForType
{
  /**
   * The cached value of the '{@link #getBegin() <em>Begin</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getBegin()
   * @generated
   * @ordered
   */
  protected Declaration begin;

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
   * The cached value of the '{@link #getEnd() <em>End</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEnd()
   * @generated
   * @ordered
   */
  protected Declaration end;

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
  protected ForTypeImpl()
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
    return SQFPackage.Literals.FOR_TYPE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Declaration getBegin()
  {
    return begin;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetBegin(Declaration newBegin, NotificationChain msgs)
  {
    Declaration oldBegin = begin;
    begin = newBegin;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQFPackage.FOR_TYPE__BEGIN, oldBegin, newBegin);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setBegin(Declaration newBegin)
  {
    if (newBegin != begin)
    {
      NotificationChain msgs = null;
      if (begin != null)
        msgs = ((InternalEObject)begin).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQFPackage.FOR_TYPE__BEGIN, null, msgs);
      if (newBegin != null)
        msgs = ((InternalEObject)newBegin).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SQFPackage.FOR_TYPE__BEGIN, null, msgs);
      msgs = basicSetBegin(newBegin, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SQFPackage.FOR_TYPE__BEGIN, newBegin, newBegin));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQFPackage.FOR_TYPE__CONDITION, oldCondition, newCondition);
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
        msgs = ((InternalEObject)condition).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQFPackage.FOR_TYPE__CONDITION, null, msgs);
      if (newCondition != null)
        msgs = ((InternalEObject)newCondition).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SQFPackage.FOR_TYPE__CONDITION, null, msgs);
      msgs = basicSetCondition(newCondition, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SQFPackage.FOR_TYPE__CONDITION, newCondition, newCondition));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Declaration getEnd()
  {
    return end;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetEnd(Declaration newEnd, NotificationChain msgs)
  {
    Declaration oldEnd = end;
    end = newEnd;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQFPackage.FOR_TYPE__END, oldEnd, newEnd);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setEnd(Declaration newEnd)
  {
    if (newEnd != end)
    {
      NotificationChain msgs = null;
      if (end != null)
        msgs = ((InternalEObject)end).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQFPackage.FOR_TYPE__END, null, msgs);
      if (newEnd != null)
        msgs = ((InternalEObject)newEnd).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SQFPackage.FOR_TYPE__END, null, msgs);
      msgs = basicSetEnd(newEnd, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SQFPackage.FOR_TYPE__END, newEnd, newEnd));
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
      loopCode = new EObjectContainmentEList<Code>(Code.class, this, SQFPackage.FOR_TYPE__LOOP_CODE);
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
      case SQFPackage.FOR_TYPE__BEGIN:
        return basicSetBegin(null, msgs);
      case SQFPackage.FOR_TYPE__CONDITION:
        return basicSetCondition(null, msgs);
      case SQFPackage.FOR_TYPE__END:
        return basicSetEnd(null, msgs);
      case SQFPackage.FOR_TYPE__LOOP_CODE:
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
      case SQFPackage.FOR_TYPE__BEGIN:
        return getBegin();
      case SQFPackage.FOR_TYPE__CONDITION:
        return getCondition();
      case SQFPackage.FOR_TYPE__END:
        return getEnd();
      case SQFPackage.FOR_TYPE__LOOP_CODE:
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
      case SQFPackage.FOR_TYPE__BEGIN:
        setBegin((Declaration)newValue);
        return;
      case SQFPackage.FOR_TYPE__CONDITION:
        setCondition((BooleanContent)newValue);
        return;
      case SQFPackage.FOR_TYPE__END:
        setEnd((Declaration)newValue);
        return;
      case SQFPackage.FOR_TYPE__LOOP_CODE:
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
      case SQFPackage.FOR_TYPE__BEGIN:
        setBegin((Declaration)null);
        return;
      case SQFPackage.FOR_TYPE__CONDITION:
        setCondition((BooleanContent)null);
        return;
      case SQFPackage.FOR_TYPE__END:
        setEnd((Declaration)null);
        return;
      case SQFPackage.FOR_TYPE__LOOP_CODE:
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
      case SQFPackage.FOR_TYPE__BEGIN:
        return begin != null;
      case SQFPackage.FOR_TYPE__CONDITION:
        return condition != null;
      case SQFPackage.FOR_TYPE__END:
        return end != null;
      case SQFPackage.FOR_TYPE__LOOP_CODE:
        return loopCode != null && !loopCode.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //ForTypeImpl

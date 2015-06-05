/**
 */
package raven.sQF.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import raven.sQF.BooleanContent;
import raven.sQF.BracketContent;
import raven.sQF.SQFPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Boolean Content</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link raven.sQF.impl.BooleanContentImpl#getBoolCon <em>Bool Con</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class BooleanContentImpl extends MinimalEObjectImpl.Container implements BooleanContent
{
  /**
   * The cached value of the '{@link #getBoolCon() <em>Bool Con</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getBoolCon()
   * @generated
   * @ordered
   */
  protected BracketContent boolCon;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected BooleanContentImpl()
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
    return SQFPackage.Literals.BOOLEAN_CONTENT;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public BracketContent getBoolCon()
  {
    return boolCon;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetBoolCon(BracketContent newBoolCon, NotificationChain msgs)
  {
    BracketContent oldBoolCon = boolCon;
    boolCon = newBoolCon;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQFPackage.BOOLEAN_CONTENT__BOOL_CON, oldBoolCon, newBoolCon);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setBoolCon(BracketContent newBoolCon)
  {
    if (newBoolCon != boolCon)
    {
      NotificationChain msgs = null;
      if (boolCon != null)
        msgs = ((InternalEObject)boolCon).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQFPackage.BOOLEAN_CONTENT__BOOL_CON, null, msgs);
      if (newBoolCon != null)
        msgs = ((InternalEObject)newBoolCon).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SQFPackage.BOOLEAN_CONTENT__BOOL_CON, null, msgs);
      msgs = basicSetBoolCon(newBoolCon, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SQFPackage.BOOLEAN_CONTENT__BOOL_CON, newBoolCon, newBoolCon));
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
      case SQFPackage.BOOLEAN_CONTENT__BOOL_CON:
        return basicSetBoolCon(null, msgs);
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
      case SQFPackage.BOOLEAN_CONTENT__BOOL_CON:
        return getBoolCon();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case SQFPackage.BOOLEAN_CONTENT__BOOL_CON:
        setBoolCon((BracketContent)newValue);
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
      case SQFPackage.BOOLEAN_CONTENT__BOOL_CON:
        setBoolCon((BracketContent)null);
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
      case SQFPackage.BOOLEAN_CONTENT__BOOL_CON:
        return boolCon != null;
    }
    return super.eIsSet(featureID);
  }

} //BooleanContentImpl

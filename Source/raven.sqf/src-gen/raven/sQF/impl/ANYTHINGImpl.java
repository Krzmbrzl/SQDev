/**
 */
package raven.sQF.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import raven.sQF.ANYTHING;
import raven.sQF.Declaration;
import raven.sQF.SQFPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>ANYTHING</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link raven.sQF.impl.ANYTHINGImpl#getBool <em>Bool</em>}</li>
 *   <li>{@link raven.sQF.impl.ANYTHINGImpl#getNum <em>Num</em>}</li>
 *   <li>{@link raven.sQF.impl.ANYTHINGImpl#getString <em>String</em>}</li>
 *   <li>{@link raven.sQF.impl.ANYTHINGImpl#getReference <em>Reference</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ANYTHINGImpl extends MinimalEObjectImpl.Container implements ANYTHING
{
  /**
   * The cached value of the '{@link #getBool() <em>Bool</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getBool()
   * @generated
   * @ordered
   */
  protected raven.sQF.Boolean bool;

  /**
   * The default value of the '{@link #getNum() <em>Num</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getNum()
   * @generated
   * @ordered
   */
  protected static final String NUM_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getNum() <em>Num</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getNum()
   * @generated
   * @ordered
   */
  protected String num = NUM_EDEFAULT;

  /**
   * The default value of the '{@link #getString() <em>String</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getString()
   * @generated
   * @ordered
   */
  protected static final String STRING_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getString() <em>String</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getString()
   * @generated
   * @ordered
   */
  protected String string = STRING_EDEFAULT;

  /**
   * The cached value of the '{@link #getReference() <em>Reference</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getReference()
   * @generated
   * @ordered
   */
  protected Declaration reference;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ANYTHINGImpl()
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
    return SQFPackage.Literals.ANYTHING;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public raven.sQF.Boolean getBool()
  {
    return bool;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetBool(raven.sQF.Boolean newBool, NotificationChain msgs)
  {
    raven.sQF.Boolean oldBool = bool;
    bool = newBool;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQFPackage.ANYTHING__BOOL, oldBool, newBool);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setBool(raven.sQF.Boolean newBool)
  {
    if (newBool != bool)
    {
      NotificationChain msgs = null;
      if (bool != null)
        msgs = ((InternalEObject)bool).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQFPackage.ANYTHING__BOOL, null, msgs);
      if (newBool != null)
        msgs = ((InternalEObject)newBool).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SQFPackage.ANYTHING__BOOL, null, msgs);
      msgs = basicSetBool(newBool, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SQFPackage.ANYTHING__BOOL, newBool, newBool));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getNum()
  {
    return num;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setNum(String newNum)
  {
    String oldNum = num;
    num = newNum;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SQFPackage.ANYTHING__NUM, oldNum, num));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getString()
  {
    return string;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setString(String newString)
  {
    String oldString = string;
    string = newString;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SQFPackage.ANYTHING__STRING, oldString, string));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Declaration getReference()
  {
    if (reference != null && reference.eIsProxy())
    {
      InternalEObject oldReference = (InternalEObject)reference;
      reference = (Declaration)eResolveProxy(oldReference);
      if (reference != oldReference)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, SQFPackage.ANYTHING__REFERENCE, oldReference, reference));
      }
    }
    return reference;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Declaration basicGetReference()
  {
    return reference;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setReference(Declaration newReference)
  {
    Declaration oldReference = reference;
    reference = newReference;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SQFPackage.ANYTHING__REFERENCE, oldReference, reference));
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
      case SQFPackage.ANYTHING__BOOL:
        return basicSetBool(null, msgs);
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
      case SQFPackage.ANYTHING__BOOL:
        return getBool();
      case SQFPackage.ANYTHING__NUM:
        return getNum();
      case SQFPackage.ANYTHING__STRING:
        return getString();
      case SQFPackage.ANYTHING__REFERENCE:
        if (resolve) return getReference();
        return basicGetReference();
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
      case SQFPackage.ANYTHING__BOOL:
        setBool((raven.sQF.Boolean)newValue);
        return;
      case SQFPackage.ANYTHING__NUM:
        setNum((String)newValue);
        return;
      case SQFPackage.ANYTHING__STRING:
        setString((String)newValue);
        return;
      case SQFPackage.ANYTHING__REFERENCE:
        setReference((Declaration)newValue);
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
      case SQFPackage.ANYTHING__BOOL:
        setBool((raven.sQF.Boolean)null);
        return;
      case SQFPackage.ANYTHING__NUM:
        setNum(NUM_EDEFAULT);
        return;
      case SQFPackage.ANYTHING__STRING:
        setString(STRING_EDEFAULT);
        return;
      case SQFPackage.ANYTHING__REFERENCE:
        setReference((Declaration)null);
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
      case SQFPackage.ANYTHING__BOOL:
        return bool != null;
      case SQFPackage.ANYTHING__NUM:
        return NUM_EDEFAULT == null ? num != null : !NUM_EDEFAULT.equals(num);
      case SQFPackage.ANYTHING__STRING:
        return STRING_EDEFAULT == null ? string != null : !STRING_EDEFAULT.equals(string);
      case SQFPackage.ANYTHING__REFERENCE:
        return reference != null;
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String toString()
  {
    if (eIsProxy()) return super.toString();

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (num: ");
    result.append(num);
    result.append(", string: ");
    result.append(string);
    result.append(')');
    return result.toString();
  }

} //ANYTHINGImpl

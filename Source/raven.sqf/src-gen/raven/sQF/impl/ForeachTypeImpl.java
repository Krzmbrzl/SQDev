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

import raven.sQF.ArrayLiteral;
import raven.sQF.Code;
import raven.sQF.Declaration;
import raven.sQF.ForeachType;
import raven.sQF.SQFPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Foreach Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link raven.sQF.impl.ForeachTypeImpl#getCode <em>Code</em>}</li>
 *   <li>{@link raven.sQF.impl.ForeachTypeImpl#getArray <em>Array</em>}</li>
 *   <li>{@link raven.sQF.impl.ForeachTypeImpl#getArrayLiteral <em>Array Literal</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ForeachTypeImpl extends MinimalEObjectImpl.Container implements ForeachType
{
  /**
   * The cached value of the '{@link #getCode() <em>Code</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCode()
   * @generated
   * @ordered
   */
  protected EList<Code> code;

  /**
   * The cached value of the '{@link #getArray() <em>Array</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getArray()
   * @generated
   * @ordered
   */
  protected Declaration array;

  /**
   * The cached value of the '{@link #getArrayLiteral() <em>Array Literal</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getArrayLiteral()
   * @generated
   * @ordered
   */
  protected ArrayLiteral arrayLiteral;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ForeachTypeImpl()
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
    return SQFPackage.Literals.FOREACH_TYPE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Code> getCode()
  {
    if (code == null)
    {
      code = new EObjectContainmentEList<Code>(Code.class, this, SQFPackage.FOREACH_TYPE__CODE);
    }
    return code;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Declaration getArray()
  {
    if (array != null && array.eIsProxy())
    {
      InternalEObject oldArray = (InternalEObject)array;
      array = (Declaration)eResolveProxy(oldArray);
      if (array != oldArray)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, SQFPackage.FOREACH_TYPE__ARRAY, oldArray, array));
      }
    }
    return array;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Declaration basicGetArray()
  {
    return array;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setArray(Declaration newArray)
  {
    Declaration oldArray = array;
    array = newArray;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SQFPackage.FOREACH_TYPE__ARRAY, oldArray, array));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ArrayLiteral getArrayLiteral()
  {
    return arrayLiteral;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetArrayLiteral(ArrayLiteral newArrayLiteral, NotificationChain msgs)
  {
    ArrayLiteral oldArrayLiteral = arrayLiteral;
    arrayLiteral = newArrayLiteral;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQFPackage.FOREACH_TYPE__ARRAY_LITERAL, oldArrayLiteral, newArrayLiteral);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setArrayLiteral(ArrayLiteral newArrayLiteral)
  {
    if (newArrayLiteral != arrayLiteral)
    {
      NotificationChain msgs = null;
      if (arrayLiteral != null)
        msgs = ((InternalEObject)arrayLiteral).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQFPackage.FOREACH_TYPE__ARRAY_LITERAL, null, msgs);
      if (newArrayLiteral != null)
        msgs = ((InternalEObject)newArrayLiteral).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SQFPackage.FOREACH_TYPE__ARRAY_LITERAL, null, msgs);
      msgs = basicSetArrayLiteral(newArrayLiteral, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SQFPackage.FOREACH_TYPE__ARRAY_LITERAL, newArrayLiteral, newArrayLiteral));
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
      case SQFPackage.FOREACH_TYPE__CODE:
        return ((InternalEList<?>)getCode()).basicRemove(otherEnd, msgs);
      case SQFPackage.FOREACH_TYPE__ARRAY_LITERAL:
        return basicSetArrayLiteral(null, msgs);
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
      case SQFPackage.FOREACH_TYPE__CODE:
        return getCode();
      case SQFPackage.FOREACH_TYPE__ARRAY:
        if (resolve) return getArray();
        return basicGetArray();
      case SQFPackage.FOREACH_TYPE__ARRAY_LITERAL:
        return getArrayLiteral();
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
      case SQFPackage.FOREACH_TYPE__CODE:
        getCode().clear();
        getCode().addAll((Collection<? extends Code>)newValue);
        return;
      case SQFPackage.FOREACH_TYPE__ARRAY:
        setArray((Declaration)newValue);
        return;
      case SQFPackage.FOREACH_TYPE__ARRAY_LITERAL:
        setArrayLiteral((ArrayLiteral)newValue);
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
      case SQFPackage.FOREACH_TYPE__CODE:
        getCode().clear();
        return;
      case SQFPackage.FOREACH_TYPE__ARRAY:
        setArray((Declaration)null);
        return;
      case SQFPackage.FOREACH_TYPE__ARRAY_LITERAL:
        setArrayLiteral((ArrayLiteral)null);
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
      case SQFPackage.FOREACH_TYPE__CODE:
        return code != null && !code.isEmpty();
      case SQFPackage.FOREACH_TYPE__ARRAY:
        return array != null;
      case SQFPackage.FOREACH_TYPE__ARRAY_LITERAL:
        return arrayLiteral != null;
    }
    return super.eIsSet(featureID);
  }

} //ForeachTypeImpl

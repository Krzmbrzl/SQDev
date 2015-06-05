/**
 */
package raven.sQF.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import raven.sQF.ArrayLiteral;
import raven.sQF.Method;
import raven.sQF.MethodName;
import raven.sQF.SQFPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Method</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link raven.sQF.impl.MethodImpl#getParams <em>Params</em>}</li>
 *   <li>{@link raven.sQF.impl.MethodImpl#getExecute <em>Execute</em>}</li>
 *   <li>{@link raven.sQF.impl.MethodImpl#getMethodName <em>Method Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MethodImpl extends MinimalEObjectImpl.Container implements Method
{
  /**
   * The cached value of the '{@link #getParams() <em>Params</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getParams()
   * @generated
   * @ordered
   */
  protected ArrayLiteral params;

  /**
   * The default value of the '{@link #getExecute() <em>Execute</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getExecute()
   * @generated
   * @ordered
   */
  protected static final String EXECUTE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getExecute() <em>Execute</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getExecute()
   * @generated
   * @ordered
   */
  protected String execute = EXECUTE_EDEFAULT;

  /**
   * The cached value of the '{@link #getMethodName() <em>Method Name</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMethodName()
   * @generated
   * @ordered
   */
  protected MethodName methodName;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected MethodImpl()
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
    return SQFPackage.Literals.METHOD;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ArrayLiteral getParams()
  {
    return params;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetParams(ArrayLiteral newParams, NotificationChain msgs)
  {
    ArrayLiteral oldParams = params;
    params = newParams;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQFPackage.METHOD__PARAMS, oldParams, newParams);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setParams(ArrayLiteral newParams)
  {
    if (newParams != params)
    {
      NotificationChain msgs = null;
      if (params != null)
        msgs = ((InternalEObject)params).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQFPackage.METHOD__PARAMS, null, msgs);
      if (newParams != null)
        msgs = ((InternalEObject)newParams).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SQFPackage.METHOD__PARAMS, null, msgs);
      msgs = basicSetParams(newParams, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SQFPackage.METHOD__PARAMS, newParams, newParams));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getExecute()
  {
    return execute;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setExecute(String newExecute)
  {
    String oldExecute = execute;
    execute = newExecute;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SQFPackage.METHOD__EXECUTE, oldExecute, execute));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public MethodName getMethodName()
  {
    return methodName;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetMethodName(MethodName newMethodName, NotificationChain msgs)
  {
    MethodName oldMethodName = methodName;
    methodName = newMethodName;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQFPackage.METHOD__METHOD_NAME, oldMethodName, newMethodName);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setMethodName(MethodName newMethodName)
  {
    if (newMethodName != methodName)
    {
      NotificationChain msgs = null;
      if (methodName != null)
        msgs = ((InternalEObject)methodName).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQFPackage.METHOD__METHOD_NAME, null, msgs);
      if (newMethodName != null)
        msgs = ((InternalEObject)newMethodName).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SQFPackage.METHOD__METHOD_NAME, null, msgs);
      msgs = basicSetMethodName(newMethodName, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SQFPackage.METHOD__METHOD_NAME, newMethodName, newMethodName));
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
      case SQFPackage.METHOD__PARAMS:
        return basicSetParams(null, msgs);
      case SQFPackage.METHOD__METHOD_NAME:
        return basicSetMethodName(null, msgs);
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
      case SQFPackage.METHOD__PARAMS:
        return getParams();
      case SQFPackage.METHOD__EXECUTE:
        return getExecute();
      case SQFPackage.METHOD__METHOD_NAME:
        return getMethodName();
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
      case SQFPackage.METHOD__PARAMS:
        setParams((ArrayLiteral)newValue);
        return;
      case SQFPackage.METHOD__EXECUTE:
        setExecute((String)newValue);
        return;
      case SQFPackage.METHOD__METHOD_NAME:
        setMethodName((MethodName)newValue);
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
      case SQFPackage.METHOD__PARAMS:
        setParams((ArrayLiteral)null);
        return;
      case SQFPackage.METHOD__EXECUTE:
        setExecute(EXECUTE_EDEFAULT);
        return;
      case SQFPackage.METHOD__METHOD_NAME:
        setMethodName((MethodName)null);
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
      case SQFPackage.METHOD__PARAMS:
        return params != null;
      case SQFPackage.METHOD__EXECUTE:
        return EXECUTE_EDEFAULT == null ? execute != null : !EXECUTE_EDEFAULT.equals(execute);
      case SQFPackage.METHOD__METHOD_NAME:
        return methodName != null;
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
    result.append(" (execute: ");
    result.append(execute);
    result.append(')');
    return result.toString();
  }

} //MethodImpl

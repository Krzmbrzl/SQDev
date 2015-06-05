/**
 */
package raven.sQF.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import raven.sQF.Code;
import raven.sQF.ControlStructure;
import raven.sQF.Declaration;
import raven.sQF.Method;
import raven.sQF.SQFPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Code</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link raven.sQF.impl.CodeImpl#getDec <em>Dec</em>}</li>
 *   <li>{@link raven.sQF.impl.CodeImpl#getControl <em>Control</em>}</li>
 *   <li>{@link raven.sQF.impl.CodeImpl#getMethod <em>Method</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CodeImpl extends MinimalEObjectImpl.Container implements Code
{
  /**
   * The cached value of the '{@link #getDec() <em>Dec</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDec()
   * @generated
   * @ordered
   */
  protected Declaration dec;

  /**
   * The cached value of the '{@link #getControl() <em>Control</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getControl()
   * @generated
   * @ordered
   */
  protected ControlStructure control;

  /**
   * The cached value of the '{@link #getMethod() <em>Method</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMethod()
   * @generated
   * @ordered
   */
  protected Method method;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected CodeImpl()
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
    return SQFPackage.Literals.CODE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Declaration getDec()
  {
    return dec;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetDec(Declaration newDec, NotificationChain msgs)
  {
    Declaration oldDec = dec;
    dec = newDec;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQFPackage.CODE__DEC, oldDec, newDec);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setDec(Declaration newDec)
  {
    if (newDec != dec)
    {
      NotificationChain msgs = null;
      if (dec != null)
        msgs = ((InternalEObject)dec).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQFPackage.CODE__DEC, null, msgs);
      if (newDec != null)
        msgs = ((InternalEObject)newDec).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SQFPackage.CODE__DEC, null, msgs);
      msgs = basicSetDec(newDec, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SQFPackage.CODE__DEC, newDec, newDec));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ControlStructure getControl()
  {
    return control;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetControl(ControlStructure newControl, NotificationChain msgs)
  {
    ControlStructure oldControl = control;
    control = newControl;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQFPackage.CODE__CONTROL, oldControl, newControl);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setControl(ControlStructure newControl)
  {
    if (newControl != control)
    {
      NotificationChain msgs = null;
      if (control != null)
        msgs = ((InternalEObject)control).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQFPackage.CODE__CONTROL, null, msgs);
      if (newControl != null)
        msgs = ((InternalEObject)newControl).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SQFPackage.CODE__CONTROL, null, msgs);
      msgs = basicSetControl(newControl, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SQFPackage.CODE__CONTROL, newControl, newControl));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Method getMethod()
  {
    return method;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetMethod(Method newMethod, NotificationChain msgs)
  {
    Method oldMethod = method;
    method = newMethod;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQFPackage.CODE__METHOD, oldMethod, newMethod);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setMethod(Method newMethod)
  {
    if (newMethod != method)
    {
      NotificationChain msgs = null;
      if (method != null)
        msgs = ((InternalEObject)method).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQFPackage.CODE__METHOD, null, msgs);
      if (newMethod != null)
        msgs = ((InternalEObject)newMethod).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SQFPackage.CODE__METHOD, null, msgs);
      msgs = basicSetMethod(newMethod, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SQFPackage.CODE__METHOD, newMethod, newMethod));
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
      case SQFPackage.CODE__DEC:
        return basicSetDec(null, msgs);
      case SQFPackage.CODE__CONTROL:
        return basicSetControl(null, msgs);
      case SQFPackage.CODE__METHOD:
        return basicSetMethod(null, msgs);
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
      case SQFPackage.CODE__DEC:
        return getDec();
      case SQFPackage.CODE__CONTROL:
        return getControl();
      case SQFPackage.CODE__METHOD:
        return getMethod();
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
      case SQFPackage.CODE__DEC:
        setDec((Declaration)newValue);
        return;
      case SQFPackage.CODE__CONTROL:
        setControl((ControlStructure)newValue);
        return;
      case SQFPackage.CODE__METHOD:
        setMethod((Method)newValue);
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
      case SQFPackage.CODE__DEC:
        setDec((Declaration)null);
        return;
      case SQFPackage.CODE__CONTROL:
        setControl((ControlStructure)null);
        return;
      case SQFPackage.CODE__METHOD:
        setMethod((Method)null);
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
      case SQFPackage.CODE__DEC:
        return dec != null;
      case SQFPackage.CODE__CONTROL:
        return control != null;
      case SQFPackage.CODE__METHOD:
        return method != null;
    }
    return super.eIsSet(featureID);
  }

} //CodeImpl

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
import raven.sQF.SQFPackage;
import raven.sQF.VarContent;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Array Literal</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link raven.sQF.impl.ArrayLiteralImpl#getCon <em>Con</em>}</li>
 *   <li>{@link raven.sQF.impl.ArrayLiteralImpl#getContent <em>Content</em>}</li>
 *   <li>{@link raven.sQF.impl.ArrayLiteralImpl#getNextContent <em>Next Content</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ArrayLiteralImpl extends MinimalEObjectImpl.Container implements ArrayLiteral
{
  /**
   * The default value of the '{@link #getCon() <em>Con</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCon()
   * @generated
   * @ordered
   */
  protected static final String CON_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getCon() <em>Con</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCon()
   * @generated
   * @ordered
   */
  protected String con = CON_EDEFAULT;

  /**
   * The cached value of the '{@link #getContent() <em>Content</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getContent()
   * @generated
   * @ordered
   */
  protected VarContent content;

  /**
   * The cached value of the '{@link #getNextContent() <em>Next Content</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getNextContent()
   * @generated
   * @ordered
   */
  protected EList<VarContent> nextContent;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ArrayLiteralImpl()
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
    return SQFPackage.Literals.ARRAY_LITERAL;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getCon()
  {
    return con;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setCon(String newCon)
  {
    String oldCon = con;
    con = newCon;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SQFPackage.ARRAY_LITERAL__CON, oldCon, con));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public VarContent getContent()
  {
    return content;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetContent(VarContent newContent, NotificationChain msgs)
  {
    VarContent oldContent = content;
    content = newContent;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQFPackage.ARRAY_LITERAL__CONTENT, oldContent, newContent);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setContent(VarContent newContent)
  {
    if (newContent != content)
    {
      NotificationChain msgs = null;
      if (content != null)
        msgs = ((InternalEObject)content).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQFPackage.ARRAY_LITERAL__CONTENT, null, msgs);
      if (newContent != null)
        msgs = ((InternalEObject)newContent).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SQFPackage.ARRAY_LITERAL__CONTENT, null, msgs);
      msgs = basicSetContent(newContent, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SQFPackage.ARRAY_LITERAL__CONTENT, newContent, newContent));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<VarContent> getNextContent()
  {
    if (nextContent == null)
    {
      nextContent = new EObjectContainmentEList<VarContent>(VarContent.class, this, SQFPackage.ARRAY_LITERAL__NEXT_CONTENT);
    }
    return nextContent;
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
      case SQFPackage.ARRAY_LITERAL__CONTENT:
        return basicSetContent(null, msgs);
      case SQFPackage.ARRAY_LITERAL__NEXT_CONTENT:
        return ((InternalEList<?>)getNextContent()).basicRemove(otherEnd, msgs);
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
      case SQFPackage.ARRAY_LITERAL__CON:
        return getCon();
      case SQFPackage.ARRAY_LITERAL__CONTENT:
        return getContent();
      case SQFPackage.ARRAY_LITERAL__NEXT_CONTENT:
        return getNextContent();
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
      case SQFPackage.ARRAY_LITERAL__CON:
        setCon((String)newValue);
        return;
      case SQFPackage.ARRAY_LITERAL__CONTENT:
        setContent((VarContent)newValue);
        return;
      case SQFPackage.ARRAY_LITERAL__NEXT_CONTENT:
        getNextContent().clear();
        getNextContent().addAll((Collection<? extends VarContent>)newValue);
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
      case SQFPackage.ARRAY_LITERAL__CON:
        setCon(CON_EDEFAULT);
        return;
      case SQFPackage.ARRAY_LITERAL__CONTENT:
        setContent((VarContent)null);
        return;
      case SQFPackage.ARRAY_LITERAL__NEXT_CONTENT:
        getNextContent().clear();
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
      case SQFPackage.ARRAY_LITERAL__CON:
        return CON_EDEFAULT == null ? con != null : !CON_EDEFAULT.equals(con);
      case SQFPackage.ARRAY_LITERAL__CONTENT:
        return content != null;
      case SQFPackage.ARRAY_LITERAL__NEXT_CONTENT:
        return nextContent != null && !nextContent.isEmpty();
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
    result.append(" (con: ");
    result.append(con);
    result.append(')');
    return result.toString();
  }

} //ArrayLiteralImpl

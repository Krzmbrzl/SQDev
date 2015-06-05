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

import org.eclipse.emf.ecore.util.EDataTypeEList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import raven.sQF.BracketContent;
import raven.sQF.DecContent;
import raven.sQF.SQFPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Bracket Content</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link raven.sQF.impl.BracketContentImpl#getDecCon <em>Dec Con</em>}</li>
 *   <li>{@link raven.sQF.impl.BracketContentImpl#getComp <em>Comp</em>}</li>
 *   <li>{@link raven.sQF.impl.BracketContentImpl#getContent <em>Content</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class BracketContentImpl extends MinimalEObjectImpl.Container implements BracketContent
{
  /**
   * The cached value of the '{@link #getDecCon() <em>Dec Con</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDecCon()
   * @generated
   * @ordered
   */
  protected DecContent decCon;

  /**
   * The cached value of the '{@link #getComp() <em>Comp</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getComp()
   * @generated
   * @ordered
   */
  protected EList<String> comp;

  /**
   * The cached value of the '{@link #getContent() <em>Content</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getContent()
   * @generated
   * @ordered
   */
  protected EList<DecContent> content;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected BracketContentImpl()
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
    return SQFPackage.Literals.BRACKET_CONTENT;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DecContent getDecCon()
  {
    return decCon;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetDecCon(DecContent newDecCon, NotificationChain msgs)
  {
    DecContent oldDecCon = decCon;
    decCon = newDecCon;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQFPackage.BRACKET_CONTENT__DEC_CON, oldDecCon, newDecCon);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setDecCon(DecContent newDecCon)
  {
    if (newDecCon != decCon)
    {
      NotificationChain msgs = null;
      if (decCon != null)
        msgs = ((InternalEObject)decCon).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQFPackage.BRACKET_CONTENT__DEC_CON, null, msgs);
      if (newDecCon != null)
        msgs = ((InternalEObject)newDecCon).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SQFPackage.BRACKET_CONTENT__DEC_CON, null, msgs);
      msgs = basicSetDecCon(newDecCon, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SQFPackage.BRACKET_CONTENT__DEC_CON, newDecCon, newDecCon));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<String> getComp()
  {
    if (comp == null)
    {
      comp = new EDataTypeEList<String>(String.class, this, SQFPackage.BRACKET_CONTENT__COMP);
    }
    return comp;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<DecContent> getContent()
  {
    if (content == null)
    {
      content = new EObjectContainmentEList<DecContent>(DecContent.class, this, SQFPackage.BRACKET_CONTENT__CONTENT);
    }
    return content;
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
      case SQFPackage.BRACKET_CONTENT__DEC_CON:
        return basicSetDecCon(null, msgs);
      case SQFPackage.BRACKET_CONTENT__CONTENT:
        return ((InternalEList<?>)getContent()).basicRemove(otherEnd, msgs);
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
      case SQFPackage.BRACKET_CONTENT__DEC_CON:
        return getDecCon();
      case SQFPackage.BRACKET_CONTENT__COMP:
        return getComp();
      case SQFPackage.BRACKET_CONTENT__CONTENT:
        return getContent();
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
      case SQFPackage.BRACKET_CONTENT__DEC_CON:
        setDecCon((DecContent)newValue);
        return;
      case SQFPackage.BRACKET_CONTENT__COMP:
        getComp().clear();
        getComp().addAll((Collection<? extends String>)newValue);
        return;
      case SQFPackage.BRACKET_CONTENT__CONTENT:
        getContent().clear();
        getContent().addAll((Collection<? extends DecContent>)newValue);
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
      case SQFPackage.BRACKET_CONTENT__DEC_CON:
        setDecCon((DecContent)null);
        return;
      case SQFPackage.BRACKET_CONTENT__COMP:
        getComp().clear();
        return;
      case SQFPackage.BRACKET_CONTENT__CONTENT:
        getContent().clear();
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
      case SQFPackage.BRACKET_CONTENT__DEC_CON:
        return decCon != null;
      case SQFPackage.BRACKET_CONTENT__COMP:
        return comp != null && !comp.isEmpty();
      case SQFPackage.BRACKET_CONTENT__CONTENT:
        return content != null && !content.isEmpty();
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
    result.append(" (comp: ");
    result.append(comp);
    result.append(')');
    return result.toString();
  }

} //BracketContentImpl

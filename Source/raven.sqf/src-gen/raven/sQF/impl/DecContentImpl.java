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

import raven.sQF.DecContent;
import raven.sQF.SQFPackage;
import raven.sQF.VarContent;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Dec Content</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link raven.sQF.impl.DecContentImpl#getNeg <em>Neg</em>}</li>
 *   <li>{@link raven.sQF.impl.DecContentImpl#getSingleContent <em>Single Content</em>}</li>
 *   <li>{@link raven.sQF.impl.DecContentImpl#getOp <em>Op</em>}</li>
 *   <li>{@link raven.sQF.impl.DecContentImpl#getNextCon <em>Next Con</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DecContentImpl extends MinimalEObjectImpl.Container implements DecContent
{
  /**
   * The default value of the '{@link #getNeg() <em>Neg</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getNeg()
   * @generated
   * @ordered
   */
  protected static final String NEG_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getNeg() <em>Neg</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getNeg()
   * @generated
   * @ordered
   */
  protected String neg = NEG_EDEFAULT;

  /**
   * The cached value of the '{@link #getSingleContent() <em>Single Content</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSingleContent()
   * @generated
   * @ordered
   */
  protected VarContent singleContent;

  /**
   * The cached value of the '{@link #getOp() <em>Op</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getOp()
   * @generated
   * @ordered
   */
  protected EList<String> op;

  /**
   * The cached value of the '{@link #getNextCon() <em>Next Con</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getNextCon()
   * @generated
   * @ordered
   */
  protected EList<VarContent> nextCon;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected DecContentImpl()
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
    return SQFPackage.Literals.DEC_CONTENT;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getNeg()
  {
    return neg;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setNeg(String newNeg)
  {
    String oldNeg = neg;
    neg = newNeg;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SQFPackage.DEC_CONTENT__NEG, oldNeg, neg));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public VarContent getSingleContent()
  {
    return singleContent;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetSingleContent(VarContent newSingleContent, NotificationChain msgs)
  {
    VarContent oldSingleContent = singleContent;
    singleContent = newSingleContent;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQFPackage.DEC_CONTENT__SINGLE_CONTENT, oldSingleContent, newSingleContent);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSingleContent(VarContent newSingleContent)
  {
    if (newSingleContent != singleContent)
    {
      NotificationChain msgs = null;
      if (singleContent != null)
        msgs = ((InternalEObject)singleContent).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQFPackage.DEC_CONTENT__SINGLE_CONTENT, null, msgs);
      if (newSingleContent != null)
        msgs = ((InternalEObject)newSingleContent).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SQFPackage.DEC_CONTENT__SINGLE_CONTENT, null, msgs);
      msgs = basicSetSingleContent(newSingleContent, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SQFPackage.DEC_CONTENT__SINGLE_CONTENT, newSingleContent, newSingleContent));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<String> getOp()
  {
    if (op == null)
    {
      op = new EDataTypeEList<String>(String.class, this, SQFPackage.DEC_CONTENT__OP);
    }
    return op;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<VarContent> getNextCon()
  {
    if (nextCon == null)
    {
      nextCon = new EObjectContainmentEList<VarContent>(VarContent.class, this, SQFPackage.DEC_CONTENT__NEXT_CON);
    }
    return nextCon;
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
      case SQFPackage.DEC_CONTENT__SINGLE_CONTENT:
        return basicSetSingleContent(null, msgs);
      case SQFPackage.DEC_CONTENT__NEXT_CON:
        return ((InternalEList<?>)getNextCon()).basicRemove(otherEnd, msgs);
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
      case SQFPackage.DEC_CONTENT__NEG:
        return getNeg();
      case SQFPackage.DEC_CONTENT__SINGLE_CONTENT:
        return getSingleContent();
      case SQFPackage.DEC_CONTENT__OP:
        return getOp();
      case SQFPackage.DEC_CONTENT__NEXT_CON:
        return getNextCon();
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
      case SQFPackage.DEC_CONTENT__NEG:
        setNeg((String)newValue);
        return;
      case SQFPackage.DEC_CONTENT__SINGLE_CONTENT:
        setSingleContent((VarContent)newValue);
        return;
      case SQFPackage.DEC_CONTENT__OP:
        getOp().clear();
        getOp().addAll((Collection<? extends String>)newValue);
        return;
      case SQFPackage.DEC_CONTENT__NEXT_CON:
        getNextCon().clear();
        getNextCon().addAll((Collection<? extends VarContent>)newValue);
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
      case SQFPackage.DEC_CONTENT__NEG:
        setNeg(NEG_EDEFAULT);
        return;
      case SQFPackage.DEC_CONTENT__SINGLE_CONTENT:
        setSingleContent((VarContent)null);
        return;
      case SQFPackage.DEC_CONTENT__OP:
        getOp().clear();
        return;
      case SQFPackage.DEC_CONTENT__NEXT_CON:
        getNextCon().clear();
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
      case SQFPackage.DEC_CONTENT__NEG:
        return NEG_EDEFAULT == null ? neg != null : !NEG_EDEFAULT.equals(neg);
      case SQFPackage.DEC_CONTENT__SINGLE_CONTENT:
        return singleContent != null;
      case SQFPackage.DEC_CONTENT__OP:
        return op != null && !op.isEmpty();
      case SQFPackage.DEC_CONTENT__NEXT_CON:
        return nextCon != null && !nextCon.isEmpty();
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
    result.append(" (neg: ");
    result.append(neg);
    result.append(", op: ");
    result.append(op);
    result.append(')');
    return result.toString();
  }

} //DecContentImpl

/**
 */
package raven.sQF.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import raven.sQF.SQFPackage;
import raven.sQF.forVarDeclaration;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>for Var Declaration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link raven.sQF.impl.forVarDeclarationImpl#getFrom <em>From</em>}</li>
 *   <li>{@link raven.sQF.impl.forVarDeclarationImpl#getTo <em>To</em>}</li>
 *   <li>{@link raven.sQF.impl.forVarDeclarationImpl#getStep <em>Step</em>}</li>
 *   <li>{@link raven.sQF.impl.forVarDeclarationImpl#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class forVarDeclarationImpl extends ForTypeImpl implements forVarDeclaration
{
  /**
   * The default value of the '{@link #getFrom() <em>From</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFrom()
   * @generated
   * @ordered
   */
  protected static final String FROM_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getFrom() <em>From</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFrom()
   * @generated
   * @ordered
   */
  protected String from = FROM_EDEFAULT;

  /**
   * The default value of the '{@link #getTo() <em>To</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTo()
   * @generated
   * @ordered
   */
  protected static final String TO_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getTo() <em>To</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTo()
   * @generated
   * @ordered
   */
  protected String to = TO_EDEFAULT;

  /**
   * The default value of the '{@link #getStep() <em>Step</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getStep()
   * @generated
   * @ordered
   */
  protected static final String STEP_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getStep() <em>Step</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getStep()
   * @generated
   * @ordered
   */
  protected String step = STEP_EDEFAULT;

  /**
   * The default value of the '{@link #getName() <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
  protected static final String NAME_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
  protected String name = NAME_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected forVarDeclarationImpl()
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
    return SQFPackage.Literals.FOR_VAR_DECLARATION;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getFrom()
  {
    return from;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setFrom(String newFrom)
  {
    String oldFrom = from;
    from = newFrom;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SQFPackage.FOR_VAR_DECLARATION__FROM, oldFrom, from));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getTo()
  {
    return to;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setTo(String newTo)
  {
    String oldTo = to;
    to = newTo;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SQFPackage.FOR_VAR_DECLARATION__TO, oldTo, to));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getStep()
  {
    return step;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setStep(String newStep)
  {
    String oldStep = step;
    step = newStep;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SQFPackage.FOR_VAR_DECLARATION__STEP, oldStep, step));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getName()
  {
    return name;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setName(String newName)
  {
    String oldName = name;
    name = newName;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SQFPackage.FOR_VAR_DECLARATION__NAME, oldName, name));
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
      case SQFPackage.FOR_VAR_DECLARATION__FROM:
        return getFrom();
      case SQFPackage.FOR_VAR_DECLARATION__TO:
        return getTo();
      case SQFPackage.FOR_VAR_DECLARATION__STEP:
        return getStep();
      case SQFPackage.FOR_VAR_DECLARATION__NAME:
        return getName();
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
      case SQFPackage.FOR_VAR_DECLARATION__FROM:
        setFrom((String)newValue);
        return;
      case SQFPackage.FOR_VAR_DECLARATION__TO:
        setTo((String)newValue);
        return;
      case SQFPackage.FOR_VAR_DECLARATION__STEP:
        setStep((String)newValue);
        return;
      case SQFPackage.FOR_VAR_DECLARATION__NAME:
        setName((String)newValue);
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
      case SQFPackage.FOR_VAR_DECLARATION__FROM:
        setFrom(FROM_EDEFAULT);
        return;
      case SQFPackage.FOR_VAR_DECLARATION__TO:
        setTo(TO_EDEFAULT);
        return;
      case SQFPackage.FOR_VAR_DECLARATION__STEP:
        setStep(STEP_EDEFAULT);
        return;
      case SQFPackage.FOR_VAR_DECLARATION__NAME:
        setName(NAME_EDEFAULT);
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
      case SQFPackage.FOR_VAR_DECLARATION__FROM:
        return FROM_EDEFAULT == null ? from != null : !FROM_EDEFAULT.equals(from);
      case SQFPackage.FOR_VAR_DECLARATION__TO:
        return TO_EDEFAULT == null ? to != null : !TO_EDEFAULT.equals(to);
      case SQFPackage.FOR_VAR_DECLARATION__STEP:
        return STEP_EDEFAULT == null ? step != null : !STEP_EDEFAULT.equals(step);
      case SQFPackage.FOR_VAR_DECLARATION__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
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
    result.append(" (from: ");
    result.append(from);
    result.append(", to: ");
    result.append(to);
    result.append(", step: ");
    result.append(step);
    result.append(", name: ");
    result.append(name);
    result.append(')');
    return result.toString();
  }

} //forVarDeclarationImpl

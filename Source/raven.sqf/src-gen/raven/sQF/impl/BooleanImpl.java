/**
 */
package raven.sQF.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import raven.sQF.SQFPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Boolean</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link raven.sQF.impl.BooleanImpl#getBool <em>Bool</em>}</li>
 *   <li>{@link raven.sQF.impl.BooleanImpl#getCommand <em>Command</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class BooleanImpl extends MinimalEObjectImpl.Container implements raven.sQF.Boolean
{
  /**
   * The default value of the '{@link #getBool() <em>Bool</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getBool()
   * @generated
   * @ordered
   */
  protected static final String BOOL_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getBool() <em>Bool</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getBool()
   * @generated
   * @ordered
   */
  protected String bool = BOOL_EDEFAULT;

  /**
   * The default value of the '{@link #getCommand() <em>Command</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCommand()
   * @generated
   * @ordered
   */
  protected static final String COMMAND_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getCommand() <em>Command</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCommand()
   * @generated
   * @ordered
   */
  protected String command = COMMAND_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected BooleanImpl()
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
    return SQFPackage.Literals.BOOLEAN;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getBool()
  {
    return bool;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setBool(String newBool)
  {
    String oldBool = bool;
    bool = newBool;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SQFPackage.BOOLEAN__BOOL, oldBool, bool));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getCommand()
  {
    return command;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setCommand(String newCommand)
  {
    String oldCommand = command;
    command = newCommand;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SQFPackage.BOOLEAN__COMMAND, oldCommand, command));
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
      case SQFPackage.BOOLEAN__BOOL:
        return getBool();
      case SQFPackage.BOOLEAN__COMMAND:
        return getCommand();
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
      case SQFPackage.BOOLEAN__BOOL:
        setBool((String)newValue);
        return;
      case SQFPackage.BOOLEAN__COMMAND:
        setCommand((String)newValue);
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
      case SQFPackage.BOOLEAN__BOOL:
        setBool(BOOL_EDEFAULT);
        return;
      case SQFPackage.BOOLEAN__COMMAND:
        setCommand(COMMAND_EDEFAULT);
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
      case SQFPackage.BOOLEAN__BOOL:
        return BOOL_EDEFAULT == null ? bool != null : !BOOL_EDEFAULT.equals(bool);
      case SQFPackage.BOOLEAN__COMMAND:
        return COMMAND_EDEFAULT == null ? command != null : !COMMAND_EDEFAULT.equals(command);
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
    result.append(" (bool: ");
    result.append(bool);
    result.append(", command: ");
    result.append(command);
    result.append(')');
    return result.toString();
  }

} //BooleanImpl

/**
 */
package raven.sQF;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Boolean</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link raven.sQF.Boolean#getBool <em>Bool</em>}</li>
 *   <li>{@link raven.sQF.Boolean#getCommand <em>Command</em>}</li>
 * </ul>
 * </p>
 *
 * @see raven.sQF.SQFPackage#getBoolean()
 * @model
 * @generated
 */
public interface Boolean extends EObject
{
  /**
   * Returns the value of the '<em><b>Bool</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Bool</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Bool</em>' attribute.
   * @see #setBool(String)
   * @see raven.sQF.SQFPackage#getBoolean_Bool()
   * @model
   * @generated
   */
  String getBool();

  /**
   * Sets the value of the '{@link raven.sQF.Boolean#getBool <em>Bool</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Bool</em>' attribute.
   * @see #getBool()
   * @generated
   */
  void setBool(String value);

  /**
   * Returns the value of the '<em><b>Command</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Command</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Command</em>' attribute.
   * @see #setCommand(String)
   * @see raven.sQF.SQFPackage#getBoolean_Command()
   * @model
   * @generated
   */
  String getCommand();

  /**
   * Sets the value of the '{@link raven.sQF.Boolean#getCommand <em>Command</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Command</em>' attribute.
   * @see #getCommand()
   * @generated
   */
  void setCommand(String value);

} // Boolean

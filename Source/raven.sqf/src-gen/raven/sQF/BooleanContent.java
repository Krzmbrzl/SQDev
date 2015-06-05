/**
 */
package raven.sQF;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Boolean Content</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link raven.sQF.BooleanContent#getBoolCon <em>Bool Con</em>}</li>
 * </ul>
 * </p>
 *
 * @see raven.sQF.SQFPackage#getBooleanContent()
 * @model
 * @generated
 */
public interface BooleanContent extends EObject
{
  /**
   * Returns the value of the '<em><b>Bool Con</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Bool Con</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Bool Con</em>' containment reference.
   * @see #setBoolCon(BracketContent)
   * @see raven.sQF.SQFPackage#getBooleanContent_BoolCon()
   * @model containment="true"
   * @generated
   */
  BracketContent getBoolCon();

  /**
   * Sets the value of the '{@link raven.sQF.BooleanContent#getBoolCon <em>Bool Con</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Bool Con</em>' containment reference.
   * @see #getBoolCon()
   * @generated
   */
  void setBoolCon(BracketContent value);

} // BooleanContent

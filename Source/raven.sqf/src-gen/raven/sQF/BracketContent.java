/**
 */
package raven.sQF;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Bracket Content</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link raven.sQF.BracketContent#getDecCon <em>Dec Con</em>}</li>
 *   <li>{@link raven.sQF.BracketContent#getComp <em>Comp</em>}</li>
 *   <li>{@link raven.sQF.BracketContent#getContent <em>Content</em>}</li>
 * </ul>
 * </p>
 *
 * @see raven.sQF.SQFPackage#getBracketContent()
 * @model
 * @generated
 */
public interface BracketContent extends EObject
{
  /**
   * Returns the value of the '<em><b>Dec Con</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Dec Con</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Dec Con</em>' containment reference.
   * @see #setDecCon(DecContent)
   * @see raven.sQF.SQFPackage#getBracketContent_DecCon()
   * @model containment="true"
   * @generated
   */
  DecContent getDecCon();

  /**
   * Sets the value of the '{@link raven.sQF.BracketContent#getDecCon <em>Dec Con</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Dec Con</em>' containment reference.
   * @see #getDecCon()
   * @generated
   */
  void setDecCon(DecContent value);

  /**
   * Returns the value of the '<em><b>Comp</b></em>' attribute list.
   * The list contents are of type {@link java.lang.String}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Comp</em>' attribute list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Comp</em>' attribute list.
   * @see raven.sQF.SQFPackage#getBracketContent_Comp()
   * @model unique="false"
   * @generated
   */
  EList<String> getComp();

  /**
   * Returns the value of the '<em><b>Content</b></em>' containment reference list.
   * The list contents are of type {@link raven.sQF.DecContent}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Content</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Content</em>' containment reference list.
   * @see raven.sQF.SQFPackage#getBracketContent_Content()
   * @model containment="true"
   * @generated
   */
  EList<DecContent> getContent();

} // BracketContent

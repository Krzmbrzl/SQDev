/**
 */
package raven.sQF;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Array Literal</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link raven.sQF.ArrayLiteral#getCon <em>Con</em>}</li>
 *   <li>{@link raven.sQF.ArrayLiteral#getContent <em>Content</em>}</li>
 *   <li>{@link raven.sQF.ArrayLiteral#getNextContent <em>Next Content</em>}</li>
 * </ul>
 * </p>
 *
 * @see raven.sQF.SQFPackage#getArrayLiteral()
 * @model
 * @generated
 */
public interface ArrayLiteral extends EObject
{
  /**
   * Returns the value of the '<em><b>Con</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Con</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Con</em>' attribute.
   * @see #setCon(String)
   * @see raven.sQF.SQFPackage#getArrayLiteral_Con()
   * @model
   * @generated
   */
  String getCon();

  /**
   * Sets the value of the '{@link raven.sQF.ArrayLiteral#getCon <em>Con</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Con</em>' attribute.
   * @see #getCon()
   * @generated
   */
  void setCon(String value);

  /**
   * Returns the value of the '<em><b>Content</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Content</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Content</em>' containment reference.
   * @see #setContent(VarContent)
   * @see raven.sQF.SQFPackage#getArrayLiteral_Content()
   * @model containment="true"
   * @generated
   */
  VarContent getContent();

  /**
   * Sets the value of the '{@link raven.sQF.ArrayLiteral#getContent <em>Content</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Content</em>' containment reference.
   * @see #getContent()
   * @generated
   */
  void setContent(VarContent value);

  /**
   * Returns the value of the '<em><b>Next Content</b></em>' containment reference list.
   * The list contents are of type {@link raven.sQF.VarContent}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Next Content</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Next Content</em>' containment reference list.
   * @see raven.sQF.SQFPackage#getArrayLiteral_NextContent()
   * @model containment="true"
   * @generated
   */
  EList<VarContent> getNextContent();

} // ArrayLiteral

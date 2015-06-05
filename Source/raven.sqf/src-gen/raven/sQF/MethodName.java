/**
 */
package raven.sQF;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Method Name</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link raven.sQF.MethodName#getRef <em>Ref</em>}</li>
 * </ul>
 * </p>
 *
 * @see raven.sQF.SQFPackage#getMethodName()
 * @model
 * @generated
 */
public interface MethodName extends EObject
{
  /**
   * Returns the value of the '<em><b>Ref</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Ref</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Ref</em>' reference.
   * @see #setRef(Declaration)
   * @see raven.sQF.SQFPackage#getMethodName_Ref()
   * @model
   * @generated
   */
  Declaration getRef();

  /**
   * Sets the value of the '{@link raven.sQF.MethodName#getRef <em>Ref</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Ref</em>' reference.
   * @see #getRef()
   * @generated
   */
  void setRef(Declaration value);

} // MethodName

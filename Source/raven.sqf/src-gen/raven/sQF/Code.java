/**
 */
package raven.sQF;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Code</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link raven.sQF.Code#getDec <em>Dec</em>}</li>
 *   <li>{@link raven.sQF.Code#getControl <em>Control</em>}</li>
 *   <li>{@link raven.sQF.Code#getMethod <em>Method</em>}</li>
 * </ul>
 * </p>
 *
 * @see raven.sQF.SQFPackage#getCode()
 * @model
 * @generated
 */
public interface Code extends EObject
{
  /**
   * Returns the value of the '<em><b>Dec</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Dec</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Dec</em>' containment reference.
   * @see #setDec(Declaration)
   * @see raven.sQF.SQFPackage#getCode_Dec()
   * @model containment="true"
   * @generated
   */
  Declaration getDec();

  /**
   * Sets the value of the '{@link raven.sQF.Code#getDec <em>Dec</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Dec</em>' containment reference.
   * @see #getDec()
   * @generated
   */
  void setDec(Declaration value);

  /**
   * Returns the value of the '<em><b>Control</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Control</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Control</em>' containment reference.
   * @see #setControl(ControlStructure)
   * @see raven.sQF.SQFPackage#getCode_Control()
   * @model containment="true"
   * @generated
   */
  ControlStructure getControl();

  /**
   * Sets the value of the '{@link raven.sQF.Code#getControl <em>Control</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Control</em>' containment reference.
   * @see #getControl()
   * @generated
   */
  void setControl(ControlStructure value);

  /**
   * Returns the value of the '<em><b>Method</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Method</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Method</em>' containment reference.
   * @see #setMethod(Method)
   * @see raven.sQF.SQFPackage#getCode_Method()
   * @model containment="true"
   * @generated
   */
  Method getMethod();

  /**
   * Sets the value of the '{@link raven.sQF.Code#getMethod <em>Method</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Method</em>' containment reference.
   * @see #getMethod()
   * @generated
   */
  void setMethod(Method value);

} // Code

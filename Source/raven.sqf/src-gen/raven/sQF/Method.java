/**
 */
package raven.sQF;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Method</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link raven.sQF.Method#getParams <em>Params</em>}</li>
 *   <li>{@link raven.sQF.Method#getExecute <em>Execute</em>}</li>
 *   <li>{@link raven.sQF.Method#getMethodName <em>Method Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see raven.sQF.SQFPackage#getMethod()
 * @model
 * @generated
 */
public interface Method extends EObject
{
  /**
   * Returns the value of the '<em><b>Params</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Params</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Params</em>' containment reference.
   * @see #setParams(ArrayLiteral)
   * @see raven.sQF.SQFPackage#getMethod_Params()
   * @model containment="true"
   * @generated
   */
  ArrayLiteral getParams();

  /**
   * Sets the value of the '{@link raven.sQF.Method#getParams <em>Params</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Params</em>' containment reference.
   * @see #getParams()
   * @generated
   */
  void setParams(ArrayLiteral value);

  /**
   * Returns the value of the '<em><b>Execute</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Execute</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Execute</em>' attribute.
   * @see #setExecute(String)
   * @see raven.sQF.SQFPackage#getMethod_Execute()
   * @model
   * @generated
   */
  String getExecute();

  /**
   * Sets the value of the '{@link raven.sQF.Method#getExecute <em>Execute</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Execute</em>' attribute.
   * @see #getExecute()
   * @generated
   */
  void setExecute(String value);

  /**
   * Returns the value of the '<em><b>Method Name</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Method Name</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Method Name</em>' containment reference.
   * @see #setMethodName(MethodName)
   * @see raven.sQF.SQFPackage#getMethod_MethodName()
   * @model containment="true"
   * @generated
   */
  MethodName getMethodName();

  /**
   * Sets the value of the '{@link raven.sQF.Method#getMethodName <em>Method Name</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Method Name</em>' containment reference.
   * @see #getMethodName()
   * @generated
   */
  void setMethodName(MethodName value);

} // Method

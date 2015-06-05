/**
 */
package raven.sQF;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Foreach Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link raven.sQF.ForeachType#getCode <em>Code</em>}</li>
 *   <li>{@link raven.sQF.ForeachType#getArray <em>Array</em>}</li>
 *   <li>{@link raven.sQF.ForeachType#getArrayLiteral <em>Array Literal</em>}</li>
 * </ul>
 * </p>
 *
 * @see raven.sQF.SQFPackage#getForeachType()
 * @model
 * @generated
 */
public interface ForeachType extends EObject
{
  /**
   * Returns the value of the '<em><b>Code</b></em>' containment reference list.
   * The list contents are of type {@link raven.sQF.Code}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Code</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Code</em>' containment reference list.
   * @see raven.sQF.SQFPackage#getForeachType_Code()
   * @model containment="true"
   * @generated
   */
  EList<Code> getCode();

  /**
   * Returns the value of the '<em><b>Array</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Array</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Array</em>' reference.
   * @see #setArray(Declaration)
   * @see raven.sQF.SQFPackage#getForeachType_Array()
   * @model
   * @generated
   */
  Declaration getArray();

  /**
   * Sets the value of the '{@link raven.sQF.ForeachType#getArray <em>Array</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Array</em>' reference.
   * @see #getArray()
   * @generated
   */
  void setArray(Declaration value);

  /**
   * Returns the value of the '<em><b>Array Literal</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Array Literal</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Array Literal</em>' containment reference.
   * @see #setArrayLiteral(ArrayLiteral)
   * @see raven.sQF.SQFPackage#getForeachType_ArrayLiteral()
   * @model containment="true"
   * @generated
   */
  ArrayLiteral getArrayLiteral();

  /**
   * Sets the value of the '{@link raven.sQF.ForeachType#getArrayLiteral <em>Array Literal</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Array Literal</em>' containment reference.
   * @see #getArrayLiteral()
   * @generated
   */
  void setArrayLiteral(ArrayLiteral value);

} // ForeachType

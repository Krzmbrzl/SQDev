/**
 */
package raven.sQF;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>if Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link raven.sQF.ifType#getCondition <em>Condition</em>}</li>
 *   <li>{@link raven.sQF.ifType#getThenCode <em>Then Code</em>}</li>
 *   <li>{@link raven.sQF.ifType#getElseCode <em>Else Code</em>}</li>
 *   <li>{@link raven.sQF.ifType#getExitCode <em>Exit Code</em>}</li>
 * </ul>
 * </p>
 *
 * @see raven.sQF.SQFPackage#getifType()
 * @model
 * @generated
 */
public interface ifType extends EObject
{
  /**
   * Returns the value of the '<em><b>Condition</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Condition</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Condition</em>' containment reference.
   * @see #setCondition(BooleanContent)
   * @see raven.sQF.SQFPackage#getifType_Condition()
   * @model containment="true"
   * @generated
   */
  BooleanContent getCondition();

  /**
   * Sets the value of the '{@link raven.sQF.ifType#getCondition <em>Condition</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Condition</em>' containment reference.
   * @see #getCondition()
   * @generated
   */
  void setCondition(BooleanContent value);

  /**
   * Returns the value of the '<em><b>Then Code</b></em>' containment reference list.
   * The list contents are of type {@link raven.sQF.Code}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Then Code</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Then Code</em>' containment reference list.
   * @see raven.sQF.SQFPackage#getifType_ThenCode()
   * @model containment="true"
   * @generated
   */
  EList<Code> getThenCode();

  /**
   * Returns the value of the '<em><b>Else Code</b></em>' containment reference list.
   * The list contents are of type {@link raven.sQF.Code}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Else Code</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Else Code</em>' containment reference list.
   * @see raven.sQF.SQFPackage#getifType_ElseCode()
   * @model containment="true"
   * @generated
   */
  EList<Code> getElseCode();

  /**
   * Returns the value of the '<em><b>Exit Code</b></em>' containment reference list.
   * The list contents are of type {@link raven.sQF.Code}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Exit Code</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Exit Code</em>' containment reference list.
   * @see raven.sQF.SQFPackage#getifType_ExitCode()
   * @model containment="true"
   * @generated
   */
  EList<Code> getExitCode();

} // ifType

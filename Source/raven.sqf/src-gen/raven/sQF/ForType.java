/**
 */
package raven.sQF;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>For Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link raven.sQF.ForType#getBegin <em>Begin</em>}</li>
 *   <li>{@link raven.sQF.ForType#getCondition <em>Condition</em>}</li>
 *   <li>{@link raven.sQF.ForType#getEnd <em>End</em>}</li>
 *   <li>{@link raven.sQF.ForType#getLoopCode <em>Loop Code</em>}</li>
 * </ul>
 * </p>
 *
 * @see raven.sQF.SQFPackage#getForType()
 * @model
 * @generated
 */
public interface ForType extends EObject
{
  /**
   * Returns the value of the '<em><b>Begin</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Begin</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Begin</em>' containment reference.
   * @see #setBegin(Declaration)
   * @see raven.sQF.SQFPackage#getForType_Begin()
   * @model containment="true"
   * @generated
   */
  Declaration getBegin();

  /**
   * Sets the value of the '{@link raven.sQF.ForType#getBegin <em>Begin</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Begin</em>' containment reference.
   * @see #getBegin()
   * @generated
   */
  void setBegin(Declaration value);

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
   * @see raven.sQF.SQFPackage#getForType_Condition()
   * @model containment="true"
   * @generated
   */
  BooleanContent getCondition();

  /**
   * Sets the value of the '{@link raven.sQF.ForType#getCondition <em>Condition</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Condition</em>' containment reference.
   * @see #getCondition()
   * @generated
   */
  void setCondition(BooleanContent value);

  /**
   * Returns the value of the '<em><b>End</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>End</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>End</em>' containment reference.
   * @see #setEnd(Declaration)
   * @see raven.sQF.SQFPackage#getForType_End()
   * @model containment="true"
   * @generated
   */
  Declaration getEnd();

  /**
   * Sets the value of the '{@link raven.sQF.ForType#getEnd <em>End</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>End</em>' containment reference.
   * @see #getEnd()
   * @generated
   */
  void setEnd(Declaration value);

  /**
   * Returns the value of the '<em><b>Loop Code</b></em>' containment reference list.
   * The list contents are of type {@link raven.sQF.Code}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Loop Code</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Loop Code</em>' containment reference list.
   * @see raven.sQF.SQFPackage#getForType_LoopCode()
   * @model containment="true"
   * @generated
   */
  EList<Code> getLoopCode();

} // ForType

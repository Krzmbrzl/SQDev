/**
 */
package raven.sQF;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Switch Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link raven.sQF.SwitchType#getVar <em>Var</em>}</li>
 *   <li>{@link raven.sQF.SwitchType#getValue <em>Value</em>}</li>
 *   <li>{@link raven.sQF.SwitchType#getCaseCode <em>Case Code</em>}</li>
 *   <li>{@link raven.sQF.SwitchType#getDefaultCode <em>Default Code</em>}</li>
 * </ul>
 * </p>
 *
 * @see raven.sQF.SQFPackage#getSwitchType()
 * @model
 * @generated
 */
public interface SwitchType extends EObject
{
  /**
   * Returns the value of the '<em><b>Var</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Var</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Var</em>' reference.
   * @see #setVar(Declaration)
   * @see raven.sQF.SQFPackage#getSwitchType_Var()
   * @model
   * @generated
   */
  Declaration getVar();

  /**
   * Sets the value of the '{@link raven.sQF.SwitchType#getVar <em>Var</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Var</em>' reference.
   * @see #getVar()
   * @generated
   */
  void setVar(Declaration value);

  /**
   * Returns the value of the '<em><b>Value</b></em>' containment reference list.
   * The list contents are of type {@link raven.sQF.ANYTHING}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Value</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Value</em>' containment reference list.
   * @see raven.sQF.SQFPackage#getSwitchType_Value()
   * @model containment="true"
   * @generated
   */
  EList<ANYTHING> getValue();

  /**
   * Returns the value of the '<em><b>Case Code</b></em>' containment reference list.
   * The list contents are of type {@link raven.sQF.Code}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Case Code</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Case Code</em>' containment reference list.
   * @see raven.sQF.SQFPackage#getSwitchType_CaseCode()
   * @model containment="true"
   * @generated
   */
  EList<Code> getCaseCode();

  /**
   * Returns the value of the '<em><b>Default Code</b></em>' containment reference list.
   * The list contents are of type {@link raven.sQF.Code}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Default Code</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Default Code</em>' containment reference list.
   * @see raven.sQF.SQFPackage#getSwitchType_DefaultCode()
   * @model containment="true"
   * @generated
   */
  EList<Code> getDefaultCode();

} // SwitchType

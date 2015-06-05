/**
 */
package raven.sQF;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Control Structure</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link raven.sQF.ControlStructure#getIfStat <em>If Stat</em>}</li>
 *   <li>{@link raven.sQF.ControlStructure#getWhileStat <em>While Stat</em>}</li>
 *   <li>{@link raven.sQF.ControlStructure#getForStat <em>For Stat</em>}</li>
 *   <li>{@link raven.sQF.ControlStructure#getForEachStat <em>For Each Stat</em>}</li>
 *   <li>{@link raven.sQF.ControlStructure#getSwitchStat <em>Switch Stat</em>}</li>
 * </ul>
 * </p>
 *
 * @see raven.sQF.SQFPackage#getControlStructure()
 * @model
 * @generated
 */
public interface ControlStructure extends EObject
{
  /**
   * Returns the value of the '<em><b>If Stat</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>If Stat</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>If Stat</em>' containment reference.
   * @see #setIfStat(ifType)
   * @see raven.sQF.SQFPackage#getControlStructure_IfStat()
   * @model containment="true"
   * @generated
   */
  ifType getIfStat();

  /**
   * Sets the value of the '{@link raven.sQF.ControlStructure#getIfStat <em>If Stat</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>If Stat</em>' containment reference.
   * @see #getIfStat()
   * @generated
   */
  void setIfStat(ifType value);

  /**
   * Returns the value of the '<em><b>While Stat</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>While Stat</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>While Stat</em>' containment reference.
   * @see #setWhileStat(WhileType)
   * @see raven.sQF.SQFPackage#getControlStructure_WhileStat()
   * @model containment="true"
   * @generated
   */
  WhileType getWhileStat();

  /**
   * Sets the value of the '{@link raven.sQF.ControlStructure#getWhileStat <em>While Stat</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>While Stat</em>' containment reference.
   * @see #getWhileStat()
   * @generated
   */
  void setWhileStat(WhileType value);

  /**
   * Returns the value of the '<em><b>For Stat</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>For Stat</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>For Stat</em>' containment reference.
   * @see #setForStat(ForType)
   * @see raven.sQF.SQFPackage#getControlStructure_ForStat()
   * @model containment="true"
   * @generated
   */
  ForType getForStat();

  /**
   * Sets the value of the '{@link raven.sQF.ControlStructure#getForStat <em>For Stat</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>For Stat</em>' containment reference.
   * @see #getForStat()
   * @generated
   */
  void setForStat(ForType value);

  /**
   * Returns the value of the '<em><b>For Each Stat</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>For Each Stat</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>For Each Stat</em>' containment reference.
   * @see #setForEachStat(ForeachType)
   * @see raven.sQF.SQFPackage#getControlStructure_ForEachStat()
   * @model containment="true"
   * @generated
   */
  ForeachType getForEachStat();

  /**
   * Sets the value of the '{@link raven.sQF.ControlStructure#getForEachStat <em>For Each Stat</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>For Each Stat</em>' containment reference.
   * @see #getForEachStat()
   * @generated
   */
  void setForEachStat(ForeachType value);

  /**
   * Returns the value of the '<em><b>Switch Stat</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Switch Stat</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Switch Stat</em>' containment reference.
   * @see #setSwitchStat(SwitchType)
   * @see raven.sQF.SQFPackage#getControlStructure_SwitchStat()
   * @model containment="true"
   * @generated
   */
  SwitchType getSwitchStat();

  /**
   * Sets the value of the '{@link raven.sQF.ControlStructure#getSwitchStat <em>Switch Stat</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Switch Stat</em>' containment reference.
   * @see #getSwitchStat()
   * @generated
   */
  void setSwitchStat(SwitchType value);

} // ControlStructure

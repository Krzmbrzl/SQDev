/**
 */
package raven.sQF;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Dec Content</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link raven.sQF.DecContent#getNeg <em>Neg</em>}</li>
 *   <li>{@link raven.sQF.DecContent#getSingleContent <em>Single Content</em>}</li>
 *   <li>{@link raven.sQF.DecContent#getOp <em>Op</em>}</li>
 *   <li>{@link raven.sQF.DecContent#getNextCon <em>Next Con</em>}</li>
 * </ul>
 * </p>
 *
 * @see raven.sQF.SQFPackage#getDecContent()
 * @model
 * @generated
 */
public interface DecContent extends EObject
{
  /**
   * Returns the value of the '<em><b>Neg</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Neg</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Neg</em>' attribute.
   * @see #setNeg(String)
   * @see raven.sQF.SQFPackage#getDecContent_Neg()
   * @model
   * @generated
   */
  String getNeg();

  /**
   * Sets the value of the '{@link raven.sQF.DecContent#getNeg <em>Neg</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Neg</em>' attribute.
   * @see #getNeg()
   * @generated
   */
  void setNeg(String value);

  /**
   * Returns the value of the '<em><b>Single Content</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Single Content</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Single Content</em>' containment reference.
   * @see #setSingleContent(VarContent)
   * @see raven.sQF.SQFPackage#getDecContent_SingleContent()
   * @model containment="true"
   * @generated
   */
  VarContent getSingleContent();

  /**
   * Sets the value of the '{@link raven.sQF.DecContent#getSingleContent <em>Single Content</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Single Content</em>' containment reference.
   * @see #getSingleContent()
   * @generated
   */
  void setSingleContent(VarContent value);

  /**
   * Returns the value of the '<em><b>Op</b></em>' attribute list.
   * The list contents are of type {@link java.lang.String}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Op</em>' attribute list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Op</em>' attribute list.
   * @see raven.sQF.SQFPackage#getDecContent_Op()
   * @model unique="false"
   * @generated
   */
  EList<String> getOp();

  /**
   * Returns the value of the '<em><b>Next Con</b></em>' containment reference list.
   * The list contents are of type {@link raven.sQF.VarContent}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Next Con</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Next Con</em>' containment reference list.
   * @see raven.sQF.SQFPackage#getDecContent_NextCon()
   * @model containment="true"
   * @generated
   */
  EList<VarContent> getNextCon();

} // DecContent

/**
 */
package raven.sQF;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Declaration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link raven.sQF.Declaration#getName <em>Name</em>}</li>
 *   <li>{@link raven.sQF.Declaration#getBrCon <em>Br Con</em>}</li>
 *   <li>{@link raven.sQF.Declaration#getDeclaration <em>Declaration</em>}</li>
 *   <li>{@link raven.sQF.Declaration#getLoopDeclaration <em>Loop Declaration</em>}</li>
 * </ul>
 * </p>
 *
 * @see raven.sQF.SQFPackage#getDeclaration()
 * @model
 * @generated
 */
public interface Declaration extends EObject
{
  /**
   * Returns the value of the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Name</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Name</em>' attribute.
   * @see #setName(String)
   * @see raven.sQF.SQFPackage#getDeclaration_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link raven.sQF.Declaration#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Br Con</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Br Con</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Br Con</em>' containment reference.
   * @see #setBrCon(BracketContent)
   * @see raven.sQF.SQFPackage#getDeclaration_BrCon()
   * @model containment="true"
   * @generated
   */
  BracketContent getBrCon();

  /**
   * Sets the value of the '{@link raven.sQF.Declaration#getBrCon <em>Br Con</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Br Con</em>' containment reference.
   * @see #getBrCon()
   * @generated
   */
  void setBrCon(BracketContent value);

  /**
   * Returns the value of the '<em><b>Declaration</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Declaration</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Declaration</em>' containment reference.
   * @see #setDeclaration(Declaration)
   * @see raven.sQF.SQFPackage#getDeclaration_Declaration()
   * @model containment="true"
   * @generated
   */
  Declaration getDeclaration();

  /**
   * Sets the value of the '{@link raven.sQF.Declaration#getDeclaration <em>Declaration</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Declaration</em>' containment reference.
   * @see #getDeclaration()
   * @generated
   */
  void setDeclaration(Declaration value);

  /**
   * Returns the value of the '<em><b>Loop Declaration</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Loop Declaration</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Loop Declaration</em>' containment reference.
   * @see #setLoopDeclaration(forVarDeclaration)
   * @see raven.sQF.SQFPackage#getDeclaration_LoopDeclaration()
   * @model containment="true"
   * @generated
   */
  forVarDeclaration getLoopDeclaration();

  /**
   * Sets the value of the '{@link raven.sQF.Declaration#getLoopDeclaration <em>Loop Declaration</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Loop Declaration</em>' containment reference.
   * @see #getLoopDeclaration()
   * @generated
   */
  void setLoopDeclaration(forVarDeclaration value);

} // Declaration

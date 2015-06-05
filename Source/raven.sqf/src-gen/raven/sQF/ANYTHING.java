/**
 */
package raven.sQF;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>ANYTHING</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link raven.sQF.ANYTHING#getBool <em>Bool</em>}</li>
 *   <li>{@link raven.sQF.ANYTHING#getNum <em>Num</em>}</li>
 *   <li>{@link raven.sQF.ANYTHING#getString <em>String</em>}</li>
 *   <li>{@link raven.sQF.ANYTHING#getReference <em>Reference</em>}</li>
 * </ul>
 * </p>
 *
 * @see raven.sQF.SQFPackage#getANYTHING()
 * @model
 * @generated
 */
public interface ANYTHING extends EObject
{
  /**
   * Returns the value of the '<em><b>Bool</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Bool</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Bool</em>' containment reference.
   * @see #setBool(raven.sQF.Boolean)
   * @see raven.sQF.SQFPackage#getANYTHING_Bool()
   * @model containment="true"
   * @generated
   */
  raven.sQF.Boolean getBool();

  /**
   * Sets the value of the '{@link raven.sQF.ANYTHING#getBool <em>Bool</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Bool</em>' containment reference.
   * @see #getBool()
   * @generated
   */
  void setBool(raven.sQF.Boolean value);

  /**
   * Returns the value of the '<em><b>Num</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Num</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Num</em>' attribute.
   * @see #setNum(String)
   * @see raven.sQF.SQFPackage#getANYTHING_Num()
   * @model
   * @generated
   */
  String getNum();

  /**
   * Sets the value of the '{@link raven.sQF.ANYTHING#getNum <em>Num</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Num</em>' attribute.
   * @see #getNum()
   * @generated
   */
  void setNum(String value);

  /**
   * Returns the value of the '<em><b>String</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>String</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>String</em>' attribute.
   * @see #setString(String)
   * @see raven.sQF.SQFPackage#getANYTHING_String()
   * @model
   * @generated
   */
  String getString();

  /**
   * Sets the value of the '{@link raven.sQF.ANYTHING#getString <em>String</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>String</em>' attribute.
   * @see #getString()
   * @generated
   */
  void setString(String value);

  /**
   * Returns the value of the '<em><b>Reference</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Reference</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Reference</em>' reference.
   * @see #setReference(Declaration)
   * @see raven.sQF.SQFPackage#getANYTHING_Reference()
   * @model
   * @generated
   */
  Declaration getReference();

  /**
   * Sets the value of the '{@link raven.sQF.ANYTHING#getReference <em>Reference</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Reference</em>' reference.
   * @see #getReference()
   * @generated
   */
  void setReference(Declaration value);

} // ANYTHING

/**
 */
package raven.sQF;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Var Content</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link raven.sQF.VarContent#getUnOP <em>Un OP</em>}</li>
 *   <li>{@link raven.sQF.VarContent#getNum <em>Num</em>}</li>
 *   <li>{@link raven.sQF.VarContent#getString <em>String</em>}</li>
 *   <li>{@link raven.sQF.VarContent#getReference <em>Reference</em>}</li>
 *   <li>{@link raven.sQF.VarContent#getForEachVar <em>For Each Var</em>}</li>
 *   <li>{@link raven.sQF.VarContent#getSel <em>Sel</em>}</li>
 *   <li>{@link raven.sQF.VarContent#getIndex <em>Index</em>}</li>
 *   <li>{@link raven.sQF.VarContent#getArrayContent <em>Array Content</em>}</li>
 *   <li>{@link raven.sQF.VarContent#getExecute <em>Execute</em>}</li>
 *   <li>{@link raven.sQF.VarContent#getMethodName <em>Method Name</em>}</li>
 *   <li>{@link raven.sQF.VarContent#getEmbraced <em>Embraced</em>}</li>
 *   <li>{@link raven.sQF.VarContent#getEmbrCon <em>Embr Con</em>}</li>
 *   <li>{@link raven.sQF.VarContent#getBool <em>Bool</em>}</li>
 *   <li>{@link raven.sQF.VarContent#getParam <em>Param</em>}</li>
 * </ul>
 * </p>
 *
 * @see raven.sQF.SQFPackage#getVarContent()
 * @model
 * @generated
 */
public interface VarContent extends EObject
{
  /**
   * Returns the value of the '<em><b>Un OP</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Un OP</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Un OP</em>' attribute.
   * @see #setUnOP(String)
   * @see raven.sQF.SQFPackage#getVarContent_UnOP()
   * @model
   * @generated
   */
  String getUnOP();

  /**
   * Sets the value of the '{@link raven.sQF.VarContent#getUnOP <em>Un OP</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Un OP</em>' attribute.
   * @see #getUnOP()
   * @generated
   */
  void setUnOP(String value);

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
   * @see raven.sQF.SQFPackage#getVarContent_Num()
   * @model
   * @generated
   */
  String getNum();

  /**
   * Sets the value of the '{@link raven.sQF.VarContent#getNum <em>Num</em>}' attribute.
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
   * @see raven.sQF.SQFPackage#getVarContent_String()
   * @model
   * @generated
   */
  String getString();

  /**
   * Sets the value of the '{@link raven.sQF.VarContent#getString <em>String</em>}' attribute.
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
   * @see raven.sQF.SQFPackage#getVarContent_Reference()
   * @model
   * @generated
   */
  Declaration getReference();

  /**
   * Sets the value of the '{@link raven.sQF.VarContent#getReference <em>Reference</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Reference</em>' reference.
   * @see #getReference()
   * @generated
   */
  void setReference(Declaration value);

  /**
   * Returns the value of the '<em><b>For Each Var</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>For Each Var</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>For Each Var</em>' attribute.
   * @see #setForEachVar(String)
   * @see raven.sQF.SQFPackage#getVarContent_ForEachVar()
   * @model
   * @generated
   */
  String getForEachVar();

  /**
   * Sets the value of the '{@link raven.sQF.VarContent#getForEachVar <em>For Each Var</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>For Each Var</em>' attribute.
   * @see #getForEachVar()
   * @generated
   */
  void setForEachVar(String value);

  /**
   * Returns the value of the '<em><b>Sel</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Sel</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Sel</em>' attribute.
   * @see #setSel(String)
   * @see raven.sQF.SQFPackage#getVarContent_Sel()
   * @model
   * @generated
   */
  String getSel();

  /**
   * Sets the value of the '{@link raven.sQF.VarContent#getSel <em>Sel</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Sel</em>' attribute.
   * @see #getSel()
   * @generated
   */
  void setSel(String value);

  /**
   * Returns the value of the '<em><b>Index</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Index</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Index</em>' attribute.
   * @see #setIndex(String)
   * @see raven.sQF.SQFPackage#getVarContent_Index()
   * @model
   * @generated
   */
  String getIndex();

  /**
   * Sets the value of the '{@link raven.sQF.VarContent#getIndex <em>Index</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Index</em>' attribute.
   * @see #getIndex()
   * @generated
   */
  void setIndex(String value);

  /**
   * Returns the value of the '<em><b>Array Content</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Array Content</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Array Content</em>' containment reference.
   * @see #setArrayContent(ArrayLiteral)
   * @see raven.sQF.SQFPackage#getVarContent_ArrayContent()
   * @model containment="true"
   * @generated
   */
  ArrayLiteral getArrayContent();

  /**
   * Sets the value of the '{@link raven.sQF.VarContent#getArrayContent <em>Array Content</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Array Content</em>' containment reference.
   * @see #getArrayContent()
   * @generated
   */
  void setArrayContent(ArrayLiteral value);

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
   * @see raven.sQF.SQFPackage#getVarContent_Execute()
   * @model
   * @generated
   */
  String getExecute();

  /**
   * Sets the value of the '{@link raven.sQF.VarContent#getExecute <em>Execute</em>}' attribute.
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
   * @see raven.sQF.SQFPackage#getVarContent_MethodName()
   * @model containment="true"
   * @generated
   */
  MethodName getMethodName();

  /**
   * Sets the value of the '{@link raven.sQF.VarContent#getMethodName <em>Method Name</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Method Name</em>' containment reference.
   * @see #getMethodName()
   * @generated
   */
  void setMethodName(MethodName value);

  /**
   * Returns the value of the '<em><b>Embraced</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Embraced</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Embraced</em>' attribute.
   * @see #setEmbraced(String)
   * @see raven.sQF.SQFPackage#getVarContent_Embraced()
   * @model
   * @generated
   */
  String getEmbraced();

  /**
   * Sets the value of the '{@link raven.sQF.VarContent#getEmbraced <em>Embraced</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Embraced</em>' attribute.
   * @see #getEmbraced()
   * @generated
   */
  void setEmbraced(String value);

  /**
   * Returns the value of the '<em><b>Embr Con</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Embr Con</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Embr Con</em>' containment reference.
   * @see #setEmbrCon(BracketContent)
   * @see raven.sQF.SQFPackage#getVarContent_EmbrCon()
   * @model containment="true"
   * @generated
   */
  BracketContent getEmbrCon();

  /**
   * Sets the value of the '{@link raven.sQF.VarContent#getEmbrCon <em>Embr Con</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Embr Con</em>' containment reference.
   * @see #getEmbrCon()
   * @generated
   */
  void setEmbrCon(BracketContent value);

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
   * @see raven.sQF.SQFPackage#getVarContent_Bool()
   * @model containment="true"
   * @generated
   */
  raven.sQF.Boolean getBool();

  /**
   * Sets the value of the '{@link raven.sQF.VarContent#getBool <em>Bool</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Bool</em>' containment reference.
   * @see #getBool()
   * @generated
   */
  void setBool(raven.sQF.Boolean value);

  /**
   * Returns the value of the '<em><b>Param</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Param</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Param</em>' attribute.
   * @see #setParam(String)
   * @see raven.sQF.SQFPackage#getVarContent_Param()
   * @model
   * @generated
   */
  String getParam();

  /**
   * Sets the value of the '{@link raven.sQF.VarContent#getParam <em>Param</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Param</em>' attribute.
   * @see #getParam()
   * @generated
   */
  void setParam(String value);

} // VarContent

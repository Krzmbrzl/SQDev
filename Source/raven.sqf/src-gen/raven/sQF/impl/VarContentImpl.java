/**
 */
package raven.sQF.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import raven.sQF.ArrayLiteral;
import raven.sQF.BracketContent;
import raven.sQF.Declaration;
import raven.sQF.MethodName;
import raven.sQF.SQFPackage;
import raven.sQF.VarContent;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Var Content</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link raven.sQF.impl.VarContentImpl#getUnOP <em>Un OP</em>}</li>
 *   <li>{@link raven.sQF.impl.VarContentImpl#getNum <em>Num</em>}</li>
 *   <li>{@link raven.sQF.impl.VarContentImpl#getString <em>String</em>}</li>
 *   <li>{@link raven.sQF.impl.VarContentImpl#getReference <em>Reference</em>}</li>
 *   <li>{@link raven.sQF.impl.VarContentImpl#getForEachVar <em>For Each Var</em>}</li>
 *   <li>{@link raven.sQF.impl.VarContentImpl#getSel <em>Sel</em>}</li>
 *   <li>{@link raven.sQF.impl.VarContentImpl#getIndex <em>Index</em>}</li>
 *   <li>{@link raven.sQF.impl.VarContentImpl#getArrayContent <em>Array Content</em>}</li>
 *   <li>{@link raven.sQF.impl.VarContentImpl#getExecute <em>Execute</em>}</li>
 *   <li>{@link raven.sQF.impl.VarContentImpl#getMethodName <em>Method Name</em>}</li>
 *   <li>{@link raven.sQF.impl.VarContentImpl#getEmbraced <em>Embraced</em>}</li>
 *   <li>{@link raven.sQF.impl.VarContentImpl#getEmbrCon <em>Embr Con</em>}</li>
 *   <li>{@link raven.sQF.impl.VarContentImpl#getBool <em>Bool</em>}</li>
 *   <li>{@link raven.sQF.impl.VarContentImpl#getParam <em>Param</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class VarContentImpl extends MinimalEObjectImpl.Container implements VarContent
{
  /**
   * The default value of the '{@link #getUnOP() <em>Un OP</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getUnOP()
   * @generated
   * @ordered
   */
  protected static final String UN_OP_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getUnOP() <em>Un OP</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getUnOP()
   * @generated
   * @ordered
   */
  protected String unOP = UN_OP_EDEFAULT;

  /**
   * The default value of the '{@link #getNum() <em>Num</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getNum()
   * @generated
   * @ordered
   */
  protected static final String NUM_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getNum() <em>Num</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getNum()
   * @generated
   * @ordered
   */
  protected String num = NUM_EDEFAULT;

  /**
   * The default value of the '{@link #getString() <em>String</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getString()
   * @generated
   * @ordered
   */
  protected static final String STRING_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getString() <em>String</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getString()
   * @generated
   * @ordered
   */
  protected String string = STRING_EDEFAULT;

  /**
   * The cached value of the '{@link #getReference() <em>Reference</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getReference()
   * @generated
   * @ordered
   */
  protected Declaration reference;

  /**
   * The default value of the '{@link #getForEachVar() <em>For Each Var</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getForEachVar()
   * @generated
   * @ordered
   */
  protected static final String FOR_EACH_VAR_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getForEachVar() <em>For Each Var</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getForEachVar()
   * @generated
   * @ordered
   */
  protected String forEachVar = FOR_EACH_VAR_EDEFAULT;

  /**
   * The default value of the '{@link #getSel() <em>Sel</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSel()
   * @generated
   * @ordered
   */
  protected static final String SEL_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getSel() <em>Sel</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSel()
   * @generated
   * @ordered
   */
  protected String sel = SEL_EDEFAULT;

  /**
   * The default value of the '{@link #getIndex() <em>Index</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getIndex()
   * @generated
   * @ordered
   */
  protected static final String INDEX_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getIndex() <em>Index</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getIndex()
   * @generated
   * @ordered
   */
  protected String index = INDEX_EDEFAULT;

  /**
   * The cached value of the '{@link #getArrayContent() <em>Array Content</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getArrayContent()
   * @generated
   * @ordered
   */
  protected ArrayLiteral arrayContent;

  /**
   * The default value of the '{@link #getExecute() <em>Execute</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getExecute()
   * @generated
   * @ordered
   */
  protected static final String EXECUTE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getExecute() <em>Execute</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getExecute()
   * @generated
   * @ordered
   */
  protected String execute = EXECUTE_EDEFAULT;

  /**
   * The cached value of the '{@link #getMethodName() <em>Method Name</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMethodName()
   * @generated
   * @ordered
   */
  protected MethodName methodName;

  /**
   * The default value of the '{@link #getEmbraced() <em>Embraced</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEmbraced()
   * @generated
   * @ordered
   */
  protected static final String EMBRACED_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getEmbraced() <em>Embraced</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEmbraced()
   * @generated
   * @ordered
   */
  protected String embraced = EMBRACED_EDEFAULT;

  /**
   * The cached value of the '{@link #getEmbrCon() <em>Embr Con</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEmbrCon()
   * @generated
   * @ordered
   */
  protected BracketContent embrCon;

  /**
   * The cached value of the '{@link #getBool() <em>Bool</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getBool()
   * @generated
   * @ordered
   */
  protected raven.sQF.Boolean bool;

  /**
   * The default value of the '{@link #getParam() <em>Param</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getParam()
   * @generated
   * @ordered
   */
  protected static final String PARAM_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getParam() <em>Param</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getParam()
   * @generated
   * @ordered
   */
  protected String param = PARAM_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected VarContentImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass()
  {
    return SQFPackage.Literals.VAR_CONTENT;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getUnOP()
  {
    return unOP;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setUnOP(String newUnOP)
  {
    String oldUnOP = unOP;
    unOP = newUnOP;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SQFPackage.VAR_CONTENT__UN_OP, oldUnOP, unOP));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getNum()
  {
    return num;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setNum(String newNum)
  {
    String oldNum = num;
    num = newNum;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SQFPackage.VAR_CONTENT__NUM, oldNum, num));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getString()
  {
    return string;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setString(String newString)
  {
    String oldString = string;
    string = newString;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SQFPackage.VAR_CONTENT__STRING, oldString, string));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Declaration getReference()
  {
    if (reference != null && reference.eIsProxy())
    {
      InternalEObject oldReference = (InternalEObject)reference;
      reference = (Declaration)eResolveProxy(oldReference);
      if (reference != oldReference)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, SQFPackage.VAR_CONTENT__REFERENCE, oldReference, reference));
      }
    }
    return reference;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Declaration basicGetReference()
  {
    return reference;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setReference(Declaration newReference)
  {
    Declaration oldReference = reference;
    reference = newReference;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SQFPackage.VAR_CONTENT__REFERENCE, oldReference, reference));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getForEachVar()
  {
    return forEachVar;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setForEachVar(String newForEachVar)
  {
    String oldForEachVar = forEachVar;
    forEachVar = newForEachVar;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SQFPackage.VAR_CONTENT__FOR_EACH_VAR, oldForEachVar, forEachVar));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getSel()
  {
    return sel;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSel(String newSel)
  {
    String oldSel = sel;
    sel = newSel;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SQFPackage.VAR_CONTENT__SEL, oldSel, sel));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getIndex()
  {
    return index;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setIndex(String newIndex)
  {
    String oldIndex = index;
    index = newIndex;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SQFPackage.VAR_CONTENT__INDEX, oldIndex, index));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ArrayLiteral getArrayContent()
  {
    return arrayContent;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetArrayContent(ArrayLiteral newArrayContent, NotificationChain msgs)
  {
    ArrayLiteral oldArrayContent = arrayContent;
    arrayContent = newArrayContent;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQFPackage.VAR_CONTENT__ARRAY_CONTENT, oldArrayContent, newArrayContent);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setArrayContent(ArrayLiteral newArrayContent)
  {
    if (newArrayContent != arrayContent)
    {
      NotificationChain msgs = null;
      if (arrayContent != null)
        msgs = ((InternalEObject)arrayContent).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQFPackage.VAR_CONTENT__ARRAY_CONTENT, null, msgs);
      if (newArrayContent != null)
        msgs = ((InternalEObject)newArrayContent).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SQFPackage.VAR_CONTENT__ARRAY_CONTENT, null, msgs);
      msgs = basicSetArrayContent(newArrayContent, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SQFPackage.VAR_CONTENT__ARRAY_CONTENT, newArrayContent, newArrayContent));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getExecute()
  {
    return execute;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setExecute(String newExecute)
  {
    String oldExecute = execute;
    execute = newExecute;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SQFPackage.VAR_CONTENT__EXECUTE, oldExecute, execute));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public MethodName getMethodName()
  {
    return methodName;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetMethodName(MethodName newMethodName, NotificationChain msgs)
  {
    MethodName oldMethodName = methodName;
    methodName = newMethodName;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQFPackage.VAR_CONTENT__METHOD_NAME, oldMethodName, newMethodName);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setMethodName(MethodName newMethodName)
  {
    if (newMethodName != methodName)
    {
      NotificationChain msgs = null;
      if (methodName != null)
        msgs = ((InternalEObject)methodName).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQFPackage.VAR_CONTENT__METHOD_NAME, null, msgs);
      if (newMethodName != null)
        msgs = ((InternalEObject)newMethodName).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SQFPackage.VAR_CONTENT__METHOD_NAME, null, msgs);
      msgs = basicSetMethodName(newMethodName, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SQFPackage.VAR_CONTENT__METHOD_NAME, newMethodName, newMethodName));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getEmbraced()
  {
    return embraced;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setEmbraced(String newEmbraced)
  {
    String oldEmbraced = embraced;
    embraced = newEmbraced;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SQFPackage.VAR_CONTENT__EMBRACED, oldEmbraced, embraced));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public BracketContent getEmbrCon()
  {
    return embrCon;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetEmbrCon(BracketContent newEmbrCon, NotificationChain msgs)
  {
    BracketContent oldEmbrCon = embrCon;
    embrCon = newEmbrCon;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQFPackage.VAR_CONTENT__EMBR_CON, oldEmbrCon, newEmbrCon);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setEmbrCon(BracketContent newEmbrCon)
  {
    if (newEmbrCon != embrCon)
    {
      NotificationChain msgs = null;
      if (embrCon != null)
        msgs = ((InternalEObject)embrCon).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQFPackage.VAR_CONTENT__EMBR_CON, null, msgs);
      if (newEmbrCon != null)
        msgs = ((InternalEObject)newEmbrCon).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SQFPackage.VAR_CONTENT__EMBR_CON, null, msgs);
      msgs = basicSetEmbrCon(newEmbrCon, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SQFPackage.VAR_CONTENT__EMBR_CON, newEmbrCon, newEmbrCon));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public raven.sQF.Boolean getBool()
  {
    return bool;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetBool(raven.sQF.Boolean newBool, NotificationChain msgs)
  {
    raven.sQF.Boolean oldBool = bool;
    bool = newBool;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQFPackage.VAR_CONTENT__BOOL, oldBool, newBool);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setBool(raven.sQF.Boolean newBool)
  {
    if (newBool != bool)
    {
      NotificationChain msgs = null;
      if (bool != null)
        msgs = ((InternalEObject)bool).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQFPackage.VAR_CONTENT__BOOL, null, msgs);
      if (newBool != null)
        msgs = ((InternalEObject)newBool).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SQFPackage.VAR_CONTENT__BOOL, null, msgs);
      msgs = basicSetBool(newBool, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SQFPackage.VAR_CONTENT__BOOL, newBool, newBool));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getParam()
  {
    return param;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setParam(String newParam)
  {
    String oldParam = param;
    param = newParam;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SQFPackage.VAR_CONTENT__PARAM, oldParam, param));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case SQFPackage.VAR_CONTENT__ARRAY_CONTENT:
        return basicSetArrayContent(null, msgs);
      case SQFPackage.VAR_CONTENT__METHOD_NAME:
        return basicSetMethodName(null, msgs);
      case SQFPackage.VAR_CONTENT__EMBR_CON:
        return basicSetEmbrCon(null, msgs);
      case SQFPackage.VAR_CONTENT__BOOL:
        return basicSetBool(null, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case SQFPackage.VAR_CONTENT__UN_OP:
        return getUnOP();
      case SQFPackage.VAR_CONTENT__NUM:
        return getNum();
      case SQFPackage.VAR_CONTENT__STRING:
        return getString();
      case SQFPackage.VAR_CONTENT__REFERENCE:
        if (resolve) return getReference();
        return basicGetReference();
      case SQFPackage.VAR_CONTENT__FOR_EACH_VAR:
        return getForEachVar();
      case SQFPackage.VAR_CONTENT__SEL:
        return getSel();
      case SQFPackage.VAR_CONTENT__INDEX:
        return getIndex();
      case SQFPackage.VAR_CONTENT__ARRAY_CONTENT:
        return getArrayContent();
      case SQFPackage.VAR_CONTENT__EXECUTE:
        return getExecute();
      case SQFPackage.VAR_CONTENT__METHOD_NAME:
        return getMethodName();
      case SQFPackage.VAR_CONTENT__EMBRACED:
        return getEmbraced();
      case SQFPackage.VAR_CONTENT__EMBR_CON:
        return getEmbrCon();
      case SQFPackage.VAR_CONTENT__BOOL:
        return getBool();
      case SQFPackage.VAR_CONTENT__PARAM:
        return getParam();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case SQFPackage.VAR_CONTENT__UN_OP:
        setUnOP((String)newValue);
        return;
      case SQFPackage.VAR_CONTENT__NUM:
        setNum((String)newValue);
        return;
      case SQFPackage.VAR_CONTENT__STRING:
        setString((String)newValue);
        return;
      case SQFPackage.VAR_CONTENT__REFERENCE:
        setReference((Declaration)newValue);
        return;
      case SQFPackage.VAR_CONTENT__FOR_EACH_VAR:
        setForEachVar((String)newValue);
        return;
      case SQFPackage.VAR_CONTENT__SEL:
        setSel((String)newValue);
        return;
      case SQFPackage.VAR_CONTENT__INDEX:
        setIndex((String)newValue);
        return;
      case SQFPackage.VAR_CONTENT__ARRAY_CONTENT:
        setArrayContent((ArrayLiteral)newValue);
        return;
      case SQFPackage.VAR_CONTENT__EXECUTE:
        setExecute((String)newValue);
        return;
      case SQFPackage.VAR_CONTENT__METHOD_NAME:
        setMethodName((MethodName)newValue);
        return;
      case SQFPackage.VAR_CONTENT__EMBRACED:
        setEmbraced((String)newValue);
        return;
      case SQFPackage.VAR_CONTENT__EMBR_CON:
        setEmbrCon((BracketContent)newValue);
        return;
      case SQFPackage.VAR_CONTENT__BOOL:
        setBool((raven.sQF.Boolean)newValue);
        return;
      case SQFPackage.VAR_CONTENT__PARAM:
        setParam((String)newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eUnset(int featureID)
  {
    switch (featureID)
    {
      case SQFPackage.VAR_CONTENT__UN_OP:
        setUnOP(UN_OP_EDEFAULT);
        return;
      case SQFPackage.VAR_CONTENT__NUM:
        setNum(NUM_EDEFAULT);
        return;
      case SQFPackage.VAR_CONTENT__STRING:
        setString(STRING_EDEFAULT);
        return;
      case SQFPackage.VAR_CONTENT__REFERENCE:
        setReference((Declaration)null);
        return;
      case SQFPackage.VAR_CONTENT__FOR_EACH_VAR:
        setForEachVar(FOR_EACH_VAR_EDEFAULT);
        return;
      case SQFPackage.VAR_CONTENT__SEL:
        setSel(SEL_EDEFAULT);
        return;
      case SQFPackage.VAR_CONTENT__INDEX:
        setIndex(INDEX_EDEFAULT);
        return;
      case SQFPackage.VAR_CONTENT__ARRAY_CONTENT:
        setArrayContent((ArrayLiteral)null);
        return;
      case SQFPackage.VAR_CONTENT__EXECUTE:
        setExecute(EXECUTE_EDEFAULT);
        return;
      case SQFPackage.VAR_CONTENT__METHOD_NAME:
        setMethodName((MethodName)null);
        return;
      case SQFPackage.VAR_CONTENT__EMBRACED:
        setEmbraced(EMBRACED_EDEFAULT);
        return;
      case SQFPackage.VAR_CONTENT__EMBR_CON:
        setEmbrCon((BracketContent)null);
        return;
      case SQFPackage.VAR_CONTENT__BOOL:
        setBool((raven.sQF.Boolean)null);
        return;
      case SQFPackage.VAR_CONTENT__PARAM:
        setParam(PARAM_EDEFAULT);
        return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
    {
      case SQFPackage.VAR_CONTENT__UN_OP:
        return UN_OP_EDEFAULT == null ? unOP != null : !UN_OP_EDEFAULT.equals(unOP);
      case SQFPackage.VAR_CONTENT__NUM:
        return NUM_EDEFAULT == null ? num != null : !NUM_EDEFAULT.equals(num);
      case SQFPackage.VAR_CONTENT__STRING:
        return STRING_EDEFAULT == null ? string != null : !STRING_EDEFAULT.equals(string);
      case SQFPackage.VAR_CONTENT__REFERENCE:
        return reference != null;
      case SQFPackage.VAR_CONTENT__FOR_EACH_VAR:
        return FOR_EACH_VAR_EDEFAULT == null ? forEachVar != null : !FOR_EACH_VAR_EDEFAULT.equals(forEachVar);
      case SQFPackage.VAR_CONTENT__SEL:
        return SEL_EDEFAULT == null ? sel != null : !SEL_EDEFAULT.equals(sel);
      case SQFPackage.VAR_CONTENT__INDEX:
        return INDEX_EDEFAULT == null ? index != null : !INDEX_EDEFAULT.equals(index);
      case SQFPackage.VAR_CONTENT__ARRAY_CONTENT:
        return arrayContent != null;
      case SQFPackage.VAR_CONTENT__EXECUTE:
        return EXECUTE_EDEFAULT == null ? execute != null : !EXECUTE_EDEFAULT.equals(execute);
      case SQFPackage.VAR_CONTENT__METHOD_NAME:
        return methodName != null;
      case SQFPackage.VAR_CONTENT__EMBRACED:
        return EMBRACED_EDEFAULT == null ? embraced != null : !EMBRACED_EDEFAULT.equals(embraced);
      case SQFPackage.VAR_CONTENT__EMBR_CON:
        return embrCon != null;
      case SQFPackage.VAR_CONTENT__BOOL:
        return bool != null;
      case SQFPackage.VAR_CONTENT__PARAM:
        return PARAM_EDEFAULT == null ? param != null : !PARAM_EDEFAULT.equals(param);
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String toString()
  {
    if (eIsProxy()) return super.toString();

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (unOP: ");
    result.append(unOP);
    result.append(", num: ");
    result.append(num);
    result.append(", string: ");
    result.append(string);
    result.append(", forEachVar: ");
    result.append(forEachVar);
    result.append(", sel: ");
    result.append(sel);
    result.append(", index: ");
    result.append(index);
    result.append(", execute: ");
    result.append(execute);
    result.append(", embraced: ");
    result.append(embraced);
    result.append(", param: ");
    result.append(param);
    result.append(')');
    return result.toString();
  }

} //VarContentImpl

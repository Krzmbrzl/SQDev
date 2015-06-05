/**
 */
package raven.sQF.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import raven.sQF.BracketContent;
import raven.sQF.Declaration;
import raven.sQF.SQFPackage;
import raven.sQF.forVarDeclaration;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Declaration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link raven.sQF.impl.DeclarationImpl#getName <em>Name</em>}</li>
 *   <li>{@link raven.sQF.impl.DeclarationImpl#getBrCon <em>Br Con</em>}</li>
 *   <li>{@link raven.sQF.impl.DeclarationImpl#getDeclaration <em>Declaration</em>}</li>
 *   <li>{@link raven.sQF.impl.DeclarationImpl#getLoopDeclaration <em>Loop Declaration</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DeclarationImpl extends MinimalEObjectImpl.Container implements Declaration
{
  /**
   * The default value of the '{@link #getName() <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
  protected static final String NAME_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
  protected String name = NAME_EDEFAULT;

  /**
   * The cached value of the '{@link #getBrCon() <em>Br Con</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getBrCon()
   * @generated
   * @ordered
   */
  protected BracketContent brCon;

  /**
   * The cached value of the '{@link #getDeclaration() <em>Declaration</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDeclaration()
   * @generated
   * @ordered
   */
  protected Declaration declaration;

  /**
   * The cached value of the '{@link #getLoopDeclaration() <em>Loop Declaration</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLoopDeclaration()
   * @generated
   * @ordered
   */
  protected forVarDeclaration loopDeclaration;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected DeclarationImpl()
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
    return SQFPackage.Literals.DECLARATION;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getName()
  {
    return name;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setName(String newName)
  {
    String oldName = name;
    name = newName;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SQFPackage.DECLARATION__NAME, oldName, name));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public BracketContent getBrCon()
  {
    return brCon;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetBrCon(BracketContent newBrCon, NotificationChain msgs)
  {
    BracketContent oldBrCon = brCon;
    brCon = newBrCon;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQFPackage.DECLARATION__BR_CON, oldBrCon, newBrCon);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setBrCon(BracketContent newBrCon)
  {
    if (newBrCon != brCon)
    {
      NotificationChain msgs = null;
      if (brCon != null)
        msgs = ((InternalEObject)brCon).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQFPackage.DECLARATION__BR_CON, null, msgs);
      if (newBrCon != null)
        msgs = ((InternalEObject)newBrCon).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SQFPackage.DECLARATION__BR_CON, null, msgs);
      msgs = basicSetBrCon(newBrCon, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SQFPackage.DECLARATION__BR_CON, newBrCon, newBrCon));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Declaration getDeclaration()
  {
    return declaration;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetDeclaration(Declaration newDeclaration, NotificationChain msgs)
  {
    Declaration oldDeclaration = declaration;
    declaration = newDeclaration;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQFPackage.DECLARATION__DECLARATION, oldDeclaration, newDeclaration);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setDeclaration(Declaration newDeclaration)
  {
    if (newDeclaration != declaration)
    {
      NotificationChain msgs = null;
      if (declaration != null)
        msgs = ((InternalEObject)declaration).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQFPackage.DECLARATION__DECLARATION, null, msgs);
      if (newDeclaration != null)
        msgs = ((InternalEObject)newDeclaration).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SQFPackage.DECLARATION__DECLARATION, null, msgs);
      msgs = basicSetDeclaration(newDeclaration, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SQFPackage.DECLARATION__DECLARATION, newDeclaration, newDeclaration));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public forVarDeclaration getLoopDeclaration()
  {
    return loopDeclaration;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetLoopDeclaration(forVarDeclaration newLoopDeclaration, NotificationChain msgs)
  {
    forVarDeclaration oldLoopDeclaration = loopDeclaration;
    loopDeclaration = newLoopDeclaration;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQFPackage.DECLARATION__LOOP_DECLARATION, oldLoopDeclaration, newLoopDeclaration);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setLoopDeclaration(forVarDeclaration newLoopDeclaration)
  {
    if (newLoopDeclaration != loopDeclaration)
    {
      NotificationChain msgs = null;
      if (loopDeclaration != null)
        msgs = ((InternalEObject)loopDeclaration).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQFPackage.DECLARATION__LOOP_DECLARATION, null, msgs);
      if (newLoopDeclaration != null)
        msgs = ((InternalEObject)newLoopDeclaration).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SQFPackage.DECLARATION__LOOP_DECLARATION, null, msgs);
      msgs = basicSetLoopDeclaration(newLoopDeclaration, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SQFPackage.DECLARATION__LOOP_DECLARATION, newLoopDeclaration, newLoopDeclaration));
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
      case SQFPackage.DECLARATION__BR_CON:
        return basicSetBrCon(null, msgs);
      case SQFPackage.DECLARATION__DECLARATION:
        return basicSetDeclaration(null, msgs);
      case SQFPackage.DECLARATION__LOOP_DECLARATION:
        return basicSetLoopDeclaration(null, msgs);
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
      case SQFPackage.DECLARATION__NAME:
        return getName();
      case SQFPackage.DECLARATION__BR_CON:
        return getBrCon();
      case SQFPackage.DECLARATION__DECLARATION:
        return getDeclaration();
      case SQFPackage.DECLARATION__LOOP_DECLARATION:
        return getLoopDeclaration();
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
      case SQFPackage.DECLARATION__NAME:
        setName((String)newValue);
        return;
      case SQFPackage.DECLARATION__BR_CON:
        setBrCon((BracketContent)newValue);
        return;
      case SQFPackage.DECLARATION__DECLARATION:
        setDeclaration((Declaration)newValue);
        return;
      case SQFPackage.DECLARATION__LOOP_DECLARATION:
        setLoopDeclaration((forVarDeclaration)newValue);
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
      case SQFPackage.DECLARATION__NAME:
        setName(NAME_EDEFAULT);
        return;
      case SQFPackage.DECLARATION__BR_CON:
        setBrCon((BracketContent)null);
        return;
      case SQFPackage.DECLARATION__DECLARATION:
        setDeclaration((Declaration)null);
        return;
      case SQFPackage.DECLARATION__LOOP_DECLARATION:
        setLoopDeclaration((forVarDeclaration)null);
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
      case SQFPackage.DECLARATION__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case SQFPackage.DECLARATION__BR_CON:
        return brCon != null;
      case SQFPackage.DECLARATION__DECLARATION:
        return declaration != null;
      case SQFPackage.DECLARATION__LOOP_DECLARATION:
        return loopDeclaration != null;
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
    result.append(" (name: ");
    result.append(name);
    result.append(')');
    return result.toString();
  }

} //DeclarationImpl

/**
 */
package raven.sQF.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import raven.sQF.ControlStructure;
import raven.sQF.ForType;
import raven.sQF.ForeachType;
import raven.sQF.SQFPackage;
import raven.sQF.SwitchType;
import raven.sQF.WhileType;
import raven.sQF.ifType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Control Structure</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link raven.sQF.impl.ControlStructureImpl#getIfStat <em>If Stat</em>}</li>
 *   <li>{@link raven.sQF.impl.ControlStructureImpl#getWhileStat <em>While Stat</em>}</li>
 *   <li>{@link raven.sQF.impl.ControlStructureImpl#getForStat <em>For Stat</em>}</li>
 *   <li>{@link raven.sQF.impl.ControlStructureImpl#getForEachStat <em>For Each Stat</em>}</li>
 *   <li>{@link raven.sQF.impl.ControlStructureImpl#getSwitchStat <em>Switch Stat</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ControlStructureImpl extends MinimalEObjectImpl.Container implements ControlStructure
{
  /**
   * The cached value of the '{@link #getIfStat() <em>If Stat</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getIfStat()
   * @generated
   * @ordered
   */
  protected ifType ifStat;

  /**
   * The cached value of the '{@link #getWhileStat() <em>While Stat</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getWhileStat()
   * @generated
   * @ordered
   */
  protected WhileType whileStat;

  /**
   * The cached value of the '{@link #getForStat() <em>For Stat</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getForStat()
   * @generated
   * @ordered
   */
  protected ForType forStat;

  /**
   * The cached value of the '{@link #getForEachStat() <em>For Each Stat</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getForEachStat()
   * @generated
   * @ordered
   */
  protected ForeachType forEachStat;

  /**
   * The cached value of the '{@link #getSwitchStat() <em>Switch Stat</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSwitchStat()
   * @generated
   * @ordered
   */
  protected SwitchType switchStat;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ControlStructureImpl()
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
    return SQFPackage.Literals.CONTROL_STRUCTURE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ifType getIfStat()
  {
    return ifStat;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetIfStat(ifType newIfStat, NotificationChain msgs)
  {
    ifType oldIfStat = ifStat;
    ifStat = newIfStat;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQFPackage.CONTROL_STRUCTURE__IF_STAT, oldIfStat, newIfStat);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setIfStat(ifType newIfStat)
  {
    if (newIfStat != ifStat)
    {
      NotificationChain msgs = null;
      if (ifStat != null)
        msgs = ((InternalEObject)ifStat).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQFPackage.CONTROL_STRUCTURE__IF_STAT, null, msgs);
      if (newIfStat != null)
        msgs = ((InternalEObject)newIfStat).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SQFPackage.CONTROL_STRUCTURE__IF_STAT, null, msgs);
      msgs = basicSetIfStat(newIfStat, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SQFPackage.CONTROL_STRUCTURE__IF_STAT, newIfStat, newIfStat));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public WhileType getWhileStat()
  {
    return whileStat;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetWhileStat(WhileType newWhileStat, NotificationChain msgs)
  {
    WhileType oldWhileStat = whileStat;
    whileStat = newWhileStat;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQFPackage.CONTROL_STRUCTURE__WHILE_STAT, oldWhileStat, newWhileStat);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setWhileStat(WhileType newWhileStat)
  {
    if (newWhileStat != whileStat)
    {
      NotificationChain msgs = null;
      if (whileStat != null)
        msgs = ((InternalEObject)whileStat).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQFPackage.CONTROL_STRUCTURE__WHILE_STAT, null, msgs);
      if (newWhileStat != null)
        msgs = ((InternalEObject)newWhileStat).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SQFPackage.CONTROL_STRUCTURE__WHILE_STAT, null, msgs);
      msgs = basicSetWhileStat(newWhileStat, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SQFPackage.CONTROL_STRUCTURE__WHILE_STAT, newWhileStat, newWhileStat));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ForType getForStat()
  {
    return forStat;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetForStat(ForType newForStat, NotificationChain msgs)
  {
    ForType oldForStat = forStat;
    forStat = newForStat;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQFPackage.CONTROL_STRUCTURE__FOR_STAT, oldForStat, newForStat);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setForStat(ForType newForStat)
  {
    if (newForStat != forStat)
    {
      NotificationChain msgs = null;
      if (forStat != null)
        msgs = ((InternalEObject)forStat).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQFPackage.CONTROL_STRUCTURE__FOR_STAT, null, msgs);
      if (newForStat != null)
        msgs = ((InternalEObject)newForStat).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SQFPackage.CONTROL_STRUCTURE__FOR_STAT, null, msgs);
      msgs = basicSetForStat(newForStat, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SQFPackage.CONTROL_STRUCTURE__FOR_STAT, newForStat, newForStat));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ForeachType getForEachStat()
  {
    return forEachStat;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetForEachStat(ForeachType newForEachStat, NotificationChain msgs)
  {
    ForeachType oldForEachStat = forEachStat;
    forEachStat = newForEachStat;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQFPackage.CONTROL_STRUCTURE__FOR_EACH_STAT, oldForEachStat, newForEachStat);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setForEachStat(ForeachType newForEachStat)
  {
    if (newForEachStat != forEachStat)
    {
      NotificationChain msgs = null;
      if (forEachStat != null)
        msgs = ((InternalEObject)forEachStat).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQFPackage.CONTROL_STRUCTURE__FOR_EACH_STAT, null, msgs);
      if (newForEachStat != null)
        msgs = ((InternalEObject)newForEachStat).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SQFPackage.CONTROL_STRUCTURE__FOR_EACH_STAT, null, msgs);
      msgs = basicSetForEachStat(newForEachStat, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SQFPackage.CONTROL_STRUCTURE__FOR_EACH_STAT, newForEachStat, newForEachStat));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SwitchType getSwitchStat()
  {
    return switchStat;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetSwitchStat(SwitchType newSwitchStat, NotificationChain msgs)
  {
    SwitchType oldSwitchStat = switchStat;
    switchStat = newSwitchStat;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQFPackage.CONTROL_STRUCTURE__SWITCH_STAT, oldSwitchStat, newSwitchStat);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSwitchStat(SwitchType newSwitchStat)
  {
    if (newSwitchStat != switchStat)
    {
      NotificationChain msgs = null;
      if (switchStat != null)
        msgs = ((InternalEObject)switchStat).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQFPackage.CONTROL_STRUCTURE__SWITCH_STAT, null, msgs);
      if (newSwitchStat != null)
        msgs = ((InternalEObject)newSwitchStat).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SQFPackage.CONTROL_STRUCTURE__SWITCH_STAT, null, msgs);
      msgs = basicSetSwitchStat(newSwitchStat, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SQFPackage.CONTROL_STRUCTURE__SWITCH_STAT, newSwitchStat, newSwitchStat));
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
      case SQFPackage.CONTROL_STRUCTURE__IF_STAT:
        return basicSetIfStat(null, msgs);
      case SQFPackage.CONTROL_STRUCTURE__WHILE_STAT:
        return basicSetWhileStat(null, msgs);
      case SQFPackage.CONTROL_STRUCTURE__FOR_STAT:
        return basicSetForStat(null, msgs);
      case SQFPackage.CONTROL_STRUCTURE__FOR_EACH_STAT:
        return basicSetForEachStat(null, msgs);
      case SQFPackage.CONTROL_STRUCTURE__SWITCH_STAT:
        return basicSetSwitchStat(null, msgs);
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
      case SQFPackage.CONTROL_STRUCTURE__IF_STAT:
        return getIfStat();
      case SQFPackage.CONTROL_STRUCTURE__WHILE_STAT:
        return getWhileStat();
      case SQFPackage.CONTROL_STRUCTURE__FOR_STAT:
        return getForStat();
      case SQFPackage.CONTROL_STRUCTURE__FOR_EACH_STAT:
        return getForEachStat();
      case SQFPackage.CONTROL_STRUCTURE__SWITCH_STAT:
        return getSwitchStat();
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
      case SQFPackage.CONTROL_STRUCTURE__IF_STAT:
        setIfStat((ifType)newValue);
        return;
      case SQFPackage.CONTROL_STRUCTURE__WHILE_STAT:
        setWhileStat((WhileType)newValue);
        return;
      case SQFPackage.CONTROL_STRUCTURE__FOR_STAT:
        setForStat((ForType)newValue);
        return;
      case SQFPackage.CONTROL_STRUCTURE__FOR_EACH_STAT:
        setForEachStat((ForeachType)newValue);
        return;
      case SQFPackage.CONTROL_STRUCTURE__SWITCH_STAT:
        setSwitchStat((SwitchType)newValue);
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
      case SQFPackage.CONTROL_STRUCTURE__IF_STAT:
        setIfStat((ifType)null);
        return;
      case SQFPackage.CONTROL_STRUCTURE__WHILE_STAT:
        setWhileStat((WhileType)null);
        return;
      case SQFPackage.CONTROL_STRUCTURE__FOR_STAT:
        setForStat((ForType)null);
        return;
      case SQFPackage.CONTROL_STRUCTURE__FOR_EACH_STAT:
        setForEachStat((ForeachType)null);
        return;
      case SQFPackage.CONTROL_STRUCTURE__SWITCH_STAT:
        setSwitchStat((SwitchType)null);
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
      case SQFPackage.CONTROL_STRUCTURE__IF_STAT:
        return ifStat != null;
      case SQFPackage.CONTROL_STRUCTURE__WHILE_STAT:
        return whileStat != null;
      case SQFPackage.CONTROL_STRUCTURE__FOR_STAT:
        return forStat != null;
      case SQFPackage.CONTROL_STRUCTURE__FOR_EACH_STAT:
        return forEachStat != null;
      case SQFPackage.CONTROL_STRUCTURE__SWITCH_STAT:
        return switchStat != null;
    }
    return super.eIsSet(featureID);
  }

} //ControlStructureImpl

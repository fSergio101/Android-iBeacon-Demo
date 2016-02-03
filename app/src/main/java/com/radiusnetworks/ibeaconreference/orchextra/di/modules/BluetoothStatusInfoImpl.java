package com.radiusnetworks.ibeaconreference.orchextra.di.modules;

import com.radiusnetworks.ibeaconreference.beacons.BluetoothAvailability;
import com.radiusnetworks.ibeaconreference.beacons.BluetoothStatus;
import com.radiusnetworks.ibeaconreference.beacons.BluetoothStatusListener;
import com.radiusnetworks.ibeaconreference.beacons.CoarseLocationPermission;
import com.radiusnetworks.ibeaconreference.lifecycle.AppRunningMode;
import com.radiusnetworks.ibeaconreference.lifecycle.AppRunningModeType;
import com.radiusnetworks.ibeaconreference.lifecycle.ContextProvider;
import com.radiusnetworks.ibeaconreference.orchextra.BeaconFeature;
import com.radiusnetworks.ibeaconreference.orchextra.FeatureListener;
import com.radiusnetworks.ibeaconreference.permissions.Permission;
import com.radiusnetworks.ibeaconreference.permissions.PermissionChecker;
import com.radiusnetworks.ibeaconreference.permissions.UserPermissionRequestResponseListener;

/**
 * Created by Sergio Martinez Rodriguez
 * Date 2/2/16.
 */
public class BluetoothStatusInfoImpl implements BluetoothStatusInfo{

  private final PermissionChecker permissionChecker;
  private final BluetoothAvailability bluetoothAvailability;
  private final ContextProvider contextProvider;
  private final AppRunningMode appRunningMode;
  private final FeatureListener featureListener;
  private BluetoothStatusListener bluetoothStatusListener;


  public BluetoothStatusInfoImpl(PermissionChecker permissionChecker,
      BluetoothAvailability bluetoothAvailability, ContextProvider contextProvider,
      AppRunningMode appRunningMode, FeatureListener featureListener) {

    this.permissionChecker = permissionChecker;
    this.bluetoothAvailability = bluetoothAvailability;
    this.contextProvider = contextProvider;
    this.appRunningMode = appRunningMode;
    this.featureListener = featureListener;
  }

  @Override public void obtainBluetoothStatus() {

    if (!checkBlteSupported()){
      return;
    }

    hasBltePermissions();

  }

  private void checkEnabled() {

    if (bluetoothAvailability.isBlteEnabled()){
      informBluetoothStatus(BluetoothStatus.READY_FOR_SCAN);
    }else{
      informBluetoothStatus(BluetoothStatus.NOT_ENABLED);
    }

  }

  private void hasBltePermissions() {
    final Permission permission = new CoarseLocationPermission();

    boolean allowed = permissionChecker.isGranted(permission);
    if (allowed){
      onPermissionResponse(allowed);
    }else{
    if (appRunningMode.getRunningModeType() == AppRunningModeType.FOREGROUND){
      permissionChecker.askForPermission(permission, new UserPermissionRequestResponseListener() {
        @Override public void onPermissionAllowed(boolean permissionAllowed) {
          onPermissionResponse(permissionAllowed);
        }
      }, contextProvider.getCurrentActivity());
    }else{
      onPermissionResponse(false);
    }
    }
  }

  private void onPermissionResponse(boolean allowed) {
    if (allowed){
      checkEnabled();
    }else{
      informBluetoothStatus(BluetoothStatus.NO_PERMISSIONS);
    }
  }

  private boolean checkBlteSupported() {
    if (!bluetoothAvailability.isBlteSupported()){
      informBluetoothStatus(BluetoothStatus.NO_BLTE_SUPPORTED);
      return false;
    }
    return true;
  }

  @Override
  public void setBluetoothStatusListener(BluetoothStatusListener bluetoothStatusListener) {
    this.bluetoothStatusListener = bluetoothStatusListener;
  }

  private void informBluetoothStatus(BluetoothStatus status) {
    if (bluetoothStatusListener!=null){
      bluetoothStatusListener.onBluetoothStatus(status);
    }
    featureListener.onFeatureStatusChanged(new BeaconFeature(status));
  }

}

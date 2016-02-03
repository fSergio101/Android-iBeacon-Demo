package com.radiusnetworks.ibeaconreference.orchextra.di.modules;

import com.radiusnetworks.ibeaconreference.beacons.BluetoothStatusListener;

/**
 * Created by Sergio Martinez Rodriguez
 * Date 2/2/16.
 */
public interface BluetoothStatusInfo {
  void obtainBluetoothStatus();

  void setBluetoothStatusListener(BluetoothStatusListener bluetoothStatusListener);
}

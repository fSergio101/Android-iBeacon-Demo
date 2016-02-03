package com.radiusnetworks.ibeaconreference.beacons;

/**
 * Created by Sergio Martinez Rodriguez
 * Date 4/1/16.
 */
public interface BeaconScanner {

  void startMonitoring();
  void stopMonitoring();

  void startRangingScanner();
  void initAvailableRegionsRangingScanner();
  void stopRangingScanner();

  boolean isRanging();
  boolean isMonitoring();

}

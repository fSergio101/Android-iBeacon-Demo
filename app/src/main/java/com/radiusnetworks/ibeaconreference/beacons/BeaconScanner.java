package com.radiusnetworks.ibeaconreference.beacons;

/**
 * Created by Sergio Martinez Rodriguez
 * Date 4/1/16.
 */
public interface BeaconScanner {

  void initMonitoring();
  void stopMonitoring();

  void initRangingScanner();
  void initAvailableRegionsRangingScanner();
  void stopRangingScanner();

  boolean isRanging();
  boolean isMonitoring();

}

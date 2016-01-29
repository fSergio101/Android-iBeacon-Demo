package com.radiusnetworks.ibeaconreference.beacons;

import com.radiusnetworks.ibeaconreference.beacons.monitoring.RegionMonitoringScanner;
import com.radiusnetworks.ibeaconreference.beacons.ranging.BeaconRangingScanner;
import com.radiusnetworks.ibeaconreference.beacons.ranging.exceptions.RangingScanInBackgroundException;
import com.radiusnetworks.ibeaconreference.lifecycle.AppRunningMode;
import com.radiusnetworks.ibeaconreference.lifecycle.AppRunningModeType;
import org.altbeacon.beacon.BeaconManager;

/**
 * Created by Sergio Martinez Rodriguez
 * Date 25/1/16.
 */
public class BeaconScannerImpl implements BeaconScanner{

  private final RegionMonitoringScanner regionMonitoringScanner;
  private final BeaconRangingScanner beaconRangingScanner;
  private final BeaconManager beaconManager;
  private final AppRunningMode appRunningMode;


  public BeaconScannerImpl(RegionMonitoringScanner regionMonitoringScanner,
      BeaconRangingScanner beaconRangingScanner, BeaconManager beaconManager, AppRunningMode appRunningMode) {
    this.regionMonitoringScanner = regionMonitoringScanner;
    this.beaconRangingScanner = beaconRangingScanner;
    this.beaconManager = beaconManager;
    this.appRunningMode = appRunningMode;
  }

  @Override public void initMonitoring() {
    beaconManager.setBackgroundMode(appRunningMode.getRunningModeType() == AppRunningModeType.BACKGROUND);

    if (!regionMonitoringScanner.isMonitoring()){
      regionMonitoringScanner.initMonitoring(appRunningMode.getRunningModeType());
    }
  }

  @Override public void initRangingScanner() {
    if (!beaconRangingScanner.isRanging()){
      beaconRangingScanner.initRangingScanForAllKnownRegions(appRunningMode.getRunningModeType());
    }
  }

  @Override public void initAvailableRegionsRangingScanner() {

    if (appRunningMode.getRunningModeType() == AppRunningModeType.BACKGROUND){
      throw new RangingScanInBackgroundException("Infinite Ranging Scan in Background Mode is not allowed");
    }

    if (beaconRangingScanner.isRanging()){
      beaconRangingScanner.stopAllCurrentRangingScannedRegions();
    }

    beaconRangingScanner.initRangingScanForDetectedRegion(
        regionMonitoringScanner.obtainRegionsInRange(), BackgroundBeaconsRangingTimeType.INFINITE);
  }

  @Override public void stopMonitoring() {
    if (regionMonitoringScanner.isMonitoring()){
      regionMonitoringScanner.stopMonitoring();
    }
  }

  @Override public void stopRangingScanner() {
    if (beaconRangingScanner.isRanging()){
      beaconRangingScanner.stopAllCurrentRangingScannedRegions();
    }
  }

  public boolean isRanging() {
    return beaconRangingScanner.isRanging();
  }

  public boolean isMonitoring() {
    return regionMonitoringScanner.isMonitoring();
  }

}

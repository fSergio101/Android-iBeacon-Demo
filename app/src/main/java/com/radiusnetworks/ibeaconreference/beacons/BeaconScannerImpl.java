package com.radiusnetworks.ibeaconreference.beacons;

import com.radiusnetworks.ibeaconreference.beacons.monitoring.RegionMonitoringScanner;
import com.radiusnetworks.ibeaconreference.beacons.ranging.BeaconRangingScanner;
import com.radiusnetworks.ibeaconreference.lifecycle.AppRunningMode;

/**
 * Created by Sergio Martinez Rodriguez
 * Date 25/1/16.
 */
public class BeaconScannerImpl implements BeaconScanner{

  private final RegionMonitoringScanner regionMonitoringScanner;
  private final BeaconRangingScanner beaconRangingScanner;
  private final AppRunningMode appRunningMode;

  private boolean ranging = false;
  private boolean monitoring = false;

  public BeaconScannerImpl(RegionMonitoringScanner regionMonitoringScanner,
      BeaconRangingScanner beaconRangingScanner, AppRunningMode appRunningMode) {
    this.regionMonitoringScanner = regionMonitoringScanner;
    this.beaconRangingScanner = beaconRangingScanner;
    this.appRunningMode = appRunningMode;
  }

  @Override public void initMonitoring() {
    if (!monitoring){
      regionMonitoringScanner.setStandByMode(false);
      regionMonitoringScanner.initMonitoring(appRunningMode.getRunningModeType());
      monitoring = true;
    }
  }

  @Override public void initRangingScanner() {
    if (!ranging){
      beaconRangingScanner.initRangingScanForAllKnownRegions(appRunningMode.getRunningModeType());
      ranging = true;
    }
  }

  @Override public void stopMonitoring() {
    if (monitoring){
      regionMonitoringScanner.stopMonitoring();
      ranging = false;
    }
  }

  @Override public void stopRangingScanner() {
    if (ranging){
      beaconRangingScanner.stopRangingScanAllKnownRegions();
      ranging = false;
    }
  }

  public boolean isRanging() {
    return ranging;
  }

  public boolean isMonitoring() {
    return monitoring;
  }

  @Override public void switchMonitoringToStandBy() {
    regionMonitoringScanner.setStandByMode(true);
  }
}

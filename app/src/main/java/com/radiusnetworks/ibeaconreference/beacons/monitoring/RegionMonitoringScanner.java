package com.radiusnetworks.ibeaconreference.beacons.monitoring;

import com.radiusnetworks.ibeaconreference.beacons.RegionsProviderListener;
import com.radiusnetworks.ibeaconreference.lifecycle.AppRunningModeType;

/**
 * Created by Sergio Martinez Rodriguez
 * Date 25/1/16.
 */
public interface RegionMonitoringScanner extends RegionsProviderListener {
  void initMonitoring(AppRunningModeType appRunningModeType);
  void stopMonitoring();

  void setStandByMode(boolean mode);
}

package com.radiusnetworks.ibeaconreference.beacons.monitoring;

import com.radiusnetworks.ibeaconreference.beacons.RegionsProviderListener;
import com.radiusnetworks.ibeaconreference.lifecycle.AppRunningModeType;
import java.util.List;
import org.altbeacon.beacon.Region;

/**
 * Created by Sergio Martinez Rodriguez
 * Date 25/1/16.
 */
public interface RegionMonitoringScanner extends RegionsProviderListener {
  void initMonitoring(AppRunningModeType appRunningModeType);
  void stopMonitoring();

  List<Region> obtainRegionsInRange();

  boolean isMonitoring();
}

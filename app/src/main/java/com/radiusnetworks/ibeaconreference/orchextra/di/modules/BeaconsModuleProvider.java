package com.radiusnetworks.ibeaconreference.orchextra.di.modules;

import com.radiusnetworks.ibeaconreference.beacons.BeaconScanner;
import com.radiusnetworks.ibeaconreference.beacons.monitoring.MonitoringListener;
import com.radiusnetworks.ibeaconreference.beacons.monitoring.RegionMonitoringScanner;
import com.radiusnetworks.ibeaconreference.beacons.ranging.BeaconRangingScanner;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.powersave.BackgroundPowerSaver;

/**
 * Created by Sergio Martinez Rodriguez
 * Date 11/12/15.
 */
public interface BeaconsModuleProvider {
  BackgroundPowerSaver provideBackgroundPowerSaver();
  BeaconManager provideBeaconManager();
  BeaconScanner provideBeaconScanner();
  BeaconRangingScanner provideBeaconRangingScanner();
  RegionMonitoringScanner provideRegionMonitoringScanner();
  MonitoringListener provideMonitoringListener();
}

package com.radiusnetworks.ibeaconreference.beacons;

import android.util.Log;
import com.radiusnetworks.ibeaconreference.beacons.monitoring.MonitoringListener;
import com.radiusnetworks.ibeaconreference.beacons.ranging.BeaconRangingScanner;
import com.radiusnetworks.ibeaconreference.lifecycle.AppRunningMode;
import com.radiusnetworks.ibeaconreference.lifecycle.AppRunningModeType;
import java.util.ArrayList;
import java.util.List;
import org.altbeacon.beacon.Region;

/**
 * Created by Sergio Martinez Rodriguez
 * Date 28/1/16.
 */
public class MonitoringListenerImpl implements MonitoringListener {

  private static final String TAG = "MonitoringListenerImpl";

  private final AppRunningMode appRunningMode;
  private final BeaconRangingScanner beaconRangingScanner;
  private final BackgroundBeaconsRangingTimeType backgroundBeaconsRangingTimeType;

  public MonitoringListenerImpl(AppRunningMode appRunningMode,
      BeaconRangingScanner beaconRangingScanner) {

    this.appRunningMode = appRunningMode;
    this.beaconRangingScanner = beaconRangingScanner;
    this.backgroundBeaconsRangingTimeType = beaconRangingScanner.getBackgroundBeaconsRangingTimeType();
  }

  @Override public void onRegionEnter(Region region) {

    List<Region> regions = new ArrayList<>();
    regions.add(region);

    if (appRunningMode.getRunningModeType() == AppRunningModeType.FOREGROUND){

      beaconRangingScanner.initRangingScanForDetectedRegion(appRunningMode.getRunningModeType(),
          regions, BackgroundBeaconsRangingTimeType.INFINITE);

      Log.v(TAG, "LOG :: Ranging will be Started with infinite duration");

    }else if (appRunningMode.getRunningModeType() == AppRunningModeType.BACKGROUND &&
        backgroundBeaconsRangingTimeType != BackgroundBeaconsRangingTimeType.DISABLED){

      beaconRangingScanner.initRangingScanForDetectedRegion(appRunningMode.getRunningModeType(),
          regions, backgroundBeaconsRangingTimeType);

      Log.v(TAG, "LOG :: Ranging will be Started with " +
          String.valueOf(backgroundBeaconsRangingTimeType.getIntValue()) + " duration");

    }
  }

  @Override public void onRegionExit(Region region) {
    beaconRangingScanner.stopRangingScanForDetectedRegion(region);
  }
}

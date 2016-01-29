package com.radiusnetworks.ibeaconreference.beacons.ranging;

import com.radiusnetworks.ibeaconreference.beacons.BackgroundBeaconsRangingTimeType;
import com.radiusnetworks.ibeaconreference.beacons.RegionsProviderListener;
import com.radiusnetworks.ibeaconreference.lifecycle.AppRunningModeType;
import java.util.List;
import org.altbeacon.beacon.Region;

/**
 * Created by Sergio Martinez Rodriguez
 * Date 25/1/16.
 */
public interface BeaconRangingScanner extends RegionsProviderListener {

  void initRangingScanForAllKnownRegions(AppRunningModeType appRunningModeType);
  void initRangingScanForDetectedRegion(AppRunningModeType appRunningModeType, List<Region> regions,
      BackgroundBeaconsRangingTimeType backgroundBeaconsRangingTimeType);

  void stopRangingScanAllKnownRegions();
  void stopRangingScanForDetectedRegion(Region region);

  BackgroundBeaconsRangingTimeType getBackgroundBeaconsRangingTimeType();
}

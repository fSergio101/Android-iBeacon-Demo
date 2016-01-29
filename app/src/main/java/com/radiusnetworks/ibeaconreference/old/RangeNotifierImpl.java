package com.radiusnetworks.ibeaconreference.old;

import android.util.Log;
import java.util.Collection;
import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.RangeNotifier;
import org.altbeacon.beacon.Region;

/**
 * Created by Sergio Martinez Rodriguez
 * Date 21/1/16.
 */
public class RangeNotifierImpl {
    //implements RangeNotifier{
  //
  //private final BeaconScanner beaconScanner;
  //int rangingWithoutBeacons = 0;
  //
  //public RangeNotifierImpl(BeaconScanner beaconScanner) {
  //  this.beaconScanner = beaconScanner;
  //}
  //
  //@Override public void didRangeBeaconsInRegion(Collection<Beacon> collection, Region region) {
  //  if (collection.size() > 0) {
  //    Log.i("BeaconScannerImpl", "The first beacon I see has UUID: "
  //        + collection.iterator().next().getId1()
  //        + " major id:"
  //        + collection.iterator().next().getId2()
  //        + "  minor id: "
  //        + collection.iterator().next().getId3());
  //
  //    beaconScanner.discoveredBeacons(collection);
  //    rangingWithoutBeacons = 0;
  //  }else{
  //    rangingWithoutBeacons++;
  //    beaconScanner.noBeaconsSince(rangingWithoutBeacons);
  //    if (rangingWithoutBeacons>4){
  //      beaconScanner.stopRangingBeaconsInRegion(region);
  //      rangingWithoutBeacons = 0;
  //    }
  //  }
  //}
}

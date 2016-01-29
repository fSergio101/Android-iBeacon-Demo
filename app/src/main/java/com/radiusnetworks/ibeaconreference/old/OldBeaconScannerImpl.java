package com.radiusnetworks.ibeaconreference.old;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.RemoteException;
import android.util.Log;
import java.util.Collection;
import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.Identifier;
import org.altbeacon.beacon.Region;
import org.altbeacon.beacon.powersave.BackgroundPowerSaver;
import org.altbeacon.beacon.startup.BootstrapNotifier;
import org.altbeacon.beacon.startup.RegionBootstrap;

/**
 * Created by Sergio Martinez Rodriguez
 * Date 4/1/16.
 */
public class OldBeaconScannerImpl{
    //implements BeaconScanner, BootstrapNotifier {

  //TODO Allow devices without bluetooht

  //TODO MANAGE ENABLED BLUETOOTH

  //TODO IF NOT ALLOWED PERMISSION DONT INIT SCAN!!
  //
  //private static final String TAG = "BeaconScannerImpl";
  //private Application app;
  //private BeaconNotificationManager beaconNotificationManager;
  //private RegionBootstrap regionBootstrap;
  //private BeaconManager beaconManager;
  //private BackgroundPowerSaver backgroundPowerSaver;
  //
  //public OldBeaconScannerImpl(Application app, BeaconNotificationManager beaconNotificationManager) {
  //  this.app = app;
  //  this.beaconNotificationManager = beaconNotificationManager;
  //}
  //
  //@Override public Context getApplicationContext() {
  //  return app.getApplicationContext();
  //}
  //
  //@Override public void didExitRegion(Region region) {
  //
  //}
  //
  //@Override public void didDetermineStateForRegion(int i, Region region) {
  //
  //}
  //
  //public void initScanner(Application beaconSampleApp) {
  //  beaconManager = BeaconManager.getInstanceForApplication(app);
  //  beaconManager.setDebug(true);
  //  backgroundPowerSaver = new BackgroundPowerSaver(app);
  //  beaconManager.getBeaconParsers().add(new BeaconParser().
  //      setBeaconLayout("m:2-3=0215,i:4-19,i:20-21,i:22-23,p:24-24,d:25-25"));
  //  Identifier identifier =  Identifier.parse("e6775403-f0dd-40c4-87db-95e755738ad1");
  //
  //  Region region = new Region(".Background",null,null,null);
  //
  //  beaconManager.setRangeNotifier(new RangeNotifierImpl(this));
  //
  //  //try{
  //       //set the duration of the scan to be 1.1 seconds
  //      //beaconManager.setBackgroundScanPeriod(1500l);
  //      //beaconManager.setBackgroundBetweenScanPeriod(5000l);//3600000l
  //      //beaconManager.updateScanPeriods();
  //      //beaconManager.startMonitoringBeaconsInRegion(region);
  //      //beaconManager.setAndroidLScanningDisabled(true);
  //
  //  //}  catch (RemoteException e) {
  //  //    Log.e(TAG, "Cannot talk to service");
  //  //}
  //
  //  regionBootstrap = new RegionBootstrap(this, region);
  //
  //}
  //
  //@Override public void stopRangingBeaconsInRegion(Region region) {
  //  try {
  //    Log.d(TAG, "Ranging Stopped !");
  //    beaconManager.stopRangingBeaconsInRegion(region);
  //  } catch (RemoteException e) {
  //    e.printStackTrace();
  //  }
  //}
  //
  //@Override public void discoveredBeacons(Collection<Beacon> collection) {
  //    beaconNotificationManager.showNotification("Beacon major: "
  //        + collection.iterator().next().getId2()
  //        + "  minor id: "
  //        + collection.iterator().next().getId3());
  //  Log.d(TAG, "BEACONS !");
  //
  //}
  //
  //@Override public void noBeaconsSince(long lastSeenTimeStamp) {
  //  Log.d(TAG, "no beacons since !" + String.valueOf(lastSeenTimeStamp));
  //}
  //
  //@Override public void didEnterRegion(Region region) {
  //  //beaconNotificationManager.showNotification("Enter beacon --> " + region.getId1());
  //  //Log.d(TAG, "BEACON discovered !!");
  //  //Intent intent = new Intent(app, BeaconDetectorService.class);
  //  //app.startService(intent);
  //
  //  try {
  //    beaconManager.startRangingBeaconsInRegion(region);
  //  } catch (RemoteException e) {
  //    e.printStackTrace();
  //  }
  //}
  //
  //public String getIncompatibilityReason(Context context) {
  //  if (android.os.Build.VERSION.SDK_INT < 18) {
  //    return "requires Android 4.3";
  //  }
  //  if (!context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
  //    return "requires Bluetooth LE";
  //  }
  //  return null;
  //}

}

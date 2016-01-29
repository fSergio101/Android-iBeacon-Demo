package com.radiusnetworks.ibeaconreference.beacons.monitoring;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.RemoteException;
import android.util.Log;
import com.radiusnetworks.ibeaconreference.beacons.tools.BeaconRegionsFactory;
import com.radiusnetworks.ibeaconreference.lifecycle.AppRunningModeType;
import com.radiusnetworks.ibeaconreference.lifecycle.ContextProvider;
import java.util.List;
import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.MonitorNotifier;
import org.altbeacon.beacon.Region;

/**
 * Created by Sergio Martinez Rodriguez
 * Date 25/1/16.
 */
public class RegionMonitoringScannerImpl implements RegionMonitoringScanner,
    BeaconConsumer, MonitorNotifier {

  private static final String TAG = "RegionMonitoringScanner";

  private final BeaconManager beaconManager;
  private boolean standBy;
  private final Context context;
  private List<Region> regions;
  private final MonitoringListener monitoringListener;

  public RegionMonitoringScannerImpl(ContextProvider contextProvider, BeaconManager beaconManager,
      MonitoringListener monitoringListener) {

    this.beaconManager = beaconManager;
    this.context = contextProvider.getApplicationContext();
    this.monitoringListener = monitoringListener;

    this.beaconManager.setMonitorNotifier(this);
  }

  //region BeaconConsumer Interface

  @Override public void onBeaconServiceConnect() {
    //TODO if Regions are changed service that handles monitoring should restart
    obtainRegionsToScan();
  }

  @Override public Context getApplicationContext() {
    return context;
  }

  @Override public void unbindService(ServiceConnection serviceConnection) {
    context.unbindService(serviceConnection);
  }

  @Override public boolean bindService(Intent intent, ServiceConnection serviceConnection, int i) {
    return context.bindService(intent,serviceConnection,i);
  }

  // endregion

  //region MonitorNotifier Interface

  @Override public void didEnterRegion(Region region) {
    //TODO Notify Beacons EventNofitier
    Log.d(TAG, "LOG :: ENTER BEACON REGION : " + region.getUniqueId());
    monitoringListener.onRegionEnter(region);
  }

  @Override public void didExitRegion(Region region) {
    Log.d(TAG, "LOG :: EXIT BEACON REGION : " + region.getUniqueId());
    monitoringListener.onRegionExit(region);
    //TODO Notify Beacons EventNofitier
  }

  @Override public void didDetermineStateForRegion(int i, Region region) {}

  // endregion

  //region RegionMonitoringScanner Interface

  //@Override public void setMonitoringListener(MonitoringListener monitoringListener) {
  //  this.monitoringListener = monitoringListener;
  //}

  @Override public void initMonitoring(AppRunningModeType appRunningModeType) {
    beaconManager.setBackgroundMode(appRunningModeType == AppRunningModeType.BACKGROUND);
    beaconManager.bind(this);
  }

  @Override public void stopMonitoring() {
    for (Region region:regions){
      try {
        beaconManager.stopMonitoringBeaconsInRegion(region);
        Log.d(TAG, "LOG :: Stop Beacons Monitoring for region: " + region.getUniqueId());
      } catch (RemoteException e) {
        e.printStackTrace();
      }
    }
    beaconManager.unbind(this);
  }

  @Override public void setStandByMode(boolean mode) {
    this.standBy = mode;
  }

  private void obtainRegionsToScan() {
    //TODO First get allRegions From BD Later this:
    BeaconRegionsFactory.obtainRegionsToScan(this);
  }

  // region RegionsProviderListener Interface

  @Override public void onRegionsReady(List<Region> regions) {
    this.regions = regions;
    for (Region region:regions){
      try {
        beaconManager.startMonitoringBeaconsInRegion(region);
        Log.d(TAG, "LOG :: Start Beacons Monitoring for region " + region.getUniqueId());
      } catch (RemoteException e) {
        e.printStackTrace();
      }
    }
  }

  // endregion

  // endregion

}

package com.radiusnetworks.ibeaconreference.orchextra.di.modules;

import com.radiusnetworks.ibeaconreference.beacons.BeaconScanner;
import com.radiusnetworks.ibeaconreference.beacons.BeaconScannerImpl;
import com.radiusnetworks.ibeaconreference.beacons.BluetoothAvailability;
import com.radiusnetworks.ibeaconreference.beacons.BluetoothAvailabilityImpl;
import com.radiusnetworks.ibeaconreference.beacons.MonitoringListenerImpl;
import com.radiusnetworks.ibeaconreference.beacons.monitoring.BeaconsController;
import com.radiusnetworks.ibeaconreference.beacons.monitoring.MonitoringListener;
import com.radiusnetworks.ibeaconreference.beacons.monitoring.RegionMonitoringScanner;
import com.radiusnetworks.ibeaconreference.beacons.monitoring.RegionMonitoringScannerImpl;
import com.radiusnetworks.ibeaconreference.beacons.ranging.BeaconRangingScanner;
import com.radiusnetworks.ibeaconreference.beacons.ranging.BeaconRangingScannerImpl;
import com.radiusnetworks.ibeaconreference.lifecycle.AppRunningMode;
import com.radiusnetworks.ibeaconreference.lifecycle.ContextProvider;
import com.radiusnetworks.ibeaconreference.orchextra.FeatureListener;
import com.radiusnetworks.ibeaconreference.permissions.PermissionChecker;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.powersave.BackgroundPowerSaver;

/**
 * Created by Sergio Martinez Rodriguez
 * Date 26/1/16.
 */
@Module
public class BeaconsModule {

  @Provides @Singleton BeaconManager provideBeaconManager(ContextProvider contextProvider){
    BeaconManager beaconManager = BeaconManager.getInstanceForApplication(contextProvider.getApplicationContext());

    //BeaconManager.setDebug(true);

    beaconManager.getBeaconParsers().add(new BeaconParser().
        setBeaconLayout("m:2-3=0215,i:4-19,i:20-21,i:22-23,p:24-24,d:25-25"));

          //try{
               //set the duration of the scan to be 1.1 seconds
              //beaconManager.setBackgroundScanPeriod(1500l);
              beaconManager.setBackgroundBetweenScanPeriod(23000l);//3600000l
              //beaconManager.updateScanPeriods();
              //beaconManager.startMonitoringBeaconsInRegion(region);
              //beaconManager.setAndroidLScanningDisabled(true);

          //}  catch (RemoteException e) {
          //    Log.e("BeaconsModule", "LOG :: Cannot talk to service");
          //}

    return beaconManager;
  }

  @Provides @Singleton BackgroundPowerSaver BackgroundPowerSaver(ContextProvider contextProvider){
    return new BackgroundPowerSaver(contextProvider.getApplicationContext());
  }

  @Provides @Singleton BeaconScanner provideBeaconScanner(RegionMonitoringScanner regionMonitoringScanner,
      BeaconRangingScanner beaconRangingScanner, AppRunningMode appRunningMode, BeaconManager beaconManager,
      BluetoothStatusInfo bluetoothStatusInfo){

    return new BeaconScannerImpl(regionMonitoringScanner, beaconRangingScanner, beaconManager
        , bluetoothStatusInfo, appRunningMode);
  }

  @Provides @Singleton BeaconRangingScanner provideBeaconRangingScanner(BeaconManager beaconManager){
    return new BeaconRangingScannerImpl(beaconManager, beaconsController);
  }

  @Provides @Singleton RegionMonitoringScanner provideRegionMonitoringScanner(ContextProvider contextProvider,
    BeaconManager beaconManager, MonitoringListener monitoringListener, BeaconsController beaconsController){
    return new RegionMonitoringScannerImpl(contextProvider, beaconManager, monitoringListener,
        beaconsController);
  }

  @Provides @Singleton BeaconsController provideBeaconsController(){
    return new BeaconsController(orchextraBeaconMapper, orchextraRegionMapper, actionsScheduler);
  }

  @Provides @Singleton MonitoringListener provideMonitoringListener(AppRunningMode appRunningMode,
      BeaconRangingScanner beaconRangingScanner){
    return new MonitoringListenerImpl(appRunningMode, beaconRangingScanner);
  }

  @Provides @Singleton BluetoothAvailability provideBluetoothAvailability(BeaconManager beaconManager){
    return new BluetoothAvailabilityImpl(beaconManager);
  }

  @Provides @Singleton BluetoothStatusInfo provideBluetoothStatusInfo(BluetoothAvailability bluetoothAvailability,
      PermissionChecker permissionChecker, ContextProvider contextProvider, AppRunningMode appRunningMode,
      FeatureListener featureListener){
    return new BluetoothStatusInfoImpl(permissionChecker, bluetoothAvailability, contextProvider,
        appRunningMode, featureListener);
  }

}

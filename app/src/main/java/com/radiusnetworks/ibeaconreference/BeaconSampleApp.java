package com.radiusnetworks.ibeaconreference;

import android.app.Application;
import android.util.Log;
import com.radiusnetworks.ibeaconreference.lifecycle.ContextProvider;
import com.radiusnetworks.ibeaconreference.lifecycle.OrchextraActivityLifecycle;
import com.radiusnetworks.ibeaconreference.orchextra.Orchextra;
import com.radiusnetworks.ibeaconreference.orchextra.OrchextraCompletionCallback;
import com.radiusnetworks.ibeaconreference.service.AppStatusEventsListenerImpl;
import com.radiusnetworks.ibeaconreference.service.BackgroundTasksManager;
import com.radiusnetworks.ibeaconreference.service.BackgroundTasksManagerImpl;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.powersave.BackgroundPowerSaver;

/**
 * Created by Sergio Martinez Rodriguez
 * Date 4/1/16.
 */
public class BeaconSampleApp extends Application implements OrchextraCompletionCallback{
    private static final String TAG = ".MyApplicationName";

    //private static BackgroundTasksManager backgroundTasksManager;
    //private static BeaconManager beaconManager;
    //private static ContextProvider contextProvider;
    //private static BackgroundPowerSaver backgroundPowerSaver;

  //public static BeaconManager getBeaconManagerInstance() {
  //  if (beaconManager==null){
  //    beaconManager = BeaconManager.getInstanceForApplication(contextProvider.getApplicationContext());
  //
  //      beaconManager.setDebug(true);
  //      backgroundPowerSaver = new BackgroundPowerSaver(contextProvider.getApplicationContext());
  //      beaconManager.getBeaconParsers().add(new BeaconParser().
  //          setBeaconLayout("m:2-3=0215,i:4-19,i:20-21,i:22-23,p:24-24,d:25-25"));
  //
  //    //  //try{
  //    //       //set the duration of the scan to be 1.1 seconds
  //    //      //beaconManager.setBackgroundScanPeriod(1500l);
  //    //      //beaconManager.setBackgroundBetweenScanPeriod(5000l);//3600000l
  //    //      //beaconManager.updateScanPeriods();
  //    //      //beaconManager.startMonitoringBeaconsInRegion(region);
  //    //      //beaconManager.setAndroidLScanningDisabled(true);
  //    //
  //    //  //}  catch (RemoteException e) {
  //    //  //    Log.e(TAG, "Cannot talk to service");
  //    //  //}
  //    //
  //  }
  //  return beaconManager;
  //}

  @Override
    public void onCreate() {
      super.onCreate();
      Log.d(TAG, "App started up");
      //OrchextraActivityLifecycle orchextraActivityLifecycle =
      //    new OrchextraActivityLifecycle(this.getApplicationContext(),
      //    new AppStatusEventsListenerImpl(this.getApplicationContext()));
      //
      //registerActivityLifecycleCallbacks(orchextraActivityLifecycle);
      //
      //contextProvider = orchextraActivityLifecycle;
      Orchextra.sdkInitialize(this, this);
    }

  @Override public void onSuccess() {
    Log.d(TAG, "LOG :: SUCESS INITIALIZATON");
  }

  @Override public void onError(String s) {
    Log.d(TAG, "LOG :: ERROR INITIALIZATION :: \n" + s);
  }

  //public static BackgroundTasksManager getbackgroundTasksManagerInstance() {
  //  if (backgroundTasksManager==null){
  //    backgroundTasksManager = new BackgroundTasksManagerImpl(contextProvider.getApplicationContext());
  //  }
  //  return backgroundTasksManager;
  //}

}

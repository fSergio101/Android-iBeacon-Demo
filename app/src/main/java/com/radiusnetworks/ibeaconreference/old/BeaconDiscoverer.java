package com.radiusnetworks.ibeaconreference.old;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.RemoteException;
import android.util.Log;
import java.util.Collection;
import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.RangeNotifier;
import org.altbeacon.beacon.Region;

/**
 * Created by Sergio Martinez Rodriguez
 * Date 20/1/16.
 */
public class BeaconDiscoverer implements BeaconConsumer {

  private static final String TAG = "BeaconScannerImpl";

  private BeaconManager beaconManager;
  private Context context;

  public void startDiscoveringProccess(Context context) {
    this.context = context;
    beaconManager = BeaconManager.getInstanceForApplication(context);
    beaconManager.getBeaconParsers().add(new BeaconParser().
        setBeaconLayout("m:2-3=0215,i:4-19,i:20-21,i:22-23,p:24-24,d:25-25"));
    beaconManager.bind(this);
  }

  @Override public void onBeaconServiceConnect() {
    beaconManager.setRangeNotifier(new RangeNotifier() {
      @Override
      public void didRangeBeaconsInRegion(Collection<Beacon> beacons, Region region) {
        if (beacons.size() > 0) {
          Log.i(TAG, "The first beacon I see is about "
              + beacons.iterator().next().getDistance()
              + " meters away.");
        }else{
          Log.i(TAG, "No more beacons here");
        }
      }
    });

    try {
      beaconManager.startRangingBeaconsInRegion(new Region("myRangingUniqueId", null, null, null));
    } catch (RemoteException e) {    }
  }

  @Override public Context getApplicationContext() {
    return context;
  }

  @Override public void unbindService(ServiceConnection serviceConnection) {

  }

  @Override public boolean bindService(Intent intent, ServiceConnection serviceConnection, int i) {
    return false;
  }
}

package com.radiusnetworks.ibeaconreference.orchextra.actions;

import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.gcm.GcmNetworkManager;
import com.google.android.gms.gcm.OneoffTask;
import com.google.android.gms.gcm.Task;
import com.radiusnetworks.ibeaconreference.orchextra.FeatureListener;
import com.radiusnetworks.ibeaconreference.orchextra.GooglePlayServicesFeature;
import com.radiusnetworks.ibeaconreference.service.OrchextraGcmTaskService;

/**
 * Created by Sergio Martinez Rodriguez
 * Date 3/2/16.
 */
public class ActionsSchedulerGcmImpl implements ActionsScheduler {

  private static final long DEFAULT_DELAY = 3600L;

  private final GcmNetworkManager gcmNetworkManager;

  public ActionsSchedulerGcmImpl(Context context, FeatureListener featureListener) {
    checkPlayServicesStatus(context, featureListener);
    gcmNetworkManager = GcmNetworkManager.getInstance(context);
  }

  private int checkPlayServicesStatus(Context context, FeatureListener featureListener) {
    GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
    int status = googleApiAvailability.isGooglePlayServicesAvailable(context);
    featureListener.onFeatureStatusChanged(new GooglePlayServicesFeature(status));
    return status;
  }

  @Override public void scheduleAction(ScheduledAction action) {

    Bundle bundle = new Bundle();
    bundle.putParcelable(action);

    OneoffTask task = new OneoffTask.Builder()
        .setService(OrchextraGcmTaskService.class)
        .setTag(action.getId())
        .setExecutionWindow(action.getScheduleTime(), DEFAULT_DELAY)
        .setPersisted(true)
        .setUpdateCurrent(true)
        .setRequiresCharging(false)
        .setRequiredNetwork(Task.NETWORK_STATE_ANY)
        .setExtras(bundle)
        .build();

    gcmNetworkManager.schedule(task);
  }

  @Override public void cancelAction(ScheduledAction action) {
    gcmNetworkManager.cancelTask(action.getId(), OrchextraGcmTaskService.class);
  }

  @Override public boolean hasPersistence() {
    return true;
  }
}

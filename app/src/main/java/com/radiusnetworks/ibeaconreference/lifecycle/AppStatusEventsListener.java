package com.radiusnetworks.ibeaconreference.lifecycle;

import com.radiusnetworks.ibeaconreference.orchextra.ForegroundTasksManager;

/**
 * Created by Sergio Martinez Rodriguez
 * Date 25/1/16.
 */
public interface AppStatusEventsListener {

  void onBackgroundStart();

  void onBackgroundEnd();

  void onForegroundStart();

  void onForegroundEnd();

  void onServiceRecreated();

  void setForegroundTasksManager(ForegroundTasksManager foregroundTasksManager);

}

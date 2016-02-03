package com.radiusnetworks.ibeaconreference.service;

import com.radiusnetworks.ibeaconreference.beacons.BeaconScanner;

/**
 * Created by Sergio Martinez Rodriguez
 * Date 25/1/16.
 */
public class BackgroundTasksManagerImpl implements BackgroundTasksManager {

  private final BeaconScanner beaconScanner;

  public BackgroundTasksManagerImpl(BeaconScanner beaconScanner) {
    this.beaconScanner = beaconScanner;
  }

  @Override public void startBackgroundTasks() {
      beaconScanner.startMonitoring();
  }

  @Override public void finalizeBackgroundTasks() {
    //beaconScanner.stopMonitoring();
  }

}

package com.radiusnetworks.ibeaconreference.lifecycle;

import android.app.Activity;

/**
 * Created by Sergio Martinez Rodriguez
 * Date 29/1/16.
 */
public class AppRunningModeImpl implements AppRunningMode {

  private OrchextraActivityLifecycle orchextraActivityLifecycle;

  public void setOrchextraActivityLifecycle(OrchextraActivityLifecycle orchextraActivityLifecycle) {
    this.orchextraActivityLifecycle = orchextraActivityLifecycle;
  }

  //region running Mode interface

  @Override public AppRunningModeType getRunningModeType() {
    Activity activity = orchextraActivityLifecycle.lastPausedActivity();

    if (activity!=null){
      return AppRunningModeType.FOREGROUND;
    }else{
      return AppRunningModeType.BACKGROUND;
    }
  }

  //endregion


}

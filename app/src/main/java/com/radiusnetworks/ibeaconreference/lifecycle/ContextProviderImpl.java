package com.radiusnetworks.ibeaconreference.lifecycle;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

/**
 * Created by Sergio Martinez Rodriguez
 * Date 29/1/16.
 */
public class ContextProviderImpl implements ContextProvider {

  private static final String TAG = "ContextProviderImpl";
  private final Context context;

  private OrchextraActivityLifecycle orchextraActivityLifecycle;

  public ContextProviderImpl(Context context) {
    this.context = context;
  }

  public void setOrchextraActivityLifecycle(OrchextraActivityLifecycle orchextraActivityLifecycle) {
    this.orchextraActivityLifecycle = orchextraActivityLifecycle;
  }

  //region context provider interface
  @Override public Activity getCurrentActivity() {
    if (orchextraActivityLifecycle==null){
      Log.w(TAG, "LOG ::Calling activity context before app finished initialization");
      return null;
    }
    return orchextraActivityLifecycle.getCurrentActivity();
  }

  @Override public boolean isActivityContextAvailable() {
    if (orchextraActivityLifecycle==null){
      return false;
    }
    //this implementation gives context of paused and stopped activities
    return orchextraActivityLifecycle.isActivityContextAvailable();
  }

  @Override public Context getApplicationContext() {
    return context;
  }

  @Override public boolean isApplicationContextAvailable() {
    return (context != null);
  }

  //endregion

}

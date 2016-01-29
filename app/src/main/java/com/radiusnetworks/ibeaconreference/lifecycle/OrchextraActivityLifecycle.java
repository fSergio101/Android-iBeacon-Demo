package com.radiusnetworks.ibeaconreference.lifecycle;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.Stack;

/**
 * Created by Sergio Martinez Rodriguez
 * Date 18/1/16.
 */
public class OrchextraActivityLifecycle implements Application.ActivityLifecycleCallbacks{

  private static final String TAG = "OrchextraActivityLifecycle";
  private Stack<ActivityLifecyleWrapper> activityStack = new Stack<>();
  private final Context applicationContext;
  private final AppStatusEventsListener appStatusEventsListener;

  public OrchextraActivityLifecycle(Context applicationContext,
      AppStatusEventsListener appStatusEventsListener) {
    this.applicationContext = applicationContext;
    this.appStatusEventsListener = appStatusEventsListener;
  }

  //region Activity lifecycle Management

  @Override public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
    this.activityStack.push(new ActivityLifecyleWrapper(activity, true, true));
  }

  @Override public void onActivityStarted(Activity activity) {
    try {
      boolean wasInBackground = endBackgroundModeIfNeeded();
      setCurrentStackActivityAsNotStopped();
      if (wasInBackground){
        startForegroundMode();
      }
      cleanZombieWrappersAtStack();
    } catch (EmptyStackException e) {
      //TODO do something interesting
      Log.e(TAG,"LOG :: EmptyStackException in activity lifecycle");
    }
  }

  private void startForegroundMode() {
    appStatusEventsListener.onForegroundStart();
  }

  private boolean endBackgroundModeIfNeeded() {
    //NOTE last paused activity == null means app is in background
    if (lastPausedActivity() == null){
      appStatusEventsListener.onBackgroundEnd();
      return true;
    }else{
      return false;
    }
  }

  private void setCurrentStackActivityAsNotStopped() {
    activityStack.peek().setIsStopped(false);
  }

  @Override public void onActivityResumed(Activity activity) {
    activityStack.peek().setIsPaused(false);
  }

  @Override public void onActivityPaused(Activity activity) {
    activityStack.peek().setIsPaused(true);
  }

  @Override public void onActivityStopped(Activity activity) {
    if (appWillGoToBackground()){
      appStatusEventsListener.onForegroundEnd();
    }
    setCurrentStackActivityAsStopped();
    setBackgroundModeIfNeeded();
  }

  private void setCurrentStackActivityAsStopped() {
    activityStack.peek().setIsStopped(true);
  }

  private void setBackgroundModeIfNeeded() {
    //NOTE last paused activity == null means app is in background
    if (lastPausedActivity() == null){
      appStatusEventsListener.onBackgroundStart();
    }
  }

  @Override public void onActivityDestroyed(Activity activity) {
    this.activityStack.pop();
  }

  @Override public void onActivitySaveInstanceState(Activity activity, Bundle outState) {}

  //endregion

  //region StackUtils

  //region last activities recovery

  private boolean appWillGoToBackground() {
    Iterator<ActivityLifecyleWrapper> iter = activityStack.iterator();

    int i = 0;
    while (iter.hasNext()){
      ActivityLifecyleWrapper activityLifecyleWrapper = iter.next();
      if (!activityLifecyleWrapper.isStopped()){
        i++;
      }
    }
    return (i == 1);
  }

  public Activity lastPausedActivity() {

    for (ActivityLifecyleWrapper activityLifecyleWrapper : activityStack) {
      if (!activityLifecyleWrapper.isStopped()) {
        return activityLifecyleWrapper.getActivity();
      }
    }
    return null;
  }

  private Activity lastForegroundActivity() {

    for (ActivityLifecyleWrapper activityLifecyleWrapper : activityStack) {
      if (!activityLifecyleWrapper.isPaused()) {
        return activityLifecyleWrapper.getActivity();
      }
    }
    return null;
  }

  //endregion

  //region CleanStack

  private void cleanZombieWrappersAtStack() {

    Iterator<ActivityLifecyleWrapper> iter = activityStack.iterator();

    while (iter.hasNext()){
      ActivityLifecyleWrapper activityLifecyleWrapper = iter.next();
      if (isActivityDestroyed(activityLifecyleWrapper.getActivity())){
        iter.remove();
      }
    }
  }

  private boolean isActivityDestroyed(Activity activity) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1){
      return checkActivityDestroyedUnderV17(activity);
    }else{
      return checkActivityDestroyedV17(activity);
    }
  }

  private boolean checkActivityDestroyedUnderV17(Activity activity) {
    return activity == null || activity.getBaseContext() == null;
  }

  @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
  private boolean checkActivityDestroyedV17(Activity activity) {
    return activity == null || activity.isDestroyed();
  }

  public AppStatusEventsListener getAppStatusEventsListener() {
    return appStatusEventsListener;
  }

  public Activity getCurrentActivity() {
    Activity activity = lastForegroundActivity();

    if (activity!=null){
      return activity;
    }

    activity = lastPausedActivity();

    if (activity!=null){
      return activity;
    }

    if (activityStack.size()>0){
      activity = activityStack.peek().getActivity();

      if (activity!=null){
        return activity;
      }
    }

    return null;
  }

  public boolean isActivityContextAvailable() {
    return (applicationContext != null);
  }

  public Context getApplicationContext() {
    return applicationContext;
  }

  public boolean isApplicationContextAvailable() {
     return (applicationContext != null);
  }

  //endregion

  //endregion
}

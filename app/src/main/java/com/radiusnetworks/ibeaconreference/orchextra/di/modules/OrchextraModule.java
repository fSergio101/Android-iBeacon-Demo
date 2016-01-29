package com.radiusnetworks.ibeaconreference.orchextra.di.modules;

import android.content.Context;
import com.radiusnetworks.ibeaconreference.beacons.BeaconScanner;
import com.radiusnetworks.ibeaconreference.lifecycle.AppRunningMode;
import com.radiusnetworks.ibeaconreference.lifecycle.AppStatusEventsListener;
import com.radiusnetworks.ibeaconreference.lifecycle.ContextProvider;
import com.radiusnetworks.ibeaconreference.lifecycle.OrchextraActivityLifecycle;
import com.radiusnetworks.ibeaconreference.orchextra.ForegroundTasksManager;
import com.radiusnetworks.ibeaconreference.orchextra.ForegroundTasksManagerImpl;
import com.radiusnetworks.ibeaconreference.service.AppStatusEventsListenerImpl;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

/**
 * Created by Sergio Martinez Rodriguez
 * Date 24/11/15.
 */
@Module(includes = BeaconsModule.class)
public class OrchextraModule {

  private Context context;

  public OrchextraModule(Context context) {
    this.context = context;
  }

  @Provides
  @Singleton AppStatusEventsListener provideAppStatusEventsListener(){
    return new AppStatusEventsListenerImpl(context);
  }

  @Provides
  @Singleton OrchextraActivityLifecycle provideOrchextraActivityLifecycle(AppStatusEventsListener appStatusEventsListener) {
    return new OrchextraActivityLifecycle(context, appStatusEventsListener);
  }

  @Provides
  @Singleton ContextProvider provideContextProvider(OrchextraActivityLifecycle orchextraActivityLifecycle) {
    return orchextraActivityLifecycle;
  }

  @Provides @Singleton AppRunningMode provideAppRunningMode(OrchextraActivityLifecycle orchextraActivityLifecycle){
    return orchextraActivityLifecycle;
  }

  @Singleton @Provides ForegroundTasksManager provideBackgroundTasksManager(AppStatusEventsListener appStatusEventsListener, BeaconScanner beaconScanner){
    ForegroundTasksManagerImpl foregroundTasksManager =  new ForegroundTasksManagerImpl(beaconScanner);
    appStatusEventsListener.setForegroundTasksManager(foregroundTasksManager);
    return foregroundTasksManager;
  }

}

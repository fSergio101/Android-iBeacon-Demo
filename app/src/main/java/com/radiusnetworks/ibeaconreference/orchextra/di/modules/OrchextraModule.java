package com.radiusnetworks.ibeaconreference.orchextra.di.modules;

import android.content.Context;
import com.radiusnetworks.ibeaconreference.beacons.BeaconScanner;
import com.radiusnetworks.ibeaconreference.lifecycle.AppRunningMode;
import com.radiusnetworks.ibeaconreference.lifecycle.AppRunningModeImpl;
import com.radiusnetworks.ibeaconreference.lifecycle.AppStatusEventsListener;
import com.radiusnetworks.ibeaconreference.lifecycle.ContextProvider;
import com.radiusnetworks.ibeaconreference.lifecycle.ContextProviderImpl;
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
  @Singleton AppStatusEventsListener provideAppStatusEventsListener(ForegroundTasksManager foregroundTasksManager){
    AppStatusEventsListener appStatusEventsListener = new AppStatusEventsListenerImpl(context, foregroundTasksManager);
    return appStatusEventsListener;
  }

  @Provides
  @Singleton OrchextraActivityLifecycle provideOrchextraActivityLifecycle(AppRunningMode appRunningMode, ContextProvider contextProvider,
      AppStatusEventsListener appStatusEventsListener) {
    OrchextraActivityLifecycle orchextraActivityLifecycle = new OrchextraActivityLifecycle(context, appStatusEventsListener);
    contextProvider.setOrchextraActivityLifecycle(orchextraActivityLifecycle);
    appRunningMode.setOrchextraActivityLifecycle(orchextraActivityLifecycle);
    return orchextraActivityLifecycle;
  }

  @Provides
  @Singleton ContextProvider provideContextProvider() {
    return new ContextProviderImpl(context.getApplicationContext());
  }

  @Provides @Singleton AppRunningMode provideAppRunningMode(){
    return new AppRunningModeImpl();
  }

  @Singleton @Provides ForegroundTasksManager provideBackgroundTasksManager(BeaconScanner beaconScanner){
    ForegroundTasksManagerImpl foregroundTasksManager =  new ForegroundTasksManagerImpl(beaconScanner);
    return foregroundTasksManager;
  }

}

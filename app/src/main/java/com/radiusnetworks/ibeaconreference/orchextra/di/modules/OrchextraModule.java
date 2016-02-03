package com.radiusnetworks.ibeaconreference.orchextra.di.modules;

import android.content.Context;
import com.radiusnetworks.ibeaconreference.beacons.BeaconScanner;
import com.radiusnetworks.ibeaconreference.lifecycle.AppRunningMode;
import com.radiusnetworks.ibeaconreference.lifecycle.AppRunningModeImpl;
import com.radiusnetworks.ibeaconreference.lifecycle.AppStatusEventsListener;
import com.radiusnetworks.ibeaconreference.lifecycle.ContextProvider;
import com.radiusnetworks.ibeaconreference.lifecycle.ContextProviderImpl;
import com.radiusnetworks.ibeaconreference.lifecycle.OrchextraActivityLifecycle;
import com.radiusnetworks.ibeaconreference.orchextra.FeatureList;
import com.radiusnetworks.ibeaconreference.orchextra.FeatureListener;
import com.radiusnetworks.ibeaconreference.orchextra.FeatureStatus;
import com.radiusnetworks.ibeaconreference.orchextra.ForegroundTasksManager;
import com.radiusnetworks.ibeaconreference.orchextra.ForegroundTasksManagerImpl;
import com.radiusnetworks.ibeaconreference.orchextra.OrchextraCompletionCallback;
import com.radiusnetworks.ibeaconreference.orchextra.actions.ActionsScheduler;
import com.radiusnetworks.ibeaconreference.orchextra.actions.ActionsSchedulerController;
import com.radiusnetworks.ibeaconreference.orchextra.actions.ActionsSchedulerControllerImpl;
import com.radiusnetworks.ibeaconreference.orchextra.actions.ActionsSchedulerGcmImpl;
import com.radiusnetworks.ibeaconreference.orchextra.actions.ActionsSchedulerPersistor;
import com.radiusnetworks.ibeaconreference.orchextra.actions.ActionsSchedulerPersistorNullImpl;
import com.radiusnetworks.ibeaconreference.permissions.AndroidPermissionCheckerImpl;
import com.radiusnetworks.ibeaconreference.permissions.PermissionChecker;
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

  private final Context context;
  private final OrchextraCompletionCallback orchextraCompletionCallback;

  public OrchextraModule(Context context, OrchextraCompletionCallback orchextraCompletionCallback) {
    this.context = context;
    this.orchextraCompletionCallback = orchextraCompletionCallback;
  }

  @Provides
  @Singleton AppStatusEventsListener provideAppStatusEventsListener(ForegroundTasksManager foregroundTasksManager){
    return new AppStatusEventsListenerImpl(context, foregroundTasksManager);
  }

  @Provides
  @Singleton OrchextraActivityLifecycle provideOrchextraActivityLifecycle(AppRunningMode appRunningMode, ContextProvider contextProvider,
      AppStatusEventsListener appStatusEventsListener) {

    OrchextraActivityLifecycle orchextraActivityLifecycle = new OrchextraActivityLifecycle(appStatusEventsListener);
    contextProvider.setOrchextraActivityLifecycle(orchextraActivityLifecycle);
    appRunningMode.setOrchextraActivityLifecycle(orchextraActivityLifecycle);
    return orchextraActivityLifecycle;
  }

  @Provides
  @Singleton PermissionChecker providesPermissionChecker(ContextProvider contextProvider){
    return new AndroidPermissionCheckerImpl(contextProvider.getApplicationContext(), contextProvider);
  }

  @Provides
  @Singleton ContextProvider provideContextProvider() {
    return new ContextProviderImpl(context.getApplicationContext());
  }

  @Provides @Singleton AppRunningMode provideAppRunningMode(){
    return new AppRunningModeImpl();
  }

  @Singleton @Provides ForegroundTasksManager provideBackgroundTasksManager(BeaconScanner beaconScanner){
    return  new ForegroundTasksManagerImpl(beaconScanner);
  }

  @Singleton @Provides FeatureList provideFeatureList(){
    return new FeatureList(orchextraCompletionCallback);
  }

  @Singleton @Provides FeatureListener provideFeatureListener(FeatureList featureList){
    return featureList;
  }

  @Singleton @Provides FeatureStatus provideFeatureStatus(FeatureList featureList){
    return featureList;
  }

  @Singleton @Provides ActionsScheduler provideActionsScheduler(ContextProvider contextProvider){
    return new ActionsSchedulerGcmImpl(contextProvider.getApplicationContext(), featureListener);
  }

  @Singleton @Provides ActionsSchedulerPersistor provideActionsSchedulerPersistorNull(){
    return new ActionsSchedulerPersistorNullImpl();
  }

  @Singleton @Provides ActionsSchedulerController provideActionsSchedulerController(
      ActionsScheduler actionsScheduler, ActionsSchedulerPersistor actionsSchedulerPersistor){

    if (actionsScheduler.hasPersistence() &&
        !(actionsSchedulerPersistor instanceof ActionsSchedulerPersistorNullImpl)){
      throw new IllegalArgumentException("Param ActionsSchedulerPersistor in"
          + " ActionsSchedulerControllerImpl MUST be NullObject when ActionsScheduler "
          + "already supports persistence ");
    }

    return new ActionsSchedulerControllerImpl(actionsScheduler, actionsSchedulerPersistor);

  }


}

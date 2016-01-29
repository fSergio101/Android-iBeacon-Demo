package com.radiusnetworks.ibeaconreference.orchextra.di.modules;

import com.radiusnetworks.ibeaconreference.beacons.BeaconScanner;
import com.radiusnetworks.ibeaconreference.orchextra.di.scopes.PerService;
import com.radiusnetworks.ibeaconreference.service.BackgroundTasksManager;
import com.radiusnetworks.ibeaconreference.service.BackgroundTasksManagerImpl;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Sergio Martinez Rodriguez
 * Date 26/1/16.
 */
@Module
public class BackgroundModule {

  @PerService @Provides BackgroundTasksManager provideBackgroundTasksManager(BeaconScanner beaconScanner){
    return new BackgroundTasksManagerImpl(beaconScanner);
  }

}

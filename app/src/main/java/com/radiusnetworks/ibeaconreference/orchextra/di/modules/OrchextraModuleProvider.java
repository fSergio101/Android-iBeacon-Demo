package com.radiusnetworks.ibeaconreference.orchextra.di.modules;

import com.radiusnetworks.ibeaconreference.lifecycle.AppRunningMode;
import com.radiusnetworks.ibeaconreference.lifecycle.AppStatusEventsListener;
import com.radiusnetworks.ibeaconreference.lifecycle.ContextProvider;
import com.radiusnetworks.ibeaconreference.lifecycle.OrchextraActivityLifecycle;

/**
 * Created by Sergio Martinez Rodriguez
 * Date 11/12/15.
 */
public interface OrchextraModuleProvider {
  AppRunningMode provideAppRunningModeType();
  ContextProvider provideContextProvider();
  AppStatusEventsListener provideAppStatusEventsListener();
  OrchextraActivityLifecycle provideOrchextraActivityLifecycle();
}

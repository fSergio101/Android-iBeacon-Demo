package com.radiusnetworks.ibeaconreference.orchextra;

import android.app.Application;
import android.content.Context;
import com.radiusnetworks.ibeaconreference.lifecycle.OrchextraActivityLifecycle;
import com.radiusnetworks.ibeaconreference.orchextra.di.components.DaggerOrchextraComponent;
import com.radiusnetworks.ibeaconreference.orchextra.di.components.OrchextraComponent;
import com.radiusnetworks.ibeaconreference.orchextra.di.injector.InjectorImpl;
import com.radiusnetworks.ibeaconreference.orchextra.di.modules.OrchextraModule;
import javax.inject.Inject;

/**
 * Created by Sergio Martinez Rodriguez
 * Date 23/11/15.
 */
public class Orchextra {

  private static Orchextra instance;
  private InjectorImpl injector;

  @Inject
  OrchextraActivityLifecycle orchextraActivityLifecycle;

  public void initDependencyInjection(Context applicationContext,
      OrchextraCompletionCallback orchextraCompletionCallback) {
   OrchextraComponent orchextraComponent = DaggerOrchextraComponent.builder()
        .orchextraModule(new OrchextraModule(applicationContext, orchextraCompletionCallback))
       .build();
    injector = new InjectorImpl(orchextraComponent);
    orchextraComponent.injectOrchextra(Orchextra.instance);
  }

  public static synchronized void sdkInitialize(Application application, OrchextraCompletionCallback orchextraCompletionCallback) {
    Orchextra.instance = new Orchextra();
    Orchextra.instance.init(application, orchextraCompletionCallback);
    Orchextra.instance.start();
  }

  private void init(Application application, OrchextraCompletionCallback orchextraCompletionCallback) {
    initDependencyInjection(application.getApplicationContext(), orchextraCompletionCallback);

    initLifecyle(application);

    //TODO before or after initLifecyle??
    checkSdkBasicPermissions();

  }

  private void initLifecyle(Application application) {
    application.registerActivityLifecycleCallbacks(orchextraActivityLifecycle);
  }

  private void start() {

  }

  private static void checkSdkBasicPermissions() {

  }

  public static InjectorImpl getInjector() {
    return Orchextra.instance.injector;
  }
}

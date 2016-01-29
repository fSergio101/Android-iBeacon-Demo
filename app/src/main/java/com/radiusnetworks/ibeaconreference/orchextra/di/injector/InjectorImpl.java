package com.radiusnetworks.ibeaconreference.orchextra.di.injector;

import com.radiusnetworks.ibeaconreference.orchextra.Orchextra;
import com.radiusnetworks.ibeaconreference.orchextra.di.components.DaggerServiceComponent;
import com.radiusnetworks.ibeaconreference.orchextra.di.components.OrchextraComponent;
import com.radiusnetworks.ibeaconreference.orchextra.di.components.ServiceComponent;
import com.radiusnetworks.ibeaconreference.service.MyAppService;

/**
 * Created by Sergio Martinez Rodriguez
 * Date 3/12/15.
 */
public class InjectorImpl implements Injector{

  private OrchextraComponent orchextraComponent;

  public InjectorImpl(OrchextraComponent orchextraComponent) {
    this.orchextraComponent = orchextraComponent;
  }

  @Override public ServiceComponent injectServiceComponent(MyAppService myAppService) {
    ServiceComponent serviceComponent = DaggerServiceComponent.builder().
        orchextraComponent(orchextraComponent).build();
    serviceComponent.injectOrchextraService(myAppService);
    return serviceComponent;
  }

}

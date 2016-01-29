package com.radiusnetworks.ibeaconreference.orchextra.di.injector;

import com.radiusnetworks.ibeaconreference.orchextra.di.components.ServiceComponent;
import com.radiusnetworks.ibeaconreference.service.OrchextraBackgroundService;

/**
 * Created by Sergio Martinez Rodriguez
 * Date 3/12/15.
 */
public interface Injector {
  ServiceComponent injectServiceComponent (OrchextraBackgroundService myAppService);
}

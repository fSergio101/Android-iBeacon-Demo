package com.radiusnetworks.ibeaconreference.orchextra.di.components;

import com.radiusnetworks.ibeaconreference.orchextra.di.modules.BackgroundModule;
import com.radiusnetworks.ibeaconreference.orchextra.di.scopes.PerService;
import com.radiusnetworks.ibeaconreference.service.MyAppService;
import dagger.Component;

/**
 * Created by Sergio Martinez Rodriguez
 * Date 24/11/15.
 */
@PerService @Component(dependencies = OrchextraComponent.class, modules = BackgroundModule.class)
public interface ServiceComponent{
    void injectOrchextraService(MyAppService myAppService);
}

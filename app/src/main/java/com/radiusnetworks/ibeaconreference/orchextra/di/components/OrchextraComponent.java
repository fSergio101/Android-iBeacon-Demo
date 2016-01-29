package com.radiusnetworks.ibeaconreference.orchextra.di.components;

import com.radiusnetworks.ibeaconreference.orchextra.Orchextra;
import com.radiusnetworks.ibeaconreference.orchextra.di.modules.BeaconsModuleProvider;
import com.radiusnetworks.ibeaconreference.orchextra.di.modules.OrchextraModule;
import com.radiusnetworks.ibeaconreference.orchextra.di.modules.OrchextraModuleProvider;
import dagger.Component;
import javax.inject.Singleton;

/**
 * Created by Sergio Martinez Rodriguez
 * Date 24/11/15.
 */
@Singleton @Component(modules = {OrchextraModule.class})
public interface OrchextraComponent extends OrchextraModuleProvider, BeaconsModuleProvider {
    void injectOrchextra(Orchextra orchextra);
}

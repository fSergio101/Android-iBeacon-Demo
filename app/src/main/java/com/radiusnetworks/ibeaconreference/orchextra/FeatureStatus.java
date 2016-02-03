package com.radiusnetworks.ibeaconreference.orchextra;

import java.util.List;

/**
 * Created by Sergio Martinez Rodriguez
 * Date 2/2/16.
 */
public interface FeatureStatus {

  List<Feature> getFeatures();
  boolean areAllFeaturesReady();
}

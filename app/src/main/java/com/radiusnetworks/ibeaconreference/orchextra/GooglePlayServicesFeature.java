package com.radiusnetworks.ibeaconreference.orchextra;

import com.radiusnetworks.ibeaconreference.beacons.GooglePlayServicesStatus;

/**
 * Created by Sergio Martinez Rodriguez
 * Date 3/2/16.
 */
public class GooglePlayServicesFeature extends Feature {

  public GooglePlayServicesFeature(int status) {
    super(FeatureType.GOOGLE_PLAY_SERVICES, GooglePlayServicesStatus.getGooglePlayServicesStatus(status));
  }

  @Override public boolean isSuccess() {
    return false;
  }
}

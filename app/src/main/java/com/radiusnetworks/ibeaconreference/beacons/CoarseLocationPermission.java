package com.radiusnetworks.ibeaconreference.beacons;

import android.Manifest;
import com.radiusnetworks.ibeaconreference.R;
import com.radiusnetworks.ibeaconreference.permissions.Permission;

/**
 * Created by Sergio Martinez Rodriguez
 * Date 16/1/16.
 */
public class CoarseLocationPermission implements Permission {

  @Override public String getAndroidPermissionStringType() {
    return  Manifest.permission.ACCESS_COARSE_LOCATION;
  }

  @Override public int getPermissionSettingsDeniedFeedback() {
    return R.string.accessCoarseLocationPermissionSettingsDeniedFeedback;
  }

  @Override public int getPermissionDeniedFeedback() {
    return R.string.continueRequestPermissionDeniedFeedback;
  }

  @Override public int getPermissionRationaleTitle() {
    return R.string.accessCoarseLocationPermissionRationaleTitle;
  }

  @Override public int getPermissionRationaleMessage() {
    return R.string.continueRequestPermissionRationaleMessage;
  }
}

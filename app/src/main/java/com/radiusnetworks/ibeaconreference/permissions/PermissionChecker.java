package com.radiusnetworks.ibeaconreference.permissions;

import android.app.Activity;

/**
 * Created by Sergio Martinez Rodriguez
 * Date 15/1/16.
 */
public interface PermissionChecker {
  void askForPermission(Permission permission, UserPermissionRequestResponseListener userResponse,
      Activity activity);
  void continuePendingPermissionsRequestsIfPossible();

  boolean isGranted(Permission permission);
}

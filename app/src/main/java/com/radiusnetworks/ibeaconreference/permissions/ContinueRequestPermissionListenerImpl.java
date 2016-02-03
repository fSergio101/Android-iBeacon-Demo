package com.radiusnetworks.ibeaconreference.permissions;

import com.radiusnetworks.ibeaconreference.R;
import com.radiusnetworks.ibeaconreference.lifecycle.ContextProvider;

/**
 * Created by Sergio Martinez Rodriguez
 * Date 17/1/16.
 */
public class ContinueRequestPermissionListenerImpl extends AbstractPermissionListener{

  public ContinueRequestPermissionListenerImpl(ContextProvider contextProvider) {
    super(contextProvider);
  }

  @Override public int getPermissionDeniedFeedback() {
    return R.string.continueRequestPermissionDeniedFeedback;
  }

  @Override public int getPermissionRationaleMessage() {
    return R.string.continueRequestPermissionRationaleMessage;
  }

  @Override public int getPermissionRationaleTitle() {
    return R.string.continueRequestPermissionRationaleTitle;
  }
}

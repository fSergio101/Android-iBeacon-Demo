package com.radiusnetworks.ibeaconreference.beacons.model;

import com.radiusnetworks.ibeaconreference.lifecycle.StringValueEnum;

/**
 * Created by Sergio Martinez Rodriguez
 * Date 3/2/16.
 */
public enum BeaconDistance implements StringValueEnum{
  FAR("far"),
  NEAR("near"),
  IMMEDIATE("immediate");

  private final String type;

  BeaconDistance(final String type) {
    this.type = type;
  }

  public String getStringValue() {
    return type;
  }
}

package com.radiusnetworks.ibeaconreference.beacons;

/**
 * Created by Sergio Martinez Rodriguez
 * Date 2/2/16.
 */
public interface Observer {
  void update(Subject observable, Object data);
}

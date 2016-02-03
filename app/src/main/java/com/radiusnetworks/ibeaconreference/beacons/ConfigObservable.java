package com.radiusnetworks.ibeaconreference.beacons;

import java.util.ArrayList;

/**
 * Created by Sergio Martinez Rodriguez
 * Date 2/2/16.
 */
public class ConfigObservable implements Subject{

  private ArrayList<Observer> observers;

  public ConfigObservable() {
    this.observers = new ArrayList<>();
  }

  @Override public void registerObserver(Observer o) {
    observers.add(o);
  }

  @Override public void removeObserver(Observer o) {
    int index = observers.indexOf(o);
    if (index>=0){
      observers.remove(index);
    }
  }

  @Override public void notifyObservers(Object configData) {
    for (Observer observer:observers){
      observer.update(this, configData);
    }
  }
}

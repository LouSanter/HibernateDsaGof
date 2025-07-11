package org.lousanter.model.observer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Observable {

    private static final Set<Observer> observers = new HashSet<>();


    public static void addObserver(Observer o) {
        if (!observers.contains(o)) {
            observers.add(o);
        }
    }

    public static void removeObserver(Observer o) {
        observers.remove(o);
    }

    public static void notifyObservers() {
        for (Observer o : observers) {
            o.update();
        }
    }
}

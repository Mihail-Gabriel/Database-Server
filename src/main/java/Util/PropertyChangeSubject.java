package Util;

import java.beans.PropertyChangeListener;

public interface PropertyChangeSubject {
    void addPropertyListener(PropertyChangeListener listener);
    void removePropertyListener(PropertyChangeListener listener);
}

package restart;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MyPropertyListener implements PropertyChangeListener {
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("속성이 변경되었습니다.");
        System.out.println("속성 이름: " + evt.getPropertyName());
        System.out.println("변경 전 값: " + evt.getOldValue());
        System.out.println("변경 후 값: " + evt.getNewValue());
    }
}

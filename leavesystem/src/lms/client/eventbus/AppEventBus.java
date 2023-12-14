package lms.client.eventbus;

import com.google.web.bindery.event.shared.Event;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;

public class AppEventBus {

    private static final EventBus EVENT_BUS = new SimpleEventBus();

    private AppEventBus() {
        // Private constructor to prevent instantiation
    }

    public static EventBus getInstance() {
        return EVENT_BUS;
    }

    public static void fireEvent(Event<?> event) {
        EVENT_BUS.fireEvent(event);
    }
}

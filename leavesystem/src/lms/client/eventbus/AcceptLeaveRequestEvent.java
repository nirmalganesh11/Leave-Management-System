package lms.client.eventbus;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

public class AcceptLeaveRequestEvent extends GwtEvent<AcceptLeaveRequestEvent.Handler> {
    public interface Handler extends EventHandler {
        void onAcceptLeaveRequest(AcceptLeaveRequestEvent event);
    }

    private static Type<Handler> TYPE;

    public AcceptLeaveRequestEvent() {
    }

    public static Type<Handler> getType() {
        if (TYPE == null) {
            TYPE = new Type<>();
        }
        return TYPE;
    }

    @Override
    public Type<Handler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(Handler handler) {
        handler.onAcceptLeaveRequest(this);
    }
}

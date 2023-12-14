package lms.client.eventbus;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

public class RejectLeaveRequestEvent extends GwtEvent<RejectLeaveRequestEvent.Handler> {
    public interface Handler extends EventHandler {
        void onRejectLeaveRequest(RejectLeaveRequestEvent event);
    }

    private static Type<Handler> TYPE;

    public RejectLeaveRequestEvent() {
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
        handler.onRejectLeaveRequest(this);
    }
}
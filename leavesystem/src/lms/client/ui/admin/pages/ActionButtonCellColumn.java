package lms.client.ui.admin.pages;

import java.util.LinkedList;
import java.util.List;

import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.CompositeCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.HasCell;
import com.google.gwt.user.cellview.client.Column;

import lms.client.ui.admin.pages.ActionButtonCell.ActionButtonCellHandler;
import lms.shared.utility.LeaveRequest;

public class ActionButtonCellColumn extends Column<LeaveRequest, LeaveRequest> {

    private final ActionButtonCellHandler handler;

    public ActionButtonCellColumn(ActionButtonCellHandler handler) {
        super(new ActionButtonCell(handler));
        this.handler = handler;
    }

    @Override
    public LeaveRequest getValue(LeaveRequest object) {
        return object;
    }

    @Override
    public Cell<LeaveRequest> getCell() {
        List<HasCell<LeaveRequest, ?>> cells = new LinkedList<>();

        cells.add(new HasCell<LeaveRequest, LeaveRequest>() {
            private ActionButtonCell cell = new ActionButtonCell(handler);

            @Override
            public Cell<LeaveRequest> getCell() {
                return cell;
            }

            @Override
            public FieldUpdater<LeaveRequest, LeaveRequest> getFieldUpdater() {
                return null; // We don't need field updater
            }

            @Override
            public LeaveRequest getValue(LeaveRequest object) {
                return object;
            }
        });

        return new CompositeCell<>(cells);
    }
}

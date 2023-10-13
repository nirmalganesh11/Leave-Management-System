package lms.shared.utility;

public enum LeaveStatus {
	
	APPROVED("APPROVED"),
    PENDING("PENDING"),
    REJECTED("REJECTED");

    private String value;

    LeaveStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static LeaveStatus fromValue(String value) {
        for (LeaveStatus status : values()) {
            if (status.value.equals(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown LeaveStatus value: " + value);
    }

}

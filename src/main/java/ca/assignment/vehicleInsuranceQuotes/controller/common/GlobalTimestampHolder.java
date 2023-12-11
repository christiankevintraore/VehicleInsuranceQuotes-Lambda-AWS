package ca.assignment.vehicleInsuranceQuotes.controller.common;

import java.time.LocalDateTime;

public class GlobalTimestampHolder {

    private static final ThreadLocal<LocalDateTime> TIMESTAMP_HOLDER = new ThreadLocal<>();
    static {
    	setTimestamp(LocalDateTime.now());
    }

    
    public static void setTimestamp(LocalDateTime timestamp) {
    	TIMESTAMP_HOLDER.set(timestamp);
    }

    public static LocalDateTime getTimestamp() {
        return TIMESTAMP_HOLDER.get();
    }

    public static void clear() {
    	TIMESTAMP_HOLDER.remove();
    }
}

package net.intelie.challenges;

import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * This is just an event stub, feel free to expand it if needed.
 */
public class Event {
    private final String type;
    private final long timestamp;

    public Event(String type, long timestamp) {
        this.type = type;
        this.timestamp = timestamp;
    }

    public String type() {
        return type;
    }

    public long timestamp() {
        return timestamp;
    }

    public String toString(){
        return "[" + this.type() + "] Ev " + this.timestamp();
    }
}

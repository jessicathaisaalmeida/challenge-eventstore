package net.intelie.challenges.inmemory;

import net.intelie.challenges.Event;
import net.intelie.challenges.EventIterator;
import net.intelie.challenges.EventStore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryEventStore implements EventStore {

    private ConcurrentHashMap<String, List<Event>> events;
    private AtomicInteger size = new AtomicInteger(0);

    public InMemoryEventStore(){
        events = new ConcurrentHashMap<>();
    }

    public int getSize() {
        return size.get();
    }

    public int getSize(String type) {
        List theEvents = events.get(type);
        return  theEvents == null ? 0 : theEvents.size();
    }

    public void printEvents(){
        System.out.println("----------------------------------------");
        events.forEach((eventType, theEvents) -> {
            System.out.println(">>> " + eventType);
            theEvents.forEach((event) -> {
                System.out.println("Ev " + event.timestamp());
            });
            System.out.println("----------------------------------------");
        });
    }

    /**
     * Stores an event
     *
     * @param event
     */
    @Override
    public void insert(Event event) {
        //SYNCHRONIZED usage in order to prevent multiple access to
        //the test if the event type is not registered yet
        synchronized (events) {
            if(events.get(event.type()) == null)
            events.put(event.type(), Collections.synchronizedList(new ArrayList()));
        }

        events.get(event.type()).add(event);
        size.incrementAndGet();
    }

    /**
     * Removes all events of specific type.
     *
     * @param type
     */
    @Override
    public void removeAll(String type) {

    }

    /**
     * Retrieves an iterator for events based on their type and timestamp.
     *
     * @param type      The type we are querying for.
     * @param startTime Start timestamp (inclusive).
     * @param endTime   End timestamp (exclusive).
     * @return An iterator where all its events have same type as
     * {@param type} and timestamp between {@param startTime}
     * (inclusive) and {@param endTime} (exclusive).
     */
    @Override
    public EventIterator query(String type, long startTime, long endTime) {
        return null;
    }
}

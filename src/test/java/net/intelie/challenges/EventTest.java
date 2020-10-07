package net.intelie.challenges;

import net.intelie.challenges.inmemory.InMemoryEventStore;
import org.junit.Test;

import java.time.Instant;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.Assert.assertEquals;

public class EventTest {

    @Test
    public void thisIsAWarning() throws Exception {
        Event event = new Event("some_type", 123L);

        //THIS IS A WARNING:
        //Some of us (not everyone) are coverage freaks.
        assertEquals(123L, event.timestamp());
        assertEquals("some_type", event.type());
    }

    @Test
    public void thisIsASimpleInsertion() throws Exception {
        InMemoryEventStore store = new InMemoryEventStore();
        Event event = new Event("some_type", 123L);
        store.insert(event);
        store.printEvents();

        //THIS IS A SIMPLE INSERTION:
        //Testing a insertion of one single event
        assertEquals(1, store.getSize());
    }

    @Test
    public void thisIsASeveralInsertion() throws Exception {
        int numberOfThreads = 10;
        String[] types = {"type_a","type_b","type_c"};
        ExecutorService service = Executors.newFixedThreadPool(numberOfThreads);
        CountDownLatch latch = new CountDownLatch(numberOfThreads * types.length);

        InMemoryEventStore store = new InMemoryEventStore();
        for (String type : types)
            for (int i = 0; i < numberOfThreads; i++) {
                service.submit(() -> {
                    Instant random = RandomTimestamp.timestamp();
                    Event event = new Event(type, random.toEpochMilli());
                    store.insert(event);

                    latch.countDown();
                });
            }

        latch.await();
        store.printEvents();

        //THIS IS A SEVERAL INSERTION:
        //Testing an insertion of several events and verifying if all threads were
        // executed properly for each type.
        assertEquals(numberOfThreads * types.length, store.getSize());
        for (String type : types)
            assertEquals(numberOfThreads, store.getSize(type));
    }
}
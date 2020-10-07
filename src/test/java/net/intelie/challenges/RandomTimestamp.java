package net.intelie.challenges;

import java.time.Instant;
import java.util.concurrent.ThreadLocalRandom;

public class RandomTimestamp {
    /***
     * Auxiliary function for retrieve a random Timestamp
     * @return a random instant in time
     */
    public static Instant timestamp() {
        return Instant.ofEpochSecond(ThreadLocalRandom.current().nextInt());
    }
}

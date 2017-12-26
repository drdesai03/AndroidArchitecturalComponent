package com.inventyfy.architecture.helper;

import android.os.SystemClock;
import android.support.v4.util.ArrayMap;

import java.util.concurrent.TimeUnit;

public class RateLimiter<K> {
    private ArrayMap<K, Long> timestamps = new ArrayMap<>();
    private final long timeout;

    public RateLimiter(int timeout, TimeUnit timeUnit) {
        this.timeout = timeUnit.toMillis(timeout);
    }

    public synchronized boolean shouldFetch(K key) {
        Long lastFetched = timestamps.get(key);
        long now = now();
        if (lastFetched == null) {
            timestamps.put(key, now);
            return true;
        }
        if (now - lastFetched > timeout) {
            timestamps.put(key, now);
            return true;
        }
        return false;
    }

    private long now() {
        return SystemClock.uptimeMillis();
    }

    public synchronized void reset(K key) {
        timestamps.remove(key);
    }
}

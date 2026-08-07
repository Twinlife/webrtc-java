package net.sqlcipher;

public class DefaultCursorWindowAllocation implements CursorWindowAllocation {

    private final long initialAllocationSize = 1024 * 1024;

    public long getInitialAllocationSize() {
        return initialAllocationSize;
    }

    public long getGrowthPaddingSize() {
        return initialAllocationSize;
    }

    public long getMaxAllocationSize() {
        long windowAllocationUnbounded = 0;
        return windowAllocationUnbounded;
    }
}

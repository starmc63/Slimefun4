package io.github.bakedlibs.dough.scheduling;

import java.util.function.IntConsumer;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.apache.commons.lang.Validate;
import org.bukkit.Location;

// TODO: Convert to Java 16 record
class TaskNode {

    private final IntConsumer runnable;
    private final boolean asynchronous;

    private final Location location;
    private int delay = 0;
    private TaskNode nextNode;

    protected TaskNode(@Nonnull IntConsumer consumer, boolean async, Location location) {
        this.runnable = consumer;
        this.asynchronous = async;
        this.location = location;
    }

    protected TaskNode(@Nonnull IntConsumer consumer, int delay, boolean async, Location location) {
        this.runnable = consumer;
        this.delay = delay;
        this.asynchronous = async;
        this.location = location;
    }
    public Location getLocation(){
        return location;
    }

    protected boolean hasNextNode() {
        return nextNode != null;
    }

    public @Nullable TaskNode getNextNode() {
        return nextNode;
    }

    public void setNextNode(@Nullable TaskNode node) {
        this.nextNode = node;
    }

    public void execute(int index) {
        runnable.accept(index);
    }

    public boolean isAsynchronous() {
        return asynchronous;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        Validate.isTrue(delay >= 0, "The delay cannot be negative.");

        this.delay = delay;
    }

}

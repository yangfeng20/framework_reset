package com.maple.note.stream;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * 我流
 *
 * @author maple
 * @date 2023/04/23
 */
public class MyStream<T> {

    private final Stream<T> stream;

    private boolean enable = false;

    public MyStream(Stream<T> stream) {
        this.stream = stream;
    }


    public MyStream<T> enable(Supplier<Boolean> enableAction) {
        enable = Objects.equals(Boolean.TRUE, enableAction.get());
        return this;
    }


    public void forEach(Consumer<? super T> action) {
        if (!enable) {
            return;
        }
        this.stream.forEach(action);
    }
}

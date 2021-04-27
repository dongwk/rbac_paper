package com.app.core.thread;

public abstract class MultiThreadTask<T> {
    public T ready(int index) {
        return null;
    }
    public abstract void exec(int index, T readyRet);
}

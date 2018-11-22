package com.weboniselab.android;

import com.weboniselab.android.utils.rx.SchedulerProvider;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.TestScheduler;

/**
 * Created by rohit.anvekar on 25/4/18.
 */
public class TestSchedulerProvider implements SchedulerProvider {

    private final TestScheduler mTestScheduler;

    public TestSchedulerProvider(TestScheduler testScheduler) {
        this.mTestScheduler = testScheduler;
    }

    @Override
    public Scheduler computation() {
        return mTestScheduler;
    }

    @Override
    public Scheduler io() {
        return mTestScheduler;
    }

    @Override
    public Scheduler ui() {
        return mTestScheduler;
    }
}
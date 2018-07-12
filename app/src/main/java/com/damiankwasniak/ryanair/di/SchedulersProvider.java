package com.damiankwasniak.ryanair.di;

import rx.Scheduler;

/**
 * Created by damiankwasniak on 09.04.2018.
 */
public interface SchedulersProvider {

    Scheduler getIOScheduler();

    Scheduler getMainThreadScheduler();

}
package ru.a_party.ch3ch2l.model.utils

import io.reactivex.Scheduler

interface ISchedulerProvider {
        fun ui(): Scheduler
        fun io(): Scheduler
}
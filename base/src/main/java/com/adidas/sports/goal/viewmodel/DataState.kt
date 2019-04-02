package com.adidas.sports.goal.viewmodel

import androidx.annotation.NonNull
import androidx.annotation.Nullable

class DataState<T> {

    @NonNull
    @get:NonNull
    var status: DataStatus? = null
        private set

    @Nullable
    @get:Nullable
    var data: T? = null
        private set

    @Nullable
    @get:Nullable
    var error: Throwable? = null
        private set

    init {
        this.status = DataStatus.CREATED
        this.data = null
        this.error = null
    }

    fun loading(): DataState<T> {
        this.status = DataStatus.LOADING
        this.data = null
        this.error = null
        return this
    }

    fun success(@NonNull data: T): DataState<T> {
        this.status = DataStatus.SUCCESS
        this.data = data
        this.error = null
        return this
    }

    fun error(@NonNull error: Throwable): DataState<T> {
        this.status = DataStatus.ERROR
        this.data = null
        this.error = error
        return this
    }

    fun complete(): DataState<T> {
        this.status = DataStatus.COMPLETE
        return this
    }
}
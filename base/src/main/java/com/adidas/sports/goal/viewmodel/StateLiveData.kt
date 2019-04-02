package com.adidas.sports.goal.viewmodel

import androidx.lifecycle.MutableLiveData

open class StateLiveData<T> : MutableLiveData<DataState<T>>() {

    /**
     * Use this to put the Data on a LOADING Status
     */
    fun postLoading() {
        postValue(DataState<T>().loading())
    }

    /**
     * Use this to put the Data on a ERROR DataStatus
     * @param throwable the error to be handled
     */
    fun postError(throwable: Throwable) {
        postValue(DataState<T>().error(throwable))
    }

    /**
     * Use this to put the Data on a SUCCESS DataStatus
     * @param data
     */
    fun postSuccess(data: T) {
        postValue(DataState<T>().success(data))
    }

    /**
     * Use this to put the Data on a COMPLETE DataStatus
     */
    fun postComplete() {
        postValue(DataState<T>().complete())
    }

}
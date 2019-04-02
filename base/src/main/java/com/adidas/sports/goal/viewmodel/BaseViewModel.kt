package com.adidas.sports.goal.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.adidas.sports.goal.exception.AccessThrowable
import com.adidas.sports.goal.exception.ERROR
import kotlinx.coroutines.*

open class BaseViewModel : ViewModel() {

    protected val _spinner: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }
    val spinner: LiveData<Boolean>
        get() = _spinner

    protected val _errorToast: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val errorToast: LiveData<String>
        get() = _errorToast

    protected val _successToast: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val successToast: LiveData<String>
        get() = _successToast

    private val _networkError: MutableLiveData<AccessThrowable> by lazy { MutableLiveData<AccessThrowable>() }
    val networkError: LiveData<AccessThrowable>
        get() = _networkError

    private val viewModelJob = SupervisorJob()

    protected val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    override fun onCleared() {
        uiScope.coroutineContext.cancelChildren()
        super.onCleared()
    }

    fun cancelRequest() {
        viewModelJob.cancelChildren()
    }

    fun <T> load(loader: suspend () -> T): Deferred<T> {

        return uiScope.async(Dispatchers.IO) {
            loader()
        }
    }

    fun <T> Deferred<T>.result(
            success: (T) -> Unit,
            error: (Throwable) -> Unit = { _errorToast.value = it.message }, showSpinner: Boolean = true): Job {
        return uiScope.launch {
            try {
                if (showSpinner) {
                    _spinner.value = true
                }
                success(this@result.await())
            } catch (err: AccessThrowable) {
                when(err.code){
                    ERROR.ERROR_NETWORK -> {
                        _networkError.postValue(err)
                    }
                    else -> error(err)
                }
            } catch (err: Throwable) {
                error(err)
            } finally {
                if (showSpinner) {
                    _spinner.value = false
                }
            }
        }
    }

    infix fun <T> Deferred<T>.result(success: (T) -> Unit): Job {
        return result(success, { _errorToast.value = it.message })
    }

}

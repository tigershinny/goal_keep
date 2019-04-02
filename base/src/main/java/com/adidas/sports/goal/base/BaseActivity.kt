package com.adidas.sports.goal.base

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.adidas.sports.goal.viewmodel.BaseViewModel

abstract class BaseActivity<VM : BaseViewModel> : AppCompatActivity() {

    abstract fun getLayoutId(): Int
    abstract fun onViewCreated(savedInstanceState: Bundle?)
    abstract fun generateViewModel(): VM

    protected lateinit var viewModel: VM

    private var exitTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        actionBar?.hide()
        if (enableFullScreen()){
            window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        setContentView(getLayoutId())

        viewModel = generateViewModel()

        viewModel.errorToast.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })

        onViewCreated(savedInstanceState)
    }

    open fun enableFullScreen(): Boolean {
        return false
    }

    /**
     * tab two times to exit
     */
    protected fun exit(): Boolean {
        if (System.currentTimeMillis() - exitTime > 2000) {
            exitTime = System.currentTimeMillis()
        } else {
            finishAffinity()
        }
        return true
    }
}
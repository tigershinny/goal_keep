package com.adidas.sports.goal.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.adidas.sports.goal.viewmodel.BaseViewModel

abstract class BaseFragment<VM : BaseViewModel>: Fragment() {

    protected abstract fun getLayoutId(): Int

    abstract fun generateViewModel(): VM

    abstract fun onViewCreatedFinish(view: View, savedInstanceState: Bundle?)

    protected lateinit var viewModel: VM

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutId(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = generateViewModel()

        viewModel.errorToast.observe(this, Observer {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        })

        onViewCreatedFinish(view, savedInstanceState)
    }
}

package com.adidas.sports.goal.base

import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import com.adidas.sports.goal.viewmodel.BaseViewModel

abstract class BaseDialogFragment<VM : BaseViewModel>: DialogFragment() {

    protected abstract fun getLayoutId(): Int

    abstract fun generateViewModel(): VM

    abstract fun onViewCreatedFinish(view: View, savedInstanceState: Bundle?)

    protected lateinit var viewModel: VM

    var enableLoading = true
    var dismissCallback: (() -> Unit)? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return inflater.inflate(getLayoutId(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = generateViewModel()
        onViewCreatedFinish(view, savedInstanceState)
    }

    override fun onDismiss(dialog: DialogInterface?) {
        super.onDismiss(dialog)
        dismissCallback?.invoke()
    }
}

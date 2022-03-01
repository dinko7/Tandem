package com.demo.tandem.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.demo.tandem.TandemApplication

abstract class BaseActivity<VB : ViewDataBinding>(@LayoutRes private val layoutRes: Int) : AppCompatActivity() {

    protected lateinit var binding: VB

    private fun getApp() = application as TandemApplication

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutRes)
    }

    protected fun injector() = (applicationContext as TandemApplication).applicationComponent
}
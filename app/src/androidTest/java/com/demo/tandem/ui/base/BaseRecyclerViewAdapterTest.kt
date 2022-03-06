package com.demo.tandem.ui.base

import androidx.recyclerview.widget.RecyclerView
import com.demo.tandem.BaseRecyclerViewAdapterTestingActivity

abstract class BaseRecyclerViewAdapterTest : BaseActivityTest() {
    override val activityClass = BaseRecyclerViewAdapterTestingActivity::class.java

    protected val recyclerView: RecyclerView by lazy { (activityInstance as BaseRecyclerViewAdapterTestingActivity).recyclerView }
}
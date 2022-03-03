package com.demo.tandem.ui.common

import android.graphics.drawable.Drawable
import android.net.Uri
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

object BindingAdapter {

    @JvmStatic
    @BindingAdapter("android:visibleIf")
    fun View.visibleIf(visible: Boolean?) {
        visibility = if (visible == true) View.VISIBLE else View.GONE
    }

    @JvmStatic
    @BindingAdapter("android:imageUrl")
    fun ImageView.loadImageFromUrl(imageUrl: String?) {
        imageUrl?.let {
            Picasso.get().load(Uri.parse(it)).into(this)
        }
    }

    @JvmStatic
    @BindingAdapter("android:verticalDividerDrawable")
    fun RecyclerView.setVerticalDivider(drawable: Drawable) {
        val divider = MiddleDividerItemDecoration(
            this.context,
            DividerItemDecoration.VERTICAL
        )
        drawable.let {
            divider.setDrawable(it)
            addItemDecoration(divider)
        }
    }
}
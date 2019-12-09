package ru.shepetov.navicotest

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

@SuppressLint("ResourceType")
@BindingAdapter(
    value = ["bind:image_uri", "bind:image_placeholder", "bind:image_error"],
    requireAll = false
)
fun adapterImage(
    view: ImageView,
    uri: String? = null,
    placeholder: Drawable? = null,
    errorPlaceholder: Drawable? = null
) {
    if (uri.isNullOrBlank()) return

    Glide
        .with(view.context)
        .load(uri)
        .placeholder(placeholder)
        .error(errorPlaceholder)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(view)
}
package go.easy.skel.view.binding

import android.graphics.drawable.BitmapDrawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.squareup.picasso.RequestCreator
import go.easy.skel.common.OnCallback
import go.easy.skel.view.extension.getRoundedBitmapDrawable

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for easy_go_skel on 05.05.20 16:46.
 */
object ImageViewBinding {

  @JvmStatic
  @BindingAdapter(value = ["isRoundedBitmap"])
  fun bindIsRoundedBitmap(view: ImageView, value: Boolean) {
    if (value) {
      (view.drawable as? BitmapDrawable)?.let {
        val roundedBitmap = it.bitmap.getRoundedBitmapDrawable(view.context.resources)

        view.setImageDrawable(roundedBitmap)
      }
    }
  }

  private fun RequestCreator.scaleTypeOf(scaleType: ImageView.ScaleType): RequestCreator {
    return when (scaleType) {
      ImageView.ScaleType.CENTER_CROP -> centerCrop()
      ImageView.ScaleType.CENTER_INSIDE -> centerInside()
      ImageView.ScaleType.FIT_CENTER -> fit()
      else -> this
    }
  }

  @JvmStatic
  @BindingAdapter(value = ["picassoImageUrl", "picassoOnSuccess", "picassoOnFailed"], requireAll = false)
  fun bindPicassoImageUrl(imageView: ImageView, url: String?, onSuccess: OnCallback? = null, onFailed: OnCallback? = null) {
    Picasso.get().run {
      cancelRequest(imageView)

      load(url)
        .scaleTypeOf(imageView.scaleType)
        .into(imageView, object : Callback {
          override fun onSuccess() { onSuccess?.invoke() }
          override fun onError(e: Exception?) { onFailed?.invoke() }
        })
    }
  }
}

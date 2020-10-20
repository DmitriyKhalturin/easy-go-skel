package easy.go.skel.view.binding

import android.graphics.drawable.BitmapDrawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import easy.go.skel.view.extension.getRoundedBitmapDrawable

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for easy_go_skel on 05.05.20 16:46.
 */
object ImageBinding {

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
}

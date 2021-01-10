package go.easy.skel.view.binding

import android.view.View
import androidx.databinding.BindingAdapter

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for easy_go_skel on 05.05.20 16:43.
 */
object ViewBinding {

  @JvmStatic
  @BindingAdapter(value =["visibleOrInvisible"])
  fun bindVisibleOrInvisible(view: View, isVisible: Boolean?) {
    if (isVisible == true) {
      view.visibility = View.VISIBLE
    } else {
      view.visibility = View.INVISIBLE
    }
  }

  @JvmStatic
  @BindingAdapter(value =["visibleOrGone"])
  fun bindVisibleOrGone(view: View, isVisible: Boolean?) {
    if (isVisible == true) {
      view.visibility = View.VISIBLE
    } else {
      view.visibility = View.GONE
    }
  }

  @JvmStatic
  @BindingAdapter(value =["visibleIfNonNull"])
  fun bindVisibleIfNonNull(view: View, `object`: Any?) {
    view.visibility = if (`object` != null) {
      if (`object` is List<*>) {
        if (`object`.size == 0) {
          View.GONE
        } else {
          View.VISIBLE
        }
      } else {
        View.VISIBLE
      }
    } else {
      View.GONE
    }
  }

  @JvmStatic
  @BindingAdapter(value = ["paddingVertical", "paddingHorizontal"], requireAll = false)
  fun bindCrossPadding(view: View, paddingVertical: Float?, paddingHorizontal: Float?) {
    val top = paddingVertical?.toInt() ?: view.paddingTop
    val bottom = paddingVertical?.toInt() ?: view.paddingBottom
    val left = paddingHorizontal?.toInt() ?: view.paddingStart
    val right = paddingHorizontal?.toInt() ?: view.paddingEnd

    view.setPadding(left, top, right, bottom)
  }
}

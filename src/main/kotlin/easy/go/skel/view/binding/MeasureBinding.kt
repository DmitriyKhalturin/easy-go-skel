package easy.go.skel.view.binding

import android.view.View
import androidx.databinding.BindingAdapter

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for easy_go_skel on 05.05.20 16:45.
 */
object MeasureBinding {

  @JvmStatic
  @BindingAdapter(value = ["verticalPadding", "horizontalPadding"], requireAll = false)
  fun bindPaddings(view: View, paddingVertical: Float?, paddingHorizontal: Float?) {
    val top = paddingVertical?.toInt() ?: view.paddingTop
    val bottom = paddingVertical?.toInt() ?: view.paddingBottom
    val left = paddingHorizontal?.toInt() ?: view.paddingStart
    val right = paddingHorizontal?.toInt() ?: view.paddingEnd

    view.setPadding(left, top, right, bottom)
  }
}

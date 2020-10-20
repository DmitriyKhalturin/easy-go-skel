package easy.go.skel.view.binding

import android.view.View
import androidx.databinding.BindingAdapter
import easy.go.skel.view.extension.clearBackground
import easy.go.skel.view.extension.setActionBarItem
import easy.go.skel.view.extension.setBorderlessSelectableItem
import easy.go.skel.view.extension.setSelectableItem

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for easy_go_skel on 05.05.20 16:44.
 */
object RippleEffectBinding {

  @JvmStatic
  @BindingAdapter(value = ["setRippleEffect"])
  fun bindSetRippleEffect(view: View, value: Boolean?) {
    if (value == true) {
      view.setSelectableItem()
    } else {
      view.clearBackground()
    }
  }

  @JvmStatic
  @BindingAdapter(value = ["setCircularRippleEffect"])
  fun bindSetCircularRippleEffect(view: View, value: Boolean?) {
    if (value == true) {
      view.setActionBarItem()
    } else {
      view.clearBackground()
    }
  }

  @JvmStatic
  @BindingAdapter(value = ["setBorderlessRippleEffect"])
  fun bindSetBorderlessRippleEffect(view: View, value: Boolean?) {
    if (value == true) {
      view.setBorderlessSelectableItem()
    } else {
      view.clearBackground()
    }
  }
}

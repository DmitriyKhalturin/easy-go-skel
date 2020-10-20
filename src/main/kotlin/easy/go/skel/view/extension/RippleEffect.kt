package easy.go.skel.view.extension

import android.util.TypedValue
import android.view.View

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for easy_go_skel on 27.04.20 3:56.
 */

fun View.setSelectableItem() {
  setBackgroundResource(this, android.R.attr.selectableItemBackground)
}

fun View.setActionBarItem() {
  setBackgroundResource(this, android.R.attr.actionBarItemBackground)
}

fun View.setBorderlessSelectableItem() {
  setBackgroundResource(this, android.R.attr.selectableItemBackgroundBorderless)
}

private fun setBackgroundResource(view: View, resId: Int) = with(view) {
  isClickable = true

  val outValue = TypedValue()

  context.theme.resolveAttribute(resId, outValue, true)
  setBackgroundResource(outValue.resourceId)
}

fun View.clearBackground() {
  isClickable = false
  background = null
}

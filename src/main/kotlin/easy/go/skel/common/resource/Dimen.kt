package easy.go.skel.common.resource

import android.content.res.Resources

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for babyJa-android on 30.09.20 23:30.
 */
object Dimen {

  private val density: Float
    get() = Resources.getSystem().displayMetrics.density

  @JvmStatic
  fun dpToPx(dp: Int) = (dp * density).toInt()

  @JvmStatic
  fun pxToDp(px: Int) = (px / density).toInt()
}

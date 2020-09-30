package easy.go.library.common

import android.content.res.Resources

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for babyJa-android on 30.09.20 23:30.
 */
object Dimen {

  private val density: Int
    get() = Resources.getSystem().displayMetrics.density.toInt()

  @JvmStatic
  fun dpToPx(dp: Int) = (dp * density)

  @JvmStatic
  fun pxToDp(px: Int) = (px.toFloat() / density).toInt()
}

package easy.go.library.view.extension

import android.content.res.Resources
import android.graphics.Bitmap
import androidx.core.graphics.drawable.RoundedBitmapDrawable
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for easy_go_library on 05.05.20 16:40.
 */

fun Bitmap.getRoundedBitmapDrawable(resources: Resources): RoundedBitmapDrawable {
  val bitmap = Bitmap.createBitmap(this, 0, 0, 0, 0)
  val bitmapDrawable = RoundedBitmapDrawableFactory.create(resources, bitmap)

  return bitmapDrawable.apply {
    isFilterBitmap = true
    isCircular = true
    setAntiAlias(true)
  }
}

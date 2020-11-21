package easy.go.skel.view.extension

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.view.View

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for easy_go_skel on 27.04.20 4:00.
 */

fun View.visibleAnimate(duration: Long) {
  animate().cancel()

  alpha = 0f
  visibility = View.VISIBLE

  animate()
    .setDuration(duration)
    .alpha(1f)
    .setListener(null)
}

fun View.invisibleAnimate(duration: Long, inGone: Boolean = true) {
  animate().cancel()

  animate()
    .setDuration(duration)
    .alpha(0f)
    .setListener(object : AnimatorListenerAdapter() {
      override fun onAnimationEnd(animation: Animator?) {
        visibility = if (inGone) View.GONE else View.INVISIBLE
        alpha = 1f
      }
    })
}

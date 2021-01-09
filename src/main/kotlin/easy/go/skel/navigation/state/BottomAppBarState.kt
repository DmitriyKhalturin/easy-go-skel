package easy.go.skel.navigation.state

import com.google.android.material.appbar.AppBarLayout.LayoutParams.SCROLL_FLAG_NO_SCROLL
import com.google.android.material.appbar.AppBarLayout.LayoutParams.ScrollFlags

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for HelpMe on 08.01.21 1:57.
 */
data class BottomAppBarState(
  val navigationMenuState: NavigationMenuState? = null,
  @ScrollFlags val scrollFlags: Int = SCROLL_FLAG_NO_SCROLL,
)

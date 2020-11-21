package easy.go.skel.navigation

import androidx.fragment.app.Fragment

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for easy_go_skel on 06.04.20 19:41.
 */
abstract class NavigationWithBottomMenuActivity : NavigationActivity() {

  abstract fun setBottomNavigationVisibility(isVisible: Boolean)
  abstract fun onSetRootFragment(fragmentClass: Class<out Fragment>)
}

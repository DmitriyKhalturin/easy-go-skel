package go.easy.skel.navigation

import androidx.fragment.app.Fragment

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for easy_go_skel on 06.04.20 19:41.
 */
interface BottomNavigationViewComponent {

  fun setBottomNavigationViewVisibility(isVisible: Boolean)
  fun onSetRootFragment(fragmentClass: Class<out Fragment>)
}

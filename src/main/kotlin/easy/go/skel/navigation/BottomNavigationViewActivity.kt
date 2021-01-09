package easy.go.skel.navigation

import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import easy.go.skel.R

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for easy_go_skel on 06.04.20 19:41.
 */
abstract class BottomNavigationViewActivity : NavigationActivity(), BottomNavigationViewComponent {

  companion object {
    val fragmentContainerId = R.id.bottom_navigation_view_fragment_container
    private val bottomNavigationViewId = R.id.bottom_navigation_view
  }

  private val bottomNavigationView: BottomNavigationView by lazy { findViewById(bottomNavigationViewId) }

  abstract override fun setBottomNavigationViewVisibility(isVisible: Boolean)
  abstract override fun onSetRootFragment(fragmentClass: Class<out Fragment>)
}

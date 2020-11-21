package easy.go.skel.navigation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for easy_go_skel on 20.05.20 19:43.
 */
abstract class NavigationWithBottomMenuFragment : Fragment() {

  private val navigationActivity: NavigationWithBottomMenuActivity? by lazy {
    activity as? NavigationWithBottomMenuActivity
  }
  protected abstract val haveBottomNavigation: Boolean

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    navigationActivity?.setBottomNavigationVisibility(haveBottomNavigation)
  }
}

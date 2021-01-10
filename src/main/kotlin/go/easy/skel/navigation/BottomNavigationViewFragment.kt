package go.easy.skel.navigation

import android.os.Bundle
import android.view.View

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for easy_go_skel on 20.05.20 19:43.
 */
abstract class BottomNavigationViewFragment : NavigationFragment() {

  private val component by lazy { activity as BottomNavigationViewComponent }

  protected abstract val haveBottomNavigation: Boolean

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    component.setBottomNavigationViewVisibility(haveBottomNavigation)
  }
}

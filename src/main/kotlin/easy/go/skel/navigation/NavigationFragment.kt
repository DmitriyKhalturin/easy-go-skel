package easy.go.skel.navigation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import javax.inject.Inject

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for easy_go_skel on 20.05.20 19:43.
 */
open class NavigationFragment : Fragment() {

  @Inject
  lateinit var navigation: Navigation

  private val navigationActivity: NavigationActivity? by lazy { activity as? NavigationActivity }
  protected open val haveBottomNavigation: Boolean = true

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    navigationActivity?.setBottomNavigationVisibility(haveBottomNavigation)
  }
}

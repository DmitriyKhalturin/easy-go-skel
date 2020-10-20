package easy.go.skel.navigation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.core.parameter.DefinitionParameters
import org.koin.core.parameter.parametersOf

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for easy_go_skel on 20.05.20 19:43.
 */
open class NavigationFragment : Fragment() {

  protected val navigationViewModel by sharedViewModel<NavigationViewModel>()
  protected val navigationParameters: DefinitionParameters by lazy { parametersOf(navigationViewModel) }

  private val navigationActivity: NavigationActivity? by lazy { activity as? NavigationActivity }
  protected open val haveBottomNavigation: Boolean = true

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    navigationActivity?.setBottomNavigationVisibility(haveBottomNavigation)
  }
}

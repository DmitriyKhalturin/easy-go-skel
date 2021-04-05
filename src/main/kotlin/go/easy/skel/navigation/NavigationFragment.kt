package go.easy.skel.navigation

import androidx.databinding.ViewDataBinding
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

  protected var binding: ViewDataBinding? = null

  override fun onDestroyView() {
    super.onDestroyView()

    binding = null
  }
}

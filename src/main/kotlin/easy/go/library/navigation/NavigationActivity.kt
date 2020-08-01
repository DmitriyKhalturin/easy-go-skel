package easy.go.library.navigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for easy_go_library on 06.04.20 19:41.
 */
abstract class NavigationActivity : AppCompatActivity() {

  protected val navigationViewModel by viewModel<NavigationViewModel>()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    navigationViewModel.setViewContext(this)
  }

  override fun onDestroy() {
    navigationViewModel.clearViewContext()

    super.onDestroy()
  }

  abstract fun setBottomNavigationVisibility(isVisible: Boolean)
  abstract fun setBottomNavigationRootFragment(fragmentClass: Class<out Fragment>)
}

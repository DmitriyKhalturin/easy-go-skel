package easy.go.skel.navigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import javax.inject.Inject

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for easy_go_skel on 06.04.20 19:41.
 */
abstract class NavigationActivity : AppCompatActivity() {

  @Inject
  lateinit var navigationEntryPoint: NavigationEntryPoint

  @Inject
  lateinit var navigation: Navigation

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    navigationEntryPoint.setViewContext(this)
  }

  override fun onDestroy() {
    navigationEntryPoint.clearViewContext()

    super.onDestroy()
  }

  abstract fun setBottomNavigationVisibility(isVisible: Boolean)
  abstract fun setBottomNavigationRootFragment(fragmentClass: Class<out Fragment>)
}

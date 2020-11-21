package easy.go.skel.navigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for easy_go_skel on 06.04.20 19:41.
 */
open class NavigationActivity : AppCompatActivity() {

  protected val navigationViewModel by viewModel<NavigationViewModel>()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    navigationViewModel.setViewContext(this)
  }

  override fun onDestroy() {
    navigationViewModel.clearViewContext()

    super.onDestroy()
  }
}

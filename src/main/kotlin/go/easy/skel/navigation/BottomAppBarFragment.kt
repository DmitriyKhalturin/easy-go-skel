package go.easy.skel.navigation

import android.os.Bundle
import android.view.View
import go.easy.skel.navigation.state.BottomAppBarState
import go.easy.skel.navigation.state.FabState

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for HelpMe on 07.01.21 21:02.
 */
abstract class BottomAppBarFragment : NavigationFragment() {

  private val component by lazy { activity as BottomAppBarComponent }

  abstract val fabState: FabState?
  abstract val bottomAppBarState: BottomAppBarState?

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    component.fabState = fabState
    component.bottomAppBarState = bottomAppBarState
  }
}

package go.easy.skel.navigation

import go.easy.skel.navigation.state.BottomAppBarState
import go.easy.skel.navigation.state.FabState

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for HelpMe on 07.01.21 23:55.
 *
 * The division into fab and BottomAppBar is due to the fact that the fab can be rendered without the BottomAppBar.
 * BottomAppBar, in turn, may contain NavigationView and Toolbar menus.
 */
interface BottomAppBarComponent {

  var fabState: FabState?
  var bottomAppBarState: BottomAppBarState?
}

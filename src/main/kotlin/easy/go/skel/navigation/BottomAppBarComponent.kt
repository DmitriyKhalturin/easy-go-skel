package easy.go.skel.navigation

import easy.go.skel.navigation.state.BottomAppBarState
import easy.go.skel.navigation.state.FabState

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for HelpMe on 07.01.21 23:55.
 */
interface BottomAppBarComponent {

  var fabState: FabState?
  var bottomAppBarState: BottomAppBarState?
}

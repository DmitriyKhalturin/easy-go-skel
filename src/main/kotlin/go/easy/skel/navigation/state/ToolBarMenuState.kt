package go.easy.skel.navigation.state

import androidx.annotation.MenuRes
import go.easy.skel.navigation.OnToolBarMenuItemSelected

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for HelpMe on 16.01.21 23:43.
 */
data class ToolBarMenuState(
  @MenuRes val toolBarMenuResId: Int,
  val onToolBarMenuItemSelected: OnToolBarMenuItemSelected,
)

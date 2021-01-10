package go.easy.skel.navigation.state

import androidx.annotation.DrawableRes
import androidx.annotation.MenuRes
import go.easy.skel.navigation.OnNavigationMenuItemSelected

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for HelpMe on 08.01.21 2:38.
 */
data class NavigationMenuState(
  @DrawableRes val navigationDrawableResId: Int,
  @MenuRes val navigationMenuResId: Int,
  val isCheckedNavigationMenuItemId: Int,
  val onNavigationMenuItemSelected: OnNavigationMenuItemSelected,
)

package go.easy.skel.navigation.state

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.google.android.material.bottomappbar.BottomAppBar.FAB_ALIGNMENT_MODE_CENTER
import com.google.android.material.bottomappbar.BottomAppBar.FabAlignmentMode

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for HelpMe on 08.01.21 0:58.
 */
data class FabState(
  @DrawableRes val drawableResId: Int,
  @StringRes val contentDescriptionResId: Int,
  @FabAlignmentMode val fabAlignmentMode: Int = FAB_ALIGNMENT_MODE_CENTER,
  val onClick: () -> Unit,
)

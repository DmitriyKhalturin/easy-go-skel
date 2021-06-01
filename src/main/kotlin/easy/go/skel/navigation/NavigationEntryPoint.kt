package easy.go.skel.navigation

import androidx.fragment.app.FragmentActivity

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for BabyJa on 01.06.2021 22:54.
 */
interface NavigationEntryPoint {

  fun setViewContext(activity: FragmentActivity)
  fun clearViewContext()

  fun setContainerId(containerId: Int)
}

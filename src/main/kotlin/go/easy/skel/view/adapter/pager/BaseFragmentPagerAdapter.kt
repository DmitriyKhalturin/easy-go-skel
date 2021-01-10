package go.easy.skel.view.adapter.pager

import android.content.Context
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for easy_go_skel on 27.04.20 4:49.
 */
abstract class BaseFragmentPagerAdapter(
  private val fragmentManager: FragmentManager,
  private val context: Context
) : FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

  protected abstract val fragmentNames: List<String>
  protected abstract val pageTitles: List<Int>

  override fun getItem(position: Int) =
    fragmentManager
      .fragmentFactory
      .instantiate(context.classLoader, fragmentNames[position])

  override fun getPageTitle(position: Int): CharSequence? =
    context.getString(pageTitles[position])

  override fun getCount() = fragmentNames.size
}

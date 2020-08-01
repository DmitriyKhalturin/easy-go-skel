package easy.go.library.view.binding

import androidx.databinding.BindingAdapter
import androidx.viewpager.widget.ViewPager

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for easy_go_library on 05.05.20 16:24.
 */
object ViewPagerBinding {
  @JvmStatic
  @BindingAdapter(value = ["onAdapterChangeListener", "onPageChangeListener"], requireAll = false)
  fun bindViewPagerListener(viewPager: ViewPager, onAdapterChangeListener: ViewPager.OnAdapterChangeListener?, onPageChangeListener: ViewPager.SimpleOnPageChangeListener?) {
    onAdapterChangeListener?.let { viewPager.addOnAdapterChangeListener(it) }
    onPageChangeListener?.let { viewPager.addOnPageChangeListener(it) }
  }
}

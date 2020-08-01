package easy.go.library.view.adapter.list

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for easy_go_library on 20.05.20 23:58.
 */
abstract class CardViewPagedListAdapter<T, VH: RecyclerView.ViewHolder>(diffUtilCallback: DiffUtil.ItemCallback<T>) :
  PagedListAdapter<T, VH>(diffUtilCallback)
{

  abstract val lastItemBottomMarginDimenId: Int
  private var lastItemBottomMargin: Int? = null
  private var bottomMargin: Int? = null

  private var spanCount: Int = 1

  fun setSpanCount(value: Int) {
    spanCount = value
  }

  override fun onBindViewHolder(holder: VH, position: Int) {
    try {
      val layoutParams = (holder.itemView.layoutParams as ViewGroup.MarginLayoutParams)

      if (bottomMargin == null) {
        bottomMargin = layoutParams.bottomMargin
      }

      layoutParams.bottomMargin = if (position < (itemCount - spanCount)) {
        bottomMargin!!
      } else {
        if (lastItemBottomMargin == null) {
          lastItemBottomMargin =
            holder.itemView.context.resources.getDimensionPixelSize(lastItemBottomMarginDimenId)
        }

        (bottomMargin!! + lastItemBottomMargin!!)
      }
    } catch (exception: Exception) {
    }
  }
}

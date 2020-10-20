package easy.go.skel.view.layout

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.ListAdapter
import easy.go.skel.exception.ImpossibleException

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for easy_go_skel on 09.05.20 1:37.
 */
class ExpandedListView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0, defStyleRes: Int = 0) : LinearLayout(context, attrs, defStyleAttr, defStyleRes) {

  init {
    orientation = VERTICAL
  }

  var adapter: ListAdapter
    get() = throw ImpossibleException()
    set(value) {
      for (index in 0 until value.count) {
        addView(value.getView(index, null, this))
      }
    }
}

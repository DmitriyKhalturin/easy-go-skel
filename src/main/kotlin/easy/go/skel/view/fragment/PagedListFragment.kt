package easy.go.skel.view.fragment

import androidx.lifecycle.Observer
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import easy.go.skel.navigation.NavigationFragment
import easy.go.skel.viewmodel.PagedListViewModel

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for easy_go_skel on 29.04.20 7:54.
 */
abstract class PagedListFragment<T, VH: RecyclerView.ViewHolder> : NavigationFragment() {

  protected abstract val pagedListViewModel: PagedListViewModel<T>
  protected abstract val pagedListAdapter: PagedListAdapter<T, VH>

  protected lateinit var listView: RecyclerView

  protected fun listInit(gridSpanCount: Int = 0) {
    if(gridSpanCount > 0) {
      val layoutManager = getGridLayoutManager(gridSpanCount)
      setLayoutManager(layoutManager)
    } else {
      val layoutManager = getLinearLayoutManager()
      setLayoutManager(layoutManager)
    }

    listFetch()
  }

  private fun setLayoutManager(layoutManager: RecyclerView.LayoutManager) {
    listView.run {
      this.layoutManager = layoutManager
      adapter = pagedListAdapter
    }
  }

  private fun listFetch(){
    pagedListViewModel.livePagedList.observe(viewLifecycleOwner, Observer {
      pagedListAdapter.submitList(it)
    })
    pagedListViewModel.fetch()
  }

  private fun getLinearLayoutManager() =
    LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

  private fun getGridLayoutManager(gridSpanCount: Int) =
    GridLayoutManager(context, gridSpanCount, GridLayoutManager.VERTICAL, false)
}

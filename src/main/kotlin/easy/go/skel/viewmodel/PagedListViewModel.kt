package easy.go.skel.viewmodel

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import easy.go.skel.executor.JobExecutor

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for easy_go_skel on 09.04.20 2:25.
 */
abstract class PagedListViewModel<T>(jobExecutor: JobExecutor) : BaseViewModel() {

  companion object {
    private const val ENABLE_PLACEHOLDERS = false
    private const val PAGE_SIZE = 10
    private const val INITIAL_LOAD_KEY = 0
  }

  private val pagedListConfig = PagedList.Config.Builder()
    .setEnablePlaceholders(ENABLE_PLACEHOLDERS)
    .setPageSize(PAGE_SIZE)
    .build()

  abstract val dataSourceFactory: DataSource.Factory<Int, T>

  val livePagedList: LiveData<PagedList<T>> by lazy {
    LivePagedListBuilder(dataSourceFactory, pagedListConfig)
      .setInitialLoadKey(INITIAL_LOAD_KEY)
      .setFetchExecutor(jobExecutor)
      .build()
  }

  abstract fun fetch()
}

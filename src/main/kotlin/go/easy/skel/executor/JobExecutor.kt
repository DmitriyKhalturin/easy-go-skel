package go.easy.skel.executor

import java.util.concurrent.*

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for easy_go_skel on 03.04.20 0:55.
 */
class JobExecutor(
  threadNamePrefix: String = THREAD_NAME_PREFIX,
  corePoolSize: Int = CORE_POOL_SIZE,
  maxPoolSize: Int = MAX_POOL_SIZE,
  keepAliveTimeValue: Long = KEEP_ALIVE_TIME_VALUE,
  keepAliveTimeUnit: TimeUnit = KEEP_ALIVE_TIME_UNIT,
  killCoreThread: Boolean = KILL_CORE_THREAD
) : ThreadPoolExecutor(
  corePoolSize,
  maxPoolSize,
  keepAliveTimeValue,
  keepAliveTimeUnit,
  LinkedBlockingDeque(),
  JobThreadExecutor(threadNamePrefix)
) {

  companion object {
    private const val THREAD_NAME_PREFIX = "job_executor_"
    private const val CORE_POOL_SIZE = 5
    private const val MAX_POOL_SIZE = 10
    private const val KEEP_ALIVE_TIME_VALUE = 60L
    private val KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS
    private const val KILL_CORE_THREAD = true
  }

  init {
    if (keepAliveTimeValue > 0 && killCoreThread) {
      allowCoreThreadTimeOut(true)
    }
  }

  private class JobThreadExecutor(private val threadNamePrefix: String) : ThreadFactory {

    private var counter = 0

    private val threadName: String
      get() = "$threadNamePrefix${counter++}"

    override fun newThread(runnable: Runnable) = Thread(runnable, threadName)
  }

  fun sync(block: CountDownLatch.() -> Unit) {
    CountDownLatch(1).run {
      execute {
        block(this)
        countDown()
      }
      await()
    }
  }
}

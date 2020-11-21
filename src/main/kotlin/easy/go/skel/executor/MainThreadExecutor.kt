package easy.go.skel.executor

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for easy_go_skel on 03.04.20 0:55.
 */
class MainThreadExecutor : Executor {

  private val handler = Handler(Looper.getMainLooper())

  override fun execute(command: Runnable) {
    handler.post(command)
  }
}

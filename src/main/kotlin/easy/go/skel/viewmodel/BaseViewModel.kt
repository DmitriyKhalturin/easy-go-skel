package easy.go.skel.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for easy_go_skel on 03.04.20 0:55.
 */
abstract class BaseViewModel : ViewModel() {

  private val supervisor = SupervisorJob()

  private val exceptionHandler = CoroutineExceptionHandler { _, e ->
    onExceptionHandler(e)
  }

  abstract fun onExceptionHandler(e: Throwable)

  protected val coroutineContext = (supervisor + exceptionHandler)

  protected inline fun launch(crossinline block: suspend CoroutineScope.() -> Unit) =
    viewModelScope.launch(coroutineContext) {
      block()
    }

  protected inline fun <T> async(crossinline block: suspend CoroutineScope.() -> T): Deferred<T> =
    viewModelScope.async(coroutineContext) {
      block()
    }

  // TODO: implement later: produce, actor, broadcast
}

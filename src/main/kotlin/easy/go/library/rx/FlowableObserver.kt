package easy.go.library.rx

import com.google.firebase.crashlytics.FirebaseCrashlytics
import io.reactivex.functions.Consumer
import io.reactivex.subscribers.DisposableSubscriber

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for easy_go_library on 31.05.20 4:12.
 */
class FlowableObserver<T>(private val consumer: Consumer<T>) : DisposableSubscriber<T>() {

  override fun onNext(result: T) {
    if (!isDisposed) {
      try {
        consumer.accept(result)
      } catch (exception: Exception) {
        onError(exception)
      }
    }
  }

  override fun onComplete() {
    dispose()
  }

  override fun onError(exception: Throwable) {
    FirebaseCrashlytics.getInstance().recordException(exception)
    dispose()
  }
}

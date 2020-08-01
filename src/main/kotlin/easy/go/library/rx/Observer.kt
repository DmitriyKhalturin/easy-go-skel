package easy.go.library.rx

import com.google.firebase.crashlytics.FirebaseCrashlytics
import io.reactivex.functions.Consumer
import io.reactivex.observers.DisposableObserver

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for easy_go_library on 03.04.20 0:55.
 */
class Observer<T>(private val consumer: Consumer<T>) : DisposableObserver<T>() {

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

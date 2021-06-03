package easy.go.skel.rx

import com.google.firebase.crashlytics.FirebaseCrashlytics
import io.reactivex.functions.Consumer
import io.reactivex.observers.DisposableMaybeObserver

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for BabyJa on 03.06.2021 15:03.
 */
class MaybeObserver<T>(private val consumer: Consumer<T>) : DisposableMaybeObserver<T>() {

  override fun onSuccess(result: T) {
    if (!isDisposed) {
      try {
          consumer.accept(result)
      } catch (exception: Exception) {
        onError(exception)
      }
    }

    dispose()
  }

  override fun onError(exception: Throwable) {
    FirebaseCrashlytics.getInstance().recordException(exception)
    dispose()
  }

  override fun onComplete() {
    dispose()
  }
}

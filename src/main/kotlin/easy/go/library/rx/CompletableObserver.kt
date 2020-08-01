package easy.go.library.rx

import com.google.firebase.crashlytics.FirebaseCrashlytics
import io.reactivex.observers.DisposableCompletableObserver

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for easy_go_library on 03.04.20 0:55.
 */
class CompletableObserver : DisposableCompletableObserver() {

  override fun onComplete() {
    dispose()
  }

  override fun onError(exception: Throwable) {
    FirebaseCrashlytics.getInstance().recordException(exception)
    dispose()
  }
}

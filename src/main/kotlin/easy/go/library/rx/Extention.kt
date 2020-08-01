package easy.go.library.rx

import com.google.firebase.crashlytics.FirebaseCrashlytics
import io.reactivex.CompletableEmitter
import io.reactivex.ObservableEmitter
import io.reactivex.SingleEmitter

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for easy_go_library on 23.04.20 2:01.
 */

fun <T> ObservableEmitter<T>.use(block: () -> Unit) =
  try {
    block()
  } catch (exception: Exception) {
    FirebaseCrashlytics.getInstance().recordException(exception)
    onError(exception)
  }

fun <T> SingleEmitter<T>.use(block: () -> Unit) =
  try {
    block()
  } catch (exception: Exception) {
    FirebaseCrashlytics.getInstance().recordException(exception)
    onError(exception)
  }

fun CompletableEmitter.use(block: () -> Unit) =
  try {
    block()
  } catch (exception: Exception) {
    FirebaseCrashlytics.getInstance().recordException(exception)
    onError(exception)
  }

package go.easy.skel.exception

import com.google.firebase.crashlytics.FirebaseCrashlytics

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for babyJa-android on 22.09.20 23:49.
 */
fun TODO(exception: Throwable): Unit = try {
  TODO("message: ${exception.message}. stack-trace: ${exception.stackTraceToString()}")
} catch (e: Throwable) {
  FirebaseCrashlytics.getInstance().recordException(e)
}

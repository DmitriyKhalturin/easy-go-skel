package easy.go.skel.common.databinding

import androidx.databinding.ObservableField

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for babyJa-android on 01.10.20 0:08.
 */
fun <T> ObservableField<T>.updateAndGet(updateFunction: (value: T?) -> T?): T? {
  val result = updateFunction(get())
  set(result)
  return result
}

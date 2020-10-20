package easy.go.skel.common.livedata

import androidx.lifecycle.MutableLiveData

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for babyJa-android on 01.10.20 0:08.
 */
fun <T> MutableLiveData<T>.updateAndGet(updateFunction: (value: T?) -> T?): T? {
  val result = updateFunction(value)
  postValue(result)
  return result
}

fun <T> MutableLiveData<T>.compareAndSet(expect: T?, update: T?): Boolean {
  val result = (value == expect)
  if (!result) {
    value = update
  }
  return result
}

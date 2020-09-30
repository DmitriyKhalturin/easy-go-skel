package easy.go.library.livedata

import androidx.lifecycle.MutableLiveData

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for babyJa-android on 01.10.20 0:08.
 */
fun <T> MutableLiveData<T>.update(block: (value: T) -> T) = postValue(value?.let(block))

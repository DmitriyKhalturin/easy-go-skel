package easy.go.library.databinding

import androidx.databinding.ObservableField

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for babyJa-android on 01.10.20 0:08.
 */
fun <T> ObservableField<T>.update(block: (value: T) -> T) = set(get()?.let(block))

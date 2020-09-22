package easy.go.library.annotation

import androidx.annotation.LayoutRes

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for babyJa-android on 22.09.20 23:52.
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(value = AnnotationRetention.SOURCE)
annotation class BindingWith(@LayoutRes vararg val ids: Int)

package easy.go.library.common

import java.util.*

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for babyJa-android on 24.09.20 18:55.
 */
operator fun Date.minus(other: Date): Date = Date(this.time - other.time)
operator fun Date.plus(other: Date): Date = Date(this.time + other.time)

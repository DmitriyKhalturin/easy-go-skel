package easy.go.library.extension

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for babyJa-android on 10.10.20 15:07.
 */

/**
 * Get sub list from list without IndexOutOfBoundsException
 */
fun <T> List<T>.sublist(offset: Int, limit: Int): List<T> {
  return when {
    (offset >= size) -> emptyList()
    else -> {
      val toIndex = if (offset + limit < size) {
        (offset + limit)
      } else {
        size
      }

      subList(offset, toIndex)
    }
  }
}

inline fun <reified T: Any, R: Any> letElements(vararg elements: T?, closure: (Array<T>) -> R?): R? {
  return if (elements.all { it != null }) {
    closure(elements.filterNotNull().toTypedArray())
  } else null
}

inline fun <T> collect(count: Int, action: (index: Int) -> T): List<T> {
  return List(count) { action(it) }
}

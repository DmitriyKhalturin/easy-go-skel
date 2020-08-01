package easy.go.library.koin

import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.module.Module

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for easy_go_library on 03.04.20 13:22.
 */
object KoinUtil {
  @JvmStatic
  fun start(context: Context, modules: List<Module>, debug: Boolean = false) {
    startKoin {
      printLogger(level = if (debug) {
        Level.DEBUG
      } else {
        Level.ERROR
      })

      androidContext(context)

      modules(modules)
    }
  }
}

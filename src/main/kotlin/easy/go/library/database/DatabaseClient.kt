package easy.go.library.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import java.lang.reflect.ParameterizedType

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for easy_go_library on 03.04.20 0:55.
 */
abstract class DatabaseClient<T: RoomDatabase> constructor(private val context: Context) {

  // TODO: check class casting
  private val typeOfT: Class<T> =
    ((javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<T>)

  protected abstract val databaseName: String

  val instance: T by lazy {
    Room.databaseBuilder(context, typeOfT, databaseName)
      .fallbackToDestructiveMigration()
      .build()
  }
}

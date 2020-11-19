package easy.go.skel.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import java.lang.reflect.ParameterizedType

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for easy_go_skel on 03.04.20 0:55.
 */
abstract class DatabaseClient<T: RoomDatabase> constructor(private val context: Context) {

  @Suppress("UNCHECKED_CAST")
  private val typeOfT: Class<T> =
    ((javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<T>)

  protected abstract val databaseName: String

  val instance: T by lazy {
    Room.databaseBuilder(context, typeOfT, databaseName)
      .fallbackToDestructiveMigration()
      .build()
  }
}

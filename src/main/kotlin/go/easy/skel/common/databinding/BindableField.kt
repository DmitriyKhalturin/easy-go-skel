package go.easy.skel.common.databinding

import androidx.databinding.BaseObservable
import kotlin.reflect.KProperty

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for HelpMe on 01.02.21 22:29.
 */
class BindableField<T>(private var field: T, private val bindableId: Int) {

  operator fun getValue(thisRef: BaseObservable, property: KProperty<*>): T = field

  operator fun setValue(thisRef: BaseObservable, property: KProperty<*>, value: T) {
    if (field != value) {
      field = value

      thisRef.notifyPropertyChanged(bindableId)
    }
  }
}

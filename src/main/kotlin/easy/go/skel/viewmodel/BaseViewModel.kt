package easy.go.skel.viewmodel

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for easy_go_skel on 03.04.20 0:55.
 */
open class BaseViewModel : ViewModel() {

  private val disposables = CompositeDisposable()

  fun addDisposable(disposable: Disposable) = disposables.add(disposable)

  fun addDisposable(vararg disposable: Disposable) = disposables.addAll(*disposable)

  override fun onCleared() {
    disposables.clear()

    super.onCleared()
  }
}

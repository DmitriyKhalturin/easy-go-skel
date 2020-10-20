package easy.go.skel.repository

import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Executor

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for easy_go_skel on 03.04.20 0:55.
 */
abstract class BaseRepository(
  private val jobExecutor: Executor
) {
  protected fun <T> buildObservable(source: (ObservableEmitter<T>) -> Unit) =
    Observable.create(source)
      .subscribeOn(Schedulers.from(jobExecutor))
      .observeOn(AndroidSchedulers.mainThread())

  protected fun <T> buildSingle(source: (emitter: SingleEmitter<T>) -> Unit) =
    Single.create(source)
      .subscribeOn(Schedulers.from(jobExecutor))
      .observeOn(AndroidSchedulers.mainThread())

  protected fun buildCompletable(source: (emitter: CompletableEmitter) -> Unit) =
    Completable.create(source)
      .subscribeOn(Schedulers.from(jobExecutor))
      .observeOn(AndroidSchedulers.mainThread())
}

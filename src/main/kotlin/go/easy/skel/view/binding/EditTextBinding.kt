package go.easy.skel.view.binding

import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.databinding.BindingAdapter

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for HelpMe on 16.01.21 22:11.
 */
object EditTextBinding {

  @JvmStatic
  @BindingAdapter(value =["forceShowKeyboard"])
  fun bindForceShowKeyboard(editText: EditText, notUse: Nothing?) {
    editText.apply {
      requestFocus()

      (context.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager)?.also {
        it.toggleSoftInput(
          InputMethodManager.SHOW_FORCED,
          InputMethodManager.HIDE_IMPLICIT_ONLY,
        )
      }
    }
  }
}

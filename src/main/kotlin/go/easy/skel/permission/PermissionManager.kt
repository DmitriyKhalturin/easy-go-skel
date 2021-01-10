package go.easy.skel.permission

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import go.easy.skel.exception.ImpossibleException

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for easy_go_skel on 03.04.20 0:55.
 */
class PermissionManager(
  private val activity: Activity? = null,
  private val fragment: Fragment? = null
) {

  companion object {
    private const val FIRST_REQUEST_CODE = 1000
    private const val WITHOUT_CALLBACK_REQUEST_CODE = (FIRST_REQUEST_CODE - 1)

    fun checkSelfPermissions(context: Context, vararg permissions: String) =
      permissions
        .map { ContextCompat.checkSelfPermission(context, it) }
        .all { it == PackageManager.PERMISSION_GRANTED }
  }

  enum class AccessResult {
    PERMISSION_GRANTED,
    PERMISSION_DENIED,
    PERMANENT_PERMISSION_DENIED
  }

  private var requestCodeCounter = FIRST_REQUEST_CODE

  private val requestCallbacks = hashMapOf<Int, PermissionsResponseCallback>()

  private fun getRequestCodeForCallback(callback: PermissionsResponseCallback?): Int {
    callback?.let {
      val requestCode = requestCodeCounter++

      requestCallbacks[requestCode] = it

      return requestCode
    }

    return WITHOUT_CALLBACK_REQUEST_CODE
  }

  fun checkSelfPermissions(permissions: Array<out String>, callback: PermissionsResponseCallback? = null) {
    when {
      activity is Activity -> checkSelfPermissions(activity, permissions, callback)
      fragment is Fragment -> checkSelfPermissions(fragment, permissions, callback)
      else -> throw ImpossibleException()
    }
  }

  private fun checkSelfPermissions(activity: Activity, permissions: Array<out String>, callback: PermissionsResponseCallback? = null) {
    if (checkSelfPermissions(activity, *permissions)) {
      callback?.invoke(AccessResult.PERMISSION_GRANTED)
    } else {
      requestPermissions(activity, permissions, getRequestCodeForCallback(callback))
    }
  }

  private fun checkSelfPermissions(fragment: Fragment, permissions: Array<out String>, callback: PermissionsResponseCallback? = null) {
    try {
      if (checkSelfPermissions(fragment.requireContext(), *permissions)) {
        callback?.invoke(AccessResult.PERMISSION_GRANTED)
      } else {
        requestPermissions(fragment, permissions, getRequestCodeForCallback(callback))
      }
    } catch (exception: Exception) {
      callback?.invoke(AccessResult.PERMANENT_PERMISSION_DENIED)
    }
  }

  private fun requestPermissions(activity: Activity, permissions: Array<out String>, requestCode: Int) =
    ActivityCompat.requestPermissions(activity, permissions, requestCode)

  private fun requestPermissions(fragment: Fragment, permissions: Array<out String>, requestCode: Int) =
    fragment.requestPermissions(permissions, requestCode)

  private fun shouldShowRequestPermissionsRationale(activity: Activity, permissions: Array<out String>) =
    !permissions
      .map { !ActivityCompat.shouldShowRequestPermissionRationale(activity, it) }
      .all { it }

  private fun shouldShowRequestPermissionsRationale(fragment: Fragment, permissions: Array<out String>) =
    !permissions
      .map { !fragment.shouldShowRequestPermissionRationale(it) }
      .all { it }

  fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
    val result = if (grantResults.allPermissionsGranted()) {
      AccessResult.PERMISSION_GRANTED
    } else {
      if (
        when {
          activity is Activity -> shouldShowRequestPermissionsRationale(activity, permissions)
          fragment is Fragment -> shouldShowRequestPermissionsRationale(fragment, permissions)
          else -> throw ImpossibleException()
        }
      ) {
        AccessResult.PERMANENT_PERMISSION_DENIED
      } else {
        AccessResult.PERMISSION_DENIED
      }
    }

    requestCallbacks
      .remove(requestCode)
      ?.invoke(result)
  }
}

typealias PermissionsResponseCallback = (result: PermissionManager.AccessResult) -> Unit

fun IntArray.allPermissionsGranted() =
  this
    .map { it == PackageManager.PERMISSION_GRANTED }
    .all { it }

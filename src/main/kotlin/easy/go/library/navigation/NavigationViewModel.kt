package easy.go.library.navigation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.annotation.ColorRes
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.google.firebase.crashlytics.FirebaseCrashlytics
import easy.go.library.R
import easy.go.library.viewmodel.BaseViewModel

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for easy_go_library on 06.04.20 16:36.
 */
class NavigationViewModel : BaseViewModel(), Navigation {

  private var fragmentActivity: FragmentActivity? = null
  private var fragmentManager: FragmentManager? = null
  private var fragmentContainerId: Int? = null

  fun setViewContext(activity: FragmentActivity) {
    fragmentActivity = activity
    fragmentManager = activity.supportFragmentManager
  }

  fun setContainerId(containerId: Int) {
    fragmentContainerId = containerId
  }

  fun clearViewContext() {
    fragmentActivity = null
    fragmentContainerId = null
  }

  /**
   * Navigation methods for activity
   */

  override fun startRootActivity(activityClass: Class<out FragmentActivity>, bundle: Bundle?) {
    fragmentActivity?.use {
      startActivity(activityClass, true, bundle)
    }
  }

  override fun startIntentActivity(intent: Intent, bundle: Bundle?) {
    fragmentActivity?.use {
      startActivity(intent, bundle)
    }
  }

  /** Navigation method for start resolved activity */

  override fun startIntentResolvedActivity(intent: Intent): Boolean {
    return intent.takeIf {
      val componentName = fragmentActivity?.packageManager?.let {
          packageManager -> it.resolveActivity(packageManager)
      }
      componentName != null
    }?.let {
      startIntentActivity(it)
      true
    } != null
  }

  /**
   * Navigation methods for fragment
   */

  override fun addRootFragment(fragmentClass: Class<out Fragment>, arguments: Bundle?) {
    fragmentManager?.use {
      popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)

      (fragmentActivity as? NavigationActivity)?.setBottomNavigationRootFragment(fragmentClass)

      beginTransaction()
        .apply {
          setCustomAnimations(
            R.anim.fade_in,
            R.anim.fade_out
          )
          replace(fragmentContainerId!!, fragmentClass, arguments)
        }
        .commit()
    }
  }

  override fun addFragment(fragmentClass: Class<out Fragment>, arguments: Bundle?) {
    fragmentManager?.use {
      beginTransaction()
        .apply {
          setCustomAnimations(
            R.anim.simple_slide_in_right,
            R.anim.simple_slide_out_left,
            R.anim.simple_slide_in_left,
            R.anim.simple_slide_out_right
          )
          replace(fragmentContainerId!!, fragmentClass, arguments)
          addToBackStack(fragmentClass.name)
        }
        .commit()
    }
  }

  override fun back() {
    fragmentManager?.use {
      popBackStack()
    }
  }

  override fun showDialogFragment(dialogFragment: DialogFragment) {
    fragmentManager?.use { dialogFragment.show(this, null) }
  }

  /** Navigation methods for custom tab */

  override fun openUrlInInternalBrowser(url: String, @ColorRes customTabsColor: Int) {
    fragmentActivity?.let {
      CustomTabsIntent.Builder()
        .apply {
          setToolbarColor(ContextCompat.getColor(it, customTabsColor))
          addDefaultShareMenuItem()
          setShowTitle(true)
        }
        .build()
        .apply {
          intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        .launchUrl(it, Uri.parse(url))
    }
  }

  /**
   * Extension navigation methods
   */

  private fun FragmentActivity.startActivity(activityClass: Class<out FragmentActivity>, isRoot: Boolean = false, bundle: Bundle? = null) {
    val intent = Intent(this, activityClass)

    if (isRoot) {
      intent.apply {
        addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
      }
    }

    bundle?.let { intent.putExtras(it) }

    startActivity(intent, bundle)
  }

  private inline fun FragmentActivity.use(block: FragmentActivity.() -> Unit) {
    try {
      block()
    } catch (exception: Exception) {
      FirebaseCrashlytics.getInstance().recordException(exception)
    }
  }

  private inline fun FragmentManager.use(block: FragmentManager.() -> Unit) {
    try {
      block()
    } catch (exception: Exception) {
      FirebaseCrashlytics.getInstance().recordException(exception)
    }
  }
}

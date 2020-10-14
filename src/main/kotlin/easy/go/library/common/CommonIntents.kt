package easy.go.library.common

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.annotation.ColorRes
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat

object CommonIntents {

  lateinit var packageManager: PackageManager

  fun sendEmail(address: String) = Intent(Intent.ACTION_SENDTO).apply {
    data = Uri.parse("mailto:")
    putExtra(Intent.EXTRA_EMAIL, arrayOf(address))
  }.takeIf { it.canBeHandled() }

  fun openUrlInInternalBrowser(url: String, context: Context, @ColorRes customTabsColor: Int) {
    CustomTabsIntent.Builder()
      .apply {
        setToolbarColor(ContextCompat.getColor(context, customTabsColor))
        addDefaultShareMenuItem()
        setShowTitle(true)
      }
      .build()
      .apply {
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
      }
      .launchUrl(context, Uri.parse(url))
  }

  fun openUrlInExternalBrowser(url: String) =
    Intent(Intent.ACTION_VIEW, Uri.parse(url)).takeIf {
      it.canBeHandled()
    }

  fun callPhone(phoneNumber: String) =
    Intent(Intent.ACTION_DIAL).apply {
      data = Uri.parse("tel:$phoneNumber")
    }.takeIf { it.canBeHandled() }

  fun showLocationOnMap(query: String) =
    Intent(Intent.ACTION_VIEW).apply {
      data = Uri.parse("geo:0,0?q=${Uri.encode(query)}")
    }.takeIf { it.canBeHandled() }

  fun showRouteOnGoogleMap(destinationQuery: String, travelMode: String = "driving") =
    Intent(Intent.ACTION_VIEW).apply {
      data = Uri.parse(
        "https://www.google.com/maps/dir/?api=1&destination=${Uri.encode(destinationQuery)}&travelmode=$travelMode"
      )
    }.takeIf { it.canBeHandled() }

  /**
   * If [Intent.resolveActivity] returns null, there are no apps on the device
   * that can handle the intent
   */
  private fun Intent.canBeHandled() = this.resolveActivity(packageManager) != null
}

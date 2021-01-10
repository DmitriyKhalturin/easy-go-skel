package go.easy.skel.common

import android.content.Intent
import android.net.Uri

object Intents {

  fun sendEmail(email: String) =
    Intent(Intent.ACTION_SENDTO).apply {
      data = Uri.parse("mailto:")
      putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
    }

  fun openUrlInExternalBrowser(url: String) =
    Intent(Intent.ACTION_VIEW, Uri.parse(url))

  fun callPhone(phoneNumber: String) =
    Intent(Intent.ACTION_DIAL).apply {
      data = Uri.parse("tel:$phoneNumber")
    }

  fun showLocationOnMap(query: String) =
    Intent(Intent.ACTION_VIEW).apply {
      data = Uri.parse("geo:0,0?q=${Uri.encode(query)}")
    }

  fun showRouteOnGoogleMap(destinationQuery: String, travelMode: String = "driving") =
    Intent(Intent.ACTION_VIEW).apply {
      data = Uri.parse("https://www.google.com/maps/dir/?api=1&destination=${Uri.encode(destinationQuery)}&travelmode=$travelMode")
    }
}

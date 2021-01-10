package go.easy.skel.location

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.IntentSender
import android.location.LocationManager
import android.os.Looper
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.CommonStatusCodes
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import go.easy.skel.model.Location
import go.easy.skel.permission.PermissionGroup
import go.easy.skel.permission.PermissionManager

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for easy_go_skel on 03.04.20 0:55.
 */
class LocationProvider(
  private val context: Context
) {

  companion object {
    private const val UPDATE_PRIORITY = LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY
    private const val UPDATE_INTERVAL: Long = 10_000
    private const val FASTEST_UPDATE_INTERVAL = UPDATE_INTERVAL / 2
    private const val UPDATE_THRESHOLD = 10f

    private const val REQUEST_CODE = 100

    @JvmStatic
    private fun getLocationRequest() = LocationRequest.create()
      .setPriority(UPDATE_PRIORITY)
      .setInterval(UPDATE_INTERVAL)
      .setFastestInterval(FASTEST_UPDATE_INTERVAL)
      .setSmallestDisplacement(UPDATE_THRESHOLD)

    @JvmStatic
    private fun getLocationSettingsResponseTask(activity: Activity) =
      LocationServices.getSettingsClient(activity).run {
        val settingsRequest = LocationSettingsRequest.Builder()
          .addLocationRequest(getLocationRequest())
          .build()

        checkLocationSettings(settingsRequest)
      }
  }

  enum class AccessResult {
    ALLOW_ACCESS,
    ACCESS_DENIED
  }

  fun isEnableLocationSettings(context: Context) =
    (context.getSystemService(Context.LOCATION_SERVICE) as? LocationManager)?.run {
      isProviderEnabled(LocationManager.NETWORK_PROVIDER) || isProviderEnabled(LocationManager.GPS_PROVIDER)
    } ?: false

  fun requestEnableLocationSettings(activity: Activity, callback: SettingsResponseCallback) {
    requestCallback = callback

    getLocationSettingsResponseTask(activity).addOnCompleteListener {
      try {
        it.getResult(ApiException::class.java)
      } catch (exception: ApiException) {
        when(exception.statusCode) {
          CommonStatusCodes.RESOLUTION_REQUIRED -> {
            try {
              (exception as ResolvableApiException)
                .startResolutionForResult(activity, REQUEST_CODE)
            } catch (exception: IntentSender.SendIntentException) {
              // TODO: handler error
            }
          }
          LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE -> {
            // TODO: handler this situation
          }
        }
      }
    }
  }

  private var requestCallback: SettingsResponseCallback? = null

  fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    if (requestCode == REQUEST_CODE) {
      val result = if (resultCode == Activity.RESULT_OK) {
        AccessResult.ALLOW_ACCESS
      } else {
        AccessResult.ACCESS_DENIED
      }

      requestCallback?.invoke(result)
    }
  }

  private val client: FusedLocationProviderClient by lazy { FusedLocationProviderClient(context) }

  @SuppressLint(value = ["MissingPermission"])
  fun requestLocation(callback: LocationResponseCallback) {
    if (PermissionManager.checkSelfPermissions(context, *PermissionGroup.LOCATION)) {
      val locationCallback = object : LocationCallback() {
        override fun onLocationResult(result: LocationResult?) {
          result?.lastLocation?.run {
            callback(Location(latitude, longitude))
          }

          client.removeLocationUpdates(this)
        }

        override fun onLocationAvailability(availability: LocationAvailability?) {
          if (availability == null || !availability.isLocationAvailable) {
            callback(null)
          }
        }
      }

      client.requestLocationUpdates(getLocationRequest(), locationCallback, Looper.getMainLooper())
    }
  }
}

typealias SettingsResponseCallback = (result: LocationProvider.AccessResult) -> Unit
typealias LocationResponseCallback = (location: Location?) -> Unit

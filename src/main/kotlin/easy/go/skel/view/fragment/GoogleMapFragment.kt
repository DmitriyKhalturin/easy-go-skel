package easy.go.skel.view.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Handler
import android.os.Looper
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import easy.go.skel.location.LocationProvider
import easy.go.skel.model.Location
import easy.go.skel.navigation.NavigationFragment
import easy.go.skel.permission.PermissionGroup
import easy.go.skel.permission.PermissionManager
import java.util.*
import kotlin.concurrent.schedule

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for easy_go_skel on 21.05.20 6:52.
 */
abstract class GoogleMapFragment : NavigationFragment() {

  companion object {
    private const val CAMERA_MOVING_DELAY = 500L
    private const val CAMERA_ZOOM = 14f
  }

  private var googleMap: GoogleMap? = null

  fun setGoogleMap(value: GoogleMap) {
    googleMap = value
  }


  private var isGestureMoving = false
  private var debounceMoving: TimerTask? = null

  private fun cameraMoveStarted(reason: Int) {
    isGestureMoving = (reason == GoogleMap.OnCameraMoveStartedListener.REASON_GESTURE)

    if (isGestureMoving) {
      onCameraMoveStarted()
    }
  }

  private fun cameraIdle() {
    debounceMoving?.cancel()

    debounceMoving = Timer().schedule(CAMERA_MOVING_DELAY) {
      Handler(Looper.getMainLooper())
        .post(this@GoogleMapFragment::cameraIdleAction)
    }
  }

  private fun cameraIdleAction() {
    if (isGestureMoving) {
      onCameraMoveStopped()
    }

    googleMap?.cameraPosition?.target
      ?.run { Location(latitude, longitude) }
      ?.let { onUpdateLocation(it) }
  }

  protected abstract fun onCameraMoveStarted()
  protected abstract fun onCameraMoveStopped()
  protected abstract fun onUpdateLocation(location: Location)

  fun setGoogleMapMoveListeners() {
    googleMap?.run {
      setOnCameraMoveStartedListener(this@GoogleMapFragment::cameraMoveStarted)
      setOnCameraIdleListener(this@GoogleMapFragment::cameraIdle)
    }
  }


  private val locationProvider by lazy { LocationProvider(context!!) }
  private val permissionManager by lazy { PermissionManager(fragment = this) }

  fun setGoogleMapMyLocation(myLocation: Location?) {
    fun checkPermissions() {
      permissionManager.checkSelfPermissions(PermissionGroup.LOCATION) {
        when(it) {
          PermissionManager.AccessResult.PERMISSION_GRANTED -> {
            enableMyLocation()
            moveCameraToMyLocation(myLocation)
          }
          PermissionManager.AccessResult.PERMISSION_DENIED -> { /** TODO: show warning */ }
          PermissionManager.AccessResult.PERMANENT_PERMISSION_DENIED -> { /** TODO: show attention */ }
        }
      }
    }

    context?.let { context ->
      if (locationProvider.isEnableLocationSettings(context)) {
        checkPermissions()
      } else {
        activity?.let { activity ->
          locationProvider.requestEnableLocationSettings(activity) {
            when(it) {
              LocationProvider.AccessResult.ALLOW_ACCESS -> checkPermissions()
              LocationProvider.AccessResult.ACCESS_DENIED -> { /** TODO: show warning */ }
            }
          }
        }
      }
    }
  }

  @SuppressLint(value = ["MissingPermission"])
  private fun enableMyLocation() {
    googleMap?.run {
      isMyLocationEnabled = true
      uiSettings.isMyLocationButtonEnabled = true
    }
  }

  private fun moveCameraToMyLocation(myLocation: Location?) {
    fun moveCamera(location: Location) {
      val cameraUpdate = CameraUpdateFactory.newLatLngZoom(location.gmsLatLng, CAMERA_ZOOM)

      googleMap?.animateCamera(cameraUpdate)
    }

    if (myLocation == null) {
      locationProvider.requestLocation { location ->
        location?.let(::moveCamera)
      }
    } else {
      moveCamera(myLocation)
    }
  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)

    locationProvider.onActivityResult(requestCode, resultCode, data)
  }

  override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults)

    permissionManager.onRequestPermissionsResult(requestCode, permissions, grantResults)
  }
}

package easy.go.skel.map

import com.google.android.gms.maps.CameraUpdate
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for easy_go_skel on 03.04.20 0:55.
 */
object CameraHelper {

  private const val CAMERA_ZOOM = 12f
  private const val CAMERA_PADDING = 120

  @JvmStatic
  fun getPosition(position: LatLng): CameraUpdate =
    CameraUpdateFactory.newLatLngZoom(position, CAMERA_ZOOM)

  @JvmStatic
  fun getPosition(positions: List<LatLng>, padding: Int? = null): CameraUpdate? =
    when (positions.size) {
      0 -> null
      1 -> getPosition(positions.first())
      else -> try {
        val lats = positions.map { it.latitude }
        val lngs = positions.map { it.longitude }

        val north = LatLng(lats.maxOrNull()!!, lngs.maxOrNull()!!)
        val south = LatLng(lats.minOrNull()!!, lngs.minOrNull()!!)
        val west = LatLng(lats.maxOrNull()!!, lngs.minOrNull()!!)
        val east = LatLng(lats.minOrNull()!!, lngs.maxOrNull()!!)

        val latLngBounds = LatLngBounds.builder()
          .include(north)
          .include(south)
          .include(west)
          .include(east)
          .build()

        CameraUpdateFactory.newLatLngBounds(latLngBounds, padding ?: CAMERA_PADDING)
      } catch (exception: NullPointerException) {
        null
      }
    }
}

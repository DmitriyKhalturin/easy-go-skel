package easy.go.library.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for easy_go_library on 29.05.20 9:08.
 */
@Parcelize
data class Location(
  val lat: Double,
  val lng: Double
) : Parcelable {
  companion object {
    @JvmStatic
    fun factory(lat: Double?, lng: Double?) = Location(lat ?: 0.0, lng ?: 0.0)

    @JvmStatic
    fun factory(latLng: com.google.maps.model.LatLng) = Location(latLng.lat, latLng.lng)

    @JvmStatic
    fun factory(latLng: com.google.android.gms.maps.model.LatLng) = Location(latLng.latitude, latLng.longitude)
  }

  val mapsLatLng: com.google.maps.model.LatLng
    get() = com.google.maps.model.LatLng(lat, lng)

  val gmsLatLng: com.google.android.gms.maps.model.LatLng
    get() = com.google.android.gms.maps.model.LatLng(lat, lng)
}

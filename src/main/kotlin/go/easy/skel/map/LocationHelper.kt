package go.easy.skel.map

import android.location.Location
import com.google.maps.model.LatLng

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for easy_go_skel on 29.05.20 9:24.
 */
object LocationHelper {

  @JvmStatic
  fun getDistanceInMeter(coord1: LatLng?, coord2: LatLng): Double? {
    return coord1?.let {
      val loc1 = Location("")
      loc1.latitude = it.lat
      loc1.longitude = it.lng

      val loc2 = Location("")
      loc2.latitude = coord2.lat
      loc2.longitude = coord2.lng

      loc1.distanceTo(loc2).toDouble()
    }
  }
}

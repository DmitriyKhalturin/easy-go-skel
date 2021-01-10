package go.easy.skel.map.cluster

import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for easy_go_skel on 03.04.20 0:55.
 */
class POIItem(
  val id: Int,
  private val position: LatLng,
  val bitmapDescriptor: BitmapDescriptor
) : ClusterItem {

  companion object {
    fun factory(id: Int, position: LatLng, bitmapDescriptor: BitmapDescriptor) =
      POIItem(
        id,
        position,
        bitmapDescriptor
      )
  }

  override fun getPosition(): LatLng = position

  override fun getTitle(): String? = null

  override fun getSnippet(): String? = null
}

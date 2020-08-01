package easy.go.library.map.cluster

import android.content.Context
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.clustering.view.DefaultClusterRenderer

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for easy_go_library on 03.04.20 0:55.
 */
class POIMarkerRenderer(
  context: Context,
  map: GoogleMap,
  private val clusterManager: ClusterManager<POIItem>
) : DefaultClusterRenderer<POIItem>(context, map, clusterManager) {

  override fun onBeforeClusterItemRendered(item: POIItem?, markerOptions: MarkerOptions?) {
    markerOptions?.run {
      item?.let {
        icon(it.bitmapDescriptor)
      }
    }
  }

  fun getMarker(id: Int): Marker? = getMarker(
    clusterManager
      .algorithm
      .items
      .find { it.id == id }
  )
}

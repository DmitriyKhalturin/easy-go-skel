package easy.go.skel.permission

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for easy_go_skel on 03.04.20 0:55.
 */
object PermissionGroup {

  val LOCATION = arrayOf(
    android.Manifest.permission.ACCESS_FINE_LOCATION,
    android.Manifest.permission.ACCESS_COARSE_LOCATION
  )

  val STORAGE = arrayOf(
    android.Manifest.permission.READ_EXTERNAL_STORAGE,
    android.Manifest.permission.WRITE_EXTERNAL_STORAGE
  )

  val CAMERA = arrayOf(
    android.Manifest.permission.CAMERA
  )

  val PHONE = arrayOf(
    android.Manifest.permission.CALL_PHONE
  )
}

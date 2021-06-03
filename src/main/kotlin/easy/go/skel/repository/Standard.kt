package easy.go.skel.repository

import easy.go.skel.exception.ApiResponseException
import easy.go.skel.exception.NullableApiResponseException
import retrofit2.Response

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for easy_go_skel on 08.04.20 22:22.
 */

inline fun <T: Response<R>, R> T.useResponse(block: (R) -> Unit) {
  if (isSuccessful) {
    val result = body() ?: throw NullableApiResponseException(this)

    block(result)
  } else {
    throw ApiResponseException(this)
  }
}

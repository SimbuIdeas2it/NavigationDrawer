package com.example.navigationdrawer

import android.content.Intent
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.InputStream

class RestApiService {
    fun login(userI: LoginRequestInfo, onResult: (LoginResponseInfo?) -> Unit) {
        val retrofit = ServiceBuilder.buildService(APIService::class.java)
        retrofit.login(userI).enqueue(
            object : Callback<LoginResponseInfo> {
                override fun onResponse(call: Call<LoginResponseInfo>, response: Response<LoginResponseInfo>) {
                    var res = response.body()
                    onResult(res)
                }

                override fun onFailure(call: Call<LoginResponseInfo>, t: Throwable) {
                    onResult(null)
                }
            }
        )
    }

    fun upload(inputStream: InputStream, fileName: String, fileSize: String, mimeType: String, onResult: (APIResponse?) -> Unit) {
        val part = MultipartBody.Part.createFormData(
            "image", "myPic.png", RequestBody.create(
                MediaType.parse("image/*"),
                inputStream.readBytes()
            )
        )

        val retrofit = ServiceBuilder.buildService(APIService::class.java)
        val file_name: RequestBody = RequestBody.create(MediaType.parse("multipart/form-data"), fileName)
        val file_size: RequestBody = RequestBody.create(MediaType.parse("multipart/form-data"), fileSize)
        val mime_type: RequestBody = RequestBody.create(MediaType.parse("multipart/form-data"), mimeType)
        retrofit.updateProfile(file_name, file_size, mime_type,part).enqueue(
            object : Callback<APIResponse> {
                override fun onResponse(call: Call<APIResponse>, response: Response<APIResponse>) {
                    var res = response.body()
                    onResult(res)
                }

                override fun onFailure(call: Call<APIResponse>, t: Throwable) {
                    onResult(null)
                }
            }
        )

    }

    fun list(onResult: (ListResponse?) -> Unit) {
        val retrofit = ServiceBuilder.buildService(APIService::class.java)
        retrofit.list().enqueue(
            object : Callback<ListResponse> {
                override fun onResponse(call: Call<ListResponse>, response: Response<ListResponse>) {
                    var res = response.body()
                    onResult(res)
                }

                override fun onFailure(call: Call<ListResponse>, t: Throwable) {
                    onResult(null)
                }
            }
        )
    }
}
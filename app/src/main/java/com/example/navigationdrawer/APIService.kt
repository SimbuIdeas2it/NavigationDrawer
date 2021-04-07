package com.example.navigationdrawer

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface APIService {
    @Headers("Content-Type: application/json")
    @POST("login")
    fun login(@Body loginData: LoginRequestInfo): Call<LoginResponseInfo>

    @Multipart
    @POST("upload.php")
    fun updateProfile(@Part("name") filename: RequestBody, @Part("size") fileSize: RequestBody, @Part("type") mimeType: RequestBody, @Part filePart: MultipartBody.Part): Call<APIResponse>

    @Headers("Content-Type: application/json")
    @GET("list.php")
    fun list(): Call<ListResponse>
}
package com.example.navigationdrawer

import com.google.gson.annotations.SerializedName

class LoginModel {
}

data class LoginRequestInfo (
    @SerializedName("email") val email: String?,
    @SerializedName("password") val password: String?,
    @SerializedName("notification_id") val notification_id: String?
)

data class LoginResponseInfo (
    @SerializedName("otp_status") val otp_status: String?,
    @SerializedName("session_token") val session_token: String?,
    @SerializedName("token") val token: String?,
    @SerializedName("first_booking") val first_booking: String?,
    @SerializedName("firebase_custom_token") val firebase_custom_token: String?,
    @SerializedName("firebase_uid") val firebase_uid: String?,
    @SerializedName("status") val status: String?,
    @SerializedName("message") val message: String?,
    @SerializedName("refer_code") val refer_code: String?,
    @SerializedName("reward_amount") val reward_amount: String?,
    @SerializedName("user_id") val user_id: String?,
    @SerializedName("mobile") val mobile: String?,
    @SerializedName("access_level") val access_level: String?,
    @SerializedName("firstname") val firstname: String?,
    @SerializedName("lastname") val lastname: String?,
    @SerializedName("workplace_id") val workplace_id: String?,
    @SerializedName("workplace_approved") val workplace_approved: String?,
    @SerializedName("image") val image: String?,
    @SerializedName("by_code") val by_code: String?
)

data class UserInfo (
    @SerializedName("user_id") val userId: Int?,
    @SerializedName("user_name") val userName: String?,
    @SerializedName("user_email") val userEmail: String?,
    @SerializedName("user_age") val userAge: String?,
    @SerializedName("user_uid") val userUid: String?
)

data class APIResponse (
    @SerializedName("status") val status: String?,
    @SerializedName("message") val message: String?
)

data class ListResponse (
    @SerializedName("datas") val datas: List<ListDetailResponse>?
)

data class ListDetailResponse (
    @SerializedName("name") val name: String?,
    @SerializedName("email") val email: String?,
    @SerializedName("mobile") val mobile: String?
)
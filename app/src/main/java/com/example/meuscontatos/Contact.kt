package com.example.meuscontatos

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
class Contact (
    var name: String,
    var phone: String,
    var photo: String
        ):Parcelable {
}
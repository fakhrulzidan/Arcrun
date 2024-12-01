package com.belajar.arcruneo.Model

import android.os.Parcel
import android.os.Parcelable

data class EventModel(
    var batas_akhir: String? = null,
    var deskripsi: String? = null,
    var gambar: String? = null,
    var harga: Int = 0,
    var kategori: String? = null,
    var kuota: Int = 0,
    var lokasi: String? = null,
    var nama_event: String? = null,
    var status_event: String? = null,
    var waktu_mulai: String? = null,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(batas_akhir)
        parcel.writeString(deskripsi)
        parcel.writeString(gambar)
        parcel.writeInt(harga)
        parcel.writeString(kategori)
        parcel.writeInt(kuota)
        parcel.writeString(lokasi)
        parcel.writeString(nama_event)
        parcel.writeString(status_event)
        parcel.writeString(waktu_mulai)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<EventModel> {
        override fun createFromParcel(parcel: Parcel): EventModel {
            return EventModel(parcel)
        }

        override fun newArray(size: Int): Array<EventModel?> {
            return arrayOfNulls(size)
        }
    }
}
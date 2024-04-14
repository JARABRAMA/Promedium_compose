package com.example.promedium.ui.theme.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Grade(var name: String, var qualification: Double, val percentage: Double) : Parcelable {
    override fun toString(): String {
        return "${this.name}$SEPARATOR${this.qualification}$SEPARATOR${this.percentage}"
    }
}
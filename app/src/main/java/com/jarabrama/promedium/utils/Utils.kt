package com.jarabrama.promedium.utils

import java.text.DecimalFormat

object Utils {
    fun numberFormat(number: Double): String {
        var formattedNumber: String = number.toString()
        if (formattedNumber.endsWith(".0")) {
            formattedNumber.replace(".0", "")
        } else {
            val decimalFormat = DecimalFormat("#.00")
            formattedNumber = decimalFormat.format(number)
        }
        return formattedNumber
    }
}
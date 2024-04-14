package com.example.promedium.ui.theme.model

import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.io.IOException
import javax.security.auth.PrivateCredentialPermission

@Parcelize
class Course(var name: String, var credits: Int, var grades: @RawValue MutableList<Grade>) :
    Parcelable {
    @IgnoredOnParcel
    val coursesFileAddress: String = "$DIRECTORY_ADDRESS${this.name}.txt"
    fun average(): Double {
        var average = 0.0;
        grades.forEach { grade -> average += grade.percentage * (grade.qualification) / 100 }
        return average
    }

    fun saveInFile() {
        try {
            BufferedWriter(FileWriter(this.coursesFileAddress, false)).use { writer ->
                this.grades.forEach { grade ->
                    val line = grade.toString()
                    writer.newLine()
                    writer.write(line)
                }
            }
        } catch (_: IOException) {
        }
    }
}

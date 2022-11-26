package xyz.hellosrinath.unitconverter.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "result_table")
data class ConversionResult(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "result_id")
    val id: Int,
    @ColumnInfo(name = "result_msg1")
    val msgPart1: String,
    @ColumnInfo(name = "result_msg2")
    val msgPart2: String
)

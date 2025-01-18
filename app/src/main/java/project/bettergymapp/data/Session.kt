package project.bettergymapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import java.time.LocalDate

@Entity(tableName = "session")
@TypeConverters(Converters::class)
data class Session(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val date: LocalDate,
    val duration: Int,
    val log: List<Exercise>
){
    constructor() : this(0, LocalDate.now(), 0, emptyList())
}
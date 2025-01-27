package project.bettergymapp.data

import com.google.firebase.Timestamp
import com.google.firebase.firestore.ServerTimestamp

data class Session(
    val id: Int = 0,
    @ServerTimestamp val date: Timestamp? = null,
    val duration: Int,
    val log: List<Exercise>
){
    constructor() : this(0, null, 0, emptyList())
}
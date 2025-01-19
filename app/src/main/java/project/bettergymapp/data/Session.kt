package project.bettergymapp.data

data class Session(
     val id: Int = 0,
    //val date: LocalDate,
    //@ServerTimestamp val date: Timestamp? = null,
    val duration: Int,
    val log: List<Exercise>
){
    constructor() : this(0 , 0, emptyList())
}
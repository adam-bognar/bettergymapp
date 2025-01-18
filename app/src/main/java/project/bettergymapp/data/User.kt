package project.bettergymapp.data

data class User(
    val uid: String,
    val exercises: List<Exercise> = emptyList(),
    val routines: List<Routine> = emptyList(),
    val sessions: List<Session> = emptyList()
){
    constructor() : this("", emptyList(), emptyList(), emptyList())
}

package project.bettergymapp.data

data class ExerciseLog(
    val id: Int = 0,
    val sets: List<RepsToWeight> //A dictionary of reps to weight
){
    constructor(): this(0, listOf())
}

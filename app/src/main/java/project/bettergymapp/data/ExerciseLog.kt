package project.bettergymapp.data

data class ExerciseLog(
    val id: Int = 0,
    val sets: List<Pair<Int,Int>> //A dictionary of reps to weight
)

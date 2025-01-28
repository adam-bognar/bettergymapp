package project.bettergymapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.room.Room
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import project.bettergymapp.data.Database
import project.bettergymapp.data.repository.Exercise.IExerciseRepository
import project.bettergymapp.data.repository.ISessionRepository
import project.bettergymapp.data.repository.Routine.IRoutineRepository
import project.bettergymapp.data.repository.User.IUserRepository
import project.bettergymapp.data.service.AccountService
import project.bettergymapp.data.service.AccountServiceImpl
import project.bettergymapp.ui.screen.NavGraph


class MainActivity : ComponentActivity() {

    companion object{
        lateinit var userRepository: IUserRepository
        lateinit var routineRepository: IRoutineRepository
        lateinit var exerciseRepository: IExerciseRepository
        lateinit var sessionRepository: ISessionRepository
        lateinit var accountService: AccountService

        private lateinit var database : Database
    }


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)


            database = Room.databaseBuilder(
                applicationContext,
                Database::class.java,
                "gym_database"
            ).fallbackToDestructiveMigration().build()

            var db = Firebase.firestore


            accountService = AccountServiceImpl()




        setContent {
            NavGraph()
        }
    }


}


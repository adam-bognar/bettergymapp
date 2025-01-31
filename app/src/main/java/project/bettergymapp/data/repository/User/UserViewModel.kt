package project.bettergymapp.data.repository.User

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import project.bettergymapp.MainActivity

class UserViewModel(
    private val repository: IUserRepository
):ViewModel() {


    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                UserViewModel(repository = MainActivity.userRepository)
            }
        }
    }


}
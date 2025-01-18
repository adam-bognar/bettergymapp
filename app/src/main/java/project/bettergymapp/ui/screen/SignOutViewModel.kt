package project.bettergymapp.ui.screen

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import project.bettergymapp.MainActivity
import project.bettergymapp.SIGN_IN_SCREEN
import project.bettergymapp.data.service.AccountService
import project.bettergymapp.data.viewmodel.AppViewModel
import javax.inject.Inject

class SignOutViewModel @Inject constructor(
    private val accountService: AccountService,

    ): AppViewModel() {
    fun onSignOutClick(openAndPopUp: (String) -> Unit) {
        launchCatching {
            accountService.signOut()
            openAndPopUp(SIGN_IN_SCREEN)
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                SignOutViewModel(MainActivity.accountService)
            }
        }
    }
}
package project.bettergymapp.ui.screen.profile

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import project.bettergymapp.data.repository.Session.SessionViewModel
import project.bettergymapp.data.repository.User.UserViewModel

@Composable
fun ProfileScreen(
    sessionViewModel: SessionViewModel = viewModel(factory = SessionViewModel.Factory),
    userViewModel: UserViewModel = viewModel(factory = UserViewModel.Factory)
) {
    ProfileHeader("username", "week streak", "day streak", "total sessions")
}



package project.bettergymapp.ui.screen.profile

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.firebase.Timestamp
import project.bettergymapp.R
import project.bettergymapp.data.Session
import project.bettergymapp.data.repository.Session.MockSessionRepository
import project.bettergymapp.data.repository.Session.SessionViewModel


@Composable
fun ProfileScreen(
    sessionViewModel: SessionViewModel = viewModel(factory = SessionViewModel.Factory),
) {
    var sessionsClicked by remember { mutableStateOf(false) }
    val sessionList by sessionViewModel.list.collectAsState()
    val sessionCount by remember { mutableIntStateOf(sessionViewModel.sessionCount()) }

    var viewSession by remember { mutableStateOf(true) }
    var selectedSession: Session by remember { mutableStateOf(Session()) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.background))
    ){
        Column {
            ProfileHeader(
                sessionCount = sessionCount
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .padding(top = 10.dp),

                ) {
                Button(
                    onClick = {
                        sessionsClicked = true
                    },
                    colors = ButtonDefaults.buttonColors(colorResource(id = R.color.button_color)),
                    modifier = Modifier.weight(1f),
                    shape = MaterialTheme.shapes.medium
                ) {
                    Text(
                        text = "Sessions",
                        color = colorResource(id = R.color.button_text_color),
                        style = MaterialTheme.typography.titleMedium
                    )
                }
                Spacer(modifier = Modifier.weight(0.1f))
                Button(
                    onClick = {
                        sessionsClicked = false
                    },
                    colors = ButtonDefaults.buttonColors(colorResource(id = R.color.button_color)),
                    modifier = Modifier.weight(1f),
                    shape = MaterialTheme.shapes.medium


                ) {
                    Text(
                        text = "Statistics",
                        color = colorResource(id = R.color.button_text_color),
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }

            if (sessionsClicked) {
                LazyColumn { // This is a vertically scrolling list
                    items(sessionList.size) { session ->
                        SessionsCard(
                            name = sessionList[session].name,
                            date = dateToString(sessionList[session].date),
                            onViewClick = {
                                selectedSession = sessionList[session]
                                viewSession = !viewSession                        }
                        )
                    }
                }
            }

        }
        AnimatedVisibility(
            visible = viewSession,
            enter = slideInVertically(
                initialOffsetY = { it } // Start from below the screen
            ),
            exit = slideOutVertically(
                targetOffsetY = { it } // Exit to below the screen
            ),
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            SessionPopUp(
                name = selectedSession.name,
                date = dateToString(selectedSession.date),
                exercises = selectedSession.log,
                onClose = {
                    viewSession = false
                }
            )
        }
    }



}

fun dateToString(date: Timestamp?): String {
    if (date != null) {
        return date.toDate().toString()
    }
    return "null"
}

@Preview
@Composable
fun ProfileScreenPreview() {

    val mockSessionViewModel = SessionViewModel(repository = MockSessionRepository())

    ProfileScreen(
        sessionViewModel = mockSessionViewModel
    )
}




package project.bettergymapp.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import project.bettergymapp.R

@Composable
fun TopAppBar(
    username: String,
    viewModel: SignOutViewModel = viewModel(factory = SignOutViewModel.Factory),
    openAndPopUp: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .background(colorResource(R.color.top_app_bar))
            .padding(start = 10.dp)
    ) {
        Row {
            Column(modifier = Modifier.fillMaxWidth()) {
                // First line of text
                Text(
                    text = stringResource(R.string.hello, username),
                    color = Color.Black,
                    modifier = Modifier.padding(top = 10.dp),
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                )
                // Second line of text
                Text(
                    text = stringResource(R.string.ready),
                    color = Color.Black,
                    modifier = Modifier.padding(top = 3.dp),
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp
                    )
                )

                Button(
                    onClick = {
                        viewModel.onSignOutClick(openAndPopUp)
                    },
                    modifier = Modifier.padding(top = 5.dp)
                ) {
                    Text(
                        text = ("sign out"),
                        color = Color.Black
                    )
                }

            }
        }
    }
}

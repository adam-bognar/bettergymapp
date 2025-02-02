package project.bettergymapp.ui.screen.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import project.bettergymapp.R
import project.bettergymapp.data.Exercise

@Composable
fun SessionPopUp(
    name: String,
    date: String,
    exercises: List<Exercise>,
    onClose: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 100.dp)
            .clip(shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
            .background(color = colorResource(id = R.color.routine_card))
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(top = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = name,
                    modifier = Modifier.weight(1f),
                    color = colorResource(id = R.color.text_color),
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center
                )
                IconButton(
                    onClick = {
                        onClose()
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = "Close",
                        tint = colorResource(id = R.color.button_color),
                        modifier = Modifier.size(50.dp)
                    )
                }

            }

            Text(
                text = date,
                color = Color.Gray,
                style = MaterialTheme.typography.titleSmall
            )

            LazyColumn {
                items(exercises.size) { index ->
                    SessionItem(exercise = exercises[index])
                }
            }
        }

    }

}

@Preview(showBackground = true)
@Composable
fun SessionPopUpPreview() {
    SessionPopUp(
        name = "Session 1",
        date = "12/12/2021",
        exercises = listOf(
            Exercise(),
            Exercise(),
        ),
        onClose = {}
    )

}
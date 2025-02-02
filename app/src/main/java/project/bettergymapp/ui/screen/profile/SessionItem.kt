package project.bettergymapp.ui.screen.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import project.bettergymapp.R
import project.bettergymapp.data.Exercise

@Composable
fun SessionItem(
    exercise: Exercise,
) {
    val logs by remember { mutableStateOf(exercise.lastLog?.sets ?: listOf()) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, start = 10.dp, end = 10.dp)
            .clip(shape = RoundedCornerShape(20.dp))
            .background(colorResource(id = R.color.background))
    ) {
        Text(
            text = exercise.name,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, bottom = 10.dp),
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                color = colorResource(id = R.color.text_color)
            ),
            textAlign = TextAlign.Center
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 18.dp)
        ) {
            Text(text = "SET", modifier = Modifier.weight(1f), textAlign = TextAlign.Center, color = colorResource(id = R.color.text_color))
            Text(text = "PREVIOUS", modifier = Modifier.weight(1f), textAlign = TextAlign.Center, color = colorResource(id = R.color.text_color))
        }

        logs.forEachIndexed { index, log ->
            SessionDataRow(
                setNumber = index + 1,
                data = log,
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun SessionItemPreview() {
    SessionItem(
        exercise = Exercise(
            name = "Bench Press",
            lastLog = null
        )
    )
}
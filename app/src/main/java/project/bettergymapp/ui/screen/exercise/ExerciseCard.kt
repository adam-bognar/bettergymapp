package project.bettergymapp.ui.screen.exercise

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import project.bettergymapp.R

@Composable
fun ExerciseCard(
    name: String,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .height(60.dp)
            .fillMaxWidth().
            clip(RoundedCornerShape(8.dp))
            .background(colorResource(id = R.color.routine_card))
            .clickable(onClick = {
                onClick()
            }),
        contentAlignment = Alignment.CenterStart
    ) {
        Text(
            text = name,
            style = MaterialTheme.typography.titleLarge,
            color = colorResource(id = R.color.text_color),
            modifier = Modifier.padding(start = 20.dp)
        )

    }
}


@Preview(showBackground = true)
@Composable
fun ExerciseCardPreview() {
    ExerciseCard("Bench Press")
}
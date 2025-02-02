package project.bettergymapp.ui.screen.exercise

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import project.bettergymapp.R

@Composable
fun ExerciseCardWithDelete(
    name: String,
    onDelete: () -> Unit
) {
    Row(
        modifier = Modifier
            .padding(8.dp)
            .height(60.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(colorResource(id = R.color.routine_card)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = name,
            style = MaterialTheme.typography.titleLarge,
            color = colorResource(id = R.color.text_color),
            modifier = Modifier.padding(start = 20.dp)
        )
        Spacer(modifier = Modifier.weight(1f))
        IconButton(onClick = onDelete,
            modifier = Modifier
                .padding(end = 8.dp)
                .size(40.dp)) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete",
                    tint = Color.Red
                )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ExerciseCardWithDeletePreview() {
    ExerciseCardWithDelete("Bench Press") {
        // Handle delete action
    }
}
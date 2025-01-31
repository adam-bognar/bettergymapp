package project.bettergymapp.ui.screen.exercise

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import project.bettergymapp.R

@Composable
fun MusclesButton(
    name: String,
    onClick: () -> Unit = {},
    isSelected: Boolean = false
) {
    val color = if (isSelected) Color.Green else colorResource(id = R.color.button_color)

    ElevatedButton(
        onClick = {
            onClick()
        },
        colors = ButtonDefaults.elevatedButtonColors(
            containerColor = color
        ),
        contentPadding = PaddingValues(0.dp), // Remove padding
        modifier = Modifier.padding(4.dp)
    ) {
        Text(
            name,
            color = colorResource(id = R.color.button_text_color),
            modifier = Modifier.padding(0.dp),
            style = MaterialTheme.typography.titleMedium

        )
    }
}

@Preview(showBackground = true)
@Composable
fun MusclesButtonPreview() {
    MusclesButton(
        "Chest"
    )
}
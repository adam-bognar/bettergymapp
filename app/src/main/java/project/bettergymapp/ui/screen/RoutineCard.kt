
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
@Composable
fun RoutineCard(
    routine: String,
    color: Color,
    isEditing: Boolean
) {
    val textState = remember { mutableStateOf(routine) }

    Box(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(60.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(color),
        contentAlignment = Alignment.Center
    ) {
        if (isEditing) {
            OutlinedTextField(
                value = textState.value,
                onValueChange = { textState.value = it },
                textStyle = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color.Black // Better contrast for readability
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clip(RoundedCornerShape(4.dp)),
                singleLine = true // Ensure single-line input for this context
            )
        } else {
            Text(
                text = textState.value,
                color = Color.White,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                ),
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RoutineCardPreview() {
    RoutineCard("Chest Day", Color.Blue, isEditing = false)
}

@Preview(showBackground = true)
@Composable
fun RoutineCardEditingPreview() {
    RoutineCard("Chest Day", Color.Blue, isEditing = true)
}

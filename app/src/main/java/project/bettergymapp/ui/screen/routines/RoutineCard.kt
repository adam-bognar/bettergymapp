
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import project.bettergymapp.data.Routine

@Composable
fun RoutineCard(
    routine: Routine,
    onOptionsClick: () -> Unit,
    onStartClick: (Routine) -> Unit
) {

Box(
    modifier = Modifier
        .padding(8.dp)
        .height(130.dp)
        .fillMaxWidth().
        clip(RoundedCornerShape(18.dp))
        .background(color = colorResource(R.color.routine_card)),
){
    Column {
        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 10.dp)
            , horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.padding(start = 20.dp),
                text = routine.name,
                color = colorResource(R.color.text_color),
                style = MaterialTheme.typography.headlineSmall
            )
            IconButton(
                modifier = Modifier.padding(end = 10.dp),
                onClick = {
                    onOptionsClick()
                }

            ) {
                Icon(
                    modifier = Modifier.size(30.dp),
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "Settings",
                    tint = colorResource(R.color.button_color)
                )
            }
        }
        Button(
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 10.dp),
            onClick = {
                onStartClick(routine)
            },
            colors = ButtonDefaults.buttonColors(colorResource(R.color.button_color))
        ) {
            Text(
                text = "Start workout",
                color = colorResource(R.color.button_text_color),
                style = MaterialTheme.typography.titleMedium)
        }
    }
}


}

@Preview(showBackground = true)
@Composable
fun RoutineCardPreview() {
    val routine = Routine(name = "Routine 1", description = "")
    RoutineCard(routine = routine, onOptionsClick = {}, onStartClick = {})
}

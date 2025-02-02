package project.bettergymapp.ui.screen.profile

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import project.bettergymapp.R
import project.bettergymapp.data.RepsToWeight

@Composable
fun SessionDataRow(
    setNumber: Int,
    data: RepsToWeight,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
    ) {
        Text(
            text = setNumber.toString(),
            modifier = Modifier
                .weight(1f)
                .height(24.dp),
            textAlign = TextAlign.Center,
            color = colorResource(id = R.color.text_color)
        )
        Text(
            text = "${data.weight}kg x ${data.reps}",
            modifier = Modifier
                .weight(1f)
                .height(24.dp),
            textAlign = TextAlign.Center,
            style = TextStyle(color = Color.Gray)

        )
    }
}
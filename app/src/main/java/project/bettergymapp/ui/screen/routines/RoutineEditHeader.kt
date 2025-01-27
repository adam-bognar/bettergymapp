package project.bettergymapp.ui.screen.routines

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import project.bettergymapp.R

@Composable
fun RoutineEditHeader(
    title: String = "Create Routine",
    onNavigateBack: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(colorResource(id = R.color.background)),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        IconButton(
            onClick = { onNavigateBack() }
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBackIosNew,
                contentDescription = "Back",
                tint = colorResource(id = R.color.button_color)
            )
        }

        Text(
            text = "Edit Routine",
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.titleLarge,
            color = colorResource(id = R.color.text_color)
        )
        Spacer(modifier = Modifier.weight(0.1f)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RoutineEditHeaderPreview() {
    RoutineEditHeader(
        onNavigateBack = {}
    )
}

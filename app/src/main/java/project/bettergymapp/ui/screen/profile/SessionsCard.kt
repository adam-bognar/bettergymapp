package project.bettergymapp.ui.screen.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import project.bettergymapp.R

@Composable
fun SessionsCard(
    name: String,
    date: String,
    onViewClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp).padding(top = 10.dp)
            .clip(RoundedCornerShape(18.dp))
            .background(colorResource(id = R.color.routine_card))
    ){
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,
            horizontalArrangement = androidx.compose.foundation.layout.Arrangement.SpaceBetween
        ){
            Column(){
                Text(
                    text = name,
                    color = colorResource(id = R.color.text_color),
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(start = 20.dp, top = 10.dp)
                )
                Text(
                    text = date,
                    color = Color.Gray,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(start = 20.dp, bottom = 10.dp)
                )
            }

            IconButton(
                onClick = onViewClick,
                modifier = Modifier.padding(end = 10.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowUp,
                    contentDescription = "View Session",
                    tint = colorResource(id = R.color.button_color),
                    modifier = Modifier.size(50.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun SessionsCardPreview() {
    SessionsCard(
        name = "Session Name",
        date = "Session Date",
        onViewClick = {}
    )
}
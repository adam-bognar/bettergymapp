package project.bettergymapp.ui.screen.routines

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import project.bettergymapp.R
import project.bettergymapp.data.Routine

@Composable
fun RoutineOptionsPopUp(
    routine: Routine,
    modifier: Modifier = Modifier,
    onClose: () -> Unit,
    onRoutineDelete: (Routine) -> Unit,
    onRoutineEdit: (Routine) -> Unit
) {


    Box(
        modifier = modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .clip(
                RoundedCornerShape(
                    topStart = 28.dp, // Round top-start corner
                    topEnd = 28.dp,   // Round top-end corner
                    bottomStart = 0.dp,
                    bottomEnd = 0.dp
                )
            )
            .background(colorResource(R.color.routine_card))
    ){
        Column(
            modifier = Modifier
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp)
                    .padding(top = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = routine.name,
                    color = colorResource(R.color.text_color),
                    style = MaterialTheme.typography.headlineSmall

                )
                IconButton(
                    onClick = {
                        onClose()
                    },
                ) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = "Close",
                        tint = colorResource(R.color.button_color),
                        modifier = Modifier.size(38.dp)
                    )
                }

            }

            Box(
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp, vertical = 10.dp)

                    .clip(RoundedCornerShape(18.dp))
                    .background(colorResource(R.color.button_color)),

            ){
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    TextButton(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            //TODO
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Visibility,
                            contentDescription = "View",
                            tint = colorResource(R.color.button_text_color)
                        )
                        Text(
                            text = "View",
                            color = colorResource(R.color.button_text_color),
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier
                                .padding(start = 10.dp)
                                .fillMaxWidth()
                        )
                    }
                    HorizontalDivider(thickness = 2.dp, color = colorResource(R.color.routine_card))
                    TextButton(
                        modifier = Modifier.fillMaxWidth(),

                        onClick = {
                            onRoutineEdit(routine)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "Edit",
                            tint = colorResource(R.color.button_text_color)
                        )
                        Text(
                            text = "Edit",
                            color = colorResource(R.color.button_text_color),
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier
                                .padding(start = 10.dp)
                                .fillMaxWidth()
                        )
                    }
                    HorizontalDivider(thickness = 2.dp, color = colorResource(R.color.routine_card))
                    TextButton(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            onRoutineDelete(routine)
                            onClose()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Delete",
                            tint = Color.Red
                        )
                        Text(
                            text = "Delete",
                            color = Color.Red,
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier
                                .padding(start = 10.dp)
                                .fillMaxWidth()
                        )
                    }

                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun PreviewRoutineOptionsPopUp() {
    val routine = Routine(name = "Routine 1", description = "")
    RoutineOptionsPopUp(routine = routine, onClose = {}, onRoutineDelete = { },
        onRoutineEdit = {}
    )
}
package project.bettergymapp.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import project.bettergymapp.R

@Composable
fun WorkoutHeader(
    name: String,
    onFinish: () -> Unit,
    onTimerClick: () -> Unit,
    onBack: () -> Unit,
) {
    Box(
        modifier = Modifier
        .fillMaxWidth()
        .background(colorResource(R.color.top_app_bar))
        .drawBottomBorder(1.dp, Color.Gray)
        .padding(top = 10.dp, bottom = 10.dp)


    )  {
        Row(modifier = Modifier.align(Alignment.CenterStart)) {
            IconButton(
                modifier = Modifier.align(Alignment.CenterVertically),
                onClick = { onBack() }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBackIosNew,
                    contentDescription = "Back button"
                )
            }
            Text(
                modifier = Modifier.align(Alignment.CenterVertically),
                text = name,
                style = TextStyle(
                    fontSize = 23.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }

        Row (
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 10.dp)
        ){
            IconButton(
                modifier = Modifier.align(Alignment.CenterVertically),
                onClick = onTimerClick
            ) {
                Icon(
                    imageVector = Icons.Default.Timer,
                    contentDescription = "Timer"
                )
            }
            Button(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .wrapContentSize(),
                onClick = onFinish,
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.happypink))
            ) {
                Text("Finish",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 17.sp
                    )
                )
            }
        }
    }
}

fun Modifier.drawBottomBorder(borderWidth: Dp, borderColor: Color) = this.drawBehind {
    val strokeWidthPx = borderWidth.toPx()
    val y = size.height - strokeWidthPx / 2
    drawLine(
        color = borderColor,
        start = androidx.compose.ui.geometry.Offset(0f, y),
        end = androidx.compose.ui.geometry.Offset(size.width, y),
        strokeWidth = strokeWidthPx
    )
}

@Preview(showBackground = true)
@Composable
fun WorkoutHeaderPreview() {
    WorkoutHeader(
        name = "Workout",
        onFinish = { },
        onTimerClick = { },
        onBack = { }
    )
}
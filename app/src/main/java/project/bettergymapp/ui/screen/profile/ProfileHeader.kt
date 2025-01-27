package project.bettergymapp.ui.screen.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import project.bettergymapp.R

@Composable
fun ProfileHeader(s: String, s1: String, s2: String, s3: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(colorResource(id = R.color.top_app_bar))
            .padding(10.dp)
    ) {
        Column() {
            Text(text = s)
            Row() {
                Text(text = s1)
                Text(text = s2)
                Text(text = s3)
            }
        }
    }
}

@Preview
@Composable
fun ProfileHeaderPreview() {
    ProfileHeader("username", "week streak", "day streak", "total sessions")
}
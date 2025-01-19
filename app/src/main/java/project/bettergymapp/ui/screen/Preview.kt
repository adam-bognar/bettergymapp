package project.bettergymapp.ui.screen

import RoutineCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import project.bettergymapp.R


@Preview
@Composable
fun GreetingPreview() {
    //MainScreen("Adam")
    RoutineCard("Push pull legs",
        colorResource(id = R.color.happyblue),true)
}
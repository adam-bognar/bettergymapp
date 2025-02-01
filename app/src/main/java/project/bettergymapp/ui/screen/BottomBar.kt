package project.bettergymapp.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import project.bettergymapp.R

@Composable
fun BottomBar(
    navigateHome: () -> Unit,
    navigateProfile: () -> Unit,
    from: String
) {
    var selectedIcon by remember { mutableStateOf(from) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(colorResource(id = R.color.background))
    ) {
        HorizontalDivider(thickness = 2.dp, color = colorResource(id = R.color.routine_card))
        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp),
        )
        {
            IconButton(
                onClick = {
                    selectedIcon = "home"
                    navigateHome()
                },
                modifier = Modifier.weight(1f)
            ) {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Home",
                    tint = if (selectedIcon == "home") colorResource(id = R.color.button_color) else colorResource(id = R.color.text_color),
                    modifier = Modifier.size(50.dp)
                )
            }
            IconButton(
                onClick = {
                    selectedIcon = "profile"
                    navigateProfile()
                },
                modifier = Modifier.weight(1f)
            ) {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "Profile",
                    tint = if (selectedIcon == "profile") colorResource(id = R.color.button_color) else colorResource(id = R.color.text_color),
                    modifier = Modifier.size(50.dp)
                )
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomBarPreview() {
    BottomBar(
        navigateHome = {},
        navigateProfile = {},
        from = "home"
    )
}
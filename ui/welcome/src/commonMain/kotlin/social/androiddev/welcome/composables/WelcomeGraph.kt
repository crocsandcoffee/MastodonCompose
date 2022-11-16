package social.androiddev.welcome.composables

import WelcomeScreen
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@Composable
fun WelcomeFlowGraph(
    navigateToTimeline: () -> Unit,
) {
    var screen by rememberSaveable { mutableStateOf(Screen.Welcome) }
    when (screen) {
        Screen.Welcome -> {
            WelcomeScreen(
                modifier = Modifier.fillMaxSize(),
                onGetStartedClicked = {
                    screen = Screen.ChooseServer
                }
            )
        }
        Screen.ChooseServer -> {
            ChooseServerScreen(
                modifier = Modifier.fillMaxSize(),
                onUserLoggedIn = navigateToTimeline
            )
        }
    }
}

private enum class Screen {
    Welcome,
    ChooseServer
}
package social.androiddev.welcome.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import social.androiddev.common.composables.buttons.MastodonButton
import social.androiddev.common.composables.text.MastodonTextField
import social.androiddev.common.utils.AsyncImage
import social.androiddev.common.utils.loadImageIntoPainter

private class ViewModel(

) {
    private val _loading = MutableStateFlow(true)
    val loading = _loading.asStateFlow()

    fun login() {
        _loading.value = true
    }
}

@Composable
fun ChooseServerScreen(
    modifier: Modifier = Modifier,
    onUserLoggedIn: () -> Unit,
) {
    ChooseServerScreen(
        modifier = modifier,
        onUserLoggedIn = onUserLoggedIn,
    )
}

@Composable
private fun ChooseServerScreen(
    modifier: Modifier = Modifier,
    onUserLoggedIn: () -> Unit,
    viewModel: ViewModel = ViewModel(),
) {

    Surface(
        modifier = modifier,
        color = MaterialTheme.colors.surface,
    ) {

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Spacer(Modifier.height(32.dp))

            AsyncImage(
                load = { loadImageIntoPainter(url = "https://via.placeholder.com/200x200/6FA4DE/010041?text=MastodonX") },
                painterFor = { remember { it } },
                contentDescription = "App Logo",
                modifier = Modifier
                    .padding(horizontal = 48.dp)
                    .size(240.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop,
            )

            Spacer(Modifier.height(32.dp))

            var server by remember { mutableStateOf("") }

            MastodonTextField(
                value = server,
                onValueChange = { server = it },
                placeholder = {
                    Text(text = "androiddev.social")
                },
                label = { Text(text = "Server") }
            )

            Spacer(Modifier.height(24.dp))

            MastodonButton(
                modifier = Modifier
                    .widthIn(min = 240.dp)
                    .padding(horizontal = 24.dp),
                onClick = viewModel::login,
                text = "Log In"
            )
        }
    }
}
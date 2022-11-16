package social.androiddev.mastodon

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import social.androiddev.common.theme.MastodonTheme
import social.androiddev.welcome.composables.WelcomeFlowGraph

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MastodonTheme {
                WelcomeFlowGraph(
                    navigateToTimeline = {}
                )
            }
        }
    }
}

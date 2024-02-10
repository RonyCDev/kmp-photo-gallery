package features.groups

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Groups3
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions

object GroupsTab : Tab {

    @Composable
    override fun Content() {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("Tab Groups")
        }
    }

    override val options: TabOptions
        @Composable
        get() {
            val icon = rememberVectorPainter(Icons.Rounded.Groups3)
            val title = "Groups"
            val index: UShort = 2u

            return TabOptions(
                index, title, icon
            )
        }

}
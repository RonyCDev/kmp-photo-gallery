package features.galleries

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.GridView
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import features.components.SectionPhoto

object GalleriesTab : Tab {

    @Composable
    override fun Content() {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            item {
                SectionPhoto(
                    title = "Sun 07 May",
                    photos = listOf(
                        "https://picsum.photos/301",
                        "https://picsum.photos/302",
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
            }
            item {
                SectionPhoto(
                    title = "Sun 08 May",
                    photos = listOf(
                        "https://picsum.photos/201",
                        "https://picsum.photos/202",
                        "https://picsum.photos/203",
                        "https://picsum.photos/204",
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }

    override val options: TabOptions
        @Composable
        get() {
            val icon = rememberVectorPainter(Icons.Rounded.GridView)
            val title = "Galleries"
            val index: UShort = 1u

            return TabOptions(
                index, title, icon
            )
        }

}
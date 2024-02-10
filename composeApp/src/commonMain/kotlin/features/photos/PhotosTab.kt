package features.photos

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.rounded.Explore
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Photo
import androidx.compose.material.icons.rounded.Schedule
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import co.touchlab.kermit.Logger
import features.components.SectionPhoto
import ui.theme.LightBlue
import ui.theme.OnLightBlue
import ui.theme.OnPink
import ui.theme.OnPurple
import ui.theme.OnYellow
import ui.theme.Pink
import ui.theme.Purple
import ui.theme.Yellow

object PhotosTab : Tab {

    @Composable
    override fun Content() {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            item {
                PhotoOptionSection()
            }
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    AssistChip(
                        onClick = { Logger.d { "hello world" } },
                        label = {
                            Text(
                                text = "Sort by"
                            )
                        },
                        trailingIcon = {
                            Icon(
                                imageVector = Icons.Filled.ArrowDropDown,
                                contentDescription = null
                            )
                        }
                    )
                }
            }
            item {
                SectionPhoto(
                    title = "Sun 07 May",
                    photos = listOf(
                        "https://picsum.photos/200",
                        "https://picsum.photos/300",
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
            }
            item {
                SectionPhoto(
                    title = "Sun 08 May",
                    photos = listOf(
                        "https://picsum.photos/400",
                        "https://picsum.photos/500",
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
            }
            item {
                Spacer(Modifier.height(32.dp))
            }
        }
    }

    override val options: TabOptions
        @Composable
        get() {
            val icon = rememberVectorPainter(Icons.Rounded.Photo)
            val title = "Photo"
            val index: UShort = 0u

            return TabOptions(
                index, title, icon
            )
        }

}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun PhotoOptionSection(
    modifier: Modifier = Modifier
) {
    FlowRow(
        modifier = modifier.padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        maxItemsInEachRow = 2
    ) {
        PhotoOptionItem(
            icon = Icons.Rounded.Schedule,
            title = "Recent",
            contentColor = Pink,
            iconColor = OnPink,
            modifier = Modifier
                .weight(1f)
        )
        PhotoOptionItem(
            icon = Icons.Rounded.Favorite,
            title = "Favorites",
            contentColor = LightBlue,
            iconColor = OnLightBlue,
            modifier = Modifier
                .weight(1f)
        )
        PhotoOptionItem(
            icon = Icons.Rounded.Explore,
            title = "Location",
            contentColor = Purple,
            iconColor = OnPurple,
            modifier = Modifier
                .weight(1f)
        )
        PhotoOptionItem(
            icon = Icons.Rounded.Person,
            title = "People",
            contentColor = Yellow,
            iconColor = OnYellow,
            modifier = Modifier
                .weight(1f)
        )
    }
}

@Composable
private fun PhotoOptionItem(
    icon: ImageVector,
    title: String,
    contentColor: Color,
    iconColor: Color,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(contentColor)
            .aspectRatio(1f),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = iconColor
            )
            Text(
                text = title,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}
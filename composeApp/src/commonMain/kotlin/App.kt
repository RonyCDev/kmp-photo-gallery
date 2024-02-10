import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import co.touchlab.kermit.Logger
import features.components.HeaderBar
import features.galleries.GalleriesTab
import features.groups.GroupsTab
import features.news.NewsTab
import features.photos.PhotosTab
import shared.presentation.PhotoGalleryTheme

@Composable
fun App(
    darkTheme: Boolean,
    dynamicColor: Boolean,
) {
    PhotoGalleryTheme(
        darkTheme = darkTheme,
        dynamicColor = dynamicColor
    ) {
        TabNavigator(PhotosTab) {
            Scaffold(
                topBar = {
                    HeaderBar(
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                bottomBar = {
                    NavigationBar {
                        TabItem(PhotosTab)
                        TabItem(GalleriesTab)
                        TabItem(GroupsTab)
                        TabItem(NewsTab)
                    }
                }
            ) {
                Box(
                    modifier = Modifier
                        .padding(
                            top = it.calculateTopPadding(),
                            bottom = it.calculateBottomPadding()
                        )
                ) {
                    CurrentTab()
                }
            }
        }
    }
}

@Composable
private fun RowScope.TabItem(tab: Tab) {
    val tabNavigator = LocalTabNavigator.current

    NavigationBarItem(
        selected = tabNavigator.current == tab,
        onClick = {
            Logger.d { "Hello kermit ${tab.key}" }
            tabNavigator.current = tab
        },
        icon = {
            tab.options.icon?.let { painter ->
                Icon(painter, contentDescription = tab.options.title)
            }
        }
    )
}
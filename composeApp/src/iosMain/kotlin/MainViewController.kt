import androidx.compose.ui.window.ComposeUIViewController
import platform.UIKit.UIScreen
import platform.UIKit.UIUserInterfaceStyle.UIUserInterfaceStyleDark

fun MainViewController() = ComposeUIViewController {
    val isDarkTheme =
        UIScreen.mainScreen.traitCollection.userInterfaceStyle == UIUserInterfaceStyleDark
    App(
        darkTheme = isDarkTheme,
        dynamicColor = false
    )
}

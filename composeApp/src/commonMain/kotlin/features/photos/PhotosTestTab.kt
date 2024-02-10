package features.photos

import AlertMessageDialog
import ImageSourceOptionDialog
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Photo
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import shared.PermissionCallback
import shared.PermissionStatus
import shared.PermissionStatus.GRANTED
import shared.PermissionType
import shared.PermissionType.CAMERA
import shared.PermissionType.GALLERY
import shared.createPermissionsManager
import shared.rememberCameraManager
import shared.rememberGalleryManager

object PhotosTestTab : Tab {

    @Composable
    override fun Content() {
        val coroutineScope = rememberCoroutineScope()
        var imageBitmap by remember { mutableStateOf<ImageBitmap?>(null) }
        var imageSourceOptionDialog by remember { mutableStateOf(value = false) }
        var launchCamera by remember { mutableStateOf(value = false) }
        var launchGallery by remember { mutableStateOf(value = false) }
        var launchSetting by remember { mutableStateOf(value = false) }
        var permissionRationalDialog by remember { mutableStateOf(value = false) }
        val permissionsManager = createPermissionsManager(object : PermissionCallback {
            override fun onPermissionStatus(
                permissionType: PermissionType,
                status: PermissionStatus
            ) {
                when (status) {
                    GRANTED -> {
                        when (permissionType) {
                            CAMERA -> launchCamera = true
                            GALLERY -> launchGallery = true
                        }
                    }

                    else -> {
                        permissionRationalDialog = true
                    }
                }
            }
        })

        val cameraManager = rememberCameraManager {
            coroutineScope.launch {
                val bitmap = withContext(Dispatchers.Default) {
                    it?.toImageBitmap()
                }
                imageBitmap = bitmap
            }
        }

        val galleryManager = rememberGalleryManager {
            coroutineScope.launch {
                val bitmap = withContext(Dispatchers.Default) {
                    it?.toImageBitmap()
                }
                imageBitmap = bitmap
            }
        }

        if (imageSourceOptionDialog) {
            ImageSourceOptionDialog(onDismissRequest = {
                imageSourceOptionDialog = false
            }, onGalleryRequest = {
                imageSourceOptionDialog = false
                launchGallery = true
            }, onCameraRequest = {
                imageSourceOptionDialog = false
                launchCamera = true
            })
        }

        if (launchGallery) {
            if (permissionsManager.isPermissionGranted(GALLERY)) {
                galleryManager.launch()
            } else {
                permissionsManager.askPermission(GALLERY)
            }
            launchGallery = false
        }

        if (launchCamera) {
            if (permissionsManager.isPermissionGranted(CAMERA)) {
                cameraManager.launch()
            } else {
                permissionsManager.askPermission(CAMERA)
            }
            launchCamera = false
        }

        if (launchSetting) {
            permissionsManager.launchSettings()
            launchSetting = false
        }

        if (permissionRationalDialog) {
            AlertMessageDialog(
                title = "Permission Required",
                message = "To set your profile picture, please grant this permission. You can manage permissions in your device settings.",
                positiveButtonText = "Settings",
                negativeButtonText = "Cancel",
                onPositiveClick = {
                    permissionRationalDialog = false
                    launchSetting = true
                },
                onNegativeClick = {
                    permissionRationalDialog = false
                }
            )
        }

        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Button(onClick = { imageSourceOptionDialog = true }) {
                Text("Take photo!")
            }
            Box(
                modifier = Modifier.fillMaxSize().background(Color.DarkGray),
                contentAlignment = Alignment.Center
            ) {
                if (imageBitmap != null) {
                    Image(
                        bitmap = imageBitmap!!,
                        contentDescription = "Photo",
                        contentScale = ContentScale.Crop
                    )
                }
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
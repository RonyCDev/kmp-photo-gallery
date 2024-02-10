package features.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HeaderBar(
    title: String = "Photos",
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .height(56.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        FilledTonalIconButton(
            onClick = {

            },
            modifier = Modifier
                .padding(start = 8.dp),
            colors = IconButtonDefaults.filledTonalIconButtonColors(
                containerColor = MaterialTheme.colorScheme.background,
                contentColor = MaterialTheme.colorScheme.onBackground,
            )
        ) {
            Icon(
                imageVector = Icons.Rounded.Search,
                contentDescription = "Search photo",
            )
        }
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge
        )
        FilledTonalIconButton(
            onClick = {

            },
            modifier = Modifier
                .padding(end = 8.dp),
            colors = IconButtonDefaults.filledTonalIconButtonColors(
                containerColor = MaterialTheme.colorScheme.background,
                contentColor = MaterialTheme.colorScheme.onBackground,
            )
        ) {
            Icon(
                imageVector = Icons.Rounded.Person,
                contentDescription = "Photo profile",
                tint = MaterialTheme.colorScheme.onSecondaryContainer,
                modifier = Modifier
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                        shape = RoundedCornerShape(50)
                    )
                    .padding(4.dp)
            )
        }
    }
}
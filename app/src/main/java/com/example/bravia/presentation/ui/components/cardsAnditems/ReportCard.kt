package com.example.bravia.presentation.ui.components.cardsAnditems

import com.example.bravia.domain.model.UserReport
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.bravia.presentation.ui.theme.ThemeDefaults

@Composable
fun ReportCard(
    report: UserReport,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    //initialBookmarked: Boolean = false,
) {
    // Estado para controlar si está marcado como favorito
    //var isBookmarked by remember { mutableStateOf(initialBookmarked) }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = MaterialTheme.shapes.small,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(ThemeDefaults.cardSpacerHeight),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Imagen de la empresa (placeholder)
            Box(
                //TODO: Hacer que sea una foto y si no tiene un color aleatoreo
                modifier = Modifier
                    .size(68.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.secondary),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = report.reporterName,
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSecondary
                )
            }

            Spacer(modifier = Modifier.width(ThemeDefaults.cardSpacerHeight))

            // Información de sel report
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Emisor del reporte: " + report.reporterName,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "Usuario reportado: " + report.reportedUserName,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            //TODO: hacer que se vea el icono de verificado

            // Icono de marcador (bookmark)
            /*IconButton(
                onClick = {
                    isBookmarked = !isBookmarked
                    onBookmarkChange(isBookmarked)
                }
            ) {
                Icon(
                    imageVector = if (isBookmarked) iconA*//*Icons.Default.StarRate*//* else iconB*//*Icons.Default.StarBorder*//*,
                    contentDescription = if (isBookmarked) "Remove bookmark" else "Add bookmark",
                    tint = if (isBookmarked) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
                )
            }*/
        }
    }
}
package com.example.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.R

@Composable
fun ScrapbookCard(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    color: Color = MaterialTheme.colorScheme.surface,
    elevation: Dp = 4.dp,
    shape: Shape = RoundedCornerShape(16.dp),
    content: @Composable () -> Unit
) {
    Surface(
        modifier = modifier
            .shadow(
                elevation = elevation,
                shape = shape,
                spotColor = Color.Black.copy(alpha = 0.05f),
                ambientColor = Color.Black.copy(alpha = 0.02f)
            ),
        shape = shape,
        color = color,
        onClick = onClick ?: {}
    ) {
        Box(modifier = Modifier.padding(16.dp)) {
            content()
        }
    }
}

@Composable
fun PillButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    primary: Boolean = true
) {
    Button(
        onClick = onClick,
        modifier = modifier.clip(CircleShape),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (primary) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.primary,
            contentColor = if (primary) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onPrimary
        ),
        contentPadding = PaddingValues(horizontal = 24.dp, vertical = 12.dp)
    ) {
        if (icon != null) {
            Icon(imageVector = icon, contentDescription = null, modifier = Modifier.size(18.dp))
            Spacer(modifier = Modifier.width(8.dp))
        }
        Text(text, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun ScreenBackground(content: @Composable BoxScope.() -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Optional background texture/pattern could go here (e.g. gingham overlay with low alpha)
        Image(
            painter = painterResource(id = R.drawable.gingham_pattern),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
            alpha = 0.05f // Very subtle overlay
        )
        content()
    }
}

@Composable
fun StickerIcon(
    resourceId: Int,
    modifier: Modifier = Modifier,
    size: Dp = 64.dp
) {
    Image(
        painter = painterResource(id = resourceId),
        contentDescription = "Sticker",
        modifier = modifier
            .size(size)
            .shadow(2.dp, CircleShape)
            .background(Color.White, CircleShape)
            .border(2.dp, Color.White, CircleShape)
            .clip(CircleShape),
        contentScale = ContentScale.Crop
    )
}

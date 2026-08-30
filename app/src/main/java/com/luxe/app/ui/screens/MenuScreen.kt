package com.luxe.app.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.luxe.app.ui.theme.*
import kotlin.math.sin

@Composable
fun MenuScreen() {
    var selectedItem by remember { mutableStateOf(0) }
    val items = listOf("Home", "Profile", "Settings", "About")
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(DarkPurple, Surface)
                )
            )
    ) {
        // Animated wavy background
        WavyBackground()
        
        // Main menu content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Header with luxury title
            Spacer(modifier = Modifier.height(40.dp))
            
            Text(
                text = "LUXE",
                fontSize = 54.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Accent,
                letterSpacing = 8.sp
            )
            
            Text(
                text = "Experience Elegance",
                fontSize = 18.sp,
                fontWeight = FontWeight.Light,
                color = TextSecondary
            )
            
            Spacer(modifier = Modifier.height(40.dp))
            
            // Menu items with beautiful cards
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items.forEachIndexed { index, item ->
                    MenuItemCard(
                        text = item,
                        isSelected = selectedItem == index,
                        onClick = { selectedItem = index }
                    )
                }
            }
            
            Spacer(modifier = Modifier.weight(1f))
            
            // Beautiful button at bottom
            Button(
                onClick = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .clip(RoundedCornerShape(16.dp)),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Accent,
                    contentColor = Color.White
                )
            ) {
                Text(
                    "Start Now",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
            
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Composable
fun MenuItemCard(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val animatedScale by animateFloatAsState(
        targetValue = if (isSelected) 1.05f else 1f,
        animationSpec = spring(dampingRatio = 0.8f, stiffness = 300f)
    )
    
    val animatedAlpha by animateFloatAsState(
        targetValue = if (isSelected) 1f else 0.6f,
        animationSpec = tween(300)
    )
    
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .graphicsLayer(scaleX = animatedScale, scaleY = animatedScale)
            .alpha(animatedAlpha)
            .pointerInput(Unit) {
                detectTapGestures(onTap = { onClick() })
            },
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected) RichPurple else DeepViolet,
            contentColor = TextPrimary
        ),
        shape = RoundedCornerShape(16.dp),
        elevation = ButtonDefaults.elevatedButtonElevation(
            defaultElevation = if (isSelected) 12.dp else 4.dp
        )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = text,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
            if (isSelected) {
                Text(
                    text = "→",
                    fontSize = 22.sp,
                    color = Accent
                )
            }
        }
    }
}

@Composable
fun WavyBackground() {
    val infiniteTransition = rememberInfiniteTransition(label = "wave")
    val wave1Offset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(8000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "wave1"
    )
    
    val wave2Offset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(10000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "wave2"
    )
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .alpha(0.15f)
    ) {
        Canvas(
            modifier = Modifier.fillMaxSize()
        ) { size ->
            val width = size.width
            val height = size.height
            val amplitude = 20f
            val frequency = 0.02f
            
            // Draw wavy lines
            for (x in 0..width.toInt() step 5) {
                val y1 = height / 3 + amplitude * sin((x * frequency + wave1Offset) * Math.PI / 180).toFloat()
                val y2 = height / 2 + amplitude * sin((x * frequency + wave2Offset) * Math.PI / 180).toFloat()
                
                drawCircle(
                    color = RichPurple,
                    radius = 2f,
                    center = androidx.compose.ui.geometry.Offset(x.toFloat(), y1)
                )
                drawCircle(
                    color = Accent,
                    radius = 2f,
                    center = androidx.compose.ui.geometry.Offset(x.toFloat(), y2)
                )
            }
        }
    }
}

import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.foundation.Canvas
import androidx.compose.ui.graphics.graphicsLayer
package com.plcoding.jetpackcomposemasterclass.basic_layout

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.plcoding.jetpackcomposemasterclass.R
import com.plcoding.jetpackcomposemasterclass.ui.theme.JetpackComposeMasterclassTheme

@Composable
fun ProjectX(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
            .clip(RoundedCornerShape(4.dp))
            .background(Color(0xFFE87457))
            .fillMaxWidth()
            .padding(16.dp)


    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .size(24.dp)
                    .clip(CircleShape)
                    .border(
                        width = 1.5.dp, color = Color.White, shape = CircleShape
                    )
                    .padding(2.dp)
            )
            Row {
                Text(text = "Project X",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 34.sp,
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 16.dp)

                )
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.more_horiz),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier
                )
            }
        }
        Text(
            text = """
                The mist curled over the quiet hills, where an old path wound between scattered stones. Somewhere in the distance, a bell tolled, faint but steady, as though counting time in secret. A traveler passed by, carrying nothing but a torn journal filled with half-written notes and sketches of stars. He whispered to himself, unsure if the words were memory or imagination: “Every ending folds into a beginning.” The air smelled faintly of pine and rain, and the silence pressed closer with each step. Shadows shifted, suggesting unseen watchers, but no figure emerged. When he reached the crooked tree at the fork, he paused, staring at the two roads. Both stretched endlessly, one bright, one dim, and the choice lingered heavy in the air like thunder waiting to break.
            """.trimIndent(),
            color = Color.White,
            modifier = Modifier.padding(start = 40.dp)
        )
        Text(
            text = "Mar 5, 10:00",
            color = Color.White,
            modifier = Modifier
                .align(Alignment.End)
        )
    }


}

@Preview
@Composable
private fun ProjectXPreview() {
    JetpackComposeMasterclassTheme {
        ProjectX()
    }
}
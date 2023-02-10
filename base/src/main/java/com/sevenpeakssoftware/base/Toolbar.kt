package com.sevenpeakssoftware.base

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sevenpeakssoftware.base.theme.CarsTheme


@Composable
fun Toolbar() {
    Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                    .background(color = HexToJetpackColor.getColor("212121"))
                    .padding(horizontal = 16.dp)
                    .height(55.dp)
                    .fillMaxWidth(),
          ) {
        Text(text = "Cars", color = Color.White, fontSize = 22.sp, modifier = Modifier)
    }
}

@Preview(uiMode = UI_MODE_NIGHT_NO)
@Composable
fun ToolbarPreview() {
    CarsTheme {
        Toolbar()
    }
}
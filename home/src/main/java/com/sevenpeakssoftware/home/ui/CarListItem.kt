package com.sevenpeakssoftware.home.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import com.sevenpeakssoftware.base.HexToJetpackColor
import com.sevenpeakssoftware.base.toCustomDate
import com.sevenpeakssoftware.domain.model.Car
import com.sevenpeakssoftware.home.BuildConfig
import java.text.SimpleDateFormat
import java.util.*

class SampleUserProvider : PreviewParameterProvider<Car> {
    override val values = sequenceOf(Car())
}

@Composable
@Preview
fun Car(@PreviewParameter(SampleUserProvider::class) car: Car) {
    
    ConstraintLayout(modifier = Modifier
            .fillMaxWidth()
            .height(380.dp)) {
        
        val (image, heading, date, desc) = createRefs()
        
        AsyncImage(modifier = Modifier
                .constrainAs(image) {
                    top.linkTo(parent.top)
                    linkTo(parent.start, parent.end)
                }
                .height(270.dp),
                model = BuildConfig.IMAGE_URL + car.image,
                contentDescription = null,
                contentScale = ContentScale.Crop)
        
        Box(modifier = Modifier
                .fillMaxWidth()
                .height(380.dp)
                .background(Brush.verticalGradient(colorStops = arrayOf(0.4f to HexToJetpackColor.getColor(
                        "00000000"),
                        0.7f to HexToJetpackColor.getColor("d2000000"),
                        0f to HexToJetpackColor.getColor("000000")))))
        
        
        Text(text = car.title,
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .constrainAs(heading) {
                            bottom.linkTo(image.bottom)
                        })
        
        
        
        
        Text(text = car.dateTime.toCustomDate() ?: "",
                color = HexToJetpackColor.getColor("acacac"),
                fontSize = 14.sp,
                modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .constrainAs(date) {
                            linkTo(image.bottom, desc.top)
                        })
        
        Text(text = car.ingress,
                color = Color.White,
                fontSize = 14.sp,
                modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .constrainAs(desc) {
                            bottom.linkTo(parent.bottom, margin = 24.dp)
                        })
        
    }
}
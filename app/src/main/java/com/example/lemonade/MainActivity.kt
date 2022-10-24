package com.example.lemonade

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                LemonApplication()
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun LemonApplication() {
    LemonWithButtonAndImage(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LemonWithButtonAndImage(modifier: Modifier) {

    var result by rememberSaveable {
        mutableStateOf(1)
    }

    val imageResource = when (result) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }

    val textResource = when (result) {
        1 -> R.string.lemon_content_description
        2 -> R.string.lemon_squeeze
        3 -> R.string.lemon_made
        else -> R.string.lemon_empty
    }


    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = stringResource(textResource), fontSize = 18.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Card(border = BorderStroke(2.dp, color = Color(105, 205, 216)),
            onClick = {
                result++
                Log.e("Lemon", "result:${result}")
                if (result > 2) {
                    result = (3..4).random()
                }
                if (result >= 4) {
                    result = 0
                }
            }) {
            Image(painter = painterResource(id = imageResource), contentDescription = "1")
        }
    }
}
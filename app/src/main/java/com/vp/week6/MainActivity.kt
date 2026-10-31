package com.vp.week6

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.vp.week6.navigation.AppNavHost
import com.vp.week6.ui.theme.Week6Theme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Week6Theme {
                AppNavHost()
                }
            }
        }
    }




@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Week6Theme {
        AppNavHost()
    }
}
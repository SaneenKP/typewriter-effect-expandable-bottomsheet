package com.example.typewritereffect

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.typewritereffect.ui.theme.TypeWriterEffectTheme
import kotlinx.coroutines.delay
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TypeWriterEffectTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(){
    ModalBottomSheet(onDismissRequest = {  }) {
        TypeWriterEffectScreen()
    }
}
@Composable
fun TypeWriterEffectScreen(){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {

        TypeWriterText(
            inputText = "LLorem Ipsum is simply dummy text of the printinLorem Ipsum is simply dummy text of the printinLorem Ipsum is simply dummy text of the printinLorem Ipsum is simply dummy text of the printinLorem Ipsum is simply dummy text of the printinLorem Ipsum is simply dummy text of the printinLorem Ipsum is simply dummy text of the printinLorem Ipsum is simply dummy text of the printinLorem Ipsum is simply dummy text of the printinLorem Ipsum is simply dummy text of the printinLorem Ipsum is simply dummy text of the printinLorem Ipsum is simply dummy text of the printinLorem Ipsum is simply dummy text of the printinLorem Ipsum is simply dummy text of the printinLorem Ipsum is simply dummy text of the printinLorem Ipsum is simply dummy text of the printinLorem Ipsum is simply dummy text of the printinLorem Ipsum is simply dummy text of the printinLorem Ipsum is simply dummy text of the printinLorem Ipsum is simply dummy text of the printinLorem Ipsum is simply dummy text of the printinLorem Ipsum is simply dummy text of the printinLorem Ipsum is simply dummy text of the printinLorem Ipsum is simply dummy text of the printinLorem Ipsum is simply dummy text of the printinLorem Ipsum is simply dummy text of the printinLorem Ipsum is simply dummy text of the printinLorem Ipsum is simply dummy text of the printinLorem Ipsum is simply dummy text of the printinorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.\n" +
                    "\n",
            textComposable = {displayText ->
                Text(
                    text = displayText,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp
                )
            },
            minCharacterChunk = 1,
            maxCharacterChunk = 13,
            minDelayInMillis = 50,
            maxDelayInMillis = 150,
        )
    }
}

@Composable
fun TypeWriterText(
    inputText : String,
    minDelayInMillis: Long = 10,
    maxDelayInMillis: Long = 50,
    minCharacterChunk: Int = 1,
    maxCharacterChunk: Int = 5,
    textComposable : @Composable (finalText : String) -> Unit,
    onEffectCompleted: () -> Unit = {},
) {
    var finalText by remember { mutableStateOf("") }
    textComposable(finalText)

    LaunchedEffect(key1 = inputText, block = {
        val textLength = inputText.length
        var endIndex = 0

        while (endIndex < textLength){
            endIndex = minOf(
                endIndex + Random.nextInt(minCharacterChunk, maxCharacterChunk+1),
                textLength
            )
            finalText = inputText.substring(startIndex = 0 , endIndex = endIndex)
            delay(Random.nextLong(minDelayInMillis, maxDelayInMillis))
        }
        onEffectCompleted()
    })
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TypeWriterEffectTheme {
        MainScreen()
    }
}
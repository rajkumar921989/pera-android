import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.algorand.common.ui.theme.PeraTheme
import com.algorand.common.ui.widgets.BlackButtonWidget

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GridFormScreen() {

    // State for holding text inputs
    val inputs = remember { mutableStateListOf<String>().apply { repeat(25) { add("") } } }

    Scaffold()
    { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Text(
                text = "Enter Your Recovery Passphrase",
                fontSize = 20.sp,
                style = PeraTheme.typography.body.regular.sansBold
            )
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .weight(1f)
                    .padding(bottom = 10.dp)
            ) {
                items(25) { index ->
                    GridItem(index + 1, inputs[index]) { updatedValue ->
                        inputs[index] = updatedValue
                    }
                }
            }
            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(Color.LightGray),
                modifier =
                Modifier.fillMaxWidth().padding(start = 10.dp, end = 10.dp, top = 10.dp)
            ) {
                Text(
                    text = "Recover",
                    style = PeraTheme.typography.body.regular.sans,
                    color = Color.White
                )
            }
        }

    }

}

@ExperimentalMaterial3Api
@Composable
fun GridItem(label: Int, text: String, onTextChange: (String) -> Unit) {
    Column(
        modifier = Modifier
            .padding(1.dp),

        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row {
            Text(
                text = label.toString(),
                fontSize = 16.sp,
                color = Color.Black,
            )

            // Input Text Field
            TextField(
                modifier = Modifier.height(50.dp),
                value = text,
                onValueChange = { onTextChange(it) },
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent, // Removes background
                    focusedIndicatorColor = Color.Blue, // Bottom line when focused
                    unfocusedIndicatorColor = Color.Gray // Bottom line when not focused
                ),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
            )
        }


    }

}

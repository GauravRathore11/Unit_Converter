package com.example.unitconverter

import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverter()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun UnitConverter(){
    var input by remember { mutableStateOf("") }
    var output by remember { mutableStateOf("") }
    var inputUnit by remember { mutableStateOf("Centimeter") }
    var outputUnit by remember { mutableStateOf("Meter") }
    var iExpanded by remember { mutableStateOf(false) }
    var oExpanded by remember { mutableStateOf(false) }
    val conversionFactor = remember { mutableStateOf(0.01) }
    val outputFactor = remember { mutableStateOf(1.0) }

    fun unitConverter(){
        //firstly convert your input string to double
        val inputDouble = input.toDouble() ?: 0.0
        output = ((((conversionFactor.value*inputDouble)*outputFactor.value)).setScale(2)).toString()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Text(text = "Unit Converter")
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = input,
            onValueChange = {
            input=it
                unitConverter()
        },
            label = { Text(text = "Enter value") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row {

            //input
            Box{
                Button(onClick = {
                    iExpanded=true
                }) {
                    Text(text = inputUnit)
                    Icon(Icons.Default.ArrowDropDown,contentDescription = null)
                }
                DropdownMenu(expanded = iExpanded, onDismissRequest = { iExpanded=false }) {
                    DropdownMenuItem(
                        text = { Text(text = "Centimeter") },
                        onClick = {
                            iExpanded=false
                            inputUnit="Centimeter"
                            conversionFactor.value=0.01
                            unitConverter()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Meter") },
                        onClick = {
                            iExpanded=false
                            inputUnit="Meter"
                            conversionFactor.value=1.0
                            unitConverter()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Feet") },
                        onClick = {
                            iExpanded=false
                            inputUnit="Feet"
                            conversionFactor.value=0.3048
                            unitConverter()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Millimeter") },
                        onClick = {
                            iExpanded=false
                            inputUnit="Millimeter"
                            conversionFactor.value=0.001
                            unitConverter()
                        }
                    )
                }
            }


            Spacer(modifier = Modifier.width(16.dp))


            //output
            Box{
                Button(onClick = { oExpanded=true }) {
                    Text(text = outputUnit)
                    Icon(Icons.Default.ArrowDropDown,contentDescription = null)
                }
                DropdownMenu(expanded = oExpanded, onDismissRequest = { oExpanded=false }) {

                    DropdownMenuItem(
                        text = { Text(text = "Centimeter") },
                        onClick = {
                            oExpanded=false
                            outputUnit="Centimeter"
                            outputFactor.value=100.0
                            unitConverter()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Meter") },
                        onClick = {
                            oExpanded=false
                            outputUnit="Meter"
                            outputFactor.value=1.0
                            unitConverter()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Feet") },
                        onClick = {
                            oExpanded=false
                            outputUnit="Feet"
                            outputFactor.value=3.28
                            unitConverter()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Millimeter") },
                        onClick = {
                            oExpanded=false
                            outputUnit="Millimeter"
                            outputFactor.value=1000.0
                            unitConverter()
                        }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Result : $output $outputUnit")
    }
}

@Preview(showBackground = true)
@Composable
fun UnitConverterPreview(){
    UnitConverter()
}
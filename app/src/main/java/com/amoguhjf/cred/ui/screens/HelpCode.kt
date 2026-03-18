package com.amoguhjf.cred.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.amoguhjf.cred.R
import kotlinx.serialization.Serializable

@Serializable
sealed interface RootScreen {

    @Serializable
    data object Splash : RootScreen

    @Serializable
    data object Main : RootScreen

     @Serializable
    data object Grafik : RootScreen

     @Serializable
    data object Notifications : RootScreen
}

//------------------------------------------

@Composable
fun EditField(name : String, text : String, onText: (String) -> Unit, placeholderText : String){
    Text(text = name, fontSize = 16.sp, color = colorResource(R.color.darkblue),
        fontFamily = FontFamily(Font(R.font.interregular)),
        modifier = Modifier.fillMaxWidth()
    )
    Spacer(modifier = Modifier.padding(top = 4.dp))
    OutlinedTextField(value = text,  onValueChange = { newValue -> onText(newValue) },
        placeholder = { Text(placeholderText, color = colorResource(R.color.green)) },
        textStyle = TextStyle(color = colorResource(R.color.darkblue), fontSize = 16.sp),
        modifier = Modifier.fillMaxWidth().background(colorResource(R.color.white), RoundedCornerShape(10.dp)),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = colorResource(R.color.darkblue),
            unfocusedBorderColor = colorResource(R.color.darkblue),
            cursorColor = colorResource(R.color.darkblue),
            placeholderColor = colorResource(R.color.darkblue),
            textColor = colorResource(R.color.darkblue)
        ),
        shape = RoundedCornerShape(10.dp)
    )
}

@Composable
fun EditFieldNumbers(name : String, text : String, onText: (String) -> Unit, placeholderText : String){
    val context = LocalContext.current

    Text(text = name, fontSize = 16.sp, color = colorResource(R.color.darkblue),
        fontFamily = FontFamily(Font(R.font.interregular)),
        modifier = Modifier.fillMaxWidth()
    )
    Spacer(modifier = Modifier.padding(top = 4.dp))
    OutlinedTextField(value = text, onValueChange = { newValue ->
            if (newValue.length <= 10) {
                if (newValue.isNotEmpty()) {
                    if (newValue.first().isDigit()) {
                        onText(newValue)
                    } else {
                        if (text.isEmpty()) {
                            Toast.makeText(context, "First character must be a digit", Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    onText(newValue)
                }
            } else {
                Toast.makeText(context, "Maximum length of $10 characters reached", Toast.LENGTH_SHORT).show()
            }
        },
        placeholder = { Text(placeholderText, color = colorResource(R.color.green)) },
        textStyle = TextStyle(color = colorResource(R.color.darkblue), fontSize = 20.sp),
        modifier = Modifier.fillMaxWidth().background(colorResource(R.color.white), RoundedCornerShape(10.dp)),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = colorResource(R.color.darkblue),
            unfocusedBorderColor = colorResource(R.color.darkblue),
            cursorColor = colorResource(R.color.darkblue),
            placeholderColor = colorResource(R.color.darkblue),
            textColor = colorResource(R.color.darkblue)
        ),
        shape = RoundedCornerShape(10.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}

//@Composable
//fun MenuDown(list: List<String>, isOpen: Boolean, title: String, onChoisedText: (String) -> Unit, choisedtext: String = "") {
//    var selectedEventIndex by remember { mutableStateOf(-1) }
//    val lengths = list
//    var length by remember { mutableStateOf(choisedtext) }
//    var isOpenConditions by remember { mutableStateOf(isOpen) }
//
//    val animationDuration = 300
//    val enterTransition = remember {
//        fadeIn(animationSpec = tween(animationDuration)) + expandVertically(
//            animationSpec = tween(
//                animationDuration
//            ), expandFrom = Alignment.Top
//        )
//    }
//    val exitTransition = remember {
//        fadeOut(animationSpec = tween(animationDuration)) + shrinkVertically(
//            animationSpec = tween(
//                animationDuration
//            ), shrinkTowards = Alignment.Top
//        )
//    }
//
//    Column(modifier = Modifier.fillMaxWidth().wrapContentHeight(), horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Top) {
//        Text(text = title, fontSize = 16.sp, color = colorResource(R.color.white), fontWeight = FontWeight.Bold,
//            fontFamily = FontFamily(Font(R.font.opensansregular)),
//            modifier = Modifier.fillMaxWidth()
//        )
//        Spacer(modifier = Modifier.padding(top = 4.dp))
//        Row(modifier = Modifier.fillMaxWidth().height(50.dp).background(
//                    colorResource(R.color.itemfon), RoundedCornerShape(10.dp)),
//            verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
//            Text(text = if (selectedEventIndex == -1) length else lengths[selectedEventIndex], fontSize = 16.sp,
//                color = colorResource(if (selectedEventIndex == -1) R.color.white else R.color.white),
//                fontFamily = FontFamily(Font(R.font.opensansregular)),
//                fontWeight = FontWeight.Bold, modifier = Modifier.offset(x = 16.dp)
//            )
//            Image(painter = painterResource(if (isOpenConditions) R.drawable.arrowup else R.drawable.arrowdown),
//                contentDescription = "arrow", modifier = Modifier.size(24.dp).offset(x = -16.dp).clickable {
//                    isOpenConditions = !isOpenConditions
//                }, contentScale = ContentScale.FillBounds
//            )
//        }
//        Spacer(modifier = Modifier.padding(top = 4.dp))
//        AnimatedVisibility(visible = isOpenConditions, enter = enterTransition, exit = exitTransition) {
//            Column(modifier = Modifier.fillMaxWidth().wrapContentHeight().background(
//                        colorResource(R.color.itemfon), RoundedCornerShape(10.dp)).padding(vertical = 4.dp),
//                horizontalAlignment = Alignment.Start, verticalArrangement = Arrangement.SpaceEvenly) {
//                lengths.forEachIndexed { index, event ->
//                    Text(text = event, fontSize = 14.sp, color = colorResource(R.color.white),
//                        fontFamily = FontFamily(Font(R.font.opensansregular)),
//                        fontWeight = if (selectedEventIndex == index) FontWeight.Bold else FontWeight.Normal,
//                        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp).clickable {
//                            selectedEventIndex = index
//                            isOpenConditions = !isOpenConditions
//                            onChoisedText(event)
//                        })
//                }
//            }
//        }
//    }
//}
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun DatePicker(text2: String, onDateChange: (String) -> Unit) {
//    var showDatePicker by remember { mutableStateOf(false) }
//    val datePickerState = rememberDatePickerState()
//
//   if (showDatePicker) {
//         DatePickerDialog(
//            onDismissRequest = { showDatePicker = false },
//            confirmButton = {
//                TextButton(onClick = {
//                    val selectedDate = datePickerState.selectedDateMillis
//                   if (selectedDate != null) {
//                            val sdf = SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH)
//                            sdf.timeZone = TimeZone.getTimeZone("UTC")
//                            val formattedDate = sdf.format(Date(selectedDate))
//                            onDateChange(formattedDate)
//                        }
//                    showDatePicker = false
//                }, colors = ButtonDefaults.textButtonColors(
//                        contentColor = colorResource(R.color.black))) {
//                    Text(text = "OK", fontSize = 20.sp)
//                }
//            },
//            dismissButton = {
//                TextButton(onClick = { showDatePicker = false },
//                     colors = ButtonDefaults.textButtonColors(
//                        contentColor = colorResource(R.color.black))) {
//                    Text(text = "Cancel", fontSize = 20.sp)
//                }
//            }
//        ) {
//             DatePicker(state = datePickerState,
//                 colors = DatePickerDefaults.colors(
//                     containerColor = colorResource(R.color.tryellow),
//                     titleContentColor = colorResource(R.color.black),
//                     headlineContentColor = colorResource(R.color.black),
//                     weekdayContentColor = colorResource(R.color.black),
//                     subheadContentColor = colorResource(R.color.black),
//                     navigationContentColor = colorResource(R.color.black),
//                     yearContentColor = colorResource(R.color.black),
//                     currentYearContentColor = colorResource(R.color.black),
//                     selectedYearContentColor = colorResource(R.color.white),
//                     selectedYearContainerColor = colorResource(R.color.gray),
//                     dayContentColor = colorResource(R.color.black),
//                     disabledDayContentColor = colorResource(R.color.gray),
//                     selectedDayContentColor = colorResource(R.color.white),
//                     selectedDayContainerColor = colorResource(R.color.gray),
//                     todayContentColor = colorResource(R.color.red),
//                     todayDateBorderColor = colorResource(R.color.red),
//                     dayInSelectionRangeContentColor = colorResource(R.color.white),
//                     dayInSelectionRangeContainerColor = colorResource(R.color.black),
//                     dividerColor = colorResource(R.color.gray)
//                 ))
//        }
//    }
//
//    Box(modifier = Modifier.fillMaxWidth().height(56.dp)
//            .background(Color.Transparent, RoundedCornerShape(10.dp))
//            .border(1.dp, colorResource(R.color.gray), RoundedCornerShape(10.dp)),
//        contentAlignment = Alignment.CenterStart) {
//        Text(text = if (text2.isEmpty()) "Match Date" else text2, fontSize = 14.sp,
//            color = if (text2.isEmpty()) colorResource(R.color.white) else Color.White,
//            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp).clickable { showDatePicker = true })
//    }
//}


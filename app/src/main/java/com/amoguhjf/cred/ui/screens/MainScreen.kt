package com.amoguhjf.cred.ui.screens

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.room.Room
import com.amoguhjf.cred.R
import com.amoguhjf.cred.database.AppDatabase
import com.amoguhjf.cred.database.Credit
import kotlinx.coroutines.launch

@Composable
fun MainScreen(navController: NavController){
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val db = remember { Room.databaseBuilder(context, AppDatabase::class.java, "database").build() }
    val listCredits by db.creditDao().getAll().collectAsState(initial= emptyList())

    var showDialog = remember { mutableStateOf(false) }
    var showDialogDelete = remember { mutableStateOf(false) }
    var id by remember { mutableStateOf(0) }
    var finorg by remember { mutableStateOf("") }
    var currency by remember { mutableStateOf("$") }
    var creditamount by remember { mutableStateOf(0) }
    var percentagerate by remember { mutableStateOf(0) }
    var creditperiod by remember { mutableStateOf(0) }
    val currencies = listOf(
        "$",
        "€",
        "£",
        "¥",
        "₽",
        "₴",
        "₣",
        "₹",
        "฿",
        "₫",
        "₩",
        "₪",
        "₫",
        "₼",
        "₾",
        "₸",
        "₺",
        "R$",
        "A$",
        "C$",
        "NZ$",
        "S$",
        "HK$",
        "kr",
        "kn",
        "le",
        "Kč",
        "Ft",
        "zł",
        "Rp"
    )


    val animationDuration = 300
    val enterTransition = remember {
         fadeIn(animationSpec = tween(animationDuration)) + expandVertically(animationSpec = tween(animationDuration), expandFrom = Alignment.Top)
    }
    val exitTransition = remember {
        fadeOut(animationSpec = tween(animationDuration)) + shrinkVertically(animationSpec = tween(animationDuration), shrinkTowards = Alignment.Top)
    }

     Column(modifier = Modifier.fillMaxSize()
        .background(Brush.verticalGradient(listOf(
            colorResource(R.color.white), colorResource(R.color.green)))).systemBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Top){
         Spacer(modifier = Modifier.height(16.dp))
         Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
             verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.End){
             Text(text = "Your credits", fontSize = 20.sp, color = colorResource(R.color.darkblue),
                 fontWeight = FontWeight.Bold, fontFamily = FontFamily(Font(R.font.interregular))
             )
             Spacer(modifier = Modifier.width(100.dp))
             Image(painter = painterResource(R.drawable.iconnotifications), contentDescription = "iconnotifications",
                  modifier = Modifier.size(30.dp).clickable {
                      navController.navigate(RootScreen.Notifications){
                          popUpTo(0){inclusive = true}
                          launchSingleTop = true
                      }
                  }, contentScale = ContentScale.FillBounds
             )
         }
         Spacer(modifier = Modifier.height(8.dp))
         Divider(modifier = Modifier.fillMaxWidth().height(1.dp).padding(horizontal = 8.dp), colorResource(R.color.black))
         Spacer(modifier = Modifier.height(8.dp))
         AnimatedVisibility(visible = listCredits.isNotEmpty(),  enter = enterTransition, exit = exitTransition ) {
             Box(modifier = Modifier.fillMaxWidth().weight(1f), contentAlignment = Alignment.BottomEnd){
             LazyColumn(modifier = Modifier.fillMaxSize()) {
                 items(listCredits) { item ->
                    Card(modifier = Modifier.fillMaxWidth().height(200.dp).padding(horizontal = 16.dp, vertical = 4.dp),
                        elevation = 4.dp, backgroundColor = colorResource(R.color.white),
                        shape = RoundedCornerShape(10.dp)){
                        Column(modifier = Modifier.fillMaxSize()
                            .background(Brush.verticalGradient(listOf(
                                colorResource(R.color.lightblue), colorResource(R.color.white)
                            )), RoundedCornerShape(10.dp)).padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Top){
                            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween){
                                Text(text = "${item.finorg}", fontSize = 20.sp, color = colorResource(R.color.broun),
                                    fontWeight = FontWeight.Bold, fontFamily = FontFamily(Font(R.font.interregular))
                                )
                                Image(painter = painterResource(R.drawable.iconedit), contentDescription = "iconedit",
                                     modifier = Modifier.size(16.dp).clip(RoundedCornerShape(4.dp)).clickable {
                                         showDialog.value = true
                                         id = item.id
                                         finorg = item.finorg
                                         currency = item.currency
                                         creditamount = item.creditamount.toInt()
                                         percentagerate = item.percentagerate.toInt()
                                         creditperiod = item.creditperiod.toInt()
                                     }, contentScale = ContentScale.FillBounds
                                )
                            }
                            
                            Spacer(modifier = Modifier.height(4.dp))
                            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween){
                            Text(text = buildAnnotatedString {
                                    withStyle(style = SpanStyle(color = colorResource(R.color.darkgreen), fontSize = 14.sp)) { append("Monthly payment: ") }
                                    withStyle(style = SpanStyle(color = colorResource(R.color.darkblue), fontWeight = FontWeight.Bold, fontSize = 16.sp)) { append("${item.permonthpay} ${item.currency}") }
                                }, fontFamily = FontFamily(Font(R.font.interregular))
                            )
                                Image(painter = painterResource(R.drawable.icondelete), contentDescription = "icondelete",
                                     modifier = Modifier.size(20.dp).clickable {
                                         showDialogDelete.value = true
                                     }, contentScale = ContentScale.FillBounds
                                )
                                if (showDialogDelete.value) {
            AlertDialog(
                onDismissRequest = { showDialogDelete.value = false },
                containerColor = colorResource(id = R.color.white),
                text = {
                    Column(modifier = Modifier.fillMaxWidth().wrapContentHeight(), horizontalAlignment = Alignment.CenterHorizontally,
                       verticalArrangement = Arrangement.Top){
                       Spacer(modifier = Modifier.height(8.dp))
                       Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
                           verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.End){
                           Image(painter = painterResource(R.drawable.baseline_close_24), contentDescription = "iconclose",
                                modifier = Modifier.size(20.dp).clickable {
                                    showDialogDelete.value = false
                                }, contentScale = ContentScale.FillBounds
                           )
                       }
                       Spacer(modifier = Modifier.height(8.dp))
                       Text(text = "Do you really want to delete this?", fontSize = 20.sp, color = colorResource(R.color.darkblue),
                          fontFamily = FontFamily(Font(R.font.interregular)),
                           modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center
                       )
                        Spacer(modifier = Modifier.height(16.dp))
                         Box(modifier = Modifier.fillMaxWidth().height(60.dp)
                           .border(1.dp, colorResource(R.color.darkblue), RoundedCornerShape(16.dp))
                           .background(Brush.horizontalGradient(listOf(colorResource(R.color.white), colorResource(R.color.green))), RoundedCornerShape(16.dp)).clickable {
                                 scope.launch { db.creditDao().delete(item) }
                                 showDialogDelete.value = false
                           }, contentAlignment = Alignment.Center) {
                           Text(text = "Delete", fontSize = 20.sp, color = colorResource(R.color.darkblue),
                               fontFamily = FontFamily(Font(R.font.interregular))
                           )
                       }
                    }
                },
                confirmButton = {},
                dismissButton = {})
        }
                            }
                            Spacer(modifier = Modifier.height(4.dp))
                             Text(text = buildAnnotatedString {
                                    withStyle(style = SpanStyle(color = colorResource(R.color.darkgreen), fontSize = 14.sp)) { append("Credit amount: ") }
                                    withStyle(style = SpanStyle(color = colorResource(R.color.darkblue), fontWeight = FontWeight.Bold, fontSize = 16.sp)) { append("${item.creditamount} ${item.currency}") }
                                }, fontFamily = FontFamily(Font(R.font.interregular)), modifier = Modifier.fillMaxWidth()
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                             Text(text = buildAnnotatedString {
                                    withStyle(style = SpanStyle(color = colorResource(R.color.darkgreen), fontSize = 14.sp)) { append("Overpayment: ") }
                                    withStyle(style = SpanStyle(color = colorResource(R.color.darkblue), fontWeight = FontWeight.Bold, fontSize = 16.sp)) { append("${item.pereplata} ${item.currency}") }
                                }, fontFamily = FontFamily(Font(R.font.interregular)), modifier = Modifier.fillMaxWidth()
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                             Text(text = buildAnnotatedString {
                                    withStyle(style = SpanStyle(color = colorResource(R.color.darkgreen), fontSize = 14.sp)) { append("Total amount: ") }
                                    withStyle(style = SpanStyle(color = colorResource(R.color.darkblue), fontWeight = FontWeight.Bold, fontSize = 16.sp)) { append("${item.allamount} ${item.currency}") }
                                }, fontFamily = FontFamily(Font(R.font.interregular)), modifier = Modifier.fillMaxWidth()
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Divider(modifier = Modifier.fillMaxWidth().height(1.dp), colorResource(R.color.gray))
                            Spacer(modifier = Modifier.height(8.dp))
                            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween){
                                Text(text = "credit date: ${item.creditdate}", fontSize = 12.sp, color = colorResource(R.color.broun),
                                    fontWeight = FontWeight.Bold, fontFamily = FontFamily(Font(R.font.interregular))
                                )
                                Row(verticalAlignment = Alignment.CenterVertically){
                                     Image(painter = painterResource(R.drawable.iconnotifications), contentDescription = "iconnotifications",
                                         modifier = Modifier.size(24.dp).clip(RoundedCornerShape(4.dp)).clickable {
                                             //
                                         }, contentScale = ContentScale.FillBounds
                                    )
                                    Spacer(modifier = Modifier.width(16.dp))
                                    Image(painter = painterResource(R.drawable.icongrafic), contentDescription = "icongrafic",
                                         modifier = Modifier.width(30.dp).height(24.dp).clickable {
                                             //
                                         }, contentScale = ContentScale.FillBounds
                                    )

                                }
                            }
                        }
                    }
                 }
             }
                 Image(painter = painterResource(R.drawable.fab), contentDescription = "fab",
                      modifier = Modifier.size(60.dp).offset(x= - 24.dp, y = -24.dp).clip(RoundedCornerShape(30.dp)).clickable {
                          showDialog.value = true
                      }, contentScale = ContentScale.FillBounds
                 )
             }
         }
         AnimatedVisibility(visible = listCredits.isEmpty(),  enter = enterTransition, exit = exitTransition ) {
             Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally,
                 verticalArrangement = Arrangement.Center){
                 Text(text = "You don't have any credits yet", fontSize = 22.sp, color = colorResource(R.color.gray),
                     fontWeight = FontWeight.Bold, fontFamily = FontFamily(Font(R.font.interregular))
                 )
                 Spacer(modifier = Modifier.height(80.dp))
                 Box(modifier = Modifier.fillMaxWidth().height(60.dp).padding(horizontal = 24.dp)
                     .border(1.dp, colorResource(R.color.darkblue), RoundedCornerShape(16.dp))
                     .background(Brush.horizontalGradient(listOf(
                         colorResource(R.color.white), colorResource(R.color.green)
                     )), RoundedCornerShape(16.dp)).clickable{
                         showDialog.value = true
                     }, contentAlignment = Alignment.Center){
                     Text(text = "Add first credit", fontSize = 20.sp, color = colorResource(R.color.darkblue),
                         fontWeight = FontWeight.Bold, fontFamily = FontFamily(Font(R.font.interregular))
                     )
                 }
             }
         }
         if (showDialog.value) {
            AlertDialog(
                onDismissRequest = { showDialog.value = false },
                containerColor = colorResource(id = R.color.white),
                text = {
                   Column(modifier = Modifier.fillMaxWidth().wrapContentHeight(), horizontalAlignment = Alignment.CenterHorizontally,
                       verticalArrangement = Arrangement.Top){
                       Spacer(modifier = Modifier.height(8.dp))
                       Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
                           verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.End){
                           Image(painter = painterResource(R.drawable.baseline_close_24), contentDescription = "iconclose",
                                modifier = Modifier.size(20.dp).clickable {
                                    showDialog.value = false
                                }, contentScale = ContentScale.FillBounds
                           )
                       }
                       Spacer(modifier = Modifier.height(8.dp))
                       Text(text = "Currency", fontSize = 16.sp, color = colorResource(R.color.darkblue),
                          fontFamily = FontFamily(Font(R.font.interregular)),
                           modifier = Modifier.fillMaxWidth()
                       )
                       Spacer(modifier = Modifier.height(4.dp))
                       LazyRow(modifier = Modifier.fillMaxWidth().height(40.dp)){
                           itemsIndexed(currencies) { index, item ->
                               val item = currencies[index]
                               val isSelected = currency == item
                               Box(modifier = Modifier.size(50.dp).padding(horizontal = 1.dp).background(if (isSelected) colorResource(R.color.darkblue)
                                           else colorResource(R.color.white), shape = RoundedCornerShape(12.dp)
                                       ).border(width = 1.dp, color = colorResource(R.color.darkblue),
                                           shape = RoundedCornerShape(12.dp)).clickable {
                                           currency = item
                                       }, contentAlignment = Alignment.Center) {
                                   Text(text = item, fontSize = 18.sp, fontWeight = FontWeight.Bold,
                                       color = if (isSelected) Color.White else colorResource(R.color.green),
                                       fontFamily = FontFamily(Font(R.font.interregular))
                                   )
                               }
                           }
                       }
                       Spacer(modifier = Modifier.height(8.dp))
                       EditField("Financial organization", finorg, {finorg = it}, "Financial organization..")
                       Spacer(modifier = Modifier.height(8.dp))
                       EditFieldNumbers("Credit amount", creditamount.toString(), {creditamount = it.toIntOrNull() ?: 0}, "Credit amount..")
                       Spacer(modifier = Modifier.height(8.dp))
                       EditFieldNumbers("Percentage rate", percentagerate.toString(), {percentagerate = it.toIntOrNull() ?: 0}, "Percentage rate..")
                       Spacer(modifier = Modifier.height(8.dp))
                       EditFieldNumbers("Credit period", creditperiod.toString(), {creditperiod = it.toIntOrNull() ?: 0}, "Credit period..")
                       Spacer(modifier = Modifier.height(16.dp))
                       Box(modifier = Modifier.fillMaxWidth().height(60.dp)
                           .border(1.dp, colorResource(R.color.darkblue), RoundedCornerShape(16.dp))
                           .background(Brush.horizontalGradient(listOf(colorResource(R.color.white), colorResource(R.color.green))), RoundedCornerShape(16.dp)).clickable {
                                   scope.launch {
                                       if(finorg.isNotEmpty() && currency.isNotEmpty() && creditamount != 0 && percentagerate != 0 && creditperiod != 0){
                                           val monthlyRate = (percentagerate.toDouble() / 100) / 12
                                           val commonFactor = Math.pow(1 + monthlyRate, creditperiod.toDouble())
                                           val perMonthPayDouble = if (monthlyRate > 0) {
                                               creditamount.toDouble() * (monthlyRate * commonFactor) / (commonFactor - 1)
                                           } else {
                                               creditamount.toDouble() / creditperiod
                                           }
                                           val totalAmountDouble = perMonthPayDouble * creditperiod
                                           val overpaymentDouble = totalAmountDouble - creditamount.toDouble()
                                           val sdf = java.text.SimpleDateFormat("dd.MM.yyyy", java.util.Locale.getDefault())
                                           val currentDate = sdf.format(java.util.Date())

                                           db.creditDao().upsert(Credit(id = id, currency = currency, creditamount = creditamount.toString(),
                                               percentagerate = percentagerate.toString(), creditperiod = creditperiod.toString(),
                                               permonthpay = "%.2f".format(perMonthPayDouble), pereplata = "%.2f".format(overpaymentDouble),
                                               allamount = "%.2f".format(totalAmountDouble), finorg = finorg, creditdate = currentDate))
                                           showDialog.value = false
                                           creditamount = 0
                                           percentagerate = 0
                                           creditperiod = 0
                                       } else {
                                           Toast.makeText(context, "Fill all fields!", Toast.LENGTH_SHORT).show()
                                       }
                                   }
                           }, contentAlignment = Alignment.Center) {
                           Text(text = "Save", fontSize = 20.sp, color = colorResource(R.color.darkblue),
                               fontFamily = FontFamily(Font(R.font.interregular))
                           )
                       }
                       Spacer(modifier = Modifier.height(8.dp))
                   }
                },
                confirmButton = {},
                dismissButton = {})
        }
         Spacer(modifier = Modifier.height(8.dp))
     }
}

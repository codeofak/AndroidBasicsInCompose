package com.example.androidbasicsincompose

import android.content.Context
import android.telephony.TelephonyManager
import androidx.annotation.ReturnThis
import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import com.example.androidbasicsincompose.ui.theme.AndroidBasicsInComposeTheme
import java.security.AccessController.getContext
import java.security.Provider
import java.text.NumberFormat
import java.util.*
import kotlin.math.ceil

@Composable
fun TipTimeScreen() {

    val context = LocalContext.current

    var amountInput by remember { mutableStateOf(value = "") }

    val amount = amountInput.toDoubleOrNull() ?: 0.0


    var percentage by remember { mutableStateOf("") }

    var tipPercentage = percentage.toDoubleOrNull() ?: 0.0

    var roundUp by remember { mutableStateOf(false) }

    val tip = calculateTip(
        amount = amount,
        tipPercent = tipPercentage,
        roundUp = roundUp
    )

    val focusManager = LocalFocusManager.current


    Column(
        modifier = Modifier.padding(32.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        Text(
            text = stringResource(id = R.string.calculate_tip),
            fontSize = 24.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(16.dp))


        //Bill Amount TextField
        EditNumberField(
            value = amountInput,
            onValueChange = { amountInput = it },
            label = stringResource(id = R.string.bill_amount),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            )
        )

        Spacer(modifier = Modifier.height(24.dp))

        //Tip Percentage TextField
        EditNumberField(
            value = percentage,
            onValueChange = { percentage = it },
            label = stringResource(id = R.string.how_was_the_service),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = { focusManager.clearFocus() },
            )
        )

        RoundTipRow(
            roundUp = roundUp,
            onRoundUpChange = { roundUp = it }
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.padding(5.dp),
            horizontalArrangement = Arrangement.spacedBy(1.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "Clear All",
                fontStyle = FontStyle.Italic,
                modifier = Modifier.clickable {
                    amountInput = ""
                    percentage = ""
                },
                style = TextStyle(textDecoration = TextDecoration.Underline)
            )

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = stringResource(id = R.string.tip_amount, tip),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }

//        val local = context.resources.configuration
//
//
//        Text(text = local.screenHeightDp.toString())

    }
}


@Composable
fun EditNumberField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String = stringResource(id = R.string.bill_amount),
    keyboardOptions: KeyboardOptions,
    keyboardActions: KeyboardActions
) {


    val focusManager = LocalFocusManager.current

    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label, color = Color.White) },
        shape = RoundedCornerShape(5.dp),
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Gray),
        singleLine = true,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions

    )


//    LaunchedEffect(Unit) {
//        while (true) {
//            delay(3000)
//            focusManager.clearFocus()
//        }
//    }
}

@VisibleForTesting
internal fun calculateTip(
    amount: Double,
    tipPercent: Double,
    roundUp: Boolean
): String {
    var tip = tipPercent / 100 * amount


//    val tm = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
//    val country = tm.simCountryIso

    val country = "in"


    if (roundUp)
        tip = ceil(tip)

    //return NumberFormat.getCurrencyInstance().format(tip)
    return NumberFormat.getCurrencyInstance(Locale("en", country)).format(tip)

}

@Composable
fun RoundTipRow(
    modifier: Modifier = Modifier,
    roundUp: Boolean,
    onRoundUpChange: (Boolean) -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .size(48.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = stringResource(id = R.string.round_up_tip))

        Switch(
            modifier = modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.End),
            checked = roundUp,
            onCheckedChange = onRoundUpChange,
            colors = SwitchDefaults.colors(
                uncheckedThumbColor = Color.DarkGray,
                checkedThumbColor = Color.Blue,
                uncheckedTrackColor = Color.Gray,
            )
        )
    }

}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AndroidBasicsInComposeTheme() {
        TipTimeScreen()
    }
}
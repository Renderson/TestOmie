package com.renderson.testomie.util

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.unit.sp
import java.util.*

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun OutlinedTextFieldComposable(
    input: MutableState<String>,
    inputType: TypeInputEnum = TypeInputEnum.NONE,
    label: String,
    enabled: Boolean = true,
    readonly: Boolean = false,
    imeAction: ImeAction = ImeAction.Done,
    capitalization: KeyboardCapitalization = KeyboardCapitalization.None,
    keyboardType: KeyboardType = KeyboardType.Text,
    onInputChange: (value: String) -> Unit = {}
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth(),
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = keyboardType,
            imeAction = imeAction,
            capitalization = capitalization
        ),
        label = {
            Text(text = label)
        },
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardController?.hide()
            }
        ),
        visualTransformation = { visualTransformationGetType(inputType.type, input = it) },
        colors = OutlinedTextFieldDefaults.colors(
            disabledTextColor = Color.Black,
            disabledBorderColor = Color.Gray,
        ),
        value = input.value,
        onValueChange = {
            input.value = it
            onInputChange(input.value)
        },
        readOnly = readonly,
        enabled = enabled,
        textStyle = TextStyle(fontSize = 20.sp)
    )
}

enum class TypeInputEnum(val type: String) {
    CURRENCY("CURRENCY"),
    NONE("")
}

private fun visualTransformationGetType(type: String, input: AnnotatedString): TransformedText {
    return when (type) {
        TypeInputEnum.CURRENCY.type -> {
            visualTransformationToCurrency(input)
        }
        else -> {
            visualTransformationToNormal(input)
        }
    }
}
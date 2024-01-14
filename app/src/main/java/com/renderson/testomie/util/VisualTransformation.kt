package com.renderson.testomie.util

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import java.text.NumberFormat
import java.util.Locale

fun visualTransformationToCurrency(input: AnnotatedString): TransformedText {
    val digitsOnly = input.text.filter { it.isDigit() }
    val formattedAmount = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
        .format(digitsOnly.toDoubleOrNull() ?: 0.0)

    val origToTransformedOffset = formattedAmount.length
    val formattedCharacterCount = formattedAmount.filterNot {
        it.isDigit()
    }.count()

    val annotatedString = AnnotatedString(formattedAmount)

    val currencyOffsetTranslator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int = origToTransformedOffset

        override fun transformedToOriginal(offset: Int): Int {
            val originalOffset = offset - formattedCharacterCount
            return if (originalOffset >= 0 && originalOffset <= digitsOnly.length) {
                originalOffset
            } else {
                0
            }
        }
    }
    return TransformedText(annotatedString, currencyOffsetTranslator)
}


fun visualTransformationToNormal(input: AnnotatedString): TransformedText {

    val annotatedString = AnnotatedString(input.text)

    val offsetTranslator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int = offset

        override fun transformedToOriginal(offset: Int): Int = offset
    }
    return TransformedText(annotatedString, offsetTranslator)
}
package com.test.composestudy.movieapp.ui.components.moive.button

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.test.composestudy.ui.theme.ComposestudyTheme
import com.test.composestudy.ui.theme.Paddings
import com.test.composestudy.ui.theme.colorsScheme

@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier,
    @StringRes id: Int? = null,
    text: String = "",
    onClick: () -> Unit
) {
    Button(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colorsScheme.primary,
            contentColor = MaterialTheme.colorsScheme.onPrimary,
            disabledBackgroundColor = MaterialTheme.colorsScheme.disabledSecondary,
            disabledContentColor = MaterialTheme.colorsScheme.background,
        )
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = id?.let { stringResource(id = id) } ?: text,
                style = MaterialTheme.typography.button,
                modifier = Modifier.padding(Paddings.small)
            )
        }
    }
}

@Preview
@Composable
fun PrimaryButtonPreview() {
    ComposestudyTheme() {
        PrimaryButton(
            text = "SUBMIT"
        ) {

        }
    }
}
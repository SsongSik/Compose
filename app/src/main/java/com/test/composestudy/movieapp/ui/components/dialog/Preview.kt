package com.test.composestudy.movieapp.ui.components.dialog

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.test.composestudy.R
import com.test.composestudy.movieapp.ui.model.button.LeadingIconData
import com.test.composestudy.movieapp.ui.model.dialog.DialogButton
import com.test.composestudy.ui.theme.ComposestudyTheme

@Preview
@Composable
fun AlertPreview() {
    ComposestudyTheme {
        DialogPopup.Alert(
            title = "Title",
            bodyText = "abced abced abced abced abced",
            button = listOf(
                DialogButton.UnderlinedText("CLOSE")
            )
        )
    }
}

@Preview
@Composable
fun DefaultPreview() {
    ComposestudyTheme {
        DialogPopup.Default(
            title = "Title",
            bodyText = "abced abced abced abced abced",
            button = listOf(
                DialogButton.Primary("OPEN"),
                DialogButton.Secondary("CLOSE")
            )
        )
    }
}

@Preview
@Composable
fun RatingPreview() {
    ComposestudyTheme {
        DialogPopup.Rating(
            movieName = "Movie Name",
            rating = 3.5f,
            button = listOf(
                DialogButton.Primary(
                    title = "OPEN",
                    leadingIconData = LeadingIconData(
                        iconDrawable = R.drawable.baseline_star_rate_24,
                        iconContentDescription = null
                    )
                ),
                DialogButton.SecondaryBorderless("CLOSE")
            )
        )
    }
}
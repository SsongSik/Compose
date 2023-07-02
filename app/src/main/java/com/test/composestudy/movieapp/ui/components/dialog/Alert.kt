package com.test.composestudy.movieapp.ui.components.dialog

import androidx.compose.runtime.Composable
import com.test.composestudy.movieapp.ui.model.dialog.DialogButton
import com.test.composestudy.movieapp.ui.model.dialog.DialogContent
import com.test.composestudy.movieapp.ui.model.dialog.DialogText
import com.test.composestudy.movieapp.ui.model.dialog.DialogTitle

object DialogPopup

@Composable
fun DialogPopup.Alert(
    title: String,
    bodyText: String,
    button: List<DialogButton>
) {
    BaseDialogPopup(
        dialogTitle = DialogTitle.Header(
            title
        ),
        dialogContent = DialogContent.Large(
            DialogText.Default(bodyText)
        ),
        button = button
    )
}
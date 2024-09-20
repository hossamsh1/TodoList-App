package com.example.todolistnewapp.composablefunctions

import android.app.TimePickerDialog
import android.content.Context
import java.util.Calendar

fun showTimePickerDialog(context: Context, onTimeSelected: (Int, Int) -> Unit) {
    val calendar = Calendar.getInstance()
    val hour = calendar.get(Calendar.HOUR_OF_DAY)
    val minute = calendar.get(Calendar.MINUTE)

    val timePickerDialog = TimePickerDialog(
        context,
        { _, hourOfDay: Int, minuteOfHour: Int ->
            onTimeSelected(hourOfDay, minuteOfHour)
        },
        hour,
        minute,
        true
    )
    timePickerDialog.show()
}

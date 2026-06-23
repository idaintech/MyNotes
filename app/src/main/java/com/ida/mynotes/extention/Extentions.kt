package com.ida.mynotes.extention

import java.sql.Date
import java.text.SimpleDateFormat
import java.util.Locale

//474756747 10.06.2025
fun Long.convertToDate(
    pattern: String = "dd.MM.yyyy HH:mm:ss"
): String{
    return SimpleDateFormat(
        pattern,
        Locale.getDefault())
        .format(Date(this))
}





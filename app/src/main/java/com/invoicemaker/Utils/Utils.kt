package com.invoicemaker.Utils

import java.util.Random

class Utils {
    var context  = MyApp.getAppContext()

    fun generateUniqueId(): String {
        val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9') // define the allowed characters
        val random = Random() // create a new random number generator
        return (1..16)
            .map { allowedChars[random.nextInt(allowedChars.size)] } // generate a list of random characters
            .joinToString("") // join the list of characters into a single string
    }

}
package com.example.sillappsproject.common

import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.findNavController

fun Fragment.showError(error: Throwable) =
    Toast.makeText(context, error.localizedMessage, Toast.LENGTH_SHORT).show()

fun View?.navigate(direction: NavDirections){
    this?.findNavController()?.let { navController ->
        navController.navigate(direction)
    }
}
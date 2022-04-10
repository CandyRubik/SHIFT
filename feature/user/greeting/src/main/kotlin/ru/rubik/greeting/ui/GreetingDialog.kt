package ru.rubik.greeting.ui

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatDialogFragment

class GreetingDialog(private val nameToGreet: String) : AppCompatDialogFragment() {

	override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

		return AlertDialog.Builder(activity).setTitle("Hello").setMessage(nameToGreet).create()
	}
}
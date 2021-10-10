package com.example.myapplicationkotlin.view.fragment

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.myapplicationkotlin.view.AboutActivity

class DialogAboutFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder
                .setTitle("Информация")
                .setMessage("Перейти на страницу информации о приложении?")
                .setNegativeButton("Нет", null)
                .setPositiveButton("Да") { dialog, _ ->
                    dialog.cancel()
                    startActivity(
                        Intent(
                            it,
                            AboutActivity::class.java
                        )
                    )
                }
                .create()
        } ?: throw IllegalStateException("Activity не может быть null")
    }
}
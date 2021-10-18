package com.example.myapplicationkotlin.view.fragment

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.myapplicationkotlin.view.AboutActivity

class AboutDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val exInfo = "Activity не может быть null"
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val info = "Информация"
            val message = "Перейти на страницу информации о приложении?"
            val negBut = "Нет"
            val posBut = "Да"
            builder
                .setTitle(info)
                .setMessage(message)
                .setNegativeButton(negBut, null)
                .setPositiveButton(posBut) { dialog, _ ->
                    dialog.cancel()
                    startActivity(
                        Intent(
                            it,
                            AboutActivity::class.java
                        )
                    )
                }
                .create()
        } ?: throw IllegalStateException(exInfo)
    }
}
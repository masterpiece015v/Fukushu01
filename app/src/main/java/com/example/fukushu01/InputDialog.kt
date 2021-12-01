package com.example.fukushu01

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.EditText
import android.widget.LinearLayout
import androidx.fragment.app.DialogFragment

class InputDialog(
    val okSelected: (kurasumei:String,gakuseimei:String) -> Unit,
    val canselSelected : () -> Unit
) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val edit1 = EditText(this.context).apply{
            this.hint = "クラス名"
            this.width = 600
            this.textSize = 24.0f
        }

        val edit2 = EditText(this.context).apply{
            this.hint = "学生名"
            this.width = 600
            this.textSize = 24.0f
        }

        val linear = LinearLayout(this.context).apply{
            this.addView( edit1 )
            this.addView( edit2 )
            this.orientation = LinearLayout.VERTICAL
        }

        val builder = AlertDialog.Builder( requireActivity() )
        builder.setView( linear )
        builder.setPositiveButton("追加"){dialog,which->
            okSelected.invoke(edit1.text.toString(),edit2.text.toString())
        }
        builder.setNegativeButton("キャンセル"){dialog,which->
            canselSelected()
        }
        return builder.create()
    }
}
package com.techmashinani.toastsample

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.custom_toast.view.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setup()
    }

    private fun setup() {
        button.setOnClickListener { Toast.makeText(applicationContext, "Normal Toast", Toast.LENGTH_LONG).show() }

        button2.setOnClickListener {
            val toast = Toast.makeText(applicationContext, "Different Gravity", Toast.LENGTH_LONG)
            toast.setGravity(Gravity.TOP or Gravity.RIGHT, 0, 0)
            toast.show()
        }

        button3.setOnClickListener { customToast().show() }
    }

    @SuppressLint("SetTextI18n", "InflateParams")
    private fun customToast(): Toast {
        val layout = layoutInflater.inflate(R.layout.custom_toast, null)
        layout.custom.text = "Custom Toast"
//        return with(Toast(applicationContext)) {
//            this.duration = Toast.LENGTH_LONG
//            setGravity(Gravity.CENTER_VERTICAL and Gravity.CENTER_HORIZONTAL, 0, 0)
//            view = layout
//            this
//        }

        return Toast(applicationContext).apply {
            duration = Toast.LENGTH_LONG
            setGravity(Gravity.CENTER_VERTICAL and Gravity.CENTER_HORIZONTAL, 0, 0)
            view = layout
        }
    }
}

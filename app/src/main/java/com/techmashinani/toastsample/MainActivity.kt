package com.techmashinani.toastsample

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.custom_toast.view.*

@SuppressLint("InflateParams")
class MainActivity : AppCompatActivity() {

    private lateinit var layout: View
    private val layout2 by lazy {
        layoutInflater.inflate(R.layout.custom_toast, null)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        layout = findViewById(android.R.id.content)

        setup()
    }

    private fun setup() {
        button.setOnClickListener { Toast.makeText(applicationContext, "Normal Toast", Toast.LENGTH_LONG).show() }

        button2.setOnClickListener {
            val toast = Toast.makeText(applicationContext, "Different Gravity", Toast.LENGTH_LONG)
            toast.setGravity(Gravity.TOP or Gravity.RIGHT, 0, 30)
            toast.show()
        }

        button3.setOnClickListener { customToast().show() }

        button4.setOnClickListener { Snackbar.make(layout, "Normal Snack bar", Snackbar.LENGTH_LONG).show() }

        button5.setOnClickListener {
            Snackbar.make(layout, "Snack bar with Action", Snackbar.LENGTH_LONG).apply {
                setAction("Show me") {
                    Toast.makeText(applicationContext, "Sample Action", Toast.LENGTH_SHORT).show()
                }
            }.show()
        }

        button6.setOnClickListener {
            Snackbar.make(layout, "Snackbar with callback", Snackbar.LENGTH_INDEFINITE).apply {
                setAction("Callback") {
                    Toast.makeText(applicationContext, "Callback", Toast.LENGTH_SHORT).show()
                    this.dismiss()
                }

                addCallback(object: BaseTransientBottomBar.BaseCallback<Snackbar>() {
                    override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                        super.onDismissed(transientBottomBar, event)
                        Toast.makeText(applicationContext, "Dismissing snack bar", Toast.LENGTH_SHORT).show()
                    }

                    override fun onShown(transientBottomBar: Snackbar?) {
                        super.onShown(transientBottomBar)
                        transientBottomBar?.setActionTextColor(Color.GREEN)
                        Toast.makeText(applicationContext, "Showing snack bar", Toast.LENGTH_SHORT).show()
                    }
                })
            }.show()
        }
    }

    @SuppressLint("SetTextI18n", "InflateParams")
    private fun customToast(): Toast {
        layout2.custom.text = "Custom Toast"
//        return with(Toast(applicationContext)) {
//            this.duration = Toast.LENGTH_LONG
//            setGravity(Gravity.CENTER_VERTICAL and Gravity.CENTER_HORIZONTAL, 0, 0)
//            view = layout
//            this
//        }

        return Toast(applicationContext).apply {
            duration = Toast.LENGTH_LONG
            setGravity(Gravity.CENTER_VERTICAL and Gravity.CENTER_HORIZONTAL, 0, 0)
            view = layout2
        }
    }
}

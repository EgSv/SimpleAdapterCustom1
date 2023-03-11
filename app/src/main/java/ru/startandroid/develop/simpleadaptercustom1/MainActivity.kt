package ru.startandroid.develop.simpleadaptercustom1

import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ListView
import android.widget.SimpleAdapter
import android.widget.TextView

const val ATTRIBUTE_NAME_TEXT = "text"
const val ATTRIBUTE_NAME_VALUE = "value"
const val ATTRIBUTE_NAME_IMAGE = "image"

class MainActivity : AppCompatActivity() {

    val positive = android.R.drawable.arrow_up_float
    val negative = android.R.drawable.arrow_down_float
    var lvSImple: ListView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val values = intArrayOf(8, 4, -3, 2, -5, 0, 3, -6, 1, -1)

        val data = ArrayList<MutableMap<String?, Any?>>(values.size)
        var m:MutableMap<String?, Any?>
        var img: Int
        for (i in values.indices) {
            m = HashMap()
            m.put(ATTRIBUTE_NAME_TEXT, "Day + ${i + 1}")
            m.put(ATTRIBUTE_NAME_VALUE, values[i])
            img = if (values[i] == 0) {
                0
            } else if (values[i] > 0) {
                positive
            } else negative
            m.put(ATTRIBUTE_NAME_IMAGE, img)
            data.add(m)
        }

        val from = arrayOf(ATTRIBUTE_NAME_TEXT,
            ATTRIBUTE_NAME_VALUE,
            ATTRIBUTE_NAME_IMAGE)

        val to = intArrayOf(R.id.tvText, R.id.tvValue, R.id.ivImg)

        val sAdapter = MySimpleAdapter(this, data, R.layout.item, from, to)

        lvSImple = findViewById<View>(R.id.lvSimple) as ListView
        lvSImple?.adapter = sAdapter
    }

    internal inner class MySimpleAdapter(
        context: Context,
        data: List<MutableMap<String?, *>?>?,
        resource:Int,
        from:Array<String>,
        to: IntArray?
    ) :
        SimpleAdapter(context, data, resource, from, to) {
        override fun setViewText(v: TextView, text: String) {
            super.setViewText(v, text)
            if (v.id == R.id.tvValue) {
                val i = text.toInt()
                if (i < 0) v.setTextColor(Color.RED) else if (i > 0) v.setTextColor(Color.GREEN)
            }
        }

        override fun setViewImage(v: ImageView?, value: Int) {
            super.setViewImage(v, value)
            if (value == negative) {
                v?.setBackgroundColor(Color.RED)
            } else if (value == positive) {
                v?.setBackgroundColor(Color.GREEN)
            }
        }
    }
}
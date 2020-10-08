package com.example.myandroidchart

import android.graphics.Color
import android.graphics.DashPathEffect
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.LimitLine
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.github.mikephil.charting.utils.Utils
import java.util.*

class MainActivity : AppCompatActivity() {
    private var mChart: LineChart? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mChart = findViewById(R.id.chart)
        /*val mv = MyMarkerView(applicationContext, R.layout.custom_marker_view)
        mv.chartView = mChart*/
        renderData()
    }

    private fun renderData() {
        /*set graph height and width
        val llXAxis = LimitLine(10f, "ค่าความดันเลือด")
        llXAxis.lineWidth = 10f
        llXAxis.enableDashedLine(10f, 10f, 0f)
        llXAxis.labelPosition = LimitLine.LimitLabelPosition.RIGHT_BOTTOM
        llXAxis.textSize = 50f*/
        //set x axis
        val xAxis = mChart!!.xAxis
        xAxis.enableGridDashedLine(10f, 10f, 0f) //set Dashed Line that dragged from x axis
        //set number of x point
        xAxis.axisMaximum = 7f
        xAxis.axisMinimum = 0f
        xAxis.position = XAxis.XAxisPosition.BOTTOM //set x axis position
        //xAxis.setDrawLimitLinesBehindData(true)
        //set min max line
        val ll1 = LimitLine(215f, "Maximum Limit")//set max line point
        ll1.lineWidth = 4f
        ll1.enableDashedLine(10f, 10f, 0f)
        ll1.labelPosition = LimitLine.LimitLabelPosition.RIGHT_TOP
        ll1.textSize = 10f
        val ll2 = LimitLine(70f, "Minimum Limit")//set min line point
        ll2.lineWidth = 4f
        ll2.enableDashedLine(10f, 10f, 0f)
        ll2.labelPosition = LimitLine.LimitLabelPosition.RIGHT_BOTTOM //set position of Minimum Limit
        ll2.textSize = 10f
        //set y axis
        val leftAxis = mChart!!.axisLeft
        //leftAxis.removeAllLimitLines()
        leftAxis.addLimitLine(ll1)//add max point line
        leftAxis.addLimitLine(ll2)//add min point line
        leftAxis.axisMaximum = 350f //set max of y axis
        leftAxis.axisMinimum = 0f //set min of y axis
        leftAxis.enableGridDashedLine(10f, 10f, 0f)
        //leftAxis.setDrawZeroLine(false)
        //leftAxis.setDrawLimitLinesBehindData(false)
        mChart!!.axisRight.isEnabled = false //remove chart frame
        setData()
    }

    private fun setData() {
        //set value to data
        val values = ArrayList<Entry>()
        values.add(Entry(1F, 150F))
        values.add(Entry(2F, 100F))
        values.add(Entry(3F, 80F))
        values.add(Entry(4F, 120F))
        values.add(Entry(5F, 110F))
        values.add(Entry(6F, 150F))
        values.add(Entry(7F, 250F))

        val xAxisLabels = listOf("0","1", "2", "3", "4", "5", "6" ,"today") // set text to x axis
        mChart!!.xAxis.valueFormatter = IndexAxisValueFormatter(xAxisLabels)

        val set1: LineDataSet
        // set value to data
        if (mChart!!.data != null &&
                mChart!!.data.dataSetCount > 0) {
            set1 = mChart!!.data.getDataSetByIndex(0) as LineDataSet
            set1.values = values
            //mChart!!.data.notifyDataChanged()
            //mChart!!.notifyDataSetChanged()
        } else {
            set1 = LineDataSet(values, "Sample Data")
            set1.setDrawIcons(false)
            //set1.enableDashedLine(10f, 5f, 0f) //set Dashed line
            //set1.enableDashedHighlightLine(20f, 5f, 0f)
            set1.color = Color.DKGRAY //set line and Simple Data color
            set1.setCircleColor(Color.DKGRAY)//set point color
            set1.lineWidth = 1f
            set1.circleRadius = 3f //set point size
            set1.setDrawCircleHole(false) //set hole in point
            set1.valueTextSize = 16f//set text above point size
            set1.setDrawFilled(true)//set color fill to chart
            //set1.formLineWidth = 20f
            //set1.formLineDashEffect = DashPathEffect(floatArrayOf(50f, 5f), 0f)
            set1.formSize = 15f//set simple data size
            //set Curve graph
            set1.mode = LineDataSet.Mode.CUBIC_BEZIER
            set1.cubicIntensity = 0.2f
            //set color shade to chart
            if (Utils.getSDKInt() >= 18) {
                val drawable = ContextCompat.getDrawable(this, R.drawable.graph_color)
                set1.fillDrawable = drawable
            } else {
                set1.fillColor = Color.DKGRAY
            }
            //set value to data
            val dataSets = ArrayList<ILineDataSet>()
            dataSets.add(set1)
            val data = LineData(dataSets)
            mChart!!.data = data
        }
    }
}
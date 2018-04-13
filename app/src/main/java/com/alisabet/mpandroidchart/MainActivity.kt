package com.alisabet.mpandroidchart

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.LimitLine
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //val pieChart = findViewById<PieChart>(R.id.piechart)
        //pieChartSetup(pieChart)

        val lineChart = findViewById<LineChart>(R.id.linechart)
        lineChartSetup(lineChart)

    }

    private fun pieChartSetup(pieChart: PieChart){
        pieChart.setUsePercentValues(true)
        pieChart.description.isEnabled = false
        pieChart.setExtraOffsets(5F,10F,5F,5F)
        pieChart.dragDecelerationFrictionCoef = 0.95F
        pieChart.isDrawHoleEnabled = true
        pieChart.setHoleColor(Color.RED)
        pieChart.transparentCircleRadius = 61F

        val yValues = ArrayList<PieEntry>()

        yValues.add(PieEntry(34F, "russia"))
        yValues.add(PieEntry(23F, "usa"))
        yValues.add(PieEntry(45F, "uk"))
        yValues.add(PieEntry(30F, "iran"))
        yValues.add(PieEntry(21F, "france"))
        yValues.add(PieEntry(33F, "germany"))

        val dataSet = PieDataSet(yValues, "countries")
        dataSet.sliceSpace = 3F
        dataSet.selectionShift = 5F
        dataSet.setColors(Color.GREEN, Color.BLUE, Color.RED)

        val data = PieData(dataSet)
        data.setValueTextSize(10F)
        data.setValueTextColor(Color.BLACK)

        pieChart.data = data
    }

    private fun lineChartSetup(lineChart: LineChart){
        lineChart.isDragEnabled = true
        lineChart.setScaleEnabled(false)

        val upper_limit = LimitLine(65F, "danger")
        upper_limit.lineWidth = 4F
        upper_limit.enableDashedLine(10F,10F, 0F)
        upper_limit.labelPosition = LimitLine.LimitLabelPosition.RIGHT_TOP
        upper_limit.textSize = 15F

        val lower_limit = LimitLine(35F, "too low")
        lower_limit.lineWidth = 4F
        lower_limit.enableDashedLine(10F,10F, 0F)
        lower_limit.labelPosition = LimitLine.LimitLabelPosition.RIGHT_BOTTOM
        lower_limit.textSize = 15F

        val leftAxis = lineChart.axisLeft
        leftAxis.removeAllLimitLines()
        leftAxis.addLimitLine(upper_limit)
        leftAxis.addLimitLine(lower_limit)
        leftAxis.axisMaximum = 100F
        leftAxis.axisMinimum = 25F
        leftAxis.enableGridDashedLine(10F, 10F, 0F)
        leftAxis.setDrawLimitLinesBehindData(true)

        val yValues = ArrayList<Entry>()
        yValues.add(Entry(0F, 19F))
        yValues.add(Entry(1F, 80F))
        yValues.add(Entry(2F, 59F))
        yValues.add(Entry(3F, 14F))
        yValues.add(Entry(4F, 56F))
        yValues.add(Entry(5F, 34F))

        val set1 = LineDataSet(yValues, "data set 1")
        set1.fillAlpha = 110

        val dataSet = ArrayList<ILineDataSet>()
        dataSet.add(set1)

        val data = LineData(dataSet)

        lineChart.data = data

        val values = arrayOf("jan", "feb", "mar", "nov", "oct", "apr")

        val xAxis = lineChart.xAxis
        xAxis.setValueFormatter(MyXAxisValuesFormatter(values))
        xAxis.granularity = 1F
        xAxis.position = XAxis.XAxisPosition.BOTH_SIDED

    }

}

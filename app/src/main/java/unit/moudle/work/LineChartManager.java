package unit.moudle.work;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.puti.education.R;

import java.util.ArrayList;
import java.util.List;

import unit.widget.MyMarkerView;

/**
 * Created by ${lei} on 2018/7/29.
 */
public class LineChartManager implements OnChartValueSelectedListener {
    private LineChart lineChart;
     private YAxis leftAxis; //左边Y轴
     private YAxis rightAxis; //右边Y轴
     private XAxis xAxis; //X轴
     public LineChartManager(LineChart mLineChart) {
         this.lineChart = mLineChart;
         leftAxis = lineChart.getAxisLeft();
         rightAxis = lineChart.getAxisRight();
         xAxis = lineChart.getXAxis();
     }
     /** * 初始化LineChart */
     private void initLineChart() {

          lineChart.setDrawBorders(false); //设置动画效果
          lineChart.animateY(1000, Easing.EasingOption.Linear);
          lineChart.animateX(1000, Easing.EasingOption.Linear); //折线图例 标签 设置
          Legend legend = lineChart.getLegend();
          legend.setForm(Legend.LegendForm.CIRCLE);
          legend.setTextSize(11f); //显示位置
         legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
         legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
         legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
         legend.setDrawInside(false); //XY轴的设置 //X轴设置显示位置在底部
          xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
          xAxis.setAxisMinimum(0f);
          xAxis.setGranularity(1f); //保证Y轴从0开始,不然会上移一点
          xAxis.setDrawAxisLine(true);
          leftAxis.setDrawGridLines(true);
          leftAxis.setAxisMinimum(0f);
          leftAxis.setDrawZeroLine(true);
          rightAxis.setDrawZeroLine(true);
          rightAxis.setDrawAxisLine(true);
          leftAxis.setDrawAxisLine(true);
          rightAxis.setDrawGridLines(false);
          leftAxis.setDrawGridLines(false);
          xAxis.setDrawGridLines(false);
          rightAxis.setEnabled(false);
          lineChart.getLegend().setEnabled(false);
          lineChart.getDescription().setEnabled(false);

         // enable touch gestures
         lineChart.setTouchEnabled(true);

         // enable scaling and dragging
         lineChart.setDragEnabled(true);
         lineChart.setScaleEnabled(true);
         lineChart.getDescription().setEnabled(false);
         // if disabled, scaling can be done on x- and y-axis separately
         lineChart.setPinchZoom(false);

         lineChart.setDrawGridBackground(false);
         lineChart.setOnChartValueSelectedListener(this);

           }
          /** * 初始化曲线 每一个LineDataSet代表一条线
           * *
           * * @param lineDataSet *
           * @param color
           * * @param mode 折线图是否填充 */
          private void initLineDataSet(LineDataSet lineDataSet, int color, boolean mode) {
              lineDataSet.setColor(color);
           lineDataSet.setCircleColor(color);
           lineDataSet.setLineWidth(1f);
//           lineDataSet.setCircleRadius(0f); //设置曲线值的圆点是实心还是空心
              lineDataSet.setDrawCircleHole(false);
              lineDataSet.setDrawCircles(false);
              lineDataSet.setValueTextSize(9f); //设置折线图填充
               lineDataSet.setDrawFilled(mode);
               lineDataSet.setFormLineWidth(1f);
               lineDataSet.setFormSize(15.f); //线模式为圆滑曲线(默认折线)
               lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
                lineDataSet.setDrawValues(false);
          }
               /** * 展示折线图(一条) * *
                * @param xAxisValues *
                *  @param yAxisValues *
                * @param label * @param color  */
               public void showLineChart(List<Integer> xAxisValues, List<Integer> yAxisValues, String label, int color) {
                   int max = 0;
                   for (int i = 0; i < yAxisValues.size(); i++) {
                       int tempData = yAxisValues.get(i);
                       if (tempData >= max){
                           max = tempData;
                       }
                   }
                   if (max <= 5){
                       rightAxis.setLabelCount(max-1);
                       leftAxis.setLabelCount(max-1);
                   }else {
                       rightAxis.setLabelCount(5);
                       leftAxis.setLabelCount(5);
                   }
                   xAxis.setLabelCount(xAxisValues.size()+10);
                   initLineChart();
                   ArrayList<Entry> entries = new ArrayList<>();
                   for (int i = 0; i < xAxisValues.size(); i++) {
                           entries.add(new Entry((int) xAxisValues.get(i), (int) yAxisValues.get(i)));
                   }
                   // 每一个LineDataSet代表一条线
                   LineDataSet lineDataSet = new LineDataSet(entries, label);
                   initLineDataSet(lineDataSet, color, false);

                   ArrayList<ILineDataSet> dataSets = new ArrayList<>();
                   dataSets.add(lineDataSet);
                   LineData data = new LineData(dataSets);
                   //设置X轴的刻度数
                   xAxis.setLabelCount(xAxisValues.size(), true);
                   lineChart.setData(data); }
                   /** * 展示线性图(多条) * *
                    * @param xAxisValues *
                    * @param yAxisValues 多条曲线Y轴数据集合的集合 *
                    * @param labels *
                    * @param colours */
                   public void showLineChart(List<Float> xAxisValues, List<List<Float>> yAxisValues, List<String> labels, List<Integer> colours) {
                       initLineChart();
                       ArrayList<ILineDataSet> dataSets = new ArrayList<>();
                       for (int i = 0; i < yAxisValues.size(); i++) {
                           ArrayList<Entry> entries = new ArrayList<>();
                           for (int j = 0; j < yAxisValues.get(i).size(); j++) {
                               if (j >= xAxisValues.size()) {
                                   j = xAxisValues.size() - 1;
                               }
                               entries.add(new Entry(xAxisValues.get(j), yAxisValues.get(i).get(j)));
                           }
                           LineDataSet lineDataSet = new LineDataSet(entries, labels.get(i));
                           initLineDataSet(lineDataSet, colours.get(i), false); dataSets.add(lineDataSet); }
                           LineData data = new LineData(dataSets); xAxis.setLabelCount(xAxisValues.size(), true);
                           lineChart.setData(data);
                   }

                           /** * 设置Y轴值 *
                            * * @param max
                            * * @param min
                            * * @param labelCount */
                           public void setYAxis(float max, float min, int labelCount) {
                               if (max < min) { return; }
                               leftAxis.setAxisMaximum(max);
                               leftAxis.setAxisMinimum(min);
                               leftAxis.setLabelCount(labelCount, false);
                               rightAxis.setAxisMaximum(max);
                               rightAxis.setAxisMinimum(min);
                               rightAxis.setLabelCount(labelCount, false);
                               lineChart.invalidate();
                           }
                           /** * 设置X轴的值 *
                            * * @param max *
                            * @param min *
                            * @param labelCount */
                           public void setXAxis(float max, float min, int labelCount) {
                               xAxis.setAxisMaximum(max);
                               xAxis.setAxisMinimum(min);
                               xAxis.setLabelCount(labelCount, true); lineChart.invalidate(); }
                               /** *
                                *  设置高限制线 *
                                *  * @param high
                                *  * @param name */
                               public void setHightLimitLine(float high, String name, int color) {
                                   if (name == null) {
                                       name = "高限制线";
                                   }
                                   LimitLine hightLimit = new LimitLine(high, name);
                                   hightLimit.setLineWidth(2f);
                                   hightLimit.setTextSize(10f);
                                   hightLimit.setLineColor(color);
                                   hightLimit.setTextColor(color);
                                   leftAxis.addLimitLine(hightLimit);
                                   lineChart.invalidate();
                               }
                               /** *
                                * 设置低限制线 * *
                                * @param low *
                                * @param name */
                               public void setLowLimitLine(int low, String name) {
                                   if (name == null) {
                                       name = "低限制线";
                                   }
                                   LimitLine hightLimit = new LimitLine(low, name);
                                   hightLimit.setLineWidth(4f);
                                   hightLimit.setTextSize(10f);
                                   leftAxis.addLimitLine(hightLimit);
                                   lineChart.invalidate();
                               }
                               /**
                                * * 设置描述信息 *
                                * * @param str
                                * */
                               public void setDescription(String str) {
                                   Description description = new Description();
                                   description.setText(str);
                                   lineChart.setDescription(description);
                                   lineChart.invalidate();
                               }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }
}

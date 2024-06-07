package com.example.sgmis_java.utils;

import com.github.abel533.echarts.Grid;
import com.github.abel533.echarts.Label;
import com.github.abel533.echarts.LabelLine;
import com.github.abel533.echarts.Legend;
import com.github.abel533.echarts.Title;
import com.github.abel533.echarts.Tooltip;
import com.github.abel533.echarts.axis.AxisLabel;
import com.github.abel533.echarts.axis.ValueAxis;
import com.github.abel533.echarts.code.AxisType;
import com.github.abel533.echarts.code.Position;
import com.github.abel533.echarts.code.Trigger;
import com.github.abel533.echarts.code.X;
import com.github.abel533.echarts.json.GsonOption;
import com.github.abel533.echarts.series.Bar;
import com.github.abel533.echarts.series.Line;
import com.github.abel533.echarts.series.Lines;
import com.github.abel533.echarts.series.Pie;
import com.github.abel533.echarts.series.Series;
import com.github.abel533.echarts.style.ItemStyle;
import com.github.abel533.echarts.style.TextStyle;
import com.github.abel533.echarts.style.itemstyle.Emphasis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EchartOptionUtil {
    public static GsonOption getPigeonLineOptions(List<Object[]> createData, List<Object[]> deleteData) {
        GsonOption option = new GsonOption();
        option.title("库中鸽子增删")
                .legend(new Legend()
                        .data("创建", "删除")
                )
                .tooltip(new Tooltip()
                        .trigger(Trigger.axis)
                )
                .grid(new Grid()
                        .left("3%")
                        .right("10%")
                        .bottom("3%")
                        .containLabel(true)
                )
                .xAxis(new ValueAxis()
                        .type(AxisType.time)
                        .name("日期")
                        .boundaryGap(false)
                        .axisLabel(new AxisLabel()
                                .formatter("{MM}/{dd}")
                        )
                )
                .yAxis(new ValueAxis()
                        .name("操作")
                        .type(AxisType.value)
                )
                .series(new Line()
                        .name("创建")
                        .data(createData.toArray())
                        .itemStyle(new ItemStyle()
                                .color("#14C9C9")
                        ), new Line()
                        .name("删除")
                        .data(deleteData.toArray())
                        .itemStyle(new ItemStyle()
                                .color("#D91AD9")
                        )
                );
        return option;
    }

    public static GsonOption getOplogUtilOptions(List<List<Object[]>> contentMap) {
        GsonOption option = new GsonOption();
        option.title(new Title()
                        .subtext("操作数据变化"))
                .legend(new Legend()
                        .data("新增", "修改", "删除", "共享血统", "接收血统", "关联血亲", "解除血亲", "转移鸽棚巢箱", "其他")
                )
                .tooltip(new Tooltip()
                        .trigger(Trigger.axis)
                )
                .grid(new Grid()
                        .left("3%")
                        .right("10%")
                        .bottom("3%")
                        .containLabel(true)
                )
                .xAxis(new ValueAxis()
                        .type(AxisType.time)
                        .name("日期")
                        .boundaryGap(false)
                        .axisLabel(new AxisLabel()
                                .formatter("{MM}/{dd}")
                        )
                )
                .yAxis(new ValueAxis()
                        .name("操作")
                        .type(AxisType.value)
                )
                .series(new Line()
                        .data(contentMap.get(0).toArray())
                        .name("新增")
                        .itemStyle(new ItemStyle()
                                .color("#722ED1")
                        ), new Line()
                        .data(contentMap.get(1).toArray())
                        .name("修改")
                        .itemStyle(new ItemStyle()
                                .color("#9FDB1D")
                        ), new Line()
                        .data(contentMap.get(2).toArray())
                        .name("删除")
                        .itemStyle(new ItemStyle()
                                .color("#F53F3F")
                        ), new Line()
                        .data(contentMap.get(3).toArray())
                        .name("共享血统")
                        .itemStyle(new ItemStyle()
                                .color("#F77234")
                        ), new Line()
                        .data(contentMap.get(4).toArray())
                        .name("接收血统")
                        .itemStyle(new ItemStyle()
                                .color("#FADC19")
                        ), new Line()
                        .data(contentMap.get(5).toArray())
                        .name("关联血亲")
                        .itemStyle(new ItemStyle()
                                .color("#14C9C9")
                        ), new Line()
                        .data(contentMap.get(6).toArray())
                        .name("解除血亲")
                        .itemStyle(new ItemStyle()
                                .color("#F7BA1E")
                        ), new Line()
                        .data(contentMap.get(7).toArray())
                        .name("转移鸽棚巢箱")
                        .itemStyle(new ItemStyle()
                                .color("#F5319D")
                        ), new Line()
                        .data(contentMap.get(8).toArray())
                        .name("其他")
                        .itemStyle(new ItemStyle()
                                .color("#165DFF")
                        )
                );
        return option;
    }

    public static GsonOption getOptionPieUtilOptions(List<Map<String, Object>> countDataPie) {
        GsonOption option = new GsonOption();

        Pie pie = new Pie();
        option.title("操作总数统计")
                .tooltip(new Tooltip()
                        .trigger(Trigger.item)
                )
                .legend(new Legend()
                        .top("5%")
                        .left(X.center)
                )
                .series(pie
                        .data(countDataPie.toArray())
                        .radius("40%", "70%")
                        .avoidLabelOverlap(false)
                        .label(new ItemStyle()
                                .emphasis(new Emphasis()
                                        .label(new Label()
                                                .show(true)
                                                .textStyle(new TextStyle()
                                                        .fontSize(40)
                                                        .fontWeight("bold")
                                                )
                                        )
                                        .labelLine(new LabelLine()
                                                .show(false)
                                        )
                                )
                        )
                        .itemStyle(new ItemStyle()
                                .childBorderWidth(2)
                                .childBorderColor("#fff")
                        )
                );
        return option;
    }
}

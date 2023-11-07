package uz.gita.absolutepostz.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.yml.charts.axis.AxisData
import co.yml.charts.common.model.Point
import co.yml.charts.ui.linechart.LineChart
import co.yml.charts.ui.linechart.model.GridLines
import co.yml.charts.ui.linechart.model.IntersectionPoint
import co.yml.charts.ui.linechart.model.Line
import co.yml.charts.ui.linechart.model.LineChartData
import co.yml.charts.ui.linechart.model.LinePlotData
import co.yml.charts.ui.linechart.model.LineStyle
import co.yml.charts.ui.linechart.model.LineType
import co.yml.charts.ui.linechart.model.SelectionHighlightPoint
import co.yml.charts.ui.linechart.model.SelectionHighlightPopUp
import uz.gita.absolutepostz.R
import uz.gita.absolutepostz.data.models.CategoryData
import uz.gita.absolutepostz.ui.theme.BlueSlate
import uz.gita.absolutepostz.ui.theme.BlushSky
import uz.gita.absolutepostz.ui.theme.GluonGrey
import uz.gita.absolutepostz.ui.theme.LondonRain

@Composable
fun CategoryItem(
    data:CategoryData,
    selected:Boolean,
    onClick:()->Unit
){

    Column(
        modifier = Modifier
            .size(
                height = 50.dp,
                width = 85.dp
            )
            .clickable {
                onClick.invoke()
            }
    ) {
        Text(
            text = data.title,
            style = TextStyle(
                color =  BlueSlate,
                fontFamily = FontFamily(Font(R.font.inter_regular)),
                fontWeight = FontWeight.W400,
                lineHeight = 20.sp,
                fontSize = 14.sp
            )
        )

        Text(
            text = data.numbers,
            style = TextStyle(
                color = GluonGrey,
                fontSize = 16.sp,
                fontWeight = FontWeight.W600,
                lineHeight = 24.sp,
                fontFamily = FontFamily(Font(R.font.inter_regular))
            )
        )


            Divider(
                thickness = 2.dp,
                color = if (selected) LondonRain else BlushSky,
                modifier = Modifier
                    .padding(
                        top = 2.dp
                    )
            )
    }
}

@Preview
@Composable
fun CategoryPreview() {
    CategoryItem(CategoryData(title = "Выручка", numbers = "96 140P"), selected = true){

    }
}

@Composable
fun WidgetItem(
    title: String,
    date:String,
    categoryList:List<CategoryData>
){
    var selectedCategoryIndex by remember {
        mutableStateOf(0    )
    }

    Box(
        modifier = Modifier
            .padding(
                horizontal = 16.dp,
                vertical = 4.dp
            )
            .fillMaxWidth()
            .height(450.dp)
            .border(
                width = 2.dp,
                color = BlushSky,
                shape = RoundedCornerShape(16.dp)
            )
            .background(
                color = Color.White,
                shape = RoundedCornerShape(16.dp)
            )
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier
                        .padding(start = 16.dp)
                ) {
                    Text(
                        text = title,
                        style = TextStyle(
                            color = GluonGrey,
                            fontSize = 16.sp,
                            fontFamily = FontFamily(Font(R.font.inter_medium)),
                            fontWeight = FontWeight.W600,
                            lineHeight = 24.sp
                        )
                    )

                    Text(
                        text = date,
                        style = TextStyle(
                            color = BlueSlate,
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.inter_regular)),
                            fontWeight = FontWeight.W400,
                            lineHeight = 16.sp
                        )
                    )
                }

                Image(
                    painter = painterResource(id = R.drawable.ic_more),
                    contentDescription = "more",
                    modifier = Modifier
                        .size(45.dp)
                        .padding(end = 16.dp)
                )
            }

            Divider(
                thickness = 2.dp,
                color = BlushSky,
            )

            LazyRow(
                modifier = Modifier
                    .padding(
                        start = 16.dp,
                        top = 16.dp
                    )
            ) {
                itemsIndexed(categoryList){index, item ->
                    CategoryItem(data = item, selected = index == selectedCategoryIndex,
                    onClick = {
                        selectedCategoryIndex = index
                    })
                }
            }

            Diagram()
        }
    }
}

@Preview
@Composable
fun WidgetItemPreview() {
    WidgetItem(
        title = "Сегодня",
        date = "22.10.2023",
        categoryList = listOf(
        CategoryData(title = "Выручка","96 140P"),
        CategoryData(title = "Прибыль","48 372P"),
        CategoryData(title = "Заказы","76"),
        CategoryData(title = "Гости","131"),
        CategoryData(title = "Среднее","4820")
        )
    )
}



@Composable
fun Diagram() {

    val pointsData = listOf(
        Point(0f,3f),
        Point(1f,3.5f),
        Point(3f,0.5f),
        Point(3.6f,1.5f),
        Point(4f,1.8f),
        Point(5f,2f),
        Point(5.8f,2.1f),
        Point(6.4f,2.8f),
        Point(7.6f,3.1f),
        Point(8f,3.9f),
        Point(9f,2.1f),
        Point(9.5f,0.5f),
        Point(10f,2.1f),
        Point(10.2f,2.5f),
    )

    var time = 9

    val xAxisData = AxisData.Builder()
        .axisStepSize(60.dp)
        .backgroundColor(Color.Transparent)
        .steps(5)
        .labelData {amount->
            "${3*(2+amount/3)}:00"
//            "$amount"
        }
        .labelAndAxisLinePadding(5.dp)
        .axisLineColor(Color(0xFFA5C9FF))
        .axisLabelColor(Color.DarkGray)
        .build()

    val yAxisData = AxisData.Builder()
        .steps(3)
        .backgroundColor(Color.Transparent)
        .labelAndAxisLinePadding(15.dp)
        .labelData { amount->
            "$${amount*100}"
        }
        .axisLineColor(Color(0xFFA5C9FF))
        .axisLabelColor(Color.DarkGray)
        .build()

    val lineChartData = LineChartData(
        linePlotData = LinePlotData(
            lines = listOf(
                Line(
                    dataPoints = pointsData,
                    LineStyle(
                        color = Color(0xFFA5C9FF),
                        lineType = LineType.SmoothCurve(isDotted = false)
                    ),
                    IntersectionPoint(
                        color = Color(0xFFA5C9FF)
                    ),
                    SelectionHighlightPoint(color = Color(0xFFA5C9FF)),

                    selectionHighlightPopUp = SelectionHighlightPopUp()
                )
            )
        ),
        backgroundColor = Color.Transparent,
        xAxisData = xAxisData,
        yAxisData = yAxisData,
        gridLines = GridLines(color = Color.LightGray)
    )

    LineChart(
        modifier = Modifier
            .padding(horizontal = 4.dp)
            .fillMaxWidth()
            .background(Color.White)
            .height(280.dp),
        lineChartData = lineChartData
    )
}


@Preview(showSystemUi = true)
@Composable
fun DiagramPreview() {
    Diagram()
}


@Composable
fun DropDown(){




    Column {
        /*OutlinedTextField(
            value = TextFieldValue(selectedOption),
            onValueChange = {
                selectedOption = it.text
            },
            label = {
                Text(text = "Select an option")
            },
            modifier = Modifier
                .fillMaxWidth()
        )*/


    }
}

@Preview
@Composable
fun DropDownPreview() {
    DropDown()
}
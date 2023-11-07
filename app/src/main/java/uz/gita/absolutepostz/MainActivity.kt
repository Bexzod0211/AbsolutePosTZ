package uz.gita.absolutepostz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.gita.absolutepostz.components.WidgetItem
import uz.gita.absolutepostz.data.models.AnalyticData
import uz.gita.absolutepostz.data.models.CategoryData
import uz.gita.absolutepostz.ui.theme.AbsolutePosTZTheme
import uz.gita.absolutepostz.ui.theme.GluonGrey

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AbsolutePosTZTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    ScreenContent()
                }
            }
        }
    }
}

@Composable
private fun ScreenContent(){
    LazyColumn {
        item {
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(
                    text = "Аналитика",
                    style = TextStyle(
                        color = GluonGrey,
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(R.font.inter_medium)),
                        fontWeight = FontWeight.W600,
                        lineHeight = 28.sp
                    )
                )

                Image(
                    painter = painterResource(id =R.drawable.ic_calendar),
                    contentDescription = "calendar",
                    modifier = Modifier
                        .size(30.dp)
                )
            }
        }

        items(list){item->
            WidgetItem(title = item.title, date =item.date , categoryList = item.categoryList)
        }
    }

}

@Preview
@Composable
fun ScreenPreview() {
    ScreenContent()
}
private val categoryList:List<CategoryData> =  listOf(
    CategoryData(title = "Выручка","96 140P"),
    CategoryData(title = "Прибыль","48 372P"),
    CategoryData(title = "Заказы","76"),
    CategoryData(title = "Гости","131"),
    CategoryData(title = "Среднее","4820")
)


private val list = listOf(
    AnalyticData("Сегодня","22.10.2023", categoryList),
    AnalyticData("Продажи по дням недели","16.10.2023-22.10.2023", categoryList),
    AnalyticData("Выручка по способам оплаты","16.10.2023-22.10.2023", categoryList),
    AnalyticData("Продажи","16.10.2023-22.10.2023", categoryList),
    AnalyticData("Продажи по времени","16.20.2023-22.10.2023", categoryList),
)




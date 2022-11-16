package io.oworld.ohchild

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun dice(dimensions: List<Pair<Int, Int>>) {
    val color = MaterialTheme.colorScheme.primary
    Canvas(
        modifier = Modifier
            .width(100.dp)
            .height(100.dp),
    ) {
        drawRoundRect(
            cornerRadius = CornerRadius(30f, 30f),
            color = color,
            size = Size(width = 100.dp.toPx(), height = 100.dp.toPx()),
            style = Stroke(width = 10.dp.toPx())
        )
        for (dimension in dimensions) {
            drawCircle(
                color = color,
                radius = 10.dp.toPx(),
                center = Offset(dimension.first.dp.toPx(), dimension.second.dp.toPx()),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Dice1() {
    val dot1 = Pair(50, 50)
    val dimensions = listOf(dot1)
    dice(dimensions)
}

@Preview(showBackground = true)
@Composable
fun Dice2() {
    val dot1 = Pair(30, 50)
    val dot2 = Pair(70, 50)
    val dimensions = listOf(dot1, dot2)
    dice(dimensions)
}

@Preview(showBackground = true)
@Composable
fun Dice3() {
    val dot1 = Pair(20, 50)
    val dot2 = Pair(50, 50)
    val dot3 = Pair(80, 50)
    val dimensions = listOf(dot1, dot2, dot3)
    dice(dimensions)
}

@Preview(showBackground = true)
@Composable
fun Dice321() {
    val dot1 = Pair(50, 30)
    val dot2 = Pair(30, 70)
    val dot3 = Pair(70, 70)
    val dimensions = listOf(dot1, dot2, dot3)
    dice(dimensions)
}

@Preview(showBackground = true)
@Composable
fun Dice422() {
    val dot1 = Pair(30, 30)
    val dot2 = Pair(30, 70)
    val dot3 = Pair(70, 30)
    val dot4 = Pair(70, 70)
    val dimensions = listOf(dot1, dot2, dot3, dot4)
    dice(dimensions)
}

@Preview(showBackground = true)
@Composable
fun Dice431() {
    val dot1 = Pair(50, 30)
    val dot2 = Pair(20, 70)
    val dot3 = Pair(50, 70)
    val dot4 = Pair(80, 70)
    val dimensions = listOf(dot1, dot2, dot3, dot4)
    dice(dimensions)
}

@Preview(showBackground = true)
@Composable
fun Dice532() {
    val dot1 = Pair(35, 30)
    val dot2 = Pair(65, 30)
    val dot3 = Pair(20, 70)
    val dot4 = Pair(50, 70)
    val dot5 = Pair(80, 70)
    val dimensions = listOf(dot1, dot2, dot3, dot4, dot5)
    dice(dimensions)
}

@Preview(showBackground = true)
@Composable
fun Dice541() {
    val dot1 = Pair(30, 30)
    val dot2 = Pair(30, 70)
    val dot3 = Pair(50, 50)
    val dot4 = Pair(70, 30)
    val dot5 = Pair(70, 70)
    val dimensions = listOf(dot1, dot2, dot3, dot4, dot5)
    dice(dimensions)
}

@Preview(showBackground = true)
@Composable
fun Dice5C() {
    val dot1 = Pair(50, 20)
    val dot2 = Pair(21, 46)
    val dot3 = Pair(32, 80)
    val dot4 = Pair(69, 80)
    val dot5 = Pair(78, 46)
    val dimensions = listOf(dot1, dot2, dot3, dot4, dot5)
    dice(dimensions)
}

@Preview(showBackground = true)
@Composable
fun Dice633() {
    val dot1 = Pair(20, 30)
    val dot2 = Pair(50, 30)
    val dot3 = Pair(80, 30)
    val dot4 = Pair(20, 70)
    val dot5 = Pair(50, 70)
    val dot6 = Pair(80, 70)
    val dimensions = listOf(dot1, dot2, dot3, dot4, dot5, dot6)
    dice(dimensions)
}

@Preview(showBackground = true)
@Composable
fun Dice642() {
    val dot1 = Pair(40, 30)
    val dot2 = Pair(60, 30)
    val dot3 = Pair(20, 70)
    val dot4 = Pair(40, 70)
    val dot5 = Pair(60, 70)
    val dot6 = Pair(80, 70)
    val dimensions = listOf(dot1, dot2, dot3, dot4, dot5, dot6)
    dice(dimensions)
}

@Preview(showBackground = true)
@Composable
fun Dice6C() {
    val dot1 = Pair(50, 20)
    val dot2 = Pair(22, 35)
    val dot3 = Pair(22, 65)
    val dot4 = Pair(50, 80)
    val dot5 = Pair(78, 65)
    val dot6 = Pair(78, 35)
    val dimensions = listOf(dot1, dot2, dot3, dot4, dot5, dot6)
    dice(dimensions)
}

@Preview(showBackground = true)
@Composable
fun Dice7322() {
    val dot1 = Pair(35, 20)
    val dot2 = Pair(65, 20)
    val dot3 = Pair(35, 50)
    val dot4 = Pair(65, 50)
    val dot5 = Pair(20, 80)
    val dot6 = Pair(50, 80)
    val dot7 = Pair(80, 80)
    val dimensions = listOf(dot1, dot2, dot3, dot4, dot5, dot6, dot7)
    dice(dimensions)
}

@Preview(showBackground = true)
@Composable
fun Dice743() {
    val dot1 = Pair(30, 30)
    val dot2 = Pair(50, 30)
    val dot3 = Pair(70, 30)
    val dot4 = Pair(20, 70)
    val dot5 = Pair(40, 70)
    val dot6 = Pair(60, 70)
    val dot7 = Pair(80, 70)
    val dimensions = listOf(dot1, dot2, dot3, dot4, dot5, dot6, dot7)
    dice(dimensions)
}

@Preview(showBackground = true)
@Composable
fun Dice7C() {
    val dot1 = Pair(50, 20)
    val dot2 = Pair(22, 35)
    val dot3 = Pair(22, 65)
    val dot4 = Pair(50, 50)
    val dot5 = Pair(50, 80)
    val dot6 = Pair(78, 65)
    val dot7 = Pair(78, 35)
    val dimensions = listOf(dot1, dot2, dot3, dot4, dot5, dot6, dot7)
    dice(dimensions)
}

@Preview(showBackground = true)
@Composable
fun Dice844() {
    val dot1 = Pair(20, 30)
    val dot2 = Pair(40, 30)
    val dot3 = Pair(60, 30)
    val dot4 = Pair(80, 30)
    val dot5 = Pair(20, 70)
    val dot6 = Pair(40, 70)
    val dot7 = Pair(60, 70)
    val dot8 = Pair(80, 70)
    val dimensions = listOf(dot1, dot2, dot3, dot4, dot5, dot6, dot7, dot8)
    dice(dimensions)
}

@Preview(showBackground = true)
@Composable
fun Dice8332() {
    val dot1 = Pair(35, 20)
    val dot2 = Pair(65, 20)
    val dot3 = Pair(20, 50)
    val dot4 = Pair(50, 50)
    val dot5 = Pair(80, 50)
    val dot6 = Pair(20, 80)
    val dot7 = Pair(50, 80)
    val dot8 = Pair(80, 80)
    val dimensions = listOf(dot1, dot2, dot3, dot4, dot5, dot6, dot7, dot8)
    dice(dimensions)
}

@Preview(showBackground = true)
@Composable
fun Dice8C() {
    val dot1 = Pair(37, 20)
    val dot2 = Pair(20, 37)
    val dot3 = Pair(20, 63)
    val dot4 = Pair(37, 80)
    val dot5 = Pair(63, 80)
    val dot6 = Pair(80, 63)
    val dot7 = Pair(80, 37)
    val dot8 = Pair(63, 20)
    val dimensions = listOf(dot1, dot2, dot3, dot4, dot5, dot6, dot7, dot8)
    dice(dimensions)
}

@Preview(showBackground = true)
@Composable
fun Dice9() {
    val dot1 = Pair(20, 20)
    val dot2 = Pair(50, 20)
    val dot3 = Pair(80, 20)
    val dot4 = Pair(20, 50)
    val dot5 = Pair(50, 50)
    val dot6 = Pair(80, 50)
    val dot7 = Pair(20, 80)
    val dot8 = Pair(50, 80)
    val dot9 = Pair(80, 80)
    val dimensions = listOf(dot1, dot2, dot3, dot4, dot5, dot6, dot7, dot8, dot9)
    dice(dimensions)
}
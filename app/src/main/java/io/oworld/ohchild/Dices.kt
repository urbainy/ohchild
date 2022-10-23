package io.oworld.ohchild

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Preview(showBackground = true)
@Composable
fun Dice1() {
    Canvas(
        modifier = Modifier
            .width(100.dp)
            .height(100.dp)
    ) {
        drawRoundRect(
            cornerRadius = CornerRadius(30f, 30f),
            color = Color.Gray,
            size = Size(width = 100.dp.toPx(), height = 100.dp.toPx()),
            style = Stroke(width = 10.dp.toPx())
        )
        drawCircle(Color.Gray, 10.dp.toPx(), Offset(50.dp.toPx(), 50.dp.toPx()))
    }
}

@Preview(showBackground = true)
@Composable
fun Dice2() {
    Canvas(
        modifier = Modifier
            .width(100.dp)
            .height(100.dp)
    ) {
        drawRoundRect(
            cornerRadius = CornerRadius(30f, 30f),
            color = Color.Gray,
            size = Size(width = 100.dp.toPx(), height = 100.dp.toPx()),
            style = Stroke(width = 10.dp.toPx())
        )
        drawCircle(Color.Gray, 10.dp.toPx(), Offset(30.dp.toPx(), 50.dp.toPx()))
        drawCircle(Color.Gray, 10.dp.toPx(), Offset(70.dp.toPx(), 50.dp.toPx()))
    }
}

@Preview(showBackground = true)
@Composable
fun Dice3() {
    Canvas(
        modifier = Modifier
            .width(100.dp)
            .height(100.dp)
    ) {
        drawRoundRect(
            cornerRadius = CornerRadius(30f, 30f),
            color = Color.Gray,
            size = Size(width = 100.dp.toPx(), height = 100.dp.toPx()),
            style = Stroke(width = 10.dp.toPx())
        )
        drawCircle(Color.Gray, 10.dp.toPx(), Offset(20.dp.toPx(), 50.dp.toPx()))
        drawCircle(Color.Gray, 10.dp.toPx(), Offset(50.dp.toPx(), 50.dp.toPx()))
        drawCircle(Color.Gray, 10.dp.toPx(), Offset(80.dp.toPx(), 50.dp.toPx()))
    }
}

@Preview(showBackground = true)
@Composable
fun Dice321() {
    Canvas(
        modifier = Modifier
            .width(100.dp)
            .height(100.dp)
    ) {
        drawRoundRect(
            cornerRadius = CornerRadius(30f, 30f),
            color = Color.Gray,
            size = Size(width = 100.dp.toPx(), height = 100.dp.toPx()),
            style = Stroke(width = 10.dp.toPx())
        )
        drawCircle(Color.Gray, 10.dp.toPx(), Offset(50.dp.toPx(), 30.dp.toPx()))
        drawCircle(Color.Gray, 10.dp.toPx(), Offset(30.dp.toPx(), 70.dp.toPx()))
        drawCircle(Color.Gray, 10.dp.toPx(), Offset(70.dp.toPx(), 70.dp.toPx()))
    }
}

@Preview(showBackground = true)
@Composable
fun Dice422() {
    Canvas(
        modifier = Modifier
            .width(100.dp)
            .height(100.dp)
    ) {
        drawRoundRect(
            cornerRadius = CornerRadius(30f, 30f),
            color = Color.Gray,
            size = Size(width = 100.dp.toPx(), height = 100.dp.toPx()),
            style = Stroke(width = 10.dp.toPx())
        )
        drawCircle(Color.Gray, 10.dp.toPx(), Offset(30.dp.toPx(), 30.dp.toPx()))
        drawCircle(Color.Gray, 10.dp.toPx(), Offset(30.dp.toPx(), 70.dp.toPx()))
        drawCircle(Color.Gray, 10.dp.toPx(), Offset(70.dp.toPx(), 30.dp.toPx()))
        drawCircle(Color.Gray, 10.dp.toPx(), Offset(70.dp.toPx(), 70.dp.toPx()))
    }
}

@Preview(showBackground = true)
@Composable
fun Dice431() {
    Canvas(
        modifier = Modifier
            .width(100.dp)
            .height(100.dp)
    ) {
        drawRoundRect(
            cornerRadius = CornerRadius(30f, 30f),
            color = Color.Gray,
            size = Size(width = 100.dp.toPx(), height = 100.dp.toPx()),
            style = Stroke(width = 10.dp.toPx())
        )
        drawCircle(Color.Gray, 10.dp.toPx(), Offset(50.dp.toPx(), 30.dp.toPx()))
        drawCircle(Color.Gray, 10.dp.toPx(), Offset(20.dp.toPx(), 70.dp.toPx()))
        drawCircle(Color.Gray, 10.dp.toPx(), Offset(50.dp.toPx(), 70.dp.toPx()))
        drawCircle(Color.Gray, 10.dp.toPx(), Offset(80.dp.toPx(), 70.dp.toPx()))
    }
}

@Preview(showBackground = true)
@Composable
fun Dice532() {
    Canvas(
        modifier = Modifier
            .width(100.dp)
            .height(100.dp)
    ) {
        drawRoundRect(
            cornerRadius = CornerRadius(30f, 30f),
            color = Color.Gray,
            size = Size(width = 100.dp.toPx(), height = 100.dp.toPx()),
            style = Stroke(width = 10.dp.toPx())
        )
        drawCircle(Color.Gray, 10.dp.toPx(), Offset(35.dp.toPx(), 30.dp.toPx()))
        drawCircle(Color.Gray, 10.dp.toPx(), Offset(65.dp.toPx(), 30.dp.toPx()))
        drawCircle(Color.Gray, 10.dp.toPx(), Offset(20.dp.toPx(), 70.dp.toPx()))
        drawCircle(Color.Gray, 10.dp.toPx(), Offset(50.dp.toPx(), 70.dp.toPx()))
        drawCircle(Color.Gray, 10.dp.toPx(), Offset(80.dp.toPx(), 70.dp.toPx()))
    }
}

@Preview(showBackground = true)
@Composable
fun Dice541() {
    Canvas(
        modifier = Modifier
            .width(100.dp)
            .height(100.dp)
    ) {
        drawRoundRect(
            cornerRadius = CornerRadius(30f, 30f),
            color = Color.Gray,
            size = Size(width = 100.dp.toPx(), height = 100.dp.toPx()),
            style = Stroke(width = 10.dp.toPx())
        )
        drawCircle(Color.Gray, 10.dp.toPx(), Offset(30.dp.toPx(), 30.dp.toPx()))
        drawCircle(Color.Gray, 10.dp.toPx(), Offset(30.dp.toPx(), 70.dp.toPx()))
        drawCircle(Color.Gray, 10.dp.toPx(), Offset(50.dp.toPx(), 50.dp.toPx()))
        drawCircle(Color.Gray, 10.dp.toPx(), Offset(70.dp.toPx(), 30.dp.toPx()))
        drawCircle(Color.Gray, 10.dp.toPx(), Offset(70.dp.toPx(), 70.dp.toPx()))
    }
}

@Preview(showBackground = true)
@Composable
fun Dice5C() {
    Canvas(
        modifier = Modifier
            .width(100.dp)
            .height(100.dp)
    ) {
        drawRoundRect(
            cornerRadius = CornerRadius(30f, 30f),
            color = Color.Gray,
            size = Size(width = 100.dp.toPx(), height = 100.dp.toPx()),
            style = Stroke(width = 10.dp.toPx())
        )
        drawCircle(Color.Gray, 10.dp.toPx(), Offset(50.dp.toPx(), 20.dp.toPx()))
        drawCircle(Color.Gray, 10.dp.toPx(), Offset(21.dp.toPx(), 46.dp.toPx()))
        drawCircle(Color.Gray, 10.dp.toPx(), Offset(32.dp.toPx(), 80.dp.toPx()))
        drawCircle(Color.Gray, 10.dp.toPx(), Offset(69.dp.toPx(), 80.dp.toPx()))
        drawCircle(Color.Gray, 10.dp.toPx(), Offset(78.dp.toPx(), 46.dp.toPx()))
    }
}

@Preview(showBackground = true)
@Composable
fun Dice633() {
    Canvas(
        modifier = Modifier
            .width(100.dp)
            .height(100.dp)
    ) {
        drawRoundRect(
            cornerRadius = CornerRadius(30f, 30f),
            color = Color.Gray,
            size = Size(width = 100.dp.toPx(), height = 100.dp.toPx()),
            style = Stroke(width = 10.dp.toPx())
        )
        drawCircle(Color.Gray, 10.dp.toPx(), Offset(20.dp.toPx(), 30.dp.toPx()))
        drawCircle(Color.Gray, 10.dp.toPx(), Offset(50.dp.toPx(), 30.dp.toPx()))
        drawCircle(Color.Gray, 10.dp.toPx(), Offset(80.dp.toPx(), 30.dp.toPx()))
        drawCircle(Color.Gray, 10.dp.toPx(), Offset(20.dp.toPx(), 70.dp.toPx()))
        drawCircle(Color.Gray, 10.dp.toPx(), Offset(50.dp.toPx(), 70.dp.toPx()))
        drawCircle(Color.Gray, 10.dp.toPx(), Offset(80.dp.toPx(), 70.dp.toPx()))
    }
}

@Preview(showBackground = true)
@Composable
fun Dice642() {
    Canvas(
        modifier = Modifier
            .width(100.dp)
            .height(100.dp)
    ) {
        drawRoundRect(
            cornerRadius = CornerRadius(30f, 30f),
            color = Color.Gray,
            size = Size(width = 100.dp.toPx(), height = 100.dp.toPx()),
            style = Stroke(width = 10.dp.toPx())
        )
        drawCircle(Color.Gray, 10.dp.toPx(), Offset(40.dp.toPx(), 30.dp.toPx()))
        drawCircle(Color.Gray, 10.dp.toPx(), Offset(60.dp.toPx(), 30.dp.toPx()))
        drawCircle(Color.Gray, 10.dp.toPx(), Offset(20.dp.toPx(), 70.dp.toPx()))
        drawCircle(Color.Gray, 10.dp.toPx(), Offset(40.dp.toPx(), 70.dp.toPx()))
        drawCircle(Color.Gray, 10.dp.toPx(), Offset(60.dp.toPx(), 70.dp.toPx()))
        drawCircle(Color.Gray, 10.dp.toPx(), Offset(80.dp.toPx(), 70.dp.toPx()))
    }
}

@Preview(showBackground = true)
@Composable
fun Dice6C() {
    Canvas(
        modifier = Modifier
            .width(100.dp)
            .height(100.dp)
    ) {
        drawRoundRect(
            cornerRadius = CornerRadius(30f, 30f),
            color = Color.Gray,
            size = Size(width = 100.dp.toPx(), height = 100.dp.toPx()),
            style = Stroke(width = 10.dp.toPx())
        )
        drawCircle(Color.Gray, 10.dp.toPx(), Offset(50.dp.toPx(), 20.dp.toPx()))
        drawCircle(Color.Gray, 10.dp.toPx(), Offset(22.dp.toPx(), 35.dp.toPx()))
        drawCircle(Color.Gray, 10.dp.toPx(), Offset(22.dp.toPx(), 65.dp.toPx()))
        drawCircle(Color.Gray, 10.dp.toPx(), Offset(50.dp.toPx(), 80.dp.toPx()))
        drawCircle(Color.Gray, 10.dp.toPx(), Offset(78.dp.toPx(), 65.dp.toPx()))
        drawCircle(Color.Gray, 10.dp.toPx(), Offset(78.dp.toPx(), 35.dp.toPx()))
    }
}

@Preview(showBackground = true)
@Composable
fun Dice7322() {
    Canvas(
        modifier = Modifier
            .width(100.dp)
            .height(100.dp)
    ) {
        drawRoundRect(
            cornerRadius = CornerRadius(30f, 30f),
            color = Color.Gray,
            size = Size(width = 100.dp.toPx(), height = 100.dp.toPx()),
            style = Stroke(width = 10.dp.toPx())
        )
        drawCircle(Color.Gray, 10.dp.toPx(), Offset(35.dp.toPx(), 20.dp.toPx()))
        drawCircle(Color.Gray, 10.dp.toPx(), Offset(65.dp.toPx(), 20.dp.toPx()))
        drawCircle(Color.Gray, 10.dp.toPx(), Offset(35.dp.toPx(), 50.dp.toPx()))
        drawCircle(Color.Gray, 10.dp.toPx(), Offset(65.dp.toPx(), 50.dp.toPx()))
        drawCircle(Color.Gray, 10.dp.toPx(), Offset(20.dp.toPx(), 80.dp.toPx()))
        drawCircle(Color.Gray, 10.dp.toPx(), Offset(50.dp.toPx(), 80.dp.toPx()))
        drawCircle(Color.Gray, 10.dp.toPx(), Offset(80.dp.toPx(), 80.dp.toPx()))
    }
}

@Preview(showBackground = true)
@Composable
fun Dice7C() {
    Canvas(
        modifier = Modifier
            .width(100.dp)
            .height(100.dp)
    ) {
        drawRoundRect(
            cornerRadius = CornerRadius(30f, 30f),
            color = Color.Gray,
            size = Size(width = 100.dp.toPx(), height = 100.dp.toPx()),
            style = Stroke(width = 10.dp.toPx())
        )
        drawCircle(Color.Gray, 10.dp.toPx(), Offset(50.dp.toPx(), 20.dp.toPx()))
        drawCircle(Color.Gray, 10.dp.toPx(), Offset(22.dp.toPx(), 35.dp.toPx()))
        drawCircle(Color.Gray, 10.dp.toPx(), Offset(22.dp.toPx(), 65.dp.toPx()))
        drawCircle(Color.Gray, 10.dp.toPx(), Offset(50.dp.toPx(), 50.dp.toPx()))
        drawCircle(Color.Gray, 10.dp.toPx(), Offset(50.dp.toPx(), 80.dp.toPx()))
        drawCircle(Color.Gray, 10.dp.toPx(), Offset(78.dp.toPx(), 65.dp.toPx()))
        drawCircle(Color.Gray, 10.dp.toPx(), Offset(78.dp.toPx(), 35.dp.toPx()))
    }
}

@Preview(showBackground = true)
@Composable
fun Dice844() {
    Canvas(
        modifier = Modifier
            .width(100.dp)
            .height(100.dp)
    ) {
        drawRoundRect(
            cornerRadius = CornerRadius(30f, 30f),
            color = Color.Gray,
            size = Size(width = 100.dp.toPx(), height = 100.dp.toPx()),
            style = Stroke(width = 10.dp.toPx())
        )
        drawCircle(Color.Gray, 10.dp.toPx(), Offset(20.dp.toPx(), 30.dp.toPx()))
        drawCircle(Color.Gray, 10.dp.toPx(), Offset(40.dp.toPx(), 30.dp.toPx()))
        drawCircle(Color.Gray, 10.dp.toPx(), Offset(60.dp.toPx(), 30.dp.toPx()))
        drawCircle(Color.Gray, 10.dp.toPx(), Offset(80.dp.toPx(), 30.dp.toPx()))
        drawCircle(Color.Gray, 10.dp.toPx(), Offset(20.dp.toPx(), 70.dp.toPx()))
        drawCircle(Color.Gray, 10.dp.toPx(), Offset(40.dp.toPx(), 70.dp.toPx()))
        drawCircle(Color.Gray, 10.dp.toPx(), Offset(60.dp.toPx(), 70.dp.toPx()))
        drawCircle(Color.Gray, 10.dp.toPx(), Offset(80.dp.toPx(), 70.dp.toPx()))
    }
}

@Preview(showBackground = true)
@Composable
fun Dice8332() {
    Canvas(
        modifier = Modifier
            .width(100.dp)
            .height(100.dp)
    ) {
        drawRoundRect(
            cornerRadius = CornerRadius(30f, 30f),
            color = Color.Gray,
            size = Size(width = 100.dp.toPx(), height = 100.dp.toPx()),
            style = Stroke(width = 10.dp.toPx())
        )
        drawCircle(Color.Gray, 10.dp.toPx(), Offset(35.dp.toPx(), 20.dp.toPx()))
        drawCircle(Color.Gray, 10.dp.toPx(), Offset(65.dp.toPx(), 20.dp.toPx()))
        drawCircle(Color.Gray, 10.dp.toPx(), Offset(20.dp.toPx(), 50.dp.toPx()))
        drawCircle(Color.Gray, 10.dp.toPx(), Offset(50.dp.toPx(), 50.dp.toPx()))
        drawCircle(Color.Gray, 10.dp.toPx(), Offset(80.dp.toPx(), 50.dp.toPx()))
        drawCircle(Color.Gray, 10.dp.toPx(), Offset(20.dp.toPx(), 80.dp.toPx()))
        drawCircle(Color.Gray, 10.dp.toPx(), Offset(50.dp.toPx(), 80.dp.toPx()))
        drawCircle(Color.Gray, 10.dp.toPx(), Offset(80.dp.toPx(), 80.dp.toPx()))
    }
}

@Preview(showBackground = true)
@Composable
fun Dice8C() {
    Canvas(
        modifier = Modifier
            .width(100.dp)
            .height(100.dp)
    ) {
        drawRoundRect(
            cornerRadius = CornerRadius(30f, 30f),
            color = Color.Gray,
            size = Size(width = 100.dp.toPx(), height = 100.dp.toPx()),
            style = Stroke(width = 10.dp.toPx())
        )
        drawCircle(Color.Gray, 10.dp.toPx(), Offset(37.dp.toPx(), 20.dp.toPx()))
        drawCircle(Color.Gray, 10.dp.toPx(), Offset(20.dp.toPx(), 37.dp.toPx()))
        drawCircle(Color.Gray, 10.dp.toPx(), Offset(20.dp.toPx(), 63.dp.toPx()))
        drawCircle(Color.Gray, 10.dp.toPx(), Offset(37.dp.toPx(), 80.dp.toPx()))
        drawCircle(Color.Gray, 10.dp.toPx(), Offset(63.dp.toPx(), 80.dp.toPx()))
        drawCircle(Color.Gray, 10.dp.toPx(), Offset(80.dp.toPx(), 63.dp.toPx()))
        drawCircle(Color.Gray, 10.dp.toPx(), Offset(80.dp.toPx(), 37.dp.toPx()))
        drawCircle(Color.Gray, 10.dp.toPx(), Offset(63.dp.toPx(), 20.dp.toPx()))
    }
}

@Preview(showBackground = true)
@Composable
fun Dice9() {
    Canvas(
        modifier = Modifier
            .width(100.dp)
            .height(100.dp)
    ) {
        drawRoundRect(
            cornerRadius = CornerRadius(30f, 30f),
            color = Color.Gray,
            size = Size(width = 100.dp.toPx(), height = 100.dp.toPx()),
            style = Stroke(width = 10.dp.toPx())
        )
        drawCircle(Color.Gray, 10.dp.toPx(), Offset(20.dp.toPx(), 20.dp.toPx()))
        drawCircle(Color.Gray, 10.dp.toPx(), Offset(50.dp.toPx(), 20.dp.toPx()))
        drawCircle(Color.Gray, 10.dp.toPx(), Offset(80.dp.toPx(), 20.dp.toPx()))
        drawCircle(Color.Gray, 10.dp.toPx(), Offset(20.dp.toPx(), 50.dp.toPx()))
        drawCircle(Color.Gray, 10.dp.toPx(), Offset(50.dp.toPx(), 50.dp.toPx()))
        drawCircle(Color.Gray, 10.dp.toPx(), Offset(80.dp.toPx(), 50.dp.toPx()))
        drawCircle(Color.Gray, 10.dp.toPx(), Offset(20.dp.toPx(), 80.dp.toPx()))
        drawCircle(Color.Gray, 10.dp.toPx(), Offset(50.dp.toPx(), 80.dp.toPx()))
        drawCircle(Color.Gray, 10.dp.toPx(), Offset(80.dp.toPx(), 80.dp.toPx()))
    }
}
package io.oworld.ohchild

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.view.HapticFeedbackConstants
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.oworld.ohchild.ui.theme.OhChildTheme
import java.text.DecimalFormat


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OhChildTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val useDices = IntArray(6)
                    useDices[0] = (1..2).shuffled().last()
                    useDices[1] = (1..2).shuffled().last()
                    useDices[2] = (1..3).shuffled().last()
                    useDices[3] = (1..3).shuffled().last()
                    useDices[4] = (1..2).shuffled().last()
                    useDices[5] = (1..3).shuffled().last()
                    MainPage(useDices)
                }
            }
        }
    }
}

@Composable
fun NumberButton(number: Int, onSelect: () -> Unit) {
    val view = LocalView.current
    Button(
        onClick = { view.performHapticFeedback(HapticFeedbackConstants.KEYBOARD_TAP); onSelect() },
        modifier = Modifier
            .size(50.dp),
        contentPadding = PaddingValues(0.dp),
    ) {
        Text(text = "$number", fontSize = 30.sp)
    }
}

@Composable
fun NumberPad(selectedNumber: Int, onSelectedNumberChange: (Int) -> Unit) {
    ElevatedCard {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("$selectedNumber", fontSize = 40.sp)
            Row(modifier = Modifier.padding(all = 8.dp)) {
                Column {
                    (0..4).forEach { number ->
                        NumberButton(
                            number = number,
                            onSelect = { onSelectedNumberChange(number) }
                        )
                    }
                }
                Column {
                    (5..9).forEach { number ->
                        NumberButton(
                            number = number,
                            onSelect = { onSelectedNumberChange(number) }
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainPage(useDices: IntArray) {
    var defaultMinute by remember { mutableStateOf(3L) }
    var defaultSecond by remember { mutableStateOf(0L) }
    var currentMinute by remember { mutableStateOf(defaultMinute) }
    var currentSecond by remember { mutableStateOf(defaultSecond) }
    var timeDialog by remember { mutableStateOf(false) }
    var isRunning by remember { mutableStateOf(false) }
    var totalCorrectAnswers by remember { mutableStateOf(0) }
    var a by remember { mutableStateOf(1) }
    var b by remember { mutableStateOf(1) }
    var correctAnswer by remember { mutableStateOf(2) }
    var hundredNumber by remember { mutableStateOf(0) }
    var decadeNumber by remember { mutableStateOf(0) }
    var individualNumber by remember { mutableStateOf(0) }

    val view = LocalView.current
    val mContext = LocalContext.current
    val applicationContext = mContext.applicationContext

    val db = AppDatabase.getInstance(applicationContext)
    val recordDao = db.recordDao()
    val recordRepo = RecordRepo(recordDao)

    if (timeDialog) {
        var setMinute by remember { mutableStateOf(defaultMinute.toFloat()) }
        var setSecond by remember { mutableStateOf(defaultSecond.toFloat()) }
        AlertDialog(
            onDismissRequest = {
                timeDialog = false
            },
            title = {
                Text("Timer Setting")
            },
            text = {
                Column {
                    Text("Set Minute to: ${setMinute.toInt()}")
                    Slider(
                        value = setMinute,
                        onValueChange = { setMinute = it },
                        valueRange = 0f..60f
                    )
                    Text("Set Second to: ${setSecond.toInt()}")
                    Slider(
                        value = setSecond,
                        onValueChange = { setSecond = it },
                        valueRange = 0f..59f,
                    )
                }
            },
            confirmButton = {
                TextButton(onClick = {
                    defaultMinute = setMinute.toLong()
                    defaultSecond = setSecond.toLong()
                    currentMinute = defaultMinute
                    currentSecond = defaultSecond
                    timeDialog = false
                }) {
                    Text("Ok")
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    timeDialog = false
                }) {
                    Text("Cancel")
                }
            },
        )
    }

    var countDownTimer: CountDownTimer? by remember { mutableStateOf(null) }
    fun startCountDownTimer() {
        val currentMilliSecond = currentMinute * 60 * 1000 + currentSecond * 1000
        countDownTimer = object : CountDownTimer(currentMilliSecond, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                currentMinute = millisUntilFinished / 1000 / 60
                currentSecond = millisUntilFinished / 1000 % 60
            }

            override fun onFinish() {
                val duration = (defaultMinute + defaultSecond / 60f)
                val timestamp = System.currentTimeMillis()
                val record =
                    Record(
                        date = timestamp,
                        duration = duration,
                        score = totalCorrectAnswers
                    )
                recordRepo.insert(record)
                MediaPlayer.create(mContext, R.raw.finish).start()
                isRunning = false
            }
        }.start()
        MediaPlayer.create(mContext, R.raw.start).start()
    }

    fun pauseCountDownTimer() {
        if (countDownTimer != null) {
            countDownTimer!!.cancel()
        }
    }

    fun resetCountDownTimer() {
        if (countDownTimer != null) {
            countDownTimer!!.cancel()
            countDownTimer = null
            currentMinute = defaultMinute
            currentSecond = defaultSecond
            hundredNumber = 0
            decadeNumber = 0
            individualNumber = 0
        }
    }

    fun prepareCalcNumber() {
        a = (1..9).shuffled().last()
        b = (1..9).shuffled().last()
        correctAnswer = a + b
    }

    Column(
        modifier = Modifier.padding(all = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ElevatedButton(
            onClick = { mContext.startActivity(Intent(mContext, RecordActivity::class.java)) },
            enabled = !isRunning,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_baseline_bar_chart_24),
                contentDescription = "Records",
                modifier = Modifier.size(40.dp)
            )
            Text(
                text = "Records",
                fontSize = 40.sp
            )
        }

        Spacer(Modifier.height(8.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            ElevatedButton(
                onClick = { timeDialog = true },
                enabled = !isRunning
            ) {
                val f = DecimalFormat("00")
                Icon(
                    painter = painterResource(R.drawable.ic_baseline_alarm_24),
                    contentDescription = "Timer",
                    modifier = Modifier.size(40.dp)
                )
                Text(
                    text = "${f.format(currentMinute)}:${f.format(currentSecond)}",
                    fontSize = 40.sp
                )
            }

            Spacer(Modifier.weight(1f))
            IconButton(onClick = {
                isRunning = false
                resetCountDownTimer()
                totalCorrectAnswers = 0
            }) {
                Icon(
                    painter = painterResource(R.drawable.ic_baseline_stop_circle_24),
                    contentDescription = "Stop",
                    modifier = Modifier.size(60.dp)
                )
            }
            if (isRunning) {
                IconButton(onClick = { isRunning = !isRunning; pauseCountDownTimer() }) {
                    Icon(
                        painter = painterResource(R.drawable.ic_baseline_pause_circle_outline_24),
                        contentDescription = "Pause",
                        modifier = Modifier.size(60.dp)
                    )
                }
            } else {
                IconButton(onClick = {
                    isRunning = !isRunning
                    startCountDownTimer()
                    prepareCalcNumber()
                }) {
                    Icon(
                        painter = painterResource(R.drawable.ic_baseline_play_circle_outline_24),
                        contentDescription = "Start",
                        modifier = Modifier.size(60.dp)
                    )
                }
            }
        }



        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                "Total Correct Answers: ",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
            )
            AnimatedContent(
                targetState = totalCorrectAnswers,
                transitionSpec = {
                    slideInVertically { it } + fadeIn() with slideOutVertically { -it } + fadeOut()
                }
            ) {
                Text(
                    "$totalCorrectAnswers",
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Red,
                )
            }

        }

        Spacer(Modifier.weight(1f))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
            if (isRunning) {
                when (a) {
                    1 -> Dice1()
                    2 -> Dice2()
                    3 -> {
                        when (useDices[0]) {
                            1 -> Dice3()
                            2 -> Dice321()
                        }
                    }
                    4 -> {
                        when (useDices[1]) {
                            1 -> Dice422()
                            2 -> Dice431()
                        }
                    }
                    5 -> {
                        when (useDices[2]) {
                            1 -> Dice532()
                            2 -> Dice541()
                            3 -> Dice5C()
                        }
                    }
                    6 -> {
                        when (useDices[3]) {
                            1 -> Dice633()
                            2 -> Dice642()
                            3 -> Dice6C()
                        }
                    }
                    7 -> {
                        when (useDices[4]) {
                            1 -> Dice7322()
                            2 -> Dice7C()
                        }
                    }
                    8 -> {
                        when (useDices[5]) {
                            1 -> Dice844()
                            2 -> Dice8332()
                            3 -> Dice8C()
                        }
                    }
                    9 -> Dice9()
                }
                when (b) {
                    1 -> Dice1()
                    2 -> Dice2()
                    3 -> {
                        when (useDices[0]) {
                            1 -> Dice3()
                            2 -> Dice321()
                        }
                    }
                    4 -> {
                        when (useDices[1]) {
                            1 -> Dice422()
                            2 -> Dice431()
                        }
                    }
                    5 -> {
                        when (useDices[2]) {
                            1 -> Dice532()
                            2 -> Dice541()
                            3 -> Dice5C()
                        }
                    }
                    6 -> {
                        when (useDices[3]) {
                            1 -> Dice633()
                            2 -> Dice642()
                            3 -> Dice6C()
                        }
                    }
                    7 -> {
                        when (useDices[4]) {
                            1 -> Dice7322()
                            2 -> Dice7C()
                        }
                    }
                    8 -> {
                        when (useDices[5]) {
                            1 -> Dice844()
                            2 -> Dice8332()
                            3 -> Dice8C()
                        }
                    }
                    9 -> Dice9()
                }
            }
        }
        Spacer(Modifier.height(100.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            NumberPad(
                selectedNumber = hundredNumber,
                onSelectedNumberChange = { hundredNumber = it })
            NumberPad(
                selectedNumber = decadeNumber,
                onSelectedNumberChange = { decadeNumber = it })
            NumberPad(
                selectedNumber = individualNumber,
                onSelectedNumberChange = { individualNumber = it })
        }

        Spacer(Modifier.height(8.dp))

        ElevatedButton(
            onClick = {
                view.performHapticFeedback(HapticFeedbackConstants.KEYBOARD_TAP)
                val childAnswer = hundredNumber * 100 + decadeNumber * 10 + individualNumber
                if (childAnswer == correctAnswer) {
                    totalCorrectAnswers++
                    MediaPlayer.create(mContext, R.raw.correct_answer).start()
                    hundredNumber = 0
                    decadeNumber = 0
                    individualNumber = 0
                    prepareCalcNumber()
                } else {
                    MediaPlayer.create(mContext, R.raw.wrong_answer).start()
                }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = isRunning,
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_baseline_check_24),
                contentDescription = "Check",
                modifier = Modifier.size(40.dp)
            )
            Text("Check", fontSize = 40.sp)
        }
    }
}

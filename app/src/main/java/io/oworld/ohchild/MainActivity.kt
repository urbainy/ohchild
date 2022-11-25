package io.oworld.ohchild

import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.view.HapticFeedbackConstants
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.preferences.preferencesDataStore
import io.oworld.ohchild.ui.theme.OhChildTheme
import io.oworld.ohchild.ui.theme.Red500
import kotlinx.coroutines.flow.Flow
import java.text.DecimalFormat
import kotlin.math.pow

private val Context.dataStore by preferencesDataStore(name = "user_preferences")

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val useDices = IntArray(6)
        useDices[0] = (1..2).shuffled().last()
        useDices[1] = (1..2).shuffled().last()
        useDices[2] = (1..3).shuffled().last()
        useDices[3] = (1..3).shuffled().last()
        useDices[4] = (1..3).shuffled().last()
        useDices[5] = (1..3).shuffled().last()
        setContent {
            OhChildTheme {
                MainPage(useDices)
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun MainPage(useDices: IntArray) {
    var defaultMinute by remember { mutableStateOf(3L) }
    var defaultSecond by remember { mutableStateOf(0L) }
    var currentMinute by remember { mutableStateOf(defaultMinute) }
    var currentSecond by remember { mutableStateOf(defaultSecond) }
    var timeDialog by remember { mutableStateOf(false) }
    var isRunning by remember { mutableStateOf(false) }
    var isPause by remember { mutableStateOf(false) }
    var finished by remember { mutableStateOf(false) }
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
    val userPreferencesRepo = UserPreferencesRepo(mContext.dataStore)
    val userPreferencesFlow: Flow<UserPreferences> = userPreferencesRepo.userPreferencesFlow

    val db = AppDatabase.getInstance(applicationContext)
    val recordDao = db.recordDao()
    val recordRepo = RecordRepo(recordDao)

    var stage by remember { mutableStateOf(0) }
    val stageIcons = listOf(
        Pair(R.drawable.ic_baseline_casino_24, "Dice"),
        Pair(R.drawable.ic_baseline_add_circle_outline_24, "Plus"),
        Pair(R.drawable.ic_outline_remove_circle_outline_24, "Minus"),
        Pair(R.drawable.ic_round_highlight_off_24, "Multiply"),
        Pair(R.drawable.ic_baseline_block_24, "Divide")
    )
    var digit by remember { mutableStateOf(1) }

    val userPreference by userPreferencesFlow.collectAsState(
        initial = UserPreferences(
            stage = 0,
            digit = 1,
            minute = 3L,
            second = 0L
        )
    )
    stage = userPreference.stage
    digit = userPreference.digit
    defaultMinute = userPreference.minute
    defaultSecond = userPreference.second
    currentMinute = userPreference.minute
    currentSecond = userPreference.second

    Scaffold { paddingValue ->
        val paddingStart = paddingValue.calculateLeftPadding(LayoutDirection.Ltr) + 8.dp
        val paddingTop = paddingValue.calculateTopPadding() + 8.dp
        val paddingEnd = paddingValue.calculateRightPadding(LayoutDirection.Ltr) + 8.dp
        val paddingBottom = paddingValue.calculateBottomPadding() + 8.dp

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
                        userPreferencesRepo.updateMinute(defaultMinute)
                        userPreferencesRepo.updateSecond(defaultSecond)
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
                            stage = stage,
                            digit = digit,
                            score = totalCorrectAnswers
                        )
                    recordRepo.insert(record)
                    MediaPlayer.create(mContext, R.raw.finish).start()
                    isRunning = false
                    finished = true
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
                totalCorrectAnswers = 0
                hundredNumber = 0
                decadeNumber = 0
                individualNumber = 0
            }
        }

        fun prepareCalcNumber() {
            when (stage) {
                0, 1 -> {
                    a = (1 until (10.toFloat().pow(digit)).toInt()).shuffled().last()
                    b = (1 until (10.toFloat().pow(digit)).toInt()).shuffled().last()
                    correctAnswer = a + b
                }
                2 -> {
                    a = (1 until (10.toFloat().pow(digit)).toInt()).shuffled().last()
                    b = (1 until (10.toFloat().pow(digit)).toInt()).shuffled().last()
                    if (a < b) {
                        val t = a
                        a = b
                        b = t
                    }
                    correctAnswer = a - b
                }
                3 -> {
                    do {
                        a = (1 until (10.toFloat().pow(digit)).toInt()).shuffled().last()
                        b = (1 until (10.toFloat().pow(digit)).toInt()).shuffled().last()
                    } while (a * b > 999)
                    correctAnswer = a * b
                }
                4 -> {
                    do {
                        a = (1 until (10.toFloat().pow(digit)).toInt()).shuffled().last()
                        b = (1 until (10.toFloat().pow(digit)).toInt()).shuffled().last()
                    } while ((a < b) || (a % b != 0))
                    correctAnswer = a / b
                }
            }
        }

        Column(
            modifier = Modifier.padding(
                start = paddingStart,
                top = paddingTop,
                end = paddingEnd,
                bottom = paddingBottom
            ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                ElevatedButton(
                    onClick = { timeDialog = true },
                    enabled = !isRunning && !isPause,
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
                if (finished) {
                    IconButton(onClick = {
                        finished = false
                        resetCountDownTimer()
                    }, modifier = Modifier.size(60.dp)) {
                        Icon(
                            Icons.Rounded.Refresh,
                            contentDescription = "Reset",
                            modifier = Modifier.size(60.dp),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                } else {
                    IconButton(onClick = {
                        isRunning = false
                        isPause = false
                        resetCountDownTimer()
                    }, modifier = Modifier.size(60.dp)) {
                        Icon(
                            painter = painterResource(R.drawable.ic_baseline_stop_circle_24),
                            contentDescription = "Stop",
                            modifier = Modifier.size(60.dp),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                    if (isRunning && !isPause) {
                        IconButton(
                            onClick = { isPause = true; pauseCountDownTimer() },
                            modifier = Modifier.size(60.dp)
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.ic_baseline_pause_circle_outline_24),
                                contentDescription = "Pause",
                                modifier = Modifier.size(60.dp),
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    } else {
                        IconButton(onClick = {
                            isRunning = true
                            isPause = false
                            startCountDownTimer()
                            prepareCalcNumber()
                        }, modifier = Modifier.size(60.dp)) {
                            Icon(
                                painter = painterResource(R.drawable.ic_baseline_play_circle_outline_24),
                                contentDescription = "Start",
                                modifier = Modifier.size(60.dp),
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }
            }

            Spacer(Modifier.height(8.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    "Correct Answers: ",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.width(220.dp)
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
                        color = Red500,
                    )
                }

                Spacer(Modifier.weight(1f))

                IconButton(
                    onClick = {
                        mContext.startActivity(
                            Intent(
                                mContext,
                                RecordActivity::class.java
                            )
                        )
                    },
                    enabled = !isRunning,
                    modifier = Modifier.size(60.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_round_history_edu_24),
                        contentDescription = "History",
                        modifier = Modifier.size(60.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )
                }

            }

            Spacer(Modifier.height(8.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    "Digit Difficulty:",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.width(220.dp)
                )
                Slider(
                    value = digit.toFloat(),
                    onValueChange = {
                        digit = it.toInt(); userPreferencesRepo.updateDigit(it.toInt())
                    },
                    valueRange = 1f..3f,
                    steps = 1,
                    thumb = {
                        ElevatedButton(
                            onClick = {},
                            modifier = Modifier.size(30.dp),
                            contentPadding = PaddingValues(0.dp)
                        ) { Text("$digit", fontSize = 25.sp) }
                    },
                    enabled = (stage != 0) && (!isRunning)
                )
            }

            Spacer(Modifier.height(16.dp))

            TabRow(selectedTabIndex = stage) {
                stageIcons.forEachIndexed { index, icon ->
                    Tab(
                        selected = stage == index,
                        onClick = {
                            if (index == 0) {
                                digit = 1
                                userPreferencesRepo.updateDigit(1)
                            }
                            stage = index
                            userPreferencesRepo.updateStage(index)
                        },
                        icon = {
                            Icon(
                                painter = painterResource(icon.first),
                                contentDescription = icon.second,
                                modifier = Modifier.size(40.dp)
                            )
                        },
                        enabled = !isRunning
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                when (stage) {
                    0 -> {
                        if (isRunning) {
                            ChooseDice(useDices = useDices, i = a)
                            ChooseDice(useDices = useDices, i = b)
                        } else {
                            Dice1()
                            Text("+", fontSize = 100.sp, color = MaterialTheme.colorScheme.primary)
                            Dice1()
                        }
                    }
                    1 -> {
                        if (isRunning) {
                            Text(
                                "$a + $b",
                                fontSize = 100.sp,
                                color = MaterialTheme.colorScheme.primary
                            )
                        } else {
                            Text(
                                "1 + 1",
                                fontSize = 100.sp,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                    2 -> {
                        if (isRunning) {
                            Text(
                                "$a - $b",
                                fontSize = 100.sp,
                                color = MaterialTheme.colorScheme.primary
                            )
                        } else {
                            Text(
                                "1 - 1",
                                fontSize = 100.sp,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                    3 -> {
                        if (isRunning) {
                            Text(
                                "$a × $b",
                                fontSize = 100.sp,
                                color = MaterialTheme.colorScheme.primary,
                            )
                        } else {
                            Text(
                                "1 × 1",
                                fontSize = 100.sp,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                    4 -> {
                        if (isRunning) {
                            var textSize by remember { mutableStateOf(100.sp) }
                            Text(
                                "$a / $b",
                                fontSize = textSize,
                                color = MaterialTheme.colorScheme.primary,
                                overflow = TextOverflow.Ellipsis,
                                onTextLayout = { result ->
                                    if (result.isLineEllipsized(0)) {
                                        textSize /= 1.3
                                    }
                                }
                            )
                        } else {
                            Text(
                                "1 / 1",
                                fontSize = 100.sp,
                                color = MaterialTheme.colorScheme.primary,
                            )
                        }
                    }
                }
            }

            Spacer(Modifier.weight(1f))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
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

            Button(
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
                modifier = Modifier
                    .fillMaxWidth(),
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
            Text(
                "$selectedNumber",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
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

@Composable
fun ChooseDice(useDices: IntArray, i: Int) {
    when (i) {
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
                2 -> Dice743()
                3 -> Dice7C()
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


@Preview
@Composable
fun MainPreview() {
    OhChildTheme(darkTheme = false) {
        MainPage(IntArray(6))
    }
}

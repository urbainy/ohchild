package io.oworld.ohchild

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.oworld.ohchild.ui.theme.OhChildTheme
import java.text.DateFormat
import java.util.*

class RecordActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OhChildTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RecordPage()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecordPage() {
    val mContext = LocalContext.current
    val activity = mContext as Activity
    val db = AppDatabase.getInstance(mContext.applicationContext)
    val recordDao = db.recordDao()
    val recordRepo = RecordRepo(recordDao)
    val liveRecords = recordRepo.allRecords
    val records by liveRecords.observeAsState(listOf())
    var clearRecordsDialog by remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "History Records")
                },
                navigationIcon = {
                    IconButton(onClick = { activity.finish() }) {
                        Icon(Icons.Filled.ArrowBack, "backIcon")
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White,
                ),
            )
        }, content = {
            if (clearRecordsDialog) {
                AlertDialog(
                    onDismissRequest = {
                        clearRecordsDialog = false
                    },
                    title = {
                        Text("Clear All Records Confirmation")
                    },
                    text = {
                        Text("Do you confirm to clear all records?")
                    },
                    confirmButton = {
                        TextButton(onClick = {
                            recordRepo.deleteAll()
                            clearRecordsDialog = false
                        }) {
                            Text("Yes")
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = {
                            clearRecordsDialog = false
                        }) {
                            Text("No")
                        }
                    },
                )
            }
            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
            ) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        "Date",
                        modifier = Modifier.width(180.dp),
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        "Duration",
                        modifier = Modifier.width(120.dp),
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        "Score",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.End,
                        fontWeight = FontWeight.Bold
                    )
                }
                LazyColumn(modifier = Modifier.fillMaxWidth()) {
                    items(items = records, itemContent = { record ->
                        val dateString = DateFormat.getDateInstance().format(Date(record.date))
                        val durationString = String.format("%.2f", record.duration)
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(
                                dateString,
                                modifier = Modifier.width(180.dp),
                                textAlign = TextAlign.Start
                            )
                            Text(
                                durationString,
                                modifier = Modifier.width(120.dp),
                                textAlign = TextAlign.Start
                            )
                            Text(
                                record.score.toString(),
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.End
                            )
                        }
                    })
                }
                Spacer(Modifier.weight(1f))
                ElevatedButton(
                    onClick = { clearRecordsDialog = true },
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_baseline_delete_forever_24),
                        contentDescription = "Delete",
                        modifier = Modifier.size(40.dp)
                    )
                    Text("Clear All Records", fontSize = 30.sp)
                }
            }
        }
    )
}


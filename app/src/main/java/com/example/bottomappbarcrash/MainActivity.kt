package com.example.bottomappbarcrash

import android.content.Context
import android.content.ContextWrapper
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.BottomAppBarScrollBehavior
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bottomappbarcrash.ui.theme.BottomAppBarCrashTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BottomAppBarCrashTheme {

                val exitAlwaysScrollBehavior = BottomAppBarDefaults.exitAlwaysScrollBehavior()

                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .nestedScroll(
                            exitAlwaysScrollBehavior.nestedScrollConnection
                        ),
                    bottomBar = {
                        BottomBar(exitAlwaysScrollBehavior)
                    }
                ) { innerPadding ->
                    Box(
                        modifier = Modifier.padding(innerPadding),
                    ) {
                        Scaffold(
                            topBar = {
                                TopAppBar(
                                    title = {
                                        Text("This is a TopAppBar")
                                    },
                                )
                            }
                        ) { innerPaddingLvl2 ->
                            LazyColumn(
                                verticalArrangement = Arrangement.spacedBy(8.dp),
                                modifier = Modifier.padding(innerPaddingLvl2),
                            ) {
                                items((0..100).toList()) {
                                    Text(
                                        text = "This is item no. $it",
                                        modifier = Modifier
                                            .padding(horizontal = 16.dp)
                                            .fillMaxWidth(),
                                    )
                                }
                            }
                        }

                        var systemUiHidden by remember { mutableStateOf(false) }
                        val window = LocalContext.current.getActivity()?.window
                        TextButton(
                            onClick = {
                                if (systemUiHidden) {
                                    window?.let {
                                        WindowCompat.getInsetsController(window, it.decorView).show(WindowInsetsCompat.Type.systemBars())
                                    }
                                    systemUiHidden = false
                                } else {
                                    window?.let {
                                        WindowCompat.getInsetsController(window, it.decorView).hide(WindowInsetsCompat.Type.systemBars())
                                    }
                                    systemUiHidden = true
                                }
                            },
                            modifier = Modifier.align(Alignment.BottomEnd),
                        ) {
                            if (systemUiHidden) {
                                Text("Show System UI")
                            } else {
                                Text("Hide System UI")
                            }
                        }
                    }
                }

            }
        }
    }
}

fun Context.getActivity(): ComponentActivity? = when (this) {
    is ComponentActivity -> this
    is ContextWrapper -> baseContext.getActivity()
    else -> null
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomBar(
    scrollBehavior: BottomAppBarScrollBehavior,
) {
    BottomAppBar(
        scrollBehavior = scrollBehavior,
    ) {
        Text(
            text = "This is a hiding BottomAppBar",
        )
    }
}

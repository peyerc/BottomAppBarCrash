```
[libraries]
...
androidx-material3 = "androidx.compose.material3:material3:1.4.0-alpha10"
```

```
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
  ...
```

https://github.com/user-attachments/assets/909525e0-d666-4d98-be13-cfb8fa857b95


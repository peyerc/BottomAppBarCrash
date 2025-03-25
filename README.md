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


# CreateTemplate.kt

### Interface

```kotlin
fun concatSrcDist(
    srcMap: Map<String, String>,
    distMap: Map<String, String>,
    ignoreNotFoundKey: Boolean = false
): Map<String, String>

fun CreateTemplateUtil.copyByFileMapping(
    fileMapping: Map<String, String>,
    overwrite: Boolean = false,
    onProgress: (String, String) -> Unit = { _, _ -> }
)
```

### Project environment

Open folder `CreateTemplateKt/` by IntelliJ IDEA

### License

MIT

import java.io.File


fun CreateTemplateUtil.copyByFileMapping(
    fileMapping: Map<String, String>,
    overwrite: Boolean = false,
    onProgress: (String, String) -> Unit = { _, _ -> }
) {
    for ((src, dist) in fileMapping) {
        onProgress(src, dist)
        val srcFile = File(src)
        val distFile = File(dist)
        if (srcFile.isDirectory) {
            srcFile.copyRecursively(distFile, overwrite = overwrite)
            continue
        }
        if (dist.endsWith('/')) {
            srcFile.copyTo(File(distFile, srcFile.name), overwrite = overwrite)
        } else {
            srcFile.copyTo(distFile, overwrite = overwrite)
        }
    }
}

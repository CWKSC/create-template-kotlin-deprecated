import java.io.File

const val configFolderPath = ".create-template/"
const val srcJsonFilePath = configFolderPath + "src.json"
const val distJsonFilePath = configFolderPath + "dist.json"

val configFolder = File(configFolderPath)
val srcJsonFile = File(srcJsonFilePath)
val distJsonFile = File(distJsonFilePath)

lateinit var srcMap: MutableMap<String, String>
lateinit var distMap: MutableMap<String, String>

fun main() {

    println()

    // Check config folder exists //
    // If not, create it and exit program
    if (!configFolder.exists()) {
        println("Directory .create-template/ not exists")
        println("Create .create-template/ template")
        println()
        configFolder.mkdir()
        srcJsonFile.createNewFile()
        distJsonFile.createNewFile()
        return
    }

    // Check src.json and dist.json file exists //
    if (!srcJsonFile.exists()) {
        println("[Error] File $srcJsonFilePath not exists")
        println()
        return
    }
    if (!distJsonFile.exists()) {
        println("[Error] File $distJsonFilePath not exists")
        println()
        return
    }

    // Read src.json file and convert to map  //
    try {
        srcMap = CascadeJsonUtil.fromStringToMap(srcJsonFile.readText())
        if(srcMap.isEmpty()) {
            println("[Error] Src Json $srcJsonFilePath is empty json object")
            println()
            return
        }
    } catch (e: IllegalArgumentException) {
        println("[Error] File $srcJsonFilePath not valid json")
        println()
        return
    }

    // Read dist.json file and convert to map //
    try {
        distMap = CascadeJsonUtil.fromStringToMap(distJsonFile.readText())
        if(distMap.isEmpty()) {
            println("[Error] Dist Json $distJsonFilePath is empty json object")
            println()
            return
        }
    } catch (e: IllegalArgumentException) {
        println("[Error] File $distJsonFilePath not valid json")
        println()
        return
    }

    // Concat src and dist with key to file mapping //
    val reversedDistMap = distMap.entries.associateBy({ it.value }) { it.key }
    val fileMapping = HashMap(srcMap)
    fileMapping.forEach { (key, value) ->
        if (!reversedDistMap.containsKey(value)) {
            println("[Error] Key $key not found in $distJsonFilePath")
            println()
            return
        }
        fileMapping[key] = reversedDistMap[value]
    }

    // Confirm all src file or directory exists //
    val srcSet = srcMap.keys
    val maxLength = srcSet.map { it.length }.maxOf { it }
    val nonExistSrcList = srcSet.filter { !File(it).exists() }
    if (nonExistSrcList.isNotEmpty()) {
        println("[Error] Following files or directories not exists:")
        nonExistSrcList.forEach {
            println("\t${it.padEnd(maxLength)}")
        }
        println()
        return
    }

    // Copying from src to dist //
    fileMapping.forEach { (src, dist) ->
        val srcFile = File(src)
        val distFile = File(dist)
        val srcIsDirectory = srcFile.isDirectory
        if (srcIsDirectory) {
            srcFile.copyRecursively(distFile, overwrite = true)
        } else {
            srcFile.copyTo(distFile, overwrite = true)
        }
        println("${src.padEnd(maxLength)} -> $dist")
    }

    println("[Done]")
    println()

}
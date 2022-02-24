object CreateTemplateUtil {

    fun concatSrcDist(
        srcMap: Map<String, String>,
        distMap: Map<String, String>,
        ignoreNotFoundKey: Boolean = false
    ): Map<String, String> {
        val reversedDistMap = distMap.entries.associateBy({ it.value }) { it.key }
        if (!ignoreNotFoundKey) {
            val nonFoundRightSide = srcMap.values.filter { !reversedDistMap.containsKey(it) }
            if (nonFoundRightSide.isNotEmpty()) {
                throw IllegalArgumentException("Right side of srcMap $nonFoundRightSide not found in right side of distMap")
            }
            return srcMap.mapValues { reversedDistMap.getValue(it.value) }
        }
        return srcMap.mapValues { reversedDistMap[it.value] ?: it.value }
    }

}
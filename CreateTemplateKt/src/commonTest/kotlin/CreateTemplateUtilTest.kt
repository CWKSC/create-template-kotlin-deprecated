import CreateTemplateUtil.concatSrcDist
import kotlin.test.*

internal class CreateTemplateUtilTest {

    private val testcase1SrcMap = mapOf(
        "file1" to "value1",
        "file2" to "value2",
        "dir1/file3" to "value3",
        "dir1/file4" to "value4",
        "dir2/" to "dir2-key",
        "dir2/file5" to "value5",
        "dir2/file6" to "value6",
        "dir2/dir3/file7" to "value7",
        "dir2/dir3/file8" to "value8"
    )
    private val testcase1DistMap = mapOf(
        "output/byFile1" to "value1",
        "output/byFile2" to "value2",
        "output/byFile3" to "value3",
        "output/byFile4" to "value4",
        "output/byDir2" to "dir2-key",
        "output/byFile5" to "value5",
        "output/byFile6" to "value6",
        "output/byFile7" to "value7",
        "output/byFile8" to "value8"
    )
    private val testcase1ExpectedResult = mapOf(
        "file1" to "output/byFile1",
        "file2" to "output/byFile2",
        "dir1/file3" to "output/byFile3",
        "dir1/file4" to "output/byFile4",
        "dir2/" to "output/byDir2",
        "dir2/file5" to "output/byFile5",
        "dir2/file6" to "output/byFile6",
        "dir2/dir3/file7" to "output/byFile7",
        "dir2/dir3/file8" to "output/byFile8"
    )

    @Test
    fun concatSrcDist_testcase1() {
        assertEquals(testcase1ExpectedResult, concatSrcDist(testcase1SrcMap, testcase1DistMap))
        assertEquals(
            testcase1ExpectedResult,
            concatSrcDist(testcase1SrcMap, testcase1DistMap, ignoreNotFoundKey = true)
        )
    }

    @Test
    fun concatSrcDist_throw() {
        assertFailsWith<IllegalArgumentException> { concatSrcDist(mapOf("src" to "no this key in dist"), mapOf()) }
    }

    @Test
    fun concatSrcDist_ignoreNotFoundKey() {
        assertEquals(
            mapOf("src" to "no this key in dist"),
            concatSrcDist(mapOf("src" to "no this key in dist"), mapOf(), ignoreNotFoundKey = true)
        )
    }

}
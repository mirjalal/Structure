package az.talmir.app.structure.kernel.helpers

import az.talmir.app.structure.core.helpers.FailRemoteResponse
import az.talmir.app.structure.shared.mappers.Mapper
import az.talmir.app.structure.shared.models.Result
import org.junit.Test
import org.junit.Assert.assertEquals
import org.junit.Assert.assertSame

internal class ExtensionFunctionsTest {

    private object CustomMapper : Mapper<Int, String> {
        override fun map(`in`: Int): String =
            `in`.toString().toCharArray().joinToString(", ")
    }

    @Test
    fun `test Result_Success case is success`() {
        val given = Result.Success(105)
        val expected = Result.Success("1, 0, 5")
        assertEquals(expected, given.toPresentation(CustomMapper))
    }

    @Test
    fun `test Result_Failure case is success`() {
        val expected = Result.Failure(FailRemoteResponse(Unit, 200))
        assertSame(expected::class, expected.toPresentation(CustomMapper)::class)
    }

    @Test
    fun `test Result_Error case is success`() {
        val expected = Result.Error(Throwable("this is error in test case"))
        assertSame(expected::class, expected.toPresentation(CustomMapper)::class)
    }

    @Test
    fun `test Result_Unauthorized case is success`() {
        val expected = Result.Unauthorized
        assertSame(expected::class, expected.toPresentation(CustomMapper)::class)
    }

    @Test
    fun `test Result_NotFound case is success`() {
        val expected = Result.NotFound
        assertSame(expected::class, expected.toPresentation(CustomMapper)::class)
    }
}

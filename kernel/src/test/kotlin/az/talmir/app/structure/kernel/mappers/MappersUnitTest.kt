package az.talmir.app.structure.kernel.mappers

import az.talmir.app.structure.core.helpers.Local
import az.talmir.app.structure.core.helpers.Remote
import az.talmir.app.structure.kernel.models.presentation.Presentation
import org.junit.Assert.assertEquals
import org.junit.Test

class MappersUnitTest {

    private data class PresentationClass(val prop: Int) : Presentation()
    private data class RemoteClass(val prop: Int) : Remote()
    private data class LocalClass(val prop: Int) : Local()

    private object L2P : Local2PresentationMapper<LocalClass, PresentationClass>() {
        override fun map(`in`: LocalClass): PresentationClass =
            PresentationClass(prop = `in`.prop)
    }

    private object L2R : Local2RemoteMapper<LocalClass, RemoteClass>() {
        override fun map(`in`: LocalClass): RemoteClass =
            RemoteClass(prop = `in`.prop)
    }

    private object P2L : Presentation2LocalMapper<PresentationClass, LocalClass>() {
        override fun map(`in`: PresentationClass): LocalClass =
            LocalClass(prop = `in`.prop)
    }

    private object P2R : Presentation2RemoteMapper<PresentationClass, RemoteClass>() {
        override fun map(`in`: PresentationClass): RemoteClass =
            RemoteClass(prop = `in`.prop)
    }

    private object R2P : Remote2PresentationMapper<RemoteClass, PresentationClass>() {
        override fun map(`in`: RemoteClass): PresentationClass =
            PresentationClass(prop = `in`.prop)
    }

    @Test
    fun `test L2P mapping is correct`() {
        val given = LocalClass(prop = 12)
        val expected = PresentationClass(prop = 12)
        assertEquals(expected, L2P.map(given))
    }

    @Test
    fun `test L2R mapping is correct`() {
        val given = LocalClass(prop = 34)
        val expected = RemoteClass(prop = 34)
        assertEquals(expected, L2R.map(given))
    }

    @Test
    fun `test P2L mapping is correct`() {
        val given = PresentationClass(prop = 56)
        val expected = LocalClass(prop = 56)
        assertEquals(expected, P2L.map(given))
    }

    @Test
    fun `test P2R mapping is correct`() {
        val given = PresentationClass(prop = 78)
        val expected = RemoteClass(prop = 78)
        assertEquals(expected, P2R.map(given))
    }

    @Test
    fun `test R2P mapping is correct`() {
        val given = RemoteClass(prop = 90)
        val expected = PresentationClass(prop = 90)
        assertEquals(expected, R2P.map(given))
    }
}

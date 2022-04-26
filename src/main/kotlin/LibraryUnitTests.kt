
import org.junit.Test
import org.mockito.Mockito

class LibraryUnitTests {
    @Test(expected = IllegalStateException::class)
    fun whenBookNotAvailable() {
        val mockBookService = Mockito.mock(BookService::class.java)
        Mockito.`when`(mockBookService.inStock(100)).thenReturn(false)

        val manager = LendBookManager(mockBookService)
        manager.checkout(100, 1)
    }

    @Test
    fun whenBookAvailable() {
        val mockBookService = Mockito.mock(BookService::class.java)
        Mockito.`when`(mockBookService.inStock(100)).thenReturn(true)

        val manager = LendBookManager(mockBookService)
        manager.checkout(100, 1)
        Mockito.verify(mockBookService).lend(100, 1)
    }
}
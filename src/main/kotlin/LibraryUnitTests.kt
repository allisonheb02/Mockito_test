
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito


class LibraryUnitTests {
    @Test(expected = IllegalStateException::class)
    fun whenBookNotAvailable() {
        val mockBookService = Mockito.mock(BookService::class.java)
        Mockito.`when`(mockBookService.inStock(100)).thenReturn(false)

        val manager = LibraryManager(mockBookService)
        manager.checkout(100, 1)
    }

    @Test
    fun whenBookAvailable() {
        val mockBookService = Mockito.mock(BookService::class.java)
        Mockito.`when`(mockBookService.inStock(100)).thenReturn(true)

        val manager = LibraryManager(mockBookService)
        manager.checkout(100, 1)
        Mockito.verify(mockBookService).lendBook(100, 1)
    }

    @Test
    fun availableFromTitleLookUp() {
        val mockBookService = Mockito.mock(BookService::class.java)
        Mockito.`when`(mockBookService.lookUpId("Project Hail Mary")).thenReturn(100)
        Mockito.`when`(mockBookService.inStock(100)).thenReturn(true)

        val manager = LibraryManager(mockBookService)
        manager.checkoutTitle("Project Hail Mary",1)
        Mockito.verify(mockBookService).lendBook(100,1)
    }

    @Test(expected = IllegalStateException::class)
    fun notAvailableFromTitleLookUp() {
        val mockBookService = Mockito.mock(BookService::class.java)
        Mockito.`when`(mockBookService.lookUpId("Project Hail Mary")).thenReturn(100)
        Mockito.`when`(mockBookService.inStock(100)).thenThrow(IllegalStateException("Not available"))

        val manager = LibraryManager(mockBookService)
        manager.checkoutTitle("Project Hail Mary", 1)
    }

    @Test
    fun returnBookHaveBook() {
        val mockBookService = Mockito.mock(BookService::class.java)
        Mockito.`when`(mockBookService.myBook(1)).thenReturn(100)

        val manager = LibraryManager(mockBookService)
        manager.checkIn(1)
        Mockito.verify(mockBookService).returnBook(100,1)
    }

    @Test(expected = IllegalStateException::class)
    fun returnBookDoNotHaveBook() {
        val mockBookService = Mockito.mock(BookService::class.java)
        Mockito.`when`(mockBookService.myBook(1)).thenReturn(0)

        val manager = LibraryManager(mockBookService)
        manager.checkIn(1)
    }

    @Test
    fun fromMemberLookUpBook() {
        val mockBookService = Mockito.mock(BookService::class.java)
        Mockito.`when`(mockBookService.myBook(1)).thenReturn(100)
        Mockito.`when`(mockBookService.lookUpTitle(100)).thenReturn("Project Hail Mary")

        val manager = LibraryManager(mockBookService)
        val bookTitle = manager.whatBook(1)
        Mockito.verify(mockBookService).lookUpTitle(100)
        Mockito.verify(mockBookService).myBook(1)
        assertEquals("Project Hail Mary", bookTitle)
    }
}
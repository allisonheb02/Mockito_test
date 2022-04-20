fun main(args: Array<String>) {
    println("Hello World!")
    mock()
    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")
    val manager = LendBookManager(mockBookService)
    manager.checkout(100, 1)
    Mockito.verify(mockBookService).lend(100, 1)
}

class LendBookManager(private val bookService:BookService) {
    fun checkout(bookId: Int, memberId: Int) {
        if(bookService.inStock(bookId)) {
            bookService.lend(bookId, memberId)
        } else {
            throw IllegalStateException("Book is not available")
        }
    }
}

fun mock() {
    val mockBookService = Mockito.mock(BookService::class.java)
    Mockito.`when`(mockBookService. inStock(100)).thenReturn(true)
}

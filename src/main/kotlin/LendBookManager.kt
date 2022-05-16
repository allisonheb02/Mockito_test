

class LibraryManager(val bookService: BookService) {
    fun checkout(bookId: Int, memberId: Int) {
        if(bookService.inStock(bookId)) {
            bookService.lendBook(bookId, memberId)
        } else {
            throw IllegalStateException("Book is not available")
        }
    }
    fun checkoutTitle(bookTitle: String, memberId: Int) {
        val bookId = bookService.lookUpId(bookTitle)
        checkout(bookId,memberId)
    }

    fun checkIn(memberId: Int) {
        val bookId = bookService.myBook(memberId)
        if(bookId != 0){
            bookService.returnBook(bookId,memberId)
        } else {
            throw IllegalStateException("No book checked out")
        }
    }

    fun whatBook(memberId: Int): String {
        val bookId = bookService.myBook(memberId)
        return bookService.lookUpTitle(bookId)
    }
}
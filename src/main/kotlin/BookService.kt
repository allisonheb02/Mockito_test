interface BookService {
    fun inStock(bookId: Int): Boolean
    fun lookUpId(bookTitle: String): Int
    fun myBook(memberId: Int): Int
    fun lookUpTitle(bookId: Int): String

    fun returnBook(bookId: Int, memberId: Int)
    fun lendBook(bookId: Int, memberId: Int)
}

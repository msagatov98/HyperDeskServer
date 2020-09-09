import java.util.*
import kotlin.collections.ArrayList

/**
 * The task is to implement the Library interface (you can do that in this file directly).
 * - No database or any other storage is required, just store data in memory
 * - No smart search is required, use String method contains (case sensitive/insensitive - does not matter)
 * - Performance optimizations are optional
 */

class Book(val id: String, val name: String, val author: String) : Library {

    private val bookList: ArrayList<Book> = ArrayList()

    override fun addNewBook(book: Book): Boolean {
        if (bookList.isEmpty()) {
            bookList.add(book)
            return true
        } else {

            var exists = false

            for (addedBook in bookList) {
                if (addedBook.id == book.id) {
                    exists = true
                    break
                }
            }

            if (!exists) {
                bookList.add(book)
                return true
            }
        }

        return false
    }

    override fun deleteBook(id: String): Boolean {
        var dBook: Book? = null

        for (book in bookList) {
            if (book.id == id) {
                dBook = book
                break
            }
        }

        if (bookList.isNotEmpty()) {
            if (dBook != null) bookList.remove(dBook)
            return true
        }

        return false
    }

    override fun listBooksByName(searchString: String): Collection<String> {

        val books = ArrayList<Book>()
        val listBooksByName: ArrayList<String> = ArrayList()

        var count = 0

        for (book in bookList) {
            if (searchString == book.name) {
                books.add(book)
                count++
            }

            if (count == 10) break
        }

        books.sortBy { author }

        for (book in books)
            listBooksByName.add(book.name)

        return listBooksByName
    }

    override fun listBooksByAuthor(searchString: String): List<String> {
        val listBooksByAuthor: ArrayList<String> = ArrayList()
        val otherList : ArrayList<Book> = ArrayList()

        var count = 0

        for (book in bookList) {

            if (book.author.contains(searchString)) {
                otherList.add(book)
                count++
            }

            if (count == 10) break
        }

        otherList.sortBy { author }

        for (name in otherList)
            listBooksByAuthor.add(name.name)

        return listBooksByAuthor
    }
}

interface Library {
	/**
	  Adds a new book object to the Library.
	  @param book: book to add to the Library
	  @return false if the book with same id already exists in the Library, true – otherwise.
	*/
    fun addNewBook(book: Book): Boolean

	/**
	  Deletes the book with the specified id from the Library.
	  @return true if the book with same id existed in the Library, false – otherwise.
	*/
    fun deleteBook(id: String): Boolean

	/**
	  @return 10 book names containing the specified string. If there are several books with the same name, author's name is appended to book's name.
	*/
    fun listBooksByName(searchString: String): Collection<String>

	/**
	  @return 10 book names whose author contains the specified string, ordered by authors.
	*/
    fun listBooksByAuthor(searchString: String): List<String>
}

fun main() {
    test(Book("1", "Title", "Name"))
}

fun test(lib: Library) {
    assertTrue(!lib.deleteBook("1"))
    assertTrue(lib.addNewBook(Book("1", "1", "Lex")))
    assertTrue(!lib.addNewBook(Book("1", "1", "Lex")))
    assertTrue(lib.deleteBook("1"))
    assertTrue(lib.addNewBook(Book("4", "Name1", "Lex3")))
    assertTrue(lib.addNewBook(Book("3", "Name3", "Lex2")))
    assertTrue(lib.addNewBook(Book("2", "Name2", "Lex2")))
    assertTrue(lib.addNewBook(Book("1", "Name1", "Lex1")))
    val byNames: Collection<String> = lib.listBooksByName("Name")
    assertTrue(byNames.contains("Lex3 - Name1"))
    assertTrue(byNames.contains("Name2"))
    assertTrue(byNames.contains("Name3"))
    assertTrue(byNames.contains("Lex1 - Name1"))
    val byAuthor: List<String> = lib.listBooksByAuthor("Lex")
    assertTrue(byAuthor[0] == "Name1")
    assertTrue(byAuthor[1] == "Name2" || byAuthor[1] == "Name3")
    assertTrue(byAuthor[2] == "Name2" || byAuthor[2] == "Name3")
    assertTrue(byAuthor[3] == "Name1")
}

fun assertTrue(condition: Boolean) {
    if (!condition) {
        throw Error("assert failed")
    }
}

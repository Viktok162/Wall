import WallService.add
import WallService.posts
import WallService.update

fun main() {

    add()
    add()
    add()
    add()
    add()

    println("posts size is = ${posts.size}")

    val sample = Post(3, copyright = "Super", likes = Post.Like(500, canLike = true))

    println(update(sample))
}

data class Post(
    var id: Int = 0,
    val ownerId: Int = 0,
    val formId: Int = 0,
    val createdBy: Int = 0,
    val text: String = "Hello!",
    val copyright: String = "protected",
    val postType: String = "brief story",
    val canPin: Boolean = false,
    val canDelete: Boolean = false,
    val canEdit: Boolean = false,
    val likes: Like
) {
    init {
        println("\nInit: New instance created, id = $id")
    }

    fun showDataPost() {
        println(
            "Данные объекта post: id = $id, ownerId = $ownerId, formId = $formId," +
                    " copyright = $copyright, canPin = $canPin, $text, likes = ${likes.count}, canLike = ${likes.canLike}"
        )
    }

    class Like(
        val count: Int = 0,
        val userLikes: Boolean = false,
        val canLike: Boolean = false,
        val canPublish: Boolean = false
    )

}

object WallService {
    var posts = emptyArray<Post>()
    private var number: Int = 0

    fun add(): Post {
        number++
        val post = Post(number, likes = Post.Like())
        posts += post
        return posts.last()
    }

    fun update(post: Post): Boolean {
        for (p in posts) {
            if (post.id == p.id) {
                posts[post.id - 1] = post.copy()
                posts[post.id - 1].showDataPost()

                return true
            }
        }
        return false
    }
}

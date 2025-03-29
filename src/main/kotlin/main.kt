import WallService.add
import WallService.postsAny
import WallService.sizeOfPosts
import WallService.update

fun main() {
    val postIni = Post(0, likes = Post.Like(0, false, false, false))

    add(postIni)
    add(postIni)
    add(postIni)
    add(postIni)
    add(postIni)

    println("posts size is = ${sizeOfPosts()}")
    postsAny(1)

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

    fun add(post: Post): Post {
        number++
        val p = post.copy(id = number)
        posts += p
        return posts.last()
    }

    fun update(post: Post): Boolean {
        for (i in posts.indices) {
            if (post.id == posts[i].id) {
                posts[i] = post
                //posts[i].showDataPost()
                return true
            }
        }
        return false
    }

    fun sizeOfPosts(): Int {
        return posts.size
    }

    fun postsAny(index: Int) {
        println("Element $index of posts:")
        posts[index].showDataPost()
    }
}

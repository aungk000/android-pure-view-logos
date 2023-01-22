package me.ako.logos.model

data class Logo(
    var id: Int = 0,
    var name: String,
    var tag: TAG
)

enum class TAG {
    NETFLIX,
    MICROSOFT,
    YOUTUBE
}
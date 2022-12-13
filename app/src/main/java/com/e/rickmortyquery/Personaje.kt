package com.e.rickmortyquery

data class Personaje (
    val id: String,
    val name:String,
    val status:String,
    val type:String,
    val gender:String,
    val origin:Origin,
    val location:Location,
    val image:String

        )
data class Origin(
    val name: String,
    val url:String

)
data class Location(
    val name:String,
    val url:String
)


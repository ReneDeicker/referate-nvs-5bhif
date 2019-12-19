package at.htl.model

data class Quote(
    var id: Int?
) {
    lateinit var text: String
    lateinit var person: Person
}

data class Person(
    var id: Int?
){
    lateinit var name: String
}
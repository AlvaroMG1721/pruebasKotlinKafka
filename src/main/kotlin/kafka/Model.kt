package kafka

import java.util.*

object Model {
    data class Person(
            val firstName: String,
            val lastName: String,
            val birthDate: Date
    )
}
package at.htl

import at.htl.model.Person
import at.htl.model.Quote
import io.ktor.application.*
import io.ktor.features.ContentNegotiation
import io.ktor.html.respondHtml
import io.ktor.jackson.jackson
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.routing
import kotlinx.html.body
import kotlinx.html.h1
import kotlinx.html.p
import kotlinx.html.ul

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

val persons = mutableListOf(
    Person(1).apply { name="Erik Mayrhofer" },
    Person(2).apply { name="Test" }
)

val quotes = mutableListOf(
    Quote(1).apply{text="SomeQuoteTest"; person=persons[0]},
    Quote(2).apply{text="QuoteTest"; person=persons[0]}
)

fun insertQuote(quote: Quote): Quote{
    quote.person = persons.single { it.id == quote.person.id }
    quote.id = (quotes.mapNotNull { it.id }.max() ?: 0) + 1
    quotes += quote
    return quote
}

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    install(ContentNegotiation){
        jackson {}
    }
    routing {
        get("/quotes") {
            call.respond(quotes)
        }
        post("/quotes"){
            val quote = call.receive<Quote>()
            insertQuote(quote)
            call.respond(quote)
        }
        get("/quoteui") {
            call.respondHtml {
                body {
                    h1 {
                        +"Quotes"
                    }
                    quotes.forEach {
                        ul {
                            p {
                            +"Quote: ${it.text}"
                            }
                        }
                    }
                }
            }
        }
    }
}


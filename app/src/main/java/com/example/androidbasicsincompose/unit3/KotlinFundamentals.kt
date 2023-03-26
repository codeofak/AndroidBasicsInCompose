package com.example.androidbasicsincompose.unit3

fun main() {

    //println(question1)

    //println(Quiz.progressText)

    Quiz().printProgressBar()

    val quiz = Quiz()
    printQuiz()
}

data class Question<T>(
    val questionText: String,
    val answer: T,
    val difficulty: Difficulty
)

enum class Difficulty {
    EASY,
    MEDIUM,
    HARD
}


class Quiz: ProgressPrintable {
    val question1 = Question<String>("Name","Abdul",Difficulty.MEDIUM)
    val question2 = Question<Boolean>("Student?",true,Difficulty.EASY)
    val question3 = Question<Int>("Age?",27,Difficulty.HARD)

    companion object StudentProgress {
        var total: Int = 10
        var answered: Int = 3
    }

    override val progressText: String
        get() = "$answered of $total answered"

    override fun printProgressBar() {
        repeat(answered) { print("▓") }
        repeat(total - answered) {print("▒")}
        println()
        println(progressText)
    }
}

//val Quiz.StudentProgress.progressText: String
//    get() = "$answered of $total answered"


interface ProgressPrintable{
    val progressText: String
    fun printProgressBar()
}

fun printQuiz() {

    Quiz().apply {
        question1.let {
            println(it.questionText)
            println(it.answer)
            println(it.difficulty)
        }
        println()
        question2.let {
            println(it.questionText)
            println(it.answer)
            println(it.difficulty)
        }
        println()
        question3.let {
            println(it.questionText)
            println(it.answer)
            println(it.difficulty)
        }
        println()
    }

}
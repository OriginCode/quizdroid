package edu.uw.ischool.kaiyaw.quizdroid

import android.app.Application

interface TopicRepository {
    fun getTopics(): List<Topic>
}

class PlainTextTopicRepository : TopicRepository {
    private val topics = listOf(
        Topic(
            name = "Math",
            shortDesc = "Some number stuff",
            longDesc = "Some questions about mathematics. Mathematics is the science and study of quality, structure, space, and change.",
            icon_id = android.R.drawable.ic_dialog_info,
            questions = listOf(
                Question(
                    title = "1 + 1 = ?",
                    choices = listOf("2", "3", "4", "5"),
                    answerIdx = 0,
                ),
                Question(
                    title = "2 * 4 = ?",
                    choices = listOf("0", "124", "23", "8"),
                    answerIdx = 3,
                ),
                Question(
                    title = "124 / 124 = ?",
                    choices = listOf("3", "1", "2", "9"),
                    answerIdx = 1,
                ),
            )
        ),
        Topic(
            name = "Physics",
            shortDesc = "Some physical stuff",
            longDesc = "Some questions about physics. Physics is the branch of science that deals with the structure of matter and how the fundamental constituents of the universe interact.",
            icon_id = android.R.drawable.ic_dialog_info,
            questions = listOf(
                Question(
                    title = "What is the value of the gravitational acceleration on Earth?",
                    choices = listOf("5.6 m/s^2", "9.8 m/s^2", "6.3 m/s^2", "10.3 m/s^2"),
                    answerIdx = 1,
                ),
                Question(
                    title = "Which one is Newton's second law of motion?",
                    choices = listOf("F = mg", "F = ma^2", "F = ma", "F = ma/g"),
                    answerIdx = 2,
                ),
            )
        ),
        Topic(
            name = "Marvel Super Heroes",
            shortDesc = "Some super hero stuff",
            longDesc = "Some questions about Marvel Super Heroes. The Marvel Super Heroes[1] is an American animated television series starring five comic book superheroes from Marvel Comics.",
            icon_id = android.R.drawable.ic_dialog_info,
            questions = listOf(
                Question(
                    title = "When did the first episode of the Marvel Super Heroes release?",
                    choices = listOf("October 16, 1967", "September 1, 1966", "October 1, 1967", "September 12, 1966"),
                    answerIdx = 1,
                ),
                Question(
                    title = "What's the full name of the Scarlet Spider?",
                    choices = listOf("Benjamin \"Ben\" Reilly", "Peter Benjamin Parker", "Steven Rogers", "Anthony Edward Stark"),
                    answerIdx = 0,
                ),
            )
        ),
    )
    override fun getTopics(): List<Topic> {
        return topics
    }
}
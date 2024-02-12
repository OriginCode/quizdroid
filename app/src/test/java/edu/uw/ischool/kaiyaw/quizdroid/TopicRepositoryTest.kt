package edu.uw.ischool.kaiyaw.quizdroid

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class TopicRepositoryTest {
    @Test
    fun test_plaintext() {
        val topics = PlainTextTopicRepository().getTopics()
        assertEquals(topics.first().name, "Math")
    }

    @Test
    fun test_plaintext_size() {
        val topics = PlainTextTopicRepository().getTopics()
        assertEquals(topics.size, 3)
    }
}
package edu.uw.ischool.kaiyaw.quizdroid

import android.graphics.drawable.Drawable
import java.io.Serializable

data class Topic(
    val name: String,
    val shortDesc: String,
    val longDesc: String,
    val icon_id: Int,
    val questions: List<Question>
) : Serializable
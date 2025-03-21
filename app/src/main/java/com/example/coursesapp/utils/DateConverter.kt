package com.example.coursesapp.utils

import com.google.gson.*
import java.lang.reflect.Type

class DateConverter : JsonSerializer<Long>, JsonDeserializer<Long> {
    override fun serialize(src: Long?, typeOfSrc: Type?, context: JsonSerializationContext?): JsonElement {
        return JsonPrimitive(DateUtils.formatDate(src ?: 0L))
    }

    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): Long {
        return when (json) {
            is JsonPrimitive -> {
                if (json.isString) {
                    DateUtils.parseDate(json.asString)
                } else {
                    json.asLong
                }
            }
            else -> 0L
        }
    }
} 
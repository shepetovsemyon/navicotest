package ru.shepetov.navicotest

import org.koin.core.context.GlobalContext.get
import java.util.*

fun <T> search(
    query: String,
    completeList: List<T>,
    fieldsList: (T) -> List<String>
): List<T> {
    val locale = Locale.getDefault()
    val queries = query.trim().toLowerCase(locale).split("\\s+".toRegex())

    return if (queries.isEmpty() || queries.size == 1 && queries[0].isBlank()) completeList else {
        completeList.filter {
            val fields = fieldsList(it)
            if (fields.all { field -> queries.size > field.split("\\s+".toRegex()).size }) {
                return@filter false
            }

            var contains = true
            queries.forEach { query ->
                if (
                    contains && fields.all { field ->
                        !field.toLowerCase(locale).contains(query)
                    }
                ) contains = false
            }

            contains
        }
    }
}

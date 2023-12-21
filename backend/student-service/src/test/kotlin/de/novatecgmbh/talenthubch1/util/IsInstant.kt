package de.novatecgmbh.talenthubch1.util

import org.hamcrest.BaseMatcher
import org.hamcrest.Description
import java.time.Instant
import java.time.format.DateTimeParseException

class IsInstant : BaseMatcher<String>() {
    override fun describeTo(description: Description?) {
        description?.appendText("The timestamp to be parseable to an Instant")
    }

    override fun matches(actual: Any?): Boolean =
        when (actual) {
            is Instant -> true
            is String -> {
                try {
                    Instant.parse(actual)
                    true
                } catch (_: DateTimeParseException) {
                    false
                }
            }

            else -> false
        }
}

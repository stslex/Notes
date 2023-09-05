package com.stslex93.notes.core.navigation.model

enum class AppDestination(vararg val argsNames: String) {
    HOME,
    NOTE_EDIT("noteId", "isEdit"),
    LABEL_EDIT("noteIds"),
    UNDEFINED;

    val route: String
        get() = StringBuilder()
            .append(name, SEPARATOR_ROUTE_NAME, TAG_ROUTE)
            .toString()
            .lowercase()

    val navigationRoute: String
        get() = "$route${argsNames.argumentsRoute}"

    private val Array<out String>.argumentsRoute: String
        get() = if (isNotEmpty()) joinToString(
            separator = SEPARATOR_ROUTE,
            prefix = PREFIX_ROUTE,
            postfix = POSTFIX_ROUTE
        ) else ""

    companion object {

        private const val SEPARATOR_ROUTE_NAME = "_"
        private const val SEPARATOR_ROUTE = "}/{"
        private const val PREFIX_ROUTE = "/{"
        private const val POSTFIX_ROUTE = "}"

        private const val TAG_ROUTE = "route"
    }
}
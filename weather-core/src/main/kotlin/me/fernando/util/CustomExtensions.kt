package me.fernando.util

fun String.sanitizeForTelegram() = this.replace("([.\\-\\\\(\\\\)|])".toRegex(), "\\\\$1")

fun String.trimLeadingSpaces() = this.replace("(?m)(^ +)".toRegex(), "").trim()

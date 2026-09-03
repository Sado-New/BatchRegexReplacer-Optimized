package com.example.batchregexreplacer

import org.junit.Test
import org.junit.Assert.*

class RegexReplacerTest {
    @Test
    fun testSimpleReplacement() {
        val result = RegexReplacer.replace("hello world", "world", "Android")
        assertTrue(result.contains("hello Android"))
    }

    @Test
    fun testRegexReplacement() {
        val result = RegexReplacer.replace("test123abc", "\\d+", "")
        assertTrue(result.contains("testabc"))
    }

    @Test
    fun testInvalidRegex() {
        val result = RegexReplacer.replace("text", "[", "x")
        assertTrue(result.contains("Error"))
    }
}

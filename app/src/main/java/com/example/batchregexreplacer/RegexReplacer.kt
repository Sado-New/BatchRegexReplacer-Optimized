package com.example.batchregexreplacer

object RegexReplacer {
    fun replace(text: String, pattern: String, replacement: String): String {
        return try {
            val regex = Regex(pattern)
            val result = regex.replace(text, replacement)
            "Success: ${regex.findAll(text).count()} matches replaced\n\nResult:\n$result"
        } catch (e: Exception) {
            "Error: ${e.message}"
        }
    }

    fun replaceInFile(filePath: String, pattern: String, replacement: String): Result<String> {
        return try {
            val file = java.io.File(filePath)
            if (!file.exists()) {
                return Result.failure(Exception("File not found: $filePath"))
            }

            val content = file.readText()
            val regex = Regex(pattern)
            val newContent = regex.replace(content, replacement)
            val matchCount = regex.findAll(content).count()

            file.writeText(newContent)
            Result.success("Replaced $matchCount matches in $filePath")
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

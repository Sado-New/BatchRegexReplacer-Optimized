package com.example.batchregexreplacer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.batchregexreplacer.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
    }

    private fun setupUI() {
        binding.replacementButton.setOnClickListener {
            performReplacement()
        }
    }

    private fun performReplacement() {
        val pattern = binding.patternEditText.text.toString()
        val replacement = binding.replacementEditText.text.toString()
        val inputText = binding.inputEditText.text.toString()

        if (pattern.isEmpty()) {
            binding.resultTextView.text = "Pattern cannot be empty"
            return
        }

        lifecycleScope.launch {
            val result = withContext(Dispatchers.Default) {
                RegexReplacer.replace(inputText, pattern, replacement)
            }
            binding.resultTextView.text = result
        }
    }
}

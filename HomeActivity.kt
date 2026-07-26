package com.example.intelligentanalysis.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.intelligentanalysis.R
import com.example.intelligentanalysis.databinding.ActivityHomeBinding
import com.example.intelligentanalysis.utils.AnalysisHelper

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private val analysisHelper = AnalysisHelper()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNav.selectedItemId = R.id.nav_home
        binding.bottomNav.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> true
                R.id.nav_history -> {
                    startActivity(Intent(this, HistoryActivity::class.java))
                    true
                }
                R.id.nav_settings -> {
                    startActivity(Intent(this, SettingsActivity::class.java))
                    true
                }
                else -> false
            }
        }

        binding.btnAnalyze.setOnClickListener {
            val text = binding.etAnalysis.text.toString().trim()
            if (text.isEmpty()) {
                binding.etAnalysis.error = getString(R.string.analyze_hint)
                return@setOnClickListener
            }

            binding.btnAnalyze.text = getString(R.string.analyzing)
            binding.btnAnalyze.isEnabled = false

            Handler(Looper.getMainLooper()).postDelayed({
                val result = analysisHelper.analyze(text)
                showResults(result)
                binding.btnAnalyze.text = getString(R.string.analysis_done)
                binding.btnAnalyze.isEnabled = true

                Handler(Looper.getMainLooper()).postDelayed({
                    binding.btnAnalyze.text = getString(R.string.analyze_button)
                }, 3000)
            }, 1200)
        }
    }

    private fun showResults(result: AnalysisHelper.AnalysisResult) {
        binding.cardResults.visibility = View.VISIBLE
    }
}

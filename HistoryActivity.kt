package com.example.intelligentanalysis.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.intelligentanalysis.R
import com.example.intelligentanalysis.adapters.HistoryAdapter
import com.example.intelligentanalysis.databinding.ActivityHistoryBinding
import com.example.intelligentanalysis.models.HistoryItem

class HistoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHistoryBinding
    private lateinit var adapter: HistoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNav.selectedItemId = R.id.nav_history
        binding.bottomNav.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    startActivity(Intent(this, HomeActivity::class.java))
                    true
                }
                R.id.nav_settings -> {
                    startActivity(Intent(this, SettingsActivity::class.java))
                    true
                }
                else -> false
            }
        }

        adapter = HistoryAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        val items = listOf(
            HistoryItem("تحليل أداء الربع الثالث", "14 أكتوبر، 2023", "مالي", "مكتمل", 5, true),
            HistoryItem("توقعات سلوك المستهلك", "12 أكتوبر، 2023", "عاجل", "بحث", 12, false),
            HistoryItem("مراجعة بيانات الموظفين", "10 أكتوبر، 2023", "إداري", null, 2, null),
            HistoryItem("تحسين كفاءة العمليات", "08 أكتوبر، 2023", "تقني", "نشط", 8, true),
            HistoryItem("خطة التوسع السنوية", "05 أكتوبر، 2023", "إستراتيجي", null, 3, null)
        )
        adapter.submitList(items)
    }
}

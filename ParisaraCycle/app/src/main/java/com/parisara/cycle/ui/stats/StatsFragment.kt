package com.parisara.cycle.ui.stats

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.parisara.cycle.databinding.FragmentStatsBinding
import com.parisara.cycle.ui.MainViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class StatsFragment : Fragment() {

    private var _binding: FragmentStatsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStatsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val month = SimpleDateFormat("yyyy-MM", Locale.getDefault()).format(Date())

        viewModel.totalCO2Saved.observe(viewLifecycleOwner) { co2 ->
            val g = co2 ?: 0.0
            binding.tvAllTimeCo2.text = "%.0f g".format(g)
            binding.tvTreesEquiv.text = "≈ %.2f trees saved".format(g / 21000)
        }

        viewModel.totalDistance.observe(viewLifecycleOwner) { dist ->
            binding.tvAllTimeDist.text = "%.1f km".format(dist ?: 0.0)
        }

        viewModel.getMonthlyTotal(month).observe(viewLifecycleOwner) { co2 ->
            binding.tvMonthlyCo2.text = "%.0f g CO₂ this month".format(co2 ?: 0.0)
        }

        viewModel.allEcoStats.observe(viewLifecycleOwner) { stats ->
            val totalCal = stats.sumOf { it.caloriesBurned }
            binding.tvTotalCalories.text = "%.0f kcal burned".format(totalCal)
            binding.tvRideCount.text = "${stats.size} rides completed"
        }

        binding.btnLogRide.setOnClickListener {
            val distText = binding.etDistance.text.toString()
            val dist = distText.toDoubleOrNull() ?: 0.0
            if (dist > 0) {
                viewModel.recordRide(dist)
                binding.etDistance.text?.clear()
                binding.tvLogSuccess.visibility = View.VISIBLE
                binding.tvLogSuccess.text = "✅ Ride logged! You saved %.0f g CO₂".format(dist * 120)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

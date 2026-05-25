package com.parisara.cycle.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.parisara.cycle.R
import com.parisara.cycle.databinding.FragmentHomeBinding
import com.parisara.cycle.ui.MainViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val month = SimpleDateFormat("yyyy-MM", Locale.getDefault()).format(Date())

        viewModel.totalCO2Saved.observe(viewLifecycleOwner) { co2 ->
            val grams = co2 ?: 0.0
            binding.tvCo2Total.text = "%.0f g CO₂ saved".format(grams)
        }

        viewModel.totalDistance.observe(viewLifecycleOwner) { dist ->
            val km = dist ?: 0.0
            binding.tvTotalDistance.text = "%.1f km ridden".format(km)
        }

        viewModel.getMonthlyTotal(month).observe(viewLifecycleOwner) { monthly ->
            val grams = monthly ?: 0.0
            binding.tvMonthlyCo2.text = "Monthly Total: %.0f g CO₂".format(grams)
        }

        viewModel.nearbyBuddies.observe(viewLifecycleOwner) { buddies ->
            binding.tvBuddyCount.text = "${buddies.size} cyclists nearby"
        }

        binding.btnSafeRoute.setOnClickListener {
            findNavController().navigate(R.id.action_home_to_map)
        }
        binding.btnFindBuddies.setOnClickListener {
            findNavController().navigate(R.id.action_home_to_buddy)
        }
        binding.btnPitStops.setOnClickListener {
            findNavController().navigate(R.id.action_home_to_map)
        }
        binding.btnReportDanger.setOnClickListener {
            findNavController().navigate(R.id.action_home_to_report)
        }
        binding.btnEcoStats.setOnClickListener {
            findNavController().navigate(R.id.action_home_to_stats)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

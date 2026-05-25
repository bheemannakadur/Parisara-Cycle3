package com.parisara.cycle.ui.report

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.parisara.cycle.R
import com.parisara.cycle.databinding.FragmentReportBinding
import com.parisara.cycle.model.DangerZone
import com.parisara.cycle.ui.MainViewModel

class ReportFragment : Fragment() {

    private var _binding: FragmentReportBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReportBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val types = arrayOf("POTHOLE", "DANGEROUS_INTERSECTION", "BLOCKAGE", "OTHER")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, types)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerType.adapter = adapter

        binding.btnSubmitReport.setOnClickListener {
            val lat = viewModel.currentLat.value ?: 12.9716
            val lng = viewModel.currentLng.value ?: 77.5946
            val type = types[binding.spinnerType.selectedItemPosition]
            val desc = binding.etDescription.text.toString().trim()

            if (desc.isEmpty()) {
                binding.etDescription.error = "Please describe the issue"
                return@setOnClickListener
            }

            val zone = DangerZone(
                latitude = lat,
                longitude = lng,
                type = type,
                description = desc,
                reportedBy = viewModel.userName
            )

            viewModel.reportDangerZone(zone)
            Toast.makeText(requireContext(), "✅ Report submitted! Thank you.", Toast.LENGTH_SHORT).show()
            binding.etDescription.text?.clear()
            binding.tvCurrentLocation.text = "📍 Reported at: %.4f, %.4f".format(lat, lng)
        }

        // Show current location
        viewModel.currentLat.observe(viewLifecycleOwner) { lat ->
            val lng = viewModel.currentLng.value ?: 0.0
            binding.tvCurrentLocation.text = "📍 Your location: %.4f, %.4f".format(lat, lng)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

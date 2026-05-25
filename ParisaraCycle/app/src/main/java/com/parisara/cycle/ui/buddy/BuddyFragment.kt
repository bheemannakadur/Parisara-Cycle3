package com.parisara.cycle.ui.buddy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.parisara.cycle.databinding.FragmentBuddyBinding
import com.parisara.cycle.ui.MainViewModel

class BuddyFragment : Fragment() {

    private var _binding: FragmentBuddyBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var adapter: BuddyAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBuddyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = BuddyAdapter()
        binding.rvBuddies.layoutManager = LinearLayoutManager(requireContext())
        binding.rvBuddies.adapter = adapter

        binding.tvMyId.text = "Your ID: ${viewModel.sessionId}"

        viewModel.nearbyBuddies.observe(viewLifecycleOwner) { buddies ->
            adapter.submitList(buddies)
            binding.tvBuddyCount.text = "${buddies.size} cyclist(s) near you"
            binding.tvEmptyState.visibility = if (buddies.isEmpty()) View.VISIBLE else View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

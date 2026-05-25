package com.parisara.cycle.ui.map

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.parisara.cycle.R
import com.parisara.cycle.databinding.FragmentMapBinding
import com.parisara.cycle.model.PitStop
import com.parisara.cycle.ui.MainViewModel

class MapFragment : Fragment(), OnMapReadyCallback {

    private var _binding: FragmentMapBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by activityViewModels()

    private lateinit var googleMap: GoogleMap
    private var currentLocationMarker: Marker? = null
    private val buddyMarkers = mutableMapOf<String, Marker>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMapBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mapFragment = childFragmentManager.findFragmentById(R.id.map_fragment) as SupportMapFragment
        mapFragment.getMapAsync(this)

        binding.btnLayerSafe.setOnClickListener { toggleSafeRoutes() }
        binding.btnLayerPitStops.setOnClickListener { togglePitStops() }
        binding.btnLayerDanger.setOnClickListener { toggleDangerZones() }
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map
        googleMap.uiSettings.isZoomControlsEnabled = true
        googleMap.uiSettings.isMyLocationButtonEnabled = true

        try {
            googleMap.isMyLocationEnabled = true
        } catch (e: SecurityException) { /* handle */ }

        // Move to a default location (Bengaluru)
        val defaultLocation = LatLng(12.9716, 77.5946)
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(defaultLocation, 13f))

        observeData()
    }

    private fun observeData() {
        // Current location
        viewModel.currentLat.observe(viewLifecycleOwner) { lat ->
            val lng = viewModel.currentLng.value ?: return@observe
            val pos = LatLng(lat, lng)
            currentLocationMarker?.remove()
            currentLocationMarker = googleMap.addMarker(
                MarkerOptions().position(pos).title("You are here")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
            )
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(pos, 15f))
        }

        // Danger Zones
        viewModel.allDangerZones.observe(viewLifecycleOwner) { zones ->
            zones.forEach { zone ->
                val icon = when (zone.type) {
                    "POTHOLE" -> BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)
                    "BLOCKAGE" -> BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)
                    else -> BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)
                }
                googleMap.addMarker(
                    MarkerOptions()
                        .position(LatLng(zone.latitude, zone.longitude))
                        .title("⚠️ ${zone.type}")
                        .snippet(zone.description)
                        .icon(icon)
                )
            }
        }

        // Pit Stops
        viewModel.allPitStops.observe(viewLifecycleOwner) { stops ->
            stops.forEach { stop ->
                val icon = when (stop.type) {
                    "REPAIR_SHOP" -> BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET)
                    "WATER_POINT" -> BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)
                    else -> BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)
                }
                googleMap.addMarker(
                    MarkerOptions()
                        .position(LatLng(stop.latitude, stop.longitude))
                        .title("🔧 ${stop.name}")
                        .snippet(stop.address)
                        .icon(icon)
                )
            }
        }

        // Buddy System
        viewModel.nearbyBuddies.observe(viewLifecycleOwner) { buddies ->
            // Remove stale
            buddyMarkers.values.forEach { it.remove() }
            buddyMarkers.clear()
            buddies.forEach { buddy ->
                val marker = googleMap.addMarker(
                    MarkerOptions()
                        .position(LatLng(buddy.latitude, buddy.longitude))
                        .title("🚴 ${buddy.name}")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
                )
                marker?.let { buddyMarkers[buddy.uid] = it }
            }
        }
    }

    private fun toggleSafeRoutes() {
        Toast.makeText(requireContext(), "Safe route layer toggled", Toast.LENGTH_SHORT).show()
        // Safe route overlays would go here using Directions API
    }

    private fun togglePitStops() {
        Toast.makeText(requireContext(), "Pit stops toggled", Toast.LENGTH_SHORT).show()
    }

    private fun toggleDangerZones() {
        Toast.makeText(requireContext(), "Danger zones toggled", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

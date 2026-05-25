package com.parisara.cycle.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.parisara.cycle.data.repository.AppRepository
import com.parisara.cycle.model.BuddyUser
import com.parisara.cycle.model.DangerZone
import com.parisara.cycle.model.EcoStat
import com.parisara.cycle.model.PitStop
import com.parisara.cycle.model.Route
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.UUID

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repo = AppRepository(application)

    // Routes
    val safeRoutes = repo.safeRoutes
    val allDangerZones = repo.allDangerZones
    val allPitStops = repo.allPitStops
    val allEcoStats = repo.allEcoStats
    val totalCO2Saved = repo.totalCO2Saved
    val totalDistance = repo.totalDistance

    // Current location
    private val _currentLat = MutableLiveData<Double>()
    private val _currentLng = MutableLiveData<Double>()
    val currentLat: LiveData<Double> = _currentLat
    val currentLng: LiveData<Double> = _currentLng

    // Buddies on map
    private val _nearbyBuddies = MutableLiveData<List<BuddyUser>>()
    val nearbyBuddies: LiveData<List<BuddyUser>> = _nearbyBuddies

    // Monthly CO2
    fun getMonthlyTotal(month: String) = repo.getMonthlyTotal(month)

    // Session ID for buddy tracking
    val sessionId: String = UUID.randomUUID().toString().take(8)
    var userName: String = "Cyclist_$sessionId"

    init {
        viewModelScope.launch { repo.seedPitStops() }
        startListeningBuddies()
    }

    fun updateLocation(lat: Double, lng: Double) {
        _currentLat.value = lat
        _currentLng.value = lng
        repo.updateMyLocation(sessionId, userName, lat, lng)
    }

    fun reportDangerZone(zone: DangerZone) = viewModelScope.launch {
        repo.reportDangerZone(zone)
    }

    fun insertRoute(route: Route) = viewModelScope.launch { repo.insertRoute(route) }

    fun recordRide(distanceKm: Double) = viewModelScope.launch {
        val now = Date()
        val stat = EcoStat(
            distanceKm = distanceKm,
            co2SavedGrams = distanceKm * 120,
            caloriesBurned = distanceKm * 40,
            date = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(now),
            month = SimpleDateFormat("yyyy-MM", Locale.getDefault()).format(now)
        )
        repo.insertEcoStat(stat)
    }

    private fun startListeningBuddies() {
        repo.listenForBuddies { buddies ->
            _nearbyBuddies.postValue(buddies.filter { it.uid != sessionId })
        }
    }

    override fun onCleared() {
        super.onCleared()
        repo.removeMyLocation(sessionId)
    }
}

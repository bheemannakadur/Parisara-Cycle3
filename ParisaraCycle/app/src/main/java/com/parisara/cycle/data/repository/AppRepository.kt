package com.parisara.cycle.data.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.parisara.cycle.data.local.AppDatabase
import com.parisara.cycle.model.BuddyUser
import com.parisara.cycle.model.DangerZone
import com.parisara.cycle.model.EcoStat
import com.parisara.cycle.model.PitStop
import com.parisara.cycle.model.Route
import kotlinx.coroutines.tasks.await

class AppRepository(context: Context) {

    private val db = AppDatabase.getInstance(context)
    private val routeDao = db.routeDao()
    private val dangerZoneDao = db.dangerZoneDao()
    private val pitStopDao = db.pitStopDao()
    private val ecoStatDao = db.ecoStatDao()

    // Firebase Realtime DB reference for buddy system
    private val firebaseDb = FirebaseDatabase.getInstance()
    private val buddyRef = firebaseDb.getReference("buddy_locations")
    private val dangerRef = firebaseDb.getReference("danger_zones")

    // ── Routes ────────────────────────────────────────────────────────────────
    val allRoutes: LiveData<List<Route>> = routeDao.getAllRoutes()
    val safeRoutes: LiveData<List<Route>> = routeDao.getSafeRoutes()

    suspend fun insertRoute(route: Route) = routeDao.insertRoute(route)
    suspend fun deleteRoute(route: Route) = routeDao.deleteRoute(route)

    // ── Danger Zones ──────────────────────────────────────────────────────────
    val allDangerZones: LiveData<List<DangerZone>> = dangerZoneDao.getAllDangerZones()

    suspend fun reportDangerZone(zone: DangerZone) {
        val id = dangerZoneDao.insertDangerZone(zone)
        // Also push to Firebase for community visibility
        dangerRef.push().setValue(zone)
    }

    suspend fun upvoteDangerZone(id: Int) = dangerZoneDao.upvote(id)

    suspend fun getDangerZonesNearby(
        lat: Double, lng: Double, radiusDeg: Double = 0.05
    ) = dangerZoneDao.getDangerZonesInArea(
        lat - radiusDeg, lat + radiusDeg,
        lng - radiusDeg, lng + radiusDeg
    )

    // ── Pit Stops ─────────────────────────────────────────────────────────────
    val allPitStops: LiveData<List<PitStop>> = pitStopDao.getAllPitStops()
    suspend fun insertPitStop(stop: PitStop) = pitStopDao.insertPitStop(stop)

    // ── Eco Stats ─────────────────────────────────────────────────────────────
    val allEcoStats: LiveData<List<EcoStat>> = ecoStatDao.getAllStats()
    val totalCO2Saved: LiveData<Double?> = ecoStatDao.getTotalCO2Saved()
    val totalDistance: LiveData<Double?> = ecoStatDao.getTotalDistance()

    suspend fun insertEcoStat(stat: EcoStat) = ecoStatDao.insertStat(stat)
    suspend fun getMonthlyCO2(month: String) = ecoStatDao.getMonthlyCO2(month)
    fun getMonthlyTotal(month: String) = ecoStatDao.getMonthlyTotal(month)

    // ── Buddy System (Firebase) ───────────────────────────────────────────────
    fun updateMyLocation(uid: String, name: String, lat: Double, lng: Double) {
        val user = BuddyUser(uid, name, lat, lng)
        buddyRef.child(uid).setValue(user)
    }

    fun listenForBuddies(onUpdate: (List<BuddyUser>) -> Unit) {
        buddyRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val buddies = mutableListOf<BuddyUser>()
                for (child in snapshot.children) {
                    child.getValue(BuddyUser::class.java)?.let { buddies.add(it) }
                }
                onUpdate(buddies)
            }
            override fun onCancelled(error: DatabaseError) {}
        })
    }

    fun removeMyLocation(uid: String) {
        buddyRef.child(uid).removeValue()
    }

    // Pre-populate sample pit stops
    suspend fun seedPitStops() {
        val existing = allPitStops
        val samples = listOf(
            PitStop(name = "Green Cycle Repair", latitude = 12.9716, longitude = 77.5946,
                type = "REPAIR_SHOP", address = "MG Road, Bengaluru"),
            PitStop(name = "Water Kiosk - JP Nagar", latitude = 12.9077, longitude = 77.5857,
                type = "WATER_POINT", address = "JP Nagar 6th Phase"),
            PitStop(name = "Cubbon Park Rest Point", latitude = 12.9763, longitude = 77.5929,
                type = "REST_AREA", address = "Cubbon Park")
        )
        samples.forEach { pitStopDao.insertPitStop(it) }
    }
}

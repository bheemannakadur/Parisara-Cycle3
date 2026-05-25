package com.parisara.cycle.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.parisara.cycle.model.DangerZone
import com.parisara.cycle.model.EcoStat
import com.parisara.cycle.model.PitStop
import com.parisara.cycle.model.Route

// ── Route DAO ─────────────────────────────────────────────────────────────────
@Dao
interface RouteDao {
    @Query("SELECT * FROM routes ORDER BY timestamp DESC")
    fun getAllRoutes(): LiveData<List<Route>>

    @Query("SELECT * FROM routes WHERE isSafe = 1 ORDER BY timestamp DESC")
    fun getSafeRoutes(): LiveData<List<Route>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRoute(route: Route): Long

    @Delete
    suspend fun deleteRoute(route: Route)

    @Query("SELECT * FROM routes WHERE id = :id")
    suspend fun getRouteById(id: Int): Route?
}

// ── DangerZone DAO ────────────────────────────────────────────────────────────
@Dao
interface DangerZoneDao {
    @Query("SELECT * FROM danger_zones ORDER BY timestamp DESC")
    fun getAllDangerZones(): LiveData<List<DangerZone>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDangerZone(zone: DangerZone): Long

    @Delete
    suspend fun deleteDangerZone(zone: DangerZone)

    @Query("UPDATE danger_zones SET upvotes = upvotes + 1 WHERE id = :id")
    suspend fun upvote(id: Int)

    @Query("SELECT * FROM danger_zones WHERE latitude BETWEEN :minLat AND :maxLat AND longitude BETWEEN :minLng AND :maxLng")
    suspend fun getDangerZonesInArea(minLat: Double, maxLat: Double, minLng: Double, maxLng: Double): List<DangerZone>
}

// ── PitStop DAO ───────────────────────────────────────────────────────────────
@Dao
interface PitStopDao {
    @Query("SELECT * FROM pit_stops ORDER BY name ASC")
    fun getAllPitStops(): LiveData<List<PitStop>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPitStop(pitStop: PitStop): Long

    @Query("SELECT * FROM pit_stops WHERE type = :type")
    fun getPitStopsByType(type: String): LiveData<List<PitStop>>
}

// ── EcoStat DAO ───────────────────────────────────────────────────────────────
@Dao
interface EcoStatDao {
    @Query("SELECT * FROM eco_stats ORDER BY timestamp DESC")
    fun getAllStats(): LiveData<List<EcoStat>>

    @Insert
    suspend fun insertStat(stat: EcoStat)

    @Query("SELECT SUM(co2SavedGrams) FROM eco_stats WHERE month = :month")
    suspend fun getMonthlyCO2(month: String): Double?

    @Query("SELECT SUM(distanceKm) FROM eco_stats WHERE month = :month")
    suspend fun getMonthlyDistance(month: String): Double?

    @Query("SELECT SUM(co2SavedGrams) FROM eco_stats")
    fun getTotalCO2Saved(): LiveData<Double?>

    @Query("SELECT SUM(distanceKm) FROM eco_stats")
    fun getTotalDistance(): LiveData<Double?>

    @Query("SELECT SUM(co2SavedGrams) FROM eco_stats WHERE month = :month")
    fun getMonthlyTotal(month: String): LiveData<Double?>
}

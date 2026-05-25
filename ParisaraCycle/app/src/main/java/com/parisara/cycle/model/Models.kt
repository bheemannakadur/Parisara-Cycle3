package com.parisara.cycle.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

// ── Route Model ──────────────────────────────────────────────────────────────
@Parcelize
@Entity(tableName = "routes")
data class Route(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val startLat: Double,
    val startLng: Double,
    val endLat: Double,
    val endLng: Double,
    val distanceKm: Double,
    val isSafe: Boolean = true,
    val isShaded: Boolean = false,
    val avoidHighways: Boolean = true,
    val co2SavedGrams: Double = distanceKm * 120,
    val timestamp: Long = System.currentTimeMillis()
) : Parcelable

// ── Danger Zone / Pothole Report ─────────────────────────────────────────────
@Parcelize
@Entity(tableName = "danger_zones")
data class DangerZone(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val latitude: Double,
    val longitude: Double,
    val type: String,          // "POTHOLE", "DANGEROUS_INTERSECTION", "BLOCKAGE"
    val description: String,
    val reportedBy: String = "Anonymous",
    val timestamp: Long = System.currentTimeMillis(),
    val isVerified: Boolean = false,
    val upvotes: Int = 0
) : Parcelable

// ── Pit Stop Model ───────────────────────────────────────────────────────────
@Parcelize
@Entity(tableName = "pit_stops")
data class PitStop(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val type: String,   // "REPAIR_SHOP", "WATER_POINT", "REST_AREA"
    val address: String = "",
    val isOpen: Boolean = true
) : Parcelable

// ── Eco Stats Model ──────────────────────────────────────────────────────────
@Entity(tableName = "eco_stats")
data class EcoStat(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val distanceKm: Double,
    val co2SavedGrams: Double,
    val caloriesBurned: Double = distanceKm * 40,
    val date: String,   // "yyyy-MM-dd"
    val month: String,  // "yyyy-MM"
    val timestamp: Long = System.currentTimeMillis()
)

// ── Buddy / Live User Model ───────────────────────────────────────────────────
data class BuddyUser(
    val uid: String = "",
    val name: String = "",
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val routeId: Int = -1,
    val lastSeen: Long = System.currentTimeMillis(),
    val isOnline: Boolean = true
)

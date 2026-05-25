package com.parisara.cycle.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.parisara.cycle.model.DangerZone
import com.parisara.cycle.model.EcoStat
import com.parisara.cycle.model.PitStop
import com.parisara.cycle.model.Route

@Database(
    entities = [Route::class, DangerZone::class, PitStop::class, EcoStat::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun routeDao(): RouteDao
    abstract fun dangerZoneDao(): DangerZoneDao
    abstract fun pitStopDao(): PitStopDao
    abstract fun ecoStatDao(): EcoStatDao

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "parisara_cycle_db"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
        }
    }
}

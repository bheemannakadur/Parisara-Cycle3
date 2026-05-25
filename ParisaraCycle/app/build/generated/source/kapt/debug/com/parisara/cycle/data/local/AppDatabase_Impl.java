package com.parisara.cycle.data.local;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile RouteDao _routeDao;

  private volatile DangerZoneDao _dangerZoneDao;

  private volatile PitStopDao _pitStopDao;

  private volatile EcoStatDao _ecoStatDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `routes` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `startLat` REAL NOT NULL, `startLng` REAL NOT NULL, `endLat` REAL NOT NULL, `endLng` REAL NOT NULL, `distanceKm` REAL NOT NULL, `isSafe` INTEGER NOT NULL, `isShaded` INTEGER NOT NULL, `avoidHighways` INTEGER NOT NULL, `co2SavedGrams` REAL NOT NULL, `timestamp` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `danger_zones` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `latitude` REAL NOT NULL, `longitude` REAL NOT NULL, `type` TEXT NOT NULL, `description` TEXT NOT NULL, `reportedBy` TEXT NOT NULL, `timestamp` INTEGER NOT NULL, `isVerified` INTEGER NOT NULL, `upvotes` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `pit_stops` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `latitude` REAL NOT NULL, `longitude` REAL NOT NULL, `type` TEXT NOT NULL, `address` TEXT NOT NULL, `isOpen` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `eco_stats` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `distanceKm` REAL NOT NULL, `co2SavedGrams` REAL NOT NULL, `caloriesBurned` REAL NOT NULL, `date` TEXT NOT NULL, `month` TEXT NOT NULL, `timestamp` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '1ee4f4285b8fde09e7b67b5abcf4951e')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `routes`");
        db.execSQL("DROP TABLE IF EXISTS `danger_zones`");
        db.execSQL("DROP TABLE IF EXISTS `pit_stops`");
        db.execSQL("DROP TABLE IF EXISTS `eco_stats`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsRoutes = new HashMap<String, TableInfo.Column>(12);
        _columnsRoutes.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRoutes.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRoutes.put("startLat", new TableInfo.Column("startLat", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRoutes.put("startLng", new TableInfo.Column("startLng", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRoutes.put("endLat", new TableInfo.Column("endLat", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRoutes.put("endLng", new TableInfo.Column("endLng", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRoutes.put("distanceKm", new TableInfo.Column("distanceKm", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRoutes.put("isSafe", new TableInfo.Column("isSafe", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRoutes.put("isShaded", new TableInfo.Column("isShaded", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRoutes.put("avoidHighways", new TableInfo.Column("avoidHighways", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRoutes.put("co2SavedGrams", new TableInfo.Column("co2SavedGrams", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRoutes.put("timestamp", new TableInfo.Column("timestamp", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysRoutes = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesRoutes = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoRoutes = new TableInfo("routes", _columnsRoutes, _foreignKeysRoutes, _indicesRoutes);
        final TableInfo _existingRoutes = TableInfo.read(db, "routes");
        if (!_infoRoutes.equals(_existingRoutes)) {
          return new RoomOpenHelper.ValidationResult(false, "routes(com.parisara.cycle.model.Route).\n"
                  + " Expected:\n" + _infoRoutes + "\n"
                  + " Found:\n" + _existingRoutes);
        }
        final HashMap<String, TableInfo.Column> _columnsDangerZones = new HashMap<String, TableInfo.Column>(9);
        _columnsDangerZones.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDangerZones.put("latitude", new TableInfo.Column("latitude", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDangerZones.put("longitude", new TableInfo.Column("longitude", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDangerZones.put("type", new TableInfo.Column("type", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDangerZones.put("description", new TableInfo.Column("description", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDangerZones.put("reportedBy", new TableInfo.Column("reportedBy", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDangerZones.put("timestamp", new TableInfo.Column("timestamp", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDangerZones.put("isVerified", new TableInfo.Column("isVerified", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDangerZones.put("upvotes", new TableInfo.Column("upvotes", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysDangerZones = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesDangerZones = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoDangerZones = new TableInfo("danger_zones", _columnsDangerZones, _foreignKeysDangerZones, _indicesDangerZones);
        final TableInfo _existingDangerZones = TableInfo.read(db, "danger_zones");
        if (!_infoDangerZones.equals(_existingDangerZones)) {
          return new RoomOpenHelper.ValidationResult(false, "danger_zones(com.parisara.cycle.model.DangerZone).\n"
                  + " Expected:\n" + _infoDangerZones + "\n"
                  + " Found:\n" + _existingDangerZones);
        }
        final HashMap<String, TableInfo.Column> _columnsPitStops = new HashMap<String, TableInfo.Column>(7);
        _columnsPitStops.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPitStops.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPitStops.put("latitude", new TableInfo.Column("latitude", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPitStops.put("longitude", new TableInfo.Column("longitude", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPitStops.put("type", new TableInfo.Column("type", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPitStops.put("address", new TableInfo.Column("address", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPitStops.put("isOpen", new TableInfo.Column("isOpen", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysPitStops = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesPitStops = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoPitStops = new TableInfo("pit_stops", _columnsPitStops, _foreignKeysPitStops, _indicesPitStops);
        final TableInfo _existingPitStops = TableInfo.read(db, "pit_stops");
        if (!_infoPitStops.equals(_existingPitStops)) {
          return new RoomOpenHelper.ValidationResult(false, "pit_stops(com.parisara.cycle.model.PitStop).\n"
                  + " Expected:\n" + _infoPitStops + "\n"
                  + " Found:\n" + _existingPitStops);
        }
        final HashMap<String, TableInfo.Column> _columnsEcoStats = new HashMap<String, TableInfo.Column>(7);
        _columnsEcoStats.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEcoStats.put("distanceKm", new TableInfo.Column("distanceKm", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEcoStats.put("co2SavedGrams", new TableInfo.Column("co2SavedGrams", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEcoStats.put("caloriesBurned", new TableInfo.Column("caloriesBurned", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEcoStats.put("date", new TableInfo.Column("date", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEcoStats.put("month", new TableInfo.Column("month", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEcoStats.put("timestamp", new TableInfo.Column("timestamp", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysEcoStats = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesEcoStats = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoEcoStats = new TableInfo("eco_stats", _columnsEcoStats, _foreignKeysEcoStats, _indicesEcoStats);
        final TableInfo _existingEcoStats = TableInfo.read(db, "eco_stats");
        if (!_infoEcoStats.equals(_existingEcoStats)) {
          return new RoomOpenHelper.ValidationResult(false, "eco_stats(com.parisara.cycle.model.EcoStat).\n"
                  + " Expected:\n" + _infoEcoStats + "\n"
                  + " Found:\n" + _existingEcoStats);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "1ee4f4285b8fde09e7b67b5abcf4951e", "6d72e1cda92a626fe015d29c9f69d5a6");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "routes","danger_zones","pit_stops","eco_stats");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `routes`");
      _db.execSQL("DELETE FROM `danger_zones`");
      _db.execSQL("DELETE FROM `pit_stops`");
      _db.execSQL("DELETE FROM `eco_stats`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(RouteDao.class, RouteDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(DangerZoneDao.class, DangerZoneDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(PitStopDao.class, PitStopDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(EcoStatDao.class, EcoStatDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public RouteDao routeDao() {
    if (_routeDao != null) {
      return _routeDao;
    } else {
      synchronized(this) {
        if(_routeDao == null) {
          _routeDao = new RouteDao_Impl(this);
        }
        return _routeDao;
      }
    }
  }

  @Override
  public DangerZoneDao dangerZoneDao() {
    if (_dangerZoneDao != null) {
      return _dangerZoneDao;
    } else {
      synchronized(this) {
        if(_dangerZoneDao == null) {
          _dangerZoneDao = new DangerZoneDao_Impl(this);
        }
        return _dangerZoneDao;
      }
    }
  }

  @Override
  public PitStopDao pitStopDao() {
    if (_pitStopDao != null) {
      return _pitStopDao;
    } else {
      synchronized(this) {
        if(_pitStopDao == null) {
          _pitStopDao = new PitStopDao_Impl(this);
        }
        return _pitStopDao;
      }
    }
  }

  @Override
  public EcoStatDao ecoStatDao() {
    if (_ecoStatDao != null) {
      return _ecoStatDao;
    } else {
      synchronized(this) {
        if(_ecoStatDao == null) {
          _ecoStatDao = new EcoStatDao_Impl(this);
        }
        return _ecoStatDao;
      }
    }
  }
}

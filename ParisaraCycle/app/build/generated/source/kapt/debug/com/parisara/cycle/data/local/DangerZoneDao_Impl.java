package com.parisara.cycle.data.local;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.parisara.cycle.model.DangerZone;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class DangerZoneDao_Impl implements DangerZoneDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<DangerZone> __insertionAdapterOfDangerZone;

  private final EntityDeletionOrUpdateAdapter<DangerZone> __deletionAdapterOfDangerZone;

  private final SharedSQLiteStatement __preparedStmtOfUpvote;

  public DangerZoneDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfDangerZone = new EntityInsertionAdapter<DangerZone>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `danger_zones` (`id`,`latitude`,`longitude`,`type`,`description`,`reportedBy`,`timestamp`,`isVerified`,`upvotes`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final DangerZone entity) {
        statement.bindLong(1, entity.getId());
        statement.bindDouble(2, entity.getLatitude());
        statement.bindDouble(3, entity.getLongitude());
        if (entity.getType() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getType());
        }
        if (entity.getDescription() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getDescription());
        }
        if (entity.getReportedBy() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getReportedBy());
        }
        statement.bindLong(7, entity.getTimestamp());
        final int _tmp = entity.isVerified() ? 1 : 0;
        statement.bindLong(8, _tmp);
        statement.bindLong(9, entity.getUpvotes());
      }
    };
    this.__deletionAdapterOfDangerZone = new EntityDeletionOrUpdateAdapter<DangerZone>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `danger_zones` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final DangerZone entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__preparedStmtOfUpvote = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE danger_zones SET upvotes = upvotes + 1 WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertDangerZone(final DangerZone zone,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfDangerZone.insertAndReturnId(zone);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteDangerZone(final DangerZone zone,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfDangerZone.handle(zone);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object upvote(final int id, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfUpvote.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, id);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfUpvote.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public LiveData<List<DangerZone>> getAllDangerZones() {
    final String _sql = "SELECT * FROM danger_zones ORDER BY timestamp DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"danger_zones"}, false, new Callable<List<DangerZone>>() {
      @Override
      @Nullable
      public List<DangerZone> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfLatitude = CursorUtil.getColumnIndexOrThrow(_cursor, "latitude");
          final int _cursorIndexOfLongitude = CursorUtil.getColumnIndexOrThrow(_cursor, "longitude");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfReportedBy = CursorUtil.getColumnIndexOrThrow(_cursor, "reportedBy");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfIsVerified = CursorUtil.getColumnIndexOrThrow(_cursor, "isVerified");
          final int _cursorIndexOfUpvotes = CursorUtil.getColumnIndexOrThrow(_cursor, "upvotes");
          final List<DangerZone> _result = new ArrayList<DangerZone>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final DangerZone _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final double _tmpLatitude;
            _tmpLatitude = _cursor.getDouble(_cursorIndexOfLatitude);
            final double _tmpLongitude;
            _tmpLongitude = _cursor.getDouble(_cursorIndexOfLongitude);
            final String _tmpType;
            if (_cursor.isNull(_cursorIndexOfType)) {
              _tmpType = null;
            } else {
              _tmpType = _cursor.getString(_cursorIndexOfType);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final String _tmpReportedBy;
            if (_cursor.isNull(_cursorIndexOfReportedBy)) {
              _tmpReportedBy = null;
            } else {
              _tmpReportedBy = _cursor.getString(_cursorIndexOfReportedBy);
            }
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            final boolean _tmpIsVerified;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsVerified);
            _tmpIsVerified = _tmp != 0;
            final int _tmpUpvotes;
            _tmpUpvotes = _cursor.getInt(_cursorIndexOfUpvotes);
            _item = new DangerZone(_tmpId,_tmpLatitude,_tmpLongitude,_tmpType,_tmpDescription,_tmpReportedBy,_tmpTimestamp,_tmpIsVerified,_tmpUpvotes);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getDangerZonesInArea(final double minLat, final double maxLat, final double minLng,
      final double maxLng, final Continuation<? super List<DangerZone>> $completion) {
    final String _sql = "SELECT * FROM danger_zones WHERE latitude BETWEEN ? AND ? AND longitude BETWEEN ? AND ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 4);
    int _argIndex = 1;
    _statement.bindDouble(_argIndex, minLat);
    _argIndex = 2;
    _statement.bindDouble(_argIndex, maxLat);
    _argIndex = 3;
    _statement.bindDouble(_argIndex, minLng);
    _argIndex = 4;
    _statement.bindDouble(_argIndex, maxLng);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<DangerZone>>() {
      @Override
      @NonNull
      public List<DangerZone> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfLatitude = CursorUtil.getColumnIndexOrThrow(_cursor, "latitude");
          final int _cursorIndexOfLongitude = CursorUtil.getColumnIndexOrThrow(_cursor, "longitude");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfReportedBy = CursorUtil.getColumnIndexOrThrow(_cursor, "reportedBy");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfIsVerified = CursorUtil.getColumnIndexOrThrow(_cursor, "isVerified");
          final int _cursorIndexOfUpvotes = CursorUtil.getColumnIndexOrThrow(_cursor, "upvotes");
          final List<DangerZone> _result = new ArrayList<DangerZone>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final DangerZone _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final double _tmpLatitude;
            _tmpLatitude = _cursor.getDouble(_cursorIndexOfLatitude);
            final double _tmpLongitude;
            _tmpLongitude = _cursor.getDouble(_cursorIndexOfLongitude);
            final String _tmpType;
            if (_cursor.isNull(_cursorIndexOfType)) {
              _tmpType = null;
            } else {
              _tmpType = _cursor.getString(_cursorIndexOfType);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final String _tmpReportedBy;
            if (_cursor.isNull(_cursorIndexOfReportedBy)) {
              _tmpReportedBy = null;
            } else {
              _tmpReportedBy = _cursor.getString(_cursorIndexOfReportedBy);
            }
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            final boolean _tmpIsVerified;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsVerified);
            _tmpIsVerified = _tmp != 0;
            final int _tmpUpvotes;
            _tmpUpvotes = _cursor.getInt(_cursorIndexOfUpvotes);
            _item = new DangerZone(_tmpId,_tmpLatitude,_tmpLongitude,_tmpType,_tmpDescription,_tmpReportedBy,_tmpTimestamp,_tmpIsVerified,_tmpUpvotes);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}

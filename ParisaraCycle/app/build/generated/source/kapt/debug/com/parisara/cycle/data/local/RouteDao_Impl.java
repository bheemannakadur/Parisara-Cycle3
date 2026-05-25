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
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.parisara.cycle.model.Route;
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
public final class RouteDao_Impl implements RouteDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Route> __insertionAdapterOfRoute;

  private final EntityDeletionOrUpdateAdapter<Route> __deletionAdapterOfRoute;

  public RouteDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfRoute = new EntityInsertionAdapter<Route>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `routes` (`id`,`name`,`startLat`,`startLng`,`endLat`,`endLng`,`distanceKm`,`isSafe`,`isShaded`,`avoidHighways`,`co2SavedGrams`,`timestamp`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Route entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getName());
        }
        statement.bindDouble(3, entity.getStartLat());
        statement.bindDouble(4, entity.getStartLng());
        statement.bindDouble(5, entity.getEndLat());
        statement.bindDouble(6, entity.getEndLng());
        statement.bindDouble(7, entity.getDistanceKm());
        final int _tmp = entity.isSafe() ? 1 : 0;
        statement.bindLong(8, _tmp);
        final int _tmp_1 = entity.isShaded() ? 1 : 0;
        statement.bindLong(9, _tmp_1);
        final int _tmp_2 = entity.getAvoidHighways() ? 1 : 0;
        statement.bindLong(10, _tmp_2);
        statement.bindDouble(11, entity.getCo2SavedGrams());
        statement.bindLong(12, entity.getTimestamp());
      }
    };
    this.__deletionAdapterOfRoute = new EntityDeletionOrUpdateAdapter<Route>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `routes` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Route entity) {
        statement.bindLong(1, entity.getId());
      }
    };
  }

  @Override
  public Object insertRoute(final Route route, final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfRoute.insertAndReturnId(route);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteRoute(final Route route, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfRoute.handle(route);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public LiveData<List<Route>> getAllRoutes() {
    final String _sql = "SELECT * FROM routes ORDER BY timestamp DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"routes"}, false, new Callable<List<Route>>() {
      @Override
      @Nullable
      public List<Route> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfStartLat = CursorUtil.getColumnIndexOrThrow(_cursor, "startLat");
          final int _cursorIndexOfStartLng = CursorUtil.getColumnIndexOrThrow(_cursor, "startLng");
          final int _cursorIndexOfEndLat = CursorUtil.getColumnIndexOrThrow(_cursor, "endLat");
          final int _cursorIndexOfEndLng = CursorUtil.getColumnIndexOrThrow(_cursor, "endLng");
          final int _cursorIndexOfDistanceKm = CursorUtil.getColumnIndexOrThrow(_cursor, "distanceKm");
          final int _cursorIndexOfIsSafe = CursorUtil.getColumnIndexOrThrow(_cursor, "isSafe");
          final int _cursorIndexOfIsShaded = CursorUtil.getColumnIndexOrThrow(_cursor, "isShaded");
          final int _cursorIndexOfAvoidHighways = CursorUtil.getColumnIndexOrThrow(_cursor, "avoidHighways");
          final int _cursorIndexOfCo2SavedGrams = CursorUtil.getColumnIndexOrThrow(_cursor, "co2SavedGrams");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final List<Route> _result = new ArrayList<Route>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Route _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final double _tmpStartLat;
            _tmpStartLat = _cursor.getDouble(_cursorIndexOfStartLat);
            final double _tmpStartLng;
            _tmpStartLng = _cursor.getDouble(_cursorIndexOfStartLng);
            final double _tmpEndLat;
            _tmpEndLat = _cursor.getDouble(_cursorIndexOfEndLat);
            final double _tmpEndLng;
            _tmpEndLng = _cursor.getDouble(_cursorIndexOfEndLng);
            final double _tmpDistanceKm;
            _tmpDistanceKm = _cursor.getDouble(_cursorIndexOfDistanceKm);
            final boolean _tmpIsSafe;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsSafe);
            _tmpIsSafe = _tmp != 0;
            final boolean _tmpIsShaded;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsShaded);
            _tmpIsShaded = _tmp_1 != 0;
            final boolean _tmpAvoidHighways;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfAvoidHighways);
            _tmpAvoidHighways = _tmp_2 != 0;
            final double _tmpCo2SavedGrams;
            _tmpCo2SavedGrams = _cursor.getDouble(_cursorIndexOfCo2SavedGrams);
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            _item = new Route(_tmpId,_tmpName,_tmpStartLat,_tmpStartLng,_tmpEndLat,_tmpEndLng,_tmpDistanceKm,_tmpIsSafe,_tmpIsShaded,_tmpAvoidHighways,_tmpCo2SavedGrams,_tmpTimestamp);
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
  public LiveData<List<Route>> getSafeRoutes() {
    final String _sql = "SELECT * FROM routes WHERE isSafe = 1 ORDER BY timestamp DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"routes"}, false, new Callable<List<Route>>() {
      @Override
      @Nullable
      public List<Route> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfStartLat = CursorUtil.getColumnIndexOrThrow(_cursor, "startLat");
          final int _cursorIndexOfStartLng = CursorUtil.getColumnIndexOrThrow(_cursor, "startLng");
          final int _cursorIndexOfEndLat = CursorUtil.getColumnIndexOrThrow(_cursor, "endLat");
          final int _cursorIndexOfEndLng = CursorUtil.getColumnIndexOrThrow(_cursor, "endLng");
          final int _cursorIndexOfDistanceKm = CursorUtil.getColumnIndexOrThrow(_cursor, "distanceKm");
          final int _cursorIndexOfIsSafe = CursorUtil.getColumnIndexOrThrow(_cursor, "isSafe");
          final int _cursorIndexOfIsShaded = CursorUtil.getColumnIndexOrThrow(_cursor, "isShaded");
          final int _cursorIndexOfAvoidHighways = CursorUtil.getColumnIndexOrThrow(_cursor, "avoidHighways");
          final int _cursorIndexOfCo2SavedGrams = CursorUtil.getColumnIndexOrThrow(_cursor, "co2SavedGrams");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final List<Route> _result = new ArrayList<Route>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Route _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final double _tmpStartLat;
            _tmpStartLat = _cursor.getDouble(_cursorIndexOfStartLat);
            final double _tmpStartLng;
            _tmpStartLng = _cursor.getDouble(_cursorIndexOfStartLng);
            final double _tmpEndLat;
            _tmpEndLat = _cursor.getDouble(_cursorIndexOfEndLat);
            final double _tmpEndLng;
            _tmpEndLng = _cursor.getDouble(_cursorIndexOfEndLng);
            final double _tmpDistanceKm;
            _tmpDistanceKm = _cursor.getDouble(_cursorIndexOfDistanceKm);
            final boolean _tmpIsSafe;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsSafe);
            _tmpIsSafe = _tmp != 0;
            final boolean _tmpIsShaded;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsShaded);
            _tmpIsShaded = _tmp_1 != 0;
            final boolean _tmpAvoidHighways;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfAvoidHighways);
            _tmpAvoidHighways = _tmp_2 != 0;
            final double _tmpCo2SavedGrams;
            _tmpCo2SavedGrams = _cursor.getDouble(_cursorIndexOfCo2SavedGrams);
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            _item = new Route(_tmpId,_tmpName,_tmpStartLat,_tmpStartLng,_tmpEndLat,_tmpEndLng,_tmpDistanceKm,_tmpIsSafe,_tmpIsShaded,_tmpAvoidHighways,_tmpCo2SavedGrams,_tmpTimestamp);
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
  public Object getRouteById(final int id, final Continuation<? super Route> $completion) {
    final String _sql = "SELECT * FROM routes WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Route>() {
      @Override
      @Nullable
      public Route call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfStartLat = CursorUtil.getColumnIndexOrThrow(_cursor, "startLat");
          final int _cursorIndexOfStartLng = CursorUtil.getColumnIndexOrThrow(_cursor, "startLng");
          final int _cursorIndexOfEndLat = CursorUtil.getColumnIndexOrThrow(_cursor, "endLat");
          final int _cursorIndexOfEndLng = CursorUtil.getColumnIndexOrThrow(_cursor, "endLng");
          final int _cursorIndexOfDistanceKm = CursorUtil.getColumnIndexOrThrow(_cursor, "distanceKm");
          final int _cursorIndexOfIsSafe = CursorUtil.getColumnIndexOrThrow(_cursor, "isSafe");
          final int _cursorIndexOfIsShaded = CursorUtil.getColumnIndexOrThrow(_cursor, "isShaded");
          final int _cursorIndexOfAvoidHighways = CursorUtil.getColumnIndexOrThrow(_cursor, "avoidHighways");
          final int _cursorIndexOfCo2SavedGrams = CursorUtil.getColumnIndexOrThrow(_cursor, "co2SavedGrams");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final Route _result;
          if (_cursor.moveToFirst()) {
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final double _tmpStartLat;
            _tmpStartLat = _cursor.getDouble(_cursorIndexOfStartLat);
            final double _tmpStartLng;
            _tmpStartLng = _cursor.getDouble(_cursorIndexOfStartLng);
            final double _tmpEndLat;
            _tmpEndLat = _cursor.getDouble(_cursorIndexOfEndLat);
            final double _tmpEndLng;
            _tmpEndLng = _cursor.getDouble(_cursorIndexOfEndLng);
            final double _tmpDistanceKm;
            _tmpDistanceKm = _cursor.getDouble(_cursorIndexOfDistanceKm);
            final boolean _tmpIsSafe;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsSafe);
            _tmpIsSafe = _tmp != 0;
            final boolean _tmpIsShaded;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsShaded);
            _tmpIsShaded = _tmp_1 != 0;
            final boolean _tmpAvoidHighways;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfAvoidHighways);
            _tmpAvoidHighways = _tmp_2 != 0;
            final double _tmpCo2SavedGrams;
            _tmpCo2SavedGrams = _cursor.getDouble(_cursorIndexOfCo2SavedGrams);
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            _result = new Route(_tmpId,_tmpName,_tmpStartLat,_tmpStartLng,_tmpEndLat,_tmpEndLng,_tmpDistanceKm,_tmpIsSafe,_tmpIsShaded,_tmpAvoidHighways,_tmpCo2SavedGrams,_tmpTimestamp);
          } else {
            _result = null;
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

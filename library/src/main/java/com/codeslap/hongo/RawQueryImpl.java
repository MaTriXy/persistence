/*
 * Copyright 2013 CodeSlap
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.codeslap.hongo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import static com.codeslap.hongo.DataObjectFactory.getDataObject;

/** @author cristian */
class RawQueryImpl implements RawQuery {
  private final SQLiteDatabase mDb;

  RawQueryImpl(Context context, String name, String specId) {
    DatabaseSpec dbSpec = HongoConfig.getDatabaseSpec(specId);
    SqliteDb helper = SqliteDb.getInstance(context, name, dbSpec);
    mDb = helper.getDatabase();
  }

  @Override
  public Cursor findAll(Class<?> theClass) {
    DataObject<?> dataObject = getDataObject(theClass);
    return mDb.query(dataObject.getTableName(), null, null, null, null, null, null, null);
  }

  @Override
  public Cursor findAll(Object where) {
    return findAll(where, null);
  }

  @Override
  public Cursor findAll(Object where, Constraint constraint) {
    return SQLHelper.getCursorFindAllWhere(mDb, where.getClass(), where, null, constraint);
  }

  @Override
  public Cursor findAll(Object where, Object parent) {
    return SQLHelper.getCursorFindAllWhere(mDb, where.getClass(), where, parent, null);
  }

  @Override
  public Cursor findAll(Class<?> theClass, String where, String[] whereArgs) {
    DataObject<?> dataObject = getDataObject(theClass);
    return mDb.query(dataObject.getTableName(), null, where, whereArgs, null, null, null, null);
  }

  @Override
  public Cursor rawQuery(String rawQuery) {
    return mDb.rawQuery(rawQuery, null);
  }

  @Override
  public Cursor findAll(String table, String[] projection, String selection, String[] selectionArgs,
                        String groupBy, String having, String sortOrder, String limit) {
    return mDb
        .query(table, projection, selection, selectionArgs, groupBy, having, sortOrder, limit);
  }
}

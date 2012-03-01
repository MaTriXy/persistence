/*
 * Copyright 2012 CodeSlap
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

package com.codeslap.persistence;

import android.content.Context;

import java.util.List;

class QuickSqlAdapter implements SqlAdapter {

    private final Context mContext;
    private final String mDbName;

    QuickSqlAdapter(Context context, String dbName) {
        mContext = context;
        mDbName = dbName;
    }

    @Override
    public <T> Object store(T object) {
        SqlAdapter adapter = Persistence.getSqliteAdapter(mContext, mDbName);
        Object id = adapter.store(object);
        try {
            adapter.close();
        } catch (Exception e) {
        }
        return id;
    }

    @Override
    public <T, G> Object store(T object, G attachedTo) {
        SqlAdapter adapter = Persistence.getSqliteAdapter(mContext, mDbName);
        Object id = adapter.store(object, attachedTo);
        try {
            adapter.close();
        } catch (Exception e) {
        }
        return id;
    }

    @Override
    public <T> void storeCollection(List<T> collection, ProgressListener listener) {
        storeCollection(collection, null, listener);
    }

    @Override
    public <T, G> void storeCollection(List<T> collection, G attachedTo, ProgressListener listener) {
        SqlAdapter adapter = Persistence.getSqliteAdapter(mContext, mDbName);
        adapter.storeCollection(collection, attachedTo, listener);
        adapter.close();
    }

    @Override
    public <T> void storeUniqueCollection(List<T> collection, ProgressListener listener) {
        SqlAdapter adapter = Persistence.getSqliteAdapter(mContext, mDbName);
        adapter.storeUniqueCollection(collection, listener);
        adapter.close();
    }

    @Override
    public <T> int update(T object, T where) {
        SqlAdapter adapter = Persistence.getSqliteAdapter(mContext, mDbName);
        int count = adapter.update(object, where);
        adapter.close();
        return count;
    }

    @Override
    public <T> int update(T object, String where, String[] whereArgs) {
        SqlAdapter adapter = Persistence.getSqliteAdapter(mContext, mDbName);
        int count = adapter.update(object, where, whereArgs);
        adapter.close();
        return count;
    }

    @Override
    public <T> T findFirst(T where) {
        SqlAdapter adapter = Persistence.getSqliteAdapter(mContext, mDbName);
        T result = adapter.findFirst(where);
        adapter.close();
        return result;
    }

    @Override
    public <T> T findFirst(Class<T> clazz, String where, String[] whereArgs) {
        SqlAdapter adapter = Persistence.getSqliteAdapter(mContext, mDbName);
        T result = adapter.findFirst(clazz, where, whereArgs);
        adapter.close();
        return result;
    }

    @Override
    public <T> List<T> findAll(Class<T> clazz) {
        SqlAdapter adapter = Persistence.getSqliteAdapter(mContext, mDbName);
        List<T> feeds = adapter.findAll(clazz);
        adapter.close();
        return feeds;
    }

    @Override
    public <T> List<T> findAll(T where) {
        SqlAdapter adapter = Persistence.getSqliteAdapter(mContext, mDbName);
        List<T> feeds = adapter.findAll(where);
        adapter.close();
        return feeds;
    }

    @Override
    public <T> List<T> findAll(T where, Constraint constraint) {
        SqlAdapter adapter = Persistence.getSqliteAdapter(mContext, mDbName);
        List<T> feeds = adapter.findAll(where, constraint);
        adapter.close();
        return feeds;
    }

    @Override
    public <T, G> List<T> findAll(T where, G attachedTo) {
        SqlAdapter adapter = Persistence.getSqliteAdapter(mContext, mDbName);
        List<T> feeds = adapter.findAll(where, attachedTo);
        adapter.close();
        return feeds;
    }

    @Override
    public <T> List<T> findAll(Class<T> clazz, String where, String[] whereArgs) {
        SqlAdapter adapter = Persistence.getSqliteAdapter(mContext, mDbName);
        List<T> feeds = adapter.findAll(clazz, where, whereArgs);
        adapter.close();
        return feeds;
    }

    @Override
    public <T> int delete(T where) {
        return delete(where, false);
    }

    @Override
    public <T> int delete(T where, boolean onCascade) {
        SqlAdapter adapter = Persistence.getSqliteAdapter(mContext, mDbName);
        int delete = adapter.delete(where, onCascade);
        adapter.close();
        return delete;
    }

    @Override
    public <T> int delete(Class<T> clazz, String where, String[] whereArgs) {
        return delete(clazz, where, whereArgs, false);
    }

    @Override
    public <T> int delete(Class<T> theClass, String where, String[] whereArgs, boolean onCascade) {
        SqlAdapter adapter = Persistence.getSqliteAdapter(mContext, mDbName);
        int delete = adapter.delete(theClass, where, whereArgs, onCascade);
        adapter.close();
        return delete;
    }

    @Override
    public void truncate(Class<?>... classes) {
        SqlAdapter adapter = Persistence.getSqliteAdapter(mContext, mDbName);
        adapter.truncate(classes);
        adapter.close();
    }

    @Override
    public <T> int count(T where) {
        SqlAdapter adapter = Persistence.getSqliteAdapter(mContext, mDbName);
        int count = adapter.count(where);
        adapter.close();
        return count;
    }

    @Override
    public <T> int count(Class<T> clazz, String where, String[] whereArgs) {
        SqlAdapter adapter = Persistence.getSqliteAdapter(mContext, mDbName);
        int count = adapter.count(clazz, where, whereArgs);
        adapter.close();
        return count;
    }

    @Override
    public <T> int count(Class<T> clazz) {
        SqlAdapter adapter = Persistence.getSqliteAdapter(mContext, mDbName);
        int count = adapter.count(clazz);
        adapter.close();
        return count;
    }

    @Override
    public void close() {
        throw new UnsupportedOperationException("QuickSqlAdapter does not have an implementation of this method");
    }
}
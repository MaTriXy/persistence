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

import android.util.Log;
import com.codeslap.test.hongo.RobolectricSqliteRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.shadows.ShadowLog;

import static org.junit.Assert.assertEquals;

/**
 * @author cristian
 */
@RunWith(RobolectricSqliteRunner.class)
public class HongoLogManagerTest {

  @Before
  public void before() {
    ShadowLog.getLogs().clear();
  }

  @Test
  public void testLogManagerWithLogger() {
    HongoLogManager.register(new HongoLogManager.Logger() {
      @Override
      public String getTag() {
        return "test";
      }

      @Override
      public boolean active() {
        return true;
      }
    });

    HongoLogManager.d("foo", "bar");
    assertEquals("test:hongo:foo", ShadowLog.getLogs().get(0).tag);
    assertEquals("bar", ShadowLog.getLogs().get(0).msg);
    assertEquals(Log.DEBUG, ShadowLog.getLogs().get(0).type);

    ShadowLog.getLogs().clear();

    HongoLogManager.e("bar", "foo");
    assertEquals("test:hongo:bar", ShadowLog.getLogs().get(0).tag);
    assertEquals("foo", ShadowLog.getLogs().get(0).msg);
    assertEquals(Log.ERROR, ShadowLog.getLogs().get(0).type);
  }

  @Test
  public void testLogManagerWithLoggerInactive() {
    HongoLogManager.clear();
    HongoLogManager.register(new HongoLogManager.Logger() {
      @Override
      public String getTag() {
        return "test";
      }

      @Override
      public boolean active() {
        return false;
      }
    });

    HongoLogManager.d("foo", "bar");
    assertEquals(0, ShadowLog.getLogs().size());

    HongoLogManager.e("bar", "foo");
    assertEquals(0, ShadowLog.getLogs().size());
  }

  @Test
  public void testDirectLogManager() {
    HongoLogManager.register("test");
    HongoLogManager.register("test");

    HongoLogManager.d("foo", "bar");
    assertEquals("test:hongo:foo", ShadowLog.getLogs().get(0).tag);
    assertEquals("bar", ShadowLog.getLogs().get(0).msg);
    assertEquals(Log.DEBUG, ShadowLog.getLogs().get(0).type);

    ShadowLog.getLogs().clear();

    HongoLogManager.e("bar", "foo");
    assertEquals("test:hongo:bar", ShadowLog.getLogs().get(0).tag);
    assertEquals("foo", ShadowLog.getLogs().get(0).msg);
    assertEquals(Log.ERROR, ShadowLog.getLogs().get(0).type);
  }
}

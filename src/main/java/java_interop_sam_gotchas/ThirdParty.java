/*
 * Copyright 2020 Nazmul Idris All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package java_interop_sam_gotchas;

import java.util.ArrayList;
import java.util.List;

public final class ThirdParty {

public static final String TAG = "ThirdParty";

public List<Callback> callbacks = new ArrayList<>();

public void addCallback(Callback callback) {
  Log.d(TAG, "addCallback: " + callback);

  callbacks.add(callback);
}

public void removeCallback(Callback callback) {
  Log.d(TAG, "removeCallback: " + callback);

  callbacks.remove(callback);
}

public void printState() {
  Log.d("ThirdParty", "Callbacks count: " + callbacks.size());
}

}
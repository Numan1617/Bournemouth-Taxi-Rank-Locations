/*
 * Copyright 2014 Stefan Urech
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 *    limitations under the License
 */

package com.numan1617.bournemouthlocations.halarious;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

/**
 * Schliesst alle Felder aus, welche wir selbst verwalten
 * @author surech
 */
public class HalExclusionStrategy implements ExclusionStrategy {

    @Override
    public boolean shouldSkipField(FieldAttributes f) {
        // Auf Links überprüfen
        if (HalReflectionHelper.isLink(f)) {
            return true;
        }

        // Auf eingebettete Resourcen überprüfen
        if (HalReflectionHelper.isResource(f)) {
            return true;
        }
        
        // Alles in Ordnung, Feld darf verarbeitet werden
        return false;
    }

    @Override
    public boolean shouldSkipClass(Class<?> clazz) {
        // Grundsätzlich akzeptieren wir alle Klassen
        return false;
    }
}

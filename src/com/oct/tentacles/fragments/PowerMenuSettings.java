/*
 * Copyright (C) 2014 CarbonDev
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.oct.tentacles.fragments;

import android.content.ContentResolver;
import android.os.Bundle;
import android.os.UserHandle;
import android.os.UserManager;
import android.provider.Settings;

import com.oct.tentacles.R;
import com.oct.tentacles.preference.SettingsPreferenceFragment;

public class PowerMenuSettings extends SettingsPreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.power_menu_settings);

        final ContentResolver resolver = getContentResolver();

        // Only enable profiles item if System Profiles are also enabled
        findPreference(Settings.System.POWER_MENU_PROFILES_ENABLED).setEnabled(
                Settings.System.getInt(resolver, Settings.System.SYSTEM_PROFILES_ENABLED, 1) != 0);

        if (!UserHandle.MU_ENABLED || !UserManager.supportsMultipleUsers()) {
            getPreferenceScreen().removePreference(
                    findPreference(Settings.System.POWER_MENU_USER_ENABLED));
        }
    }
}

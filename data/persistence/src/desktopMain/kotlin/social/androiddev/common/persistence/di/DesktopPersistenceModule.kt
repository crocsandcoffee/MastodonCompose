/*
 * This file is part of Dodo.
 *
 * Dodo is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * Dodo is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with Dodo.
 * If not, see <https://www.gnu.org/licenses/>.
 */
package social.androiddev.common.persistence.di

import com.russhwolf.settings.PreferencesSettings
import com.squareup.sqldelight.sqlite.driver.JdbcSqliteDriver
import kotlinx.serialization.json.Json
import org.koin.core.module.Module
import org.koin.dsl.module
import social.androiddev.common.persistence.AuthenticationDatabase
import social.androiddev.common.persistence.localstorage.DodoAuthStorage
import social.androiddev.common.persistence.localstorage.DodoAuthStorageImpl
import social.androiddev.common.timeline.TimelineDatabase

/**
 * Koin DI module for all desktop specific persistence dependencies
 */
actual val persistenceModule: Module = module {
    single<DodoAuthStorage> {
        DodoAuthStorageImpl(
            settings = PreferencesSettings
                .Factory()
                .create(AUTH_SETTINGS_NAME),
            json = Json {
                ignoreUnknownKeys = true
                encodeDefaults = false
            }
        )
    }

    single {
        val driver = JdbcSqliteDriver(url = JdbcSqliteDriver.IN_MEMORY).also { driver ->
            AuthenticationDatabase.Schema.create(driver = driver)
        }
        AuthenticationDatabase(driver)
    }

    single {
        val driver = JdbcSqliteDriver(url = JdbcSqliteDriver.IN_MEMORY).also { driver ->
            TimelineDatabase.Schema.create(driver = driver)
        }
        TimelineDatabase(driver)
    }
}

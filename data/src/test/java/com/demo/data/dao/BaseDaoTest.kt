package com.demo.data.dao

import android.os.Build
import androidx.room.Room
import com.demo.data.database.TandemDatabase
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
abstract class BaseDaoTest {

    protected lateinit var testDatabase: TandemDatabase

    @Before
    fun initDatabase() {
        val context = RuntimeEnvironment.application
        testDatabase =
            Room.inMemoryDatabaseBuilder(context, TandemDatabase::class.java)
                .allowMainThreadQueries()
                .build()
    }

    @After
    fun closeDb() {
        testDatabase.close()
    }
}

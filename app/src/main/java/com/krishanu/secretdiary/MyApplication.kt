package com.krishanu.secretdiary

import android.app.Application
import androidx.room.Room
import com.krishanu.secretdiary.data.database.AppDatabase
import com.krishanu.secretdiary.data.repository.NoteRepository
import com.krishanu.secretdiary.ui.viewmodel.HomeViewModel
import org.koin.android.ext.koin.androidApplication

import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MyApplication: Application() {


    override fun onCreate() {
        super.onCreate()


        val viewModelModule = module {
            viewModel { HomeViewModel(get()) }
        }

        val databaseModule = module {
            single {
                Room.databaseBuilder(
                    androidApplication(),
                    AppDatabase::class.java,
                    "secret_diary_db"
                ).build()
            }

            single { get<AppDatabase>().noteDao() }
            single { NoteRepository(get()) }
        }

        startKoin {
            androidContext(this@MyApplication)
            modules(listOf(databaseModule, viewModelModule))
        }
    }
}
package com.galartt.data

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.galartt.model.Galeria

@Database(entities = [Galeria::class], version = 1, exportSchema = false)

abstract class GaleriaDatabase : RoomDatabase()  {

    abstract fun galeriaDao() : GaleriaDao

    companion object{

        @Volatile
        private var INSTANCE: GaleriaDatabase? = null

        fun getDatabase(context: android.content.Context) : GaleriaDatabase{
            val tempInstance = INSTANCE

            if(tempInstance != null){
                return tempInstance
            }

            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    GaleriaDatabase::class.java,
                    "galeria_database"
                ).build()

                INSTANCE = instance
                return instance
            }
        }
    }
}
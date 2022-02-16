package lab.maxb.currency_converter.presentation.repository.implementations.localDb.room

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import lab.maxb.currency_converter.presentation.repository.implementations.localDb.room.dao.CurrencyConverterDAO
import lab.maxb.currency_converter.presentation.repository.implementations.localDb.room.dao.CurrencyDAO
import lab.maxb.currency_converter.presentation.repository.implementations.localDb.room.model.CurrencyConverterDTO
import lab.maxb.currency_converter.presentation.repository.implementations.localDb.room.model.CurrencyDTO

@Database(entities = [
            CurrencyDTO::class,
            CurrencyConverterDTO::class,
          ], version = 1, exportSchema = false)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun currencyConverterDao(): CurrencyConverterDAO
    abstract fun currencyDao(): CurrencyDAO

    companion object {
        fun buildDatabase(app: Application)
            = Room.databaseBuilder(
                app.applicationContext,
                LocalDatabase::class.java, "currency_converter_database"
            ).allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }
}
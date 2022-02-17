package lab.maxb.currency_converter.presentation.repository.implementations.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow
import lab.maxb.currency_converter.presentation.repository.implementations.local.room.model.CurrencyDTO

@Dao
interface CurrencyDAO {
    @Insert(onConflict = IGNORE)
    suspend fun saveAll(currency: List<CurrencyDTO>)

    @Transaction
    @Query("SELECT * FROM currencies")
    fun getAll(): Flow<List<CurrencyDTO>>

    @Transaction
    @Query("SELECT * FROM currencies JOIN converters ON code = fromCode")
    fun getAvailable(): Flow<List<CurrencyDTO>>

    @Transaction
    @Query("SELECT * FROM currencies JOIN converters ON code = toCode WHERE fromCode = :from")
    fun getAvailableConversionOptions(from: String): Flow<List<CurrencyDTO>>
}
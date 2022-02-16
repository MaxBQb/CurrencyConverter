package lab.maxb.currency_converter.presentation.repository.implementations.localDb.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow
import lab.maxb.currency_converter.presentation.repository.implementations.localDb.room.model.CurrencyConverterDTO
import lab.maxb.currency_converter.presentation.repository.implementations.localDb.room.relations.FullCurrencyConverter

@Dao
interface CurrencyConverterDAO {
    @Insert(onConflict = REPLACE)
    suspend fun save(currencyConverter: CurrencyConverterDTO)

    @Transaction
    @Query("SELECT * FROM converters WHERE fromCode = :from AND toCode = :to")
    fun get(from: String, to: String): Flow<FullCurrencyConverter?>
}
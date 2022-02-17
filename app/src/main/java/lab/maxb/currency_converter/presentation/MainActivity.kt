package lab.maxb.currency_converter.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import lab.maxb.currency_converter.R
import lab.maxb.currency_converter.presentation.view.MainFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }
}
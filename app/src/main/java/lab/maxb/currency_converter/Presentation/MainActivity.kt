package lab.maxb.currency_converter.Presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import lab.maxb.currency_converter.Presentation.View.MainFragment
import lab.maxb.currency_converter.R

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
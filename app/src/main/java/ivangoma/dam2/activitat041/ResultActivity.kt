package ivangoma.dam2.activitat041

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ivangoma.dam2.activitat041.CalculatorActivity.Companion.IMC_KEY

class ResultActivity : AppCompatActivity() {

    private lateinit var tvEstat: TextView
    private lateinit var tvValor: TextView
    private lateinit var tvDesc: TextView
    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result)
        val result = intent.extras?.getDouble(IMC_KEY) ?: -1.0
        initComponents()
        initUI(result)
        initListeners()
    }

    private fun initListeners() {

        button.setOnClickListener {
            onBackPressed()
        }
    }

    private fun initUI(result: Double) {
        tvValor.text=result.toString()
        when(result){
            in 0.00..18.50 -> {
                tvEstat.text = getString(R.string.title_low)
                tvEstat.setTextColor(ContextCompat.getColor(this,R.color.dolent))
                tvDesc.text = getString(R.string.desc_low)
                tvDesc.setTextColor(ContextCompat.getColor(this,R.color.dolent))
            }
            in 18.51..29.99 -> {
                tvEstat.text = getString(R.string.title_medium)
                tvEstat.setTextColor(ContextCompat.getColor(this,R.color.normal))
                tvDesc.text = getString(R.string.desc_medium)
                tvDesc.setTextColor(ContextCompat.getColor(this,R.color.normal))
            }
            in 30.00..99.00 -> {
                tvEstat.text = getString(R.string.title_high)
                tvEstat.setTextColor(ContextCompat.getColor(this,R.color.dolent))
                tvDesc.text = getString(R.string.desc_high)
                tvDesc.setTextColor(ContextCompat.getColor(this,R.color.dolent))
            }
            else -> {
                tvEstat.text = getString(R.string.error)
                tvValor.text = getString(R.string.error)
                tvDesc.text = getString(R.string.error)
            }

        }
    }

    private fun initComponents() {
        tvEstat = findViewById(R.id.estat)
        tvValor = findViewById(R.id.valor)
        tvDesc = findViewById(R.id.descripcio)
        button=findViewById(R.id.btnRecalcular)

    }
}
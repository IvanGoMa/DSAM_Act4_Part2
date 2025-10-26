package ivangoma.dam2.activitat041;

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat;
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider
import java.text.DecimalFormat



class CalculatorActivity: AppCompatActivity()  {
    private var isMaleSelected:Boolean = true
    private var isFemaleSelected:Boolean = false
    private var currentWeight: Int = 70
    private var currentAge: Int = 30
    private var currentHeigth: Int = 120

    private lateinit var viewMale: CardView
    private lateinit var viewFemale:CardView
    private lateinit var tvHeigth: TextView
    private lateinit var rsHeigth: RangeSlider
    private lateinit var btnSubstractWeight: FloatingActionButton
    private lateinit var btnPlusWeight: FloatingActionButton
    private lateinit var tvWeight: TextView
    private lateinit var btnPlusAge: FloatingActionButton
    private lateinit var btnSubstractAge: FloatingActionButton
    private lateinit var tvAge: TextView
    private lateinit var btnCalcular: Button

    companion object{
        const val IMC_KEY = "IMC_RESULT"
    }


    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)
        initComponents()
        initListeners()
        initUI()
    }

    private fun initComponents(){
        viewMale = findViewById(R.id.CVMale)
        viewFemale = findViewById(R.id.CVFemale)
        tvHeigth = findViewById(R.id.tvAlcada)
        tvWeight= findViewById(R.id.tvPes)
        tvAge=findViewById(R.id.tvEdat)
        rsHeigth=findViewById(R.id.slider)
        btnPlusWeight=findViewById(R.id.btnMasPeso)
        btnSubstractWeight=findViewById(R.id.btnMenosPeso)
        btnSubstractAge=findViewById(R.id.btnMenosEdad)
        btnPlusAge=findViewById(R.id.btnMasEdad)
        btnCalcular=findViewById(R.id.btnCalcular)
    }

    private fun initListeners(){
        viewMale.setOnClickListener{
            changeGender()
            setGenderColor()
        }
        viewFemale.setOnClickListener{
            changeGender()
            setGenderColor()
        }
        rsHeigth.addOnChangeListener { _, value, _ ->
            currentHeigth =value.toInt()
            tvHeigth.text = "$currentHeigth cm"
        }
        btnPlusAge.setOnClickListener {
            currentAge += 1
            setAge()
        }
        btnSubstractAge.setOnClickListener{
            currentAge -= 1
            setAge()
        }
        btnPlusWeight.setOnClickListener {
            currentWeight += 1
            setWeight()
        }
        btnSubstractWeight.setOnClickListener{
            currentWeight -= 1
            setWeight()
        }
        btnCalcular.setOnClickListener {
            val resultado = calcularIMC()
            //navigateToResult(resultado)
        }

    }

    private fun navigateToResult(result:Double){
        val intent = Intent(this, ResultActivity::class.java)
        intent.putExtra(IMC_KEY, result)
        startActivity(intent)
    }


    private fun calcularIMC():Double {
        val df = DecimalFormat("#.##")
        val imc = currentWeight/(currentHeigth.toDouble()/100*currentHeigth.toDouble()/100)
        return df.format(imc).toDouble()
    }
    private fun setAge(){
        tvAge.text= currentAge.toString()
    }
    private fun setWeight(){
        tvWeight.text = currentWeight.toString()
    }

    private fun changeGender(){
        isMaleSelected = !isMaleSelected
        isFemaleSelected = !isFemaleSelected
    }
    private fun setGenderColor(){
        viewMale.setCardBackgroundColor(getBackgroundColor(isMaleSelected))
        viewFemale.setCardBackgroundColor(getBackgroundColor(isFemaleSelected))
    }

    private fun getBackgroundColor(isSelectedComponent: Boolean): Int{
        val colorReference = if(isSelectedComponent){
            R.color.button
        } else {
            R.color.card
        }
        return ContextCompat.getColor(this,colorReference)
    }

    private fun initUI(){
        setGenderColor()
        setWeight()
        setAge()
    }
}

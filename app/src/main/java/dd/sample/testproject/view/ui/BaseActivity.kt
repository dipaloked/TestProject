package dd.sample.testproject.view.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun showToast(msg: String){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
    }
}

package dd.sample.testproject.view.ui

import androidx.databinding.DataBindingUtil
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import dd.sample.testproject.view.adapter.ItemsAdapter
import dd.sample.testproject.viewmodel.MainViewModel
import dd.sample.testproject.R
import dd.sample.testproject.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    private lateinit var lazyAdapter : ItemsAdapter
    private lateinit var dataBinding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding= DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        supportActionBar?.title=""// reset actionbar's default title

        val mainViewModel=ViewModelProviders.of(this).get(MainViewModel::class.java)
        dataBinding.executePendingBindings()

        setObservers(mainViewModel)
        lazyAdapter= ItemsAdapter(applicationContext, mutableListOf())
        dataBinding.lazyListview.adapter=lazyAdapter

        dataBinding.buttonRefresh.setOnClickListener {
            mainViewModel.loadItems()
        }
    }

    private fun setObservers(viewModel : MainViewModel){
        viewModel.getError().observe(this, Observer {
            showToast(it)
        })

        viewModel.getTitle().observe(this, Observer {
            supportActionBar?.title=it
        })

        viewModel.getItems().observe(this, Observer {
            lazyAdapter.updateAdapter(it)
            dataBinding.lazyListview.setSelection(0)
        })
    }
}

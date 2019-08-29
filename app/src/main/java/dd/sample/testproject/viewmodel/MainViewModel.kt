package dd.sample.testproject.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import dd.sample.testproject.model.Item
import dd.sample.testproject.model.repository.ItemRepository
import dd.sample.testproject.utils.NetManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainViewModel(application: Application) : AndroidViewModel(application){

    private var items = MutableLiveData<MutableList<Item>>()
    private var title = MutableLiveData<String>()
    private var errorMessage = MutableLiveData<String>()

    init {
        loadItems()
    }

    fun getItems(): MutableLiveData<MutableList<Item>> = items
    fun getTitle(): MutableLiveData<String> = title
    fun getError(): MutableLiveData<String> = errorMessage

    fun loadItems(){

        if(NetManager.isConnected(getApplication()))
            ItemRepository.getItems()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe ({
                    items.value=it.rows
                    title.value=it.title

                }, { error ->
                    errorMessage.value=error.localizedMessage
                })
        else
            errorMessage.value="Check your internet connectivity"
    }
}
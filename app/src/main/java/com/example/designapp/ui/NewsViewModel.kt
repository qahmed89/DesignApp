package com.example.designapp.ui

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.designapp.data.model.TopHeadlinesResponse
import com.example.designapp.other.Event
import com.example.designapp.other.Resource
import com.example.designapp.repository.NewsRepository
import kotlinx.coroutines.launch

class NewsViewModel @ViewModelInject constructor (
    private val repository: NewsRepository,
    @Assisted state : SavedStateHandle
):ViewModel() {
//    val currentcountry = state.getLiveData("country","us")
private  val _data: MutableLiveData<Event<Resource<TopHeadlinesResponse>>> = MutableLiveData()

    val data :LiveData<Event<Resource<TopHeadlinesResponse>>> = _data
    val currentspage = state.getLiveData("page",data).value

 var x = MutableLiveData<Int>()

    fun setData (page : Int , country :String  ) {
        _data.value = Event(Resource.loading(null))

            viewModelScope.launch {
                val response =
                        repository.getTopHedlines(country, page)
                _data.value = Event(response)


        }
    }
    }





package com.example.myapplication.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.model.Article
import com.example.usecase.GetArticlesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getArticlesUseCase: GetArticlesUseCase
) : ViewModel() {

    private val _articles: MutableLiveData<List<Article>> = MutableLiveData(listOf())
    val articles: LiveData<List<Article>> = _articles

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    fun getArticles() {
        _isLoading.postValue(true)
        getArticlesUseCase.execute(GetArticlesUseCase.Params(null, 1))
            .onEach {
                _articles.postValue(it)
            }
            .catch { }
            .onCompletion {
                _isLoading.postValue(false)
            }
            .launchIn(viewModelScope)
    }
}
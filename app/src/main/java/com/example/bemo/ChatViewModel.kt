package com.example.bemo

import androidx.lifecycle.ViewModel
import com.example.bemo.domain.repository.MyRepository
import dagger.Lazy
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val myRepository: Lazy<MyRepository>
) : ViewModel() {

}
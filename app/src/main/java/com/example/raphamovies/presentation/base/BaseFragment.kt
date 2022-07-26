package com.example.raphamovies.presentation.base

import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import com.example.raphamovies.utils.Command

abstract class BaseFragment: Fragment() {
    abstract var command: MutableLiveData<Command>
}
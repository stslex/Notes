package com.stslex93.notes.utilites

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.stslex93.notes.appComponent
import dagger.Lazy
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

open class BaseFragment : Fragment() {

    @Inject
    open lateinit var viewModelFactory: Lazy<ViewModelProvider.Factory>

    @ExperimentalCoroutinesApi
    override fun onAttach(context: Context) {
        super.onAttach(context)
        requireContext().appComponent.inject(this)
    }
}
package com.stslex93.notes.ui.edit

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.transition.MaterialContainerTransform
import com.stslex93.core.Resource
import com.stslex93.notes.R
import com.stslex93.notes.appComponent
import com.stslex93.notes.databinding.FragmentEditBinding
import com.stslex93.notes.ui.model.NoteUIModel
import com.stslex93.notes.ui.utils.snackbar.SnackBarUtil
import com.stslex93.notes.ui.utils.time.TimeUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ExperimentalCoroutinesApi
class EditFragment : Fragment() {

    private var _binding: FragmentEditBinding? = null
    private val binding get() = checkNotNull(_binding)

    private lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var timeUtil: TimeUtil
    private lateinit var snackBarUtil: SnackBarUtil
    private val args: EditFragmentArgs by navArgs()
    private val viewModel: EditNoteViewModel by viewModels { viewModelFactory }

    @Inject
    fun injection(
        viewModelFactory: ViewModelProvider.Factory,
        timeUtil: TimeUtil,
        snackBarUtil: SnackBarUtil
    ) {
        this.viewModelFactory = viewModelFactory
        this.timeUtil = timeUtil
        this.snackBarUtil = snackBarUtil
    }

    override fun onAttach(context: Context) {
        requireActivity().appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = MaterialContainerTransform().apply {
            drawingViewId = R.id.nav_host_fragment
            duration = resources.getInteger(R.integer.transition_duration).toLong()
            scrimColor = Color.TRANSPARENT
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.editCardView.transitionName = args.transitionName
        if (args.edit) getNoteJob.start()
        else binding.editTime.text = currentTime
        binding.editFragmentReturn.setOnClickListener(returnClickListener)
    }

    private val returnClickListener = View.OnClickListener {
        findNavController().popBackStack()
    }

    private val currentTime: String
        get() = "${getString(R.string.label_edit)}: ${timeUtil.getCurrentTime()}"

    private val getNoteJob by lazy {
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
            viewModel.getNoteById(args.id).collect(::collector)
        }
    }

    private suspend fun collector(resource: Resource<NoteUIModel>) {
        when (resource) {
            is Resource.Success -> resource.result()
            is Resource.Failure -> Unit
            is Resource.Loading -> Unit
        }
    }

    private suspend fun Resource.Success<NoteUIModel>.result() = withContext(Dispatchers.Main) {
        with(binding) {
            data.bindEditNote(editInputTitle, editInputContent)
            data.setLastEditTime(editTime, getString(R.string.label_edit))
        }
    }

    override fun onStop() {
        super.onStop()
        if (title.isNotEmpty() || content.isNotEmpty()) viewModel.insertNote(noteFromThisPage)
    }

    private val noteFromThisPage
        get() = NoteUIModel.Base(
            id = args.id,
            title = title,
            content = content,
            timestamp = System.currentTimeMillis()
        )

    private val title: String
        get() = binding.editInputTitle.editText?.text.toString()

    private val content: String
        get() = binding.editInputContent.editText?.text.toString()

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        getNoteJob.cancel()
    }
}
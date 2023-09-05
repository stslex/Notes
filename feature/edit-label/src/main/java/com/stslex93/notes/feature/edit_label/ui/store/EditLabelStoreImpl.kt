package com.stslex93.notes.feature.edit_label.ui.store

import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.stslex.aproselection.core.core.Logger
import com.stslex93.notes.core.ui.base.store.BaseStoreImpl
import com.stslex93.notes.core.ui.emptyImmutableSet
import com.stslex93.notes.feature.edit_label.domain.interactor.EditLabelInteractor
import com.stslex93.notes.feature.edit_label.domain.model.LabelDomain
import com.stslex93.notes.feature.edit_label.ui.model.Label
import com.stslex93.notes.feature.edit_label.ui.model.toUi
import com.stslex93.notes.feature.edit_label.ui.store.EditLabelStore.Action
import com.stslex93.notes.feature.edit_label.ui.store.EditLabelStore.Event
import com.stslex93.notes.feature.edit_label.ui.store.EditLabelStore.State
import kotlinx.collections.immutable.toImmutableSet
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

class EditLabelStoreImpl @Inject constructor(
    private val interactor: EditLabelInteractor
) : EditLabelStore, BaseStoreImpl<State, Event, Action>() {

    override val initialState: State = State(
        notesIds = emptyImmutableSet(),
        query = "",
        labels = ::labels
    )

    override val state: MutableStateFlow<State> = MutableStateFlow(initialState)

    @OptIn(ExperimentalCoroutinesApi::class)
    private val labels: StateFlow<PagingData<Label>>
        get() = state
            .map { currentState ->
                currentState.query
            }
            .distinctUntilChanged()
            .flatMapLatest(interactor::searchLabels)
            .map { pagingData ->
                pagingData.map { note -> note.toUi() }
            }
            .cachedIn(scope)
            .flowOn(Dispatchers.IO)
            .stateIn(
                scope = scope,
                started = SharingStarted.Lazily,
                initialValue = PagingData.empty()
            )

    override fun processAction(action: Action) {
        when (action) {
            is Action.Init -> initStore(action)
            Action.BackPressed -> sendEvent(Event.Navigation.OnBackPressed)
            Action.AddLabelClicked -> onCreateClicked()
            is Action.QueryInput -> onQueryInput(action)
            is Action.OnLabelSelected -> onLabelSelected(action)
        }
    }

    private fun onLabelSelected(action: Action.OnLabelSelected) {
        val currentState = state.value
        scope.launch(Dispatchers.IO) {
            runCatching {
                interactor.addLabel(
                    noteIds = currentState.notesIds,
                    labelUuid = action.labelUuid
                )
            }.onSuccess {
                sendEvent(Event.Navigation.OnBackPressed)
            }.onFailure { exception ->
                Logger.exception(exception)
                sendEvent(Event.SnackbarError)
            }
        }
    }

    private fun initStore(action: Action.Init) {
        updateState { currentState ->
            currentState.copy(
                notesIds = action.noteIds.toImmutableSet()
            )
        }
    }

    private fun onQueryInput(action: Action.QueryInput) {
        updateState { currentState ->
            currentState.copy(
                query = action.text.take(15)
            )
        }
    }

    private fun onCreateClicked() {
        val currentState = state.value
        if (currentState.query.isBlank()) return
        scope.launch(Dispatchers.IO) {
            runCatching {
                interactor.createLabelAndAdd(
                    noteIds = currentState.notesIds,
                    label = LabelDomain(
                        uuid = UUID.randomUUID().toString(),
                        title = currentState.query,
                    )
                )
            }.onSuccess {
                sendEvent(Event.Navigation.OnBackPressed)
            }.onFailure { exception ->
                Logger.exception(exception)
                sendEvent(Event.SnackbarError)
            }
        }
    }
}
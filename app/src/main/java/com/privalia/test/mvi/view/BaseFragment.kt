package com.privalia.test.mvi.view

import android.os.Bundle
import android.os.Parcelable
import android.support.v4.app.Fragment
import com.privalia.test.mvi.BaseEvent
import com.privalia.test.mvi.toLiveData
import com.privalia.test.mvi.view.BaseView.UI_MODEL
import com.privalia.test.mvi.viewmodel.BaseViewModel
import io.reactivex.Observable

/**
 * @author Zeyad Gasser.
 */
abstract class BaseFragment<S : Parcelable, VM : BaseViewModel<S>> : Fragment(), LoadDataView<S> {

    lateinit var viewModel: VM
    lateinit var viewState: S

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
        if (savedInstanceState != null || arguments != null)
            viewState = BaseView.getViewStateFrom(savedInstanceState, arguments)
        initialize()
    }

    override fun onStart() {
        super.onStart()
        viewModel.uiModels(viewState).toLiveData()
                .observe(this, UIObserver(this, errorMessageFactory()))
        viewModel.processEvents(events())
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelable(UI_MODEL, viewState)
        super.onSaveInstanceState(outState)
    }

    override fun setState(bundle: S) {
        viewState = bundle
    }

    abstract fun errorMessageFactory(): ErrorMessageFactory

    /**
     * Initialize any objects or any required dependencies.
     */
    abstract fun initialize()

    /**
     * Merge all events into one [Observable].
     *
     * @return [Observable].
     */
    abstract fun events(): Observable<BaseEvent<*>>
}
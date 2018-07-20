package com.privalia.test.mvi.view

import android.os.Bundle
import android.os.Parcelable
import android.support.v7.app.AppCompatActivity
import android.support.v7.app.AppCompatDelegate
import com.privalia.test.mvi.BaseEvent
import com.privalia.test.mvi.view.BaseView.UI_MODEL
import com.privalia.test.mvi.viewmodel.BaseViewModel
import com.privalia.test.toLiveData
import io.reactivex.Observable

/**
 * @author Zeyad Gasser.
 */
abstract class BaseActivity<S : Parcelable, VM : BaseViewModel<S>> : AppCompatActivity(), LoadDataView<S> {
    lateinit var viewModel: VM
    lateinit var viewState: S

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        val isNew = savedInstanceState == null
        if (!isNew || intent != null)
            viewState = BaseView.getViewStateFrom(savedInstanceState, intent)
        initialize()
        setupUI(isNew)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        viewState = BaseView.getViewStateFrom(savedInstanceState, Bundle.EMPTY)
    }

    override fun onSaveInstanceState(bundle: Bundle) {
        bundle.putParcelable(UI_MODEL, viewState)
        super.onSaveInstanceState(bundle)
    }

    override fun onStart() {
        super.onStart()
        viewModel.uiModels(viewState).toLiveData()
                .observe(this, UIObserver<LoadDataView<S>, S>(this, errorMessageFactory()))
        viewModel.processEvents(events())
    }

    override fun setState(bundle: S) {
        viewState = bundle
    }

    abstract fun errorMessageFactory(): ErrorMessageFactory

    /**
     * Initialize objects or any required dependencies.
     */
    abstract fun initialize()

    /**
     * Setup the UI.
     *
     * @param isNew = savedInstanceState == null
     */
    abstract fun setupUI(isNew: Boolean)

    /**
     * Merge all events into one [Observable].
     *
     * @return [Observable].
     */
    abstract fun events(): Observable<BaseEvent<*>>
}
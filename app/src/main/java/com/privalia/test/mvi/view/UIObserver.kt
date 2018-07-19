package com.privalia.test.mvi.view

import android.arch.lifecycle.Observer
import android.util.Log
import com.privalia.test.mvi.UIModel

/**
 * @author Zeyad Gasser.
 */
class UIObserver<V : LoadDataView<S>, S>(private val view: V, private val errorMessageFactory: ErrorMessageFactory) :
        Observer<UIModel<S>> {
    override fun onChanged(uiModel: UIModel<S>?) {
        Log.d("onNext", "UIModel: " + uiModel.toString())
        val loading = uiModel?.isLoading!!
        val event = uiModel.getEvent()!!
        view.toggleViews(loading, event)
        if (!loading) {
            if (uiModel.isSuccessful) {
                val bundle = uiModel.getBundle()
                view.setState(bundle)
                view.renderSuccessState(bundle, event)
            } else {
                val error = uiModel.throwable
                if (error != null) {
                    Log.e("UIObserver", "onChanged", error)
                    view.showError(errorMessageFactory.getErrorMessage(error), event)
                }
            }
        }
    }
}
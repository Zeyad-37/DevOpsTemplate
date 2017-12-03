package com.dd.template.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import com.dd.template.BuildConfig
import com.dd.template.R
import com.dd.template.utils.ViewUtils.findById

class MainActivity : AppCompatActivity(), MainView {

    private var presenter: MainPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val appVersionView = findById<TextView>(this, R.id.appVersionView)
        appVersionView.text = getString(R.string.main_app_version, BuildConfig.VERSION_NAME)

        val appNameView = findById<TextView>(this, R.id.appNameView)
        appNameView.text = getString(R.string.main_app_name, getString(R.string.app_name))

        val appVersionCodeView = findById<TextView>(this, R.id.appVersionCodeView)
        appVersionCodeView.text = getString(R.string.main_app_version_code, BuildConfig.VERSION_CODE)

        val exitView = findById<Button>(this, R.id.exitView)
        exitView.setOnClickListener { presenter!!.onExitClicked() }

        presenter = MainPresenter(this)
    }

    override fun exit() {
        finish()
    }
}

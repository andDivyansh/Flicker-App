package com.divyansh.flicker.activity

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.divyansh.flicker.R
import com.divyansh.flicker.adapter.FlickerAdapter
import com.divyansh.flicker.dialog.ProgressDialog
import com.divyansh.flicker.viewmodel.FlickerViewModel
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

/**
 * Created by Divyansh Srivastava on 2019-10-11.
 *
 */
class MainActivity : AppCompatActivity(), HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var flickerViewModel: FlickerViewModel

    private var dialog: ProgressDialog? = null

    override fun activityInjector(): AndroidInjector<Activity> {
        return dispatchingAndroidInjector
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AndroidInjection.inject(this)
        setSupportActionBar(toolbar)
        dialog = ProgressDialog(this)
        initialiseViewModel()
        initialiseView()

    }

    private fun initialiseViewModel() {
        flickerViewModel = ViewModelProviders.of(this@MainActivity, viewModelFactory).get(FlickerViewModel::class.java)
    }

    private fun initialiseView() {
        val flickerAdapter = FlickerAdapter()
        flickerRecyclerView.apply {
            adapter = flickerAdapter
            layoutManager = GridLayoutManager(this@MainActivity, 3)
        }

        dialog?.show()

        flickerViewModel.getFlickerImage()

        flickerViewModel.imageLinksData.observe(this, Observer {
            flickerAdapter.setUrls(it.toMutableList())
            dialog?.dismiss()
        })

    }

    override fun onDestroy() {
        super.onDestroy()
        dialog?.dismiss()
    }

}

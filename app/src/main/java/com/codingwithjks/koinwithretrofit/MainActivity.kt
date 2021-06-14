package com.codingwithjks.koinwithretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.codingwithjks.koinwithretrofit.container.Component
import com.codingwithjks.koinwithretrofit.data.adapter.BusAdapter
import com.codingwithjks.koinwithretrofit.data.util.ApiState
import com.codingwithjks.koinwithretrofit.data.util.Listener
import com.codingwithjks.koinwithretrofit.data.util.showMsg
import com.codingwithjks.koinwithretrofit.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import org.koin.core.component.KoinApiExtension

@KoinApiExtension
class MainActivity : AppCompatActivity(),Listener {
    private lateinit var binding: ActivityMainBinding

    private val component = Component()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerview()
        getBus()
        setData()
        BusAdapter(this)
    }

    private fun setData() {
        binding.apply {
            save.setOnClickListener {
                lifecycleScope.launchWhenStarted {
                    if (!TextUtils.isEmpty(busNo.text.toString()) && !TextUtils.isEmpty(town.text.toString())) {
                        component.mainViewModel.setBus(
                            busNo.text.toString().trim(),
                            town.text.toString()
                        ).catch { e ->
                            Log.d("main", "setData: ${e.message} ")
                        }.collect {
                            Toast.makeText(
                                this@MainActivity,
                                "data added successfully...",
                                Toast.LENGTH_SHORT
                            )
                                .show()
                            getBus()
                        }
                    } else {
                        Toast.makeText(
                            this@MainActivity,
                            "please fill all the fields",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
                }
            }
        }
    }

    private fun initRecyclerview() {
        binding.apply {
            recyclerview.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = component.busAdapter
            }
        }
    }

    private fun getBus() {
        component.mainViewModel.getBus()
        lifecycleScope.launchWhenStarted {
            component.mainViewModel.busApi.collect {
                binding.apply {
                    when (it) {
                        is ApiState.Success -> {
                            recyclerview.isVisible = true
                            progressbar.isVisible = false
                            component.busAdapter.submitList(it.data)
                        }
                        is ApiState.Failure -> {
                            recyclerview.isVisible = false
                            progressbar.isVisible = false
                            Log.d("main", " ${it.msg} ")
                        }
                        is ApiState.Loading -> {
                            recyclerview.isVisible = false
                            progressbar.isVisible = true
                        }
                        is ApiState.Empty -> {

                        }
                    }
                }
            }
        }
    }

    override fun onClickDelete(position: Int, busNo: String) {
        lifecycleScope.launchWhenStarted {
            component.mainViewModel.deleteBus(busNo).catch {e->
                Log.d("main", "${e.message}")
            }.collect {
                showMsg("deleted successfully..")
                getBus()
            }
        }
    }
}
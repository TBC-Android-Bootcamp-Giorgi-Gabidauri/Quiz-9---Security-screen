package com.gabo.quiz9_securityscren

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.gabo.quiz9_securityscren.adapter.DotsAdapter
import com.gabo.quiz9_securityscren.adapter.NumbersAdapter
import com.gabo.quiz9_securityscren.databinding.ActivityMainBinding
import com.gabo.quiz9_securityscren.model.DotModel
import com.gabo.quiz9_securityscren.model.NumberModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var dotsAdapter: DotsAdapter
    private lateinit var numbersAdapter: NumbersAdapter
    private var passcodeList = mutableListOf<Int>()
    private var dotList = mutableListOf(
        DotModel("gray"),
        DotModel("gray"),
        DotModel("gray"),
        DotModel("gray")
    )
    private var numberList = mutableListOf<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupAdapters()
        provideNumberList()
        passCodeToList()
        provideDotsLists(dotList)
    }

    private fun provideDotsLists(list: List<DotModel>) {
        dotsAdapter.submitList(list)
    }

    private fun resetLists() {
        numberList = mutableListOf()
        dotList = mutableListOf(
            DotModel("gray"),
            DotModel("gray"),
            DotModel("gray"),
            DotModel("gray")
        )
    }

    private fun passCodeToList() {
        PASSCODE.forEach {
            passcodeList.add(it.toString().toInt())
        }
    }

    private fun onNumberAdapterClick(model: NumberModel) {
        check(model)
        when {
            numberList.size == 4 -> {
                if (numberList.toString() == passcodeList.toString()) {
                    Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
                    resetLists()
                } else {
                    resetLists()
                }
            }
            (numberList.size < 5) -> {
            }
        }
        provideDotsLists(dotList)
    }

    private fun check(model: NumberModel) {
        when (model.icon) {
            "delete" -> {
                if (numberList.size > 0) {
                    numberList.removeLast()
                    deleteDotColor()
                }
            }
            "touchId" -> {
                Toast.makeText(this, "not implemented yet", Toast.LENGTH_SHORT).show()
            }
            else -> {
                changeDotsColor(model)
            }
        }
    }

    private fun changeDotsColor(model: NumberModel) {
        model.icon.toIntOrNull()?.let {
            numberList.add(it)
            for (i in 0 until numberList.size) {
                dotList[i] = DotModel("Green")
            }
        }
    }

    private fun deleteDotColor() {
        if (numberList.size >= 0) {
            dotList[numberList.size] = DotModel("gray")
            dotsAdapter.submitList(dotList)
            dotsAdapter.notifyDataSetChanged()
        }
    }

    private fun setupAdapters() {
        with(binding) {
            dotsAdapter = DotsAdapter()
            rvDots.adapter = dotsAdapter
            rvDots.layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)

            numbersAdapter = NumbersAdapter { onNumberAdapterClick(it) }
            rvNumbers.adapter = numbersAdapter
            rvNumbers.layoutManager = GridLayoutManager(this@MainActivity, 3)
        }
    }

    private fun provideNumberList() {
        val listOfNumbers = listOf(
            NumberModel("1"), NumberModel("2"), NumberModel("3"),
            NumberModel("4"), NumberModel("5"), NumberModel("6"),
            NumberModel("7"), NumberModel("8"), NumberModel("9"),
            NumberModel("touchId"), NumberModel("0"), NumberModel("delete")
        )
        numbersAdapter.submitList(listOfNumbers)
    }

    companion object {
        const val PASSCODE = "0934"
    }

}
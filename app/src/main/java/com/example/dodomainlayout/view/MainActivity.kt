package com.example.dodomainlayout.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.HorizontalScrollView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.models.SlideModel
import com.example.dodomainlayout.DataBase.dbCategoryManager
import com.example.dodomainlayout.network.model.MyAdapter
import com.example.dodomainlayout.viewModel.MyViewModel
import com.example.dodomainlayout.R
import com.example.dodomainlayout.helper.Utility

class MainActivity : AppCompatActivity() {
    lateinit var btn1: Button
    lateinit var btn2: Button
    lateinit var btn3: Button
    lateinit var btn4: Button

    lateinit var dbManager: dbCategoryManager

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val iSlider = findViewById<ImageSlider>(R.id.image_slider)
        val iList = ArrayList<SlideModel>()
        iList.add(SlideModel(R.drawable.banner))
        iList.add(SlideModel(R.drawable.banner2))
        iList.add(SlideModel(R.drawable.banner3))
        iSlider.setImageList(iList)

        val internetFlag = Utility.isNetworkConnected(this)
        val sharedpref =
            this.applicationContext.getSharedPreferences(
                "user details",
                AppCompatActivity.MODE_PRIVATE
            )
        val DbDataReady: String? = sharedpref?.getString("DbDataReady", null)
        if (DbDataReady == null) {
            if (!internetFlag) {
                Toast.makeText(this, "Для приложения необходим интернет", Toast.LENGTH_LONG).show()
                return
            }
        }

        val adapter = MyAdapter()
        val recyclerView = findViewById<RecyclerView>(R.id.recycle)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        val scrollView = findViewById<HorizontalScrollView>(R.id.hScrollView)

        val viewModel: MyViewModel by viewModels { MyViewModel.Factory }
        if (internetFlag) viewModel.getcategory()
        else viewModel.getcategorybyDb()

        viewModel.categoryArray.observe(this, Observer {
            it.let {
                adapter.refresh(it)
                if (internetFlag && it != null) {
                    dbManager = dbCategoryManager(this)
                    dbManager.OpenDb()
                    dbManager.delete()
                    for (cat in it.categories!!)
                        dbManager.InsertToDb(cat)
                    sharedpref?.edit()?.putString("DbDataReady", "Ready")?.apply()
                }
            }
        })

        btn1 = findViewById<Button>(R.id.btn1)
        btn2 = findViewById<Button>(R.id.btn2)
        btn3 = findViewById<Button>(R.id.btn3)
        btn4 = findViewById<Button>(R.id.btn4)

        adapter.data.observe(this, Observer { value ->
            when (value) {
                3, 2, 1, 0 -> {
                    scrollView.scrollBy(-500, 0)
                    btnActive(btn1)
                }
                4, 5, 6 -> {
                    scrollView.scrollBy(500, 0)
                    btnActive(btn2)
                }

                7, 8, 9 -> {
                    scrollView.scrollBy(500, 0)
                    btnActive(btn3)
                }

                10, 11, 12, 13 -> {
                    scrollView.scrollBy(500, 0)
                    btnActive(btn4)
                }
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        if (dbManager != null) dbManager.CloseDb()
    }

    @SuppressLint("ResourceAsColor")
    fun btnActive(btnActive: Button) {
        val btnArr: Array<Button> = arrayOf(btn1, btn2, btn3, btn4)
        for (btn in btnArr) {
            if (btn == btnActive) {
                btn.setBackgroundColor(Color.parseColor("#33FD3A69"))
                btn.setTextColor(Color.parseColor("#FD3A69"))
            } else {
                btn.setBackgroundColor(Color.parseColor("#2BBEBEBE"))
                btn.setTextColor(Color.parseColor("#C3C4C9"))
            }
        }
    }

}
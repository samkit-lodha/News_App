package com.example.vtr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.vtr.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)



        setUpTabs()
    }

    private fun setUpTabs() {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(TopFragment(),"Top")
        adapter.addFragment(EverythingFragment(),"Everything")
        adapter.addFragment(BusinessFragment(),"Business")
        adapter.addFragment(EntertainmentFragment(),"Entertainment")
        adapter.addFragment(SportsFragment(),"Sports")
        adapter.addFragment(CrytoFragment(),"Crypto")
        adapter.addFragment(TechnologyFragment(),"Tech World")



        binding.viewPager.adapter = adapter
        binding.tabLayoutView.setupWithViewPager(binding.viewPager)

    }
}
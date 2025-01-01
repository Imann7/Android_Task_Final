package com.mobileapps.lesson2

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class FragmentActivity : AppCompatActivity() {

    private var isFragmentAVisible = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)

        replaceFragment(FragmentA())

        findViewById<Button>(R.id.toggleButton).setOnClickListener {
            replaceFragment(if (isFragmentAVisible) FragmentB() else FragmentA())
            isFragmentAVisible = !isFragmentAVisible
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }
}

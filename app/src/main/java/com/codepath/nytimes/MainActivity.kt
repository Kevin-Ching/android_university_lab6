package com.codepath.nytimes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.codepath.nytimes.R
import com.codepath.nytimes.ui.books.BestSellerBooksFragment
import com.codepath.nytimes.ui.home.HomeFragment
import com.codepath.nytimes.ui.search.ArticleResultFragment
import com.codepath.nytimes.ui.settings.SettingsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

private const val HOME_TAG = "home"
private const val BOOKS_TAG = "books"
private const val ARTICLES_TAG = "articles"
private const val SETTINGS_TAG = "settings"

class MainActivity : AppCompatActivity() {

    // Defining fragments
    private val homeFragment = HomeFragment()
    private val articleResultFragment = ArticleResultFragment()
    private val bestSellerBooksFragment = BestSellerBooksFragment()
    private val settingsFragment = SettingsFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentManager: FragmentManager = supportFragmentManager
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)

        // handle navigation selection
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            lateinit var fragment: Fragment
            lateinit var string: String
            when (item.itemId) {
                R.id.nav_home -> {
                    fragment = homeFragment
                    string = HOME_TAG
                }
                R.id.nav_search -> {
                    fragment = articleResultFragment
                    string = ARTICLES_TAG
                }
                R.id.nav_books -> {
                    fragment = bestSellerBooksFragment
                    string = BOOKS_TAG
                }
                R.id.nav_settings -> {
                    fragment = settingsFragment
                    string = SETTINGS_TAG
                }
            }
            fragmentManager.beginTransaction().replace(R.id.placeholder, fragment, string).commit()
            true
        }

        // Set default selection
        bottomNavigationView.selectedItemId = R.id.nav_home
    }
}
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

    private val SELECTED_ITEM_ID_KEY = "selectedItemIdKey"
    // Defining fragments
    private var homeFragment = HomeFragment()
    private var articleResultFragment = ArticleResultFragment()
    private var bestSellerBooksFragment = BestSellerBooksFragment()
    private var settingsFragment = SettingsFragment()

    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentManager: FragmentManager = supportFragmentManager
        bottomNavigationView = findViewById(R.id.bottom_navigation)

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

        // Set default selection, which may not be the default if we are rotating
        if (savedInstanceState != null) {
            val selectedBottomId = savedInstanceState.getInt(SELECTED_ITEM_ID_KEY)
            when (selectedBottomId) {
                R.id.nav_home -> {
                    homeFragment = supportFragmentManager.findFragmentByTag(HOME_TAG) as HomeFragment
                }
                R.id.nav_search -> {
                    articleResultFragment = supportFragmentManager.findFragmentByTag(ARTICLES_TAG) as ArticleResultFragment
                }
                R.id.nav_books -> {
                    bestSellerBooksFragment = supportFragmentManager.findFragmentByTag(BOOKS_TAG) as BestSellerBooksFragment
                }
                R.id.nav_settings -> {
                    settingsFragment = supportFragmentManager.findFragmentByTag(SETTINGS_TAG) as SettingsFragment
                }
            }
            bottomNavigationView.selectedItemId = selectedBottomId
        } else {
            bottomNavigationView.selectedItemId = R.id.nav_home
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(SELECTED_ITEM_ID_KEY, bottomNavigationView?.selectedItemId)
        super.onSaveInstanceState(outState)
    }
}
package com.codepath.nytimes.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.codepath.nytimes.R


class HomeFragment : Fragment() {
    // The onCreateView method is called when Fragment should create its View object hierarchy,
    // either dynamically or via XML layout inflation.
    override fun onCreateView(inflater: LayoutInflater, parent: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Defines the xml file for the fragment
        val view = inflater.inflate(R.layout.fragment_home, parent, false)
        activity?.title = getString(R.string.action_bar_home)
        return view
    }

//    // This event is triggered soon after onCreateView().
//    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
//    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
//        // Setup any handles to view objects here
//        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);
//    }
}
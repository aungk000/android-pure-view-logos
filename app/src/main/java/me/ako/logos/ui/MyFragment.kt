package me.ako.logos.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle

open class MyFragment : Fragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addMenuProvider()
    }

    private fun addMenuProvider() {
        val menuHost = requireActivity() as MenuHost
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                onCreateMyMenu(menu, menuInflater)

            }
            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return onMyMenuItemSelected(menuItem)
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    open fun onCreateMyMenu(menu: Menu, menuInflater: MenuInflater) {}
    open fun onMyMenuItemSelected(menuItem: MenuItem): Boolean { return false }
}
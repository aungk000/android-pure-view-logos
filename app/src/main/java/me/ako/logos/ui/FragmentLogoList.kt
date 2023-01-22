package me.ako.logos.ui

import android.content.res.Configuration
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import me.ako.logos.R
import me.ako.logos.databinding.FragmentLogoListBinding
import me.ako.logos.model.AppViewModel

class FragmentLogoList : MyFragment() {
    private val viewModel: AppViewModel by activityViewModels {
        AppViewModel.Factory()
    }
    private var _binding: FragmentLogoListBinding? = null
    private val binding get() = _binding!!
    private var isNightMode = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLogoListBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkNightMode()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onBind()
    }

    override fun onCreateMyMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu_logo_list, menu)
        setMenuIcon(menu.findItem(R.id.menu_day_night))
    }

    override fun onMyMenuItemSelected(menuItem: MenuItem): Boolean {
        return when(menuItem.itemId) {
            R.id.menu_day_night -> {
                setMenuIcon(menuItem)
                isNightMode = !isNightMode
                changeNightMode(isNightMode)
                true
            }
            else -> super.onMyMenuItemSelected(menuItem)
        }
    }

    private fun onBind() {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        binding.recyclerView.adapter = LogoAdapter {
            val action = FragmentLogoListDirections.actionFragmentLogoListToFragmentLogoDetail(
                it.name,
                it.tag
            )
            findNavController().navigate(action)
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                RecyclerView.VERTICAL
            )
        )
    }

    private fun setMenuIcon(menuItem: MenuItem?) {
        if (menuItem == null) {
            return
        }

        menuItem.icon = if (isNightMode) {
            ContextCompat.getDrawable(requireContext(), R.drawable.ic_day)
        } else {
            ContextCompat.getDrawable(requireContext(), R.drawable.ic_night)
        }
    }

    private fun changeNightMode(enable: Boolean) {
        if (enable) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    private fun checkNightMode() {
        when (resources.configuration.uiMode.and(Configuration.UI_MODE_NIGHT_MASK)) {
            Configuration.UI_MODE_NIGHT_UNDEFINED -> {
            }
            Configuration.UI_MODE_NIGHT_NO -> {
                isNightMode = false
            }
            Configuration.UI_MODE_NIGHT_YES -> {
                isNightMode = true
            }
        }
    }
}
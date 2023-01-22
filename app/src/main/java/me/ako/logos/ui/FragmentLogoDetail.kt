package me.ako.logos.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import me.ako.logos.databinding.FragmentLogoDetailBinding
import me.ako.logos.model.TAG
import me.ako.logos.view.MicrosoftLogo
import me.ako.logos.view.NetflixLogo
import me.ako.logos.view.YoutubeLogo

class FragmentLogoDetail : Fragment() {
    private val navArgs: FragmentLogoDetailArgs by navArgs()
    private var _binding: FragmentLogoDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLogoDetailBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        when (navArgs.tag) {
            TAG.NETFLIX -> {
                setLogoView(
                    NetflixLogo(requireContext(), null),
                    200,
                    300,
                    Padding(20)
                )
            }
            TAG.MICROSOFT -> {
                setLogoView(MicrosoftLogo(requireContext(), null), 300, 300)
            }
            TAG.YOUTUBE -> {
                val youtube = YoutubeLogo(requireContext(), null)
                youtube.setCornerRadius(30)
                setLogoView(youtube, 300, 200)
            }
        }
    }

    data class Padding(
        var size: Int
    )

    private fun setLogoView(
        view: View,
        width: Int,
        height: Int,
        padding: Padding? = Padding(0)
    ) {
        val layout = binding.constraintLayout
        val set = ConstraintSet()
        view.id = View.generateViewId()
        padding?.let {
            view.setPadding(it.size, it.size, it.size, it.size)
        }
        layout.addView(view, LayoutParams(width, height))
        set.clone(layout)
        set.connect(view.id, ConstraintSet.TOP, layout.id, ConstraintSet.TOP)
        set.connect(view.id, ConstraintSet.START, layout.id, ConstraintSet.START)
        set.connect(view.id, ConstraintSet.END, layout.id, ConstraintSet.END)
        set.connect(view.id, ConstraintSet.BOTTOM, layout.id, ConstraintSet.BOTTOM)
        set.applyTo(layout)
    }
}
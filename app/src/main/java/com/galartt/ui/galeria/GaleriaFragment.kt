package com.galartt.ui.galeria

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.galartt.databinding.FragmentGaleriaBinding
import com.galartt.viewmodel.GaleriaViewModel

class GaleriaFragment : Fragment() {

    private var _binding: FragmentGaleriaBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val galeriaViewModel =
            ViewModelProvider(this).get(GaleriaViewModel::class.java)

        _binding = FragmentGaleriaBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        galeriaViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
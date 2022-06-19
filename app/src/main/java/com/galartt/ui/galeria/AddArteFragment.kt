package com.galartt.ui.galeria

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.galartt.R
import com.galartt.databinding.FragmentAddArteBinding
import com.galartt.viewmodel.GaleriaViewModel

// FragmentAddArteBinding = FragmentAddGaleriaBinding

class AddArteFragment : Fragment() {

    private lateinit var galeriaViewModel: GaleriaViewModel

    private var _binding: FragmentAddArteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        galeriaViewModel = ViewModelProvider(this)[GaleriaViewModel::class.java]
        _binding = FragmentAddArteBinding.inflate(inflater, container,false)

        // agregar un lugar
        binding.btAdd.setOnClickListener { addArte() }

        return binding.root
    }
    // Galeria
    private fun addArte() {
        val autor = binding.etAutor.text.toString()
        val correo = binding.etCorreo.text.toString()
        val telefono = binding.etTelefono.text.toString()
        val web = binding.etWeb.text.toString()
        if (autor.isNotEmpty()) {
            val arte = Galeria(0, autor, correo, telefono, web, 0.0, 0.0, 0.0, "", "")
            galeriaViewModel.addArte(arte)
            Toast.makeText(requireContext(), getString(R.string.arteAdded), Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addArteFragment_to_nav_galeria)
        } else {
            Toast.makeText(requireContext(), getString(R.string.notAdded), Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package com.galartt.ui.galeria

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.galartt.R
import com.galartt.databinding.FragmentUpdateArteBinding
import com.galartt.model.Galeria
import com.galartt.viewmodel.GaleriaViewModel

// FragmentUpdateArteBinding = FragmentUpdateGaleriaBinding

class UpdateArteFragment : Fragment() {

    private val args by navArgs<UpdateArteFragmentArgs>()

    private lateinit var galeriaViewModel: GaleriaViewModel

    private var _binding: FragmentUpdateArteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        galeriaViewModel = ViewModelProvider(this)[GaleriaViewModel::class.java]
        _binding = FragmentUpdateArteBinding.inflate(inflater, container,false)

        // se coloca la info de objeto arte que se pasa
        binding.etAutor.setText(args.arte.autor)
        binding.etCorreo.setText(args.arte.telefono)
        binding.etCorreo.setText(args.arte.correo)
        binding.etWeb.setText(args.arte.web)

        // actualizar un arte
        binding.btUpdate.setOnClickListener { updateArte() }

        return binding.root
    }

    private fun updateArte() {
        val autor = binding.etAutor.text.toString()
        val correo = binding.etCorreo.text.toString()
        val telefono = binding.etTelefono.text.toString()
        val web = binding.etWeb.text.toString()
        if (autor.isNotEmpty()) {
            val arte = Galeria(args.arte.id, autor, correo, telefono, web, 0.0, 0.0, 0.0, "", "")
            galeriaViewModel.updateArte(arte)
            Toast.makeText(requireContext(), getString(R.string.arteAdded), Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateArteFragment_to_nav_galeria)
        } else {
            Toast.makeText(requireContext(), getString(R.string.notAdded), Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
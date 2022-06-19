package com.galartt.ui.galeria

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.galartt.R
import com.galartt.adapter.GaleriaAdapter
import com.galartt.databinding.FragmentGaleriaBinding
import com.galartt.viewmodel.GaleriaViewModel

// FragmentGaleriaBinding = FragmentArteBinding

class GaleriaFragment : Fragment() {

    private lateinit var galeriaViewModel: GaleriaViewModel

    private var _binding: FragmentGaleriaBinding? = null
    private val binding get() = _binding!!

/* 1:17:00  https://ufidelitas.sharepoint.com/sites/ProgramacinparaDispositivosMvilesCampusVirtual2022
Segundo712/Documentos%20compartidos/Forms/AllItems.aspx?id=%2Fsites%2FProgramacinparaDispositivosMviles
CampusVirtual2022Segundo712%2FDocumentos%20compartidos%2FGeneral%2FRecordings%2FClase%20Sincr%C3%B3nica%20
M%C3%B3vil%20Jueves%20Noche%2D20220602%5F181058%2DMeeting%20Recording%2Emp4&parent=%2Fsites%2FProgramacinpara
DispositivosMvilesCampusVirtual2022Segundo712%2FDocumentos%20compartidos%2FGeneral%2FRecordings*/

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        galeriaViewModel = ViewModelProvider(this)[GaleriaViewModel::class.java]
        _binding = FragmentGaleriaBinding.inflate(inflater, container,false)

        // accion para pasar a addObra
        binding.addArteButton.setOnClickListener{
            findNavController().navigate(R.id.action_nav_galeria_to_addArteFragment)
        }

        // activar el reciclador
        val galeriaAdapter = GaleriaAdapter()
        val reciclador = binding.reciclador

        reciclador.adapter = galeriaAdapter
        reciclador.layoutManager = LinearLayoutManager(requireContext())

        galeriaViewModel = ViewModelProvider(this)[GaleriaViewModel::class.java]

        galeriaViewModel.getAllData.observe(viewLifecycleOwner) {
            artes -> galeriaAdapter.setData(artes)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package com.galartt.ui.galeria

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
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
    ): View {
        galeriaViewModel = ViewModelProvider(this)[GaleriaViewModel::class.java]
        _binding = FragmentUpdateArteBinding.inflate(inflater, container,false)

        // se coloca la info de objeto arte que se pasa
        binding.etAutor.setText(args.arte.autor)
        binding.etTelefono.setText(args.arte.telefono)
        binding.etCorreo.setText(args.arte.correo)
        binding.etWeb.setText(args.arte.web)

        // actualizar un arte
        binding.btUpdate.setOnClickListener { updateArte() }

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //Pregunto si se dió click en el icono de borrado
        if(item.itemId==R.id.menu_delete){
            //Hace algo si se dio click
            //Llamamos la función...
            deleteLugar()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteLugar() {
        val consulta= AlertDialog.Builder(requireContext())
        //Consultamos si quiere eliminar el lugar
        consulta.setTitle(R.string.delete)
        //Ponemos un mensaje a la ventana
        consulta.setMessage(getString(R.string.seguroBorrar)+" ${args.arte.autor}?")

        //Acciones a ejecutar si respondo YES
        consulta.setPositiveButton(getString(R.string.si)){_,_ ->
            //Borramos el lugar... sin consultar...
            galeriaViewModel.deleteArte(args.arte)
            findNavController().navigate(R.id.action_updateArteFragment_to_nav_galeria)
        }

        //Si la respuesta es no
        consulta.setNegativeButton(getString(R.string.no)){_,_ -> }

        //Crear la alerta y mostrarla
        consulta.create().show()
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
package com.example.exercise

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.EditText
import androidx.navigation.fragment.findNavController
import com.example.exercise.databinding.FragmentHomeBinding
import androidx.core.content.edit
import androidx.core.widget.addTextChangedListener
import com.example.exercise.R


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
        */
        class HomeFragment : Fragment(R.layout.fragment_home) {
            // TODO: Rename and change types of parameters
            private var param1: String? = null
            private var param2: String? = null

            private var _binding: FragmentHomeBinding? = null
            private val binding get() = _binding!!

            override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                arguments?.let {
                    param1 = it.getString(ARG_PARAM1)
                    param2 = it.getString(ARG_PARAM2)
                }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
       // val view =  inflater.inflate(R.layout.fragment_home, container, false)

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonCalendar.setOnClickListener {
            activity?.let {
                val intent = Intent(context, CalendarActivity::class.java)
                startActivity(intent)
            }
        }
        binding.buttonExercise.setOnClickListener {
            activity?.let {
                val intent = Intent(context, ExerciseActivity::class.java)
                startActivity(intent)
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
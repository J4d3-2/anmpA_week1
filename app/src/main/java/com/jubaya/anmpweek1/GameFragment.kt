package com.jubaya.anmpweek1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.jubaya.anmpweek1.databinding.FragmentGameBinding
import android.util.Log
import java.util.Random

class GameFragment : Fragment() {
    private lateinit var binding:FragmentGameBinding
    private var score = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(arguments != null){
            val name = GameFragmentArgs.fromBundle(requireArguments()).playerName
            binding.txtTurn.text = "$name's turn"
        }
        var num1 = (0..100).random()
        var num2 = (0..100).random()

        binding.txtNum1.text = num1.toString()
        binding.txtNum2.text = num2.toString()

        binding.btnSubmit.setOnClickListener {
            var n1 = Integer.parseInt(binding.txtNum1.text.toString())
            var n2 = Integer.parseInt(binding.txtNum2.text.toString())
            var ans = binding.txtAnswer.text.toString()

            var total = n1 + n2

            if (ans != null){
                var answer = Integer.parseInt(ans)
                if(total == answer){
                    score += 1

                    num1 = (0..100).random()
                    num2 = (0..100).random()

                    binding.txtNum1.text = num1.toString()
                    binding.txtNum2.text = num2.toString()
                }
                else if (total != answer){
                    val action = GameFragmentDirections.actionResultFragment(score)
                    Navigation.findNavController(it).navigate(action)
                }
            }
            else{
                val action = GameFragmentDirections.actionResultFragment(score)
                Navigation.findNavController(it).navigate(action)
            }
        }
    }

}
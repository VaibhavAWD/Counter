package app.vaibhavawd.counter

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import app.vaibhavawd.counter.databinding.FragmentFrequencyBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.fragment_frequency.*

class FrequencyFragment : DialogFragment() {

    private lateinit var viewModel: CounterViewModel
    private lateinit var binding: FragmentFrequencyBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = MaterialAlertDialogBuilder(context)
        dialog.setTitle(getString(R.string.frequency_dialog_title))

        activity?.let {
            viewModel = ViewModelProvider(it)[CounterViewModel::class.java]
        }

        binding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.fragment_frequency,
            null,
            false
        )
        binding.viewmodel = viewModel

        dialog.setView(binding.root)
        dialog.setNeutralButton(getString(R.string.btn_set)) { _, _ ->
            setFrequency()
        }
        return dialog.create()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    private fun setFrequency() {
        val value = input_frequency.text.toString()
        binding.viewmodel?.setFrequency(value)
    }

}
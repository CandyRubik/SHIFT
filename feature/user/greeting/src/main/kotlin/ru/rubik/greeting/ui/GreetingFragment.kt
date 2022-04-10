package ru.rubik.greeting.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.rubik.greeting.databinding.FragmentGreetingBinding
import ru.rubik.greeting.di.GreetingComponentProvider
import ru.rubik.greeting.presentation.GreetingState
import ru.rubik.greeting.presentation.GreetingViewModel
import ru.rubik.greeting.presentation.GreetingViewModelFactory
import javax.inject.Inject

class GreetingFragment : Fragment() {

	private var _binding: FragmentGreetingBinding? = null
	private val binding
		get() = _binding!!

	companion object {

		private const val DIALOG_TAG = "dialog"
	}

	@Inject
	lateinit var viewModelFactory: GreetingViewModelFactory
	private lateinit var viewModel: GreetingViewModel

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
		_binding = FragmentGreetingBinding.inflate(inflater)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		(activity?.applicationContext as GreetingComponentProvider).provideGreetingComponent().inject(this)
		viewModel = ViewModelProvider(this, viewModelFactory)[GreetingViewModel::class.java]

		bindUi()
	}

	private fun bindUi() {
		binding.button.setOnClickListener {
			viewModel.getUserInfo()
		}

		viewModel.uiState
			.onEach {
				when (it) {
					is GreetingState.Dialog -> {
						openDialog(it.firstName)
					}

					GreetingState.Button    -> {}
				}
			}.launchIn(lifecycleScope)
	}

	private fun openDialog(firstName: String) {
		val greetingDialog = GreetingDialog(firstName)
		greetingDialog.show(parentFragmentManager, DIALOG_TAG)
	}
}
package ru.rubik.registration.ui

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.rubik.navigation.NavCommand
import ru.rubik.navigation.NavCommands
import ru.rubik.navigation.navigate
import ru.rubik.registration.R
import ru.rubik.registration.databinding.FragmentRegistrationBinding
import ru.rubik.registration.di.RegistrationComponentProvider
import ru.rubik.registration.presentation.RegistrationViewModel
import ru.rubik.registration.presentation.RegistrationViewModelFactory
import javax.inject.Inject

class RegistrationFragment : Fragment() {

	private var _binding: FragmentRegistrationBinding? = null
	private val binding
		get() = _binding!!

	@Inject
	lateinit var viewModelFactory: RegistrationViewModelFactory
	private lateinit var viewModel: RegistrationViewModel

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
		_binding = FragmentRegistrationBinding.inflate(inflater)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		(activity?.applicationContext as RegistrationComponentProvider).provideRegistrationComponent().inject(this)
		viewModel = ViewModelProvider(this, viewModelFactory)[RegistrationViewModel::class.java]

		bindStates()
		bindChangeListeners()

		binding.signUpButton.setOnClickListener {
			viewModel.saveUser(
				binding.firstNameTextInputEditText.text.toString(),
				binding.lastNameTextInputEditText.text.toString(),
				binding.datePickerInputEditText.text.toString(),
				binding.passwordInputEditText.text.toString(),
			)
			navigate(NavCommand(NavCommands.DeepLink(Uri.parse("app://greeting"), true)))
		}
	}

	private fun bindStates() {

		binding.signUpButton.isEnabled = false

		viewModel.isNameCorrect
			.onEach {
				if (it.not()) {
					binding.firstNameTextInputLayout.error = getString(R.string.first_name_error)
				} else {
					binding.firstNameTextInputLayout.error = null
				}
			}.launchIn(lifecycleScope)

		viewModel.isLastNameCorrect
			.onEach {
				if (it.not()) {
					binding.lastNameTextInputLayout.error = getString(R.string.last_name_error)
				} else {
					binding.lastNameTextInputLayout.error = null
				}
			}.launchIn(lifecycleScope)

		viewModel.isDateCorrect
			.onEach {
				if (it.not()) {
					binding.datePickerInputLayout.error = getString(R.string.date_error)
				} else {
					binding.datePickerInputLayout.error = null
				}
			}.launchIn(lifecycleScope)

		viewModel.isPasswordsEquals
			.onEach {
				if (it.not()) {
					binding.rePasswordTextInputLayout.error = getString(R.string.passwords_error)
				} else {
					binding.rePasswordTextInputLayout.error = null
				}
			}.launchIn(lifecycleScope)
	}

	private fun bindChangeListeners() {
		binding.firstNameTextInputEditText.addTextChangedListener(
			onTextChanged = { text, _, _, _ ->
				viewModel.validateFirstName(text.toString())
			},
			afterTextChanged = {
				isButtonAvailable()
			}
		)

		binding.lastNameTextInputEditText.addTextChangedListener(
			onTextChanged = { text, _, _, _ ->
				viewModel.validateLastName(text.toString())
			},
			afterTextChanged = {
				isButtonAvailable()
			}
		)

		binding.datePickerInputEditText.addTextChangedListener(
			onTextChanged = { text, _, _, _ ->
				viewModel.validateDate(text.toString())
			},
			afterTextChanged = {
				isButtonAvailable()
			}
		)

		binding.rePasswordInputEditText.addTextChangedListener(
			onTextChanged = { text, _, _, _ ->
				viewModel.validateEqualsPasswords(text.toString(), binding.passwordInputEditText.text.toString())
			},
			afterTextChanged = {
				isButtonAvailable()
			}
		)
	}

	private fun isButtonAvailable() {
		binding.signUpButton.isEnabled = viewModel.run {
			return@run isNameCorrect.value
				&& isLastNameCorrect.value
				&& isDateCorrect.value
				&& isPasswordsEquals.value
		} && binding.firstNameTextInputEditText.text.toString().isNotBlank()
			&& binding.lastNameTextInputEditText.text.toString().isNotBlank()
			&& binding.datePickerInputEditText.text.toString().isNotBlank()
			&& binding.passwordInputEditText.text.toString().isNotBlank()
			&& binding.rePasswordInputEditText.text.toString().isNotBlank()

	}


}
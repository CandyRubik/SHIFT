package rubik.shifttest.domain.usecases;

import android.widget.EditText;

import com.google.android.material.datepicker.MaterialDatePicker;

public class SaveDateUseCase {
    private final MaterialDatePicker mDatePicker;
    private final EditText mDatePickerEditText;

    public SaveDateUseCase(MaterialDatePicker datePicker, EditText datePickerEditText) {
        mDatePicker = datePicker;
        mDatePickerEditText = datePickerEditText;
    }

    public void execute() {
        mDatePicker.addOnPositiveButtonClickListener(selection -> mDatePickerEditText.setText(mDatePicker.getHeaderText()));
    }
}

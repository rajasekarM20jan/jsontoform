package model;

import android.content.Context;
import android.text.InputType;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.jsontoform.R;

public class DynamicForm {
    EditText editText,editTextNumber,editTextArea;
    CheckBox checkBox;
    RadioGroup radioGroup;
    RadioButton radioButton;
    Context context;

    public EditText getEditTextNumber() {
        editTextNumber=new EditText(context);
        editTextNumber.setInputType(InputType.TYPE_CLASS_NUMBER);
        editTextNumber.setBackground(context.getResources().getDrawable(R.drawable.edit_text_bg));
        return editTextNumber;
    }

    public EditText getEditTextArea() {
        editTextArea=new EditText(context);
        editTextArea.setHeight(350);
        editTextArea.setBackground(context.getResources().getDrawable(R.drawable.edit_text_bg));
        return editTextArea;
    }

    public DynamicForm(Context context) {
        this.context=context;
    }

    public EditText getEditText() {
        editText=new EditText(context);
        editText.setBackground(context.getResources().getDrawable(R.drawable.edit_text_bg));
        return editText;
    }

    public CheckBox getCheckBox() {
        checkBox=new CheckBox(context);
        return checkBox;
    }

    public RadioGroup getRadioGroup() {
        radioGroup=new RadioGroup(context);
        return radioGroup;
    }

    public RadioButton getRadioButton() {
        radioButton=new RadioButton(context);
        return radioButton;
    }
}

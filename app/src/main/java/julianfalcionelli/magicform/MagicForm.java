package julianfalcionelli.magicform;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import julianfalcionelli.magicform.base.FormError;
import julianfalcionelli.magicform.base.FormField;
import julianfalcionelli.magicform.base.FormattedFormField;
import julianfalcionelli.magicform.base.ValidationMode;
import julianfalcionelli.magicform.base.ValidatorCallbacks;
import julianfalcionelli.magicform.helper.ValidationHelper;
import julianfalcionelli.magicform.validation.Validation;

public class MagicForm
{
	private ValidationMode mMode = ValidationMode.ON_VALIDATE;

	private List<FormField> mFields = new ArrayList<>();
	private List<FormField> mValidFields = new ArrayList<>();

	private ValidatorCallbacks mListener;

	/*
	 Esta variable indica si se debe continuar con todos las validaciones de un campo, aun cuando ya
	 se haya comprobado que ya tiene un error.
	 */
	private boolean mDeepValidation = false;

	public MagicForm()
	{

	}

	public MagicForm(ValidationMode mode)
	{
		mMode = mode;
	}

	public void validate()
	{
		List<FormError> errors = new ArrayList<>();

		for (FormField formField : mFields)
		{
			errors.addAll(checkField(formField));
		}

		if (errors.isEmpty())
		{
			onSuccess();
		} else
		{
			onFailed(errors);
		}
	}

	private void onSuccess()
	{
		if (mListener != null)
		{
			mListener.onSuccess();
		}
	}

	private void onFailed(List<FormError> errors)
	{
		if (mListener != null)
		{
			mListener.onFailed(errors);
		}
	}


	List<FormError> mErrors = new ArrayList<>();

	private void validateField(FormField formField)
	{
		List<FormError> errors = checkField(formField);

		if (errors.isEmpty())
		{
			addValidField(formField);
		} else
		{
			mErrors.addAll(errors);

			removeValidField(formField);
		}
	}

	private void addValidField(FormField formField)
	{
		if (!mValidFields.contains(formField))
		{
			mValidFields.add(formField);
		}

		checkFormStatus();
	}

	private void removeValidField(FormField formField)
	{
		if (mValidFields.contains(formField))
		{
			mValidFields.remove(formField);
		}

		checkFormStatus();
	}

	private void checkFormStatus()
	{
		if (mFields.size() == mValidFields.size())
		{
			onSuccess();
		} else
		{
			onFailed(mErrors);
		}
	}

	private boolean isFieldValid(FormField formField)
    {
        return checkField(formField, false).isEmpty();
    }

    private List<FormError> checkField(FormField formField)
    {
        return checkField(formField, true);
    }

	private List<FormError> checkField(FormField formField, boolean setErrorInView)
	{
		List<FormError> errors = new ArrayList<>();

		View view = formField.getView();

		FormError firstError = null;

		for (Validation validation : formField.getValidations())
		{
			if (!validation.isValid(view))
			{
				FormError formError = new FormError(view, validation.getMessage());

				errors.add(formError);

				if (firstError == null)
				{
					firstError = formError;
				}

				if (!mDeepValidation)
				{
					break;
				}
			}
		}

		if (firstError != null && setErrorInView)
		{
			ValidationHelper.setError(view, firstError.getMessage());
		} else
		{
			cleanFieldError(formField);
		}

		return errors;
	}

	private void cleanFieldError(FormField formField)
	{
		View view = formField.getView();

		ValidationHelper.cleanError(view);

		List<FormError> newErrors = new ArrayList<>();

		for (FormError formError : mErrors)
		{
			if (formError.getView().getId() != view.getId())
			{
				newErrors.add(formError);
			}
		}

		mErrors.clear();
		mErrors.addAll(newErrors);
	}

	public void setDeepValidation(boolean deepValidation)
	{
		mDeepValidation = deepValidation;
	}

	public MagicForm setMode(ValidationMode mode)
	{
		mMode = mode;

        return this;
	}

	public MagicForm addField(FormField formField)
	{
		if (!mFields.contains(formField))
		{
			mFields.add(formField);

			initializeFormField(formField, formField.getMode() != null ? formField.getMode() : mMode);
		}

		return this;
	}

	public void removeField(FormField formField)
	{
		if (mFields.contains(formField))
		{
			mFields.remove(formField);
			mValidFields.remove(formField);
		}
	}

	private void initializeFormField(FormField formField, ValidationMode mode)
	{
		switch (mode)
		{
			case ON_VALIDATE:
			{
				setupFormFieldOnValidateMode(formField);
				break;
			}
			case ON_FOCUS_CHANGE:
			{
				setupFormFieldOnFocusChangeMode(formField);
				break;
			}
			case ON_CONTENT_CHANGE:
			{
				setupFormFieldOnContentChangeMode(formField);
				break;
			}
		}
	}

	private void setupFormFieldOnValidateMode(final FormField formField)
	{
		setupTextChangeListener(formField, false);
	}

	private void setupFormFieldOnFocusChangeMode(final FormField formField)
	{
		final View view = formField.getView();

        if (isFieldValid(formField))
        {
            addValidField(formField);
        }

		view.setOnFocusChangeListener(new View.OnFocusChangeListener()
		{
			@Override
			public void onFocusChange(View v, boolean hasFocus)
			{
				if (!hasFocus)
				{
					validateField(formField);
				}
			}
		});

		setupTextChangeListener(formField, false);
	}

	private void setupFormFieldOnContentChangeMode(final FormField formField)
	{
		setupTextChangeListener(formField, true);
	}

	private void setupTextChangeListener(final FormField formField, final boolean validateOnChange)
	{
		final boolean clearErrorsOnChange = formField.isClearErrorsOnChange();

		final boolean hasFormat = formField instanceof FormattedFormField;

		if ((clearErrorsOnChange || hasFormat || validateOnChange) && formField.getView() instanceof TextView)
		{
			final TextView view = (TextView) formField.getView();

			view.addTextChangedListener(new TextWatcher()
			{
				@Override
				public void beforeTextChanged(CharSequence s, int start, int count, int after)
				{

				}

				@Override
				public void onTextChanged(CharSequence s, int start, int before, int count)
				{
					if (clearErrorsOnChange)
					{
						cleanFieldError(formField);
					}

					if (hasFormat)
					{
						checkFormat((FormattedFormField) formField);
					}

					if (validateOnChange && view.hasFocus())
					{
						validateField(formField);
					}
				}

				@Override
				public void afterTextChanged(Editable s)
				{

				}
			});
		}
	}

	private void checkFormat(FormattedFormField formField)
	{
		View view = formField.getView();

		String currentViewText = ((TextView) view).getText().toString();

		String currentViewRawText = formField.getRawValue(currentViewText);

		if (currentViewRawText.length() != formField.getRawValue().length())
		{
			String formattedValue = formField.getFormattedValue(currentViewRawText);

			formField.setRawValue(currentViewRawText);

			((TextView) view).setText(formattedValue);

			if (view instanceof EditText)
			{
				((EditText) view).setSelection(((TextView) view).length());
			}
		}

	}

	public MagicForm setListener(ValidatorCallbacks listener)
	{
		mListener = listener;
		return this;
	}

}

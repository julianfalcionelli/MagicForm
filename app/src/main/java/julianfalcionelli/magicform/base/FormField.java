package julianfalcionelli.magicform.base;

import android.app.Activity;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import julianfalcionelli.magicform.validation.Validation;

public class FormField
{
	private View mView;
	private List<Validation> mValidations = new ArrayList<>();

	private ValidationMode mMode;

	private boolean mClearErrorsOnChange = true;

	public FormField(View view)
	{
		mView = view;
	}

	public FormField(Activity activity, int viewId)
	{
		this(activity.findViewById(viewId));
	}

	public FormField addValidation(Validation validation)
	{
		if (!mValidations.contains(validation))
		{
			mValidations.add(validation);
		}

		return this;
	}

	public FormField removeValidation(Validation validation)
	{
		if (mValidations.contains(validation))
		{
			mValidations.remove(validation);
		}

		return this;
	}

	public List<Validation> getValidations()
	{
		return mValidations;
	}

	public View getView()
	{
		return mView;
	}

	public void setMode(ValidationMode mode)
	{
		mMode = mode;
	}

	public ValidationMode getMode()
	{
		return mMode;
	}

	public boolean isClearErrorsOnChange()
	{
		return mClearErrorsOnChange;
	}

	public void setClearErrorsOnChange(boolean clearErrorsOnChange)
	{
		mClearErrorsOnChange = clearErrorsOnChange;
	}

	public void addFormatter(Formatter formatter)
	{
		addFormatter(formatter.getRegularExp(), formatter.getReplacement());
	}

	public void addFormatter(String regularExp, String replacement)
	{
		StringBuilder formattedSecurityNumber = new StringBuilder();
	/*	formattedSecurityNumber.append(String.replaceFirst("(\\d{0,3})(\\d{0,2})(\\d{0,4})", "$1-$2-$3"));

		while (formattedSecurityNumber.length() > 0 && !Character.isDigit(formattedSecurityNumber.charAt(formattedSecurityNumber.length() - 1)))
		{
			formattedSecurityNumber.deleteCharAt(formattedSecurityNumber.length() - 1);
		}
		return formattedSecurityNumber.toString();*/
	}
}
package julianfalcionelli.magicform;

import android.app.Activity;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

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
}
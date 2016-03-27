package julianfalcionelli.magicform;

import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ValidationHelper
{
	public static final String TAG = ValidationHelper.class.getSimpleName();

	public static void cleanError(View view)
	{
		if (view instanceof EditText)
		{
			cleanError((EditText) view);
			return;
		}

		if (view instanceof TextView)
		{
			((TextView) view).setError(null);
			return;
		}

	}

	private static void cleanError(EditText editText)
	{
		try
		{
			((TextInputLayout) editText.getParent()).setErrorEnabled(false);
		} catch (ClassCastException e)
		{
			editText.setError(null);
		}
	}

	public static void setError(View view, String error)
	{
		if (view instanceof EditText)
		{
			setError((EditText) view, error);
			return;
		}

		if (view instanceof TextView)
		{
			((TextView) view).setError(error);
			return;
		}
	}

	private static void setError(EditText editText, String error)
	{
		try
		{
			((TextInputLayout) editText.getParent()).setError(error);
		} catch (ClassCastException e)
		{
			editText.setError(error);
		}
	}
}

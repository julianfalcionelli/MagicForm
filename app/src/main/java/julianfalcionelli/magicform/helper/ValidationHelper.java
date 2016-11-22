package julianfalcionelli.magicform.helper;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.view.ViewParent;
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
		TextInputLayout textInputLayout = getTextInputLayout(editText);

		if (textInputLayout != null)
		{
			textInputLayout.setError(null);
			textInputLayout.setErrorEnabled(false);
		}
		else
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
		TextInputLayout textInputLayout = getTextInputLayout(editText);

		if (textInputLayout != null)
		{
			textInputLayout.setErrorEnabled(true);
			textInputLayout.setError(error);
		}
		else
		{
			editText.setError(error);
		}
	}

	@Nullable
	private static TextInputLayout getTextInputLayout(@NonNull EditText editText)
	{
		View currentView = editText;

		for (int i = 0; i < 2; i++)
		{
			ViewParent parent = currentView.getParent();

			if (parent instanceof TextInputLayout)
			{
				return (TextInputLayout) parent;
			}
			else
			{
				currentView = (View) parent;
			}
		}

		return null;
	}
}

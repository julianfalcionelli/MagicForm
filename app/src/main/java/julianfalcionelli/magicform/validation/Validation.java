package julianfalcionelli.magicform.validation;

import android.view.View;

public abstract class Validation<T extends View>
{
	private String mMessage;

	public abstract boolean isValid(T view);

	public Validation setMessage(String message)
	{
		mMessage = message;

		return this;
	}

	public String getMessage()
	{
		return mMessage;
	}
}

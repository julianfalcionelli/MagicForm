package julianfalcionelli.magicform;

import android.view.View;

public abstract class Validation
{
	private String mMessage;

	public abstract boolean isValid(View view);

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

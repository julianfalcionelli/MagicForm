package julianfalcionelli.magicform.base;

import android.view.View;

public class FormError
{
	private View mView;
	private String mMessage;

	public FormError(View view, String message)
	{
		mView = view;
		mMessage = message;
	}

	public View getView()
	{
		return mView;
	}

	public String getMessage()
	{
		return mMessage;
	}
}

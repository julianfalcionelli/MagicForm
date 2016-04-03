package julianfalcionelli.magicform.validation;

import android.view.View;
import android.widget.CompoundButton;

public class ValidationChecked extends Validation
{
	private boolean mChecked;

	public ValidationChecked(boolean checked)
	{
		mChecked = checked;
	}

	@Override
	public boolean isValid(View view)
	{
		return ((CompoundButton) view).isChecked() == mChecked;
	}
}

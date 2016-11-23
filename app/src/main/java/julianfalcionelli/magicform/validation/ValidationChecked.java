package julianfalcionelli.magicform.validation;

import android.widget.CompoundButton;

public class ValidationChecked extends Validation<CompoundButton>
{
	private boolean mChecked;

	public ValidationChecked(boolean checked)
	{
		mChecked = checked;
	}

	@Override
	public boolean isValid(CompoundButton view)
	{
		return view.isChecked() == mChecked;
	}
}

package julianfalcionelli.magicform.validation;

import android.view.View;
import android.widget.TextView;

public class ValidationLength extends Validation
{
	private Integer mMin, mMax;

	public ValidationLength(Integer min, Integer max)
	{
		mMin = min;
		mMax = max;
	}

	@Override
	public boolean isValid(View view)
	{
		return (mMin == null || mMin <= ((TextView) view).length()) && (mMax == null || mMax >= ((TextView) view).length());
	}
}

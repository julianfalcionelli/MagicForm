package julianfalcionelli.magicform.validation;

import android.widget.TextView;

public class ValidationLength extends Validation<TextView>
{
	private Integer mMin, mMax;

	public ValidationLength(Integer min, Integer max)
	{
		mMin = min;
		mMax = max;
	}

	@Override
	public boolean isValid(TextView view)
	{
		return (mMin == null || mMin <= view.length()) && (mMax == null || mMax >= view.length());
	}
}

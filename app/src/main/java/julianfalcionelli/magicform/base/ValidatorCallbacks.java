package julianfalcionelli.magicform.base;

import java.util.List;

public interface ValidatorCallbacks
{
	void onSuccess();

	void onFailed(List<FormError> errors);
}

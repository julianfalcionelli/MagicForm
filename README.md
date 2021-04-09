MagicForm 
===========
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-MagicForm-green.svg?style=true)](https://android-arsenal.com/details/1/3461)

Magic Form allows fast, easy and customisable creation of forms with validations.

Setup
-----

```groovy
dependencies {
    implementation 'io.github.julianfalcionelli:MagicForm:1.5.1'
}
```

Use
-----

All you need to do is create a MagicForm object and then gradually add the form fields with their validations.

Example:

```java
MagicForm mMagicForm = new MagicForm()
				.addField(
					new FormField(mEmailEditText)
						.addValidation(
							new ValidationRegex(Patterns.EMAIL_ADDRESS)
								.setMessage("invalid email"))
				)
				.addField(
					new FormField(mPasswordEditText)
						.addValidation(
							new ValidationNotEmpty()
								.setMessage("Required Field"))
				)
				.setListener(this);
```

Adds a listener to perform certain events if the form is valid or invalid.

Otherwise you can call the `isValid()` method.

### Validation mode

A very important feature is that MagicForm can validate in different ways, by setting one mode to the entire form or to each field.

Available modes:
- ON_VALIDATE: Default mode of the form. The form is validated with the call of `validate()` method.
- ON_CONTENT_CHANGE: The form is validated each time you change the content / state of the fields.
- ON_FOCUS_CHANGE: Fields are validated each time lose their focus.

### Validations
It allows you to create custom validations very easily through the Validations class, or can use the already available:

- ValidationLength
- ValidationMaxLength
- ValidationMinLength
- ValidationNotEmpty
- ValidationRegex
- ValidationChecked (Useful for Checkbox, Switch)
- more

If you want to reuse validations on various parts of your application we suggest you make a class extending of Validation. Also you can create it inline, when you are creating the form.

### MagicForm power

```java
MagicForm mMagicForm = new MagicForm(ValidationMode.ON_CONTENT_CHANGE)
				.addField(
					new FormField(mEmailEditText)
						.addValidation(
							new ValidationRegex(Patterns.EMAIL_ADDRESS)
								.setMessage("invalid email"))
				)
				.addField(
					new FormField(mPasswordEditText)
						.addValidation(
							new ValidationNotEmpty()
								.setMessage("Required Field"))
						.addValidation(
							new ValidationLength(2, 4))
				)
				.addField(
					new FormField(this, R.id.fieldEditText)
						.addValidation(
							new Validation<EditText>() {
								@Override
								public boolean isValid(EditText view) {
									return view.getText().equals("something");
								}
						}.setMessage("Invalid field!!!"))
				)
				.addField(
					new FormField(mCheckBox)
						.addValidation(
							new ValidationChecked(true))
				)
				.setListener(
					new ValidatorCallbacks() {
						@Override
						public void onSuccess() {
							Log.i("MagicForm", "Valid!");
						}

						@Override
						public void onFailed(List<FormError> errors) {
							Log.i("MagicForm", "Invalid!");
						}
				});
```


License
-----
    Copyright 2016 Julián Falcionelli

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

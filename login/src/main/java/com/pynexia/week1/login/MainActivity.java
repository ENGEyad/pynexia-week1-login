package com.pynexia.week1.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;
import android.view.autofill.AutofillManager;
public class MainActivity extends BaseActivity {
    private EditText identity, password;
    private TextView feedback;
    @Override protected void onCreate(Bundle state) {
        super.onCreate(state);
        showLayout(R.layout.activity_main);
        identity = findViewById(R.id.identity); password = findViewById(R.id.password);
        feedback = findViewById(R.id.feedback);
        findViewById(R.id.login).setOnClickListener(view -> login());
        TextWatcher watcher = new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void onTextChanged(CharSequence s, int start, int before, int count) { feedback.setText(R.string.feedback_empty); }
            public void afterTextChanged(Editable s) {}
        };
        identity.addTextChangedListener(watcher); password.addTextChangedListener(watcher);
    }
    private void login() {
        identity.setError(null); password.setError(null);
        String user = identity.getText().toString(), secret = password.getText().toString();
        if (user.trim().isEmpty()) { identity.setError(getString(R.string.identity_error)); identity.requestFocus(); return; }
        if (secret.isEmpty()) { password.setError(getString(R.string.password_error)); password.requestFocus(); return; }
        if (!DemoAuthenticator.authenticate(user, secret)) { feedback.setText(R.string.invalid); return; }
        AutofillManager autofill = getSystemService(AutofillManager.class);
        if (autofill != null) autofill.cancel();
        password.setText("");
        startActivity(new Intent(this, HomeActivity.class));
        finish();
    }
}

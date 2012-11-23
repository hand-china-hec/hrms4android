package com.hand.hrms4android.activity;

import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hand.hrms4android.R;
import com.hand.hrms4android.model.LoginModel;
import com.hand.hrms4android.model.Model;
import com.loopj.android.http.RequestParams;

public class LoginActivity extends BaseActivity {

	private EditText usernameEditText;
	private EditText passwordEditText;
	private Button loginButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		this.model = new LoginModel(this);
		bindAllViews();
	}

	private void bindAllViews() {
		usernameEditText = (EditText) findViewById(R.id.activity_login_textedit_username);
		passwordEditText = (EditText) findViewById(R.id.activity_login_textedit_password);
		loginButton = (Button) findViewById(R.id.activity_login_textedit_loginButton);

		loginButton.setOnClickListener(new LoginButtonClickListener());
	}

	@Override
	public void modelDidFinishedLoad(Model model) {
		List<Map<String, String>> dataset = model.getResult();
		String mobile_auto_status = dataset.get(0).get("mobile_auto_status");
		Toast.makeText(this, mobile_auto_status, Toast.LENGTH_LONG).show();
	}

	private class LoginButtonClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {

			String username = usernameEditText.getText().toString();
			String password = passwordEditText.getText().toString();

			RequestParams params = new RequestParams();
			params.put("user_name", username);
			params.put("user_password", password);
			params.put("langugae", "简体中文");
			params.put("user_language", "ZHS");
			params.put("is_ipad", "N");
			params.put("device_type", "Android");
			params.put("company_id", "1");
			params.put("role_id", "41");

			model.load(2, params);
		}

	}
}

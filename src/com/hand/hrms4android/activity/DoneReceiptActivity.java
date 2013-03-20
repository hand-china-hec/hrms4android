package com.hand.hrms4android.activity;

import java.util.Map;

import com.actionbarsherlock.view.MenuItem;
import com.hand.hrms4android.exception.ParseExpressionException;
import com.hand.hrms4android.network.NetworkUtil;
import com.hand.hrms4android.parser.Expression;
import com.hand.hrms4android.util.PlaceHolderReplacer;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class DoneReceiptActivity extends BaseReceiptActivity {

	private String urlKeyName;

	@Override
	protected void afterSuperOnCreateFinish(Bundle savedInstanceState) {
		super.afterSuperOnCreateFinish(savedInstanceState);

		try {
			urlKeyName = configReader
			        .getAttr(new Expression(
			                "/config/application/activity[@name='todo_list_activity']/request/url[@name='todo_list_query_url']/detail_page_url_column",
			                "name"));

			loadResources(getAbsolutePageUrl(listModel.currentItem()));
		} catch (ParseExpressionException e) {
			e.printStackTrace();
			Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
		}
	}

	@Override
	protected EmployeeCardInfo getEmployeeCardParams() {
		EmployeeCardInfo info = new EmployeeCardInfo();
		try {
			String configUrl = configReader
			        .getAttr(new Expression(
			                "/config/application/activity[@name='approve_detail_activity']/request/url[@name='employee_card_url']",
			                "value"));
			info.cardInfoUrl = PlaceHolderReplacer.replaceForValue(listModel.currentItem(), configUrl);
		} catch (ParseExpressionException e) {
			e.printStackTrace();
			info.cardInfoUrl = "";
		}

		try {
			String actionItemTextKeyName = configReader.getAttr(new Expression(
			        "/config/application/activity[@name='approve_detail_activity']/view/employee_action_item", "text"));
			info.displayName = PlaceHolderReplacer.replaceForValue(listModel.currentItem(), actionItemTextKeyName);
		} catch (ParseExpressionException e) {
			e.printStackTrace();
			info.displayName = "";
		}
		return info;
	}

	@Override
	protected void onPageableOptionsItemSelected(MenuItem item) {
		loadResources(getAbsolutePageUrl(listModel.currentItem()));
	}

	private String getAbsolutePageUrl(Map<String, String> record) {
		return NetworkUtil.getAbsoluteUrl(listModel.currentItem().get(urlKeyName));
	}

	private void loadResources(String absoluteUrl) {
		// 拿到当前指向的记录
		loadingProgress.setVisibility(View.VISIBLE);
		contentWebView.loadUrl(absoluteUrl);
	}
}